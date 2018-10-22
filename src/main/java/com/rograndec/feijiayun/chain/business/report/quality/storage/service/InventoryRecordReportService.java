package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.InventoryRecordReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportInventoryRecordVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/19 <br/>
 * 描述：盘点记录报表
 */
@Service
public interface InventoryRecordReportService {
    /**
     * 商品-停售通知报表数据
     */
    Page<InventoryRecordReportTotalVO> getInventoryRecordReportList(RequestReportInventoryRecordVO requestVO, Page page, UserVO userVO) throws Exception;

    /**
     * 导出商品-停售通知
     * @param output
     * @param totalVO
     * @param userVO
     */
    void excelExport(OutputStream output, InventoryRecordReportTotalVO totalVO, UserVO userVO);
}
