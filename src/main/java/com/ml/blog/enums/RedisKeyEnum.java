package com.ml.blog.enums;

/**
 * @author Mr.ml
 * @date 2021/4/6
 */
public enum RedisKeyEnum {

    ARTICLE_VIEW_COUNT("ARTICLE_VIEW_COUNT"),
    USER_LIKE_ARTICLE_KEY("USER_LIKE_ARTICLE_KEY"),
    ARTICLE_LIKED_USER_KEY("ARTICLE_LIKED_USER_KEY");

    private String value;

    RedisKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
