package com.ml.blog.controller;

import com.ml.blog.entity.User;
import com.ml.blog.service.UserService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/insert}")
    public ResultVO insertUser(User user) {
        userService.saveUser(user);
        return ResultVO.success();
    }

    @DeleteMapping("/delete/{userId}")
    public ResultVO deleteUser(@PathVariable("userId") Integer userId) {
        userService.removeUser(userId);
        return ResultVO.success();
    }

    @PutMapping("/update")
    public ResultVO updateUser(User user) {
        userService.updateUser(user);
        return ResultVO.success();
    }

    @GetMapping("/get/{userId}")
    public ResultVO getUser(@PathVariable("userId") Integer userId) {
        return ResultVO.success(userService.getUser(userId));
    }

}
