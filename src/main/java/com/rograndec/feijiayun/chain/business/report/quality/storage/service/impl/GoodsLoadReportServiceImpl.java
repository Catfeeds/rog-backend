package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsLoadReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsLoadMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Service
public class GoodsLoadReportServiceImpl implements GoodsLoadReportService{

    @Autowired
    private GoodsLoadMapper goodsLoadMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public GoodLoadReportVO getGoodLoadPage(Page page, RequestGoodLoadVO requestGoodLoadVO, UserVO loginUser) {
        GoodLoadReportVO pageVO = new GoodLoadReportVO();
        BigDecimal quantity = BigDecimal.ZERO;

        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(28);
        map.put("businessVariety", requestGoodLoadVO.getBusinessVariety());
        map.put("param", requestGoodLoadVO.getParam());
        map.put("chainType", requestGoodLoadVO.getChainType());
        map.put("enterpriseCode", requestGoodLoadVO.getEnterpriseCode());
        map.put("enterpriseName", requestGoodLoadVO.getEnterpriseName());
        map.put("startTime", requestGoodLoadVO.getStartTime());
        map.put("endTime", requestGoodLoadVO.getEndTime());
        map.put("code", requestGoodLoadVO.getCode());
        map.put("loadManName", requestGoodLoadVO.getLoadManName());
        map.put("auditManName", requestGoodLoadVO.getAuditManName());
        map.put("status", requestGoodLoadVO.getStatus());

        map.put("categoryId", requestGoodLoadVO.getCategoryId());
        map.put("domesticImport", requestGoodLoadVO.getDomesticImport());
        map.put("storageTemp", requestGoodLoadVO.getStorageTemp());
        map.put("storageConditionName", requestGoodLoadVO.getStorageConditionName());
        map.put("registeredTrademark", requestGoodLoadVO.getRegisteredTrademark());
        map.put("brand", requestGoodLoadVO.getBrand());
        map.put("goodsAttribute", requestGoodLoadVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestGoodLoadVO.getPrescriptionDrug());
        map.put("otcType", requestGoodLoadVO.getOtcType());
        map.put("managementScopeId", requestGoodLoadVO.getManagementScopeId());
        map.put("specialDrug", requestGoodLoadVO.getSpecialDrug());
        map.put("spiritDrugType", requestGoodLoadVO.getSpiritDrugType());
        map.put("inChargeDrug", requestGoodLoadVO.getInChargeDrug());
        map.put("formulationType", requestGoodLoadVO.getFormulationType());

        String order = requestGoodLoadVO.getOrder();
        if ("loadDate".equals(order)){
            order = "load_date";
        }else if ("code".equals(order)){
            order = "load_code";
        }
        map.put("order", order);
        map.put("sort", requestGoodLoadVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<GoodLoadReportPageVO> list = new ArrayList<GoodLoadReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = goodsLoadMapper.selectFatherGoodLoadReport(map);
            totalRecord = goodsLoadMapper.selectFatherStastic(map);
            if (list != null && list.size() > 0){
                for (GoodLoadReportPageVO vo : list) {
                    vo = GoodLoadReportPageVO.convertTOStr(vo);
                }
            }
            quantity = goodsLoadMapper.selectFatherQuantity(map);
            pageVO.setQuantity(quantity);
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = goodsLoadMapper.selectSonGoodLoadReport(map);
            totalRecord = goodsLoadMapper.selectSonStastic(map);
            if (list != null && list.size() > 0){
                for (GoodLoadReportPageVO vo : list) {
                    vo = GoodLoadReportPageVO.convertTOStr(vo);
                }
            }
            quantity = goodsLoadMapper.selectSonQuantity(map);
            pageVO.setQuantity(quantity);
        }

        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setPage(page);
        return pageVO;
    }

    @Override
    public GoodsLoadReportExcelPageVO getExcelForm(RequestGoodLoadVO requestGoodLoadVO, UserVO loginUser) {
        GoodsLoadReportExcelPageVO pageVO = new GoodsLoadReportExcelPageVO();
        BigDecimal quantity = BigDecimal.ZERO;

        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(28);
        map.put("businessVariety", requestGoodLoadVO.getBusinessVariety());
        map.put("param", requestGoodLoadVO.getParam());
        map.put("chainType", requestGoodLoadVO.getChainType());
        map.put("enterpriseCode", requestGoodLoadVO.getEnterpriseCode());
        map.put("enterpriseName", requestGoodLoadVO.getEnterpriseName());
        map.put("startTime", requestGoodLoadVO.getStartTime());
        map.put("endTime", requestGoodLoadVO.getEndTime());
        map.put("code", requestGoodLoadVO.getCode());
        map.put("loadManName", requestGoodLoadVO.getLoadManName());
        map.put("auditManName", requestGoodLoadVO.getAuditManName());
        map.put("status", requestGoodLoadVO.getStatus());

        map.put("categoryId", requestGoodLoadVO.getCategoryId());
        map.put("domesticImport", requestGoodLoadVO.getDomesticImport());
        map.put("storageTemp", requestGoodLoadVO.getStorageTemp());
        map.put("storageConditionName", requestGoodLoadVO.getStorageConditionName());
        map.put("registeredTrademark", requestGoodLoadVO.getRegisteredTrademark());
        map.put("brand", requestGoodLoadVO.getBrand());
        map.put("goodsAttribute", requestGoodLoadVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestGoodLoadVO.getPrescriptionDrug());
        map.put("otcType", requestGoodLoadVO.getOtcType());
        map.put("managementScopeId", requestGoodLoadVO.getManagementScopeId());
        map.put("specialDrug", requestGoodLoadVO.getSpecialDrug());
        map.put("spiritDrugType", requestGoodLoadVO.getSpiritDrugType());
        map.put("inChargeDrug", requestGoodLoadVO.getInChargeDrug());
        map.put("formulationType", requestGoodLoadVO.getFormulationType());

        String order = requestGoodLoadVO.getOrder();
        if ("loadDate".equals(order)){
            order = "load_date";
        }else if ("code".equals(order)){
            order = "load_code";
        }
        map.put("order", order);
        map.put("sort", requestGoodLoadVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<GoodLoadReportPageVO> list = new ArrayList<GoodLoadReportPageVO>();
        List<GoodsLoadExcelVO> excelList = new ArrayList<GoodsLoadExcelVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = goodsLoadMapper.selectFatherGoodLoadReport(map);
            if (list != null && list.size() > 0){
                for (GoodLoadReportPageVO vo : list) {
                    vo = GoodLoadReportPageVO.convertTOStr(vo);
                    GoodsLoadExcelVO excel = new GoodsLoadExcelVO();
                    excel = GoodsLoadExcelVO.convertTOExcel(vo);
                    excelList.add(excel);
                }
            }

            quantity = goodsLoadMapper.selectFatherQuantity(map);
            pageVO.setQuantity(quantity);
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = goodsLoadMapper.selectSonGoodLoadReport(map);
            if (list != null && list.size() > 0){
                for (GoodLoadReportPageVO vo : list) {
                    vo = GoodLoadReportPageVO.convertTOStr(vo);
                    GoodsLoadExcelVO excel = new GoodsLoadExcelVO();
                    excel = GoodsLoadExcelVO.convertTOExcel(vo);
                    excel.setEnterpriseCode(loginUser.getEnterpriseCode());
                    excel.setEnterpriseName(loginUser.getEnterpriseName());
                    excelList.add(excel);
                }
            }
            quantity = goodsLoadMapper.selectSonQuantity(map);
            pageVO.setQuantity(quantity);
        }
        pageVO.setList(excelList);
        return pageVO;
    }

    @Override
    public void export(OutputStream output, GoodsLoadReportExcelPageVO goodsLoadReportExcelPageVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("loadDate", "日期");
        map.put("code", "单号");
        map.put("loadManName", "装斗人员");
        map.put("auditManName", "复核人员");
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
        map.put("srcShelfName", "源货位");
        map.put("srcShelfStatusDesc", "质量状况");
        map.put("targetShelfName", "目标货位");
        map.put("targetShelfStatusDesc", "质量状况");
        map.put("quantity", "数量");
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
        name.add("中药装斗");
        BigDecimal quantity = goodsLoadReportExcelPageVO.getQuantity();

        StringBuilder end = new StringBuilder();
        end.append(quantity == null?"":quantity);
        List<String> list = new ArrayList<String>();
        list.add("quantity");
        purchaseGeneralComponent.commExcelExport(output, map, goodsLoadReportExcelPageVO.getList(), name, new ArrayList<>(), end.toString(), false, list);
    }
}
