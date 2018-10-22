package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: chain-backend
 * @description: 库存总账(按组织机构)
 * @author: dongyang.du
 * @create: 2018-01-14 13:45
 **/
public class StockLedgerEnterpriseVO extends BaseInOutTotal implements Serializable{


    @ApiModelProperty(required = false, value = "组织机构ID")
    private Long enterpriseId;

    @ApiModelProperty(required = false, value = "组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty(required = false, value = "组织机构名称")
    private String enterpriseName;

    @ApiModelProperty(required = false, value = "组织机构类型")
    private Integer chainType;

    @ApiModelProperty(required = false, value = "组织机构类型名称")
    private String chainTypeName;


    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public String getChainTypeName() {
        return ChainType.getName(chainType);
    }

    public void setChainTypeName(String chainTypeName) {
        this.chainTypeName = chainTypeName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

}
