package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.service;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import java.beans.IntrospectionException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface PrepayInvoiceService {
    PrepayInvoice save(UserVO userVO, PrepayInvoiceSaveVO prepayInvoiceSaveVO) throws Exception;

    PrepayInvoice update(UserVO userVO, PrepayInvoiceSaveVO prepayInvoiceSaveVO) throws Exception;

    List<PrepayInvoiceResponseVO> queryPrepayInvoiceResponseVOs(UserVO userVO, PrepayInvoiceQueryParamVO prepayInvoiceQueryParamVO, Page page) throws InvocationTargetException, IntrospectionException, IllegalAccessException, NoSuchFieldException;

    PrepayInvoiceResponseVO queryPrepayInvoiceResponseVO(UserVO userVO, Long prepayInvoiceId);

    PrepayInvoiceResponseVO queryPrepayInvoiceList(UserVO userVO, Long prepayInvoiceId);

    PrepayInvoiceInStoreResponseTotalVO queryInStoreList(UserVO userVO,Long prepayDetailId, String startDate, String endDate, Integer financeAccountType);

    PrepayInvoiceResponseVO autoReconciliation(UserVO userVO, Long prepayinvoiceId) throws Exception;

    void reconciliations(UserVO userVO, List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParams) throws Exception;

    void reconciliation(UserVO userVO, PerpayInvoiceAccountParamVO perpayInvoiceAccountParamVO) throws Exception;

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws BusinessException;

    void removeDraftCach(Long supplierId,Long enterpriseId, String type, String redisKeyValue) throws BusinessException;

    List<DraftCacheVO> getDraftCacheVO(Long supplierId ,UserVO userVO);

    void wariteOff(UserVO userVO,Long id) throws Exception;

    List<PrepayInvoiceModifyRecordResponseVO> queryModifyRecords(UserVO userVO, Long prepayInvoiceId, Page page);

    void export(OutputStream output, PrepayInvoiceResponseVO prepayInvoiceResponseVO);

    void exportUpdateRecord(OutputStream output, List<PrepayInvoiceModifyRecordResponseVO> list, UserVO loginUser);
}
