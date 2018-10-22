package com.rograndec.feijiayun.chain.business.report.retail.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.retail.dao.SaleFlowReportMapper;
import com.rograndec.feijiayun.chain.business.report.retail.service.SaleFlowReportParentService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryParentVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.SaleGenre;
import com.rograndec.feijiayun.chain.common.constant.SaleMode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class SaleFlowReportParentServiceImpl implements SaleFlowReportParentService{

	@Autowired
	private SaleFlowReportMapper saleFlowReportMapper;
	
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Override
	public Page selectSaleFlowPageByDateForParent(
			SaleFlowListDateQueryParentVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "storeCode";
		} else if("saleDate".equals(order)){
			order = "sale_date";
		} else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListDateResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDateForParent(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListByDateForParent(vo, loginUser.getEnterpriseId());
		
		SaleFlowListDateResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListByDateForParent(vo, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleFlowListDateResultBranchTotalVO();
		}
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public List<SaleFlowListDateResultBranchVO> selectSaleFlowListByDateForParent(
			SaleFlowListDateQueryParentVO vo, UserVO loginUser) {
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "storeCode";
		} else if("saleDate".equals(order)){
			order = "sale_date";
		} else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListDateResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDateForParent(vo, 
				null, null, loginUser.getEnterpriseId(), order, sort);
		return resultBranchVOList;
	}

	@Override
	public void exportExcelSaleFlowListByDateForParent(OutputStream output,
			List<SaleFlowListDateResultBranchVO> resultBranchVOList,
			UserVO loginUser) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("storeCode","门店编码");
		map.put("storeName","门店名称");
        map.put("saleDate","日期");
        map.put("realAmountTotal","金额");
        map.put("notaxRealAmountTotal","不含税金额");
        map.put("taxAmountTotal","税额");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("销售流水");
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, resultBranchVOList, name, null, "", false, needTotalName);

		
	}

	@Override
	public Page selectSaleFlowPageBySumForParent(
			SaleFlowListSumQueryBranchVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		} else if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " s.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListBySumForParent(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListSumResultBranchVO().receveList(resultBranchVOList);
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListBySumForParent(vo, loginUser.getEnterpriseId());
		
		SaleFlowListSumResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListBySumForParent(vo, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleFlowListSumResultBranchTotalVO();
		}
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public List<SaleFlowListSumResultBranchVO> selectSaleFlowListBySumForParent(
			SaleFlowListSumQueryBranchVO vo, UserVO loginUser) {
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		} else if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " s.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListBySumForParent(vo, 
				null, null, loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListSumResultBranchVO().receveList(resultBranchVOList);
		
		return resultBranchVOList;
	}

	@Override
	public void exportExcelSaleFlowListBySumForParent(OutputStream output,
			List<SaleFlowListSumResultBranchVO> resultBranchVOList,
			UserVO loginUser, SaleFlowListSumQueryBranchVO vo) {
		
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("storeCode","门店编码");
		map.put("storeName","门店名称");
		if(vo.getDailySettlementFlag() == 1){
        	map.put("dailyDate","日结日期");
		}
		
        map.put("saleDate","销售时间");
        map.put("saleCode","销售单号");
        map.put("saleTypeName","销售类型");
        map.put("saleModeName","销售模式");
        
        map.put("standCode","款台");
        map.put("teamName","班组");
        map.put("payeeName","收款人员");
        map.put("memberCardCode","会员卡号");
        
        map.put("amountTotal","总计");
        map.put("wholeDiscount","整单折扣");
        map.put("wholeDiscountAmount","抹零");
        map.put("realAmountTotal","总额");
        
        map.put("notaxRealAmountTotal","不含税总额");
        map.put("taxAmountTotal","税额");
        
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("销售流水");
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, resultBranchVOList, name, null, "", false, needTotalName);
		
	}

	@Override
	public Page selectSaleFlowPageByDtlForParent(
			SaleFlowListDtlQueryBranchVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		} else if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " d.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDtlForParent(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListDtlResultBranchVO().receveList(resultBranchVOList);
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListByDtlForParent(vo, loginUser.getEnterpriseId());
		
		SaleFlowListDtlResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListByDtlForParent(vo, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleFlowListDtlResultBranchTotalVO();
		}
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public List<SaleFlowListDtlResultBranchVO> selectSaleFlowListByDtlForBranch(
			SaleFlowListDtlQueryBranchVO vo, UserVO loginUser) {
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		} else if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " d.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDtlForParent(vo, 
				null, null, loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListDtlResultBranchVO().receveList(resultBranchVOList);
		
		return resultBranchVOList;
	}

	@Override
	public void exportExcelSaleFlowListByDtlForBranch(OutputStream output,
			List<SaleFlowListDtlResultBranchVO> resultBranchVOList,
			UserVO loginUser, SaleFlowListDtlQueryBranchVO vo) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		if(vo.getDailySettlementFlag() == 1){
        	map.put("dailyDate","日结日期");
		}
        map.put("saleDate","销售时间");
        map.put("saleCode","销售单号");
        map.put("saleTypeName","销售类型");
        map.put("saleModeName","销售模式");
        
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("quantity","数量");
        map.put("unitPrice","单价");
        
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期");
        
        map.put("lineDiscount","折扣");
        map.put("amount","金额");
        map.put("wholeDiscount","整单折扣");
        map.put("lineDiscountAmount","分摊抹零金额");
        
        map.put("realPrice","实际单价");
        map.put("realAmount","总额");
        map.put("notaxRealAmount","不含税总额");
        map.put("taxAmount","税额");
        
        map.put("standCode","款台");
        map.put("teamName","班组");
        map.put("payeeName","收款人员");
        map.put("clerkName","营业员名称");
        map.put("memberCardCode","会员卡号");
        
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("销售流水");
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, resultBranchVOList, name, null, "", false, needTotalName);

		
	}

}
