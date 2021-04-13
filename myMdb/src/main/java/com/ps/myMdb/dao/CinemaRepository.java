package com.ps.myMdb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.myMdb.dto.OwnedCinemas;
import com.ps.myMdb.dto.UnconfirmedUsers;
import com.ps.myMdb.entities.Cinema;
import com.ps.myMdb.entities.Movie;
import com.ps.myMdb.entities.User;

public interface CinemaRepository extends CrudRepository<Cinema,Long>{
	
	
	@Override
	public List<Cinema> findAll();
	
	
	@Override
	public Optional<Cinema> findById(Long value);
	
	
	@Query(nativeQuery=true,value="SELECT * FROM cinemas where cinemas.id IN(SELECT cinema_id from owns where user_id=?1 )")
	public List<OwnedCinemas> cinemaByOwnerId(Long value);


}
