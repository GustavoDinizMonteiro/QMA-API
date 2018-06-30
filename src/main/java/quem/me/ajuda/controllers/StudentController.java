package quem.me.ajuda.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quem.me.ajuda.constants.CacheKeys;
import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.MinimalStudent;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.services.StudentService;

@RestController
@CrossOrigin
@RequestMapping(Endpoints.STUDENT_ENDPONT)
public class StudentController {
	@Autowired
	private StudentService service;
	
	@PostMapping
	@CacheEvict(value = CacheKeys.STUDENTS, allEntries = true)
	public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
		return new ResponseEntity<>(this.service.create(student), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(
			@PathVariable Long id,
			@Valid @RequestBody Student student,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

		return ResponseEntity.ok(this.service.update(id, student, token));
	}
	
	@GetMapping
	@Cacheable(CacheKeys.STUDENTS)
	public Collection<MinimalStudent> getAll() {
		return this.service.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getById(
			@PathVariable Long id,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		return ResponseEntity.ok(this.service.getByIdWithCheck(id, token));
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = CacheKeys.STUDENTS, allEntries = true)
	public ResponseEntity<HttpStatus> delete(
			@PathVariable Long id,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		this.service.delete(id, token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
