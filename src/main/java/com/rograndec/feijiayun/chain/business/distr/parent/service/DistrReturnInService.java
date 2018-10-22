package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheck;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer.DistrReturnCheckListParam;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * zhaiwei
 */
public interface DistrReturnInService {
    List<DistrReturnCheck> getDistrReturnChecks(DistrReturnCheckListParam distrReturnCheckListParam, Page page);

    DistrReturnCheck4ReturnInVO getDistrReturnCheck4ReturnInVO(Long checkId);

    List<DistrReturnInPageVO> getDistrReturnInPageVOs(DistrReturnCheckListParam distrReturnCheckListParam, Page page);

    DistrReturnInPageVO getDistrReturnInPageVO(Long returnId);

    void saveDistrReturnIn(UserVO userVO, DistrReturnInFormVO distrReturnInFormVO) throws Exception;

    /**
     * 入库前准备数据
     * @return
     */
    DistrReturnInPageVO getDistrReturnInPageVO4Check(UserVO userVO, Long checkId);

    void exportDetail(OutputStream output, Long orderId);

    /**
     * 调用配后退回通知
     * @param page
     * @param enterpriseId
     * @param startTime
     * @param endTime
     * @param order
     *@param sort @return
     */
    Page getDistrReturnNoticeList(Page page, Long enterpriseId, Date startTime, Date endTime, String order, String sort);

    /**
     * 查询配后退回明细
     *
     * @param loginUser
     * @param id
     * @param page
     * @return
     */
    Page<List<DistrReturnNoticeDtlVO>> getDistrReturnNoticeDtlList(UserVO loginUser, Long id, Page page);

    /**
     * 配后退回调用保存
     * @param loginUser
     * @param distrReturnInFromNoticeVO
     */
    List<String> saveDistrReturnInFromNotice(UserVO loginUser, DistrReturnInFromNoticeVO distrReturnInFromNoticeVO) throws Exception;

    /**
     * 一键入库 初始化数据
     * @param loginUser
     * @param id
     * @return
     */
    DistrReturnInFromNoticeVO initReturnInFromNoticeVO(UserVO loginUser, Long id);
}
