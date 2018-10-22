package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.dao;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common.ReceivableStatus;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivableDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceReceivableDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinanceReceivableDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinanceReceivableDetail record);

    /**
     *
     * @mbg.generated
     */
    FinanceReceivableDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinanceReceivableDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinanceReceivableDetail record);

    List<FinanceReceivableDetail> selectByReceivableId(Long id);

    void updateStatus(@Param("id") Long id, @Param("status") int status);
}