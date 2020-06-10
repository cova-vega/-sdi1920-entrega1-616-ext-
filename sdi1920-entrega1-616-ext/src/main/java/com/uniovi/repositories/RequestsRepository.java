package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;

public interface RequestsRepository extends CrudRepository<Request, Long> {
	
	@Query ("SELECT r.usuarioRecibidor FROM Request r  WHERE r.usuarioEnviador.id = ?1")
	Page<User> findByUsuarioEnviador(Pageable pageable,
			Long l);

	@Query ("SELECT r.usuarioEnviador FROM Request r  WHERE r.usuarioRecibidor.id = ?1")
	Page<User> findByUsuarioRecibidor(Pageable pageable,
			Long l);
	
	@Query("SELECT r FROM Request r WHERE r.usuarioEnviador.id = ?1 AND r.usuarioRecibidor.id = ?2")
	Request findByUsuarioEnviadorUsuarioRecibidor(long usuarioEnviadorId,
			long usuarioRecibidorId);
	
}
