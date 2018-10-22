package com.rograndec.feijiayun.chain.business.report.quality.distr.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrInReturnOutReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut.InReturnOutGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut.InReturnOutTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut.RequestGetInReturnOutParamVO;
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
public class DistrInReturnOutReportServiceImpl implements DistrInReturnOutReportService {

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public void getInReturnOutGoodsList(Page<InReturnOutTotalVO> page, RequestGetInReturnOutParamVO getReturnParamVO, UserVO userVO) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Long enterpriseId = userVO.getEnterpriseId();
        InReturnOutTotalVO returnTotalVO = getInReturnOutTotalVO(getReturnParamVO, enterpriseId);

        page.setResult(returnTotalVO);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportInReturnOutGoodsList(OutputStream output, UserVO userVO, RequestGetInReturnOutParamVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        InReturnOutTotalVO returnTotalVO = getInReturnOutTotalVO(paramForListVO, enterpriseId);

        List<InReturnOutGoodsReportVO> returnGoodsReportVOList = returnTotalVO.getInReturnOutGoodsReportVOS();
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "配进退出出库单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("outDate","日期");
        headerHashMap.put("code","单号");
        headerHashMap.put("distrUnitCode","配货单位编码");
        headerHashMap.put("distrUnitName","配货单位名称");
        headerHashMap.put("outManName","出库人员");
        headerHashMap.put("auditManName","复核人员");
        headerHashMap.put("distrTypeName","配货类型");
        headerHashMap.put("flowCode","流通监管码");
        headerHashMap.put("reqPlanCode","要货计划单号");
        headerHashMap.put("baseOrderCode","配进退出单号");
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


    private InReturnOutTotalVO getInReturnOutTotalVO(RequestGetInReturnOutParamVO getReturnParamVO, Long enterpriseId) {

        List<InReturnOutGoodsReportVO> inReturnOutGoodsReportVOS = goodsMapper.getInReturnOutGoodsReport(getReturnParamVO, enterpriseId);
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

        for (InReturnOutGoodsReportVO item : inReturnOutGoodsReportVOS) {
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
        InReturnOutTotalVO returnTotalVO = new InReturnOutTotalVO();
        returnTotalVO.setQuantityTotal(quantityTotal);
        returnTotalVO.setAmountTotal(amountTotal);
        returnTotalVO.setRealAmountTotal(realAmountTotal);
        returnTotalVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        returnTotalVO.setTaxAmountTotal(taxAmountTotal);
        returnTotalVO.setInReturnOutGoodsReportVOS(inReturnOutGoodsReportVOS);
        return returnTotalVO;
    }
}
