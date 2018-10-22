package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.service;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrEnterpriseVO;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettleDetail;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface RetailDailySettleService {
    Page<RetailDailySettleReponseVO> getDailySettleByParam(DailtSettleRequestPageParamVO paramVO, UserVO loginUser) throws Exception;

    RetailDailySettleVO getDailySettleDetail(Long settleId, UserVO loginUser) throws Exception;

    String updateDailySettle(RetailDailySettleVO dailySettleVO, UserVO loginUser) throws Exception;

    String chargrAgainstDailySettle(List<Long> settleId, UserVO loginUser) throws Exception;

    String saveDailySettle(RetailDailySettleVO retailDailySettleVO, UserVO loginUser) throws Exception;

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue) throws Exception;

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<RetailDailySettleVO> draftCacheVO) throws Exception;

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception;

    void exportExcel(OutputStream output, Long id, UserVO loginUser) throws Exception;

    RetailDailySettleExportVO getDailySettlePrint(UserVO loginUser, Long id) throws Exception;

    Page<List<RetailDailySettleModifyRecordVO>> getDailySettleModifyRecord(Integer pageNo, Integer pageSize, Long id, UserVO loginUser) throws Exception;

    void exportExcelModifyRecord(OutputStream output, Long id, UserVO loginUser) throws Exception;

    List<RetailDailySettleDetailVO> getWillDailySettleByStore(Long storeId, UserVO loginUser) throws Exception;

    List<DistrEnterpriseVO> getDirectSaleStore(UserVO loginUser) throws Exception;
}
