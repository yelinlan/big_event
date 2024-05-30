package com.yll.event.logrecord;

import com.yll.event.entity.RequestLog;
import com.yll.event.service.RequestLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 切面表达式 https://www.cnblogs.com/javaxubo/p/16018938.html
 * @author 夜林蓝
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class logAspectj {

	@Autowired
	private RequestLogService requestLogService;

	@Pointcut("execution(public * com.yll.event.controller.*.*(..))")
	public void log() {
	}

	@Around("log()")
	public Object requestRecord(ProceedingJoinPoint point) throws Throwable {
		RequestLog log = new RequestLog();
		MethodSignature signature = (MethodSignature) point.getSignature();
		log.setClassName(point.getTarget().getClass().getSimpleName());
		log.setMethodName(signature.getName());
		log.setParams(Arrays.stream(signature.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(",")) );
		log.setRequestEnterTime(LocalDateTime.now());

		Object result = point.proceed(point.getArgs());

		log.setReturnParams(signature.getReturnType().getSimpleName());
		log.setRequestLeaveTime(LocalDateTime.now());
		log.setRequestSpendTime(Duration.between(log.getRequestEnterTime(), log.getRequestLeaveTime()).toMillis());
		requestLogService.save(log);
		return result;
	}

}