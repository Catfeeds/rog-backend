package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.service;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface RetailPayMentService {
    Page<RetailPayMentResponseVO> getPayMentByParam(RetailPayMentRageParamVO paramVO, UserVO loginUser) throws Exception;

    RetailPaymentVO getPayMentDetail(Long id, UserVO loginUser) throws Exception;

    String updatePayMent(RetailPaymentVO paymentVO, UserVO loginUser) throws Exception;

    String chargrAgainstPayMent(List<Long> ids, UserVO loginUser) throws Exception;

    String savePayMent(RetailPaymentVO retailPaymentVO, UserVO loginUser) throws Exception;

    Page<List<RetailPaymentModifyRecordVO>> getPayMentModifyRecord(Integer pageNo, Integer pageSize, Long id, UserVO loginUser) throws Exception;

    void exportExcel(OutputStream output, Long id, UserVO loginUser) throws Exception;

    void exportExcelModifyRecord(OutputStream output, Long id, UserVO loginUser) throws Exception;

    RetailPaymentExportVO getPayMentPrint(UserVO loginUser, Long id) throws Exception;

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception;

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<RetailPaymentVO> draftCacheVO) throws Exception;

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue) throws Exception;

    List<RetailPaymentItemVO> getWillPayMentByStore(Long storeId, UserVO loginUser) throws Exception;
}
