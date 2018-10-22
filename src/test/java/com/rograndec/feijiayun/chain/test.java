package com.rograndec.feijiayun.chain;

import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaiwei on 2017/8/31.
 */
public class test {

    public static void main(String[] args) {
        String str = "asasass";
        String trg = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\u4e00-\\u9fa5_a-zA-Z0-9]";
        Pattern p = Pattern.compile(trg);
        Matcher m = p.matcher(str);
        boolean matches = m.find();
        String length = m.replaceAll("").trim();
        System.out.println(matches);
        System.out.println(length.length());

    }

    private boolean specialTxt(String s){
        //String trg = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\u4e00-\\u9fa5_a-zA-Z0-9]";
        Pattern p;
        p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\u4e00-\\\\u9fa5_a-zA-Z0-9]");
        Matcher m = p.matcher(s);
        String result = m.replaceAll("").trim();
        if (result.length() > 0){
            return true;
        }
        return false;
    }

    public static boolean check(){
        boolean flag = false;
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();

        aList.add(1);
        aList.add(2);
        aList.add(3);
        aList.add(4);


        bList.add(2);
        bList.add(3);
        bList.add(4);
        bList.add(5);

        for(Integer a : aList){
            for(Integer b : bList){
                if(a == b){
                    System.out.println(a);
                    System.out.println("====");
                    System.out.println(b);
                    return true;

                }
            }
        }

        return flag;
    }
}
