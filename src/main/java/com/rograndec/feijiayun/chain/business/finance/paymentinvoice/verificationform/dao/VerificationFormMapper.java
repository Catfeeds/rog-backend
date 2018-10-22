package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationForm;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.PurchaseInStorageReportPageVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.SaleOutORreturnVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormVO;

import java.util.List;
import java.util.Map;

public interface VerificationFormMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(VerificationForm record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(VerificationForm record);

    /**
     *
     * @mbg.generated
     */
    VerificationForm selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(VerificationForm record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(VerificationForm record);

    List<SaleOutORreturnVO> getSaleOut(Map<String,Object> map);

    List<SaleOutORreturnVO> getSaleReturn(Map<String,Object> map);

    List<SaleOutORreturnVO> getDistrOut(Map<String,Object> map);

    List<SaleOutORreturnVO> getDistrReturn(Map<String,Object> map);

    List<PurchaseInStorageReportPageVO> getPurchaseInStorageReportPage(Map<String, Object> map);

    Integer getVerificationFormVOCount(Map<String, Object> map);

    List<VerificationFormVO> getVerificationFormVOCountList(Map<String, Object> map);

    List<PurchaseInStorageReportPageVO> getDistrInStorageReportPage(Map<String, Object> map);
}