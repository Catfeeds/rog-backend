package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettle;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.DailtSettleRequestPageParamVO;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.RetailDailySettleReponseVO;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.RetailDailySettleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetailDailySettleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailDailySettle record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailDailySettle record);

    /**
     *
     * @mbg.generated
     */
    RetailDailySettle selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailDailySettle record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailDailySettle record);

    List<RetailDailySettleVO> selectByPageParam(@Param("param") DailtSettleRequestPageParamVO param);

    List<RetailDailySettleVO> selectCountByPageParam(@Param("param") DailtSettleRequestPageParamVO param);

    List<RetailDailySettle> selectByIds(List<Long> settleId);

    void updateStatus(@Param("settleId") Long settleId, @Param("status") Integer status);

    List<RetailDailySettle> selectByEnterpriseId(Long enterpriseId);

    List<RetailDailySettle> selectByEnterpriseIdByStatus(@Param("enterpriseId") Long enterpriseId, @Param("status") Integer status);

    List<RetailDailySettle> selectBySettleDate(@Param("settleDate") String settleDate, @Param("enterpriseId") Long enterpriseId);
}