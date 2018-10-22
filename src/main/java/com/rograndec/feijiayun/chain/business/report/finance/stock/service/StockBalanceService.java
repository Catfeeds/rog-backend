package com.rograndec.feijiayun.chain.business.report.finance.stock.service;

import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceTotalVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 *
 * @ClassName: StockBalanceService
 * @Description: 库存余额
 * @author dongyang.du
 * @version 1.0
 * @date 2018-01-08 11:21:24
 */
public interface StockBalanceService {
    /** 
    * @Description: 库存余额列表
    * @return:  
    * @Author: dongyang.du
    * @Date: 11/01/2018 
    */
    Page<StockBalanceTotalVO> getBalances(UserVO userVO, StockBalanceReqVO balanceReqVO);


    /** 
    * @Description: 导出库存余额
    * @return:  
    * @Author: dongyang.du
    * @Date: 11/01/2018
     * @param: 
    */ 
    void export(OutputStream output, StockBalanceReqVO balanceReqVO, UserVO userVO) throws Exception;

    /** 
    * @Description: 打印
    * @return:  
    * @Author: dongyang.du
    * @Date: 11/01/2018 
    */ 
    StockBalanceTotalVO print(StockBalanceReqVO balanceReqVO, UserVO userVO);
}
