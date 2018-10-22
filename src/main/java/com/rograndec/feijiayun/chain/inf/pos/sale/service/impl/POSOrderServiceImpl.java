package com.rograndec.feijiayun.chain.inf.pos.sale.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.service.POSOrderService;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderParamVO;

@Service
public class POSOrderServiceImpl implements POSOrderService{
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private SaleDetailMapper saleDetailMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectOrderDataByParam(POSOrderParamVO param, UserVO userVO) {
		
		Page page = null;
		
		if(param.getPageNo() == null && param.getPageSize() == null){
			
			page = new Page();
		
			List<POSOrderVO> listVO = saleMapper.selectOrderPageDataByParam(param, null, null, userVO.getEnterpriseId(), 0);
			
			page.setStart(0);
			page.setPageNo(1);
//			page.setPageSize(listVO==null?0:listVO.size());
			page.setResult(listVO);
			page.setTotalRecord(listVO==null?0:listVO.size());
//			page.setTotalPage(listVO==null?0:listVO.size());
		}else{
			
			page = new Page(param.getPageNo(), param.getPageSize());
			
			Long totalRecord = saleMapper.queryOrderCountByParam(param, userVO.getEnterpriseId(), 0);
			
			List<POSOrderVO> listVO = saleMapper.selectOrderPageDataByParam(param, page.getStart(), page.getPageSize(), userVO.getEnterpriseId(), 0);
			
			page.setTotalRecord(totalRecord.intValue());
			
			page.setResult(listVO);
			
		}
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectOrderDeatilDataByParam(POSOrderParamVO param,
			UserVO userVO) {
		Page page = null;
		
		if(param.getPageNo() == null && param.getPageSize() == null){
			
			page = new Page();
		
			List<POSOrderDetailVO> listVO = saleDetailMapper.selectOrderDetailPageDataByParam(param, null, null, userVO.getEnterpriseId(), 0);
			
			page.setStart(0);
			page.setPageNo(1);
//			page.setPageSize(listVO==null?0:listVO.size());
			page.setResult(listVO);
			page.setTotalRecord(listVO==null?0:listVO.size());
//			page.setTotalPage(listVO==null?0:listVO.size());
		}else{
			
			page = new Page(param.getPageNo(), param.getPageSize());
			
			Long totalRecord = saleDetailMapper.queryOrderDetailCountByParam(param, userVO.getEnterpriseId(), 0);
			
			List<POSOrderDetailVO> listVO = saleDetailMapper.selectOrderDetailPageDataByParam(param, page.getStart(), page.getPageSize(), userVO.getEnterpriseId(), 0);
			
			page.setTotalRecord(totalRecord.intValue());
			
			page.setResult(listVO);
			
		}
		return page;
	}

}
