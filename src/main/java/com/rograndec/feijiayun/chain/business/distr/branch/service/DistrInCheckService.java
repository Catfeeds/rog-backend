package com.rograndec.feijiayun.chain.business.distr.branch.service;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2ListVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.RequestParamForListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List; /**
 * 功能描述：
 * Created by ST on 2017/10/10 11:37
 */

public interface DistrInCheckService {
    /**
     * 查询已验收列表
     * @param page
     * @param param
     * @param enterpriseId
     */
    void getDistrInCheckList(Page<List<DistrInCheck2ListVO>> page, RequestParamForListVO param, Long enterpriseId);

    /**
     * 查看验收详情
     * @param userVO
     * @param id
     * @return
     */
    DistrInCheck2DetailVO getInCheckDetail(UserVO userVO, Long id);

    /**
     * 保存验收单
     * @param userVO
     * @param inCheck2DetailVO
     * @return
     */
    int saveDistrInCheck(UserVO userVO, DistrInCheck2DetailVO inCheck2DetailVO) throws Exception;

    /**
     * 验收
     * @param userVO
     * @param id
     * @return
     */
    DistrInCheck2DetailVO getReceiveDetail2Check(UserVO userVO, Long receiveId) throws Exception;

    void export(OutputStream output, UserVO userVO, Long id) throws Exception;
}