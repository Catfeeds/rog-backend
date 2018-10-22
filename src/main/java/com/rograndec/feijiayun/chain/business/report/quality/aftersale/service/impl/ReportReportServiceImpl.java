package com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.dao.AdverseReactionReportMapper;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportPageVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportRequestVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.ReportReportService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ReportReportServiceImpl implements ReportReportService {

    @Autowired
    private AdverseReactionReportMapper reportMapper;

    @Autowired
    private ExcelComponent<ReportPageVO> excelComponent;


    @Override
    public Page getReportPageList(ReportRequestVO requestVO, UserVO loginUser) {
        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            requestVO.setType(1);
        }

        if ("date".equals(requestVO.getOrder())) {
            requestVO.setOrder("report_time");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);

        requestVO.setStart(start);
        requestVO.setEnd(end);

        Page<List<ReportPageVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());

       // com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        requestVO.setPageStart(page.getStart());

        List<ReportPageVO> dataList = reportMapper.selectReportReportPage(requestVO);

        Integer count = reportMapper.selectReportReportCount(requestVO);

        page.setResult(dataList);
        page.setTotalRecord(count);

        return page;
    }

    @Override
    public void export(ReportRequestVO requestVO, UserVO loginUser, OutputStream output) throws Exception {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
            requestVO.setType(1);
        }

        map.put("reportTime", "日期");
        map.put("code", "单号");
        map.put("reportManName", "报告人员");
        //
        map.put("firstReportName", "是否首次报告");
        map.put("reportTypeName", "报告类型");
        map.put("reportUnitTypeName", "报告单位类别");
        map.put("reportUnitName", "报告单位");



        if ("date".equals(requestVO.getOrder())) {
            requestVO.setOrder("report_time");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);

        requestVO.setStart(start);
        requestVO.setEnd(end);

        List<ReportPageVO> dataList = reportMapper.selectReportReportPage(requestVO);



        excelComponent.exportExcel(output, loginUser.getEnterpriseName(), "投诉管理", new ArrayList<>(), map, dataList,null);

    }
}
