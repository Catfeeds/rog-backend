package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceModifyRecord;

public interface AdvanceReceivableInvoiceModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdvanceReceivableInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdvanceReceivableInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    AdvanceReceivableInvoiceModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdvanceReceivableInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdvanceReceivableInvoiceModifyRecord record);
    
    /**
     * 查询修改记录列表
     * @param start
     * @param pageSize
     * @return
     */
    List<AdvanceReceivableInvoiceModifyRecord> selectAdvanceReceivableInvoiceModifyRecordList(
    	@Param("keyId")Long  keyId,
    	@Param("enterpriseId")Long  enterpriseId,
    	@Param("start")int start,
    	@Param("pageSize")int pageSize);
    /**
     * 查询修改记录列表
     * @return
     */
    List<AdvanceReceivableInvoiceModifyRecord> selectAdvanceReceivableInvoiceModifyRecordListNoPage(
    		@Param("keyId")Long  keyId,
    		@Param("enterpriseId")Long  enterpriseId);
    /**
     * 查询修改记录列表总数
     * @return
     */
   Long selectAdvanceReceivableInvoiceModifyRecordListCount(@Param("keyId")Long  keyId,@Param("enterpriseId")Long  enterpriseId);
}