package com.rograndec.feijiayun.chain.business.goods.sale.service;

import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionStrategyInfoVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by madong on 2017/9/5.
 */
public interface CommissionStrategyService {
    int saveRoyaltyStrategy(CommissionStrategyInfoVO strategyInfoVO, UserVO loginUser) throws Exception;

    int updateRoyaltyStrategy(CommissionStrategyInfoVO strategyInfoVO, UserVO loginUser) throws Exception;

    int deleteRoyaltyStrategy(Long id, UserVO loginUser) throws Exception;

    List<CommissionStrategyInfoVO> getRoyaltyStrategy(String orderName, String orderType, UserVO loginUser) throws Exception;

    boolean canDeleteRoyaltyStrategy(Long id, UserVO loginUser) throws Exception;

    Long checkExistsCode(UserVO loginUser, String code) throws Exception ;

    Long checkExistsName(UserVO loginUser, String name) throws Exception;
}
