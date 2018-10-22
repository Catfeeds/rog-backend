package com.rograndec.feijiayun.chain.inf.rogrand.utils;

import com.rograndec.feijiayun.chain.utils.MD5.MD5Utils;



/**
 * 功能描述：加密工具
 * Created by ST on 2017/10/31 13:50
 */

public class EncryptUtil{

    /**
     * 加密盐
     */
    private final static String SALT = "rograndec";

    /**
     * 为一套加密算法；保证数据传输过程中的安全性、完整性。
     目前的算法为：MD5(head节点的字符串 +  body节点字符串 + 固定加密串)，对比md5 的结果后8位，不区分大小写。
     康康买药系统接入的固定加密串为：kkmy。
     其它系统接入的固定加密串为：rograndec。
     * @param head
     * @param body
     * @return
     */
    public static String encrypt(String head,String body){
        String md5String = MD5Utils.getMD5String(head + body + SALT);
        return md5String.substring(md5String.length() - 8,md5String.length());
    }


    public static void main(String[] args) {
        String encrypt = EncryptUtil.encrypt("1", "2");
        System.out.println(encrypt);
    }

}