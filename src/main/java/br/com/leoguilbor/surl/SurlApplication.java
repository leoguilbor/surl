package br.com.leoguilbor.surl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.leoguilbor.surl.domain.User;
import br.com.leoguilbor.surl.repository.UserRepository;

@SpringBootApplication
public class SurlApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	public static void main(String[] args) {
		
		SpringApplication.run(SurlApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		if (userRepo.existsByLogin("admin") == false) { 
			User u = new User(1L,"admin",bCrypt.encode("123deoliveira4"));	
			userRepo.save(u);
		}
	}
}