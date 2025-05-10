package luca.engineer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import luca.engineer.models.User;
import luca.engineer.repositories.UserRepository;

@Controller
public class PagesController {

	@Autowired
	UserRepository userRepository;
	
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
	
	@GetMapping("/app/lezioni")
	public String lezioniPage(HttpSession session, Model model) throws Exception {
		Long idUser = new Long((long) session.getAttribute("idUser"));
		User user = userRepository.findById(idUser).orElseThrow(() -> new Exception());
		model.addAttribute("lezioni", user.getLezioni());
		return "lezioni";
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
	
	@GetMapping("/app/attività")
	public String attivitàPage() {
		return "attività";
	}
	
	@GetMapping("/app/profilo")
	public String profiloPage() {
		return "profilo";
	}
	
}
