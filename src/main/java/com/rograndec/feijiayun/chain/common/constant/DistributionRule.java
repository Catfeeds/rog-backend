package com.rograndec.feijiayun.chain.common.constant;

/**
 *
 * @ClassName: DistributionRule
 * @Description: 配货规则
 * @author dongyang.du
 * @date 配送类型
 * 
 */
public enum DistributionRule {

	DISTRIBUTION_RULE_A(0, "按要货顺序"), DISTRIBUTION_RULE_B(1, "按库存平均"), DISTRIBUTION_RULE_C(2, "按要货数量占比");

	private int code;
	private String name;

	private DistributionRule(int code, String name) {
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
		for (DistributionRule c : DistributionRule.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}
}
