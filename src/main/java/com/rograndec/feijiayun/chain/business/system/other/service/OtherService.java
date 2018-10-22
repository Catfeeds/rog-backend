package com.rograndec.feijiayun.chain.business.system.other.service;

import com.rograndec.feijiayun.chain.business.system.other.entity.DataBackup;
import com.rograndec.feijiayun.chain.business.system.other.entity.SysLog;
import com.rograndec.feijiayun.chain.business.system.other.vo.DataBackupVO;
import com.rograndec.feijiayun.chain.business.system.other.vo.SysLogParamVO;
import com.rograndec.feijiayun.chain.common.Page;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by ST on 2017/8/22.
 */
public interface OtherService {

    void saveSystemLog(SysLog log);

    Page<List<SysLog>> getSystemLogList(SysLogParamVO sysLogParamVO);


    void excelExport(OutputStream output, SysLogParamVO sysLogParamVO);

    List<DataBackupVO> getDataBackup(Long enterpriseId, Long parentId);
}
