package com.rograndec.feijiayun.chain.business.report.quality.aftersale.service;

import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecallRecordReportReqVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecoverRecordReportReqVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface RecoverRecordReportService {
    Page getReportPageList(RecoverRecordReportReqVO requestVO, UserVO loginUser);

    void exportRecover(RecoverRecordReportReqVO requestVO, UserVO loginUser, OutputStream output) throws Exception;

    Page getRecallPageList(RecallRecordReportReqVO requestVO, UserVO loginUser);

    void exportRecall(RecallRecordReportReqVO requestVO, UserVO loginUser, OutputStream output) throws Exception;
}
