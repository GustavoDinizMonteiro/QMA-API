package quem.me.ajuda.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.services.StudentService;

@Controller
@CrossOrigin
@RequestMapping(value = Endpoints.STUDENT_ENDPONT)
public class StudentController {
	@Autowired
	private StudentService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Student> create(@RequestBody Student student) {
		return new ResponseEntity<>(this.service.create(student), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Boolean> update(@RequestBody Student student) {
		Boolean successful = this.service.update(student);
		
		if (successful) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Student>> getAll() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> getById(@PathVariable Long id) {
		Optional<Student> student = this.service.getById(id);
		
		if (student.isPresent())
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean successful = this.service.delete(id);
		
		if (successful) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
