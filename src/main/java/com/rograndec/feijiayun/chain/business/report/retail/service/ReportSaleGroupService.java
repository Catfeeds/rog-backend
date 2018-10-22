package com.rograndec.feijiayun.chain.business.report.retail.service;

import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportSaleAnalysisTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportTeamSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestTeamSaleVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/24 <br/>
 * 描述：销售分析报表-柜组/班组
 */
public interface ReportSaleGroupService {

    /**
     * 获取柜组销售列表
     *
     * @param requestVO
     * @param userVO
     * @return
     */
    Page<ReportSaleAnalysisTotalVO<ReportCabinetSaleVO>> getCabinetSaleList(RequestCabinetSaleVO requestVO, Page page, UserVO userVO) throws Exception;

    /**
     * 获取柜组销售明细
     * @param enterpriseId
     * @param cargoAreaId
     * @param type
     * @param dateStr
     * @return
     * @throws Exception
     */
    ReportSaleAnalysisTotalVO<ReportCabinetSaleVO> getCabinetSaleDetailList(Long enterpriseId, Long cargoAreaId, Integer type, String dateStr) throws Exception;


    /**
     * 导出柜组报表
     *
     * @param output
     * @param cabinetTotalVO
     * @param userVO
     */
    void excelExportCabinetSale(OutputStream output, ReportSaleAnalysisTotalVO<ReportCabinetSaleVO> cabinetTotalVO, Integer type, UserVO userVO);

    /**
     * 获取班组销售列表
     * @param requestVO
     * @param userVO
     * @return
     */
    Page<ReportSaleAnalysisTotalVO<ReportTeamSaleVO>> getTeamSaleList(RequestTeamSaleVO requestVO, Page page, UserVO userVO) throws Exception;

    /**
     * 获取班组销售明细列表
     * @return
     */
    ReportSaleAnalysisTotalVO<ReportTeamSaleVO> getTeamSaleDetailList(Long enterpriseId, Long teamId, Integer type, String dateStr) throws Exception;

    /**
     * 导出班组报表
     *
     * @param output
     * @param teamTotalVO
     * @param userVO
     */
    void excelExportTeamSale(OutputStream output, ReportSaleAnalysisTotalVO<ReportTeamSaleVO> teamTotalVO, Integer type, UserVO userVO);
}
