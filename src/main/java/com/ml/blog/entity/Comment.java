package com.ml.blog.entity;

import java.util.Date;
import java.util.List;

public class Comment {

    private Integer commentId;

    private String username;

    private String content;

    private String email;

    private String avatar;

    private Date createTime;

    private Integer articleId;

    private Integer parentCommentId;

    private Boolean adminComment;

    private List<Comment> replyComments;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment() {
    }

    public Comment(Integer commentId, String username, String content, String email, String avatar, Date createTime, Integer articleId, Integer parentCommentId, Boolean adminComment, List<Comment> replyComments) {
        this.commentId = commentId;
        this.username = username;
        this.content = content;
        this.email = email;
        this.avatar = avatar;
        this.createTime = createTime;
        this.articleId = articleId;
        this.parentCommentId = parentCommentId;
        this.adminComment = adminComment;
        this.replyComments = replyComments;
    }

}