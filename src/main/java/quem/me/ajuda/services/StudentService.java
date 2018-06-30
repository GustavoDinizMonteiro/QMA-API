package quem.me.ajuda.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quem.me.ajuda.exceptions.IncompatibleUserIDAndTokenException;
import quem.me.ajuda.exceptions.UserNotFoundException;
import quem.me.ajuda.models.MinimalStudent;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private AuthenticationService authService;
	
	@Transactional
	public Student create(Student student) {
		student.setPassword(BCrypt.hashpw(student.getPassword(), BCrypt.gensalt()));
		return this.repository.save(student);
	}
	
	@Transactional
	public Student update(Long id, Student student, String token) {
		this.checkPermission(id, token);
		
		return this.repository.save(student);
	}
	
	public List<MinimalStudent> getAll() {
		return this.repository.findAll().stream()
				.map(student -> new MinimalStudent(student))
				.collect(Collectors.toList());
	}
	
	
	public Student getByIdWithCheck(Long id, String token) {
		this.checkPermission(id, token);
		return this.getById(id);
	}
	
	@Transactional
	public void delete(Long id, String token) {
		this.checkPermission(id, token);
		
		this.repository.deleteById(id);
	}
	
	public Student getByRegistration(String registration) {
		return this.repository.findByRegistration(registration)
				.map(user -> user)
				.orElseThrow(() -> new UserNotFoundException());
	}

	public Student getById(Long id) {
		return this.repository.findById(id)
				.map(user -> user)
				.orElseThrow(() -> new UserNotFoundException());
	}
	
	private void checkPermission(Long id, String token) {
		Student idStudent = this.getById(id);
		Student tokenStudent = this.authService.getUserFromToken(token);

		if (!idStudent.equals(tokenStudent))
			throw new IncompatibleUserIDAndTokenException();
	}
}
