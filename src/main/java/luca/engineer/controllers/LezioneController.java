package luca.engineer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import luca.engineer.dto.ParamAggiungiLezione;
import luca.engineer.services.LezioneService;

@Controller
public class LezioneController {

	@Autowired
	LezioneService lezioneService;
	
	@PostMapping("/api/lezione/aggiungi")
	public String aggiungiLezione(@ModelAttribute ParamAggiungiLezione json, Model model) {
		try {
			lezioneService.aggiungiLezione(json);
			return "redirect:../../app/lezioni/aggiungi/success";
		} catch (Exception e) {
			model.addAttribute("errore", "Attenzione! si è verificato un errore, effettua il logout e riprova");
			return "redirect:../../app/lezioni/aggiungi";
		}
	}
	
	@DeleteMapping("/api/lezione/elimina")
	public boolean eliminaAttività(@RequestParam Long id, HttpSession session) throws Exception {
		Long idLezione = id;
		Long idUtente = new Long((Long)session.getAttribute("idUser"));
		lezioneService.eliminaLezione(idLezione, idUtente);
		return true;
	}
	
}
