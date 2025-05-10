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
@Table(name="my-fitness-attività")
public class Attività {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idAttività;
	private String esercizio;
	private int serie;
	private int ripetizioni;
	
}
