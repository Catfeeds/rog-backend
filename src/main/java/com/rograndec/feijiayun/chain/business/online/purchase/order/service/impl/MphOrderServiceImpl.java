package com.rograndec.feijiayun.chain.business.online.purchase.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.online.purchase.order.service.MphOrderService;
import com.rograndec.feijiayun.chain.business.online.purchase.order.vo.MphOrderVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.OnlineMarketService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectSmartGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectSmartSupplierVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.mph.GetSaasOrderPageComponent;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

/**
 * 
 * @ClassName: MphOrderServiceImpl   
 * @Description: 获取MPH订单数据
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月23日 上午11:47:50
 */
@Service
public class MphOrderServiceImpl implements MphOrderService {

	@Autowired
	private GetSaasOrderPageComponent getSaasOrderPageComponent;
	@Autowired
	private OnlineMarketService onlineMarketService;

	@Override
	public Page<List<MphOrderVO>> getMphOrder(Integer pageNo, Integer pageSize, String search, UserVO userVO) {
		Long rgtUserId = userVO.getRgtUserId() == null ? null : userVO.getRgtUserId().longValue();
		Long rgtEnterpriseId = userVO.getRgtEnterpriseId() == null ? null : userVO.getRgtEnterpriseId().longValue();
		String mphResult = getSaasOrderPageComponent.getSaasOrderPageByParam(rgtUserId, rgtEnterpriseId, pageNo, pageSize, search);
		Page<List<MphOrderVO>> page = new Page<>();
		if (StringUtils.isNotBlank(mphResult) && !(mphResult.contains("html") || mphResult.contains("DOCTYPE"))) {
			List<MphOrderVO> mphOrderList = new ArrayList<>();
			JSONObject jasonObject = new JSONObject();
			jasonObject = JSONObject.parseObject(mphResult);
			if (jasonObject.getBooleanValue("success") == true && jasonObject.getString("data") != null) {
				// 获取数据
				JSONObject jsonData = (JSONObject) jasonObject.get("data");
				if (jsonData == null || jsonData.isEmpty()) {
					return page;
				}
				JSONArray json_array = jsonData.getJSONArray("orderList");
				if (json_array == null || json_array.isEmpty()) {
					return page;
				}
				// 存放处理后的数据;
				for (int i = 0; i < json_array.size(); i++) {
					JSONObject jsonObject = json_array.getJSONObject(i);
					if (jsonObject.isEmpty() || null == jsonObject) {
						break;
					}
					String oid = jsonObject.get("oid") == null ? "" : jsonObject.get("oid").toString();
					String osn = jsonObject.getString("osn") == null ? "" : jsonObject.getString("osn"); // 订单号
					Long oaddTimeLong = jsonObject.get("oaddTime") == null ? 0L : jsonObject.getLong("oaddTime");// 下单时间
					String oaddTime = DateUtils.DateToString(new Date(oaddTimeLong), DateStyle.YYYY_MM_DD_HH_MM_SS_CN);
					String osellerName = jsonObject.getString("osellerName") == null ? "" : jsonObject.getString("osellerName");// 供应商
					BigDecimal ototalPrice = jsonObject.get("ototalPrice") == null ? BigDecimal.ZERO : jsonObject.getBigDecimal("ototalPrice");// 实付/应收金额
					Integer ostatus = jsonObject.get("ostatus") == null ? 0 : jsonObject.getInteger("ostatus");// 订单状态对应的参数
					Integer opaymentMethod = jsonObject.get("opaymentMethod") == null ? 0 : jsonObject.getInteger("opaymentMethod");// 付款方式对应的参数
					Integer payStatus = jsonObject.get("payStatus") == null ? 0 : jsonObject.getInteger("payStatus");// 付款状态对应的参数
					String sourceCode = jsonObject.getString("sourceCode") == null ? "" : jsonObject.getString("sourceCode");// 供应商
					String orderType = jsonObject.getString("orderType") == null ? "" : jsonObject.getString("orderType");// 供应商
					// 减 退货金额
					ototalPrice = ototalPrice.subtract((jsonObject.get("backPrice") == null || StringUtils.isBlank(jsonObject.get("backPrice").toString())) ?
							BigDecimal.ZERO : jsonObject.getBigDecimal("backPrice"));
					// 减 驳回金额
					ototalPrice = ototalPrice.subtract((jsonObject.get("rebutPrice") == null || StringUtils.isBlank(jsonObject.get("rebutPrice").toString())) ?
							BigDecimal.ZERO : jsonObject.getBigDecimal("rebutPrice"));

					MphOrderVO mphOrderVO = new MphOrderVO();
					mphOrderVO.setuId(userVO.getRgtUserId());
					mphOrderVO.setMphImplUrl(getSaasOrderPageComponent.getMphUrl());
					mphOrderVO.setOid(oid);
					mphOrderVO.setOsn(osn);
					mphOrderVO.setOaddTime(oaddTime);
					mphOrderVO.setOsellerName(osellerName);
					mphOrderVO.setPayAmount(ototalPrice);
					mphOrderVO.setPayStatus(payStatus);
					mphOrderVO.setOpaymentMethod(opaymentMethod);
					mphOrderVO.setSourceCode(sourceCode);
					mphOrderVO.setOrderType(orderType);
					mphOrderVO.setOstatus(ostatus);
					mphOrderList.add(mphOrderVO);
				}

				Integer recordCount = 0;
				JSONObject pagerInfo = (JSONObject) jsonData.get("pagerInfo");
				if (null != pagerInfo) {
					recordCount = pagerInfo.get("rowsCount") == null ? 0 : pagerInfo.getInteger("rowsCount");
				} else {
					recordCount = 0;
				}
				page.setResult(mphOrderList);
				page.setTotalRecord(recordCount);
			}
		}
		return page;
	}

	@Override
	public void reBuy(UserVO userVO, String mphResult) throws Exception {
		JSONObject jasonObject = JSONObject.parseObject(mphResult);
		JSONArray jsonArray = new JSONArray();
		if (jasonObject.get("code") != null && "0".equals(jasonObject.get("code").toString())) {
			if (jasonObject.get("data") == null || org.apache.commons.lang3.StringUtils.isBlank(jasonObject.get("data").toString())
					|| "null".equals(jasonObject.get("data").toString())) {
				String msg = (jasonObject.get("message") == null || org.apache.commons.lang3.StringUtils.isBlank(jasonObject.get("message").toString())
						|| "null".equals(jasonObject.get("message").toString()))?"":jasonObject.get("message").toString();
				throw new BusinessException(msg);	
			} else {
				jsonArray = com.alibaba.fastjson.JSONArray.parseArray(jasonObject.get("data").toString());
			}
		} else {
		}

		if (jsonArray != null && jsonArray.size() > 0) {
			SelectSmartSupplierVO supplierVO = addOrderGoods(jsonArray, userVO);
			onlineMarketService.insertCart(userVO,supplierVO);
		}

	}

	private SelectSmartSupplierVO addOrderGoods(JSONArray jsonArray, UserVO userVO) {
		SelectSmartSupplierVO supplierVO = new SelectSmartSupplierVO();
		List<SelectSmartGoodsVO> goodsVOList = new ArrayList<>();
		for (Object object : jsonArray) {
			SelectSmartGoodsVO goodsVO = new SelectSmartGoodsVO();
			JSONObject ob = (JSONObject) object;
			Long gId = (ob.get("gid") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("gid").toString())) ? 0 : Long.parseLong(ob.get("gid").toString());

			String gName = (ob.get("gName") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("gName").toString())) ? "" : ob.get("gName").toString();

			String gSpecification = (ob.get("gSpecifications") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("gSpecifications").toString())) ? "" : ob.get("gSpecifications").toString();

			String gManufacturer = (ob.get("gManufacture") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("gManufacture").toString())) ? "" : ob.get("gManufacture").toString();

			Long mphSupplierId = (ob.get("suId") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("suId").toString())) ? 0 : Long.parseLong(ob.get("suId").toString());

			String mphSupplierName = (ob.get("suName") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("suName").toString())) ? "" : ob.get("suName").toString();

			Integer gCanSplit = (ob.get("gCanSplit") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("gCanSplit").toString())) ? 1 : Integer.parseInt(ob.get("gCanSplit").toString());

			Integer gMiddlePackage = (ob.get("gMiddlePackage") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("gMiddlePackage").toString())) ? 0 : Integer.parseInt(ob.get("gMiddlePackage").toString());

			BigDecimal lastBuyNumber = (ob.get("lastBuyNumber") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("lastBuyNumber").toString())) ? null : ob.getBigDecimal("lastBuyNumber");

			BigDecimal newPrice = (ob.get("buyPrice") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("buyPrice").toString())) ? null : ob.getBigDecimal("buyPrice");

			BigDecimal inventoryQuantity = (ob.get("stock") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("stock").toString())) ? null : ob.getBigDecimal("stock");

			//是否限制起配金额。0否1是
			Integer suIsLimit = (ob.get("suIsLimit") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("suIsLimit").toString())) ? 0 : Integer.parseInt(ob.get("suIsLimit").toString());

			BigDecimal suDistributionAmount = (ob.get("suDistributionAmount") == null
					|| org.apache.commons.lang3.StringUtils.isBlank(ob.get("suDistributionAmount").toString())) ? null : ob.getBigDecimal("suDistributionAmount");

			goodsVO.setGoodsId(gId);
			goodsVO.setGoodsName(gName);
//			goodsVO.setRetailPrice(newPrice);
			goodsVO.setPurchasePrice(newPrice==null?0:newPrice.doubleValue());
			goodsVO.setQuantity(lastBuyNumber == null ? 1 : lastBuyNumber.intValue());//上次购买数量
			goodsVO.setSpecification(gSpecification);
			goodsVO.setManufacturer(gManufacturer);
			goodsVO.setInventoryQuantity(inventoryQuantity.intValue());
			goodsVO.setCanSplit(gCanSplit);
			goodsVO.setMiddlePackage(gMiddlePackage);
			goodsVOList.add(goodsVO);
			if(suIsLimit == 1){
				supplierVO.setMatchingAmount(suDistributionAmount);
			}else{
				supplierVO.setMatchingAmount(new BigDecimal(-1));
			}
			supplierVO.setMphSupplierId(mphSupplierId);
			supplierVO.setMphSupplierName(mphSupplierName);
		}
		supplierVO.setSelectSmartGoodsVO(goodsVOList);
		return supplierVO;
	}
}
