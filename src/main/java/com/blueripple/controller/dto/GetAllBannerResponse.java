package com.blueripple.controller.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBannerResponse {

	@Schema(description = "所有告示")
	private List<BannerVo> banner;
}
