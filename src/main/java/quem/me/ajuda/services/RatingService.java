package quem.me.ajuda.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quem.me.ajuda.models.Rating;
import quem.me.ajuda.repositories.RatingRepository;

@Service
public class RatingService {
	@Autowired
	private RatingRepository repository;
	
	@Autowired
	private AuthenticationService authService;

	public Rating create(Rating rating) {
		return this.repository.save(rating);
	}

	public Collection<Rating> getAll(String token) {
		return this.repository.findByStudentId(this.authService.getUserFromToken(token).getId());
	}

	public Rating getById(Long id) {
		return this.repository.findById(id).get();
	}

	public Boolean edit(Long id, Rating rating) {
		if(this.repository.findById(id).isPresent()) {
			this.repository.save(rating);
			return true;
		}
		return false;
	}

	public void delete(Long id) {
		this.repository.deleteById(id);
	}
}
