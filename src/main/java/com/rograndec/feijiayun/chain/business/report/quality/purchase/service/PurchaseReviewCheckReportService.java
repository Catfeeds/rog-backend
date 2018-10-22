package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;


import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck.PurchaseReviewCheckTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck.RequestGetReviewCheckParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface PurchaseReviewCheckReportService {


    void getReviewCheckGoodsList(Page<PurchaseReviewCheckTotalVO> page, RequestGetReviewCheckParamVO getReturnParamVO, UserVO userVO);

    void exportReviewCheckGoodsList(OutputStream output, UserVO userVO, RequestGetReviewCheckParamVO paramForListVO) throws Exception;

}
