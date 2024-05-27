package com.yll.event.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 夜林蓝
 */
@Data
@Schema(description = "创建请求对象")
public class RegisterUserReq {

	@Schema(description = "用户名", required = true)
	@NotBlank
	private String username;


	@Schema(description = "密码", required = true)
	@NotBlank
	private String password;

}
