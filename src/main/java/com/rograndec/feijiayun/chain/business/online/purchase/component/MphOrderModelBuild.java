package com.rograndec.feijiayun.chain.business.online.purchase.component;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusinessVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartGoodVO;
import com.rograndec.feijiayun.chain.business.online.purchase.constant.OnlineType;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingSupplierVO;
import com.rograndec.feijiayun.chain.utils.Base64Util;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

public class MphOrderModelBuild {
	
	private Long rgtUserId;
	
	private String mphImplUrl;
	
	public MphOrderModelBuild(Long rgtUserId, String mphImplUrl) {
		this.rgtUserId = rgtUserId;
		this.mphImplUrl = mphImplUrl;
	}
	
	/**
	 * @Description: TODO构建集采去下单 数据
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年11月20日 下午9:07:06 
	 * @param businessList
	 * @return
	 * @throws Exception 
	 * @return PlaceOrderDataVO
	 */
	public PlaceOrderDataVO centralizedOrderModelBuild(List<CentralizedCartBusinessVO> businessList) throws Exception{
		
		PlaceOrderDataVO vo = new PlaceOrderDataVO();
		vo.setUrl(mphImplUrl + "saas/auth.html");
		vo.setCode(rgtUserId);
		vo.setCallbackUrl("/balance.html");
		
//		vo.setCode(5965082L);
//		vo.setUrl("http://www.test.wdyy.com.cn/saas/auth.html");
//		vo.setCallbackUrl("/balance.html");
		
		if(businessList != null){
			JSONArray ar = new JSONArray();
			JSONObject ob = null;
			for (CentralizedCartBusinessVO cartBusiness : businessList) {
				if(cartBusiness != null && cartBusiness.getGoodsList() != null){
					for (CentralizedCartGoodVO cartGood : cartBusiness.getGoodsList()) {
						ob = new JSONObject();
						ob.put("gid", cartGood.getgId());
						ob.put("suId", cartBusiness.getMphSupplierId());
						ob.put("number", cartGood.getQuantity());
						ob.put("price", cartGood.getRetailPrice());
						ob.put("goodsSource", "1"); //0智能采购,1菲加云集采
						ob.put("cartAddTime", DateUtils.DateToString(cartGood.getAddCartTime(), "yyyy-MM-dd HH:mm:ss"));
						ar.add(ob);
					}
				}
			}
			StringBuilder sbOrder = new StringBuilder();
			
			sbOrder.append("saasType=").append(OnlineType.CENTRALIZED.getType()).append("&saasGidList=").append(Base64Util.encrypt(ar.toString()));
			
			vo.setParams(sbOrder.toString());
			
			
//			vo.setParams("saasType=3&saasGidList=W3siZ2lkIjoiMTE4Mzg2MDIiLCJzdUlkIjoiMzgzIiwibnVtYmVyIjozLCJwcmljZSI6IjIyLjAw"
//					+ "MDAiLCJnb29kc1NvdXJjZSI6IjAiLCJjYXJ0QWRkVGltZSI6IjIwMTctMTEtMjIgMTg6MjI6MDMi"
//					+ "fSx7ImdpZCI6IjExODM4NjAxIiwic3VJZCI6IjM4MyIsIm51bWJlciI6MSwicHJpY2UiOiI1Mi4w"
//					+ "MzAwIiwiZ29vZHNTb3VyY2UiOiIwIiwiY2FydEFkZFRpbWUiOiIyMDE3LTExLTIyIDE4OjIyOjAz"
//					+ "In0seyJnaWQiOiI2NTQ5MDM5Iiwic3VJZCI6IjU0OCIsIm51bWJlciI6MTMsInByaWNlIjoiNDIu"
//					+ "MDAwMCIsImdvb2RzU291cmNlIjoiMCIsImNhcnRBZGRUaW1lIjoiMjAxNy0xMS0yMyAxNjoyMzowNCJ9XQ==");
		}
		
		return vo;
		
	}
	
	
	/**
	 * @Description: TODO构建智能采购去下单 数据
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年11月20日 下午9:06:38 
	 * @param supplierList
	 * @return
	 * @throws Exception 
	 * @return PlaceOrderDataVO
	 */
	public PlaceOrderDataVO smartSourcingOrderModelBuild(List<SmartSourcingSupplierVO> supplierList) throws Exception{
		
		PlaceOrderDataVO vo = new PlaceOrderDataVO();
		vo.setUrl(mphImplUrl + "saas/auth.html");
		vo.setCode(rgtUserId);
		vo.setCallbackUrl("/balance.html");
		
		if(supplierList != null){
			JSONArray ar = new JSONArray();
			JSONObject ob = null;
			for (SmartSourcingSupplierVO cartSupplier : supplierList) {
				if(cartSupplier != null && cartSupplier.getGoodsList() != null){
					for (SmartSourcingGoodsVO cartGood : cartSupplier.getGoodsList()) {
						ob = new JSONObject();
						ob.put("gid", cartGood.getgId());
						ob.put("suId", cartSupplier.getMphSupplierId());
						ob.put("number", cartGood.getQuantity());
						ob.put("price", cartGood.getRetailPrice());
						ob.put("goodsSource", "0"); //0智能采购,1菲加云集采
						ob.put("cartAddTime", DateUtils.DateToString(cartGood.getAddCartTime(), "yyyy-MM-dd HH:mm:ss"));
						ar.add(ob);
					}
				}
			}
			StringBuilder sbOrder = new StringBuilder();
			
			sbOrder.append("saasType=").append(OnlineType.SMART_SOURCING.getType()).append("&saasGidList=").append(Base64Util.encrypt(ar.toString()));
			
			vo.setParams(sbOrder.toString());
		}
		
		return vo;
		
	}


	
}
