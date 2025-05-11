package luca.engineer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import luca.engineer.dto.ParamAggiungiAttività;
import luca.engineer.services.AttivitàService;

@Controller
public class AttivitàController {

	@Autowired
	AttivitàService attivitàService;
	
	@PostMapping("/api/attivita/aggiungi")
	public String aggiungiAttività(@ModelAttribute ParamAggiungiAttività json, Model model) {
		try {
			attivitàService.aggiungiAttività(json);
			return "redirect:../../app/attivita/aggiungi/success";
		} catch (Exception e) {
			model.addAttribute("errore", "Attenzione! si è verificato un errore, effettua il logout e riprova");
			return "redirect:../../app/attivita/aggiungi";
		}
	}
	
	@DeleteMapping("/api/attivita/elimina")
	public boolean eliminaAttività(@RequestParam Long id, HttpSession session) throws Exception {
		Long idAttività = id;
		Long idUtente = new Long((Long)session.getAttribute("idUser"));
		attivitàService.eliminaAttività(idAttività, idUtente);
		return true;
	}
	
}
