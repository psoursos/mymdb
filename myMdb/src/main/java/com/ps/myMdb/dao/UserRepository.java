package com.ps.myMdb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.myMdb.dto.UnconfirmedUsers;
import com.ps.myMdb.entities.User;

public interface UserRepository extends CrudRepository<User,Long> {
	
	@Override
	public List<User> findAll();
	
	
	public User findByUsername(String value);
	
	@Override
	public Optional<User> findById(Long value);
	

	
	
	
	@Query(nativeQuery=true,value="SELECT first_name as name ,last_name as surname FROM users where confirmed ='0'")
	public List<UnconfirmedUsers> UnconfirmedUsers();
	
	@Query(nativeQuery=true,value="SELECT first_name, last_name, username, email, role, confirmed, id, password  FROM users")
	public List<User> findAllUsers();

}
