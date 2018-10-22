package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_in_return_out_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnOutDetailPageVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 购进退出单明细id
     */
    @ApiModelProperty(value = "购进退出单明细id")
    private Long returnDetailId;

    /**
     * 配进退出出库单ID
     */
    @ApiModelProperty(value = "配进退出出库单ID")
    private Long outId;


    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;


    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;
    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 实际单价（实际金额/数量）
     */
    @ApiModelProperty(value = "实际单价（实际金额/数量）")
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private List<DistrInReturnOutShelfPageVO> distrInReturnOutShelfFormVOS = new ArrayList<>();

    public static DistrInReturnOutDetailPageVO getDistrInReturnOutDetailPageVO4ReturnDetail(DistrInReturnDetail distrInReturnOutDetail){

        DistrInReturnOutDetailPageVO distrInReturnOutDetailPageVO = new DistrInReturnOutDetailPageVO();

        distrInReturnOutDetailPageVO.setReturnDetailId(distrInReturnOutDetail.getId());

        /**
         * 商品ID
         */
        distrInReturnOutDetailPageVO.setGoodsId(distrInReturnOutDetail.getGoodsId());

        /**
         * 商品编码
         */
        distrInReturnOutDetailPageVO.setGoodsCode(distrInReturnOutDetail.getGoodsCode());

        /**
         * 商品通用名称
         */
        distrInReturnOutDetailPageVO.setGoodsGenericName(distrInReturnOutDetail.getGoodsGenericName());

        /**
         * 剂型名称
         */
        distrInReturnOutDetailPageVO.setDosageName(distrInReturnOutDetail.getDosageName());

        /**
         * 单位名称
         */
        distrInReturnOutDetailPageVO.setUnitName(distrInReturnOutDetail.getUnitName());

        /**
         * 商品规格
         */
        distrInReturnOutDetailPageVO.setGoodsSpecification(distrInReturnOutDetail.getGoodsSpecification());

        /**
         * 生产厂商
         */
        distrInReturnOutDetailPageVO.setManufacturer(distrInReturnOutDetail.getManufacturer());

        /**
         * 商品产地
         */
        distrInReturnOutDetailPageVO.setGoodsPlace(distrInReturnOutDetail.getGoodsPlace());

        /**
         * 批号ID
         */
        distrInReturnOutDetailPageVO.setLotId(distrInReturnOutDetail.getLotId());

        /**
         * 批号
         */
        distrInReturnOutDetailPageVO.setLotNumber(distrInReturnOutDetail.getLotNumber());
        /**
         * 生产日期
         */
        distrInReturnOutDetailPageVO.setProductDate(distrInReturnOutDetail.getProductDate());

        /**
         * 有效期
         */
        distrInReturnOutDetailPageVO.setValidDate(distrInReturnOutDetail.getValidDate());

        /**
         * 数量
         */
        distrInReturnOutDetailPageVO.setQuantity(distrInReturnOutDetail.getQuantity());

        /**
         * 单价
         */
        distrInReturnOutDetailPageVO.setUnitPrice(distrInReturnOutDetail.getUnitPrice());

        /**
         * 行折扣（%）
         */
        distrInReturnOutDetailPageVO.setLineDiscount(distrInReturnOutDetail.getLineDiscount());

        /**
         * 金额（整单优惠前金额）
         */
        distrInReturnOutDetailPageVO.setAmount(distrInReturnOutDetail.getAmount());

        /**
         * 实际单价（实际金额/数量）
         */
        distrInReturnOutDetailPageVO.setRealPrice(distrInReturnOutDetail.getRealPrice());

        /**
         * 实际金额
         */
        distrInReturnOutDetailPageVO.setRealAmount(distrInReturnOutDetail.getRealAmount());

        /**
         * 税率ID
         */
        distrInReturnOutDetailPageVO.setTaxRateId(distrInReturnOutDetail.getTaxRateId());

        /**
         * 税率
         */
        distrInReturnOutDetailPageVO.setTaxRate(distrInReturnOutDetail.getTaxRate());

        /**
         * 不含税实际单价
         */
        distrInReturnOutDetailPageVO.setNotaxRealPrice(distrInReturnOutDetail.getNotaxRealPrice());

        /**
         * 不含税实际金额
         */
        distrInReturnOutDetailPageVO.setNotaxRealAmount(distrInReturnOutDetail.getNotaxRealAmount());

        /**
         * 税额
         */
        distrInReturnOutDetailPageVO.setTaxAmount(distrInReturnOutDetail.getTaxAmount());

        /**
         * 状态
         */
        distrInReturnOutDetailPageVO.setStatus(distrInReturnOutDetail.getStatus());
        distrInReturnOutDetailPageVO.setRemark(distrInReturnOutDetail.getRemark());

        return distrInReturnOutDetailPageVO;
    }

    public static DistrInReturnOutDetailPageVO getDistrInReturnOutDetailPageVO(DistrInReturnOutDetail distrInReturnOutDetail, DistrInReturnOutShelf distrInReturnOutShelf){

        DistrInReturnOutDetailPageVO distrInReturnOutDetailPageVO = new DistrInReturnOutDetailPageVO();

        /**
         * 主键ID
         */
        distrInReturnOutDetailPageVO.setId(distrInReturnOutDetail.getId());

        distrInReturnOutDetailPageVO.setReturnDetailId(distrInReturnOutDetail.getBaseOrderDtlId());
        /**
         * 配进退出出库单ID
         */
        distrInReturnOutDetailPageVO.setOutId(distrInReturnOutDetail.getOutId());

        /**
         * 商品ID
         */
        distrInReturnOutDetailPageVO.setGoodsId(distrInReturnOutDetail.getGoodsId());

        /**
         * 商品编码
         */
        distrInReturnOutDetailPageVO.setGoodsCode(distrInReturnOutDetail.getGoodsCode());

        /**
         * 商品通用名称
         */
        distrInReturnOutDetailPageVO.setGoodsGenericName(distrInReturnOutDetail.getGoodsGenericName());

        /**
         * 剂型名称
         */
        distrInReturnOutDetailPageVO.setDosageName(distrInReturnOutDetail.getDosageName());

        /**
         * 单位名称
         */
        distrInReturnOutDetailPageVO.setUnitName(distrInReturnOutDetail.getUnitName());

        /**
         * 商品规格
         */
        distrInReturnOutDetailPageVO.setGoodsSpecification(distrInReturnOutDetail.getGoodsSpecification());

        /**
         * 生产厂商
         */
        distrInReturnOutDetailPageVO.setManufacturer(distrInReturnOutDetail.getManufacturer());

        /**
         * 商品产地
         */
        distrInReturnOutDetailPageVO.setGoodsPlace(distrInReturnOutDetail.getGoodsPlace());

        /**
         * 批号ID
         */
        distrInReturnOutDetailPageVO.setLotId(distrInReturnOutShelf.getLotId());

        /**
         * 批号
         */
        distrInReturnOutDetailPageVO.setLotNumber(distrInReturnOutShelf.getLotNumber());
        /**
         * 生产日期
         */
        distrInReturnOutDetailPageVO.setProductDate(distrInReturnOutShelf.getProductDate());

        /**
         * 有效期
         */
        distrInReturnOutDetailPageVO.setValidDate(distrInReturnOutShelf.getValidDate());

        /**
         * 数量
         */
        distrInReturnOutDetailPageVO.setQuantity(distrInReturnOutDetail.getQuantity());

        /**
         * 单价
         */
        distrInReturnOutDetailPageVO.setUnitPrice(distrInReturnOutDetail.getUnitPrice());

        /**
         * 行折扣（%）
         */
        distrInReturnOutDetailPageVO.setLineDiscount(distrInReturnOutDetail.getLineDiscount());

        /**
         * 金额（整单优惠前金额）
         */
        distrInReturnOutDetailPageVO.setAmount(distrInReturnOutDetail.getAmount());

        /**
         * 实际单价（实际金额/数量）
         */
        distrInReturnOutDetailPageVO.setRealPrice(distrInReturnOutDetail.getRealPrice());

        /**
         * 实际金额
         */
        distrInReturnOutDetailPageVO.setRealAmount(distrInReturnOutDetail.getRealAmount());

        /**
         * 税率ID
         */
        distrInReturnOutDetailPageVO.setTaxRateId(distrInReturnOutDetail.getTaxRateId());

        /**
         * 税率
         */
        distrInReturnOutDetailPageVO.setTaxRate(distrInReturnOutDetail.getTaxRate());

        /**
         * 不含税实际单价
         */
        distrInReturnOutDetailPageVO.setNotaxRealPrice(distrInReturnOutDetail.getNotaxRealPrice());

        /**
         * 不含税实际金额
         */
        distrInReturnOutDetailPageVO.setNotaxRealAmount(distrInReturnOutDetail.getNotaxRealAmount());

        /**
         * 税额
         */
        distrInReturnOutDetailPageVO.setTaxAmount(distrInReturnOutDetail.getTaxAmount());

        /**
         * 状态
         */
        distrInReturnOutDetailPageVO.setStatus(distrInReturnOutDetail.getStatus());

        distrInReturnOutDetailPageVO.setLineNum(distrInReturnOutDetail.getLineNum());
        distrInReturnOutDetailPageVO.setRemark(distrInReturnOutDetail.getRemark());

        return distrInReturnOutDetailPageVO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getReturnDetailId() {
        return returnDetailId;
    }

    public void setReturnDetailId(Long returnDetailId) {
        this.returnDetailId = returnDetailId;
    }

    public List<DistrInReturnOutShelfPageVO> getDistrInReturnOutShelfFormVOS() {
        return distrInReturnOutShelfFormVOS;
    }

    public void setDistrInReturnOutShelfFormVOS(List<DistrInReturnOutShelfPageVO> distrInReturnOutShelfFormVOS) {
        this.distrInReturnOutShelfFormVOS = distrInReturnOutShelfFormVOS;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}