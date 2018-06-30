package quem.me.ajuda.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quem.me.ajuda.models.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	Collection<Rating> findByStudent(Long userid);
}
