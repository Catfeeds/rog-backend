package com.rograndec.feijiayun.chain.business.goods.price.entity;

import com.rograndec.feijiayun.chain.business.goods.price.vo.UpdatePriceOrderDetailVO;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class PriceOrderDetail implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 价格清单ID
     */
    private Long priceOrderId;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 配货价格
     */
    private BigDecimal distrPrice;

    /**
     * 配货税率
     */
    private BigDecimal distrTaxRate;
    private Long distrTaxRateId;

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

    /**
     * 不含税零售单价
     */
    private BigDecimal notaxRetailPrice;

    /**
     * 不含税会员单价
     */
    private BigDecimal notaxMemberPrice;


    /**
     * 供货单位ID
     */
    private Long supplierId;


    /**
     * 最新采购税率ID
     */
    private Long purTaxRateId;

    /**
     * 最新采购税率
     */
    private BigDecimal purTaxRate;

    /**
     * 最新采购价
     */
    private BigDecimal purPrice;

    /**
     * 最近入库税率ID
     */
    private Long inTaxRateId;

    /**
     * 最近入库税率
     */
    private BigDecimal inTaxRate;

    /**
     * 最近入库价

     */
    private BigDecimal inPrice;



    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码 
     */
    private String createrCode;

    /**
     * 创建人名称 
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID 
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称 
     */
    private String modifierName;

    /**
     * 更新时间 
     */
    private Date updateTime;


    public static Map<Long,PriceOrderDetail> generatePriceOrderDetailMap(List<PriceOrderDetail> priceOrderDetails){

        Map<Long,PriceOrderDetail> map = new HashMap<>();

        for(PriceOrderDetail priceOrderDetail : priceOrderDetails){
            map.put(priceOrderDetail.getGoodsId(),priceOrderDetail);
        }

        return map;
    }


    public static PriceOrderDetail setPriceOrderDetail4AdjustDetail(PriceOrderDetail priceOrderDetail,PriceAdjustDetail priceAdjustDetail){


        PriceOrderDetail newPriceOrderDetail = new PriceOrderDetail();

        newPriceOrderDetail.setId(priceOrderDetail.getId());
        /**
         * 配货价格
         */
        newPriceOrderDetail.setDistrPrice(priceAdjustDetail.getDistrPrice());

        /**
         * 配货税率
         */
        newPriceOrderDetail.setDistrTaxRate(priceAdjustDetail.getDistrTaxRate());
        newPriceOrderDetail.setDistrTaxRateId(priceAdjustDetail.getDistrTaxRateId());


        /**
         * 不含税配货单价
         */
        newPriceOrderDetail.setNotaxDistrPrice(priceAdjustDetail.getNotaxDistrPrice());


        /**
         * 零售单价
         */
        newPriceOrderDetail.setRetailPrice(priceAdjustDetail.getRetailPrice());


        /**
         * 会员单价
         */
        newPriceOrderDetail.setMemberPrice(priceAdjustDetail.getMemberPrice());


        /**
         * 销项税
         */
        newPriceOrderDetail.setSaleTaxRate(priceAdjustDetail.getSaleTaxRate());
        newPriceOrderDetail.setSaleTaxRateId(priceAdjustDetail.getSaleTaxRateId());

        /**
         * 不含税零售单价
         */
        newPriceOrderDetail.setNotaxRetailPrice(priceAdjustDetail.getNotaxRetailPrice());

        /**
         * 不含税会员单价
         */
        newPriceOrderDetail.setNotaxMemberPrice(priceAdjustDetail.getNotaxMemberPrice());

        newPriceOrderDetail.setModifierId(priceAdjustDetail.getModifierId());
        newPriceOrderDetail.setModifierCode(priceAdjustDetail.getModifierCode());
        newPriceOrderDetail.setModifierName(priceAdjustDetail.getModifierName());
        newPriceOrderDetail.setUpdateTime(new Date());

        return newPriceOrderDetail;

    }

    public static Set<Long> getGoodsIds(List<PriceOrderDetail> priceOrderDetails){

        Set<Long> goodsIds = new HashSet<>();

        for(PriceOrderDetail priceOrderDetail : priceOrderDetails){
            goodsIds.add(priceOrderDetail.getGoodsId());
        }

        return goodsIds;

    }

    public static PriceOrderDetail getPriceOrderDetail4VO(UserVO userVO, Integer status, UpdatePriceOrderDetailVO updatePriceOrderDetailVO, Map<Long,GoodsTaxRate> goodsTaxRateMap, boolean isAdd) throws Exception {

        PriceOrderDetail priceOrderDetail = new PriceOrderDetail();
        priceOrderDetail.setId(updatePriceOrderDetailVO.getId());

        if(isAdd){
            priceOrderDetail.setEnterpriseId(userVO.getEnterpriseId());
            if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
                priceOrderDetail.setParentId(0L);
            }else {
                priceOrderDetail.setParentId(userVO.getParentId());
            }
        }

        priceOrderDetail.setPriceOrderId(updatePriceOrderDetailVO.getPriceOrderId());

        priceOrderDetail.setGoodsId(updatePriceOrderDetailVO.getGoodsId());
        priceOrderDetail.setDistrPrice(updatePriceOrderDetailVO.getDistrPrice());
        if(updatePriceOrderDetailVO.getDistrTaxRateId() != null){
            GoodsTaxRate goodsTaxRate = goodsTaxRateMap.get(updatePriceOrderDetailVO.getDistrTaxRateId());
            priceOrderDetail.setDistrTaxRate(null == goodsTaxRate ? BigDecimal.ZERO : goodsTaxRate.getTaxRate());
            priceOrderDetail.setDistrTaxRateId(updatePriceOrderDetailVO.getDistrTaxRateId());
        }

        priceOrderDetail.setRetailPrice(updatePriceOrderDetailVO.getRetailPrice());

        if(updatePriceOrderDetailVO.getSaleTaxRateId() != null){
            GoodsTaxRate goodsTaxRate = goodsTaxRateMap.get(updatePriceOrderDetailVO.getSaleTaxRateId());
            priceOrderDetail.setSaleTaxRate(null == goodsTaxRate ? BigDecimal.ZERO : goodsTaxRate.getTaxRate());
            priceOrderDetail.setSaleTaxRateId(updatePriceOrderDetailVO.getSaleTaxRateId());
        }
        priceOrderDetail.setMemberPrice(updatePriceOrderDetailVO.getMemberPrice());

        priceOrderDetail.setSaleTaxRateId(updatePriceOrderDetailVO.getSaleTaxRateId());

        if(null != priceOrderDetail.getRetailPrice() && null != priceOrderDetail.getSaleTaxRate()) {
            BigDecimal notaxRetailPrice = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(priceOrderDetail.getRetailPrice(), priceOrderDetail.getSaleTaxRate());
            priceOrderDetail.setNotaxRetailPrice(notaxRetailPrice);
        }

        if(null != priceOrderDetail.getDistrPrice() && null != priceOrderDetail.getDistrTaxRate()){
            BigDecimal notaxDistrPrice = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(priceOrderDetail.getDistrPrice(), priceOrderDetail.getDistrTaxRate());
            priceOrderDetail.setNotaxDistrPrice(notaxDistrPrice);
        }

        if(null != priceOrderDetail.getMemberPrice() && null != priceOrderDetail.getSaleTaxRate()) {
            BigDecimal notaxMemberPrice = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(priceOrderDetail.getMemberPrice(), priceOrderDetail.getSaleTaxRate());
            priceOrderDetail.setNotaxMemberPrice(notaxMemberPrice);
        }


        priceOrderDetail.setStatus(status);
        UserEnterpriseUtils.setUserCreateOrModify(priceOrderDetail,userVO,isAdd);

        return priceOrderDetail;
    }

    /**
     * saas_price_order_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 价格清单ID
     * @return price_order_id 价格清单ID
     */
    public Long getPriceOrderId() {
        return priceOrderId;
    }

    /**
     * 价格清单ID
     * @param priceOrderId 价格清单ID
     */
    public void setPriceOrderId(Long priceOrderId) {
        this.priceOrderId = priceOrderId;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 配货价格
     * @return distr_price 配货价格
     */
    public BigDecimal getDistrPrice() {
        return distrPrice;
    }

    /**
     * 配货价格
     * @param distrPrice 配货价格
     */
    public void setDistrPrice(BigDecimal distrPrice) {
        this.distrPrice = distrPrice;
    }

    /**
     * 配货税率
     * @return distr_tax_rate 配货税率
     */
    public BigDecimal getDistrTaxRate() {
        return distrTaxRate;
    }

    /**
     * 配货税率
     * @param distrTaxRate 配货税率
     */
    public void setDistrTaxRate(BigDecimal distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }

    /**
     * 不含税配货单价
     * @return notax_distr_price 不含税配货单价
     */
    public BigDecimal getNotaxDistrPrice() {
        return notaxDistrPrice;
    }

    /**
     * 不含税配货单价
     * @param notaxDistrPrice 不含税配货单价
     */
    public void setNotaxDistrPrice(BigDecimal notaxDistrPrice) {
        this.notaxDistrPrice = notaxDistrPrice;
    }

    /**
     * 零售单价
     * @return retail_price 零售单价
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 零售单价
     * @param retailPrice 零售单价
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 会员单价
     * @return member_price 会员单价
     */
    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    /**
     * 会员单价
     * @param memberPrice 会员单价
     */
    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    /**
     * 销项税
     * @return sale_tax_rate 销项税
     */
    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    /**
     * 销项税
     * @param saleTaxRate 销项税
     */
    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    /**
     * 不含税零售单价
     * @return notax_retail_price 不含税零售单价
     */
    public BigDecimal getNotaxRetailPrice() {
        return notaxRetailPrice;
    }

    /**
     * 不含税零售单价
     * @param notaxRetailPrice 不含税零售单价
     */
    public void setNotaxRetailPrice(BigDecimal notaxRetailPrice) {
        this.notaxRetailPrice = notaxRetailPrice;
    }

    /**
     * 不含税会员单价
     * @return notax_member_price 不含税会员单价
     */
    public BigDecimal getNotaxMemberPrice() {
        return notaxMemberPrice;
    }

    /**
     * 不含税会员单价
     * @param notaxMemberPrice 不含税会员单价
     */
    public void setNotaxMemberPrice(BigDecimal notaxMemberPrice) {
        this.notaxMemberPrice = notaxMemberPrice;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码 
     * @return creater_code 创建人编码 
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码 
     * @param createrCode 创建人编码 
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称 
     * @return creater_name 创建人名称 
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称 
     * @param createrName 创建人名称 
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID 
     * @return modifier_id 最后修改人ID 
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID 
     * @param modifierId 最后修改人ID 
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称 
     * @return modifier_name 最后修改人名称 
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称 
     * @param modifierName 最后修改人名称 
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间 
     * @return update_time 更新时间 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间 
     * @param updateTime 更新时间 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PriceOrderDetail{" +
                "id=" + id +
                ", priceOrderId=" + priceOrderId +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", goodsId=" + goodsId +
                ", distrPrice=" + distrPrice +
                ", distrTaxRate=" + distrTaxRate +
                ", distrTaxRateId=" + distrTaxRateId +
                ", notaxDistrPrice=" + notaxDistrPrice +
                ", retailPrice=" + retailPrice +
                ", memberPrice=" + memberPrice +
                ", saleTaxRate=" + saleTaxRate +
                ", saleTaxRateId=" + saleTaxRateId +
                ", notaxRetailPrice=" + notaxRetailPrice +
                ", notaxMemberPrice=" + notaxMemberPrice +
                ", supplierId=" + supplierId +
                ", purTaxRateId=" + purTaxRateId +
                ", purTaxRate=" + purTaxRate +
                ", purPrice=" + purPrice +
                ", inTaxRateId=" + inTaxRateId +
                ", inTaxRate=" + inTaxRate +
                ", inPrice=" + inPrice +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                '}';
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


    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getInTaxRate() {
        return inTaxRate;
    }

    public void setInTaxRate(BigDecimal inTaxRate) {
        this.inTaxRate = inTaxRate;
    }

    public Long getInTaxRateId() {
        return inTaxRateId;
    }

    public void setInTaxRateId(Long inTaxRateId) {
        this.inTaxRateId = inTaxRateId;
    }

    public Long getPurTaxRateId() {
        return purTaxRateId;
    }

    public void setPurTaxRateId(Long purTaxRateId) {
        this.purTaxRateId = purTaxRateId;
    }

    public BigDecimal getPurTaxRate() {
        return purTaxRate;
    }

    public void setPurTaxRate(BigDecimal purTaxRate) {
        this.purTaxRate = purTaxRate;
    }

    public BigDecimal getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(BigDecimal purPrice) {
        this.purPrice = purPrice;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }
}