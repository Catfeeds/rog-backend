package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NormInstrumentVO", description = "标准医疗器械")
public class NormInstrumentVO {

	@ApiModelProperty(value = "产品标准")
	private String ni_product_standard;
	@ApiModelProperty(value = "产品性能结构及组成")
	private String ni_structures_compositions;
	@ApiModelProperty(value = "产品使用范围")
    private String ni_product_scope;
	@ApiModelProperty(value = "注册代理")
    private String ni_registered_agent;
	@ApiModelProperty(value = "售后服务机构")
    private String ni_after_sale_service;
	@ApiModelProperty(value = "注意事项")
	private String ni_note_events;
	@ApiModelProperty(value = "使用说明")
    private String ni_instructions;
	@ApiModelProperty(value = "功能描述")
    private String ni_desc;
	@ApiModelProperty(value = "贮藏条件")
    private String ni_store_condition;
	@ApiModelProperty(value = "禁忌")
    private String ni_contraindication;
	
	public String getNi_product_standard() {
		return ni_product_standard;
	}
	public void setNi_product_standard(String ni_product_standard) {
		this.ni_product_standard = ni_product_standard;
	}
	public String getNi_structures_compositions() {
		return ni_structures_compositions;
	}
	public void setNi_structures_compositions(String ni_structures_compositions) {
		this.ni_structures_compositions = ni_structures_compositions;
	}
	public String getNi_product_scope() {
		return ni_product_scope;
	}
	public void setNi_product_scope(String ni_product_scope) {
		this.ni_product_scope = ni_product_scope;
	}
	public String getNi_registered_agent() {
		return ni_registered_agent;
	}
	public void setNi_registered_agent(String ni_registered_agent) {
		this.ni_registered_agent = ni_registered_agent;
	}
	public String getNi_after_sale_service() {
		return ni_after_sale_service;
	}
	public void setNi_after_sale_service(String ni_after_sale_service) {
		this.ni_after_sale_service = ni_after_sale_service;
	}
	public String getNi_note_events() {
		return ni_note_events;
	}
	public void setNi_note_events(String ni_note_events) {
		this.ni_note_events = ni_note_events;
	}
	public String getNi_instructions() {
		return ni_instructions;
	}
	public void setNi_instructions(String ni_instructions) {
		this.ni_instructions = ni_instructions;
	}
	public String getNi_desc() {
		return ni_desc;
	}
	public void setNi_desc(String ni_desc) {
		this.ni_desc = ni_desc;
	}
	public String getNi_store_condition() {
		return ni_store_condition;
	}
	public void setNi_store_condition(String ni_store_condition) {
		this.ni_store_condition = ni_store_condition;
	}
	public String getNi_contraindication() {
		return ni_contraindication;
	}
	public void setNi_contraindication(String ni_contraindication) {
		this.ni_contraindication = ni_contraindication;
	}

    
}