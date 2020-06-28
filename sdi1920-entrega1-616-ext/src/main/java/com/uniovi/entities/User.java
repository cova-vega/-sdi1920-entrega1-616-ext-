package com.uniovi.entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String lastName;
	private String email;
	private String password;
	@Transient // propiedad que no se almacena en la tabla
	private String passwordConfirm;

	private String role;

	@OneToMany(mappedBy = "usuarioEnviador", cascade = CascadeType.ALL)
	private Set<Request> peticionesEnviadas = new HashSet<Request>();

	@OneToMany(mappedBy = "usuarioRecibidor", cascade = CascadeType.ALL)
	private Set<Request> peticionesRecibidas = new HashSet<Request>();

	// List of friends
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> friends;

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Request> getPeticionesEnviadas() {
		return peticionesEnviadas;
	}

	public void setPeticionesEnviadas(Set<Request> peticionesEnviadas) {
		this.peticionesEnviadas = peticionesEnviadas;
	}

	public Set<Request> getPeticionesRecibidas() {
		return peticionesRecibidas;
	}

	public void setPeticionesRecibidas(Set<Request> peticionesRecibidas) {
		this.peticionesRecibidas = peticionesRecibidas;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	public void addFriend(User friend) {
		friends.add(friend);

	}
	public boolean isFriend(User friend) {
		return friends.contains(friend);
	}
	
	public Page<User> getFriendsList() {
		LinkedList<User> userFriends = new LinkedList<User>();
		for (User friend : friends)
			userFriends.add(friend);
		return new PageImpl<User>(userFriends);
	}
	
	public void borrarListaAmigos(User user) {
		for(User friend : friends)
			friend.getFriends().remove(user);
		friends = new HashSet<User>();
		
	}             
}