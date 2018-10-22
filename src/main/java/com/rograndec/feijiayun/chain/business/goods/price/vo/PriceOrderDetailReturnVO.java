package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceOrderDetailReturnVO implements Serializable {
	
	/**
	 * 门店的价格清单是否可修改标识
	 */
	private boolean updateFlag=false;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 价格清单ID
     */
    private Long priceOrderId;


    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 原商品编码
     */
    private String goodsOldCode;

    /**
     * 商品通用名称
     */
    private String goodsName;

    /**
     * 剂型ID
     */
    private Long dosageId;

    /**
     * 剂型名称
     */
    private String dosageName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 库存计量单位ID
     */
    private Long unitId;

    /**
     * 库存计量单位名称
     */
    private String unitName;


    /**
     * 生产厂商ID
     */
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    private String manufacturer;
    
    /**
     * 产地
     */
    private String place;

    /**
     * 配货价格
     */
    private BigDecimal distrPrice;

    /**
     * 配货税率
     */
    private BigDecimal distrTaxRate;
    private Long distrTaxRateId;
    private String distrTaxRateStr;

    /**
     * 不含税配货单价
     */
    private BigDecimal notaxDistrPrice;

    /**
     * 零售单价
     */
    private BigDecimal retailPrice;

    /**
     * 会员单价
     */
    private BigDecimal memberPrice;

    /**
     * 销项税
     */
    private BigDecimal saleTaxRate;
    private Long saleTaxRateId;
    private String saleTaxRateStr;

    /**
     * 不含税零售单价
     */
    private BigDecimal notaxRetailPrice;

    /**
     * 不含税会员单价
     */
    private BigDecimal notaxMemberPrice;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;



    public static List<PriceOrderDetailReturnVO> getPriceOrderDetailReturnVOs4Entitys(List<Goods> goods, List<PriceOrderDetail> priceOrderDetails ,UserVO userVO){

        List<PriceOrderDetailReturnVO> priceOrderDetailReturnVOS = new ArrayList<>();

        for(Goods g :  goods){
            for(PriceOrderDetail p : priceOrderDetails){
                if(g.getId().equals(p.getGoodsId())){
                    PriceOrderDetailReturnVO returnVO = getPriceOrderDetailReturnVO4Entity(g,p, userVO);
                    priceOrderDetailReturnVOS.add(returnVO);
                    break;
                }
            }

        }

        return priceOrderDetailReturnVOS;

    }

    /**
     * 价格清单明细对象转换成vo
     * @param goods
     * @param priceOrderDetail
     * @return
     */
    public static PriceOrderDetailReturnVO getPriceOrderDetailReturnVO4Entity(Goods goods, PriceOrderDetail priceOrderDetail ,UserVO userVO){

        PriceOrderDetailReturnVO returnVO = new PriceOrderDetailReturnVO();


        returnVO.setId(priceOrderDetail.getId());
        returnVO.setPriceOrderId(priceOrderDetail.getPriceOrderId());
        returnVO.setGoodsId(priceOrderDetail.getGoodsId());
        returnVO.setGoodsCode(goods.getCode());
        returnVO.setGoodsOldCode(goods.getOldCode());
        returnVO.setGoodsName(goods.getGenericName());
        returnVO.setManufacturer(goods.getManufacturer());
        returnVO.setManufacturerId(goods.getManufacturerId());
        returnVO.setDosageId(goods.getDosageId());
        returnVO.setDosageName(goods.getDosageName());
        returnVO.setSpecification(goods.getSpecification());
        returnVO.setUnitId(goods.getUnitId());
        returnVO.setUnitName(goods.getUnitName());
        returnVO.setDistrPrice(priceOrderDetail.getDistrPrice());
        returnVO.setDistrTaxRate(priceOrderDetail.getDistrTaxRate());
        returnVO.setDistrTaxRateId(priceOrderDetail.getDistrTaxRateId());
        returnVO.setNotaxDistrPrice(priceOrderDetail.getNotaxDistrPrice());
        returnVO.setRetailPrice(priceOrderDetail.getRetailPrice());
        returnVO.setMemberPrice(priceOrderDetail.getMemberPrice());
        returnVO.setSaleTaxRate(priceOrderDetail.getSaleTaxRate());
        returnVO.setSaleTaxRateId(priceOrderDetail.getSaleTaxRateId());
        returnVO.setNotaxRetailPrice(priceOrderDetail.getNotaxRetailPrice());
        returnVO.setNotaxMemberPrice(priceOrderDetail.getNotaxMemberPrice());
        returnVO.setStatus(priceOrderDetail.getStatus());
        returnVO.setPlace(goods.getPlace());
        returnVO.setUpdateFlag(goods.getOwnerId().equals(userVO.getEnterpriseId()));//设置总部是否可编辑

        return returnVO;
    }

    /**
     * 价格清单明细对象转换成vo
     * @param goods
     * @param priceOrderDetail
     * @return
     */
    public static PriceOrderDetailReturnVO getPriceOrderDetailReturnVO4Old(Goods goods, PriceOrderDetail priceOrderDetail ){

        PriceOrderDetailReturnVO returnVO = new PriceOrderDetailReturnVO();


        returnVO.setId(priceOrderDetail.getId());
        returnVO.setPriceOrderId(priceOrderDetail.getPriceOrderId());
        returnVO.setGoodsId(priceOrderDetail.getGoodsId());
        returnVO.setGoodsCode(goods.getCode());
        returnVO.setGoodsOldCode(goods.getOldCode());
        returnVO.setGoodsName(goods.getGenericName());
        returnVO.setManufacturer(goods.getManufacturer());
        returnVO.setManufacturerId(goods.getManufacturerId());
        returnVO.setDosageId(goods.getDosageId());
        returnVO.setDosageName(goods.getDosageName());
        returnVO.setSpecification(goods.getSpecification());
        returnVO.setUnitId(goods.getUnitId());
        returnVO.setUnitName(goods.getUnitName());
        returnVO.setDistrPrice(priceOrderDetail.getDistrPrice());
        returnVO.setDistrTaxRate(priceOrderDetail.getDistrTaxRate());
        returnVO.setDistrTaxRateId(priceOrderDetail.getDistrTaxRateId());
        returnVO.setNotaxDistrPrice(priceOrderDetail.getNotaxDistrPrice());
        returnVO.setRetailPrice(priceOrderDetail.getRetailPrice());
        returnVO.setMemberPrice(priceOrderDetail.getMemberPrice());
        returnVO.setSaleTaxRate(priceOrderDetail.getSaleTaxRate());
        returnVO.setSaleTaxRateId(priceOrderDetail.getSaleTaxRateId());
        returnVO.setNotaxRetailPrice(priceOrderDetail.getNotaxRetailPrice());
        returnVO.setNotaxMemberPrice(priceOrderDetail.getNotaxMemberPrice());
        returnVO.setStatus(priceOrderDetail.getStatus());
        returnVO.setPlace(goods.getPlace());

        return returnVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPriceOrderId() {
        return priceOrderId;
    }

    public void setPriceOrderId(Long priceOrderId) {
        this.priceOrderId = priceOrderId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getManufacturerId() {

        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
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

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDistrTaxRateStr() {
		return distrTaxRateStr;
	}

	public void setDistrTaxRateStr(String distrTaxRateStr) {
		this.distrTaxRateStr = distrTaxRateStr;
	}

	public String getSaleTaxRateStr() {
		return saleTaxRateStr;
	}

	public void setSaleTaxRateStr(String saleTaxRateStr) {
		this.saleTaxRateStr = saleTaxRateStr;
	}
	
}