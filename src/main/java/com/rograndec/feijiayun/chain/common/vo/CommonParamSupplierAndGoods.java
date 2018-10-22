package com.rograndec.feijiayun.chain.common.vo;

/**
 * 功能描述：
 * Created by ST on 2017/12/25 10:56
 */

public class CommonParamSupplierAndGoods {

    //企业id
    private Long enterpriseId;
    //企业类型ID（0-总部；1-自营店；2-加盟店）
    private Integer chainType;

    private Long parentId;


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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}