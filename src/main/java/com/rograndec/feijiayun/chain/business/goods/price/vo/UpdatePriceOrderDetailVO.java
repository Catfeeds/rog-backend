package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.utils.ExcelMethodUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel
public class UpdatePriceOrderDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "价格清单id,修改时需要传入,新增时不需要", required = false)
    private Long id;

    /**
     * 价格清单ID
     */
    @NotNull(message = "价格清单ID不能为空")
    @ApiModelProperty(value = "价格清单ID", required = true)
    private Long priceOrderId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    @NotNull(message = "商品ID不能为空")
    private Long goodsId;

    /**
     * 配货价格
     */
    @ApiModelProperty(value = "配货价格")
    private BigDecimal distrPrice;

    /**
     * 配货税率
     */
    @ApiModelProperty(value = "配货税率Id")
    private Long distrTaxRateId;

    /**
     * 不含税配货单价
     */
    @ApiModelProperty(value = "不含税配货单价")
    private BigDecimal notaxDistrPrice;

    /**
     * 零售单价
     */
    @ApiModelProperty(value = "零售单价")
    private BigDecimal retailPrice;

    /**
     * 会员单价
     */
    @ApiModelProperty(value = "会员单价")
    private BigDecimal memberPrice;

    /**
     * 销项税
     */
    @ApiModelProperty(value = "销项税Id")
    private Long saleTaxRateId;

    /**
     * 不含税零售单价
     */
    @ApiModelProperty(value = "不含税零售单价")
    private BigDecimal notaxRetailPrice;

    /**
     * 不含税会员单价
     */
    @ApiModelProperty(value = "不含税会员单价")
    private BigDecimal notaxMemberPrice;

    public static List<UpdatePriceOrderDetailVO> generateUpdatePriceOrderDetailVOs(List<PriceOrderExcelVO> priceOrderExcelVOS,Long priceOrderId){


        return priceOrderExcelVOS.stream().map(priceOrderExcelVO -> {
            UpdatePriceOrderDetailVO updatePriceOrderDetailVO = new UpdatePriceOrderDetailVO();

            /**
             * 主键ID
             */
            updatePriceOrderDetailVO.setId(priceOrderExcelVO.getId());

            /**
             * 价格清单ID
             */
            updatePriceOrderDetailVO.setPriceOrderId(priceOrderId);

            /**
             * 商品ID
             */
            updatePriceOrderDetailVO.setGoodsId(priceOrderExcelVO.getGoodsId());

            /**
             * 配货价格
             */
            String distrPrice = priceOrderExcelVO.getDistrPrice();
            if(!StringUtils.isEmpty(distrPrice)){
                boolean isDecimals = ExcelMethodUtils.isDecimals(distrPrice);
                if(!isDecimals)throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,priceOrderExcelVO.getLineNum()+"行:配货价格不是一个数字");

                BigDecimal dp = new BigDecimal(distrPrice);
                updatePriceOrderDetailVO.setDistrPrice(dp);
            }


            /**
             * 配货税率
             */
            updatePriceOrderDetailVO.setDistrTaxRateId(priceOrderExcelVO.getDistrTaxRateId());


            /**
             * 零售单价
             */
            String retailPrice = priceOrderExcelVO.getRetailPrice();
            if(!StringUtils.isEmpty(retailPrice)) {
                boolean isDecimals = ExcelMethodUtils.isDecimals(retailPrice);
                if(!isDecimals)throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,priceOrderExcelVO.getLineNum()+"行:零售单价不是一个数字");

                BigDecimal dp = new BigDecimal(retailPrice);
                updatePriceOrderDetailVO.setRetailPrice(dp);
            }

            /**
             * 会员单价
             */
            String memberPrice = priceOrderExcelVO.getMemberPrice();
            if(!StringUtils.isEmpty(memberPrice)) {
                boolean isDecimals = ExcelMethodUtils.isDecimals(memberPrice);
                if(!isDecimals)throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,priceOrderExcelVO.getLineNum()+"行:会员单价不是一个数字");

                BigDecimal dp = new BigDecimal(memberPrice);
                updatePriceOrderDetailVO.setMemberPrice(dp);
            }

            /**
             * 销项税
             */
            updatePriceOrderDetailVO.setSaleTaxRateId(priceOrderExcelVO.getSaleTaxRateId());


            return updatePriceOrderDetailVO;

        }).collect(Collectors.toList());




    }

    public static Set<Long> getTaxRateIds(List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS){
        Set<Long> ids = new HashSet<>();

        for(UpdatePriceOrderDetailVO updatePriceOrderDetailVO : updatePriceOrderDetailVOS){
            ids.add(updatePriceOrderDetailVO.getDistrTaxRateId());
            ids.add(updatePriceOrderDetailVO.getSaleTaxRateId());
        }

        return ids;

    }

    public static List<Long>  getGoodsIds(List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS){

        Set<Long> collect = updatePriceOrderDetailVOS.stream().map(updatePriceOrderDetailVO -> updatePriceOrderDetailVO.getGoodsId()).collect(Collectors.toSet());

        List<Long> list = collect.stream().collect(Collectors.toList());
        return list;
    }

    public static List<Long>  getIds(List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS){

        Set<Long> collect = updatePriceOrderDetailVOS.stream().map(updatePriceOrderDetailVO -> updatePriceOrderDetailVO.getId()).collect(Collectors.toSet());

        List<Long> list = collect.stream().collect(Collectors.toList());
        return list;
    }

    public static List<UpdatePriceOrderDetailVO> getAddPriceOrderDetailVOs4Entitys(List<PriceOrderDetail> priceOrderDetails, PriceOrder priceOrder){

        List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS = new ArrayList<>();

        for(PriceOrderDetail priceOrderDetail : priceOrderDetails){
            UpdatePriceOrderDetailVO priceOrderDetailVO = getUpdatePriceOrderDetailVO4Entity(priceOrderDetail,priceOrder);
            priceOrderDetailVO.setId(null);
            updatePriceOrderDetailVOS.add(priceOrderDetailVO);
        }

        return updatePriceOrderDetailVOS;

    }

    public static UpdatePriceOrderDetailVO getUpdatePriceOrderDetailVO4Entity(PriceOrderDetail priceOrderDetail, PriceOrder priceOrder){

        UpdatePriceOrderDetailVO updatePriceOrderDetailVO = new UpdatePriceOrderDetailVO();


        /*updatePriceOrderDetailVO.setId(priceOrderDetail.getId());*/
        updatePriceOrderDetailVO.setPriceOrderId(priceOrder.getId());
        updatePriceOrderDetailVO.setGoodsId(priceOrderDetail.getGoodsId());
        updatePriceOrderDetailVO.setDistrPrice(priceOrderDetail.getDistrPrice());
        updatePriceOrderDetailVO.setDistrTaxRateId(priceOrderDetail.getDistrTaxRateId());
        updatePriceOrderDetailVO.setNotaxDistrPrice(priceOrderDetail.getNotaxDistrPrice());
        updatePriceOrderDetailVO.setRetailPrice(priceOrderDetail.getRetailPrice());
        updatePriceOrderDetailVO.setMemberPrice(priceOrderDetail.getMemberPrice());
        updatePriceOrderDetailVO.setSaleTaxRateId(priceOrderDetail.getSaleTaxRateId());
        updatePriceOrderDetailVO.setNotaxRetailPrice(priceOrderDetail.getNotaxRetailPrice());
        updatePriceOrderDetailVO.setNotaxMemberPrice(priceOrderDetail.getNotaxMemberPrice());

        return updatePriceOrderDetailVO;
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

    public BigDecimal getDistrPrice() {
        return distrPrice;
    }

    public void setDistrPrice(BigDecimal distrPrice) {
        this.distrPrice = distrPrice;
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
}