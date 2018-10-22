package com.rograndec.feijiayun.chain.business.medicine.consult.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_medicine_consult_check
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-16
 */
public class MedicineConsultCheckVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据（用药咨询）ID
     */
    @ApiModelProperty(value = "单据（用药咨询）ID")
    private Long consultId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 警报名称
     */
    @ApiModelProperty(value = "警报名称")
    private String alarmName;

    /**
     * 简单描述
     */
    @ApiModelProperty(value = "简单描述")
    private String simpleDesc;

    /**
     * 复杂描述
     */
    @ApiModelProperty(value = "复杂描述")
    private String complexDesc;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_medicine_consult_check
     */
    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConsultId() {
		return consultId;
	}

	public void setConsultId(Long consultId) {
		this.consultId = consultId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsGenericName() {
		return goodsGenericName;
	}

	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public String getSimpleDesc() {
		return simpleDesc;
	}

	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}

	public String getComplexDesc() {
		return complexDesc;
	}

	public void setComplexDesc(String complexDesc) {
		this.complexDesc = complexDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}