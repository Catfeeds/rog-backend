package com.rograndec.feijiayun.chain.common.constant;

/**
 *
 * @ClassName: DistributionType
 * @Description: 采购管理-采购计划中
 * @author dongyang.du
 * @date 配送类型
 * 
 */
public enum DistributionType {

	DISTRIBUTION_HEAD(0, "总部配送"), ENTRUST_TRANSPORT(1, "委托运输"), SELF(2, "自提"), SWAP_BETWEEN_STORES(3,"门店间调剂"),
	DIRECT_DISTRIBUTION(4,"直调配送");

	private int code;
	private String name;

	private DistributionType(int code, String name) {
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

	public static String getName(Integer code) {
		if (code == null) {
			return null;
		}
		for (DistributionType c : DistributionType.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
