package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u!=?1 order by u.comunidad")
	Page<User> findAllButUser(Pageable pageable, User user);
	
	@Query("SELECT u FROM User u WHERE (LOWER(u.name) LIKE LOWER(?1) OR LOWER(u.email) LIKE LOWER(?1))")
	Page<User> searchByNameAndEmail(String searchText, Pageable pageable);
	
	Page<User> findAll(Pageable pageable);

	@Query("SELECT u FROM User u WHERE (LOWER(u.name) LIKE LOWER(?1) OR LOWER(u.comunidad) LIKE LOWER(?1))")
	Page<User> searchByNameAndComunidad(String string, Pageable pageable);
}
