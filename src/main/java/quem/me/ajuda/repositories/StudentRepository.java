package quem.me.ajuda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quem.me.ajuda.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	Student findByRegistration(String registration);
}
