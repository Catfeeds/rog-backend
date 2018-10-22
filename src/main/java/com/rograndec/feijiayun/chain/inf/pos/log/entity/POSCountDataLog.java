package com.rograndec.feijiayun.chain.inf.pos.log.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: POSSyncDataLog   
 * @Description: POS同步表数据总数日志
 * @author yuting.li
 * @version 1.0 
 * @date 2018年2月11日 下午5:34:49
 */
public class POSCountDataLog implements Serializable {

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2018年2月11日 下午5:35:25 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "企业id")
    private Long enterpriseId;  

	@ApiModelProperty(value = "用户名称")
    private String userName;
	
    @ApiModelProperty(value = "登录账号")
    private String loginAccount; 
	
	@ApiModelProperty(value = "同步总数参数")
    private String countParam;
	
	@ApiModelProperty(value = "同步数据结果")
    private String countData;
	
	@ApiModelProperty(value = "日志时间")
    private String logDate;
	
	public POSCountDataLog(Long enterpriseId, String userName, String loginAccount, String countParam, String countData,
			String logDate) {
		super();
		this.enterpriseId = enterpriseId;
		this.userName = userName;
		this.loginAccount = loginAccount;
		this.countParam = countParam;
		this.countData = countData;
		this.logDate = logDate;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getCountParam() {
		return countParam;
	}

	public void setCountParam(String countParam) {
		this.countParam = countParam;
	}

	public String getCountData() {
		return countData;
	}

	public void setCountData(String countData) {
		this.countData = countData;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	
	
}
