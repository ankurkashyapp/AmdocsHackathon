package com.medidonate.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medidonate.model.User;
import com.medidonate.service.UserServiceIntf;
import com.medidonate.utils.AbstractResponse;


@RestController
public class UserController {

	@Autowired
	UserServiceIntf userService;
	
	@RequestMapping(path = "/createUser", method = RequestMethod.POST, consumes= {"application/json"}, produces= {"application/json"})
	public AbstractResponse createUser(User user) {
		
		
		return userService.saveUser(user);
	}
}
