package com.rograndec.feijiayun.chain.business.report.storage.dao;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnExpireGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnExpireGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StockWarnReportMapper {

	List<StockWarnExpireGoodsVO> selectExpireGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize,
			@Param("order")String order, @Param("sort")String sort);

	List<StockWarnExpireGoodsVO> selectExpireGoods2WarnSet(@Param("nowDate")Date nowDate,
			@Param("enterpriseId")Long enterpriseId, @Param("ownerId")Long ownerId);

	Long queryCountExpireGoodsList(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockWarnExpireGoodsTotalVO queryExpireGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);


}
