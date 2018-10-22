package com.rograndec.feijiayun.chain.business.basic.user.constant;

import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 用户类型（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
 * Created by zhaiwei on 2017/8/28.
 */
public enum UserTypeEum {

    SYSTEM_USER(0, "系统管理员"),
    CLOUD_USER(1, "云系统用户"),
    POS_USER(2, "POS用户"),
    CLOUD_POS_USER(3, "云系统用户和POS用户");

    private int code;
    private String value;

    UserTypeEum(int code, String value) {
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

    public static UserTypeEum getUserTypeEum4Code(int code) {

        UserTypeEum userTypeEum = Arrays.stream(UserTypeEum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

    public static UserTypeEum getUserTypeEum4Value(String value) {

        UserTypeEum userTypeEum = Arrays.stream(UserTypeEum.values())
                .filter(c -> value.equals(c.value)).findFirst().orElse(null);

        return userTypeEum;

    }

    public static String getValue4CodeStr(String codes) {
        StringBuilder name = new StringBuilder();
        if (StringUtils.isNotBlank(codes)) {
            List<Long> longs = StringSplit.strSplit(codes);
            UserTypeEum userType = null;
            for (Long code : longs) {
                userType = getUserTypeEum4Code(Integer.parseInt(code.toString()));
                if (null != userType) {
                    name.append(userType.getValue()).append(",");
                }
            }
        }
        return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
    }
}
