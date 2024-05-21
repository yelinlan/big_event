package com.yll.event.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yll.event.entity.User;
import com.yll.event.mapper.UserMapper;
import com.yll.event.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 夜林蓝
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-05-12 15:26:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public User getUserByName(String username) {
		LambdaQueryWrapper<User> query = Wrappers.lambdaQuery(User.class);
		query.eq(User::getUsername, username);
		this.getOne(query, false);
		return this.getOne(query);
	}

	@Override
	public void register(String username, String password) {
		String md5 = SecureUtil.md5(password);
		User user = new User();
		user.setUsername(username);
		user.setPassword(md5);
		this.save(user);
	}
}




