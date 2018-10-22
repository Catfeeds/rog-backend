package com.rograndec.feijiayun.chain.business.report.storage.service;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface StockListReportService {

	@SuppressWarnings("rawtypes")
	Page selectStockListBySum(StockListQueryVO vo, UserVO loginUser);

	Page selectStockDtlListBySum(StockListQueryVO vo, UserVO loginUser);

}
