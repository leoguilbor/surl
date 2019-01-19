package br.com.leoguilbor.surl.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtils {
	@Value("${jwt.secret}")
	private String jwt_secret;
	
	@Value("${jwt.expiration}")
	private Long jwt_expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + jwt_expiration))
				.signWith(SignatureAlgorithm.HS512,jwt_secret.getBytes())
				.compact();
				
	}

	public boolean validToken(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(jwt_secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
}
}
