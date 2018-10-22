package com.rograndec.feijiayun.chain.business.init.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: EnterpriseQualificationModel  
 * @Description: 企业资质初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午5:49:26  
 *
 */
public class EnterpriseQualificationModel {

	private Integer suitableUnit;// 适用机构：0-全部；1-总部；2-分店；3-供货单位
	private Integer typeMust;
	private String code;
	private String name;
	private Integer controlType;
	private Integer codeMust;
	private Integer validUntilMust;
	private Integer fileMust;
	private String businessVariety;
	
	public EnterpriseQualificationModel(){}

	public EnterpriseQualificationModel(Integer suitableUnit, Integer typeMust, String code, String name,
			Integer controlType, Integer codeMust, Integer validUntilMust, Integer fileMust, String businessVariety) {
		super();
		this.suitableUnit = suitableUnit;
		this.typeMust = typeMust;
		this.code = code;
		this.name = name;
		this.controlType = controlType;
		this.codeMust = codeMust;
		this.validUntilMust = validUntilMust;
		this.fileMust = fileMust;
		this.businessVariety = businessVariety;
	}

	public static List<EnterpriseQualificationModel> build(){
		List<EnterpriseQualificationModel> eqList = new ArrayList<EnterpriseQualificationModel>();
		eqList.add(new EnterpriseQualificationModel(0, 1, "01", "营业执照统一社会信用代码", 0, 1, 1, 0,"0,1,2,3,4"));
		eqList.add(new EnterpriseQualificationModel(0, 1, "02", "年检证明文件", 1, 0, 0, 0,"0,1,2,3,4"));
		eqList.add(new EnterpriseQualificationModel(0, 1, "03", "随货通行单(票)样式", 1, 0, 0, 0,"0,1,2,3,4"));
		eqList.add(new EnterpriseQualificationModel(0, 1, "04", "相关印章", 0, 1, 0, 0,"0,1,2,3,4"));
		eqList.add(new EnterpriseQualificationModel(0, 1, "05", "质量协议书", 0, 1, 1, 0,"0,1,2,3,4"));
		eqList.add(new EnterpriseQualificationModel(0, 1, "06", "药品经营许可证", 0, 1, 1, 0,"0"));
		eqList.add(new EnterpriseQualificationModel(0, 1, "07", "药品经营质量管理规范认证证书", 0, 1, 1, 0,"0"));
		eqList.add(new EnterpriseQualificationModel(0, 0, "08", "医疗器械经营企业许可证", 0, 1, 1, 0,"1"));
		eqList.add(new EnterpriseQualificationModel(0, 0, "09", "食品流通许可证", 0, 1, 1, 0,"2"));
		eqList.add(new EnterpriseQualificationModel(0, 0, "10", "食品卫生许可证", 0, 1, 1, 0,"2"));
		
		return eqList;
	}

	public Integer getSuitableUnit() {
		return suitableUnit;
	}

	public void setSuitableUnit(Integer suitableUnit) {
		this.suitableUnit = suitableUnit;
	}

	public Integer getTypeMust() {
		return typeMust;
	}

	public void setTypeMust(Integer typeMust) {
		this.typeMust = typeMust;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getControlType() {
		return controlType;
	}

	public void setControlType(Integer controlType) {
		this.controlType = controlType;
	}

	public Integer getCodeMust() {
		return codeMust;
	}

	public void setCodeMust(Integer codeMust) {
		this.codeMust = codeMust;
	}

	public Integer getValidUntilMust() {
		return validUntilMust;
	}

	public void setValidUntilMust(Integer validUntilMust) {
		this.validUntilMust = validUntilMust;
	}

	public Integer getFileMust() {
		return fileMust;
	}

	public void setFileMust(Integer fileMust) {
		this.fileMust = fileMust;
	}

	public String getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(String businessVariety) {
		this.businessVariety = businessVariety;
	}
}
