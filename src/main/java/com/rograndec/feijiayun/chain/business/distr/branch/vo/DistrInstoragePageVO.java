package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrInstoragePageVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date inDate;

    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "配进入库单号")
    private String code;

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
     * 出库位单位编码
     */
    @ApiModelProperty(value = "出库位单位编码 ")
    private String outboundUnitCode;

    /**
     * 出库单位名称
     */
    @ApiModelProperty(value = "出库单位名称")
    private String outboundUnitName;


    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    /**
     * 配货类型供前台显示（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private String distrTypeName;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 当前页搜索条件下的金额总计----右下角的金额总计
     */
    @ApiModelProperty(value = "当前页搜索条件下的金额总计----右下角的金额总计")
    private BigDecimal stasticRealAmountTotal;

    /**
     * 当前页搜索条件下的含税金额总计----右下角的不含税金额总计
     */
    @ApiModelProperty(value = "当前页搜索条件下的含税金额总计----右下角的不含税金额总计")
    private BigDecimal stasticNotaxRealAmountTotal;

    /**
     * 当前页搜索条件下的税额总计----右下角的税额总计
     */
    @ApiModelProperty(value = "当前页搜索条件下的税额总计----右下角的税额总计")
    private BigDecimal stasticTaxAmountTotal;

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getStasticRealAmountTotal() {
        return stasticRealAmountTotal;
    }

    public void setStasticRealAmountTotal(BigDecimal stasticRealAmountTotal) {
        this.stasticRealAmountTotal = stasticRealAmountTotal;
    }

    public BigDecimal getStasticNotaxRealAmountTotal() {
        return stasticNotaxRealAmountTotal;
    }

    public void setStasticNotaxRealAmountTotal(BigDecimal stasticNotaxRealAmountTotal) {
        this.stasticNotaxRealAmountTotal = stasticNotaxRealAmountTotal;
    }

    public BigDecimal getStasticTaxAmountTotal() {
        return stasticTaxAmountTotal;
    }

    public void setStasticTaxAmountTotal(BigDecimal stasticTaxAmountTotal) {
        this.stasticTaxAmountTotal = stasticTaxAmountTotal;
    }

    @Override
    public String toString() {
        return "DistrInstoragePageVO{" +
                "id=" + id +
                ", inDate=" + inDate +
                ", code='" + code + '\'' +
                ", distrUnitId=" + distrUnitId +
                ", distrUnitCode='" + distrUnitCode + '\'' +
                ", distrUnitName='" + distrUnitName + '\'' +
                ", distrType=" + distrType +
                ", storageManId=" + storageManId +
                ", storageManCode='" + storageManCode + '\'' +
                ", storageManName='" + storageManName + '\'' +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", status='" + status + '\'' +
                '}';
    }

    public static DistrInstoragePageVO converToVO(DistrIn d) {
        DistrInstoragePageVO vo = new DistrInstoragePageVO();
        vo.setId(d.getId());
        vo.setInDate(d.getInDate());
        vo.setCode(d.getCode());
        vo.setDistrUnitId(d.getDistrUnitId());
        vo.setDistrUnitCode(d.getDistrUnitCode());
        vo.setDistrUnitName(d.getDistrUnitName());
        vo.setOutboundUnitCode(d.getOutboundUnitCode());
        vo.setOutboundUnitName(d.getOutboundUnitName());
        vo.setDistrType(d.getDistrType());
        //（0-总部配送；3-分店间调剂；4-直调配送）
        vo.setDistrTypeName(DistributionType.getName(d.getDistrType()));
        vo.setStorageManId(d.getStorageManId());
        vo.setStorageManCode(d.getStorageManCode());
        vo.setStorageManName(d.getStorageManName());
        vo.setRealAmountTotal(d.getRealAmountTotal());
        vo.setNotaxRealAmountTotal(d.getNotaxRealAmountTotal());
        vo.setTaxAmountTotal(d.getTaxAmountTotal());
        vo.setStatus(DistrInStatus.getStatusDesc(d.getStatus()));
        return vo;
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
