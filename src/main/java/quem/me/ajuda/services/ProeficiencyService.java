package quem.me.ajuda.services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quem.me.ajuda.models.Proeficiency;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.repositories.ProeficiencyRepository;
import quem.me.ajuda.repositories.StudentRepository;

@Service
public class ProeficiencyService {
	@Autowired
	private ProeficiencyRepository proeficiencyRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Transactional
	public void save(Long studentId, Proeficiency proeficiency) {
		Student info = this.studentRepository.getOne(studentId);
	
			proeficiency.setTutorInfo(info.getTutorInfo());
		this.proeficiencyRepository.save(proeficiency);
	}
	
	public Collection<Proeficiency> getProeficienciesByStudent(Long studentId) {
		return this.proeficiencyRepository.findByTutorInfo(studentId);
	}

}
