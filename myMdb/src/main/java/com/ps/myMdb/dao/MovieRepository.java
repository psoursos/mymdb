package com.ps.myMdb.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.myMdb.entities.Movie;


public interface MovieRepository extends CrudRepository<Movie, Long>{
	
	@Override
	public List<Movie> findAll();
	
	
    
	
	
	public Movie findByTitle(String value);

	public Movie findByCategory(String value);
	
	public Movie findByStartDate(Date value);
	
	//public List<Movie> findByCinemaId(Long value);
	
	@Query(nativeQuery=true,value="SELECT * FROM mymdb.movies where movies.cinema_id =?1")
		public List<Movie> moviesByCinemaId(Long cinemaId);
		
	//public List<==> moviesBy();
	//@Query(nativeQuery=true,value="SELECT * FROM movies where movieId ='0'")
	//public List<==> m();
	
	
	@Query(nativeQuery=true,value="SELECT * FROM mymdb.movies WHERE movies.cinema_id IN ( SELECT cinema_id FROM mymdb.owns WHERE mymdb.owns.user_id = ?1)")
	public List<Movie> moviesByOwner(Long ownerID);
	
	
	@Query(nativeQuery=true,value="SELECT * FROM  mymdb.movies WHERE mymdb.movies.movie_id IN ( SELECT movie_id FROM mymdb.favorites WHERE mymdb.favorites.user_id = ?1)")
			public List<Movie> favoriteMovies(Long userID);
	
	@Query(nativeQuery=true,value="SELECT * FROM  mymdb.movies WHERE (mymdb.movies.category LIKE %?1%) "
			+ "OR (mymdb.movies.title LIKE %?1%) "
			+ "OR (mymdb.movies.cinema_name LIKE %?1%) ")
	public List<Movie> searchMovies(String search);


}
