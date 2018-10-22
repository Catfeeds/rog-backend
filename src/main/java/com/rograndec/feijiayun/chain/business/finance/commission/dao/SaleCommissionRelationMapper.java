package com.rograndec.feijiayun.chain.business.finance.commission.dao;

import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleCommissionRelationMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleCommissionRelation record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleCommissionRelation record);

    /**
     *
     * @mbg.generated
     */
    SaleCommissionRelation selectByPrimaryKey(Long id);

    List<SaleCommissionRelation> selectByCommissionId(@Param("commissionId") Long commissionId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleCommissionRelation record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleCommissionRelation record);
}