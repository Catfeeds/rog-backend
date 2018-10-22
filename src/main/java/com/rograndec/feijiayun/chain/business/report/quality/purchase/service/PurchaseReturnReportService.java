package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.PurchaseReturnOutTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.PurchaseReturnTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.RequestGetReturnOutParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.RequestGetReturnParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface PurchaseReturnReportService {
    //购进退出
    void getPurchaseReturnGoodsList(Page<PurchaseReturnTotalVO> page, RequestGetReturnParamVO getReturnParamVO, UserVO userVO);

    void exportPurchaseReturnGoodsList(OutputStream output, UserVO userVO, RequestGetReturnParamVO paramForListVO) throws Exception;

    //购进退出出库
    void getPurchaseReturnOutGoodsList(Page<PurchaseReturnOutTotalVO> page, RequestGetReturnOutParamVO getReturnParamVO, UserVO userVO);

    void exportPurchaseReturnOutGoodsList(OutputStream output, UserVO userVO, RequestGetReturnOutParamVO paramForListVO) throws Exception;

}
