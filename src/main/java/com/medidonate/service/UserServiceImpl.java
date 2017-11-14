package com.medidonate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medidonate.model.User;
import com.medidonate.repository.UserRepository;
import com.medidonate.utils.AbstractResponse;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	UserRepository userRepository;
	
	@Override
	public AbstractResponse saveUser(User user) {
		System.out.println(user.toString());
		try {
		userRepository.save(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		AbstractResponse ar = new AbstractResponse();
		ar.setStatusCode("200");
		ar.setMessage("OK");
		return ar;
	}

}
