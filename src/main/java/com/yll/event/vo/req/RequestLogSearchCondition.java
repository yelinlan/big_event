package com.yll.event.vo.req;

import com.yll.event.pagehelper.SearchCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求日志搜索条件
 * @author 夜林蓝
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestLogSearchCondition extends SearchCondition {

	/**
	 * 模糊查询
	 */
	private String searchKey;
	/**
	 * 排序字段
	 */
	private String orderBy = "class_name";
	/**
	 * 排序方式
	 */
	private boolean asc = false;

}