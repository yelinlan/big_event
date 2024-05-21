package com.yll;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * <ol>
 *     <li>启用springboot项目</li>
 *     <li>启用swagger</li>
 *     <li>启用Mapper包自动扫描</li>
 * </ol>
 * @author 夜林蓝
 */
@SpringBootApplication
@MapperScan("com.yll.event.mapper")
@EnableOpenApi
public class BigEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigEventApplication.class, args);
	}

}
