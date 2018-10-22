package com.rograndec.feijiayun.chain.inf.pos.login.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: POSLoginUserVO
 * @Description: POS用户
 * @author yuting.li
 * @version 1.0
 * @date 2017年10月25日 上午10:52:01
 */
@ApiModel(value = "POSLoginUserVO", description = "POS登录用户对象")
public class POSLoginUserVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月25日 上午11:05:11 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	@ApiModelProperty(value = "企业ID")
	private Long enterpriseId;
	@ApiModelProperty(value = "企业编码")
	private String enterpriseCode;
	@ApiModelProperty(value = "企业名称")
	private String enterpriseName;
	@ApiModelProperty(value = "上级企业ID")
	private Long parentId;
	@ApiModelProperty(value = "上级企业编码")
	private Long parentCode;
	@ApiModelProperty(value = "上级企业名称")
	private String parentName;
	@ApiModelProperty(value = "企业类型：0-总部；1-自营店；2-加盟店")
	private Integer chainType;
	@ApiModelProperty(value = "用户编码")
	private String userCode;
	@ApiModelProperty(value = "用户名称")
	private String userName;
	@ApiModelProperty(value = "登录账号")
	private String loginAccount;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentCode() {
		return parentCode;
	}

	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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


	@Override
	public String toString() {
		return "POSLoginUserVO [userId=" + userId + ", enterpriseId=" + enterpriseId + ", enterpriseCode="
				+ enterpriseCode + ", enterpriseName=" + enterpriseName + ", parentId=" + parentId + ", parentCode="
				+ parentCode + ", parentName=" + parentName + ", chainType=" + chainType + ", userCode=" + userCode
				+ ", userName=" + userName + ", loginAccount=" + loginAccount + "]";
	}



}
