package com.yll.event.pagehelper;


//统一响应结果

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = " 响应数据")
public class Result<T> {

	@Schema(description = "业务状态码  0-成功  1-失败",required = true)
    private Integer code;

	@Schema(description = "提示信息",required = true)
    private String message;

	@Schema(description = "响应数据",required = true)
    private T data;

	/**
	 * 快速返回操作成功响应结果(带响应数据)
	 * @param data 响应数据
	 * @return com.yll.event.entity.Result<E>
	 */
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

	/**
	 * 快速返回操作成功响应结果
	 * @return com.yll.event.entity.Result
	 */
    public static Result success() {
        return new Result(0, "操作成功", null);
    }

    /**
     * 快速返回操作失败响应结果
     * @param message 失败消息
     * @return com.yll.event.entity.Result
     */
    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
