package com.rograndec.feijiayun.chain.business.retail.dailysettle.service;

import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.DailySettleStoreVO;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.DailySettleVO;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.SaveShowDailyInfo;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.WillDailySettleVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by madong on 2017/9/19.
 */
public interface DailySettleService {
    Page<List<DailySettleStoreVO>> getDailySettle(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception;

    Page<List<DailySettleStoreVO>> getDailySettleStore(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception;

    Page<List<WillDailySettleVO>> getWillDailySettle(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception;

    Page<List<WillDailySettleVO>> getWillDailySettleStore(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception;

    List<SaveShowDailyInfo> showDailySettle(UserVO loginUser, String ids, Long settleType ,Long enterpriseId, String orderName,String orderType) throws Exception;

    int saveDailySettle(UserVO loginUser, List<SaveShowDailyInfo> saveShowDailyInfos) throws Exception;

    void export(OutputStream output, UserVO loginUser, String ids);
}
