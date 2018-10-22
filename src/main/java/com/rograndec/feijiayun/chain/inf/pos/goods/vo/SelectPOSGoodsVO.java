package com.rograndec.feijiayun.chain.inf.pos.goods.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SelectPOSGoodsVO", description = "POS选择商品")
public class SelectPOSGoodsVO implements Serializable {
    /**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年9月25日 下午8:04:43 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

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
    
    @ApiModelProperty(value = "条形码")
    private String barcode;
    
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
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;
    

    @ApiModelProperty(value="零售单价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value="会员单价")
    private BigDecimal memberPrice;
    
    @ApiModelProperty(value = "税率")
	private String goodsTaxRate;
    @ApiModelProperty(value = "税率ID")
    private Long goodsTaxRateId;
    
    @ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private int businessVariety;
    
    @ApiModelProperty(value = "商品属性\r\n" + 
    		"（1）品种类别为0-药品：[0-成药；1-中药材；2-中药饮片]\r\n" + 
    		"（2）品种类别为1-医疗器械：［0-一类医疗器械；1-二类医疗器械；2-三类医疗器械］\r\n" + 
    		"（3）品种类别为2-食品：［0-食品；1-保健食品；2-婴幼儿配方食品；3-特殊医学用途配方食品］\r\n" + 
    		"（4）品种类别为3-化妆品：［0-非特殊用途化妆品；1-特殊用途化妆品］\r\n" + 
    		"（5）品种类别为\r\n" + 
    		"4-其它［0-赠品；1-服务］\r\n" + 
    		"")
    private int goodsAttribute;
    
    @ApiModelProperty(value = "品种类别为0-药品，商品属性为0-成药时，prescription_drug含义为是否为处方药［0：非处方药，1：处方药］")
    private int prescriptionDrug;
    
    @ApiModelProperty(value = "品种类别为0-药品，商品属性为0-成药，并且为非处方药时，otc_type\r\n" + 
    		"表示非处方药类别［0-甲类；1-乙类］\r\n" )
    private int otcType;
    
    @ApiModelProperty(value ="是否为医保药品（0-否；1-是）")
    private int medicalInsurance;
    
    @ApiModelProperty(value ="首营品种（0-否；1-是）")
    private int first;
    
    @ApiModelProperty(value ="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private int specialDrug;
    
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private int inChargeDrug;
    
    @ApiModelProperty(value="限购数量")
    private int limitQuantity;

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

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
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


	public String getGoodsTaxRate() {
		return goodsTaxRate;
	}

	public void setGoodsTaxRate(String goodsTaxRate) {
		this.goodsTaxRate = goodsTaxRate;
	}

	public Long getGoodsTaxRateId() {
		return goodsTaxRateId;
	}

	public void setGoodsTaxRateId(Long goodsTaxRateId) {
		this.goodsTaxRateId = goodsTaxRateId;
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

	public int getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(int businessVariety) {
		this.businessVariety = businessVariety;
	}

	public int getGoodsAttribute() {
		return goodsAttribute;
	}

	public void setGoodsAttribute(int goodsAttribute) {
		this.goodsAttribute = goodsAttribute;
	}

	public int getPrescriptionDrug() {
		return prescriptionDrug;
	}

	public void setPrescriptionDrug(int prescriptionDrug) {
		this.prescriptionDrug = prescriptionDrug;
	}

	public int getOtcType() {
		return otcType;
	}

	public void setOtcType(int otcType) {
		this.otcType = otcType;
	}

	public int getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(int medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSpecialDrug() {
		return specialDrug;
	}

	public void setSpecialDrug(int specialDrug) {
		this.specialDrug = specialDrug;
	}

	public int getInChargeDrug() {
		return inChargeDrug;
	}

	public void setInChargeDrug(int inChargeDrug) {
		this.inChargeDrug = inChargeDrug;
	}

	public int getLimitQuantity() {
		return limitQuantity;
	}

	public void setLimitQuantity(int limitQuantity) {
		this.limitQuantity = limitQuantity;
	}
    
	
	
}