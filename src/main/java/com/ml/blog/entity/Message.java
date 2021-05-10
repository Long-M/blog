package com.ml.blog.entity;

import java.util.Date;

public class Message {

    private Integer messageId;

    private String content;

    private String email;

    private String username;

    private Date createTime;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Message() {
    }

    public Message(Integer messageId, String content, String email, String username, Date createTime) {
        this.messageId = messageId;
        this.content = content;
        this.email = email;
        this.username = username;
        this.createTime = createTime;
    }

}