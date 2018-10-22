package com.rograndec.feijiayun.chain.business.finance.set.period.dao;

import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriod;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.FinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.ResponseFinalSettleVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountingPeriodMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AccountingPeriod record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AccountingPeriod record);

    /**
     *
     * @mbg.generated
     */
    AccountingPeriod selectByPrimaryKey(Long id);

    List<AccountingPeriod> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId,  @Param("status") Integer status);
    AccountingPeriod selectByEnterpriseIdAndYear(@Param("enterpriseId") Long enterpriseId, @Param("year") Integer year, @Param("status") Integer status);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AccountingPeriod record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AccountingPeriod record);
    
    Integer getPeriodYear(@Param("enterpriseId") Long enterpriseId);
    
    Date getPeriodYearStartDate(@Param("enterpriseId") Long enterpriseId);
    
    Integer getTotalPeriodPage(Map<String,Object> map);
    
    List<Long> getPeriodPage(Map<String,Object> map);
    
    List<AccountingPeriodVO> getPeriodList(List<Long> list);
    
    List<Long> getEnterpriseIdByFinaceControl(@Param("enterpriseId") Long enterpriseId);
    
    Date getNearOrderDate(List<Long> list);
    
    Date getNearFinalSettleDate(@Param("enterpriseId") Long enterpriseId);
    
    Long getPeriodId(@Param("year") Integer year,@Param("enterpriseId") Long enterpriseId);
    
    FinalSettleVO getFinalSettlePeriod(@Param("enterpriseId") Long enterpriseId);
    
    List<ResponseFinalSettleVO> getFinalSettlePage(Map<String,Object> map);
    
    Integer getFinalSettleTotalRecord(Map<String,Object> map);
    
    Long getNearestPeriodId(@Param("enterpriseId") Long enterpriseId);
    
}