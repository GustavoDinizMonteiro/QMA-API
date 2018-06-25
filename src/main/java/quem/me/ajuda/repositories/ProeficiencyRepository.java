package quem.me.ajuda.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import quem.me.ajuda.models.Proeficiency;

@Repository
public interface ProeficiencyRepository extends JpaRepository<Proeficiency, Long>{
	@Query("select p from Proeficiency p where p.tutorInfo.id = ?1")
	Collection<Proeficiency> findByTutorInfo(Long id);
}
