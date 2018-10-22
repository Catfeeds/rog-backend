package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceListrequestVo;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.ReceivableDocumentsPageVO;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.ReceivableDocumentsVO;

public interface AdvanceReceivableInvoiceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdvanceReceivableInvoice record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdvanceReceivableInvoice record);

    /**
     *
     * @mbg.generated
     */
    AdvanceReceivableInvoice selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdvanceReceivableInvoice record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdvanceReceivableInvoice record);

    List<ReceivableDocumentsPageVO> selectWithUnclear(Map<String, Object> map);

    Integer selectTotalRecord(Map<String, Object> map);

    ReceivableDocumentsVO selectAmountTotal(Map<String, Object> map);
    /**
     * 查询发票列表
     * @param invoiceListrequestVo
     * @param start
     * @param pageSize
     * @return
     */
    List<AdvanceReceivableInvoice> selectAdvanceReceivableInvoiceList(
    	@Param("invoiceListrequestVo")InvoiceListrequestVo invoiceListrequestVo,
    	@Param("start")int start,
    	@Param("pageSize")int pageSize);
    /**
     * 查询发票列表总数
     * @param invoiceListrequestVo
     * @return
     */
   Long selectAdvanceReceivableInvoiceListCount(
    		@Param("invoiceListrequestVo")InvoiceListrequestVo invoiceListrequestVo);
}