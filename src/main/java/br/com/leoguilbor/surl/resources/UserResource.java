package br.com.leoguilbor.surl.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leoguilbor.surl.domain.User;
import br.com.leoguilbor.surl.dto.NewUserDTO;
import br.com.leoguilbor.surl.dto.UserDTO;
import br.com.leoguilbor.surl.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody NewUserDTO newUserDTO) {

		User user = service.insert(newUserDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(uri).body(user); // uncertain if this sintax works. can crash.
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findOne(@PathVariable Long id) {
		User user = service.findOne(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> listAll() {
		// security token validation
		List<User> users = service.listAll();
		List<UserDTO> uDTOs = users.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(uDTOs);		
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> edit(@RequestBody UserDTO userDTO ) {
		service.update(userDTO);
		return ResponseEntity.noContent().build();
		// security token validation
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
		// security token validation
	}

}
