package br.com.leoguilbor.surl.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.leoguilbor.surl.dto.CredentialsDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		 setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.jwtUtils = jwtUtils;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			CredentialsDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredentialsDTO.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getLogin(),
					creds.getPassword(), new ArrayList<>());
			
			Authentication auth = authenticationManager.authenticate(authToken);
			System.out.println("teste");
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) {
		String username = ((UserSecurity) auth.getPrincipal()).getUsername();
		String token = jwtUtils.generateToken(username);
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers", "Authorization");
	}

	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json());
		}

		private String json() {
			long date = new Date().getTime();
			return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
					+ "\"message\": \"Email ou senha inválidos\", " + "\"path\": \"/login\"}";
		}
	}
}
