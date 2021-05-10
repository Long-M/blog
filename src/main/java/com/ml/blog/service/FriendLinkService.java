package com.ml.blog.service;

import com.ml.blog.entity.FriendLink;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface FriendLinkService {

    int saveFriendLink(FriendLink friendLink);

    int removeFriendLink(Integer friendLinkId);

    int updateFriendLink(FriendLink friendLink);

    FriendLink getFriendLink(Integer friendLinkId);

    FriendLink getFriendLinkByBlogger(String blogger);

    List<FriendLink> listFriendLinks();

}
