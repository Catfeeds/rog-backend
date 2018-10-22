package com.rograndec.feijiayun.chain.business.report.retail.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.retail.dao.SaleAnalysisReportPayeeMapper;
import com.rograndec.feijiayun.chain.business.report.retail.service.SaleAnalysisReportPayeeService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchDoubleClickResultVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeBranchQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeResultBranchTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.SaleAnalyPayeeResultBranchVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class SaleAnalysisReportPayeeServiceImpl implements SaleAnalysisReportPayeeService{

	@Autowired
	private SaleAnalysisReportPayeeMapper saleAnalysisReportPayeeMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectBranchPayeeSalePageByDailyTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		setBranchQueryVO(vo);
		
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListByDailyTime(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), null, null, null);
		
		Long totalRecord = saleAnalysisReportPayeeMapper.queryCountBranchPayeeSaleListByDailyTime(vo, loginUser.getEnterpriseId(), null);
		
		SaleAnalyPayeeResultBranchTotalVO resultVO = saleAnalysisReportPayeeMapper.querySaleAnalysisReportPayeeDailyTime(vo, 
				loginUser.getEnterpriseId(), null);
		
		if(resultVO == null){
			resultVO = new SaleAnalyPayeeResultBranchTotalVO();
		}
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}
	
	@Override
	public List<SaleAnalyPayeeResultBranchVO> selectBranchPayeeSaleListByDailyTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		setBranchQueryVO(vo);
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListByDailyTime(vo, 
				null, null, loginUser.getEnterpriseId(), null, null, null);
		
		return resultBranchVOList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelBranchPayeeSaleListByDailyTime(OutputStream output,
			List<SaleAnalyPayeeResultBranchVO> resultBranchVOList,
			UserVO loginUser) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("payeeName","收款人员");
        map.put("saleQuantity","销售笔数");
        map.put("realAmount","销售金额");
        map.put("returnQuantity","退货笔数");
        map.put("returnRealAmount","退货金额");
        map.put("realAmountTotal","总额");
        map.put("notaxRealAmountTotal","不含税总额");
        map.put("taxAmountTotal","税额");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("收款人员销售");
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, resultBranchVOList, name, null, "", false, needTotalName);
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectBranchPayeeSalePageBySaleTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		setBranchQueryVO(vo);
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListBySaleTime(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), null, null, null);
		
		Long totalRecord = saleAnalysisReportPayeeMapper.queryCountBranchPayeeSaleListBySaleTime(vo, loginUser.getEnterpriseId(), null);
		
		SaleAnalyPayeeResultBranchTotalVO resultVO = saleAnalysisReportPayeeMapper.querySaleAnalysisReportPayeeSaleTime(vo, 
				loginUser.getEnterpriseId(), null);
		
		if(resultVO == null){
			resultVO = new SaleAnalyPayeeResultBranchTotalVO();
		}
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}
	
	
	@Override
	public List<SaleAnalyPayeeResultBranchVO> selectBranchPayeeSaleListBySaleTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		setBranchQueryVO(vo);
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListBySaleTime(vo, 
				null, null, loginUser.getEnterpriseId(), null, null, null);
		return resultBranchVOList;
	}
	
	
	private void setBranchQueryVO(SaleAnalyPayeeBranchQueryVO vo) {
		vo.setChainType(null);
		vo.setStoreCode(null);
		vo.setStoreName(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectParentPayeeSalePageByDailyTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "storeCode";
		} else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListByDailyTime(vo, 
				page.getStart(), page.getPageSize(), null, loginUser.getEnterpriseId(), order, sort);
		
		Long totalRecord = saleAnalysisReportPayeeMapper.queryCountBranchPayeeSaleListByDailyTime(vo, null, loginUser.getEnterpriseId());
		
		SaleAnalyPayeeResultBranchTotalVO resultVO = saleAnalysisReportPayeeMapper.querySaleAnalysisReportPayeeDailyTime(vo, 
				null, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyPayeeResultBranchTotalVO();
		}
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		return page;
	}

	@Override
	public List<SaleAnalyPayeeResultBranchVO> selectParentPayeeSaleListByDailyTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "storeCode";
		} else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListByDailyTime(vo, 
				null, null, null, loginUser.getEnterpriseId(), order, sort);
		return resultBranchVOList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelParentPayeeSaleListByDailyTime(OutputStream output,
			List<SaleAnalyPayeeResultBranchVO> resultBranchVOList,
			UserVO loginUser) {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("storeCode","门店编码");
        map.put("storeName","门店名称");
        map.put("payeeName","收款人员");
        map.put("saleQuantity","销售笔数");
        map.put("realAmount","销售金额");
        map.put("returnQuantity","退货笔数");
        map.put("returnRealAmount","退货金额");
        map.put("realAmountTotal","总额");
        map.put("notaxRealAmountTotal","不含税总额");
        map.put("taxAmountTotal","税额");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("收款人员销售");
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, resultBranchVOList, name, null, "", false, needTotalName);

		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectParentPayeeSalePageBySaleTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "storeCode";
		} else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListBySaleTime(vo, 
				page.getStart(), page.getPageSize(), null, loginUser.getEnterpriseId(), order, sort);
		
		Long totalRecord = saleAnalysisReportPayeeMapper.queryCountBranchPayeeSaleListBySaleTime(vo, null, loginUser.getEnterpriseId());
		
		SaleAnalyPayeeResultBranchTotalVO resultVO = saleAnalysisReportPayeeMapper.querySaleAnalysisReportPayeeSaleTime(vo, 
				null, loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyPayeeResultBranchTotalVO();
		}
		
		resultVO.setBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public List<SaleAnalyPayeeResultBranchVO> selectParentPayeeSaleListBySaleTime(
			SaleAnalyPayeeBranchQueryVO vo, UserVO loginUser) {
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "storeCode";
		} else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<SaleAnalyPayeeResultBranchVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectBranchPayeeSaleListBySaleTime(vo, 
				null, null, null, loginUser.getEnterpriseId(), order, sort);
		return resultBranchVOList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectDoubleClickPayeeSalePageBySaleDate(
			SaleAnalyPayeeBranchDoubleClickQueryVO vo, UserVO loginUser) {
		
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
		
//		List<SaleAnalyPayeeBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectDoubleClickPayeeSaleListBySaleDate(vo, 
//				page.getStart(), page.getPageSize(), vo.getEnterpriseId(), vo.getPayeeId(), order, sort);
		
		List<SaleAnalyPayeeBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectDoubleClickPayeeSaleListBySaleDate(vo, 
				null, null, vo.getEnterpriseId(), vo.getPayeeId(), order, sort);
		
		Long totalRecord = saleAnalysisReportPayeeMapper.queryCountDoubleClickPayeeSaleListBySaleDate(vo, vo.getEnterpriseId(), vo.getPayeeId());
		
		SaleAnalyPayeeBranchDoubleClickResultTotalVO resultVO = saleAnalysisReportPayeeMapper.queryDoubleClickPayeeSaleListBySaleDate(vo, 
				vo.getEnterpriseId(), vo.getPayeeId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyPayeeBranchDoubleClickResultTotalVO();
		}
		
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectDoubleClickPayeeSalePageByDailyTime(
			SaleAnalyPayeeBranchDoubleClickQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
//		if("saleDate".equals(order)){
			order = "tab1.settle_date";
//		} else{
//			order = null;
//		}
			
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
//		List<SaleAnalyPayeeBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectDoubleClickPayeeSaleListByDailyTime(vo, 
//				page.getStart(), page.getPageSize(), vo.getEnterpriseId(), vo.getPayeeId(), order, sort);
		
		
		List<SaleAnalyPayeeBranchDoubleClickResultVO> resultBranchVOList = saleAnalysisReportPayeeMapper.selectDoubleClickPayeeSaleListByDailyTime(vo, 
				null, null, vo.getEnterpriseId(), vo.getPayeeId(), order, sort);
		
		
		if(resultBranchVOList != null){
			for (SaleAnalyPayeeBranchDoubleClickResultVO resultVO : resultBranchVOList) {
				SaleAnalyPayeeBranchDoubleClickResultVO resultVO1 = saleAnalysisReportPayeeMapper.queryDoubleClickResultVODataByShiftId(resultVO.getId());
				resultVO = setResultVO(resultVO1, resultVO);
			}
		}	
		
		Long totalRecord = saleAnalysisReportPayeeMapper.queryCountDoubleClickPayeeSaleListByDailyTime(vo, vo.getEnterpriseId(), vo.getPayeeId());
		
		SaleAnalyPayeeBranchDoubleClickResultTotalVO resultVO = saleAnalysisReportPayeeMapper.queryDoubleClickPayeeSaleListByDailyTime(vo, 
				vo.getEnterpriseId(), vo.getPayeeId());
		
		if(resultVO == null){
			resultVO = new SaleAnalyPayeeBranchDoubleClickResultTotalVO();
		}
		
		resultVO.setResultBranchVOList(resultBranchVOList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	private SaleAnalyPayeeBranchDoubleClickResultVO setResultVO(
			SaleAnalyPayeeBranchDoubleClickResultVO resultVO1,
			SaleAnalyPayeeBranchDoubleClickResultVO resultVO) {
		if(resultVO1 != null){
			resultVO.setStandCode(resultVO1.getStandCode());
			resultVO.setSaleQuantity(resultVO1.getSaleQuantity());
			resultVO.setRealAmount(resultVO1.getRealAmount());
			resultVO.setReturnQuantity(resultVO1.getReturnQuantity());
			resultVO.setReturnRealAmount(resultVO1.getReturnRealAmount());
			resultVO.setRealAmountTotal(resultVO1.getRealAmountTotal());
			resultVO.setNotaxRealAmountTotal(resultVO1.getNotaxRealAmountTotal());
			resultVO.setTaxAmountTotal(resultVO1.getTaxAmountTotal());
		}
		return resultVO;
	}

}
