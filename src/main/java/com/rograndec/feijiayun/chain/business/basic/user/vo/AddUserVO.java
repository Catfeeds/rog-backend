package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaiwei on 2017/8/23.
 */
@ApiModel
public class AddUserVO implements Serializable {

    /**
     * userId
     */
    @ApiModelProperty(value = "用户id ,修改时需要传入,新增时不需要", required = false)
    private Long id;
    /**
     * 企业ID
     */
    @ApiModelProperty(value = "组织机构id", required = true)
    @NotNull(message = "组织机构不能为空")
    private Long enterpriseId;

    /**
     * 编码 (根据init接口的初始化业务规则判断是否需要传入)
     */
    @ApiModelProperty(value = "编码", required = false)
    private String code;

    /**
     * 检索码
     */
    @ApiModelProperty(value = "检索码", required = true)
    private String pinyin;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称", required = true)
    @NotNull(message = "员工名称不能为空")
    @Size(min = 1,message = "员工名称不能为空")
    private String name;

    /**
     * 修改原因
     */
    @ApiModelProperty(value = " 修改原因", required = true)
    private String remark;

    /**
     * 行政信息
     */
    @ApiModelProperty(value = "行政信息", required = true)
    @NotNull(message = "行政信息不能为空")
    private UserAdministrationVO userAdministrationDTO;

    /**
     * 个人信息集合
     */
    @ApiModelProperty(value = "行政信息", required = true)
    private UserPersonalVO userPersonalDTOS;

    /**
     * 资质新增关联
     */
    @ApiModelProperty(value = " 资质信息", required = false)
    private List<UserQualificationConfigVO> userQualificationConfigDTOS = new ArrayList<>();;

    /**
     * 员工资质删除id集合
     */
    @ApiModelProperty(value = " 员工资质删除id集合 如果修改时,有删除时需要传递,新增时不需要传递", required = false)
    private List<Long> deleteUserQualificationConfigIds = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public UserAdministrationVO getUserAdministrationDTO() {
        return userAdministrationDTO;
    }

    public void setUserAdministrationDTO(UserAdministrationVO userAdministrationDTO) {
        this.userAdministrationDTO = userAdministrationDTO;
    }

    public UserPersonalVO getUserPersonalDTOS() {
        return userPersonalDTOS;
    }

    public void setUserPersonalDTOS(UserPersonalVO userPersonalDTOS) {
        this.userPersonalDTOS = userPersonalDTOS;
    }

    public List<UserQualificationConfigVO> getUserQualificationConfigDTOS() {
        return userQualificationConfigDTOS;
    }

    public void setUserQualificationConfigDTOS(List<UserQualificationConfigVO> userQualificationConfigDTOS) {
        this.userQualificationConfigDTOS = userQualificationConfigDTOS;
    }

    public List<Long> getDeleteUserQualificationConfigIds() {
        return deleteUserQualificationConfigIds;
    }

    public void setDeleteUserQualificationConfigIds(List<Long> deleteUserQualificationConfigIds) {
        this.deleteUserQualificationConfigIds = deleteUserQualificationConfigIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
