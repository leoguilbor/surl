package br.com.leoguilbor.surl.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;

	public CredentialsDTO() {
		super();
		// TODO Auto-generated constructor stub
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
