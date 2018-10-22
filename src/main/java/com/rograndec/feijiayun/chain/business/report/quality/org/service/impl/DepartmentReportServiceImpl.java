package com.rograndec.feijiayun.chain.business.report.quality.org.service.impl;

import com.rograndec.feijiayun.chain.business.report.quality.org.service.DepartmentReportService;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DepartmentReportServiceImpl implements DepartmentReportService {


    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public List<Department> getDepartMentOrganization(UserVO user) {

        Long eId = user.getEnterpriseId();
        //加盟店只检索系统默认和本企业的信息
        if(user.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            eId = user.getParentId();
        }

        List<Department> list = departmentMapper.selectByEnterpriseId2Report(eId);
        return list;
    }


    /**
     * 导出
     *
     * @param output
     */
    @Override
    public void exportExcel(OutputStream output, UserVO user) {

        List<Department> depts = getDepartMentOrganization(user);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());//企业
        List<String> headerList = new ArrayList<>();
        headerList.add(enterprise.getName());
        headerList.add("部门");


        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
        rowHeaderMap.put("name","部门");
        rowHeaderMap.put("remark","职责");

        StringBuilder endString = new StringBuilder();

        // 单元格合并
        // 四个参数分别是：起始行，起始列，结束行，结束列
//        CellRangeAddress r1 = new CellRangeAddress(0, (short) 0, 0,
//                (short) (rowHeaderMap.size()-1));
//
//        CellRangeAddress r2 = new CellRangeAddress(1, (short) 1, 1,
//                (short) (rowHeaderMap.size()-1));

        purchaseGeneralComponent.commExcelExport(
                output
                ,rowHeaderMap
                ,depts
                ,headerList
                ,new ArrayList<>()
                ,endString.toString()
                ,true
                ,new ArrayList<>()
        );
    }
}
