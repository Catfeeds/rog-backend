package com.rograndec.feijiayun.chain.business.system.enterprise.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: SelectBean   
 * @Description: 页面下拉框、checkbox
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月23日 下午4:27:42
 */
@ApiModel(value = "selectBean", description = "下拉框id-text")
public class SelectBeanVO implements Serializable{
	/**
	 * serialVersionUID : <功能描述>
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "下拉框id")
	private  Long id; 
	@ApiModelProperty(value = "下拉框text")
	private  String text;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	} 

}
