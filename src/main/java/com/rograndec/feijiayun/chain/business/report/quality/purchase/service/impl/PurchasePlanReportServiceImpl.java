package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.purchase.plan.dao.PurchasePlanDetailMapper;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchasePlanReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PlanPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchasePlanReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PurchasePlanReportServiceImpl implements PurchasePlanReportService {
    @Autowired
    PurchasePlanDetailMapper purchasePlanDetailMapper;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Override
    public Page<TotalReportVO<PurchasePlanReportVO>> getPurchasePlanPage(UserVO loginUser, PlanPageVO planPageVO, Page<TotalReportVO<PurchasePlanReportVO>> page) throws Exception {
        if(planPageVO.getOrderName() != null && planPageVO.getOrderType() != null){
            if(planPageVO.getOrderName().equals("planDate")){
                planPageVO.setOrderName("doc.plan_date");
            } else if(planPageVO.getOrderName().equals("code")){
                planPageVO.setOrderName("doc.code");
            }else {
                planPageVO.setOrderName(null);
                planPageVO.setOrderType(null);
            }
        }
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<PurchasePlanReportVO> purchasePlanReportVOS = purchasePlanDetailMapper.getPlanReportInfo(loginUser.getEnterpriseId(),planPageVO);
        TotalReportVO<PurchasePlanReportVO> totalReportVO = getGoodsInfo(purchasePlanReportVOS);
        page.setResult(totalReportVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, PlanPageVO planPageVO) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("planDate","日期");
        map.put("code","单号");
        map.put("pannerName","计划人员");
        map.put("goodsCode","商品编码");
        map.put("barcode","条形码");
        map.put("goodsGenericName","通用名称");
        map.put("goodsName","商品名称");
        map.put("dosageName","剂型");
        map.put("specification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("approvalNumber","批准文号");
        map.put("quantity","数量");
        map.put("unitPrice","单价");
        map.put("amount","金额");
        map.put("taxRate","税率");
        map.put("notaxPrice","不含税单价");
        map.put("notaxAmount","不含税金额");
        map.put("taxAmout","税额");
        map.put("supplierCode","供货单位编码");
        map.put("supplierName","供货单位名称");
        map.put("statusName","状态");
        map.put("businessVarietyName","品种类型");
        map.put("categoryName","商品分类");
        map.put("domesticImportName","国产/进口");
        map.put("managementScopeName","经营范围");
        map.put("specialDrugName","特殊管理药品");
        map.put("inChargeDrugName","专管药品");
        map.put("limitQuantity","限购数量");
        map.put("storageTempName","贮藏温度");
        map.put("storageConditionName","贮藏条件");
        map.put("qualityPeriodName","保质期");
        List<PurchasePlanReportVO> purchasePlanReportVOS = purchasePlanDetailMapper.getPlanReportInfo(loginUser.getEnterpriseId(),planPageVO);
        TotalReportVO<PurchasePlanReportVO> totalReportVO = getGoodsInfo(purchasePlanReportVOS);
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal notaxAmount = BigDecimal.ZERO;
        BigDecimal taxAmout = BigDecimal.ZERO;
        for(PurchasePlanReportVO purchasePlanReportVO : purchasePlanReportVOS){
            amount = amount.add(purchasePlanReportVO.getAmount());
            quantity = quantity.add(purchasePlanReportVO.getQuantity());
            notaxAmount = notaxAmount.add(purchasePlanReportVO.getNotaxAmount());
            taxAmout = taxAmout.add(purchasePlanReportVO.getTaxAmout());
        }
        end.append(quantity);
        end.append(";");
        end.append(amount);
        end.append(";");
        end.append(notaxAmount);
        end.append(";");
        end.append(taxAmout);
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("quantity");
        needTotalName.add("amount");
        needTotalName.add("notaxAmount");
        needTotalName.add("taxAmout");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("采购计划");
        planPageVO.setPageNo(null);
        planPageVO.setPageSize(null);
        purchaseGeneralComponent.commExcelExport(output,map,totalReportVO.getData(),name,titleSecond,end.toString(),false,needTotalName);

    }

    private TotalReportVO<PurchasePlanReportVO> getGoodsInfo(List<PurchasePlanReportVO> purchasePlanReportVOS) {
        TotalReportVO<PurchasePlanReportVO> totalReportVO = new TotalReportVO<>();
        BigDecimal quantityTotal = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmoutTotal = BigDecimal.ZERO;
        for(PurchasePlanReportVO purchasePlanReportVO : purchasePlanReportVOS){
            purchasePlanReportVO.setBusinessVarietyName(BusinessVariety.getName(purchasePlanReportVO.getBusinessVariety()));
            //商品分类
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(purchasePlanReportVO.getCategoryId());
            purchasePlanReportVO.setCategoryName(goodsCategory==null?null:goodsCategory.getName());
            //商品属性
            purchasePlanReportVO.setGoodsAttributeName(purchasePlanReportVO.getGoodsAttribute()==null?null: GoodsAttribute2DrugsA.getName(purchasePlanReportVO.getGoodsAttribute()));
            //国产/进口
            purchasePlanReportVO.setDomesticImportName(purchasePlanReportVO.getDomesticImport()==null?null: DomesticImport.getName(purchasePlanReportVO.getDomesticImport()));
            //特殊管理药品
            purchasePlanReportVO.setSpecialDrugName(purchasePlanReportVO.getSpecialDrug()==null?null: SpecialDrugs.getName(purchasePlanReportVO.getSpecialDrug()));
            //专管药品
            purchasePlanReportVO.setInChargeDrugName(purchasePlanReportVO.getInChargeDrug()==null?null: ChargeDrugs.getName(purchasePlanReportVO.getInChargeDrug()));
            //贮藏温度
            purchasePlanReportVO.setStorageTempName(purchasePlanReportVO.getStorageTemp()==null?null:StorageTemp.getName(purchasePlanReportVO.getStorageTemp()));
            //保质期
            purchasePlanReportVO.setQualityPeriodUnitName(purchasePlanReportVO.getQualityPeriod().toString()+GoodsQualityPeriodUnit.getName(purchasePlanReportVO.getQualityPeriodUnit()));
            //状态
            purchasePlanReportVO.setStatusName(PurchaseStatus.getName(purchasePlanReportVO.getStatus()));
            quantityTotal = quantityTotal.add(purchasePlanReportVO.getQuantity()==null?BigDecimal.ZERO:purchasePlanReportVO.getQuantity());
            amountTotal = amountTotal.add(purchasePlanReportVO.getAmount()==null?BigDecimal.ZERO:purchasePlanReportVO.getAmount());
            notaxAmountTotal = notaxAmountTotal.add(purchasePlanReportVO.getNotaxAmount()==null?BigDecimal.ZERO:purchasePlanReportVO.getNotaxAmount());
            taxAmoutTotal = taxAmoutTotal.add(purchasePlanReportVO.getTaxAmout()==null?BigDecimal.ZERO:purchasePlanReportVO.getTaxAmout());
        }
        totalReportVO.setTaxAmoutTotal(taxAmoutTotal);
        totalReportVO.setAmountTotal(amountTotal);
        totalReportVO.setNotaxAmountTotal(notaxAmountTotal);
        totalReportVO.setQuantityTotal(quantityTotal);
        totalReportVO.setData(purchasePlanReportVOS);
        return totalReportVO;
    }
}
