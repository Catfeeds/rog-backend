package com.rograndec.feijiayun.chain.business.report.retail.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickDetailQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SaleAnalysisReportClerkService {

	@SuppressWarnings("rawtypes")
	Page selectBranchClerkSalePageByDailyTime(SaleAnalyClerkBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyClerkResultBranchVO> selectClerkSaleListByDailyTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser);

	void exportExcelForClerkSaleListByDailyTime(OutputStream output,
			List<SaleAnalyClerkResultBranchVO> resultBranchVOList,
			UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectBranchClerkSaleListBySaleTime(SaleAnalyClerkBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyClerkResultBranchVO> selectClerkSaleListBySaleTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectParentClerkSalePageByDailyTime(SaleAnalyClerkBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyClerkResultBranchVO> selectParentClerkSaleListByDailyTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser);

	void exportExcelParentClerkSaleListByDailyTime(OutputStream output,
			List<SaleAnalyClerkResultBranchVO> resultBranchVOList,
			UserVO loginUser);

	Page selectParentClerkSalePageBySaleTime(SaleAnalyClerkBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyClerkResultBranchVO> selectParentClerkSaleListBySaleTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser);

	Page selectDoubleClickClerkSalePageByDailyTime(
			SaleAnalyClerkBranchDoubleClickQueryVO vo, UserVO loginUser);

	Page selectDoubleClickClerkSalePageBySaleDate(
			SaleAnalyClerkBranchDoubleClickQueryVO vo, UserVO loginUser);

	Page selectDoubleClickDetailClerkSalePageByDailyTime(
			SaleAnalyClerkBranchDoubleClickDetailQueryVO vo, UserVO loginUser);

}
