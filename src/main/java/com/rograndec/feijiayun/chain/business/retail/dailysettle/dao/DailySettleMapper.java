package com.rograndec.feijiayun.chain.business.retail.dailysettle.dao;

import com.rograndec.feijiayun.chain.business.retail.dailysettle.entity.DailySettle;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.DailySettleStoreVO;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.DailySettleVO;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.WillDailySettleDetailVO;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.WillDailySettleVO;

import java.util.List;
import java.util.Map;

public interface DailySettleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DailySettle record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DailySettle record);

    /**
     *
     * @mbg.generated
     */
    DailySettle selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DailySettle record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DailySettle record);

    List<DailySettleStoreVO> getDailySettle(Map param);

    List<DailySettleStoreVO> getDailySettleStore(Map param);

    List<WillDailySettleVO> getWillDailySettleStore(Map param);

    List<WillDailySettleVO> getWillDailySettle(Map param);

    List<WillDailySettleDetailVO> selectwillDailySettleDetail(Map param);

    List<WillDailySettleDetailVO> selectDailySettleDetail(Map param);
}