package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.dao.GoodsSeriesReportMapper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsHandleReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsHandleReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsHandleVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/20 <br/>
 * 描述：商品处理报表
 */
@Service
public class GoodsHandleReportServiceImpl implements GoodsHandleReportService {

    @Autowired
    private GoodsSeriesReportMapper goodsSeriesReportMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page getGoodsHandleReportList(RequestReportGoodsHandleVO requestVO, Page page, UserVO userVO) throws Exception {
        Integer sortDate = requestVO.getSortDate();
        Integer sortCode = requestVO.getSortCode();
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "gl.lock_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "gl.lock_date desc,";
        }
        if (sortCode != null && sortCode == 0) {
            sort += "gl.code,";
        }
        if (sortCode != null && sortCode == 1) {
            sort += "gl.code desc,";
        }
        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        requestVO.setSort(sort);

        //判断是总部，还是门店
        if (userVO.getParentId() == 0) {
            requestVO.setType(1);
        } else {
            requestVO.setType(2);
        }
        requestVO.setEnterpriseId(userVO.getEnterpriseId());

        BaseGoodsReportTotalVO<GoodsHandleReportVO> goodsHandleReportTotalVO = new BaseGoodsReportTotalVO();
        //获取总数量
        BigDecimal quantityTotal = goodsSeriesReportMapper.getGoodsHandleReportQuantityTotal(requestVO);
        goodsHandleReportTotalVO.setQuantityTotal(quantityTotal == null?BigDecimal.ZERO:quantityTotal);

        //商品锁定列表
        com.github.pagehelper.Page hPage = null;
        if (requestVO.getPageNo() != null && requestVO.getPageSize() != null) {
            hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        }
        List<GoodsHandleReportVO> goodsHandleReportVOList = goodsSeriesReportMapper.getGoodsHandleReport(requestVO);
        goodsHandleReportTotalVO.setGoodsReportList(goodsHandleReportVOList == null ? new ArrayList<>() : goodsHandleReportVOList);

        page.setResult(goodsHandleReportTotalVO);
        if (hPage != null) {
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return page;
    }

    @Override
    public void excelExport(OutputStream output, BaseGoodsReportTotalVO<GoodsHandleReportVO> goodsHandleReportTotalVO, UserVO userVO) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("药品处理");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (userVO.getParentId() == 0) {
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
        }
        map.put("handleDate", "日期");
        map.put("handleCode", "单号");
        map.put("handleManName", "处理人员");
        map.put("handleResultDesc", "处理结果");
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
        map.put("quantity", "数量");
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
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("quantity");
        purchaseGeneralComponent.commExcelExport(output, map,goodsHandleReportTotalVO == null?null: goodsHandleReportTotalVO.getGoodsReportList(),
                names, null, goodsHandleReportTotalVO == null?null:goodsHandleReportTotalVO.getQuantityTotal().toString(), false, needTotalName);

    }
}
