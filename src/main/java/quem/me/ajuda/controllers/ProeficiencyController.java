package quem.me.ajuda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.Proeficiency;
import quem.me.ajuda.services.ProeficiencyService;

@RestController
@CrossOrigin
@RequestMapping(value = Endpoints.STUDENT_ENDPONT)
public class ProeficiencyController {
	@Autowired
	private ProeficiencyService service;

	@PostMapping(value = "/{studentId}/proeficiency")
	public ResponseEntity<Proeficiency> addProeficiency(@PathVariable Long studentId, @RequestBody Proeficiency proeficiency) {
		return new ResponseEntity<>(this.service.addProeficiency(studentId, proeficiency), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{studentId}/proeficiency/{id}")
	public ResponseEntity<Proeficiency> editProeficiency(@PathVariable Long id, @RequestBody Proeficiency proeficiency) {
		return new ResponseEntity<>(this.service.editProeficiency(id, proeficiency), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{studentId}/proeficiency/{id}")
	public ResponseEntity<Boolean> deleteProeficiency(@PathVariable Long id) {
		return new ResponseEntity<>(this.service.deleteProeficiency(id), HttpStatus.OK);
	}
}
