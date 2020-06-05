package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {

		User user1 = new User("pedro@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		
		User user2 = new User("lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		
		User user3 = new User("maria@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
	

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		

	}

}
