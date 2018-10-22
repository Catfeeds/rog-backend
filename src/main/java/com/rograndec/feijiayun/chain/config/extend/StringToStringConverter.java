package com.rograndec.feijiayun.chain.config.extend;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToStringConverter implements Converter<String, String> {

	@Override
	public String convert(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}
		return s;
	}
}
