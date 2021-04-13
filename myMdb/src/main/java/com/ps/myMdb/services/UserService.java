package com.ps.myMdb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.entities.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getAll() {
		return userRepo.findAll();
	}
	
//	public List<EmployeeProject> employeeProjects() {
//		return empRepo.employeeProjects();
//	}
	
	
	
	public  User findByUserId(long theId) {
		return userRepo.findById(theId).get();
	}
	


	public User save(User theUser) {
		// TODO Auto-generated method stub
		return userRepo.save(theUser);
		
	}
	
	public void delete(User theUser) {
		userRepo.delete(theUser);
	}
	
	public void deleteById(Long value) {
		userRepo.delete(userRepo.findById(value).get());
	}
	
	

}
