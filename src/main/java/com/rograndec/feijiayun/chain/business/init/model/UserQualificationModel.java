package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: UserQualificationModel  
 * @Description: 员工资质初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午6:55:51  
 *
 */
public class UserQualificationModel {

	private Integer suitableUnit;// 适用机构：0-全部；1-总部；2-分店；3-供货单位
	private String positionNames;// 使用岗位名称集合
	private Integer typeMust;
	private String code;
	private String name;
	private Integer controlType;
	private Integer codeMust;
	private Integer registerCodeMust;
	private Integer rangeMust;
	private Integer categoryMust;
	private Integer regionMust;
	private Integer gradeMust;
	private Integer fileMust;
	
	public UserQualificationModel(){}

	public UserQualificationModel(Integer suitableUnit, String positionNames, Integer typeMust, String code,
			String name, Integer controlType, Integer codeMust, Integer registerCodeMust, Integer rangeMust,
			Integer categoryMust, Integer regionMust, Integer gradeMust, Integer fileMust) {
		super();
		this.suitableUnit = suitableUnit;
		this.positionNames = positionNames;
		this.typeMust = typeMust;
		this.code = code;
		this.name = name;
		this.controlType = controlType;
		this.codeMust = codeMust;
		this.registerCodeMust = registerCodeMust;
		this.rangeMust = rangeMust;
		this.categoryMust = categoryMust;
		this.regionMust = regionMust;
		this.gradeMust = gradeMust;
		this.fileMust = fileMust;
	}

	public static List<UserQualificationModel> build(){
		List<UserQualificationModel> uqList = new ArrayList<UserQualificationModel>();
		uqList.add(new UserQualificationModel(0, "质量负责人,处方管理,药学管理,店长", 0, "01", "执业药师", 0, 0, 1, 1, 1, 1, 0, 0));
		uqList.add(new UserQualificationModel(1, "处方管理", 0, "02", "中药调剂员", 0, 0, 0, 0, 0, 0, 0, 0));
		return uqList;
	}

	public Integer getSuitableUnit() {
		return suitableUnit;
	}

	public void setSuitableUnit(Integer suitableUnit) {
		this.suitableUnit = suitableUnit;
	}

	public String getPositionNames() {
		return positionNames;
	}

	public void setPositionNames(String positionNames) {
		this.positionNames = positionNames;
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

	public Integer getRegisterCodeMust() {
		return registerCodeMust;
	}

	public void setRegisterCodeMust(Integer registerCodeMust) {
		this.registerCodeMust = registerCodeMust;
	}

	public Integer getRangeMust() {
		return rangeMust;
	}

	public void setRangeMust(Integer rangeMust) {
		this.rangeMust = rangeMust;
	}

	public Integer getCategoryMust() {
		return categoryMust;
	}

	public void setCategoryMust(Integer categoryMust) {
		this.categoryMust = categoryMust;
	}

	public Integer getRegionMust() {
		return regionMust;
	}

	public void setRegionMust(Integer regionMust) {
		this.regionMust = regionMust;
	}

	public Integer getGradeMust() {
		return gradeMust;
	}

	public void setGradeMust(Integer gradeMust) {
		this.gradeMust = gradeMust;
	}

	public Integer getFileMust() {
		return fileMust;
	}

	public void setFileMust(Integer fileMust) {
		this.fileMust = fileMust;
	}

}
