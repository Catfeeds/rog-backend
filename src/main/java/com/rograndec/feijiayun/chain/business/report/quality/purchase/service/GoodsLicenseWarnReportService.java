package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.GoodsLicenseWarnVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface GoodsLicenseWarnReportService {
    Page<List<GoodsLicenseWarnVO>> getGoodsLicenseWarn(UserVO loginUser, Integer businessVariety, String param, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception;

    List<GoodsLicenseWarnVO> getGoodsLicenseWarnNot2WarnSet(UserVO loginUser, List<WarnSet> goodsWarnSets) throws Exception;

    List<GoodsLicenseWarnVO> getGoodsLicenseWarnNotPageNotPage(UserVO loginUser, Integer businessVariety, String param, String orderName, String orderType) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, String param, String orderName, String orderType, Integer businessVariety) throws Exception;
}
