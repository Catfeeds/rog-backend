package com.rograndec.feijiayun.chain.business.member.integralexchange.dao;

import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchange;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangePageVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeRequestVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IntegralExchangeMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(IntegralExchange record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(IntegralExchange record);

    /**
     *
     * @mbg.generated
     */
    IntegralExchange selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IntegralExchange record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IntegralExchange record);

    /**
     * 获取明细
     * @param exchangeId
     * @return
     */
    IntegralExchangeVO selectDetailById(@Param("id") Long exchangeId);

    /**
     * 查询积分兑换分页
     * @param exchangeRequestVO
     * @return
     */
    List<IntegralExchangePageVO> selectExchangePage(IntegralExchangeRequestVO exchangeRequestVO);

    /**
     * 查询总数
     * @param exchangeRequestVO
     * @return
     */
    Map<String,Object> selectTotal(IntegralExchangeRequestVO exchangeRequestVO);
}