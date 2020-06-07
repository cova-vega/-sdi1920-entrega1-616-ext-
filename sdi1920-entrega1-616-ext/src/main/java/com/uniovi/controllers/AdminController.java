package com.uniovi.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;
import java.security.Principal;

@Controller
public class AdminController {
	@Autowired
	public UsersService usersService;

	@RequestMapping("/user/admin")
	public String getAdminList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User usersLog = usersService.getUserByEmail(email);
		Page<User> user = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			user = usersService.searchUserByNameAndEmail(searchText, pageable);
		} else {
			user = usersService.getUsersForUser(pageable,usersLog);
		}
		model.addAttribute("userAdmin", user.getContent());
		model.addAttribute("page", user);
		return "user/admin";
	}
}
