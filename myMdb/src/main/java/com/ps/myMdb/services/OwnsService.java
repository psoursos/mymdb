package com.ps.myMdb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.myMdb.dao.OwnsRepository;
import com.ps.myMdb.entities.Owns;

@Service
public class OwnsService {
	
	@Autowired
	OwnsRepository ownsRepo;
	
	public Owns save(Owns theOwns) {
		// TODO Auto-generated method stub
		return ownsRepo.save(theOwns);
		
	}
	
	public void delete(Owns theOwns) {
		ownsRepo.delete(theOwns);
	}

}
