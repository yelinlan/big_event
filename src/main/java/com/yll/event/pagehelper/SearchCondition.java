package com.yll.event.pagehelper;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 条件搜索父类
 * @author 夜林蓝
 */
public class SearchCondition {
	@ApiModelProperty("页数")
	@NotNull
	private Integer pageNum;
	@ApiModelProperty("单页条数")
	private Integer pageSize;
}