package com.rograndec.feijiayun.chain.business.report.retail.service;

import com.rograndec.feijiayun.chain.business.report.retail.vo.*;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface TimeSaleReportService {

	public RetailTimeSaleVo getRetailTimeSaleVo(UserVO uservo, RequestRetailTimeSale requestRetailTimeSale);

	public void exportExcel(UserVO userVO, OutputStream output,RequestRetailTimeSale requestRetailTimeSale);

	List<GoodsCategorySaleVo> listGoodsCategorySaleVos(RequestGoodsCategorySaleVo requestGoodsCategorySaleVo);

	void exportExcelForGoodsCategorySale(OutputStream output, RequestGoodsCategorySaleVo requestGoodsCategorySaleVo, UserVO userVO);

	List<QuerySaleYVO> querySaleX(UserVO userVO, Integer saleType, String saleDate);
}
