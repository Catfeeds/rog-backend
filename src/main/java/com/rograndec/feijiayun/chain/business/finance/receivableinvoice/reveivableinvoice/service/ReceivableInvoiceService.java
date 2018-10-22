package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.service;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface ReceivableInvoiceService {

    TabableTotalVO getReveivableInvoicePage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String purchaseUnitCode, String purchaseUnitName, String code, String billManName, Integer status, String orderName, String orderType);

    String saveReveivableInvoice(UserVO loginUser, SaveReveivableInvoiceVO saveReveivableInvoiceVO) throws Exception;

    DistrOutPageTotalVO getDistrOutPage(int pageNo, int pageSize, UserVO loginUser, Page page, Long purchaseUnitId, String key);

    List<PurchaseUnitVO> getPurchaseUnit(UserVO userVO, String key);

    SaveReveivableInvoiceVO getSaveReveivableInvoice(UserVO userVO, Long id);

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long supplierId) throws Exception;

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCacheVO) throws Exception;

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue, Long supplierId) throws Exception;

    void getAlreadyWriteInvoice(UserVO userVO, Long id) throws Exception;

    List<CallDistrOutPageVO> getCallDistrOutPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String orderName, String orderType, Long supplierId);

    List<DistrOutPageVO> getCallDistrOutDeatil(UserVO loginUser, List<Long> ids);

    List<InvoiceModifyRecordPageVO> getInvoiceModifyRecordPage(int pageNo, int pageSize, UserVO loginUser, Page page, Long id);

    void exportExcel(OutputStream output, SaveReveivableInvoiceVO saveReveivableinvoice, UserVO loginUser);

    List<InvoiceModifyRecordPageVO> getInvoiceModifyRecord(UserVO loginUser, Long id);

    void exportExcelModifyRecord(OutputStream output, List<InvoiceModifyRecordPageVO> invoiceModifyRecordPage, UserVO loginUser);

    SaveReveivableInvoiceVO calculationReveivableInvoice(UserVO loginUser, SaveReveivableInvoiceVO saveReveivableInvoiceVO);

    SaveReceivableInvoiceInfoVO getSaveReveivableInvoiceInfo(UserVO userVO, Long supplierId) throws Exception;
}
