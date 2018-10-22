package com.rograndec.feijiayun.chain.business.member.integralexchange.dao;

import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeDtl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralExchangeDtlMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(IntegralExchangeDtl record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(IntegralExchangeDtl record);

    /**
     *
     * @mbg.generated
     */
    IntegralExchangeDtl selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IntegralExchangeDtl record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IntegralExchangeDtl record);

    /**
     * 根据 积分兑换品种ID
     * @param id
     * @return
     */
    List<IntegralExchangeDtl> selectByExchangeGoodsId(@Param("id") Long id);
}