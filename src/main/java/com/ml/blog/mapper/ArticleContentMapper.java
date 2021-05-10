package com.ml.blog.mapper;

import com.ml.blog.entity.ArticleContent;

public interface ArticleContentMapper {
    int insertArticleContent(ArticleContent articleContent);

    int deleteArticleContent(Integer articleContentId);

    int updateArticleContent(ArticleContent articleContent);

    ArticleContent getArticleContent(Integer articleContentId);
}