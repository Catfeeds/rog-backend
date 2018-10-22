package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/9/1.
 */
public class RequestWarehouseCommonVO {

    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示的数量",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "状态（0-禁用/1-启用) 不填写查询所有的状态")
    private Integer status;

    @ApiModelProperty(value = "编码(0/倒序；1/正序；)")
    private Integer codeOrder;



    @ApiModelProperty(value = "仓库名称(0/倒序；1/正序；)")
    private Integer warehouseNameOrder;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
            return 1;
        }
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }



    public Integer getWarehouseNameOrder() {
        if(warehouseNameOrder == null){
            return 1;
        }
        return warehouseNameOrder;
    }

    public void setWarehouseNameOrder(Integer warehouseNameOrder) {
        this.warehouseNameOrder = warehouseNameOrder;
    }
}