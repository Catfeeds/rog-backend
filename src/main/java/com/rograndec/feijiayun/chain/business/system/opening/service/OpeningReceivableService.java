package com.rograndec.feijiayun.chain.business.system.opening.service;

import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableListVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableSaveVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.ResponsePaymentReceivableExcelVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;

/**
 * 期初应收
 * @author zhangyu
 */
public interface OpeningReceivableService {
    OpenningPaymentReceivableListVO getOpeningReceivableList(UserVO userVO);

    void saveDraftCache(UserVO userVO, OpenningPaymentReceivableListVO openningReceivableListVO);

    ResponsePaymentReceivableExcelVO excelImport(HttpServletRequest request) throws Exception;

    void exportUnqualified(OutputStream outputStream, String key, Integer type, Long enterpriseId);

    OpenningPaymentReceivableListVO continueToImport(OpenningPaymentReceivableListVO openningReceivableListVO, String key, UserVO userVO);

    String save(OpenningPaymentReceivableSaveVO saveVO, UserVO userVO) throws Exception;

    void exportOpeningReceivable(OutputStream outputStream, UserVO userVO);

    void exporTtemplate(OutputStream outputStream, UserVO userVO);
}
