package com.rograndec.feijiayun.chain.business.retail.payment.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PaymentVO", description = "零售管理-零售缴款-缴费详情对象")
public class PaymentVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//交接班ID
	@ApiModelProperty(required = true, value = "交接班ID")
	private Long id;
	
	//默认当前日期
	@ApiModelProperty(required = false, value = "收款日期")
	private String createDate;
	
	//默认当前登录人员
	@ApiModelProperty(required = false, value = "收款人员")
	private String payeeName;
	
	//取交班数据
	@ApiModelProperty(required = false, value = "缴款人员")
	private String paymentManName;
	
	@ApiModelProperty(required = false, value = "班组名称")
	private String teamName;
	
	@ApiModelProperty(required = true, value = "款台编码")
	private String standCode;
	
	@ApiModelProperty(required = true, value = "交班日期")
	private String shiftDate;
	
	@ApiModelProperty(required = true, value = "开班时间")
	private Date openingTime;
	
	@ApiModelProperty(required = true, value = "交班时间")
	private Date shiftTime;
	
	private List<PaymentListVO> paymentList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPaymentManName() {
		return paymentManName;
	}

	public void setPaymentManName(String paymentManName) {
		this.paymentManName = paymentManName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public Date getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}

	public List<PaymentListVO> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentListVO> paymentList) {
		this.paymentList = paymentList;
	}

	public String getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(String shiftDate) {
		this.shiftDate = shiftDate;
	}
	
}
