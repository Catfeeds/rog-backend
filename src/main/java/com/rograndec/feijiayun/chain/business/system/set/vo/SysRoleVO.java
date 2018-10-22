package com.rograndec.feijiayun.chain.business.system.set.vo;

import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysRoleVO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 机构类型（0-总部；1-分店）
     */
    private Integer chainType;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 岗位ID
     */
    private Long positionId;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 0：用户自定义角色；1-系统默认角色
     */
    private Integer sysType;


    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", chainType=").append(chainType);
        sb.append(", deptId=").append(deptId);
        sb.append(", positionId=").append(positionId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", sysType=").append(sysType);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    public static List<SysRoleVO> getSysRoleVOByRole(List<SysRole> roles){

        List<SysRoleVO> roleVOS = new ArrayList<>();

        for(SysRole sysRole : roles){
            SysRoleVO sysRoleVO = new SysRoleVO();
            sysRoleVO.setId(sysRole.getId());
            sysRoleVO.setChainType(sysRole.getChainType());
            sysRoleVO.setCode(sysRole.getCode());
            sysRoleVO.setDeptId(sysRole.getDeptId());
            sysRoleVO.setEnterpriseId(sysRole.getEnterpriseId());
            sysRoleVO.setName(sysRole.getName());
            sysRoleVO.setParentId(sysRole.getParentId());
            sysRoleVO.setStatus(sysRole.getStatus());
            sysRoleVO.setPositionId(sysRole.getPositionId());
            roleVOS.add(sysRoleVO);
        }

        return roleVOS;
    }
}