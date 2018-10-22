package com.rograndec.feijiayun.chain.business.report.quality.distr.service;

import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.InCheckQuaTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.RequestGetInCheckQuaParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface DistrInCheckQuaReportService {

    //配进退出出库
    void getInCheckQuaGoodsList(Page<InCheckQuaTotalVO> page, RequestGetInCheckQuaParamVO getReturnParamVO, UserVO userVO);

    void exportInCheckQuaGoodsList(OutputStream output, UserVO userVO, RequestGetInCheckQuaParamVO paramForListVO) throws Exception;

}
