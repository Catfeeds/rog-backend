package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.OtherOutReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherOutMapper;
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
public class OtherOutReportServiceImpl implements OtherOutReportService {


    @Autowired
    private OtherOutMapper otherOutMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public OtherOutReportVO getOtherOutPage(Page page, RequestOtherOutVO requestOtherOutVO, UserVO loginUser) {
        OtherOutReportVO pageVO = new OtherOutReportVO();
        /*BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal noTaxAmount = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;*/

        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(30);
        map.put("businessVariety", requestOtherOutVO.getBusinessVariety());
        map.put("param", requestOtherOutVO.getParam());
        map.put("chainType", requestOtherOutVO.getChainType());
        map.put("enterpriseCode", requestOtherOutVO.getEnterpriseCode());
        map.put("enterpriseName", requestOtherOutVO.getEnterpriseName());
        map.put("startTime", requestOtherOutVO.getStartTime());
        map.put("endTime", requestOtherOutVO.getEndTime());
        map.put("code", requestOtherOutVO.getCode());
        map.put("outManName", requestOtherOutVO.getOutManName());
        map.put("outType", requestOtherOutVO.getOutType());
        map.put("flowUnitType", requestOtherOutVO.getFlowUnitType());

        map.put("flowUnitId", requestOtherOutVO.getFlowUnitId());
        map.put("categoryId", requestOtherOutVO.getCategoryId());
        map.put("domesticImport", requestOtherOutVO.getDomesticImport());
        map.put("storageTemp", requestOtherOutVO.getStorageTemp());
        map.put("storageConditionName", requestOtherOutVO.getStorageConditionName());
        map.put("registeredTrademark", requestOtherOutVO.getRegisteredTrademark());
        map.put("brand", requestOtherOutVO.getBrand());
        map.put("goodsAttribute", requestOtherOutVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestOtherOutVO.getPrescriptionDrug());
        map.put("otcType", requestOtherOutVO.getOtcType());
        map.put("managementScopeId", requestOtherOutVO.getManagementScopeId());
        map.put("specialDrug", requestOtherOutVO.getSpecialDrug());
        map.put("spiritDrugType", requestOtherOutVO.getSpiritDrugType());
        map.put("inChargeDrug", requestOtherOutVO.getInChargeDrug());
        map.put("formulationType", requestOtherOutVO.getFormulationType());

        String order = requestOtherOutVO.getOrder();
        if ("outDate".equals(order)){
            order = "out_date";
        }else if ("code".equals(order)){
            order = "code";
        }
        map.put("order", order);
        map.put("sort", requestOtherOutVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<OtherOutReportPageVO> list = new ArrayList<OtherOutReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = otherOutMapper.selectFatherOtherOutReport(map);
            totalRecord = otherOutMapper.selectFatherOtherOutTotal(map);
            OtherOutReportStasticVO stasticVO = otherOutMapper.selectFatherStastic(map);

            if (list != null && list.size() > 0){
                for (OtherOutReportPageVO otherOutVO : list) {
                    otherOutVO = OtherOutReportPageVO.converTOStr(otherOutVO);
                }
            }
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
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = otherOutMapper.selectSonOtherOutReport(map);
            totalRecord = otherOutMapper.selectSonOtherOutTotal(map);
            if (list != null && list.size() > 0){
                for (OtherOutReportPageVO otherOutVO : list) {
                    otherOutVO = OtherOutReportPageVO.converTOStr(otherOutVO);
                }
            }
            OtherOutReportStasticVO stasticVO = otherOutMapper.selectSonStastic(map);
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

        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setPage(page);
        return pageVO;
    }

    @Override
    public OtherOutExcelPageVO getExcelForm(RequestOtherOutVO requestOtherOutVO, UserVO loginUser) {
        OtherOutExcelPageVO pageVO = new OtherOutExcelPageVO();

        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(30);
        map.put("businessVariety", requestOtherOutVO.getBusinessVariety());
        map.put("param", requestOtherOutVO.getParam());
        map.put("chainType", requestOtherOutVO.getChainType());
        map.put("enterpriseCode", requestOtherOutVO.getEnterpriseCode());
        map.put("enterpriseName", requestOtherOutVO.getEnterpriseName());
        map.put("startTime", requestOtherOutVO.getStartTime());
        map.put("endTime", requestOtherOutVO.getEndTime());
        map.put("code", requestOtherOutVO.getCode());
        map.put("outManName", requestOtherOutVO.getOutManName());
        map.put("outType", requestOtherOutVO.getOutType());
        map.put("flowUnitType", requestOtherOutVO.getFlowUnitType());

        map.put("flowUnitId", requestOtherOutVO.getFlowUnitId());
        map.put("categoryId", requestOtherOutVO.getCategoryId());
        map.put("domesticImport", requestOtherOutVO.getDomesticImport());
        map.put("storageTemp", requestOtherOutVO.getStorageTemp());
        map.put("storageConditionName", requestOtherOutVO.getStorageConditionName());
        map.put("registeredTrademark", requestOtherOutVO.getRegisteredTrademark());
        map.put("brand", requestOtherOutVO.getBrand());
        map.put("goodsAttribute", requestOtherOutVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestOtherOutVO.getPrescriptionDrug());
        map.put("otcType", requestOtherOutVO.getOtcType());
        map.put("managementScopeId", requestOtherOutVO.getManagementScopeId());
        map.put("specialDrug", requestOtherOutVO.getSpecialDrug());
        map.put("spiritDrugType", requestOtherOutVO.getSpiritDrugType());
        map.put("inChargeDrug", requestOtherOutVO.getInChargeDrug());
        map.put("formulationType", requestOtherOutVO.getFormulationType());

        String order = requestOtherOutVO.getOrder();
        if ("outDate".equals(order)){
            order = "out_date";
        }else if ("code".equals(order)){
            order = "code";
        }
        map.put("order", order);
        map.put("sort", requestOtherOutVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<OtherOutReportPageVO> list = new ArrayList<OtherOutReportPageVO>();
        List<OtherOutReportExcelVO> excelList = new ArrayList<OtherOutReportExcelVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = otherOutMapper.selectFatherOtherOutReport(map);
            OtherOutReportStasticVO stasticVO = otherOutMapper.selectFatherStastic(map);

            if (list != null && list.size() > 0){
                for (OtherOutReportPageVO otherOutVO : list) {
                    otherOutVO = OtherOutReportPageVO.converTOStr(otherOutVO);
                    OtherOutReportExcelVO excelVO = new OtherOutReportExcelVO();
                    excelVO = OtherOutReportExcelVO.convertTOExcel(otherOutVO);
                    excelList.add(excelVO);
                }
            }

            buildExcelPageVO(pageVO,stasticVO);

        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = otherOutMapper.selectSonOtherOutReport(map);
            OtherOutReportStasticVO stasticVO = otherOutMapper.selectSonStastic(map);
            if (list != null && list.size() > 0){
                for (OtherOutReportPageVO otherOutVO : list) {
                    otherOutVO = OtherOutReportPageVO.converTOStr(otherOutVO);
                    OtherOutReportExcelVO excelVO = new OtherOutReportExcelVO();
                    excelVO = OtherOutReportExcelVO.convertTOExcel(otherOutVO);
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


    private void buildExcelPageVO(OtherOutExcelPageVO pageVO, OtherOutReportStasticVO stasticVO) {
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
    public void export(OutputStream output, OtherOutExcelPageVO otherOutExcelPageVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("outDate", "出库日期");
        map.put("code", "出库单号");
        map.put("outType", "出库类型");
        map.put("outManName", "出库人员");
        map.put("auditManName", "复核人员");
        map.put("flowUnitName", "流向单位");
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
        name.add("其他出库单");
        BigDecimal quantity = otherOutExcelPageVO.getQuantity();
        BigDecimal amount = otherOutExcelPageVO.getAmount();
        BigDecimal noTaxAmount = otherOutExcelPageVO.getNoTaxAmount();
        BigDecimal taxAmount = otherOutExcelPageVO.getTaxAmount();

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
        purchaseGeneralComponent.commExcelExport(output, map, otherOutExcelPageVO.getList(), name, new ArrayList<>(), end.toString(), false, list);
    }
}
