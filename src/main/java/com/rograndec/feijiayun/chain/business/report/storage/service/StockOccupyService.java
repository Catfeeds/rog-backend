package com.rograndec.feijiayun.chain.business.report.storage.service;

import com.rograndec.feijiayun.chain.business.report.storage.vo.StockOccupyExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockOccupyVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface StockOccupyService {

    StockOccupyVO queryStockOccupyList(Page page, String param, Long enterpriseId, String order, String sort, UserVO userVO);

    List<StockOccupyExcelPageVO> getExcel(String param, Long enterpriseId, String order, String sort, UserVO userVO);

    void export(OutputStream output, List<StockOccupyExcelPageVO> vo, UserVO userVO);
}
