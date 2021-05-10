package com.ml.blog.controller;

import com.ml.blog.form.ArticleInsertForm;
import com.ml.blog.form.ArticleUpdateForm;
import com.ml.blog.service.ArticleService;
import com.ml.blog.vo.ResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/insert")
    public ResultVO insertArticle(ArticleInsertForm articleInsertForm, String tagIds) {
        articleService.saveArticle(articleInsertForm, tagIds);
        return ResultVO.success();
    }

    @DeleteMapping("/delete/{articleId}")
    public ResultVO deleteArticle(@PathVariable("articleId") Integer articleId) {
        articleService.removeArticle(articleId);
        return ResultVO.success();
    }

    @PutMapping("/update")
    public ResultVO updateArticle(ArticleUpdateForm articleUpdateForm, String tagIds) {
        articleService.updateArticle(articleUpdateForm, tagIds);
        return ResultVO.success();
    }

    @GetMapping("/get/{articleId}")
    public ResultVO getArticle(@PathVariable("articleId") Integer articleId) {
        return ResultVO.success(articleService.getAndConvert(articleId));
    }

    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResultVO listArticles(@RequestParam(defaultValue = "1") @PathVariable("pageNum") Integer pageNum,
                                 @RequestParam(defaultValue = "8") @PathVariable("pageSize") Integer pageSize) {
        return ResultVO.success(articleService.listArticles(pageNum, pageSize));
    }

    @GetMapping("/list/recommend/{size}")
    public ResultVO listRecommendArticlesTop(@PathVariable("size") Integer size) {
        return ResultVO.success(articleService.listRecommendArticlesTop(size));
    }

    @GetMapping("/list/{typeId}")
    public ResultVO listArticlesByTypeId(@PathVariable("typeId") Integer typeId) {
        return ResultVO.success(articleService.listArticlesByTypeId(typeId, 1, 8));
    }

    @GetMapping("/counts")
    public ResultVO counts() {
        return ResultVO.success(articleService.countArticles());
    }

    @GetMapping("/query")
    public ResultVO queryArticle(String keyword) {
        return ResultVO.success(articleService.queryArticle(keyword));
    }

    @GetMapping("/archive")
    public ResultVO archiveArticles() {
        return ResultVO.success(articleService.archiveArticles());
    }

    @PostMapping("/like")
    public ResultVO likeArticle(@RequestParam("userId") @Min(value = 1, message = "用户id不能小于1") Integer userId,
                                @RequestParam("articleId") @Min(value = 1, message = "文章id不能小于1") Integer articleId) {
        return ResultVO.success(articleService.likeArticle(userId, articleId));
    }

    @PostMapping("/unlike")
    public ResultVO unlikeArticle(@RequestParam("userId") @Min(value = 1, message = "用户id不能小于1") Integer userId,
                                  @RequestParam("articleId") @Min(value = 1, message = "文章id不能小于1") Integer articleId) {
        articleService.unlikeArticle(userId, articleId);
        return ResultVO.success();
    }

    @GetMapping("/countLike")
    public ResultVO countArticleLike(@RequestParam("articleId") @Min(value = 1, message = "文章id不能小于1") Integer articleId) {
        articleService.countArticleLike(articleId);
        return ResultVO.success();
    }

}
