package com.blueripple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blueripple.controller.dto.CreateAccountRequest;
import com.blueripple.controller.dto.GetAllUserResponse;
import com.blueripple.controller.dto.UserDto;
import com.blueripple.entity.UserEntity;
import com.blueripple.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/front")
@CrossOrigin(origins = "http://localhost:3000") // 允许跨域
public class FrontController {

	@Autowired
	private UserService userService;
	
	@Operation(summary = "取得所有使用著")
	@PostMapping("/getAllUser")
	@ResponseBody
	public ResponseEntity<GetAllUserResponse> getAllUser() {
		System.out.println("getAllUser 被调用");
		List<UserEntity> list = userService.getAllUser();
		List<UserDto> listDto = new ArrayList<>();
		for(UserEntity param : list) {
			UserDto vo = new UserDto();
			vo.setId(param.getId());
			vo.setUserName(param.getUserName());
			vo.setUserPassword(param.getUserPassword());
			vo.setPhoneNumber(param.getPhoneNumber());
			vo.setBirthDay(param.getBirthDay());
			vo.setAdress(param.getAdress());
			listDto.add(vo);
		}
		
		GetAllUserResponse result = new GetAllUserResponse();
		result.setAllUsers(listDto);
		return ResponseEntity.ok(result);
		
	}
	
	@Operation(summary = "建立帳號")
	@PostMapping("/setUserInfo")
	@ResponseBody
	public ResponseEntity<String> setUserName(@Valid @RequestBody CreateAccountRequest request) {
		System.out.println("setUserName 被调用");
		String result = userService.setUserInfo(request);
		return ResponseEntity.ok(result);
	}
	
	
}
