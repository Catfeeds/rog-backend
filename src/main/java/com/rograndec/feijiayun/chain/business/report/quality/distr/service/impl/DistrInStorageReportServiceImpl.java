package com.rograndec.feijiayun.chain.business.report.quality.distr.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrInStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.InStorageGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.InStorageTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.RequestGetInStorageParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能描述：门店配进退出
 * Created by ST on 2017/10/19 13:44
 */
@Service
public class DistrInStorageReportServiceImpl implements DistrInStorageReportService {


    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;



    @Override
    public void getInStorageGoodsList(Page<InStorageTotalVO> page, RequestGetInStorageParamVO getStorageParamVO, UserVO userVO) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Long enterpriseId = userVO.getEnterpriseId();

        InStorageTotalVO StorageTotalVO = getInStorageTotalVO(getStorageParamVO, enterpriseId);

        page.setResult(StorageTotalVO);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportInStorageGoodsList(OutputStream output, UserVO userVO, RequestGetInStorageParamVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        InStorageTotalVO storageTotalVO = getInStorageTotalVO(paramForListVO, enterpriseId);

        List<InStorageGoodsReportVO> storageGoodsReportVOList = storageTotalVO.getInStorageGoodsReportVOS();
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "配进入库单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("inDate","日期");
        headerHashMap.put("code","单号");
        headerHashMap.put("distrUnitCode","配货单位编码");
        headerHashMap.put("distrUnitName","配货单位名称");
        headerHashMap.put("storageManName","入库人员");
        headerHashMap.put("distrType","配货类型");
        headerHashMap.put("baseOrderCode","配进验收单号");
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

        totalFieldMap.put("quantity",StringUtil.transferTrimStr(storageTotalVO.getQuantityTotal()));
        totalFieldMap.put("amount",StringUtil.transferTrimStr(storageTotalVO.getAmountTotal()));
        totalFieldMap.put("realAmount",StringUtil.transferTrimStr(storageTotalVO.getRealAmountTotal()));
        totalFieldMap.put("notaxRealAmount",StringUtil.transferTrimStr(storageTotalVO.getNotaxRealAmountTotal()));
        totalFieldMap.put("taxAmount",StringUtil.transferTrimStr(storageTotalVO.getTaxAmountTotal()));

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,storageGoodsReportVOList,totalFieldMap);
    }


    private InStorageTotalVO getInStorageTotalVO(RequestGetInStorageParamVO getStorageParamVO, Long enterpriseId) {

        List<InStorageGoodsReportVO> inStorageGoodsReportVOS = goodsMapper.getInStorageGoodsReport(getStorageParamVO, enterpriseId);
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

        for (InStorageGoodsReportVO item : inStorageGoodsReportVOS) {

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
//            //购进退出单id
//            Long returnId = item.getBaseOrderId();
//            Map<String,String> orderMap = purchaseReturnMapper.getOrderInfoByReturnId(enterpriseId,returnId);
//            if(orderMap != null){
//                item.setOrderCode(orderMap.get("code"));
//            }
        }
        InStorageTotalVO storageTotalVO = new InStorageTotalVO();
        storageTotalVO.setQuantityTotal(quantityTotal);
        storageTotalVO.setAmountTotal(amountTotal);
        storageTotalVO.setRealAmountTotal(realAmountTotal);
        storageTotalVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        storageTotalVO.setTaxAmountTotal(taxAmountTotal);
        storageTotalVO.setInStorageGoodsReportVOS(inStorageGoodsReportVOS);
        return storageTotalVO;
    }
}
