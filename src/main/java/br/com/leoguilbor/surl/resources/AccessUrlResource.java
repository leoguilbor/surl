package br.com.leoguilbor.surl.resources;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.leoguilbor.surl.domain.ShortUrl;
import br.com.leoguilbor.surl.service.ShortUrlService;

@RestController

public class AccessUrlResource {
	@Autowired
	private ShortUrlService service;

	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public ResponseEntity<?> findOne(@PathVariable String uid, @RequestHeader(value="Authorization",defaultValue="") String token) throws URISyntaxException {
		
		ShortUrl surl = service.findOne(uid,token);
		
		if (surl.getUrl().startsWith("http://") || surl.getUrl().startsWith("https://")) {
			return ResponseEntity.status(HttpStatus.FOUND).header("Location", surl.getUrl()).build();	
		}
		
		return ResponseEntity.status(HttpStatus.FOUND).header("Location", "http://"+ surl.getUrl()).build();
	}
}