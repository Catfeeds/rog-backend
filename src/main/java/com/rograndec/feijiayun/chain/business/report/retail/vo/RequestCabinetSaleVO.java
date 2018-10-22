package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/23 <br/>
 * 描述：柜组销售请求参数封装
 */
public class RequestCabinetSaleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "每页显示数据", required = false)
    private Integer pageSize;

    @ApiModelProperty(value = "页码", required = false)
    private Integer pageNo;

    /**
     * 总部快速搜索，仅搜索直营店销售数据 ，在此处加默认们直营店
     */
    @ApiModelProperty(required = false, value = "门店类型")
    private Integer chainType ;

    @ApiModelProperty(required = false, value = "门店编码/检索码")
    private String enterpriseCode;

    @ApiModelProperty(required = false, value = "门店名称")
    private String enterpriseName;

    @ApiModelProperty(required = false, value = "柜组名称（注：柜组下拉列表已改为输入文本）")
    private String cargoAreaName;

    @ApiModelProperty(required = true, value = "搜索类型：1-按销售日期；2-按日结日期")
    private Integer type;

    @ApiModelProperty(required = false, value = "销售日期排序：0-升序；1-降序")
    private Integer sortSale;

    @ApiModelProperty(required = false, value = "日结日期排序：0-升序；1-降序")
    private Integer sortDaily;

    @ApiModelProperty(required = false, value = "门店编码排序：0-升序；1-降序")
    private Integer sortCode;

    @ApiModelProperty(required = false, value = "柜组编码排序：0-升序；1-降序")
    private Integer sortCargoAreaCode;

    @ApiModelProperty(value = "查询开始时间", required = false)
    private String startDate;

    @ApiModelProperty(value = "查询结束时间", required = false)
    private String endDate;

    private String sort;
    private Integer epType;
    private Long enterpriseId;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
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

    public String getCargoAreaName() {
        return cargoAreaName;
    }

    public void setCargoAreaName(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
    }

    public Integer getSortSale() {
        return sortSale;
    }

    public void setSortSale(Integer sortSale) {
        this.sortSale = sortSale;
    }

    public Integer getSortDaily() {
        return sortDaily;
    }

    public void setSortDaily(Integer sortDaily) {
        this.sortDaily = sortDaily;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSortCargoAreaCode() {
        return sortCargoAreaCode;
    }

    public void setSortCargoAreaCode(Integer sortCargoAreaCode) {
        this.sortCargoAreaCode = sortCargoAreaCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEpType() {
        return epType;
    }

    public void setEpType(Integer epType) {
        this.epType = epType;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
