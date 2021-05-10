package com.ml.blog.form;

import java.util.Date;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
public class ArticleUpdateForm {

    private Integer articleId;

    private String title;

    private String description;

    private String content;

    private String author;

    private Integer views;

    private Integer typeId;

    private String firstPicture;

    private String flag;

    private Date createTime;

    private Date updateTime;

    private Boolean appreciation;

    private Boolean commentabled;

    private Boolean published;

    private Boolean recommend;

    private Boolean shareStatement;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(Boolean commentabled) {
        this.commentabled = commentabled;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(Boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public ArticleUpdateForm() {
    }

    public ArticleUpdateForm(Integer articleId, String title, String description, String content, String author, Integer views, Integer typeId, String firstPicture, String flag, Date createTime, Date updateTime, Boolean appreciation, Boolean commentabled, Boolean published, Boolean recommend, Boolean shareStatement) {
        this.articleId = articleId;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.views = views;
        this.typeId = typeId;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.appreciation = appreciation;
        this.commentabled = commentabled;
        this.published = published;
        this.recommend = recommend;
        this.shareStatement = shareStatement;
    }

}
