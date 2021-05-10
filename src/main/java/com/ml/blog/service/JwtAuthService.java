package com.ml.blog.service;

/**
 * @author Mr.ml
 * @date 2021/3/29
 */
public interface JwtAuthService {

    String login(String username, String password);

    String refreshToken(String oldToken);

}
