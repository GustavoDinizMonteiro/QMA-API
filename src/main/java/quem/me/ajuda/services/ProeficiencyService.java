package quem.me.ajuda.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quem.me.ajuda.models.Proeficiency;
import quem.me.ajuda.repositories.ProeficiencyRepository;

@Service
public class ProeficiencyService {
	@Autowired
	private ProeficiencyRepository repository;

	public void save(Long studentId, Proeficiency proeficiency) {
		this.repository.save(proeficiency);
	}
	
	public Collection<Proeficiency> getProeficienciesByStudent() {
		return this.repository.findAll();
	}

}
