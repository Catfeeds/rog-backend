package com.rograndec.feijiayun.chain.business.purchase.addinstorage.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.entity.PurchaseCalcul;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.service.PurchaseAddInStorageService;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckLotMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheck;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckDetail;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckLot;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderResponseVO;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveDetail;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveOther;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.LastInPriceVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @ClassName: PurchaseAddInStorageServiceImpl   
 * @Description: 采购入库 --- 新增
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月29日 下午3:52:06
 */
@Service
public class PurchaseAddInStorageServiceImpl implements PurchaseAddInStorageService{
	
	@Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    private PurchaseOrderOtherMapper purchaseOrderOtherMapper;
	
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierSalerMapper supplierSalerMapper;
    
    @Autowired 
    private GoodsMapper goodsMapper;
    @Autowired  
	private GoodsTaxRateMapper goodsTaxRateMapper;
    @Autowired
	private GoodsBusinessMapper goodsBusinessMapper;
    
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    PurchaseReceiveMapper purchaseReceiveMapper;
    @Autowired
    PurchaseReceiveDetailMapper purchaseReceiveDetailMapper;
    @Autowired
    PurchaseReceiveOtherMapper purchaseReceiveOtherMapper;
    
    @Autowired
    PurchaseCheckMapper purchaseCheckMapper;
    @Autowired
    PurchaseCheckDetailMapper purchaseCheckDetailMapper;
    @Autowired
    PurchaseCheckLotMapper purchaseCheckLotMapper;
    
    @Autowired
	private PurchaseInStorageMapper purchaseInStorageMapper;
	@Autowired
	private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;
	@Autowired
	private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;
	
	@Autowired
	private InOutComponent inOutComponent;
	@Autowired
	private CommonComponent commonComponent;
	
	@Autowired
	private WarehouseShelfMapper warehouseShelfMapper;

	@Autowired
	private ManageConfigMapper manageConfigMapper;

	@Autowired
	private SafetyStockMapper safetyStockMapper;
	
	@Autowired
	private FinanceComponent financeComponent;
    // 所有单据的状态都为已完成，财务已修改，总部和自营店逆向生成为 代开票，加盟店逆向生成的为 已完成
    //待开票状态
    private static Integer WAIT_BILL = PurchaseStatus.WAIT_BILL.getStatus();

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void save(UserVO userVO,AddInstorageVO addInstorageVO) throws Exception,BusinessException {
		Map<String,Object> orderMap = addOrder(userVO, addInstorageVO);
		Map<String,Object> receiveMap = addReceive(userVO, addInstorageVO, orderMap);
		Map<String,Object> checkMap = addCheck(userVO, addInstorageVO, receiveMap);
		addInstorage(userVO, addInstorageVO, checkMap, orderMap);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public List<String> saveReturnMsg(UserVO userVO,AddInstorageVO addInstorageVO) throws Exception,BusinessException {
		Map<String,Object> orderMap = addOrder(userVO, addInstorageVO);
		Map<String,Object> receiveMap = addReceive(userVO, addInstorageVO, orderMap);
		Map<String,Object> checkMap = addCheck(userVO, addInstorageVO, receiveMap);
		Map<String,Object> storage = addInstorage(userVO, addInstorageVO, checkMap, orderMap);
		return setReturnMsg(0,orderMap, receiveMap, checkMap, storage);
	}
	
	/**
	 * 
	 * @Description: 返回生成单据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月6日 下午12:51:24 
	 * @param type=0 入库新增,为空则1调用
	 * @param orderMap
	 * @param receiveMap
	 * @param checkMap
	 * @param storage
	 * @return 
	 * @return List<String>
	 */
	private List<String> setReturnMsg(Integer type,Map<String,Object> orderMap,Map<String,Object> receiveMap,Map<String,Object> checkMap,Map<String,Object> storage){
		List<String> msgLsit = new ArrayList<>();
		if(type == 0 && orderMap != null && orderMap.containsKey("purchaseOrder")) {
			PurchaseOrder purchaseOrder = (PurchaseOrder)orderMap.get("purchaseOrder");
			msgLsit.add("已经成功生成总部采购订单："+purchaseOrder.getCode());
		}
		if(receiveMap != null && receiveMap.containsKey("purchaseReceive")) {
			PurchaseReceive purchaseReceive = (PurchaseReceive)receiveMap.get("purchaseReceive");		
			msgLsit.add("已经成功生成总部收货订单："+purchaseReceive.getCode());
		}
		if(checkMap != null && checkMap.containsKey("purchaseCheck")) {
			PurchaseCheck purchaseCheck = (PurchaseCheck)checkMap.get("purchaseCheck");
			msgLsit.add("已经成功生成总部验收订单："+purchaseCheck.getCode());
		}
		if(storage != null && storage.containsKey("storage")) {
			PurchaseInStorage inStorage = (PurchaseInStorage)storage.get("storage");
			msgLsit.add("已经成功生成总部入库订单："+inStorage.getCode());
		}
		return msgLsit;
	}
	
	//公共的计算
	@Override
	public void setCalcul(AddInstorageDtlVO dtlVO, PurchaseOrderDetail purchaseOrderDetail, BigDecimal quantityTotal,
			BigDecimal amountTotal, BigDecimal notaxRealAmountTotal, BigDecimal taxAmountTotal,
			BigDecimal realAmountTotal, BigDecimal wholeDiscountAmount,BigDecimal wholeDiscount,
			PurchaseCalcul purchaseCalcul) {
		GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(dtlVO.getTaxRateId());
		if (goodsTaxRate == null) {
			throw new BusinessException("没有该税率值，请核实税率ID");
		} else {
			purchaseOrderDetail.setTaxRateId(goodsTaxRate.getId());
			purchaseOrderDetail.setTaxRate(goodsTaxRate.getTaxRate());
		}
		// 计算
		BigDecimal quantity = dtlVO.getQuantity();
		BigDecimal unitPrice = dtlVO.getUnitPrice();
		BigDecimal lineDiscount = dtlVO.getLineDiscount();// 行折扣
		BigDecimal taxRate = purchaseOrderDetail.getTaxRate();
//		BigDecimal wholeDiscount = dtlVO.getWholeDiscount();// 整单折扣

		// 计算金额合计
		BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(quantity, unitPrice,
				lineDiscount);
		amountTotal = purchaseCalcul.getAmountTotal().add(amount);
		CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice,
				lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);
		// 设置计算结果
		purchaseOrderDetail.setQuantity(quantity);
		purchaseOrderDetail.setUnitPrice(unitPrice);
		purchaseOrderDetail.setLineDiscount(lineDiscount);
		purchaseOrderDetail.setWholeDiscount(wholeDiscount);
		purchaseOrderDetail.setLineDiscountAmount(calculateResultModel.getLineRoundOff());
		purchaseOrderDetail.setNotaxRealAmount(calculateResultModel.getNotaxAmount());// 不含税金额
		purchaseOrderDetail.setNotaxRealPrice(calculateResultModel.getNotaxPrice());// 不含税实际单价
		purchaseOrderDetail.setRealAmount(calculateResultModel.getRealAmount());// 实际金额
		purchaseOrderDetail.setRealPrice(calculateResultModel.getRealPrice());// 实际单价
		purchaseOrderDetail.setTaxAmount(calculateResultModel.getTaxAmount());// 税额
		purchaseOrderDetail.setAmount(calculateResultModel.getAmount());
		// 主表的计算
		quantityTotal = purchaseCalcul.getQuantityTotal().add(quantity);
		realAmountTotal = purchaseCalcul.getRealAmountTotal().add(calculateResultModel.getRealAmount());
		notaxRealAmountTotal = purchaseCalcul.getNotaxRealAmountTotal().add(calculateResultModel.getNotaxAmount());// 不含税金额总额
		taxAmountTotal = purchaseCalcul.getTaxAmountTotal().add(calculateResultModel.getTaxAmount());
		// 行号
		purchaseOrderDetail.setLineNum(dtlVO.getLineNum());
		purchaseOrderDetail.setUnclearQuantity(BigDecimal.ZERO);
		purchaseOrderDetail.setClearQuantity(quantity);
		
		purchaseCalcul.setQuantityTotal(quantityTotal);
		purchaseCalcul.setAmountTotal(amountTotal);
		purchaseCalcul.setNotaxRealAmountTotal(notaxRealAmountTotal);
		purchaseCalcul.setTaxAmountTotal(taxAmountTotal);
		purchaseCalcul.setRealAmountTotal(realAmountTotal);
	}


	//采购订单
	@Override
	public Map<String,Object> addOrder(UserVO userVO, AddInstorageVO addInstorageVO) throws Exception,BusinessException{
		Long enterpriseId = userVO.getEnterpriseId();
		String enterpriseCode = userVO.getEnterpriseCode();
		Integer type = addInstorageVO.getType();
		Integer status = PurchaseStatus.WAIT_BILL.getStatus();// 总部新增或者自营店=逆向生成
		if(type == 1){// 直调逆向生成
			enterpriseId = userVO.getParentId();
			enterpriseCode = userVO.getEnterpriseCode();
			if(ChainType.Division.getCode() == userVO.getChainType()){// 加盟店逆向生成的为已完成
				status = PurchaseStatus.FINISHED.getStatus();
			} else if (ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){// 自营店逆向生成为 待开票
				status = PurchaseStatus.WAIT_BILL.getStatus();
			}
		}
		Map<String,Object> result = new HashMap<>(2);
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder = (PurchaseOrder) EntityUtils.reflectAddSetDefaultValue(purchaseOrder.getClass(),userVO);//复制用户
		if(type == 1){
			purchaseOrder.setEnterpriseId(userVO.getParentId());
			purchaseOrder.setParentId(0L);

		}
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(addInstorageVO,purchaseOrder);
		Long supplierId = addInstorageVO.getSupplierId();
		if(type == 1){
			supplierId = addInstorageVO.getOutboundUnitId();
		}
		Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
		if(supplier!=null){
    		purchaseOrder.setSupplierId(supplier.getId());
    		purchaseOrder.setSupplierName(supplier.getName());
        	purchaseOrder.setSupplierCode(supplier.getCode());
        	if(type == 1){
        		//承运单位设置为供货商单位
        		addInstorageVO.getAddInstorageOtherVO().setCarriageUnit(supplier.getName());
			}
    	} else {
			throw new BusinessException("供货单位不存在");
		}

		if(type == 1){
			//配进入库调用
			purchaseOrder.setSupplierSalerId(supplier.getSaleManId());
			purchaseOrder.setSupplierSalerCode(supplier.getSaleManCode());
			purchaseOrder.setSupplierSalerName(supplier.getSaleManName());
//			purchaseOrder.setSupplierSalerPhone(supplier.getS());
		} else {
			SupplierSaler supplierSaler = supplierSalerMapper.selectByPrimaryKey(addInstorageVO.getSupplierSalerId());
			if(supplierSaler!=null){
				purchaseOrder.setSupplierSalerId(supplierSaler.getId());
				purchaseOrder.setSupplierSalerCode(supplierSaler.getCode());
				purchaseOrder.setSupplierSalerName(supplierSaler.getName());
				purchaseOrder.setSupplierSalerPhone(supplierSaler.getTelephone());
			}
		}
		if(type == 1){
			//采购人员： 配进入库查询，企业默认的采购人员
			ManageConfig manageConfig = manageConfigMapper.getMangeConfigByEPId(userVO.getParentId());
			if(manageConfig.getPurchaserId() == null || manageConfig.getPurchaserCode() == null || manageConfig.getPurchaserName() == null){
				throw new BusinessException("请设置总部默认采购人员！");
			}
			purchaseOrder.setPurchaserId(manageConfig.getPurchaserId());
			purchaseOrder.setPurchaserCode(manageConfig.getPurchaserCode());
			purchaseOrder.setPurchaserName(manageConfig.getPurchaserName());
		} else {
			//设置采购人员
			User user = userMapper.selectByPrimaryKey(purchaseOrder.getPurchaserId());
			if(user != null) {
				purchaseOrder.setPurchaserId(purchaseOrder.getPurchaserId());
				purchaseOrder.setPurchaserCode(user.getCode());
				purchaseOrder.setPurchaserName(user.getName());
			} else {
				throw new BusinessException("采购人员不存在");
			}
		}


		purchaseOrder.setArrivalTime(addInstorageVO.getReceiveDate());//预计到货日期为收货日期
		purchaseOrder.setStatus(status);
		purchaseOrder.setOrderMode(1);//采购方式，现在默认为标准采购
		String code = orderCodeComponent.generate(OrderRule.PURCHASE_ORDER.getCodePrefix(), enterpriseId, enterpriseCode);
		purchaseOrder.setCode(code);
        purchaseOrder.setOrderType(OrderRule.PURCHASE_ORDER.getType());//订单类型
		if(type == 1){
			purchaseOrder.setOrderDate(new Date());
		} else {
			purchaseOrder.setOrderDate(addInstorageVO.getPurchaserDate());
		}

        
        BigDecimal quantityTotal = BigDecimal.ZERO;//数量合计
        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税总额 
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额
        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
		BigDecimal wholeDiscount = addInstorageVO.getWholeDiscount();
		List<AddInstorageDtlVO> dtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        PurchaseCalcul purchaseCalcul = new PurchaseCalcul();
        //商品id
        List<Long> goodsIds = new ArrayList<>();
        List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<>();
        for(AddInstorageDtlVO dtlVO : dtlVOList) {
        	PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
        	purchaseOrderDetail = (PurchaseOrderDetail) EntityUtils.reflectAddSetDefaultValue(purchaseOrderDetail.getClass(),userVO);//复制用户
			if(type == 1){
				purchaseOrderDetail.setEnterpriseId(userVO.getParentId());
				purchaseOrderDetail.setParentId(0L);
			}
        	//设置商品id
			purchaseOrderDetail.setGoodsId(dtlVO.getGoodsId());
        	setCalcul(dtlVO, purchaseOrderDetail, quantityTotal, amountTotal, notaxRealAmountTotal, taxAmountTotal, realAmountTotal, purchaseOrder.getWholeDiscountAmount(),wholeDiscount,purchaseCalcul);
        	purchaseOrderDetail.setOrderCode(code);
    		purchaseOrderDetail.setOrderType(OrderRule.PURCHASE_ORDER.getType());
    		purchaseOrderDetail.setOrderId(purchaseOrder.getId());
    		purchaseOrderDetail.setOrderDate(purchaseOrder.getOrderDate());
    		purchaseOrderDetail.setStatus(purchaseOrder.getStatus());
			purchaseOrderDetailList.add(purchaseOrderDetail);
        }
        //主表设置
        purchaseOrder.setVarietiesQuantity(dtlVOList == null ? 0 : dtlVOList.size());
        purchaseOrder.setQuantityTotal(purchaseCalcul.getQuantityTotal());
    	purchaseOrder.setAmountTotal(purchaseCalcul.getAmountTotal());//总额（优惠前金额合计）
    	purchaseOrder.setWholeDiscount(wholeDiscount);
		purchaseOrder.setWholeDiscountAmount(addInstorageVO.getWholeDiscountAmount());
    	purchaseOrder.setRealAmountTotal(purchaseCalcul.getRealAmountTotal());//实际金额合计
    	purchaseOrder.setNotaxRealAmountTotal(purchaseCalcul.getNotaxRealAmountTotal());
    	purchaseOrder.setTaxAmountTotal(purchaseCalcul.getTaxAmountTotal());
    	purchaseOrderMapper.insert(purchaseOrder);
    	
		//处理商品信息
		goodsIds.addAll(dtlVOList.stream().map(p -> {
			return p.getGoodsId();
		}).distinct().collect(Collectors.toList()));
		List<Goods> goodsList = null;
		//处理价格 获取 零售基价 会员基价 配货基价
		List<GoodsBusiness> goodsBusinessList = null;
		if(type == 1){
			//自营店查询商品
			goodsList = goodsMapper.getGoodsByIds(goodsIds, enterpriseId,enterpriseId);
			goodsBusinessList = goodsBusinessMapper.selectByGoodsIds(goodsIds, enterpriseId);
			if(userVO.getChainType() == ChainType.Division.getCode()){
				//加盟店查询商品
				goodsList.addAll(goodsMapper.getGoodsByIds(goodsIds,enterpriseId, userVO.getEnterpriseId()));
				goodsBusinessList.addAll(goodsBusinessMapper.selectByGoodsIds(goodsIds, userVO.getEnterpriseId()));
			}
		} else {
			goodsList = goodsMapper.getGoodsByIds(goodsIds,enterpriseId,userVO.getEnterpriseId());
			goodsBusinessList = goodsBusinessMapper.selectByGoodsIds(goodsIds, enterpriseId);
		}

		if(CollectionUtils.isEmpty(goodsList)) {
			throw new BusinessException("选择的商品信息不存在！");
		}
    	//采购订单细表数据添加
		for(PurchaseOrderDetail dtl : purchaseOrderDetailList) {
			//设置采购订单主表信息
    		dtl.setOrderId(purchaseOrder.getId());
    		dtl.setOrderType(purchaseOrder.getOrderType());
    		dtl.setOrderCode(purchaseOrder.getCode());
    		dtl.setOrderDate(purchaseOrder.getOrderDate());
    		goodsList.forEach(goods->{
    			if(dtl.getGoodsId().longValue() ==  goods.getId().longValue()) {
    				dtl.setGoodsId(goods.getId());
    				dtl.setGoodsCode(goods.getCode());
    				dtl.setGoodsName(goods.getName());
    				dtl.setBarcode(goods.getBarcode());
    				dtl.setGoodsGenericName(goods.getGenericName());
    				dtl.setDosageId(goods.getDosageId());//剂型ID
    				dtl.setDosageName(goods.getDosageName());
    				dtl.setUnitId(goods.getUnitId());
    				dtl.setUnitName(goods.getUnitName());
    				dtl.setGoodsPlace(goods.getPlace());
    				dtl.setGoodsSpecification(goods.getSpecification());
    				dtl.setManufacturerId(goods.getManufacturerId());//生产厂商ID
    				dtl.setManufacturer(goods.getManufacturer());
    				dtl.setApprovalNumber(goods.getApprovalNumber());
    			}
    		});
    		if(goodsBusinessList != null && goodsBusinessList.size() > 0) {
    			goodsBusinessList.forEach(goodsBusiness->{
    				if(dtl.getGoodsId().longValue() ==  goodsBusiness.getGoodsId().longValue()) {
    					dtl.setRetailPrice(goodsBusiness.getRetailPrice());
        				dtl.setMemberPrice(goodsBusiness.getMemberPrice());
        				dtl.setDistrPrice(goodsBusiness.getDistrPrice());
    				}
    			});
    		}
		}

    	//添加采购订单细表数据
    	purchaseOrderDetailMapper.batchInsert(purchaseOrderDetailList);
    	
    	//配送信息
    	AddInstorageOtherVO addInstorageOtherVO = addInstorageVO.getAddInstorageOtherVO();
    	PurchaseOrderOther purchaseOrderOther = new PurchaseOrderOther();
    	purchaseOrderOther = (PurchaseOrderOther) EntityUtils.reflectAddSetDefaultValue(purchaseOrderOther.getClass(),userVO);
		if(type == 1){
			purchaseOrderOther.setEnterpriseId(userVO.getParentId());
			purchaseOrderOther.setParentId(0L);
		}
    	CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(addInstorageOtherVO,purchaseOrderOther);
    	purchaseOrderOther.setStatus(purchaseOrder.getStatus());
    	purchaseOrderOther.setOrderId(purchaseOrder.getId());
    	Enterprise settlementUnit = enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getSettlementUnitId());//结算单位
    	if(settlementUnit!=null){
        	purchaseOrderOther.setSettlementUnitName(settlementUnit.getName());
        }
        Enterprise receiveUnit = enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getReceiveUnitId());//收货单位
    	User receiver = userMapper.selectByPrimaryKey(purchaseOrderOther.getReveiverId());//收货人员
    	if(receiveUnit != null){
        	purchaseOrderOther.setReceiveUnitName(receiveUnit.getName());
        	purchaseOrderOther.setReceiveUnitCode(receiveUnit.getCode());
        }
        if(receiver != null){
        	purchaseOrderOther.setReceiverCode(receiver.getCode());
        	purchaseOrderOther.setReceiverName(receiver.getName());
        }
        purchaseOrderOtherMapper.insertSelective(purchaseOrderOther);
        
        result.put("purchaseOrder", purchaseOrder);
        result.put("purchaseOrderDetailList", purchaseOrderDetailList);
        return result;
	}
	
	//采购收货
	@SuppressWarnings("unchecked")
	@Override
	public  Map<String,Object> addReceive(UserVO userVO, AddInstorageVO addInstorageVO, Map<String, Object> orderMap) throws Exception,BusinessException{
		Integer type = addInstorageVO.getType();
		Map<String,Object> result = new HashMap<>(2);
		//采购订单数据
		PurchaseOrder purchaseOrder = (PurchaseOrder)orderMap.get("purchaseOrder");
		List<PurchaseOrderDetail> purchaseOrderDetailList = (List<PurchaseOrderDetail>)orderMap.get("purchaseOrderDetailList");
		PurchaseReceive purchaseReceive = new PurchaseReceive();
		purchaseReceive = (PurchaseReceive) EntityUtils.reflectAddSetDefaultValue(purchaseReceive.getClass(),userVO);//复制用户

		Integer status = PurchaseStatus.WAIT_BILL.getStatus();// 总部新增或者自营店=逆向生成

		if(type == 1){
			purchaseReceive.setEnterpriseId(userVO.getParentId());
			purchaseReceive.setParentId(0L);

			if(ChainType.Division.getCode() == userVO.getChainType()){// 加盟店逆向生成的为已完成
				status = PurchaseStatus.FINISHED.getStatus();
			}else if (ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){// 自营店逆向生成为 待开票
				status = PurchaseStatus.WAIT_BILL.getStatus();
			}
		}

		if(type == 1){
			//配进入库调用
			ManageConfig mangeConfigByEPId = manageConfigMapper.getMangeConfigByEPId(userVO.getParentId());
			if(mangeConfigByEPId.getReceiverId() == null|| mangeConfigByEPId.getReceiverCode() == null || mangeConfigByEPId.getReceiverName() == null) {
				throw new BusinessException("请设置总部默认收货人员");
			}
			purchaseReceive.setReceiverId(mangeConfigByEPId.getReceiverId());
			purchaseReceive.setReceiverCode(mangeConfigByEPId.getReceiverCode());
			purchaseReceive.setReceiverName(mangeConfigByEPId.getReceiverName());

			purchaseReceive.setSecondReceiverId(mangeConfigByEPId.getSecondReceiverId());
			purchaseReceive.setSecondReceiverCode(mangeConfigByEPId.getSecondReceiverCode());
			purchaseReceive.setSecondReceiverName(mangeConfigByEPId.getSecondReceiverName());
		} else {
			User user = userMapper.selectByPrimaryKey(addInstorageVO.getReceiverId());
			if(user != null) {
				purchaseReceive.setReceiverId(user.getId());
				purchaseReceive.setReceiverCode(user.getCode());
				purchaseReceive.setReceiverName(user.getName());
			} else {
				throw new BusinessException("收货人员不存在！");
			}

			if(addInstorageVO.getSecondReceiverId() != null){
				user = userMapper.selectByPrimaryKey(addInstorageVO.getSecondReceiverId());
				if(user != null) {
					purchaseReceive.setSecondReceiverId(user.getId());
					purchaseReceive.setSecondReceiverCode(user.getCode());
					purchaseReceive.setSecondReceiverName(user.getName());
				} else {
					throw new BusinessException("收货人员2不存在！");
				}
			}
		}


		purchaseReceive.setReceiveDate(addInstorageVO.getReceiveDate());
		//收货日期不能小于订单日期
        Date orderDate = DateUtils.StringToDate(DateUtils.DateToString(purchaseOrder.getOrderDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
        Date receiveDate = DateUtils.StringToDate(DateUtils.DateToString(purchaseReceive.getReceiveDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
        if(type != 1){
			if(receiveDate.getTime() < orderDate.getTime()) {
				throw new BusinessException("收货日期不能小于订单日期!");
			}
		}

        //基础单据
        purchaseReceive.setBaseOrderId(purchaseOrder.getId());
        purchaseReceive.setBaseOrderCode(purchaseOrder.getCode());
        purchaseReceive.setBaseOrderType(purchaseOrder.getOrderType());
        purchaseReceive.setBaseOrderDate(purchaseOrder.getOrderDate());
        //供应商信息
        purchaseReceive.setSupplierId(purchaseOrder.getSupplierId());
        purchaseReceive.setSupplierCode(purchaseOrder.getSupplierCode());
        purchaseReceive.setSupplierName(purchaseOrder.getSupplierName());
        //供应商销售人员信息
        purchaseReceive.setSupplierSalerId(purchaseOrder.getSupplierSalerId());
        purchaseReceive.setSupplierSalerCode(purchaseOrder.getSupplierSalerCode());
        purchaseReceive.setSupplierSalerName(purchaseOrder.getSupplierSalerName());
        purchaseReceive.setSupplierSalerPhone(purchaseOrder.getSupplierSalerPhone());
        purchaseReceive.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
        purchaseReceive.setStatus(status);
		String reciveCode = "";
        if(type == 1){
			reciveCode = orderCodeComponent.generate(OrderRule.PURCHASE_RECEIVE.getCodePrefix(),userVO.getParentId(),userVO.getParentCode());
		} else {
			reciveCode = orderCodeComponent.generate(OrderRule.PURCHASE_RECEIVE.getCodePrefix(),userVO.getEnterpriseId(),userVO.getEnterpriseCode());
		}

        purchaseReceive.setCode(reciveCode);
        purchaseReceive.setQuantityTotal(purchaseOrder.getQuantityTotal());
        purchaseReceive.setVarietiesQuantity(purchaseOrder.getVarietiesQuantity());
        purchaseReceiveMapper.insertSelective(purchaseReceive);
        //收货明细集合
        List<PurchaseReceiveDetail> purchaseReceiveDetails = new ArrayList<>();
        //前台细表VO
        List<AddInstorageDtlVO> dtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        //收货细表数据
        for(PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetailList) {
        	PurchaseReceiveDetail purchaseReceiveDetail = new PurchaseReceiveDetail();
        	purchaseReceiveDetail = (PurchaseReceiveDetail) EntityUtils.reflectAddSetDefaultValue(purchaseReceiveDetail.getClass(),userVO);//复制用户
			if(type == 1){
				purchaseReceiveDetail.setEnterpriseId(userVO.getParentId());
				purchaseReceiveDetail.setParentId(0L);
			}
        	CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseOrderDetail,purchaseReceiveDetail);
        	purchaseReceiveDetail.setId(null);
        	for(AddInstorageDtlVO dtlVO : dtlVOList) {
        		if(purchaseReceiveDetail.getGoodsId().longValue() == dtlVO.getGoodsId().longValue()) {
        			purchaseReceiveDetail.setArrivalQuantity(purchaseOrderDetail.getQuantity());
        			purchaseReceiveDetail.setReceiveQuantity(dtlVO.getReceiveQuantity());
        			purchaseReceiveDetail.setRefuseQuantity(dtlVO.getRefuseQuantity());
        		}
        	}
        	//set基础单据信息
        	purchaseReceiveDetail.setBaseOrderId(purchaseOrderDetail.getOrderId());
            purchaseReceiveDetail.setBaseOrderCode(purchaseOrderDetail.getOrderCode());
            purchaseReceiveDetail.setBaseOrderDate(purchaseOrderDetail.getOrderDate());
            purchaseReceiveDetail.setBaseOrderType(purchaseOrderDetail.getOrderType());
            purchaseReceiveDetail.setBaseOrderDtlId(purchaseOrderDetail.getId());
            //set商品信息
            Goods goods = goodsMapper.selectByPrimaryKey(purchaseOrderDetail.getGoodsId());
            if(goods.getSpecialDrug() != -1 && purchaseReceive.getSecondReceiverId() == null){
                throw new BusinessException("第"+purchaseOrderDetail.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
            }
            //set状态
            purchaseReceiveDetail.setStatus(status);
            //set收货单据id/code/类型/日期
            purchaseReceiveDetail.setReceiveId(purchaseReceive.getId());
            purchaseReceiveDetail.setReceiveCode(purchaseReceive.getCode());
            purchaseReceiveDetail.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
            purchaseReceiveDetail.setReceiveDate(purchaseReceive.getReceiveDate());
            //设置订单数量=采购的数量
            purchaseReceiveDetail.setOrderQuantity(purchaseOrderDetail.getQuantity());
            purchaseReceiveDetails.add(purchaseReceiveDetail);
        }
        //批量保存收货
        purchaseReceiveDetailMapper.batchInsert(purchaseReceiveDetails);
        //保存收货配送跟结算
        PurchaseReceiveOther purchaseReceiveOther = new PurchaseReceiveOther();
        purchaseReceiveOther = (PurchaseReceiveOther) EntityUtils.reflectAddSetDefaultValue(purchaseReceiveOther.getClass(),userVO);//复制用户
		if(type == 1){
			purchaseReceiveOther.setEnterpriseId(userVO.getParentId());
			purchaseReceiveOther.setParentId(0L);
		}
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(addInstorageVO.getAddInstorageOtherVO(),purchaseReceiveOther);
        purchaseReceiveOther.setStatus(status);
        purchaseReceiveOther.setReceiveId(purchaseReceive.getId());
        purchaseReceiveOther.setOrderId(purchaseReceive.getBaseOrderId());
        purchaseReceiveOther.setOrderCode(purchaseReceive.getBaseOrderCode());
        purchaseReceiveOtherMapper.insertSelective(purchaseReceiveOther);
        result.put("purchaseReceive", purchaseReceive);
        result.put("purchaseReceiveDetails", purchaseReceiveDetails);
        return result;
	}
	
	//采购验收
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> addCheck(UserVO userVO, AddInstorageVO addInstorageVO, Map<String, Object> receiveMap) throws Exception,BusinessException{
		Integer type = addInstorageVO.getType();
		Map<String,Object> result = new HashMap<>(4);
		PurchaseReceive purchaseReceive = (PurchaseReceive)receiveMap.get("purchaseReceive");
		List<PurchaseReceiveDetail> purchaseReceiveDetailList  = (List<PurchaseReceiveDetail>)receiveMap.get("purchaseReceiveDetails");
		PurchaseCheck purchaseCheck = new PurchaseCheck();
		purchaseCheck = (PurchaseCheck) EntityUtils.reflectAddSetDefaultValue(purchaseCheck.getClass(),userVO);//复制用户

		Integer status = PurchaseStatus.WAIT_BILL.getStatus();
		if(type == 1){
			purchaseCheck.setEnterpriseId(userVO.getParentId());
			purchaseCheck.setParentId(0L);

			if(ChainType.Division.getCode() == userVO.getChainType()){// 加盟店逆向生成的为已完成
				status = PurchaseStatus.FINISHED.getStatus();
			}else if (ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){// 自营店逆向生成为 待开票
				status = PurchaseStatus.WAIT_BILL.getStatus();
			}
		}



		if(type == 1){
			ManageConfig mangeConfigByEPId = manageConfigMapper.getMangeConfigByEPId(userVO.getParentId());
			if(mangeConfigByEPId.getCheckerId() == null || mangeConfigByEPId.getCheckerCode() == null || mangeConfigByEPId.getCheckerName() == null){
				throw new BusinessException("请设置总部的默认验收人员！");
			}
			purchaseCheck.setCheckerId(mangeConfigByEPId.getCheckerId());
			purchaseCheck.setCheckerCode(mangeConfigByEPId.getCheckerCode());
			purchaseCheck.setCheckerName(mangeConfigByEPId.getCheckerName());

			purchaseCheck.setSecondCheckerId(mangeConfigByEPId.getSecondCheckerId());
			purchaseCheck.setSecondCheckerCode(mangeConfigByEPId.getSecondCheckerCode());
			purchaseCheck.setSecondCheckerName(mangeConfigByEPId.getSecondCheckerName());
		} else {
			User user = userMapper.selectByPrimaryKey(addInstorageVO.getCheckerId());
			if(user != null) {
				purchaseCheck.setCheckerId(user.getId());
				purchaseCheck.setCheckerCode(user.getCode());
				purchaseCheck.setCheckerName(user.getName());
			} else {
				throw new BusinessException("验收人员不存在！");
			}

			if(addInstorageVO.getSecondReceiverId() != null){
				user = userMapper.selectByPrimaryKey(addInstorageVO.getSecondCheckerId());
				if(user != null) {
					purchaseCheck.setSecondCheckerId(user.getId());
					purchaseCheck.setSecondCheckerCode(user.getCode());
					purchaseCheck.setSecondCheckerName(user.getName());
				} else {
					throw new BusinessException("验收人员2不存在！");
				}
			}
		}


		purchaseCheck.setCheckDate(addInstorageVO.getCheckDate());
        Date receiveDate = DateUtils.StringToDate(DateUtils.DateToString(purchaseReceive.getReceiveDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
        Date checkDate = DateUtils.StringToDate(DateUtils.DateToString(purchaseCheck.getCheckDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
        if(receiveDate.getTime() > checkDate.getTime()) {
        	throw new BusinessException("验收时间必须大于收货时间!");
        }
        //基础单据
        purchaseCheck.setBaseOrderId(purchaseReceive.getId());
        purchaseCheck.setBaseOrderDate(purchaseReceive.getReceiveDate());
        purchaseCheck.setBaseOrderCode(purchaseReceive.getCode());
        purchaseCheck.setBaseOrderType(purchaseReceive.getOrderType());
        //供应商信息
        purchaseCheck.setSupplierId(purchaseReceive.getSupplierId());
        purchaseCheck.setSupplierCode(purchaseReceive.getSupplierCode());
        purchaseCheck.setSupplierName(purchaseReceive.getSupplierName());
        //供应商销售人员信息
        purchaseCheck.setSupplierSalerId(purchaseReceive.getSupplierSalerId());
        purchaseCheck.setSupplierSalerCode(purchaseReceive.getSupplierSalerCode());
        purchaseCheck.setSupplierSalerName(purchaseReceive.getSupplierSalerName());
        purchaseCheck.setSupplierSalerPhone(purchaseReceive.getSupplierSalerPhone());
        purchaseCheck.setQuantityTotal(purchaseReceive.getQuantityTotal());
        purchaseCheck.setVarietiesQuantity(purchaseReceive.getVarietiesQuantity());
        //验收单号,单据类型
        purchaseCheck.setOrderType(OrderRule.PURCHASE_CHECK.getType());
        purchaseCheck.setStatus(status);
		String checkCode = "";
		if(type == 1){
			checkCode = orderCodeComponent.generate(OrderRule.PURCHASE_CHECK.getCodePrefix(),userVO.getParentId(),userVO.getParentCode());
		} else {
			checkCode = orderCodeComponent.generate(OrderRule.PURCHASE_CHECK.getCodePrefix(),userVO.getEnterpriseId(),userVO.getEnterpriseCode());
		}

        purchaseCheck.setCode(checkCode);
        purchaseCheck.setPurchaseOrderId(purchaseReceive.getBaseOrderId());
        purchaseCheckMapper.insertSelective(purchaseCheck);
        //前台细表VO
        List<AddInstorageDtlVO> dtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        //验收明细集合
        List<PurchaseCheckDetail> purchaseCheckDetailList = new ArrayList<>();
        List<PurchaseCheckLot> purchaseCheckLots = new ArrayList<>();
        //合格品货位ID,与不合格品货位ID
        List<Long> qualifiedShelfIds = new ArrayList<Long>();
        for(PurchaseReceiveDetail purchaseReceiveDetails : purchaseReceiveDetailList) {
            //处理前台输入的数量
            for(AddInstorageDtlVO dtlVO : dtlVOList) {
            	PurchaseCheckDetail purchaseCheckDetail = new PurchaseCheckDetail();
            	if(purchaseReceiveDetails.getGoodsId().longValue() == dtlVO.getGoodsId().longValue()) {
                	purchaseCheckDetail = (PurchaseCheckDetail) EntityUtils.reflectAddSetDefaultValue(purchaseCheckDetail.getClass(),userVO);//复制用户
					if(type == 1){
						purchaseCheckDetail.setEnterpriseId(userVO.getParentId());
						purchaseCheckDetail.setParentId(0L);
					}
                	//包含商品信息复制
                	CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseReceiveDetails,purchaseCheckDetail);
                	purchaseCheckDetail.setId(null);
                	//set基础单据信息
                    purchaseCheckDetail.setBaseOrderId(purchaseReceiveDetails.getReceiveId());
                    purchaseCheckDetail.setBaseOrderDate(purchaseReceiveDetails.getReceiveDate());
                    purchaseCheckDetail.setBaseOrderType(purchaseReceiveDetails.getOrderType());
                    purchaseCheckDetail.setBaseOrderCode(purchaseReceiveDetails.getReceiveCode());
                    purchaseCheckDetail.setBaseOrderDtlId(purchaseReceiveDetails.getId());
                    //set状态
                    purchaseCheckDetail.setStatus(status);
                    //set验收单据id/code/类型/日期
                    purchaseCheckDetail.setCheckId(purchaseCheck.getId());
                    purchaseCheckDetail.setCheckCode(purchaseCheck.getCode());
                    purchaseCheckDetail.setOrderType(purchaseCheck.getOrderType());
                    purchaseCheckDetail.setCheckDate(purchaseCheck.getCheckDate());
                    //收货表细单id设置
                    purchaseCheckDetail.setPurchaseOrderId(purchaseReceiveDetails.getBaseOrderId());
                    purchaseCheckDetail.setPurchaseOrderDtlId(purchaseReceiveDetails.getBaseOrderDtlId());
                    //设置数量
					purchaseCheckDetail.setQuantity(purchaseReceiveDetails.getReceiveQuantity());
					if(type == 1){
						purchaseCheckDetail.setQualifiedQuantity(purchaseReceiveDetails.getReceiveQuantity());
						purchaseCheckDetail.setUnqualifiedQuantity(BigDecimal.ZERO);
					} else {
						purchaseCheckDetail.setQualifiedQuantity(dtlVO.getQualifiedQuantity());
						purchaseCheckDetail.setUnqualifiedQuantity(dtlVO.getUnqualifiedQuantity());
					}

                    purchaseCheckDetailList.add(purchaseCheckDetail);
            	}
            }
        }
        //批量保存验收细表数据
        purchaseCheckDetailMapper.batchInsert(purchaseCheckDetailList);
        //处理前台输入批号信息
        for(PurchaseCheckDetail purchaseCheckDetail : purchaseCheckDetailList) {
        	for(AddInstorageDtlVO dtlVO : dtlVOList) {
        		if(purchaseCheckDetail.getGoodsId().longValue() == dtlVO.getGoodsId().longValue()) {
        			//商品批号信息
                	List<AddInstorageGoodsLotNumberVO> glotVO = dtlVO.getGoodsLotNumberVOList();
                	if(null != glotVO && glotVO.size() > 0) {
                		//合格品与不合格品货位id
                		qualifiedShelfIds.addAll(glotVO.stream().map(l ->{
        					return l.getQualifiedShelfId();
        				}).distinct().collect(Collectors.toList()));
                		qualifiedShelfIds.addAll(glotVO.stream().map(l ->{
        					return l.getUnqualifiedShelfId();
        				}).distinct().collect(Collectors.toList()));
                		int kk = 1;
                		for(AddInstorageGoodsLotNumberVO gl : glotVO) {
                			//如果验收数量小于验收合格数量
                            if(gl.getReceiveQuantity().compareTo(gl.getQualifiedQuantity())==-1){
                            	throw new BusinessException("验收合格数量不能大于收货数量");
                            }
                			PurchaseCheckLot purchaseCheckLot = new PurchaseCheckLot();
                			purchaseCheckLot = (PurchaseCheckLot) EntityUtils.reflectAddSetDefaultValue(purchaseCheckLot.getClass(),userVO);//复制用户
							if(type == 1){
								purchaseCheckLot.setEnterpriseId(userVO.getParentId());
								purchaseCheckLot.setParentId(0L);
							}
                			purchaseCheckLot.setCheckDtlId(purchaseCheckDetail.getId());
                            purchaseCheckLot.setCheckId(purchaseCheck.getId());
                            purchaseCheckLot.setCheckProjectIds(gl.getCheckProjectIds());
                            purchaseCheckLot.setGoodsId(purchaseCheckDetail.getGoodsId());
                            purchaseCheckLot.setLotNumber(gl.getLotNumber());
                            purchaseCheckLot.setProductDate(gl.getProductDate());
                            purchaseCheckLot.setValidDate(gl.getValidDate());
                            purchaseCheckLot.setTestReportIds(gl.getTestReportIds());
                            purchaseCheckLot.setReceiveQuantity(gl.getReceiveQuantity());
                            purchaseCheckLot.setSamplingQuantity(gl.getSamplingQuantity());
                            if(type == 1){
								purchaseCheckLot.setQualifiedQuantity(gl.getReceiveQuantity());
								purchaseCheckLot.setUnqualifiedReasonIds(null);
								purchaseCheckLot.setUnqualifiedQuantity(BigDecimal.ZERO);
								//设置货位id
								purchaseCheckLot.setQualifiedShelfId(gl.getQualifiedShelfId());
							} else {
								purchaseCheckLot.setQualifiedQuantity(gl.getQualifiedQuantity());
								purchaseCheckLot.setUnqualifiedReasonIds(gl.getUnqualifiedReasonIds());
								purchaseCheckLot.setUnqualifiedQuantity(gl.getUnqualifiedQuantity());
								purchaseCheckLot.setMeasuresIds(gl.getMeasuresIds());
								//设置货位id
								purchaseCheckLot.setQualifiedShelfId(gl.getQualifiedShelfId());
								purchaseCheckLot.setUnqualifiedShelfId(gl.getUnqualifiedShelfId());
							}
                            purchaseCheckLot.setConclusionIds(gl.getConclusionIds());
                            purchaseCheckLot.setLineNum(kk++);
                            //状态
                            purchaseCheckLot.setStatus(status);
                            purchaseCheckLot.setOrderType(OrderRule.PURCHASE_CHECK.getType());
                            //set未清数量/以清数量
                            purchaseCheckLot.setUnclearQuantity(BigDecimal.ZERO);
                            purchaseCheckLot.setClearQuantity(purchaseCheckLot.getReceiveQuantity());

                            //设置合格不合格状态
                            if(purchaseCheckLot.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) == 0) {
                            	purchaseCheckLot.setJobArea(0);//合格
                            } else {
                            	purchaseCheckLot.setJobArea(2);//不合格品
                            }
                			//验证判断
                            if (purchaseCheckLot.getValidDate().before(purchaseCheckLot.getProductDate())) {
                                throw new BusinessException("有效期必须大于生产日期,否则无法保存");
                            }
                            int num = purchaseCheckLot.getReceiveQuantity().compareTo(purchaseCheckLot.getSamplingQuantity()); //和0，Zero比较
                            if (num < 0) {
                                throw new BusinessException("抽样数量不能大于收货数量,否则无法保存");
                            }
                            int numTwo = purchaseCheckLot.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO); //和0，Zero比较
                            if (numTwo > 0 && (gl.getUnqualifiedReasonIds() == null
                                    || gl.getMeasuresIds() == null || gl.getUnqualifiedReasonIds().equals("")
                                    || gl.getMeasuresIds().equals(""))) {
                                throw new BusinessException("存在验收不合格数量,必须填写不合格原因和处置措施,否则无法保存");
                            }
                            //收货数量验证
                            int receiveQuantity = purchaseCheckLot.getReceiveQuantity().compareTo(purchaseCheckDetail.getQuantity());
                            if (receiveQuantity > 0) {
                                throw new BusinessException("该验收单中的收货数量超出收货单中的收货数量,无法保存");
                            }
                            //合格数量验证
                            int qualifiedQuantity = purchaseCheckLot.getQualifiedQuantity().compareTo(purchaseCheckDetail.getQuantity());
                            if (qualifiedQuantity > 0) {
                                throw new BusinessException("该验收单中的验收合格数量超出收货单中的收货数量,无法保存");
                            }
                            purchaseCheckLots.add(purchaseCheckLot);
                		}
                	}
        		}
            }
        }
        //批量保存批号数据
        purchaseCheckLotMapper.batchInsert(purchaseCheckLots);
        result.put("purchaseCheck", purchaseCheck);
        result.put("purchaseCheckDetailList", purchaseCheckDetailList);
        result.put("purchaseCheckLots", purchaseCheckLots);
        result.put("qualifiedShelfIds", qualifiedShelfIds);
		return result;
	}
	
	//采购入库
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> addInstorage(UserVO userVO, AddInstorageVO addInstorageVO, Map<String, Object> checkMap, Map<String, Object> orderMap) throws Exception,BusinessException{
		Integer type = addInstorageVO.getType();
		Map<String,Object> result = new HashMap<>(3);
		Map<String,SafetyStock> shelfVOMap = new HashMap<>();//配进入库时，使用
		PurchaseCheck purchaseCheck = (PurchaseCheck)checkMap.get("purchaseCheck");
		List<PurchaseCheckDetail> purchaseCheckDetailList  = (List<PurchaseCheckDetail>)checkMap.get("purchaseCheckDetailList");
		PurchaseInStorage storage = new PurchaseInStorage();
		storage = (PurchaseInStorage) EntityUtils.reflectAddSetDefaultValue(storage.getClass(),userVO);//复制用户

		Integer status = PurchaseStatus.WAIT_BILL.getStatus();// 总部新增或者自营店=逆向生成

		if(type == 1){
			storage.setEnterpriseId(userVO.getParentId());
			storage.setParentId(0L);

			if(ChainType.Division.getCode() == userVO.getChainType()){// 加盟店逆向生成的为已完成
				status = PurchaseStatus.FINISHED.getStatus();
			}else if (ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){// 自营店逆向生成为 待开票
				status = PurchaseStatus.WAIT_BILL.getStatus();
			}

		}


		//复制采购订单的计算
		PurchaseOrder purchaseOrder = (PurchaseOrder)orderMap.get("purchaseOrder");
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseOrder,storage);
		storage.setId(null);
		storage.setOrderType(OrderRule.PURCHASE_IN.getType());
		String code = "";
		if(type == 1){
			code = orderCodeComponent.generate(OrderRule.PURCHASE_IN.getCodePrefix(),
					userVO.getParentId(), userVO.getParentCode());
		} else {
			code = orderCodeComponent.generate(OrderRule.PURCHASE_IN.getCodePrefix(),
					userVO.getEnterpriseId(), userVO.getEnterpriseCode());
		}
		storage.setCode(code);
		if(type == 1){
			storage.setInStorageDate(new Date());
		} else {
			//入库日期不能小于验收日期
	        Date checkDate = DateUtils.StringToDate(DateUtils.DateToString(purchaseCheck.getCheckDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
	        Date inStorageDate = DateUtils.StringToDate(DateUtils.DateToString(addInstorageVO.getInStorageDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
	        if(inStorageDate.getTime() < checkDate.getTime()) {
				throw new BusinessException("入库日期不能小于验收日期!");
			}
			storage.setInStorageDate(addInstorageVO.getInStorageDate());
		}

		//基础单据
		storage.setBaseOrderId(purchaseCheck.getId());
		storage.setBaseOrderDate(purchaseCheck.getCheckDate());
		storage.setBaseOrderCode(purchaseCheck.getCode());
		storage.setBaseOrderType(purchaseCheck.getOrderType());
		storage.setPurchaseOrderId(purchaseCheck.getPurchaseOrderId());
		//供货单位信息
		storage.setSupplierId(purchaseCheck.getSupplierId());
		storage.setSupplierCode(purchaseCheck.getSupplierCode());
		storage.setSupplierName(purchaseCheck.getSupplierName());
		storage.setSupplierSalerId(purchaseCheck.getSupplierSalerId());
		storage.setSupplierSalerCode(purchaseCheck.getSupplierSalerCode());
		storage.setSupplierSalerName(purchaseCheck.getSupplierSalerName());
		storage.setSupplierSalerPhone(purchaseCheck.getSupplierSalerPhone());
		if(type == 1){
			//配进入库
			ManageConfig mangeConfigByEPId = manageConfigMapper.getMangeConfigByEPId(userVO.getParentId());
			if(mangeConfigByEPId.getInOutManId() == null || mangeConfigByEPId.getInOutManCode() == null || mangeConfigByEPId.getInOutManName() == null){
				throw new BusinessException("请设置总部的默认入库人员");
			}
			storage.setStorageManId(mangeConfigByEPId.getInOutManId());
			storage.setStorageManCode(mangeConfigByEPId.getInOutManCode());
			storage.setStorageManName(mangeConfigByEPId.getInOutManName());
		} else {
			//人员
			User user = userMapper.selectByPrimaryKey(addInstorageVO.getStorageManId());
			if(user != null) {
				storage.setStorageManId(user.getId());
				storage.setStorageManCode(user.getCode());
				storage.setStorageManName(user.getName());
			} else {
				throw new BusinessException("入库人员不存在！");
			}
		}

		storage.setFlowCode(addInstorageVO.getFlowCode());
		//按验收单据数量为主
		storage.setQuantityTotal(purchaseCheck.getQuantityTotal());
		storage.setVarietiesQuantity(purchaseCheck.getVarietiesQuantity());
		storage.setStatus(status);
		purchaseInStorageMapper.insertSelective(storage);
		//细表数据
		List<PurchaseInStorageDetail> detailList = new ArrayList<>();
		List<PurchaseOrderDetail> purchaseOrderDetailList = (List<PurchaseOrderDetail>)orderMap.get("purchaseOrderDetailList");
		for(PurchaseOrderDetail poDtl : purchaseOrderDetailList){
			PurchaseInStorageDetail dtl = new PurchaseInStorageDetail();
			dtl = (PurchaseInStorageDetail) EntityUtils.reflectAddSetDefaultValue(dtl.getClass(),userVO);//复制用户

			//将采购订单中的细表数据复制入库单
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(poDtl,dtl);
			dtl.setId(null);

			if(type == 1){
				dtl.setEnterpriseId(userVO.getParentId());
				dtl.setParentId(0L);
				dtl.setUnclearQuantity(BigDecimal.ZERO);
				dtl.setClearQuantity(dtl.getQuantity());
			} else {
				dtl.setUnclearQuantity(dtl.getQuantity());
				dtl.setClearQuantity(BigDecimal.ZERO);
			}
			//获取验收单数据
			for(PurchaseCheckDetail checkDetail : purchaseCheckDetailList) {
				if(poDtl.getGoodsId().longValue() == checkDetail.getGoodsId().longValue()) {
					dtl.setInStorageId(storage.getId());
					dtl.setOrderType(OrderRule.PURCHASE_IN.getType());
					dtl.setInStorageCode(storage.getCode());
					if(type == 1){
						dtl.setInStorageDate(new Date());
					} else {
						dtl.setInStorageDate(addInstorageVO.getInStorageDate());
					}

					//基础单据
					dtl.setBaseOrderDtlId(checkDetail.getId());
					dtl.setBaseOrderId(storage.getBaseOrderId());
					dtl.setBaseOrderType(storage.getBaseOrderType());
					dtl.setBaseOrderCode(storage.getBaseOrderCode());
					dtl.setBaseOrderDate(storage.getBaseOrderDate());
					dtl.setPurchaseOrderId(checkDetail.getPurchaseOrderId());
					dtl.setPurchaseOrderDtlId(checkDetail.getPurchaseOrderDtlId());
					//设置入库单状态
					dtl.setStatus(status);
				}
			}
			detailList.add(dtl);
		}
		purchaseInStorageDetailMapper.batchSave(detailList);
		List<PurchaseCheckLot> purchaseCheckLots = (List<PurchaseCheckLot>)checkMap.get("purchaseCheckLots");

		//货位id
		List<Long> qualifiedShelfIds = (List<Long>)checkMap.get("qualifiedShelfIds");
		List<PurchaseInStorageShelf> shelfList = null;
		//批号信息
		// 配进入库货位
		if(type == 1) {
			shelfList = setInStorageShelfList(detailList, storage, userVO, purchaseCheckLots, qualifiedShelfIds, type, shelfVOMap);
		}else {
			shelfList = setInStorageShelfList(detailList, storage, userVO,purchaseCheckLots,qualifiedShelfIds);
		}


		if(shelfList != null && shelfList.size()==0) {
			throw new BusinessException("入库没有对应的货位信息！");
		}
		purchaseInStorageShelfMapper.batchSave(shelfList);

		/**
		 * 更新供应商经营范围
		 */
		for(PurchaseInStorageDetail detail : detailList){

			commonComponent.updateSupplierVarieties(detail,storage.getSupplierId());
		}


		
		// 采购入库生成关键表数据
		OrderModelBuilder builder = new OrderModelBuilder(userVO);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.PURCHASE_IN, storage, shelfList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
		
		financeComponent.purchaseToBalanceAndVoucher(userVO, storage);
		
		//入库的数据
		result.put("storage", storage);
		result.put("detailList", detailList);
		result.put("shelfVOMap", shelfVOMap);
		return result;
	}
	
	/**
	 * 
	 * @Description: 入库的货位信息
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年11月30日 下午4:58:15 
	 * @param detailList
	 * @param storage
	 * @param loginUser
	 * @param purchaseCheckLots
	 * @param qualifiedShelfIds
	 * @return
	 * @throws Exception 
	 * @return List<PurchaseInStorageShelf>
	 */
	private List<PurchaseInStorageShelf> setInStorageShelfList(
			List<PurchaseInStorageDetail> detailList,
			PurchaseInStorage storage, UserVO loginUser, List<PurchaseCheckLot> purchaseCheckLots,List<Long> qualifiedShelfIds,int type,Map<String,SafetyStock> shelfVOMap) throws Exception {
		
		/*Map<Long, PurchaseCheckLot> mapLot = new HashMap<Long, PurchaseCheckLot>();
		if(purchaseCheckLots.size() > 0){
			List<PurchaseCheckLot> listLot = purchaseCheckLots;
			if(listLot != null){
				for (PurchaseCheckLot purchaseCheckLot : listLot) {
					mapLot.put(purchaseCheckLot.getId(), purchaseCheckLot);
				}
			}
		}*/

		Map<Long, WarehouseShelf> mapShelf = new HashMap<Long, WarehouseShelf>();
		if(type == 1){

		} else {

			if(qualifiedShelfIds.size() > 0){
				List<WarehouseShelf> listShelf = warehouseShelfMapper.selectByIds(qualifiedShelfIds);
				if(listShelf != null){
					for (WarehouseShelf warehouseShelf : listShelf) {
						mapShelf.put(warehouseShelf.getId(), warehouseShelf);
					}
				}
			}
		}


		List<PurchaseInStorageShelf> list = new ArrayList<PurchaseInStorageShelf>();
		WarehouseShelf warehouseShelf = null;
		int i=0;
		for (PurchaseInStorageDetail detail : detailList) {
			if(purchaseCheckLots != null && purchaseCheckLots.size() > 0){
				
				BigDecimal lineDisAll = new BigDecimal(0);//总行优惠
				PurchaseInStorageShelf shelf = null;
				//获取验收单的批号信息
				for(PurchaseCheckLot checklot : purchaseCheckLots) {
					if(detail.getGoodsId().longValue() == checklot.getGoodsId()) {
						shelf = new PurchaseInStorageShelf();
						shelf = (PurchaseInStorageShelf) EntityUtils.reflectAddSetDefaultValue(shelf.getClass(),loginUser);//复制用户
						if(type == 1){
							shelf.setEnterpriseId(loginUser.getParentId());
							shelf.setParentId(0L);
						}
						shelf.setInStorageDtlId(detail.getId());
						shelf.setInStorageId(detail.getInStorageId());
						shelf.setGoodsId(detail.getGoodsId());
						shelf.setGoodsCode(detail.getGoodsCode());
						shelf.setGoodsName(detail.getGoodsName());
						shelf.setLotNumber(checklot.getLotNumber());
						shelf.setProductDate(checklot.getProductDate());
						shelf.setValidDate(checklot.getValidDate());

						if(type == 1){
							//配进入库，查询默认货位
							SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(checklot.getGoodsId(), loginUser.getParentId());
							if(safetyStock == null || safetyStock.getDefaultShelfId() == null || safetyStock.getDefaultShelfId() == 0){
								throw new BusinessException("商品编码‘" +detail.getGoodsCode() +  "’商品名称‘" + detail.getGoodsName() + "’在直调采购入库查询默认货位失败！请设置该商品的默认货位");
							}
							Long defaultShelfId = safetyStock.getDefaultShelfId();
							shelf.setShelfId(defaultShelfId);
							shelf.setShelfName(safetyStock.getDefaultShelfName());
							shelfVOMap.put(checklot.getLotNumber() + "" + checklot.getGoodsId(),safetyStock);
							if(checklot.getQualifiedQuantity() != null && checklot.getUnqualifiedQuantity() != null){
								shelf.setQuantity(checklot.getQualifiedQuantity().add(checklot.getUnqualifiedQuantity()));
							} else if(checklot.getQualifiedQuantity() != null){
								shelf.setQuantity(checklot.getQualifiedQuantity());
							} else if(checklot.getUnqualifiedQuantity() != null){
								shelf.setQuantity(checklot.getUnqualifiedQuantity());
							} else {
								shelf.setQuantity(BigDecimal.ZERO);
							}


						} else {
							shelf.setShelfId(checklot.getQualifiedShelfId());
							if(mapShelf.containsKey(shelf.getShelfId())){
								warehouseShelf = mapShelf.get(shelf.getShelfId());
								shelf.setShelfName(warehouseShelf.getName());
							} else {
								throw new BusinessException("货位ID："+shelf.getShelfId()+"查不到数据！");
							}
							if(checklot.getJobArea() == 0){
								//合格数量
								shelf.setQuantity(checklot.getQualifiedQuantity());
							}else{
								//不合格数量
								shelf.setQuantity(checklot.getUnqualifiedQuantity());
							}
						}



						shelf.setCanReturnQuantity(shelf.getQuantity());
						shelf.setLineDiscount(detail.getLineDiscount());
						shelf.setUnitPrice(detail.getUnitPrice());
						if(BigDecimal.ZERO.compareTo(detail.getUnitPrice()) == 0){
							shelf.setAmount(BigDecimal.ZERO);
							//行优惠 = 行金额除以总金额乘以整单优惠金额
							if(i != purchaseCheckLots.size() - 1){

								lineDisAll = lineDisAll.add(BigDecimal.ZERO);
								shelf.setLineDiscountAmount(BigDecimal.ZERO);
							} else {
								//最后一条减，保证小数除不尽问题
								shelf.setLineDiscountAmount(detail.getLineDiscountAmount().subtract(lineDisAll));
							}
						} else {

							shelf.setAmount(shelf.getUnitPrice()
									.multiply(shelf.getQuantity().multiply(shelf.getLineDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))));

							//行优惠 = 行金额除以总金额乘以整单优惠金额
							if(i != purchaseCheckLots.size() - 1){
								BigDecimal lineDis = shelf.getAmount().divide(detail.getAmount(), 4, BigDecimal.ROUND_HALF_UP)
										.multiply(detail.getLineDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);//行优惠（整单优惠分摊到行的金额）
								lineDisAll = lineDisAll.add(lineDis);
								shelf.setLineDiscountAmount(lineDis);
							} else {
								//最后一条减，保证小数除不尽问题
								shelf.setLineDiscountAmount(detail.getLineDiscountAmount().subtract(lineDisAll));
							}
						}


						shelf.setWholeDiscount(detail.getWholeDiscount());


						//实际金额 = 单行金额乘以整单折扣减去行优惠
						shelf.setRealAmount(shelf.getAmount().multiply(detail.getWholeDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))
								.subtract(shelf.getLineDiscountAmount()));
						//实际单价（实际金额/数量）
						shelf.setRealPrice(CalculateComponent.
								getRealPriceByRealAmountAndQuantity(shelf.getRealAmount(),shelf.getQuantity()));

						shelf.setTaxRateId(detail.getTaxRateId());
						shelf.setTaxRate(detail.getTaxRate());
						shelf.setNotaxRealPrice(detail.getNotaxRealPrice());
						shelf.setNotaxRealAmount(shelf.getNotaxRealPrice().multiply(shelf.getQuantity()));
						shelf.setTaxAmount(shelf.getRealAmount().subtract(shelf.getNotaxRealAmount()));

						shelf.setUnclearQuantity(BigDecimal.ZERO);// 反向生成的未清为0
						shelf.setClearQuantity(shelf.getQuantity());
						shelf.setVerificationQuantity(BigDecimal.ZERO);
						shelf.setUnverificationQuantity(shelf.getQuantity());
						shelf.setLineNum(checklot.getLineNum());
						shelf.setStatus(storage.getStatus());

						list.add(shelf);



						
						/*shelf.setCreaterId(loginUser.getUserId());
						shelf.setCreaterCode(loginUser.getUserCode());
						shelf.setCreaterName(loginUser.getUserName());
						shelf.setCreateTime(new Date());
						shelf.setModifierId(loginUser.getUserId());
						shelf.setModifierCode(loginUser.getUserCode());
						shelf.setModifierName(loginUser.getUserName());
						shelf.setUpdateTime(new Date());*/

						if(type == 1){

						} else {
							commonComponent.updateGoodsDefShelf(shelf.getEnterpriseId(),
									shelf.getParentId(), loginUser.getChainType(), shelf.getGoodsId(),
									shelf.getShelfId(), shelf.getShelfName(), loginUser);
						}

						
						if(type == 1){
							LastInPriceVO lastInPriceVO = new LastInPriceVO();
							/**
							 private Long inTaxRateId;// 入库税率ID
							 private BigDecimal inTaxRate;// 入库税率
							 private BigDecimal inPrice;// 入库单价
							 */
							lastInPriceVO.setEnterpriseId(loginUser.getParentId());
							lastInPriceVO.setParentId(0L);
							lastInPriceVO.setChainType(loginUser.getChainType());
							lastInPriceVO.setSupplierId(storage.getSupplierId());
							lastInPriceVO.setPurTaxRateId(shelf.getTaxRateId());
							lastInPriceVO.setPurTaxRate(shelf.getTaxRate());
							lastInPriceVO.setPurPrice(shelf.getRealPrice());
							lastInPriceVO.setGoodsId(shelf.getGoodsId());
							lastInPriceVO.setInTaxRateId(shelf.getTaxRateId());
							lastInPriceVO.setInTaxRate(shelf.getTaxRate());
							lastInPriceVO.setInPrice(shelf.getUnitPrice());
							commonComponent.updateLastPriceInfo(lastInPriceVO);

						} else {
							LastInPriceVO lastInPriceVO = new LastInPriceVO();
							/**
							 private Long inTaxRateId;// 入库税率ID
							 private BigDecimal inTaxRate;// 入库税率
							 private BigDecimal inPrice;// 入库单价
							 */
							lastInPriceVO.setEnterpriseId(loginUser.getEnterpriseId());
							lastInPriceVO.setParentId(loginUser.getParentId());
							lastInPriceVO.setChainType(loginUser.getChainType());
							lastInPriceVO.setSupplierId(storage.getSupplierId());
							lastInPriceVO.setPurTaxRateId(shelf.getTaxRateId());
							lastInPriceVO.setPurTaxRate(shelf.getTaxRate());
							lastInPriceVO.setPurPrice(shelf.getRealPrice());
							lastInPriceVO.setGoodsId(shelf.getGoodsId());
							lastInPriceVO.setInTaxRateId(shelf.getTaxRateId());
							lastInPriceVO.setInTaxRate(shelf.getTaxRate());
							lastInPriceVO.setInPrice(shelf.getUnitPrice());
							commonComponent.updateLastPriceInfo(lastInPriceVO);
						}

					}
					i++;
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Description: 入库的货位信息
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年11月30日 下午4:58:15 
	 * @param detailList
	 * @param storage
	 * @param loginUser
	 * @param purchaseCheckLots
	 * @param qualifiedShelfIds
	 * @return
	 * @throws Exception 
	 * @return List<PurchaseInStorageShelf>
	 */
	private List<PurchaseInStorageShelf> setInStorageShelfList(
			List<PurchaseInStorageDetail> detailList,
			PurchaseInStorage storage, UserVO loginUser, List<PurchaseCheckLot> purchaseCheckLots,List<Long> qualifiedShelfIds) throws Exception {	
		
		/*Map<Long, PurchaseCheckLot> mapLot = new HashMap<Long, PurchaseCheckLot>();
		if(purchaseCheckLots.size() > 0){
			List<PurchaseCheckLot> listLot = purchaseCheckLots;
			if(listLot != null){
				for (PurchaseCheckLot purchaseCheckLot : listLot) {
					mapLot.put(purchaseCheckLot.getId(), purchaseCheckLot);
				}
			}
		}*/
		
		Map<Long, WarehouseShelf> mapShelf = new HashMap<Long, WarehouseShelf>();
		if(qualifiedShelfIds.size() > 0){
			List<WarehouseShelf> listShelf = warehouseShelfMapper.selectByIds(qualifiedShelfIds);
			if(listShelf != null){
				for (WarehouseShelf warehouseShelf : listShelf) {
					mapShelf.put(warehouseShelf.getId(), warehouseShelf);
				}
			}
		}
		
		List<PurchaseInStorageShelf> list = new ArrayList<PurchaseInStorageShelf>();
		WarehouseShelf warehouseShelf = null;
		int i = 0;
		for (PurchaseInStorageDetail detail : detailList) {
			if(purchaseCheckLots != null && purchaseCheckLots.size() > 0){
				BigDecimal lineDisAll = new BigDecimal(0);//总行优惠
				PurchaseInStorageShelf shelf = null;
				//获取验收单的批号信息
				for(PurchaseCheckLot checklot : purchaseCheckLots) {
					if(detail.getGoodsId().longValue() ==  checklot.getGoodsId()) {
						// 合格品数量
						if(checklot.getQualifiedQuantity().compareTo(BigDecimal.ZERO) == 1) {
							shelf = new PurchaseInStorageShelf();
							shelf = (PurchaseInStorageShelf) EntityUtils.reflectAddSetDefaultValue(shelf.getClass(),loginUser);//复制用户
							//合格数量
							shelf.setQuantity(checklot.getQualifiedQuantity());
							shelf.setShelfId(checklot.getQualifiedShelfId());
							setShelf(i, lineDisAll, loginUser, list, storage, purchaseCheckLots, shelf, checklot, detail, mapShelf, warehouseShelf);
						}
						
						// 不合格数量大于0则入不合格品货位
						if(checklot.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) == 1) {
							shelf = new PurchaseInStorageShelf();
							shelf = (PurchaseInStorageShelf) EntityUtils.reflectAddSetDefaultValue(shelf.getClass(),loginUser);//复制用户
							//不合格数量
							shelf.setQuantity(checklot.getUnqualifiedQuantity());
							shelf.setShelfId(checklot.getUnqualifiedShelfId());
							setShelf(i, lineDisAll, loginUser, list, storage, purchaseCheckLots, shelf, checklot, detail, mapShelf, warehouseShelf);
						}
					}
					i++;
				}
			}
		}
		return list;
	}
	
	private void setShelf(int i,BigDecimal lineDisAll,
			UserVO loginUser,
			List<PurchaseInStorageShelf> list,
			PurchaseInStorage storage,
			List<PurchaseCheckLot> purchaseCheckLots,PurchaseInStorageShelf shelf,PurchaseCheckLot checklot,
			PurchaseInStorageDetail detail,Map<Long, WarehouseShelf> mapShelf,WarehouseShelf warehouseShelf) {
		shelf.setInStorageDtlId(detail.getId());
		shelf.setInStorageId(detail.getInStorageId());
		shelf.setGoodsId(detail.getGoodsId());
		shelf.setGoodsCode(detail.getGoodsCode());
		shelf.setGoodsName(detail.getGoodsName());
		shelf.setLotNumber(checklot.getLotNumber());
		shelf.setProductDate(checklot.getProductDate());
		shelf.setValidDate(checklot.getValidDate());
		if(mapShelf.containsKey(shelf.getShelfId())){
			warehouseShelf = mapShelf.get(shelf.getShelfId());
			shelf.setShelfName(warehouseShelf.getName());
		/*}else if(lotList.get(i).getShelfId() != null && lotList.get(i).getShelfId() != 0){
			throw new Exception("货位ID："+lotList.get(i).getShelfId()+"查不到数据！");
		}*/
		} else {
			throw new BusinessException("货位ID："+shelf.getShelfId()+"查不到数据！");
		}

		shelf.setCanReturnQuantity(shelf.getQuantity());
		
		shelf.setUnitPrice(detail.getUnitPrice());
		shelf.setLineDiscount(detail.getLineDiscount());
		shelf.setAmount(shelf.getUnitPrice()
				.multiply(shelf.getQuantity().multiply(shelf.getLineDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))));
		
		shelf.setWholeDiscount(detail.getWholeDiscount());
		
		//行优惠 = 行金额除以总金额乘以整单优惠金额
		shelf.setUnitPrice(detail.getUnitPrice());
		shelf.setLineDiscount(detail.getLineDiscount());
		shelf.setAmount(shelf.getUnitPrice()
				.multiply(shelf.getQuantity().multiply(shelf.getLineDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))));

		shelf.setWholeDiscount(detail.getWholeDiscount());
		
		if(BigDecimal.ZERO.compareTo(detail.getUnitPrice()) == 0){
			shelf.setAmount(BigDecimal.ZERO);
			//行优惠 = 行金额除以总金额乘以整单优惠金额
			if(i != purchaseCheckLots.size() - 1){
				lineDisAll = lineDisAll.add(BigDecimal.ZERO);
				shelf.setLineDiscountAmount(BigDecimal.ZERO);
			} else {
				//最后一条减，保证小数除不尽问题
				shelf.setLineDiscountAmount(detail.getLineDiscountAmount().subtract(lineDisAll));
			}
		} else {
			//行优惠 = 行金额除以总金额乘以整单优惠金额
			if(i != purchaseCheckLots.size() - 1){
				BigDecimal lineDis = shelf.getAmount().divide(detail.getAmount(), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(detail.getLineDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);//行优惠（整单优惠分摊到行的金额）
				lineDisAll = lineDisAll.add(lineDis);
				shelf.setLineDiscountAmount(lineDis);
			} else {
				//最后一条减，保证小数除不尽问题
				shelf.setLineDiscountAmount(detail.getLineDiscountAmount().subtract(lineDisAll));
			}
		}
		//实际金额 = 单行金额乘以整单折扣减去行优惠
		shelf.setRealAmount(shelf.getAmount().multiply(detail.getWholeDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))
				.subtract(shelf.getLineDiscountAmount()));
		//实际单价（实际金额/数量）
		shelf.setRealPrice(CalculateComponent.
				getRealPriceByRealAmountAndQuantity(shelf.getRealAmount(),shelf.getQuantity()));

		shelf.setTaxRateId(detail.getTaxRateId());
		shelf.setTaxRate(detail.getTaxRate());
		shelf.setNotaxRealPrice(detail.getNotaxRealPrice());
		shelf.setNotaxRealAmount(shelf.getNotaxRealPrice().multiply(shelf.getQuantity()));
		shelf.setTaxAmount(shelf.getRealAmount().subtract(shelf.getNotaxRealAmount()));

		shelf.setUnclearQuantity(shelf.getQuantity());
		shelf.setClearQuantity(new BigDecimal(0));
		shelf.setVerificationQuantity(BigDecimal.ZERO);
		shelf.setUnverificationQuantity(shelf.getQuantity());
		shelf.setLineNum(checklot.getLineNum());
		shelf.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
		
		list.add(shelf);
		
		commonComponent.updateGoodsDefShelf(shelf.getEnterpriseId(), 
				shelf.getParentId(), loginUser.getChainType(), shelf.getGoodsId(), 
				shelf.getShelfId(), shelf.getShelfName(), loginUser);
		
		
		LastInPriceVO lastInPriceVO = new LastInPriceVO();
        /**
         private Long inTaxRateId;// 入库税率ID
         private BigDecimal inTaxRate;// 入库税率
         private BigDecimal inPrice;// 入库单价
         */
        lastInPriceVO.setEnterpriseId(loginUser.getEnterpriseId());
        lastInPriceVO.setParentId(loginUser.getParentId());
        lastInPriceVO.setChainType(loginUser.getChainType());
        lastInPriceVO.setSupplierId(storage.getSupplierId());
        lastInPriceVO.setPurTaxRateId(shelf.getTaxRateId());
        lastInPriceVO.setPurTaxRate(shelf.getTaxRate());
        lastInPriceVO.setPurPrice(shelf.getRealPrice());
        lastInPriceVO.setGoodsId(shelf.getGoodsId());
        lastInPriceVO.setInTaxRateId(shelf.getTaxRateId());
        lastInPriceVO.setInTaxRate(shelf.getTaxRate());
        lastInPriceVO.setInPrice(shelf.getUnitPrice());
        commonComponent.updateLastPriceInfo(lastInPriceVO);
	}
	

	@SuppressWarnings("rawtypes")
	@Override
	public Page<List<PurchaseOrderDataVO>> getPurchaseOrder(Page page, UserVO userVO, Date startTime, Date endTime)
			throws Exception, BusinessException {
		List<PurchaseOrderDataVO> list = new ArrayList<>();
		Page<List<PurchaseOrderDataVO>> pageVO = new Page<>();
		SimpleDateFormat formatter = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		if(startTime != null && endTime != null) {
			String startDate = formatter.format(startTime); 
			String endDate = formatter.format(endTime);
			startTime =  DateUtils.StringToDate(startDate, DateStyle.YYYY_MM_DD);
			endTime =  DateUtils.StringToDate(endDate, DateStyle.YYYY_MM_DD);
		}
		Long enterpriseId = userVO.getEnterpriseId();
		//审核通过且可以入库的单据
		List<Integer> statusList = new ArrayList<>();
		statusList.add(PurchaseStatus.WAIT_RECEIVE.getStatus());
		Long totalRecord = purchaseOrderMapper.queryCountByEnterpriseId(enterpriseId, null,null,null,null,statusList,null,startTime,endTime);
		List<PurchaseOrderResponseVO>  purchaseOrderList = purchaseOrderMapper.selectByEnterpriseId(page.getStart(),page.getPageSize(),enterpriseId,
    			null,null,null,null,null,statusList,null,startTime,endTime);
		
		//采购订单主表id
		List<Long> orderIds = new ArrayList<>();
		if(null != purchaseOrderList && purchaseOrderList.size() > 0) {
			orderIds.addAll(purchaseOrderList.stream().map(l ->{
				return l.getId();
			}).distinct().collect(Collectors.toList()));
			purchaseOrderList.forEach(p->{
				PurchaseOrderDataVO orderDataVO = new PurchaseOrderDataVO();
				CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(p, orderDataVO);
				list.add(orderDataVO);
			});
		}
		//配送信息
		if(orderIds.size() > 0) {
			List<PurchaseOrderOther> otherList = purchaseOrderOtherMapper.selectByEnterpriseIdAndOrderIds(enterpriseId, orderIds);
			if(null != otherList && otherList.size() > 0) {
				list.forEach(l->{
					otherList.forEach(o->{
						if(l.getId().longValue() == o.getOrderId().longValue()) {
							PurchaseOrderOtherVO otherVO = new PurchaseOrderOtherVO();
							CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(o,otherVO);
							l.setCarriageMode(otherVO.getCarriageMode());
							l.setOrderOtherVO(otherVO);
						}
					});
				});
			}
		}
		pageVO.setTotalRecord(totalRecord.intValue());
		pageVO.setResult(list);
		return pageVO;
	}

	@Override
	public Page<List<PurchaseOrderGoodsVO>> getGoodsVO(Page<?> page, UserVO userVO, Long orderId)
			throws Exception, BusinessException {
		Long enterpriseId = userVO.getEnterpriseId();
		Page<List<PurchaseOrderGoodsVO>> pageVO = new Page<>();
		List<PurchaseOrderGoodsVO> list = purchaseOrderDetailMapper.getOrderGoods(page.getStart(),page.getPageSize(), enterpriseId, orderId);
		if(null != list && list.size() > 0) {
			list.parallelStream().forEach(item->{
				//获取商品默认货位
				SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(item.getGoodsId(), enterpriseId);
				if(null != safetyStock) {
					item.setDefaultShelfId(safetyStock.getDefaultShelfId());
					item.setDefaultShelfName(safetyStock.getDefaultShelfName());
				}
			});
		}
		Long count = purchaseOrderDetailMapper.countOrderGoods(enterpriseId, orderId);
		pageVO.setTotalRecord(count.intValue());
		pageVO.setResult(list);
		return pageVO;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void saveByPurchaseOrder(UserVO userVO, AddInstorageVO addInstorageVO) throws Exception, BusinessException {
		Map<String,Object> orderMap = getOrder(userVO.getEnterpriseId(), addInstorageVO);
		//更新采购订单状态及重新计算金额
		updatePurchaseOrder(orderMap, addInstorageVO);
		Map<String,Object> receiveMap = addReceive(userVO, addInstorageVO, orderMap);
		Map<String,Object> checkMap = addCheck(userVO, addInstorageVO, receiveMap);
		addInstorage(userVO, addInstorageVO, checkMap, orderMap);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public List<String> saveByPurchaseOrderReturnMsg(UserVO userVO, AddInstorageVO addInstorageVO) throws Exception, BusinessException {
		Map<String,Object> orderMap = getOrder(userVO.getEnterpriseId(), addInstorageVO);
		//更新采购订单状态及重新计算金额
		updatePurchaseOrder(orderMap, addInstorageVO);
		Map<String,Object> receiveMap = addReceive(userVO, addInstorageVO, orderMap);
		Map<String,Object> checkMap = addCheck(userVO, addInstorageVO, receiveMap);
		Map<String,Object> storage = addInstorage(userVO, addInstorageVO, checkMap, orderMap);
		return setReturnMsg(1,orderMap, receiveMap, checkMap, storage);
	}
	
	private Map<String,Object> getOrder(Long enterpriseId,AddInstorageVO addInstorageVO)throws Exception, BusinessException {
		Map<String,Object> result = new HashMap<>(3);
		Long purchaseOrderId = addInstorageVO.getPurchaseOrderId();
		PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseOrderId);
		if(null == purchaseOrderId || purchaseOrderId.longValue() == 0 || null == purchaseOrder) {
			throw new BusinessException("调用的采购订单数据不存在！");
		} else {
			// 已完成的状态不能调用
			if(purchaseOrder.getStatus() == PurchaseStatus.FINISHED.getStatus()) {
				throw new BusinessException("调用的采购订单状态已完成！");
			}
		}
		List<PurchaseOrderDetail> purchaseOrderDetailList = purchaseOrderDetailMapper.selectByEnterpriseIdByOrderId(enterpriseId, purchaseOrderId);
		if(null == purchaseOrderDetailList) {
			throw new BusinessException("调用的采购订单商品信息数据不存在！");
		}
		//验收单中商品与前台输入商品验证
		checkGoodsQuantity(addInstorageVO.getAddInstorageDtlVOList(), purchaseOrderDetailList);
		PurchaseOrderOther purchaseOrderOther = purchaseOrderOtherMapper.selectByEnterPriseId(enterpriseId, purchaseOrderId);
		if(null == purchaseOrderOther) {
			throw new BusinessException("调用的采购订单配送信息数据不存在！");
		}
		result.put("purchaseOrder", purchaseOrder);
        result.put("purchaseOrderDetailList", purchaseOrderDetailList);
        result.put("purchaseOrderOther", purchaseOrderOther);
		return result;
	}
	
	/**
	 * 
	 * @Description: 更新采购订单的状态
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月4日 下午1:22:44 
	 * @param map 
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void updatePurchaseOrder(Map<String,Object> map,AddInstorageVO addInstorageVO) {
		PurchaseOrder purchaseOrder = (PurchaseOrder) map.get("purchaseOrder");
		List<PurchaseOrderDetail> purchaseOrderDetailList = (List<PurchaseOrderDetail>) map.get("purchaseOrderDetailList");
		PurchaseOrderOther purchaseOrderOther = (PurchaseOrderOther) map.get("purchaseOrderOther");
		//更新采购订单
		updateSetPurchaseOrder(addInstorageVO, purchaseOrderDetailList, purchaseOrder);
		//更新状态为待开票
        purchaseOrderOther.setStatus(WAIT_BILL);
		purchaseOrderOther.setUpdateTime(new Date());
        purchaseOrderOtherMapper.updateByPrimaryKey(purchaseOrderOther);
	}
	
	/**
	 * 
	 * @Description: 验收单中商品与前台输入商品验证
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月5日 上午10:41:29 
	 * @param dtlVO
	 * @param orderDtl 
	 * @return void
	 */
	private void checkGoodsQuantity(List<AddInstorageDtlVO> dtlVO,List<PurchaseOrderDetail> orderDtl) {
		List<Long> goodsIdVOList = new ArrayList<>();//前台输入商品
		List<Long> orderGoodsIdList = new ArrayList<>();//采购订单细表商品
		if(null == dtlVO) {
			throw new BusinessException("请添加采购入库商品！");
		}
		if(null == orderDtl) {
			throw new BusinessException("调用的入库单没有商品！");
		}
		goodsIdVOList.addAll(dtlVO.stream().map(d->{
			return d.getGoodsId();
		}).distinct().collect(Collectors.toList()));
		orderGoodsIdList.addAll(orderDtl.stream().map(o->{
			return o.getGoodsId();
		}).distinct().collect(Collectors.toList()));
		if(!goodsIdVOList.containsAll(orderGoodsIdList) || goodsIdVOList.size() != orderGoodsIdList.size()) {
			throw new BusinessException("调用采购订单必须引用采购订单中的商品！");
		}
	}
	
	/**
	 * 
	 * @Description: 如果数量不相等则以前台输入的数量为主,并重新计算
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月5日 下午3:29:37 
	 * @param addInstorageVO
	 * @param orderDtl
	 * @param purchaseOrder 
	 * @return void
	 */
	private void updateSetPurchaseOrder(AddInstorageVO addInstorageVO,List<PurchaseOrderDetail> orderDtl,PurchaseOrder purchaseOrder) {
		BigDecimal quantityTotal = BigDecimal.ZERO;//数量合计
        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税总额 
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额
        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
		BigDecimal wholeDiscount = addInstorageVO.getWholeDiscount();
		PurchaseCalcul purchaseCalcul = new PurchaseCalcul();
        List<AddInstorageDtlVO> dtlVO = addInstorageVO.getAddInstorageDtlVOList();
		for(PurchaseOrderDetail odtl : orderDtl) {
			for(AddInstorageDtlVO dvo : dtlVO) {
				if(dvo.getGoodsId().longValue() == odtl.getGoodsId().longValue()) {
					//以前台输入的数量为主,并重新计算
					setCalcul(dvo, odtl, quantityTotal, amountTotal, notaxRealAmountTotal, taxAmountTotal, realAmountTotal, addInstorageVO.getWholeDiscountAmount(),wholeDiscount,purchaseCalcul);
					//更新状态为待开票
					odtl.setStatus(WAIT_BILL);
					odtl.setUpdateTime(new Date());
					purchaseOrderDetailMapper.updateByPrimaryKeySelective(odtl);
				}
			}
		}
		//设置主表信息
		purchaseOrder.setQuantityTotal(purchaseCalcul.getQuantityTotal());
        purchaseOrder.setVarietiesQuantity(orderDtl == null ? 0 : orderDtl.size());
    	purchaseOrder.setAmountTotal(purchaseCalcul.getAmountTotal());//总额（优惠前金额合计）
    	purchaseOrder.setWholeDiscount(wholeDiscount);
		purchaseOrder.setWholeDiscountAmount(addInstorageVO.getWholeDiscountAmount());
    	purchaseOrder.setRealAmountTotal(purchaseCalcul.getRealAmountTotal());//实际金额合计
    	purchaseOrder.setNotaxRealAmountTotal(purchaseCalcul.getNotaxRealAmountTotal());
    	purchaseOrder.setTaxAmountTotal(purchaseCalcul.getTaxAmountTotal());
		//更新状态为待开票
    	purchaseOrder.setStatus(WAIT_BILL);
        purchaseOrder.setUpdateTime(new Date());
        purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);
	}
	
}
