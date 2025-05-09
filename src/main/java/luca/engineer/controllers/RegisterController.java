package luca.engineer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import luca.engineer.dto.ParamRegisterUser;
import luca.engineer.services.UserService;


@Controller
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/user/register")
	public String registerUser(@ModelAttribute ParamRegisterUser json, Model model) {
		try {
			userService.registerUser(json);
			return "redirect:/register/success";
		} catch (Exception e) {
			model.addAttribute("errore", "l'email che stai tentando di utilizzare è associata ad un utente esistente");
			return "register";
		}
	}
	
}
