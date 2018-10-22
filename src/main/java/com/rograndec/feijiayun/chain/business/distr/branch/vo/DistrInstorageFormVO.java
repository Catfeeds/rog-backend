package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DistrInstorageFormVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 上游验收单ID
     */
    @ApiModelProperty(value = "上游验收单ID")
    private Long checkId;

    /**
     * 配货单位ID
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 配货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 出库单位ID
     */
    @ApiModelProperty(value = "出库单位ID,配货类型为总部配送的不需要传这个值,配货类型为门店间调剂对应调出单位;配货类型为直调配送对应供货单位")
    private Long outboundUnitId;

    /**
     * 出库单位
     */
    @ApiModelProperty(value = "出库单位")
    private String outboundUnitCode;

    /**
     * 出库单位
     */
    @ApiModelProperty(value = "出库单位名称")
    private String outboundUnitName;

    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "配进入库单号")
    private String code;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date inDate;

    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID")
    private Long storageManId;

    /**
     * 入库人员编码
     */
    @ApiModelProperty(value = "入库人员编码")
    private String storageManCode;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String storageManName;


    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;


    @ApiModelProperty(value = "供前台显示配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private String distrTypeDetail;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;

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
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

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

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;



    /**
     * 品种明细
     */
    @ApiModelProperty(value = "品种明细")
    private List<DistrInstorageDtlVO> distrInstorageDtlList;

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDistrUnitId() {
        return distrUnitId;
    }

    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Long getStorageManId() {
        return storageManId;
    }

    public void setStorageManId(Long storageManId) {
        this.storageManId = storageManId;
    }

    public String getStorageManCode() {
        return storageManCode;
    }

    public void setStorageManCode(String storageManCode) {
        this.storageManCode = storageManCode;
    }

    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
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

    public List<DistrInstorageDtlVO> getDistrInstorageDtlList() {
        return distrInstorageDtlList;
    }

    public void setDistrInstorageDtlList(List<DistrInstorageDtlVO> distrInstorageDtlList) {
        this.distrInstorageDtlList = distrInstorageDtlList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public String getDistrTypeDetail() {
        return distrTypeDetail;
    }

    public void setDistrTypeDetail(String distrTypeDetail) {
        this.distrTypeDetail = distrTypeDetail;
    }

    @Override
    public String toString() {
        return "DistrInstorageFormVO{" +
                "id=" + id +
                ", distrUnitId=" + distrUnitId +
                ", distrUnitCode='" + distrUnitCode + '\'' +
                ", distrUnitName='" + distrUnitName + '\'' +
                ", code='" + code + '\'' +
                ", inDate=" + inDate +
                ", storageManId=" + storageManId +
                ", storageManCode='" + storageManCode + '\'' +
                ", storageManName='" + storageManName + '\'' +
                ", distrType=" + distrType +
                ", status='" + status + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", amountTotal=" + amountTotal +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", distrInstorageDtlList=" + distrInstorageDtlList +
                '}';
    }

    public static DistrInstorageFormVO convertToVO(DistrIn distrIn) {
        DistrInstorageFormVO vo = new DistrInstorageFormVO();
        vo.setId(distrIn.getId());
        vo.setDistrUnitId(distrIn.getDistrUnitId());
        vo.setDistrUnitCode(distrIn.getDistrUnitCode());
        vo.setDistrUnitName(distrIn.getDistrUnitName());
        vo.setCode(distrIn.getCode());
        vo.setInDate(distrIn.getInDate());
        vo.setStorageManId(distrIn.getStorageManId());
        vo.setStorageManCode(distrIn.getStorageManCode());
        vo.setStorageManName(distrIn.getStorageManName());
        vo.setDistrType(distrIn.getDistrType());
        vo.setStatus(DistrInStatus.getStatusDesc(distrIn.getStatus()));
        vo.setQuantityTotal(distrIn.getQuantityTotal());
        vo.setVarietiesQuantity(distrIn.getVarietiesQuantity());
        vo.setAmountTotal(distrIn.getAmountTotal());
        vo.setWholeDiscount(distrIn.getWholeDiscount());
        vo.setWholeDiscountAmount(distrIn.getWholeDiscountAmount());
        vo.setRealAmountTotal(distrIn.getRealAmountTotal());
        vo.setNotaxRealAmountTotal(distrIn.getNotaxRealAmountTotal());
        vo.setTaxAmountTotal(distrIn.getTaxAmountTotal());
        vo.setRemark(distrIn.getRemark());
        vo.setDistrTypeDetail(DistributionType.getName(distrIn.getDistrType()));
        vo.setOutboundUnitId(distrIn.getOutboundUnitId());
        vo.setOutboundUnitCode(distrIn.getOutboundUnitCode());
        vo.setOutboundUnitName(distrIn.getOutboundUnitName());
        return vo;
    }

    public Long getOutboundUnitId() {
        return outboundUnitId;
    }

    public void setOutboundUnitId(Long outboundUnitId) {
        this.outboundUnitId = outboundUnitId;
    }

    public String getOutboundUnitCode() {
        return outboundUnitCode;
    }

    public void setOutboundUnitCode(String outboundUnitCode) {
        this.outboundUnitCode = outboundUnitCode;
    }

    public String getOutboundUnitName() {
        return outboundUnitName;
    }

    public void setOutboundUnitName(String outboundUnitName) {
        this.outboundUnitName = outboundUnitName;
    }
}
