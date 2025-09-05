package com.blueripple.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBannerResquest {

	@Schema(description = "標題", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "標題不能空白")
	private String title;
	
	@Schema(description = "內容", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "內容不能空白")
	private String content;
	
	@Schema(description = "圖片位置", requiredMode = Schema.RequiredMode.REQUIRED)
	private String imgUrl;
	
	@Schema(description = "連結位置", requiredMode = Schema.RequiredMode.REQUIRED)
	private String linkUrl;
	

}
