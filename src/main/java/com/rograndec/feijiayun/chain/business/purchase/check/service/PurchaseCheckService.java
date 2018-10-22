package com.rograndec.feijiayun.chain.business.purchase.check.service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/13.
 */
public interface PurchaseCheckService {

    List<PurchaseCheckVO> getPurchaseCheckPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime,String supplierCode,
                                               String supplierName,String code,String checkerName,String orderName, String orderType, Long type);

    List<PurchaseCheckHeadVO> getPurchaseCheckHead(UserVO userVO, Long id);


    PurchaseCheckOtherVO getPurchaseCheckRight(Long enterpriseId, Long id, Long type);


    List<PurchaseReceiveReVO> getWaitPurchaseCheckPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String supplierCode,
                                                     String supplierName, String code, String receiverName, String orderName, String orderType);

    List<WaitPurchaseCheckHeadVO> getWaitPurchaseCheckHead(Long enterpriseId, Long id);

    List<WaitPurchaseCheckDeatilVO> getWaitPurchaseCheckDetail(Long enterpriseId, Long id);

    List<PurchaseCheckDeatilVO> getPurchaseCheckDetail(Long enterpriseId, Long id);

    CheckHeadVO getCheckHead(Long enterpriseId, Long id,UserVO userVO) throws Exception;

    List<CheckDeatilVO> getCheckDetail(UserVO userVO, Long enterpriseId, Long id) throws Exception;

    List<CheckProjectVO> getCheckProject(Long enterpriseId, String code);

    Integer saveCheck(UserVO loginUser, SaveCheckVO saveCheckVO) throws Exception;

    List<ConclusionVO> getConclusion(Long enterpriseId, Integer chainType,Long type);

    List<SimpleUserVO> getChecker(UserVO loginUser) throws Exception;

    PurchaseCheckRequestVO getCheckDetails(UserVO loginUser, Long id) throws Exception;

    void exportExcel(OutputStream output, PurchaseCheckRequestVO purchaseCheckRequestVO, UserVO loginUser);

    DraftCacheVO getDraftCacheVO(UserVO userVO, Long baseOrderId);
    
    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue);

    DraftCacheVO<CheckHeadVO> saveDraftCache(UserVO userVO, DraftCacheVO<CheckHeadVO> draftCacheVO) throws  Exception;
}
