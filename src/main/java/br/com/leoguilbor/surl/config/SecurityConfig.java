package br.com.leoguilbor.surl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_GET_MATCHERS = {"/*","/h2-console"};
	private static final String[] PRIVATE_GET_MATCHERS = {"/shortUrls"};
	private static final String[] PUBLIC_POST_MATCHERS = {"/shortUrls"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PRIVATE_GET_MATCHERS).authenticated()		
			.antMatchers(HttpMethod.GET, PUBLIC_GET_MATCHERS).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_POST_MATCHERS).permitAll()
			.anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}