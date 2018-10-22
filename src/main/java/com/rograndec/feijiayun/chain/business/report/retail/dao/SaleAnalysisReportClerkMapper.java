package com.rograndec.feijiayun.chain.business.report.retail.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickResultVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchVO;

public interface SaleAnalysisReportClerkMapper {

	public List<SaleAnalyClerkResultBranchVO> selectBranchClerkSaleListByDailyTime(
			@Param("vo")SaleAnalyClerkBranchQueryVO vo, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("parentId")Long parentId, @Param("order")String order, @Param("sort")String sort);

	public Long queryCountBranchClerkSaleListByDailyTime(
			@Param("vo")SaleAnalyClerkBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	public SaleAnalyClerkResultBranchTotalVO querySaleAnalysisReportClerkDailyTime(
			@Param("vo")SaleAnalyClerkBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	public Long querySaleAllQuantityByClerkNameAndDailyTime(@Param("vo")SaleAnalyClerkBranchQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId, 
			@Param("saleType")Integer saleType);

	public Long querySaleQuantityByClerkNameAndDailyTime(@Param("clerkId")Long clerkId,
		    @Param("enterpriseId")Long enterpriseId, @Param("startDate")Date startDate, 
		    @Param("endDate")Date endDate, @Param("saleType")Integer saleType);

	public List<SaleAnalyClerkResultBranchVO> selectBranchClerkSaleListBySaleTime(
			@Param("vo")SaleAnalyClerkBranchQueryVO vo,  @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("parentId")Long parentId, @Param("order")String order, @Param("sort")String sort);

	public Long queryCountBranchClerkSaleListBySaleTime(
			@Param("vo")SaleAnalyClerkBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);
	
	public SaleAnalyClerkResultBranchTotalVO querysaleAnalysisReportClerkSaleTime(
			@Param("vo")SaleAnalyClerkBranchQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);
	
	public Long querySaleQuantityByClerkNameAndSaleTime(@Param("clerkId")Long clerkId,
		    @Param("enterpriseId")Long enterpriseId, @Param("startDate")Date startDate, 
		    @Param("endDate")Date endDate, @Param("saleType")Integer saleType);

	public Long querySaleAllQuantityByClerkNameAndSaleTime(@Param("vo")SaleAnalyClerkBranchQueryVO vo, 
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId, 
			@Param("saleType")Integer saleType);

	public List<SaleAnalyClerkBranchDoubleClickResultVO> selectDoubleClickClerkSaleListByDailyTime(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("start")Integer start,
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("clerkId")Long parentId, @Param("order")String order, @Param("sort")String sort);

	public Long querySaleQuantityByClerkIdAndDailyTime(@Param("clerkId")Long clerkId,
			@Param("enterpriseId")Long enterpriseId, @Param("dailyTime")Date dailyTime, 
			@Param("saleType")Integer saleType);

	public Long queryCountDoubleClickClerkSaleListByDailyTime(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("clerkId")Long clerkId);

	public SaleAnalyClerkBranchDoubleClickResultTotalVO queryDoubleClickClerkSaleListByDailyTime(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("clerkId")Long clerkId);

	public Long queryDoubleClickAllQuantityByDailyTime(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("clerkId")Long clerkId, @Param("saleType")Integer saleType);

	public List<SaleAnalyClerkBranchDoubleClickResultVO> selectDoubleClickClerkSaleListBySaleDate(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("start")Integer start,
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("clerkId")Long parentId, @Param("order")String order, @Param("sort")String sort);

	public Long querySaleQuantityByClerkIdAndSaleDate(@Param("clerkId")Long clerkId,
			@Param("enterpriseId")Long enterpriseId, @Param("saleDate")Date saleDate, 
			@Param("saleType")Integer saleType);

	public Long queryCountDoubleClickClerkSaleListBySaleDate(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("clerkId")Long clerkId);

	public SaleAnalyClerkBranchDoubleClickResultTotalVO queryDoubleClickClerkSaleListBySaleDate(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("clerkId")Long clerkId);

	public Long queryDoubleClickAllQuantityBySaleDate(
			@Param("vo")SaleAnalyClerkBranchDoubleClickQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("clerkId")Long clerkId, @Param("saleType")Integer saleType);

	

	

}
