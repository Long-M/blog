package com.ml.blog.enums;

public enum ResultCodeEnum {

    SUCCESS(2000, "成功"),
    FAIL(4000, "失败"),

    INSERT_FAIL(4001, "插入失败"),
    DELETE_FAIL(4002, "删除失败"),
    UPDATE_FAIL(4003, "更新失败"),
    SELECT_FAIL(4005, "查询失败"),
    NOT_FOUNT(4004, "资源不存在"),

    TOKEN_EXPIRE(4006, "Token已过期"),
    TOKEN_GENERATED_FAIL(4007, "Token生成失败"),
    USER_NOT_NULL(4008, "用户名或者密码不能为空"),
    USER_ERROR(4009, "用户名或者密码不正确");

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
