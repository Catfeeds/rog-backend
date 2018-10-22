package com.rograndec.feijiayun.chain.business.retail.pricing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricingShelf;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingExcel;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingShelfVO;

public interface SalePricingShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SalePricingShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SalePricingShelf record);

    /**
     *
     * @mbg.generated
     */
    SalePricingShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SalePricingShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SalePricingShelf record);
    
    /**
     * 获取划价明细中商品的货位
     * @param pricingDtlId
     * @param pricingId
     * @return
     */
    List<SalePricingShelfVO> getPricingShelfGoods(@Param("pricingDtlId")Long pricingDtlId,@Param("goodsId")Long goodsId);
    
    
    List<SalePricingExcel> getPricingShelfGoodsExcel(Long pricingId);
    
}