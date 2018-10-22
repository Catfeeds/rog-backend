package com.rograndec.feijiayun.chain.business.system.approval.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: SysUserVO  
 * @Description: TODO(vo 用于前端人员选择框数据传递)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月22日 下午4:59:08  
 *
 */
public class SysUserVO {
	/**
	 * 显示名称
	 */
	@ApiModelProperty(value = "用户名称", required = true)
	private String label;
	
	/**
	 * 标识值
	 */
	@ApiModelProperty(value = "用户ID", required = true)
	private Object value;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
