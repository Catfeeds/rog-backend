package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: NormDrugVO   
 * @Description: 标准药品
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月17日 下午8:31:15
 */
@ApiModel(value = "NormDrugVO", description = "标准药品")
public class NormDrugVO {

	@ApiModelProperty(value = "适应症")
	private String nd_indications;
	@ApiModelProperty(value = "不良反应")
	private String nd_adverse_events;
	@ApiModelProperty(value = "存储注意事项")
	private String nd_store_note;
	@ApiModelProperty(value = "用法与用量")
	private String nd_usage_dosage;
	@ApiModelProperty(value = "禁忌症")
	private String nd_contraindication;
	@ApiModelProperty(value = "注意事项")
	private String nd_note_events;
	@ApiModelProperty(value = "保质期")
	private String nd_shelf_life;
	@ApiModelProperty(value = "存储条件")
	private String nd_storage_conditions;
	@ApiModelProperty(value = "药品成分")
	private String nd_drug_details;
	@ApiModelProperty(value = "特殊成分")
	private String nd_special_composition;
	
	public String getNd_indications() {
		return nd_indications;
	}
	public void setNd_indications(String nd_indications) {
		this.nd_indications = nd_indications;
	}
	public String getNd_adverse_events() {
		return nd_adverse_events;
	}
	public void setNd_adverse_events(String nd_adverse_events) {
		this.nd_adverse_events = nd_adverse_events;
	}
	public String getNd_store_note() {
		return nd_store_note;
	}
	public void setNd_store_note(String nd_store_note) {
		this.nd_store_note = nd_store_note;
	}
	public String getNd_usage_dosage() {
		return nd_usage_dosage;
	}
	public void setNd_usage_dosage(String nd_usage_dosage) {
		this.nd_usage_dosage = nd_usage_dosage;
	}
	public String getNd_contraindication() {
		return nd_contraindication;
	}
	public void setNd_contraindication(String nd_contraindication) {
		this.nd_contraindication = nd_contraindication;
	}
	public String getNd_note_events() {
		return nd_note_events;
	}
	public void setNd_note_events(String nd_note_events) {
		this.nd_note_events = nd_note_events;
	}
	public String getNd_shelf_life() {
		return nd_shelf_life;
	}
	public void setNd_shelf_life(String nd_shelf_life) {
		this.nd_shelf_life = nd_shelf_life;
	}
	public String getNd_storage_conditions() {
		return nd_storage_conditions;
	}
	public void setNd_storage_conditions(String nd_storage_conditions) {
		this.nd_storage_conditions = nd_storage_conditions;
	}
	public String getNd_drug_details() {
		return nd_drug_details;
	}
	public void setNd_drug_details(String nd_drug_details) {
		this.nd_drug_details = nd_drug_details;
	}
	public String getNd_special_composition() {
		return nd_special_composition;
	}
	public void setNd_special_composition(String nd_special_composition) {
		this.nd_special_composition = nd_special_composition;
	}
	
	
    
}
