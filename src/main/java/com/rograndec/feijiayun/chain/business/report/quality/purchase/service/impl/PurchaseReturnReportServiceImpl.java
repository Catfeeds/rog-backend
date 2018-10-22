package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseReturnReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.*;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.StorageTemp;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
@Service
public class PurchaseReturnReportServiceImpl implements PurchaseReturnReportService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;


    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private QualitySetMapper qualitySetMapper;




    @Override
    public void getPurchaseReturnGoodsList(Page<PurchaseReturnTotalVO> page, RequestGetReturnParamVO getReturnParamVO, UserVO userVO){
        Long enterpriseId = userVO.getEnterpriseId();
        Integer chainType = userVO.getChainType();
        if(chainType == ChainType.Headquarters.getCode()){
            enterpriseId = userVO.getEnterpriseId();
        } else {
            enterpriseId = userVO.getParentId();
        }
        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(enterpriseId, null, null, EnableStatus.ENABLE.getStatus());

        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        PurchaseReturnTotalVO returnTotalVO = getPurchaseReturnTotalVO(getReturnParamVO, enterpriseId, qualitySetVOList);

        page.setResult(returnTotalVO);
        page.setTotalRecord((int) objects.getTotal());
    }

    private PurchaseReturnTotalVO getPurchaseReturnTotalVO(RequestGetReturnParamVO getReturnParamVO, Long enterpriseId,List<QualitySetVO> qualitySetVOList) {

        List<PurchaseReturnGoodsReportVO> purchaseReturnGoodsReport = goodsMapper.getPurchaseReturnGoodsReport(getReturnParamVO, enterpriseId);
        //        数量合计
        BigDecimal quantityTotal = BigDecimal.ZERO;
        //        金额合计
        BigDecimal amountTotal = BigDecimal.ZERO;
        //        实际金额合计
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        //        不含税实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        //        税额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        for (PurchaseReturnGoodsReportVO item : purchaseReturnGoodsReport) {
            item.converTOStr(item);
            quantityTotal = quantityTotal.add(Optional.ofNullable(item.getQuantity()).orElse(BigDecimal.ZERO));
            amountTotal = amountTotal.add(Optional.ofNullable(item.getAmount()).orElse(BigDecimal.ZERO));
            realAmountTotal = realAmountTotal.add(Optional.ofNullable(item.getRealAmount()).orElse(BigDecimal.ZERO));
            notaxRealAmountTotal = notaxRealAmountTotal.add(Optional.ofNullable(item.getNotaxRealAmount()).orElse(BigDecimal.ZERO));
            taxAmountTotal = taxAmountTotal.add(Optional.ofNullable(item.getTaxAmount()).orElse(BigDecimal.ZERO));
            //商品分类
            Long categoryId = item.getCategoryId();
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
            if(goodsCategory != null){
                item.setCategoryName(goodsCategory.getName());
            }
            //订单编码
            Long purchaseOrderId = item.getPurchaseOrderId();
            PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseOrderId);
            if(purchaseOrder != null){
                item.setOrderCode(purchaseOrder.getCode());
            }
            //退货原因

            String checkProjectIds = item.getReturnReasonIds();//检验项目集合
            if(checkProjectIds != null){
                String[] checkProjectIdArr = checkProjectIds.split(",");
                Stream.of(checkProjectIdArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getReturnReasonNames();
                    if(name != null){
                        item.setReturnReasonNames(names == null ? name : names + "," + name);
                    }
                });
            }

            int storageTemp = item.getStorageTemp();
            //贮藏温度
            item.setStorageTempString(String.valueOf(storageTemp) == null?null: StorageTemp.getName(storageTemp));
        }
        PurchaseReturnTotalVO returnTotalVO = new PurchaseReturnTotalVO();
        returnTotalVO.setQuantityTotal(quantityTotal);
        returnTotalVO.setAmountTotal(amountTotal);
        returnTotalVO.setRealAmountTotal(realAmountTotal);
        returnTotalVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        returnTotalVO.setTaxAmountTotal(taxAmountTotal);
        returnTotalVO.setReturnGoodsReportVOList(purchaseReturnGoodsReport);
        return returnTotalVO;
    }




    @Override
    public void exportPurchaseReturnGoodsList(OutputStream output, UserVO userVO, RequestGetReturnParamVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer chainType = userVO.getChainType();
        if(chainType == ChainType.Headquarters.getCode()){
            enterpriseId = userVO.getEnterpriseId();
        } else {
            enterpriseId = userVO.getParentId();
        }
        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(enterpriseId, null, null, EnableStatus.ENABLE.getStatus());
        PurchaseReturnTotalVO returnTotalVO = getPurchaseReturnTotalVO(paramForListVO, enterpriseId,qualitySetVOList);

        List<PurchaseReturnGoodsReportVO> returnGoodsReportVOList = returnTotalVO.getReturnGoodsReportVOList();
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "购进退出单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("returnDateString","日期");
        headerHashMap.put("code","单号");
        headerHashMap.put("supplierCode","供货单位编码");
        headerHashMap.put("supplierName","供货单位名称");
        headerHashMap.put("supplierSalerName","销售人员");
        headerHashMap.put("supplierSalerPhone","联系电话");
        headerHashMap.put("returnManName","退货人员");
        headerHashMap.put("returnTypeName","退货类型");
        headerHashMap.put("orderCode","订单单号");
        headerHashMap.put("instorageCode","入库单号");
        headerHashMap.put("goodsCode","商品编码");
        headerHashMap.put("barcode","条形码");
        headerHashMap.put("goodsGenericName","通用名称");
        headerHashMap.put("goodsName","商品名称");
        headerHashMap.put("dosageName","剂型");
        headerHashMap.put("goodsSpecification","规格");
        headerHashMap.put("unitName","单位");
        headerHashMap.put("manufacturer","生产厂商");
        headerHashMap.put("goodsPlace","产地");
        headerHashMap.put("approvalNumber","批准文号");
        headerHashMap.put("lotNum","批号");
        headerHashMap.put("productDate","生产日期");
        headerHashMap.put("validDate","有效期至");

        headerHashMap.put("quantity","数量");
        headerHashMap.put("unitPrice","单价");
        headerHashMap.put("lineDiscount","折扣");
        headerHashMap.put("amount","金额");

        headerHashMap.put("wholeDiscount","整单折扣");
        headerHashMap.put("lineDiscountAmount","优惠分摊");
        headerHashMap.put("realPrice","实际单价");

        headerHashMap.put("realAmount","实际金额");
        headerHashMap.put("taxRate","税率");
        headerHashMap.put("notaxRealPrice","不含税单价");

        headerHashMap.put("notaxRealAmount","不含税金额");
        headerHashMap.put("taxAmount","税额");
        headerHashMap.put("returnReasonIds","退货原因");
        headerHashMap.put("statusName","状态");

        headerHashMap.put("returnOutCount","品种类型");
        headerHashMap.put("categoryName","商品分类");
        headerHashMap.put("goodsAttributeDetail","商品属性");
        headerHashMap.put("domesticImportString","国产/进口");
        headerHashMap.put("managementScopeName","经营范围");

        headerHashMap.put("specialDrugDetail","特殊管理药品");
        headerHashMap.put("inChargeDrugDetail","专管药品");
        headerHashMap.put("limitQuantity","限购数量");
        headerHashMap.put("storageTempString","贮藏温度");

        headerHashMap.put("storageConditionName","贮藏条件");
        headerHashMap.put("qualityPeriod","保质期");

        //合计内容
        Map<String,String> totalFieldMap = new HashMap<>();

        totalFieldMap.put("quantity",StringUtil.transferTrimStr(returnTotalVO.getQuantityTotal()));
        totalFieldMap.put("amount",StringUtil.transferTrimStr(returnTotalVO.getAmountTotal()));
        totalFieldMap.put("realAmount",StringUtil.transferTrimStr(returnTotalVO.getRealAmountTotal()));
        totalFieldMap.put("notaxRealAmount",StringUtil.transferTrimStr(returnTotalVO.getNotaxRealAmountTotal()));
        totalFieldMap.put("taxAmount",StringUtil.transferTrimStr(returnTotalVO.getTaxAmountTotal()));

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,returnGoodsReportVOList,totalFieldMap);
    }

    @Override
    public void getPurchaseReturnOutGoodsList(Page<PurchaseReturnOutTotalVO> page, RequestGetReturnOutParamVO getReturnParamVO, UserVO userVO) {

        Long enterpriseId = userVO.getEnterpriseId();
        Integer chainType = userVO.getChainType();
        if(chainType == ChainType.Headquarters.getCode()){
            enterpriseId = userVO.getEnterpriseId();
        } else {
            enterpriseId = userVO.getParentId();
        }
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        PurchaseReturnOutTotalVO returnTotalVO = getPurchaseReturnOutTotalVO(getReturnParamVO, enterpriseId);

        page.setResult(returnTotalVO);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportPurchaseReturnOutGoodsList(OutputStream output, UserVO userVO, RequestGetReturnOutParamVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer chainType = userVO.getChainType();
        if(chainType == ChainType.Headquarters.getCode()){
            enterpriseId = userVO.getEnterpriseId();
        } else {
            enterpriseId = userVO.getParentId();
        }
        PurchaseReturnOutTotalVO returnTotalVO = getPurchaseReturnOutTotalVO(paramForListVO, enterpriseId);

        List<PurchaseReturnOutGoodsReportVO> returnGoodsReportVOList = returnTotalVO.getReturnGoodsReportVOList();
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "购进退出出库单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("outDate","日期");
        headerHashMap.put("code","单号");
        headerHashMap.put("supplierCode","供货单位编码");
        headerHashMap.put("supplierName","供货单位名称");
        headerHashMap.put("supplierSalerName","销售人员");
        headerHashMap.put("supplierSalerPhone","联系电话");
        headerHashMap.put("outManName","出库人员");
        headerHashMap.put("flowCode","流通监管码");
        headerHashMap.put("orderCode","订单单号");
        headerHashMap.put("baseOrderCode","购进退出单号");
        headerHashMap.put("goodsCode","商品编码");
        headerHashMap.put("barcode","条形码");
        headerHashMap.put("goodsGenericName","通用名称");
        headerHashMap.put("goodsName","商品名称");
        headerHashMap.put("dosageName","剂型");
        headerHashMap.put("goodsSpecification","规格");
        headerHashMap.put("unitName","单位");
        headerHashMap.put("manufacturer","生产厂商");
        headerHashMap.put("goodsPlace","产地");
        headerHashMap.put("approvalNumber","批准文号");
        headerHashMap.put("lotNum","批号");
        headerHashMap.put("productDate","生产日期");
        headerHashMap.put("validDate","有效期至");
        headerHashMap.put("shelfName","货位");
        headerHashMap.put("shelfStatusDesc","质量状况");



        headerHashMap.put("quantity","数量");
        headerHashMap.put("unitPrice","单价");
        headerHashMap.put("lineDiscount","折扣");
        headerHashMap.put("amount","金额");

        headerHashMap.put("wholeDiscount","整单折扣");
        headerHashMap.put("lineDiscountAmount","优惠分摊");
        headerHashMap.put("realPrice","实际单价");

        headerHashMap.put("realAmount","实际金额");
        headerHashMap.put("taxRate","税率");
        headerHashMap.put("notaxRealPrice","不含税单价");

        headerHashMap.put("notaxRealAmount","不含税金额");
        headerHashMap.put("taxAmount","税额");
        headerHashMap.put("returnReasonIds","退货原因");
        headerHashMap.put("status","状态");

        headerHashMap.put("returnOutCount","品种类型");
        headerHashMap.put("categoryName","商品分类");
        headerHashMap.put("goodsAttributeDetail","商品属性");
        headerHashMap.put("domesticImportString","国产/进口");
        headerHashMap.put("managementScopeName","经营范围");

        headerHashMap.put("specialDrugDetail","特殊管理药品");
        headerHashMap.put("inChargeDrugDetail","专管药品");
        headerHashMap.put("limitQuantity","限购数量");
        headerHashMap.put("storageTempString","贮藏温度");

        headerHashMap.put("storageConditionName","贮藏条件");
        headerHashMap.put("qualityPeriod","保质期");

        //合计内容
        Map<String,String> totalFieldMap = new HashMap<>();

        totalFieldMap.put("quantity",StringUtil.transferTrimStr(returnTotalVO.getQuantityTotal()));
        totalFieldMap.put("amount",StringUtil.transferTrimStr(returnTotalVO.getAmountTotal()));
        totalFieldMap.put("realAmount",StringUtil.transferTrimStr(returnTotalVO.getRealAmountTotal()));
        totalFieldMap.put("notaxRealAmount",StringUtil.transferTrimStr(returnTotalVO.getNotaxRealAmountTotal()));
        totalFieldMap.put("taxAmount",StringUtil.transferTrimStr(returnTotalVO.getTaxAmountTotal()));

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,returnGoodsReportVOList,totalFieldMap);
    }


    private PurchaseReturnOutTotalVO getPurchaseReturnOutTotalVO(RequestGetReturnOutParamVO getReturnParamVO, Long enterpriseId) {

        List<PurchaseReturnOutGoodsReportVO> purchaseReturnOutGoodsReportList = goodsMapper.getPurchaseReturnOutGoodsReport(getReturnParamVO, enterpriseId);
        //        数量合计
        BigDecimal quantityTotal = BigDecimal.ZERO;
        //        金额合计
        BigDecimal amountTotal = BigDecimal.ZERO;
        //        实际金额合计
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        //        不含税实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        //        税额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        for (PurchaseReturnOutGoodsReportVO item : purchaseReturnOutGoodsReportList) {
            item.converTOStr(item);
            quantityTotal = quantityTotal.add(Optional.ofNullable(item.getQuantity()).orElse(BigDecimal.ZERO));
            amountTotal = amountTotal.add(Optional.ofNullable(item.getAmount()).orElse(BigDecimal.ZERO));
            realAmountTotal = realAmountTotal.add(Optional.ofNullable(item.getRealAmount()).orElse(BigDecimal.ZERO));
            notaxRealAmountTotal = notaxRealAmountTotal.add(Optional.ofNullable(item.getNotaxRealAmount()).orElse(BigDecimal.ZERO));
            taxAmountTotal = taxAmountTotal.add(Optional.ofNullable(item.getTaxAmount()).orElse(BigDecimal.ZERO));
            //商品分类
            Long categoryId = item.getCategoryId();
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
            if(goodsCategory != null){
                item.setCategoryName(goodsCategory.getName());
            }
            //购进退出单id
            Long returnId = item.getBaseOrderId();
            Map<String,String> orderMap = purchaseReturnMapper.getOrderInfoByReturnId(enterpriseId,returnId);
            if(orderMap != null){
                item.setOrderCode(orderMap.get("code"));
            }
            int storageTemp = item.getStorageTemp();
            //贮藏温度
            item.setStorageTempString(String.valueOf(storageTemp) == null?null: StorageTemp.getName(storageTemp));

        }
        PurchaseReturnOutTotalVO returnTotalVO = new PurchaseReturnOutTotalVO();
        returnTotalVO.setQuantityTotal(quantityTotal);
        returnTotalVO.setAmountTotal(amountTotal);
        returnTotalVO.setRealAmountTotal(realAmountTotal);
        returnTotalVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        returnTotalVO.setTaxAmountTotal(taxAmountTotal);
        returnTotalVO.setReturnGoodsReportVOList(purchaseReturnOutGoodsReportList);
        return returnTotalVO;
    }
}
