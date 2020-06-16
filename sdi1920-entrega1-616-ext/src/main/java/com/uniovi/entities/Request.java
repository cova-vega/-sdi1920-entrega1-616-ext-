package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Request {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User usuarioRecibidor;

	@ManyToOne
	private User usuarioEnviador;

	public Request(User usuarioRecibidor, User usuarioEnviador) {
		super();
		this.usuarioRecibidor = usuarioRecibidor;
		this.usuarioEnviador = usuarioEnviador;
	}

	public Request() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUsuarioRecibidor() {
		return usuarioRecibidor;
	}

	public void setUsuarioRecibidor(User usuarioRecibidor) {
		this.usuarioRecibidor = usuarioRecibidor;
	}

	public User getUsuarioEnviador() {
		return usuarioEnviador;
	}

	public void setUsuarioEnviador(User usuarioEnviador) {
		this.usuarioEnviador = usuarioEnviador;
	}

	
	
	
}
