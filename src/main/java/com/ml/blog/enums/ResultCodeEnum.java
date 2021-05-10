package com.ml.blog.enums;

public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    USER_NOT_NULL(401, "用户名或者密码不能为空"),
    USER_ERROR(402, "用户名或者密码不正确");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }

}
