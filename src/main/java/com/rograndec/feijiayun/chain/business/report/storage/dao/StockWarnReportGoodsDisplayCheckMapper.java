package com.rograndec.feijiayun.chain.business.report.storage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnGoodsMaintanceTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnGoodsMaintanceVO;

public interface StockWarnReportGoodsDisplayCheckMapper {

	List<StockWarnGoodsMaintanceVO> selectGoodsDisplayCheckList(
			@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId, 
			@Param("start")Integer start,
			@Param("pageSize")Integer pageSize, @Param("order")String order, @Param("sort")String sort);

	Long queryCountGoodsDisplayCheckList(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId);

	StockWarnGoodsMaintanceTotalVO queryGoodsDisplayCheckList(
			@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId);

}
