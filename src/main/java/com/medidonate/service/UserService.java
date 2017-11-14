package com.medidonate.service;

import com.medidonate.model.User;
import com.medidonate.utils.AbstractResponse;

public interface UserService {

	public AbstractResponse saveUser(User user);
}
