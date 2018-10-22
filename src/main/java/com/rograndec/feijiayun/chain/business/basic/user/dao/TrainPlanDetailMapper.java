package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.TrainPlanDetail;
import com.rograndec.feijiayun.chain.business.basic.user.vo.ClickTrainPlanDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrainPlanDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(TrainPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TrainPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    TrainPlanDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TrainPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TrainPlanDetail record);

    List<ClickTrainPlanDetailVO> selectByTrainPlanId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    ClickTrainPlanDetailVO selectPositionByUserId(@Param("userId")Long userId, @Param("enterpriseId")Long enterpriseId);

    ClickTrainPlanDetailVO selectDeptByUserId(@Param("userId")Long userId, @Param("enterpriseId")Long enterpriseId);

    String selectByPositionIds(@Param("positionId")String[] positionId, @Param("enterpriseId")Long enterpriseId);

    String selectByDeptIds(@Param("deptId")String[] deptId, @Param("enterpriseId")Long enterpriseId);
}