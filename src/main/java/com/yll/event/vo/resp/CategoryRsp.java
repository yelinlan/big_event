package com.yll.event.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章分类vo
 * @author 夜林蓝
 */
@Data
public class CategoryRsp implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Integer id;
    /**
    * 分类名称
    */
    @NotBlank(message="[分类名称]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("分类名称")
    @Length(max= 32,message="编码长度不能超过32")
    private String categoryName;
    /**
    * 分类别名
    */
    @NotBlank(message="[分类别名]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("分类别名")
    @Length(max= 32,message="编码长度不能超过32")
    private String categoryAlias;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    @NotNull(message="[修改时间]不能为空")
    @ApiModelProperty("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
