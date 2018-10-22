package com.rograndec.feijiayun.chain.business.basic.user.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.basic.user.entity.HealthCheck;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.EnterpriseReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.GoodsDistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckVO;

public interface HealthCheckMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(HealthCheck record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(HealthCheck record);

    /**
     *
     * @mbg.generated
     */
    HealthCheck selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HealthCheck record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HealthCheck record);
    
    
    /**
     * 获取健康检查详情
     * */
    HealthCheckVO selectById(Long id);
    
    /**
     * 获取健康检查列表
     * */
    List<HealthCheckVO> selectList(Map map);
    
    
    Long selectCount(Map map);
    
    /**
     * 获取员工信息
     * */
    List<HealthCheckUserVO> selectUserMessage(Long enterpriseId);
    
    /**
     * 获取健康检查计划列表
     * @param map
     * @return
     */
    List<ResponseHealthCheckVO> getHealthCheckReportList(Map<String,Object> map);
    
    Integer getHealthCheckReportListTotalNum(Map<String,Object> map);
    
    /**
     * 获取健康检查单详情
     * @param map
     * @return
     */
    List<ResponseHealthCheckDetailVO> getHealthCheckReportDetlList(Map<String,Object> map);
    		
    Integer getHealthCheckReportDetlListTotalNum(Map<String,Object> map);	
    
    ResponseHealthCheckVO getHealthCheckReportById(Long id);
    
}