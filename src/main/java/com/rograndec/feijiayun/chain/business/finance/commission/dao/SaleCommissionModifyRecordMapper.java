package com.rograndec.feijiayun.chain.business.finance.commission.dao;

import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommissionModifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleCommissionModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleCommissionModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleCommissionModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    SaleCommissionModifyRecord selectByPrimaryKey(Long id);

    List<SaleCommissionModifyRecord> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("keyId") Long keyId);


    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleCommissionModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleCommissionModifyRecord record);
}