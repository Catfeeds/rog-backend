package com.rograndec.feijiayun.chain.business.report.retail.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQueryDtlVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQuerySumVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SaleFlowReportBranchService {

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowPageByDateForBranch(SaleFlowListDateQueryBranchVO vo,
			UserVO loginUser);

	List<SaleFlowListDateResultBranchVO> selectSaleFlowListByDateForBranch(
			SaleFlowListDateQueryBranchVO vo, UserVO loginUser);

	void exportExcelSaleFlowListByDateForBranch(OutputStream output,
			List<SaleFlowListDateResultBranchVO> resultBranchVOList,
			UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowPageBySumForBranch(SaleFlowListSumQueryBranchVO vo,
			UserVO loginUser);

	List<SaleFlowListSumResultBranchVO> selectSaleFlowListBySumForBranch(
			SaleFlowListSumQueryBranchVO vo, UserVO loginUser);

	void exportExcelSaleFlowListBySumForBranch(OutputStream output,
			List<SaleFlowListSumResultBranchVO> resultBranchVOList,
			UserVO loginUser, SaleFlowListSumQueryBranchVO vo);

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowPageByDtlForBranch(SaleFlowListDtlQueryBranchVO vo,
			UserVO loginUser);

	List<SaleFlowListDtlResultBranchVO> selectSaleFlowListByDtlForBranch(
			SaleFlowListDtlQueryBranchVO vo, UserVO loginUser);

	void exportExcelSaleFlowListByDtlForBranch(OutputStream output,
			List<SaleFlowListDtlResultBranchVO> resultBranchVOList,
			UserVO loginUser, SaleFlowListDtlQueryBranchVO vo);

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowListDoubleClickBySum(
			SaleFlowListDoubleClickQuerySumVO vo, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowListDoubleClickByDtl(
			SaleFlowListDoubleClickQueryDtlVO vo, UserVO loginUser);

}
