package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.RequestService;
import com.uniovi.services.UsersService;

@Controller
public class RequestController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	public RequestService requestService;
	
	/*
	 * Metodo que saca la lista de peticiones
	 */
	@RequestMapping("/request/list")
	public String getRequests(Model model, Pageable pageable, Principal principal) {
		
		String email = principal.getName();
		User usersLog = usersService.getUserByEmail(email);
		Page<User> lista = new PageImpl<User>(new LinkedList<User>());
		lista=requestService.getUsuariosEnviadores(pageable, usersLog);
		
		model.addAttribute("requestList", lista.getContent());
		model.addAttribute("page", lista);
		return "/request/list";
	}
	/*
	 * Metodo que envia las peticiones
	 */
	@RequestMapping("/user/send/{id}")
	public String sendFriendRequest(Model model, @PathVariable Long id,
			Principal principal) {
		String email = principal.getName();
		User emisor = usersService.getUserByEmail(email);
		User receptor = usersService.getUser(id);
		requestService.makeRequest(emisor,receptor);
		return "redirect:/user/list";
	}
	/*
	 * Metodo que acepta las peticiones
	 */
	@RequestMapping("/user/accept/{id}")
	public String acceptRequest(@PathVariable Long id, Model model,
			Principal principal) {
		String email = principal.getName();
		User emisor = usersService.getUserByEmail(email);
		User receptor = usersService.getUser(id);
		requestService.deleteRequest(receptor, emisor);
		return "redirect:/request/list";
	}
	
	
}
