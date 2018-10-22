package com.rograndec.feijiayun.chain.utils;

import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：
 * Created by ST on 2017/11/13 14:55
 */

public class ExcelMethodUtils {

    public static boolean checkPattern(String source) {
        boolean isOk = true;
        if(Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}-\\d{1,2}-\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$", source)
                || Pattern.matches("^\\d{1,2}/\\d{1,2}/\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}/\\d{1,2}/\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}\\d{1,2}\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}\\d{1,2}\\d{1,2} \\d{1,2}\\d{1,2}\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}\\.\\d{1,2}\\.\\d{1,2}$", source)
                || Pattern.matches("^\\d{4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}\\.\\d{1,2}\\.\\d{1,2}$", source)
                ){
        } else {
            isOk = false;
        }
        return isOk;
    }
    public static String getDate(String source) {
        Date date = null;
        if(Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYY_MM_DD.getValue());
        } else if (Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}-\\d{1,2}-\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYY_MM_DD_HH_MM_SS_.getValue());
        } else if(Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
        } else if( Pattern.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYY_MM_DD_EN.getValue());
        }else if( Pattern.matches("^\\d{1,2}/\\d{1,2}/\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.MM_DD_YY.getValue());
        } else if(Pattern.matches("^\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}/\\d{1,2}/\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.DD_MM_YY.getValue());
        } else if(Pattern.matches("^\\d{4}\\d{1,2}\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYYMMDD.getValue());
        } else if(Pattern.matches("^\\d{4}\\d{1,2}\\d{1,2} \\d{1,2}\\d{1,2}\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYYMMDDHHMMSS.getValue());
        } else if(Pattern.matches("^\\d{4}\\.\\d{1,2}\\.\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYYMMDD_.getValue());
        } else if(Pattern.matches("^\\d{4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}\\.\\d{1,2}\\.\\d{1,2}$", source)){
            date = DateUtils.StringToDate(source, DateStyle.YYYYMMDDHHMMSS_.getValue());
        }
        else {
            return  null;
        }
        return DateUtils.DateToString(date,DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
    }

    /**
     * 校验一个字符串是否为整数
     * @param string
     * @return true 为整数
     */
    public static  boolean isPureDigital(String string) {
        String regEx1 = "[0-9]\\d*";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 校验一个字符串是否小数
     * @param string
     * @return true 为小数
     */
    public static  boolean isDecimals(String string) {
        String regEx1 = "\\d+(\\.\\d{1,6})?";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {

        System.out.println(isDecimals("0.007"));
        System.out.println(isDecimals("8"));
    }


}