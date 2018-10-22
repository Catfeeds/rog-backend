package com.rograndec.feijiayun.chain.business.basic.user.dao;

import java.util.Map;

import com.rograndec.feijiayun.chain.business.basic.user.entity.HealthCheckDetail;

public interface HealthCheckDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(HealthCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(HealthCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    HealthCheckDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HealthCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HealthCheckDetail record);
    
    
    /**
     * 根据ID删除明细
     * */
    void deleteByCheckId(Long planId);
    
    /**
     * 根据ID修改明细的状态
     * */
    void updateStatusByCheckId(Map map);
    
    
    
}