package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.service;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface ReceivableVoucherService {

    TabableTotalVoucherVO getReceivableVoucherPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String purchaseUnitCode, String purchaseUnitName, String code, String postManName, Integer status, String orderName, String orderType);

    String saveReveivableVoucher(UserVO loginUser, SaveReveivableVoucherVO saveReveivableVoucherVO) throws Exception;

    SaveReveivableVoucherVO getSaveReveivableVoucher(UserVO userVO, Long id);

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long supplierId) throws Exception;

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCacheVO) throws Exception;

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue, Long supplierId) throws Exception;

    List<VoucherModifyRecordPageVO> getVoucherModifyRecordPage(int pageNo, int pageSize, UserVO loginUser, Page page, Long id);

    void exportExcel(OutputStream output, SaveReveivableVoucherVO saveReveivableVoucher, UserVO loginUser);

    List<VoucherModifyRecordPageVO> getVoucherModifyRecordPageList(UserVO loginUser, Long id);

    void exportExcelModifyRecord(OutputStream output, List<VoucherModifyRecordPageVO> voucherModifyRecordPageVOS, UserVO loginUser);

    DistrReturnInTotalVO getDistrReturnInTotal(int pageNo, int pageSize, UserVO loginUser, Page page, Long purchaseUnitId, String key);

    List<CallDistrReturnInPageVO> getCallDistrReturnInPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String orderName, String orderType, Long supplierId);

    List<DistrReturnInPageListVO> getCallDistrReturnInDeatil(UserVO loginUser, List<Long> ids);

    void getAlreadyWriteVoucher(UserVO userVO, Long id) throws Exception;
}
