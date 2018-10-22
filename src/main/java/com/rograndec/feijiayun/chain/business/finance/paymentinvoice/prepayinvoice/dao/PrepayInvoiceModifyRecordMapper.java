package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoiceModifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrepayInvoiceModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrepayInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrepayInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    PrepayInvoiceModifyRecord selectByPrimaryKey(Long id);

    List<PrepayInvoiceModifyRecord> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId,@Param("keyId") Long keyId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrepayInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrepayInvoiceModifyRecord record);
}