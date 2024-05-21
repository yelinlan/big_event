package com.yll.event.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yll.event.entity.Category;
import com.yll.event.service.CategoryService;
import com.yll.event.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 夜林蓝
* @description 针对表【category】的数据库操作Service实现
* @createDate 2024-05-12 15:26:28
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




