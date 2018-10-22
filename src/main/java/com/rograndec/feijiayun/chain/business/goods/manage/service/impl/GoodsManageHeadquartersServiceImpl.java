package com.rograndec.feijiayun.chain.business.goods.manage.service.impl;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.GoodsManageMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.manage.service.GoodsManageHeadquartersService;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.GoodsManageStatus;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class GoodsManageHeadquartersServiceImpl implements GoodsManageHeadquartersService{

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsManageMapper goodsManageMapper;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private SafetyStockMapper safetyStockMapper;
	
	@Autowired
	private PriceOrderDetailMapper priceOrderDetailMapper;
	
	@Autowired
	private GoodsTaxRateMapper goodsTaxRateMapper;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<GoodsHeadquartersVO> selectGoodsHeadquartersVOPage(int pageNo,
			int pageSize, Long enterpriseId,String key, String order,
			String sort, Page page,UserVO userVO) {
		
		if("businessVarietyName".equals(order)||"businessVariety".equals(order)){
			order = "business_variety";
		}else if("goodsCode".equals(order)){
			order = "code";
		}else{
			order = "";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "";
		}
		CommonParamSupplierAndGoods paramVO = new CommonParamSupplierAndGoods();
		ParamUtils.packParam(userVO,paramVO);
		Long totalRecord = goodsMapper.queryCountByEnterpriseIdAndKey(enterpriseId,key,paramVO);
		
		List<GoodsHeadquartersVO> list = goodsMapper.selectByEnterpriseIdAndKey(page.getStart(), pageSize, enterpriseId, key, order, sort, paramVO);
		
		if(list != null){
			for (GoodsHeadquartersVO goodsHeadquartersVO : list) {
				goodsHeadquartersVO.setBusinessVarietyName(BusinessVariety.getName(goodsHeadquartersVO.getBusinessVariety()!=null?goodsHeadquartersVO.getBusinessVariety():9));
			}
		}
		
		page.setTotalRecord(totalRecord.intValue());
		
		return list;
	}

	@Override
	public List<GoodsHeadquartersDetailVO> selectGoodsHeadquartersDetail(
			Long enterpriseId, Long goodsId, Integer chainType,
			String key, String order, String sort) {
		
		if("chainType".equals(order)||"chainTypeName".equals(order)){
			order = "m.chain_type";
		}else if("enterpriseName".equals(order)){
			order = "e.name";
		}else{
			order = "";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "";
		}
		
		if(chainType != null && chainType == 3){//全部
			chainType = null;
		}
		
		List<GoodsHeadquartersDetailVO> list = goodsManageMapper.selectGoodsManageByEnterpriseIdAndGoodsId(enterpriseId, goodsId, chainType, key, order, sort);
		if(list != null && list.size() > 0){
			
			//查询库存
			List<Map<String, Object>> mapList = stockMapper.selectGoodsQuantityByEnterpriseIdAndGoodsId(enterpriseId, goodsId);
			Map<Long, Object> map = new HashMap<Long, Object>();
			if(mapList != null){
				for (Map<String, Object> map2 : mapList) {
					map.put(Long.parseLong(map2.get("enterpriseId").toString()), map2.get("quantity"));
				}
			}
			
			Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
			
			List<Long> safetyStockId = new ArrayList<>();//安全库存List
			List<Long> priceOrderDtlId = new ArrayList<>();//价格清单明细ID
			
			for (GoodsHeadquartersDetailVO vo : list) {
				
				vo.setGoodsChainType(goods.getChainType());
				
				safetyStockId.add(vo.getSafetyStockId());
				priceOrderDtlId.add(vo.getPriceOrderDtlId());
				
				vo.setManageStatusName(GoodsManageStatus.getName(vo.getManageStatus()));
				vo.setChainTypeName(ChainType.getName(vo.getChainType()));
				if(map.containsKey(vo.getEnterpriseId().longValue())){//设置库存数量
					vo.setStockQuantity(new BigDecimal(map.get(vo.getEnterpriseId().longValue()).toString()));
				}else {
					vo.setStockQuantity(BigDecimal.valueOf(0));
				}
			}
			//批量查询安全库存
			Map<Long, SafetyStock> stockMap = new HashMap<>();
			List<SafetyStock> stockList = safetyStockMapper.selectSafetyStockByIds(safetyStockId);
			if(stockList != null){
				for (SafetyStock safetyStock : stockList) {
					stockMap.put(safetyStock.getId(), safetyStock);
				}
			}
			//批量查询价格清单
			Map<Long, PriceOrderDetail> orderDetailMap = new HashMap<>();
			List<PriceOrderDetail> priceList = priceOrderDetailMapper.selectPriceOrderDetailByIds(priceOrderDtlId);
			if(priceList != null){
				for (PriceOrderDetail orderDetail : priceList) {
					orderDetailMap.put(orderDetail.getId(), orderDetail);
				}
			}
			
			SafetyStock stock = null; 
			PriceOrderDetail orderDetail = null;
			for (GoodsHeadquartersDetailVO vo : list) {
				if(stockMap.containsKey(vo.getSafetyStockId())){
					stock = stockMap.get(vo.getSafetyStockId());
					vo.setDefaultShelfId(stock.getDefaultShelfId());
					vo.setDefaultShelfName(stock.getDefaultShelfName());
					vo.setInventoryUp(stock.getInventoryUp());
					vo.setSafetyStock(stock.getSafetyStock());
					vo.setInventoryDown(stock.getInventoryDown());
				}
				if(orderDetailMap.containsKey(vo.getPriceOrderDtlId())){
					orderDetail = orderDetailMap.get(vo.getPriceOrderDtlId());
//					vo.setDistrPrice(orderDetail.getDistrPrice()); //配货单价,与张红运确认,列表不显示该字段,保存时不传
//					vo.setDistrTaxRate(orderDetail.getDistrTaxRate());//配货税率,与张红运确认,列表不显示该字段,保存时不传
//					vo.setNotaxDistrPrice(orderDetail.getNotaxDistrPrice());//不含税配货单价,与张红运确认,列表不显示该字段,保存时不传
					vo.setRetailPrice(orderDetail.getRetailPrice());
					vo.setMemberPrice(orderDetail.getMemberPrice());
					vo.setSaleTaxRateId(orderDetail.getSaleTaxRateId());
					vo.setSaleTaxRate(orderDetail.getSaleTaxRate());
					vo.setNotaxRetailPrice(orderDetail.getNotaxRetailPrice());
					vo.setNotaxMemberPrice(orderDetail.getNotaxMemberPrice());
				}
				
			}
			
		}
		
		return list;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public String saveGoodsHeadquartersDetail(UserVO loginUser,
			List<GoodsHeadquartersDetailVO> goodsDetailList) throws Exception {
		
		List<Long> list = new ArrayList<>();
		for (GoodsHeadquartersDetailVO vo : goodsDetailList) {
			list.add(vo.getId());
		}
		List<GoodsManage> manageList = goodsManageMapper.selectByIds(list);
		
		String msg = validateSaveGoodsHeadquartersDetail(goodsDetailList, manageList);
		if(StringUtils.isNotBlank(msg)){
			return msg;
		}
		
		List<Long> safetyList = new ArrayList<>();
		List<Long> priceOrderList = new ArrayList<>();
		Map<Long, Long> safetyMap = new HashMap<>();//绑定安全库存ID-商品管理ID
		Map<Long, Long> priceOrderMap = new HashMap<>();//绑定价格清单ID-商品管理ID
		for (GoodsManage manage : manageList) {
			
			safetyList.add(manage.getSafetyStockId());
			priceOrderList.add(manage.getPriceOrderDtlId());
			
			safetyMap.put(manage.getSafetyStockId(), manage.getId());
			priceOrderMap.put(manage.getPriceOrderDtlId(), manage.getId());
		}
		
		Map<Long, GoodsHeadquartersDetailVO> voMap = new HashMap<>();//绑定商品管理ID-detailVO
		for (GoodsHeadquartersDetailVO vo : goodsDetailList) {
			voMap.put(vo.getId(), vo);
		}
		
		List<SafetyStock> stockList = safetyStockMapper.selectSafetyStockByIds(safetyList);
		if(stockList != null){
			
			GoodsHeadquartersDetailVO vo = null;
			for (SafetyStock safetyStock : stockList) {
				if(safetyMap.containsKey(safetyStock.getId())){
					Long manageId = safetyMap.get(safetyStock.getId());
					if(voMap.containsKey(manageId)){
						vo = voMap.get(manageId);
						safetyStock = transformationToStock(safetyStock, vo, loginUser);
						
						safetyStockMapper.updateByPrimaryKey(safetyStock);
					}
				}
			}
			
//			safetyStockMapper.batchUpdate(stockList);
		}
		
		List<PriceOrderDetail> priceDetailList = priceOrderDetailMapper.selectPriceOrderDetailByIds(priceOrderList);
		if(priceDetailList != null){
			
			GoodsHeadquartersDetailVO vo = null;
			for (PriceOrderDetail orderdetail : priceDetailList) {
				if(priceOrderMap.containsKey(orderdetail.getId())){
					Long manageId = priceOrderMap.get(orderdetail.getId());
					if(voMap.containsKey(manageId)){
						vo = voMap.get(manageId);
						orderdetail = transformationToOrderDetail(orderdetail, vo, loginUser);
						
						priceOrderDetailMapper.updateByPrimaryKey(orderdetail);
					}
				}
			}
			
//			priceOrderDetailMapper.batchUpdate(priceDetailList);
		}
		
		return null;
	}

	/**
	 * @Description: TODO detailVO to safetyStock
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月6日 下午4:25:18 
	 * @param orderdetail
	 * @param vo
	 * @param loginUser
	 * @return 
	 * @return PriceOrderDetail
	 * @throws Exception 
	 */
	private PriceOrderDetail transformationToOrderDetail(
			PriceOrderDetail orderdetail, GoodsHeadquartersDetailVO vo,
			UserVO loginUser) throws Exception {
//		orderdetail.setDistrPrice(vo.getDistrPrice()); //配货单价,与张红运确认,列表不显示该字段,保存时不传
//		orderdetail.setDistrTaxRate(vo.getDistrTaxRate());//配货税率,与张红运确认,列表不显示该字段,保存时不传
//		orderdetail.setNotaxDistrPrice(vo.getNotaxDistrPrice());//不含税配货单价,与张红运确认,列表不显示该字段,保存时不传
		//orderdetail.setRetailPrice(vo.getRetailPrice());//零售单价
		//orderdetail.setMemberPrice(vo.getMemberPrice());//会员单价
//		orderdetail.setSaleTaxRateId(vo.getSaleTaxRateId());
//		GoodsTaxRate rate = goodsTaxRateMapper.selectByPrimaryKey(vo.getSaleTaxRateId());
//		if(rate == null){
//			throw new Exception("税率ID："+vo.getSaleTaxRateId()+"查不到数据！");
//		}
//		orderdetail.setSaleTaxRate(rate.getTaxRate());
//		orderdetail.setNotaxRetailPrice(vo.getNotaxRetailPrice());
//		orderdetail.setNotaxMemberPrice(vo.getNotaxMemberPrice());
		
		//orderdetail.setNotaxRetailPrice(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(orderdetail.getRetailPrice(), orderdetail.getSaleTaxRate()));
		//orderdetail.setNotaxMemberPrice(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(orderdetail.getMemberPrice(), orderdetail.getSaleTaxRate()));
		
		orderdetail.setModifierId(loginUser.getUserId());
		orderdetail.setModifierCode(loginUser.getUserCode());
		orderdetail.setModifierName(loginUser.getUserName());
		orderdetail.setUpdateTime(new Date());
		return orderdetail;
	}

	/**
	 * @Description: TODO detailVO to safetyStock
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月6日 下午4:17:13 
	 * @param safetyStock
	 * @param vo
	 * @return 
	 * @return SafetyStock
	 */
	private SafetyStock transformationToStock(SafetyStock safetyStock,
			GoodsHeadquartersDetailVO vo, UserVO loginUser) {
		safetyStock.setDefaultShelfId(vo.getDefaultShelfId());
		safetyStock.setDefaultShelfName(vo.getDefaultShelfName());
		safetyStock.setInventoryUp(vo.getInventoryUp());
		safetyStock.setInventoryDown(vo.getInventoryDown());
		safetyStock.setSafetyStock(vo.getSafetyStock());
		safetyStock.setModifierId(loginUser.getUserId());
		safetyStock.setModifierCode(loginUser.getUserCode());
		safetyStock.setModifierName(loginUser.getUserName());
		safetyStock.setUpdateTime(new Date());
		return safetyStock;
	}

	private String validateSaveGoodsHeadquartersDetail(
			List<GoodsHeadquartersDetailVO> goodsDetailList, List<GoodsManage> manageList) {
		
		if(manageList == null){
			StringBuilder sb = new StringBuilder();
			for (GoodsHeadquartersDetailVO vo : goodsDetailList) {
				sb.append(vo.getId()).append(",");
			}
			return "GoodsHeadquartersDetailVO中ID错误，ID为"+sb.toString()+"不可修改";
		} 
		if(goodsDetailList.size() != manageList.size()){
			
			StringBuilder sb = new StringBuilder();

			Map<Long, Long> voMap = new HashMap<>();
			for (GoodsManage goodsManage : manageList) {
				voMap.put(goodsManage.getId(), goodsManage.getId());
			}
			
			for (GoodsHeadquartersDetailVO vo : goodsDetailList) {
				if(!voMap.containsKey(vo.getId())){
					sb.append(vo.getId()).append(",");
				}
			}
			
			if(StringUtils.isNotBlank(sb.toString())){
				return "GoodsHeadquartersDetailVO中ID错误，ID为"+sb.toString()+"不可修改";
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<EnterpriseHeadquartersVO> selectEnterpriseHeadquartersVOPage(
			int pageNo, int pageSize, Long enterpriseId,Integer type, String key,
			String order, String sort, Page page) {
		
		List<EnterpriseHeadquartersVO> list = new ArrayList<>();
		
		if("chainType".equals(order)||"chainTypeName".equals(order)){
			order = "m.chain_type";
		}else if("enterpriseName".equals(order)||"enterpriseCode".equals(order)){
			order = "e.name";
		}else if("businessVariety".equals(order)||"businessVarietyName".equals(order)){
			order = "d.business_variety";
		}else if("goodsCode".equals(order)){
			order = "d.code";
		}else{
			order = "";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "";
		}
		
		Long totalRecord = 0L;
		
		if(type != null && type == 0){//商品
			totalRecord = goodsManageMapper.queryEnterpriseHeadquartersCountByGoodsParams(enterpriseId,key);
			
			list = goodsManageMapper.selectEnterpriseHeadquartersVOByGoodsParams(page.getStart(), 
					pageSize, enterpriseId,key, order, sort);
		}else if(type != null && type == 1){//组织机构
			totalRecord = goodsManageMapper.queryEnterpriseHeadquartersCountByEnterpriseParams(enterpriseId,key);
			
			list = goodsManageMapper.selectEnterpriseHeadquartersVOByEnterpriseParams(page.getStart(), 
					pageSize, enterpriseId, key, order, sort);
			
		}
		
		if(list != null && list.size() > 0){
			List<Long> enterId = new ArrayList<>();//门店IDList
			List<Long> goodsId = new ArrayList<>();//商品IDList
			List<Long> safetyStockId = new ArrayList<>();//安全库存List
			List<Long> priceOrderDtlId = new ArrayList<>();//价格清单明细ID
			for (EnterpriseHeadquartersVO vo : list) {
				
				enterId.add(vo.getEnterpriseId());
				goodsId.add(vo.getGoodsId());
				safetyStockId.add(vo.getSafetyStockId());
				priceOrderDtlId.add(vo.getPriceOrderDtlId());
				
				vo.setManageStatusName(GoodsManageStatus.getName(vo.getManageStatus()));
				vo.setChainTypeName(ChainType.getName(vo.getChainType()));
				vo.setBusinessVarietyName(vo.getBusinessVariety() == null ? "" : BusinessVariety.getName(vo.getBusinessVariety()));
			}
			//批量查询库存
			List<Map<String, Object>> mapList = stockMapper.selectGoodsQuantityInEnterpriseIdAndGoodsId(enterId, goodsId);
			Map<String, Object> map = new HashMap<String, Object>();
			if(mapList != null){
				for (Map<String, Object> map2 : mapList) {
					String stockKey = map2.get("enterpriseId").toString()+"-"+map2.get("goodsId").toString();
					map.put(stockKey, map2.get("quantity"));
				}
			}
			
			//批量查询安全库存
			Map<Long, SafetyStock> stockMap = new HashMap<>();
			List<SafetyStock> stockList = safetyStockMapper.selectSafetyStockByIds(safetyStockId);
			if(stockList != null){
				for (SafetyStock safetyStock : stockList) {
					stockMap.put(safetyStock.getId(), safetyStock);
				}
			}
			//批量查询价格清单
			Map<Long, PriceOrderDetail> orderDetailMap = new HashMap<>();
			List<PriceOrderDetail> priceList = priceOrderDetailMapper.selectPriceOrderDetailByIds(priceOrderDtlId);
			if(priceList != null){
				for (PriceOrderDetail orderDetail : priceList) {
					orderDetailMap.put(orderDetail.getId(), orderDetail);
				}
			}
			
			
			SafetyStock stock = null; 
			PriceOrderDetail orderDetail = null;
			for (EnterpriseHeadquartersVO vo : list) {
				String stockKey = vo.getEnterpriseId().toString()+"-"+vo.getGoodsId().toString();
				if(map.containsKey(stockKey)){//设置库存数量
					vo.setStockQuantity(new BigDecimal(map.get(stockKey).toString()));
				}else{
					vo.setStockQuantity(BigDecimal.ZERO);
				}
				if(stockMap.containsKey(vo.getSafetyStockId())){
					stock = stockMap.get(vo.getSafetyStockId());
					vo.setDefaultShelfId(stock.getDefaultShelfId());
					vo.setDefaultShelfName(stock.getDefaultShelfName());
					vo.setInventoryUp(stock.getInventoryUp());
					vo.setSafetyStock(stock.getSafetyStock());
					vo.setInventoryDown(stock.getInventoryDown());
				}
				if(orderDetailMap.containsKey(vo.getPriceOrderDtlId())){
					orderDetail = orderDetailMap.get(vo.getPriceOrderDtlId());
//					vo.setDistrPrice(orderDetail.getDistrPrice()); //配货单价,与张红运确认,列表不显示该字段,保存时不传
//					vo.setDistrTaxRate(orderDetail.getDistrTaxRate());//配货税率,与张红运确认,列表不显示该字段,保存时不传
//					vo.setNotaxDistrPrice(orderDetail.getNotaxDistrPrice());//不含税配货单价,与张红运确认,列表不显示该字段,保存时不传
					vo.setRetailPrice(orderDetail.getRetailPrice());
					vo.setMemberPrice(orderDetail.getMemberPrice());
					vo.setSaleTaxRateId(orderDetail.getSaleTaxRateId());
					vo.setSaleTaxRate(orderDetail.getSaleTaxRate());
					vo.setNotaxRetailPrice(orderDetail.getNotaxRetailPrice());
					vo.setNotaxMemberPrice(orderDetail.getNotaxMemberPrice());
				}
				
			}
		}
		
		page.setTotalRecord(totalRecord.intValue());
		
		return list;
	}

	@Override
	public String saveGoodsDetailByEnterprise(
			EnterpriseHeadquartersDetailVO vo, UserVO loginUser) throws Exception {
		GoodsManage manage = goodsManageMapper.selectByPrimaryKey(vo.getId());
		
		String msg = validateSaveGoodsDetailByEnterprise(manage, vo);
		if(StringUtils.isNotBlank(msg)){
			return msg;
		}
		
		//保存安全库存
		if(manage.getSafetyStockId() != null){
			SafetyStock stock = safetyStockMapper.selectByPrimaryKey(manage.getSafetyStockId());
			if(stock != null){
				stock = transformationToStock(stock, vo, loginUser);
				safetyStockMapper.updateByPrimaryKey(stock);
			}
		}	
		//保存价格明细
		if(manage.getPriceOrderDtlId() != null){
			PriceOrderDetail detail = priceOrderDetailMapper.selectByPrimaryKey(manage.getPriceOrderDtlId());
			if(detail != null){
				detail = transformationToPriceOrder(detail, vo, loginUser);
				priceOrderDetailMapper.updateByPrimaryKey(detail);
			}
		}
		
		return null;
	}

	private PriceOrderDetail transformationToPriceOrder(PriceOrderDetail detail,
			EnterpriseHeadquartersDetailVO vo, UserVO loginUser) throws Exception {
//		detail.setDistrPrice(vo.getDistrPrice()); //配货单价,与张红运确认,列表不显示该字段,保存时不传
//		detail.setDistrTaxRate(vo.getDistrTaxRate());//配货税率,与张红运确认,列表不显示该字段,保存时不传
//		detail.setNotaxDistrPrice(vo.getNotaxDistrPrice());//不含税配货单价,与张红运确认,列表不显示该字段,保存时不传
//		detail.setRetailPrice(vo.getRetailPrice());//零售单价 
//		detail.setMemberPrice(vo.getMemberPrice());//会员单价
		detail.setSaleTaxRateId(vo.getSaleTaxRateId());
		GoodsTaxRate rate = goodsTaxRateMapper.selectByPrimaryKey(vo.getSaleTaxRateId());
		if(rate == null){
			throw new Exception("税率ID："+vo.getSaleTaxRateId()+"查不到数据！");
		}
		//销项税
	//	detail.setSaleTaxRate(rate.getTaxRate());
//		detail.setNotaxRetailPrice(vo.getNotaxRetailPrice());
//		detail.setNotaxMemberPrice(vo.getNotaxMemberPrice());
		//不含税零售单价
		//detail.setNotaxRetailPrice(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(detail.getRetailPrice(), detail.getSaleTaxRate()));
		//不含税会员单价
		//detail.setNotaxMemberPrice(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(detail.getMemberPrice(), detail.getSaleTaxRate()));
		detail.setModifierId(loginUser.getUserId());
		detail.setModifierCode(loginUser.getUserCode());
		detail.setModifierName(loginUser.getUserName());
		detail.setUpdateTime(new Date());
		return detail;
	}

	private SafetyStock transformationToStock(SafetyStock stock,
			EnterpriseHeadquartersDetailVO vo, UserVO loginUser) {
		stock.setDefaultShelfId(vo.getDefaultShelfId());
		stock.setDefaultShelfName(vo.getDefaultShelfName());
		stock.setInventoryUp(vo.getInventoryUp());
		stock.setInventoryDown(vo.getInventoryDown());
		stock.setSafetyStock(vo.getSafetyStock());
		stock.setModifierId(loginUser.getUserId());
		stock.setModifierCode(loginUser.getUserCode());
		stock.setModifierName(loginUser.getUserName());
		stock.setUpdateTime(new Date());
		return stock;
	}

	private String validateSaveGoodsDetailByEnterprise(GoodsManage manage, EnterpriseHeadquartersDetailVO vo) {
		if(manage == null){
			return "商品管理ID错误！";
		}
		if(!manage.getEnterpriseId().equals(vo.getEnterpriseId().longValue()) || !manage.getGoodsId().equals(vo.getGoodsId())){
			return "药店ID或商品ID错误！";
		}
		return "";
	}

	@Override
	public GoodsManage selectGoodsManageById(Long id) {
		return goodsManageMapper.selectByPrimaryKey(id);
		
	}
	

}
