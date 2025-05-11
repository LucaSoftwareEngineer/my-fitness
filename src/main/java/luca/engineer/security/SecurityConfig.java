package luca.engineer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(req -> {
				req.requestMatchers("/").permitAll();
				req.requestMatchers("/login").permitAll();
				req.requestMatchers("/register").permitAll();
				req.requestMatchers("/register/success").permitAll();
				req.requestMatchers(HttpMethod.POST,"/api/user/register").permitAll();
				req.requestMatchers(HttpMethod.POST,"/api/lezione/aggiungi").permitAll();
				req.requestMatchers(HttpMethod.DELETE,"/api/attivita/elimina").permitAll();
				req.requestMatchers("/app/**").authenticated();
				req.requestMatchers("/api/**").permitAll();
				req.requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll();
			})
			.formLogin(login -> {
				login
					.loginPage("/login")
					.defaultSuccessUrl("/app/",true)
					.permitAll();
			})
			.logout(logout -> {
				logout
					.logoutUrl("/logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll();
			});
		
		
		return http.build();
	}
	
}
