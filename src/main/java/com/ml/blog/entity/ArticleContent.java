package com.ml.blog.entity;

public class ArticleContent {

    private Integer articleContentId;

    private String articleContent;

    public Integer getArticleContentId() {
        return articleContentId;
    }

    public void setArticleContentId(Integer articleContentId) {
        this.articleContentId = articleContentId;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public ArticleContent() {
    }

    public ArticleContent(Integer articleContentId, String articleContent) {
        this.articleContentId = articleContentId;
        this.articleContent = articleContent;
    }

}