package com.yll.event.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "创建请求对象")
public class LoginUserReq {

	@Schema(description = "用户名",required = true)
	@Pattern(regexp = "^\\S{5,16}$", message = "5~16位非空字符")
	@NotBlank()
	private final String username;


	@Schema(description = "密码",required = true)
	@Pattern(regexp = "^\\S{5,16}$", message = "5~16位非空字符")
	@NotBlank
	private final String password;
}
