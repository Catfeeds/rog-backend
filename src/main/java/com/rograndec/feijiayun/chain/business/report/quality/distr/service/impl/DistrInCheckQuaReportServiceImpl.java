package com.rograndec.feijiayun.chain.business.report.quality.distr.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrInCheckQuaReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.InCheckQuaGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.InCheckQuaTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.RequestGetInCheckQuaParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * 功能描述：门店配进退出
 * Created by ST on 2017/10/19 13:44
 */
@Service
public class DistrInCheckQuaReportServiceImpl implements DistrInCheckQuaReportService {


    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;



    @Override
    public void getInCheckQuaGoodsList(Page<InCheckQuaTotalVO> page, RequestGetInCheckQuaParamVO getStorageParamVO, UserVO userVO) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Long enterpriseId = userVO.getEnterpriseId();

        InCheckQuaTotalVO StorageTotalVO = getInCheckQuaTotalVO(getStorageParamVO, enterpriseId);

        page.setResult(StorageTotalVO);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportInCheckQuaGoodsList(OutputStream output, UserVO userVO, RequestGetInCheckQuaParamVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        InCheckQuaTotalVO storageTotalVO = getInCheckQuaTotalVO(paramForListVO, enterpriseId);

        List<InCheckQuaGoodsReportVO> storageGoodsReportVOList = storageTotalVO.getInCheckQuaGoodsReportVOS();
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "药品验收资质单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("checkDate","日期");
        headerHashMap.put("code","单号");
        headerHashMap.put("distrUnitCode","配货单位编码");
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
        headerHashMap.put("checkProjectIdNames","验收项目");

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


        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,storageGoodsReportVOList,null);
    }


    private InCheckQuaTotalVO getInCheckQuaTotalVO(RequestGetInCheckQuaParamVO getStorageParamVO, Long enterpriseId) {

        List<InCheckQuaGoodsReportVO> inCheckQuaGoodsReportVOS = goodsMapper.getInCheckQuaGoodsReport(getStorageParamVO, enterpriseId);
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

        for (InCheckQuaGoodsReportVO item : inCheckQuaGoodsReportVOS) {
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

            String testReportIds = item.getTestReportIds();
            if(testReportIds != null) {
                if (testReportIds.contains(":")) {
                    StringBuilder sb = new StringBuilder();
                    if(testReportIds.contains(",")) {
                        String[] testReportIdsArr = testReportIds.split(",");
                        for (int i = 0; i < testReportIdsArr.length; i++) {
                            String[] project2ReportArr = testReportIdsArr[i].split(":");
                            sb.append(project2ReportArr[1]);
                            if(i != testReportIdsArr.length-1){
                                sb.append(",");
                            }
                        }
                    } else {
                        String[] project2ReportArr = testReportIds.split(":");
                        sb.append(project2ReportArr[1]);
                    }
                    item.setTestReportIds(sb.toString());
                }
            }
        }
        InCheckQuaTotalVO storageTotalVO = new InCheckQuaTotalVO();
        storageTotalVO.setQuantityTotal(quantityTotal);
        storageTotalVO.setAmountTotal(amountTotal);
        storageTotalVO.setRealAmountTotal(realAmountTotal);
        storageTotalVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        storageTotalVO.setTaxAmountTotal(taxAmountTotal);
        storageTotalVO.setInCheckQuaGoodsReportVOS(inCheckQuaGoodsReportVOS);
        return storageTotalVO;
    }
}
