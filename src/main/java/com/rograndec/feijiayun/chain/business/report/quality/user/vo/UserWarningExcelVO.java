package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserWarningExcelVO implements Serializable{

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
    private String warningContext;

    /**
     * 预警信息
     */
    @ApiModelProperty(value = "预警信息")
    private String warningMessage;

    public static List<UserWarningExcelVO> convert4UserWarningPageVOs(List<UserWarningPageVO> userWarningPageVOS){

        List<UserWarningExcelVO> userWarningExcelVOS = new ArrayList<>();

        for(UserWarningPageVO up : userWarningPageVOS){
            List<UserWarningExcelVO> userWarningExcels = convert4UserWarningPageVO(up);
            userWarningExcelVOS.addAll(userWarningExcels);
        }

        return userWarningExcelVOS;
    }

    public static List<UserWarningExcelVO> convert4UserWarningPageVO(UserWarningPageVO userWarningPageVOS){

        List<String> warningContexts = userWarningPageVOS.getWarningContext();

        List<String> warningMessages = userWarningPageVOS.getWarningMessage();

        List<UserWarningExcelVO> userWarningExcelVOS = new ArrayList<>();

        for(int i = 0 ; i < warningContexts.size() ; i++ ){

            UserWarningExcelVO we = new UserWarningExcelVO();
            we.setId(userWarningPageVOS.getId());
            we.setEnterpriseCode(userWarningPageVOS.getEnterpriseCode());
            we.setEnterpriseName(userWarningPageVOS.getEnterpriseName());
            we.setCode(userWarningPageVOS.getCode());
            we.setName(userWarningPageVOS.getName());
            we.setDeptIds(userWarningPageVOS.getDeptIds());
            we.setPositionIds(userWarningPageVOS.getPositionIds());
            we.setWarningContext(warningContexts.get(i));
            we.setWarningMessage(warningMessages.get(i));
            userWarningExcelVOS.add(we);
        }

        return userWarningExcelVOS;

    }

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

    public String getWarningContext() {
        return warningContext;
    }

    public void setWarningContext(String warningContext) {
        this.warningContext = warningContext;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    @Override
    public String toString() {
        return "UserWarningExcelVO{" +
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
