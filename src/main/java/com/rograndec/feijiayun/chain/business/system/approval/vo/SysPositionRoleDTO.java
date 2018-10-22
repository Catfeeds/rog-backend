package com.rograndec.feijiayun.chain.business.system.approval.vo;

/**
 * 
 * @ClassName: SysPositionRoleDTO  
 * @Description: TODO(岗位角色分组)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月25日 下午2:05:25  
 *
 */
public class SysPositionRoleDTO {
	private long positionId;
	private String positionName;
	private long roleId;
	private String roleName;
	
	public long getPositionId() {
		return positionId;
	}
	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
