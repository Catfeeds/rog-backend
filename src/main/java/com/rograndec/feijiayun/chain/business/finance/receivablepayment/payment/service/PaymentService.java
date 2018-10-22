package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.service;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.*;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.RequestModifyPageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface PaymentService {

    Page getPayPage(Page page, RequestPayPageVO requestPayPageVO, UserVO loginUser);

    PayFormVO getPayDetail(Long id);

    void export(OutputStream output, PayFormVO payFormVO, UserVO loginUser);

    String addOrUpdatePayDetail(PayFormVO payFormVO, UserVO loginUser) throws Exception;

    void sterilisation(List<Long> list, UserVO loginUser) throws Exception;

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long companyId, Integer companyType);

    DraftCacheVO<String> saveDraftCache(UserVO userVO, DraftCacheVO<String> cacheVO);

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue, Long companyId, Integer companyType);

    Page getPayUpdateRecord(Page page, RequestModifyPageVO requestModifyPageVO, UserVO loginUser);

    List<PayUpdateRecordVO> getUpdateRecordWithNoPage(Long id);

    void exportUpdateRecord(OutputStream output, List<PayUpdateRecordVO> list, UserVO loginUser);

    Page getPayDocuments(Page page, RequestPayDocuments requestPayDocuments, UserVO loginUser);
}
