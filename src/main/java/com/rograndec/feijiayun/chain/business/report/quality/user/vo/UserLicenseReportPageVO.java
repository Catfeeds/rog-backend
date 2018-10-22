package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.vo.UserQualificationConfigReturnVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class UserLicenseReportPageVO implements Serializable{

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
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAccount;

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
     * 当前会员信息资质集合
     */
    @ApiModelProperty(value = "当前会员信息资质集合")
    private List<UserQualificationConfigReturnVO> list;

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

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
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

    public List<UserQualificationConfigReturnVO> getList() {
        return list;
    }

    public void setList(List<UserQualificationConfigReturnVO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserLicenseReportPageVO{" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", deptIds='" + deptIds + '\'' +
                ", positionIds='" + positionIds + '\'' +
                ", list=" + list +
                '}';
    }


}
