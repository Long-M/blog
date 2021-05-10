package com.ml.blog.qq;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Mr.ml
 * @date 2021/4/7
 *
 * QQ登录的令牌，由Spring Security的QQLoginProvider来处理这种令牌的信息
 */
public class QQLoginToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 3363567749045247416L;
    private final Object principal;
    private Object credentials;
    private String accessToken;
    private String clientId;

    public QQLoginToken(Object principal, String accessToken, String clientId) {
        super(null);
        this.principal = principal;
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.credentials = null;
        setAuthenticated(false);
    }

    public QQLoginToken(Object principal, String accessToken, String clientId, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.credentials = null;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientId() {
        return clientId;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

}
