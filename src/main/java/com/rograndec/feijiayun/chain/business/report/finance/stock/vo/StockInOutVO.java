package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockInOutVO implements Serializable {

    @ApiModelProperty(required = false, value = "单据日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @ApiModelProperty(required = false, value = "单据类型")
    private Integer orderType;

    @ApiModelProperty(required = false, value = "名称")
    private String orderTypeName;

    @ApiModelProperty(required = false, value = "单据编码")
    private String orderCode;

    @ApiModelProperty(required = false, value = "人员")
    private String manName;

    @ApiModelProperty(required = false, value = "备注")
    private String remark;

    @ApiModelProperty(required = false, value = "方向")
    private Integer direction;

    @ApiModelProperty(required = false, value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(required = false, value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(required = false, value = "组织机构ID")
    private Long enterpriseId;

    @ApiModelProperty(required = false, value = "组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty(required = false, value = "组织机构名称")
    private String enterpriseName;

    @ApiModelProperty(required = false, value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(required = false, value = "规格")
    private String specification;
    /**
     * 商品编码
     */
    @ApiModelProperty(required = false, value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(required = false, value = "条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty(required = false, value = "通用名称")
    private String goodsGenericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(required = false, value = "商品名称")
    private String goodsName;

    /**
     * 检索码（型如“通用名检索码—商品名检索码”）
     */
    @ApiModelProperty(required = false, value = "检索码")
    private String pinyinCode;

    /**
     * 产地
     */
    @ApiModelProperty(required = false, value = "产地")
    private String place;

    /**
     * 批准文号
     */
    @ApiModelProperty(required = false, value = "批准文号")
    private String approvalNumber;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = false, value = "生产厂商")
    private String manufacturer;


    @ApiModelProperty(value = "发出数量")
    private BigDecimal outQuantity;

    @ApiModelProperty(value = "发出金额")
    private BigDecimal outAmount;

    @ApiModelProperty(value = "收入数量")
    private BigDecimal inQuantity;

    @ApiModelProperty(value = "收入金额")
    private BigDecimal inAmount;

    /**
     * 业务发生前商品库存数量
     */
    @ApiModelProperty(value = "业务发生前商品库存数量")
    private BigDecimal storageQuantity;


    @ApiModelProperty(value = "余额数量")
    private BigDecimal balanceQuantity;

    @ApiModelProperty(value = "余额金额")
    private BigDecimal balanceAmount;


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public String getOrderTypeName() {
        if (orderType == null) {
            return "";
        }
        return OrderRule.getName(orderType);
    }


    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOutQuantity() {
        quantity = quantity == null ? BigDecimal.ZERO : quantity;
        if (direction != null) {
            if (direction == OrderDirection.OUT.getDirection()) {
                //出库
                return quantity;
            }
        } else {// 导出时用到
            return outQuantity;
        }

        return BigDecimal.ZERO;
    }

    public void setOutQuantity(BigDecimal outQuantity) {
        this.outQuantity = outQuantity;
    }

    public BigDecimal getOutAmount() {
        amount = amount == null ? BigDecimal.ZERO : amount;
        if (direction != null) {
            if (direction == OrderDirection.OUT.getDirection()) {
                //出库
                return amount;
            }
        } else { // 导出时用到

            return outAmount;
        }
        return BigDecimal.ZERO;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    public BigDecimal getInQuantity() {
        quantity = quantity == null ? BigDecimal.ZERO : quantity;
        if (direction != null) {
            if (direction == OrderDirection.IN.getDirection()) {
                return quantity;
            }
        } else { //导出时需要

            return inQuantity;
        }
        return BigDecimal.ZERO;
    }

    public void setInQuantity(BigDecimal inQuantity) {
        this.inQuantity = inQuantity;
    }

    public BigDecimal getInAmount() {
        amount = amount == null ? BigDecimal.ZERO : amount;
        if (direction != null) {
            if (direction == OrderDirection.IN.getDirection()) {
                return amount;
            }
        } else { // 导出时用
            return inAmount;
        }
        return BigDecimal.ZERO;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public BigDecimal getStorageQuantity() {
        return storageQuantity;
    }

    public void setStorageQuantity(BigDecimal storageQuantity) {
        this.storageQuantity = storageQuantity;
    }

    public BigDecimal getBalanceQuantity() {
        quantity = quantity == null ? BigDecimal.ZERO : quantity;
        storageQuantity = storageQuantity == null ? BigDecimal.ZERO : storageQuantity;
        if (direction != null) {
            if (direction == OrderDirection.OUT.getDirection()) {
                //出库
                return balanceQuantity = storageQuantity.subtract(quantity);
            } else if (direction == OrderDirection.IN.getDirection()) {
                return balanceQuantity = storageQuantity.add(quantity);
            }
        } else {// 导出

           return balanceQuantity;
        }
        return BigDecimal.ZERO;
    }

    public void setBalanceQuantity(BigDecimal balanceQuantity) {
        this.balanceQuantity = balanceQuantity;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecification() {

        return specification == null ? "" : specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getManufacturer() {
        return manufacturer == null ? "" : manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
