package com.ml.blog.vo;

import com.ml.blog.entity.Tag;
import com.ml.blog.entity.Type;

import java.util.Date;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/4/7
 */
public class ArticleSearchVO {

    private Integer articleId;

    private String title;

    private String description;

    private String content;

    private String author;

    private Integer views;

    private Type type;

    private List<Tag> tags;

    private String firstPicture;

    private Date updateTime;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ArticleSearchVO{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", views=" + views +
                ", type=" + type +
                ", tags=" + tags +
                ", firstPicture='" + firstPicture + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

}
