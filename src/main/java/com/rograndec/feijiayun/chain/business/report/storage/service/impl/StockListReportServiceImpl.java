package com.rograndec.feijiayun.chain.business.report.storage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.storage.dao.StockListReportMapper;
import com.rograndec.feijiayun.chain.business.report.storage.service.StockListReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListQueryVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchDtlTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchDtlVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockListResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.GoodsManageStatus;
import com.rograndec.feijiayun.chain.common.constant.InventoryStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class StockListReportServiceImpl implements StockListReportService{

	@Autowired
	private StockListReportMapper stockListReportMapper;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectStockListBySum(StockListQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockListResultBranchVO> resultList = null;
		
		Long totalRecord = 0L;
		
		StockListResultBranchTotalVO resultVO = new StockListResultBranchTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){
			
			resultList = stockListReportMapper.selectStockListBySum(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockListReportMapper.queryCountStockListBySum(vo, loginUser.getEnterpriseId(), null);
			
			resultVO = stockListReportMapper.queryStockListBySum(vo, 
					loginUser.getEnterpriseId(), null);
			
			if(resultList != null){
				for (StockListResultBranchVO stockListResultBranchVO : resultList) {
					stockListResultBranchVO.setManageStatusName(GoodsManageStatus.getName(stockListResultBranchVO.getManageStatus()));
				}
			}
		}else {
			
			resultList = stockListReportMapper.selectStockListBySum(vo, null, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockListReportMapper.queryCountStockListBySum(vo, null, loginUser.getEnterpriseId());
			
			resultVO = stockListReportMapper.queryStockListBySum(vo, 
					null, loginUser.getEnterpriseId());
			
			if(resultList != null){
				for (StockListResultBranchVO stockListResultBranchVO : resultList) {
					if(stockListResultBranchVO.getManageStatus() == null){//总部 4-已禁用
						stockListResultBranchVO.setManageStatusName("");
					}else if(4 == stockListResultBranchVO.getManageStatus()){//总部 4-已禁用
						stockListResultBranchVO.setManageStatusName("已禁用");
					}else{
						stockListResultBranchVO.setManageStatusName(GoodsManageStatus.getName(stockListResultBranchVO.getManageStatus()));
					}
				}
			}
		}
		
		
		if(resultVO == null){
			resultVO = new StockListResultBranchTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public Page selectStockDtlListBySum(StockListQueryVO vo, UserVO loginUser) {
		
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else if("shelfName".equals(order)){
			order = "s.shelf_id";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockListResultBranchDtlVO> resultList = null;
		
		Long totalRecord = 0L;
		
		StockListResultBranchDtlTotalVO resultVO = new StockListResultBranchDtlTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){
			
			resultList = stockListReportMapper.selectStockListDtlBySum(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockListReportMapper.queryCountStockListDtlBySum(vo, loginUser.getEnterpriseId(), null);
			
			resultVO = stockListReportMapper.queryStockListDtlBySum(vo, 
					loginUser.getEnterpriseId(), null);
			
			if(resultList != null){
				for (StockListResultBranchDtlVO stockListResultBranchVO : resultList) {
					stockListResultBranchVO.setManageStatusName(GoodsManageStatus.getName(stockListResultBranchVO.getManageStatus()));
					stockListResultBranchVO.setStockStatusName(InventoryStatus.getName(stockListResultBranchVO.getStockStatus()));
				}
			}
		}else {
			
			resultList = stockListReportMapper.selectStockListDtlBySum(vo, null, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockListReportMapper.queryCountStockListDtlBySum(vo, null, loginUser.getEnterpriseId());
			
			resultVO = stockListReportMapper.queryStockListDtlBySum(vo, 
					null, loginUser.getEnterpriseId());
			
			if(resultList != null){
				for (StockListResultBranchDtlVO stockListResultBranchVO : resultList) {
					if(stockListResultBranchVO.getManageStatus() == null){//总部 4-已禁用
						stockListResultBranchVO.setManageStatusName("");
					}else if(4 == stockListResultBranchVO.getManageStatus()){//总部 4-已禁用
						stockListResultBranchVO.setManageStatusName("已禁用");
					}else{
						stockListResultBranchVO.setManageStatusName(GoodsManageStatus.getName(stockListResultBranchVO.getManageStatus()));
					}
					stockListResultBranchVO.setStockStatusName(InventoryStatus.getName(stockListResultBranchVO.getStockStatus()));
				}
			}
			
		}
		
		
		if(resultVO == null){
			resultVO = new StockListResultBranchDtlTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;	
	}

}
