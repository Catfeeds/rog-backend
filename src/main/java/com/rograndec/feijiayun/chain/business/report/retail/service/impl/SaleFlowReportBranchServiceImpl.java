package com.rograndec.feijiayun.chain.business.report.retail.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.rograndec.feijiayun.chain.common.constant.YesAndNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.retail.dao.SaleFlowReportMapper;
import com.rograndec.feijiayun.chain.business.report.retail.service.SaleFlowReportBranchService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDateResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQueryDtlVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDoubleClickQuerySumVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListDtlResultBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumQueryBranchVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleFlowListSumResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class SaleFlowReportBranchServiceImpl implements SaleFlowReportBranchService{

	@Autowired
	private SaleFlowReportMapper saleFlowReportMapper;
	
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectSaleFlowPageByDateForBranch(
			SaleFlowListDateQueryBranchVO vo, UserVO loginUser) {
		
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
//		if("saleDate".equals(order)){
			order = "sale_date";
//		} else{
//			order = null;
//		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListDateResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDateForBranch(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListByDateForBranch(vo, loginUser.getEnterpriseId());
		
		SaleFlowListDateResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListByDateForBranch(vo, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleFlowListDateResultBranchTotalVO();
		}
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public List<SaleFlowListDateResultBranchVO> selectSaleFlowListByDateForBranch(
			SaleFlowListDateQueryBranchVO vo, UserVO loginUser) {
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
//		if("saleDate".equals(order)){
			order = "sale_date";
//		} else{
//			order = "id";
//		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListDateResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDateForBranch(vo, 
				null, null, loginUser.getEnterpriseId(), order, sort);
		
		return resultBranchVOList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelSaleFlowListByDateForBranch(OutputStream output,
			List<SaleFlowListDateResultBranchVO> resultBranchVOList,
			UserVO loginUser) {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectSaleFlowPageBySumForBranch(
			SaleFlowListSumQueryBranchVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " s.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListBySumForBranch(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListSumResultBranchVO().receveList(resultBranchVOList);
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListBySumForBranch(vo, loginUser.getEnterpriseId());
		
		SaleFlowListSumResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListBySumForBranch(vo, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleFlowListSumResultBranchTotalVO();
		}
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public List<SaleFlowListSumResultBranchVO> selectSaleFlowListBySumForBranch(
			SaleFlowListSumQueryBranchVO vo, UserVO loginUser) {

		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " s.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListBySumForBranch(vo, 
				null, null, loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListSumResultBranchVO().receveList(resultBranchVOList);
		
		return resultBranchVOList;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelSaleFlowListBySumForBranch(OutputStream output,
			List<SaleFlowListSumResultBranchVO> resultBranchVOList,
			UserVO loginUser, SaleFlowListSumQueryBranchVO vo) {
		
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
//      if(vo.getDailySettlementFlag() == 1){
//        	map.put("dailyDate","日期");
//      }
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectSaleFlowPageByDtlForBranch(
			SaleFlowListDtlQueryBranchVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " d.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDtlForBranch(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListDtlResultBranchVO().receveList(resultBranchVOList);
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListByDtlForBranch(vo, loginUser.getEnterpriseId());
		
		SaleFlowListDtlResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListByDtlForBranch(vo, loginUser.getEnterpriseId());
		
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
		
		if("dailyDate".equals(order)){
			order = " s.daily_time";
		} else if("saleDate".equals(order)){
			order = " s.sale_date";
		}else {
			order = " d.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListByDtlForBranch(vo, 
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

	@Override
	public Page selectSaleFlowListDoubleClickBySum(
			SaleFlowListDoubleClickQuerySumVO vo, UserVO loginUser) {
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
		Long enterpriseId = (loginUser.getParentId()==null||loginUser.getParentId()==0)?vo.getEnterpriseId():loginUser.getEnterpriseId();
//		List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListDoubleClickBySum(vo, 
//				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		List<SaleFlowListSumResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListDoubleClickBySum(vo, 
				null, null, enterpriseId, order, sort);
		
		new SaleFlowListSumResultBranchVO().receveList(resultBranchVOList);
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListDoubleClickBySum(vo, enterpriseId);
		
		SaleFlowListSumResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListDoubleClickBySum(vo, enterpriseId);
		
		if(resultVO == null){
			resultVO = new SaleFlowListSumResultBranchTotalVO();
		}
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
		public Page selectSaleFlowListDoubleClickByDtl(
			SaleFlowListDoubleClickQueryDtlVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "o.code";
		} else if("dailyDate".equals(order)){
			order = " o.daily_time";
		} else if("saleDate".equals(order)){
			order = " o.sale_date";
		}else {
			order = " d.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
//		List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListDoubleClickByDtl(vo, 
//				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		List<SaleFlowListDtlResultBranchVO> resultBranchVOList = saleFlowReportMapper.selectSaleFlowListDoubleClickByDtl(vo, 
				null, null, loginUser.getEnterpriseId(), order, sort);
		
		new SaleFlowListDtlResultBranchVO().receveList(resultBranchVOList);
		Long parentId = loginUser.getEnterpriseId();
		if(!loginUser.getChainType().equals(ChainType.Headquarters.getCode())) parentId = loginUser.getParentId();
		for(SaleFlowListDtlResultBranchVO dtl:resultBranchVOList) {
			//若不是总部的商品则是加盟店的商品
			if(!parentId.equals(dtl.getEnterpriseId())) {
				dtl.setChainType(ChainType.Division.getCode());
				dtl.setFranchisedStoreFlag(YesAndNo.YES.getCode());
			}
		}
		
		Long totalRecord = saleFlowReportMapper.queryCountSaleFlowListDoubleClickByDtl(vo, loginUser.getEnterpriseId());
		
		SaleFlowListDtlResultBranchTotalVO resultVO = saleFlowReportMapper.querySaleFlowListDoubleClickByDtl(vo, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleFlowListDtlResultBranchTotalVO();
		}
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

}
