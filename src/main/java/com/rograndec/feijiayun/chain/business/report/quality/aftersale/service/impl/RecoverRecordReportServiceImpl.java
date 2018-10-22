package com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsReportTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.dao.AfterSaleRecordMapper;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.RecoverRecordReportService;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecallRecordReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecallRecordReportReqVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecoverRecordReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.RecoverRecordReportReqVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.*;

@Service
public class RecoverRecordReportServiceImpl implements RecoverRecordReportService{



    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private AfterSaleRecordMapper afterSaleRecordMapper;

    @Override
    public Page getReportPageList(RecoverRecordReportReqVO requestVO, UserVO loginUser) {

        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            requestVO.setType(1);
        }

        if ("date".equals(requestVO.getOrder())) {// 追回日期
            requestVO.setOrder("recover_date");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);

        requestVO.setStart(start);
        requestVO.setEnd(end);

        BaseGoodsReportTotalVO<RecoverRecordReportPageVO> reportTotalVO = new BaseGoodsReportTotalVO<>();

        Page<BaseGoodsReportTotalVO> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());

        // 获取列表
        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        requestVO.setPageStart(page.getStart());
        List<RecoverRecordReportPageVO> dataList =afterSaleRecordMapper.selectRecoverReportPage(requestVO);
        Integer count = afterSaleRecordMapper.selectRecoverReportPageCount(requestVO);

        // 获取总额
        Map<String,Object> totalMap = afterSaleRecordMapper.selectRecoverTotal(requestVO);

        reportTotalVO.setTotal(totalMap);

        reportTotalVO.setGoodsReportList(dataList);
        page.setResult(reportTotalVO);
        page.setTotalRecord(count);

        return page;
    }

    @Override
    public void exportRecover(RecoverRecordReportReqVO requestVO, UserVO loginUser, OutputStream output) throws Exception {


        LinkedHashMap<String, String> map = new LinkedHashMap<>();


        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
            requestVO.setType(1);
        }

        map.put("planDate", "日期");
        map.put("code", "单号");
        map.put("recoverMan", "追回责任人");
        map.put("recoverManPhone", "追回责任人电话");

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
        map.put("recoverReason","追回原因");
        map.put("recoverQuantity","追回数量");
        map.put("handleMeasuresDesc","追回处理");
        map.put("unrecoverQuantity","未追回数量");

        map.put("unrecoverReason","未追回原因");

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

        if ("date".equals(requestVO.getOrder())) {// 追回日期

            requestVO.setOrder("recover_date");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);
        requestVO.setStart(start);
        requestVO.setEnd(end);

        List<RecoverRecordReportPageVO> dataList =afterSaleRecordMapper.selectRecoverReportPage(requestVO);

        // 获取总额
        Map<String,Object> totalMap = afterSaleRecordMapper.selectRecoverTotal(requestVO);

        excelComponent.exportExcel(output, loginUser.getEnterpriseName(), "追回记录", new ArrayList<>(), map, dataList,totalMap);


    }

    @Override
    public Page getRecallPageList(RecallRecordReportReqVO requestVO, UserVO loginUser) {


        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            requestVO.setType(1);
        }

        if ("date".equals(requestVO.getOrder())) {// 追回日期
            requestVO.setOrder("recover_date");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);

        requestVO.setStart(start);
        requestVO.setEnd(end);

        BaseGoodsReportTotalVO<RecallRecordReportPageVO> reportTotalVO = new BaseGoodsReportTotalVO<>();

        Page<BaseGoodsReportTotalVO> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());

        // 获取列表
        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        requestVO.setPageStart(page.getStart());
        List<RecallRecordReportPageVO> dataList =afterSaleRecordMapper.selectRecallReportPage(requestVO);

        Integer count = afterSaleRecordMapper.selectRecallReportPageCount(requestVO);

        // 获取总额
        Map<String,Object> totalMap = afterSaleRecordMapper.selectRecallTotal(requestVO);

        reportTotalVO.setTotal(totalMap);

        reportTotalVO.setGoodsReportList(dataList);
        page.setResult(reportTotalVO);
        page.setTotalRecord(count);


        return page;
    }

    @Override
    public void exportRecall(RecallRecordReportReqVO requestVO, UserVO loginUser, OutputStream output) throws Exception {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();


        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
            requestVO.setType(1);
        }

        map.put("planDate", "日期");
        map.put("code", "单号");
        map.put("recallCompany", "召回单位");
        map.put("recallMan", "召回负责人");

        map.put("recallManPhone","联系电话");
        map.put("recallLevelName","召回级别");

        map.put("recallTimeLimit","召回时限");
        map.put("recallReasonName","召回原因");
        map.put("recallDepositName","召回存放");
        map.put("quantity","召回处理");


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

        map.put("quantity","召回数量");


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

        if ("date".equals(requestVO.getOrder())) {// 追回日期

            requestVO.setOrder("recover_date");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);
        requestVO.setStart(start);
        requestVO.setEnd(end);

        List<RecallRecordReportPageVO> dataList =afterSaleRecordMapper.selectRecallReportPage(requestVO);

        // 获取总额
        Map<String,Object> totalMap = afterSaleRecordMapper.selectRecallTotal(requestVO);

        excelComponent.exportExcel(output, loginUser.getEnterpriseName(), "召回记录", new ArrayList<>(), map, dataList,totalMap);


    }
}
