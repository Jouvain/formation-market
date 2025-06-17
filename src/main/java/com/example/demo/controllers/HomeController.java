package com.example.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/", "/home"})
	public String homeRendering(Model model) {
		var user = SecurityContextHolder.getContext().getAuthentication();
		if(user != null && !"anonymousUser".equals(user.getPrincipal())) {
			model.addAttribute("logged", true);
		}
		return "home";
	}
	
}
