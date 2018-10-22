package com.rograndec.feijiayun.chain.business.storage.move.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherOutReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherOutReportStasticVO;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutGoodsPageVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutPageTotalVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.StockAndShelfPageVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OtherOutMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OtherOut record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OtherOut record);

    /**
     *
     * @mbg.generated
     */
    OtherOut selectByPrimaryKey(Long id);

    List<StockAndShelfPageVO> selectStockByGoodsAndShelf(Map<String,Object> map);

    List<OtherOut> selectByParam(Map<String,Object> map);

    OtherOutPageTotalVO sumAmountTotal(Map<String,Object> map);

    List<OtherOutGoodsPageVO> selectGoodsByParam(Map<String,Object> map);

    List<OtherOutGoodsPageVO> selectGoodsLotByParam(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OtherOut record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OtherOut record);

    List<OtherOutReportPageVO> selectFatherOtherOutReport(Map<String, Object> map);

    List<OtherOutReportPageVO> selectSonOtherOutReport(Map<String, Object> map);

    Integer selectFatherOtherOutTotal(Map<String, Object> map);

    Integer selectSonOtherOutTotal(Map<String, Object> map);

    OtherOutReportStasticVO selectFatherStastic(Map<String, Object> map);

    OtherOutReportStasticVO selectSonStastic(Map<String, Object> map);
}