package com.yll.event.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yll.event.entity.Article;
import com.yll.event.service.ArticleService;
import com.yll.event.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author 夜林蓝
* @description 针对表【article】的数据库操作Service实现
* @createDate 2024-05-12 15:26:28
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




