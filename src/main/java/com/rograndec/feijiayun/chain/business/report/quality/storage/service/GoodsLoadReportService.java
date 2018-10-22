package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodLoadReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsLoadReportExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestGoodLoadVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface GoodsLoadReportService {

    GoodLoadReportVO getGoodLoadPage(Page page, RequestGoodLoadVO requestGoodLoadVO, UserVO loginUser);

    GoodsLoadReportExcelPageVO getExcelForm(RequestGoodLoadVO requestGoodLoadVO, UserVO loginUser);

    void export(OutputStream output, GoodsLoadReportExcelPageVO goodsLoadReportExcelPageVO, UserVO loginUser);
}
