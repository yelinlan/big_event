package com.yll.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置
 */
@Configuration
public class Swagger3 {


	/**
	 * swagger页面设置名字
	 * @return springfox.documentation.spring.web.plugins.Docket
	 */
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())

				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger3【大事件】测试项目")
				.description("Swagger3【大事件】测试项目(v1.0)")
				.version("v1.0")
				.contact(new Contact("夜林蓝", "", "1562098029@qq.com"))
				.build();
	}



}