package com.yll.event.vo.req;

import com.yll.event.pagehelper.SearchCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章搜索条件
 * @author 夜林蓝
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleSearchCondition extends SearchCondition {

	@ApiModelProperty("文章分类id")
	private Integer categoryId;
	@ApiModelProperty("文章状态: 只能是[已发布] 或者 [草稿]")
	private String state;
	@ApiModelProperty("通用搜索")
	private String searchKey;

}