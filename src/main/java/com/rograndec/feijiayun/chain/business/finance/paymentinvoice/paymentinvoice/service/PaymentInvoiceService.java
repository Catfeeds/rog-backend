package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.service;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import java.beans.IntrospectionException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface PaymentInvoiceService {

    PaymentInvoice save(UserVO userVO, PaymentInvoiceSaveVO paymentInvoiceSaveVO) throws Exception;

    PaymentInvoice update(UserVO userVO, PaymentInvoiceSaveVO paymentInvoiceSaveVO) throws Exception;

    List<PaymentInvoiceResponseVO> queryPaymentInvoiceResponseVOs(UserVO userVO, PaymentInvoiceQueryParamVO paymentInvoiceQueryParamVO, Page page) throws InvocationTargetException, IntrospectionException, IllegalAccessException, NoSuchFieldException;

    PaymentInvoiceResponseVO queryPaymentInvoiceResponseVO(UserVO userVO, Long prepayInvoiceId);

    List<PaymentInvoiceGoodsVO> queryPaymentInvoiceGoods(UserVO userVO, Long supplierId, Integer type, String param, Page page);

    List<PaymentInvoiceModifyRecordResponseVO> queryModifyRecords(UserVO userVO, Long paymentInvoiceId, Page page);

    void wariteOff(UserVO userVO, Long id) throws Exception;

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws BusinessException;

    void removeDraftCach(Long supplierId, Long enterpriseId, String type, String redisKeyValue) throws BusinessException;

    List<DraftCacheVO> getDraftCacheVO(Long supplierId, UserVO userVO);

    void export(OutputStream output, PaymentInvoiceResponseVO paymentInvoiceResponseVO);

    void exportUpdateRecord(OutputStream output, List<PaymentInvoiceModifyRecordResponseVO> list, UserVO loginUser);

    List<PaymentInvoiceInStorageVO> queryInStores(UserVO userVO, Long suppilerId, String startDate, String endDate, Integer type, Page page);

    List<PaymentInvoiceGoodsVO> queryPaymentInvoiceGoods(UserVO userVO, List<Long> inStorageIds);
}
