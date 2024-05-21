package com.yll.event.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 用户表
* @TableName user
*/
@Data
public class UserRsp implements Serializable {

    /**
    * ID
    */
    private Integer id;
    /**
    * 用户名
    */
    private String username;
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
    /**
    * 头像
    */
    private String userPic;
    /**
    * 创建时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
