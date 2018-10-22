package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaiwei on 2017/8/23.
 */
@ApiModel
public class UserInitVO implements Serializable {

    /**
     * 质量控制开关状态 0:禁用,1:启用
     */
    @ApiModelProperty(value = "质量控制开关状态 0:禁用,1:启用 (新增行政信息时 字段选填和必填) ", required = true)
    private Integer qualityEnable;

    /**
     * 需要校验的字段,如果质量控制开关状态为禁用则改属性为空
     */
    @ApiModelProperty(value = "质量控制开关状态开启后哪些字段必传 0:禁用,1:启用 该属性为map,key是字段名称,value为true 例: limitVariety:true", required = true)
    private List<String> qualityEnableCheckFileds;


    /**
     * 用户是否自定义用户编码
     */
    @ApiModelProperty(value = "用户是否自定义用户编码 0:禁用,1:启用", required = true)
    private Integer userCodeEnable;

    @ApiModelProperty(value = "用户是否设置了非自定义编码哪些字段必传 0:禁用,1:启用 该属性为map,key是字段名称,value为true 例: limitVariety:true", required = true)
    private List<String> userCodeEnableCheckFileds;

    /**
     * 添加资质信息时 选择对应的资质描述字段可选和必填数组,需要和资质描述信息里面对应的字段设置来做判断
     */
    @ApiModelProperty(value = "选择资质信息的资质为必填后哪些字段必填", required = true)
    private List<String>userQualificationConfigFields;

    @ApiModelProperty(value = "修改资质时哪些字段不可修改", required = true)
    private List<String> updateUserFields;

    @ApiModelProperty(value = "用户类型,0:总部,1:非总部", required = true)
    private Integer userType;

    public Integer getQualityEnable() {
        return qualityEnable;
    }

    public void setQualityEnable(Integer qualityEnable) {
        this.qualityEnable = qualityEnable;
    }


    public Integer getUserCodeEnable() {
        return userCodeEnable;
    }

    public void setUserCodeEnable(Integer userCodeEnable) {
        this.userCodeEnable = userCodeEnable;
    }

    public List<String> getUserCodeEnableCheckFileds() {
        return userCodeEnableCheckFileds;
    }

    public void setUserCodeEnableCheckFileds(List<String> userCodeEnableCheckFileds) {
        this.userCodeEnableCheckFileds = userCodeEnableCheckFileds;
    }

    public List<String> getUserQualificationConfigFields() {
        return userQualificationConfigFields;
    }

    public void setUserQualificationConfigFields(List<String> userQualificationConfigFields) {
        this.userQualificationConfigFields = userQualificationConfigFields;
    }

    public List<String> getUpdateUserFields() {
        return updateUserFields;
    }

    public void setUpdateUserFields(List<String> updateUserFields) {
        this.updateUserFields = updateUserFields;
    }

    public List<String> getQualityEnableCheckFileds() {
        return qualityEnableCheckFileds;
    }

    public void setQualityEnableCheckFileds(List<String> qualityEnableCheckFileds) {
        this.qualityEnableCheckFileds = qualityEnableCheckFileds;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
