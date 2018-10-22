package com.rograndec.feijiayun.chain.business.report.storage.service;

import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.GoodsDtlReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.InOutRecordDetailReportPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.InOutRecordDetailReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.RequestParamGoodsDtlReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/26 14:34
 */
public interface GoodsDtlAccountReportService {

   void getGoodsDtlReportList(Page<List<GoodsDtlReportVO>> page, RequestParamGoodsDtlReportVO paramVO, UserVO userVO);

    void exportGoodsDtlReport(OutputStream output, UserVO userVO, RequestParamGoodsDtlReportVO paramVO) throws Exception;

    /**
     * 查看商品明细账
     * @param userVO
     * @param reqParam
     * @param page
     */
    void getInOutRecordDetailReportVOList(UserVO userVO, RequestParamGoodsDtlReportVO reqParam, Page<InOutRecordDetailReportPageVO> page);
}
