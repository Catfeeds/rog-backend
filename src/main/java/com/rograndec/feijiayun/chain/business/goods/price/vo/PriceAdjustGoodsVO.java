package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjustDetail;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@ApiModel
public class PriceAdjustGoodsVO implements Serializable {
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    /**
     * 标准库ID
     */
    @ApiModelProperty(value = "商品标准库id")
    private Long standardLibraryId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String code;
    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称")
    private String genericName;

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
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

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
     * 库存计量单位ID
     */
    @ApiModelProperty(value = "库存计量单位ID")
    private Long unitId;
    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value = "库存计量单位名称想")
    private String unitName;


    /**
     * 价格清单id
     */
    @ApiModelProperty(value = "价格清单id")
    private Long priceOrderId;

    @ApiModelProperty(value = "价格清单明细id")
    private Long orderDetailId;
    /**
     * 配货价格
     */
    @ApiModelProperty(value = "配货价格")
    private BigDecimal distrPrice;


    /**
     * 原配货价格
     */
    @ApiModelProperty(value = "原配货价格")
    private BigDecimal oldDistrPrice;
    /**
     * 配货税率
     */
    @ApiModelProperty(value = "配货税率")
    private BigDecimal distrTaxRate;

    @ApiModelProperty(value = "配货税率Id")
    private Long distrTaxRateId;

    /**
     * 原配货税率
     */
    @ApiModelProperty(value = "原配货税率")
    private BigDecimal oldDistrTaxRate;

    @ApiModelProperty(value = "原配货税率Id")
    private Long oldDistrTaxRateId;

    /**
     * 不含税配货单价
     */
    @ApiModelProperty(value = "不含税配货单价")
    private BigDecimal notaxDistrPrice;

    /**
     * 原不含税配货单价
     */
    @ApiModelProperty(value = "原不含税配货单价")
    private BigDecimal oldNotaxDistrPrice;

    /**
     * 零售单价
     */
    @ApiModelProperty(value = "零售单价")
    private BigDecimal retailPrice;

    /**
     * 原零售单价
     */
    @ApiModelProperty(value = "原零售单价")
    private BigDecimal oldRetailPrice;

    /**
     * 会员单价
     */
    @ApiModelProperty(value = "会员单价")
    private BigDecimal memberPrice;

    /**
     * 原会员单价
     */
    @ApiModelProperty(value = "原会员单价")
    private BigDecimal oldMemberPrice;

    /**
     * 销项税
     */
    @ApiModelProperty(value = "销项税")
    private BigDecimal saleTaxRate;
    @ApiModelProperty(value = "销项税Id")
    private Long saleTaxRateId;

    /**
     * 原销项税
     */
    @ApiModelProperty(value = "原销项税")
    private BigDecimal oldSaleTaxRate;
    @ApiModelProperty(value = "原销项税Id")
    private Long oldSaleTaxRateId;
    /**
     * 不含税零售单价
     */
    @ApiModelProperty(value = "不含税零售单价")
    private BigDecimal notaxRetailPrice;

    /**
     * 原不含税零售单价
     */
    @ApiModelProperty(value = "原不含税零售单价")
    private BigDecimal oldNotaxRetailPrice;

    /**
     * 不含税会员单价
     */
    @ApiModelProperty(value = "不含税会员单价")
    private BigDecimal notaxMemberPrice;

    /**
     * 原不含税会员单价
     */
    @ApiModelProperty(value = "原不含税会员单价")
    private BigDecimal oldNotaxMemberPrice;

    public static List<PriceAdjustGoodsVO> getPriceAdjustGoods4GoodsAndPriceOrderDetails(List<Goods> goods
    , List<PriceOrderDetail> priceOrderDetails){

        List<PriceAdjustGoodsVO> list = new ArrayList<>();
        for(Goods g : goods){
            for(PriceOrderDetail priceOrderDetail : priceOrderDetails){
                if(g.getId().equals(priceOrderDetail.getGoodsId())){
                    PriceAdjustGoodsVO priceAdjustGoodsVO = getPriceAdjustGoods4GoodsAndPriceOrderDetail(g, priceOrderDetail);
                    list.add(priceAdjustGoodsVO);
                }
            }
        }
        return list;
    }

    public static PriceAdjustGoodsVO getPriceAdjustGoods4GoodsAndPriceOrderDetail(Goods goods, PriceOrderDetail priceOrderDetail){

        PriceAdjustGoodsVO priceAdjustGoodsVO = new PriceAdjustGoodsVO();
        priceAdjustGoodsVO.setGoodsId(goods.getId());
        priceAdjustGoodsVO.setStandardLibraryId(goods.getStandardLibraryId());
        priceAdjustGoodsVO.setCode(goods.getCode());
        priceAdjustGoodsVO.setGenericName(goods.getGenericName());
        priceAdjustGoodsVO.setDosageId(goods.getDosageId());
        priceAdjustGoodsVO.setDosageName(goods.getDosageName());
        priceAdjustGoodsVO.setSpecification(goods.getSpecification());
        priceAdjustGoodsVO.setManufacturerId(goods.getManufacturerId());
        priceAdjustGoodsVO.setManufacturer(goods.getManufacturer());
        priceAdjustGoodsVO.setUnitId(goods.getUnitId());
        priceAdjustGoodsVO.setUnitName(goods.getUnitName());
        priceAdjustGoodsVO.setPriceOrderId(priceOrderDetail.getPriceOrderId());
        priceAdjustGoodsVO.setOrderDetailId(priceOrderDetail.getId());
        priceAdjustGoodsVO.setDistrPrice(priceOrderDetail.getDistrPrice());
        priceAdjustGoodsVO.setDistrTaxRate(priceOrderDetail.getDistrTaxRate());
        priceAdjustGoodsVO.setDistrTaxRateId(priceOrderDetail.getDistrTaxRateId());
        priceAdjustGoodsVO.setNotaxDistrPrice(priceOrderDetail.getNotaxDistrPrice());
        priceAdjustGoodsVO.setRetailPrice(priceOrderDetail.getRetailPrice());
        priceAdjustGoodsVO.setMemberPrice(priceOrderDetail.getMemberPrice());
        priceAdjustGoodsVO.setSaleTaxRate(priceOrderDetail.getSaleTaxRate());
        priceAdjustGoodsVO.setSaleTaxRateId(priceOrderDetail.getSaleTaxRateId());
        priceAdjustGoodsVO.setNotaxRetailPrice(priceOrderDetail.getNotaxRetailPrice());
        priceAdjustGoodsVO.setNotaxMemberPrice(priceOrderDetail.getNotaxMemberPrice());

        return priceAdjustGoodsVO;
    }

    public static List<PriceAdjustGoodsVO> getPriceAdjustGoods4GoodsAndAdjustDetails(List<Goods> goods
            , List<PriceAdjustDetail> priceAdjustDetails){

        List<PriceAdjustGoodsVO> list = new ArrayList<>();
        for(Goods g : goods){
            for(PriceAdjustDetail priceAdjustDetail : priceAdjustDetails){
                if(g.getId().equals(priceAdjustDetail.getGoodsId())){
                    PriceAdjustGoodsVO priceAdjustGoodsVO = getPriceAdjustGoods4GoodsAndAdjustDetail(g, priceAdjustDetail);
                    list.add(priceAdjustGoodsVO);
                }
            }
        }
        return list;
    }

    public static PriceAdjustGoodsVO getPriceAdjustGoods4GoodsAndAdjustDetail(Goods goods, PriceAdjustDetail priceAdjustDetail){

        PriceAdjustGoodsVO priceAdjustGoodsVO = new PriceAdjustGoodsVO();
        priceAdjustGoodsVO.setGoodsId(goods.getId());
        priceAdjustGoodsVO.setStandardLibraryId(goods.getStandardLibraryId());
        priceAdjustGoodsVO.setCode(goods.getCode());
        priceAdjustGoodsVO.setGenericName(goods.getGenericName());
        priceAdjustGoodsVO.setDosageId(goods.getDosageId());
        priceAdjustGoodsVO.setDosageName(goods.getDosageName());
        priceAdjustGoodsVO.setSpecification(goods.getSpecification());
        priceAdjustGoodsVO.setManufacturerId(goods.getManufacturerId());
        priceAdjustGoodsVO.setManufacturer(goods.getManufacturer());
        priceAdjustGoodsVO.setUnitId(goods.getUnitId());
        priceAdjustGoodsVO.setUnitName(goods.getUnitName());
        priceAdjustGoodsVO.setOrderDetailId(priceAdjustDetail.getId());
        priceAdjustGoodsVO.setDistrPrice(priceAdjustDetail.getDistrPrice());
        priceAdjustGoodsVO.setDistrTaxRate(priceAdjustDetail.getDistrTaxRate());
        priceAdjustGoodsVO.setDistrTaxRateId(priceAdjustDetail.getDistrTaxRateId());
        priceAdjustGoodsVO.setNotaxDistrPrice(priceAdjustDetail.getNotaxDistrPrice());
        priceAdjustGoodsVO.setRetailPrice(priceAdjustDetail.getRetailPrice());
        priceAdjustGoodsVO.setMemberPrice(priceAdjustDetail.getMemberPrice());
        priceAdjustGoodsVO.setSaleTaxRate(priceAdjustDetail.getSaleTaxRate());
        priceAdjustGoodsVO.setSaleTaxRateId(priceAdjustDetail.getSaleTaxRateId());
        priceAdjustGoodsVO.setNotaxRetailPrice(priceAdjustDetail.getNotaxRetailPrice());
        priceAdjustGoodsVO.setNotaxMemberPrice(priceAdjustDetail.getNotaxMemberPrice());

        priceAdjustGoodsVO.setOldDistrPrice(priceAdjustDetail.getOldDistrPrice());
        priceAdjustGoodsVO.setOldDistrTaxRate(priceAdjustDetail.getOldDistrTaxRate());
        priceAdjustGoodsVO.setOldDistrTaxRateId(priceAdjustDetail.getOldDistrTaxRateId());
        priceAdjustGoodsVO.setOldNotaxDistrPrice(priceAdjustDetail.getOldNotaxDistrPrice());
        priceAdjustGoodsVO.setOldRetailPrice(priceAdjustDetail.getOldRetailPrice());
        priceAdjustGoodsVO.setOldMemberPrice(priceAdjustDetail.getOldMemberPrice());
        priceAdjustGoodsVO.setOldSaleTaxRate(priceAdjustDetail.getOldSaleTaxRate());
        priceAdjustGoodsVO.setOldSaleTaxRateId(priceAdjustDetail.getOldSaleTaxRateId());
        priceAdjustGoodsVO.setOldNotaxRetailPrice(priceAdjustDetail.getOldNotaxRetailPrice());
        priceAdjustGoodsVO.setOldNotaxMemberPrice(priceAdjustDetail.getOldNotaxMemberPrice());

        return priceAdjustGoodsVO;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getStandardLibraryId() {
        return standardLibraryId;
    }

    public void setStandardLibraryId(Long standardLibraryId) {
        this.standardLibraryId = standardLibraryId;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public BigDecimal getDistrPrice() {
        return distrPrice;
    }

    public void setDistrPrice(BigDecimal distrPrice) {
        this.distrPrice = distrPrice;
    }

    public BigDecimal getDistrTaxRate() {
        return distrTaxRate;
    }

    public void setDistrTaxRate(BigDecimal distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }

    public BigDecimal getNotaxDistrPrice() {
        return notaxDistrPrice;
    }

    public void setNotaxDistrPrice(BigDecimal notaxDistrPrice) {
        this.notaxDistrPrice = notaxDistrPrice;
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

    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public BigDecimal getNotaxRetailPrice() {
        return notaxRetailPrice;
    }

    public void setNotaxRetailPrice(BigDecimal notaxRetailPrice) {
        this.notaxRetailPrice = notaxRetailPrice;
    }

    public BigDecimal getNotaxMemberPrice() {
        return notaxMemberPrice;
    }

    public void setNotaxMemberPrice(BigDecimal notaxMemberPrice) {
        this.notaxMemberPrice = notaxMemberPrice;
    }

    public Long getPriceOrderId() {
        return priceOrderId;
    }

    public void setPriceOrderId(Long priceOrderId) {
        this.priceOrderId = priceOrderId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public BigDecimal getOldDistrPrice() {
        return oldDistrPrice;
    }

    public void setOldDistrPrice(BigDecimal oldDistrPrice) {
        this.oldDistrPrice = oldDistrPrice;
    }

    public BigDecimal getOldDistrTaxRate() {
        return oldDistrTaxRate;
    }

    public void setOldDistrTaxRate(BigDecimal oldDistrTaxRate) {
        this.oldDistrTaxRate = oldDistrTaxRate;
    }

    public BigDecimal getOldNotaxDistrPrice() {
        return oldNotaxDistrPrice;
    }

    public void setOldNotaxDistrPrice(BigDecimal oldNotaxDistrPrice) {
        this.oldNotaxDistrPrice = oldNotaxDistrPrice;
    }

    public BigDecimal getOldRetailPrice() {
        return oldRetailPrice;
    }

    public void setOldRetailPrice(BigDecimal oldRetailPrice) {
        this.oldRetailPrice = oldRetailPrice;
    }

    public BigDecimal getOldMemberPrice() {
        return oldMemberPrice;
    }

    public void setOldMemberPrice(BigDecimal oldMemberPrice) {
        this.oldMemberPrice = oldMemberPrice;
    }

    public BigDecimal getOldSaleTaxRate() {
        return oldSaleTaxRate;
    }

    public void setOldSaleTaxRate(BigDecimal oldSaleTaxRate) {
        this.oldSaleTaxRate = oldSaleTaxRate;
    }

    public BigDecimal getOldNotaxRetailPrice() {
        return oldNotaxRetailPrice;
    }

    public void setOldNotaxRetailPrice(BigDecimal oldNotaxRetailPrice) {
        this.oldNotaxRetailPrice = oldNotaxRetailPrice;
    }

    public BigDecimal getOldNotaxMemberPrice() {
        return oldNotaxMemberPrice;
    }

    public void setOldNotaxMemberPrice(BigDecimal oldNotaxMemberPrice) {
        this.oldNotaxMemberPrice = oldNotaxMemberPrice;
    }

    public Long getDistrTaxRateId() {
        return distrTaxRateId;
    }

    public void setDistrTaxRateId(Long distrTaxRateId) {
        this.distrTaxRateId = distrTaxRateId;
    }

    public Long getOldDistrTaxRateId() {
        return oldDistrTaxRateId;
    }

    public void setOldDistrTaxRateId(Long oldDistrTaxRateId) {
        this.oldDistrTaxRateId = oldDistrTaxRateId;
    }

    public Long getSaleTaxRateId() {
        return saleTaxRateId;
    }

    public void setSaleTaxRateId(Long saleTaxRateId) {
        this.saleTaxRateId = saleTaxRateId;
    }

    public Long getOldSaleTaxRateId() {
        return oldSaleTaxRateId;
    }

    public void setOldSaleTaxRateId(Long oldSaleTaxRateId) {
        this.oldSaleTaxRateId = oldSaleTaxRateId;
    }
}