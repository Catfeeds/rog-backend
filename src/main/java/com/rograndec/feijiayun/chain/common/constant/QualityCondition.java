package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

public enum QualityCondition {
	NONCONFORMING_PRODUCT(0,"合格品"),
	UNNONCONFORMING_PRODUCT(2,"不合格品");

    private int code;
    private String value;

    QualityCondition(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getQualityConditionEnum(int code){

    	QualityCondition userTypeEum = Arrays.stream(QualityCondition.values())
                .filter(c -> code == c.getCode()).findFirst().orElse(null);

        return userTypeEum.getValue();

    }
    
}
