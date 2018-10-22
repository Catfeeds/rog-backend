package com.rograndec.feijiayun.chain.business.keytable.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.keytable.entity.FinanceVoucher;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerQueryVO;

public interface FinanceVoucherMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinanceVoucher record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinanceVoucher record);

    /**
     *
     * @mbg.generated
     */
    FinanceVoucher selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinanceVoucher record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinanceVoucher record);

	List<FinanceVoucher> selectHeadquartersGeneralLedgerList(
			GeneralLedgerQueryVO vo, Long enterpriseId, int code,
			String accountCode);

	List<FinanceVoucher> selectDivisionGeneralLedgerList(
			GeneralLedgerQueryVO vo, Long enterpriseId, String accountCode);
}