package com.ps.myMdb.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/app-api/movies")
public class MoviesRestController {

	
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
		
		@GetMapping("/")///app-api/movies/?keyword=title2 example
		public List<Movie> displayMovies(@AuthenticationPrincipal UserDetails userDetails,@Param("keyword") String keyword) {
			
			//	String username = userDetails.getUsername();
			//	User user = userRepo.findByUsername(username);
				
				
			List<Movie> movies = movieService.getAll(keyword);
		
					
		return (movies);
		}
		
		@PatchMapping(path="/edit/{movieId}",consumes = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public Movie partiaUpdate(@PathVariable("movieId") Long movieId,@RequestBody Movie patchMovie) {
			Movie movie = movieService.findBymovieId(movieId);
			if(patchMovie.getCategory() !=null) {
				movie.setCategory(patchMovie.getCategory());                             
			}
			if(patchMovie.getCinemaName() !=null) {
				movie.setCinemaName(patchMovie.getCinemaName());                            
			}
			if(patchMovie.getStartDate() !=null) {
				movie.setStartDate(patchMovie.getStartDate());                             
			}
			if(patchMovie.getEndDate() !=null) {
				movie.setEndDate(patchMovie.getEndDate());                             
			}
			if(patchMovie.getTitle() !=null) {
				movie.setTitle(patchMovie.getTitle());                           
			}
			
	
			return movieService.save(movie);
		}
		
		
		
		@GetMapping("/favorites/{userId}")
		public List<Movie> displayFavoriteMovies( @PathVariable("userId") long userId) {
			
			List<Movie> favoriteMovies = movieRepo.favoriteMovies(userId);
			
					
		return favoriteMovies;
		}
		
		
		
		@PostMapping(path="/new/{cinemaId}",consumes = "application/json")
		@ResponseStatus(HttpStatus.CREATED)
		public Movie newMovie(@PathVariable("cinemaId") Long cinemaId,@RequestBody Movie movie) {
			Cinema cinema = cinemaService.findByCinemaId(cinemaId);
			movie.setTheCinema(cinema);
		  return movieService.save(movie);
		}

		
		@PostMapping(path="/favorites/{userId}/add/{movieId}",consumes="application/json")

		public void addFavorite( @PathVariable("movieId") long movieId,  @PathVariable("userId") long userId ) {

			
			User user = userService.findByUserId(userId);
			Movie movie = movieService.findBymovieId(movieId);
			if (favoriteService.findByUserIdAndMovieId(movieId, userId) == null) {
			Favorite favorite = new Favorite();
			favorite.setMovie(movie);
			favorite.setUser(user);
			favoriteService.add(favorite);
			}
		}
			

		
		@DeleteMapping(path="/favorites/{userId}/remove/{movieId}",consumes="application/json")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public  void removeFavorite( @PathVariable("movieId") long movieId,  @PathVariable("userId") long userId ) {
			try {
				Favorite favorite= favoriteService.findByUserIdAndMovieId(userId,movieId);
				try {
					favoriteService.remove(favorite);
				} catch (EmptyResultDataAccessException er) {
			         }
					
			} catch (InvalidDataAccessApiUsageException e ) {		
			}
		}


	}