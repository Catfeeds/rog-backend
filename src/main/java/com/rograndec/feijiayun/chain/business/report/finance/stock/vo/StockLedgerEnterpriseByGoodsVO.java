package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @program: chain-backend
 * @description: 库存总账 企业层级 按商品
 * @author: dongyang.du
 * @create: 2018-01-14 18:03
 **/
public class StockLedgerEnterpriseByGoodsVO implements Serializable{


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




    @ApiModelProperty(required = false, value = "月份list")
    List<StockLedgerMonthByGoodsVO> monthList;

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

    public List<StockLedgerMonthByGoodsVO> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<StockLedgerMonthByGoodsVO> monthList) {
        this.monthList = monthList;
    }
}
