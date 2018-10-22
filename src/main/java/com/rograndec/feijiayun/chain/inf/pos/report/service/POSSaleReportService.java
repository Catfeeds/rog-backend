package com.rograndec.feijiayun.chain.inf.pos.report.service;

import com.rograndec.feijiayun.chain.inf.pos.report.vo.ReceiptVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleCodeTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleDateTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleParamVO;

public interface POSSaleReportService {
	
	/**
	 * 
	 * @Description: 按销售日期
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月26日 下午5:00:52 
	 * @param paramVO
	 * @return 
	 * @return SaleDateTotalVO
	 */
	SaleDateTotalVO getSaleDate(SaleParamVO paramVO) throws Exception;
	
	/**
	 * 
	 * @Description: 按单据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月26日 下午5:01:05 
	 * @param paramVO
	 * @return 
	 * @return SaleCodeTotalVO
	 */
	SaleCodeTotalVO getSaleCode(SaleParamVO paramVO) throws Exception;
	
	/**
	 * 
	 * @Description: 按明细
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月26日 下午5:01:15 
	 * @param paramVO
	 * @return 
	 * @return SaleGoodsTotalVO
	 */
	SaleGoodsTotalVO getSaleGoods(SaleParamVO paramVO) throws Exception;
	
	/**
	 * 
	 * @Description: 获取收款数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月18日 上午11:30:09 
	 * @param saleId
	 * @param enterpriseId
	 * @return
	 * @throws Exception 
	 * @return ReceiptVO
	 */
	ReceiptVO getReceiptData(Long saleId,Long enterpriseId)throws Exception;
	
}
