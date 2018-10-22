package com.rograndec.feijiayun.chain.business.online.purchase.smart.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.online.purchase.component.CalculateCartAmountComponent;
import com.rograndec.feijiayun.chain.business.online.purchase.component.CommonQueryConfiguration;
import com.rograndec.feijiayun.chain.business.online.purchase.constant.SmartCartConstant;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SmartSupplierDisplayDetailMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SmartSupplierDisplayMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SupplierDisplayDetailMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SupplierDisplayMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SupplierDisplayDetail;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.SmartSpecificPriceService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.ManualSearchQueryVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectMphSupplierRequestVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingSupplierVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSpecificPriceVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.search.MphGoodsSearchServiceClient;
import com.rograndec.feijiayun.chain.inf.search.vo.MphGoods;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphGoodsResult;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;

@Service
public class SmartSpecificPriceServiceImpl implements SmartSpecificPriceService{

	private final static Logger logger = LoggerFactory.getLogger(SmartSpecificPriceServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MphGoodsSearchServiceClient mphGoodsSearchServiceClient;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private SmartSupplierDisplayMapper smartSupplierDisplayMapper;
	
	@Autowired
	private SmartSupplierDisplayDetailMapper smartSupplierDisplayDetailMapper;
	
	@Autowired
	private SupplierDisplayMapper supplierDisplayMapper;
	
	@Autowired
	private SupplierDisplayDetailMapper supplierDisplayDetailMapper;
	
	@Autowired
	private CommonQueryConfiguration commonQueryConfiguration;
	
	@Autowired
	private CalculateCartAmountComponent calculateCartAmountComponent;
	
	@Autowired
    private RedisComponent redisComponent;
	
	@Value("${mph.impl.url}")
	private String mphImplUrl;
	
	@Value("${mph.user.url}")
	private String mphUserUrl;
	
	@Override
	public List<SmartSpecificPriceVO> selectSmartSpecificPrice(UserVO userVO,
			Long supplierId, Long goodsId, Long gId) throws Exception {
		User user = userMapper.selectByPrimaryKey(userVO.getUserId());
		
		if(user == null){
			throw new Exception("查不到用户数据");
		}
		
		if(redisComponent.get(getRedisKey(userVO)) == null){
			throw new Exception("购物车无商品信息");
		}
		
		String goodsName = "";
		String registrationCode = "";
		String specification = "";
		String manufacturer = "";
		
		SmartSourcingGoodsVO selectGoodsVO = getSelectGoodsVO(userVO, supplierId, goodsId, gId);
		
		if(selectGoodsVO != null){
			goodsName = StringUtils.isNotBlank(selectGoodsVO.getGoodsName())?selectGoodsVO.getGoodsName():selectGoodsVO.getgName();
			registrationCode = StringUtils.isNotBlank(selectGoodsVO.getRegistrationCode())?selectGoodsVO.getRegistrationCode().replaceAll("[^a-z^A-Z^0-9]", ""):"";
			specification = StringUtils.isNotBlank(selectGoodsVO.getGoodsSpecification())?selectGoodsVO.getGoodsSpecification():selectGoodsVO.getgSpecification();
			manufacturer = StringUtils.isNotBlank(selectGoodsVO.getManufacturer())?selectGoodsVO.getManufacturer():selectGoodsVO.getgManufacturer();
		}
		
		List<SmartSpecificPriceVO> objectList = new ArrayList<SmartSpecificPriceVO>();
		
		String supplierIds = commonQueryConfiguration.getSmartDisplay(userVO.getEnterpriseId());
		
		List<SupplierDisplayDetail> listSupLev = commonQueryConfiguration.getSupplierDisplayDetailList(userVO.getEnterpriseId());
		
		SearchMphGoodsResult result = null;
		try {
			
			result = mphGoodsSearchServiceClient.searchAll(goodsName, registrationCode, specification, manufacturer, supplierIds, "", user.getRgtUserId(), userVO.getEnterpriseId().intValue(), 0, 1000);
		} catch (Exception e) {
			
			logger.error("匹配供应商价格异常！", e);
			e.getStackTrace();
		}
		
		if(result != null && result.getGoodsList() != null){
			
			List<MphGoods> mphGoodsList = result.getGoodsList();
			
			if(mphGoodsList != null && mphGoodsList.size() > 0){
				
				Map map = new HashMap<>();
				List<SmartSpecificPriceVO> allUsable = new ArrayList<>();// 所有可用的价格集合
				List<SmartSpecificPriceVO> allLev = new ArrayList<>();// 设置优先级的价格集合
				
				for (MphGoods mphGoods : mphGoodsList) {
					
					SmartSpecificPriceVO dataJson1 = new SmartSpecificPriceVO();
					
					if ((0 == mphGoods.getPurchasePrice()) ||  mphGoods.getCanPurchased() == 0) {
						continue;
					}
					String mphSupplierId = String.valueOf(mphGoods.getSupplier().getId());
					List<MphGoods> listDetail = new ArrayList<MphGoods>();
					if (map.containsKey(mphSupplierId)) {
						listDetail = (List<MphGoods>) map.get(mphSupplierId);
						listDetail.add(mphGoods);
					} else {
						listDetail.add(mphGoods);
					}
					map.put(mphSupplierId, listDetail);

					dataJson1 = setSmartSpecificPriceVO(mphGoods, user.getRgtUserId());

					allUsable.add(dataJson1);
					
				}
				
				if(listSupLev != null){
					
					for (SupplierDisplayDetail object : listSupLev) {
	
						String mphSupplierId = object.getMphSupplierId()==null?"":object.getMphSupplierId().toString();
	
						if (map.containsKey(mphSupplierId)) {
	
							List<MphGoods> listDetail = (List<MphGoods>) map.get(mphSupplierId);
	
							for (MphGoods mph : listDetail) {
								SmartSpecificPriceVO dataJson1 = new SmartSpecificPriceVO();
	
								dataJson1 = setSmartSpecificPriceVO(mph, user.getRgtUserId());
	
								allLev.add(dataJson1);
							}
						}
					}
				}	
				objectList.addAll(allLev);

				allUsable.removeAll(allLev);

				Collections.sort(allUsable, new Comparator<SmartSpecificPriceVO>() {

					@Override
					public int compare(SmartSpecificPriceVO a, SmartSpecificPriceVO b) {
						String valA_1 = new String();
						String valB_1 = new String();

						try {
							valA_1 = a.getRetailPrice()==null?"":a.getRetailPrice().toString();
							valB_1 = b.getRetailPrice()==null?"":b.getRetailPrice().toString();
						} catch (JSONException e) {
							logger.error("对非优先级数据按价格排序错误", e);
						}
						int flag = valA_1.compareTo(valB_1);
						return flag;
					}
				});
				
				objectList.addAll(allUsable);
				
			}
		}
		
		return objectList;
	}
		
	/*private List<SupplierDisplayDetail> getSupplierDisplayDetailList(
			Long enterpriseId) {
		
		Enterprise en = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		
		SupplierDisplay dis = null;
		
		if(en.getAreaId() != null && en.getAreaId() != 0){
			dis = supplierDisplayMapper.selectByAreaId(en.getAreaId());
		}
		if(dis == null && en.getCityId() != null && en.getCityId() != 0){
			dis = supplierDisplayMapper.selectByCityId(en.getCityId());
		}
		if(dis == null && en.getProvinceId() != null && en.getProvinceId() != 0){
			dis = supplierDisplayMapper.selectByProvinceId(en.getProvinceId());
		}
		if(dis != null){
			List<SupplierDisplayDetail> detailList = supplierDisplayDetailMapper.selectBySupplierDisplayId(dis.getId());
		
			return detailList;
		}
		
		return null;
	}*/

	private SmartSourcingGoodsVO getSelectGoodsVO(UserVO userVO,
			Long supplierId, Long goodsId, Long gId) {
		SmartSourcingCartVO smartCart = (SmartSourcingCartVO)redisComponent.get(getRedisKey(userVO));
		if(smartCart != null && smartCart.getSupplierList() != null){
			for (SmartSourcingSupplierVO supplierVO : smartCart.getSupplierList()) {
				if(supplierId.equals(supplierVO.getMphSupplierId()) && supplierVO.getGoodsList() != null){
					for (SmartSourcingGoodsVO goodsVO : supplierVO.getGoodsList()) {
						if(supplierId == null || supplierId.equals(0L)){
							if(goodsId.equals(goodsVO.getGoodsId())){
								return goodsVO;
							}
						}else{
							if(gId.equals(goodsVO.getgId())){
								return goodsVO;
							}
						}
					}
				}
			}
		}
		return null;
	}

	private SmartSpecificPriceVO setSmartSpecificPriceVO(MphGoods mphGoods,
			Integer rgtUserId) {
		SmartSpecificPriceVO vo = new SmartSpecificPriceVO();
		vo.setMphGoodsId(mphGoods.getId());
		vo.setMphSupplierId(mphGoods.getSupplier().getId());
		vo.setRgtUserId(rgtUserId.longValue());
//		mphImplUrl="http://www.mypharma.com/";
//		mphUserUrl="http://user.mypharma.com/";
		vo.setMphUserUrl(mphUserUrl+"userInfos/saasLogin?uId="+rgtUserId);
		vo.setMphImplUrl(mphImplUrl+"saas/auth.html?code="+rgtUserId+"&callbackUrl="+mphImplUrl);
		vo.setMphGoodsName(mphGoods.getName());
		vo.setMphSpecification(mphGoods.getSpecification());
		vo.setMphManufacturer(mphGoods.getManufacturer());
		vo.setNameView(mphGoods.getName() + "<br>"
				+ mphGoods.getSpecification() + "<br>"
				+ mphGoods.getManufacturer());
		vo.setIsProcurement(mphGoods.getCanPurchased());
		vo.setRetailPrice(mphGoods.getPurchasePrice());
		vo.setMphSupplierName(mphGoods.getSupplier().getName());
		vo.setMatchingAmount(mphGoods.getSupplier().getIsMinPurchased()==0?-1:mphGoods.getSupplier().getMinPurchaseAmount());
		vo.setMatchingAmountName(mphGoods.getSupplier().getIsMinPurchased()==0?"无限制":String.valueOf(mphGoods.getSupplier().getMinPurchaseAmount()));
		vo.setInventoryQuantity(mphGoods.getInventoryQuantity());
		vo.setgCanSplit(mphGoods.getgCanSplit());
		vo.setgMiddlePackage(mphGoods.getgMiddlePackage());
		return vo;
	}

	@Override
	public Page selectManualSearch(UserVO userVO,
			ManualSearchQueryVO vo) throws Exception {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		User user = userMapper.selectByPrimaryKey(userVO.getUserId());
		
		if(user == null){
			throw new Exception("查不到用户数据");
		}

		String supplierIds = commonQueryConfiguration.getSmartDisplay(userVO.getEnterpriseId());
		
		int offset = commonQueryConfiguration.getOffsetByPageNoAndPageSize(vo.getPageNo(), vo.getPageSize());
		
		SearchMphGoodsResult result = null;
		
		try{
		
			result = mphGoodsSearchServiceClient.search(vo.getGoodsName(), vo.getSpecification(), vo.getManufacturer(), supplierIds, user.getRgtUserId(), userVO.getEnterpriseId().intValue(), offset, vo.getPageSize());
		
		} catch (Exception e) {
			
			logger.error("手动搜索接口异常！", e);
			e.getStackTrace();
		}
		
		List<SmartSpecificPriceVO> objectList = new ArrayList<SmartSpecificPriceVO>();
		
		int totalCount = 0;
		
		if(result != null && result.getGoodsList() != null){
			
			totalCount = result.getTotalCount();
			
			List<MphGoods> mphGoodsList = result.getGoodsList();
			
			if(mphGoodsList != null && mphGoodsList.size() > 0){
				
				for (MphGoods mphGoods : mphGoodsList) {
					
					SmartSpecificPriceVO dataJson1 = setSmartSpecificPriceVO(mphGoods, user.getRgtUserId());
					
					objectList.add(dataJson1);
				}
			}
		}
		
		page.setResult(objectList);
		
		page.setTotalRecord(totalCount);
		
		return page;
	}

	@Override
	public String selectMphSupplier(UserVO userVO, SelectMphSupplierRequestVO vo) {

		if(redisComponent.get(getRedisKey(userVO)) == null){
			return "购物车无商品信息";
		}else{
			
			SmartSourcingCartVO smartCart = (SmartSourcingCartVO)redisComponent.get(getRedisKey(userVO));
			
            List<SmartSourcingSupplierVO> preSupplierList = smartCart.getSupplierList();
            
            List<SmartSourcingSupplierVO> removeSupplierList = new ArrayList();
			
            if(preSupplierList != null){
            	
            	boolean flag = false;
				if(vo.getSupplierId().equals(vo.getMphSupplierId())){
					flag = true;//当前供应商与匹配钱供应商一致
				}
				
            	Iterator<SmartSourcingSupplierVO> iterator = preSupplierList.iterator();
            	
            	while(iterator.hasNext()) {
            		
            		SmartSourcingSupplierVO supplierVO = iterator.next();
            		
            	
					if(supplierVO.getMphSupplierId().equals(vo.getSupplierId())){
						
						Iterator<SmartSourcingGoodsVO> iteratorGoods = supplierVO.getGoodsList().iterator();
						
						while(iteratorGoods.hasNext()) {
							
							SmartSourcingGoodsVO goodsVO = iteratorGoods.next();
							
							if(vo.getSupplierId().equals(0L)){
								if(vo.getGoodsId().equals(goodsVO.getGoodsId())){
									if(flag){
										setSmartGoodsVO(goodsVO, vo, userVO);//当前供应商与匹配钱供应商一致
									}else{
//										smartSourcingGoodsVO = setNewSmartGoodsVO(goodsVO, vo, userVO);
										iteratorGoods.remove();
									}
								}
							}else{
								if(vo.getgId().equals(goodsVO.getgId())){
									if(flag){
										setSmartGoodsVO(goodsVO, vo, userVO);//当前供应商与匹配钱供应商一致
									}else{
//										smartSourcingGoodsVO = setNewSmartGoodsVO(goodsVO, vo, userVO);
										iteratorGoods.remove();
									}
								}
							}
								
						}
							
						if(supplierVO.getGoodsList() == null || supplierVO.getGoodsList().size() == 0){
						
							removeSupplierList.add(supplierVO);
						}
					}
				}
            	
            	preSupplierList.removeAll(removeSupplierList);
            	
            	boolean isMatchSupplier = true;
            	boolean isMatchGoods = true;
            	
            	//当前供应商与匹配钱供应商一致，把匹配成的新的商品放回购物车
            	if(!flag){
	            	for (SmartSourcingSupplierVO supplierVO : preSupplierList) {
	            		if(supplierVO.getMphSupplierId().longValue() == (vo.getMphSupplierId().longValue())){
	            			//原购物车包含新匹配的供应商
	            			isMatchSupplier = false;
	            			List<SmartSourcingGoodsVO> goodsList = supplierVO.getGoodsList();
							if(goodsList != null){
								for (SmartSourcingGoodsVO smartSourcingGoodsVO2 : goodsList) {
									if(vo.getMphGoodsId().equals(smartSourcingGoodsVO2.getgId())){
										//原购物车包含新匹配的商品
										setSmartGoodsVO(smartSourcingGoodsVO2, vo, userVO);
										
										isMatchGoods = false;
										break;
									}
								}
							}
							
							if(isMatchGoods){//原购物车不包含新匹配的商品
								SmartSourcingGoodsVO newGoodsVO = setNewSmartGoodsVOBySmartSpecificPriceVO(vo, userVO);
								
								if(goodsList != null){
									goodsList.add(newGoodsVO);
									
								}else{
									goodsList = new ArrayList<SmartSourcingGoodsVO>();
									goodsList.add(newGoodsVO);
								}
								
								supplierVO.setGoodsList(goodsList);
							
							}
							
							break;
	            		}
	            	}
	            	
	            	if(isMatchSupplier){
	            		
	            		SmartSourcingSupplierVO supplierVO = setNewSmartSupplierVOBySmartSpecificPriceVO(vo, userVO);
	            		
	            		preSupplierList.add(supplierVO);
	            	}
            	}	
            	
            	try {
					calculateCartAmountComponent.reCalculateSmartCart(smartCart);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("选择供应商后计算购物车金额失败！", e);
				}
            	
            	redisComponent.set(getRedisKey(userVO), smartCart);
            	
            }
		}
		
		return null;
	}

	private SmartSourcingSupplierVO setNewSmartSupplierVOBySmartSpecificPriceVO(
			SelectMphSupplierRequestVO vo, UserVO userVO) {

		SmartSourcingGoodsVO smartGoodsVO = setNewSmartGoodsVOBySmartSpecificPriceVO(vo, userVO);
		List<SmartSourcingGoodsVO> list = new ArrayList<>();
		list.add(smartGoodsVO);
		SmartSourcingSupplierVO su = new SmartSourcingSupplierVO();
		su.setMphSupplierId(vo.getMphSupplierId().longValue());
		su.setMphSupplierName(vo.getMphSupplierName());
		su.setMatchingAmount(BigDecimal.valueOf(vo.getMatchingAmount()));
		su.setEntrepriseId(userVO.getEnterpriseId());
		su.setChecked(true);
		su.setBillingAmount(smartGoodsVO.getSubtotal());
		su.setGoodsList(list);
		return su;
	}

	private SmartSourcingGoodsVO setNewSmartGoodsVOBySmartSpecificPriceVO(
			SelectMphSupplierRequestVO vo, UserVO userVO) {
		SmartSourcingGoodsVO goodsVO = new SmartSourcingGoodsVO();
		goodsVO.setgId(vo.getMphGoodsId().longValue());
		goodsVO.setgManufacturer(vo.getMphManufacturer());
		goodsVO.setgName(vo.getMphGoodsName());
		goodsVO.setgSpecification(vo.getMphSpecification());
		goodsVO.setInventoryQuantity(vo.getInventoryQuantity() == null ? 0 : vo.getInventoryQuantity().intValue());
		goodsVO.setgCanSplit(vo.getgCanSplit());
		goodsVO.setgMiddlePackage(vo.getgMiddlePackage());
		goodsVO.setEntrepriseId(userVO.getEnterpriseId());
		if(vo.getgCanSplit().equals(0)){
			goodsVO.setQuantity(vo.getgMiddlePackage());
		}else{
			goodsVO.setQuantity(1);
		}
		goodsVO.setRetailPrice(BigDecimal.valueOf(vo.getRetailPrice()));
		goodsVO.setChecked(true);
		goodsVO.setSubtotal(goodsVO.getRetailPrice().multiply(BigDecimal.valueOf(goodsVO.getQuantity())));
		goodsVO.setCreaterId(userVO.getUserId());
		goodsVO.setCreaterName(userVO.getUserName());
		goodsVO.setAddCartTime(new Date());
		return goodsVO;
		
	}

	private SmartSourcingGoodsVO setNewSmartGoodsVO(SmartSourcingGoodsVO gvo,
			SelectMphSupplierRequestVO vo, UserVO userVO) {
		SmartSourcingGoodsVO goodsVO = new SmartSourcingGoodsVO();
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(gvo, goodsVO);
		goodsVO.setgId(vo.getMphGoodsId().longValue());
		goodsVO.setgManufacturer(vo.getMphManufacturer());
		goodsVO.setgName(vo.getMphGoodsName());
		goodsVO.setgSpecification(vo.getMphSpecification());
		goodsVO.setInventoryQuantity(vo.getInventoryQuantity() == null ? 0 : vo.getInventoryQuantity().intValue());
		goodsVO.setgCanSplit(vo.getgCanSplit());
		goodsVO.setgMiddlePackage(vo.getgMiddlePackage());
		goodsVO.setEntrepriseId(userVO.getEnterpriseId());
		if(vo.getgCanSplit().equals(0)){
			goodsVO.setQuantity(vo.getgMiddlePackage());
		}else{
			goodsVO.setQuantity(1);
		}
		goodsVO.setRetailPrice(BigDecimal.valueOf(vo.getRetailPrice()));
		goodsVO.setChecked(true);
		goodsVO.setSubtotal(goodsVO.getRetailPrice().multiply(BigDecimal.valueOf(goodsVO.getQuantity())));
		goodsVO.setCreaterId(userVO.getUserId());
		goodsVO.setCreaterName(userVO.getUserName());
		return goodsVO;
	}
	
	private SmartSourcingGoodsVO setSmartGoodsVO(SmartSourcingGoodsVO goodsVO,
			SelectMphSupplierRequestVO vo, UserVO userVO) {
		
		if(vo.getgCanSplit().equals(0)){
			if(goodsVO.getgId() != null && !goodsVO.getgId().equals(0L)){
				goodsVO.setQuantity(vo.getgMiddlePackage()+goodsVO.getQuantity());
			}else{
				goodsVO.setQuantity(vo.getgMiddlePackage());
			}
		}else{
			if(goodsVO.getgId() != null && !goodsVO.getgId().equals(0L)){
				goodsVO.setQuantity(goodsVO.getQuantity() + 1);
			}else{
				goodsVO.setQuantity(1);
			}
		}
		
		goodsVO.setgId(vo.getMphGoodsId().longValue());
		goodsVO.setgManufacturer(vo.getMphManufacturer());
		goodsVO.setgName(vo.getMphGoodsName());
		goodsVO.setgSpecification(vo.getMphSpecification());
		goodsVO.setInventoryQuantity(vo.getInventoryQuantity() == null ? 0 : vo.getInventoryQuantity().intValue());
		goodsVO.setgCanSplit(vo.getgCanSplit());
		goodsVO.setgMiddlePackage(vo.getgMiddlePackage());
		goodsVO.setEntrepriseId(userVO.getEnterpriseId());
		
		goodsVO.setRetailPrice(BigDecimal.valueOf(vo.getRetailPrice()));
		goodsVO.setChecked(true);
		goodsVO.setSubtotal(goodsVO.getRetailPrice().multiply(BigDecimal.valueOf(goodsVO.getQuantity())));
		goodsVO.setCreaterId(userVO.getUserId());
		goodsVO.setCreaterName(userVO.getUserName());
		return goodsVO;
	}

	/**
	 * @Description: TODO查询saas_smart_display表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年11月22日 下午7:47:40 
	 * @param enterpriseId
	 * @return 
	 * @return String
	 */
	/*private String getSmartDisplay(Long enterpriseId) {
		List<SmartDisplay> disList = smartDisplayMapper.selectByEnterpriseId(enterpriseId);
		StringBuilder sb = new StringBuilder();
		if(disList != null && !disList.isEmpty()){
			for (SmartDisplay smartDisplay : disList) {
				List<SmartDisplayDetail> detailList = smartDisplayDetailMapper.selectBySmartConfigId(smartDisplay.getId());
				if(detailList != null){
					for (int i = 0; i < detailList.size(); i++) {
						if(i == detailList.size()-1){
							sb.append(detailList.get(i).getMphSupplierId());
						}else{
							sb.append(detailList.get(i).getMphSupplierId()).append(",");
						}
					}
				}
			}
		}
		return sb.toString();
	}*/

	private String getRedisKey(UserVO loginUser) {
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(SmartCartConstant.SMART_PURCHASE_CART);
        redisKey.append(loginUser.getEnterpriseId());
        return redisKey.toString();
    }
}
