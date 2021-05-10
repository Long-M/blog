package com.ml.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mr.ml
 * @date 2021/4/12
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UpdateException extends RuntimeException {

    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

}
