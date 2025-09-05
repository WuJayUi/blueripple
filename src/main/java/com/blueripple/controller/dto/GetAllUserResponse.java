package com.blueripple.controller.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUserResponse {

	@Schema(description = "所有使用著")
	private List<UserDto> allUsers;
}
