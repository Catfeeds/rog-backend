package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherOutExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherOutReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestOtherOutVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface OtherOutReportService {

    OtherOutReportVO getOtherOutPage(Page page, RequestOtherOutVO requestOtherOutVO, UserVO loginUser);

    OtherOutExcelPageVO getExcelForm(RequestOtherOutVO requestOtherOutVO, UserVO loginUser);

    void export(OutputStream output, OtherOutExcelPageVO otherOutExcelPageVO, UserVO loginUser);
}
