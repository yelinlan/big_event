package com.yll.event.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @TableName article
 */
@TableName(value = "article")
@Data
public class Article implements Serializable {
	/**
	 * ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 文章标题
	 */
	@TableField(value = "title")
	private String title;

	/**
	 * 文章内容
	 */
	@TableField(value = "content")
	private String content;

	/**
	 * 文章封面
	 */
	@TableField(value = "cover_img")
	private String coverImg;

	/**
	 * 文章状态: 只能是[已发布] 或者 [草稿]
	 */
	@TableField(value = "state")
	private String state;

	/**
	 * 文章分类ID
	 */
	@TableField(value = "category_id")
	private Integer categoryId;

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