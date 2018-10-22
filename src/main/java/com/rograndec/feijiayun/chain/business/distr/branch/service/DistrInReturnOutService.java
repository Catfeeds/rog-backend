package com.rograndec.feijiayun.chain.business.distr.branch.service;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer.DistrInReturnOutSearchParam;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer.DistrInReturnSearchParam;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface DistrInReturnOutService {

    void saveDistrInReturnOut(UserVO userVO, DistrInReturnOutFormVO distrInReturnOutFormVO) throws Exception;

    void save4DistrType(UserVO userVO, DistrInReturnOutAddFormVO distrInReturnOutAddFormVO) throws Exception;

    List<String>  save2Distr(UserVO userVO, DistrInReturnOutAddFormVO distrInReturnOutAddFormVO) throws Exception;

    List<String> audit(UserVO userVO, DistrInReturnOutAuditFormVO distrInReturnOutAuditFormVO) throws Exception;

    List<ResponseDistrInReturnVO>  getDistrReturnInOrderList(DistrInReturnSearchParam distrInReturnOutSearchParam, UserVO userVO, Page page);

    List<DistrInReturnOutPageVO> getDistrInReturnOuts(DistrInReturnOutSearchParam distrInReturnOutSearchParam, UserVO userVO, Page page);

    DistrInReturnOutPageVO getDistrInReturnOutsAndDetails(UserVO userVO, Long outId);


    /**
     * 出库默认信息展示
     */
    DistrInReturnOutPageVO getDistrInReturnOutsAndDetails4Return(UserVO userVO, Long returnId);

    void exportDetail(UserVO userVO, OutputStream output, Long orderId);
}
