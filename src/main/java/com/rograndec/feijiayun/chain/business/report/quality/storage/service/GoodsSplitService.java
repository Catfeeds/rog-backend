package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsSplitReportExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsSplitReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestGoodsSplitVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface GoodsSplitService {

    GoodsSplitReportVO getGoodsSpiltPage(Page page, RequestGoodsSplitVO requestGoodsSplitVO, UserVO loginUser);

    GoodsSplitReportExcelPageVO getExcelForm(RequestGoodsSplitVO requestGoodsSplitVO, UserVO loginUser);

    void export(OutputStream output, GoodsSplitReportExcelPageVO goodsSplitReportExcelPageVO, UserVO loginUser);
}
