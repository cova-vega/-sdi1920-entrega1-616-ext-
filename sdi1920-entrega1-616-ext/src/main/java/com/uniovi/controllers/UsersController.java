package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RequestService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private RequestService RequestService;

	/*
	 * Metodo GET de Idenficacion
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(name = "error", required = false) String error) {
		model.addAttribute("error", error);
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	/*
	 * Metodo GET de Registro
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	/*
	 * Metodo POST Registro
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[1]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:user/list";
	}
	
	/*
	 * Metodo par acceder a la lista de usuarios
	 */
	
	@RequestMapping("/user/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User usersLog = usersService.getUserByEmail(email);
		Page<User> user = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			user = usersService.searchUserByNameAndEmail(searchText, pageable);
			user= usersService.searchUserByNameAndComunidad(searchText, pageable);
		} else {
			user = usersService.getUsersForUser(pageable,usersLog);
		}
		
		Page<User> requestList = RequestService.getUsuariosRecibidores(pageable, usersLog.getId());
		Page<User> friendsList = usersLog.getFriendsList();
		
		model.addAttribute("friendsList", friendsList.getContent());
		model.addAttribute("requestList", requestList.getContent());
		model.addAttribute("userList", user.getContent());
		model.addAttribute("page", user);
		return "user/list";
	}
	
	/*
	 * Metodo que actualiza la lista de usuarios
	 */
	@RequestMapping("/user/list/update")
	public String updateList(Model model, Pageable pageable) { 
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		return "user/list :: tableUser";
	}
}
