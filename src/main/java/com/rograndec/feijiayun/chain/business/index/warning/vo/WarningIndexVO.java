package com.rograndec.feijiayun.chain.business.index.warning.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class WarningIndexVO implements Serializable {

    /**
     * 预警类型
     */
    @ApiModelProperty(value = "预警类型 : 组织机构,供货单位,员工,商品,库存过期,库存近效期,库存积货,库存缺货,库存滞销,库存养护 的编码")
    private String WarningType;

    @ApiModelProperty(value = "预警类型描述")
    private String WarningTypeDesc;

    /**
     * 预警内容
     */
    @ApiModelProperty(value = "预警内容")
    private String warningContext;

    @ApiModelProperty(value = "预警数量")
    private String warnInfoCount;

    @ApiModelProperty(value = "预警内容+数量")
    private String warningContextAndCount;

    public String getWarningType() {
        return WarningType;
    }

    public void setWarningType(String warningType) {
        WarningType = warningType;
    }

    public String getWarningTypeDesc() {
        return WarningTypeDesc;
    }

    public void setWarningTypeDesc(String warningTypeDesc) {
        WarningTypeDesc = warningTypeDesc;
    }

    public String getWarningContext() {
        return warningContext;
    }

    public void setWarningContext(String warningContext) {
        this.warningContext = warningContext;
    }

    public String getWarnInfoCount() {
        return warnInfoCount;
    }

    public void setWarnInfoCount(String warnInfoCount) {
        this.warnInfoCount = warnInfoCount;
    }

    public String getWarningContextAndCount() {
        return this.warningContext+","+this.warnInfoCount+"条";
    }

    public void setWarningContextAndCount(String warningContextAndCount) {
        this.warningContextAndCount = warningContextAndCount;
    }
}
