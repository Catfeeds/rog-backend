package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosSalePeriodSaveOrupdateVO
 * @Description:  零售管理-POS管理-销售时段-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:52:54
 */
@ApiModel(value = "PosSalePeriodSaveOrupdateVO", description = "零售管理-POS管理-销售时段")
public class PosSalePeriodSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID,修改时需要传入,新增时不需要")
	private Long id;
	
	/**
     * 编码
     */
	@NotEmpty(message="编码不能为空!")
	@ApiModelProperty(required = true, value = "编码")
	private String code;
	
	/**
     * 名称
     */
	@NotEmpty(message="名称不能为空!")
	@ApiModelProperty(required = true, value = "名称")
	private String name;
	
	/**
     * 开始时间
     */
	@NotEmpty(message="开始时间不能为空!")
	@ApiModelProperty(required = true, value = "开始时间,日期格式 HH:mm:ss")
	private String startTime;
	
	/**
     * 结束时间
     */
	@NotEmpty(message="结束时间不能为空!")
	@ApiModelProperty(required = true, value = "结束时间,日期格式 HH:mm:ss")
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	

}