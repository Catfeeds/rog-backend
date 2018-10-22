package com.rograndec.feijiayun.chain.business.finance.set.period.service;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingFreshVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodFreshVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.FinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestAccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestFinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.ResponseFinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.YearAndMonthVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface AccountingPeriodService {

    Integer getPeriodYear(UserVO userVO);
    
    YearAndMonthVO getPeriodYearAndMonth(UserVO userVO);
    
    String getPeriodYearStartDate(UserVO userVO);
    
    void addPeriod(UserVO userVO, RequestAccountingPeriodVO requestAccountingPeriodVO);
    
    void updatePeriod(UserVO userVO, RequestAccountingPeriodVO requestAccountingPeriodVO);
    
    void getPeriodPage(UserVO userVO,Page<List<AccountingPeriodVO>> page);
    
    AccountingPeriodVO getPeriodDetail(UserVO userVO,Long id);
    
    void removePeriod(UserVO userVO,Long id);
    
    FinalSettleVO getFinalSettlePeriod(UserVO userVO);
    
    void addFinalSettle(UserVO userVO, RequestFinalSettleVO requestFinalSettleVO);
    
    List<ResponseFinalSettleVO> getFinalSettlePage(UserVO userVO,Page<List<ResponseFinalSettleVO>> page);
    
    List<User> getFinalSettleUser(UserVO userVO);
    
    void getPeriodDetailForFresh(UserVO userVO , AccountingFreshVO accountingFreshVO);
}
