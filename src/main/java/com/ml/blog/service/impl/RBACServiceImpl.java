package com.ml.blog.service.impl;

import com.ml.blog.service.RBACService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.ml
 * @date 2020/12/5
 */
@Service("rbacService")
public class RBACServiceImpl implements RBACService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(request.getRequestURI());
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);
        }
        return false;
    }

}
