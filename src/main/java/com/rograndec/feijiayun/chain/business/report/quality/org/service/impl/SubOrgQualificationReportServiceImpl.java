package com.rograndec.feijiayun.chain.business.report.quality.org.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.org.service.SubOrgQualificationReportService;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseQualificationReport2ExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 功能描述： 门店组织机构与质量管理职责
 * Created by ST on 2017/10/16 17:30
 */
@Service
public class SubOrgQualificationReportServiceImpl implements SubOrgQualificationReportService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private EnterpriseQualificationMapper enterpriseQualificationMapper;

    @Override
    public void getEnterpriseQualificationReportList(Page<List<QualificationConfigVO>> page, RequestParamOrgReportVO paramOrgReportVO, UserVO userVO){
        Long enterpriseId = userVO.getEnterpriseId();
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<QualificationConfigVO> qualificationConfigReport = enterpriseMapper.getQualificationConfigReport(enterpriseId,userVO.getChainType());
        page.setResult(qualificationConfigReport);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public List<QualificationConfigVO> getEnterpriseQualificationWarnReportList(UserVO userVO){
        Long enterpriseId = userVO.getEnterpriseId();
        List<QualificationConfigVO> qualificationConfigReport = getEnterpriseQualificationReportVOS(enterpriseId,userVO.getChainType());
        return qualificationConfigReport;

    }

    private List<QualificationConfigVO> getEnterpriseQualificationReportVOS(Long enterpriseId,Integer chainType) {
        List<EnterpriseQualification> qualificationList = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(enterpriseId,2+"",null);
        List<QualificationConfigVO> qualificationConfigReport = enterpriseMapper.getQualificationConfigReport(enterpriseId,chainType);
        List<QualificationConfigVO> warnList = new ArrayList<>();
        checkQualificationConfig(qualificationList, qualificationConfigReport, warnList);

        return warnList;
    }

    private void checkQualificationConfig(List<EnterpriseQualification> qualificationList, List<QualificationConfigVO> qualificationConfigReport, List<QualificationConfigVO> warnList) {
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
    }


    @Override
    public void export(OutputStream output, UserVO userVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        List<QualificationConfigVO> qualificationConfigReport = enterpriseMapper.getQualificationConfigReport(enterpriseId,userVO.getChainType());
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "企业资质";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("qualificationName","资质名称");
        headerHashMap.put("qualificationCode","资质编码");
        headerHashMap.put("validDateString","有效期至");
        headerHashMap.put("fileId","附件");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,qualificationConfigReport,null);

    }

    @Override
    public void exportWarn(OutputStream output, UserVO userVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        List<QualificationConfigVO> qualificationConfigReport = getEnterpriseQualificationReportVOS(enterpriseId,userVO.getChainType());
        List<EnterpriseQualificationReport2ExcelVO> reportList = new ArrayList<>();

        qualificationConfigReport.forEach(configVO -> {
                EnterpriseQualificationReport2ExcelVO excelVO = new EnterpriseQualificationReport2ExcelVO();
                excelVO.setWarnContent(configVO.getWarnContent());
                excelVO.setWarnInfo(configVO.getWarnInfo());
                reportList.add(excelVO);
        });
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "企业资质预警";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("warnContent","预警内容");
        headerHashMap.put("warnInfo","预警信息");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,reportList,null);
    }


}