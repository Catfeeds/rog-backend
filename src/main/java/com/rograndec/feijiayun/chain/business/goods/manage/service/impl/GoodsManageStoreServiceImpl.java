package com.rograndec.feijiayun.chain.business.goods.manage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.goods.manage.dao.GoodsManageMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.manage.service.GoodsManageStoreService;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsStoreDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsStorePageVO;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.GoodsManageStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
 
@Service
public class GoodsManageStoreServiceImpl implements GoodsManageStoreService{

	@Autowired
	GoodsManageMapper goodsManageMapper;
	
	@Autowired
	StockMapper stockMapper;

	@Autowired
	SafetyStockMapper safetyStockMapper;
	
	@Autowired
	PriceOrderDetailMapper priceOrderDetailMapper;
	
	@Autowired
	private GoodsTaxRateMapper goodsTaxRateMapper;
	
	@Override
	public List<GoodsStorePageVO> selectGoodsManagePage(int pageNo,
			int pageSize, Long enterpriseId,Integer type, String key, String order,
			String sort, Page page) {
		
		if(type != null && type == 9){//全部
			type = null;
		}
		
		if("goodsCode".equals(order)){
			order = "d.code";
		}else{
			order = "";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "";
		}
		
		Long totalRecord = goodsManageMapper.queryStoreCountByGoodsParams(enterpriseId,key, type);
		
		List<GoodsStorePageVO> list = goodsManageMapper.selectGoodsStoreVOByGoodsParams(page.getStart(), 
				pageSize, enterpriseId, type, key, order, sort);
		
		page.setTotalRecord(totalRecord.intValue());
		
		if(list != null && list.size() > 0){
			
			List<Long> goodsId = new ArrayList<>();//商品IDList
			List<Long> safetyStockId = new ArrayList<>();//安全库存List
			List<Long> priceOrderDtlId = new ArrayList<>();//价格清单明细ID
			
			for (GoodsStorePageVO vo : list) {
				
				safetyStockId.add(vo.getSafetyStockId());
				priceOrderDtlId.add(vo.getPriceOrderDtlId());
				goodsId.add(vo.getGoodsId());
				
				vo.setManageStatusName(GoodsManageStatus.getName(vo.getManageStatus()));
				vo.setShelfStatus(getShelfStatus(vo.getManageStatus()));
//				vo.setShelfStatusName(GoodsManageStatus.getName(vo.getShelfStatus()));
			}
			
			//批量查询库存
			List<Map<String, Object>> mapList = stockMapper.selectGoodsQuantityByEnterpriseIdAndInGoodsId(enterpriseId, goodsId);
			Map<String, Object> map = new HashMap<String, Object>();
			if(mapList != null){
				for (Map<String, Object> map2 : mapList) {
					map.put(map2.get("goodsId").toString(), map2.get("quantity"));
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
			for (GoodsStorePageVO vo : list) {
				if(map.containsKey(vo.getGoodsId().toString())){//设置库存数量
					vo.setStockQuantity(new BigDecimal(map.get(vo.getGoodsId().toString()).toString()));
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

	private Integer getShelfStatus(Integer manageStatus) {
		switch (manageStatus) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 0;
		case 3:
			return 0;
		case 4:
			return 1;
		default:
			break;
		}
		return null;
	}

	@Override
	public String saveGoodsStoreDetail(GoodsStoreDetailVO vo, UserVO loginUser) throws Exception {
		
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
			GoodsStoreDetailVO vo, UserVO loginUser) throws Exception {
//		detail.setRetailPrice(vo.getRetailPrice());
//		detail.setMemberPrice(vo.getMemberPrice());
//		detail.setSaleTaxRateId(vo.getSaleTaxRateId());
//		GoodsTaxRate rate = goodsTaxRateMapper.selectByPrimaryKey(vo.getSaleTaxRateId());
//		if(rate == null){
//			throw new Exception("税率ID："+vo.getSaleTaxRateId()+"查不到数据！");
//		}
//		detail.setSaleTaxRate(rate.getTaxRate());
//		detail.setNotaxRetailPrice(vo.getNotaxRetailPrice());
//		detail.setNotaxMemberPrice(vo.getNotaxMemberPrice());
		
//		detail.setNotaxRetailPrice(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(detail.getRetailPrice(), detail.getSaleTaxRate()));
//		detail.setNotaxMemberPrice(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(detail.getMemberPrice(), detail.getSaleTaxRate()));

		detail.setModifierId(loginUser.getUserId());
		detail.setModifierCode(loginUser.getUserCode());
		detail.setModifierName(loginUser.getUserName());
		detail.setUpdateTime(new Date());
		return detail;
	}

	
	private SafetyStock transformationToStock(SafetyStock stock,
			GoodsStoreDetailVO vo, UserVO loginUser) {
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

	private String validateSaveGoodsDetailByEnterprise(GoodsManage manage, GoodsStoreDetailVO vo) {
		if(manage == null){
			return "商品管理ID错误！";
		}
		if(!manage.getGoodsId().equals(vo.getGoodsId())){
			return "商品ID错误！";
		}
		return "";
	}
	
	@Override
	public Map<String, String> batchOnShelfByIds(String[] ids, UserVO loginUser) throws Exception{
		
		Map<String, String> map = new HashMap<String, String>();
		
		List<Long> goodsManageIds = new ArrayList<>();//商品管理List
		for (String id : ids) {
			if(StringUtils.isNotBlank(id)){
				try {
					goodsManageIds.add(Long.parseLong(id));
				} catch (Exception e) {
					map.put("errMsg", "商品管理ID中存在错误数据:"+id);
					return map;
				}
			}
		}
		
		List<GoodsManage> manageList = goodsManageMapper.selectByIds(goodsManageIds);
		
		String msg = validateOnOffShelfData(ids, loginUser, manageList);
		
		if(StringUtils.isNotBlank(msg)){
			map.put("errMsg", msg);
			return map;
		}
		
		int total = 0;
		
//		for (GoodsManage goodsManage : manageList) {
//			goodsManage.setStatus(1);//已上架
//			goodsManage.setModifierId(loginUser.getUserId());
//			goodsManage.setModifierCode(loginUser.getUserCode());
//			goodsManage.setModifierName(loginUser.getUserName());
//			goodsManage.setCreateTime(new Date());
//			int count = goodsManageMapper.updateByPrimaryKeySelective(goodsManage);
//
//			total = total + count;
//		}
		for(GoodsManage manage:manageList){
			Long goodsId = manage.getGoodsId();
			Long enterpriseId = loginUser.getEnterpriseId();
			Long parentId = loginUser.getParentId();
			BigDecimal stockQuantityTotal = stockMapper.getQuantityTotalByGoodsId(goodsId, enterpriseId);
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("enterpriseId", enterpriseId);
			paramMap.put("parentId", parentId);
			paramMap.put("goodsId", goodsId);
			if(stockQuantityTotal != null){
				List<GoodsManage> goodsManageList = goodsManageMapper.selectByParamMap(paramMap);
				if(stockQuantityTotal.compareTo(BigDecimal.ZERO)>0){
					for(GoodsManage goodsManage:goodsManageList){
						goodsManage.setStatus(GoodsManageStatus.ON_SALE.getCode());
						UserEnterpriseUtils.setUserCreateOrModify(goodsManage,loginUser,false);
						int count = goodsManageMapper.updateByPrimaryKeySelective(goodsManage);
						total = total + count;
					}
				}else if(stockQuantityTotal.compareTo(BigDecimal.ZERO)==0){
					for(GoodsManage goodsManage:goodsManageList){
						goodsManage.setStatus(GoodsManageStatus.SOLD_OUT.getCode());
						UserEnterpriseUtils.setUserCreateOrModify(goodsManage,loginUser,false);
						int count = goodsManageMapper.updateByPrimaryKeySelective(goodsManage);
						total = total + count;
					}
				}
			}else {
				List<GoodsManage> goodsManageList = goodsManageMapper.selectByParamMap(paramMap);
				for(GoodsManage goodsManage:goodsManageList){
					goodsManage.setStatus(GoodsManageStatus.SOLD_OUT.getCode());
					UserEnterpriseUtils.setUserCreateOrModify(goodsManage,loginUser,false);
					int count = goodsManageMapper.updateByPrimaryKeySelective(goodsManage);
					total = total + count;
				}
			}
		}

		map.put("total", String.valueOf(total));
		
		return map;
	}

	private String validateOnOffShelfData(String[] ids, UserVO loginUser,
			List<GoodsManage> manageList) {
		
		if(manageList == null || manageList.size() == 0){
			return "商品管理ID错误，查不到数据";
		}
		
		for (GoodsManage goodsManage : manageList) {
			if(!goodsManage.getEnterpriseId().equals(loginUser.getEnterpriseId())){
				return "当前用户无操作商品管理ID:"+goodsManage.getId()+"权限";
			}
		}
		return null;
	}

	@Override
	public Map<String, String> batchOffShelfByIds(String[] ids, UserVO loginUser) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		List<Long> goodsManageIds = new ArrayList<>();//商品管理List
		for (String id : ids) {
			if(StringUtils.isNotBlank(id)){
				try {
					goodsManageIds.add(Long.parseLong(id));
				} catch (Exception e) {
					map.put("errMsg", "商品管理ID中存在错误数据:"+id);
					return map;
				}
			}
		}
		
		List<GoodsManage> manageList = goodsManageMapper.selectByIds(goodsManageIds);
		
		String msg = validateOnOffShelfData(ids, loginUser, manageList);
		
		if(StringUtils.isNotBlank(msg)){
			map.put("errMsg", msg);
			return map;
		}
		
		int total = 0;
		
		for (GoodsManage goodsManage : manageList) {
			goodsManage.setStatus(4);//已下架
			goodsManage.setModifierId(loginUser.getUserId());
			goodsManage.setModifierCode(loginUser.getUserCode());
			goodsManage.setModifierName(loginUser.getUserName());
			goodsManage.setCreateTime(new Date());
			int count = goodsManageMapper.updateByPrimaryKeySelective(goodsManage);
			
			total = total + count;
		}
		
		map.put("total", String.valueOf(total));
		
		return map;
	}

}
