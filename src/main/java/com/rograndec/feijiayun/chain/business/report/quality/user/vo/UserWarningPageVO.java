package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class UserWarningPageVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码")
    private String enterpriseCode;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称")
    private String enterpriseName;

    /**
     * 员工编码
     */
    @ApiModelProperty(value = "员工编码")
    private String code;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称")
    private String name;

    /**
     * 部门集合
     */
    @ApiModelProperty(value = "部门集合")
    private String deptIds;

    /**
     * 岗位集合
     */
    @ApiModelProperty(value = "岗位集合")
    private String positionIds;

    /**
     * 预警内容
     */
    @ApiModelProperty(value = "预警内容")
    private List<String> warningContext;

    /**
     * 预警信息
     */
    @ApiModelProperty(value = "预警信息")
    private List<String> warningMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }

    public List<String> getWarningContext() {
        return warningContext;
    }

    public void setWarningContext(List<String> warningContext) {
        this.warningContext = warningContext;
    }

    public List<String> getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(List<String> warningMessage) {
        this.warningMessage = warningMessage;
    }

    @Override
    public String toString() {
        return "UserWarningPageVO{" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", deptIds='" + deptIds + '\'' +
                ", positionIds='" + positionIds + '\'' +
                ", warningContext='" + warningContext + '\'' +
                ", warningMessage='" + warningMessage + '\'' +
                '}';
    }

}
