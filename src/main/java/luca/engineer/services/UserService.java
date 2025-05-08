package luca.engineer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luca.engineer.dto.ParamRegisterUser;
import luca.engineer.models.User;
import luca.engineer.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean registerUser(ParamRegisterUser json) throws Exception {
		User user = new User(null, json.getNome(), json.getCognome(), json.getEmail(), json.getPassword(), json.getDataNascita());
		userRepository.save(user);
		return true;
	}
	
}
