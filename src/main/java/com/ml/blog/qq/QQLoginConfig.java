package com.ml.blog.qq;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/4/7
 */
public class QQLoginConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        QQLoginFilter qqLoginFilter = new QQLoginFilter("/login/qq", restTemplate);
        qqLoginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        qqLoginFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        http.authenticationProvider(new QQLoginProvider(restTemplate)).addFilterBefore(qqLoginFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }

}
