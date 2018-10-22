package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "GoosMPHVO", description = "标准库返回商品信息")
public class GoosMPHVO implements Serializable{
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月28日 下午4:01:52 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "标准库ID")
	private Long standardLibraryId;

	@ApiModelProperty(value = "商品名称")
    private String name;

	@ApiModelProperty(value = "商品通用名称名称")
	private String genericName;
	
	@ApiModelProperty(value="规格")
    private String specification;
	
	@ApiModelProperty(value = "剂型ID")
    private Long dosageId;
	
	@ApiModelProperty(value = "剂型名称")
    private String dosageName;
	
	@ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;
	
	@ApiModelProperty(value = "生产厂商")
    private String manufacturer;
	
	@ApiModelProperty(value = "条形码")
    private String barcode;
	
	@ApiModelProperty(value = "批准文号")
    private String approvalNumber;
	
	@ApiModelProperty(value="商品属性-是否为处方药，0：非处方药，1：处方药")
    private Integer prescriptionDrug;
	
	@ApiModelProperty(value = "贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;
	
	@ApiModelProperty(value="含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;
	

	public Long getStandardLibraryId() {
		return standardLibraryId;
	}

	public void setStandardLibraryId(Long standardLibraryId) {
		this.standardLibraryId = standardLibraryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public Integer getPrescriptionDrug() {
		return prescriptionDrug;
	}

	public void setPrescriptionDrug(Integer prescriptionDrug) {
		this.prescriptionDrug = prescriptionDrug;
	}

	public Integer getStorageTemp() {
		return storageTemp;
	}

	public void setStorageTemp(Integer storageTemp) {
		this.storageTemp = storageTemp;
	}


	public Integer getFormulationType() {
		return formulationType;
	}

	public void setFormulationType(Integer formulationType) {
		this.formulationType = formulationType;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
}
