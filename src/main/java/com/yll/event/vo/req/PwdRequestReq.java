package com.yll.event.vo.req;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 修改密码vo
 * @author 夜林蓝
 */
@Data
public class PwdRequestReq implements Serializable {

	@ApiModelProperty("密码")
	@Pattern(regexp = "^\\S{5,16}$", message = "5~16位非空字符")
	@JsonAlias("old_pwd")
	@NotEmpty
	private String oldPwd;

	@ApiModelProperty("密码")
	@Pattern(regexp = "^\\S{5,16}$", message = "5~16位非空字符")
	@JsonAlias("new_pwd")
	@NotEmpty
	private String newPwd;

	@Pattern(regexp = "^\\S{5,16}$", message = "5~16位非空字符")
	@ApiModelProperty("密码")
	@JsonAlias("re_pwd")
	@NotEmpty
	private String rePwd;

}