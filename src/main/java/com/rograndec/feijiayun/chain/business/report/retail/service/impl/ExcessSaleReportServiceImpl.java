package com.rograndec.feijiayun.chain.business.report.retail.service.impl;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.report.retail.dao.ExcessSaleReportMapper;
import com.rograndec.feijiayun.chain.business.report.retail.service.ExcessSaleReportService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleExcelResultVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleResultDetailVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleResultTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleResultVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleToOtherInVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherInService;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.InOutComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.ExcessiveSale;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSSaleDetailVO;

@Service
public class ExcessSaleReportServiceImpl implements ExcessSaleReportService{

	
	@Autowired
	private ExcessSaleReportMapper excessSaleReportMapper;
	
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private OtherInService otherInService;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired 
	private SaleShelfMapper saleShelfMapper;
	
	@Autowired
	private InOutComponent inOutComponent;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectExcessSalePageByParam(ExcessSaleQueryVO vo,
			UserVO loginUser) {

		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("goodsCode".equals(order)){
			order = "de.goods_code";
		} else if("shelfName".equals(order)){
			order = "sh.shelf_name";
		} else {
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<ExcessSaleResultVO> voList = excessSaleReportMapper.selectExcessSalePageByParam(vo, 
				page.getStart(), page.getPageSize(), loginUser.getEnterpriseId(), order, sort);
		
		if(voList != null){
			
			List<ExcessSaleResultDetailVO> voDetailList = null;
			GoodsInfoStockShelfVO shelfVO = null;
			for (ExcessSaleResultVO excessSaleResultVO : voList) {
				
				voDetailList = excessSaleReportMapper.selectExcessSaleDetailByParam(
						loginUser.getEnterpriseId(), excessSaleResultVO.getGoodsId(), excessSaleResultVO.getLotId(), excessSaleResultVO.getShelfId());
				if(voDetailList != null){
					
					excessSaleResultVO.setDetailList(voDetailList);
				}
				
				shelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(loginUser.getEnterpriseId(), 
						excessSaleResultVO.getGoodsId(), excessSaleResultVO.getShelfId(), excessSaleResultVO.getLotId());
				
				BigDecimal saleQuantity = getSaleQuantity(excessSaleResultVO);
				
				if(shelfVO != null){
					excessSaleResultVO.setStockQuantity(shelfVO.getQuantity());
					excessSaleResultVO.setUsableQuantity(shelfVO.getUsableQuantity());
					
				}	
				
				BigDecimal excessSaleQuantity = getExcessSaleQuantity(saleQuantity, excessSaleResultVO.getUsableQuantity());
				
				excessSaleResultVO.setExcessSaleQuantity(excessSaleQuantity);
			}
		}
		
		Long totalRecord = excessSaleReportMapper.queryCountExcessSaleByParam(vo, loginUser.getEnterpriseId());
		
		ExcessSaleResultTotalVO resultVO = excessSaleReportMapper.queryExcessSalePageByParam(vo, 
				loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new ExcessSaleResultTotalVO();
		}
		if(resultVO.getExcessSaleQuantity().compareTo(BigDecimal.ZERO) < 0){
			resultVO.setExcessSaleQuantity(BigDecimal.ZERO);
		}
		resultVO.setResultList(voList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	private BigDecimal getExcessSaleQuantity(BigDecimal saleQuantity,
			BigDecimal usableQuantity) {
		
		if(usableQuantity != null){
			if(saleQuantity.compareTo(usableQuantity) > 0){
				return saleQuantity.subtract(usableQuantity);
			}else{
				return BigDecimal.ZERO;
			}
		}
		
		return saleQuantity;
	}


	private BigDecimal getSaleQuantity(ExcessSaleResultVO excessSaleResultVO) {
		List<ExcessSaleResultDetailVO> detailList = excessSaleResultVO.getDetailList();
		BigDecimal saleQuantity = BigDecimal.ZERO;
		if(detailList != null){
			for (ExcessSaleResultDetailVO excessSaleResultDetailVO : detailList) {
				saleQuantity = saleQuantity.add(excessSaleResultDetailVO.getSaleQuantity()==null?
						BigDecimal.ZERO:excessSaleResultDetailVO.getSaleQuantity());
			}
		}	
		return saleQuantity;
	}


	@Override
	public void exportExcel(OutputStream output, ExcessSaleQueryVO vo,
			UserVO loginUser, UserVO loginUser2) throws Exception {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("manufacturer","生产厂商");
        map.put("unitName","单位");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("shelfName","货位");
        map.put("saleCode","销售单号");
        map.put("saleQuantity","销售数量");
        map.put("stockQuantity","库存数量");
        map.put("usableQuantity","可用数量");
        map.put("excessSaleQuantity","超量数量");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("超量销售");
        
        List<ExcessSaleExcelResultVO> excelList = getExcelList(vo,
			loginUser);
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, excelList, name, null, "", false, needTotalName);

		
	}


	private List<ExcessSaleExcelResultVO> getExcelList(ExcessSaleQueryVO vo,
			UserVO loginUser) throws IllegalAccessException, InvocationTargetException {
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("goodsCode".equals(order)){
			order = "de.goods_code";
		} else if("shelfName".equals(order)){
			order = "sh.shelf_name";
		} else {
			order = null;
		}
		
		List<ExcessSaleResultVO> voList = excessSaleReportMapper.selectExcessSalePageByParam(vo, 
				null, null, loginUser.getEnterpriseId(), order, sort);
		List<ExcessSaleExcelResultVO> resultList = new ArrayList<>();
		if(voList != null){
			
			List<ExcessSaleResultDetailVO> voDetailList = null;
			GoodsInfoStockShelfVO shelfVO = null;
			
			for (ExcessSaleResultVO excessSaleResultVO : voList) {
				
				voDetailList = excessSaleReportMapper.selectExcessSaleDetailByParam(
						loginUser.getEnterpriseId(), excessSaleResultVO.getGoodsId(), excessSaleResultVO.getLotId(), excessSaleResultVO.getShelfId());
				if(voDetailList != null){
					
					excessSaleResultVO.setDetailList(voDetailList);
				}
				
				shelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(loginUser.getEnterpriseId(), 
						excessSaleResultVO.getGoodsId(), excessSaleResultVO.getShelfId(), excessSaleResultVO.getLotId());
				
				BigDecimal saleQuantity = getSaleQuantity(excessSaleResultVO);
				
				if(shelfVO != null){
					excessSaleResultVO.setStockQuantity(shelfVO.getQuantity());
					excessSaleResultVO.setUsableQuantity(shelfVO.getUsableQuantity());
					
				}	
				
				BigDecimal excessSaleQuantity = getExcessSaleQuantity(saleQuantity, excessSaleResultVO.getUsableQuantity());
				
				excessSaleResultVO.setExcessSaleQuantity(excessSaleQuantity);
			}
			
			ExcessSaleExcelResultVO result = null;
			
			for (ExcessSaleResultVO excessSaleResultVO : voList) {
				
				voDetailList = excessSaleResultVO.getDetailList();
				
				if(voDetailList == null || voDetailList.size() == 0){
					result = new ExcessSaleExcelResultVO();
					BeanUtils.copyProperties(result, excessSaleResultVO);
					resultList.add(result);
				}else{
					for (ExcessSaleResultDetailVO detailVO : voDetailList) {
						result = new ExcessSaleExcelResultVO();
						BeanUtils.copyProperties(result, excessSaleResultVO);
						result.setSaleCode(detailVO.getSaleCode());
						result.setSaleQuantity(detailVO.getSaleQuantity());
						resultList.add(result);
					}
				}
			}
		}
		
		return resultList;
	}


	@Override
	public List<OtherInDetailVO> selectExcessSaleDataByParam(ExcessSaleQueryVO vo,
			UserVO loginUser) throws Exception {

		//处理超量订单
		List<Sale> listSale = saleMapper.selectExecessSaleDataByEnterpriseId(loginUser.getEnterpriseId());
		Map<String, BigDecimal> judgeIsExcessiveSaleMap = new HashMap<String, BigDecimal>();
		if(listSale != null){
			StringBuilder sb = new StringBuilder();
			for (Sale sale : listSale) {
				
				boolean flag = isSaleExcessSale(sale, judgeIsExcessiveSaleMap);
				
				if(flag){
					sb.append(sale.getCode()).append(",");
				}
			}
			
			inOutComponent.handleExcessiveSaleData(loginUser.getEnterpriseId(), sb.toString(), loginUser);
		}
		
		List<OtherInDetailVO> list = otherInService.getGoodsList(loginUser, "over", vo.getKey());	
		
		/*ExcessSaleToOtherInVO saleVO = new ExcessSaleToOtherInVO();
		saleVO.setDetailList(list);
		saleVO.setInDate(new Date());
		saleVO.setInUserId(loginUser.getUserId());
		saleVO.setInUserName(loginUser.getUserName());
		saleVO.setInType(0);
		saleVO.setInTypeName("超量销售");
		saleVO.setStoreId(loginUser.getEnterpriseId());
		saleVO.setStoreName(loginUser.getEnterpriseName());*/
		
		return list;
	}
	
	
	private boolean isSaleExcessSale(Sale sale, Map<String, BigDecimal> judgeIsExcessiveSaleMap) {
		List<SaleShelf> shelfList = saleShelfMapper.queryBySaleIdAndEnterpriseId(sale.getId(), sale.getEnterpriseId());
		
		if(shelfList != null){
			for (SaleShelf saleShelf : shelfList) {
				boolean flag = judgeIsExcessiveSale(saleShelf, sale, judgeIsExcessiveSaleMap);
				if(!flag){
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * @Description: TODO判断是否超售
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年10月21日 下午6:22:35 
	 * @param detailVO
	 * @param sale
	 * @param judgeIsExcessiveSaleMap 
	 * @return void
	 */
	private boolean judgeIsExcessiveSale(SaleShelf saleShelf, Sale sale, Map<String, BigDecimal> judgeIsExcessiveSaleMap) {

		String key = sale.getEnterpriseId() + "-" + saleShelf.getGoodsId() +"-" + saleShelf.getShelfId() + "-" + saleShelf.getLotId();
		
		BigDecimal usableQuantity = BigDecimal.ZERO;
		
		if(judgeIsExcessiveSaleMap.containsKey(key)){

			usableQuantity = judgeIsExcessiveSaleMap.get(key);
			
			usableQuantity = usableQuantity.subtract(saleShelf.getQuantity());
		}else{
			
			GoodsInfoStockShelfVO vo = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(sale.getEnterpriseId(), 
					saleShelf.getGoodsId(), saleShelf.getShelfId(), saleShelf.getLotId());
			
			usableQuantity = vo.getUsableQuantity().subtract(saleShelf.getQuantity());
			
		}
		judgeIsExcessiveSaleMap.put(key, usableQuantity);
		
		if(usableQuantity.compareTo(BigDecimal.ZERO) < 0){
			
			return false;
		}
		return true;
		
	}

}
