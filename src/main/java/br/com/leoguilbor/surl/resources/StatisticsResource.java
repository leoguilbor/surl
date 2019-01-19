package br.com.leoguilbor.surl.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leoguilbor.surl.domain.ShortUrl;
import br.com.leoguilbor.surl.dto.NewShortUrlDTO;
import br.com.leoguilbor.surl.dto.ShortUrlDTO;
import br.com.leoguilbor.surl.exception.ObjectNotFoundException;
import br.com.leoguilbor.surl.exception.UrlNotShortedException;
import br.com.leoguilbor.surl.service.ShortUrlService;

@RestController
@RequestMapping(value = "/shortUrlsx")
public class StatisticsResource {
	@Autowired
	private ShortUrlService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody NewShortUrlDTO newShortUrlDTO) {
		ShortUrl newSurl;
		Boolean created = false;
		//newSurl = service.insert(newShortUrlDTO.fromDTO());
		newSurl = new ShortUrl();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSurl.getUid())
				.toUri();
		
		return ResponseEntity.created(uri).body(newSurl); // uncertain if this sintax works. can crash.
	}

	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public ResponseEntity<ShortUrl> findOne(@PathVariable String uid) {
		ShortUrl shortUrl = service.findOne(uid);
		return ResponseEntity.ok().body(shortUrl);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ShortUrlDTO>> listAll() {
		// security token validation
		List<ShortUrl> shortUrls = service.listAll();
		List<ShortUrlDTO> jDTOs = shortUrls.stream().map(item -> new ShortUrlDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);		
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> edit(@RequestBody ShortUrl shortUrl) {
		ShortUrl newShortUrl = service.update(shortUrl);
		return ResponseEntity.noContent().build();
		// security token validation
	}

	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable String uid) {
		service.delete(uid);
		return ResponseEntity.noContent().build();
		// security token validation
	}

}
