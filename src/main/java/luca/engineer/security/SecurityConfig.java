package luca.engineer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(req -> {
				req.requestMatchers("/").permitAll();
				req.requestMatchers("/login").permitAll();
				req.requestMatchers("/register").permitAll();
				req.requestMatchers(HttpMethod.POST,"/api/user/register").permitAll();
				req.requestMatchers("/app/**").authenticated();
				req.requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll();
			});
		
		return http.build();
	}
	
}
