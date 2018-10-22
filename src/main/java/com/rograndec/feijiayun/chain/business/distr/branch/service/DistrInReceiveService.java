package com.rograndec.feijiayun.chain.business.distr.branch.service;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveSaveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInReceiveReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInReceive;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface DistrInReceiveService {
    String saveDistrInReceiveOrder(UserVO loginUser, DistrInReceiveSaveVO distrInReceiveSaveVO) throws Exception;

    DistrInReceiveVO getDistrInReceiveOrderDtlList(UserVO loginUser, Long id) throws Exception;

    Page<List<DistrInReceiveVO>> getDistrInReceiveOrderList(UserVO loginUser, Date startDate, Date endDate, String distrUnitCode, String distrUnitName, String code, Integer distrType, Integer status, String receiverName, String secondReceiverName, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception;

    void exportExcel(OutputStream output, Long id, UserVO loginUser)  throws Exception;

    DistrInReceiveVO showWillSaveList(UserVO loginUser, Long id) throws Exception;

	void getDistrInReceiveList(Page<OrderReportVo<DistrInReceiveReportVo>> page, RequestDistrInReceive requestDistrInReceive);

    void excelExportReport(OutputStream output, List<DistrInReceiveReportVo> distrInReceiveReportVos, UserVO userVO);

    boolean checkReceived(UserVO loginUser, DistrInReceiveSaveVO distrInReceiveSaveVO) throws Exception;
}
