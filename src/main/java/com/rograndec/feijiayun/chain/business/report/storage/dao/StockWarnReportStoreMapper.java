package com.rograndec.feijiayun.chain.business.report.storage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnStoreGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnStoreGoodsVO;

public interface StockWarnReportStoreMapper {

	List<StockWarnStoreGoodsVO> selectStoreGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize,
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountStoreGoodsList(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockWarnStoreGoodsTotalVO queryStoreGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);


}
