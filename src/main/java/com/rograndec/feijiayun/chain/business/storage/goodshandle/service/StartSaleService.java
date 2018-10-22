package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by dudy on 2017/9/27.
 */
public interface StartSaleService {
    Page<List<StartSalePageVO>> selectStartSalePage(SaleOrderRequestVO requestVO, UserVO loginUser);

    StartSaleReturnVO selectDetailById(Long id);

    Result<String> saveStartSale(UserVO userVO, StartSaleRequestVO startSaleRequestVO) throws  Exception;

    void exportExcel(OutputStream output, UserVO userVO, Long id) throws Exception;

    /**
     * 查询锁定状态的货位信息
     * @param map
     * @return
     */
    List<StockDestroyVO> getLockStockList(Map<String, Object> map);
}
