package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsClearReportExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsClearReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestGoodClearVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface GoodsClearReportService {

    GoodsClearReportVO getGoodClearPage(Page page, RequestGoodClearVO requestGoodClearVO, UserVO loginUser);

    GoodsClearReportExcelPageVO getExcelForm(RequestGoodClearVO requestGoodClearVO, UserVO loginUser);

    void export(OutputStream output, GoodsClearReportExcelPageVO goodsClearReportExcelPageVO, UserVO loginUser);
}
