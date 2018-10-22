package com.rograndec.feijiayun.chain.business.goods.pharmacy.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.PharmacyDictionaryService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.GoodsDictionaryVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
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
 * 生成日期：2017/10/19 <br/>
 * 描述：药品词典、选药知道
 */
@Service
public class PharmacyDictionaryServiceImpl implements PharmacyDictionaryService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Override
    public Page getPharmacyDictionaryPage(Page page, String param, Integer sort, Long enterpiseId) {
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<GoodsDictionaryVO> goodsList = getGoodsList(param, sort, enterpiseId);
        page.setResult(goodsList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public void excelExportDictionary(OutputStream outPut, String param, Integer sort, UserVO userVO) throws Exception {
        List<GoodsDictionaryVO> goodsList = getGoodsList(param, sort, userVO.getEnterpriseId());
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "药品词典";

        LinkedHashMap<String, String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("code", "商品编码");
        headerHashMap.put("barcode", "条形码");
        headerHashMap.put("genericName", "通用名称");
        headerHashMap.put("name", "商品名称");
        headerHashMap.put("dosageName", "剂型");
        headerHashMap.put("specification", "规格");
        headerHashMap.put("unitName", "单位");
        headerHashMap.put("manufacturer", "生产厂商");
        headerHashMap.put("place", "产地");
        headerHashMap.put("approvalNumber", "批准文号");
        headerHashMap.put("goodsAttributeName", "商品属性");
        headerHashMap.put("domesticImportDesc", "国产/进口");
        headerHashMap.put("specialDrugName", "特殊管理药品");
        headerHashMap.put("inChargeDrugName", "专管药品");
        headerHashMap.put("limitQuantity", "限购数量");
        headerHashMap.put("storageTempDesc", "储藏温度");
        headerHashMap.put("storageConditionName", "储藏条件");
        headerHashMap.put("qualityPeriodDesc", "保质期");
        headerHashMap.put("registeredTrademark", "注册商标");
        headerHashMap.put("brand", "品牌");

        excelComponent.exportExcel(outPut, firstTitle, secondTitle, new ArrayList<>(), headerHashMap, goodsList,null);
    }

    @Override
    public void excelExportSelectGuide(OutputStream outPut, String param, Integer sort, UserVO userVO) throws Exception {
        List<GoodsDictionaryVO> goodsList = getGoodsList(param, sort, userVO.getEnterpriseId());
        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "药品词典";

        LinkedHashMap<String, String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("code", "商品编码");
        headerHashMap.put("barcode", "条形码");
        headerHashMap.put("genericName", "通用名称");
        headerHashMap.put("name", "商品名称");
        headerHashMap.put("dosageName", "剂型");
        headerHashMap.put("specification", "规格");
        headerHashMap.put("unitName", "单位");
        headerHashMap.put("manufacturer", "生产厂商");
        headerHashMap.put("place", "产地");
        headerHashMap.put("approvalNumber", "批准文号");

        excelComponent.exportExcel(outPut, firstTitle, secondTitle, new ArrayList<>(), headerHashMap, goodsList,null);
    }

    /**
     * 获取商品列表
     * @param param
     * @param sort
     * @param enterpiseId
     * @return
     */
    private List<GoodsDictionaryVO> getGoodsList(String param, Integer sort, Long enterpiseId){
        String sortStr = "";
        if (sort == null) {
            sortStr = "";
        } else {
            if (sort == 0) {
                sortStr += "code";
            } else if (sort == 1) {
                sortStr += "code desc";
            }
        }
        List<GoodsDictionaryVO> goodsList = goodsMapper.getGoodsDictionaryList(enterpiseId, param, sortStr);
        return goodsList == null ? new ArrayList<>() : goodsList;
    }
}
