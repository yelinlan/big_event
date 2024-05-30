package com.yll.event.logrecord;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yll.event.anno.AutoPage;
import com.yll.event.entity.RequestLog;
import com.yll.event.pagehelper.Result;
import com.yll.event.service.RequestLogService;
import com.yll.event.vo.req.RequestLogSearchCondition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@项目名称: big_event
 *@类名称: UserController
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/5/13 20:57
 **/
@RestController
@RequestMapping("/log")
@Validated
@Tag(name = "日志相关接口", description = "日志相关接口")
@RequiredArgsConstructor
@CrossOrigin
public class RequestLogController {

	private final RequestLogService requestLogService;

	@Operation(summary = "该接口用于根据条件查询请求日志,带分页", description = "该接口用于根据条件查询请求日志,带分页")
	@GetMapping
	@AutoPage
	public Result list(@Validated RequestLogSearchCondition sc) {
		System.out.println(JSONUtil.toJsonStr(sc));
		LambdaQueryWrapper<RequestLog> query = Wrappers.lambdaQuery(RequestLog.class);
		if (StrUtil.isNotBlank(sc.getSearchKey())) {
			query.or().like(RequestLog::getClassName, sc.getSearchKey());
			query.or().like(RequestLog::getCreateUser, sc.getSearchKey());
			query.or().like(RequestLog::getParams, sc.getSearchKey());
			query.or().like(RequestLog::getReturnParams, sc.getSearchKey());
			query.or().like(RequestLog::getMethodName, sc.getSearchKey());
		}
		if (StrUtil.isAllNotBlank(sc.getOrderBy())){
			query.last("order by "+sc.getOrderBy()+" "+(sc.isAsc()?"asc":"desc"));
			System.out.println((sc.isAsc()?"asc":"desc"));
		}
		return Result.success(requestLogService.list(query));
	}

}