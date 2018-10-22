package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.OtherInReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OtherInReportServiceImpl implements OtherInReportService{

    @Autowired
    private OtherInMapper otherInMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public OtherInReportVO getOtherInPage(Page page, RequestOtherInVO requestOtherInVO, UserVO loginUser) {
        OtherInReportVO pageVO = new OtherInReportVO();
        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(30);
        map.put("businessVariety", requestOtherInVO.getBusinessVariety());
        map.put("param", requestOtherInVO.getParam());
        map.put("chainType", requestOtherInVO.getChainType());
        map.put("enterpriseCode", requestOtherInVO.getEnterpriseCode());
        map.put("enterpriseName", requestOtherInVO.getEnterpriseName());
        map.put("startTime", requestOtherInVO.getStartTime());
        map.put("endTime", requestOtherInVO.getEndTime());
        map.put("code", requestOtherInVO.getCode());
        map.put("inManName", requestOtherInVO.getInManName());
        map.put("inType", requestOtherInVO.getInType());
        map.put("srcUnitType", requestOtherInVO.getSrcUnitType());

        map.put("srcUnitId", requestOtherInVO.getSrcUnitId());
        map.put("categoryId", requestOtherInVO.getCategoryId());
        map.put("domesticImport", requestOtherInVO.getDomesticImport());
        map.put("storageTemp", requestOtherInVO.getStorageTemp());
        map.put("storageConditionName", requestOtherInVO.getStorageConditionName());
        map.put("registeredTrademark", requestOtherInVO.getRegisteredTrademark());
        map.put("brand", requestOtherInVO.getBrand());
        map.put("goodsAttribute", requestOtherInVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestOtherInVO.getPrescriptionDrug());
        map.put("otcType", requestOtherInVO.getOtcType());
        map.put("managementScopeId", requestOtherInVO.getManagementScopeId());
        map.put("specialDrug", requestOtherInVO.getSpecialDrug());
        map.put("spiritDrugType", requestOtherInVO.getSpiritDrugType());
        map.put("inChargeDrug", requestOtherInVO.getInChargeDrug());
        map.put("formulationType", requestOtherInVO.getFormulationType());

        String order = requestOtherInVO.getOrder();
        if ("inDate".equals(order)){
            order = "in_date";
        }else if ("code".equals(order)){
            order = "in_code";
        }
        map.put("order", order);
        map.put("sort", requestOtherInVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<OtherInReportPageVO> list = new ArrayList<OtherInReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = otherInMapper.selectFatherOtherInReport(map);
            totalRecord = otherInMapper.selectFatherOtherInTotalRecord(map);
            OtherInReportStasticVO stasticVO = otherInMapper.selectFatherStastic(map);
            if (list != null && list.size() > 0){
                for (OtherInReportPageVO otherInVO : list) {
                    otherInVO = OtherInReportPageVO.converTOStr(otherInVO);
                }
            }

            buildPageVO(pageVO,stasticVO);
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = otherInMapper.selectSonOtherInReport(map);
            totalRecord = otherInMapper.selectSonOtherInTotalRecord(map);
            if (list != null && list.size() > 0){
                for (OtherInReportPageVO otherInVO : list) {
                    otherInVO = OtherInReportPageVO.converTOStr(otherInVO);
                }
            }
            OtherInReportStasticVO stasticVO = otherInMapper.selectSonStastic(map);
            buildPageVO(pageVO,stasticVO);

        }

        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setPage(page);
        return pageVO;
    }

    private void buildPageVO(OtherInReportVO pageVO, OtherInReportStasticVO stasticVO) {
        if(stasticVO == null){
            pageVO.setAmount(BigDecimal.ZERO);
            pageVO.setQuantity(BigDecimal.ZERO);
            pageVO.setNoTaxAmount(BigDecimal.ZERO);
            pageVO.setTaxAmount(BigDecimal.ZERO);

        } else {
            pageVO.setAmount(stasticVO.getAmount());
            pageVO.setQuantity(stasticVO.getQuantity());
            pageVO.setNoTaxAmount(stasticVO.getNoTaxAmount());
            pageVO.setTaxAmount(stasticVO.getTaxAmount());
        }
    }

    @Override
    public OtherInExcelPageVO getExcelForm(RequestOtherInVO requestOtherInVO, UserVO loginUser) {
        OtherInExcelPageVO pageVO = new OtherInExcelPageVO();
        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(30);
        map.put("businessVariety", requestOtherInVO.getBusinessVariety());
        map.put("param", requestOtherInVO.getParam());
        map.put("chainType", requestOtherInVO.getChainType());
        map.put("enterpriseCode", requestOtherInVO.getEnterpriseCode());
        map.put("enterpriseName", requestOtherInVO.getEnterpriseName());
        map.put("startTime", requestOtherInVO.getStartTime());
        map.put("endTime", requestOtherInVO.getEndTime());
        map.put("code", requestOtherInVO.getCode());
        map.put("inManName", requestOtherInVO.getInManName());
        map.put("inType", requestOtherInVO.getInType());
        map.put("srcUnitType", requestOtherInVO.getSrcUnitType());

        map.put("srcUnitId", requestOtherInVO.getSrcUnitId());
        map.put("categoryId", requestOtherInVO.getCategoryId());
        map.put("domesticImport", requestOtherInVO.getDomesticImport());
        map.put("storageTemp", requestOtherInVO.getStorageTemp());
        map.put("storageConditionName", requestOtherInVO.getStorageConditionName());
        map.put("registeredTrademark", requestOtherInVO.getRegisteredTrademark());
        map.put("brand", requestOtherInVO.getBrand());
        map.put("goodsAttribute", requestOtherInVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestOtherInVO.getPrescriptionDrug());
        map.put("otcType", requestOtherInVO.getOtcType());
        map.put("managementScopeId", requestOtherInVO.getManagementScopeId());
        map.put("specialDrug", requestOtherInVO.getSpecialDrug());
        map.put("spiritDrugType", requestOtherInVO.getSpiritDrugType());
        map.put("inChargeDrug", requestOtherInVO.getInChargeDrug());
        map.put("formulationType", requestOtherInVO.getFormulationType());

        String order = requestOtherInVO.getOrder();
        if ("inDate".equals(order)){
            order = "in_date";
        }else if ("code".equals(order)){
            order = "in_code";
        }
        map.put("order", order);
        map.put("sort", requestOtherInVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<OtherInReportPageVO> list = new ArrayList<OtherInReportPageVO>();
        List<OtherInExcelVO> excelList = new ArrayList<OtherInExcelVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = otherInMapper.selectFatherOtherInReport(map);
            OtherInReportStasticVO stasticVO = otherInMapper.selectFatherStastic(map);

            if (list != null && list.size() > 0){
                for (OtherInReportPageVO otherInVO : list) {
                    otherInVO = OtherInReportPageVO.converTOStr(otherInVO);
                    OtherInExcelVO excelVO = new OtherInExcelVO();
                    excelVO = OtherInExcelVO.convertTOExcel(otherInVO);
                    excelList.add(excelVO);
                }
            }

            buildExcelPageVO(pageVO,stasticVO);

        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = otherInMapper.selectSonOtherInReport(map);
            OtherInReportStasticVO stasticVO = otherInMapper.selectSonStastic(map);
            if (list != null && list.size() > 0){
                for (OtherInReportPageVO otherInVO : list) {
                    otherInVO = OtherInReportPageVO.converTOStr(otherInVO);
                    OtherInExcelVO excelVO = new OtherInExcelVO();
                    excelVO = OtherInExcelVO.convertTOExcel(otherInVO);
                    excelVO.setEnterpriseCode(loginUser.getEnterpriseCode());
                    excelVO.setEnterpriseName(loginUser.getEnterpriseName());
                    excelList.add(excelVO);
                }
            }
            buildExcelPageVO(pageVO,stasticVO);

        }
        pageVO.setList(excelList);
        return pageVO;
    }

    private void buildExcelPageVO(OtherInExcelPageVO pageVO, OtherInReportStasticVO stasticVO) {
        if(stasticVO == null){
            pageVO.setAmount(null);
            pageVO.setQuantity(null);
            pageVO.setNoTaxAmount(null);
            pageVO.setTaxAmount(null);
        } else {
            pageVO.setAmount(stasticVO.getAmount());
            pageVO.setQuantity(stasticVO.getQuantity());
            pageVO.setNoTaxAmount(stasticVO.getNoTaxAmount());
            pageVO.setTaxAmount(stasticVO.getTaxAmount());
        }
    }

    @Override
    public void export(OutputStream output, OtherInExcelPageVO otherInExcelPageVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("inDate", "日期");
        map.put("code", "单号");
        map.put("inType", "入库类型");
        map.put("inManName", "入库人员");
        map.put("srcUnitName", "来源单位");
        map.put("flowCode", "流通监管码");
        map.put("goodsCode", "商品编码");
        map.put("barcode", "条形码");
        map.put("goodsGenericName", "通用名称");
        map.put("goodsName", "商品名称");

        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");

        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("approvalNumber", "批准文号");
        map.put("lotNum", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("shelfName", "货位");
        map.put("shelfStatusDesc", "质量状况");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("amount", "金额");
        map.put("taxRate", "税率");
        map.put("notaxPrice", "不含税单价");
        map.put("notaxAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("remark", "备注");
        map.put("status", "状态");
        map.put("businessVariety", "品种类型");
        map.put("categoryName", "商品分类");
        map.put("goodsAttribute", "商品属性");
        map.put("domesticImport", "国产/进口");
        map.put("managementScopeName", "经营范围");
        map.put("specialDrug", "特殊管理药品");
        map.put("inChargeDrug", "专管药品");
        map.put("limitQuantity", "限购数量");
        map.put("storageTemp", "贮藏温度");
        map.put("storageConditionName", "贮藏条件");
        map.put("safeTime", "保质期");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("其他入库单");
        BigDecimal quantity = otherInExcelPageVO.getQuantity();
        BigDecimal amount = otherInExcelPageVO.getAmount();
        BigDecimal noTaxAmount = otherInExcelPageVO.getNoTaxAmount();
        BigDecimal taxAmount = otherInExcelPageVO.getTaxAmount();

        StringBuilder end = new StringBuilder();
        end.append(quantity == null?"":quantity);
        end.append(amount == null?"":amount);
        end.append(noTaxAmount == null?"":noTaxAmount);
        end.append(taxAmount == null?"":taxAmount);
        List<String> list = new ArrayList<String>();
        list.add("quantity");
        list.add("amount");
        list.add("notaxAmount");
        list.add("taxAmount");
        purchaseGeneralComponent.commExcelExport(output, map, otherInExcelPageVO.getList(), name, new ArrayList<>(), end.toString(), false, list);
    }
}
