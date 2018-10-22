package com.rograndec.feijiayun.chain.business.report.quality.org.service;

import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/16 17:29
 */
public interface SubOrgQualificationReportService {

    void getEnterpriseQualificationReportList(Page<List<QualificationConfigVO>> page, RequestParamOrgReportVO paramOrgReportVO, UserVO userVO);
    List<QualificationConfigVO> getEnterpriseQualificationWarnReportList(UserVO userVO);

    void export(OutputStream output, UserVO userVO) throws Exception;

    void exportWarn(OutputStream output, UserVO userVO) throws Exception;
}
