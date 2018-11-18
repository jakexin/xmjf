package com.shsxt.xmjf.api.exceptions;

import com.shsxt.xmjf.api.constant.P2pConstant;

public class BusiException extends  RuntimeException {
    private Integer code= P2pConstant.OPS_ERROR_CODE;
    private  String msg=P2pConstant.OPS_ERROR_MSG;

    public BusiException() {
        super(P2pConstant.OPS_ERROR_MSG);
    }

    public BusiException(Integer code) {
        super(P2pConstant.OPS_ERROR_MSG);
        this.code = code;
    }

    public BusiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusiException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
