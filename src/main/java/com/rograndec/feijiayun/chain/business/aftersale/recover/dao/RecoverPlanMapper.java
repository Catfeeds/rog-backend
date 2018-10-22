package com.rograndec.feijiayun.chain.business.aftersale.recover.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverPlan;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanVO;

import java.util.List;
import java.util.Map;

public interface RecoverPlanMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecoverPlan record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecoverPlan record);

    /**
     *
     * @mbg.generated
     */
    RecoverPlan selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecoverPlan record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecoverPlan record);

    /**
     * 根据条件搜索追回计划列表
     * @param map
     * @return
     */
    List<RecoverPlanVO> getRecoverPlanPage(Map<String, Object> map);

    /**
     * 通过id，查询追回计划
     * @param id 追回计划id
     * @return
     */
    RecoverPlanVO getRecoverPlanWithId(Long id);
}