package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * 功能描述：供货单位质量档案
 * Created by ST on 2017/10/19 14:42
 */

public class SupplierFileReportDetailVO  extends SupplierDetailVO{

    @ApiModelProperty(value = "订单笔数")
    private BigDecimal orderCount;
    @ApiModelProperty(value = "订单数量")
    private BigDecimal orderQuantityTotal;
    @ApiModelProperty(value = "订单品种")
    private Long orderVarietiesQuantity;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmountTotal;




    @ApiModelProperty(value = "拒收笔数")
    private BigDecimal receiveRefuseCount;
    @ApiModelProperty(value = "拒收数量")
    private BigDecimal receiveRefuseQuantity;
    @ApiModelProperty(value = "拒收品种")
    private Long receiveRefuseVarietiesQuantity;


    @ApiModelProperty(value = "验收不合格笔数")
    private BigDecimal checkUnqualifiedCount;
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal checkUnqualifiedQuantity;
    @ApiModelProperty(value = "验收不合格品种")
    private Long checkVarietiesQuantity;


    @ApiModelProperty(value = "入库笔数")
    private BigDecimal inStorageCount;
    @ApiModelProperty(value = "入库数量")
    private BigDecimal inStorageQuantityTotal;
    @ApiModelProperty(value = "入库品种")
    private Long inStorageVarietiesQuantity;
    @ApiModelProperty(value = "入库金额")
    private BigDecimal inStorageAmountTotal;


    @ApiModelProperty(value = "购进退出笔数")
    private BigDecimal returnOutCount;
    @ApiModelProperty(value = "购进退出数量")
    private BigDecimal returnOutQuantityTotal;
    @ApiModelProperty(value = "购进退出品种")
    private Long returnOutVarietiesQuantity;
    @ApiModelProperty(value = "购进退出金额")
    private BigDecimal returnOutAmountTotal;

    public BigDecimal getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(BigDecimal orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderQuantityTotal() {
        return orderQuantityTotal;
    }

    public void setOrderQuantityTotal(BigDecimal orderQuantityTotal) {
        this.orderQuantityTotal = orderQuantityTotal;
    }

    public Long getOrderVarietiesQuantity() {
        return orderVarietiesQuantity;
    }

    public void setOrderVarietiesQuantity(Long orderVarietiesQuantity) {
        this.orderVarietiesQuantity = orderVarietiesQuantity;
    }

    public BigDecimal getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(BigDecimal orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public BigDecimal getReceiveRefuseCount() {
        return receiveRefuseCount;
    }

    public void setReceiveRefuseCount(BigDecimal receiveRefuseCount) {
        this.receiveRefuseCount = receiveRefuseCount;
    }

    public BigDecimal getReceiveRefuseQuantity() {
        return receiveRefuseQuantity;
    }

    public void setReceiveRefuseQuantity(BigDecimal receiveRefuseQuantity) {
        this.receiveRefuseQuantity = receiveRefuseQuantity;
    }

    public Long getReceiveRefuseVarietiesQuantity() {
        return receiveRefuseVarietiesQuantity;
    }

    public void setReceiveRefuseVarietiesQuantity(Long receiveRefuseVarietiesQuantity) {
        this.receiveRefuseVarietiesQuantity = receiveRefuseVarietiesQuantity;
    }

    public BigDecimal getCheckUnqualifiedCount() {
        return checkUnqualifiedCount;
    }

    public void setCheckUnqualifiedCount(BigDecimal checkUnqualifiedCount) {
        this.checkUnqualifiedCount = checkUnqualifiedCount;
    }

    public BigDecimal getCheckUnqualifiedQuantity() {
        return checkUnqualifiedQuantity;
    }

    public void setCheckUnqualifiedQuantity(BigDecimal checkUnqualifiedQuantity) {
        this.checkUnqualifiedQuantity = checkUnqualifiedQuantity;
    }

    public Long getCheckVarietiesQuantity() {
        return checkVarietiesQuantity;
    }

    public void setCheckVarietiesQuantity(Long checkVarietiesQuantity) {
        this.checkVarietiesQuantity = checkVarietiesQuantity;
    }

    public BigDecimal getInStorageCount() {
        return inStorageCount;
    }

    public void setInStorageCount(BigDecimal inStorageCount) {
        this.inStorageCount = inStorageCount;
    }

    public BigDecimal getInStorageQuantityTotal() {
        return inStorageQuantityTotal;
    }

    public void setInStorageQuantityTotal(BigDecimal inStorageQuantityTotal) {
        this.inStorageQuantityTotal = inStorageQuantityTotal;
    }

    public Long getInStorageVarietiesQuantity() {
        return inStorageVarietiesQuantity;
    }

    public void setInStorageVarietiesQuantity(Long inStorageVarietiesQuantity) {
        this.inStorageVarietiesQuantity = inStorageVarietiesQuantity;
    }

    public BigDecimal getInStorageAmountTotal() {
        return inStorageAmountTotal;
    }

    public void setInStorageAmountTotal(BigDecimal inStorageAmountTotal) {
        this.inStorageAmountTotal = inStorageAmountTotal;
    }

    public BigDecimal getReturnOutCount() {
        return returnOutCount;
    }

    public void setReturnOutCount(BigDecimal returnOutCount) {
        this.returnOutCount = returnOutCount;
    }

    public BigDecimal getReturnOutQuantityTotal() {
        return returnOutQuantityTotal;
    }

    public void setReturnOutQuantityTotal(BigDecimal returnOutQuantityTotal) {
        this.returnOutQuantityTotal = returnOutQuantityTotal;
    }

    public Long getReturnOutVarietiesQuantity() {
        return returnOutVarietiesQuantity;
    }

    public void setReturnOutVarietiesQuantity(Long returnOutVarietiesQuantity) {
        this.returnOutVarietiesQuantity = returnOutVarietiesQuantity;
    }

    public BigDecimal getReturnOutAmountTotal() {
        return returnOutAmountTotal;
    }

    public void setReturnOutAmountTotal(BigDecimal returnOutAmountTotal) {
        this.returnOutAmountTotal = returnOutAmountTotal;
    }
}