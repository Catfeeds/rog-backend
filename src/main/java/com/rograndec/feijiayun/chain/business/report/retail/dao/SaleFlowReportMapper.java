package com.rograndec.feijiayun.chain.business.report.retail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryParentVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQueryDtlVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQuerySumVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchVO;

public interface SaleFlowReportMapper {

	List<SaleFlowListDateResultBranchVO> selectSaleFlowListByDateForBranch(
			@Param("vo")SaleFlowListDateQueryBranchVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId,
			@Param("order")String order, @Param("sort") String sort);

	Long queryCountSaleFlowListByDateForBranch(
			@Param("vo")SaleFlowListDateQueryBranchVO vo, @Param("enterpriseId")Long enterpriseId);

	SaleFlowListDateResultBranchTotalVO querySaleFlowListByDateForBranch(
			@Param("vo")SaleFlowListDateQueryBranchVO vo, @Param("enterpriseId")Long enterpriseId);

	List<SaleFlowListSumResultBranchVO> selectSaleFlowListBySumForBranch(
			@Param("vo")SaleFlowListSumQueryBranchVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountSaleFlowListBySumForBranch(@Param("vo")SaleFlowListSumQueryBranchVO vo,
			@Param("enterpriseId")Long enterpriseId);

	SaleFlowListSumResultBranchTotalVO querySaleFlowListBySumForBranch(
			@Param("vo")SaleFlowListSumQueryBranchVO vo, @Param("enterpriseId")Long enterpriseId);

	List<SaleFlowListDtlResultBranchVO> selectSaleFlowListByDtlForBranch(
			@Param("vo")SaleFlowListDtlQueryBranchVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountSaleFlowListByDtlForBranch(@Param("vo")SaleFlowListDtlQueryBranchVO vo,
			@Param("enterpriseId")Long enterpriseId);

	SaleFlowListDtlResultBranchTotalVO querySaleFlowListByDtlForBranch(
			@Param("vo")SaleFlowListDtlQueryBranchVO vo, @Param("enterpriseId")Long enterpriseId);

	List<SaleFlowListDateResultBranchVO> selectSaleFlowListByDateForParent(
			@Param("vo")SaleFlowListDateQueryParentVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountSaleFlowListByDateForParent(
			@Param("vo")SaleFlowListDateQueryParentVO vo, @Param("enterpriseId")Long enterpriseId);

	SaleFlowListDateResultBranchTotalVO querySaleFlowListByDateForParent(
			@Param("vo")SaleFlowListDateQueryParentVO vo, @Param("enterpriseId")Long enterpriseId);

	List<SaleFlowListSumResultBranchVO> selectSaleFlowListBySumForParent(
			@Param("vo")SaleFlowListSumQueryBranchVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountSaleFlowListBySumForParent(@Param("vo")SaleFlowListSumQueryBranchVO vo,
			@Param("enterpriseId")Long enterpriseId);

	SaleFlowListSumResultBranchTotalVO querySaleFlowListBySumForParent(
			@Param("vo")SaleFlowListSumQueryBranchVO vo, @Param("enterpriseId")Long enterpriseId);

	List<SaleFlowListDtlResultBranchVO> selectSaleFlowListByDtlForParent(
			@Param("vo")SaleFlowListDtlQueryBranchVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountSaleFlowListByDtlForParent(@Param("vo")SaleFlowListDtlQueryBranchVO vo,
			@Param("enterpriseId")Long enterpriseId);

	SaleFlowListDtlResultBranchTotalVO querySaleFlowListByDtlForParent(
			@Param("vo")SaleFlowListDtlQueryBranchVO vo, @Param("enterpriseId")Long enterpriseId);

	List<SaleFlowListSumResultBranchVO> selectSaleFlowListDoubleClickBySum(
			@Param("vo")SaleFlowListDoubleClickQuerySumVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountSaleFlowListDoubleClickBySum(
			@Param("vo")SaleFlowListDoubleClickQuerySumVO vo, @Param("enterpriseId")Long enterpriseId);

	SaleFlowListSumResultBranchTotalVO querySaleFlowListDoubleClickBySum(
			@Param("vo")SaleFlowListDoubleClickQuerySumVO vo, @Param("enterpriseId")Long enterpriseId);

	List<SaleFlowListDtlResultBranchVO> selectSaleFlowListDoubleClickByDtl(
			@Param("vo")SaleFlowListDoubleClickQueryDtlVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountSaleFlowListDoubleClickByDtl(
			@Param("vo")SaleFlowListDoubleClickQueryDtlVO vo, @Param("enterpriseId")Long enterpriseId);

	SaleFlowListDtlResultBranchTotalVO querySaleFlowListDoubleClickByDtl(
			@Param("vo")SaleFlowListDoubleClickQueryDtlVO vo, @Param("enterpriseId")Long enterpriseId);

}
