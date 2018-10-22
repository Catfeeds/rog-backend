package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: GoodsLockDetailVO
 * @Description:  储存管理-商品处理-药品锁定明细
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:26:57
 */
@ApiModel(value = "GoodsLockDetailVO", description = "储存管理-商品处理-药品锁定明细")
public class GoodsLockDetailVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 企业ID
     */
	@ApiModelProperty(required = true, value = "企业ID")
	private Long enterpriseId;
	
	/**
     * 上级企业ID
     */
	@ApiModelProperty(required = true, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 单据（药品锁定单）ID
     */
	@ApiModelProperty(required = true, value = "单据（药品锁定单）ID")
	private Long lockId;
	
	/**
     * 单据（药品锁定单）类型
     */
	@ApiModelProperty(required = true, value = "单据（药品锁定单）类型")
	private Integer orderType;
	
	/**
     * 单据（药品锁定单）编码
     */
	@ApiModelProperty(required = true, value = "单据（药品锁定单）编码")
	private String lockCode;
	
	/**
     * 单据（药品锁定单）日期
     */
	@ApiModelProperty(required = true, value = "单据（药品锁定单）日期")
	private Date lockDate;
	
	/**
     * 商品ID
     */
	@ApiModelProperty(required = true, value = "商品ID")
	private Long goodsId;
	
	/**
     * 商品编码
     */
	@ApiModelProperty(required = true, value = "商品编码")
	private String goodsCode;
	
	/**
     * 条形码
     */
	@ApiModelProperty(required = false, value = "条形码")
	private String barcode;
	
	/**
     * 商品名称
     */
	@ApiModelProperty(required = true, value = "商品名称")
	private String goodsName;
	
	/**
     * 商品通用名称
     */
	@ApiModelProperty(required = false, value = "商品通用名称")
	private String goodsGenericName;
	
	/**
     * 剂型ID
     */
	@ApiModelProperty(required = false, value = "剂型ID")
	private Long dosageId;
	
	/**
     * 剂型名称
     */
	@ApiModelProperty(required = false, value = "剂型名称")
	private String dosageName;
	
	/**
     * 单位ID
     */
	@ApiModelProperty(required = false, value = "单位ID")
	private Long unitId;
	
	/**
     * 单位名称
     */
	@ApiModelProperty(required = false, value = "单位名称")
	private String unitName;
	
	/**
     * 商品规格
     */
	@ApiModelProperty(required = false, value = "商品规格")
	private String goodsSpecification;
	
	/**
     * 生产厂商ID
     */
	@ApiModelProperty(required = false, value = "生产厂商ID")
	private Long manufacturerId;
	
	/**
     * 生产厂商
     */
	@ApiModelProperty(required = false, value = "生产厂商")
	private String manufacturer;
	
	/**
     * 商品产地
     */
	@ApiModelProperty(required = false, value = "商品产地")
	private String goodsPlace;
	
	/**
     * 批准文号
     */
	@ApiModelProperty(required = false, value = "批准文号")
	private String approvalNumber;
	
	/**
     * 数量
     */
	@ApiModelProperty(required = true, value = "数量")
	private BigDecimal quantity;
	
	/**
     * 行号
     */
	@ApiModelProperty(required = true, value = "行号")
	private Integer lineNum;
	
	/**
     * 状态
     */
	@ApiModelProperty(required = true, value = "状态")
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;
	

	/**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 企业ID
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	/**
	 * 企业ID
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	/**
	 * 上级企业ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 上级企业ID
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 单据（药品锁定单）ID
	 */
	public void setLockId(Long lockId) {
		this.lockId = lockId;
	}
	
	/**
	 * 单据（药品锁定单）ID
	 */
	public Long getLockId() {
		return lockId;
	}
	
	/**
	 * 单据（药品锁定单）类型
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据（药品锁定单）类型
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 单据（药品锁定单）编码
	 */
	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}
	
	/**
	 * 单据（药品锁定单）编码
	 */
	public String getLockCode() {
		return lockCode;
	}
	
	/**
	 * 单据（药品锁定单）日期
	 */
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	
	/**
	 * 单据（药品锁定单）日期
	 */
	public Date getLockDate() {
		return lockDate;
	}
	
	/**
	 * 商品ID
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	/**
	 * 商品ID
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * 商品编码
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	/**
	 * 商品编码
	 */
	public String getGoodsCode() {
		return goodsCode;
	}
	
	/**
	 * 条形码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	/**
	 * 条形码
	 */
	public String getBarcode() {
		return barcode;
	}
	
	/**
	 * 商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	/**
	 * 商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * 商品通用名称
	 */
	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}
	
	/**
	 * 商品通用名称
	 */
	public String getGoodsGenericName() {
		return goodsGenericName;
	}
	
	/**
	 * 剂型ID
	 */
	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}
	
	/**
	 * 剂型ID
	 */
	public Long getDosageId() {
		return dosageId;
	}
	
	/**
	 * 剂型名称
	 */
	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}
	
	/**
	 * 剂型名称
	 */
	public String getDosageName() {
		return dosageName;
	}
	
	/**
	 * 单位ID
	 */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	
	/**
	 * 单位ID
	 */
	public Long getUnitId() {
		return unitId;
	}
	
	/**
	 * 单位名称
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	/**
	 * 单位名称
	 */
	public String getUnitName() {
		return unitName;
	}
	
	/**
	 * 商品规格
	 */
	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}
	
	/**
	 * 商品规格
	 */
	public String getGoodsSpecification() {
		return goodsSpecification;
	}
	
	/**
	 * 生产厂商ID
	 */
	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	
	/**
	 * 生产厂商ID
	 */
	public Long getManufacturerId() {
		return manufacturerId;
	}
	
	/**
	 * 生产厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * 生产厂商
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	
	/**
	 * 商品产地
	 */
	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}
	
	/**
	 * 商品产地
	 */
	public String getGoodsPlace() {
		return goodsPlace;
	}
	
	/**
	 * 批准文号
	 */
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	
	/**
	 * 批准文号
	 */
	public String getApprovalNumber() {
		return approvalNumber;
	}
	
	/**
	 * 数量
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 数量
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	/**
	 * 行号
	 */
	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	
	/**
	 * 行号
	 */
	public Integer getLineNum() {
		return lineNum;
	}
	
	/**
	 * 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 状态
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}
	

}