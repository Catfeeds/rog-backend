package com.rograndec.feijiayun.chain.business.report.quality.distr.service;

import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.InStorageTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.RequestGetInStorageParamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
public interface DistrInStorageReportService {

    //配进退出出库
    void getInStorageGoodsList(Page<InStorageTotalVO> page, RequestGetInStorageParamVO getReturnParamVO, UserVO userVO);

    void exportInStorageGoodsList(OutputStream output, UserVO userVO, RequestGetInStorageParamVO paramForListVO) throws Exception;

}
