package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NormOtherVO", description = "其他药品")
public class NormOtherVO {
	
	@ApiModelProperty(value = "成分")
	private String no_composition;
	@ApiModelProperty(value = "使用方法")
	private String no_instructions;
	@ApiModelProperty(value = "禁忌")
	private String no_contraindication;
	@ApiModelProperty(value = "保质期")
	private String no_shelf_life;
	@ApiModelProperty(value = "存储方式")
	private String no_storage_conditions;
	@ApiModelProperty(value = "注意事项")
	private String no_note_events;
	
	public String getNo_composition() {
		return no_composition;
	}
	public void setNo_composition(String no_composition) {
		this.no_composition = no_composition;
	}
	public String getNo_instructions() {
		return no_instructions;
	}
	public void setNo_instructions(String no_instructions) {
		this.no_instructions = no_instructions;
	}
	public String getNo_contraindication() {
		return no_contraindication;
	}
	public void setNo_contraindication(String no_contraindication) {
		this.no_contraindication = no_contraindication;
	}
	public String getNo_shelf_life() {
		return no_shelf_life;
	}
	public void setNo_shelf_life(String no_shelf_life) {
		this.no_shelf_life = no_shelf_life;
	}
	public String getNo_storage_conditions() {
		return no_storage_conditions;
	}
	public void setNo_storage_conditions(String no_storage_conditions) {
		this.no_storage_conditions = no_storage_conditions;
	}
	public String getNo_note_events() {
		return no_note_events;
	}
	public void setNo_note_events(String no_note_events) {
		this.no_note_events = no_note_events;
	}
	
    
}