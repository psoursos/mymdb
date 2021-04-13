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

import com.ps.myMdb.dao.CinemaRepository;
import com.ps.myMdb.dao.MovieRepository;
import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.dto.OwnedCinemas;
import com.ps.myMdb.entities.Cinema;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.Owns;
import com.ps.myMdb.entities.User;
import com.ps.myMdb.services.CinemaService;
import com.ps.myMdb.services.MovieService;
import com.ps.myMdb.services.OwnsService;

@Controller
@RequestMapping("/owner")
public class OwnerController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	CinemaRepository cinemaRepo;
	
	@Autowired
	CinemaService cinemaService;
	@Autowired
	OwnsService ownsService;
	
	
		@GetMapping("/movies")
		public String displayMovies(@AuthenticationPrincipal UserDetails userDetails,Model model) {
			
		
				
				String owner = userDetails.getUsername();
				
				User user = userRepo.findByUsername(owner);
				model.addAttribute("user", user);
				
	
				
			List<Movie> movies = movieRepo.moviesByOwner(user.getUserId());
			model.addAttribute("moviesList", movies);
		
			
			return "owner/list-movies";
		}
		
		@GetMapping("/cinemas")
		public String displayCinemas(@AuthenticationPrincipal UserDetails userDetails,Model model) {
			
		
				
				String owner = userDetails.getUsername();
				
				User user = userRepo.findByUsername(owner);
				model.addAttribute("user", user);
				
	
				
			List<OwnedCinemas> cinemas = cinemaRepo.cinemaByOwnerId(user.getUserId());
			model.addAttribute("cinemasList", cinemas);
		
			
			return "owner/list-cinemas";
		}
		
		@GetMapping("/cinemas/new")
		public String newCinema(@AuthenticationPrincipal UserDetails userDetails,Model model,Owns owns) {
			
		
				
				String owner = userDetails.getUsername();
				
				User user = userRepo.findByUsername(owner);
				model.addAttribute("user", user);
				Cinema cinema = new Cinema();
				model.addAttribute("cinema",cinema);
				
		
			return "owner/new-cinema";
		}
		
		@PostMapping("/cinemas/new/save")
		public String saveCinema(@AuthenticationPrincipal UserDetails userDetails,Model model, Cinema cinema) {
			String owner = userDetails.getUsername();
			User user = userRepo.findByUsername(owner);
			model.addAttribute("user", user);
			cinemaService.save(cinema);
			Owns owns = new Owns();
			
//			owns.setCinemaId(cinema.getCinemaId());
//			owns.setUserId(user.getUserId());
			model.addAttribute("owns",owns);
			owns.setCinema(cinema);
			owns.setUser(user);
			ownsService.save(owns);
			return "redirect:/owner/cinemas/";
		}
		
		
		
		@GetMapping("cinemas/view")
         public String displayMyMovies(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("cinemaId") long cinemaId, Model model,Movie movie, Cinema cinema) {
			
			String owner = userDetails.getUsername();
			
			User user = userRepo.findByUsername(owner);
			model.addAttribute("user", user);
			
			Cinema theCinema = cinemaService.findByCinemaId(cinemaId);
			model.addAttribute("theCinema", theCinema);
			
			List<Movie> movies = movieRepo.moviesByCinemaId(cinemaId);
			model.addAttribute("moviesByCinemaList", movies);	
					
		  return "owner/list-movies";
		}
		
		@GetMapping("cinemas/edit")
		 public String editMovie(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("movieId") long movieId, Model model,Movie movie) {
			
			

			String owner = userDetails.getUsername();
			User user = userRepo.findByUsername(owner);
			model.addAttribute("user", user);
			Movie theMovie = movieService.findBymovieId(movieId);
			//theMovie.setCinema_id();
//			theMovie.setCinemaName(movieId);
			model.addAttribute("theMovie", theMovie);	
			
					
			
			return"owner/edit-movie";
		}
		
		@GetMapping("cinemas/delete")
		 public String deleteMovie(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("movieId") long movieId, Model model,Movie movie) {
			
			
//
//			String owner = userDetails.getUsername();
//			User user = userRepo.findByUsername(owner);
//			model.addAttribute("user", user);
			Movie theMovie = movieService.findBymovieId(movieId);
			movieService.delete(theMovie);
////			theMovie.setCinemaName(movieId);
//			model.addAttribute("theMovie", theMovie);	


			
			return"redirect:/owner/cinemas/";
		}
		
		

	}
