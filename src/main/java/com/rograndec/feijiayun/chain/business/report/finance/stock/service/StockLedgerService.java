package com.rograndec.feijiayun.chain.business.report.finance.stock.service;

import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.BasePageReqParam;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockLedgerEnterpriseByGoodsVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockLedgerMonthVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
* @Description: 库存总账
* @return:  
* @Author: dongyang.du
* @Date: 14/01/2018 
*/ 
public interface StockLedgerService {
    /** 
    * @Description: 按组织机构获取 库存总账数据
    * @return:  
    * @Author: dongyang.du
    * @Date: 14/01/2018 
    */
    Page<List<StockLedgerMonthVO>> getLedgersByEtps(UserVO loginUser, BasePageReqParam requestVO);

    /** 
    * @Description: 按商品 获取 库存总账数据
    * @return:  
    * @Author: dongyang.du
    * @Date: 14/01/2018 
    */
    Page<List<StockLedgerEnterpriseByGoodsVO>> getLedgersByGoods(UserVO loginUser, StockInOutReqVO requestVO);

    /** 
    * @Description: 按商品导出 库存总账数据
    * @return:  
    * @Author: dongyang.du
    * @Date: 16/01/2018 
    */
    void exportByGoods(OutputStream output, StockInOutReqVO requestVO, UserVO userVO);

    /** 
    * @Description: 按组织机构导出 库存总账数据
    * @return:  
    * @Author: dongyang.du
    * @Date: 16/01/2018 
    */ 
    void exportByEtps(OutputStream output, BasePageReqParam requestVO, UserVO userVO);
}
