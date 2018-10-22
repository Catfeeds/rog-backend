package com.rograndec.feijiayun.chain.business.aftersale.recall.service;

import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallPlanPageParamVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallPlanVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallRecordPageParamVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallRecordVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.AfterSaleChooseGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface RecallService {
    Page<List<RecallPlanVO>> getRecallPlanList(UserVO loginUser, RecallPlanPageParamVO recallPlanPageParamVO) throws Exception;

    Page<List<RecallRecordVO>> getRecallRecordList(UserVO loginUser, RecallRecordPageParamVO recallRecordPageParamVO) throws Exception;

    boolean isDeleteRecallPlan(UserVO loginUser, Long id) throws Exception;

    int deleteRecallPlan(UserVO loginUser, Long id) throws Exception;

    int deleteRecallRecord(UserVO loginUser, Long id) throws Exception;

    RecallPlanVO showRecallPlanDetail(UserVO loginUser, Long id) throws Exception;

    RecallRecordVO showRecallRecordDetail(UserVO loginUser, Long id) throws Exception;

    int saveRecallPlan(UserVO loginUser, RecallPlanVO recallPlanVO) throws Exception;

    int updateRecallPlan(UserVO loginUser, RecallPlanVO recallPlanVO) throws Exception;

    int saveRecallRecord(UserVO loginUser, RecallRecordVO recallRecordVO) throws Exception;

    int updateRecallRecord(UserVO loginUser, RecallRecordVO recallRecordVO) throws Exception;

    List<AfterSaleChooseGoodsVO> getRecordGoodsList(Long id, UserVO loginUser, String param) throws Exception;

    void exportExcel(OutputStream output, Long id, UserVO loginUser, Integer type) throws Exception;
}
