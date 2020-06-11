package com.uniovi.services;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;
	@Autowired
	private RolesService rolesService;
	

	@PostConstruct
	public void init() {

		User user1 = new User("alfonso@alfonso.com", "Alfonso", "Herrero");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[1]);
		User user2 = new User("grippo@grippo.com", "Simone", "Grippo");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[1]);
		User user3 = new User("arribas@arribas.com", "Alejandro", "Arribas");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[1]);
		
		User user4 = new User("admin@email.com ", "Admin", "Admin");
		user4.setPassword("admin");
		user4.setRole(rolesService.getRoles()[0]);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);


		
	}

}
