package com.rograndec.feijiayun.chain.business.report.quality.aftersale.dao;

import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecallRecordReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecallRecordReportReqVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecoverRecordReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecoverRecordReportReqVO;

import java.util.List;
import java.util.Map;

public interface AfterSaleRecordMapper {
    /**
     * 获取追回分页记录
     * @param requestVO
     * @return
     */
    List<RecoverRecordReportPageVO> selectRecoverReportPage(RecoverRecordReportReqVO requestVO);

    /**
     * 获取追回报表合计
     * @param requestVO
     * @return
     */
    Map<String,Object> selectRecoverTotal(RecoverRecordReportReqVO requestVO);

    /**
     * 获取召回分页记录
     * @param requestVO
     * @return
     */
    List<RecallRecordReportPageVO> selectRecallReportPage(RecallRecordReportReqVO requestVO);

    /**
     * 获取追回报表合计
     * @param requestVO
     * @return
     */
    Map<String,Object> selectRecallTotal(RecallRecordReportReqVO requestVO);

    /**
     * 召回分页总和
     * @param requestVO
     * @return
     */
    Integer selectRecallReportPageCount(RecallRecordReportReqVO requestVO);

    /**
     * 追回分页总和
     * @param requestVO
     * @return
     */
    Integer selectRecoverReportPageCount(RecoverRecordReportReqVO requestVO);
}
