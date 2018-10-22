package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.service.PurchaseReceiveService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseReceiveReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseReceiveReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.ReceivePageVO;
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
public class PurchaseReceiveReportServiceImpl implements PurchaseReceiveReportService {
    @Autowired
    PurchaseReceiveDetailMapper purchaseReceiveDetailMapper;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    PurchaseReceiveService purchaseReceiveService;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page<TotalReportVO<PurchaseReceiveReportVO>> getPurchaseReceive(UserVO loginUser, ReceivePageVO receivePageVO, Page<TotalReportVO<PurchaseReceiveReportVO>> page) throws Exception {
        if(receivePageVO.getOrderName() != null && receivePageVO.getOrderType() != null){
            if(receivePageVO.getOrderName().equals("orderDate")){
                receivePageVO.setOrderName("dtl.receive_date");
            } else if(receivePageVO.getOrderName().equals("orderCode") || receivePageVO.getOrderName().equals("code")){
                receivePageVO.setOrderName("dtl.receive_code");
            }else {
                receivePageVO.setOrderName(null);
                receivePageVO.setOrderType(null);
            }
        }
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<PurchaseReceiveReportVO> purchaseOrderReportVOS = purchaseReceiveDetailMapper.getPlanReportInfo(loginUser.getEnterpriseId(),receivePageVO);
        TotalReportVO<PurchaseReceiveReportVO> totalReportVO = getGoodsInfo(purchaseOrderReportVOS,loginUser);
        page.setResult(totalReportVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, ReceivePageVO receivePageVO) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("receiveDate","日期");
        map.put("receiveCode","单号");
        map.put("supplierCode","供货单位编码");
        map.put("supplierName","供货单位名称");
        map.put("supplierSalerName","销售人员");
        map.put("supplierSalerPhone","联系电话");
        map.put("baseOrderCode","订单单号");
        if(receivePageVO.getReportType()==0){
            map.put("receiverName","收货人员");
            map.put("secondReceiverName","收货人员2");
        }else if(receivePageVO.getReportType()==1){
            map.put("receiverName","拒收人员");
        }
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
        map.put("orderQuantity","订单数量");
        map.put("arrivalQuantity","到货数量");
        map.put("receiveQuantity","收货数量");
        map.put("refuseReason","拒收原因");
        map.put("quantity","订单数量");
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
        if(receivePageVO.getOrderName() != null && receivePageVO.getOrderType() != null){
            if(receivePageVO.getOrderName().equals("orderDate")){
                receivePageVO.setOrderName("dtl.receive_date");
            } else if(receivePageVO.getOrderName().equals("orderCode") || receivePageVO.getOrderName().equals("code")){
                receivePageVO.setOrderName("dtl.receive_code");
            }else {
                receivePageVO.setOrderName(null);
                receivePageVO.setOrderType(null);
            }
        }
        List<PurchaseReceiveReportVO> purchaseOrderReportVOS = purchaseReceiveDetailMapper.getPlanReportInfo(loginUser.getEnterpriseId(),receivePageVO);
        TotalReportVO<PurchaseReceiveReportVO> totalReportVO = getGoodsInfo(purchaseOrderReportVOS,loginUser);
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        BigDecimal orderQuantity = BigDecimal.ZERO;
        BigDecimal arrivalQuantity = BigDecimal.ZERO;
        BigDecimal receiveQuantity = BigDecimal.ZERO;
        BigDecimal refuseQuantity = BigDecimal.ZERO;
        for(PurchaseReceiveReportVO purchaseReceiveReportVO : purchaseOrderReportVOS){
            orderQuantity = orderQuantity.add(purchaseReceiveReportVO.getOrderQuantity());
            arrivalQuantity = arrivalQuantity.add(purchaseReceiveReportVO.getArrivalQuantity());
            receiveQuantity = receiveQuantity.add(purchaseReceiveReportVO.getReceiveQuantity());
            refuseQuantity = refuseQuantity.add(purchaseReceiveReportVO.getReceiveQuantity());
        }
        end.append(orderQuantity);
        end.append(";");
        end.append(arrivalQuantity);
        end.append(";");
        end.append(receiveQuantity);
        end.append(";");
        end.append(refuseQuantity);
        List<String> needTotalName = new ArrayList<>();
        if(receivePageVO.getReportType()==0){
            needTotalName.add("orderQuantity");
            needTotalName.add("arrivalQuantity");
            needTotalName.add("receiveQuantity");
        }
        needTotalName.add("refuseQuantity");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        if(receivePageVO.getReportType()==0){
            name.add("采购收货");
        }else if(receivePageVO.getReportType()==1){
            name.add("采购拒收");
        }
        receivePageVO.setPageNo(null);
        receivePageVO.setPageSize(null);
        purchaseGeneralComponent.commExcelExport(output,map,totalReportVO.getData(),name,titleSecond,end.toString(),false,needTotalName);

    }

    private TotalReportVO<PurchaseReceiveReportVO> getGoodsInfo(List<PurchaseReceiveReportVO> purchaseReceiveReportVOS, UserVO loginUser) throws Exception{
        TotalReportVO<PurchaseReceiveReportVO> totalReportVO = new TotalReportVO<>();
        BigDecimal orderQuantity = BigDecimal.ZERO;
        BigDecimal arrivalQuantity = BigDecimal.ZERO;
        BigDecimal receiveQuantity = BigDecimal.ZERO;
        BigDecimal refuseQuantity = BigDecimal.ZERO;
        for(PurchaseReceiveReportVO purchaseReceiveReportVO : purchaseReceiveReportVOS){
            purchaseReceiveReportVO.setBusinessVarietyName(BusinessVariety.getName(purchaseReceiveReportVO.getBusinessVariety()));
            //商品分类
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(purchaseReceiveReportVO.getCategoryId());
            purchaseReceiveReportVO.setCategoryName(goodsCategory==null?null:goodsCategory.getName());
            //商品属性
            purchaseReceiveReportVO.setGoodsAttributeName(purchaseReceiveReportVO.getGoodsAttribute()==null?null: GoodsAttribute2DrugsA.getName(purchaseReceiveReportVO.getGoodsAttribute()));
            //国产/进口
            purchaseReceiveReportVO.setDomesticImportName(purchaseReceiveReportVO.getDomesticImport()==null?null: DomesticImport.getName(purchaseReceiveReportVO.getDomesticImport()));
            //特殊管理药品
            purchaseReceiveReportVO.setSpecialDrugName(purchaseReceiveReportVO.getSpecialDrug()==null?null: SpecialDrugs.getName(purchaseReceiveReportVO.getSpecialDrug()));
            //专管药品
            purchaseReceiveReportVO.setInChargeDrugName(purchaseReceiveReportVO.getInChargeDrug()==null?null: ChargeDrugs.getName(purchaseReceiveReportVO.getInChargeDrug()));
            //贮藏温度
            purchaseReceiveReportVO.setStorageTempName(purchaseReceiveReportVO.getStorageTemp()==null?null:StorageTemp.getName(purchaseReceiveReportVO.getStorageTemp()));
            //保质期
            purchaseReceiveReportVO.setQualityPeriodUnitName(purchaseReceiveReportVO.getQualityPeriod().toString()+GoodsQualityPeriodUnit.getName(purchaseReceiveReportVO.getQualityPeriodUnit()));
            //状态
            purchaseReceiveReportVO.setStatusName(PurchaseStatus.getName(purchaseReceiveReportVO.getStatus()));
            //拒收原因
            purchaseReceiveReportVO.setRefuseReason(purchaseReceiveService.getRefuseReasons(purchaseReceiveReportVO.getRefuseReasonIds(),loginUser));
            orderQuantity = orderQuantity.add(purchaseReceiveReportVO.getOrderQuantity()==null?BigDecimal.ZERO:purchaseReceiveReportVO.getOrderQuantity());
            arrivalQuantity = arrivalQuantity.add(purchaseReceiveReportVO.getArrivalQuantity()==null?BigDecimal.ZERO:purchaseReceiveReportVO.getArrivalQuantity());
            receiveQuantity = receiveQuantity.add(purchaseReceiveReportVO.getReceiveQuantity()==null?BigDecimal.ZERO:purchaseReceiveReportVO.getReceiveQuantity());
            refuseQuantity = refuseQuantity.add(purchaseReceiveReportVO.getReceiveQuantity()==null?BigDecimal.ZERO:purchaseReceiveReportVO.getRefuseQuantity());
        }
        totalReportVO.setOrderQuantity(orderQuantity);
        totalReportVO.setArrivalQuantity(arrivalQuantity);
        totalReportVO.setReceiveQuantity(receiveQuantity);
        totalReportVO.setRefuseQuantity(refuseQuantity);
        totalReportVO.setData(purchaseReceiveReportVOS);
        return totalReportVO;
    }

}
