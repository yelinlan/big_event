package com.yll.event.pagehelper;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yll.event.anno.AutoPage;
import com.yll.event.vo.resp.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分页切面  使用攻略：https://blog.csdn.net/qq_59517460/article/details/130780631
 * @author 夜林蓝
 */
@Slf4j
@Aspect
@Component
public class AutoPageAspectj {

	@Autowired
	private HttpServletRequest req;

	@Pointcut("@annotation(com.yll.event.anno.AutoPage)")
	public void doPointcut() {
	}

	@Around("@annotation(myAutoPage)")
	public Object test(ProceedingJoinPoint point, AutoPage myAutoPage) throws Throwable {
		//开始分页
		Integer page = Convert.toInt(req.getParameter("page"), myAutoPage.page());
		Integer size = Convert.toInt(req.getParameter("size"), myAutoPage.size());
		PageHelper.startPage(page, size);
		Object[] args = point.getArgs();
		Object result = point.proceed(args);
		return pageResult(result);
	}

	@After("doPointcut()")
	public void after() {
		log.info("清除分页线程");
		PageHelper.clearPage();
	}

	/**
	 * 对分页结果处理
	 *
	 * @param o o
	 */
	private Object pageResult(Object o) {
		if (o instanceof Result) {
			Result<Object> r = (Result<Object>) o;
			Object data = ((Result<?>) r).getData();
			if (data instanceof List) {
				Page<Object> page = (Page<Object>) data;
				r.setData(new PageBean<>(page.getTotal(), page.getResult()));
				return o;
			}
		}
		return o;
	}

}