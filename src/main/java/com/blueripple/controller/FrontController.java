package com.blueripple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blueripple.controller.dto.CreateAccountRequest;
import com.blueripple.controller.dto.CreateProdectRequest;
import com.blueripple.controller.dto.GetAllUserResponse;
import com.blueripple.controller.dto.UserDto;
import com.blueripple.entity.UserEntity;
import com.blueripple.service.ProdectService;
import com.blueripple.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/front")
@CrossOrigin(origins = "http://localhost:3000") // 允许跨域
public class FrontController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProdectService prodectService;
	
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
	
	@Operation(summary = "建立商品")
	@PostMapping("/createProdect")
	@ResponseBody
	public ResponseEntity<String> createProdect(
			@Parameter(description = "產品名稱", required = true)
			@RequestParam("prodectName") String prodectName,

			@Parameter(description = "產品描述", required = true)
			@RequestParam("prodectDesc") String prodectDesc,

			@Parameter(description = "產品數量", required = true)
			@RequestParam("prodectCount") String prodectCount,

			@Parameter(description = "產品金額", required = true)
			@RequestParam("prodectAmount") String prodectAmount,
			
            @Parameter(description = "主要圖片", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE))
			@RequestPart("mainImg") MultipartFile mainImg,
			
	        @Parameter(description = "副圖清單", required = false,
            content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE))
			@RequestPart("viceImgs") List<MultipartFile> viceImgs
			
			) {
		System.out.println("createProdect 被调用");
		CreateProdectRequest request = CreateProdectRequest.builder()
				.prodectName(prodectName)
				.prodectDesc(prodectDesc)
				.prodectAmount(prodectAmount)
				.prodectCount(prodectCount)
				.build();
		String result = prodectService.createProdect(request,mainImg,viceImgs);
		return ResponseEntity.ok(result);
	}
}
