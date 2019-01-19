package br.com.leoguilbor.surl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.surl.domain.User;
import br.com.leoguilbor.surl.repository.UserRepository;
import br.com.leoguilbor.surl.security.UserSecurity;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opU = userRepo.findByLogin(username);
		
		User u = opU.orElseThrow(() -> new UsernameNotFoundException(username));
		List<GrantedAuthority> gAuthority = new ArrayList<>();
		gAuthority.add(new SimpleGrantedAuthority("ADMIN"));
		return new UserSecurity(u.getId(),u.getLogin(),u.getPassword(),gAuthority);
	}

}
