package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	/*
	 * Metodo que retorna una lista de todos los usuarios del sistema
	 */
	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = usersRepository.findAll(pageable);
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
	/*
	 * Metodo que con filtra por email
	 */
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	/*
	 * Metodo que saca una lista de los usuarios para el administrador
	 */
	public Page<User> getUsersForUser(Pageable pageable,User user) {
		Page<User> users =usersRepository.findAllButUser(pageable, user);
		return users;
	}
	/*
	 * Metodo que busca en admin por email y nombre en la lista de usuarios
	 */
	public Page<User> searchUserByNameAndEmail(String searchText,
			Pageable pageable) {
		return usersRepository.searchByNameAndEmail("%" + searchText + "%",
				pageable);
	}

}
