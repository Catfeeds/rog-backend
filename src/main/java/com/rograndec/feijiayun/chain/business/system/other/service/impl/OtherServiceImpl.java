package com.rograndec.feijiayun.chain.business.system.other.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.system.other.dao.DataBackupMapper;
import com.rograndec.feijiayun.chain.business.system.other.dao.SysLogMapper;
import com.rograndec.feijiayun.chain.business.system.other.entity.SysLog;
import com.rograndec.feijiayun.chain.business.system.other.service.OtherService;
import com.rograndec.feijiayun.chain.business.system.other.vo.DataBackupVO;
import com.rograndec.feijiayun.chain.business.system.other.vo.SysLogParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2017/8/22.
 */
@Service
public class OtherServiceImpl implements OtherService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Autowired
    private DataBackupMapper dataBackupMapper;
    @Override
    public void saveSystemLog(SysLog log) {
        sysLogMapper.insertSelective(log);
    }

    @Override
    public Page<List<SysLog>> getSystemLogList(SysLogParamVO sysLogParamVO) {

        Date start = DateUtils.StringToDate(sysLogParamVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(sysLogParamVO.getEndDate()), 1);

        sysLogParamVO.setStart(start);
        sysLogParamVO.setEnd(end);

        if("loginTime".equals(sysLogParamVO.getOrder())){
            sysLogParamVO.setOrder("login_time");
        }

        Page<List<SysLog>> page = new Page<>(sysLogParamVO.getPageNo(), sysLogParamVO.getPageSize());


        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        List<SysLog> list = sysLogMapper.getSysLogListByParam(sysLogParamVO);

        page.setResult(list);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());

        return page;
    }

    @Override
    public void excelExport(OutputStream output,SysLogParamVO sysLogParamVO) {
        List<SysLog> sysLogList = sysLogMapper.getSysLogListByParam(sysLogParamVO);
        try {
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
                    // 电子表格开始
                    this.beginWorkSheet();
                    this.beginSheet();
                    createRowHeader(this);
                    for (int rowNum = 0; rowNum < sysLogList.size(); rowNum++) {
                        SysLog sysLog = sysLogList.get(rowNum);
                        // 插入新行
                        this.insertRow(rowNum + 1);
                        // 建立新单元格,索引值从0开始,表示第一列
                        int k = 0;
                        this.createCell(k++, DateUtils.getDate(sysLog.getLoginTime()));
                        this.createCell(k++, sysLog.getAccount());
                        this.createCell(k++, sysLog.getEmployeeName());
                        this.createCell(k++, sysLog.getLoginPlace());
                        this.createCell(k++, sysLog.getIp());
                        this.createCell(k, sysLog.getActionName());
                        // 结束行
                        this.endRow();
                    }
                    // 电子表格结束
                    this.endSheet();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DataBackupVO> getDataBackup(Long enterpriseId, Long parentId) {
        return dataBackupMapper.getDataBackUp(enterpriseId,parentId);
    }

    private void createRowHeader(ExcelWriter writer) throws IOException {
        // 插入新行
        writer.insertRow(0);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "登录时间");
        writer.createCell(k++, "账号");
        writer.createCell(k++, "员工");
        writer.createCell(k++, "登录地点");
        writer.createCell(k++, "IP地址");
        writer.createCell(k, "功能");
//        writer.createCell(k, "操作");
        // 结束行
        writer.endRow();
    }
}
