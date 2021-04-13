package com.ps.myMdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.myMdb.dao.MovieRepository;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.User;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepo;
	
	
	public List<Movie> getAll(String search) {
		if (search != null) {
			return movieRepo.searchMovies(search);
		}
		return movieRepo.findAll();
	}
	
	
	public  Movie findBymovieId(long theId) {
		return movieRepo.findById(theId).get();
	}
	


	public Movie save(Movie themovie) {
		// TODO Auto-generated method stub
		return movieRepo.save(themovie);
		
	}
	
	public void delete(Movie themovie) {
		movieRepo.delete(themovie);
	}
}
