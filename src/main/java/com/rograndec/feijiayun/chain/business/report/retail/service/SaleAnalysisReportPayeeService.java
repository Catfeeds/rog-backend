package com.rograndec.feijiayun.chain.business.report.retail.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SaleAnalysisReportPayeeService {

	@SuppressWarnings("rawtypes")
	Page selectBranchPayeeSalePageByDailyTime(SaleAnalyPayeeBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyPayeeResultBranchVO> selectBranchPayeeSaleListByDailyTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser);

	void exportExcelBranchPayeeSaleListByDailyTime(OutputStream output,
			List<SaleAnalyPayeeResultBranchVO> resultBranchVOList,
			UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectBranchPayeeSalePageBySaleTime(SaleAnalyPayeeBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyPayeeResultBranchVO> selectBranchPayeeSaleListBySaleTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectParentPayeeSalePageByDailyTime(SaleAnalyPayeeBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyPayeeResultBranchVO> selectParentPayeeSaleListByDailyTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser);

	void exportExcelParentPayeeSaleListByDailyTime(OutputStream output,
			List<SaleAnalyPayeeResultBranchVO> resultBranchVOList,
			UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectParentPayeeSalePageBySaleTime(SaleAnalyPayeeBranchQueryVO vo,
			UserVO loginUser);

	List<SaleAnalyPayeeResultBranchVO> selectParentPayeeSaleListBySaleTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectDoubleClickPayeeSalePageBySaleDate(
			SaleAnalyPayeeBranchDoubleClickQueryVO vo, UserVO loginUser);

	Page selectDoubleClickPayeeSalePageByDailyTime(
			SaleAnalyPayeeBranchDoubleClickQueryVO vo, UserVO loginUser);

}
