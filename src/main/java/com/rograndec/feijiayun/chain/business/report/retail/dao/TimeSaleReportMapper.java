package com.rograndec.feijiayun.chain.business.report.retail.dao;

import com.rograndec.feijiayun.chain.business.report.retail.vo.GoodsCategorySaleVo;
import com.rograndec.feijiayun.chain.business.report.retail.vo.QuerySaleYVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestGoodsCategorySaleVo;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestRetailTimeSale;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TimeSaleReportMapper {

	BigDecimal getSalePrice(RequestRetailTimeSale requestRetailTimeSale);

	BigDecimal getSaleCount(RequestRetailTimeSale requestRetailTimeSale);

	GoodsCategorySaleVo getGoodsCategorySaleVos(RequestGoodsCategorySaleVo requestGoodsCategorySaleVo);

	List<GoodsCategorySaleVo>  getParent(@Param("enterpriseId")Long enterpriseId,@Param("parentId")Long parentId);

	BigDecimal selectQuerySaleY(@Param("enterpriseId")Long enterpriseId, @Param("saleType")Integer saleType,@Param("startTimeNow")String startTimeNow,@Param("endTimeNow")String endTimeNow);
}
