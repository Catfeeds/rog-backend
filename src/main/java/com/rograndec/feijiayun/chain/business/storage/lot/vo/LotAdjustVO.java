package com.rograndec.feijiayun.chain.business.storage.lot.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_lot_adjust
 * 
 * 
 * @author kexinhao
 * 
 * 2017-09-28
 */
public class LotAdjustVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据类型（5321）
     */
    @ApiModelProperty(value = "单据类型（5321）")
    private Integer orderType;

    /**
     * 调整日期
     */
    @ApiModelProperty(value = "调整日期")
    private Date adjustDate;

    /**
     * 调整单号
     */
    @ApiModelProperty(value = "调整单号")
    private String code;

    /**
     * 调整人员ID
     */
    @ApiModelProperty(value = "调整人员ID")
    private Long adjustManId;

    /**
     * 调整人员编码
     */
    @ApiModelProperty(value = "调整人员编码")
    private String adjustManCode;

    /**
     * 调整人员名称
     */
    @ApiModelProperty(value = "调整人员名称")
    private String adjustManName;

    /**
     * 调整原因
     */
    @ApiModelProperty(value = "调整原因")
    private String adjustReason;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 批号调整单明细
     * */
    @ApiModelProperty(value = "批号调整单明细列表")
    private List<LotAdjustDetailVO> lotAdjustDetailVOList;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getAdjustManId() {
		return adjustManId;
	}

	public void setAdjustManId(Long adjustManId) {
		this.adjustManId = adjustManId;
	}

	public String getAdjustManCode() {
		return adjustManCode;
	}

	public void setAdjustManCode(String adjustManCode) {
		this.adjustManCode = adjustManCode;
	}

	public String getAdjustManName() {
		return adjustManName;
	}

	public void setAdjustManName(String adjustManName) {
		this.adjustManName = adjustManName;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<LotAdjustDetailVO> getLotAdjustDetailVOList() {
		return lotAdjustDetailVOList;
	}

	public void setLotAdjustDetailVOList(List<LotAdjustDetailVO> lotAdjustDetailVOList) {
		this.lotAdjustDetailVOList = lotAdjustDetailVOList;
	}


}