package com.blueripple.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueripple.controller.dto.CreateAccountRequest;
import com.blueripple.entity.UserEntity;
import com.blueripple.repository.UserRepository;
import com.blueripple.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserEntity> getAllUser() {
	    return userRepository.findAll();
	}

	@Override
	public String setUserInfo(CreateAccountRequest request) {
		UserEntity user = new UserEntity();
		user.setUserName(request.getUserName());
		user.setUserPassword(request.getUserPassword());
		user.setBirthDay(request.getBirthDay());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setAdress(request.getAdress());
		userRepository.save(user);
		return "OK";
	}


}
