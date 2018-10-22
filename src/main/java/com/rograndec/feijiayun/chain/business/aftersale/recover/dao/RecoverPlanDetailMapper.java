package com.rograndec.feijiayun.chain.business.aftersale.recover.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverPlanDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanDetailVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecoverPlanDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecoverPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecoverPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    RecoverPlanDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecoverPlanDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecoverPlanDetail record);

    /**
     * 通过追回计划查询追回明细列表
     * @param planId 追回计划ID
     * @return
     */
    List<RecoverPlanDetailVO> getRecoverPlanDetailWithPlanId(@Param("planId")Long planId, @Param("param")String param);

    /**
     * 通过追回计划查询追回明细列表
     * @param planId
     * @return
     */
    List<RecoverPlanDetail> getRecoverPlanDetails(Long planId);

    /**
     * 删除追回计划下的所有明细
     * @param planId
     * @return
     */
    int deleteDetailWithPlanId(Long planId);

    /**
     * 通过追回计划ID,商品ID，批号ID,获取明细信息
     * @param planId
     * @param goodsId
     * @param lotId
     * @return
     */
    RecoverPlanDetail getRecoverPlanDetailWithPlanIdGoodsIdLotId(@Param("planId")Long planId, @Param("goodsId")Long goodsId, @Param("lotId")Long lotId);
}