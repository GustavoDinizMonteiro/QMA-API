package quem.me.ajuda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quem.me.ajuda.models.Proeficiency;

@Repository
public interface ProeficiencyRepository extends JpaRepository<Proeficiency, Long>{

}
