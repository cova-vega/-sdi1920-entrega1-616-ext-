package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;
import com.uniovi.repositories.RequestsRepository;

@Service
public class RequestService {
	
	@Autowired
	RequestsRepository RequestRepository;
	
	/*
	 * Metodo que hace una peticion 
	 */
	public void makeRequest(User user1, User user2) {
		RequestRepository.save(new Request(user1, user2));

	}
	/*
	 * Metodo que saca todos los usuarios enviadores
	 */
	public Page<User> getUsuariosEnviadores(Pageable pageable, User user) {
		return RequestRepository.findByUsuarioEnviador(pageable, user.getId());
	}

	/*
	 * Metodo que saca todos los usuarios recibidores
	 */
	public Page<User> getUsuariosRecibidores(Pageable pageable, Long l) {
		return RequestRepository.findByUsuarioRecibidor(pageable, l);
	}

	/*
	 * Metodo para eliminar la peticion usuarioRecibidor
	 */
	public void deleteRequest(User usuarioEnviador, User usuarioRecibidor) {
		RequestRepository.delete(RequestRepository.findByUsuarioEnviadorUsuarioRecibidor(usuarioEnviador.getId(), usuarioRecibidor.getId()));
	}
	
}
