package luca.engineer.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParamRegisterUser {

	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Date dataNascita;
	private String passwordConfirm;
	
}
