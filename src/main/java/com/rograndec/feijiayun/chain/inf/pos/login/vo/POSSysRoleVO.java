package com.rograndec.feijiayun.chain.inf.pos.login.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class POSSysRoleVO implements Serializable{

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = 6274386773625477567L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(value = "角色id")
    private Long roleId;

    /**
     * 角色编码
     */
	@ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 角色名称
     */
	@ApiModelProperty(value = "角色名称")
    private String roleName;
    
	
	public POSSysRoleVO(){}
	
	public POSSysRoleVO(Long roleId, String roleCode, String roleName) {
		super();
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "POSSysRoleVO [roleId=" + roleId + ", roleCode=" + roleCode + ", roleName=" + roleName + "]";
	}


	
	
    
}
