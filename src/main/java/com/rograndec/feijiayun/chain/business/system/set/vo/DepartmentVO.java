package com.rograndec.feijiayun.chain.business.system.set.vo;

import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel
public class DepartmentVO implements Serializable {

    /**
     * 企业ID
     */
	@ApiModelProperty(value="企业ID",required=true)
    private Long enterpriseId;

    /**
     * 编码
     */
	@ApiModelProperty(value="编码",required=true)
    private String code;

    /**
     * 部门名称
     */
	@ApiModelProperty(value="部门名称",required=true)
    private String name;

    /**
     * 0：用户自定义部门；1-系统默认部门
     */
	@ApiModelProperty(value="0：用户自定义部门；1-系统默认部门",required=true)
    private Integer sysType;

    /**
     * 状态(0-禁用 1-启用)
     */
	@ApiModelProperty(value="状态(0-禁用 1-启用)",required=true)
    private Integer status;

    @ApiModelProperty(value="部门id",required=true)
	private Long id;
	
	/**
     * saas_department
     */
    private static final long serialVersionUID = 1L;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", sysType=").append(sysType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


    public static List<DepartmentVO> getDepartmentVOs4Departments( List<Department> departments){
        List<DepartmentVO> departmentVOS = new ArrayList<>();
        for(Department department : departments){
            DepartmentVO departmentVO = new DepartmentVO();
            departmentVO.setCode(department.getCode());
            departmentVO.setEnterpriseId(department.getEnterpriseId());
            departmentVO.setName(department.getName());
            departmentVO.setStatus(department.getStatus());
            departmentVO.setSysType(department.getSysType());
            departmentVO.setId(department.getId());
            departmentVOS.add(departmentVO);
        }
        return departmentVOS;

    }
}