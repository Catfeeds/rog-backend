package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckLotMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckMapper;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseCheckReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseCheckReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.RequestParamForCheckReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalPurchaseCheckReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PurchaseCheckReportServiceImpl implements PurchaseCheckReportService {

    @Autowired
    PurchaseCheckLotMapper purchaseCheckLotMapper;
    @Autowired
    PurchaseCheckMapper purchaseCheckMapper;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    PurchaseGeneralComponent<PurchaseCheckReportPageVO> purchaseGeneralComponent;

    @Override
    public Page<TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>> getPurchaseCheckReportPage(Page<TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>> page, RequestParamForCheckReportListVO paramForListVO, Long enterpriseId) {

//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(paramForListVO.getPageNo(), paramForListVO.getPageSize());
        if(paramForListVO.getOrderName() != null && paramForListVO.getOrderName().equals("checkDate")){
            paramForListVO.setOrderName("check_date");
        }else{
            paramForListVO.setOrderName(null);
            paramForListVO.setOrderType(null);
        }
        //先获取采购验收明细信息
        paramForListVO.setPageStart(page.getStart());
        Integer count = purchaseCheckLotMapper.getPurchaseCheckReportPageCount(paramForListVO, enterpriseId);
        List<PurchaseCheckReportPageVO> purchaseCheckReportPageVO = purchaseCheckLotMapper.getPurchaseCheckReportPageList(paramForListVO, enterpriseId);
        TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>  totalPurchaseCheckReportVO = setDataName(purchaseCheckReportPageVO, enterpriseId);

        paramForListVO.setPageStart(null);
        paramForListVO.setPageSize(null);
        List<PurchaseCheckReportPageVO> purchaseCheckReportPage = purchaseCheckLotMapper.getPurchaseCheckReportPageList(paramForListVO, enterpriseId);
        TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO> totalPurchaseCheckReport = setDataName(purchaseCheckReportPage, enterpriseId);
        totalPurchaseCheckReportVO.setQualifiedQuantityAll(totalPurchaseCheckReport.getQualifiedQuantityAll());
        totalPurchaseCheckReportVO.setReceiveQuantityAll(totalPurchaseCheckReport.getReceiveQuantityAll());
        totalPurchaseCheckReportVO.setSamplingQuantityAll(totalPurchaseCheckReport.getSamplingQuantityAll());
        totalPurchaseCheckReportVO.setUnqualifiedQuantityAll(totalPurchaseCheckReport.getUnqualifiedQuantityAll());
        page.setResult(totalPurchaseCheckReportVO);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, RequestParamForCheckReportListVO paramForListVO) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("checkDate","日期");
        map.put("code","单号");
        map.put("supplierCode","供货单位编码");
        map.put("supplierName","供货单位名称");
        map.put("supplierSalerName","销售人员");
        map.put("supplierSalerPhone","联系电话");
        map.put("checkerName","验收人员");
        map.put("secondCheckerName","验收人员2");
        map.put("purchaseOrderCode","订单单号");
        map.put("baseOrderCode","收货单号");
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
        map.put("checkProjectNames","检验项目");
        map.put("receiveQuantity","收货数量");
        map.put("samplingQuantity","抽检数量");
        map.put("qualifiedQuantity","验收合格数量");
        map.put("conclusionNames","验收结论");
        map.put("unqualifiedQuantity","验收不合格数量");
        map.put("unqualifiedReasonName","不合格原因");
        map.put("measuresNames","处置措施");
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
        List<PurchaseCheckReportPageVO> purchaseCheckReportPageVOS = purchaseCheckLotMapper.getPurchaseCheckReportPageList(paramForListVO, loginUser.getEnterpriseId());
        setDataName(purchaseCheckReportPageVOS, loginUser.getEnterpriseId());
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        //分批号数量
        BigDecimal receiveQuantity = BigDecimal.ZERO;//收货数量
        BigDecimal qualifiedQuantity = BigDecimal.ZERO;//验收合格数量
        BigDecimal unqualifiedQuantity = BigDecimal.ZERO;//验收不合格数量
        BigDecimal samplingQuantity = BigDecimal.ZERO;//抽样数量

        for (PurchaseCheckReportPageVO purchaseCheckReportPageVO : purchaseCheckReportPageVOS) {
            receiveQuantity = receiveQuantity.add(purchaseCheckReportPageVO.getReceiveQuantity());
            qualifiedQuantity = qualifiedQuantity.add(purchaseCheckReportPageVO.getQualifiedQuantity());
            unqualifiedQuantity = unqualifiedQuantity.add(purchaseCheckReportPageVO.getUnqualifiedQuantity());
            samplingQuantity = samplingQuantity.add(purchaseCheckReportPageVO.getSamplingQuantity());
        }
        end.append(receiveQuantity);
        end.append(";");
        end.append(qualifiedQuantity);
        end.append(";");
        end.append(unqualifiedQuantity);
        end.append(";");
        end.append(samplingQuantity);
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("receiveQuantity");
        needTotalName.add("qualifiedQuantity");
        needTotalName.add("unqualifiedQuantity");
        needTotalName.add("samplingQuantity");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("采购验收");
        paramForListVO.setPageNo(null);
        paramForListVO.setPageSize(null);
        purchaseGeneralComponent.commExcelExport(output,map,purchaseCheckReportPageVOS,name,titleSecond,end.toString(),false,needTotalName);
    }

    private TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO> setDataName(List<PurchaseCheckReportPageVO> purchaseCheckReportPageVOS, Long enterpriseId) {

        TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO>  totalPurchaseCheckReportVO = new TotalPurchaseCheckReportVO<>();
        BigDecimal receiveQuantity = BigDecimal.ZERO;//收货数量
        BigDecimal qualifiedQuantity = BigDecimal.ZERO;//验收合格数量
        BigDecimal unqualifiedQuantity = BigDecimal.ZERO;//验收不合格数量
        BigDecimal samplingQuantity = BigDecimal.ZERO;//抽样数量
        for (PurchaseCheckReportPageVO purchaseCheckReportPageVO : purchaseCheckReportPageVOS) {

            //检验项目ID集合
            String check_project_ids = purchaseCheckReportPageVO.getCheckProjectIds();
            if (StringUtils.isNotBlank(check_project_ids)) {
                String check_project_ids_names = getDeatilNum(enterpriseId, check_project_ids, (long) 5);
                if(check_project_ids_names != null) {
                    purchaseCheckReportPageVO.setCheckProjectNames(check_project_ids_names);
                }
            }

            //验收结论ID集合
            String conclusion_ids = purchaseCheckReportPageVO.getConclusionIds();
            if (StringUtils.isNotBlank(conclusion_ids) ) {
                String conclusion_ids_names = getDeatilNum(enterpriseId, conclusion_ids, (long) 6);
                if(conclusion_ids_names != null) {
                    purchaseCheckReportPageVO.setConclusionNames(conclusion_ids_names);
                }
            }

            //处置措施ID集合
            String measures_ids = purchaseCheckReportPageVO.getMeasuresIds();
            if (StringUtils.isNotBlank(measures_ids)) {
                String measures_ids_names = getDeatilNum(enterpriseId, measures_ids, (long) 3);
                if(measures_ids_names != null) {
                    purchaseCheckReportPageVO.setMeasuresNames(measures_ids_names);
                }
            }

            //验收不合格原因ID集合
            String unqualified_reason_ids = purchaseCheckReportPageVO.getUnqualifiedReasonIds();
            if (StringUtils.isNotBlank(unqualified_reason_ids)) {
                String unqualified_reason_ids_names = getDeatilNum(enterpriseId, unqualified_reason_ids, (long) 1);
                if(unqualified_reason_ids_names != null) {
                    purchaseCheckReportPageVO.setUnqualifiedReasonName(unqualified_reason_ids_names);
                }
            }
            //品种类别
            purchaseCheckReportPageVO.setBusinessVarietyName(purchaseCheckReportPageVO.getBusinessVariety()==null?null:BusinessVariety.getName(purchaseCheckReportPageVO.getBusinessVariety()));
            //商品属性
            purchaseCheckReportPageVO.setGoodsAttributeName(purchaseCheckReportPageVO.getGoodsAttribute()==null?null: GoodsAttribute2DrugsA.getName(purchaseCheckReportPageVO.getGoodsAttribute()));
            purchaseCheckReportPageVO.setPrescriptionDrugName(purchaseCheckReportPageVO.getPrescriptionDrug()==null?null: PrescriptionDrug.getName(purchaseCheckReportPageVO.getPrescriptionDrug()));
            purchaseCheckReportPageVO.setOtcTypeName(purchaseCheckReportPageVO.getOtcType()==null?null: GoodsAttributeDrugsOTCType.getName(purchaseCheckReportPageVO.getOtcType()));
            //国产/进口
            purchaseCheckReportPageVO.setDomesticImportName(purchaseCheckReportPageVO.getDomesticImport()==null?null: DomesticImport.getName(purchaseCheckReportPageVO.getDomesticImport()));
            //特殊管理药品
            purchaseCheckReportPageVO.setSpecialDrugName(purchaseCheckReportPageVO.getSpiritDrugType()==null?null: SpecialDrugsAll.getName(purchaseCheckReportPageVO.getSpiritDrugType()));
            purchaseCheckReportPageVO.setSpiritDrugTypeName(purchaseCheckReportPageVO.getSpiritDrugType()==null?null: SpiritDrugsType.getName(purchaseCheckReportPageVO.getSpiritDrugType()));
            //专管药品
            purchaseCheckReportPageVO.setInChargeDrugName(purchaseCheckReportPageVO.getInChargeDrug()==null?null: InChargeDrug.getName(purchaseCheckReportPageVO.getInChargeDrug()));
            purchaseCheckReportPageVO.setFormulationTypeName(purchaseCheckReportPageVO.getFormulationType()==null?null: SpecialCompoundPreparationsType.getName(purchaseCheckReportPageVO.getFormulationType()));
            //贮藏温度
            purchaseCheckReportPageVO.setStorageTempName(purchaseCheckReportPageVO.getStorageTemp()==null?null:StorageTemp.getName(purchaseCheckReportPageVO.getStorageTemp()));
            //保质期单位
            QualityPeriodUnitName(purchaseCheckReportPageVO.getQualityPeriodUnit(),purchaseCheckReportPageVO);
            //状态
            purchaseCheckReportPageVO.setStatusName(PurchaseStatus.getName(purchaseCheckReportPageVO.getStatus()));


            //拼接商品属性
            purchaseCheckReportPageVO.setGoodsAttributeAll((purchaseCheckReportPageVO.getGoodsAttributeName() == null ? "" : purchaseCheckReportPageVO.getGoodsAttributeName()) + (purchaseCheckReportPageVO.getPrescriptionDrugName() == null ? "" :  "-" + purchaseCheckReportPageVO.getPrescriptionDrugName()) + (purchaseCheckReportPageVO.getOtcTypeName() == null ? "" : "-" + purchaseCheckReportPageVO.getOtcTypeName()));
            //拼接特殊管理药品
            purchaseCheckReportPageVO.setSpecialDrugAll((purchaseCheckReportPageVO.getSpecialDrugName() == null ? "" : purchaseCheckReportPageVO.getSpecialDrugName()) + (purchaseCheckReportPageVO.getSpiritDrugTypeName() == null ? "" :  "-" + purchaseCheckReportPageVO.getSpiritDrugTypeName()));
            //拼接专管药品
            purchaseCheckReportPageVO.setInChargeDrugAll((purchaseCheckReportPageVO.getInChargeDrugName() == null ? "" : purchaseCheckReportPageVO.getInChargeDrugName()) + (purchaseCheckReportPageVO.getFormulationTypeName() == null ? "" :  "-" + purchaseCheckReportPageVO.getFormulationTypeName()));
            //拼接保质期
            purchaseCheckReportPageVO.setQualityPeriodAll((purchaseCheckReportPageVO.getQualityPeriod() == null ? "" : purchaseCheckReportPageVO.getQualityPeriod()) + (purchaseCheckReportPageVO.getQualityPeriodUnitName() == null ? "" : purchaseCheckReportPageVO.getQualityPeriodUnitName()));

            receiveQuantity = receiveQuantity.add(purchaseCheckReportPageVO.getReceiveQuantity());
            qualifiedQuantity = qualifiedQuantity.add(purchaseCheckReportPageVO.getQualifiedQuantity());
            unqualifiedQuantity = unqualifiedQuantity.add(purchaseCheckReportPageVO.getUnqualifiedQuantity());
            samplingQuantity = samplingQuantity.add(purchaseCheckReportPageVO.getSamplingQuantity());
        }
        totalPurchaseCheckReportVO.setReceiveQuantityAll(receiveQuantity);
        totalPurchaseCheckReportVO.setQualifiedQuantityAll(qualifiedQuantity);
        totalPurchaseCheckReportVO.setUnqualifiedQuantityAll(unqualifiedQuantity);
        totalPurchaseCheckReportVO.setSamplingQuantityAll(samplingQuantity);
        totalPurchaseCheckReportVO.setData(purchaseCheckReportPageVOS);
        return  totalPurchaseCheckReportVO;
    }

    private void QualityPeriodUnitName(Integer qualityPeriodUnit, PurchaseCheckReportPageVO purchaseCheckReportPageVO) {
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
        purchaseCheckReportPageVO.setQualityPeriodUnitName(unitDesc == null ? "" : unitDesc);
    }

    public String getDeatilNum(Long enterpriseId, String ids, Long setType) {

        String[] id = ids.split(",");
        String backVal = purchaseCheckMapper.selectById(enterpriseId, id, setType);
        return backVal;
    }

}
