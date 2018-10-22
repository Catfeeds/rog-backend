package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutDetail;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutShelf;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_other_out_detail
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
public class OtherOutDetailPageVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;


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
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

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
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

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
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 销项税ID
     */
    @ApiModelProperty(value = "销项税ID")
    private Long taxRateId;

    /**
     * 销项税
     */
    @ApiModelProperty(value = "销项税")
    private BigDecimal taxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;


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

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;


    @ApiModelProperty(value = "其他出库明细货位信息")
    private List<OtherOutShelfPageVO> otherOutShelfFormVOS = new ArrayList<>();

    public static List<OtherOutDetailPageVO> getOtherOutDetailPageVOs(List<OtherOutDetail> otherOutDetails, List<OtherOutShelf> otherOutShelves){

        List<OtherOutDetailPageVO> otherOutDetailPageVOS = new ArrayList<>();

        for(OtherOutDetail otherOutDetail : otherOutDetails){
            OtherOutDetailPageVO otherOutPageDetail = getOtherOutPageDetail(otherOutDetail, otherOutShelves);
            otherOutDetailPageVOS.add(otherOutPageDetail);
        }

        return otherOutDetailPageVOS;
    }

    public static OtherOutDetailPageVO getOtherOutPageDetail(OtherOutDetail otherOutDetail, List<OtherOutShelf> otherOutShelves){

        OtherOutDetailPageVO otherOutDetailPageVO = new OtherOutDetailPageVO();


        otherOutShelves.stream().filter(os -> os.getDtlId().equals(otherOutDetail.getId())).forEach(os -> {

            OtherOutShelfPageVO otherOutShelfPageVO = OtherOutShelfPageVO.getOtherOutShelfPageVO(os);
            otherOutDetailPageVO.getOtherOutShelfFormVOS().add(otherOutShelfPageVO);
        });


        /**
         * 主键ID
         */
        otherOutDetailPageVO.setId(otherOutDetail.getId());

        /**
         * 企业ID
         */
        otherOutDetailPageVO.setEnterpriseId(otherOutDetail.getEnterpriseId());

        /**
         * 上级企业ID
         */
        otherOutDetailPageVO.setParentId(otherOutDetail.getParentId());

        /**
         * 商品ID
         */
        otherOutDetailPageVO.setGoodsId(otherOutDetail.getGoodsId());

        /**
         * 商品编码
         */
        otherOutDetailPageVO.setGoodsCode(otherOutDetail.getGoodsCode());

        /**
         * 商品名称
         */
        otherOutDetailPageVO.setGoodsName(otherOutDetail.getGoodsName());

        /**
         * 商品通用名称
         */
        otherOutDetailPageVO.setGoodsGenericName(otherOutDetail.getGoodsGenericName());

        /**
         * 剂型ID
         */
        otherOutDetailPageVO.setDosageId(otherOutDetail.getDosageId());

        /**
         * 剂型名称
         */
        otherOutDetailPageVO.setDosageName(otherOutDetail.getDosageName());

        /**
         * 单位ID
         */
        otherOutDetailPageVO.setUnitId(otherOutDetail.getUnitId());

        /**
         * 单位名称
         */
        otherOutDetailPageVO.setUnitName(otherOutDetail.getUnitName());

        /**
         * 商品规格
         */
        otherOutDetailPageVO.setGoodsSpecification(otherOutDetail.getGoodsSpecification());

        /**
         * 生产厂商ID
         */
        otherOutDetailPageVO.setManufacturerId(otherOutDetail.getManufacturerId());

        /**
         * 生产厂商
         */
        otherOutDetailPageVO.setManufacturer(otherOutDetail.getManufacturer());



        /**
         * 商品产地
         */
        otherOutDetailPageVO.setGoodsPlace(otherOutDetail.getGoodsPlace());

        /**
         * 数量
         */
        otherOutDetailPageVO.setQuantity(otherOutDetail.getQuantity());

        /**
         * 单价
         */
        otherOutDetailPageVO.setUnitPrice(otherOutDetail.getUnitPrice());

        /**
         * 金额
         */
        otherOutDetailPageVO.setAmount(otherOutDetail.getAmount());

        /**
         * 销项税ID
         */
        otherOutDetailPageVO.setTaxRateId(otherOutDetail.getTaxRateId());

        /**
         * 销项税
         */
        otherOutDetailPageVO.setTaxRate(otherOutDetail.getTaxRate());

        /**
         * 不含税单价
         */
        otherOutDetailPageVO.setNotaxPrice(otherOutDetail.getNotaxPrice());

        /**
         * 不含税金额
         */
        otherOutDetailPageVO.setNotaxAmount(otherOutDetail.getNotaxAmount());

        /**
         * 税额
         */
        otherOutDetailPageVO.setTaxAmount(otherOutDetail.getTaxAmount());

        /**
         * 状态
         */
        otherOutDetailPageVO.setStatus(otherOutDetail.getStatus());

        /**
         * 备注
         */
        otherOutDetailPageVO.setRemark(otherOutDetail.getRemark());

        otherOutDetailPageVO.setLineNum(otherOutDetail.getLineNum());


        return otherOutDetailPageVO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
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

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public BigDecimal getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(BigDecimal notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
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

    public List<OtherOutShelfPageVO> getOtherOutShelfFormVOS() {
        return otherOutShelfFormVOS;
    }

    public void setOtherOutShelfFormVOS(List<OtherOutShelfPageVO> otherOutShelfFormVOS) {
        this.otherOutShelfFormVOS = otherOutShelfFormVOS;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}