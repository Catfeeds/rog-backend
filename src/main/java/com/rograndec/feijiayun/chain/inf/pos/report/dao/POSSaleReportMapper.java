package com.rograndec.feijiayun.chain.inf.pos.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.inf.pos.report.vo.ReceiptDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.ReceiptVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleCodeTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleCodeVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleDateTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleDateVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleGoodsVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleParamVO;

/**
 * 
 * @ClassName: POSSaleReportMapper   
 * @Description: POS销售报表
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月26日 下午3:51:04
 */
public interface POSSaleReportMapper {
	
	/**
	 * 
	 * @Description: 按销售日期
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月26日 下午3:53:00 
	 * @param paramVO
	 * @return 
	 * @return List<SaleDateVO>
	 */
	List<SaleDateVO> getSaleDate(SaleParamVO paramVO);
	SaleDateTotalVO sumSaleDate(SaleParamVO paramVO);
	
	/**
	 * 
	 * @Description: 按单据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月26日 下午3:54:31 
	 * @param paramVO
	 * @return 
	 * @return List<SaleCodeVO>
	 */
	List<SaleCodeVO> getSaleCode(SaleParamVO paramVO);
	SaleCodeTotalVO sumSaleCode(SaleParamVO paramVO);
	
	/**
	 * 
	 * @Description: 按日期
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月26日 下午3:54:43 
	 * @param paramVO
	 * @return 
	 * @return List<SaleGoodsVO>
	 */
	List<SaleGoodsVO> getSaleGoods(SaleParamVO paramVO);
	SaleGoodsTotalVO sumSaleGoods(SaleParamVO paramVO);
	
	/**
	 * 
	 * @Description: 获取销售单细表数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月12日 上午11:01:46 
	 * @param saleId
	 * @param enterpriseId
	 * @return 
	 * @return List<SaleGoodsVO>
	 */
	List<SaleGoodsVO> getSaleGoodsBySaleId(@Param("saleId")Long saleId,@Param("enterpriseId")Long enterpriseId);
	
	/**
	 * 
	 * @Description: 获取收款方式
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月18日 上午11:14:52 
	 * @param saleId
	 * @param saleCode
	 * @param enterpriseId
	 * @return 
	 * @return ReceiptVO
	 */
	ReceiptVO getReceipt(@Param("saleId")Long saleId,@Param("enterpriseId")Long enterpriseId);
	List<ReceiptDetailVO> getReceiptDtl(@Param("receiptId")Long receiptId,@Param("enterpriseId")Long enterpriseId);

}
