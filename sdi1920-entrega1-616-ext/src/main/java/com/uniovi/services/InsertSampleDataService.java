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

		User user1 = new User("alfonso@alfonso.com", "Alfonso", "Herrero","Tartiere","Canarias");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[1]);
		
		User user2 = new User("grippo@grippo.com", "Simone", "Grippo","Tartiere","Canarias");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[1]);
		
		User user3 = new User("arribas@arribas.com", "Alejandro", "Arribas","Tartiere","Asturias");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[1]);
		
		User user4 = new User("admin@email.com ", "Admin", "Admin","Tartiere","Castilla la Mancha");
		user4.setPassword("admin");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("christian@christian.com", "Christian", "Fernandez","Tartiere","Asturias");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		
		User user6 = new User("carlos@carlos.com", "Carlos", "Hernandez","Tartiere","Baleares");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[1]);
		
		User user7 = new User("sangalli@sangalli.com", "Marco", "Sangalli","Tartiere","Comunidad Valenciana");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[1]);
		
		User user8 = new User("borja@borja.com", "Borja", "Sanchez","Tartiere","Canarias");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[1]);
		
		User user9 = new User("rodri@rodri.com", "Rodri", "Rios","Tartiere","Canarias");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[1]);
		
		User user10 = new User("berjon@berjon.com", "Saul", "Berjon","Tartiere","Canarias");
		user10.setPassword("123456");
		user10.setRole(rolesService.getRoles()[1]);
		
		User user11 = new User("luismi@luismi.com", "Luismi", "Sanchez","Tartiere","Canarias");
		user11.setPassword("123456");
		user11.setRole(rolesService.getRoles()[1]);
		
		User user12 = new User("mossa@mossa.com", "Mossa", "Angresola","Tartiere","Canarias");
		user12.setPassword("123456");
		user12.setRole(rolesService.getRoles()[1]);
		
		User user13 = new User("juanjo@juanjo.com", "Juanjo", "Nieto","Tartiere","Cantabria");
		user13.setPassword("123456");
		user13.setRole(rolesService.getRoles()[1]);
		
		User user14 = new User("ortuño@ortuño.com", "Alfredo", "Ortuño","Tartiere","Cantabria");
		user14.setPassword("123456");
		user14.setRole(rolesService.getRoles()[1]);
		
		User user15 = new User("jimmy@jimmy.com", "Jaime", "Suarez","Tartiere","Cantabria");
		user15.setPassword("123456");
		user15.setRole(rolesService.getRoles()[1]);
		
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user10);
		usersService.addUser(user11);
		usersService.addUser(user12);
		usersService.addUser(user13);
		usersService.addUser(user14);
		usersService.addUser(user15);



		
	}

}
