package com.yll.event.config;

import com.yll.event.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 * @author 夜林蓝
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	/**
	 * 添加拦截器
	 * @param registry 拦截器注册
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.excludePathPatterns(
						"/user/login",
						"/user/register",
						"/user/captcha",
						"/user/rsa",
						"/swagger-resources/**",
						"/v3/api-docs/**",
						"/swagger-ui/**"
				);
	}
}