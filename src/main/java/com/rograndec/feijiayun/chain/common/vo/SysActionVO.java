package com.rograndec.feijiayun.chain.common.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @ClassName: SysActionVO  
 * @Description: 系统功能对象
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月23日 下午4:31:51  
 *
 */
public class SysActionVO implements Serializable{

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = -3622620800201948518L;
	
	/**
     * 主键ID
     */
    private Long actionId;

    /**
     * 功能编码
     */
    private String actionCode;

    /**
     * 功能名称
     */
    private String actionName;
    
    /**
     * 角色集合
     */
    private Set<SysRoleVO> roles=new HashSet<SysRoleVO>();
    
    public SysActionVO(){}

	public SysActionVO(Long actionId, String actionCode, String actionName) {
		super();
		this.actionId = actionId;
		this.actionCode = actionCode;
		this.actionName = actionName;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Set<SysRoleVO> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRoleVO> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "SysActionVO [actionId=" + actionId + ", actionCode=" + actionCode + ", actionName=" + actionName
				+ ", roles=" + roles + ", getActionId()=" + getActionId() + ", getActionCode()=" + getActionCode()
				+ ", getActionName()=" + getActionName() + ", getRoles()=" + getRoles() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
}
