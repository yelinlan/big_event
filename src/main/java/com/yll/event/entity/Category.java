package com.yll.event.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @TableName category
 */
@TableName(value = "category")
@Data
public class Category implements Serializable {
	/**
	 * ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 分类名称
	 */
	@TableField(value = "category_name")
	private String categoryName;

	/**
	 * 分类别名
	 */
	@TableField(value = "category_alias")
	private String categoryAlias;

	/**
	 * 创建人ID
	 */
	@TableField(value = "create_user", fill = FieldFill.INSERT_UPDATE)
	private Integer createUser;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}