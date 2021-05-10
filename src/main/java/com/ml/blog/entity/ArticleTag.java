package com.ml.blog.entity;

public class ArticleTag {

    private Integer articleTagId;

    private Integer articleId;

    private Integer tagId;

    public Integer getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(Integer articleTagId) {
        this.articleTagId = articleTagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public ArticleTag() {
    }

    public ArticleTag(Integer articleId, Integer articleTagId) {
        this.articleId = articleId;
        this.articleTagId = articleTagId;
    }

    public ArticleTag(Integer articleTagId, Integer articleId, Integer tagId) {
        this.articleTagId = articleTagId;
        this.articleId = articleId;
        this.tagId = tagId;
    }

}