package com.rograndec.feijiayun.chain.utils.string;

import org.apache.commons.lang3.StringUtils;

import com.rograndec.feijiayun.chain.exception.BusinessException;

public class ChineseString {

    /**
     * 判断字符串是否有中文,有返回true
     * @param code
     * @return
     */
    public static boolean isChinese(String code) {
        char[] ch = code.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @Description: 编码不允许有汉字
     * @author yuting.li
     * @version 1.0 
     * @date 2017年11月1日 上午10:50:21 
     * @param code 
     * @return void
     */
    public static void checkCode(String code) {
    	if(StringUtils.isNotBlank(code)) {
    		if(isChinese(code)) {
    			throw new BusinessException("编码不允许有汉字");
    		}
    	}
    }
    
    public static void main(String[] args) {
        System.out.println(isChinese("saldgnadslkglasd"));
        System.out.println(isChinese("马东"));
        System.out.println(isChinese("马东，。？！%……&*（）——{}【】”"));
    }
}
