package com.yll.event.interceptor;

import com.yll.event.service.impl.CommonCache;
import com.yll.event.util.ThreadLocalUtil;
import com.yll.event.util.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author 夜林蓝
 */
@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

	private final CommonCache commonCache;

	/**
	 * 入站处理
	 * @param request 请求
	 * @param response 相应
	 * @param handler 处理器
	 * @return boolean 是否放行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("Authorization");
		try {
			Object o = commonCache.getToken(token);
			if (o==null){
				throw new RuntimeException();
			}
			ThreadLocalUtil.set(Token.get(token));
			return true;
		} catch (Exception e) {
			response.setStatus(401);
			response.getWriter().println("NO AUTHORIZATION");
			return false;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ThreadLocalUtil.remove();
	}
}