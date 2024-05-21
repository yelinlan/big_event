package com.yll.event.anno;

import com.yll.event.validation.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义枚举验证器 使用攻略：https://blog.csdn.net/qq_41307963/article/details/136647219
 * @author 夜林蓝
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValid {

	Class<?> value();

	String message() default "入参值不在正确枚举中";

	/**
	 * 默认反射调用的方法
	 * @return 返回方法参数
	 */
	String method() default "getName";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
