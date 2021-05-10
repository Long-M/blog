package com.ml.blog.controller;

import com.ml.blog.entity.FriendLink;
import com.ml.blog.service.FriendLinkService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@RestController
@RequestMapping("/friendLink")
public class FriendLinkController {

    @Resource
    private FriendLinkService friendLinkService;

    @PostMapping("/insert}")
    public ResultVO insertFriendLink(FriendLink friendLink) {
        friendLinkService.saveFriendLink(friendLink);
        return ResultVO.success();
    }

    @DeleteMapping("/delete/{friendLinkId}")
    public ResultVO deleteFriendLink(@PathVariable("friendLinkId") Integer friendLinkId) {
        friendLinkService.removeFriendLink(friendLinkId);
        return ResultVO.success();
    }

    @PutMapping("/update")
    public ResultVO updateFriendLink(FriendLink friendLink) {
        friendLinkService.updateFriendLink(friendLink);
        return ResultVO.success();
    }

    @GetMapping("/get/{friendLinkId}")
    public ResultVO getFriendLink(@PathVariable("friendLinkId") Integer friendLinkId) {
        friendLinkService.getFriendLink(friendLinkId);
        return ResultVO.success();
    }

}
