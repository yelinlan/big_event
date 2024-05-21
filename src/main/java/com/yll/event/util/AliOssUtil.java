package com.yll.event.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * 对象存储服务工具类
 * @author 夜林蓝
 */
@Slf4j
public class AliOssUtil {

	private static final String ENDPOINT = "";
	private static final String ACCESS_KEY_ID = "";
	private static final String SECRET_ACCESS_KEY = "";
	private static final String BUCKET_NAME = "";

	/**
	 * 上传文件,返回文件的公网访问地址
	 * @param objectName 文件名
	 * @param inputStream 文件流
	 * @return java.lang.String http
	 */
	public static String uploadFile(String objectName, InputStream inputStream) {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
		//公文访问地址
		String url = "";
		try {
			// 创建存储空间。
			ossClient.createBucket(BUCKET_NAME);
			ossClient.putObject(BUCKET_NAME, objectName, inputStream);
			url = "https://" + BUCKET_NAME + "." + ENDPOINT.substring(ENDPOINT.lastIndexOf("/") + 1) + "/" + objectName;
		} catch (OSSException oe) {
			log.error("Caught an OSSException, which means your request made it to OSS, "
					+ "but was rejected with an error response for some reason.");
			log.error("Error Message:{}", oe.getErrorMessage());
			log.error("Error Code:{}", oe.getErrorCode());
			log.error("Request ID:{}", oe.getRequestId());
			log.error("Host ID:{}", oe.getHostId());
		} catch (ClientException ce) {
			log.error("Caught an ClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with OSS, "
					+ "such as not being able to access the network.");
			log.error("Error Message:{}", ce.getMessage());
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
		return url;
	}
}
