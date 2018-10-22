package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsClearReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsClearMapper;
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
public class GoodsClearReportServiceImpl implements GoodsClearReportService{

    @Autowired
    private GoodsClearMapper goodsClearMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public GoodsClearReportVO getGoodClearPage(Page page, RequestGoodClearVO requestGoodClearVO, UserVO loginUser) {
        GoodsClearReportVO pageVO = new GoodsClearReportVO();
        BigDecimal quantity = BigDecimal.ZERO;

        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(30);
        map.put("businessVariety", requestGoodClearVO.getBusinessVariety());
        map.put("param", requestGoodClearVO.getParam());
        map.put("chainType", requestGoodClearVO.getChainType());
        map.put("enterpriseCode", requestGoodClearVO.getEnterpriseCode());
        map.put("enterpriseName", requestGoodClearVO.getEnterpriseName());
        map.put("startTime", requestGoodClearVO.getStartTime());
        map.put("endTime", requestGoodClearVO.getEndTime());
        map.put("code", requestGoodClearVO.getCode());
        map.put("clearManName", requestGoodClearVO.getClearManName());
        map.put("auditManName", requestGoodClearVO.getAuditManName());
        map.put("flowCode", requestGoodClearVO.getFlowCode());
        map.put("status", requestGoodClearVO.getStatus());

        map.put("categoryId", requestGoodClearVO.getCategoryId());
        map.put("domesticImport", requestGoodClearVO.getDomesticImport());
        map.put("storageTemp", requestGoodClearVO.getStorageTemp());
        map.put("storageConditionName", requestGoodClearVO.getStorageConditionName());
        map.put("registeredTrademark", requestGoodClearVO.getRegisteredTrademark());
        map.put("brand", requestGoodClearVO.getBrand());
        map.put("goodsAttribute", requestGoodClearVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestGoodClearVO.getPrescriptionDrug());
        map.put("otcType", requestGoodClearVO.getOtcType());
        map.put("managementScopeId", requestGoodClearVO.getManagementScopeId());
        map.put("specialDrug", requestGoodClearVO.getSpecialDrug());
        map.put("spiritDrugType", requestGoodClearVO.getSpiritDrugType());
        map.put("inChargeDrug", requestGoodClearVO.getInChargeDrug());
        map.put("formulationType", requestGoodClearVO.getFormulationType());

        String order = requestGoodClearVO.getOrder();
        if ("clearDate".equals(order)){
            order = "clear_date";
        }else if ("code".equals(order)){
            order = "code";
        }
        map.put("order", order);
        map.put("sort", requestGoodClearVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<GoodClearReportPageVO> list = new ArrayList<GoodClearReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = goodsClearMapper.selectFatherGoodClearReport(map);
            totalRecord = goodsClearMapper.selectFatherStastic(map);
            if (list != null && list.size() > 0){
                for (GoodClearReportPageVO vo : list) {
                    vo = GoodClearReportPageVO.convertTOStr(vo);
                }
            }
            quantity = goodsClearMapper.selectFatherQuantity(map);
            pageVO.setQuantity(quantity);
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = goodsClearMapper.selectSonGoodClearReport(map);
            totalRecord = goodsClearMapper.selectSonStastic(map);
            if (list != null && list.size() > 0){
                for (GoodClearReportPageVO vo : list) {
                    vo = GoodClearReportPageVO.convertTOStr(vo);
                }
            }
            quantity = goodsClearMapper.selectSonQuantity(map);
            pageVO.setQuantity(quantity);
        }

        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setPage(page);
        return pageVO;
    }

    @Override
    public GoodsClearReportExcelPageVO getExcelForm(RequestGoodClearVO requestGoodClearVO, UserVO loginUser) {
        GoodsClearReportExcelPageVO pageVO = new GoodsClearReportExcelPageVO();
        BigDecimal quantity = BigDecimal.ZERO;

        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(28);
        map.put("businessVariety", requestGoodClearVO.getBusinessVariety());
        map.put("param", requestGoodClearVO.getParam());
        map.put("chainType", requestGoodClearVO.getChainType());
        map.put("enterpriseCode", requestGoodClearVO.getEnterpriseCode());
        map.put("enterpriseName", requestGoodClearVO.getEnterpriseName());
        map.put("startTime", requestGoodClearVO.getStartTime());
        map.put("endTime", requestGoodClearVO.getEndTime());
        map.put("code", requestGoodClearVO.getCode());
        map.put("clearManName", requestGoodClearVO.getClearManName());
        map.put("auditManName", requestGoodClearVO.getAuditManName());
        map.put("flowCode", requestGoodClearVO.getFlowCode());
        map.put("status", requestGoodClearVO.getStatus());

        map.put("categoryId", requestGoodClearVO.getCategoryId());
        map.put("domesticImport", requestGoodClearVO.getDomesticImport());
        map.put("storageTemp", requestGoodClearVO.getStorageTemp());
        map.put("storageConditionName", requestGoodClearVO.getStorageConditionName());
        map.put("registeredTrademark", requestGoodClearVO.getRegisteredTrademark());
        map.put("brand", requestGoodClearVO.getBrand());
        map.put("goodsAttribute", requestGoodClearVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestGoodClearVO.getPrescriptionDrug());
        map.put("otcType", requestGoodClearVO.getOtcType());
        map.put("managementScopeId", requestGoodClearVO.getManagementScopeId());
        map.put("specialDrug", requestGoodClearVO.getSpecialDrug());
        map.put("spiritDrugType", requestGoodClearVO.getSpiritDrugType());
        map.put("inChargeDrug", requestGoodClearVO.getInChargeDrug());
        map.put("formulationType", requestGoodClearVO.getFormulationType());

        String order = requestGoodClearVO.getOrder();
        if ("clearDate".equals(order)){
            order = "clear_date";
        }else if ("code".equals(order)){
            order = "code";
        }
        map.put("order", order);
        map.put("sort", requestGoodClearVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<GoodClearReportPageVO> list = new ArrayList<GoodClearReportPageVO>();
        List<GoodsClearReportExcelVO> excelList = new ArrayList<GoodsClearReportExcelVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = goodsClearMapper.selectFatherGoodClearReport(map);
            if (list != null && list.size() > 0){
                for (GoodClearReportPageVO vo : list) {
                    vo = GoodClearReportPageVO.convertTOStr(vo);
                    GoodsClearReportExcelVO excel = new GoodsClearReportExcelVO();
                    excel = GoodsClearReportExcelVO.convertTOExcel(vo);
                    excelList.add(excel);
                }
            }
            quantity = goodsClearMapper.selectFatherQuantity(map);
            pageVO.setQuantity(quantity);
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = goodsClearMapper.selectSonGoodClearReport(map);
            if (list != null && list.size() > 0){
                for (GoodClearReportPageVO vo : list) {
                    vo = GoodClearReportPageVO.convertTOStr(vo);
                    GoodsClearReportExcelVO excel = new GoodsClearReportExcelVO();
                    excel = GoodsClearReportExcelVO.convertTOExcel(vo);
                    excel.setEnterpriseCode(loginUser.getEnterpriseCode());
                    excel.setEnterpriseName(loginUser.getEnterpriseName());
                    excelList.add(excel);
                }
            }
            quantity = goodsClearMapper.selectSonQuantity(map);
            pageVO.setQuantity(quantity);
        }
        pageVO.setList(excelList);

        return pageVO;
    }

    @Override
    public void export(OutputStream output, GoodsClearReportExcelPageVO goodsClearReportExcelPageVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("clearDate", "日期");
        map.put("code", "单号");
        map.put("clearManName", "装斗人员");
        map.put("auditManName", "复核人员");
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
        name.add("中药清斗");
        BigDecimal quantity = goodsClearReportExcelPageVO.getQuantity();

        StringBuilder end = new StringBuilder();
        end.append(quantity);
        List<String> list = new ArrayList<String>();
        list.add("quantity");
        purchaseGeneralComponent.commExcelExport(output, map, goodsClearReportExcelPageVO.getList(), name, new ArrayList<>(), end.toString(), false, list);
    }
}
