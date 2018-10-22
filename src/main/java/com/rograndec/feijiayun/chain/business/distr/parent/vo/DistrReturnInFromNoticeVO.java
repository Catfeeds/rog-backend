package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DistrReturnInFromNoticeVO implements Serializable{


    // -------------收货--------------------
    /**
     * 配后退回收货日期
     */
    @ApiModelProperty(value = "配后退回收货日期")
    private Date receiveDate;


    @ApiModelProperty(value = "配后退回ID")
    private Long  noticeId;
    /**
     * 收货人员ID
     */
    @ApiModelProperty(value = "收货人员ID")
    private Long receiverId;

    @ApiModelProperty(value = "收货人员名称")
    private String receiverName;

    /**
     * 第二收货人员ID
     */
    @ApiModelProperty(value = "第二收货人员ID")
    private Long secondReceiverId;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;



    //-------------验收---------------

    /**
     * 配货类型ID
     */
    @ApiModelProperty(value = "配货类型ID")
    private Integer distrType;


    @ApiModelProperty(value = "配货类型描述")
    private String distrTypeDesc;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;

    /**
     * 验收人员1ID
     */
    @ApiModelProperty(value = "验收人员1ID")
    private Long checkerId;

    private String checkerName;

    /**
     * 验收人员2ID
     */
    @ApiModelProperty(value = "验收人员2ID")
    private Long secondCheckerId;




    //-------------入库---------------

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期",required = true)
    private Date returnInDate;


    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID",required = true)
    private Long storageManId;

    @ApiModelProperty(value = "入库人员名称",required = true)
    private String storageManName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;



    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）",required = true)
    private BigDecimal wholeDiscount;


    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额",required = true)
    private BigDecimal wholeDiscountAmount;




    // --------------配后退回通知单(页码展示)------------------

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;


    @ApiModelProperty(value = "商品明细单",required = true)
    private List<DistrReturnInFromNoticeDtlVO> distrReturnInFromNoticeDtlVOList;


    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode;
    }

    public String getRequestUnitName() {
        return requestUnitName;
    }

    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }


    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public Date getReturnInDate() {
        return returnInDate;
    }

    public void setReturnInDate(Date returnInDate) {
        this.returnInDate = returnInDate;
    }

    public Long getStorageManId() {
        return storageManId;
    }

    public void setStorageManId(Long storageManId) {
        this.storageManId = storageManId;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    public void setSecondReceiverId(Long secondReceiverId) {
        this.secondReceiverId = secondReceiverId;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    public List<DistrReturnInFromNoticeDtlVO> getDistrReturnInFromNoticeDtlVOList() {
        return distrReturnInFromNoticeDtlVOList;
    }

    public void setDistrReturnInFromNoticeDtlVOList(List<DistrReturnInFromNoticeDtlVO> distrReturnInFromNoticeDtlVOList) {
        this.distrReturnInFromNoticeDtlVOList = distrReturnInFromNoticeDtlVOList;
    }

    public String getDistrTypeDesc() {
        return DistrType.getValue(distrType);
    }

    public void setDistrTypeDesc(String distrTypeDesc) {
        this.distrTypeDesc = distrTypeDesc;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
    }
}
