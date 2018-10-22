package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.FinanceBalance;
import java.util.Map;

public interface FinanceBalanceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinanceBalance record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinanceBalance record);

    /**
     *
     * @mbg.generated
     */
    FinanceBalance selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinanceBalance record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinanceBalance record);

    FinanceBalance selectByParamMap(Map<String, Object> paramMap);
}