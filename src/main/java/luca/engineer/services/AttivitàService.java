package luca.engineer.services;

import java.util.Iterator;
import java.util.List;

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
	AttivitàRepository attivitàRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void aggiungiAttività(ParamAggiungiAttività json) throws Exception {
		Attività attività = new Attività(null, json.getEsercizio(), json.getSerie(), json.getRipetizioni());
		Attività nuovaAttività = attivitàRepository.save(attività);
		Long idAttività = nuovaAttività.getIdAttività();
		User user = userRepository.findById(json.getIdUser()).orElseThrow(() -> new Exception());
		user.getAttività().add(nuovaAttività);
		userRepository.save(user);
	}
	
	public void eliminaAttività(Long idAttività, Long idUtente) throws Exception {
		Attività attività = attivitàRepository.findById(idAttività).orElseThrow(() -> new Exception());
		User user = userRepository.findById(idUtente).orElseThrow(() -> new Exception());
		
		List<Attività> attivitàDiUser = user.getAttività();
		
		Iterator it = attivitàDiUser.iterator();
		while (it.hasNext()) {
			Attività cerca = (Attività) it.next();
			if (cerca.equals(attività)) {
				attivitàDiUser.remove(cerca);
				user.setAttività(attivitàDiUser);
				userRepository.save(user);
				attivitàRepository.delete(cerca);
			}
		}
	}
	
}
