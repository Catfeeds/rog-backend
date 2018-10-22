package com.rograndec.feijiayun.chain.business.distr.branch.service;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInstorageFormVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2ListVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.RequestParamForListVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface DistrInStorageService {

    Page getInstorageHasBeenPage(Page page, Date startTime, Date endTime, String distrUnitCode, String distrUnitName, String code, String storageManName, Integer distrType, String order, String sort, Long enterpriseId);

    DistrInstorageFormVO getInstorageHasBeenDeatil(Long enterpriseId, Long id);

    void export(OutputStream output, DistrInstorageFormVO distrInstorageFormVO, UserVO loginUser);

    void getDistrInCheckList(Page<List<DistrInCheck2ListVO>> page, RequestParamForListVO param, Long enterpriseId);

    DistrInstorageFormVO queryStayInstorageFormByCheckId(Long id, UserVO loginUser);

    List<String> saveStayInstorageForm(DistrInstorageFormVO distrInstorageFormVO, UserVO loginUser) throws Exception;

    List<String> saveInstorage(AddInstorageVO addInstorageVO, UserVO loginUser) throws Exception;


    List<StockLockRecordVO> getShelfByLotNum(Long enterpriseId, Long goodsId, String lotNum, UserVO userVO, Integer distrType);

    List<StockShelfVO> getShelfByLotNum(Long enterpriseId, Long goodsId, String lotNum);

    DistrInstorageVO getInStorageInfoByPlan(Long planId, UserVO userVO);

    DistrInstorageVO getInStorageInfoByNoticeId(Long noticeId, UserVO userVO);

    Page getGoodsByParam(Page page, UserVO userVO, Long supplierId, String param);
}
