package com.blueripple.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	@Schema(description = "id")
	private Long id;   
	
	@Schema(description = "使用者名稱")
	private String userName;   

	@Schema(description = "使用者密碼")
	private String userPassword;   

	@Schema(description = "生日")
	private String birthDay;   

	@Schema(description = "電話號碼")
	private String phoneNumber;   

	@Schema(description = "地址")
	private String adress;   
		
}
