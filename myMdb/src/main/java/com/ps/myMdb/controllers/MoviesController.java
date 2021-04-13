package com.ps.myMdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ps.myMdb.dao.CinemaRepository;
import com.ps.myMdb.dao.FavoritesRepository;
import com.ps.myMdb.dao.MovieRepository;
import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.entities.Cinema;
import com.ps.myMdb.entities.Favorite;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.User;
import com.ps.myMdb.services.CinemaService;
import com.ps.myMdb.services.FavoriteService;
import com.ps.myMdb.services.MovieService;
import com.ps.myMdb.services.UserService;



@Controller
@RequestMapping("/movies")
public class MoviesController {

	
	@Autowired
	MovieRepository movieRepo;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	CinemaService cinemaService;
		
		@GetMapping("/")
		public String displayMovies(@AuthenticationPrincipal UserDetails userDetails,Model model,@Param("keyword") String keyword) {
			
				String username = userDetails.getUsername();
				User user = userRepo.findByUsername(username);
				model.addAttribute("user", user);
				
			List<Movie> movies = movieService.getAll(keyword);
			model.addAttribute("moviesList", movies);
					
		return "movies/list-movies";
		}
		
		
		
		@GetMapping("/favorites")
		public String displayFavoriteMovies( @RequestParam("userId") long userId, Model model,Movie movie) {
			
			//	String username = userDetails.getUsername();
			//	User user = userRepo.findByUsername(username);
			//	model.addAttribute("user", user);
			User user = userService.findByUserId(userId);
			model.addAttribute("user", user);	
			
			List<Movie> favoriteMovies = movieRepo.favoriteMovies(userId);
			model.addAttribute("favoritesList", favoriteMovies);
					
		return "movies/list-favorite-movies";
		}
		
		
		
		@GetMapping("/new")
		public String newMovie(@RequestParam("cinemaId") long cinemaId, Model model, Cinema cinema) {

			Cinema myCinema = cinemaService.findByCinemaId(cinemaId);
			Movie movie = new Movie();
			movie.setTheCinema(myCinema);
//			movie.setCinema_id(cinemaId);
			model.addAttribute("movie",movie);
			//myCinema.setMovies().;
			movie.setCinemaName(myCinema.getName());
			model.addAttribute("myCinema", myCinema);
			
			return "movies/new-movie";
		}
		
		@PostMapping("/save")
		public String saveMovie(Model model, Movie movie) {
			
			movieService.save(movie);
			return "redirect:/owner/cinemas/";
		}
		
		@GetMapping("/add")
		public String addFavorite(@RequestParam("movieId") long movieId, @RequestParam("userId") long userId ,Model model ) {
		try {
			if (favoriteService.findByUserIdAndMovieId(movieId, userId) == null ) {
				
				Favorite theFavorite = new Favorite ();
				User theUser = userService.findByUserId(userId);
				Movie theMovie = movieService.findBymovieId(movieId);
				theFavorite.setMovie(theMovie);
				theFavorite.setUser(theUser);
	          	favoriteService.add(theFavorite);
			}
		}catch(IllegalArgumentException e ) {
			}

			return "redirect:/movies/";
		}
		
		@GetMapping("/remove")
		public String removeFavorite( @RequestParam("userId") long userId ,@RequestParam("movieId") long movieId, Model model /* Favorite favorite */) {
			
//			favoriteService.findByUserId(movieId)
//			User theUser = userService.findByUserId(theId);
//			userService.delete(theUser);
			try {
				if (favoriteService.findByUserIdAndMovieId(movieId, userId) != null) {
					Favorite favorite = favoriteService.findByUserIdAndMovieId(movieId, userId);
					favoriteService.remove(favorite);
				}
				
			}catch(IllegalArgumentException e ) {
			}

			return "redirect:/movies/";
	}

	}

