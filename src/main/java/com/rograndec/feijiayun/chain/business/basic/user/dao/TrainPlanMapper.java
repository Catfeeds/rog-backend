package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.TrainPlan;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.vo.*;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TrainPlanMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(TrainPlan record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TrainPlan record);

    /**
     *
     * @mbg.generated
     */
    TrainPlan selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TrainPlan record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TrainPlan record);

    Long queryCountByWaitTrainPlanPageParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                             @Param("endTimes")String endTimes, @Param("code")String code, @Param("plannerName")String plannerName, @Param("planYear")Long planYear,
                                             @Param("planTitle")String planTitle, @Param("trainType")Long trainType, @Param("trainContent")Long trainContent, @Param("type")Long type, @Param("chainType")Integer chainType);

    List<WaitTrainPlanPageVO> selectByWaitTrainPlanPageParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                                              @Param("endTimes")String endTimes, @Param("code")String code, @Param("plannerName")String plannerName, @Param("planYear")Long planYear,
                                                              @Param("planTitle")String planTitle, @Param("trainType")Long trainType, @Param("trainContent")Long trainContent, @Param("type")Long type, @Param("orderName")String orderName, @Param("orderType")String orderType, @Param("chainType")Integer chainType);

    CheckWaitTrainPlanVO selectByCheckWaitTrainPlanPageParams(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    Department selectByDeptIds(@Param("deptId")String[] deptId, @Param("enterpriseId")Long enterpriseId);

    Position selectByPositionIds(@Param("positionId")String[] positionId, @Param("enterpriseId")Long enterpriseId);

    User selectByUserIds(@Param("userId")String[] userId, @Param("enterpriseId")Long enterpriseId);

    ClickWaitTrainPlanHeadVO selectByClickWaitTrainPlanHeadParams(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<EnterprisePageVO> selectEnterprise(@Param("enterpriseId")Long enterpriseId, @Param("chainType")Long chainType, @Param("key")String values, @Param("start")int start, @Param("pageSize")int pageSize);

    Long queryCountEnterprise(@Param("enterpriseId")Long enterpriseId, @Param("chainType")Long chainType, @Param("key")String values);

    List<ResponseTrainPlanVO> getTrainPlanReport(Map<String,Object> map);
    
    Integer getTrainPlanReportTotalNum(Map<String,Object> map);
    
    List<ResponseTrainPlanDetailVO> getTrainRecordReportDtlList(Map<String,Object> map);
    
    Integer getTrainRecordReportDtlListTotalNum(Map<String,Object> map);

}