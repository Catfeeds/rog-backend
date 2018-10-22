package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrLackReportVo extends BaseGoodsDetailVO implements Serializable{
	/**
	 * 配货日期
	 */
	@ApiModelProperty(value = "配货日期")
	private Date sendDate;
	private String sendDateStr;
	/**
	 * 缺配单号
	 */
	@ApiModelProperty(value = "缺配单号")
	private String code;
	/**
	 * 要货单位编码
	 */
	@ApiModelProperty(value = "要货单位编码")
	private String requestUnitCode;

	/**
	 * 要货单位名称
	 */
	@ApiModelProperty(value = "要货单位名称")
	private String requestUnitName;
	/**
	 * 要货人员名称
	 */
	@ApiModelProperty(value = "要货人员名称")
	private String requesterName;

	@ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
	private Integer distrType;
	private String distrTypeStr;

	/**
	 * 要货计划单号
	 */
	@ApiModelProperty(value = "要货计划单号")
	private String requestPlanCode;
	/**
	 * 要货数量
	 */
	@ApiModelProperty(value = "要货数量")
	private BigDecimal requestQuantity;

	/**
	 * 配货数量
	 */
	@ApiModelProperty(value = "配货数量")
	private BigDecimal sendQuantity;

	/**
	 * 缺配数量
	 */
	@ApiModelProperty(value = "缺配数量")
	private BigDecimal lackQuantity;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态（31-待配货；33-已配货；34-已取消）")
	private Integer status;
	private String statusStr;

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendDateStr() {
		return DateUtils.DateToString(this.sendDate,"yyyy-MM-dd");
	}

	public void setSendDateStr(String sendDateStr) {
		this.sendDateStr = sendDateStr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRequestUnitCode() {
		return requestUnitCode;
	}

	public void setRequestUnitCode(String requestUnitCode) {
		this.requestUnitCode = requestUnitCode;
	}

	public String getRequestUnitName() {
		return requestUnitName;
	}

	public void setRequestUnitName(String requestUnitName) {
		this.requestUnitName = requestUnitName;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public String getDistrTypeStr() {
		if(this.distrType==null){
			return "";
		}
		//配货类型（0-总部配送；3-分店间调剂；4-直调配送）
		if(this.distrType==0){
			return "总部配送";
		}
		if(this.distrType==3){
			return "分店间调剂";
		}
		if(this.distrType==4){
			return "直调配送";
		}
		return distrTypeStr;
	}

	public void setDistrTypeStr(String distrTypeStr) {
		this.distrTypeStr = distrTypeStr;
	}

	public String getRequestPlanCode() {
		return requestPlanCode;
	}

	public void setRequestPlanCode(String requestPlanCode) {
		this.requestPlanCode = requestPlanCode;
	}

	public BigDecimal getRequestQuantity() {
		return requestQuantity;
	}

	public void setRequestQuantity(BigDecimal requestQuantity) {
		this.requestQuantity = requestQuantity;
	}

	public BigDecimal getSendQuantity() {
		return sendQuantity;
	}

	public void setSendQuantity(BigDecimal sendQuantity) {
		this.sendQuantity = sendQuantity;
	}

	public BigDecimal getLackQuantity() {
		return lackQuantity;
	}

	public void setLackQuantity(BigDecimal lackQuantity) {
		this.lackQuantity = lackQuantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		//状态（31-待配货；33-已配货；34-已取消）
		if(this.status==null){
			return "";
		}
		if(this.status==31){
			return "待配货";
		}
		if(this.status==33){
			return "已配货";
		}
		if(this.status==34){
			return "已取消";
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
}
