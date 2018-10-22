package com.rograndec.feijiayun.chain.business.retail.royalty.exception;

/**
 * Created by zhaiwei on 2017/9/28.
 */
public class RoyaltyBizException extends RuntimeException{

    public static String DEFULT_ERRO_CODE = "111111111";
    private String retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

    public RoyaltyBizException() {
        super();
    }

    public RoyaltyBizException(String message) {
        super(message);
        msgDes = message;
    }

    public RoyaltyBizException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
