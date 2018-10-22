package com.rograndec.feijiayun.chain.business.report.retail.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryParentVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SaleFlowReportParentService {

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowPageByDateForParent(SaleFlowListDateQueryParentVO vo,
			UserVO loginUser);

	List<SaleFlowListDateResultBranchVO> selectSaleFlowListByDateForParent(
			SaleFlowListDateQueryParentVO vo, UserVO loginUser);

	void exportExcelSaleFlowListByDateForParent(OutputStream output,
			List<SaleFlowListDateResultBranchVO> resultBranchVOList,
			UserVO loginUser);

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowPageBySumForParent(SaleFlowListSumQueryBranchVO vo,
			UserVO loginUser);

	List<SaleFlowListSumResultBranchVO> selectSaleFlowListBySumForParent(
			SaleFlowListSumQueryBranchVO vo, UserVO loginUser);

	void exportExcelSaleFlowListBySumForParent(OutputStream output,
			List<SaleFlowListSumResultBranchVO> resultBranchVOList,
			UserVO loginUser, SaleFlowListSumQueryBranchVO vo);

	@SuppressWarnings("rawtypes")
	Page selectSaleFlowPageByDtlForParent(SaleFlowListDtlQueryBranchVO vo,
			UserVO loginUser);

	List<SaleFlowListDtlResultBranchVO> selectSaleFlowListByDtlForBranch(
			SaleFlowListDtlQueryBranchVO vo, UserVO loginUser);

	void exportExcelSaleFlowListByDtlForBranch(OutputStream output,
			List<SaleFlowListDtlResultBranchVO> resultBranchVOList,
			UserVO loginUser, SaleFlowListDtlQueryBranchVO vo);

}
