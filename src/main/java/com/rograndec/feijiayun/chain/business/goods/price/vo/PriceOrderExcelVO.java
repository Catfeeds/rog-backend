package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.utils.ExcelMethodUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by zhaiwei on 2018/1/26.
 */
public class PriceOrderExcelVO implements Serializable {

    @ApiModelProperty(value="价格清单明细id")
    private Long id;

    @ApiModelProperty(value="商品id")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value="商品编码")
    private String goodsCode;

    /**
     * 商品编码
     */
    @ApiModelProperty(value="原商品编码")
    private String goodsOldCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value="通用名称")
    private String goodsGenericName;

    /**
     * 规格
     */
    @ApiModelProperty(value="规格")
    private String goodsSpecification;


    /**
     * 生产厂商
     */
    @ApiModelProperty(value="生产厂商")
    private String manufacturer;


    /**
     * 零售基价
     */
    @ApiModelProperty(value="零售基价")
    private String retailPrice;

    /**
     * 配货价格
     */
    @ApiModelProperty(value="配货价格")
    private String distrPrice;

    @ApiModelProperty(value="会员单价")
    private String memberPrice;//会员单价*

    @ApiModelProperty(value = "配货税率Id")
    private Long distrTaxRateId;

    @ApiModelProperty(value="配货税率")
    private String distrTaxRate;//税率*

    /**
     * 销项税
     */
    @ApiModelProperty(value="销项税税率")
    private String saleTaxRate;

    @ApiModelProperty(value = "销项税税率Id")
    private Long saleTaxRateId;

    @ApiModelProperty(value = "剂型")
    private String dosageName;

    @ApiModelProperty(value = "单位")
    private String unitId;

    private String lineNum;//序号

    public static List<String> getGoodsCode(List<PriceOrderExcelVO> priceOrderExcelVOS){

        if(CollectionUtils.isEmpty(priceOrderExcelVOS)) return Collections.emptyList();

        Set<String> collect = priceOrderExcelVOS.stream()
                .filter(updatePriceOrderDetailVO -> !StringUtils.isEmpty(updatePriceOrderDetailVO.getGoodsCode()))
                .map(updatePriceOrderDetailVO -> updatePriceOrderDetailVO.getGoodsCode()).collect(Collectors.toSet());

        List<String> list = collect.stream().collect(Collectors.toList());
        return list;
    }

    public static List<String> getGoodsOldCode(List<PriceOrderExcelVO> priceOrderExcelVOS){

        if(CollectionUtils.isEmpty(priceOrderExcelVOS)) return Collections.emptyList();

        Set<String> collect = priceOrderExcelVOS.stream()
                .filter(updatePriceOrderDetailVO -> !StringUtils.isEmpty(updatePriceOrderDetailVO.getGoodsOldCode()))
                .map(updatePriceOrderDetailVO -> updatePriceOrderDetailVO.getGoodsOldCode()).collect(Collectors.toSet());

        List<String> list = collect.stream().collect(Collectors.toList());
        return list;
    }

    public static List<BigDecimal> getGoodsTaxRates(List<PriceOrderExcelVO> priceOrderExcelVOS){

        if(CollectionUtils.isEmpty(priceOrderExcelVOS)) return Collections.emptyList();

        Set<BigDecimal> saleTaxRates = priceOrderExcelVOS.stream()
                .filter(updatePriceOrderDetailVO -> !StringUtils.isEmpty(updatePriceOrderDetailVO.getSaleTaxRate()))
                .filter(updatePriceOrderDetailVO -> ExcelMethodUtils.isDecimals(updatePriceOrderDetailVO.getSaleTaxRate()))
                .map(updatePriceOrderDetailVO ->  new BigDecimal(updatePriceOrderDetailVO.getSaleTaxRate()).setScale(4,BigDecimal.ROUND_HALF_UP)).collect(Collectors.toSet());
        Set<BigDecimal> distrTaxRates = priceOrderExcelVOS.stream()
                .filter(updatePriceOrderDetailVO -> !StringUtils.isEmpty(updatePriceOrderDetailVO.getDistrTaxRate()))
                .filter(updatePriceOrderDetailVO -> ExcelMethodUtils.isDecimals(updatePriceOrderDetailVO.getDistrTaxRate()))
                .map(updatePriceOrderDetailVO -> new BigDecimal(updatePriceOrderDetailVO.getDistrTaxRate()).setScale(4,BigDecimal.ROUND_HALF_UP)).collect(Collectors.toSet());
        List<BigDecimal> list = new ArrayList<>();

        if(!CollectionUtils.isEmpty(saleTaxRates))
            list.addAll(saleTaxRates);

        if(!CollectionUtils.isEmpty(distrTaxRates))
            list.addAll(distrTaxRates);



        return list;
    }

    public PriceOrderExcelVO() {
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

    public String getGoodsOldCode() {
        return goodsOldCode;
    }

    public void setGoodsOldCode(String goodsOldCode) {
        this.goodsOldCode = goodsOldCode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
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

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getDistrPrice() {
        return distrPrice;
    }

    public void setDistrPrice(String distrPrice) {
        this.distrPrice = distrPrice;
    }

    public String getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getDistrTaxRate() {
        return distrTaxRate;
    }

    public void setDistrTaxRate(String distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }

    public String getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(String saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDistrTaxRateId() {
        return distrTaxRateId;
    }

    public void setDistrTaxRateId(Long distrTaxRateId) {
        this.distrTaxRateId = distrTaxRateId;
    }

    public Long getSaleTaxRateId() {
        return saleTaxRateId;
    }

    public void setSaleTaxRateId(Long saleTaxRateId) {
        this.saleTaxRateId = saleTaxRateId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }
}