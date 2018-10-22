package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseReviewCheckReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck.PurchaseReviewCheckGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck.PurchaseReviewCheckTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck.RequestGetReviewCheckParamVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
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
 *
 * Created by ST on 2017/10/19 13:44
 */
@Service
public class PurchaseReviewCheckReportServiceImpl implements PurchaseReviewCheckReportService {


    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private QualitySetMapper qualitySetMapper;


    @Override
    public void getReviewCheckGoodsList(Page<PurchaseReviewCheckTotalVO> page, RequestGetReviewCheckParamVO getStorageParamVO, UserVO userVO) {
        Long enterpriseId = userVO.getEnterpriseId();
        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(enterpriseId, null, null, EnableStatus.ENABLE.getStatus());
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        PurchaseReviewCheckTotalVO totalVO = getReviewCheckTotalVO(getStorageParamVO, enterpriseId, qualitySetVOList);
        page.setResult(totalVO);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportReviewCheckGoodsList(OutputStream output, UserVO userVO, RequestGetReviewCheckParamVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(enterpriseId, null, null, EnableStatus.ENABLE.getStatus());
        PurchaseReviewCheckTotalVO totalVO = getReviewCheckTotalVO(paramForListVO, enterpriseId,qualitySetVOList);
        List<PurchaseReviewCheckGoodsReportVO> reviewCheckGoodsReportVOS = totalVO.getReviewCheckGoodsReportVOS();
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "药品复查单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("checkDate","日期");
        headerHashMap.put("code","单号");
        headerHashMap.put("supplierCode","供货单位编码");
        headerHashMap.put("supplierName","配货单位名称");
        headerHashMap.put("supplierSalerName","销售人员");
        headerHashMap.put("supplierSalerPhone","联系电话");
        headerHashMap.put("secondCheckerName","复查人员");
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

        headerHashMap.put("unqualifiedQuantity","复查数量");
        headerHashMap.put("unqualifiedReasonNames","不合格原因");
        headerHashMap.put("measuresNames","处置措施");
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

        totalFieldMap.put("unqualifiedQuantity", StringUtil.transferTrimStr(totalVO.getReviewQuantityTotal()));


        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,reviewCheckGoodsReportVOS,totalFieldMap);
    }


    private PurchaseReviewCheckTotalVO getReviewCheckTotalVO(RequestGetReviewCheckParamVO getStorageParamVO, Long enterpriseId,List<QualitySetVO> qualitySetVOList) {


        List<PurchaseReviewCheckGoodsReportVO> reviewCheckGoodsReportVOS = goodsMapper.getPurchaseReviewCheckGoodsReport(getStorageParamVO, enterpriseId);
        //        复查数量合计
        BigDecimal reviewQuantityTotal = BigDecimal.ZERO;

        for (PurchaseReviewCheckGoodsReportVO item : reviewCheckGoodsReportVOS) {
            item.converTOStr(item);
            reviewQuantityTotal = reviewQuantityTotal.add(Optional.ofNullable(item.getUnqualifiedQuantity()).orElse(BigDecimal.ZERO));
            //商品分类
            Long categoryId = item.getCategoryId();
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
            if(goodsCategory != null){
                item.setCategoryName(goodsCategory.getName());
            }
            //不合格原因，处置措施

            String unqualifiedReasonIds = item.getUnqualifiedReasonIds();//不合格原因集合
            if(unqualifiedReasonIds != null){
                String[] unqualifiedReasonIdsArr = unqualifiedReasonIds.split(",");
                Stream.of(unqualifiedReasonIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getUnqualifiedReasonNames();
                    if(name != null){
                        item.setUnqualifiedReasonNames(names == null ? name : names + "," + name);
                    }

                });
            }
            //处置措施
            String measuresIds = item.getMeasuresIds();
            if(measuresIds != null){
                String[] measuresIdsArr = measuresIds.split(",");
                Stream.of(measuresIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getMeasuresNames();
                    if(name != null){
                        item.setMeasuresNames(names == null ? name : names + "," + name);
                    }
                });
            }

            int storageTemp = item.getStorageTemp();
            //贮藏温度
            item.setStorageTempString(String.valueOf(storageTemp) == null?null: StorageTemp.getName(storageTemp));

        }
        PurchaseReviewCheckTotalVO totalVO = new PurchaseReviewCheckTotalVO();
        totalVO.setReviewQuantityTotal(reviewQuantityTotal);
        totalVO.setReviewCheckGoodsReportVOS(reviewCheckGoodsReportVOS);

        return totalVO;
    }


}
