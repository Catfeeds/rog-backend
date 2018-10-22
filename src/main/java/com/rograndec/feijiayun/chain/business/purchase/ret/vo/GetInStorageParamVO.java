package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/21 14:04
 */

public class GetInStorageParamVO {

    @ApiModelProperty("开始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;

    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

    @ApiModelProperty("日期排序 0/倒序；1/正序;默认0")
    private Integer dateOrder;

    @ApiModelProperty("单号排序 0/倒序；1/正序;默认0")
    private Integer codeOrder;

    @ApiModelProperty("行号排序 0/倒序；1/正序;默认0")
    private Integer lineNumOrder;

    private Long enterpriseId;

    @ApiModelProperty("入库单id")
    private Long storageId;

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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCodeOrder() {
        if(codeOrder == null){
            return 0;
        }
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public Integer getLineNumOrder() {
        if(lineNumOrder == null){
            return 1;
        }
        return lineNumOrder;
    }

    public void setLineNumOrder(Integer lineNumOrder) {
        this.lineNumOrder = lineNumOrder;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getDateOrder() {
        if(dateOrder == null){
            return 0;
        }
        return dateOrder;
    }

    public void setDateOrder(Integer dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }
}