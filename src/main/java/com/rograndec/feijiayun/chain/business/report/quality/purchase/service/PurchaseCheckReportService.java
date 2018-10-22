package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseCheckReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.RequestParamForCheckReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalPurchaseCheckReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface PurchaseCheckReportService {

    Page<TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>> getPurchaseCheckReportPage(Page<TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>> page, RequestParamForCheckReportListVO paramForListVO, Long enterpriseId);

    void exportExcel(OutputStream output, UserVO loginUser, RequestParamForCheckReportListVO param);
}
