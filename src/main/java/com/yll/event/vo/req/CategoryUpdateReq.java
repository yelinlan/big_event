package com.yll.event.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 文章分类vo
 * @author 夜林蓝
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryUpdateReq extends CategoryInsertReq {

	/**
	 * ID
	 */
	@NotNull(message="[ID]不能为空")
	@ApiModelProperty("ID")
	private Integer id;

}
