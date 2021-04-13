package com.ps.myMdb.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.dto.UnconfirmedUsers;
import com.ps.myMdb.entities.User;
import com.ps.myMdb.services.UserService;

@RestController
@RequestMapping("/app-api/admin")
public class AdminApiController {
		
		@Autowired
		UserService userService;
		
		@Autowired
		UserRepository userRepo;
		
		@GetMapping("/")
		public List<User> displayUsers(@AuthenticationPrincipal UserDetails userDetails) {
			//String username = userDetails.getUsername();
			//User user = userRepo.findByUsername(username);
			//String username = userDetails.getUsername();
			
			//User user = userRepo.findByUsername(username);
			List<User> tusers =  userRepo.findAllUsers();
			List<UnconfirmedUsers> unconfirmedUsers = userRepo.UnconfirmedUsers();
			return (tusers);
		}
		
		@GetMapping("/{id}")
		public User getUserById(@PathVariable("id") Long id) {
			return userService.findByUserId(id);
		}
		
		@PostMapping(consumes = "application/json")
		@ResponseStatus(HttpStatus.CREATED)
		public User create( @RequestBody User user ) {
			return userService.save(user);
		}
		
		@PutMapping(consumes = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public User update(@RequestBody User user) {
			return  userService.save(user);
		}
		
		@PatchMapping(path="/{id}",consumes = "application/json")
		//@ResponseStatus(HttpStatus.OK)
		public User partiaUpdate(@PathVariable("id") Long id,@RequestBody User patchUser) {
			User user = userService.findByUserId(id);
			
			if(patchUser.getEmail() !=null) {
				user.setEmail(patchUser.getEmail());                             
			}
			if(patchUser.getFirstName() !=null) {
				user.setFirstName(patchUser.getFirstName());                            
			}
			if(patchUser.getLastName() !=null) {
				user.setLastName(patchUser.getLastName());                             
			}
			if(patchUser.getRole() !=null) {
				user.setRole(patchUser.getRole());                             
			}
			if(patchUser.getUsername() !=null) {
				user.setUsername(patchUser.getUsername());                           
			}
			if(patchUser.isConfirmed()) {
				user.setConfirmed(true);                             
			}else {user.setConfirmed(false); }
	
			return userService.save(user);
		}
		
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteUser (@PathVariable("id") Long id) {
			try {
			userService.deleteById(id);
			}
			catch(EmptyResultDataAccessException e) {
				
			}
		}
			
	
		
//		@PostMapping("/save")
//		public String createUser(Model model, User user) {
//			
//			userService.save(user);
//			
//			return "redirect:/admin/list-users";
//			
//		}
//		
//		@GetMapping("/update")
//		public String displayUserUpdateForm(@RequestParam("id") long theId, Model model) {
//			
//			User theUser = userService.findByUserId(theId);
//			model.addAttribute("user", theUser);
//			
//			return "admin/update-user";
//	}
//
//		
//		
//		@GetMapping("/delete")
//		public String deleteUser(@RequestParam("id") long theId, Model model) {
//			
//			User theUser = userService.findByUserId(theId);
//			userService.delete(theUser);
//			
//			return "redirect:/admin/";
//	}
//	}
}
