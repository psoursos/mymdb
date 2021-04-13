package com.ps.myMdb.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.myMdb.dao.MovieRepository;
import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.User;

@RestController
@RequestMapping("/app-api/user")
public class UserRestController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/movies")
	public List<Movie> displayMovies(@AuthenticationPrincipal UserDetails userDetails) {
		
		String cUser = userDetails.getUsername();
		
		User user = userRepo.findByUsername(cUser);
		
		List<Movie> movies = movieRepo.findAll();
		
		return (movies);
	
	}
	
	


}
