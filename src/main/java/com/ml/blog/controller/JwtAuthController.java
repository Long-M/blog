package com.ml.blog.controller;

import com.ml.blog.service.JwtAuthService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/29
 */
@RestController
public class JwtAuthController {

    @Resource
    private JwtAuthService jwtAuthService;

    @PostMapping("/authentication")
    public ResultVO login(String username, String password) {
        jwtAuthService.login(username, password);
        return ResultVO.success();
    }

    @PostMapping("/refreshToken")
    public ResultVO refresh(@RequestHeader("${jwt.header}") String token) {
        return ResultVO.success(jwtAuthService.refreshToken(token));
    }

    @PostMapping("/register")
    public ResultVO register() {
        return null;
    }

}
