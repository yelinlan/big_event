package com.yll.event.anno;

import java.lang.annotation.*;
/**
 * 分页注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoPage {
	/**
	 * 默认 第一页
	 */
	int page() default 1;

	/**
	 * 默认每页显示7条数据
	 */
	int size() default 20;

}
