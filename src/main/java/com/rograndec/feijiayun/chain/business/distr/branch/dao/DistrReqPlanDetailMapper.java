package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlanDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanDetailPageVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReqPlanGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DistrReqPlanDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReqPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReqPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrReqPlanDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReqPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReqPlanDetail record);
    
    /**
     * 根据计划ID删除明细
     * */
    void deleteByPlanId(Long planId);
    
    /**
     * 根据计划ID修改明细的状态
     * */
    void updateStatusByPlanId(Map map);

    /**
     * 通过要货计划id列表，获取对应的明细列表
     * @param planIds
     * @return
     */
    List<DistrReqPlanGoodsVO> getDistrReqPlanGoodsList(@Param("list")List<Long> planIds, @Param("sort")String sort);

    List<DistrReqPlanDetail> selectByDistrReqPlanId(Long baseOrderId);

    List<DistrReqPlanDetailPageVO> selectByPlanId(@Param("planId")Long planId, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes,@Param("start")int start, @Param("pageSize")int pageSize);

    Long countByPlanId(@Param("planId")Long planId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes);

    int updateByPlanIdSelective(DistrReqPlanDetail reqPlanDetail);
}