package com.rograndec.feijiayun.chain.business.report.storage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnLackGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnLackGoodsVO;

public interface StockWarnReportLackMapper {

	List<StockWarnLackGoodsVO> selectLackGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize,
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountLackGoodsList(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockWarnLackGoodsTotalVO queryLackGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);


}
