package com.rograndec.feijiayun.chain.business.report.storage.service;

import com.rograndec.feijiayun.chain.business.report.storage.vo.OnWayGoodsPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.OnWayGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface OnWayStockReportService {


    OnWayGoodsVO queryOnWayStockList(Page page, String param, Long enterpriseId, String order, String sort, UserVO loginEnterpriseId, Integer chainType, String enterpriseCode, String enterpriseName);

    List<OnWayGoodsPageVO> getOnWayStockExcel(String param, Long enterpriseId, String order, String sort, UserVO userVO, Integer chainType, String enterpriseCode, String enterpriseName);

    void export(OutputStream output, List<OnWayGoodsPageVO> vo, UserVO userVO);
}
