package br.com.leoguilbor.surl.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class NewUserDTO {

	@NotEmpty(message="Field cannot be empty")
	private String login;
	
	@NotEmpty(message="Field cannot be empty")
	@Min(value=8)
	private String password;

	public NewUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewUserDTO(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
