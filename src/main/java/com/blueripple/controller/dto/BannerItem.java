package com.blueripple.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BannerItem {

	@Schema(description = "告示Id", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull(message = "告示Id不能空白")
	private Long id;
	
	@Schema(description = "告示順序", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "告示順序不能空白")
	private Integer priority;
}
