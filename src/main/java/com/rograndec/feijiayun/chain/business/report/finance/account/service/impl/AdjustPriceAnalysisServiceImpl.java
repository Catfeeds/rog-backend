package com.rograndec.feijiayun.chain.business.report.finance.account.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.finance.account.dao.AdjustPriceAnalysisMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.service.AdjustPriceAnalysisService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceDetail;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceTotalVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.AdjustPriceReportExcelComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * 
 * @ClassName: AdjustPriceAnalysisServiceImpl   
 * @Description: 应收/应付调价分析
 * @author dongdong.zhang
 * @version 1.0 
 * @date 2018年1月12日 下午15:33:43
 */
@Service
public class AdjustPriceAnalysisServiceImpl implements AdjustPriceAnalysisService{
	
	@Autowired
	AdjustPriceAnalysisMapper adjustPriceAnalysisMapper;
	@SuppressWarnings("rawtypes")
	@Autowired
	AdjustPriceReportExcelComponent adjustPriceReportExcelComponent;

	@Override
	public AdjustPriceTotalVO getPayAdjustList(UserVO userVO, Page<AdjustPriceTotalVO> page, Map<String, Object> map, Integer type,
			Integer sign) {
		validOrderByParams(map);
		AdjustPriceTotalVO adjustPriceTotalVO = new AdjustPriceTotalVO();
		adjustPriceTotalVO.setList(new ArrayList<>());
		if(type == null) {
			Integer totalRecord = adjustPriceAnalysisMapper.getAdjustPriceTotalRecord(map);
			page.setTotalRecord(totalRecord);
			if(totalRecord != null && totalRecord != 0) {
				List<AdjustPriceTotalVO> adjustPriceTotalVOs = adjustPriceAnalysisMapper.getAdjustPriceListTotal(map);
				setTotalInfo(adjustPriceTotalVO, adjustPriceTotalVOs);
				List<AdjustPriceVO> resList = adjustPriceAnalysisMapper.queryAdjustPriceList(map);
				setListTotalInfo(resList);
				adjustPriceTotalVO.setList(resList);
				page.setResult(adjustPriceTotalVO);
			}else {
				setTotalInfo(adjustPriceTotalVO, null);
				page.setResult(adjustPriceTotalVO);
			}
		}else {
			validType(type, sign);
			Integer totalRecord = adjustPriceAnalysisMapper.getAdjustPriceTotalRecordByType(map);
			page.setTotalRecord(totalRecord);
			if(totalRecord != null && totalRecord != 0) {
				List<AdjustPriceTotalVO> adjustPriceTotalVOs = adjustPriceAnalysisMapper.getAdjustPriceListByTypeTotal(map);
				if(!adjustPriceTotalVOs.isEmpty()) {
					setTotalInfo(adjustPriceTotalVO, adjustPriceTotalVOs);
					List<AdjustPriceVO> resList = adjustPriceAnalysisMapper.queryAdjustPriceListByType(map);
					setListTotalInfo(resList);
					adjustPriceTotalVO.setList(resList);
				}
				page.setResult(adjustPriceTotalVO);
			}else {
				setTotalInfo(adjustPriceTotalVO, null);
				page.setResult(adjustPriceTotalVO);
			}
		}
		return adjustPriceTotalVO;
		
	}

	@Override
	public AdjustPriceTotalVO getReceiveAdjustList(UserVO userVO, Page<AdjustPriceTotalVO> page, Map<String, Object> map,
			Integer type, Integer sign) {
		validOrderByParams(map);
		AdjustPriceTotalVO adjustPriceTotalVO = new AdjustPriceTotalVO();
		adjustPriceTotalVO.setList(new ArrayList<>());
		if(type == null) {
			Integer totalRecord = adjustPriceAnalysisMapper.getReceiveAdjustPriceTotalRecord(map);
			page.setTotalRecord(totalRecord);
			if(totalRecord != null && totalRecord != 0) {
				List<AdjustPriceTotalVO> adjustPriceTotalVOs = adjustPriceAnalysisMapper.getReceiveAdjustPriceListTotal(map);
				setTotalInfo(adjustPriceTotalVO, adjustPriceTotalVOs);
				List<AdjustPriceVO> resList = adjustPriceAnalysisMapper.queryReceiveAdjustPriceList(map);
				setListTotalInfo(resList);
				adjustPriceTotalVO.setList(resList);
				page.setResult(adjustPriceTotalVO);
			}else {
				setTotalInfo(adjustPriceTotalVO, null);
				page.setResult(adjustPriceTotalVO);
			}
		}else {
			validType(type, sign);
			Integer totalRecord = adjustPriceAnalysisMapper.getReceiveAdjustPriceTotalRecordByType(map);
			page.setTotalRecord(totalRecord);
			if(totalRecord != null && totalRecord != 0) {
				List<AdjustPriceTotalVO> adjustPriceTotalVOs = adjustPriceAnalysisMapper.getReceiveAdjustPriceListByTypeTotal(map);
				if(!adjustPriceTotalVOs.isEmpty()) {
					setTotalInfo(adjustPriceTotalVO, adjustPriceTotalVOs);
					List<AdjustPriceVO> resList = adjustPriceAnalysisMapper.queryReceiveAdjustPriceListByType(map);
					setListTotalInfo(resList);
					adjustPriceTotalVO.setList(resList);
				}
				page.setResult(adjustPriceTotalVO);
			}else {
				setTotalInfo(adjustPriceTotalVO, null);
				page.setResult(adjustPriceTotalVO);
			}
		}
		return adjustPriceTotalVO;
	}

	@Override
	public List<AdjustPriceDetail> getPayAdjustDetail(UserVO userVO, Long id, Integer type, Integer sign) {
		if(type == null || sign == null) throw new BusinessException("筛选类型不符合条件");
		validType(type, sign);
		List<AdjustPriceDetail> resList = adjustPriceAnalysisMapper.queryAdjustPriceDetailList(id, type,userVO.getChainType());
		setDtlListTotalInfo(resList);
		calculatePrice(resList);
		return resList;
	}

	@Override
	public List<AdjustPriceDetail> getReceiveAdjustDetail(UserVO userVO, Long id, Integer type, Integer sign) {
		if(type == null || sign == null) throw new BusinessException("筛选类型不符合条件");
		validType(type, sign);
		List<AdjustPriceDetail> resList = adjustPriceAnalysisMapper.queryReceiveAdjustPriceDetailList(id, type,userVO.getChainType());
		setDtlListTotalInfo(resList);
		calculatePrice(resList);
		return resList;
	}
	
	/**
	 * 验证是否符合类型查询条件
	 * @param type
	 * @param sign
	 */
	private void validType(Integer type, Integer sign) {
		if(sign == 0) {
			//2201-预付发票,2202-应付发票,2203-应付贷项凭证;
			if(type != null) {
				if(!type.equals(2201)&&!type.equals(2202)&&!type.equals(2203)) throw new BusinessException("筛选类型不符合条件");
			}
		}else {
			//2301-预收发票,2302-应收发票,2303-应收贷项凭证
			if(type != null) {
				if(!type.equals(2301)&&!type.equals(2302)&&!type.equals(2303)) throw new BusinessException("筛选类型不符合条件");
			}
		}
	}
	
	private void setTotalInfo(AdjustPriceTotalVO adjustPriceTotalVO, List<AdjustPriceTotalVO> adjustPriceTotalVOs) {
		BigDecimal amountTotal = BigDecimal.ZERO;
	    BigDecimal notaxAmountTotal = BigDecimal.ZERO;
	    BigDecimal taxAmountTotal = BigDecimal.ZERO;
	    BigDecimal diffAmountTotal = BigDecimal.ZERO;
	    BigDecimal diffNotaxAmountTotal = BigDecimal.ZERO;
	    BigDecimal diffTaxAmountTotal = BigDecimal.ZERO;
		if(adjustPriceTotalVOs != null && !adjustPriceTotalVOs.isEmpty()) {
		    for(AdjustPriceTotalVO vo:adjustPriceTotalVOs) {
		    	if(vo != null) {
		    		if(vo.getAmountTotal() != null) amountTotal = amountTotal.add(vo.getAmountTotal());
		    		if(vo.getNotaxAmountTotal() != null) notaxAmountTotal = notaxAmountTotal.add(vo.getNotaxAmountTotal());
		    		if(vo.getTaxAmountTotal() != null) taxAmountTotal = taxAmountTotal.add(vo.getTaxAmountTotal());
		    		if(vo.getDiffAmountTotal() != null) diffAmountTotal = diffAmountTotal.add(vo.getDiffAmountTotal());
		    		if(vo.getDiffNotaxAmountTotal() != null) diffNotaxAmountTotal = diffNotaxAmountTotal.add(vo.getDiffNotaxAmountTotal());
		    		if(vo.getDiffTaxAmountTotal() != null) diffTaxAmountTotal = diffTaxAmountTotal.add(vo.getDiffTaxAmountTotal());
		    	}
		    }
		}
		adjustPriceTotalVO.setAmountTotal(amountTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		adjustPriceTotalVO.setNotaxAmountTotal(notaxAmountTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		adjustPriceTotalVO.setTaxAmountTotal(taxAmountTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		adjustPriceTotalVO.setDiffAmountTotal(diffAmountTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		adjustPriceTotalVO.setDiffNotaxAmountTotal(diffNotaxAmountTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		adjustPriceTotalVO.setDiffTaxAmountTotal(diffTaxAmountTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	private void setListTotalInfo(List<AdjustPriceVO> resList) {
		if(resList != null && !resList.isEmpty()) {
			for(AdjustPriceVO vo:resList) {
				if(vo.getAmountTotal() != null) vo.setAmountTotal(vo.getAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getNotaxAmountTotal() != null) vo.setNotaxAmountTotal(vo.getNotaxAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getTaxAmountTotal() != null) vo.setTaxAmountTotal(vo.getTaxAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getDiffAmountTotal() != null) vo.setDiffAmountTotal(vo.getDiffAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getDiffNotaxAmountTotal() != null) vo.setDiffNotaxAmountTotal(vo.getDiffNotaxAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getDiffTaxAmountTotal() != null) vo.setDiffTaxAmountTotal(vo.getDiffTaxAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
	}
	
	private void setDtlListTotalInfo(List<AdjustPriceDetail> resList ) {
		if(resList != null && !resList.isEmpty()) {
			for(AdjustPriceDetail vo:resList) {
				if(vo.getAmount() != null) vo.setAmount(vo.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getNotaxAmount() != null) vo.setNotaxAmount(vo.getNotaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getTaxAmount() != null) vo.setTaxAmount(vo.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getDiffAmount() != null) vo.setDiffAmount(vo.getDiffAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getDiffNotaxAmount() != null) vo.setDiffNotaxAmount(vo.getDiffNotaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
	    		if(vo.getDiffTaxAmount() != null) vo.setDiffTaxAmount(vo.getDiffTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void excelExport(OutputStream output, UserVO userVO, Map<String, Object> map,Integer type, Integer sign) {
		AdjustPriceTotalVO adjustPriceTotalVO = null;
		Page<AdjustPriceTotalVO> page = new Page<AdjustPriceTotalVO>();
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		if(sign == 0) {
			adjustPriceTotalVO = getPayAdjustList(userVO, page, map, type,sign);
			rowHeaderList.put("unitCode", "供货单位编码");
			rowHeaderList.put("unitName", "供货单位名称");
		}else {
			adjustPriceTotalVO = getReceiveAdjustList(userVO, page, map, type,sign);
			rowHeaderList.put("unitCode", "购货单位编码");	
			rowHeaderList.put("unitName", "购货单位名称");
		}
		
		rowHeaderList.put("billDate", "单据日期");
		rowHeaderList.put("code", "单据编号");
		rowHeaderList.put("orderTypeName", "单据类型");
		rowHeaderList.put("amountTotal", "金额");
		rowHeaderList.put("notaxAmountTotal", "不含税金额");
		rowHeaderList.put("taxAmountTotal", "税额");
		rowHeaderList.put("diffAmountTotal", "金额差额");
		rowHeaderList.put("diffNotaxAmountTotal", "不含税金额差额");
		rowHeaderList.put("diffTaxAmountTotal", "税额差额");
		adjustPriceReportExcelComponent.commExcelExport(output, rowHeaderList, adjustPriceTotalVO.getList(), userVO, adjustPriceTotalVO, sign);
	}
	
	
	private void validOrderByParams( Map<String, Object> map) {
		String order = null;
		if(map.get("order") != null) {
			order = map.get("order").toString();
		}
		String sort = null;
		if(map.get("sort") !=null) {
			sort = map.get("sort").toString();
		}
		if(order == null || sort == null) {
			map.put("order", null);
			map.put("sort", null);
		}else {
			//ORDER BY billDate asc,`code` asc  desc
			//orderDate-单据日期,orderCode-单据编号
			if(!"asc".equals(sort) && !"desc".equals(sort)) throw new BusinessException("排序类型不符合条件");
			if(!"orderDate".equals(order) && !"orderCode".equals(order)) throw new BusinessException("排序列不符合条件");
			if("orderDate".equals(order)) {
				map.put("order", "billDate");
			}else {
				map.put("order", "code");
			}
			map.put("sort", sort);
		}
	}
	
	private void calculatePrice(List<AdjustPriceDetail> resList) {
		if(!CollectionUtils.isEmpty(resList)) {
			for(AdjustPriceDetail adjustPriceDetail : resList) {
				adjustPriceDetail.setBaseAmount(adjustPriceDetail.getBaseUnitPrice().multiply(adjustPriceDetail.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP));
				adjustPriceDetail.setBaseNotaxAmount(adjustPriceDetail.getBaseNotaxPrice().multiply(adjustPriceDetail.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP));
				adjustPriceDetail.setBaseTaxAmount(adjustPriceDetail.getBaseAmount().subtract(adjustPriceDetail.getBaseNotaxAmount()));
			}
		}
	}
}
