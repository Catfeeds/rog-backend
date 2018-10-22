package com.rograndec.feijiayun.chain.inf.pos.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.inf.pos.report.dao.POSSaleReportMapper;
import com.rograndec.feijiayun.chain.inf.pos.report.service.POSSaleReportService;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.ReceiptDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.ReceiptVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleCodeTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleCodeVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleDateTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleDateVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleGoodsVO;
import com.rograndec.feijiayun.chain.inf.pos.report.vo.SaleParamVO;

@Service
public class POSSaleReportServiceImpl implements POSSaleReportService{
	
	@Autowired
	private POSSaleReportMapper pOSSaleReportMapper;
	
	@Override
	public SaleDateTotalVO getSaleDate(SaleParamVO paramVO) throws Exception{
		List<SaleDateVO> list = pOSSaleReportMapper.getSaleDate(paramVO);
		SaleDateTotalVO total = pOSSaleReportMapper.sumSaleDate(paramVO);
		if(null != total) {
			total.setList(list);
		}
		return total;
	}

	@Override
	public SaleCodeTotalVO getSaleCode(SaleParamVO paramVO) throws Exception{
		List<SaleCodeVO> list = pOSSaleReportMapper.getSaleCode(paramVO);
		SaleCodeTotalVO total = pOSSaleReportMapper.sumSaleCode(paramVO);
		if(null != total) {
			list.forEach(s->{
				List<SaleGoodsVO> gList = pOSSaleReportMapper.getSaleGoodsBySaleId(s.getId(), paramVO.getEnterpriseId());
				s.setGoodsVO(gList);
			});
			total.setList(list);
		}
		return total;
	}

	@Override
	public SaleGoodsTotalVO getSaleGoods(SaleParamVO paramVO) throws Exception{
		List<SaleGoodsVO> list = pOSSaleReportMapper.getSaleGoods(paramVO);
		SaleGoodsTotalVO total = pOSSaleReportMapper.sumSaleGoods(paramVO);
		if(null != total) {
			total.setList(list);
		}
		return total;
	}

	@Override
	public ReceiptVO getReceiptData(Long saleId, Long enterpriseId) throws Exception {
		ReceiptVO receipt = pOSSaleReportMapper.getReceipt(saleId, enterpriseId);
		if(receipt != null) {
			List<ReceiptDetailVO> list = pOSSaleReportMapper.getReceiptDtl(receipt.getReceiptId(), enterpriseId);
			receipt.setList(list);
		}
		return receipt;
	}

}
