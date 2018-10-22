package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.dao.GoodsSeriesReportMapper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsLockReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsLockReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestReportGoodsLockVO;
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
 * 生成日期：2017/10/19 <br/>
 * 描述：商品锁定报表
 */
@Service
public class GoodsLockReportServiceImpl implements GoodsLockReportService {

    @Autowired
    private GoodsSeriesReportMapper goodsSeriesReportMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page getGoodsLockReportList(RequestReportGoodsLockVO requestVO, Page page, UserVO userVO) throws Exception {
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

        BaseGoodsReportTotalVO<GoodsLockReportVO> goodsLockReportTotalVO = new BaseGoodsReportTotalVO<GoodsLockReportVO>();
        //获取总数量
        BigDecimal quantityTotal = goodsSeriesReportMapper.getGoodsLockReportQuantityTotal(requestVO);
        goodsLockReportTotalVO.setQuantityTotal(quantityTotal == null?BigDecimal.ZERO:quantityTotal);

        //商品锁定列表
        com.github.pagehelper.Page hPage = null;
        if (requestVO.getPageNo() != null && requestVO.getPageSize() != null) {
            hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        }
        List<GoodsLockReportVO> goodsLockReportVOList = goodsSeriesReportMapper.getGoodsLockReport(requestVO);
        goodsLockReportTotalVO.setGoodsReportList(goodsLockReportVOList == null ? new ArrayList<>() : goodsLockReportVOList);

        page.setResult(goodsLockReportTotalVO);
        if (hPage != null) {
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return page;
    }

    @Override
    public void excelExport(OutputStream output, BaseGoodsReportTotalVO<GoodsLockReportVO> goodsLockReportTotalVO, UserVO userVO) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("药品锁定");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (userVO.getParentId() == 0) {
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
        }
        map.put("lockDate", "日期");
        map.put("lockCode", "单号");
        map.put("lockManName", "锁定人员");
        map.put("lockReasonDesc", "锁定原因");
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

        map.put("quantity", "数量");
        map.put("quantity", "数量");
        map.put("quantity", "数量");
        map.put("quantity", "数量");


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
        purchaseGeneralComponent.commExcelExport(output, map, goodsLockReportTotalVO.getGoodsReportList(),
                names, null, goodsLockReportTotalVO.getQuantityTotal().toString(), false, needTotalName);

    }
}
