package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PlanPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchasePlanReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface PurchasePlanReportService {
    Page<TotalReportVO<PurchasePlanReportVO>> getPurchasePlanPage(UserVO loginUser, PlanPageVO planPageVO, Page<TotalReportVO<PurchasePlanReportVO>> page) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, PlanPageVO planPageVO) throws Exception;
}
