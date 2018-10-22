package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseReceiveReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.ReceivePageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface PurchaseReceiveReportService {
    Page<TotalReportVO<PurchaseReceiveReportVO>> getPurchaseReceive(UserVO loginUser, ReceivePageVO receivePageVO, Page<TotalReportVO<PurchaseReceiveReportVO>> page) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, ReceivePageVO receivePageVO) throws Exception;
}
