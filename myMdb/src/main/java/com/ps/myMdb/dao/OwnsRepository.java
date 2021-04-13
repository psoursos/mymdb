package com.ps.myMdb.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;


import com.ps.myMdb.entities.Owns;

public interface OwnsRepository  extends CrudRepository<Owns, Long>{
	
	
	@Override
	public List<Owns> findAll();
	
	
	@Override
	public Optional<Owns> findById(Long value);
	
	
	//@Query(nativeQuery=true,value="SELECT * FROM cinemas where cinemas.id IN(SELECT cinema_id from owns where user_id=?1 )")
   //	public List<OwnedCinemas> cinemaByOwnerId(Long value);


}
