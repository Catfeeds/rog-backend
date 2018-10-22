package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.TemperatureHumidityReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Map;

/*
 * 孙帮祥
 */
public interface GoodsStorageReportService {
	void getLotAdjustList(Page page, Map map);
	void getGoodsMaintanceList(Page page, Map map, UserVO userVO) throws Exception;
	void getGoodsDisplayCheckList(Page page, Map map, UserVO userVO) throws Exception;
	void getTemperatureHumidityList(Page page, Map map);
	void getGoodsUnqualifiedList(Page page, Map map);
	void getGoodsExpireList(Page page, Map map);
     TemperatureHumidityReportVO getTempHumidityRecordDtlList(Long id);
	void lotAdjustExcelExport(OutputStream output, UserVO loginUser, Map map);
	void goodsMaintanceExcelExport(OutputStream output, UserVO loginUser, Map map,UserVO userVO) throws Exception;
	void goodsDisplayCheckExcelExport(OutputStream output, UserVO loginUser, Map map,UserVO userVO) throws Exception;
	void goodsUnqualifiedExcelExport(OutputStream output, UserVO loginUser, Map map);
	void goodsExpireExcelExport(OutputStream output, UserVO loginUser, Map map);
	void temperatureHumidityExcelExport(OutputStream output, UserVO loginUser, Map map);
	void temperatureHumidityDetailExcelExport(OutputStream output, UserVO loginUser, Long id);
	void goodsExpireExcelExportBranch(OutputStream output, UserVO loginUser, Map map);
	void goodsUnqualifiedExcelExportBranch(OutputStream output, UserVO loginUser, Map map);
	void goodsDisplayCheckExcelExportBranch(OutputStream output, UserVO loginUser, Map map, UserVO userVO)
			throws Exception;
	void goodsMaintanceExcelExportBranch(OutputStream output, UserVO loginUser, Map map, UserVO userVO)
			throws Exception;
	void lotAdjustExcelExportBranch(OutputStream output, UserVO loginUser, Map map);
}
