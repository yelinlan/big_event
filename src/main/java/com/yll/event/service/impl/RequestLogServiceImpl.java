package com.yll.event.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yll.event.entity.RequestLog;
import com.yll.event.service.RequestLogService;
import com.yll.event.mapper.RequestLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 夜林蓝
* @description 针对表【request_log(请求日志)】的数据库操作Service实现
* @createDate 2024-05-30 19:06:25
*/
@Service
public class RequestLogServiceImpl extends ServiceImpl<RequestLogMapper, RequestLog>
    implements RequestLogService{

}




