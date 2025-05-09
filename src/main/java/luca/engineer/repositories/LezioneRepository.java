package luca.engineer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import luca.engineer.models.Lezione;

@Repository
public interface LezioneRepository extends JpaRepository<Lezione, Long> {

}
