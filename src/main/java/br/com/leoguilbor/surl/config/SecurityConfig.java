package br.com.leoguilbor.surl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.leoguilbor.surl.security.JWTAuthenticationFilter;
import br.com.leoguilbor.surl.security.JWTAuthorizationFilter;
import br.com.leoguilbor.surl.security.JwtUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserDetailsService userDetailsService;

	private static final String[] PUBLIC_MATCHERS = { "/h2/**" };
	private static final String[] PUBLIC_GET_MATCHERS = { "/*" };
	private static final String[] PUBLIC_POST_MATCHERS = { "/shortUrls" };
	private static final String[] PRIVATE_GET_MATCHERS = { "/shortUrls" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers(HttpMethod.GET, PRIVATE_GET_MATCHERS).authenticated()
				.antMatchers(HttpMethod.GET, PUBLIC_GET_MATCHERS).permitAll()
				.antMatchers(HttpMethod.POST, PUBLIC_POST_MATCHERS).permitAll().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtils));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtils, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
