package com.ml.blog.mapper;

import com.ml.blog.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int insertComment(Comment comment);

    int deleteComment(Integer commentId);

    int updateComment(Comment comment);

    Comment getComment(Integer commentId);

    List<Comment> listCommentsByArticleId(Integer articleId);
}