package luca.engineer.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import luca.engineer.dto.ParamRegisterUser;
import luca.engineer.services.UserService;

@RestController
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/user/register")
	public HashMap<String, Boolean> registerUser(@RequestBody ParamRegisterUser json) throws Exception {
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("response",userService.registerUser(json));
		return response;
	}
	
}
