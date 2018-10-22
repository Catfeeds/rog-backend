package com.rograndec.feijiayun.chain.business.finance.commission.dao;

import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommissionDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleCommissionDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleCommissionDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleCommissionDetail record);

    /**
     *
     * @mbg.generated
     */
    SaleCommissionDetail selectByPrimaryKey(Long id);

    List<SaleCommissionDetail> selectByCommissionId(@Param("commissionId") Long commissionId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleCommissionDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleCommissionDetail record);

    List<SaleCommissionDetail> selectByCommissionStrategyId(@Param("commissionStrategyId") Long commissionStrategyId);
}