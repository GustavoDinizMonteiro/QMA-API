package quem.me.ajuda.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quem.me.ajuda.exceptions.UserNotFoundException;
import quem.me.ajuda.models.MinimalStudent;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;
	
	@Transactional
	public Student create(Student student) {
		student.setPassword(BCrypt.hashpw(student.getPassword(), BCrypt.gensalt()));
		return this.repository.save(student);
	}
	
	@Transactional
	public Student update(Long id, Student student) {
		if(this.exists(id))
			return this.repository.save(student);
		throw new UserNotFoundException();
	}
	
	public List<MinimalStudent> getAll() {
		return this.repository.findAll().stream()
				.map(student -> new MinimalStudent(student))
				.collect(Collectors.toList());
	}
	
	public Student getById(Long id) {
		return this.repository.findById(id)
			.map(user -> user)
			.orElseThrow(() -> new UserNotFoundException());
	}
	
	@Transactional
	public void delete(Long id) {
		if(!this.exists(id)) 
			throw new UserNotFoundException();
		
		this.repository.deleteById(id);
	}
	
	public Student getByRegistration(String registration) {
		return this.repository.findByRegistration(registration)
				.map(user -> user)
				.orElseThrow(() -> new UserNotFoundException());
	}
	
	private Boolean exists(Long id) {
		return this.repository.findById(id).isPresent();
	}
}
