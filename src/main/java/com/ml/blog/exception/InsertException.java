package com.ml.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mr.ml
 * @date 2021/4/12
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsertException extends RuntimeException {

    public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

}
