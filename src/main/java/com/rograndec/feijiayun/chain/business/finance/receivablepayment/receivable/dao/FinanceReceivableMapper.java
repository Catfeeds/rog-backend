package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.dao;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.CollectMoneyPageTotal;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivable;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.ReceiveMoneyPageVO;

import java.util.List;
import java.util.Map;

public interface FinanceReceivableMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinanceReceivable record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinanceReceivable record);

    /**
     *
     * @mbg.generated
     */
    FinanceReceivable selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinanceReceivable record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinanceReceivable record);

    List<ReceiveMoneyPageVO> getReceivePage(Map<String, Object> map);

    Integer getTotalRecord(Map<String, Object> map);

    CollectMoneyPageTotal selectTotal(Map<String, Object> map);
}