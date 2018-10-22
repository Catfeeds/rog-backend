package com.rograndec.feijiayun.chain.common.constant;

/**
 * 
 * @ClassName: QualitySetType  
 * @Description: 质量设置类型常量
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 下午4:11:51  
 *
 */
public enum QualitySetType {

	REJECTR_EASON(0, "拒收原因"),
	UNQUALIFIED_REASON(1, "不合格原因"),
	RETURN_REASON(2, "退货原因"),
	HANDLE_MEASURES(3, "处置措施"),
	CHECK_TYPE(4, "验收类型"),
	CHECK_POJECT(5, "验收项目"),
	CHECK_CONCLUSION(6, "验收结论"),
	MAINTANCE_MEASURES(7, "养护措施");
	
	private Integer setType;
	private String name;
	
	private QualitySetType(Integer setType, String name){
		this.setType = setType;
		this.name = name;
	}

	public Integer getSetType() {
		return setType;
	}

	public void setSetType(Integer setType) {
		this.setType = setType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
