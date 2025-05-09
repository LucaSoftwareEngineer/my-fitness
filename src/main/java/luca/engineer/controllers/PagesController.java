package luca.engineer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

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
	public String dashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/app/lezioni")
	public String lezioniPage() {
		return "lezioni";
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
