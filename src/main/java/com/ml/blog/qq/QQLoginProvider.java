package com.ml.blog.qq;

import com.ml.blog.entity.User;
import com.ml.blog.util.FastJSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mr.ml
 * @date 2021/4/7
 *
 * 根据用户的openId去获取在系统中的实际用户信息
 */
public class QQLoginProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取QQ的用户信息
     */
    private static final String GET_USER_INFO = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";

    private RestTemplate restTemplate;

    public QQLoginProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        QQLoginToken qqLoginToken = (QQLoginToken) authentication;
        String openId = (String) qqLoginToken.getPrincipal();
        String accessToken = qqLoginToken.getAccessToken();
        String oauthConsumerKey = qqLoginToken.getClientId();
        String userInfo = restTemplate.getForObject(String.format(GET_USER_INFO, accessToken, oauthConsumerKey, openId), String.class);
        logger.info("获取到的qq登录信息为:{}", userInfo);
        QQUserInfo qqUserInfo = FastJSONUtils.deserialize(userInfo, QQUserInfo.class);
        if (qqUserInfo.getRet() < 0) {
            throw new InternalAuthenticationServiceException(qqUserInfo.getMsg());
        }
        User securityUser = new User();
        securityUser.setUsername(qqUserInfo.getNickname());
        securityUser.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
        QQLoginToken token = new QQLoginToken(securityUser, qqLoginToken.getAccessToken(), qqLoginToken.getClientId(), securityUser.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return QQLoginToken.class.isAssignableFrom(authentication);
    }

}
