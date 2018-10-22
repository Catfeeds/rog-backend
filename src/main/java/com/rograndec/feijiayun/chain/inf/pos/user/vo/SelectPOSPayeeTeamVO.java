package com.rograndec.feijiayun.chain.inf.pos.user.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SelectPOSPayeeTeamVO", description = "POS选择款员的开班时间")
public class SelectPOSPayeeTeamVO implements Serializable{
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年9月30日 下午4:33:42 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "款员ID")
	private Long payeeId;
	
	@ApiModelProperty(value = "款员编码")
	private String payeeCode;
	
	@ApiModelProperty(value = "账号")
	private String loginAccount;
	
	@ApiModelProperty(value = "款员名称")
	private String payeeName;
	
	@ApiModelProperty(value = "班组名称")
	private String teamName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "开班时间")
	private Date startTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "交班时间")
	private Date endTime;

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public String getPayeeCode() {
		return payeeCode;
	}

	public void setPayeeCode(String payeeCode) {
		this.payeeCode = payeeCode;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
