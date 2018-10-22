package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MemberIntegralRecordVO   
 * @Description: 会员报表-积分兑换
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月16日 下午2:01:44
 */
@ApiModel(value = "MemberIntegralExchange", description = "积分兑换报表列表")
public class MemberIntegralExchangeVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月16日 下午3:46:38 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "会员卡号")
	private String cardCode;
	@ApiModelProperty(value = "会员姓名")
	private String memberName;
	@ApiModelProperty(value = "手机")
	private String mobilePhone;
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "日期")
	private Date saleDate;
	@ApiModelProperty(value = "单号")
	private String saleCode;
	@ApiModelProperty(value = "门店编码")
	private String enterpriseCode;
	@ApiModelProperty(value = "门店名称")
	private String enterpriseName;
	
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
    
    @ApiModelProperty(value = "兑换积分")
    private BigDecimal integralExchange;
    
    @ApiModelProperty(value = "兑换数量")
    private BigDecimal exchangeQuantity;
    
    @ApiModelProperty(value = "扣除积分")
    private BigDecimal deductIntegral;

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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

	public BigDecimal getIntegralExchange() {
		return integralExchange;
	}

	public void setIntegralExchange(BigDecimal integralExchange) {
		this.integralExchange = integralExchange;
	}

	public BigDecimal getExchangeQuantity() {
		return exchangeQuantity;
	}

	public void setExchangeQuantity(BigDecimal exchangeQuantity) {
		this.exchangeQuantity = exchangeQuantity;
	}

	public BigDecimal getDeductIntegral() {
		return deductIntegral;
	}

	public void setDeductIntegral(BigDecimal deductIntegral) {
		this.deductIntegral = deductIntegral;
	}
    
    

}
