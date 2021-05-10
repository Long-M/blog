package com.ml.blog.mapper;

import com.ml.blog.entity.Article;
import com.ml.blog.vo.ResultVO;

import java.util.List;

public interface ArticleMapper {
    int insertArticle(Article article);

    int deleteArticle(Integer articleId);

    int updateArticle(Article article);

    Article getArticle(Integer articleId);

    List<Article> listArticles();

    List<Article> listArticlesByTypeId(Integer typeId);

    List<Article> listRecommendArticlesTop();

    List<String> listGroupYear();

    List<Article> listArticlesByYear(String year);

    int updateViews(Integer articleId);

    int count();
}