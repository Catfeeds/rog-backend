package com.rograndec.feijiayun.chain.business.report.retail.dao;

import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportSaleDetailVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportTeamSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestTeamSaleVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/23 <br/>
 * 描述：柜组/班组 销售报表
 */
public interface SaleAnalysisReportGroupMapper {

    /**
     * 获取柜组销售报表搜索列表
     * @param requestVO
     * @return
     */
    List<ReportCabinetSaleVO> getReportCabinetSaleList(RequestCabinetSaleVO requestVO);

    /**
     * 获取柜组销售明细
     * @return
     */
    List<ReportCabinetSaleVO> getReportCabinetSaleDetailList(@Param("enterpriseId")Long enterpriseId, @Param("cargoAreaId")Long cargoAreaId,
                                                             @Param("type")Integer type, @Param("dateStr")String dateStr);

    /**
     * 根据柜组、企业、日期获取销售明细
     * @param enterpriseId
     * @param cargoAreaId
     * @param dateStr
     * @param type
     * @return
     */
    List<ReportSaleDetailVO> getReportCabinetSaleDetail(@Param("enterpriseId")Long enterpriseId, @Param("cargoAreaId")Long cargoAreaId, @Param("clerkId")Long clerkId,
                                                        @Param("dateStr")String dateStr, @Param("type")Integer type);

    /**
     * 获取班组销售报表搜索列表
     * @param requestVO
     * @return
     */
    List<ReportTeamSaleVO> getReportTeamSaleList(RequestTeamSaleVO requestVO);

    /**
     * 获取班组销售报表搜索列表
     * @return
     */
    List<ReportTeamSaleVO> getReportTeamSaleDetailList(@Param("enterpriseId")Long enterpriseId, @Param("teamId")Long teamId,
                                                       @Param("type")Integer type, @Param("dateStr")String dateStr);

    /**
     * 根据班组、企业、日期获取销售明细
     * @param enterpriseId
     * @param teamId
     * @param dateStr
     * @param type
     * @return
     */
    List<ReportSaleDetailVO> getReportTeamSaleDetail(@Param("enterpriseId")Long enterpriseId, @Param("teamId")Long teamId, @Param("payeeId")Long payeeId, @Param("dateStr")String dateStr, @Param("type")Integer type);
}
