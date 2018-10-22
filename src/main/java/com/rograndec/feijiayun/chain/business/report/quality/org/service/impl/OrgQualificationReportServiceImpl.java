package com.rograndec.feijiayun.chain.business.report.quality.org.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.index.warning.vo.EnterpriseWarnReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.service.OrgQualificationReportService;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 功能描述： 组织机构与质量管理职责
 * Created by ST on 2017/10/16 17:30
 */
@Service
public class OrgQualificationReportServiceImpl implements OrgQualificationReportService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private EnterpriseQualificationMapper enterpriseQualificationMapper;

    @Override
    public void getEnterpriseQualificationReportList(Page<List<EnterpriseQualificationReportVO>> page, RequestParamOrgReportVO paramOrgReportVO, UserVO userVO){
        Long enterpriseId = userVO.getEnterpriseId();//总部id
        Integer status = EnableStatus.ENABLE.getStatus();
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<EnterpriseQualificationReportVO> enterpriseQualificationReportList = enterpriseMapper.getEnterprise2Report(paramOrgReportVO, enterpriseId, status);
        enterpriseQualificationReportList.stream().forEach(item->{
            List<QualificationConfigVO> qualificationConfigReport = enterpriseMapper.getQualificationConfigReport(item.getId(),userVO.getChainType());
            item.setQualificationConfigVOS(qualificationConfigReport);
        });
        page.setResult(enterpriseQualificationReportList);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public List<EnterpriseQualificationReportVO> getEnterpriseQualificationWarnReportList(RequestParamOrgWarnReportVO paramOrgReportVO, UserVO userVO){
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = EnableStatus.ENABLE.getStatus();
        RequestParamOrgReportVO requestParamOrgReportVO = new RequestParamOrgReportVO();
        BeanUtils.copyProperties(paramOrgReportVO,requestParamOrgReportVO);
        return getEnterpriseQualificationReportVOS(requestParamOrgReportVO, enterpriseId, status);
    }

    @Override
    public List<EnterpriseWarnReportVO> getEnterpriseQualification2WarnSet(UserVO userVO, List<WarnSet> enterpriseWarnSets) throws ParseException {

        List<EnterpriseWarnReportVO> enterpriseWarnReportVOS = new ArrayList<>();

        List<EnterpriseQualification> qualificationList = new ArrayList<>();

        if(ChainType.Headquarters.getCode() == userVO.getChainType()){
            qualificationList = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(userVO.getEnterpriseId(),"1",null);
        }else {
            qualificationList = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(userVO.getEnterpriseId(),"2",null);
        }

        if(CollectionUtils.isEmpty(enterpriseWarnSets)){
            return enterpriseWarnReportVOS;
        }
        List<Long> qualificationIds = WarnSet.getQualificationIds(enterpriseWarnSets);

        List<QualificationConfigVO> qualificationConfigReport = enterpriseMapper.getQualificationConfig(userVO.getEnterpriseId(),qualificationIds);

        List<QualificationConfigVO> warnList = new ArrayList<>();

        for(QualificationConfigVO configVO : qualificationConfigReport){

            Long qualificationId = configVO.getQualificationId();

            String fileId = configVO.getFileId();//企业附件id
            Date validDate = configVO.getValidDate();//有效期至


            WarnSet warnSet = enterpriseWarnSets.stream().filter(ws -> ws.getQualificationId().equals(configVO.getQualificationId())).findFirst().orElse(null);

            /**
             * 如果找不到预警设置信息则,则提前预警时间为0
             */
            boolean b = DateUtils.isValidityVDate(validDate, null == warnSet ? 0 : warnSet.getWarnDays());

            /**
             * 如果不到预警时间,则退出
             */
            if(!b) continue;

            EnterpriseQualification enterpriseQualification = qualificationList.stream().filter(w -> w.getId().equals(qualificationId)).findFirst().orElse(null);

            if(enterpriseQualification != null){
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
        }

        List<Long> enterpriseIds = QualificationConfigVO.getEnterpriseIds(warnList);

        if(CollectionUtils.isEmpty(enterpriseIds)){

            return new ArrayList<>();
        }

        List<Enterprise> enterprises = enterpriseMapper.selectByIds(enterpriseIds);

        for(Enterprise enterprise : enterprises){

            QualificationConfigVO qualificationConfigVO = warnList.stream().filter(warn -> warn.getEnterpriseId().equals(enterprise.getId())).findFirst().orElse(null);

            EnterpriseWarnReportVO enterpriseWarnReportVO = EnterpriseWarnReportVO.getEnterpriseWarnReportVO(qualificationConfigVO, enterprise);

            enterpriseWarnReportVOS.add(enterpriseWarnReportVO);
        }

        return enterpriseWarnReportVOS;
    }

    private List<EnterpriseQualificationReportVO> getEnterpriseQualificationReportVOS(RequestParamOrgReportVO paramOrgReportVO, Long enterpriseId, Integer status) {
        List<EnterpriseQualification> qualificationList = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(enterpriseId,2+"",null);
        List<EnterpriseQualificationReportVO> enterpriseQualificationReportList = enterpriseMapper.getEnterprise2Report(paramOrgReportVO, enterpriseId, status);

        enterpriseQualificationReportList.stream().forEach(item->{
            List<QualificationConfigVO> qualificationConfigReport = enterpriseMapper.getQualificationConfigReport(item.getId(),paramOrgReportVO.getChainType());
            List<QualificationConfigVO> warnList = new ArrayList<>();
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


        return enterpriseQualificationReportList;
    }


    @Override
    public void export(OutputStream output, UserVO userVO, RequestParamOrgReportVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = EnableStatus.ENABLE.getStatus();
        List<EnterpriseQualificationReport2ExcelVO> reportList = enterpriseMapper.getEnterpriseQualificationReportList(paramForListVO, enterpriseId, status);
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "组织机构资质";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("chainTypeName","类型");
        headerHashMap.put("groupName","分组");
        headerHashMap.put("code","组织机构编码");
        headerHashMap.put("name","组织机构名称");
        headerHashMap.put("qualificationName","资质名称");
        headerHashMap.put("qualificationCode","资质编码");
        headerHashMap.put("validDateString","有效期至");
        headerHashMap.put("fileId","附件");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,reportList,null);

    }

    @Override
    public void exportWarn(OutputStream output, UserVO userVO, RequestParamOrgWarnReportVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = EnableStatus.ENABLE.getStatus();
        RequestParamOrgReportVO requestParamOrgReportVO = new RequestParamOrgReportVO();
        BeanUtils.copyProperties(paramForListVO,requestParamOrgReportVO);
        List<EnterpriseQualificationReportVO> enterpriseQualificationReportVOS = getEnterpriseQualificationReportVOS(requestParamOrgReportVO, enterpriseId, status);
        List<EnterpriseQualificationReport2ExcelVO> reportList = new ArrayList<>();
        enterpriseQualificationReportVOS.forEach(item->{
            List<QualificationConfigVO> qualificationConfigVOS = item.getQualificationConfigVOS();
            qualificationConfigVOS.forEach(configVO -> {
                EnterpriseQualificationReport2ExcelVO excelVO = new EnterpriseQualificationReport2ExcelVO();
                excelVO.setChainTypeName(item.getChainTypeName());
                excelVO.setGroupName(item.getGroupName());
                excelVO.setName(item.getName());
                excelVO.setCode(item.getCode());
                excelVO.setWarnContent(configVO.getWarnContent());
                excelVO.setWarnInfo(configVO.getWarnInfo());
                reportList.add(excelVO);
            });
        });
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "组织机构资质预警";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("chainTypeName","类型");
        headerHashMap.put("groupName","分组");
        headerHashMap.put("code","组织机构编码");
        headerHashMap.put("name","组织机构名称");
        headerHashMap.put("warnContent","预警内容");
        headerHashMap.put("warnInfo","预警信息");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,reportList,null);
    }


}