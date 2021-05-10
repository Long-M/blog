package com.ml.blog.entity;

public class FriendLink {

    private Integer friendLinkId;

    private String url;

    private String blogger;

    private String avatar;

    private String description;

    public Integer getFriendLinkId() {
        return friendLinkId;
    }

    public void setFriendLinkId(Integer friendLinkId) {
        this.friendLinkId = friendLinkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBlogger() {
        return blogger;
    }

    public void setBlogger(String blogger) {
        this.blogger = blogger;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FriendLink() {
    }

    public FriendLink(Integer friendLinkId, String url, String blogger, String avatar, String description) {
        this.friendLinkId = friendLinkId;
        this.url = url;
        this.blogger = blogger;
        this.avatar = avatar;
        this.description = description;
    }

}