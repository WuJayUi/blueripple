package com.blueripple.service;

import java.util.List;

import com.blueripple.controller.dto.CreateAccountRequest;
import com.blueripple.entity.UserEntity;

public interface UserService {

	public List<UserEntity> getAllUser();
	
	public String setUserInfo(CreateAccountRequest request);
}
