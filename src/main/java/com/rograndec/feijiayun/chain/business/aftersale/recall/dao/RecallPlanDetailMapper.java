package com.rograndec.feijiayun.chain.business.aftersale.recall.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallPlanDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallPlanDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecallPlanDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecallPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecallPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    RecallPlanDetail selectByPrimaryKey(Long planId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecallPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecallPlanDetail record);

    int deleteByPlanId(@Param("planId") Long planId, @Param("enterpriseId") Long enterpriseId);

    List<RecallPlanDetailVO> selectByPlanId(@Param("planId") Long planId, @Param("enterpriseId") Long enterpriseId);

    int batchInsert(List<RecallPlanDetail> recallPlanDetails);

    List<RecallPlanDetailVO> selectByPlanIdByParam(@Param("planId") Long id, @Param("enterpriseId") Long enterpriseId, @Param("param") String param);
}