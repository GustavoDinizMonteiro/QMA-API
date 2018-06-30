package quem.me.ajuda.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.Rating;
import quem.me.ajuda.services.RatingService;

@RestController
@CrossOrigin
@RequestMapping(value = Endpoints.RATING_ENDPOINT)
public class RatingController {
	@Autowired
	private RatingService service;
	
	@PostMapping
	public ResponseEntity<Rating> create(@Valid @RequestBody Rating rating) {
		return ResponseEntity.ok(this.service.create(rating));
	}
	
	@GetMapping
	public Collection<Rating> getAll() {
		return this.service.getAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Rating> getById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getById(id));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Boolean> edit(@PathVariable Long id, @Valid @RequestBody Rating rating) {
		return ResponseEntity.ok(this.service.edit(id, rating));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
