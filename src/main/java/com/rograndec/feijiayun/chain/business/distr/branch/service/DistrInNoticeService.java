package com.rograndec.feijiayun.chain.business.distr.branch.service;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInNoticeReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInNotice;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by dudy on 2017/10/7.
 */
public interface DistrInNoticeService {


    /**
     * 获取订单详情
     * @param id
     * @return
     */
    DistrInNoticeVO getDistrInOrderDtlList(Long id,UserVO loginUser);


    /**
     * 获取分页列表
     * @param requestVO
     * @param userVO
     * @return
     */
    Page getDistrInNoticePageList(DistrInNoticeRequestVO requestVO, UserVO userVO) throws Exception;


    Page getDistrNoticeDetailList(String ids,Integer pageNo,Integer pageSize,UserVO userVO);

    void exportDetail(OutputStream output, Long orderId, UserVO loginUser);

	void getDistrInNoticeList(Page<OrderReportVo<DistrInNoticeReportVo>> page, RequestDistrInNotice requestDistrInNotice);

    void excelExportReport(OutputStream output, List<DistrInNoticeReportVo> distrInNoticeReportVos, UserVO userVO);

    void saveDistrInNotice(UserVO loginUser, SaveDistrInNoticeVO saveDistrInNoticeVO) throws Exception;

    List<GoodsInNoticeVO> getGoodsByParam(UserVO userVO,Long supplierId,String param);

    void cancelDistrInNotice(Long id,UserVO loginUser) throws Exception;

    void updateDistrInNotice(UserVO loginUser, SaveDistrInNoticeVO saveDistrInNoticeVO) throws Exception;

    List<DistrReqPlanPageVO> getDistrReqPlanPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String orderName, String orderType);

    List<DistrReqPlanDetailPageVO> getDistrReqPlanDetailPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, Long planId);

    DistrInNoticeVO callDistrReqPlan(Long id, UserVO loginUser) throws Exception;
}
