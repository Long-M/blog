package com.ml.blog.exception;

import com.ml.blog.enums.ResultCodeEnum;

/**
 * @author Mr.ml
 * @date 2021/7/10
 */
public class UsernamePasswordAuthenticationException extends RuntimeException {

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsernamePasswordAuthenticationException() {
    }

    public UsernamePasswordAuthenticationException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

}
