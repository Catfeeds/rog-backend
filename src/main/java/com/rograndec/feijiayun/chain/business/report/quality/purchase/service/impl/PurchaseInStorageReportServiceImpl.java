package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseInStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalPurchaseInStorageReportVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.service.ChGoodsLoadService;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.GoodsShelfStatusDescVO;
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
public class PurchaseInStorageReportServiceImpl implements PurchaseInStorageReportService {

    @Autowired
    PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;
    @Autowired
    ChGoodsLoadService chGoodsLoadService;
    @Autowired
    PurchaseGeneralComponent<PurchaseInStorageReportPageVO> purchaseGeneralComponent;

    @Override
    public Page<TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO>> getPurchaseInStorageReportPage(Page<TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO>> page, PurchaseInStorageReportListVO paramForListVO, Long enterpriseId) {

//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(paramForListVO.getPageNo(), paramForListVO.getPageSize());
        if(paramForListVO.getOrderName() != null && paramForListVO.getOrderName().equals("inStorageDate")){
            paramForListVO.setOrderName("in_storage_date");
        }else{
            paramForListVO.setOrderName(null);
            paramForListVO.setOrderType(null);
        }
        //先获取采购验收明细信息
        paramForListVO.setPageStart(page.getStart());
        Integer count = purchaseInStorageShelfMapper.getPurchaseInStorageReportPageCount(paramForListVO, enterpriseId);
        List<PurchaseInStorageReportPageVO> purchaseInStorageReportPageVO = purchaseInStorageShelfMapper.getPurchaseInStorageReportPageList(paramForListVO, enterpriseId);
        TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO> totalPurchaseInStorageReportVO = setDataName(purchaseInStorageReportPageVO, enterpriseId);

        paramForListVO.setPageStart(null);
        paramForListVO.setPageSize(null);
        List<PurchaseInStorageReportPageVO> purchaseInStorageReportPage = purchaseInStorageShelfMapper.getPurchaseInStorageReportPageList(paramForListVO, enterpriseId);
        TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO> totalPurchaseInStorageReport = setDataName(purchaseInStorageReportPage, enterpriseId);
        totalPurchaseInStorageReportVO.setAmountAll(totalPurchaseInStorageReport.getAmountAll());
        totalPurchaseInStorageReportVO.setNotaxRealAmountAll(totalPurchaseInStorageReport.getNotaxRealAmountAll());
        totalPurchaseInStorageReportVO.setQuantityAll(totalPurchaseInStorageReport.getQuantityAll());
        totalPurchaseInStorageReportVO.setRealAmountAll(totalPurchaseInStorageReport.getRealAmountAll());
        totalPurchaseInStorageReportVO.setTaxAmountAll(totalPurchaseInStorageReport.getTaxAmountAll());

        page.setResult(totalPurchaseInStorageReportVO);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, PurchaseInStorageReportListVO paramForListVO) {

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("inStorageDate","日期");
        map.put("code","单号");
        map.put("supplierCode","供货单位编码");
        map.put("supplierName","供货单位名称");
        map.put("supplierSalerName","销售人员");
        map.put("supplierSalerPhone","联系电话");
        map.put("checkerName","入库人员");
        map.put("purchaseOrderCode","订单单号");
        map.put("baseOrderCode","验收单号");
        map.put("goodsCode","商品编码");
        map.put("barcode","条形码");
        map.put("goodsGenericName","通用名称");
        map.put("goodsName","商品名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("approvalNumber","批准文号");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("shelfName","货位");
        map.put("shelfStatusDesc","质量状况");
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
        map.put("taxAmount","税额");
        map.put("statusName","状态");
        map.put("businessVarietyName","品种类型");
        map.put("categoryName","商品分类");
        map.put("goodsAttributeAll","商品属性");
        map.put("domesticImportName","国产/进口");
        map.put("managementScopeName","经营范围");
        map.put("specialDrugAll","特殊管理药品");
        map.put("inChargeDrugAll","专管药品");
        map.put("limitQuantity","限购数量");
        map.put("storageTempName","贮藏温度");
        map.put("storageConditionName","贮藏条件");
        map.put("qualityPeriodAll","保质期");
        List<PurchaseInStorageReportPageVO> purchaseInStorageReportPageVOS = purchaseInStorageShelfMapper.getPurchaseInStorageReportPageList(paramForListVO, loginUser.getEnterpriseId());
        setDataName(purchaseInStorageReportPageVOS, loginUser.getEnterpriseId());
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        //分批号数量
        BigDecimal quantity = BigDecimal.ZERO;//数量
        BigDecimal amount = BigDecimal.ZERO;//金额
        BigDecimal realAmount = BigDecimal.ZERO;//实际金额
        BigDecimal notaxRealAmount = BigDecimal.ZERO;//不含税金额
        BigDecimal taxAmount = BigDecimal.ZERO;//税额

        for (PurchaseInStorageReportPageVO purchaseInStorageReportPageVO : purchaseInStorageReportPageVOS) {
            quantity = quantity.add(purchaseInStorageReportPageVO.getQuantity());
            amount = amount.add(purchaseInStorageReportPageVO.getAmount());
            realAmount = realAmount.add(purchaseInStorageReportPageVO.getRealAmount());
            notaxRealAmount = notaxRealAmount.add(purchaseInStorageReportPageVO.getNotaxRealAmount());
            taxAmount = taxAmount.add(purchaseInStorageReportPageVO.getTaxAmount());
        }
        end.append(quantity);
        end.append(";");
        end.append(amount);
        end.append(";");
        end.append(realAmount);
        end.append(";");
        end.append(notaxRealAmount);
        end.append(";");
        end.append(taxAmount);
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("quantity");
        needTotalName.add("amount");
        needTotalName.add("realAmount");
        needTotalName.add("notaxRealAmount");
        needTotalName.add("taxAmount");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("采购入库");
        paramForListVO.setPageNo(null);
        paramForListVO.setPageSize(null);
        purchaseGeneralComponent.commExcelExport(output,map,purchaseInStorageReportPageVOS,name,titleSecond,end.toString(),false,needTotalName);
    }

    private TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO> setDataName(List<PurchaseInStorageReportPageVO> purchaseInStorageReportPageVOS, Long enterpriseId) {

        TotalPurchaseInStorageReportVO<PurchaseInStorageReportPageVO> totalPurchaseInStorageReportVO = new TotalPurchaseInStorageReportVO<>();
        BigDecimal quantity = BigDecimal.ZERO;//数量
        BigDecimal amount = BigDecimal.ZERO;//金额
        BigDecimal realAmount = BigDecimal.ZERO;//实际金额
        BigDecimal notaxRealAmount = BigDecimal.ZERO;//不含税金额
        BigDecimal taxAmount = BigDecimal.ZERO;//税额

        for (PurchaseInStorageReportPageVO purchaseInStorageReportPageVO : purchaseInStorageReportPageVOS) {
            //品种类别
            purchaseInStorageReportPageVO.setBusinessVarietyName(purchaseInStorageReportPageVO.getBusinessVariety()==null?null: BusinessVariety.getName(purchaseInStorageReportPageVO.getBusinessVariety()));
            //商品属性
            purchaseInStorageReportPageVO.setGoodsAttributeName(purchaseInStorageReportPageVO.getGoodsAttribute()==null?null: GoodsAttribute2DrugsA.getName(purchaseInStorageReportPageVO.getGoodsAttribute()));
            purchaseInStorageReportPageVO.setPrescriptionDrugName(purchaseInStorageReportPageVO.getPrescriptionDrug()==null?null: PrescriptionDrug.getName(purchaseInStorageReportPageVO.getPrescriptionDrug()));
            purchaseInStorageReportPageVO.setOtcTypeName(purchaseInStorageReportPageVO.getOtcType()==null?null: GoodsAttributeDrugsOTCType.getName(purchaseInStorageReportPageVO.getOtcType()));
            //国产/进口
            purchaseInStorageReportPageVO.setDomesticImportName(purchaseInStorageReportPageVO.getDomesticImport()==null?null: DomesticImport.getName(purchaseInStorageReportPageVO.getDomesticImport()));
            //特殊管理药品
            purchaseInStorageReportPageVO.setSpecialDrugName(purchaseInStorageReportPageVO.getSpiritDrugType()==null?null: SpecialDrugsAll.getName(purchaseInStorageReportPageVO.getSpiritDrugType()));
            purchaseInStorageReportPageVO.setSpiritDrugTypeName(purchaseInStorageReportPageVO.getSpiritDrugType()==null?null: SpiritDrugsType.getName(purchaseInStorageReportPageVO.getSpiritDrugType()));
            //专管药品
            purchaseInStorageReportPageVO.setInChargeDrugName(purchaseInStorageReportPageVO.getInChargeDrug()==null?null: InChargeDrug.getName(purchaseInStorageReportPageVO.getInChargeDrug()));
            purchaseInStorageReportPageVO.setFormulationTypeName(purchaseInStorageReportPageVO.getFormulationType()==null?null: SpecialCompoundPreparationsType.getName(purchaseInStorageReportPageVO.getFormulationType()));
            //贮藏温度
            purchaseInStorageReportPageVO.setStorageTempName(purchaseInStorageReportPageVO.getStorageTemp()==null?null:StorageTemp.getName(purchaseInStorageReportPageVO.getStorageTemp()));
            //保质期单位
            QualityPeriodUnitName(purchaseInStorageReportPageVO.getQualityPeriodUnit(),purchaseInStorageReportPageVO);
            //状态
            purchaseInStorageReportPageVO.setStatusName(PurchaseStatus.getName(purchaseInStorageReportPageVO.getStatus()));
            //质量状况
            if(purchaseInStorageReportPageVO.getShelfId() != null){
                GoodsShelfStatusDescVO goodsShelfStatusDescVO = chGoodsLoadService.getGoodsShelfStatusDesc(enterpriseId,Long.valueOf(purchaseInStorageReportPageVO.getShelfId()));
                purchaseInStorageReportPageVO.setShelfStatusDesc(goodsShelfStatusDescVO.getShelfStatusDesc());
            }

            //拼接商品属性
            purchaseInStorageReportPageVO.setGoodsAttributeAll((purchaseInStorageReportPageVO.getGoodsAttributeName() == null ? "" : purchaseInStorageReportPageVO.getGoodsAttributeName()) + (purchaseInStorageReportPageVO.getPrescriptionDrugName() == null ? "" :  "-" + purchaseInStorageReportPageVO.getPrescriptionDrugName()) + (purchaseInStorageReportPageVO.getOtcTypeName() == null ? "" : "-" + purchaseInStorageReportPageVO.getOtcTypeName()));
            //拼接特殊管理药品
            purchaseInStorageReportPageVO.setSpecialDrugAll((purchaseInStorageReportPageVO.getSpecialDrugName() == null ? "" : purchaseInStorageReportPageVO.getSpecialDrugName()) + (purchaseInStorageReportPageVO.getSpiritDrugTypeName() == null ? "" :  "-" + purchaseInStorageReportPageVO.getSpiritDrugTypeName()));
            //拼接专管药品
            purchaseInStorageReportPageVO.setInChargeDrugAll((purchaseInStorageReportPageVO.getInChargeDrugName() == null ? "" : purchaseInStorageReportPageVO.getInChargeDrugName()) + (purchaseInStorageReportPageVO.getFormulationTypeName() == null ? "" :  "-" + purchaseInStorageReportPageVO.getFormulationTypeName()));
            //拼接保质期
            purchaseInStorageReportPageVO.setQualityPeriodAll((purchaseInStorageReportPageVO.getQualityPeriod() == null ? "" : purchaseInStorageReportPageVO.getQualityPeriod()) + (purchaseInStorageReportPageVO.getQualityPeriodUnitName() == null ? "" : purchaseInStorageReportPageVO.getQualityPeriodUnitName()));

            quantity = quantity.add(purchaseInStorageReportPageVO.getQuantity());
            amount = amount.add(purchaseInStorageReportPageVO.getAmount());
            realAmount = realAmount.add(purchaseInStorageReportPageVO.getRealAmount());
            notaxRealAmount = notaxRealAmount.add(purchaseInStorageReportPageVO.getNotaxRealAmount());
            taxAmount = taxAmount.add(purchaseInStorageReportPageVO.getTaxAmount());
        }
        totalPurchaseInStorageReportVO.setQuantityAll(quantity);
        totalPurchaseInStorageReportVO.setAmountAll(amount);
        totalPurchaseInStorageReportVO.setRealAmountAll(realAmount);
        totalPurchaseInStorageReportVO.setNotaxRealAmountAll(notaxRealAmount);
        totalPurchaseInStorageReportVO.setTaxAmountAll(taxAmount);
        totalPurchaseInStorageReportVO.setData(purchaseInStorageReportPageVOS);

        return totalPurchaseInStorageReportVO;
    }

    private void QualityPeriodUnitName(Integer qualityPeriodUnit, PurchaseInStorageReportPageVO purchaseInStorageReportPageVO) {
        String unitDesc = null;
        switch (qualityPeriodUnit) {
            case 0:
                unitDesc = "日";
                break;
            case 1:
                unitDesc = "月";
                break;
            case 2:
                unitDesc = "年";
                break;
            default:
                unitDesc = "";
                break;
        }
        purchaseInStorageReportPageVO.setQualityPeriodUnitName(unitDesc == null ? "" : unitDesc);
    }
}
