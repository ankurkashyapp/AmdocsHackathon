package com.medidonate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medidonate.controller.UserController;
import com.medidonate.model.User;
import com.medidonate.repository.UserRepository;
import com.medidonate.utils.AbstractResponse;

@Service
public class UserServiceImpl implements UserService {
	

	private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public AbstractResponse saveUser(User user) {
		System.out.println(user.toString());
		try {
			LOGGER.info("saveUser() : Saving user Details."); 
			user.setPic("default.png");
			User userDB = userRepository.save(user);
			
			if(userDB.getId() != null) {
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		AbstractResponse ar = new AbstractResponse();
		ar.setStatusCode("200");
		ar.setMessage("OK");
		return ar;
	}

	@Override
	public User userLogin(String email, String password) {

		
		LOGGER.info("userLogin() : Retrieving UserDetails");
		
		User userDB = userRepository.findByEmailAndPassword(email, password);
		
		if(userDB != null) {
			userDB.setStatusCode("200");
			userDB.setMessage("User Found!");
		}else {
			userDB = new User();
			userDB.setStatusCode("404");
			userDB.setErrorMessage("User Not Found!");
			
		}
		
		return userDB;
	}

}
