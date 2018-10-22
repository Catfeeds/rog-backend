package com.rograndec.feijiayun.chain.business.quality.review.service.impl;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.quality.review.dao.GoodsQualityReviewMapper;
import com.rograndec.feijiayun.chain.business.quality.review.entity.GoodsQualityReview;
import com.rograndec.feijiayun.chain.business.quality.review.service.ReviewService;
import com.rograndec.feijiayun.chain.business.quality.review.vo.GoodsReviewVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewPageVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewRequestVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    private GoodsQualityReviewMapper reviewMapper;

    @Autowired
    private GoodsMapper goodsMapper;


    @Autowired
    private ExcelComponent<ReviewPageVO> excelComponent;

    @Override
    public List<GoodsReviewVO> getGoods(Map<String, Object> map) {

        List<GoodsReviewVO> goodsList = reviewMapper.getGoods(map);

        for (GoodsReviewVO goods : goodsList) {

            // 拼装商品属性描述

            String attributeDesc = "";

            Integer type = goods.getBusinessVariety();//0-药品；1-食品；2-化妆品；3-医疗器械；4-其它
            /**
             商品属性
             （1）品种类别为0-药品：[0-成药；1-中药材；2-中药饮片]
             （2）品种类别为1-医疗器械：［0-一类医疗器械；1-二类医疗器械；2-三类医疗器械］
             （3）品种类别为2-食品：［0-食品；1-保健食品；2-婴幼儿配方食品；3-特殊医学用途配方食品］
             （4）品种类别为3-化妆品：［0-非特殊用途化妆品；1-特殊用途化妆品］
             （5）品种类别为
             4-其它［0-赠品；1-服务］
             */
            Integer goodsAttribute = goods.getGoodsAttribute();//

            if (type == BusinessVariety.DRUGS.getCode()) {// 药品
                attributeDesc = attributeDesc + GoodsAttribute2DrugsA.getName(goodsAttribute);

            } else if (type == BusinessVariety.FOODS.getCode()) {// 食品
                attributeDesc = attributeDesc + GoodsAttributeFood.getName(goodsAttribute);

            } else if (type == BusinessVariety.COSMETICS.getCode()) {//化妆品

                attributeDesc = attributeDesc + GoodsAttributeCosmetics.getName(goodsAttribute);
            } else if (type == BusinessVariety.MEDICAL_APPARATUS.getCode()) {// 医疗器械
                attributeDesc = attributeDesc + GoodsAttributeMedicalApparatus.getName(goodsAttribute);

            } else if (type == BusinessVariety.OTHERS.getCode()) {// 其他
                attributeDesc = attributeDesc + GoodsAttributeOthers.getName(goodsAttribute);
            }

            Integer prescriptionDrug = goods.getPrescriptionDrug();
            // 品种类别为0-药品，商品属性为0-成药时，prescription_drug含义为是否为处方药［0：非处方药，1：处方药］
            if (prescriptionDrug != null) {
                if(type == BusinessVariety.DRUGS.getCode()
                        && goodsAttribute == GoodsAttribute2DrugsA.PATENTMEDICINE.getCode()){

                    if (prescriptionDrug == GoodsAttributeDrugsRXDrug.RX_DRUG.getCode()) {// 处方药

                        attributeDesc = attributeDesc + "-" + GoodsAttributeDrugsRXDrug.RX_DRUG.getName();

                    } else if (prescriptionDrug == GoodsAttributeDrugsOTC.OTC.getCode()) {// 非处方药

                        attributeDesc = attributeDesc + "-" + GoodsAttributeDrugsOTC.OTC.getName();

                    }
                }

            }

            // 品种类别为0-药品，商品属性为0-成药，并且为非处方药时，otc_type 表示非处方药类别［0-甲类；1-乙类］
            Integer otcType = goods.getOtcType();
            if (otcType != null) {// 甲类/乙类

                if (type == BusinessVariety.DRUGS.getCode()// 药品
                        && goodsAttribute == GoodsAttribute2DrugsA.PATENTMEDICINE.getCode()// 成药
                        && prescriptionDrug == GoodsAttributeDrugsOTC.OTC.getCode()){// 非处方药

                    attributeDesc = attributeDesc + "-" + GoodsAttributeDrugsOTCType.getName(goods.getOtcType());

                }

            }

            // 品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］
            if (goods.getCosmetics() != null) { // 特殊类型化妆品

                if(type == BusinessVariety.COSMETICS.getCode() &&
                        goodsAttribute == GoodsAttributeCosmetics.COSMETICS_SPECIAL_USE.getCode()){
                    attributeDesc = attributeDesc + "-" + SpecialUseCosmetics.getName(goods.getCosmetics());

                }
            }

            goods.setGoodsAttributeDesc(attributeDesc);

            // 其他属性转换
            goods.setDomesticImportDesc(DomesticImport.getName(goods.getDomesticImport()));//国产/进口

            if(goods.getSpecialDrug() != null){
                goods.setSpecialDrugDesc(SpecialDrugs.getName(goods.getSpecialDrug()));// 特殊管理药品
            }

            if (goods.getSpiritDrugType() != null && goods.getSpecialDrug() != null) {
                if(goods.getSpecialDrug() == SpecialDrugsAll.SPIRIT_DRUGS.getCode()){// 特殊管理药品为精神药品时
                    goods.setSpecialDrugDesc(goods.getSpecialDrugDesc() + "-" + SpiritDrugsType.getName(goods.getSpiritDrugType()));
                }
            }

            if(goods.getInChargeDrug() != null){
                goods.setInChargeDrugDesc(SpecialCompoundPreparations.getName(goods.getInChargeDrug()));// 专管药品
            }

            if (goods.getFormulationType() != null  && goods.getInChargeDrug() != null) {
                if (goods.getInChargeDrug() == SpecialCompoundPreparations.CONTAINING_EPHEDRINE.getCode()){
                    goods.setInChargeDrugDesc(goods.getInChargeDrugDesc() + "-" + SpecialCompoundPreparationsType.getName(goods.getFormulationType()));
                }
            }

            if(goods.getStorageTemp() != null){
                goods.setStorageTempDesc(StorageTemp.getName(goods.getStorageTemp()));// 储藏温度
            }

            if(goods.getQualityPeriod() != null){
                goods.setQualityPeriodDesc(TimeUnit.getName(goods.getQualityPeriod()));// 保质期单位名称
            }

        }

        return goodsList;
    }

    @Override
    public void save(ReviewVO reviewVO, UserVO loginUser) throws Exception {

        GoodsQualityReview review = (GoodsQualityReview) EntityUtils.reflectAddSetDefaultValue(GoodsQualityReview.class, loginUser);
        BeanUtils.copyProperties(review, reviewVO);
        review.setParentId(loginUser.getParentId());
        review.setEnterpriseId(loginUser.getEnterpriseId());
        review.setStatus(EnableStatus.ENABLE.getStatus());
        reviewMapper.insertSelective(review);

    }

    @Override
    public void update(ReportVO report, UserVO loginUser) throws Exception {
        GoodsQualityReview review = (GoodsQualityReview) EntityUtils.reflectUpdateSetDefaultValue(GoodsQualityReview.class, loginUser);
        BeanUtils.copyProperties(review, report);
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public void delete(Long id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ReviewVO get(Long id) throws InvocationTargetException, IllegalAccessException {

        ReviewVO reviewVO = new ReviewVO();
        GoodsQualityReview review = reviewMapper.selectByPrimaryKey(id);
        System.out.println(review);
        BeanUtils.copyProperties(reviewVO,review);

        return reviewVO;
    }

    @Override
    public void export(Long id, OutputStream output, UserVO loginUser) throws Exception {
        ReviewVO reviewVO = this.get(id);


        ExcelWriter excelWriter = new ExcelWriter() {
            @Override
            public void generate() throws Exception {


                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();

                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();

                int num = 0;
                createHeader(num++, this, loginUser.getEnterpriseName());
                createHeader(num++, this, "药品质量评审表");
                createHeader(num++, this, "");


                createRow(num++, this, "评审编号：," + reviewVO.getReviewCode() + ",,," + "评审日期：," + reviewVO.getReviewDate() + ",,");
                createRow(num++, this, "评审期间（从）:," + reviewVO.getStartDate() + ",,," + "评审期间（至）:," + reviewVO.getEndDate() + ",,");

                createRow(num++, this, "编码: ," + reviewVO.getGoodsCode() + ",,通用名称," + reviewVO.getGenericName() + ",,,");
                createRow(num++, this, "条形码: ," + reviewVO.getBarcode() + ",,商品名称," + reviewVO.getGoodsName() + ",,,");

                createRow(num++, this, "剂型:," + reviewVO.getDosageName() + ",,规格," + reviewVO.getSpecification() + ",," + "单位," + reviewVO.getUnitName());
                createRow(num++, this, "生产厂商:," + reviewVO.getManufacturer() + ",,产地," + reviewVO.getPlace() + ",," + "国产/进口," + reviewVO.getDomesticImportName());
                createRow(num++, this, "批准文号:," + reviewVO.getApprovalNumber() + ",,有效期至," + reviewVO.getValidUntil() + ",," + "经营范围," + reviewVO.getManagementScopeName());
                createRow(num++, this, "特殊管理药品:," + reviewVO.getSpecialDrugName() + ",,专管药品," + reviewVO.getInChargeDrugName() + ",," + "限购数量," + reviewVO.getLimitQuantity());
                createRow(num++, this, "贮藏温度:," + reviewVO.getStorageTempName() + ",,贮藏条件," + reviewVO.getStorageConditionName() + ",," + "保质期," + reviewVO.getQualityPeriodUnitName());
                createRow(num++, this, "商品属性:," + reviewVO.getGoodsAttributeDesc());



                createRow(num++, this, "供货单位：,," + reviewVO.getSupplierNames() + ",,,,");
                createRow(num++,this,"");
                createRow(num++,this,"");

                createRow(num++, this, "订货笔数:,," + reviewVO.getOrderCount() + ",,订单数量,," + reviewVO.getOrderQuanlity());

                createRow(num++, this, "到货笔数:,," + reviewVO.getArrivalCount() + ",,收货数量,," + reviewVO.getReceiveQuanlity());

                createRow(num++, this, "拒收数量,," + reviewVO.getRejectQuanlity() + ",,验收合格数量,," + reviewVO.getCheckQualifiedQuantity());

                createRow(num++, this, "验收合格数量,," + reviewVO.getCheckBatch() + ",,收货合格率,," + reviewVO.getCheckQualifiedRate());

                createRow(num++, this, "验收不合格数量,," + reviewVO.getCheckUnqualifiedQuantity() + ",,验收合格率,," + reviewVO.getCheckQualifiedRate());

                createRow(num++, this, "购进退出笔数,," + reviewVO.getPurchaseReturnCount() + ",,购进退出批次,," + reviewVO.getPurchaseReturnBatch());

                createRow(num++, this, "购进退出数量,," + reviewVO.getPurchaseReturnCount() + ",,销售笔数,," + reviewVO.getSaleCount());

                createRow(num++, this, "销售批次,," + reviewVO.getSaleBatch() + ",,销售数量,," + reviewVO.getSaleQuantity());

                createRow(num++, this, "销后退回笔数,," + reviewVO.getSaleReturnCount() + ",,销售退回批次,," + reviewVO.getSaleReturnBatch());
                createRow(num++, this, "销后退回数量,," + reviewVO.getSaleReturnQuantity() + ",,销毁笔数,," + reviewVO.getDestroyCount());

                createRow(num++, this, "销毁批次,," + reviewVO.getDestroyBatch() + ",,销毁数量,," + reviewVO.getDestroyCount());

                createRow(num++, this, "投诉笔数,," + reviewVO.getComplainCount() + ",,投诉批次,," + reviewVO.getComplainBatch());

                createRow(num++, this, "不良反应笔数,," + reviewVO.getAdverseReactionCount() + ",,不良反应批次,," + reviewVO.getAdverseReactionBatch());

                createRow(num++, this, "召回数量,," + reviewVO.getRecallQuantity() + ",,召回批次,," + reviewVO.getRecallBatch());

                createRow(num++, this, "追回数量,," + reviewVO.getRecoverQuantity() + ",,追回批次,," + reviewVO.getRecoverBatch());

                createRow(num++, this, "业务部门评审意见,评审意见：" + (reviewVO.getBusinessOpinion()==null?" ":reviewVO.getBusinessOpinion()));
                createRow(num++, this, ",,,,,,,");

                createRow(num++, this, ",,评审日期:," + reviewVO.getBusinessReviewTime() + ",评审人员:," + reviewVO.getBusinessReviewer());

                createRow(num++, this, "质管部门评审意见,评审意见：" + (reviewVO.getQualityOpinion()==null?" ":reviewVO.getQualityOpinion()));
                createRow(num++, this, ",,,,,,,");

                createRow(num++, this, ",,评审日期:," + reviewVO.getQualityReviewTime() + ",评审人员:," + reviewVO.getQualityReviewer());

                createRow(num++, this, "总经理评审意见,评审意见： " + (reviewVO.getManagerOpinion()==null?" ":reviewVO.getManagerOpinion()));
                createRow(num++, this, ",,,,,,,");

                createRow(num++, this, ",,评审日期:," + reviewVO.getManagerReviewTime() + ",评审人员:," + reviewVO.getManagerReviewer());



                // 电子表格结束
                this.endSheet();
                //合并单元格
                this.beginMergerCell();

                // 头三行
                this.setMergeCell(0, 0, 0, 7);
                this.setMergeCell(1, 0, 1, 7);
                this.setMergeCell(2, 0, 2, 7);

                this.setMergeCell(3, 1, 3, 3);
                this.setMergeCell(3, 5, 3, 7);

                this.setMergeCell(4, 1, 4, 3);
                this.setMergeCell(4, 5, 4, 7);
                this.setMergeCell(5, 1, 5, 2);
                this.setMergeCell(5, 4, 5, 7);


                this.setMergeCell(6, 1, 6, 2);
                this.setMergeCell(6, 4, 6, 7);

                for (int i = 7; i < 12; i++) {
                    this.setMergeCell(i, 1, i, 2);
                    this.setMergeCell(i, 4, i, 5);
                }

                this.setMergeCell(12, 1, 12, 7);//商品属性
                this.setMergeCell(13, 0, 15, 1);
                this.setMergeCell(13, 2, 15, 7);

                for (int i = 16; i < 31; i++) {
                    this.setMergeCell(i, 0, i, 1);
                    this.setMergeCell(i, 2, i, 3);
                    this.setMergeCell(i, 4, i, 5);
                    this.setMergeCell(i, 6, i, 7);

                }

                this.setMergeCell(31, 0, 33, 0);
                this.setMergeCell(31,1,32,7);
                this.setMergeCell(34, 0, 36, 0);
                this.setMergeCell(34,1,35,7);
                this.setMergeCell(37, 0, 39, 0);
                this.setMergeCell(37,1,38,7);


                this.endMergerCell();
                this.endWorkSheet();

            }
        };
        excelWriter.process(output);

    }

    private void createHeader(int number, ExcelWriter excelWriter, String s) throws IOException {
        excelWriter.insertRow(number);

        Map<String, XSSFCellStyle> styleMap = excelWriter.getCellStyles();

        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            excelWriter.createCell(i, split[i], styleMap.get("cell_string").getIndex());
        }

        excelWriter.endRow();
    }


    private void createRow(int number, ExcelWriter excelWriter, String s) throws IOException {
        excelWriter.insertRow(number);

        Map<String, XSSFCellStyle> styleMap = excelWriter.getCellStyles();
        XSSFCellStyle cellStyle = styleMap.get("cell_string_left");

        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            excelWriter.createCell(i, "null".equals(split[i]) ? "" : split[i], cellStyle.getIndex());
        }

        excelWriter.endRow();
    }

    @Override
    public Page getPageList(ReviewRequestVO requestVO, UserVO loginUser) {
        //企业ID，加盟店允许查看总部和仅属于本门店的药品质量评审
        getRequestVO(requestVO, loginUser);

        if ("date".equals(requestVO.getOrder())) {
            requestVO.setOrder("review_date");
        }

        if ("code".equals(requestVO.getOrder())) {
            requestVO.setOrder("review_code");
        }

        Page<List<ReviewPageVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());
        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        requestVO.setPageStart(page.getStart());
        List<ReviewPageVO> dataList = reviewMapper.selectPageList(requestVO);
        dataList.stream().forEach(item->{
            item.setOwner(item.getEnterpriseId().equals(loginUser.getEnterpriseId()));
        });
        Integer count = reviewMapper.selectPageListCount(requestVO);

        page.setResult(dataList);
        page.setTotalRecord(count);


        return page;

    }

    private void getRequestVO(ReviewRequestVO requestVO, UserVO loginUser) {
        Long parentId = null;
        Long enterpriseId = null;
        requestVO.setChainType(loginUser.getChainType());
        if(loginUser.getChainType() == ChainType.Headquarters.getCode()){
            //总部查看所有的记录
            enterpriseId = loginUser.getEnterpriseId();
            parentId = loginUser.getEnterpriseId();
        } else if(loginUser.getChainType() == ChainType.Selfoperatedshop.getCode()){
            enterpriseId = loginUser.getParentId();
        } else if(loginUser.getChainType() == ChainType.Division.getCode()){
            enterpriseId = loginUser.getEnterpriseId();
            parentId = loginUser.getParentId();
        }
        requestVO.setParentId(parentId);
        requestVO.setEnterpriseId(enterpriseId);
    }

    @Override
    public void exportList(ReviewRequestVO requestVO, UserVO loginUser, OutputStream output) throws Exception {
        //企业ID，加盟店允许查看总部和仅属于本门店的药品质量评审
        getRequestVO(requestVO, loginUser);

        List<ReviewPageVO> dataList = reviewMapper.selectPageList(requestVO);

        //序号
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("reviewDate", "评审日期");
        map.put("reviewCode", "评审编号");
        map.put("startDate", "评审期间（从）");
        map.put("endDate", "评审期间(至）");
        map.put("goodsCode", "商品编码");
        map.put("barcode", "条形码");
        map.put("genericName", "通用名称");
        map.put("goodsName", "商品名称");
        map.put("dosageName", "剂型");
        map.put("specification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("place", "产地");
        map.put("approvalNumber", "批准文号");
        map.put("validUntil", "有效期至");
        map.put("goodsAttributeDesc", "商品属性");
        map.put("domesticImportDesc", "国产/进口");
        map.put("managementScopeName", "经营范围");
        map.put("inChargeDrugDesc", "特殊管理药品");
        map.put("inChargeDrugDesc", "专管药品");
        map.put("limitQuantity", "限购数量");
        map.put("storageTempDesc", "贮藏温度");
        map.put("storageConditionName", "贮藏条件");
        map.put("qualityPeriodDesc", "保质期");

        excelComponent.exportExcel(output, loginUser.getEnterpriseName(), "药品质量评审", new ArrayList<>(), map, dataList,null);

    }

    /**
     * 查询期间 统计数据
     *
     * @param goodsId
     * @param startDate
     * @param endDate
     * @param loginUser
     * @return
     */
    @Override
    public ReviewVO getStatistics(Long goodsId, String startDate, String endDate, UserVO loginUser) {

        ReviewVO reviewVO = new ReviewVO();

        Map<String, Object> param = new HashMap<>();
        param.put("eId", loginUser.getEnterpriseId());
        param.put("goodsId", goodsId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("orderStatus", PurchaseStatus.CANCELED.getStatus());// 查询供货单位时用


        // 供货单位(订单表中非取消状态的期间数据)
        List<PurchaseOrder> orderSupplierList = reviewMapper.selectSupplierByGIDBtwDate(param);
        String supplierIds = "";
        String supplierNames = "";
        System.out.println(orderSupplierList.size());
        for (PurchaseOrder order : orderSupplierList) {
            supplierIds = supplierIds + order.getSupplierId();
            supplierNames = supplierNames + order.getSupplierName();
        }

        reviewVO.setSupplierIds(supplierIds);
        reviewVO.setSupplierNames(supplierNames);

        // 订单笔数
        Map<String, Object> orderMap = reviewMapper.selectOrderCountByGIDBtwDate(param);
        reviewVO.setOrderCount(((Long)orderMap.get("count")).intValue());
        reviewVO.setOrderQuanlity((BigDecimal) orderMap.get("quantity"));

        // 到货笔数 -收货数量
        // 拒收数量  收货合格率
        Map<String, Object> receiveResult = reviewMapper.selectReceiveByGIDBtwDate(param);

        reviewVO.setArrivalCount(((Long) receiveResult.get("arrivalCount")).intValue());
        reviewVO.setReceiveQuanlity((BigDecimal) receiveResult.get("receiveQuantity"));
        reviewVO.setRejectQuanlity((BigDecimal) receiveResult.get("refuseQuantity"));

        if (reviewVO.getReceiveQuanlity().compareTo(BigDecimal.ZERO) == 0
                && reviewVO.getRejectQuanlity().compareTo(BigDecimal.ZERO) == 0) {
            reviewVO.setReceiveQualifiedRate(BigDecimal.ZERO);
        } else {
            BigDecimal arrivalTotal = reviewVO.getReceiveQuanlity().add(reviewVO.getRejectQuanlity());
            reviewVO.setReceiveQualifiedRate(reviewVO.getReceiveQuanlity().divide(arrivalTotal,2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
        }


        // 验收批次  验收合格数量  验收不合格数量  验收合格率

        Map<String, Object> checkResult = reviewMapper.selectCheckByGIDBtwDate(param);
        reviewVO.setCheckBatch(((Long) checkResult.get("batch")).intValue());
        reviewVO.setCheckQualifiedQuantity((BigDecimal) checkResult.get("qualifiedQuantity"));
        reviewVO.setCheckUnqualifiedQuantity((BigDecimal) checkResult.get("unqualifiedQuantity"));


        if (reviewVO.getCheckQualifiedQuantity().compareTo(BigDecimal.ZERO) == 0
                && reviewVO.getCheckUnqualifiedQuantity().compareTo(BigDecimal.ZERO) == 0) {
            reviewVO.setCheckQualifiedRate(BigDecimal.ZERO);
        } else {
            reviewVO.setCheckQualifiedRate(reviewVO.getCheckQualifiedQuantity().divide(reviewVO.getCheckQualifiedQuantity().add(reviewVO.getCheckUnqualifiedQuantity()),2,BigDecimal.ROUND_HALF_EVEN));
            reviewVO.setCheckQualifiedRate(reviewVO.getCheckQualifiedRate().multiply(new BigDecimal(100)));
        }

        // 购进退出笔数 购进退出批次 购进退出数量

        Map<String, Object> purchaseReturnResult = reviewMapper.selectPurchaseReturnByGIDBtwDate(param);
        reviewVO.setPurchaseReturnBatch(((Long) purchaseReturnResult.get("batch")).intValue());
        reviewVO.setPurchaseReturnCount(((Long) purchaseReturnResult.get("count")).intValue());
        reviewVO.setPurchaseReturnQuantity((BigDecimal) purchaseReturnResult.get("quantity"));

        // 销售笔数  销售批次  销售数量

        param.put("saleType", SaleGenre.SALE.getCode());
        Map<String, Object> saleResult = reviewMapper.selectSaleByGIDBtwDate(param);

        reviewVO.setSaleBatch(((Long) saleResult.get("batch")).intValue());
        reviewVO.setSaleCount(((Long) saleResult.get("count")).intValue());
        reviewVO.setSaleQuantity((BigDecimal) saleResult.get("quantity"));


        // 销后退回笔数  销售退回批次  销后退回数量

        param.put("saleType", SaleGenre.SALERETURN.getCode());

        Map<String, Object> saleReturnResult = reviewMapper.selectSaleByGIDBtwDate(param);

        reviewVO.setSaleReturnBatch(((Long) saleReturnResult.get("batch")).intValue());
        reviewVO.setSaleReturnCount(((Long) saleReturnResult.get("count")).intValue());
        reviewVO.setSaleReturnQuantity((BigDecimal) saleReturnResult.get("returnQuantity"));


        // 销毁笔数  销毁批次  销毁数量

        Map<String, Object> destroyResult = reviewMapper.selectDestoryByGIDBtwDate(param);

        reviewVO.setDestroyBatch(((Long) destroyResult.get("batch")).intValue());
        reviewVO.setDestroyCount(((Long) destroyResult.get("count")).intValue());
        reviewVO.setDestroyQuantity((BigDecimal) destroyResult.get("quantity"));


        // 投诉笔数  投诉批次
        Integer complainCount = reviewMapper.selectComplainCountByGIDBtwDate(param);
        reviewVO.setComplainCount(complainCount);
        reviewVO.setComplainBatch(complainCount);
        //不良反应笔数  不良反应批次

        Integer adverseReactionCount = reviewMapper.selectAdverseReactionCountByGIDBtwDate(param);
        reviewVO.setAdverseReactionCount(adverseReactionCount);
        reviewVO.setAdverseReactionBatch(adverseReactionCount);

        //召回数量 召回批次
        Map<String, Object> recallResult = reviewMapper.selectRecallSumByGIDBtwDate(param);
        reviewVO.setRecallQuantity((BigDecimal) recallResult.get("quantity"));
        reviewVO.setRecallBatch(((Long) recallResult.get("count")).intValue());

        //追回数量  追回批次

        Map<String, Object> recoverResult = reviewMapper.selectRecoverSumByGIDBtwDate(param);

        reviewVO.setRecoverQuantity((BigDecimal) recoverResult.get("quantity"));
        reviewVO.setRecoverBatch(((Long) recoverResult.get("count")).intValue());

        return reviewVO;
    }


}
