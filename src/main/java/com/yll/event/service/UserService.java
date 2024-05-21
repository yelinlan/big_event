package com.yll.event.service;

import com.yll.event.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 夜林蓝
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-05-12 15:26:28
*/
public interface UserService extends IService<User> {

	User getUserByName(String username);

	void register(String username, String password);
}
