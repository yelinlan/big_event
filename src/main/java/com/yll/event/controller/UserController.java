package com.yll.event.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.yll.event.entity.User;
import com.yll.event.pagehelper.Result;
import com.yll.event.service.UserService;
import com.yll.event.service.impl.CommonCache;
import com.yll.event.util.ThreadLocalUtil;
import com.yll.event.util.Token;
import com.yll.event.vo.req.LoginUserReq;
import com.yll.event.vo.req.PwdRequestReq;
import com.yll.event.vo.req.UserUpdateReq;
import com.yll.event.vo.resp.UserRsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 *@项目名称: big_event
 *@类名称: UserController
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/5/13 20:57
 **/
@RestController
@RequestMapping("/user")
@Validated
@Tag(name = "用户相关接口", description = "用户相关接口")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

	private final UserService userService;
	private final CommonCache commonCache;

	@Operation(summary = "该接口用于注册新用户", description = "该接口用于注册新用户")
	@PostMapping("/register")
	public Result register(@Validated LoginUserReq loginUserReq) {
		User user = userService.getUserByName(loginUserReq.getUsername());
		if (user == null) {
			userService.register(loginUserReq.getUsername(), loginUserReq.getPassword());
			return Result.success().setMessage("注册成功");
		} else {
			return Result.error("用户名太受欢迎，请换一个用户名称！");
		}
	}

	@Operation(summary = "该接口用于登录", description = "该接口用于登录")
	@PostMapping("/login")
	public Result login(@Valid LoginUserReq loginUserReq) {
		User user = userService.getUserByName(loginUserReq.getUsername());
		String md5 = SecureUtil.md5(loginUserReq.getPassword());
		if (user != null && user.getPassword().equals(md5)) {
			Map<String, Object> claims = new HashMap<>(16);
			claims.put("id", user.getId());
			claims.put("username", user.getUsername());
			String token = Token.gen(claims);
			commonCache.setToken(token);
			return Result.success(token).setMessage("登录成功");
		}
		return Result.error("用户名或密码错误！");
	}

	@Operation(summary = "该接口用于获取当前已登录用户的详细信息", description = "该接口用于获取当前已登录用户的详细信息")
	@GetMapping("/userInfo")
	public Result<UserRsp> userInfo() {
		try {
			User user = userService.getUserByName(ThreadLocalUtil.username());
			UserRsp userRsp = new UserRsp();
			if (user != null) {
				BeanUtil.copyProperties(user, userRsp);
			}
			return Result.success(userRsp);
		} catch (Exception e) {
			return Result.success(null);
		}
	}

	@Operation(summary = "该接口用于更新已登录用户的基本信息(除头像和密码)", description = "该接口用于更新已登录用户的基本信息(除头像和密码)")
	@PutMapping("/update")
	public Result<UserRsp> update(@Validated @RequestBody UserUpdateReq userUpdateReq) {
		User user = new User();
		user.setId(ThreadLocalUtil.userId());
		user.setNickname(userUpdateReq.getNickname());
		user.setEmail(userUpdateReq.getEmail());
		userService.updateById(user);
		return Result.success(null);
	}

	@Operation(summary = "该接口用于更新已登录用户的头像", description = "该接口用于更新已登录用户的头像")
	@PatchMapping("/updateAvatar")
	public Result<UserRsp> updateAvatar(@RequestParam @URL String avatarUrl) {
		User user = new User();
		user.setId(ThreadLocalUtil.userId());
		user.setUserPic(avatarUrl);
		userService.updateById(user);
		return Result.success(null);
	}

	@Operation(summary = "该接口用于更新已登录用户的密码", description = "该接口用于更新已登录用户的密码")
	@PatchMapping("/updatePwd")
	public Result<UserRsp> updatePwd(@Validated @RequestBody PwdRequestReq pwdRequestReq,@RequestHeader("Authorization") String token) {
		if (!pwdRequestReq.getNewPwd().equals(pwdRequestReq.getRePwd())){
			return Result.error("两次填写的密码不一致");
		}
		User userByName = userService.getUserByName(ThreadLocalUtil.username());
		if (!SecureUtil.md5(pwdRequestReq.getOldPwd()).equals(userByName.getPassword())) {
			return Result.error("原密码不正确");
		}
		User user = new User();
		user.setId(ThreadLocalUtil.userId());
		user.setPassword(SecureUtil.md5(pwdRequestReq.getNewPwd()));
		userService.updateById(user);
		commonCache.deleteToken(token);
		return Result.success(null);
	}

	@Operation(summary = "该接口用于退出登录", description = "该接口用于退出登录")
	@GetMapping("/logout")
	public Result<UserRsp> logout(@RequestHeader("Authorization") String token) {
		ThreadLocalUtil.remove();
		commonCache.deleteToken(token);
		return Result.success(null);
	}



}