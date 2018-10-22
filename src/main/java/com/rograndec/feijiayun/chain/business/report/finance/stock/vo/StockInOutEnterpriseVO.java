package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 库存明细账: 第一层，企业层
 */
public class StockInOutEnterpriseVO {

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

    @ApiModelProperty(required = false, value = "明细账商品层")
    private List<StockInOutGoodsVO> stockInOutGoodsVOList;


    public List<StockInOutGoodsVO> getStockInOutGoodsVOList() {
        return stockInOutGoodsVOList;
    }

    public void setStockInOutGoodsVOList(List<StockInOutGoodsVO> stockInOutGoodsVOList) {
        this.stockInOutGoodsVOList = stockInOutGoodsVOList;
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
