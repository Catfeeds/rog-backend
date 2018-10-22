package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceInfo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo.InvoiceModifyRecordPageVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo.SaveReceivableInvoiceInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceivableInvoiceInfoMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceivableInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceivableInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    ReceivableInvoiceInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceivableInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceivableInvoiceInfo record);

    ReceivableInvoiceInfo getReceivableInvoiceInfo(Long id);

    Integer getInvoiceModifyRecordPageCount(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<InvoiceModifyRecordPageVO> getInvoiceModifyRecordPage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("id")Long id);

    SaveReceivableInvoiceInfoVO getInvoiceInfo(Long id);
}