package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierFileReportDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface SupplierFileReportService {
    void getSupplierFileList(Page<List<SupplierFileReportDetailVO>> page, RequestParamSupplierReportVO supplierReportVO, UserVO userVO);

    SupplierFileReportDetailVO getSupplierFileDetail(UserVO userVO, Long supplierId);

    void exportSupplierFileList(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception;

    void exportSupplierDetail(OutputStream output, UserVO userVO, Long supplierId) throws Exception;
}
