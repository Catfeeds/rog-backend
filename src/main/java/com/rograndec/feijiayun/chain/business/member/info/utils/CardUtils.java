package com.rograndec.feijiayun.chain.business.member.info.utils;


import java.util.regex.Pattern;

/**
 * Created by ST on 2017/3/30.
 */
public class CardUtils {


    /**
     * 根据规则拼接卡号
     * @param number 卡号去除前缀的内容
     * @param prefix 卡号的前缀
     * @param length 该卡号的总长度
     * @return
     */
    public static String getCardNumber(Integer number, String prefix,Integer length){

        int len = number.toString().length();//非0数字的长度
        int diff = length - len;//需要补充的0的个数
        String cardNumber = "";
        if(diff == 1){
            cardNumber = "0" + number;
        }else if(diff == 2){
            cardNumber = "00" + number;
        }else if(diff == 3){
            cardNumber = "000" + number;
        }else if(diff == 4){
            cardNumber = "0000" + number;
        }else if(diff == 5){
            cardNumber = "00000" + number;
        }else if(diff == 6){
            cardNumber = "000000" + number;
        }else if(diff == 7){
            cardNumber = "0000000" + number;
        }else {
            cardNumber = number.toString();
        }
        return prefix + cardNumber;
    }

    /**
     * 判断一串字符串中是否全是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断一串字符串的长度需要在6-20位
     * @param str
     * @return
     */
    public static boolean safeLength(String str) {
        if (str == null){
            return false;
        }
        if (str.length() < 6 || str.length() > 20){
            return false;
        }
        return true;
    }


}
