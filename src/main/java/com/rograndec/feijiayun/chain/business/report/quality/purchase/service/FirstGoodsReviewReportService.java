package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsReviewPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface FirstGoodsReviewReportService {
    Page<List<FirstGoodsVO>> getGoodsInfo(UserVO loginUser, FirstGoodsReviewPageVO pageVO) throws Exception;

    FirstGoodsVO getGoodsInfoDetail(UserVO loginUser, Long goodsId) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, FirstGoodsReviewPageVO pageVO) throws Exception;

    void exportDetailExcel(OutputStream output, UserVO loginUser, Long goodsId) throws Exception;
}
