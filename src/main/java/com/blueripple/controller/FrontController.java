package com.blueripple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueripple.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class FrontController {

	@Autowired
	private UserService userService;
	
	@Operation(summary = "登入", description = "回傳 name")
	@PostMapping("/getName")
	public String getUserName(String name) {
		return userService.getUserName(name);
		
	}
	
	@Operation(summary = "建立姓名", description = "save name")
	@PostMapping("setName")
	public String setUserName(String name) {
		return userService.setUserName(name);
	}
}
