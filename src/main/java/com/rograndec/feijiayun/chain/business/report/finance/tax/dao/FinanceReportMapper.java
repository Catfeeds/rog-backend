package com.rograndec.feijiayun.chain.business.report.finance.tax.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceBalanceQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceBalanceResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailDtlResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerDeatilResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerNewResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerQueryVO;

public interface FinanceReportMapper {

	List<FinanceBalanceResultVO> selectHeadquartersFinanceBalanceList(
			@Param("vo")FinanceBalanceQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("chainType")Integer chainType, @Param("accountCode")String accountCode,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Long queryCountHeadquartersFinanceBalance(@Param("vo")FinanceBalanceQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType,
			@Param("accountCode")String accountCode);

	List<FinanceBalanceResultVO> selectDivisionFinanceBalanceList(
			@Param("vo")FinanceBalanceQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("accountCode")String accountCode,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Long queryCountDivisionFinanceBalance(@Param("vo")FinanceBalanceQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("accountCode")String accountCode);

	List<GeneralLedgerDeatilResultVO> selectHeadquartersGeneralLedgerList(
			@Param("startDate")Date startDate, @Param("endDate")Date endDate, 
			@Param("enterpriseId")Long enterpriseId,
			@Param("chainType")Integer chainType, @Param("accountCode")String accountCode,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Long queryCountHeadquartersGeneralLedger(@Param("vo")GeneralLedgerQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType,
			@Param("accountCode")String accountCode);

	List<GeneralLedgerDeatilResultVO> selectDivisionGeneralLedgerList(
			@Param("startDate")Date startDate, @Param("endDate")Date endDate, 
			@Param("enterpriseId")Long enterpriseId,
			@Param("accountCode")String accountCode,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Long queryCountDivisionGeneralLedger(@Param("vo")GeneralLedgerQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("accountCode")String accountCode);

	List<FinanceBalanceResultVO> selectHeadquartersAccountSubList(
			@Param("vo")FinanceDetailQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("chainType")Integer chainType, @Param("accountCode")String accountCode, 
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	List<FinanceBalanceResultVO> selectDivisionAccountSubList(
			@Param("vo")FinanceDetailQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("accountCode")String accountCode,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	List<FinanceDetailDtlResultVO> selectHeadquartersFinanceDetailList(
			@Param("vo")FinanceDetailQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("chainType")Integer chainType,
			@Param("accountCode")String taxType, @Param("subAccountId")Long taxId);

	List<FinanceDetailDtlResultVO> selectDivisionFinanceDetailList(
			@Param("vo")FinanceDetailQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("accountCode")String taxType, @Param("subAccountId")Long taxId);

	List<FinanceVoucherResultVO> selectHeadquartersFinanceVoucherList(
			@Param("vo")FinanceVoucherQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("chainType")Integer chainType, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize);

	Long queryCountHeadquartersFinanceVoucher(@Param("vo")FinanceVoucherQueryVO vo, 
			@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType);

	List<FinanceVoucherResultVO> selectDivisionFinanceVoucherList(
			@Param("vo")FinanceVoucherQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Long queryCountDivisionFinanceVoucher(@Param("vo")FinanceVoucherQueryVO vo,
			@Param("enterpriseId")Long enterpriseId);

	List<FinanceDetailDtlResultVO> selectHeadquartersFinanceDetaiNewlList(
			@Param("vo")FinanceDetailQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("chainType")Integer chainType, @Param("accountCode")String accountCode, 
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Long queryCountHeadquartersFinanceDetaiNewlList(@Param("vo")FinanceDetailQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType,
			@Param("accountCode")String accountCode);

	FinanceDetailDtlResultVO getSumAmount(@Param("vo")FinanceDetailQueryVO vo,
			@Param("startDate")Date startDate, @Param("endDate")Date endDate, 
			@Param("accountCode")String accountCode, @Param("enterpriseId")Long enterpriseId, 
			@Param("chainType")Integer chainType, @Param("result")FinanceDetailDtlResultVO resultvo);

	
}
