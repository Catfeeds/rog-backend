package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo.InvoiceModifyRecordPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceivableInvoiceModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceivableInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceivableInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    ReceivableInvoiceModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceivableInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceivableInvoiceModifyRecord record);

    List<InvoiceModifyRecordPageVO> getInvoiceModifyRecord(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);
}