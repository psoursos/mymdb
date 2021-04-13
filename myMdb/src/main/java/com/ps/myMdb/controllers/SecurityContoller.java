package com.ps.myMdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.entities.User;

@Controller
public class SecurityContoller {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@GetMapping("/register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, User user) {
		//Encrypting Password
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/";
	}
	@PostMapping("/register/update")
	public String updateUser(Model model, User user) {
		//Encrypting Password
		//user.setPassword(bCryptEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/admin/";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//			return "security/login";
//		}
		return "security/login";
	}
//	

}
