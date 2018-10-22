package com.rograndec.feijiayun.chain.business.system.opening.service;

import com.rograndec.feijiayun.chain.business.system.opening.vo.AccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableListVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableSaveVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.ResponsePaymentReceivableExcelVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.util.List;

public interface OpeningPaymentService {
    OpenningPaymentReceivableListVO getOpeningPaymentList(UserVO userVO);

    void saveDraftCache(UserVO userVO, OpenningPaymentReceivableListVO openningPaymentListVO);

    ResponsePaymentReceivableExcelVO excelImport(HttpServletRequest request) throws Exception;

    void exportUnqualified(OutputStream outputStream, String key, Integer type, Long enterpriseId);

    OpenningPaymentReceivableListVO continueToImport(OpenningPaymentReceivableListVO openningPaymentListVO, String key, UserVO userVO);

    List<AccountingPeriodVO> getAccountingPeriod(UserVO userVO);

    String save(OpenningPaymentReceivableSaveVO saveVO, UserVO userVO) throws Exception;

    void exportOpeningPayment(OutputStream outputStream, UserVO userVO);

    void exporTtemplate(OutputStream outputStream, UserVO userVO);
}
