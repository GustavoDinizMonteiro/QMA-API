package quem.me.ajuda.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public void addProeficiency(@PathVariable Long studentId, @RequestBody Proeficiency proeficiency) {
		this.service.save(studentId, proeficiency);
	}
	
	@GetMapping(value = "/{studentId}/proeficiency")
	public  Collection<Proeficiency> addProeficiency(@PathVariable Long studentId) {
		return this.service.getProeficienciesByStudent(studentId);
	}
}
