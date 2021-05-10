package com.ml.blog.security.jwt;

import com.ml.blog.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mr.ml
 * @date 2020/12/5
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtTokenUtils jwtTokenUtils;
    @Resource
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader(jwtTokenUtils.getHeader());
        if (!StringUtils.isEmpty(jwtToken)) {
            String username = jwtTokenUtils.getUsernameFromToken(jwtToken);

            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (jwtTokenUtils.validateToken(jwtToken, userDetails)) {
                    // 给使用该JWT令牌的用户进行授权
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
