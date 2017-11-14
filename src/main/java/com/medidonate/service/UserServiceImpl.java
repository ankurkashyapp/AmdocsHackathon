package com.medidonate.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.medidonate.model.User;
import com.medidonate.repository.UserRepository;
import com.medidonate.utils.AbstractResponse;

public class UserServiceImpl implements UserServiceIntf {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public AbstractResponse saveUser(User user) {

		userRepo.save(user);
		AbstractResponse ar = new AbstractResponse();
		ar.setStatusCode("200");
		ar.setMessage("OK");
		return ar;
	}

}
