package com.uniovi.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	/*
	 * Metodo que devuelve una lista al administrador de todos los usuarios del sistema
	 */
	@RequestMapping("/admin/list")
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
		model.addAttribute("usersList", user.getContent());
		model.addAttribute("page", user);
		return "/admin/list";
	}
	@RequestMapping("/admin/list/update")
	public String updateList(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		return "admin/list :: tableUsers";
	}
	
	
	@RequestMapping("/admin/{id}/delete" )
	public String delete(@PathVariable Long id){
		User user = usersService.getUser(id);
		user.borrarListaAmigos(user);
		usersService.addUser(user);
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}
	
}
