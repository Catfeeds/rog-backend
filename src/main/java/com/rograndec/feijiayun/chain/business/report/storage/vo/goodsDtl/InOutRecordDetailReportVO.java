package com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl;

import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InOutRecordDetailReportVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 出入库单ID
     */
    @ApiModelProperty(value="出入库单ID")
    private Long inOutId;

    /**
     * 企业ID
     */
    @ApiModelProperty(value="企业ID")
    private Long enterpriseId;



    /**
     * 方向（0-入库；1-出库）
     */
    @ApiModelProperty(value="方向（0-入库；1-出库）")
    private Integer direction;

    /**
     *
     */
    @ApiModelProperty(value="业务单据ID")
    private Long orderId;

    /**
     * 业务单据编码
     */
    @ApiModelProperty(value="业务单据编码")
    private String orderCode;

    /**
     * 业务单据明细ID
     */
    @ApiModelProperty(value="业务单据明细ID")
    private Long orderDtlId;

    /**
     * 单据类型
     */
    @ApiModelProperty(value="单据类型")
    private Integer orderType;

    @ApiModelProperty(value="单据类型")
    private String orderTypeName;

    /**
     * 单据日期
     */

    @ApiModelProperty(value="单据日期")
    private Date orderDate;

    /**
     * 批号ID
     */
    @ApiModelProperty(value="批号ID")
    private Long lotId;
    
    /**
     * 批号
     */
    @ApiModelProperty(value="批号")
    private String lotNum;

    /**
     * 货位ID
     */
    @ApiModelProperty(value="货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value="货位名称")
    private String shelfName;

    /**
     * 数量
     */
    @ApiModelProperty(value="数量")
    private BigDecimal quantity;


    @ApiModelProperty(value="发出数量")
    private BigDecimal outQuantity;

    @ApiModelProperty(value="收入数量")
    private BigDecimal inQuantity;
    /**
     * 业务发生前商品库存数量
     */
    @ApiModelProperty(value="业务发生前商品库存数量")
    private BigDecimal storageQuantity;



    @ApiModelProperty(value="结存")
    private BigDecimal storageBalance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInOutId() {
        return inOutId;
    }

    public void setInOutId(Long inOutId) {
        this.inOutId = inOutId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getOrderDtlId() {
        return orderDtlId;
    }

    public void setOrderDtlId(Long orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getStorageQuantity() {
        return storageQuantity;
    }

    public void setStorageQuantity(BigDecimal storageQuantity) {
        this.storageQuantity = storageQuantity;
    }

    public BigDecimal getStorageBalance() {
        quantity = quantity == null ? BigDecimal.ZERO : quantity;
        storageQuantity = storageQuantity == null ? BigDecimal.ZERO : storageQuantity;
        if(direction != null){
            if(direction == OrderDirection.OUT.getDirection()){
                //出库
               return storageBalance = storageQuantity.subtract(quantity);
            } else if(direction == OrderDirection.IN.getDirection()){
               return storageBalance = storageQuantity.add(quantity);
            }
        }
        return BigDecimal.ZERO;
    }

    public void setStorageBalance(BigDecimal storageBalance) {
        this.storageBalance = storageBalance;
    }

    public String getOrderTypeName() {

        if(orderType == null){
            return "";
        }
        return OrderRule.getName(orderType);
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public BigDecimal getOutQuantity() {
        quantity = quantity == null ? BigDecimal.ZERO : quantity;
        if(direction != null){
            if(direction == OrderDirection.OUT.getDirection()){
                //出库
                return quantity;
            }
        }
        return BigDecimal.ZERO;
    }

    public void setOutQuantity(BigDecimal outQuantity) {
        this.outQuantity = outQuantity;
    }

    public BigDecimal getInQuantity() {
        quantity = quantity == null ? BigDecimal.ZERO : quantity;
        if(direction != null){
            if(direction == OrderDirection.IN.getDirection()){
                return quantity;
            }
        }
        return BigDecimal.ZERO;
    }

    public void setInQuantity(BigDecimal inQuantity) {
        this.inQuantity = inQuantity;
    }
}