package com.rograndec.feijiayun.chain.common.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SysRoleVO implements Serializable{

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = 6274386773625477567L;
	
	/**
     * 主键ID
     */
    private Long roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 用户集合
     */
    private Set<UserVO> users=new HashSet<UserVO>();
    
	public static List<Long> getRoleIds(Set<SysRoleVO> sysRoleVOS){

		List<Long> collect = sysRoleVOS.stream().map(sysRoleVO -> sysRoleVO.getRoleId()).collect(Collectors.toList());

		return collect;
	}
	
	public SysRoleVO(){}
	
	public SysRoleVO(Long roleId, String roleCode, String roleName) {
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

	public Set<UserVO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserVO> users) {
		this.users = users;
	}


    
}
