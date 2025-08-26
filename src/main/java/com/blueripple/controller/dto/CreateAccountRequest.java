package com.blueripple.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {
	
	@Schema(description = "使用著名稱", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "使用著名稱不能空白")
	private String userName;
	
	@Schema(description = "使用著密碼", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "使用著密碼不能空白")
	private String userPassword;
	
	@Schema(description = "出生年月日", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "出生年月日不能空白")
	private String birthDay;
	
	@Schema(description = "手機號碼", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "手機號碼不能空白")
	private String phoneNumber;
	
	@Schema(description = "地址", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "地址不能空白")
	private String adress;

}
