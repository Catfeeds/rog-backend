package com.rograndec.feijiayun.chain.common.constant;

/**
 * 
 * @ClassName: CheckType  
 * @Description: 验收类型常量 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月25日 下午5:17:14  
 *
 */
public enum CheckType {
	
	ALL(0,"全部"),
	DOMESTIC_DRUG(1,"国产成药"),
	IMPORTED_DRUGS(2,"进口成药"),
	CH_MEDICINE_PIECES(3,"中药饮片"),
	CH_MEDICINE(4,"中药材"),
	MEDICAL_APPARATUS(5,"医疗器械"),
	FOOD(6,"食品"),
	COSMETICS(7,"化妆品"),
	SPECIAL(8, "进口麻醉药品、精神药品以及蛋白同化制剂、肽类激素"),
	BIOLOGY(9, "生物制品"),
	IMPORTED_BIOLOGY(10, "进口生物制品"),
	OTHER(11,"其它");
	
	private Integer type;
	private String typeName;
	
	private CheckType(Integer type, String typeName){
		this.type = type;
		this.typeName = typeName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
