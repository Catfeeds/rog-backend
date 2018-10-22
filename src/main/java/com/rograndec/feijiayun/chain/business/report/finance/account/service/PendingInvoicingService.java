package com.rograndec.feijiayun.chain.business.report.finance.account.service;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * 应付/应收待开票据服务
 *
 * @author zhangyu
 * @create 2018-01-11}
 */
public interface PendingInvoicingService {

    Page<List<PendingInvoicingVO>> getPageList(UserVO userVO, Page<List<PendingInvoicingVO>> page, Integer accountType, Date startDate, Date endDate, String code, String unitName, Integer orderType) throws Exception;

    PendingInvoicingPrintVO getPrintList(UserVO userVO, Integer accountType, Date startDate, Date endDate, String code, String unitName, Integer orderType) throws Exception;

    void excelExport(OutputStream output, UserVO userVO, Integer accountType, Date startDate, Date endDate, String code, String unitName, Integer orderType);
}
