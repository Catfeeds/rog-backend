package com.rograndec.feijiayun.chain.business.goods.price.entity;

import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdateAdjustDetailVO;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class PriceAdjustDetail implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 价格调整单ID
     */
    private Long adjustId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 配货价格
     */
    private BigDecimal distrPrice;

    /**
     * 原配货价格
     */
    private BigDecimal oldDistrPrice;

    /**
     * 配货税率
     */
    private BigDecimal distrTaxRate;
    private Long distrTaxRateId;

    /**
     * 原配货税率
     */
    private BigDecimal oldDistrTaxRate;
    private Long oldDistrTaxRateId;

    /**
     * 不含税配货单价
     */
    private BigDecimal notaxDistrPrice;

    /**
     * 原不含税配货单价
     */
    private BigDecimal oldNotaxDistrPrice;

    /**
     * 零售单价
     */
    private BigDecimal retailPrice;

    /**
     * 原零售单价
     */
    private BigDecimal oldRetailPrice;

    /**
     * 会员单价
     */
    private BigDecimal memberPrice;

    /**
     * 原会员单价
     */
    private BigDecimal oldMemberPrice;

    /**
     * 销项税
     */
    private BigDecimal saleTaxRate;
    private Long saleTaxRateId;

    /**
     * 原销项税
     */
    private BigDecimal oldSaleTaxRate;
    private Long oldSaleTaxRateId;

    /**
     * 不含税零售单价
     */
    private BigDecimal notaxRetailPrice;

    /**
     * 原不含税零售单价
     */
    private BigDecimal oldNotaxRetailPrice;

    /**
     * 不含税会员单价
     */
    private BigDecimal notaxMemberPrice;

    /**
     * 原不含税会员单价
     */
    private BigDecimal oldNotaxMemberPrice;

    /**
     * 状态（0-禁用；1-启用
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

    /**
     * saas_price_adjust_detail
     */
    private static final long serialVersionUID = 1L;

    public static Set<Long> getGoodsIds(List<PriceAdjustDetail> priceAdjustDetails){

        Set<Long> list = new HashSet<>();

        for(PriceAdjustDetail priceAdjustDetail : priceAdjustDetails){
            list.add(priceAdjustDetail.getGoodsId());
        }

        return list;
    }


//    public static List<PriceAdjustDetail> getAdjustDetail4VO(UserVO userVO
//            ,PriceAdjust priceAdjust
//            , List<PriceOrderDetail> priceOrderDetails
//            , List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS
//            , boolean isAdd) throws Exception {
//        return getAdjustDetail4VO(userVO,priceAdjust,null,priceOrderDetails,addOrUpdateAdjustDetailVOS,isAdd);
//    }

    /**
     *  根据vo获取价格调整单明细
     * @param priceAdjust 价格调整单头信息
     * @param priceOrderDetails 价格清单实体集合
     * @param addOrUpdateAdjustDetailVOS 价格调整单vo实体集合
     * @return
     */
    public static List<PriceAdjustDetail> getAdjustDetail4VO(UserVO userVO
                , PriceAdjust priceAdjust
                , List<PriceOrderDetail> priceOrderDetails
                , List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS
                , Map<Long,GoodsTaxRate> goodsTaxRatesMap
                , boolean isAdd) throws Exception {
        List<PriceAdjustDetail> priceAdjustDetails = new ArrayList<>();

        BigDecimal zero = new BigDecimal(0);


        for(AddOrUpdateAdjustDetailVO addOrUpdateAdjustDetailVO : addOrUpdateAdjustDetailVOS){
            for(PriceOrderDetail priceOrderDetail : priceOrderDetails){
                if(addOrUpdateAdjustDetailVO.getGoodsId().equals(priceOrderDetail.getGoodsId())){
                    PriceAdjustDetail priceAdjustDetail = new PriceAdjustDetail();
                    priceAdjustDetail.setId(addOrUpdateAdjustDetailVO.getId());
                    priceAdjustDetail.setEnterpriseId(priceAdjust.getEnterpriseId());
                    priceAdjustDetail.setParentId(priceAdjust.getParentId());
                    priceAdjustDetail.setAdjustId(priceAdjust.getId());
                    priceAdjustDetail.setGoodsId(addOrUpdateAdjustDetailVO.getGoodsId());
                    priceAdjustDetail.setDistrPrice(addOrUpdateAdjustDetailVO.getDistrPrice());

                    priceAdjustDetail.setOldDistrPrice(priceOrderDetail.getDistrPrice() == null ? zero : priceOrderDetail.getDistrPrice());

                    if(null != addOrUpdateAdjustDetailVO.getDistrTaxRateId()) {
                        priceAdjustDetail.setDistrTaxRate(goodsTaxRatesMap.get(addOrUpdateAdjustDetailVO.getDistrTaxRateId()).getTaxRate());
                        priceAdjustDetail.setDistrTaxRateId(addOrUpdateAdjustDetailVO.getDistrTaxRateId());
                    }


                    priceAdjustDetail.setOldDistrTaxRate(priceOrderDetail.getDistrTaxRate() == null ? zero : priceOrderDetail.getDistrTaxRate());
                    priceAdjustDetail.setOldDistrTaxRateId(priceOrderDetail.getDistrTaxRateId());


                    priceAdjustDetail.setNotaxDistrPrice(addOrUpdateAdjustDetailVO.getNotaxDistrPrice());


                    priceAdjustDetail.setOldNotaxDistrPrice(priceOrderDetail.getNotaxDistrPrice() == null ? zero : priceOrderDetail.getNotaxDistrPrice());

                    priceAdjustDetail.setRetailPrice(addOrUpdateAdjustDetailVO.getRetailPrice());


                    priceAdjustDetail.setOldRetailPrice(priceOrderDetail.getRetailPrice() == null ? zero : priceOrderDetail.getRetailPrice() );

                    priceAdjustDetail.setMemberPrice(addOrUpdateAdjustDetailVO.getMemberPrice());

                    priceAdjustDetail.setOldMemberPrice(priceOrderDetail.getMemberPrice() == null ? zero : priceOrderDetail.getMemberPrice());


                    if(addOrUpdateAdjustDetailVO.getSaleTaxRateId() != null){
                        priceAdjustDetail.setSaleTaxRate(goodsTaxRatesMap.get(addOrUpdateAdjustDetailVO.getSaleTaxRateId()).getTaxRate());
                        priceAdjustDetail.setSaleTaxRateId(addOrUpdateAdjustDetailVO.getSaleTaxRateId());
                    }



                    priceAdjustDetail.setOldSaleTaxRate(priceOrderDetail.getSaleTaxRate() == null ? zero : priceOrderDetail.getSaleTaxRate());
                    priceAdjustDetail.setOldSaleTaxRateId(priceOrderDetail.getSaleTaxRateId());


                    priceAdjustDetail.setNotaxRetailPrice(addOrUpdateAdjustDetailVO.getNotaxRetailPrice());

                    priceAdjustDetail.setOldNotaxRetailPrice(priceOrderDetail.getNotaxRetailPrice() == null ? zero : priceOrderDetail.getNotaxRetailPrice());

                    priceAdjustDetail.setNotaxMemberPrice(addOrUpdateAdjustDetailVO.getNotaxMemberPrice());


                    priceAdjustDetail.setOldNotaxMemberPrice(priceOrderDetail.getNotaxMemberPrice() == null ? zero : priceOrderDetail.getNotaxMemberPrice());


                    priceAdjustDetail.setStatus(PurchaseStatus.PENDING_AUDIT.getStatus());
                    UserEnterpriseUtils.setUserCreateOrModify(priceAdjustDetail,userVO,isAdd);
                    priceAdjustDetails.add(priceAdjustDetail);

                    continue;
                }

            }

        }

        return priceAdjustDetails;
    }

    /**
     * 主键id
     * @return id 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
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
     * 价格调整单ID
     * @return adjust_id 价格调整单ID
     */
    public Long getAdjustId() {
        return adjustId;
    }

    /**
     * 价格调整单ID
     * @param adjustId 价格调整单ID
     */
    public void setAdjustId(Long adjustId) {
        this.adjustId = adjustId;
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
     * 原配货价格
     * @return old_distr_price 原配货价格
     */
    public BigDecimal getOldDistrPrice() {
        return oldDistrPrice;
    }

    /**
     * 原配货价格
     * @param oldDistrPrice 原配货价格
     */
    public void setOldDistrPrice(BigDecimal oldDistrPrice) {
        this.oldDistrPrice = oldDistrPrice;
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
     * 原配货税率
     * @return old_distr_tax_rate 原配货税率
     */
    public BigDecimal getOldDistrTaxRate() {
        return oldDistrTaxRate;
    }

    /**
     * 原配货税率
     * @param oldDistrTaxRate 原配货税率
     */
    public void setOldDistrTaxRate(BigDecimal oldDistrTaxRate) {
        this.oldDistrTaxRate = oldDistrTaxRate;
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
     * 原不含税配货单价
     * @return old_notax_distr_price 原不含税配货单价
     */
    public BigDecimal getOldNotaxDistrPrice() {
        return oldNotaxDistrPrice;
    }

    /**
     * 原不含税配货单价
     * @param oldNotaxDistrPrice 原不含税配货单价
     */
    public void setOldNotaxDistrPrice(BigDecimal oldNotaxDistrPrice) {
        this.oldNotaxDistrPrice = oldNotaxDistrPrice;
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
     * 原零售单价
     * @return old_retail_price 原零售单价
     */
    public BigDecimal getOldRetailPrice() {
        return oldRetailPrice;
    }

    /**
     * 原零售单价
     * @param oldRetailPrice 原零售单价
     */
    public void setOldRetailPrice(BigDecimal oldRetailPrice) {
        this.oldRetailPrice = oldRetailPrice;
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
     * 原会员单价
     * @return old_member_price 原会员单价
     */
    public BigDecimal getOldMemberPrice() {
        return oldMemberPrice;
    }

    /**
     * 原会员单价
     * @param oldMemberPrice 原会员单价
     */
    public void setOldMemberPrice(BigDecimal oldMemberPrice) {
        this.oldMemberPrice = oldMemberPrice;
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
     * 原销项税
     * @return old_sale_tax_rate 原销项税
     */
    public BigDecimal getOldSaleTaxRate() {
        return oldSaleTaxRate;
    }

    /**
     * 原销项税
     * @param oldSaleTaxRate 原销项税
     */
    public void setOldSaleTaxRate(BigDecimal oldSaleTaxRate) {
        this.oldSaleTaxRate = oldSaleTaxRate;
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
     * @return old_notax_retail_price 不含税会员单价
     */
    public BigDecimal getOldNotaxRetailPrice() {
        return oldNotaxRetailPrice;
    }

    /**
     * 不含税会员单价
     * @param oldNotaxRetailPrice 不含税会员单价
     */
    public void setOldNotaxRetailPrice(BigDecimal oldNotaxRetailPrice) {
        this.oldNotaxRetailPrice = oldNotaxRetailPrice;
    }

    /**
     * 原不含税零售单价
     * @return notax_member_price 原不含税零售单价
     */
    public BigDecimal getNotaxMemberPrice() {
        return notaxMemberPrice;
    }

    /**
     * 原不含税零售单价
     * @param notaxMemberPrice 原不含税零售单价
     */
    public void setNotaxMemberPrice(BigDecimal notaxMemberPrice) {
        this.notaxMemberPrice = notaxMemberPrice;
    }

    /**
     * 原不含税会员单价
     * @return old_notax_member_price 原不含税会员单价
     */
    public BigDecimal getOldNotaxMemberPrice() {
        return oldNotaxMemberPrice;
    }

    /**
     * 原不含税会员单价
     * @param oldNotaxMemberPrice 原不含税会员单价
     */
    public void setOldNotaxMemberPrice(BigDecimal oldNotaxMemberPrice) {
        this.oldNotaxMemberPrice = oldNotaxMemberPrice;
    }

    /**
     * 状态（0-禁用；1-启用
     * @return status 状态（0-禁用；1-启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用
     * @param status 状态（0-禁用；1-启用
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

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", adjustId=").append(adjustId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", distrPrice=").append(distrPrice);
        sb.append(", oldDistrPrice=").append(oldDistrPrice);
        sb.append(", distrTaxRate=").append(distrTaxRate);
        sb.append(", oldDistrTaxRate=").append(oldDistrTaxRate);
        sb.append(", notaxDistrPrice=").append(notaxDistrPrice);
        sb.append(", oldNotaxDistrPrice=").append(oldNotaxDistrPrice);
        sb.append(", retailPrice=").append(retailPrice);
        sb.append(", oldRetailPrice=").append(oldRetailPrice);
        sb.append(", memberPrice=").append(memberPrice);
        sb.append(", oldMemberPrice=").append(oldMemberPrice);
        sb.append(", saleTaxRate=").append(saleTaxRate);
        sb.append(", oldSaleTaxRate=").append(oldSaleTaxRate);
        sb.append(", notaxRetailPrice=").append(notaxRetailPrice);
        sb.append(", oldNotaxRetailPrice=").append(oldNotaxRetailPrice);
        sb.append(", notaxMemberPrice=").append(notaxMemberPrice);
        sb.append(", oldNotaxMemberPrice=").append(oldNotaxMemberPrice);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
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