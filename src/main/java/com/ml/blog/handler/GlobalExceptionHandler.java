package com.ml.blog.handler;

import com.ml.blog.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author Mr.ml
 * @date 2021/4/6
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO handleMethodArgumentNotValidException(ConstraintViolationException exception) {
        logger.error(exception.getMessage(), exception);
        String message = exception.getMessage();
        return ResultVO.error(400, message);
    }

}
