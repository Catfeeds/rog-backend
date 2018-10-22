package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.common.SelectThreeBean;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface CollectMoneyService {

    Page getReceivePage(Page page, RequestReceivePageVO requestReceivePageVO, UserVO loginUser);

    ReceiveFormVO getReceiveDetail(Long id);

    Page getReceiveUpdateRecord(Page page,RequestModifyPageVO requestModifyPageVO, UserVO loginUser);

    void export(OutputStream output, ReceiveFormVO receiveFormVO, UserVO loginUser);

    String addOrUpdateReceiveDetail(ReceiveFormVO receiveFormVO, UserVO loginUser) throws Exception;

    void sterilisation(List<Long> list, UserVO loginUser) throws Exception;

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long companyId, Integer companyType);

    DraftCacheVO<String> saveDraftCache(UserVO userVO, DraftCacheVO<String> cache);

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue, Long companyId, Integer companyType);

    List<User> getReceivableManNameSelector(UserVO loginUser, String param);

    List<SelectBeanWithCode> getPayCompanyCodeSelector(Integer companyType, UserVO loginUser, String param);

    String getPayCompanyName(Integer companyType, UserVO loginUser, Long id);

    List<SelectThreeBean> getBankSelector(UserVO loginUser);

    Page getReceivableDocuments(Page page, RequestReceivableDocuments requestReceivableDocuments, UserVO loginUser);

    List<ReceiveUpdateRecordVO> getUpdateRecordWithNoPage(Long id);

    void exportUpdateRecord(OutputStream output, List<ReceiveUpdateRecordVO> list, UserVO loginUser);
}
