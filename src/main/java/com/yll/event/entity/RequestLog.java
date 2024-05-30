package com.yll.event.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 请求日志
 * @TableName request_log
 */
@TableName(value = "request_log")
@Data
public class RequestLog implements Serializable {
	/**
	 * 日志id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 类名
	 */
	@TableField(value = "class_name")
	private String className;

	/**
	 * 方法名称
	 */
	@TableField(value = "method_name")
	private String methodName;

	/**
	 * 参数JSON
	 */
	@TableField(value = "params")
	private String params;

	/**
	 * 返回参数
	 */
	@TableField(value = "return_params")
	private String returnParams;

	/**
	 * 请求开始时间
	 */
	@TableField(value = "request_enter_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime requestEnterTime;

	/**
	 * 请求结束时间
	 */
	@TableField(value = "request_leave_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime requestLeaveTime;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;

	/**
	 * 操作人
	 */
	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private Integer createUser;

	/**
	 * 请求耗时（ms）
	 */
	@TableField(value = "request_spend_time")
	private long requestSpendTime;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}