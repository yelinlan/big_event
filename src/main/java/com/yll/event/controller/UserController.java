package com.yll.event.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.yll.event.entity.User;
import com.yll.event.pagehelper.Result;
import com.yll.event.service.UserService;
import com.yll.event.service.impl.CommonCache;
import com.yll.event.util.ThreadLocalUtil;
import com.yll.event.util.Token;
import com.yll.event.vo.req.LoginUserReq;
import com.yll.event.vo.req.PwdRequestReq;
import com.yll.event.vo.req.RegisterUserReq;
import com.yll.event.vo.req.UserUpdateReq;
import com.yll.event.vo.resp.UserRsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
	private final StringRedisTemplate redisTemplate;

	@Operation(summary = "该接口用于注册新用户", description = "该接口用于注册新用户")
	@PostMapping("/register")
	public Result register(@Validated RegisterUserReq loginUserReq) {
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
	public Result login(@Validated LoginUserReq loginUserReq) {
		String s = redisTemplate.opsForValue().get(loginUserReq.getCode());
		if (StrUtil.isBlank(s)){
			return Result.error("验证码错误！");
		}
		User user = userService.getUserByName(loginUserReq.getUsername());
		String privateKey = redisTemplate.opsForValue().get(SecureUtil.md5(loginUserReq.getUsername()));
		String pwd = SecureUtil.rsa(privateKey, null).decryptStr(loginUserReq.getPassword(), KeyType.PrivateKey);
		System.out.println(pwd);
		String md5 = SecureUtil.md5(pwd);
		if (user != null && user.getPassword().equals(md5)) {
			Map<String, Object> claims = new HashMap<>(16);
			claims.put("id", user.getId());
			claims.put("username", user.getUsername());
			String token = Token.gen(claims);
			commonCache.setToken(token);
			redisTemplate.delete(SecureUtil.md5(loginUserReq.getUsername()));
			redisTemplate.delete(loginUserReq.getCode());
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
	public Result<UserRsp> updatePwd(@Validated @RequestBody PwdRequestReq pwdRequestReq,
			@RequestHeader("Authorization") String token) {
		if (!pwdRequestReq.getNewPwd().equals(pwdRequestReq.getRePwd())) {
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
		clearUserInfoCache(token);
		return Result.success(null);
	}

	@Operation(summary = "该接口用于退出登录", description = "该接口用于退出登录")
	@GetMapping("/logout")
	public Result<UserRsp> logout(@RequestHeader("Authorization") String token) {
		clearUserInfoCache(token);
		return Result.success(null);
	}

	private void clearUserInfoCache(String token) {
		commonCache.deleteToken(token);
		ThreadLocalUtil.remove();
	}

	@Operation(summary = "该接口用于返回验证码", description = "该接口用于返回验证码")
	@GetMapping("/captcha")
	public Result<String> captcha() {
		int width = 100;
		int height = 30;
		CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(width, height, 5, 0);
		String code = captcha.getCode();
		redisTemplate.opsForValue().set(code, code, 30, TimeUnit.SECONDS);
		return Result.success(captcha.getImageBase64Data());
	}

	@Operation(summary = "该接口用于返回公钥", description = "该接口用于返回公钥")
	@GetMapping("/rsa")
	public Result<String> rsa(@Validated @Pattern(regexp = "^\\S{5,16}$", message = "5~16位非空字符") String username) {
		User user = userService.getUserByName(username);
		String md5 = SecureUtil.md5(username);
		if (user != null) {
			RSA rsa = SecureUtil.rsa();
			redisTemplate.opsForValue().set(md5, rsa.getPrivateKeyBase64(), 5, TimeUnit.MINUTES);
			return Result.success(rsa.getPublicKeyBase64());
		}
		return Result.error("用户名或密码错误！");
	}

}