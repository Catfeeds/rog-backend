package com.rograndec.feijiayun.chain.business.report.quality.storage.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsDisplayCheckCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsDisplayCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsExpireCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsExpireReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsMaintanceCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsMaintanceReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsUnqualifiedCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsUnqualifiedReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LotAdjustReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.TemperatureHumidityReportVO;

import java.util.List;
import java.util.Map;

/*
 * 孙帮祥
 */
public interface GoodsStorageReportMapper {
     List<LotAdjustReportVO> selectLotAdjustList(Map map);
     Long selectLotAdjustCount(Map map);
     
     List<GoodsMaintanceReportVO> selectGoodsMaintanceList(Map map);
     Long selectGoodsMaintanceCount(Map map);
     GoodsMaintanceCountReportVO selectGoodsMaintanceCountTotal(Map map);
   
     List<GoodsDisplayCheckReportVO> selectGoodsDisplayCheckList(Map map);
     Long selectGoodsDisplayCheckCount(Map map);
     GoodsDisplayCheckCountReportVO selectGoodsDisplayCheckCountTotal(Map map);
   
     List<TemperatureHumidityReportVO> selectTemperatureHumidityList(Map map);
     Long selectTemperatureHumidityCount(Map map);
     TemperatureHumidityReportVO selectTempHumidityRecordDtlList(Long id);

     List<GoodsUnqualifiedReportVO> selectGoodsUnqualifiedList(Map map);
     Long selectGoodsUnqualifiedCount(Map map);
     GoodsUnqualifiedCountReportVO selectGoodsUnqualifiedCountTotal(Map map);
   
     List<GoodsExpireReportVO> selectGoodsExpireList(Map map);
     Long selectGoodsExpireCount(Map map);
     GoodsExpireCountReportVO selectGoodsExpireCountTotal(Map map);
  }
