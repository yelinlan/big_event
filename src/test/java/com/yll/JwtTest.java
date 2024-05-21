package com.yll;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *@项目名称: big_event
 *@类名称: JwtTest
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/5/13 22:14
 **/
public class JwtTest {

	@Test
	public void testGen() {
		Map<String, Object> map = new HashMap<>();
		map.put("user", "yll");
		String token = JWTUtil.createToken(map, "123".getBytes(StandardCharsets.UTF_8));
		System.out.println(token);
	}

	@Test
	public void testParse() {
		JWT jwt = JWTUtil.parseToken(
				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoieWxsIn0.9XeHo_uN_r_9O2Dhu5yI8Al26vfxS5FtudMh70o1UKU");
		Object user = jwt.getPayload().getClaim("user");
		System.out.println(user);
	}

}