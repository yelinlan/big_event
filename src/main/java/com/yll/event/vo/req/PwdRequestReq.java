package com.yll.event.vo.req;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 修改密码vo
 * @author 夜林蓝
 */
@Data
public class PwdRequestReq implements Serializable {

	@Size(max = 32, message = "编码长度不能超过128")
	@ApiModelProperty("密码")
	@Length(max = 32, message = "编码长度不能超过128")
	@JsonAlias("old_pwd")
	@NotEmpty
	private String oldPwd;

	@Size(max = 32, message = "编码长度不能超过128")
	@ApiModelProperty("密码")
	@Length(max = 32, message = "编码长度不能超过128")
	@JsonAlias("new_pwd")
	@NotEmpty
	private String newPwd;

	@Size(max = 32, message = "编码长度不能超过128")
	@ApiModelProperty("密码")
	@Length(max = 32, message = "编码长度不能超过128")
	@JsonAlias("re_pwd")
	@NotEmpty
	private String rePwd;

}