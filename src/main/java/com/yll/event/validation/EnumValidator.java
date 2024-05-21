package com.yll.event.validation;

import com.yll.event.anno.EnumValid;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValid, String> {

	/**
	 * 枚举代码
	 */
	private final Set<String> codeList = new HashSet<>();

	@Override
	public void initialize(EnumValid enumValid) {
		Class<?> clazz = enumValid.value();
		Object[] objects = clazz.getEnumConstants();
		try {
			// 反射获取对应方法
			Method method = clazz.getMethod(enumValid.method());
			Object value;
			for (Object obj : objects) {
				// 调用 获取校验值
				value = method.invoke(obj);
				codeList.add((String) value);
			}
		} catch (Exception e) {
			log.error("处理枚举校验异常: ", e);
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		return null == value || codeList.contains(value);
	}

}
