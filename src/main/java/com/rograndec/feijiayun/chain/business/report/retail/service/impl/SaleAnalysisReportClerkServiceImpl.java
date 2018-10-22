package com.rograndec.feijiayun.chain.business.report.retail.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.retail.dao.SaleAnalysisReportClerkMapper;
import com.rograndec.feijiayun.chain.business.report.retail.service.SaleAnalysisReportClerkService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickDetailQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchDoubleClickResultVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyClerkResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class SaleAnalysisReportClerkServiceImpl implements SaleAnalysisReportClerkService{

	@Autowired
	private SaleAnalysisReportClerkMapper saleAnalysisReportClerkMapper;
	
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectBranchClerkSalePageByDailyTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		
		setBranchQueryVO(vo);
		
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListByDailyTime(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), null, null, null);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
			
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		
		Long totalRecord = saleAnalysisReportClerkMapper.queryCountBranchClerkSaleListByDailyTime(vo, loginUser.getEnterpriseId(), null);
		
		SaleAnalyClerkResultBranchTotalVO resultVO = saleAnalysisReportClerkMapper.querySaleAnalysisReportClerkDailyTime(vo, 
				loginUser.getEnterpriseId(), null);
		
		if(resultVO == null){
			resultVO = new SaleAnalyClerkResultBranchTotalVO();
		}
		
		Long saleQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndDailyTime(vo,  
				loginUser.getEnterpriseId(), null, 0);
		
		Long returnQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndDailyTime(vo, 
				loginUser.getEnterpriseId(), null, 1);
		
		resultVO.setSaleQuantity(saleQuantity);
		
		resultVO.setReturnQuantity(returnQuantity);
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public List<SaleAnalyClerkResultBranchVO> selectClerkSaleListByDailyTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		
		setBranchQueryVO(vo);
		
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListByDailyTime(vo, 
				null, null, loginUser.getEnterpriseId(), null, null, null);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
			
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		
		return resultBranchVOList;
	}


	@Override
	public void exportExcelForClerkSaleListByDailyTime(OutputStream output,
			List<SaleAnalyClerkResultBranchVO> resultBranchVOList,
			UserVO loginUser) {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("clerkName","营业人员");
        map.put("saleQuantity","销售笔数");
        map.put("realAmount","销售金额");
        map.put("returnQuantity","退货笔数");
        map.put("returnRealAmount","退货金额");
        map.put("realAmountTotal","总额");
        map.put("notaxRealAmountTotal","不含税总额");
        map.put("taxAmountTotal","税额");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("营业人员销售");
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, resultBranchVOList, name, null, "", false, needTotalName);
		
	}


	@Override
	public Page selectBranchClerkSaleListBySaleTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		setBranchQueryVO(vo);
		
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListBySaleTime(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), null, null, null);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
				
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		
		Long totalRecord = saleAnalysisReportClerkMapper.queryCountBranchClerkSaleListBySaleTime(vo, loginUser.getEnterpriseId(), null);
		
		SaleAnalyClerkResultBranchTotalVO resultVO = saleAnalysisReportClerkMapper.querysaleAnalysisReportClerkSaleTime(vo, 
				loginUser.getEnterpriseId(), null);
		
		if(resultVO == null){
			resultVO = new SaleAnalyClerkResultBranchTotalVO();
		}
		
		Long saleQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndSaleTime(vo, 
				loginUser.getEnterpriseId(), null, 0);
		
		Long returnQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndSaleTime(vo, 
				loginUser.getEnterpriseId(), null, 1);
		
		resultVO.setSaleQuantity(saleQuantity);
		
		resultVO.setReturnQuantity(returnQuantity);
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public List<SaleAnalyClerkResultBranchVO> selectClerkSaleListBySaleTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		
		setBranchQueryVO(vo);
		
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListBySaleTime(vo, 
				null, null, loginUser.getEnterpriseId(), null, null, null);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
			
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		return resultBranchVOList;
	}


	@Override
	public Page selectParentClerkSalePageByDailyTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
//		if("saleDate".equals(order)){
			order = "tab1.code";
//		} else{
//			order = null;
//		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListByDailyTime(vo, 
				page.getStart(), page.getPageSize(), null, loginUser.getEnterpriseId(), order, sort);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
			
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		
		Long totalRecord = saleAnalysisReportClerkMapper.queryCountBranchClerkSaleListByDailyTime(vo, null, loginUser.getEnterpriseId());
		
		SaleAnalyClerkResultBranchTotalVO resultVO = saleAnalysisReportClerkMapper.querySaleAnalysisReportClerkDailyTime(vo, 
				null, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyClerkResultBranchTotalVO();
		}
		
		Long saleQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndDailyTime(vo, 
				null, loginUser.getEnterpriseId(), 0);
		
		Long returnQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndDailyTime(vo, 
				null, loginUser.getEnterpriseId(), 1);
		
		resultVO.setSaleQuantity(saleQuantity);
		
		resultVO.setReturnQuantity(returnQuantity);
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public List<SaleAnalyClerkResultBranchVO> selectParentClerkSaleListByDailyTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListByDailyTime(vo, 
				null, null, null, loginUser.getEnterpriseId(), null, null);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndDailyTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
			
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		
		return resultBranchVOList;
	}


	@Override
	public Page selectParentClerkSalePageBySaleTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
//		if("saleDate".equals(order)){
			order = "tab1.code";
//		} else{
//			order = null;
//		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListBySaleTime(vo, 
				page.getStart(), page.getPageSize(), null, loginUser.getEnterpriseId(), order, sort);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
				
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		
		Long totalRecord = saleAnalysisReportClerkMapper.queryCountBranchClerkSaleListBySaleTime(vo, null, loginUser.getEnterpriseId());
		
		SaleAnalyClerkResultBranchTotalVO resultVO = saleAnalysisReportClerkMapper.querysaleAnalysisReportClerkSaleTime(vo, 
				null, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyClerkResultBranchTotalVO();
		}
		
		Long saleQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndSaleTime(vo, 
				null, loginUser.getEnterpriseId(), 0);
		
		Long returnQuantity = saleAnalysisReportClerkMapper.querySaleAllQuantityByClerkNameAndSaleTime(vo, 
				null, loginUser.getEnterpriseId(), 1);
		
		resultVO.setSaleQuantity(saleQuantity);
		
		resultVO.setReturnQuantity(returnQuantity);
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public void exportExcelParentClerkSaleListByDailyTime(OutputStream output,
			List<SaleAnalyClerkResultBranchVO> resultBranchVOList,
			UserVO loginUser) {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("storeCode","门店编码");
        map.put("storeName","门店名称");
        map.put("clerkName","营业人员");
        map.put("saleQuantity","销售笔数");
        map.put("realAmount","销售金额");
        map.put("returnQuantity","退货笔数");
        map.put("returnRealAmount","退货金额");
        map.put("realAmountTotal","总额");
        map.put("notaxRealAmountTotal","不含税总额");
        map.put("taxAmountTotal","税额");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("营业人员销售");
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, resultBranchVOList, name, null, "", false, needTotalName);

		
	}


	
	private void setBranchQueryVO(SaleAnalyClerkBranchQueryVO vo) {
		vo.setChainType(null);
		vo.setStoreCode(null);
		vo.setStoreName(null);
	}


	@Override
	public List<SaleAnalyClerkResultBranchVO> selectParentClerkSaleListBySaleTime(
			SaleAnalyClerkBranchQueryVO vo, UserVO loginUser) {
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		order = "tab1.code";
//		} else{
//			order = null;
//		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleAnalyClerkResultBranchVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectBranchClerkSaleListBySaleTime(vo, 
				null, null, null, loginUser.getEnterpriseId(), order, sort);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkResultBranchVO saleAnalyClerkResultBranchVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkNameAndSaleTime(saleAnalyClerkResultBranchVO.getClerkId(), 
						saleAnalyClerkResultBranchVO.getEnterpriseId(), vo.getStartDate(), vo.getEndDate(), 1);
				
				saleAnalyClerkResultBranchVO.setSaleQuantity(saleQuantity);
				saleAnalyClerkResultBranchVO.setReturnQuantity(returnQuantity);
			}
		}
		return resultBranchVOList;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Page selectDoubleClickClerkSalePageByDailyTime(
			SaleAnalyClerkBranchDoubleClickQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "code";
		}else if("dailyTime".equals(order)){
			order = "daily_time";
		}else if("clerkName".equals(order)){
			order = "clerk_name";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
//		List<SaleAnalyClerkBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectDoubleClickClerkSaleListByDailyTime(vo, 
//				page.getStart(), page.getPageSize(), vo.getEnterpriseId(), vo.getClerkId(), order, sort);
		
		List<SaleAnalyClerkBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectDoubleClickClerkSaleListByDailyTime(vo, 
				null, null, vo.getEnterpriseId(), vo.getClerkId(), order, sort);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkBranchDoubleClickResultVO resultVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkIdAndDailyTime(resultVO.getClerkId(), 
						resultVO.getEnterpriseId(), resultVO.getDailyTime(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkIdAndDailyTime(resultVO.getClerkId(), 
						resultVO.getEnterpriseId(), resultVO.getDailyTime(), 1);
				
				resultVO.setSaleQuantity(saleQuantity);
				resultVO.setReturnQuantity(returnQuantity);
			}
		}
		
		Long totalRecord = saleAnalysisReportClerkMapper.queryCountDoubleClickClerkSaleListByDailyTime(vo, vo.getEnterpriseId(), vo.getClerkId());
		
		SaleAnalyClerkBranchDoubleClickResultTotalVO resultVO = saleAnalysisReportClerkMapper.queryDoubleClickClerkSaleListByDailyTime(vo, 
				vo.getEnterpriseId(), vo.getClerkId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyClerkBranchDoubleClickResultTotalVO();
		}
		
		Long saleQuantity = saleAnalysisReportClerkMapper.queryDoubleClickAllQuantityByDailyTime(vo, 
				vo.getEnterpriseId(), vo.getClerkId(), 0);
		
		Long returnQuantity = saleAnalysisReportClerkMapper.queryDoubleClickAllQuantityByDailyTime(vo, 
				vo.getEnterpriseId(), vo.getClerkId(), 1);
		
		resultVO.setSaleQuantity(saleQuantity);
		
		resultVO.setReturnQuantity(returnQuantity);
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public Page selectDoubleClickClerkSalePageBySaleDate(
			SaleAnalyClerkBranchDoubleClickQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "code";
		}else if("dailyTime".equals(order)){
			order = "sale_date";
		}else if("clerkName".equals(order)){
			order = "clerk_name";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
//		List<SaleAnalyClerkBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectDoubleClickClerkSaleListBySaleDate(vo, 
//				page.getStart(), page.getPageSize(), vo.getEnterpriseId(), vo.getClerkId(), order, sort);
		
		List<SaleAnalyClerkBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportClerkMapper.selectDoubleClickClerkSaleListBySaleDate(vo, 
				null, null, vo.getEnterpriseId(), vo.getClerkId(), order, sort);
		
		if(resultBranchVOList != null){
			for (SaleAnalyClerkBranchDoubleClickResultVO resultVO : resultBranchVOList) {
				Long saleQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkIdAndSaleDate(resultVO.getClerkId(), 
						resultVO.getEnterpriseId(), resultVO.getDailyTime(), 0);
				
				Long returnQuantity = saleAnalysisReportClerkMapper.querySaleQuantityByClerkIdAndSaleDate(resultVO.getClerkId(), 
						resultVO.getEnterpriseId(), resultVO.getDailyTime(), 1);
				
				resultVO.setSaleQuantity(saleQuantity);
				resultVO.setReturnQuantity(returnQuantity);
			}
		}
		
		Long totalRecord = saleAnalysisReportClerkMapper.queryCountDoubleClickClerkSaleListBySaleDate(vo, vo.getEnterpriseId(), vo.getClerkId());
		
		SaleAnalyClerkBranchDoubleClickResultTotalVO resultVO = saleAnalysisReportClerkMapper.queryDoubleClickClerkSaleListBySaleDate(vo, 
				vo.getEnterpriseId(), vo.getClerkId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyClerkBranchDoubleClickResultTotalVO();
		}
		
		Long saleQuantity = saleAnalysisReportClerkMapper.queryDoubleClickAllQuantityBySaleDate(vo, 
				vo.getEnterpriseId(), vo.getClerkId(), 0);
		
		Long returnQuantity = saleAnalysisReportClerkMapper.queryDoubleClickAllQuantityBySaleDate(vo, 
				vo.getEnterpriseId(), vo.getClerkId(), 1);
		
		resultVO.setSaleQuantity(saleQuantity);
		
		resultVO.setReturnQuantity(returnQuantity);
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public Page selectDoubleClickDetailClerkSalePageByDailyTime(
			SaleAnalyClerkBranchDoubleClickDetailQueryVO vo, UserVO loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
}
