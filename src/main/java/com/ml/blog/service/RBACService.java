package com.ml.blog.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.ml
 * @date 2020/12/5
 */
public interface RBACService {

    boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication);

}
