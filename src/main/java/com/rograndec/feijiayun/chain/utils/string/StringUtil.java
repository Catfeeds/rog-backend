package com.rograndec.feijiayun.chain.utils.string;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

/**
 * 
 * @ClassName: StringUtil   
 * @Description: 字符串工具类
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月11日 下午5:55:45
 */
public class StringUtil {
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append('_');
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		if (!param.contains("_") || len <= 0)
			return param;
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static String trimStr(String str) {
		if(StringUtils.isNotBlank(str)) {
			return str.trim();
		}
		return null;
	}

	/**
	 * null 类型转 空字符串
	 * @param object
	 * @return
	 */
	public static String transferTrimStr(Object object){
		Optional<Object> op = Optional.ofNullable(object);
		if(op.isPresent()){
			Object o = op.get();
			if(o instanceof Date){
				return DateUtils.DateToString((Date) o, DateStyle.YYYY_MM_DD_HH_MM_SS);
			} else {
				return o.toString();
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 删除字符串后面的0
	 */
	public static String deleteZero(String tempString) {
		  
        int initlen = tempString.length(); // 串的初始长度
        int finallen = initlen; // 串的最终长度
        int start = 0; // 串的开始位置
        int off = 0; // 串的偏移位置
        char[] val = new char[initlen];
        tempString.getChars(0, finallen, val, 0); // 保存原数据，用于判断字符
 
        while ((start < finallen) && (val[off + finallen - 1] == '0' || val[off + finallen - 1] == '.')) {
            finallen--;
        }
        String re = ((start > 0) || (finallen < initlen)) ? tempString.substring(start, finallen): tempString;
        return StringUtils.isBlank(re)?"0":re;
    }


}
