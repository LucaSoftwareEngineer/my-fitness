package luca.engineer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import jakarta.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import luca.engineer.models.Attività;
import luca.engineer.models.Lezione;
import luca.engineer.models.User;
import luca.engineer.repositories.AttivitàRepository;
import luca.engineer.repositories.LezioneRepository;
import luca.engineer.repositories.UserRepository;

@Controller
public class PagesController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LezioneRepository lezioneRepository;
	
	@Autowired
	AttivitàRepository attivitàRepository;
	
	@GetMapping("/")
	public String indexPage() {
		return "login"; //redirect alla login
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@GetMapping("/register/success")
	public String registerSuccessPage() {
		return "register-success";
	}
	
	@GetMapping("/app/")
	public String dashboardPage(HttpSession session, Model model, @AuthenticationPrincipal UserDetails currentUser) throws Exception {
		User user = (User) userRepository.findByEmail(currentUser.getUsername()).orElseThrow(() -> new Exception());
        session.setAttribute("idUser", user.getIdUser());
		return "dashboard";
	}
	
	/*************************** BEGIN LEZIONI ************************************************/
	
	@GetMapping("/app/lezioni")
	public String lezioniPage(HttpSession session, Model model) throws Exception {
		Long idUser = new Long((long) session.getAttribute("idUser"));
		User user = userRepository.findById(idUser).orElseThrow(() -> new Exception());
		model.addAttribute("lezioni", user.getLezioni());
		return "lezioni";
	}
	
	@GetMapping("/app/lezioni/{idLezione}")
	public String lezioneOpenPage(@PathVariable Long idLezione, Model model) throws Exception {
		Lezione lezione = lezioneRepository.findById(idLezione).orElseThrow(() -> new Exception());
		model.addAttribute("titolo", lezione.getTitolo());
		model.addAttribute("descrizione", lezione.getDescrizione());
		model.addAttribute("url", lezione.getUrl());
		model.addAttribute("id", lezione.getIdLesson());
		return "lezione-open";
	}
	
	@GetMapping("/app/lezioni/aggiungi")
	public String lezioniAggiungiPage(HttpSession session, Model model) {
		Long idUser = new Long((long) session.getAttribute("idUser"));
		model.addAttribute("idUser", idUser);
		return "lezioni-aggiungi";
	}
	
	@GetMapping("app/lezioni/aggiungi/success")
	public String lezioneAggiungiSuccessPage(Model model) {
		return "lezione-aggiungi-success";
	}
	
	@GetMapping("app/lezione/rimuovi/success")
	public String lezioneRimuoviSuccessPage(Model model) {
		return "lezione-rimuovi-success";
	}
	
	/*************************** END LEZIONI ************************************************/
	
	/*************************** BEGIN ATTIVITA' ************************************************/
	
	@GetMapping("/app/attivita")
	public String attivitàPage(HttpSession session, Model model) throws Exception {
		Long idUser = new Long((long) session.getAttribute("idUser"));
		User user = userRepository.findById(idUser).orElseThrow(() -> new Exception());
		model.addAttribute("attività", user.getAttività());
		return "attività";
	}
	
	@GetMapping("/app/attivita/{idAttività}")
	public String attivitàOpenPage(@PathVariable Long idAttività, Model model) throws Exception {
		Attività attività = attivitàRepository.findById(idAttività).orElseThrow(() -> new Exception());
		model.addAttribute("esercizio", attività.getEsercizio());
		model.addAttribute("serie", attività.getSerie());
		model.addAttribute("ripetizioni", attività.getRipetizioni());
		model.addAttribute("idAttivita", idAttività);
		return "attività-open";
	}
	
	@GetMapping("/app/attivita/aggiungi")
	public String attivitàAggiungiPage(HttpSession session, Model model) {
		Long idUser = new Long((long) session.getAttribute("idUser"));
		model.addAttribute("idUser", idUser);
		return "attività-aggiungi";
	}
	
	@GetMapping("app/attivita/aggiungi/success")
	public String attivitàAggiungiSuccessPage(Model model) {
		return "attività-aggiungi-success";
	}
	
	@GetMapping("app/attivita/rimuovi/success")
	public String attivitàRimuoviSuccessPage(Model model) {
		return "attività-rimuovi-success";
	}
	
	/*************************** END ATTIVITA' ************************************************/
	
	@GetMapping("/app/profilo")
	public String profiloPage(HttpSession session, Model model) throws Exception {
		Long idUser = new Long((Long)session.getAttribute("idUser"));
		User user = userRepository.findById(idUser).orElseThrow(() -> new Exception());
		model.addAttribute("nome", user.getNome());
		model.addAttribute("cognome", user.getCognome());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("dataNascita", user.getDataNascita());
		return "profilo";
	}
	
}
