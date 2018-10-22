package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsDestroyReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsDestroyVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/19 <br/>
 * 描述：商品销毁报表
 */
@Service
public interface GoodsDestroyReportService {
    /**
     * <根据条件查询商品销毁报表数据>
     */
    Page getGoodsDestroyReportList(RequestReportGoodsDestroyVO requestVO, Page page, UserVO userVO) throws Exception;

    /**
     * 导出商品处理
     * @param output
     * @param goodsDestroyReportTotalVO
     * @param userVO
     */
    void excelExport(OutputStream output, BaseGoodsReportTotalVO<GoodsDestroyReportVO> goodsDestroyReportTotalVO, UserVO userVO);
}
