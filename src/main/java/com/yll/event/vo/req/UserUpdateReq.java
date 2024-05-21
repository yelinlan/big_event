package com.yll.event.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
* 用户表
* @TableName user
*/
@Data
public class UserUpdateReq implements Serializable {
    /**
    * 昵称
    */
    @Size(max= 10,message="编码长度不能超过10")
    @ApiModelProperty("昵称")
    @Length(max= 10,message="编码长度不能超过10")
    private String nickname;
    /**
    * 邮箱
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("邮箱")
    @Length(max= 128,message="编码长度不能超过128")
	@Email
    private String email;
}
