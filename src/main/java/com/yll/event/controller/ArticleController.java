package com.yll.event.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yll.event.anno.AutoPage;
import com.yll.event.entity.Article;
import com.yll.event.entity.Category;
import com.yll.event.pagehelper.Result;
import com.yll.event.service.ArticleService;
import com.yll.event.service.CategoryService;
import com.yll.event.vo.req.ArticleInsertReq;
import com.yll.event.vo.req.ArticleSearchCondition;
import com.yll.event.vo.req.ArticleUpdateReq;
import com.yll.event.vo.resp.ArticleRsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *@项目名称: big_event
 *@类名称: UserController
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/5/13 20:57
 **/
@RestController
@RequestMapping("/article")
@Validated
@Tag(name = "文章管理相关接口", description = "文章管理相关接口")
@RequiredArgsConstructor
@CrossOrigin
public class ArticleController {

	private final ArticleService articleService;
	private final CategoryService categoryService;

	@Operation(summary = "该接口用于新增文章(发布文章)", description = "该接口用于新增文章(发布文章)")
	@PostMapping
	public Result add(@RequestBody @Validated ArticleInsertReq articleInsertReq) {
		Category category = categoryService.getById(articleInsertReq.getCategoryId());
		if (category == null || category.getId() == null) {
			return Result.error("文章分类不存在！");
		}
		Article article = new Article();
		BeanUtil.copyProperties(articleInsertReq, article);
		articleService.save(article);
		return Result.success();
	}

	@Operation(summary = "该接口用于更新文章信息", description = "该接口用于更新文章信息")
	@PutMapping
	public Result update(@RequestBody @Validated ArticleUpdateReq articleUpdateReq) {
		Article articleDb = articleService.getById(articleUpdateReq.getId());
		if (articleDb == null || articleDb.getId() == null) {
			return Result.error("文章详情不存在！");
		}
		Article article = new Article();
		BeanUtil.copyProperties(articleUpdateReq, article);
		articleService.updateById(article);
		return Result.success();
	}


	@Operation(summary = "该接口用于根据ID获取文章详细信息", description = "该接口用于根据ID获取文章详细信息")
	@GetMapping("/detail")
	public Result detail(String id) {
		Article article = articleService.getById(id);
		ArticleRsp articleRsp = new ArticleRsp();
		if (article != null) {
			BeanUtil.copyProperties(article, articleRsp);
		}
		return Result.success(articleRsp);
	}

	@Operation(summary = "该接口用于根据ID删除文章", description = "该接口用于根据ID删除文章")
	@DeleteMapping
	public Result delete(String id) {
		articleService.removeById(id);
		return Result.success();
	}

	@Operation(summary = "该接口用于根据条件查询文章,带分页", description = "该接口用于根据条件查询文章,带分页")
	@GetMapping
	@AutoPage
	public Result list(@Validated ArticleSearchCondition sc) {
		LambdaQueryWrapper<Article> query = Wrappers.lambdaQuery(Article.class);
		query.eq(Article::getCategoryId, sc.getCategoryId());
		query.eq(StrUtil.isNotBlank(sc.getState()),Article::getState, sc.getState());
		return Result.success(articleService.list(query));
	}

}