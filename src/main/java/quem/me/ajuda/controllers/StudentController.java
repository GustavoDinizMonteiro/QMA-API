package quem.me.ajuda.controllers;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.services.StudentService;

@Controller
@CrossOrigin
@RequestMapping(value = Endpoints.STUDENT_ENDPONT)
public class StudentController {
	@Autowired
	private StudentService service;
	
	@PostMapping
	public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
		return new ResponseEntity<>(this.service.create(student), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Boolean> update(@PathVariable Long id, @Valid @RequestBody Student student) {
		Boolean successful = this.service.update(id, student);
		
		if (successful) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Student>> getAll() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> getById(@PathVariable Long id) {
		Optional<Student> student = this.service.getById(id);
		
		if (student.isPresent())
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean successful = this.service.delete(id);
		
		if (successful) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
