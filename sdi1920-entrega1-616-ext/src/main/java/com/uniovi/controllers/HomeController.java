package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
