package com.rograndec.feijiayun.chain.business.report.storage.service;

import com.rograndec.feijiayun.chain.business.report.storage.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface StockWarnReportService {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	Page selectExpireGoodsList(StockListQueryVO vo, UserVO loginUser);

    StockWarnExpireGoodsTotalVO selectExpireGoodsList2WarnSet(WarnSet warnSet, UserVO loginUser);

    StockWarnExpireGoodsTotalVO selectExpireGoodsListNotPage(StockListQueryVO vo, UserVO loginUser);

    StockWarnNearPeriodGoodsTotalVO selectNearPeriodGoodsList2WarnSet(WarnSet warnSet, UserVO loginUser);

    StockWarnNearPeriodGoodsTotalVO selectNearPeriodGoodsListNotPage(StockListQueryVO vo, UserVO loginUser);

	Page selectNearPeriodGoodsList(StockListQueryVO vo, UserVO loginUser);

	StockWarnLackGoodsTotalVO selectLackGoodsListNotPage(StockListQueryVO vo, UserVO loginUser);

	Page selectLackGoodsList(StockListQueryVO vo, UserVO loginUser);

	StockWarnStoreGoodsTotalVO selectStoreGoodsListNotPage(StockListQueryVO vo, UserVO loginUser);

	Page selectStoreGoodsList(StockListQueryVO vo, UserVO loginUser);

	Page selectLagSaleGoodsList(StockListQueryVO vo, UserVO loginUser);

    StockWarnLagSaleGoodsTotalVO selectLagSaleGoodsList2WarnSet(WarnSet warnSet, UserVO loginUser);

    StockWarnLagSaleGoodsTotalVO selectLagSaleGoodsListNotPage(StockListQueryVO vo, UserVO loginUser);

	Page selectGoodsMaintanceList(StockListQueryVO vo, UserVO loginUser);

    List<StockWarnGoodsMaintanceVO> selectGoodsMaintanceList2WarnSet(WarnSet warnSet, UserVO loginUser);

    StockWarnGoodsMaintanceTotalVO selectGoodsMaintanceListNotPage(StockListQueryVO vo,
                                                                   UserVO loginUser);

	Page selectGoodsDisplayCheckList(StockListQueryVO vo, UserVO loginUser);

}
