package com.yll.event.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 文章分类vo
 * @author 夜林蓝
 */
@Data
public class CategoryInsertReq implements Serializable {
	/**
	 * 分类名称
	 */
	@NotBlank(message = "[分类名称]不能为空")
	@Size(max = 32, message = "编码长度不能超过32")
	@ApiModelProperty("分类名称")
	@Length(max = 32, message = "编码长度不能超过32")
	private String categoryName;
	/**
	 * 分类别名
	 */
	@NotBlank(message = "[分类别名]不能为空")
	@Size(max = 32, message = "编码长度不能超过32")
	@ApiModelProperty("分类别名")
	@Length(max = 32, message = "编码长度不能超过32")
	private String categoryAlias;
}
