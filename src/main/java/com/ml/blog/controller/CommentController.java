package com.ml.blog.controller;

import com.ml.blog.entity.Comment;
import com.ml.blog.service.CommentService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/insert")
    public ResultVO insertComment(Comment comment) {
        commentService.saveComment(comment);
        return ResultVO.success();
    }

    @GetMapping("/list/{articleId}")
    public ResultVO listCommentsByArticleId(@PathVariable("articleId") Integer articleId) {
        return ResultVO.success(commentService.listCommentsByArticleId(articleId));
    }

}
