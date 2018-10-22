package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrReqPlanReportVo extends BaseGoodsDetailVO implements Serializable{

	@ApiModelProperty(value = "组织机构编码")
	private String enterpriseCode;

	@ApiModelProperty(value = "组织机构名称")
	private String enterpriseName;

	@ApiModelProperty(value = "计划日期")
	private Date planDate;

	private String planDateStr;

	@ApiModelProperty(value = "要货计划单号")
	private String code;

	@ApiModelProperty(value = "配货单位编码")
	private String distrUnitCode;

	@ApiModelProperty(value = "配货单位名称")
	private String distrUnitName;

	@ApiModelProperty(value = "计划人员名称")
	private String plannerName;

	@ApiModelProperty(value = "要货类型（0-总部配送；3-分店间调剂）")
	private Integer requestType;

	private String requestTypeStr;

	@ApiModelProperty(value = "要货数量")
	private BigDecimal quantity;

	@ApiModelProperty(value = "状态（21-待审核；22-审核通过；23-审核驳回；31-待配货；33-已配货；34-已取消）")
	private Integer status;

	private String statusStr;

	public String getPlanDateStr() {
		return DateUtils.DateToString(this.planDate,"yyyy-MM-dd");
	}

	public String getRequestTypeStr() {
		if(this.requestType==null){
			return "";
		}
		if(this.requestType==0){
			return "总部配送";
		}
		if(this.requestType==1){
			return "分店间调剂";
		}
		return requestTypeStr;
	}

	public String getStatusStr() {
		if(this.status==null){
			return "";
		}
		//21-待审核；22-审核通过；23-审核驳回；31-待配货；33-已配货；34-已取消
		if(this.status==21){
			return "待审核";
		}
		if(this.status==22){
			return "审核通过";
		}
		if(this.status==23){
			return "审核驳回";
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

	public void setPlanDateStr(String planDateStr) {
		this.planDateStr = planDateStr;
	}

	public void setRequestTypeStr(String requestTypeStr) {
		this.requestTypeStr = requestTypeStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getPlannerName() {
		return plannerName;
	}

	public void setPlannerName(String plannerName) {
		this.plannerName = plannerName;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
}
