package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.ShelfMoveReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.business.storage.move.dao.ShelfMoveMapper;
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
public class ShelfMoveReportServiceImpl implements ShelfMoveReportService{

    @Autowired
    private ShelfMoveMapper shelfMoveMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public ShelfMovePageVO  getShelfMovePage(Page page, RequestShelfMoveVO requestShelfMoveVO, UserVO loginUser) {
        ShelfMovePageVO pageVO = new ShelfMovePageVO();
        BigDecimal quantity = BigDecimal.ZERO;
        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(29);
        map.put("businessVariety", requestShelfMoveVO.getBusinessVariety());
        map.put("param", requestShelfMoveVO.getParam());
        map.put("chainType", requestShelfMoveVO.getChainType());
        map.put("enterpriseCode", requestShelfMoveVO.getEnterpriseCode());
        map.put("enterpriseName", requestShelfMoveVO.getEnterpriseName());
        map.put("startTime", requestShelfMoveVO.getStartTime());
        map.put("endTime", requestShelfMoveVO.getEndTime());
        map.put("code", requestShelfMoveVO.getCode());
        map.put("moveManName", requestShelfMoveVO.getMoveManName());
        map.put("receiverName", requestShelfMoveVO.getReceiverName());
        map.put("categoryId", requestShelfMoveVO.getCategoryId());

        map.put("domesticImport", requestShelfMoveVO.getDomesticImport());
        map.put("storageTemp", requestShelfMoveVO.getStorageTemp());
        map.put("storageConditionName", requestShelfMoveVO.getStorageConditionName());
        map.put("registeredTrademark", requestShelfMoveVO.getRegisteredTrademark());
        map.put("brand", requestShelfMoveVO.getBrand());
        map.put("goodsAttribute", requestShelfMoveVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestShelfMoveVO.getPrescriptionDrug());
        map.put("otcType", requestShelfMoveVO.getOtcType());
        map.put("managementScopeId", requestShelfMoveVO.getManagementScopeId());
        map.put("specialDrug", requestShelfMoveVO.getSpecialDrug());
        map.put("spiritDrugType", requestShelfMoveVO.getSpiritDrugType());
        map.put("inChargeDrug", requestShelfMoveVO.getInChargeDrug());
        map.put("formulationType", requestShelfMoveVO.getFormulationType());

        String order = requestShelfMoveVO.getOrder();
        if ("moveDate".equals(order)){
            order = "move_date";
        }else if ("code".equals(order)){
            order = "move_code";
        }
        map.put("order", order);
        map.put("sort", requestShelfMoveVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<ShelfMoveVO> list = new ArrayList<ShelfMoveVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = shelfMoveMapper.selectFatherMoveReport(map);
            totalRecord = shelfMoveMapper.selectFatherMoveTotalRecord(map);
            if (list != null && list.size() > 0){
                for (ShelfMoveVO move : list) {
                    move = ShelfMoveVO.converTOStr(move);
                }
            }
            quantity = shelfMoveMapper.selectFatherQuantity(map);
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = shelfMoveMapper.selectSonMoveReport(map);
            totalRecord = shelfMoveMapper.selectSonMoveTotalRecord(map);
            if (list != null && list.size() > 0){
                for (ShelfMoveVO move : list) {
                    move = ShelfMoveVO.converTOStr(move);
                }
            }
            quantity = shelfMoveMapper.selectSonQuantity(map);
        }

        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setQuantity(quantity);
        pageVO.setPage(page);
        return pageVO;
    }

    @Override
    public ShelfMoveExcelPageVO getExcelForm(RequestShelfMoveVO requestShelfMoveVO, UserVO loginUser) {
        ShelfMoveExcelPageVO vo = new ShelfMoveExcelPageVO();
        Map<String, Object> map = new HashMap<String, Object>(25);
        map.put("businessVariety", requestShelfMoveVO.getBusinessVariety());
        map.put("param", requestShelfMoveVO.getParam());
        map.put("chainType", requestShelfMoveVO.getChainType());
        map.put("enterpriseCode", requestShelfMoveVO.getEnterpriseCode());
        map.put("enterpriseName", requestShelfMoveVO.getEnterpriseName());
        map.put("startTime", requestShelfMoveVO.getStartTime());
        map.put("endTime", requestShelfMoveVO.getEndTime());
        map.put("code", requestShelfMoveVO.getCode());
        map.put("moveManName", requestShelfMoveVO.getMoveManName());
        map.put("receiverName", requestShelfMoveVO.getReceiverName());
        map.put("categoryId", requestShelfMoveVO.getCategoryId());

        map.put("domesticImport", requestShelfMoveVO.getDomesticImport());
        map.put("storageTemp", requestShelfMoveVO.getStorageTemp());
        map.put("storageConditionName", requestShelfMoveVO.getStorageConditionName());
        map.put("registeredTrademark", requestShelfMoveVO.getRegisteredTrademark());
        map.put("brand", requestShelfMoveVO.getBrand());
        map.put("goodsAttribute", requestShelfMoveVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestShelfMoveVO.getPrescriptionDrug());
        map.put("otcType", requestShelfMoveVO.getOtcType());
        map.put("managementScopeId", requestShelfMoveVO.getManagementScopeId());
        map.put("specialDrug", requestShelfMoveVO.getSpecialDrug());
        map.put("inChargeDrug", requestShelfMoveVO.getInChargeDrug());
        map.put("spiritDrugType", requestShelfMoveVO.getSpiritDrugType());
        map.put("formulationType", requestShelfMoveVO.getFormulationType());

        String order = requestShelfMoveVO.getOrder();
        if ("moveDate".equals(order)){
            order = "move_date";
        }else if ("code".equals(order)){
            order = "move_code";
        }
        map.put("order", order);
        map.put("sort", requestShelfMoveVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        BigDecimal quantity = BigDecimal.ZERO;
        Integer userChainType = loginUser.getChainType();
        List<ShelfMoveVO> list = new ArrayList<ShelfMoveVO>();
        List<ShelfMoveExcelVO> excelList = new ArrayList<ShelfMoveExcelVO>();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = shelfMoveMapper.selectFatherMoveReport(map);
            for (ShelfMoveVO move : list) {
                move = ShelfMoveVO.converTOStr(move);
            }
            quantity = shelfMoveMapper.selectFatherQuantity(map);
            if (list != null && list.size() > 0){
                for (ShelfMoveVO move : list) {
                    ShelfMoveExcelVO excelVO = new ShelfMoveExcelVO();
                    excelVO = ShelfMoveExcelVO.convertTOExcelVO(move);
                    excelList.add(excelVO);
                }
            }
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = shelfMoveMapper.selectSonMoveReport(map);
            for (ShelfMoveVO move : list) {
                move = ShelfMoveVO.converTOStr(move);
            }
            quantity = shelfMoveMapper.selectSonQuantity(map);
            if (list != null && list.size() > 0){
                for (ShelfMoveVO move : list) {
                    ShelfMoveExcelVO excelVO = new ShelfMoveExcelVO();
                    excelVO = ShelfMoveExcelVO.convertTOExcelVO(move);
                    excelVO.setEnterpriseCode(loginUser.getEnterpriseCode());
                    excelVO.setEnterpriseName(loginUser.getEnterpriseName());
                    excelList.add(excelVO);
                }
            }
        }
        vo.setList(excelList);
        vo.setQuantity(quantity);
        return vo;
    }

    @Override
    public void export(OutputStream output, ShelfMoveExcelPageVO shelfMoveExcelVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("moveDate", "日期");
        map.put("code", "单号");
        map.put("moveManName", "移动人员");
        map.put("receiverName", "接收人员");
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
        name.add("货位移动");
        BigDecimal quantity = shelfMoveExcelVO.getQuantity();
        StringBuilder end = new StringBuilder();
        end.append(quantity == null?"":quantity);
        List<String> list = new ArrayList<String>();
        list.add("quantity");
        purchaseGeneralComponent.commExcelExport(output, map, shelfMoveExcelVO.getList(), name, new ArrayList<>(), end.toString(), false, list);
    }
}
