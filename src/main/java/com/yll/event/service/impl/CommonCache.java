package com.yll.event.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 通用Token缓存  使用攻略：https://blog.csdn.net/2301_77835649/article/details/131434427
 * @author 夜林蓝
 */
@Service
public class CommonCache {

	/**
	 * 保存，需要返回值
	 * @param token token
	 * @return java.lang.String
	 */
	@CachePut(value = "token", key = "#token")
	public String setToken(String token) {
		return token;
	}

	/**
	 * 删除
	 * @param token token
	 * @return void
	 */
	@CacheEvict(value = "token", key = "#token")
	public void deleteToken(String token) {
	}

	/**
	 * 获取Token ，没有就为null
	 * @param token token
	 * @return java.lang.Object
	 */
	@Cacheable(value = "token", key = "#token")
	public Object getToken(String token) {
		return null;
	}

}