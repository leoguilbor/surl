package br.com.leoguilbor.surl.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.leoguilbor.surl.dto.DomainStatsDTO;
import br.com.leoguilbor.surl.service.StatisticsService;

@RestController
@RequestMapping(value = "/stats")
public class StatisticsResource {
	@Autowired
	private StatisticsService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> countFullStats() {
		return ResponseEntity.ok().body(service.countFullStats()); 
	}
	
	@RequestMapping(value = "/today",method = RequestMethod.GET)
	public ResponseEntity<?> countFullStatsToday() {	
		return ResponseEntity.ok().body(service.countFullStatsToday());
	}
	
	@RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> countStatsByUser(@PathVariable Long id) {		
		return ResponseEntity.ok().body(service.countStatsByUser(id)); 
	}

	@RequestMapping(value = "/domains",method = RequestMethod.GET)
	public ResponseEntity<List<DomainStatsDTO>> countStatsByUser() {		
		return ResponseEntity.ok().body(service.countStatsByDomain()); 
	}
	
	@RequestMapping(value = "/domains/{domain}",method = RequestMethod.GET)
	public ResponseEntity<?> countStatsByUser(@PathVariable String domain) {		
		return ResponseEntity.ok().body(service.countStatsByDomain(domain)); 
	}
	
	

}
