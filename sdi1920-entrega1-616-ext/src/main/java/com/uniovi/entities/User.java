package com.uniovi.entities;

import javax.persistence.*;
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String lastName;
	private String email;
	private String  password;
	@Transient //propiedad que no se almacena en la tabla
	private String passwordConfirm;
	
	private String role;

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

}