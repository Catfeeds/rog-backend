package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 功能描述：会计期间
 * Created by dongdong.zhang on 2018/01/16 15:16
 */

public enum GoodTaxTypeStatus {

    TAX_IN(0,"进项税","TAXIN"),
    TAX_OUT(1,"销项税","TAXOUT");

    private Integer code;
    private String name;
    private String flag;//标识

    private GoodTaxTypeStatus(Integer code, String name, String flag) {
        this.code = code;
        this.name = name;
        this.flag = flag;
    }
    public void setCode(Integer code) {
		this.code = code;
	}
    public Integer getCode() {
        return code;
    }
    public void setType(Integer code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public static String getName(Integer code) {
    	if(code == null) return null;
        for (GoodTaxTypeStatus c : GoodTaxTypeStatus.values()) {
            if (c.getCode().equals(code)) {
                return c.getName();
            }
        }
        return null;
    }
	
	public static String getFlag(Integer code) {
    	if(code == null) return null;
        for (GoodTaxTypeStatus c : GoodTaxTypeStatus.values()) {
            if (c.getCode().equals(code)) {
                return c.getFlag();
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
    	if(name == null) return -1;
        for (GoodTaxTypeStatus c : GoodTaxTypeStatus.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}