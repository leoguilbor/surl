package br.com.leoguilbor.surl.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private String password;
	private Collection<? extends GrantedAuthority> authority;
	public UserSecurity() {
		super();
	}
	public UserSecurity(Long id, String login, String password, List<GrantedAuthority> authority) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.authority = authority;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authority;
	}

	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {

		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public Long getId() {
		return id;
	}


}
