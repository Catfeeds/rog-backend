package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierVarietiesMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierVarietiesExportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.SupplierQualificationReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReport2ExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierSalerReportExcelVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.component.SupplierComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述：
 * Created by ST on 2017/10/18 15:52
 */
@Service
public class SupplierQualificationReportServiceImpl implements SupplierQualificationReportService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private SupplierQualificationConfigMapper supplierQualificationConfigMapper;

    @Autowired
    private EnterpriseQualificationMapper enterpriseQualificationMapper;


    @Autowired
    private SupplierVarietiesMapper supplierVarietiesMapper;

    @Autowired
    private SupplierComponent supplierComponent;

    @Override
    public void getSupplierQualificationList(Page<List<SupplierQualificationReportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO){
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Integer status = EnableStatus.ENABLE.getStatus();
        ParamUtils.packParam(userVO,paramOrgReportVO);
        List<SupplierQualificationReportVO> reportVOS = supplierMapper.getSupplierQualificationReportList(paramOrgReportVO,status);
        //增加资质信息
        reportVOS.stream().forEach(item->{
            List<QualificationConfigVO> qualificationConfigReport = supplierQualificationConfigMapper.getQualificationConfigReport(item.getId());
            item.setQualificationConfigVOS(qualificationConfigReport);
        });
        page.setResult(reportVOS);
        page.setTotalRecord((int) objects.getTotal());

    }

    @Override
    public List<SupplierQualificationReportVO> getSupplierQualificationList(RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO){
        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        Integer status = EnableStatus.ENABLE.getStatus();
        ParamUtils.packParam(userVO,paramOrgReportVO);
        List<SupplierQualificationReportVO> supplierQualificationReportVOS = getSupplierQualificationReportVOS(paramOrgReportVO, enterpriseId, status);

        return supplierQualificationReportVOS;
    }

    @Override
    public void export(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception {
        Integer status = EnableStatus.ENABLE.getStatus();
        ParamUtils.packParam(userVO,paramForListVO);
        List<SupplierQualificationReport2ExcelVO> reportList = supplierMapper.getSupplierQualificationReportExcelList(paramForListVO, status);
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "供货单位资质";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("natureName","类型");
        headerHashMap.put("code","组织机构编码");
        headerHashMap.put("name","组织机构名称");
        headerHashMap.put("qualificationName","资质名称");
        headerHashMap.put("qualificationCode","资质编码");
        headerHashMap.put("validDateString","有效期至");
        headerHashMap.put("fileId","附件");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,reportList,null);

    }

    @Override
    public void getSupplierQualificationWarnReportList(Page<List<SupplierQualificationReportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO) {
        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        Integer status = EnableStatus.ENABLE.getStatus();
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        ParamUtils.packParam(userVO,paramOrgReportVO);
        List<SupplierQualificationReportVO> supplierQualificationReportVOS = getSupplierQualificationReportVOS(paramOrgReportVO, enterpriseId, status);
        page.setResult(supplierQualificationReportVOS);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportWarn(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception {
        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        Integer status = EnableStatus.ENABLE.getStatus();
        ParamUtils.packParam(userVO,paramForListVO);
        List<SupplierQualificationReportVO> supplierQualificationReportVOS = getSupplierQualificationReportVOS(paramForListVO, enterpriseId, status);
        List<SupplierQualificationReport2ExcelVO> reportList = new ArrayList<>();
        supplierQualificationReportVOS.forEach(item->{
            List<QualificationConfigVO> qualificationConfigVOS = item.getQualificationConfigVOS();
            qualificationConfigVOS.forEach(configVO -> {
                SupplierQualificationReport2ExcelVO excelVO = new SupplierQualificationReport2ExcelVO();
                excelVO.setNature(item.getNature());
                excelVO.setName(item.getName());
                excelVO.setCode(item.getCode());
                excelVO.setWarnContent(configVO.getWarnContent());
                excelVO.setWarnInfo(configVO.getWarnInfo());
                reportList.add(excelVO);
            });
        });
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "供货单位资质预警";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("natureName","企业类型");
        headerHashMap.put("code","供货单位编码");
        headerHashMap.put("name","供货单位名称");
        headerHashMap.put("warnContent","预警内容");
        headerHashMap.put("warnInfo","预警信息");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,reportList,null);
    }

    @Override
    public List<SupplierQualificationReportVO> getSupplierQualificationReport2WarinSet(UserVO userVO, List<WarnSet> supplierWarnSets) throws ParseException {

        List<SupplierQualificationReportVO> supplierQualificationReportVOS = new ArrayList<>();

        Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);

        List<EnterpriseQualification> qualificationList = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(headEnterpriseId,String.valueOf(3),null);

        if(CollectionUtils.isEmpty(supplierWarnSets)){
            return supplierQualificationReportVOS;
        }

        RequestParamSupplierReportVO paramOrgReportVO = new RequestParamSupplierReportVO();
        paramOrgReportVO.setEnterpriseId(headEnterpriseId);
        paramOrgReportVO.setOwnerId(userVO.getEnterpriseId());
        List<SupplierQualificationReportVO> supplierQualificationReportList = supplierMapper.getSupplierQualificationReportList2WarnSet(paramOrgReportVO, 1);

        for(SupplierQualificationReportVO item : supplierQualificationReportList){
            List<QualificationConfigVO> qualificationConfigReport = supplierQualificationConfigMapper.getQualificationConfigReport(item.getId());
            List<QualificationConfigVO> warnList = new ArrayList<>();
            for(QualificationConfigVO configVO : qualificationConfigReport){
                Long qualificationId = configVO.getQualificationId();
                String fileId = configVO.getFileId();//企业附件id
                Date validDate = configVO.getValidDate();//有效期至
                EnterpriseQualification enterpriseQualification = qualificationList.stream().filter(w -> w.getId().equals(qualificationId)).findFirst().orElse(null);

                WarnSet warnSet = supplierWarnSets.stream().filter(ws -> ws.getQualificationId().equals(enterpriseQualification.getId())).findFirst().orElse(null);

                /**
                 * 如果找不到预警设置信息则,则提前预警时间为0
                 */
                boolean b = DateUtils.isValidityVDate(validDate, null == warnSet ? 0 : warnSet.getWarnDays());

                /**
                 * 如果不到预警时间,则退出
                 */
                if(!b) continue;

                if(null != enterpriseQualification){

                    Integer fileMust = enterpriseQualification.getFileMust();//附件是否必填
                    Integer validUntilMust = enterpriseQualification.getValidUntilMust();//有效期至是否必填
                    if(validDate != null){

                        Date currentDate = DateUtils.getCurrentDate(new Date());
                        Long diffDays = DateUtils.differenceBetweenDates(currentDate, validDate).third;

                        if(diffDays < 0){
                            configVO.setWarnContent(enterpriseQualification.getName());
                            configVO.setWarnInfo("过期"+ Math.abs(diffDays)+ "天");
                            warnList.add(configVO);
                        }
                    } else if(validUntilMust != null && validUntilMust == 1 && validDate == null){
                        configVO.setWarnContent(enterpriseQualification.getName());
                        configVO.setWarnInfo("效期缺失");
                        warnList.add(configVO);
                    } else
                    if(fileId == null && fileMust == 1){
                        configVO.setWarnContent(enterpriseQualification.getName());
                        configVO.setWarnInfo("资质缺失");
                        warnList.add(configVO);
                    }

                }

            }
            item.setQualificationConfigVOS(warnList);

        }

        Iterator<SupplierQualificationReportVO> iterator = supplierQualificationReportList.iterator();
        while (iterator.hasNext()){
            SupplierQualificationReportVO next = iterator.next();
            if(CollectionUtils.isEmpty(next.getQualificationConfigVOS())){
                iterator.remove();
            }
        }
        return supplierQualificationReportList;
    }



    private List<SupplierQualificationReportVO> getSupplierQualificationReportVOS(RequestParamSupplierReportVO paramOrgReportVO, Long enterpriseId, Integer status) {
        List<EnterpriseQualification> qualificationList = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(enterpriseId,3+"",null);
        List<SupplierQualificationReportVO> supplierQualificationReportList = supplierMapper.getSupplierQualificationReportList(paramOrgReportVO, status);

        supplierQualificationReportList.stream().forEach(item->{
            List<QualificationConfigVO> warnList = new ArrayList<>();
            List<QualificationConfigVO> qualificationConfigReport = supplierQualificationConfigMapper.getQualificationConfigReport(item.getId());
            qualificationConfigReport.stream().forEach(configVO->{
                Long qualificationId = configVO.getQualificationId();
                String fileId = configVO.getFileId();//企业附件id
                Date validDate = configVO.getValidDate();//有效期至
                EnterpriseQualification enterpriseQualification = qualificationList.stream().filter(w -> w.getId().equals(qualificationId)).findFirst().orElse(null);
                if(enterpriseQualification != null){
                    Integer fileMust = enterpriseQualification.getFileMust();//附件是否必填
                    Integer validUntilMust = enterpriseQualification.getValidUntilMust();//有效期至是否必填
                    if(validDate != null){
                        Long diffDays = DateUtils.differenceBetweenDates(new Date(), validDate).third;
                        if(diffDays < 0){
                            configVO.setWarnContent(enterpriseQualification.getName());
                            configVO.setWarnInfo("过期"+ Math.abs(diffDays)+ "天");
                            warnList.add(configVO);
                        }
                    } else
                    if(validUntilMust != null && validUntilMust == 1 && validDate == null){
                        configVO.setWarnContent(enterpriseQualification.getName());
                        configVO.setWarnInfo("效期缺失");
                        warnList.add(configVO);
                    } else
                    if(fileId == null && fileMust == 1){
                        configVO.setWarnContent(enterpriseQualification.getName());
                        configVO.setWarnInfo("资质缺失");
                        warnList.add(configVO);
                    }
                }

            });
            item.setQualificationConfigVOS(warnList);
        });
        return supplierQualificationReportList;
    }


    /**
     *查询供货单位销售人员
     */
    @Override
    public void getSupplierSalesList(Page<List<SupplierQualificationReportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO){
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        Integer status = EnableStatus.ENABLE.getStatus();
        ParamUtils.packParam(userVO,paramOrgReportVO);
        List<SupplierQualificationReportVO> reportVOS = getSupplierSupplierReportVOS(paramOrgReportVO, enterpriseId, status);
        page.setResult(reportVOS);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportSupplierSaler(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception {
        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        Integer status = EnableStatus.ENABLE.getStatus();
        ParamUtils.packParam(userVO,paramForListVO);
        List<SupplierQualificationReportVO> supplierSupplierReportVOS = getSupplierSupplierReportVOS(paramForListVO, enterpriseId, status);

        List<SupplierSalerReportExcelVO> supplierSalerReportExcelVOList = new ArrayList<>();
        supplierSupplierReportVOS.forEach(item->{
            List<SupplierSalerReportExcelVO> supplierSalerReportExcelVOS = packSupplierSalerExcelVO(item);
            supplierSalerReportExcelVOList.addAll(supplierSalerReportExcelVOS);
        });
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "供货单位销售人员";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("natureName","企业类型");
        headerHashMap.put("code","供货单位编码");
        headerHashMap.put("supplierName","供货单位名称");
        headerHashMap.put("name","销售人员");
        headerHashMap.put("idNum","身份证号");
        headerHashMap.put("idValidUntilString","有效期至");
        headerHashMap.put("idFileId","附件");
        headerHashMap.put("certificateNum","授权书");
        headerHashMap.put("certificateValidUntilString","授权书有效期至");
        headerHashMap.put("certificateFileId","附件");
        headerHashMap.put("authorizedVarietyNames","授权品种");
        headerHashMap.put("authorizedVarietyScopeNames","授权范围");
        headerHashMap.put("authorizedRegion","授权地域");
        headerHashMap.put("telephone","电话");
        headerHashMap.put("fax","传真");
        headerHashMap.put("mobilePhone","手机");
        headerHashMap.put("code","邮箱");
        headerHashMap.put("wechatNum","微信");
        headerHashMap.put("qqNum","QQ");
        headerHashMap.put("remark","备注");
        headerHashMap.put("statusName","状态");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,supplierSalerReportExcelVOList,null);
    }

    /**
     * 供货单位-经营品种
     *
     * @param page
     * @param paramOrgReportVO
     * @param userVO
     */
    @Override
    public void getSupplierVarietiesList(Page<List<SupplierVarietiesExportVO>> page, RequestParamSupplierReportVO paramOrgReportVO, UserVO userVO) {
        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        Integer status = EnableStatus.ENABLE.getStatus();
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SupplierVarietiesExportVO> reportVOS = supplierVarietiesMapper.getSupplierVarieByParam(paramOrgReportVO,enterpriseId,status);
        page.setResult(reportVOS);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportSupplierVarieties(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception {
        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        Integer status = EnableStatus.ENABLE.getStatus();

        List<SupplierVarietiesExportVO> supplierVarieList = supplierVarietiesMapper.getSupplierVarieByParam(paramForListVO, enterpriseId, status);

        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "供货单位经营品种";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("nature","企业类型");
        headerHashMap.put("supplierCode","供货单位编码");
        headerHashMap.put("supplierName","供货单位名称");
        headerHashMap.put("goodsCode","商品编码");
        headerHashMap.put("barcode","条形码");
        headerHashMap.put("genericName","通用名称");
        headerHashMap.put("goodsName","商品名称");
        headerHashMap.put("dosageName","剂型");
        headerHashMap.put("specification","规格");
        headerHashMap.put("goodsUnitName","单位");
        headerHashMap.put("manufacturer","生产厂商");
        headerHashMap.put("goodsPlace","产地");
        headerHashMap.put("approvalNumber","批准文号");
        headerHashMap.put("soleSupplier","唯一供货单位");
        headerHashMap.put("supplierGoodsCode","供货单位商品编码");
        headerHashMap.put("managementMode","经营方式");
        headerHashMap.put("agreementPrice","协议单价");
        headerHashMap.put("taxRate","税率");
        headerHashMap.put("notaxAgreementPrice","不含税协议单价");
        headerHashMap.put("lastPurPrice","最近采购竞价");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,supplierVarieList,null);
    }

    private List<SupplierQualificationReportVO> getSupplierSupplierReportVOS(RequestParamSupplierReportVO paramOrgReportVO, Long enterpriseId, Integer status) {

        List<SupplierQualificationReportVO> reportVOS = supplierMapper.getSupplierQualificationReportList(paramOrgReportVO,status);
        //设置供货单位销售人员
        reportVOS.forEach(item->{
            List<SupplierSalerVO> supplierSalerVOList = supplierComponent.getSupplierSalerVOS(enterpriseId,status,item.getId());
            item.setSupplierSalerVOS(supplierSalerVOList);
        });
        return reportVOS;
    }


    private List<SupplierSalerReportExcelVO> packSupplierSalerExcelVO(SupplierQualificationReportVO reportVO){
        SupplierSalerReportExcelVO supplierSalerReportExcelVO = new SupplierSalerReportExcelVO();
        supplierSalerReportExcelVO.setCode(reportVO.getCode());
        supplierSalerReportExcelVO.setSupplierName(reportVO.getName());
        supplierSalerReportExcelVO.setNature(reportVO.getNature());
        List<SupplierSalerReportExcelVO> supplierSalerReportExcelVOS = reportVO.getSupplierSalerVOS().stream().map(item -> copyBean(item, supplierSalerReportExcelVO)).collect(Collectors.toList());
        return supplierSalerReportExcelVOS;
    }



    private SupplierSalerReportExcelVO copyBean(SupplierSalerVO saler,SupplierSalerReportExcelVO supplierSalerReportExcelVO){
        BeanUtils.copyProperties(saler,supplierSalerReportExcelVO);
        return supplierSalerReportExcelVO;
    }


}