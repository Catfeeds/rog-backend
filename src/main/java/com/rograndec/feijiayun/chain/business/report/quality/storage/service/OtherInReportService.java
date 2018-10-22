package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherInExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherInReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestOtherInVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface OtherInReportService {

    OtherInReportVO getOtherInPage(Page page, RequestOtherInVO requestOtherInVO, UserVO loginUser);

    OtherInExcelPageVO getExcelForm(RequestOtherInVO requestOtherInVO, UserVO loginUser);

    void export(OutputStream output, OtherInExcelPageVO otherInExcelPageVO, UserVO loginUser);
}
