package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsLockReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsLockVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/19 <br/>
 * 描述：商品锁定报表
 */
@Service
public interface GoodsLockReportService {
    /**
     * <根据条件查询商品锁定报表数据>
     */
    Page getGoodsLockReportList(RequestReportGoodsLockVO requestGoodsLockListVo, Page page, UserVO userVO) throws Exception;

    /**
     * 导出商品锁定
     * @param output
     * @param goodsLockReportTotalVO
     * @param userVO
     */
    void excelExport(OutputStream output, BaseGoodsReportTotalVO<GoodsLockReportVO> goodsLockReportTotalVO, UserVO userVO);
}
