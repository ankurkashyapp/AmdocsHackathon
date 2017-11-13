package com.medidonate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medidonate.model.User;


@RestController
public class UserController {

	@RequestMapping("/createUser")
	public String createUser(User user) {
		
		return "Working fine";
	}
}
