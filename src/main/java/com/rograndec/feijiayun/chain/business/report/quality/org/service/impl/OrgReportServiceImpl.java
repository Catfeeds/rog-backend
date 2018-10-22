package com.rograndec.feijiayun.chain.business.report.quality.org.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.EnterpriseGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleCircleMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.StoreLevelMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleArea;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleCircle;
import com.rograndec.feijiayun.chain.business.basic.store.entity.StoreLevel;
import com.rograndec.feijiayun.chain.business.report.quality.org.service.OrgReportService;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessMode;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.EconomyType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 功能描述： 组织机构与质量管理职责
 * Created by ST on 2017/10/16 17:30
 */
@Service
public class OrgReportServiceImpl implements OrgReportService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private EnterpriseGroupMapper enterpriseGroupMapper;

    @Autowired
    private SaleAreaMapper saleAreaMapper;

    @Autowired
    private SaleCircleMapper saleCircleMapper;

    @Autowired
    private BusinessScopeMapper businessScopeMapper;

    @Autowired
    private StoreLevelMapper storeLevelMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Override
    public void getEnterpriseReportList(Page<List<EnterpriseReportVO>> page, RequestParamOrgReportVO paramOrgReportVO, UserVO userVO){
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = EnableStatus.ENABLE.getStatus();
        paramOrgReportVO.setStart(page.getStart());
        List<EnterpriseReportVO> reportList = getEnterpriseReportVOS(paramOrgReportVO, userVO, enterpriseId, status);
        page.setResult(reportList);
        Integer totalRecord = enterpriseMapper.getTotalRecord(paramOrgReportVO, userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus());
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord);
    }


    @Override
    public List<BeanMap> getEnterpGroup(Long enterpriseId, Integer type){
        return enterpriseGroupMapper.getGroupByEnterpriseId(enterpriseId,type);
    }

    @Override
    public void export(OutputStream output, UserVO userVO, RequestParamOrgReportVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = EnableStatus.ENABLE.getStatus();
        List<EnterpriseReportVO> reportList = getEnterpriseReportVOS(paramForListVO, userVO, enterpriseId, status);
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "组织机构";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("chainTypeName","类型");
        headerHashMap.put("groupName","分组");
        headerHashMap.put("code","组织机构编码");
        headerHashMap.put("name","组织机构名称");
        headerHashMap.put("registerMoney","注册资金");
        headerHashMap.put("regionalism","行政区域");
        headerHashMap.put("companyAddress","公司地址");
        headerHashMap.put("postcode","邮政编码");
        headerHashMap.put("tel","公司电话");
        headerHashMap.put("fax","公司传真");
        headerHashMap.put("email","公司邮箱");
        headerHashMap.put("site","公司网址");
        headerHashMap.put("saleAreaName","销售片区");
        headerHashMap.put("saleCircleName","销售商圈");
        headerHashMap.put("storeLevelName","门店级别");
        headerHashMap.put("acreage","面积");
        headerHashMap.put("monthly","月租");
        headerHashMap.put("shopDate","开店日期");
        headerHashMap.put("businessManName","企业负责人");
        headerHashMap.put("legalManName","法定代表人");
        headerHashMap.put("qualityOfficerName","质量负责人");

        headerHashMap.put("bankName","开户银行");
        headerHashMap.put("bankAccount","开户账号");
        headerHashMap.put("bankAccountName","开户户名");
        headerHashMap.put("businessVarietyName","经营品种");

        headerHashMap.put("businessScopeName","经营范围");
        headerHashMap.put("medicalFlagName","医保药店");
        headerHashMap.put("medicalCode","医保机构编码");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,reportList,null);

    }


    private List<EnterpriseReportVO> getEnterpriseReportVOS(RequestParamOrgReportVO paramOrgReportVO, UserVO userVO, Long enterpriseId, Integer status) {
        //销售片区
        List<SaleArea> saleAreas = saleAreaMapper.selectByEnterpriseIdStatus(enterpriseId,status);
        //销售商圈
        List<SaleCircle> saleCircles = saleCircleMapper.selectByEnterpriseIdStatus(enterpriseId, status);
        //门店级别
        List<StoreLevel> storeLevels = storeLevelMapper.selectByEnterpriseIdStatus(enterpriseId, status);
        //经营范围
        List<BusinessScopeVO> businessScopeVOList = businessScopeMapper.getBusinessScopeVOList(null, status, enterpriseId);

        //查询企业
        List<EnterpriseReportVO> reportList = enterpriseMapper.getEnterpriseReportList(paramOrgReportVO, userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus());

        for (EnterpriseReportVO item : reportList) {
            //行政区域
            item.setRegionalism(Optional.ofNullable(item.getProvinceName()).orElse("")
                    + Optional.ofNullable(item.getCityName()).orElse("") + Optional.ofNullable(item.getAreaName()).orElse(""));

            //销售片区
            Long saleAreaId = item.getSaleAreaId();
            if(saleAreaId != null){
                String saleAreaName = saleAreas.stream().filter(s->saleAreaId.equals(s.getId())).findFirst().orElse(new SaleArea()).getName();
                item.setSaleAreaName(Optional.ofNullable(saleAreaName).orElse(""));
            }
            //销售商圈
            Long saleCircleId = item.getSaleCircleId();
            if(saleCircleId != null){
                String saleCircleName = saleCircles.stream().filter(s->saleCircleId.equals(s.getId())).findFirst().orElse(new SaleCircle()).getName();
                item.setSaleCircleName(Optional.ofNullable(saleCircleName).orElse(""));
            }
            //门店级别
            Long storeLevelId = item.getStoreLevelId();
            if(storeLevelId != null){
                String levelName = storeLevels.stream().filter(s -> storeLevelId.equals(s.getId())).findFirst().orElse(new StoreLevel()).getName();
                item.setStoreLevelName(Optional.ofNullable(levelName).orElse(""));
            }

            //经营范围
            String businessScopeIds = item.getBusinessScopeId();
            if(StringUtils.isNotBlank(businessScopeIds)){
                String[] ids = businessScopeIds.split(",");
                if(ids.length > 0){
                    Stream.of(ids).forEach((String p)->{
                        String scopeName = businessScopeVOList.stream().filter(q-> p.equals(q.getId()+"")).findFirst().orElse(new BusinessScopeVO()).getName();
                        String businessScopeName = item.getBusinessScopeName();
                        if(scopeName != null){
                            item.setBusinessScopeName(businessScopeName == null ? scopeName : businessScopeName + "," + scopeName);
                        }
                    });
                }

            }
            //经营品种
            String businessVarietyId = item.getBusinessVariety();
            if(StringUtils.isNotBlank(businessVarietyId)){
                String[] varietyIds = businessVarietyId.split(",");
                if(varietyIds.length > 0){
                    Stream.of(varietyIds).forEach((String v)->{
                        String name = BusinessVariety.getName(Integer.valueOf(v));
                        String businessVarietyName = item.getBusinessVarietyName();
                        if(name != null){
                            item.setBusinessVarietyName(businessVarietyName == null ? name : businessVarietyName + "," + name);
                        }
                    });
                }

            }
            //经济类型
            Integer economicType = item.getEconomicType();
            if(economicType != null){
                item.setEconomicTypeName(EconomyType.getName(economicType));
            }
            Integer businessMode = item.getBusinessMode();
            if(businessMode != null){
                item.setBusinessModeName(BusinessMode.getName(businessMode));
            }


        }
        return reportList;
    }




}