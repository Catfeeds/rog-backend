package com.rograndec.feijiayun.chain.business.report.quality.org.service;

import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface DepartmentReportService {
    List<Department> getDepartMentOrganization(UserVO user);

    void exportExcel(OutputStream output, UserVO user);
}
