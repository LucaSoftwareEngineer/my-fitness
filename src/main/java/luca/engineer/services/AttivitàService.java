package luca.engineer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luca.engineer.dto.ParamAggiungiAttività;
import luca.engineer.models.Attività;
import luca.engineer.models.User;
import luca.engineer.repositories.AttivitàRepository;
import luca.engineer.repositories.UserRepository;

@Service
public class AttivitàService {
	
	@Autowired
	AttivitàRepository attivitàService;
	
	@Autowired
	UserRepository userRepository;
	
	public void aggiungiAttività(ParamAggiungiAttività json) throws Exception {
		Attività attività = new Attività(null, json.getEsercizio(), json.getSerie(), json.getRipetizioni());
		Attività nuovaAttività = attivitàService.save(attività);
		Long idAttività = nuovaAttività.getIdAttività();
		User user = userRepository.findById(json.getIdUser()).orElseThrow(() -> new Exception());
		user.getAttività().add(nuovaAttività);
		userRepository.save(user);
	}
	
}
