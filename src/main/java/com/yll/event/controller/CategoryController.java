package com.yll.event.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yll.event.entity.Category;
import com.yll.event.pagehelper.Result;
import com.yll.event.service.CategoryService;
import com.yll.event.vo.req.CategoryInsertReq;
import com.yll.event.vo.req.CategoryUpdateReq;
import com.yll.event.vo.resp.CategoryRsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@Validated
@Tag(name = "文章分类相关接口", description = "文章分类相关接口")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

	private final CategoryService categoryService;

	@Operation(summary = "该接口用于获取当前已登录用户创建的所有文章分类", description = "该接口用于获取当前已登录用户创建的所有文章分类")
	@GetMapping
	public Result list() {
		List<CategoryRsp> list = categoryService.list().stream().map(p -> {
			CategoryRsp categoryRsp = new CategoryRsp();
			BeanUtil.copyProperties(p, categoryRsp);
			return categoryRsp;
		}).collect(Collectors.toList());
		return Result.success(list);
	}

	@Operation(summary = "该接口用于新增文章分类", description = "该接口用于新增文章分类")
	@PostMapping
	public Result add(@RequestBody @Validated CategoryInsertReq categoryInsertReq) {
		Category category = new Category();
		BeanUtil.copyProperties(categoryInsertReq,category);
		categoryService.save(category);
		return Result.success();
	}

	@Operation(summary = "该接口用于更新文章分类", description = "该接口用于更新文章分类")
	@PutMapping
	public Result update(@RequestBody @Validated CategoryUpdateReq categoryUpdateReq) {

		List<Category> list = categoryService
				.list(Wrappers.lambdaQuery(Category.class).eq(Category::getId, categoryUpdateReq.getId()));
		if (list.size() == 0) {
			return Result.error("文章分类不存在！");
		}
		Category category = new Category();
		category.setId(categoryUpdateReq.getId());
		category.setCategoryName(categoryUpdateReq.getCategoryName());
		category.setCategoryAlias(categoryUpdateReq.getCategoryAlias());
		categoryService.updateById(category);
		return Result.success();
	}

	@Operation(summary = "该接口用于根据ID获取文章分类详情", description = "该接口用于根据ID获取文章分类详情")
	@GetMapping("/detail")
	public Result detail(String id) {
		Category category = categoryService.getById(id);
		CategoryRsp categoryRsp = new CategoryRsp();
		if (category!=null){
			BeanUtil.copyProperties(category, categoryRsp);
		}
		return Result.success(categoryRsp);
	}

	@Operation(summary = "该接口用于根据ID删除文章分类", description = "该接口用于根据ID删除文章分类")
	@DeleteMapping
	public Result delete(String id) {
		categoryService.removeById(id);
		return Result.success();
	}

}