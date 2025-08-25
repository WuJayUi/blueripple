package com.blueripple.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueripple.entity.UserEntity;
import com.blueripple.repository.UserRepository;
import com.blueripple.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String getUserName(String name) {
	    return userRepository.findByUserName(name)
	                         .map(UserEntity::getUserName)
	                         .orElse("找不到使用者");
	}

	@Override
	public String setUserName(String name) {
		UserEntity user = new UserEntity();
		user.setUserName(name);
		userRepository.save(user);
		return "已儲存UserName";
	}


}
