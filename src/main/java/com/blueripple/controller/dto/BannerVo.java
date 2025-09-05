package com.blueripple.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerVo {

	@Schema(description = "id")
	private Long id;
	
	@Schema(description = "標題")
	private String title;
	
	@Schema(description = "內容")
	private String content;
	
	@Schema(description = "圖片位置")
	private String imgBase64;
	
	@Schema(description = "圖片名稱")
	private String imgName;
	
	@Schema(description = "圖片樣式")
	private String imgType;
	
	@Schema(description = "連結位置")
	private String linkUrl;

	@Schema(description = "優先級")
	private Integer priority;
	
	@Schema(description = "是否啟用")
	private Boolean isActive;
}

