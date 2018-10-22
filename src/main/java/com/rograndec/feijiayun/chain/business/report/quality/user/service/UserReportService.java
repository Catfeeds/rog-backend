package com.rograndec.feijiayun.chain.business.report.quality.user.service;

import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserLicenseExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserReportExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserWarningExcelVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * @author sulei
 */
public interface UserReportService {

    Page getUserReportPage(Page page, String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, Long educationId, Long majorId, Long useNature, Integer status, String order, String sort, UserVO loginUser);

    List<UserReportExcelVO> getExcelForm(String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, Long educationId, Long majorId, Long useNature, Integer status, String order, String sort, UserVO loginUser);

    void export(OutputStream output, List<UserReportExcelVO> userReportExcelVO, UserVO loginUser);

    Page getUserLicensePage(Page page, String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, String order, String sort, UserVO loginUser);

    List<UserLicenseExcelVO> getUserLicenseExcelForm(String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, String order, String sort, UserVO loginUser);

    void exportLicense(OutputStream output, List<UserLicenseExcelVO> userLicenseExcelVO, UserVO loginUser);

    Page getUserWarningPage(Page page, String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, String order, String sort, UserVO loginUser);

   /* List<UserWarningPageVO> getUserWarningPage(String order, String sort, UserVO loginUser);*/

    /*
    list = userMapper.selectSonUserWarningByMap(map);
    list = getUserWarningContext(list);
    if (list != null && list.size() > 0) {
        for (UserWarningPageVO vo : list) {
            vo = addDeptAndPositionWarningDetail(vo);
        }
    }
}
return list;
}*/
    List<UserWarningExcelVO> getUserWarningExcelForm(String order, String sort, UserVO loginUser);

    List<UserWarningExcelVO> getUserWarning2WarinSet(UserVO loginUser, List<WarnSet> userWarnSets);

    List<UserWarningExcelVO> getUserWarningExcelForm(String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, String order, String sort, UserVO loginUser);

    void exportWarning(OutputStream output, List<UserWarningExcelVO> userWarningExcelVO, UserVO loginUser);
}
