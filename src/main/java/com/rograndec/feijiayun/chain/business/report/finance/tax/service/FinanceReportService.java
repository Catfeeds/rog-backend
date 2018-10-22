package com.rograndec.feijiayun.chain.business.report.finance.tax.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceBalanceQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailDtlResultPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerDeatilResultPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerQueryVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface FinanceReportService {

	@SuppressWarnings("rawtypes")
	Page selectFinanceBalanceList(FinanceBalanceQueryVO vo, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectGeneralLedgerList(GeneralLedgerQueryVO vo, UserVO loginUser);
	
	List<GeneralLedgerDeatilResultPrintVO> selectGeneralLedgerPrintList(
			Date startDate, Date endDate, UserVO userVO);

	@SuppressWarnings("rawtypes")
	Page selectFinanceDetailList(FinanceDetailQueryVO vo, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectFinanceVoucherList(FinanceVoucherQueryVO vo, UserVO loginUser);

	void exportExcelFinanceBalance(OutputStream output, UserVO userVO);

	void exportExcelGeneralLedger(OutputStream output, Date startDate, 
			Date endDate, UserVO userVO);

	void exportExcelFinanceDetail(OutputStream output, FinanceDetailQueryVO vo,
			UserVO userVO);

	void exportExcelFinanceVoucherList(OutputStream output,
			FinanceVoucherQueryVO vo, UserVO userVO);

	List<SelectBean> getFinanceAccountSelectBean();

	@SuppressWarnings("rawtypes")
	Page selectFinanceDetailNewList(FinanceDetailQueryVO vo, UserVO loginUser);

	List<FinanceDetailDtlResultPrintVO> selectFinanceDetailNewPrintList(
			FinanceDetailQueryVO vo, UserVO userVO);

	List<NewSelectBean> getOrderTypeSelectBean(UserVO loginUser);



}
