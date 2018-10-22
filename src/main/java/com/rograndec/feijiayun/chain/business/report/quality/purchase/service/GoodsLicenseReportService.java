package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface GoodsLicenseReportService {
    Page<List<FirstGoodsVO>> getQualificationGoodsInfo(UserVO loginUser, Integer businessVariety, String param, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, String param, String orderName, String orderType, Integer businessVariety) throws Exception;

    GoodsVO getQualificationGoodsDtlInfo(UserVO loginUser, Long goodsId) throws Exception;
}
