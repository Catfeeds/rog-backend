package com.rograndec.feijiayun.chain.inf.mph.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusinessVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartGoodVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.component.CalculateCartAmountComponent;
import com.rograndec.feijiayun.chain.business.online.purchase.constant.OnlineType;
import com.rograndec.feijiayun.chain.business.online.purchase.constant.SmartCartConstant;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.impl.SmartSpecificPriceServiceImpl;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingSupplierVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.inf.mph.service.RemoveSaasCartService;

@Service
public class RemoveSaasCartServiceImpl implements RemoveSaasCartService{
	
	private final static Logger logger = LoggerFactory.getLogger(SmartSpecificPriceServiceImpl.class);

	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
    private RedisComponent redisComponent;
	
	@Autowired
    private CalculateCartAmountComponent calculateCartAmountComponent;
	
	@Override
	public void deleteCartByMph(JSONObject ob) {

		//{dataJson={"gid":[8618314,8618069],"uId":5963338,"eId":2602505}}
		String gid = ob.get("gid") != null ? ob.get("gid").toString() : "";
		String uId = ob.get("uId") != null ? ob.get("uId").toString() : "";
		String eId = ob.get("eId") != null ? ob.get("eId").toString() : "";
		Integer saasType = ob.get("saasType") != null ? ob.getInteger("saasType") : 0;
		Map<String, String> gIds = new HashMap<String, String>();
		if (!StringUtils.isBlank(gid)) {
			JSONArray array = JSONArray.parseArray(gid);
			for (int i = 0; i < array.size(); i++) {
				gIds.put(array.get(i).toString(), array.get(i).toString());
			}
		}
		
		if(StringUtils.isBlank(eId) || StringUtils.isBlank(uId) || gIds.isEmpty()){
			return;
		}
		
		List<Enterprise> enList = enterpriseMapper.selectByRgtEnterpriseId(eId);
		
		if(saasType == OnlineType.CENTRALIZED.getType()){//删除集采购物车
			
			if(enList != null && enList.size() > 0){
				
				for (Enterprise enterprise : enList) {

					CentralizedCartVO centralizedCart = (CentralizedCartVO)redisComponent.get(getCentralizedRedisKey(enterprise.getId()));
				
					if(centralizedCart != null){
						
						List<CentralizedCartBusinessVO> supplierList = centralizedCart.getSupplierList();
						
						if(supplierList != null){
							
							Iterator<CentralizedCartBusinessVO> iterator = supplierList.iterator();
//							for (CentralizedCartBusinessVO suvo : supplierList) {
							while(iterator.hasNext()) {
								
								CentralizedCartBusinessVO supplierVO = iterator.next();
								
								List<CentralizedCartGoodVO> goodsList = supplierVO.getGoodsList();
								
								if(goodsList != null){
									
									Iterator<CentralizedCartGoodVO> iteratorGoods = goodsList.iterator();
									
									while(iteratorGoods.hasNext()) {
									
										CentralizedCartGoodVO goodsVO = iteratorGoods.next();
										
										if(goodsVO.getgId() != null && gIds.containsKey(goodsVO.getgId().toString())){
											
											iteratorGoods.remove();
										}
									}
								}
								if(supplierVO.getGoodsList() == null || supplierVO.getGoodsList().size() == 0){
								
									iterator.remove();
								}
							}
						}
						
						try {
							calculateCartAmountComponent.reCalculateCentralizedCart(centralizedCart);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("重新计算购物车金额异常！", e);
						}
						
						redisComponent.set(getCentralizedRedisKey(enterprise.getId()),centralizedCart);
					}
				}
			}
			
		}else if(saasType == OnlineType.SMART_SOURCING.getType()){//删除智能采购购物车
			
			if(enList != null && enList.size() > 0){
				
				for (Enterprise enterprise : enList) {

					SmartSourcingCartVO smartCart = (SmartSourcingCartVO)redisComponent.get(getSmartRedisKey(enterprise.getId()));
				
					if(smartCart != null){
						
						List<SmartSourcingSupplierVO> supplierList = smartCart.getSupplierList();
						
						if(supplierList != null){
							
							Iterator<SmartSourcingSupplierVO> iterator = supplierList.iterator();
							while(iterator.hasNext()) {
								
								SmartSourcingSupplierVO supplierVO = iterator.next();
								
								List<SmartSourcingGoodsVO> goodsList = supplierVO.getGoodsList();
								
								if(goodsList != null){
									
									Iterator<SmartSourcingGoodsVO> iteratorGoods = goodsList.iterator();
									while(iteratorGoods.hasNext()) {	

										SmartSourcingGoodsVO smartSourcingGoodsVO = iteratorGoods.next();
										
										if(smartSourcingGoodsVO.getgId() != null && gIds.containsKey(smartSourcingGoodsVO.getgId().toString())){
											
											iteratorGoods.remove();
										}
									}
								}
								
								if(supplierVO.getGoodsList() == null || supplierVO.getGoodsList().size() == 0){
								
									iterator.remove();
								}
							}
						}
						
						try {
							calculateCartAmountComponent.reCalculateSmartCart(smartCart);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("重新计算购物车金额异常！", e);
						}
						
						redisComponent.set(getSmartRedisKey(enterprise.getId()),smartCart);
					}
				}
			}
		}	
	}
	
	private String getSmartRedisKey(Long enterpriseId) {
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(SmartCartConstant.SMART_PURCHASE_CART);
        redisKey.append(enterpriseId);
        return redisKey.toString();
    }
	
	private String getCentralizedRedisKey(Long enterpriseId) {
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(SmartCartConstant.CENTRALIZED_PURCHASE_CART);
        redisKey.append(enterpriseId);
        return redisKey.toString();
    }

}
