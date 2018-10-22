package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierFirstApproReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface SupplierFirstApproReportService {
    void getSupplierFirstApproList(Page<List<SupplierDetailVO>> page, RequestParamSupplierReportVO supplierReportVO, UserVO userVO);

    SupplierFirstApproReportVO getSupplierFirstApproDetail(UserVO userVO, Long supplierId);

    void exportSupplierFirstApproList(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception;

    void exportSupplierFirstDetail(OutputStream output, UserVO userVO, Long supplierId) throws Exception;
}
