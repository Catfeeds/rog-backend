package com.rograndec.feijiayun.chain.business.report.quality.distr.service;

import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut.InReturnOutTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut.RequestGetInReturnOutParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface DistrInReturnOutReportService {
    //配进退出出库
    void getInReturnOutGoodsList(Page<InReturnOutTotalVO> page, RequestGetInReturnOutParamVO getReturnParamVO, UserVO userVO);

    void exportInReturnOutGoodsList(OutputStream output, UserVO userVO, RequestGetInReturnOutParamVO paramForListVO) throws Exception;

}
