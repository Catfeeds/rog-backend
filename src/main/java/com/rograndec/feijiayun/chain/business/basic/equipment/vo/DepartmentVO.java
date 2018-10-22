package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class DepartmentVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 上级部门ID
     */
    @ApiModelProperty(value="上级部门ID")
    private Long parentDeptId;

    /**
     * 编码
     */
    @ApiModelProperty(value="编码")
    private String code;

    /**
     * 部门名称
     */
    @ApiModelProperty(value="部门名称")
    private String name;


    @ApiModelProperty(value = "子部门")
    private List<DepartmentVO> childDepartment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Long parentDeptId) {
        this.parentDeptId = parentDeptId;
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

    public List<DepartmentVO> getChildDepartment() {
        return childDepartment;
    }

    public void setChildDepartment(List<DepartmentVO> childDepartment) {
        this.childDepartment = childDepartment;
    }
}
