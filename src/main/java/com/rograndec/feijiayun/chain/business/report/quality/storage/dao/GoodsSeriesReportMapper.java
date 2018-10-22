package com.rograndec.feijiayun.chain.business.report.quality.storage.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsDestroyReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsHandleReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsLockReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsStartSaleReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsStopSaleReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.InventoryRecordReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.InventoryRecordReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsDestroyVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsHandleVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsLockVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsStartSaleVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsStopSaleVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportInventoryRecordVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/19 <br/>
 * 描述：商品锁定、商品处理、商品销毁、药品停售通知、药品解停通知
 */
public interface GoodsSeriesReportMapper {
    /**
     * 商品锁定查询列表
     *
     * @param requestVO
     * @return
     */
    List<GoodsLockReportVO> getGoodsLockReport(RequestReportGoodsLockVO requestVO);

    /**
     * 商品锁定查询列表 总数量
     *
     * @param requestVO
     * @return
     */
    BigDecimal getGoodsLockReportQuantityTotal(RequestReportGoodsLockVO requestVO);

    /**
     * 商品处理列表
     * @param requestVO
     * @return
     */
    List<GoodsHandleReportVO> getGoodsHandleReport(RequestReportGoodsHandleVO requestVO);

    /**
     * 商品处理总数量
     * @param requestVO
     * @return
     */
    BigDecimal getGoodsHandleReportQuantityTotal(RequestReportGoodsHandleVO requestVO);

    /**
     * 商品销毁报表列表
     * @param requestVO
     * @return
     */
    List<GoodsDestroyReportVO> getGoodsDestroyReport(RequestReportGoodsDestroyVO requestVO);

    /**
     * 商品销毁报表总数量
     * @param requestVO
     * @return
     */
    BigDecimal getGoodsDestroyReportQuantityTotal(RequestReportGoodsDestroyVO requestVO);

    /**
     * 商品-停售通知
     * @param requestVO
     * @return
     */
    List<GoodsStopSaleReportVO> getGoodsStopSaleReport(RequestReportGoodsStopSaleVO requestVO);

    /**
     * 商品-停售通知商品总数量
     * @param requestVO
     * @return
     */
    BigDecimal getGoodsStopSaleReportQuantityTotal(RequestReportGoodsStopSaleVO requestVO);

    /**
     * 商品-解停通知
     * @param requestVO
     * @return
     */
    List<GoodsStartSaleReportVO> getGoodsStartSaleReport(RequestReportGoodsStartSaleVO requestVO);

    /**
     * 商品-解停通知表商品总数量
     * @param requestVO
     * @return
     */
    BigDecimal getGoodsStartSaleReportQuantityTotal(RequestReportGoodsStartSaleVO requestVO);

    /**
     * 盘点记录列表
     * @param requestVO
     * @return
     */
    List<InventoryRecordReportVO> getInventoryRecordReport(RequestReportInventoryRecordVO requestVO);

    /**
     * 商品-解停通知表商品总数量
     * @param requestVO
     * @return
     */
    InventoryRecordReportTotalVO getInventoryRecordReportTotal(RequestReportInventoryRecordVO requestVO);
}
