package com.rograndec.feijiayun.chain.business.purchase.addinstorage.service;

import com.rograndec.feijiayun.chain.business.purchase.addinstorage.entity.PurchaseCalcul;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageDtlVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.PurchaseOrderDataVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.PurchaseOrderGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: PurchaseAddInStorageService   
 * @Description: 采购入库 --- 新增
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月28日 下午7:14:19
 */
public interface PurchaseAddInStorageService {
	
	// 新增
	/**
	 * 
	 * @Description: 新增入库数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年11月29日 下午3:53:46 
	 * @param userVO
	 * @param addInstorageVO
	 * @throws BusinessException 
	 * @return void
	 */
	void save(UserVO userVO,AddInstorageVO addInstorageVO) throws Exception,BusinessException;
	
	/**
	 * 
	 * @Description:返回逆向生成单据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月6日 上午11:46:24 
	 * @param userVO
	 * @param addInstorageVO
	 * @return
	 * @throws Exception
	 * @throws BusinessException 
	 * @return List<String>
	 */
	List<String> saveReturnMsg(UserVO userVO,AddInstorageVO addInstorageVO) throws Exception,BusinessException;
	
	/**
	 * 
	 * @Description: 由采购订单生成数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月1日 下午7:54:24 
	 * @param userVO
	 * @param addInstorageVO
	 * @throws Exception
	 * @throws BusinessException 
	 * @return void
	 */
	void saveByPurchaseOrder(UserVO userVO,AddInstorageVO addInstorageVO) throws Exception,BusinessException;
	
	/**
	 * 
	 * @Description: 返回逆向生成单据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月6日 下午12:04:33 
	 * @param userVO
	 * @param addInstorageVO
	 * @return
	 * @throws Exception
	 * @throws BusinessException 
	 * @return List<String>
	 */
	List<String> saveByPurchaseOrderReturnMsg(UserVO userVO,AddInstorageVO addInstorageVO) throws Exception,BusinessException;
	
	// 调用接口

	//公共的计算
	void setCalcul(AddInstorageDtlVO dtlVO, PurchaseOrderDetail purchaseOrderDetail, BigDecimal quantityTotal,
				   BigDecimal amountTotal, BigDecimal notaxRealAmountTotal, BigDecimal taxAmountTotal,
				   BigDecimal realAmountTotal, BigDecimal wholeDiscountAmount,BigDecimal wholeDiscount,
				   PurchaseCalcul purchaseCalcul);

	//采购订单
	Map<String,Object> addOrder(UserVO userVO, AddInstorageVO addInstorageVO) throws Exception,BusinessException;

	//采购收货
	@SuppressWarnings("unchecked")
	Map<String,Object> addReceive(UserVO userVO, AddInstorageVO addInstorageVO, Map<String, Object> orderMap) throws Exception,BusinessException;

	//采购验收
	@SuppressWarnings("unchecked")
	Map<String,Object> addCheck(UserVO userVO, AddInstorageVO addInstorageVO, Map<String, Object> receiveMap) throws Exception,BusinessException;

	//采购入库
	@SuppressWarnings("unchecked")
	Map<String,Object> addInstorage(UserVO userVO, AddInstorageVO addInstorageVO, Map<String, Object> checkMap, Map<String, Object> orderMap) throws Exception,BusinessException;

	/**
	 * 
	 * @Description: 调用采购订单数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月1日 下午2:12:19 
	 * @param page
	 * @param userVO
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 * @throws BusinessException 
	 * @return Page<PurchaseOrderDataVO>
	 */
	@SuppressWarnings("rawtypes")
	Page<List<PurchaseOrderDataVO>> getPurchaseOrder(Page page,UserVO userVO,Date startTime, Date endTime) throws Exception,BusinessException;
	
	/**
	 * 
	 * @Description: 采购订单商品数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月1日 下午5:08:31 
	 * @param page
	 * @param userVO
	 * @param orderId
	 * @return
	 * @throws Exception
	 * @throws BusinessException 
	 * @return Page<List<PurchaseOrderGoodsVO>>
	 */
	Page<List<PurchaseOrderGoodsVO>> getGoodsVO(Page<?> page,UserVO userVO,Long orderId) throws Exception,BusinessException;
	
}
