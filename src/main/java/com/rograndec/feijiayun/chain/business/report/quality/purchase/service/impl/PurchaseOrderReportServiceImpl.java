package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseOrderReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.OrderPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseOrderReportVO;
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
public class PurchaseOrderReportServiceImpl implements PurchaseOrderReportService {
    @Autowired
    PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Override
    public Page<TotalReportVO<PurchaseOrderReportVO>> getPurchaseOrderPage(UserVO loginUser, OrderPageVO orderPageVO, Page<TotalReportVO<PurchaseOrderReportVO>> page) throws Exception {
        if(orderPageVO.getOrderName() != null && orderPageVO.getOrderType() != null){
            if(orderPageVO.getOrderName().equals("orderDate")){
                orderPageVO.setOrderName("dtl.order_date");
            } else if(orderPageVO.getOrderName().equals("orderCode") || orderPageVO.getOrderName().equals("code")){
                orderPageVO.setOrderName("dtl.order_code");
            }else {
                orderPageVO.setOrderName(null);
                orderPageVO.setOrderType(null);
            }
        }
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<PurchaseOrderReportVO> purchaseOrderReportVOS = purchaseOrderDetailMapper.getPlanReportInfo(loginUser.getEnterpriseId(),orderPageVO);
        TotalReportVO<PurchaseOrderReportVO> totalReportVO = getGoodsInfo(purchaseOrderReportVOS);
        page.setResult(totalReportVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, OrderPageVO orderPageVO) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("orderDate","日期");
        map.put("code","单号");
        map.put("supplierSalerName","销售人员");
        map.put("supplierSalerPhone","联系电话");
        map.put("purchaserName","采购人员");
        map.put("arrivalTime","预计到货日期");
        map.put("baseOrderCode","计划单号");
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
        map.put("lineDiscount","折扣");
        map.put("amount","金额");
        map.put("wholeDiscount","整单折扣");
        map.put("lineDiscountAmount","优惠分摊");
        map.put("realPrice","实际单价");
        map.put("realAmount","实际金额");
        map.put("taxRate","税率");
        map.put("notaxRealPrice","不含税单价");
        map.put("notaxRealAmount","不含税金额");
        map.put("taxAmout","税额");
        map.put("statusName","状态");
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
        List<PurchaseOrderReportVO> purchaseOrderReportVOS = purchaseOrderDetailMapper.getPlanReportInfo(loginUser.getEnterpriseId(),orderPageVO);
        TotalReportVO<PurchaseOrderReportVO> totalReportVO = getGoodsInfo(purchaseOrderReportVOS);
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal notaxRealAmount = BigDecimal.ZERO;
        BigDecimal taxAmout = BigDecimal.ZERO;
        for(PurchaseOrderReportVO purchaseOrderReportVO : purchaseOrderReportVOS){
            amount = amount.add(purchaseOrderReportVO.getAmount());
            realAmount = amount.add(purchaseOrderReportVO.getAmount());
            quantity = quantity.add(purchaseOrderReportVO.getQuantity());
            notaxRealAmount = notaxRealAmount.add(purchaseOrderReportVO.getNotaxRealAmount());
            taxAmout = taxAmout.add(purchaseOrderReportVO.getTaxAmount());
        }
        end.append(quantity);
        end.append(";");
        end.append(amount);
        end.append(";");
        end.append(notaxRealAmount);
        end.append(";");
        end.append(taxAmout);
        end.append(";");
        end.append(realAmount);
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("quantity");
        needTotalName.add("amount");
        needTotalName.add("notaxAmount");
        needTotalName.add("taxAmout");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("采购计划");
        orderPageVO.setPageNo(null);
        orderPageVO.setPageSize(null);
        purchaseGeneralComponent.commExcelExport(output,map,totalReportVO.getData(),name,titleSecond,end.toString(),false,needTotalName);
    }

    private TotalReportVO<PurchaseOrderReportVO> getGoodsInfo(List<PurchaseOrderReportVO> purchaseOrderReportVOS) {
        TotalReportVO<PurchaseOrderReportVO> totalReportVO = new TotalReportVO<>();
        BigDecimal quantityTotal = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmoutTotal = BigDecimal.ZERO;
        for(PurchaseOrderReportVO purchaseOrderReportVO : purchaseOrderReportVOS){
            purchaseOrderReportVO.setBusinessVarietyName(BusinessVariety.getName(purchaseOrderReportVO.getBusinessVariety()));
            //商品分类
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(purchaseOrderReportVO.getCategoryId());
            purchaseOrderReportVO.setCategoryName(goodsCategory==null?null:goodsCategory.getName());
            //商品属性
            purchaseOrderReportVO.setGoodsAttributeName(purchaseOrderReportVO.getGoodsAttribute()==null?null: GoodsAttribute2DrugsA.getName(purchaseOrderReportVO.getGoodsAttribute()));
            //国产/进口
            purchaseOrderReportVO.setDomesticImportName(purchaseOrderReportVO.getDomesticImport()==null?null: DomesticImport.getName(purchaseOrderReportVO.getDomesticImport()));
            //特殊管理药品
            purchaseOrderReportVO.setSpecialDrugName(purchaseOrderReportVO.getSpecialDrug()==null?null: SpecialDrugs.getName(purchaseOrderReportVO.getSpecialDrug()));
            //专管药品
            purchaseOrderReportVO.setInChargeDrugName(purchaseOrderReportVO.getInChargeDrug()==null?null: ChargeDrugs.getName(purchaseOrderReportVO.getInChargeDrug()));
            //贮藏温度
            purchaseOrderReportVO.setStorageTempName(purchaseOrderReportVO.getStorageTemp()==null?null:StorageTemp.getName(purchaseOrderReportVO.getStorageTemp()));
            //保质期
            purchaseOrderReportVO.setQualityPeriodUnitName(purchaseOrderReportVO.getQualityPeriod().toString()+GoodsQualityPeriodUnit.getName(purchaseOrderReportVO.getQualityPeriodUnit()));
            //状态
            purchaseOrderReportVO.setStatusName(PurchaseStatus.getName(purchaseOrderReportVO.getStatus()));
            quantityTotal = quantityTotal.add(purchaseOrderReportVO.getQuantity()==null?BigDecimal.ZERO:purchaseOrderReportVO.getQuantity());
            amountTotal = amountTotal.add(purchaseOrderReportVO.getAmount()==null?BigDecimal.ZERO:purchaseOrderReportVO.getAmount());
            realAmountTotal = realAmountTotal.add(purchaseOrderReportVO.getRealAmount()==null?BigDecimal.ZERO:purchaseOrderReportVO.getRealAmount());
            notaxAmountTotal = notaxAmountTotal.add(purchaseOrderReportVO.getNotaxRealAmount()==null?BigDecimal.ZERO:purchaseOrderReportVO.getNotaxRealAmount());
            taxAmoutTotal = taxAmoutTotal.add(purchaseOrderReportVO.getTaxAmount()==null?BigDecimal.ZERO:purchaseOrderReportVO.getTaxAmount());
        }
        totalReportVO.setData(purchaseOrderReportVOS);
        totalReportVO.setTaxAmoutTotal(taxAmoutTotal);
        totalReportVO.setAmountTotal(amountTotal);
        totalReportVO.setNotaxAmountTotal(notaxAmountTotal);
        totalReportVO.setQuantityTotal(quantityTotal);
        totalReportVO.setRealAmountTotal(realAmountTotal);
        return totalReportVO;
    }

}
