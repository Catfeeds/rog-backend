package com.rograndec.feijiayun.chain.business.retail.royalty.dao;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyDetail;

import java.util.List;

public interface SaleRoyaltyDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleRoyaltyDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleRoyaltyDetail record);

    /**
     *
     * @mbg.generated
     */
    SaleRoyaltyDetail selectByPrimaryKey(Long id);

    List<SaleRoyaltyDetail> selectByToatalIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleRoyaltyDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleRoyaltyDetail record);
}