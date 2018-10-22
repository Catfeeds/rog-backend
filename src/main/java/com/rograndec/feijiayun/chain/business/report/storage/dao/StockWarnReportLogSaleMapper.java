package com.rograndec.feijiayun.chain.business.report.storage.dao;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnLagSaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnLagSaleGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockWarnReportLogSaleMapper {

	List<StockWarnLagSaleGoodsVO> selectLogSaleGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize,
			@Param("order")String order, @Param("sort")String sort);

	List<StockWarnLagSaleGoodsVO> selectLogSaleGoods2WarnSet(@Param("ownerId")Long ownerId,
			@Param("enterpriseId")Long enterpriseId,@Param("warnDays")Integer warnDays);

	Long queryCountLogSaleGoodsList(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockWarnLagSaleGoodsTotalVO queryLogSaleGoodsList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);


}
