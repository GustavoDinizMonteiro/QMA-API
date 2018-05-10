package quem.me.ajuda.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quem.me.ajuda.models.Student;
import quem.me.ajuda.repositories.StudentRepository;

@Service
@Transactional
public class StudentService {
	@Autowired
	private StudentRepository repository;
	
	public Student create(Student student) {
		return this.repository.save(student);
	}
	
	public Boolean update(Student student) {
		if(this.repository.existsById(student.getId())) {
			this.repository.save(student);
			return true;
		}
		return false;
	}
	
	public Collection<Student> getAll() {
		return this.repository.findAll();
	}
	
	public Optional<Student> getById(Long id) {
		return Optional.ofNullable(this.repository.getOne(id));
	}
	
	public Boolean delete(Long id) {
		if(this.repository.existsById(id)) {
			this.repository.deleteById(id);
			return true;
		}
		return false;
	}
}
