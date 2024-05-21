package com.yll.event.util;


import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * token生成与验证
 * @author 夜林蓝
 */
public class Token {

	/**
	 * 秘钥
	 */
    private static final String KEY = "itheima";

    /**
     * 根据业务数据（有效载荷）生成token
     * @param claims 有效载荷
     * @return java.lang.String
     */
    public static String gen(Map<String, Object> claims) {
        return JWT.create()
                .setPayload("claims", claims)
                .setExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(JWTSignerUtil.hs256(KEY.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 获取token业务数据（有效载荷）
     * @param token token
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> get(String token) {
        return (Map<String, Object>) JWTUtil.parseToken(token)
				.getPayload()
                .getClaim("claims");
    }

}
