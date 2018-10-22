package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosSalePeriodVO
 * @Description:  零售管理-POS管理-销售时段
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:52:54
 */
@ApiModel(value = "PosSalePeriodVO", description = "零售管理-POS管理-销售时段")
public class PosSalePeriodVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	
	/**
     * 编码
     */
	@ApiModelProperty(required = true, value = "编码")
	private String code;
	
	/**
     * 名称
     */
	@ApiModelProperty(required = true, value = "名称")
	private String name;
	
	/**
     * 开始时间
     */
	@ApiModelProperty(required = true, value = "开始时间")
	private String startTime;
	
	/**
     * 结束时间
     */
	@ApiModelProperty(required = true, value = "结束时间")
	private String endTime;
	
	

	/**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	
	/**
	 * 编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 编码
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 开始时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 开始时间
	 */
	public String getStartTime() {
		return startTime;
	}
	
	/**
	 * 结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 结束时间
	 */
	public String getEndTime() {
		return endTime;
	}
	
	

}