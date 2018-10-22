package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.OrderPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseOrderReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface PurchaseOrderReportService {
    Page<TotalReportVO<PurchaseOrderReportVO>> getPurchaseOrderPage(UserVO loginUser, OrderPageVO orderPageVO, Page<TotalReportVO<PurchaseOrderReportVO>> page) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, OrderPageVO orderPageVO) throws Exception;
}
