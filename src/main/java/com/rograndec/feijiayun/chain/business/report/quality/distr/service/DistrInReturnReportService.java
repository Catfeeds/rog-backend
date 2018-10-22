package com.rograndec.feijiayun.chain.business.report.quality.distr.service;

import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturn.InReturnTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturn.RequestGetInReturnParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface DistrInReturnReportService {

    //配进退出出库
    void getInReturnGoodsList(Page<InReturnTotalVO> page, RequestGetInReturnParamVO getReturnParamVO, UserVO userVO);

    void exportInReturnGoodsList(OutputStream output, UserVO userVO, RequestGetInReturnParamVO paramForListVO) throws Exception;

}
