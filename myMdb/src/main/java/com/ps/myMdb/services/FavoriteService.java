package com.ps.myMdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.myMdb.dao.FavoritesRepository;
import com.ps.myMdb.entities.Favorite;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.User;


@Service
public class FavoriteService {
	
	@Autowired
	FavoritesRepository favoritesRepo;

	public Iterable<Favorite> getAll() {
		return favoritesRepo.findAll();
	}
	
	public List<Movie> favoritesByUserId(long uId) {
		return favoritesRepo.findFavoritesByUserId(uId);
	}
	
	
	
	public  Favorite findByUserId(long theId) {
		return favoritesRepo.findById(theId).get();
	}
	
	public  Favorite findByUserIdAndMovieId(long uId,long mId ) {
		return favoritesRepo.findByUserAndByMovieId(uId, mId);
	}
	
	
	


	public Favorite add(Favorite theFavorite) {
		// TODO Auto-generated method stub
		return favoritesRepo.save(theFavorite);
		
	}
	
	public void remove(Favorite theFavorite) {
		favoritesRepo.delete(theFavorite);
	}
	
	
}
