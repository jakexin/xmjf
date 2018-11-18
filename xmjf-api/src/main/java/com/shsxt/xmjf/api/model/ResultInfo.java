package com.shsxt.xmjf.api.model;

import com.shsxt.xmjf.api.constant.P2pConstant;

import java.io.Serializable;

/**
 * 返回前台的消息对象
 */
public class ResultInfo implements Serializable {

    private static final long serialVersionUID = -7622269999864718942L;
    public String message=P2pConstant.OPS_SUCCESS_MSG;
    public Integer code=P2pConstant.OPS_SUCCESS_CODE;

    public static ResultInfo success() {
        ResultInfo result = new ResultInfo();
        result.setMessage(P2pConstant.OPS_SUCCESS_MSG);
        result.setCode(P2pConstant.OPS_SUCCESS_CODE);
        return result;
    }

    public static ResultInfo error() {
        ResultInfo result = new ResultInfo();
        result.setMessage(P2pConstant.OPS_ERROR_MSG);
        result.setCode(P2pConstant.OPS_ERROR_CODE);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
