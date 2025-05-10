package luca.engineer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luca.engineer.dto.ParamAggiungiLezione;
import luca.engineer.models.Lezione;
import luca.engineer.models.User;
import luca.engineer.repositories.LezioneRepository;
import luca.engineer.repositories.UserRepository;

@Service
public class LezioneService {

	@Autowired
	LezioneRepository lezioneRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void aggiungiLezione(ParamAggiungiLezione json) throws Exception {
		Lezione tmp = new Lezione(null, json.getTitolo(), json.getDescrizione(), json.getUrl());
		Lezione nuova = lezioneRepository.save(tmp);
		User user = userRepository.findById(json.getIdUser()).orElseThrow(() -> new Exception());
		user.lezioni.add(nuova);
		userRepository.save(user);
	}
	
}
