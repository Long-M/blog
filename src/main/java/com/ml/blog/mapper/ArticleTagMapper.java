package com.ml.blog.mapper;

import com.ml.blog.entity.ArticleTag;

import java.util.List;

public interface ArticleTagMapper {
    int insertArticleTag(ArticleTag articleTag);

    int deleteArticleTag(Integer articleTagId);

    int deleteArticleTagByArticleId(Integer articleId);

    int deleteArticleTagByTagId(Integer tagId);

    int updateArticleTag(ArticleTag articleTag);

    ArticleTag getArticleTag(Integer articleTagId);

    List<ArticleTag> listArticleTagByArticleId(Integer articleId);

    List<ArticleTag> listArticleTagByTagId(Integer tagId);
}