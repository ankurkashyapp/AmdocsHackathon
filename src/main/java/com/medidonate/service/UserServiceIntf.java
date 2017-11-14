package com.medidonate.service;

import com.medidonate.model.User;
import com.medidonate.utils.AbstractResponse;

public interface UserServiceIntf {

	public AbstractResponse saveUser(User user);
}
