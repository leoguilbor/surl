package br.com.leoguilbor.surl.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.surl.config.AppConfigParameter;
import br.com.leoguilbor.surl.domain.User;
import br.com.leoguilbor.surl.dto.NewUserDTO;
import br.com.leoguilbor.surl.dto.UserDTO;
import br.com.leoguilbor.surl.exception.ObjectNotFoundException;
import br.com.leoguilbor.surl.repository.UserRepository;

@Service
public class UserService {


	@Autowired
	private AppConfigParameter params;
	
	@Autowired
	private UserRepository rep;

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	public User findOne(Long id) {
		Optional<User> user = rep.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! UID:" + id + " Tipo:" + User.class.getName()));
	}

	@Transactional
	public User insert(NewUserDTO userDTO) {
		
		User user = this.fromDto(userDTO);
		user.setId(null);
		return rep.save(user);
	}

	private User fromDto(NewUserDTO dto) {
		// TODO Auto-generated method stub
		return new User(null, dto.getLogin(), bCrypt.encode(dto.getPassword()));
	}
	private User fromDto(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	private Boolean userExist(String login) {
		// TODO Auto-generated method stub
		if (rep.findByLogin(login).isPresent()) {
			return true;
		}
		return false;
	}

	public List<User> listAll() {
		return rep.findAll();
	}

	@Transactional
	public User update(UserDTO userDto) {

		return rep.save(this.fromDto(userDto));
	}

	@Transactional
	public void delete(Long id) {

		User surl = findOne(id);
		rep.deleteById(surl.getId());
	}
	



}
