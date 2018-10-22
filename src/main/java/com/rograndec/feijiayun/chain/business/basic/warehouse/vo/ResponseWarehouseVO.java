package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/9/1.
 */
public class ResponseWarehouseVO extends RequestWarehouseVO {

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

//    @ApiModelProperty(value = "是否为默认企业0/否;1/是;默认的仓库编码规则在没使用的情况下是可以编辑的,其他仓库的编码规则都不可以编辑")
//    private int isDefault;

    @ApiModelProperty(value = "是否可以删除0/否;1/是")
    private int isDelete;

    @ApiModelProperty(value = "是否可以修改为禁用的状态,0/否;1/是")
    private Integer isDisabled ;


    @ApiModelProperty(value = "编码规则是否可修改判断")
    private Boolean canUpdateCodeRule;





    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }


    public Integer getIsDisabled() {
        if(isDisabled == null){
            return 0;
        }
        return isDisabled;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Boolean getCanUpdateCodeRule() {
        if(canUpdateCodeRule == null){
            return false;
        }
        return canUpdateCodeRule ;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public void setCanUpdateCodeRule(Boolean canUpdateCodeRule) {
        this.canUpdateCodeRule = canUpdateCodeRule;
    }
}