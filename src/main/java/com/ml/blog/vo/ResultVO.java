package com.ml.blog.vo;

import com.ml.blog.enums.ResultCodeEnum;

import java.io.Serializable;

/**
 * @author Mr.ml
 * @date 2020/11/15
 */
public class ResultVO implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(ResultCodeEnum.SUCCESS);
        return resultVO;
    }

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(ResultCodeEnum.SUCCESS);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static ResultVO error(ResultCodeEnum resultCodeEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(resultCodeEnum);
        return resultVO;
    }

    public static ResultVO error(ResultCodeEnum resultCodeEnum, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(resultCodeEnum);
        resultVO.setData(data);
        return resultVO;
    }

}
