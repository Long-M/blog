package com.ml.blog.service.impl;

import com.ml.blog.entity.FriendLink;
import com.ml.blog.exception.DeleteException;
import com.ml.blog.exception.InsertException;
import com.ml.blog.exception.SelectException;
import com.ml.blog.exception.UpdateException;
import com.ml.blog.mapper.FriendLinkMapper;
import com.ml.blog.service.FriendLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Resource
    private FriendLinkMapper friendLinkMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveFriendLink(FriendLink friendLink) {
        int res = friendLinkMapper.insertFriendLink(friendLink);
        if (res == -1) {
            throw new InsertException("");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeFriendLink(Integer id) {
        int res = friendLinkMapper.deleteFriendLink(id);
        if (res == -1) {
            throw new DeleteException("");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateFriendLink(FriendLink friendLink) {
        int res = friendLinkMapper.updateFriendLink(friendLink);
        if (res == -1) {
            throw new UpdateException("");
        }
        return res;
    }

    @Override
    public FriendLink getFriendLink(Integer id) {
        FriendLink friendLink = friendLinkMapper.getFriendLink(id);
        if (friendLink == null) {
            throw new SelectException("");
        }
        return friendLink;
    }

    @Override
    public FriendLink getFriendLinkByBlogger(String blogger) {
        FriendLink friendLink = friendLinkMapper.getFriendLinkByBlogger(blogger);
        if (friendLink == null) {
            throw new SelectException("");
        }
        return friendLink;
    }

    @Override
    public List<FriendLink> listFriendLinks() {
        List<FriendLink> friendLinks = friendLinkMapper.listFriendLinks();
        if (friendLinks == null) {
            throw new SelectException("");
        }
        return friendLinks;
    }

}
