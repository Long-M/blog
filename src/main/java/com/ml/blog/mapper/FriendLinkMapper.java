package com.ml.blog.mapper;

import com.ml.blog.entity.FriendLink;

import java.util.List;

public interface FriendLinkMapper {
    int insertFriendLink(FriendLink friendLink);

    int deleteFriendLink(Integer friendLinkId);

    int updateFriendLink(FriendLink friendLink);

    FriendLink getFriendLink(Integer friendLinkId);

    FriendLink getFriendLinkByBlogger(String blogger);

    List<FriendLink> listFriendLinks();
}