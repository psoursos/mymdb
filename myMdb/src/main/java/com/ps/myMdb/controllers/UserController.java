package com.ps.myMdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ps.myMdb.dao.MovieRepository;
import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
		@GetMapping("/movies")
		public String displayMovies(@AuthenticationPrincipal UserDetails userDetails,Model model) {
			
		
				
				String cUser = userDetails.getUsername();
				
				User user = userRepo.findByUsername(cUser);
				model.addAttribute("user", user);
				
	
				
			List<Movie> movies = movieRepo.findAll();
			model.addAttribute("moviesList", movies);
		
			
			return "user/list-all-movies";
		}

	}
