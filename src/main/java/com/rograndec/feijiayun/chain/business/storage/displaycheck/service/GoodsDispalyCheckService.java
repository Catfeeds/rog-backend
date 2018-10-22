package com.rograndec.feijiayun.chain.business.storage.displaycheck.service;

import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.GoodsDisplayCheckDetailVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.GoodsDisplayCheckInfoVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.ShowDisplayCheckVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.ShowGoodsDisplayCheckVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface GoodsDispalyCheckService {
    int cancelDisplayCheckOrder(UserVO loginUser, Long id) throws Exception;

    int saveDisplayCheckOrder(UserVO loginUser, GoodsDisplayCheckInfoVO goodsDisplayCheckInfoVO) throws Exception;

    int finishCheckOrder(UserVO loginUser, List<GoodsDisplayCheckDetailVO> goodsDisplayCheckDetailVOS) throws Exception;

    Page<List<ShowGoodsDisplayCheckVO>> getDisplayCheckOrderList(UserVO loginUser, Integer checkOrderType, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, String code, Integer pageNo, Integer pageSize) throws Exception;

//    List<GoodsDisplayCheckDetailVO> getDisplayCheckOrderDtlList(UserVO loginUser, String ids, Integer checkOrderType, Integer maintanceType, Long warehouseAreaId, Integer goodsType) throws Exception;
    List<GoodsDisplayCheckDetailVO> getDisplayCheckOrderDtlList(UserVO loginUser, String ids) throws Exception;

    Page<List<ShowDisplayCheckVO>> getWillCheckOrderList(UserVO loginUser, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, Integer pageNo, Integer pageSize) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, Long id) throws Exception;
}
