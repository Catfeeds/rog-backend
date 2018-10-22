package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalPurchaseInStorageReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface PurchaseInStorageReportService {

    Page<TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO>> getPurchaseInStorageReportPage(Page<TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO>> page, PurchaseInStorageReportListVO param, Long enterpriseId);

    void exportExcel(OutputStream output, UserVO loginUser, PurchaseInStorageReportListVO param);
}
