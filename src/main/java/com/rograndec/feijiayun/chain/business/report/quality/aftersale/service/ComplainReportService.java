package com.rograndec.feijiayun.chain.business.report.quality.aftersale.service;

import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.ComplainReqVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface ComplainReportService {
    Page getPageList(ComplainReqVO reqVO, UserVO loginUser);

    void export(ComplainReqVO reqVO, UserVO loginUser, OutputStream output) throws Exception;
}
