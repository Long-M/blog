package com.ml.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mr.ml
 * @date 2021/4/12
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeleteException extends RuntimeException {

    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

}
