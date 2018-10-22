package com.rograndec.feijiayun.chain.business.report.quality.purchase.service;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierVarietiesExportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/18 15:51
 */
public interface SupplierQualificationReportService {

    /**
     * 供货单位资质查询
     * @param page
     * @param paramOrgReportVO
     * @param userVO
     */
    void getSupplierQualificationList(Page<List<SupplierQualificationReportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO);

    List<SupplierQualificationReportVO> getSupplierQualificationList(RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO);

    /**
     * 供货单位资质导出
     * @param output
     * @param userVO
     * @param paramForListVO
     * @throws Exception
     */
    void export(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception;

    /**
     * 供货单位资质预警查询
     * @param page
     * @param paramOrgReportVO
     * @param userVO
     */
    void getSupplierQualificationWarnReportList(Page<List<SupplierQualificationReportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO);

    /**
     * 供货单位资质预警导出
     * @param output
     * @param userVO
     * @param paramForListVO
     * @throws Exception
     */
    void exportWarn(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception;


    List<SupplierQualificationReportVO> getSupplierQualificationReport2WarinSet(UserVO userVO, List<WarnSet> supplierWarnSets) throws ParseException;

    /**
     * 供货单位销售人员
     * @param page
     * @param paramOrgReportVO
     * @param userVO
     */
    void getSupplierSalesList(Page<List<SupplierQualificationReportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO);

    void exportSupplierSaler(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception;

    /**
     * 供货单位-经营品种
     * @param page
     * @param paramOrgReportVO
     * @param userVO
     */
    void getSupplierVarietiesList(Page<List<SupplierVarietiesExportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO);

    void exportSupplierVarieties(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception;
}
