package com.blueripple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blueripple.controller.dto.CreateAccountRequest;
import com.blueripple.entity.UserEntity;
import com.blueripple.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FrontController {

	@Autowired
	private UserService userService;
	
	@Operation(summary = "取得所有使用著")
	@PostMapping("/getAllUser")
	public List<UserEntity> getAllUser() {
		return userService.getAllUser();
		
	}
	
	@Operation(summary = "建立帳號")
	@PostMapping("/setUserInfo")
	@ResponseBody
	public ResponseEntity<String> setUserName(@Valid @RequestBody CreateAccountRequest request) {
		String result = userService.setUserInfo(request);
		return ResponseEntity.ok(result);
	}
}
