package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NormHealthProductsVO", description = "标准保健食品")
public class NormHealthProductsVO {
	
	@ApiModelProperty(value = "保健功能")
    private String nhp_health_function;
	@ApiModelProperty(value = "功效成分")
    private String nhp_ingredients;
	@ApiModelProperty(value = "主要原料")
    private String nhp_material;
	@ApiModelProperty(value = "适宜人群")
    private String nhp_fit_crowd;
	@ApiModelProperty(value = "不适宜人群")
    private String nhp_no_fit_crowd;
	@ApiModelProperty(value = "食用方法及用量")
    private String nhp_edible_method;
	@ApiModelProperty(value = "贮藏方法")
    private String nhp_storage_method;
	@ApiModelProperty(value = "注意事项")    
    private String nhp_notes_events;
	
	public String getNhp_health_function() {
		return nhp_health_function;
	}
	public void setNhp_health_function(String nhp_health_function) {
		this.nhp_health_function = nhp_health_function;
	}
	public String getNhp_ingredients() {
		return nhp_ingredients;
	}
	public void setNhp_ingredients(String nhp_ingredients) {
		this.nhp_ingredients = nhp_ingredients;
	}
	public String getNhp_material() {
		return nhp_material;
	}
	public void setNhp_material(String nhp_material) {
		this.nhp_material = nhp_material;
	}
	public String getNhp_fit_crowd() {
		return nhp_fit_crowd;
	}
	public void setNhp_fit_crowd(String nhp_fit_crowd) {
		this.nhp_fit_crowd = nhp_fit_crowd;
	}
	public String getNhp_no_fit_crowd() {
		return nhp_no_fit_crowd;
	}
	public void setNhp_no_fit_crowd(String nhp_no_fit_crowd) {
		this.nhp_no_fit_crowd = nhp_no_fit_crowd;
	}
	public String getNhp_edible_method() {
		return nhp_edible_method;
	}
	public void setNhp_edible_method(String nhp_edible_method) {
		this.nhp_edible_method = nhp_edible_method;
	}
	public String getNhp_storage_method() {
		return nhp_storage_method;
	}
	public void setNhp_storage_method(String nhp_storage_method) {
		this.nhp_storage_method = nhp_storage_method;
	}
	public String getNhp_notes_events() {
		return nhp_notes_events;
	}
	public void setNhp_notes_events(String nhp_notes_events) {
		this.nhp_notes_events = nhp_notes_events;
	}


    
	
    
}