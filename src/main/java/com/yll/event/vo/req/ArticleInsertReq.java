package com.yll.event.vo.req;

import com.yll.event.anno.EnumValid;
import com.yll.event.enums.ArticleEnum;
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
public class ArticleInsertReq implements Serializable {

	/**
	 * 文章标题
	 */
	@NotBlank(message = "[文章标题]不能为空")
	@Size(max = 30, message = "编码长度不能超过30")
	@ApiModelProperty("文章标题")
	@Length(max = 30, message = "编码长度不能超过30")
	private String title;
	/**
	 * 文章内容
	 */
	@NotBlank(message = "[文章内容]不能为空")
	@Size(max = 10000, message = "编码长度不能超过10000")
	@ApiModelProperty("文章内容")
	@Length(max = 10000, message = "编码长度不能超过10,000")
	private String content;
	/**
	 * 文章封面
	 */
	@NotBlank(message = "[文章封面]不能为空")
	@Size(max = 128, message = "编码长度不能超过128")
	@ApiModelProperty("文章封面")
	@Length(max = 128, message = "编码长度不能超过128")
	private String coverImg;
	/**
	 * 文章状态: 只能是[已发布] 或者 [草稿]
	 */
	@Size(max = 3, message = "编码长度不能超过3")
	@ApiModelProperty("文章状态: 只能是[已发布] 或者 [草稿]")
	@Length(max = 3, message = "编码长度不能超过3")
	@EnumValid(message = "文章状态错误",value = ArticleEnum.class,method = "getDescription")
	private String state;
	/**
	 * 文章分类ID
	 */
	@ApiModelProperty("文章分类ID")
	private Integer categoryId;

}
