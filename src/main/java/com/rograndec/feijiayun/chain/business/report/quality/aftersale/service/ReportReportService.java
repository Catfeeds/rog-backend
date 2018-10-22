package com.rograndec.feijiayun.chain.business.report.quality.aftersale.service;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportRequestVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface ReportReportService {
    Page getReportPageList(ReportRequestVO requestVO, UserVO loginUser);

    void export(ReportRequestVO reqVO, UserVO loginUser, OutputStream output) throws Exception;
}
