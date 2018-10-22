package com.rograndec.feijiayun.chain.common.constant;

/**
 * 特殊管理药品
 * @ClassName: EconomyType
 * @Description:
 * @author sunteng
 * @version 1.0
 * @date 2017年9月8日
 */
public enum GoodsAttributeDrugs {

	DRUGS_OTC_A(0, "成药-非处方药-甲类"),
	DRUGS_OTC_B(1, "成药-非处方药-乙类"),
	DRUGS_RX_DRUG(2, "成药-处方药"),
	CHINESE_MEDICINAL_MATERIALS(3, "中药材"),
	CHINESE_MEDICINE_DECOCTION_PIECES(4, "中药饮片");
	private int code;
	private String name;

	GoodsAttributeDrugs(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public static String getName(int code) {
		for (GoodsAttributeDrugs c : GoodsAttributeDrugs.values()) {
			if (c.getCode() == code) {
				return c.getName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (GoodsAttributeDrugs c : GoodsAttributeDrugs.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
