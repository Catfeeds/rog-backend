package com.rograndec.feijiayun.chain.business.finance.set.period.dao;

import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriodDetail;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.AccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.AccountingPeriodVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AccountingPeriodDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AccountingPeriodDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AccountingPeriodDetail record);

    /**
     *
     * @mbg.generated
     */
    AccountingPeriodDetail selectByPrimaryKey(Long id);
    List<AccountingPeriodDetail> selectByPeriodIds(@Param("list") List<Long> list,@Param("status") Integer status);
    List<AccountingPeriodDetail> selectByPeriodId(@Param("periodId")Long periodId);
    List<AccountingPeriodDetail> selectByPeriodIdAndMonth(@Param("periodId")Long periodId,@Param("month")Integer month);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AccountingPeriodDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AccountingPeriodDetail record);
    
    /**
    *
    * 根据会计期间id删除会计期间详情
    */
   int deleteByPeriodId(Long id);

	List<AccountingPeriodDetailVO> selectByEnterpriseId(@Param("enterpriseId")Long enterpriseId,
			@Param("startDate")Date startDate, @Param("endDate")Date endDate);

    List<AccountingPeriodVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("status")Integer status);

    int selectByParams(@Param("enterpriseId")Long enterpriseId,@Param("date") Date date, @Param("status")Integer status);
}