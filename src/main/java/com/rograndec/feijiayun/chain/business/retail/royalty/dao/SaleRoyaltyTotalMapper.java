package com.rograndec.feijiayun.chain.business.retail.royalty.dao;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyTotal;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.ResponseSaleTotalVO;

import java.util.List;

public interface SaleRoyaltyTotalMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleRoyaltyTotal record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleRoyaltyTotal record);

    /**
     *
     * @mbg.generated
     */
    SaleRoyaltyTotal selectByPrimaryKey(Long id);

    List<SaleRoyaltyTotal> selectByRoyaltyIds(List<Long> list);

    ResponseSaleTotalVO selectBySumAmount(List<Long> list);

    List<SaleRoyaltyTotal> selectByRoyaltyId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleRoyaltyTotal record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleRoyaltyTotal record);
}