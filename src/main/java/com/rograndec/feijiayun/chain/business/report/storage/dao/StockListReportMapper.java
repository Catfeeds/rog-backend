package com.rograndec.feijiayun.chain.business.report.storage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchDtlTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchDtlVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchVO;

public interface StockListReportMapper {

	List<StockListResultBranchVO> selectStockListBySum(
			@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("parentId")Long parentId, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("order")String order, @Param("sort")String sort);

	Long queryCountStockListBySum(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockListResultBranchTotalVO queryStockListBySum(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);

	List<StockListResultBranchDtlVO> selectStockListDtlBySum(
			@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("parentId")Long parentId, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("order")String order, @Param("sort")String sort);

	Long queryCountStockListDtlBySum(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockListResultBranchDtlTotalVO queryStockListDtlBySum(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);

}
