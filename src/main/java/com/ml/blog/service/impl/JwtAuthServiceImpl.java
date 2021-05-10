package com.ml.blog.service.impl;

import com.ml.blog.exception.TokenExpiredException;
import com.ml.blog.exception.TokenGeneratedException;
import com.ml.blog.exception.UsernamePasswordAuthenticationException;
import com.ml.blog.exception.UsernamePasswordEmptyException;
import com.ml.blog.security.jwt.JwtTokenUtils;
import com.ml.blog.service.JwtAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/29
 */
@Service
public class JwtAuthServiceImpl implements JwtAuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 登录认证换取JWT令牌
     *
     * @return JWT
     */
    @Override
    public String login(String username, String password) {
        if (StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)) {
            throw new UsernamePasswordEmptyException("用户名或者密码不能为空");
        }

        try {
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new UsernamePasswordAuthenticationException("用户名或者密码不正确");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtils.generateToken(userDetails);
        if (token == null || token.length() == 0) {
            throw new TokenGeneratedException("");
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        if (jwtTokenUtils.isTokenExpired(oldToken)) {
            throw new TokenExpiredException("Token过期");
        }
        return jwtTokenUtils.refreshToken(oldToken);
    }

}
