package com.rograndec.feijiayun.chain.business.report.retail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickResultVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeResultBranchVO;

public interface SaleAnalysisReportPayeeMapper {

	List<SaleAnalyPayeeResultBranchVO> selectBranchPayeeSaleListByDailyTime(
			@Param("vo")SaleAnalyPayeeBranchQueryVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("parentId")Long parentId, @Param("order")String order, @Param("sort")String sort);

	Long queryCountBranchPayeeSaleListByDailyTime(
			@Param("vo")SaleAnalyPayeeBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	SaleAnalyPayeeResultBranchTotalVO querySaleAnalysisReportPayeeDailyTime(
			@Param("vo")SaleAnalyPayeeBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	List<SaleAnalyPayeeResultBranchVO> selectBranchPayeeSaleListBySaleTime(
			@Param("vo")SaleAnalyPayeeBranchQueryVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("parentId")Long parentId, @Param("order")String order, @Param("sort")String sort);

	Long queryCountBranchPayeeSaleListBySaleTime(
			@Param("vo")SaleAnalyPayeeBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	SaleAnalyPayeeResultBranchTotalVO querySaleAnalysisReportPayeeSaleTime(
			@Param("vo")SaleAnalyPayeeBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	List<SaleAnalyPayeeBranchDoubleClickResultVO> selectDoubleClickPayeeSaleListBySaleDate(
			@Param("vo")SaleAnalyPayeeBranchDoubleClickQueryVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("payeeId")Long payeeId, @Param("order")String order, @Param("sort")String sort);

	Long queryCountDoubleClickPayeeSaleListBySaleDate(
			@Param("vo")SaleAnalyPayeeBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("payeeId")Long payeeId);

	SaleAnalyPayeeBranchDoubleClickResultTotalVO queryDoubleClickPayeeSaleListBySaleDate(
			@Param("vo")SaleAnalyPayeeBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("payeeId")Long payeeId);

	List<SaleAnalyPayeeBranchDoubleClickResultVO> selectDoubleClickPayeeSaleListByDailyTime(
			@Param("vo")SaleAnalyPayeeBranchDoubleClickQueryVO vo, @Param("start")Integer start,
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("payeeId")Long payeeId, @Param("order")String order,
			@Param("sort")String sort);

	SaleAnalyPayeeBranchDoubleClickResultVO queryDoubleClickResultVODataByShiftId(Long id);

	Long queryCountDoubleClickPayeeSaleListByDailyTime(
			@Param("vo")SaleAnalyPayeeBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("payeeId")Long payeeId);

	SaleAnalyPayeeBranchDoubleClickResultTotalVO queryDoubleClickPayeeSaleListByDailyTime(
			@Param("vo")SaleAnalyPayeeBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("payeeId")Long payeeId);

}
