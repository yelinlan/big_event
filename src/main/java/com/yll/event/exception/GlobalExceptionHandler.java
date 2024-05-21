package com.yll.event.exception;

import com.yll.event.pagehelper.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * @author 夜林蓝
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 字段绑定异常
	 * @param e 绑定异常
	 * @return com.yll.event.entity.Result
	 */
	@ExceptionHandler(BindException.class)
	public Result handleException(BindException e) {
		log.error(e.getMessage(), e);
		String errorMsg = e.getFieldErrors().stream().map(this::error)
				.collect(Collectors.joining(System.lineSeparator()));
		return Result.error(Objects.toString(errorMsg, "操作失败"));
	}

	/**
	 * 字段校验异常
	 * @param e 校验异常
	 * @return com.yll.event.entity.Result
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handleException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		String errorMsg = e.getBindingResult().getFieldErrors().stream().map(this::error)
				.collect(Collectors.joining(System.lineSeparator()));
		return Result.error(Objects.toString(errorMsg, "操作失败"));
	}

	/**
	 * 获取字段绑定异常内容
	 * @param fieldError 字段异常原因
	 * @return java.lang.String
	 */
	private String error(FieldError fieldError) {
		return fieldError.getField() + " ：" + fieldError.getRejectedValue() + " -> " + fieldError.getDefaultMessage();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public Result handleException(ConstraintViolationException e) {
		log.error(e.getMessage(), e);
		String errorMsg = e.getConstraintViolations().stream().map(this::constraintViolationError)
				.collect(Collectors.joining(System.lineSeparator()));
		return Result.error(Objects.toString(errorMsg, "操作失败"));
	}

	/**
	 * 获取字段绑定异常内容
	 * @param constraintViolation 字段异常原因
	 * @return java.lang.String
	 */
	private String constraintViolationError(ConstraintViolation constraintViolation) {
		return constraintViolation.getPropertyPath() + " ：" + constraintViolation.getInvalidValue() + " -> "
				+ constraintViolation.getMessage();
	}

	/**
	 * 通用异常处理
	 * @param e 异常
	 * @return com.yll.event.entity.Result
	 */
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) {
		log.error(e.getMessage(), e);
		return Result.error(Objects.toString(e.getMessage(), "操作失败"));
	}

}