package com.rograndec.feijiayun.chain.business.report.storage.dao;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnNearPeriodGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnNearPeriodGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockWarnReportNearPeriodMapper {

	List<StockWarnNearPeriodGoodsVO> selectNearPeriodGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize,
			@Param("order")String order, @Param("sort")String sort);

	List<StockWarnNearPeriodGoodsVO> selectNearPeriodGoods2WarnSet(@Param("enterpriseId")Long enterpriseId,
			@Param("ownerId")Long ownerId, @Param("warnDays")Integer warnDays);

	Long queryCountNearPeriodGoodsList(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockWarnNearPeriodGoodsTotalVO queryNearPeriodGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);


}
