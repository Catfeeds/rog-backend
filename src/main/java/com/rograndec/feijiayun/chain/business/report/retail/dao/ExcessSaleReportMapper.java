package com.rograndec.feijiayun.chain.business.report.retail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleResultDetailVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleResultVO;

public interface ExcessSaleReportMapper {

	List<ExcessSaleResultVO> selectExcessSalePageByParam(@Param("vo")ExcessSaleQueryVO vo,
			@Param("start")Integer start, @Param("pageSize")Integer pageSize, 
			@Param("enterpriseId")Long enterpriseId, @Param("order")String order,
			@Param("sort")String sort);

	Long queryCountExcessSaleByParam(@Param("vo")ExcessSaleQueryVO vo, @Param("enterpriseId")Long enterpriseId);

	ExcessSaleResultTotalVO queryExcessSalePageByParam(@Param("vo")ExcessSaleQueryVO vo,
			@Param("enterpriseId")Long enterpriseId);

	List<ExcessSaleResultDetailVO> selectExcessSaleDetailByParam(@Param("enterpriseId")Long enterpriseId, 
			@Param("goodsId")Long goodsId, @Param("lotId")Long lotId, @Param("shelfId")Long shelfId);

}
