package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/27 <br/>
 * 描述：拆零设置单据列表页数据
 */
public class SplitSetPageVO implements Serializable {
    private static final long serialVersionUID = 8064001930935537290L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value="拆零主键id")
    private Long id;

    @ApiModelProperty(value="上级企业ID")
    private Long parentId;

    /**
     * 原普通商品ID
     */
    @ApiModelProperty(value="原普通商品ID")
    private Long goodsId;

    /**
     * 原普通商品编码
     */
    @ApiModelProperty(value="原普通商品编码")
    private String goodsCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value="通用名称")
    private String genericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称")
    private String name;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value="剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value="规格")
    private String specification;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="库存计量单位名称")
    private String unitName;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value=" 生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value="生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value="产地")
    private String place;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 批准文号
     */
    @ApiModelProperty(value="批准文号")
    private String approvalNumber;

    /**
     * 零售价格
     */
    @ApiModelProperty(value="零售价格")
    private BigDecimal retailPrice;

    /**
     * 会员价格
     */
    @ApiModelProperty(value="会员价格")
    private BigDecimal memberPrice;

    /**
     * 条形码
     */
    @ApiModelProperty(value=" 条形码")
    private String barcode;

    /**
     * 拆零商品id
     */
    @ApiModelProperty(value="拆零商品id")
    private Long splitGoodsId;

    /**
     * 拆零商品编码
     */
    @ApiModelProperty(value="拆零商品编码")
    private String splitGoodsCode;

    /**
     * 拆零规格
     */
    @ApiModelProperty(value="拆零规格")
    private String splitSpecification;

    /**
     * 拆零单位ID
     */
    @ApiModelProperty(value="拆零单位ID")
    private String splitUnitId;

    /**
     * 拆零单位名称
     */
    @ApiModelProperty(value="拆零单位名称")
    private String splitUnitName;

    /**
     * 拆零货位id
     */
    @ApiModelProperty(value="拆零货位id")
    private Long splitShelfId;

    /**
     * 拆零货位名称
     */
    @ApiModelProperty(value="拆零货位名称")
    private String splitShelfName;

    /**
     * 拆零数量
     */
    @ApiModelProperty(value="拆零数量")
    private String splitQuantity;

    /**
     * 拆零零售价格
     */
    @ApiModelProperty(value="拆零零售价格")
    private BigDecimal splitRetailPrice;

    /**
     * 拆零会员价格
     */
    @ApiModelProperty(value="拆零会员价格")
    private BigDecimal splitMemberPrice;

    @ApiModelProperty(value="启用状态--商品信息表中的启用状态")
    private Integer status;

    @ApiModelProperty(value="启用状态描述--商品信息表中的启用状态")
    private String statusDes;

    @ApiModelProperty(value="修改标志")
    private Boolean updateFlag = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Long getSplitGoodsId() {
        return splitGoodsId;
    }

    public void setSplitGoodsId(Long splitGoodsId) {
        this.splitGoodsId = splitGoodsId;
    }

    public String getSplitGoodsCode() {
        return splitGoodsCode;
    }

    public void setSplitGoodsCode(String splitGoodsCode) {
        this.splitGoodsCode = splitGoodsCode;
    }

    public String getSplitSpecification() {
        return splitSpecification;
    }

    public void setSplitSpecification(String splitSpecification) {
        this.splitSpecification = splitSpecification;
    }

    public String getSplitUnitId() {
        return splitUnitId;
    }

    public void setSplitUnitId(String splitUnitId) {
        this.splitUnitId = splitUnitId;
    }

    public String getSplitUnitName() {
        return splitUnitName;
    }

    public void setSplitUnitName(String splitUnitName) {
        this.splitUnitName = splitUnitName;
    }

    public Long getSplitShelfId() {
        return splitShelfId;
    }

    public void setSplitShelfId(Long splitShelfId) {
        this.splitShelfId = splitShelfId;
    }

    public String getSplitShelfName() {
        return splitShelfName;
    }

    public void setSplitShelfName(String splitShelfName) {
        this.splitShelfName = splitShelfName;
    }

    public String getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(String splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    public BigDecimal getSplitRetailPrice() {
        return splitRetailPrice;
    }

    public void setSplitRetailPrice(BigDecimal splitRetailPrice) {
        this.splitRetailPrice = splitRetailPrice;
    }

    public BigDecimal getSplitMemberPrice() {
        return splitMemberPrice;
    }

    public void setSplitMemberPrice(BigDecimal splitMemberPrice) {
        this.splitMemberPrice = splitMemberPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SplitSetPageVO{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", parentId=" + parentId +
                ", goodsCode='" + goodsCode + '\'' +
                ", genericName='" + genericName + '\'' +
                ", name='" + name + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", place='" + place + '\'' +
                ", shelfName='" + shelfName + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", retailPrice=" + retailPrice +
                ", memberPrice=" + memberPrice +
                ", barcode='" + barcode + '\'' +
                ", splitGoodsId=" + splitGoodsId +
                ", splitGoodsCode='" + splitGoodsCode + '\'' +
                ", splitSpecification='" + splitSpecification + '\'' +
                ", splitUnitId='" + splitUnitId + '\'' +
                ", splitUnitName='" + splitUnitName + '\'' +
                ", splitShelfId='" + splitShelfId + '\'' +
                ", splitShelfName='" + splitShelfName + '\'' +
                ", splitQuantity='" + splitQuantity + '\'' +
                ", splitRetailPrice=" + splitRetailPrice +
                ", splitMemberPrice=" + splitMemberPrice +
                ", status=" + status +
                ", updateFlag=" + updateFlag +
                '}';
    }
}
