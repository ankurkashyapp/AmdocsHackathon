package com.medidonate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medidonate.model.User;
import com.medidonate.service.UserService;
import com.medidonate.utils.AbstractResponse;



@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(path = "/createUser", method = RequestMethod.POST, consumes= {"application/json"}, produces= {"application/json"})
	public AbstractResponse createUser(@RequestBody User user) {
		
		LOGGER.info("UserController :: createUser() : Inside"); 
		return userService.saveUser(user);
	}
	
	@RequestMapping(path = "/userLogin", method = RequestMethod.POST,  produces= {"application/json"})
	public User userLogin(@RequestParam(name="email") String email,@RequestParam("password") String password) {
		
		LOGGER.info("userLogin() : Inside"); 
		return userService.userLogin(email,password);
	}
}
