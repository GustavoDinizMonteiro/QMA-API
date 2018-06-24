package quem.me.ajuda.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.Proeficiency;
import quem.me.ajuda.services.ProeficiencyService;

@Controller
@CrossOrigin
@RequestMapping(value = Endpoints.STUDENT_ENDPONT)
public class ProeficiencyController {
	@Autowired
	private ProeficiencyService service;

	@PostMapping(value = "/{studentId}/proeficiency")
	public void addProeficiency(@PathVariable Long studentId, Proeficiency proeficiency) {
		this.service.save(studentId, proeficiency);
	}
	
	@GetMapping(value = "/{studentId}/proeficiency")
	public Collection<Proeficiency> addProeficiency(@PathVariable Long studentId) {
		return this.service.getProeficienciesByStudent();
	}
}
