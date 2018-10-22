package com.rograndec.feijiayun.chain.business.retail.pricing.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricingDetail;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingDetailVO;

public interface SalePricingDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SalePricingDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SalePricingDetail record);

    /**
     *
     * @mbg.generated
     */
    SalePricingDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SalePricingDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SalePricingDetail record);
    
    /**
     * 获取划价详情
     * @param pricingId
     * @return
     */
    List<SalePricingDetailVO> getSalePricingDetail(Long pricingId);


    void deleteBySalePricingId(Long id);
}