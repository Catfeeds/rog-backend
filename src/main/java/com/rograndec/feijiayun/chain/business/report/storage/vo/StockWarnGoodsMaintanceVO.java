package com.rograndec.feijiayun.chain.business.report.storage.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class StockWarnGoodsMaintanceVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月25日 下午1:08:03 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "品种类别")
	private Integer businessVariety;
	
	@ApiModelProperty(value = "品种类别名称")
	private String businessVarietyName;
	
	@ApiModelProperty(value = "门店编码")
	private String storeCode;
	
	@ApiModelProperty(value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(value = "商品编码")
    private String code;
	
	@ApiModelProperty(value = "通用名称")
    private String genericName;
	
	@ApiModelProperty(value = "剂型")
	private String dosageName;

	@ApiModelProperty(value = "规格")
    private String specification;
    
	@ApiModelProperty(value = "生产厂商")
    private String manufacturer;
    
	@ApiModelProperty(value = "单位")
    private String unitName;
	
	@ApiModelProperty(value = "产地")
    private String place;
	
	@ApiModelProperty(value = "批号")
	private String lotNum;
	
	@ApiModelProperty(value = "生产日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date productDate;
	
	@ApiModelProperty(value = "有效日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date validUntil;
	
	@ApiModelProperty(value = "货位ID")
	private Long shelfId;
	
	@ApiModelProperty(value = "货位")
	private String shelfName;
	
	@ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity = BigDecimal.ZERO;
    
	@ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "养护类型")
	private Integer maintanceType;
	
	@ApiModelProperty(value = "养护类型/检查类型")
	private String maintanceTypeName;
	
	@ApiModelProperty(value = "养护周期/检查周期")
	private Integer maintanceCycle;
	
	@ApiModelProperty(value = "上次养护日期/上次检查日期")
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date lastMaintanceDate;
	
	@ApiModelProperty(value = "计划养护日期/计划检查日期")
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date planMaintanceDate;
	
	@ApiModelProperty(value = "近效期周期")
	private Integer nearPeriodCycle;

	/**
	 * 预警信息
	 */
	@ApiModelProperty(value = "预警天数")
	private String warningDay;
	
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public BigDecimal getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}

	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}

	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
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

	public Integer getMaintanceType() {
		return maintanceType;
	}

	public void setMaintanceType(Integer maintanceType) {
		this.maintanceType = maintanceType;
	}

	public String getMaintanceTypeName() {
		return maintanceTypeName;
	}

	public void setMaintanceTypeName(String maintanceTypeName) {
		this.maintanceTypeName = maintanceTypeName;
	}

	public Integer getMaintanceCycle() {
		return maintanceCycle;
	}

	public void setMaintanceCycle(Integer maintanceCycle) {
		this.maintanceCycle = maintanceCycle;
	}

	public Date getLastMaintanceDate() {
		return lastMaintanceDate;
	}

	public void setLastMaintanceDate(Date lastMaintanceDate) {
		this.lastMaintanceDate = lastMaintanceDate;
	}

	public Date getPlanMaintanceDate() {
		return planMaintanceDate;
	}

	public void setPlanMaintanceDate(Date planMaintanceDate) {
		this.planMaintanceDate = planMaintanceDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getNearPeriodCycle() {
		return nearPeriodCycle;
	}

	public void setNearPeriodCycle(Integer nearPeriodCycle) {
		this.nearPeriodCycle = nearPeriodCycle;
	}

	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getBusinessVarietyName() {
		return businessVarietyName;
	}

	public void setBusinessVarietyName(String businessVarietyName) {
		this.businessVarietyName = businessVarietyName;
	}

	public String getWarningDay() {
		Instant planMaintanceInstant = Instant.ofEpochMilli(planMaintanceDate.getTime());
		Instant now = Instant.now();
		Duration time = Duration.between(now, planMaintanceInstant);
		if(time.toMillis() > 0 && time.toMillis() < (1000 * 60 * 60 * 24)){
			return "1";
		}
		if(time.toMillis() < 0 && time.toMillis() > (1- (1000 * 60 * 60 * 24))){
			return "1";
		}

		if(time.toDays() < 0){
			return "过期"+Math.abs(time.toDays());
		}
		return String.valueOf(time.toDays());
	}

	public void setWarningDay(String warningDay) {
		this.warningDay = warningDay;
	}
}
