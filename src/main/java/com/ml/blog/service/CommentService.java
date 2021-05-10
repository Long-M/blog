package com.ml.blog.service;

import com.ml.blog.entity.Comment;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface CommentService {

    /**
     * 保存评论信息
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

    /**
     * 根据文章id查找评论信息
     * @param articleId
     * @return
     */
    List<Comment> listCommentsByArticleId(Integer articleId);

}
