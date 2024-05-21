package com.yll.event.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yll.event.util.ThreadLocalUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充处理器 使用攻略：https://blog.csdn.net/weixin_44774355/article/details/116302436
 * @author 夜林蓝
 */
@Component
public class AutoFillHandler implements MetaObjectHandler {

	/**
	 * 使用mp做添加操作时候，这个方法执行
	 * @param metaObject metaObject
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		//设置属性值
		this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
		this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		this.setFieldValByName("createUser", ThreadLocalUtil.userId(), metaObject);
	}

	/**
	 * 使用mp做修改操作时候，这个方法执行
	 * @param metaObject metaObject
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		this.setFieldValByName("createUser", ThreadLocalUtil.userId(), metaObject);
	}
}
