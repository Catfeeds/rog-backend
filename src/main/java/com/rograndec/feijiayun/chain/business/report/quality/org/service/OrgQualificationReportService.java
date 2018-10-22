package com.rograndec.feijiayun.chain.business.report.quality.org.service;

import com.rograndec.feijiayun.chain.business.index.warning.vo.EnterpriseWarnReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgWarnReportVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/16 17:29
 */
public interface OrgQualificationReportService {

    void getEnterpriseQualificationReportList(Page<List<EnterpriseQualificationReportVO>> page, RequestParamOrgReportVO paramOrgReportVO, UserVO userVO);
    List<EnterpriseQualificationReportVO> getEnterpriseQualificationWarnReportList( RequestParamOrgWarnReportVO paramOrgReportVO, UserVO userVO);

    List<EnterpriseWarnReportVO> getEnterpriseQualification2WarnSet(UserVO userVO, List<WarnSet> enterpriseWarnSets) throws ParseException;

    void export(OutputStream output, UserVO userVO, RequestParamOrgReportVO paramForListVO) throws Exception;

    void exportWarn(OutputStream output, UserVO userVO, RequestParamOrgWarnReportVO paramForListVO) throws Exception;
}
