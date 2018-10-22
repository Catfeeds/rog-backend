package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveSaveVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DistrReturnReceiveService {
    Result<Map<String,Object>> saveDistrReturnReceiveOrder(UserVO loginUser, DistrReturnReceiveSaveVO distrReturnReceiveSaveVO) throws Exception;

    Page<List<DistrReturnReceiveVO>> getDistrReturnReceiveOrderList(UserVO loginUser, Date startDate, Date endDate, String requestUnitCode, String requestUnitName, String code, Integer distrType, Integer status, String receiverName, String secondReceiverName, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception;

    DistrReturnReceiveVO getDistrReturnReceiveOrderDtlList(UserVO loginUser, Long receiveId) throws Exception;

    void exportExcel(OutputStream output, Long id, UserVO loginUser);

    DistrReturnReceiveVO showWillSaveList(UserVO loginUser, Long id) throws Exception;

    Boolean isSpecialDrug(UserVO loginUser, Long id) throws Exception;

    boolean checkReceived(UserVO loginUser, DistrReturnReceiveSaveVO distrReturnReceiveSaveVO) throws Exception;
}
