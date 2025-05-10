package luca.engineer.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import luca.engineer.dto.ParamAggiungiLezione;
import luca.engineer.services.LezioneService;

@Controller
public class LezioneController {

	@Autowired
	LezioneService lezioneService;
	
	@PostMapping("/api/lezione/aggiungi")
	public String aggiungiLezione(@ModelAttribute ParamAggiungiLezione json, Model model) {
		String idUserStr = json.getIdUser().toString();
		String idUser = Base64.getEncoder().encodeToString(idUserStr.getBytes());
		try {
			lezioneService.aggiungiLezione(json);
			return "redirect:../../app/lezioni/aggiungi/success?id=" + idUser;
		} catch (Exception e) {
			model.addAttribute("errore", "Attenzione! si è verificato un errore, effettua il logout e riprova");
			return "redirect:../../app/lezioni/aggiungi?id=" + idUser;
		}
	}
	
}
