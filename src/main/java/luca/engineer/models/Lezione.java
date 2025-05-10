package luca.engineer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="my-fitness-lezioni")
public class Lezione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idLesson;
	private String titolo;
	private String descrizione;
	private String url;
	
}
