package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.SaleOrderRequestVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSalePageVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleRequestVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleReturnVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by dudy on 2017/9/27.
 */
public interface StopSaleService {
    Page<List<StopSalePageVO>> selectStopSalePage(SaleOrderRequestVO requestVO, UserVO loginUser);

    StopSaleReturnVO selectDetailById(Long id);

    Result<String> saveStopSale(UserVO userVO, StopSaleRequestVO stopSaleRequestVO) throws Exception;

    void exportExcel(OutputStream output, UserVO userVO, Long id) throws Exception;
}
