package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import com.rograndec.feijiayun.chain.business.online.purchase.constant.OperationType;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SmartPurchasingPlan;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PurchasePlanGoodsVO implements Serializable {

    @ApiModelProperty(value = "商品ID")
    private Long id;

    @ApiModelProperty(value = "商品编码")
    private String code;

    @ApiModelProperty(value = "通用名称")
    private String genericName;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    @ApiModelProperty(value = "规格")
    private String specification;

    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    @ApiModelProperty(value = "生产厂商名称")
    private String manufacturer;

    @ApiModelProperty(value="产地")
    private String place;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

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
     * 供货单位ID
     */
    @ApiModelProperty(value = "默认供货单位ID")
    private Long supplierId;

    /**
     * 供货位单位编码
     */
    @ApiModelProperty(value = "默认供货位单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "默认供货单位名称")
    private String supplierName;


    @ApiModelProperty(value = "对应供货单位下拉列表")
    private List<SupplierVO> supplierVOList;


    @ApiModelProperty(value = "经营范围ID(后台用)")
    private Long managementScopeId;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity;
    
    @ApiModelProperty(value = "结算方式（0-购销；1-实销实结）")
    private Integer managementMode;


    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @ApiModelProperty(value = "可用库存，后台用")
    private BigDecimal usableQuantity;

    @ApiModelProperty(value = "需要的数量，后台用")
    private BigDecimal needQuantity;

    public BigDecimal getUsableQuantity() {
        return usableQuantity == null?BigDecimal.ZERO:usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }


    public BigDecimal getNeedQuantity() {
        return needQuantity;
    }

    public void setNeedQuantity(BigDecimal needQuantity) {
        this.needQuantity = needQuantity;
    }

    public Long getManagementScopeId() {
        return managementScopeId;
    }

    public void setManagementScopeId(Long managementScopeId) {
        this.managementScopeId = managementScopeId;
    }

    public List<SupplierVO> getSupplierVOList() {
        return supplierVOList;
    }

    public void setSupplierVOList(List<SupplierVO> supplierVOList) {
        this.supplierVOList = supplierVOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public Integer getManagementMode() {
		return managementMode;
	}

	public void setManagementMode(Integer managementMode) {
		this.managementMode = managementMode;
	}

	@Override
    public String toString() {
        return "PurchasePlanGoodsVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", genericName='" + genericName + '\'' +
                ", name='" + name + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", place='" + place + '\'' +
                ", unitPrice=" + unitPrice +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierVOList=" + supplierVOList +
                ", managementScopeId=" + managementScopeId +
                ", quantity=" + quantity +
                '}';
    }

    public static SmartPurchasingPlan convertTOEntity(PurchasePlanGoodsVO vo, UserVO userVO) throws Exception{
        SmartPurchasingPlan plan = new SmartPurchasingPlan();
        plan.setGoodsId(vo.getId());
        plan.setGoodsCode(vo.getCode());
        plan.setGoodsName(vo.getName());
        plan.setGoodsGenericName(vo.getGenericName());
        plan.setManufacturerId(vo.getManufacturerId());
        plan.setManufacturer(vo.getManufacturer());
        plan.setInventoryQuantity(vo.getStockQuantity());
        plan.setLackQuantity(vo.getQuantity());
        plan.setPurchaseQuantity(vo.getQuantity());
        plan.setType(OperationType.SYSTEM_ADD.getType());
        UserEnterpriseUtils.setUserCreateOrModify(plan,userVO,true);
        plan.setEnterpriseId(userVO.getEnterpriseId());
        return plan;
    }
}
