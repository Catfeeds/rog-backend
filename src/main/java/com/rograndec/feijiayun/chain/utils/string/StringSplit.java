package com.rograndec.feijiayun.chain.utils.string;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaiwei on 2017/9/1.
 */
public class StringSplit {

    public static List<Long> strSplit(String str){
        List<Long>  stringList = new ArrayList<>();
        if(!StringUtils.isEmpty(str)){
            String[] strings = str.split(",");
            for(String sr : strings){
                if(!StringUtils.isEmpty(sr)){
                    stringList.add(Long.parseLong(sr));
                }
            }
        }


        return stringList;
    }

    public static String StringAppendSymbol(List strings){

        StringBuffer stringBuffer = new StringBuffer();
        if(CollectionUtils.isEmpty(strings)){
            return "";
        }
        for(int i = 0 ; i < strings.size() ; i ++ ){
            stringBuffer.append(strings.get(i).toString());
            if(i < strings.size() - 1){
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }
}
