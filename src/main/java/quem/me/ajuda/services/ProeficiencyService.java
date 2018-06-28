package quem.me.ajuda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quem.me.ajuda.models.Proeficiency;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.repositories.ProeficiencyRepository;

@Service
public class ProeficiencyService {
	@Autowired
	private StudentService userService;
	
	@Autowired
	private ProeficiencyRepository proeficiencyRepository;
	
	public Proeficiency addProeficiency(Long studentId, Proeficiency proeficiency) {
		Student student = this.userService.getById(studentId);
		proeficiency = this.proeficiencyRepository.save(proeficiency);
		student.getTutorInfo().getProeficiencies().add(proeficiency);
		this.userService.create(student);
		return proeficiency;
	}

	public Proeficiency editProeficiency(Long id, Proeficiency proeficiency) {
		if (this.proeficiencyRepository.findById(id).isPresent()) {
			return this.proeficiencyRepository.save(proeficiency); 			
		}
		return null;
	}

	public Boolean deleteProeficiency(Long id) {
		this.proeficiencyRepository.deleteById(id);
		return true;
	}
}
