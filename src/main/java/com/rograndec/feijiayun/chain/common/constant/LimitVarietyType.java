/**    
 * @Title: ChainType.java  
 * @Package com.rograndec.feijiayun.chain.common.constant  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author lei.su lei.su@rograndec.com    
 * @date 2017年8月22日 下午8:55:26  
 * @version V1.0    
 */
package com.rograndec.feijiayun.chain.common.constant;

import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 受限品种集合（可多选：0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
 * zhaiwei
 */
public enum LimitVarietyType {

	DRUG(0,"药品"),
	FOOD(1,"医疗器械"),
	COSMETICS(2,"食品"),
	MEDICAL(3,"化妆品"),
	OTHER(4,"其它");

	private int code;
    private String name;

    private LimitVarietyType(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setType(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public static LimitVarietyType getLimitVarietyType4Code(int code){

		LimitVarietyType userTypeEum = Arrays.stream(LimitVarietyType.values())
				.filter(c -> code == c.code).findFirst().orElse(null);

		return userTypeEum;

	}

	public static String getName4CodeStr(String codes) {
		StringBuilder name = new StringBuilder();
		if (StringUtils.isNotBlank(codes)) {
			List<Long> longs = StringSplit.strSplit(codes);
			LimitVarietyType varietyType = null;
			for (Long code : longs) {
				varietyType = getLimitVarietyType4Code(Integer.parseInt(code.toString()));
				if (null != varietyType) {
					name.append(varietyType.getName()).append(",");
				}
			}
		}
		return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
	}
}
