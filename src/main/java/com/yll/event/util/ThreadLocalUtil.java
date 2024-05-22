package com.yll.event.util;

import cn.hutool.core.convert.NumberWithFormat;

import java.util.Map;

/**
 * ThreadLocal 工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
	/**
	 * 提供ThreadLocal对象
	 */
	private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

	/**
	 * 根据键获取值
	 */
	public static <T> T get() {
		return (T) THREAD_LOCAL.get();
	}

	/**
	 * userId
	 * @return java.lang.Integer
	 */
	public static Integer userId() {
		Map<String, Object> map = get();
		if (map==null){
			return null;
		}
		NumberWithFormat number = (NumberWithFormat) map.get("id");
		return number.intValue();
	}

	/**
	 * username
	 * @return java.lang.String
	 */
	public static String username() {
		Map<String, Object> map = ThreadLocalUtil.get();
		return (String) map.get("username");
	}

	/**
	 * 存储键值对
	 * @param value 值
	 */
	public static void set(Object value) {
		THREAD_LOCAL.set(value);
	}


	/**
	 * 清除ThreadLocal 防止内存泄漏
	 */
	public static void remove() {
		THREAD_LOCAL.remove();
	}
}
