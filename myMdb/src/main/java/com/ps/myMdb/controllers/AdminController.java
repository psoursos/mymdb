package com.ps.myMdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.dto.UnconfirmedUsers;
import com.ps.myMdb.entities.User;
import com.ps.myMdb.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	
		
		
	public String displayUsers(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		String username = userDetails.getUsername();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);
		
		List<User> users = userRepo.findAll();
		model.addAttribute("usersList", users);
		
		
		List<UnconfirmedUsers> UnconfirmedUsers = userRepo.UnconfirmedUsers();
		model.addAttribute("UnconfirmedUsersList", UnconfirmedUsers);
		
		return "admin/list-users";
	}
	
	@PostMapping("/save")
	public String createUser(Model model, User user) {
		
		userService.save(user);
		
		return "redirect:/admin/list-users";
		
	}
	
	@GetMapping("/update")
	public String displayUserUpdateForm(@RequestParam("id") long theId, Model model) {
		
		User theUser = userService.findByUserId(theId);
		model.addAttribute("user", theUser);
		
		return "admin/update-user";
}

	
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") long theId, Model model) {
		
		User theUser = userService.findByUserId(theId);
		userService.delete(theUser);
		
		return "redirect:/admin/";
}
}
