package com.yll.event.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 在控制台输出swagger地址
 */
@Component
@Slf4j
@EnableSwagger2
@Configuration
public class SwaggerPrintConfig implements ApplicationListener<WebServerInitializedEvent> {

	/**
	 * web初始化完成时输出
	 * @param event web初始化完成事件
	 */
	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		//获取IP
		String hostAddress = "127.0.0.1";
		//获取端口号
		int port = event.getWebServer().getPort();
		//获取应用名
		String applicationName = event.getApplicationContext().getApplicationName();
		//swagger3和swagger2的地址不一样
		log.info("\n项目启动启动成功！接口文档地址: \n"+"    http://"+hostAddress+":"+port+applicationName+"/swagger-ui/index.html");
	}
}