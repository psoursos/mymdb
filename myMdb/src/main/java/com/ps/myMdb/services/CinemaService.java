package com.ps.myMdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.myMdb.dao.CinemaRepository;
import com.ps.myMdb.entities.Cinema;
//import com.ps.myMdb.entities.Favorite;
import com.ps.myMdb.entities.Movie;

@Service
public class CinemaService {
	
	
	@Autowired
	CinemaRepository cinemaRepo;
	
	public Iterable<Cinema> getAll() {
		return cinemaRepo.findAll();
	}
	
	public  Cinema findByCinemaId(long theId) {
		return cinemaRepo.findById(theId).get();
	}
	
	public Cinema save(Cinema theCinema) {
		// TODO Auto-generated method stub
		return cinemaRepo.save(theCinema);
		
	}
	
	public void delete(Cinema theCinema) {
		cinemaRepo.delete(theCinema);
	}

}
