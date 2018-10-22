package com.rograndec.feijiayun.chain.business.report.quality.org.service;

import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.commons.beanutils.BeanMap;

import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/16 17:29
 */
public interface OrgReportService {
    void getEnterpriseReportList(Page<List<EnterpriseReportVO>> page, RequestParamOrgReportVO paramOrgReportVO, UserVO userVO);

    List<BeanMap> getEnterpGroup(Long enterpriseId, Integer type);

    void export(OutputStream output, UserVO userVO, RequestParamOrgReportVO paramForListVO) throws Exception;
}
