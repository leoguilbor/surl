package br.com.leoguilbor.surl.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.leoguilbor.surl.domain.User;

public class UserDTO {

	@Id
	private Long id;
	@NotEmpty
	private String login;
	@JsonIgnore
	@NotEmpty
	private String password;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.login = user.getLogin();
		this.password = user.getPassword();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
