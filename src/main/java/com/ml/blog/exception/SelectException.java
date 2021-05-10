package com.ml.blog.exception;

/**
 * @author Mr.ml
 * @date 2021/4/12
 */
public class SelectException extends RuntimeException {

    public SelectException() {
    }

    public SelectException(String message) {
        super(message);
    }

    public SelectException(String message, Throwable cause) {
        super(message, cause);
    }

}
