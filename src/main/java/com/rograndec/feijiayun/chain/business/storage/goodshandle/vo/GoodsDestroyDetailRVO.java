package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author 孙帮祥
 * 2017-09-25
 */
public class GoodsDestroyDetailRVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 单据（药品销毁单）ID
     */
    @ApiModelProperty(value = "单据（药品销毁单）ID")
    private Long destroyId;

    /**
     * 单据（药品销毁单）类型
     */
    @ApiModelProperty(value = "单据（药品销毁单）类型")
    private Integer orderType;

    /**
     * 单据（药品销毁单）编码
     */
    @ApiModelProperty(value = "单据（药品销毁单）编码")
    private String destroyCode;

    /**
     * 单据（药品销毁单）日期
     */
    @ApiModelProperty(value = "单据（药品销毁单）日期")
    private Date destroyDate;

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
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

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
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 货位列表
     * */
    private List<GoodsDestroyShelfRVO> goodsDestroyShelfRVOList;
    @ApiModelProperty(value = "质量状况描述")
    /**
     * saas_goods_destroy_detail
     */
    private static final long serialVersionUID = 1L;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getDestroyId() {
		return destroyId;
	}


	public void setDestroyId(Long destroyId) {
		this.destroyId = destroyId;
	}


	public Integer getOrderType() {
		return orderType;
	}


	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}


	public String getDestroyCode() {
		return destroyCode;
	}


	public void setDestroyCode(String destroyCode) {
		this.destroyCode = destroyCode;
	}


	public Date getDestroyDate() {
		return destroyDate;
	}


	public void setDestroyDate(Date destroyDate) {
		this.destroyDate = destroyDate;
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


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
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


	public Integer getLineNum() {
		return lineNum;
	}


	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<GoodsDestroyShelfRVO> getGoodsDestroyShelfRVOList() {
		return goodsDestroyShelfRVOList;
	}


	public void setGoodsDestroyShelfRVOList(List<GoodsDestroyShelfRVO> goodsDestroyShelfRVOList) {
		this.goodsDestroyShelfRVOList = goodsDestroyShelfRVOList;
	}


	public String getApprovalNumber() {
		return approvalNumber;
	}


	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
    
    
}