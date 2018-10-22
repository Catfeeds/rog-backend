package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ST on 2017/9/5.
 */
public class EnterpriseInGoodsVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    /**
     * 上级企业ID，默认值为0
     */
    @ApiModelProperty(value = "企业父级id")
    private Long parentId;

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }
}