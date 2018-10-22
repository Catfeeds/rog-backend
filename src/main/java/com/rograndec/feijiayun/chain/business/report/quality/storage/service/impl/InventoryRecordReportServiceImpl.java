package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.dao.GoodsSeriesReportMapper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.InventoryRecordReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.InventoryRecordReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.InventoryRecordReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportInventoryRecordVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/21 <br/>
 * 描述：盘点记录报表
 */
@Service
public class InventoryRecordReportServiceImpl implements InventoryRecordReportService {

    @Autowired
    private GoodsSeriesReportMapper goodsSeriesReportMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page<InventoryRecordReportTotalVO> getInventoryRecordReportList(RequestReportInventoryRecordVO requestVO, Page page, UserVO userVO) throws Exception {
        //判断是总部，还是门店
        if (userVO.getParentId() == 0) {
            requestVO.setType(1);
        } else {
            requestVO.setType(2);
        }
        requestVO.setEnterpriseId(userVO.getEnterpriseId());

        //获取合计
        InventoryRecordReportTotalVO inventoryRecordReportTotalVO = goodsSeriesReportMapper.getInventoryRecordReportTotal(requestVO);
        if (inventoryRecordReportTotalVO == null) {
            inventoryRecordReportTotalVO = new InventoryRecordReportTotalVO();
        }

        //商品锁定列表
        com.github.pagehelper.Page hPage = null;
        if (requestVO.getPageNo() != null && requestVO.getPageSize() != null) {
            hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        }
        List<InventoryRecordReportVO> inventoryRecordReportVOList = goodsSeriesReportMapper.getInventoryRecordReport(requestVO);
        inventoryRecordReportTotalVO.setDataList(inventoryRecordReportVOList == null ? new ArrayList<>() : inventoryRecordReportVOList);

        page.setResult(inventoryRecordReportTotalVO);
        if (hPage != null) {
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return page;
    }

    @Override
    public void excelExport(OutputStream output, InventoryRecordReportTotalVO totalVO, UserVO userVO) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("盘点记录");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (userVO.getParentId() == 0) {
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
        }
        map.put("invCode", "盘点单号");
        map.put("createTime", "创建日期");
        map.put("createrName", "创建人员");
        map.put("registerDate", "登记日期");
        map.put("registerManName", "登记人员");
        map.put("invDate", "盘点日期");
        map.put("invManName", "盘点人员");
        map.put("secondInvManName", "复盘人员");
        map.put("postDate", "过账日期");
        map.put("postManName", "过账人员");

        map.put("goodsCode", "商品编码");
        map.put("barcode", "条形码");
        map.put("goodsGenericName", "通用名称");
        map.put("goodsName", "商品名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("approvalNumber", "批准文号");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("shelfName", "货位");
        map.put("shelfStatusDesc", "质量状况");

        map.put("quantity", "账面数量");
        map.put("invQuantity", "实盘数量");
        map.put("diffQuantity", "损溢数量");
        map.put("unitPrice", "单价");
        map.put("amount", "账面金额");
        map.put("realAmount", "实盘金额");
        map.put("diffAmount", "损溢金额");
        map.put("taxRate", "税率");
        map.put("notaxPrice", "不含税单价");
        map.put("notaxAmount", "不含税账面金额");
        map.put("realNotaxAmount", "不含税实盘金额");
        map.put("diffNotaxAmount", "不含税损溢金额");
        map.put("taxAmount", "账面税额");
        map.put("realTaxAmount", "实盘税额");
        map.put("diffTaxAmount", "损溢税额");
        map.put("retailPrice", "零售单价");
        map.put("retailAmount", "账面零售金额");
        map.put("realRetailAmount", "实盘零售金额");
        map.put("diffRetailAmount", "损溢零售金额");

        map.put("statusStr", "状态");
        map.put("businessVarietyName", "品种类型");
        map.put("categoryName", "商品分类");
        map.put("goodsAttributeName", "商品属性");
        map.put("domesticImportDesc", "国产/进口");
        map.put("managementScopeName", "经营范围");
        map.put("specialDrugName", "特殊管理药品");
        map.put("inChargeDrugName", "专管药品");
        map.put("limitQuantity", "限购数量");
        map.put("storageTempDesc", "贮藏温度");
        map.put("storageConditionName", "贮藏条件");
        map.put("qualityPeriodDesc", "保质期");

        //合计
        StringBuilder endTotal = new StringBuilder();
        endTotal.append(totalVO.getQuantityTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getInvQuantityTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getDiffQuantityTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getRealAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getDiffAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getNotaxAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getRealNotaxAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getTaxAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getRealTaxAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getDiffTaxAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getRetailAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getRealRetailAmountTotal());
        endTotal.append(";");
        endTotal.append(totalVO.getDiffRetailAmountTotal());

        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("quantity");
        needTotalName.add("invQuantity");
        needTotalName.add("diffQuantity");
        needTotalName.add("amount");
        needTotalName.add("realAmount");
        needTotalName.add("diffAmount");
        needTotalName.add("notaxAmount");
        needTotalName.add("realNotaxAmount");
        needTotalName.add("taxAmount");
        needTotalName.add("realTaxAmount");
        needTotalName.add("diffTaxAmount");
        needTotalName.add("retailAmount");
        needTotalName.add("realRetailAmount");
        needTotalName.add("diffRetailAmount");

        purchaseGeneralComponent.commExcelExport(output, map, totalVO.getDataList(),
                names, null, endTotal.toString(), false, needTotalName);

    }

}
