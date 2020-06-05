package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	
	private UsersRepository usersRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;	
	
	@PostConstruct
	public void init() {
	}
	/*
	 * Metodo que retorna una lista de todos los usuarios del sistema
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	/*
	 * Metodo que retorna un usuario del sistema
	 */
	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}
	/*
	 * Metodo que añade un usuario al sistema
	 */
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	/*
	 * Metodo que elimina un usuario del sistema
	 */
	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
}
