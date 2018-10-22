package com.rograndec.feijiayun.chain.common.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 当前登录人信息对象
 * @author zhongyi.li
 *
 */
public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3700704636887115325L;

	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
     * 企业ID
     */
    private Long enterpriseId;

	/**
	 * 企业编码
	 */
	private String enterpriseCode;

	/**
	 * 企业名称
	 */
	private String enterpriseName;
	
    /**
     * 上级企业ID
     */
    private Long parentId;
    
    /**
     * 上级企业编码
     */
    private String parentCode;
    
    /**
     * 上级企业名称
     */
    private String parentName;
    
    /**
     * 企业类型：0-总部；1-自营店；2-加盟店
     */
    private Integer chainType;
    
    /**
     * 用户编码
     */
    private String userCode;
    
    /**
     * 用户名称
     */
    private String userName;
    
    /**
     * 登录账号
     */
    private String loginAccount;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 角色集合
     */
    private Set<SysRoleVO> roles=new HashSet<SysRoleVO>();

    /**
     * 融贯通用户id
     */
    private Integer rgtUserId;
    
    /**
     * 融贯通企业ID
     */
    private Integer rgtEnterpriseId;
    private Integer approveStatus;
    private Integer status;

    
    public static UserVO getUserVO(Enterprise enterprise, User user, UserAdministration userAdministration){

		UserVO userVO = new UserVO();

		/**
		 * 用户ID
		 */
		userVO.setUserId(user.getId());

		/**
		 * 企业ID
		 */
		userVO.setEnterpriseId(user.getEnterpriseId());

		/**
		 * 企业编码
		 */
		userVO.setEnterpriseCode(enterprise.getCode());

		/**
		 * 企业名称
		 */
		userVO.setEnterpriseName(enterprise.getName());

		/**
		 * 上级企业ID
		 */
		userVO.setParentId(user.getParentId());

		/**
		 * 企业类型：0-总部；1-自营店；2-加盟店
		 */
		userVO.setChainType(enterprise.getChainType());

		/**
		 * 用户编码
		 */
		userVO.setUserCode(user.getCode());

		/**
		 * 用户名称
		 */
		userVO.setUserName(user.getName());

		/**
		 * 登录账号
		 */
		userVO.setLoginAccount(userAdministration.getLoginAccount());

		return userVO;
	}

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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<SysRoleVO> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRoleVO> roles) {
		this.roles = roles;
	}
	
	public Integer getRgtUserId() {
		return rgtUserId;
	}

	public void setRgtUserId(Integer rgtUserId) {
		this.rgtUserId = rgtUserId;
	}

	public Integer getRgtEnterpriseId() {
		return rgtEnterpriseId;
	}

	public void setRgtEnterpriseId(Integer rgtEnterpriseId) {
		this.rgtEnterpriseId = rgtEnterpriseId;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", enterpriseId=" + enterpriseId + ", enterpriseCode=" + enterpriseCode
				+ ", enterpriseName=" + enterpriseName + ", parentId=" + parentId + ", parentCode=" + parentCode
				+ ", parentName=" + parentName + ", chainType=" + chainType + ", userCode=" + userCode + ", userName="
				+ userName + ", loginAccount=" + loginAccount + ", password=" + password + ", roles=" + roles
				+ ", rgtUserId=" + rgtUserId + ", rgtEnterpriseId=" + rgtEnterpriseId + "]";
	}

	



}
