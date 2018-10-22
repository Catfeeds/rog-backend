package com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.impl;

import com.rograndec.feijiayun.chain.business.aftersale.complain.dao.ComplainMapper;
import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.ComplainPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.service.ComplainReportService;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.ComplainReqVO;
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
public class ComplainReportServiceImpl implements ComplainReportService {


    @Autowired
    private ComplainMapper complainMapper;

    @Autowired
    private ExcelComponent<ComplainPageVO> excelComponent;


    @Override
    public Page getPageList(ComplainReqVO requestVO, UserVO loginUser) {


        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            requestVO.setType(1);
        }

        if ("date".equals(requestVO.getOrder())) {
            requestVO.setOrder("complain_date");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);

        requestVO.setStart(start);
        requestVO.setEnd(end);

        Page<List<ComplainPageVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());

        requestVO.setPageStart(page.getStart());
        List<ComplainPageVO> dataList = complainMapper.selectReportPage(requestVO);
        Integer count = complainMapper.selectReportPageCount(requestVO);

        page.setResult(dataList);
        page.setTotalRecord(count);

        return page;
    }

    @Override
    public void export(ComplainReqVO requestVO, UserVO loginUser, OutputStream output) throws Exception {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        requestVO.setEnterpriseId(loginUser.getEnterpriseId());
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {// 不是总部的
            requestVO.setType(2);
        } else {// 总部
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
            requestVO.setType(1);
        }

        map.put("complainDateStr","投诉日期");
        map.put("code","单号");
        map.put("acceptManName","受理人员");
        map.put("channelName","投诉渠道");
        map.put("name","投诉人员");
        map.put("content","投诉内容");

        if ("date".equals(requestVO.getOrder())) {
            requestVO.setOrder("report_time");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);

        requestVO.setStart(start);
        requestVO.setEnd(end);
        List<ComplainPageVO> dataList = complainMapper.selectReportPage(requestVO);


        excelComponent.exportExcel(output, loginUser.getEnterpriseName(), "投诉管理", new ArrayList<>(), map, dataList,null);

    }
}
