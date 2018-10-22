package com.rograndec.feijiayun.chain.business.report.storage.dao;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnGoodsMaintanceTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockWarnGoodsMaintanceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockWarnReportGoodsMaintanceMapper {

	List<StockWarnGoodsMaintanceVO> selectGoodsMaintanceList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize,
			@Param("order")String order, @Param("sort")String sort);

	List<StockWarnGoodsMaintanceVO> selectGoodsMaintance2WarSet(
			@Param("enterpriseId")Long enterpriseId, @Param("ownerId")Long ownerId,
			@Param("warnDays")Integer warnDays);

	Long queryCountGoodsMaintanceList(@Param("vo")StockListQueryVO vo, @Param("enterpriseId")Long enterpriseId,
			@Param("parentId")Long parentId);

	StockWarnGoodsMaintanceTotalVO queryGoodsMaintanceList(@Param("vo")StockListQueryVO vo,
			@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);

	List<StockWarnGoodsMaintanceVO> selectParertGoodsMaintanceList(@Param("vo")StockListQueryVO vo,
			@Param("parentId")Long parentId,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize,
			@Param("order")String order, @Param("sort")String sort);

	Long queryCountParertGoodsMaintanceList(@Param("vo")StockListQueryVO vo, 
			@Param("parentId")Long parentId);

	StockWarnGoodsMaintanceTotalVO queryParertGoodsMaintanceList(@Param("vo")StockListQueryVO vo,
			@Param("parentId")Long parentId);


}
