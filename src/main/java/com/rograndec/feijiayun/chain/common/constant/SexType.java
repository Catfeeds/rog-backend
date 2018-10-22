/**
 * @Title: ChainType.java
 * @Package com.rograndec.feijiayun.chain.common.constant
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lei.su lei.su@rograndec.com
 * @date 2017年8月22日 下午8:55:26
 * @version V1.0
 */
package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 性别（0-男；1-女；2-其它）
 * zhaiwei
 */
public enum SexType {

    MALE(0, "男"),
    FEMALE(1, "女"),
    OTHER(2, "其他");

    private int code;
    private String name;

    private SexType(int code, String name) {
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

    public static SexType getSexType4Code(int code) {

        SexType sexType = Arrays.stream(SexType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return sexType;

    }

    public static String getName(Integer code) {
        for (SexType c : SexType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
