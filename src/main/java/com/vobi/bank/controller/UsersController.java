package com.vobi.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UsersDTO;
import com.vobi.bank.mapper.UsersMapper;
import com.vobi.bank.service.UsersService;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

	@Autowired 
	UsersService usersService;

	@Autowired
	UsersMapper usersMapper;

	// borrar
	@DeleteMapping("/{email}")
	public void delete(@PathVariable("email") String email) throws Exception {
		usersService.deleteById(email);
	}

	// Modificar
	@PutMapping
	public UsersDTO update(@Valid @RequestBody UsersDTO usersDTO) throws Exception {
		Users users = usersMapper.usersDTOtoUsers(usersDTO);
		users = usersService.update(users);
		usersDTO = usersMapper.usersToUsersDTO(users);

		return usersDTO;
	}

	// Crear
	@PostMapping
	public UsersDTO save(@Valid @RequestBody UsersDTO usersDTO) throws Exception {
		Users users = usersMapper.usersDTOtoUsers(usersDTO);
		users = usersService.save(users);
		usersDTO = usersMapper.usersToUsersDTO(users);

		return usersDTO;
	}

	// Consultar por email
	@GetMapping("/{email}")
	public UsersDTO findById(@PathVariable("email") String email) throws Exception {
		// Customer
		// customer=(customerService.findById(id).isPresent()==true)?customerService.findById(id).get():null;

		Users users = null ;
		UsersDTO usersDTO = null;
		if (usersService.findById(email).isPresent() == true)
			users = usersService.findById(email).get();

		usersDTO = usersMapper.usersToUsersDTO(users);

		return usersDTO;
	}

	// Consultar todos los customers
	@GetMapping()
	public List<UsersDTO> findAll() throws Exception {

		List<Users> users = usersService.findAll();
		List<UsersDTO> usersDTOs = usersMapper.usersListToUsersDTOList(users);

		return usersDTOs;
	}

}