package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrInReceiveReportVo extends BaseGoodsDetailVO implements Serializable{
	@ApiModelProperty(value = "组织机构编码")
	private String enterpriseCode;

	@ApiModelProperty(value = "组织机构名称")
	private String enterpriseName;
	/**
	 * 配进订单日期
	 */
	@ApiModelProperty(value = "配进收货日期")
	private Date receiveDate;
	private String receiveDateStr;
	/**
	 * 缺配单号
	 */
	@ApiModelProperty(value = "单号")
	private String code;
	/**
	 * 配货单位编码
	 */
	@ApiModelProperty(value = "配货单位编码")
	private String distrUnitCode;

	/**
	 * 配货单位名称
	 */
	@ApiModelProperty(value = "配货单位名称")
	private String distrUnitName;

	/**
	 * 基础单据编码
	 */
	@ApiModelProperty(value = "基础单据编码-配货单号")
	private String distrOutCode;
	/**
	 * 基础单据编码
	 */
	@ApiModelProperty(value = "基础单据编码-配进订单单号")
	private String baseOrderCode;

	@ApiModelProperty(value = "收货人员名称")
	private String receiverName;

	@ApiModelProperty(value = "第二收货人员名称")
	private String secondReceiverName;
	/**
	 * 配进订单数量
	 */
	@ApiModelProperty(value = " 配进订单数量")
	private BigDecimal quantity;
	/**
	 * 到货数量
	 */
	@ApiModelProperty(value = "到货数量")
	private BigDecimal arrivalQuantity;

	/**
	 * 收货数量
	 */
	@ApiModelProperty(value = "收货数量")
	private BigDecimal receiveQuantity;

	/**
	 * 拒收数量
	 */
	@ApiModelProperty(value = "拒收数量")
	private BigDecimal refuseQuantity;


	@ApiModelProperty(value = "拒收原因ID集合，多个用逗号分隔")
	private String refuseReasonIds;
	private String refuseReason;

	/**
	 * 状态
	 */
	@ApiModelProperty(value ="状态（ 21-待审核,22-审核通过,23-审核驳回,30-待收货,31-待验收（已收货）,32-待入库（已验收）,33-已完成（已入库）,34-已取消）")
	private Integer status;
	private String statusStr;

	public String getRefuseReason() {
		if(refuseReasonIds==null){
			return "";
		}
		String ref="";
		String []ids=refuseReasonIds.split(",");
		for (String id : ids) {
			if(id.equals("1")){
				ref+="1.未采用规定的冷藏设备运输或温度不符合";
			}
			if(id.equals("2")){
				ref+="2.无随货同行单（票）或无采购记录";
			}
			if(id.equals("3")){
				ref+="3.随货同行单（票）与采购记录以及本企业实际情况不符";
			}
			if(id.equals("4")){
				ref+="4.随货同行单（票）与药品实物不符";
			}
			if(id.equals("5")){
				ref+="5.药品外包装出现破损、污染、标识不清等情况";
			}
			if(id.equals("6")){
				ref+="6.其它情况";
			}
		}
		return ref;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSecondReceiverName() {
		return secondReceiverName;
	}

	public void setSecondReceiverName(String secondReceiverName) {
		this.secondReceiverName = secondReceiverName;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveDateStr() {
		return DateUtils.DateToString(this.receiveDate,"yyyy-MM-dd");
	}

	public void setReceiveDateStr(String receiveDateStr) {
		this.receiveDateStr = receiveDateStr;
	}

	public String getDistrOutCode() {
		return distrOutCode;
	}

	public void setDistrOutCode(String distrOutCode) {
		this.distrOutCode = distrOutCode;
	}

	public BigDecimal getArrivalQuantity() {
		return arrivalQuantity;
	}

	public void setArrivalQuantity(BigDecimal arrivalQuantity) {
		this.arrivalQuantity = arrivalQuantity;
	}

	public BigDecimal getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(BigDecimal receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
	}

	public BigDecimal getRefuseQuantity() {
		return refuseQuantity;
	}

	public void setRefuseQuantity(BigDecimal refuseQuantity) {
		this.refuseQuantity = refuseQuantity;
	}

	public String getRefuseReasonIds() {
		return refuseReasonIds;
	}

	public void setRefuseReasonIds(String refuseReasonIds) {
		this.refuseReasonIds = refuseReasonIds;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		//状态:30-待收货,31-待验收（已收货）,32-待入库（已验收）,33-已完成（已入库"
		if(this.status==null){
			return "";
		}
		if(this.status==30){
			return "待收货";
		}
		if(this.status==31){
			return "待验收";
		}
		if(this.status==32){
			return "待入库";
		}
		if(this.status==33){
			return "已完成";
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}


	public String getDistrUnitCode() {
		return distrUnitCode;
	}

	public void setDistrUnitCode(String distrUnitCode) {
		this.distrUnitCode = distrUnitCode;
	}

	public String getDistrUnitName() {
		return distrUnitName;
	}

	public void setDistrUnitName(String distrUnitName) {
		this.distrUnitName = distrUnitName;
	}
}
