package com.rograndec.feijiayun.chain.inf.rogrand.utils.http;


/**
 * 功能描述：
 * Created by ST on 2017/10/31 14:40
 */

public class RequestEntity {

    private String mac;
    private RequestHeadEntity head;
    private Object body;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public RequestHeadEntity getHead() {
        return head;
    }

    public void setHead(RequestHeadEntity head) {
        this.head = head;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}