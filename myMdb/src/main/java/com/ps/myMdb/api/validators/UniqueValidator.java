//package com.ps.myMdb.api.validators;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ps.myMdb.dao.UserRepository;
//import com.ps.myMdb.entities.User;
//
//public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
//	
//	@Autowired
//	UserRepository userRepo;
//
//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		// TODO Auto-generated method stub
//		
//		User user = userRepo.findByUsername(value);
//		
//		if(user != null)
//			return false;
//		else
//			return true;
//	}
//	
//
//}
