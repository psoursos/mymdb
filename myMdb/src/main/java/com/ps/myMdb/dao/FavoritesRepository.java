package com.ps.myMdb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.myMdb.entities.Favorite;
import com.ps.myMdb.entities.Movie;


public interface FavoritesRepository extends CrudRepository<Favorite,Long> {
	
	@Override
	public List<Favorite> findAll();
	

	
	
	@Query(nativeQuery=true,value="SELECT * FROM  mymdb.favorites WHERE  mymdb.favorites.movie_id =?1  AND mymdb.favorites.user_id = ?2")
	public  Favorite findByUserAndByMovieId(Long userID,Long movieID);
	
	@Query(nativeQuery=true,value="SELECT * FROM  mymdb.movies WHERE  mymdb.movies.movie_id IN (SELECT movie_id FROM mymdb.favorites WHERE mymdb.favorites.user_id=?1)")
	public  List<Movie> findFavoritesByUserId(Long userID);

}
