package com.rograndec.feijiayun.chain.business.purchase.receive.service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherRequestVO;
import com.rograndec.feijiayun.chain.business.purchase.receive.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by madong on 2017/9/13.
 */
public interface PurchaseReceiveService {
    Page getReceive(UserVO loginUser, Integer pageNo, Integer isReceive, Integer pageSize, String orderName,
                    String orderType, String startTime, String endTime, String supplierCode,
                    String supplierName, String code, String receiverName) throws Exception;

    PurchaseOrderRequestVO getOrderDetail(UserVO loginUser, Long id) throws Exception;

    PurchaseOrderOtherRequestVO getOrderSet(UserVO loginUser, Long id) throws Exception;

    PurchaseReceiveRequestVO getReceiveDetail(UserVO loginUser, Long id) throws Exception;

    PurchaseReceiveOtherVO getReceiveSet(UserVO loginUser, Long id) throws Exception;

    String saveReceive(UserVO loginUser, SavePurchaseReceiveVO savePurchaseReceiveVO) throws Exception;

    List<SimpleUserVO> getReceiver(UserVO loginUser) throws Exception;

    List<QualitySetVO> getRefuseReason(UserVO loginUser) throws Exception;

    Map<String,String> getReceiveUnit(UserVO loginUser) throws Exception;

    void exportExcel(OutputStream output, PurchaseReceiveRequestVO purchaseReceiveRequestVO, UserVO loginUser);

    boolean checkReceived(UserVO loginUser, SavePurchaseReceiveVO savePurchaseReceiveVO);

    String getRefuseReasons(String refuseReasonIds, UserVO loginUser) throws Exception;
}
