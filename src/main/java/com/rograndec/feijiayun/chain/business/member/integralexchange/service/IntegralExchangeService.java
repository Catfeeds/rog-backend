package com.rograndec.feijiayun.chain.business.member.integralexchange.service;

import com.rograndec.feijiayun.chain.business.member.info.vo.SimpleMemberInfoVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangePageVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeRequestVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeReturnVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by dudy on 2017/9/23.
 */
public interface IntegralExchangeService {
    Result<String> save(IntegralExchangeVO integralExchangeVO, UserVO userVO)  throws Exception;

    IntegralExchangeVO selectDetailById(Long exchangeId);

    /**
     *获取积分兑换分页
     * @param exchangeRequestVO
     * @param loginUser
     * @return
     */
    Page<IntegralExchangeReturnVO> getIntegralExchangePage(IntegralExchangeRequestVO exchangeRequestVO, UserVO loginUser);

    /**
     * 获取简单会员信息
     * @param param
     * @param loginUser
     * @return
     */
    List<SimpleMemberInfoVO> getSimpleMemberInfo(String param, UserVO loginUser);
}
