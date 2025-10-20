package com.blueripple.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProdectRequest {

	@Schema(description = "產品名稱", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "產品名稱不能空白")
	private String prodectName;
	
	@Schema(description = "產品描述", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "產品描述不能空白")
	private String prodectDesc;
	
	@Schema(description = "產品數量", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "產品數量不能空白")
	private String prodectCount;
	
	@Schema(description = "產品金額", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "產品金額不能空白")
	private String prodectAmount;
	
}
