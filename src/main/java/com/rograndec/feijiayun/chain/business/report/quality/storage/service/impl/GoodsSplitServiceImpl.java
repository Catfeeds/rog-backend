package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsSplitService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitMapper;
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
public class GoodsSplitServiceImpl implements GoodsSplitService {

    @Autowired
    private SplitMapper splitMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public GoodsSplitReportVO getGoodsSpiltPage(Page page, RequestGoodsSplitVO requestGoodsSplitVO, UserVO loginUser) {
        GoodsSplitReportVO pageVO = new GoodsSplitReportVO();

        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(31);
        map.put("businessVariety", requestGoodsSplitVO.getBusinessVariety());
        map.put("param", requestGoodsSplitVO.getParam());
        map.put("chainType", requestGoodsSplitVO.getChainType());
        map.put("enterpriseCode", requestGoodsSplitVO.getEnterpriseCode());
        map.put("enterpriseName", requestGoodsSplitVO.getEnterpriseName());
        map.put("startTime", requestGoodsSplitVO.getStartTime());
        map.put("endTime", requestGoodsSplitVO.getEndTime());
        map.put("code", requestGoodsSplitVO.getCode());
        map.put("splitManName", requestGoodsSplitVO.getSplitManName());
        map.put("auditManName", requestGoodsSplitVO.getAuditManName());
        map.put("flowCode", requestGoodsSplitVO.getFlowCode());
        map.put("status", requestGoodsSplitVO.getStatus());

        map.put("categoryId", requestGoodsSplitVO.getCategoryId());
        map.put("domesticImport", requestGoodsSplitVO.getDomesticImport());
        map.put("storageTemp", requestGoodsSplitVO.getStorageTemp());
        map.put("storageConditionName", requestGoodsSplitVO.getStorageConditionName());
        map.put("registeredTrademark", requestGoodsSplitVO.getRegisteredTrademark());
        map.put("brand", requestGoodsSplitVO.getBrand());
        map.put("goodsAttribute", requestGoodsSplitVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestGoodsSplitVO.getPrescriptionDrug());
        map.put("otcType", requestGoodsSplitVO.getOtcType());
        map.put("managementScopeId", requestGoodsSplitVO.getManagementScopeId());
        map.put("specialDrug", requestGoodsSplitVO.getSpecialDrug());
        map.put("spiritDrugType", requestGoodsSplitVO.getSpiritDrugType());
        map.put("inChargeDrug", requestGoodsSplitVO.getInChargeDrug());
        map.put("formulationType", requestGoodsSplitVO.getFormulationType());

        String order = requestGoodsSplitVO.getOrder();
        if ("splitDate".equals(order)){
            order = "split_date";
        }else if ("code".equals(order)){
            order = "split_code";
        }
        map.put("order", order);
        map.put("sort", requestGoodsSplitVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",page.getStart());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<GoodsSplitReportPageVO> list = new ArrayList<GoodsSplitReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = splitMapper.selectFatherGoodSplitReport(map);
            totalRecord = splitMapper.selectFatherTotalRecord(map);
            GoodsSplitStasticVO stasticVO = splitMapper.selectFatherGoodsSplitStastic(map);
            if (stasticVO == null){
                pageVO.setQuantity(BigDecimal.ZERO);
                pageVO.setSplitQuantity(BigDecimal.ZERO);
            }else {
                pageVO.setQuantity(stasticVO.getQuantity() == null ? BigDecimal.ZERO : stasticVO.getQuantity());
                pageVO.setSplitQuantity(stasticVO.getSplitQuantity() == null ? BigDecimal.ZERO : stasticVO.getSplitQuantity());
            }
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = splitMapper.selectSonGoodSplitReport(map);
            totalRecord = splitMapper.selectSonTotalRecord(map);
            GoodsSplitStasticVO stasticVO = splitMapper.selectSonGoodsSplitStastic(map);
            if (stasticVO == null){
                pageVO.setQuantity(BigDecimal.ZERO);
                pageVO.setSplitQuantity(BigDecimal.ZERO);
            }else {
                pageVO.setQuantity(stasticVO.getQuantity() == null ? BigDecimal.ZERO : stasticVO.getQuantity());
                pageVO.setSplitQuantity(stasticVO.getSplitQuantity() == null ? BigDecimal.ZERO : stasticVO.getSplitQuantity());
            }
        }
        if (list != null && list.size() > 0){
            for (GoodsSplitReportPageVO vo : list) {
                vo = GoodsSplitReportPageVO.convertTOStr(vo);
                Long goodsId = vo.getGoodsId();
                if (goodsId != null){
                    Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                    vo.setBarcode(goods.getBarcode());
                    vo.setGoodsGenericName(goods.getGenericName());
                    vo.setDosageName(goods.getDosageName());
                    vo.setGoodsSpecification(goods.getSpecification());
                    vo.setUnitName(goods.getUnitName());
                    vo.setManufacturer(goods.getManufacturer());
                    vo.setGoodsPlace(goods.getPlace());
                    vo.setApprovalNumber(goods.getApprovalNumber());
                    Long splitGoodsId = vo.getSplitGoodsId();
                    if (splitGoodsId != null){
                        Goods splitGoods = goodsMapper.selectByPrimaryKey(splitGoodsId);
                        vo.setSplitGoodsSpecification(splitGoods.getSpecification());
                        vo.setSplitUnitName(splitGoods.getUnitName());
                    }
                }
            }
        }

        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setPage(page);
        return pageVO;
    }

    @Override
    public GoodsSplitReportExcelPageVO getExcelForm(RequestGoodsSplitVO requestGoodsSplitVO, UserVO loginUser) {
        GoodsSplitReportExcelPageVO pageVO = new GoodsSplitReportExcelPageVO();
        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(29);
        map.put("businessVariety", requestGoodsSplitVO.getBusinessVariety());
        map.put("param", requestGoodsSplitVO.getParam());
        map.put("chainType", requestGoodsSplitVO.getChainType());
        map.put("enterpriseCode", requestGoodsSplitVO.getEnterpriseCode());
        map.put("enterpriseName", requestGoodsSplitVO.getEnterpriseName());
        map.put("startTime", requestGoodsSplitVO.getStartTime());
        map.put("endTime", requestGoodsSplitVO.getEndTime());
        map.put("code", requestGoodsSplitVO.getCode());
        map.put("splitManName", requestGoodsSplitVO.getSplitManName());
        map.put("auditManName", requestGoodsSplitVO.getAuditManName());
        map.put("flowCode", requestGoodsSplitVO.getFlowCode());
        map.put("status", requestGoodsSplitVO.getStatus());

        map.put("categoryId", requestGoodsSplitVO.getCategoryId());
        map.put("domesticImport", requestGoodsSplitVO.getDomesticImport());
        map.put("storageTemp", requestGoodsSplitVO.getStorageTemp());
        map.put("storageConditionName", requestGoodsSplitVO.getStorageConditionName());
        map.put("registeredTrademark", requestGoodsSplitVO.getRegisteredTrademark());
        map.put("brand", requestGoodsSplitVO.getBrand());
        map.put("goodsAttribute", requestGoodsSplitVO.getGoodsAttribute());
        map.put("prescriptionDrug", requestGoodsSplitVO.getPrescriptionDrug());
        map.put("otcType", requestGoodsSplitVO.getOtcType());
        map.put("managementScopeId", requestGoodsSplitVO.getManagementScopeId());
        map.put("specialDrug", requestGoodsSplitVO.getSpecialDrug());
        map.put("spiritDrugType", requestGoodsSplitVO.getSpiritDrugType());
        map.put("inChargeDrug", requestGoodsSplitVO.getInChargeDrug());
        map.put("formulationType", requestGoodsSplitVO.getFormulationType());

        String order = requestGoodsSplitVO.getOrder();
        if ("splitDate".equals(order)){
            order = "split_date";
        }else if ("code".equals(order)){
            order = "split_code";
        }
        map.put("order", order);
        map.put("sort", requestGoodsSplitVO.getSort());
        map.put("enterpriseId", loginUser.getEnterpriseId());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<GoodsSplitReportPageVO> list = new ArrayList<GoodsSplitReportPageVO>();
        List<GoodsSplitReportExcelVO> excelList = new ArrayList<GoodsSplitReportExcelVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = splitMapper.selectFatherGoodSplitReport(map);
            GoodsSplitStasticVO stasticVO = splitMapper.selectFatherGoodsSplitStastic(map);
            pageVO.setQuantity(stasticVO.getQuantity());
            pageVO.setSplitQuantity(stasticVO.getSplitQuantity());
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = splitMapper.selectSonGoodSplitReport(map);
            GoodsSplitStasticVO stasticVO = splitMapper.selectSonGoodsSplitStastic(map);
            if(stasticVO != null){
                pageVO.setQuantity(stasticVO.getQuantity());
                pageVO.setSplitQuantity(stasticVO.getSplitQuantity());
            }
        }
        if (list != null && list.size() > 0){
            for (GoodsSplitReportPageVO vo : list) {
                GoodsSplitReportExcelVO excel = new GoodsSplitReportExcelVO();
                vo = GoodsSplitReportPageVO.convertTOStr(vo);
                Long goodsId = vo.getGoodsId();
                if (goodsId != null){
                    Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                    vo.setBarcode(goods.getBarcode());
                    vo.setGoodsGenericName(goods.getGenericName());
                    vo.setDosageName(goods.getDosageName());
                    vo.setGoodsSpecification(goods.getSpecification());
                    vo.setUnitName(goods.getUnitName());
                    vo.setManufacturer(goods.getManufacturer());
                    vo.setGoodsPlace(goods.getPlace());
                    vo.setApprovalNumber(goods.getApprovalNumber());
                    Long splitGoodsId = vo.getSplitGoodsId();
                    if (splitGoodsId != null){
                        Goods splitGoods = goodsMapper.selectByPrimaryKey(splitGoodsId);
                        vo.setSplitGoodsSpecification(splitGoods.getSpecification());
                        vo.setSplitUnitName(splitGoods.getUnitName());
                    }
                }
                /**
                 * 转变成excel数据
                 */
                excel = GoodsSplitReportExcelVO.convertTOExcel(vo);
                if (userChainType != ChainType.Headquarters.getCode()){
                    /**
                     * 分店情况 组织机构编码 + 组织机构名称 显示当前分店的信息
                     */
                    excel.setEnterpriseCode(loginUser.getEnterpriseCode());
                    excel.setEnterpriseName(loginUser.getEnterpriseName());
                }
                excelList.add(excel);
            }
        }

        pageVO.setList(excelList);
        return pageVO;
    }

    @Override
    public void export(OutputStream output, GoodsSplitReportExcelPageVO goodsSplitReportExcelPageVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("splitDate", "日期");
        map.put("code", "单号");
        map.put("splitManName", "分拆人员");
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
        map.put("splitGoodsCode", "拆零商品编码");
        map.put("splitUnitName", "拆零商品单位");
        map.put("splitGoodsSpecification", "拆零商品规格");
        map.put("splitShelfName", "拆零商品货位");
        map.put("splitShelfStatusDesc", "拆零商品质量状况");
        map.put("splitQuantity", "拆零商品数量");
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
        name.add("商品拆零");
        BigDecimal quantity = goodsSplitReportExcelPageVO.getQuantity();
        BigDecimal splitQuantity = goodsSplitReportExcelPageVO.getSplitQuantity();
        StringBuilder end = new StringBuilder();
        end.append(quantity);
        end.append(splitQuantity);
        List<String> list = new ArrayList<String>();
        list.add("quantity");
        list.add("splitQuantity");
        purchaseGeneralComponent.commExcelExport(output, map, goodsSplitReportExcelPageVO.getList(), name, new ArrayList<>(), end.toString(), false, list);
    }
}
