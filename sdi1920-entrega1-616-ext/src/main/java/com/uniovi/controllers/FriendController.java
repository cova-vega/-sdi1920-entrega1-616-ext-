package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.uniovi.entities.User;

import com.uniovi.services.UsersService;

@Controller
public class FriendController {

	@Autowired
	public UsersService usersService;
	

	
	/*
	 * Metodo que saca la lista de amigos
	 */
	@RequestMapping("/friend/list")
	public String getListadoAmidos(Model model, Pageable pageable,Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<User> friendsList = user.getFriendsList();
		model.addAttribute("friendsList", friendsList.getContent());
		model.addAttribute("page", friendsList);
		return "friend/list";
	}
}
