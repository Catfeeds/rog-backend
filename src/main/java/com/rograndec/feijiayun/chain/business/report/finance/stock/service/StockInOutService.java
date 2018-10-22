package com.rograndec.feijiayun.chain.business.report.finance.stock.service;

import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutEnterpriseVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * 财务报表-存放管理-出入库明细账
 */
public interface  StockInOutService {
    /**
     * 获取出入库明细账列表
     * @param loginUser
     * @param stockInOutReqVO
     * @return
     */
    Page<List<StockInOutEnterpriseVO>> getInouts(UserVO loginUser, StockInOutReqVO stockInOutReqVO);

    /** 
    * @Description: 库存明细账导出
    * @return:  
    * @Author: dongyang.du
    * @Date: 16/01/2018 
    */ 
    void export(OutputStream output, StockInOutReqVO stockInOutReqVO, UserVO userVO);
}
