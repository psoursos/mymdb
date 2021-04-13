package com.ps.myMdb.api.controllers;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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

import com.ps.myMdb.dao.CinemaRepository;
import com.ps.myMdb.dao.MovieRepository;
import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.dto.OwnedCinemas;
import com.ps.myMdb.entities.Cinema;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.Owns;
import com.ps.myMdb.services.CinemaService;
import com.ps.myMdb.services.MovieService;
import com.ps.myMdb.services.OwnsService;
import com.ps.myMdb.services.UserService;

@RestController
@RequestMapping("/app-api/owner")
public class OwnerRestController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
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
	
	
//		@GetMapping("/movies")
//		public String displayMovies(@AuthenticationPrincipal UserDetails userDetails,Model model) {
//			
//		
//				
//				String owner = userDetails.getUsername();
//				
//				User user = userRepo.findByUsername(owner);
//				model.addAttribute("user", user);
//				
//	
//				
//			List<Movie> movies = movieRepo.moviesByOwner(user.getUserId());
//			model.addAttribute("moviesList", movies);
//		
//			
//			return "owner/list-movies";
//		}
		
		@GetMapping("/cinemas/{userId}")
		public List<OwnedCinemas> displayCinemas(@AuthenticationPrincipal UserDetails userDetails,@PathVariable("userId") Long userId) {	
				//String owner = userDetails.getUsername();
				//User user = userRepo.findByUsername(owner);
				//model.addAttribute("user", user);
				//List<OwnedCinemas> cinemas = cinemaRepo.cinemaByOwnerId(user.getUserId());
			List<OwnedCinemas> cinemas = cinemaRepo.cinemaByOwnerId(userId);
				//model.addAttribute("cinemasList", cinemas);
				return cinemas;
		}
		
		@PostMapping(path="/cinemas/{userId}/new",consumes="application/json")
		public void newCinema(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("userId") long userId,@RequestBody Cinema cinema) {
			
				//String owner = userDetails.getUsername();
				//User user = userRepo.findByUsername(owner);
				//model.addAttribute("user", user);
			    cinemaService.save(cinema);
			    //User user = new User() ;
			    //user = userService.findByUserId(userId);
			    Owns own = new Owns();
			    own.setCinema(cinema);
			    own.setUser(userService.findByUserId(userId));
			    ownsService.save(own);
			    //user.setOwnCinemas(own);
			    //Cinema newCinema = cinema;
			    //cinema.setOwner(null);
			    //Owns own = new;
				
				
				//model.addAttribute("cinema",cinema);
		}
//		
//		@PostMapping("/cinemas/new/save")
//		public String saveCinema(@AuthenticationPrincipal UserDetails userDetails,Model model, Cinema cinema) {
//			String owner = userDetails.getUsername();
//			User user = userRepo.findByUsername(owner);
//			model.addAttribute("user", user);
//			cinemaService.save(cinema);
//			Owns owns = new Owns();
//			
////			owns.setCinemaId(cinema.getCinemaId());
////			owns.setUserId(user.getUserId());
//			model.addAttribute("owns",owns);
//			owns.setCinema(cinema);
//			owns.setUser(user);
//			ownsService.save(owns);
//			return "redirect:/owner/cinemas/";
//		}
		
		
		
		@GetMapping("cinemas/view/{cinemaId}")
         public List<Movie> displayMyMovies(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("cinemaId") long cinemaId) {
			
			//String owner = userDetails.getUsername();
			
			//User user = userRepo.findByUsername(owner);
			//model.addAttribute("user", user);
			
			//Cinema theCinema = cinemaService.findByCinemaId(cinemaId);
			//model.addAttribute("theCinema", theCinema);
			
			List<Movie> movies = movieRepo.moviesByCinemaId(cinemaId);
		
			return movies;
		  //return "owner/list-movies";
		}
		
		@PatchMapping(path="cinemas/edit/{movieId}",consumes = "application/json")
		@ResponseStatus(HttpStatus.OK)
		 public Movie editMovie(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("movieId") long movieId, @RequestBody Movie patchMovie) {
			Movie movie = movieService.findBymovieId(movieId);
			
			if(patchMovie.getTitle() !=null) {
				movie.setTitle(patchMovie.getTitle());                             
			}
			if(patchMovie.getCategory() !=null) {
				movie.setCategory(patchMovie.getCategory());                             
			}
			if(patchMovie.getStartDate() !=null) {
				movie.setStartDate(patchMovie.getStartDate());                             
			}
			if(patchMovie.getEndDate() !=null) {
				movie.setEndDate(patchMovie.getEndDate());                            
			}

			//String owner = userDetails.getUsername();
			//User user = userRepo.findByUsername(owner);
			//model.addAttribute("user", user);
			//Movie theMovie = movieService.findBymovieId(movieId);
			//theMovie.setCinema_id();
//			theMovie.setCinemaName(movieId);
			//model.addAttribute("theMovie", theMovie);	
			
					
			return movieService.save(movie);
			//return"owner/edit-movie";
		}
		
		@DeleteMapping("cinemas/delete/{movieId}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		 public void deleteMovie(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("movieId") long movieId) {
			
			
//
//			String owner = userDetails.getUsername();
//			User user = userRepo.findByUsername(owner);
//			model.addAttribute("user", user);
		//	Movie theMovie = movieService.findBymovieId(movieId);
		//	movieService.delete(theMovie);
////			theMovie.setCinemaName(movieId);
//			model.addAttribute("theMovie", theMovie);	
			try {
				Movie theMovie = movieService.findBymovieId(movieId);
				try { 
					movieService.delete(theMovie);
				     
			     }catch (EmptyResultDataAccessException er) {
			     }
			} catch (InvalidDataAccessApiUsageException e ) {		
			}
		}
					

	}