package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel
public class EducationMajorVO implements Serializable {

    /**
     * 学历、专业名称
     */
    @NotNull(message = "名称不能为空")
    @ApiModelProperty(value = "学历、专业名称", required = true)
    private String name;

    /**
     * 系统默认标志（0-用户自定义；1-系统默认）
     */
    @ApiModelProperty(value = "系统默认标志（0-用户自定义；1-系统默认）,默认不需要传递,用户添加属于用户自定义", required = false)
    private Integer sysType = 0;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用) 默认不需要传递,默认是启用", required = false)
    private Integer status = 1;


    /**
     * saas_education_major
     */
    private static final long serialVersionUID = 1L;

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
        sb.append(", name=").append(name);
        sb.append(", sysType=").append(sysType);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}