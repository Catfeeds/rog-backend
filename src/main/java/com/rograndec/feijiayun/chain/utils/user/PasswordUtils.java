package com.rograndec.feijiayun.chain.utils.user;

import com.rograndec.feijiayun.chain.utils.MD5.MD5Utils;

/**
 * Created by zhaiwei on 2017/8/31.
 */
public class PasswordUtils {


    private static String DEFULT_PASSWORD = "00000000";

    /**
     * md5加密密码
     * @param password
     * @return
     */
  /*  public static String MD5Password(String password){
        return MD5Utils.getMD5String(password);
    }*/

    /**
     * 校验密码和md5 值是否一样
     * @param password
     * @param MD5Password
     * @return
     */
    public static boolean checkPassword4MD5(String password,String MD5Password){
        return MD5Utils.checkPassword(password,MD5Password);
    }

    /**
     * 重置为默认密码
     * @return
     */
    /*public static String resetPassword2MD5(){
        return MD5Password(DEFULT_PASSWORD);
    }*/

    public static String getDefultPassword(){

        return DEFULT_PASSWORD;
    }
}
