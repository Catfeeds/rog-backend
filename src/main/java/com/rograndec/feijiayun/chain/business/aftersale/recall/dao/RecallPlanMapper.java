package com.rograndec.feijiayun.chain.business.aftersale.recall.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallPlan;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallPlanPageParamVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallPlanVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecallPlanMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecallPlan record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecallPlan record);

    /**
     *
     * @mbg.generated
     */
    RecallPlan selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecallPlan record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecallPlan record);

    List<RecallPlanVO> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("param") RecallPlanPageParamVO recallPlanPageParamVO);
}