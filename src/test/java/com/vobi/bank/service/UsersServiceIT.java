package com.vobi.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.UserType;
import com.vobi.bank.domain.Users;
import com.vobi.bank.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UsersServiceIT {

	@Autowired
	UsersService usersService;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(usersService);
		assertNotNull(userTypeRepository);
	}

	@Test
	@Order(2)
	void debeCrearunUsers() throws Exception {
		// Arrange
		Integer idUserType = 3;
		String email = "prueba@prueba.com";

		Users users = null;
		UserType userType = userTypeRepository.findById(idUserType).get();

		users = new Users();
		users.setUserType(userType);
		users.setUserEmail(email);
		users.setName("Homero J Simpson");
		users.setToken("sdfsfdgsjkfhsdgkdhfsjk");
		users.setEnable("Y");

		// Act
		users = usersService.save(users);

		// Assert
		assertNotNull(users, "El user es nulo, no se pudo grabar");

	}
	
	@Test
	@Order(3)
	void debeModificarUnUser() throws Exception {
		// Arrange
		//Integer idUserType = 3;
		String email = "prueba@prueba.com";
		Users users = null;

		users = usersService.findById(email).get();
		users.setEnable("N");
		
		// Act
		users = usersService.update(users);

		// Assert
		assertNotNull(users, "El users es nulo, no se pudo grabar");

	}
	
	@Test
	@Order(4)
	void debeBorrarUnuser() throws Exception {
		// Arrange
		String email = "prueba@prueba.com";
		Users users = null;
		Optional<Users> customerOptional=null;
		
		assertTrue(usersService.findById(email).isPresent(),"No se encontro ningun user");

		users = usersService.findById(email).get();
		
		// Act
		usersService.delete(users);
		customerOptional = usersService.findById(email);

		// Assert
		assertFalse(customerOptional.isPresent(), "No pudo borraar el user");

	}
	
	@Test
	@Order(5)
	void debeConsultarTodosLosUsers() throws Exception {
		//Arrange
		List<Users> customers=null;
		
		//Act
		
		customers=usersService.findAll();
		
		customers.forEach(customer->log.info(customer.getName()));		
		
		//Assert
		
		assertFalse(customers.isEmpty(),"No consulto Customers");	
	}

}