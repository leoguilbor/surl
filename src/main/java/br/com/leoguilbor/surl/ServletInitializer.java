package br.com.leoguilbor.surl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import br.com.leoguilbor.surl.domain.User;
import br.com.leoguilbor.surl.dto.NewUserDTO;
import br.com.leoguilbor.surl.service.UserService;

public class ServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(SurlApplication.class);
	}

}

