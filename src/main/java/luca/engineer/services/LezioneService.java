package luca.engineer.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luca.engineer.dto.ParamAggiungiLezione;
import luca.engineer.models.Attività;
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
	
	public void eliminaLezione(Long idLezione, Long idUtente) throws Exception {
		Lezione lezione = lezioneRepository.findById(idLezione).orElseThrow(() -> new Exception());
		User user = userRepository.findById(idUtente).orElseThrow(() -> new Exception());
		
		List<Lezione> lezioniDiUser = user.getLezioni();
		
		Iterator it = lezioniDiUser.iterator();
		while (it.hasNext()) {
			Lezione cerca = (Lezione) it.next();
			if (cerca.equals(lezione)) {
				lezioniDiUser.remove(cerca);
				user.setLezioni(lezioniDiUser);
				userRepository.save(user);
				lezioneRepository.delete(cerca);
			}
		}
	}
	
}
