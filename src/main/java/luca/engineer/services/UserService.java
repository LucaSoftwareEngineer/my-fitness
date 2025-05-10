package luca.engineer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import luca.engineer.dto.ParamRegisterUser;
import luca.engineer.models.User;
import luca.engineer.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final String USER_ROLE = "USER";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean registerUser(ParamRegisterUser json) throws Exception {
		if (json.getPassword().equals(json.getPasswordConfirm())) {
			User user = new User(null, json.getNome(), json.getCognome(), json.getEmail(), passwordEncoder.encode(json.getPassword()), json.getDataNascita(), null, null);
			userRepository.save(user);
		} else {
			new Exception("le password non coincidono");
		}
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("utente non trovato"));		
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(user.getPassword())
				.roles(USER_ROLE)
				.build();
	}
	
}
