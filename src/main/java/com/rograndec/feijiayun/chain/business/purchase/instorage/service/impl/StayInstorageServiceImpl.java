package com.rograndec.feijiayun.chain.business.purchase.instorage.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierVarietiesMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
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
import com.rograndec.feijiayun.chain.business.purchase.instorage.service.StayInstorageService;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.LastInPriceVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StayInstorageServiceImpl implements StayInstorageService{

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
	private PurchaseCheckMapper purchaseCheckMapper;
	
	@Autowired
	private PurchaseCheckDetailMapper purchaseCheckDetailMapper;
	
	@Autowired
	private PurchaseCheckLotMapper purchaseCheckLotMapper;

	@Autowired
	private PurchaseReceiveMapper purchaseReceiveMapper;
	
	@Autowired
	private PurchaseReceiveDetailMapper purchaseReceiveDetailMapper;
	
	@Autowired
	private PurchaseReceiveOtherMapper purchaseReceiveOtherMapper;
	
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	
	@Autowired
	private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
	
	@Autowired
	private PurchaseOrderOtherMapper purchaseOrderOtherMapper;
	
	@Autowired
	private PriceOrderDetailMapper priceOrderDetailMapper;
	
	@Autowired
	private WarehouseShelfMapper warehouseShelfMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderCodeComponent orderCodeComponent;
	
	@Autowired
	private SafetyStockMapper safetyStockMapper;
	
	@Autowired
	private GoodsTaxRateMapper goodsTaxRateMapper;
	
	@Autowired
	private SupplierVarietiesMapper supplierVarietiesMapper;
	
	@Autowired
	private GoodsBusinessMapper goodsBusinessMapper;

	@Autowired
	private RedisComponent redisComponent;
	
	@Autowired
	private FinanceComponent financeComponent;
	
	
	@Override
	public StayInstorageFormVO queryStayInstorageFormByCheckId(Long id,
			UserVO loginUser) {
		
		PurchaseCheck check = purchaseCheckMapper.selectByPrimaryKey(id);
		
		if(check != null){
			StayInstorageFormVO vo = StayInstorageFormVO.setVoByPurchaseCheck(check, loginUser);
			
			BigDecimal amountTotal = new BigDecimal(0);//总商品金额
			BigDecimal wholeDiscountAmount = new BigDecimal(0);//整单优惠金额
			BigDecimal notaxRealAmountTotal = new BigDecimal(0);//不含税总额
			
			List<PurchaseCheckDetail> detailList = purchaseCheckDetailMapper.selectByCheckId(check.getId());
			
			List<Long> orderDtlId = new ArrayList<Long>();
			
			if(detailList != null){
				
				for (PurchaseCheckDetail purchaseCheckDetail : detailList) {

					orderDtlId.add(purchaseCheckDetail.getPurchaseOrderDtlId());
				}
				
				Map<Long, PurchaseOrderDetail> orderMap = new HashMap<>();
				List<PurchaseOrderDetail> orderList = purchaseOrderDetailMapper.selectOrderDetailInId(orderDtlId);
				if(orderList != null){
					for (PurchaseOrderDetail detail : orderList) {
						orderMap.put(detail.getId(), detail);
					}
				}
				
				for (PurchaseCheckDetail purchaseCheckDetail : detailList) {
					if(orderMap.containsKey(purchaseCheckDetail.getPurchaseOrderDtlId())){
						
						PurchaseOrderDetail datail = orderMap.get(purchaseCheckDetail.getPurchaseOrderDtlId());
						
						BigDecimal de = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseCheckDetail.getQuantity(),
								datail.getUnitPrice(), datail.getLineDiscount());
						amountTotal = amountTotal.add(de);
						
						//优惠分摊=金额*整单折扣/100-实际单价*实际数量
						BigDecimal whole = de.multiply(datail.getWholeDiscount().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
								.subtract(datail.getRealPrice().multiply(purchaseCheckDetail.getQuantity()));
						wholeDiscountAmount = wholeDiscountAmount.add(whole);
						
						//不含税金额 =不含税单价*收货数量 
						BigDecimal notax = datail.getNotaxRealPrice().multiply(purchaseCheckDetail.getQuantity());
						notaxRealAmountTotal = notaxRealAmountTotal.add(notax);
					}
				}
			}
			
			PurchaseOrder order = purchaseOrderMapper.selectByPrimaryKey(check.getPurchaseOrderId());
			
			if(order != null){
				vo.setAmountTotal(amountTotal);
				vo.setWholeDiscount(order.getWholeDiscount());
				
				BigDecimal discount = new BigDecimal(1).subtract(order.getWholeDiscount().divide(new BigDecimal(100), 10, BigDecimal.ROUND_HALF_UP));
				
				vo.setWholeDiscountMoney(amountTotal.multiply(discount));
				vo.setWholeDiscountAmount(wholeDiscountAmount);
				vo.setRealAmountTotal(amountTotal.subtract(vo.getWholeDiscountMoney()).subtract(vo.getWholeDiscountAmount()));
				vo.setNotaxRealAmountTotal(notaxRealAmountTotal);
			}
			
			return vo;
		}
		
		return null;
	}

	@Override
	public List<StayInstorageDetailVO> selectStayInstorageDetailListByCheckId(
			Long id, UserVO loginUser) {
		
		List<StayInstorageDetailVO> voList = purchaseCheckLotMapper.selectStayInstorageDetailListByCheckId(id); 
		
		if(voList != null && voList.size() > 0){
			
			List<Long> orderDtlId = new ArrayList<Long>();//purchaseOrderDetailId
			List<Long> goodsIds = new ArrayList<Long>();//goodsId
			for (StayInstorageDetailVO stayInstorageDetailVO : voList) {
				orderDtlId.add(stayInstorageDetailVO.getPurchaseOrderDtlId());
				goodsIds.add(stayInstorageDetailVO.getGoodsId());
			}
			
			//查询安全库存
			Map<Long, SafetyStock> safetyStockMap = new HashMap<Long, SafetyStock>();
			List<SafetyStock> safetyStockList = safetyStockMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, loginUser.getEnterpriseId());
			if(safetyStockList != null){
				for (SafetyStock safetyStock : safetyStockList) {
					safetyStockMap.put(safetyStock.getGoodsId(), safetyStock);
				}
			}
			
			//查询价格清单
			Map<Long, PriceOrderDetail> priceMap = new HashMap<Long, PriceOrderDetail>();
			List<PriceOrderDetail> priceDetailList = priceOrderDetailMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, loginUser.getEnterpriseId());
			if(priceDetailList != null){
				for (PriceOrderDetail purchaseOrderDetail : priceDetailList) {
					priceMap.put(purchaseOrderDetail.getGoodsId(), purchaseOrderDetail);
				}
			}
			
			//查询采购订单明细
			Map<Long, PurchaseOrderDetail> detailMap = new HashMap<Long, PurchaseOrderDetail>();
			List<PurchaseOrderDetail> orderDetailList = purchaseOrderDetailMapper.selectOrderDetailInId(orderDtlId);
			if(orderDetailList != null){
				for (PurchaseOrderDetail purchaseOrderDetail : orderDetailList) {
					detailMap.put(purchaseOrderDetail.getId(), purchaseOrderDetail);
				}
			}
			
			List<CheckLotDetailVO> list = null;
			for (StayInstorageDetailVO stayInstorageDetailVO : voList) {
				if(stayInstorageDetailVO.getCheckLotDetailList() != null 
						&& stayInstorageDetailVO.getCheckLotDetailList().size() > 0){
					
					stayInstorageDetailVO = setVOData(stayInstorageDetailVO, detailMap, priceMap, loginUser.getEnterpriseId());
					
					list = new ArrayList<CheckLotDetailVO>();
					
					CheckLotDetailVO vo = null;
					for (CheckLotDetailVO checkLotDetailVO : stayInstorageDetailVO.getCheckLotDetailList()) {
						
						if(checkLotDetailVO.getQualifiedQuantity() != null 
								&& checkLotDetailVO.getQualifiedQuantity().doubleValue() != 0){
							//合格数量
							vo = new CheckLotDetailVO();
							vo.setCheckLotId(checkLotDetailVO.getCheckLotId());
							vo.setJobArea(QualityCondition.NONCONFORMING_PRODUCT.getCode());
							vo.setJobAreaName(QualityCondition.NONCONFORMING_PRODUCT.getValue());
							vo.setLotNumber(checkLotDetailVO.getLotNumber());
							vo.setProductDate(checkLotDetailVO.getProductDate());
							vo.setValidDate(checkLotDetailVO.getValidDate());
							vo.setStringProductDate(DateUtils.DateToString(
									checkLotDetailVO.getProductDate(), DateUtils.FMT_DATE));
							vo.setStringValidDate(DateUtils.DateToString(
									checkLotDetailVO.getValidDate(), DateUtils.FMT_DATE));
							vo.setReceiveQuantity(checkLotDetailVO.getQualifiedQuantity());
							vo.setQualifiedQuantity(checkLotDetailVO.getQualifiedQuantity());
							vo.setUnqualifiedQuantity(checkLotDetailVO.getUnqualifiedQuantity());
							
							if(safetyStockMap.containsKey(stayInstorageDetailVO.getGoodsId())){
								if(safetyStockMap.get(stayInstorageDetailVO.getGoodsId()).getDefaultShelfId()!=0){
									vo.setShelfId(safetyStockMap.get(stayInstorageDetailVO.getGoodsId()).getDefaultShelfId());
									vo.setShelfName(safetyStockMap.get(stayInstorageDetailVO.getGoodsId()).getDefaultShelfName());
								}
							}
							
							list.add(vo);
						}
						
						if(checkLotDetailVO.getUnqualifiedQuantity() != null 
								&& checkLotDetailVO.getUnqualifiedQuantity().doubleValue() != 0){
							//不合格数量
							vo = new CheckLotDetailVO();
							vo.setCheckLotId(checkLotDetailVO.getCheckLotId());
							vo.setJobArea(QualityCondition.UNNONCONFORMING_PRODUCT.getCode());
							vo.setJobAreaName(QualityCondition.UNNONCONFORMING_PRODUCT.getValue());
							vo.setLotNumber(checkLotDetailVO.getLotNumber());
							vo.setProductDate(checkLotDetailVO.getProductDate());
							vo.setValidDate(checkLotDetailVO.getValidDate());
							vo.setStringProductDate(DateUtils.DateToString(
									checkLotDetailVO.getProductDate(), DateUtils.FMT_DATE));
							vo.setStringValidDate(DateUtils.DateToString(
									checkLotDetailVO.getValidDate(), DateUtils.FMT_DATE));
							vo.setReceiveQuantity(checkLotDetailVO.getUnqualifiedQuantity());
							vo.setQualifiedQuantity(checkLotDetailVO.getQualifiedQuantity());
							vo.setUnqualifiedQuantity(checkLotDetailVO.getUnqualifiedQuantity());
							list.add(vo);
						}
						
					}
					
					stayInstorageDetailVO.setCheckLotDetailList(list);
				}
				
			}
		}
		
		return voList;
	}

	private StayInstorageDetailVO setVOData(StayInstorageDetailVO stayInstorageDetailVO,
			Map<Long, PurchaseOrderDetail> detailMap, Map<Long, PriceOrderDetail> priceMap, Long enterpriseId) {
		
		stayInstorageDetailVO.setRemark("");
		
		if(detailMap.containsKey(stayInstorageDetailVO.getPurchaseOrderDtlId())){
			
			PurchaseOrderDetail detail = detailMap.get(stayInstorageDetailVO.getPurchaseOrderDtlId());
			
			stayInstorageDetailVO.setUnitPrice(detail.getUnitPrice());
			stayInstorageDetailVO.setLineDiscount(detail.getLineDiscount());
			stayInstorageDetailVO.setAmount(stayInstorageDetailVO.getReceiveQuantity().multiply(stayInstorageDetailVO.getUnitPrice()).
					multiply(stayInstorageDetailVO.getLineDiscount()).divide(new BigDecimal(100)));
//			stayInstorageDetailVO.setTaxRate(detail.getTaxRate());
			
//			List<GoodsTaxRate> rateList = goodsTaxRateMapper.selectByEnterpriseIdOrRate(enterpriseId, detail.getTaxRate());
//			stayInstorageDetailVO.setTaxRateId((rateList != null&&rateList.size()>0) ? rateList.get(0).getId() : 0);
			stayInstorageDetailVO.setTaxRateId(detail.getTaxRateId());
			
			stayInstorageDetailVO.setNotaxRealPrice(detail.getNotaxRealPrice());
			stayInstorageDetailVO.setNotaxRealAmount(detail.getNotaxRealPrice().multiply(stayInstorageDetailVO.getReceiveQuantity()));
			stayInstorageDetailVO.setTaxAmount(detail.getRealPrice().multiply(stayInstorageDetailVO.getReceiveQuantity())
					.subtract(stayInstorageDetailVO.getNotaxRealAmount()));
			
		}
		
		if(priceMap.containsKey(stayInstorageDetailVO.getGoodsId())){
			PriceOrderDetail detail = priceMap.get(stayInstorageDetailVO.getGoodsId());
			
			stayInstorageDetailVO.setDistrPrice(detail.getDistrPrice());
			stayInstorageDetailVO.setRetailPrice(detail.getRetailPrice());
			stayInstorageDetailVO.setMemberPrice(detail.getMemberPrice());
		}
		
		return stayInstorageDetailVO;
	}

	@Override
	public StayInstorageOtherVO queryStayInstorageOther(Long id) {
		
		PurchaseCheck check = purchaseCheckMapper.selectByPrimaryKey(id);
		
		StayInstorageOtherVO vo = setOtherVoByCheck(check);
		
		if(check != null){
			
			PurchaseOrder order = purchaseOrderMapper.selectByPrimaryKey(check.getPurchaseOrderId());
			
			setOtherVoByOrder(order, vo);
			
			PurchaseReceive receive = purchaseReceiveMapper.selectByPrimaryKey(check.getBaseOrderId());
			
			setOtherVoByReceive(receive, vo);
			
			PurchaseOrderOther other = purchaseOrderOtherMapper.selectByOrderId(check.getPurchaseOrderId());
			
			setOtherVoByOther(other, vo);
		}
		
		return vo;
	}

	private void setOtherVoByOther(PurchaseOrderOther other,
			StayInstorageOtherVO vo) {
		
		if(other != null){
			
			vo.setContractCode(other.getContractCode());
			vo.setSettlementUnit(other.getSettlementUnit());
			vo.setSettlementUnitPhone(other.getSettlementUnitPhone());
			vo.setSettlementType(other.getSettlementType());
			if(other.getSettlementType() != null){
				vo.setSettlementTypeName(PaymentProvision.getName(other.getSettlementType()));
			}
			vo.setSettlementUnitAddress(other.getSettlementUnitAddress());
			vo.setInvoiceType(other.getInvoiceType());
			if(other.getInvoiceType() != null){
				vo.setInvoiceTypeName(InvoiceType.getName(other.getInvoiceType()));
			}
			vo.setTaxpayerCode(other.getTaxpayerCode());
			vo.setAccountName(other.getAccountName());
			vo.setAccount(other.getAccount());
			vo.setBank(other.getBank());
		}
	}

	private void setOtherVoByReceive(PurchaseReceive receive,
			StayInstorageOtherVO vo) {
		
		if(receive != null){
			
			vo.setReceiverName(receive.getReceiverName());
			
			vo.setSecondReceiverName(receive.getSecondReceiverName());
		}
		
	}

	private void setOtherVoByOrder(PurchaseOrder order,
			StayInstorageOtherVO vo) {

		if(order != null){
			
			vo.setOrderCode(order.getCode());
			
			vo.setPurchaserName(order.getPurchaserName());
		}
		
	}

	private StayInstorageOtherVO setOtherVoByCheck(PurchaseCheck check) {
		
		StayInstorageOtherVO vo = new StayInstorageOtherVO();
		
		if(check != null){
			
			vo.setCheckerName(check.getCheckerName());
			
			vo.setSecondCheckerName(check.getSecondCheckerName());
		}
		
		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveStayInstorage(StayInstorageSaveVO vo, UserVO loginUser) throws Exception {
		
		PurchaseCheck check = purchaseCheckMapper.selectByPrimaryKey(vo.getId());
		if(check == null){
			return "验收单ID错误！";
		}
		User user = userMapper.selectByPrimaryKey(vo.getStorageManId());
		/*if(user == null || !user.getEnterpriseId().equals(loginUser.getEnterpriseId())){
			return "入库人员ID错误！";
		}*/
		
		String msg = validateInstorageVo(check, vo); 
		if(StringUtils.isNotBlank(msg)){
			return msg;
		}
		
		Map<Long, PurchaseCheckDetail> checkDtlMap = new HashMap<>();//验收明细Map
		
		Map<Long, GoodsTaxRate> taxRateMap = new HashMap<Long, GoodsTaxRate>();//税率Map
		
		Map<String, BigDecimal> map = getTotalDataByVO(vo, checkDtlMap, taxRateMap);
		
		PurchaseInStorage storage = setInstorage(vo, check, user, loginUser, map);
		
		purchaseInStorageMapper.insert(storage);
		
		List<PurchaseInStorageDetail> detailList = setInStorageDetailList(vo, storage, loginUser, checkDtlMap, taxRateMap);
		
		purchaseInStorageDetailMapper.batchSave(detailList);
		
		List<PurchaseInStorageShelf> shelfList = setInStorageShelfList(detailList, storage, loginUser);
		
		purchaseInStorageShelfMapper.batchSave(shelfList);

		// 采购入库生成关键表数据
		OrderModelBuilder builder = new OrderModelBuilder(loginUser);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.PURCHASE_IN, storage, shelfList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
		
		financeComponent.purchaseToBalanceAndVoucher(loginUser, storage);
		
		this.updateUpstreamOrderStatus(check);
		/**
		 * 删除redis的草稿
		 */
		String redisKeyValue = vo.getRedisKeyValue();
		if (redisKeyValue != null && "".equals(redisKeyValue)){
			redisComponent.removeDraftCacheVO(loginUser.getEnterpriseId(), OrderRule.PURCHASE_IN.getCodePrefix(),redisKeyValue);
		}

		return null;
	}

	@Override
	public DraftCacheVO getDraftCacheVO(UserVO userVO, Long baseOrderId) {
		Class<StayInstorageSaveVO> clazz = StayInstorageSaveVO.class;
		return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),baseOrderId,OrderRule.PURCHASE_IN.getCodePrefix(),clazz);
	}

	@Override
	public DraftCacheVO<StayInstorageSaveVO> saveDraftCache(UserVO userVO, DraftCacheVO<StayInstorageSaveVO> draftCache) throws Exception{
		if (draftCache.getBaseOrderId() == null || draftCache.getBaseOrderId() == 0){
			throw new BusinessException(SysCode.FAIL.getCode(),"上游验收单ID为必填!");
		}

		String redisKeyValue = draftCache.getId();

		DraftCacheVO<StayInstorageSaveVO> draftCacheVO = new DraftCacheVO();

		draftCacheVO.setOrderCode(OrderRule.PURCHASE_IN.getCodePrefix());

		draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

		draftCacheVO.setOrderData(draftCache.getOrderData());
		draftCacheVO.setId(redisKeyValue);
		draftCacheVO.setBaseOrderId(draftCache.getBaseOrderId());
		DraftCacheVO dcv = redisComponent.saveDraftCacheVO(draftCacheVO);
		return dcv;
	}

	@Override
	public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) {

		redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
	}

	/*@Override
	public StayInstorageSaveVO getStayInstorageDraft(Long id, UserVO userVO) {
			//先查询缓存，如果存在返回缓存，
		Class<StayInstorageSaveVO> clazz = StayInstorageSaveVO.class;
		DraftCacheVO draftCacheVO = redisComponent.getDraftCacheVO(userVO.getEnterpriseId(), id, OrderRule.PURCHASE_IN.getCodePrefix(), clazz);
		if(draftCacheVO != null){
			StayInstorageSaveVO orderData = (StayInstorageSaveVO) draftCacheVO.getOrderData();
			StayInstorageSaveVO vo = new StayInstorageSaveVO();
			BeanUtils.copyProperties(orderData,vo);
			return vo;
		}
		return null;
	}*/


	private String validateInstorageVo(PurchaseCheck check,
			StayInstorageSaveVO vo) {
//		if(check.getCheckDate().getTime() > vo.getInStorageDate().getTime()){
//			return "验收时间不能大于入库时间";
//		}
		Date orderDate = check.getCheckDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if(vo.getInStorageDate().before(calendar.getTime())){
        	return"入库时间不能早于验收时间！";
        }
		return null;
	}

	private void updateUpstreamOrderStatus(PurchaseCheck check) {
		check.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
		purchaseCheckMapper.updateByPrimaryKey(check);
		//更新采购验收品种状态待开票，已清数量为数量，未清数量为0
		purchaseCheckDetailMapper.updateStatusFinishedByCheckId(PurchaseStatus.WAIT_BILL.getStatus(), check.getId());
		//更新采购验收明细状态待开票，已清数量为收货数量，未清数量为0
		purchaseCheckLotMapper.updateStatusFinishedByCheckId(PurchaseStatus.WAIT_BILL.getStatus(), check.getId());
		
		purchaseReceiveMapper.updateStatusFinishedById(PurchaseStatus.WAIT_BILL.getStatus(), check.getBaseOrderId());

		purchaseReceiveDetailMapper.updateStatusFinishedByReceiveId(PurchaseStatus.WAIT_BILL.getStatus(), check.getBaseOrderId());
		
		purchaseReceiveOtherMapper.updateStatusFinishedByReceiveId(PurchaseStatus.WAIT_BILL.getStatus(), check.getBaseOrderId());
		
		purchaseOrderMapper.updateStatusFinishedById(PurchaseStatus.WAIT_BILL.getStatus(), check.getPurchaseOrderId());

		purchaseOrderDetailMapper.updateStatusFinishedByOrderId(PurchaseStatus.WAIT_BILL.getStatus(), check.getPurchaseOrderId());
		
		purchaseOrderOtherMapper.updateStatusFinishedByOrderId(PurchaseStatus.WAIT_BILL.getStatus(), check.getPurchaseOrderId());
	}

	/**
	 * @Description: TODO入库货位细表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月15日 下午8:08:06 
	 * @param detailList
	 * @param storage
	 * @param loginUser
	 * @return 
	 * @return List<PurchaseInStorageShelf>
	 * @throws Exception 
	 */
	private List<PurchaseInStorageShelf> setInStorageShelfList(
			List<PurchaseInStorageDetail> detailList,
			PurchaseInStorage storage, UserVO loginUser) throws Exception {
		
		List<Long> checkLotIdList = new ArrayList<Long>();//验收批号明细ID集合
		
		List<Long> shelfIdList = new ArrayList<Long>();//货位ID集合
		
		for (PurchaseInStorageDetail detail : detailList) {
			
			if(detail.getCheckLotDetailVO() != null && detail.getCheckLotDetailVO().size() > 0){
				
				/*for (int i = 0; i < detail.getStayInstorageLotSaveVO().size(); i++) {
					checkLotIdList.add(detail.getStayInstorageLotSaveVO().get(i).getCheckLotId());
					shelfIdList.add(detail.getStayInstorageLotSaveVO().get(i).getShelfId());
				}*/
				
				checkLotIdList.addAll(detail.getCheckLotDetailVO().stream().map(l ->{
					return l.getCheckLotId();
				}).distinct().collect(Collectors.toList()));
				
				shelfIdList.addAll(detail.getCheckLotDetailVO().stream().map(l ->{
					return l.getShelfId();
				}).distinct().collect(Collectors.toList()));
			}
			
		}
		
		Map<Long, PurchaseCheckLot> mapLot = new HashMap<Long, PurchaseCheckLot>();
		if(checkLotIdList.size() > 0){
			List<PurchaseCheckLot> listLot = purchaseCheckLotMapper.selectByIds(checkLotIdList);
			if(listLot != null){
				for (PurchaseCheckLot purchaseCheckLot : listLot) {
					mapLot.put(purchaseCheckLot.getId(), purchaseCheckLot);
				}
			}
		}
		
		Map<Long, WarehouseShelf> mapShelf = new HashMap<Long, WarehouseShelf>();
		if(shelfIdList.size() > 0){
			List<WarehouseShelf> listShelf = warehouseShelfMapper.selectByIds(shelfIdList);
			if(listShelf != null){
				for (WarehouseShelf warehouseShelf : listShelf) {
					mapShelf.put(warehouseShelf.getId(), warehouseShelf);
				}
			}
		}
		
		List<PurchaseInStorageShelf> list = new ArrayList<PurchaseInStorageShelf>();
		List<CheckLotDetailVO> lotList = null;
		PurchaseCheckLot lot = null;
		WarehouseShelf warehouseShelf = null;
		
		for (PurchaseInStorageDetail detail : detailList) {
			if(detail.getCheckLotDetailVO() != null && detail.getCheckLotDetailVO().size() > 0){
				
				BigDecimal lineDisAll = new BigDecimal(0);//总行优惠
				PurchaseInStorageShelf shelf = null;
				lotList = detail.getCheckLotDetailVO();
				
				for (int i = 0; i < lotList.size(); i++) {
					
					shelf = new PurchaseInStorageShelf();
					
					shelf.setEnterpriseId(loginUser.getEnterpriseId());
					shelf.setParentId(loginUser.getParentId());
					shelf.setInStorageDtlId(detail.getId());
					shelf.setInStorageId(detail.getInStorageId());
					shelf.setGoodsId(detail.getGoodsId());
					shelf.setGoodsCode(detail.getGoodsCode());
					shelf.setGoodsName(detail.getGoodsName());
					
//					PurchaseCheckLot lot = purchaseCheckLotMapper.selectByPrimaryKey(lotList.get(i).getCheckLotId());
					if(mapLot.containsKey(lotList.get(i).getCheckLotId())){
						lot = mapLot.get(lotList.get(i).getCheckLotId());
						shelf.setLotNumber(lot.getLotNumber());
						shelf.setProductDate(lot.getProductDate());
						shelf.setValidDate(lot.getValidDate());
						shelf.setShelfId(lotList.get(i).getShelfId());
						if(mapShelf.containsKey(lotList.get(i).getShelfId())){
							warehouseShelf = mapShelf.get(lotList.get(i).getShelfId());
							shelf.setShelfName(warehouseShelf.getName());
						/*}else if(lotList.get(i).getShelfId() != null && lotList.get(i).getShelfId() != 0){
							throw new Exception("货位ID："+lotList.get(i).getShelfId()+"查不到数据！");
						}*/
						} else {
							throw new Exception("货位ID："+lotList.get(i).getShelfId()+"查不到数据！");
						}
						if(lotList.get(i).getJobArea() == 0){
							//合格数量
							shelf.setQuantity(lot.getQualifiedQuantity());
						}else{
							//不合格数量
							shelf.setQuantity(lot.getUnqualifiedQuantity());
						}
						shelf.setCanReturnQuantity(shelf.getQuantity());
					}else if(lotList.get(i).getCheckLotId() != null && lotList.get(i).getCheckLotId() != 0){
						throw new Exception("验收批号明细ID："+lotList.get(i).getCheckLotId()+"查不到数据！");
					}
					
					shelf.setUnitPrice(detail.getUnitPrice());
					shelf.setLineDiscount(detail.getLineDiscount());
					shelf.setAmount(shelf.getUnitPrice()
							.multiply(shelf.getQuantity().multiply(shelf.getLineDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))));
					
					shelf.setWholeDiscount(detail.getWholeDiscount());
					
					//行优惠 = 行金额除以总金额乘以整单优惠金额
					if(i != lotList.size() - 1){
						BigDecimal lineDis = shelf.getAmount().compareTo(BigDecimal.ZERO)!=0?shelf.getAmount().divide(detail.getAmount(), 4, BigDecimal.ROUND_HALF_UP)
								.multiply(detail.getLineDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_UP):BigDecimal.ZERO;//行优惠（整单优惠分摊到行的金额）
						lineDisAll = lineDisAll.add(lineDis);
						shelf.setLineDiscountAmount(lineDis);
					}else{
						//最后一条减，保证小数除不尽问题
						shelf.setLineDiscountAmount(detail.getLineDiscountAmount().subtract(lineDisAll));
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
					shelf.setLineNum(i+1);
					shelf.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
					
					shelf.setCreaterId(loginUser.getUserId());
					shelf.setCreaterCode(loginUser.getUserCode());
					shelf.setCreaterName(loginUser.getUserName());
					shelf.setCreateTime(new Date());
					shelf.setModifierId(loginUser.getUserId());
					shelf.setModifierCode(loginUser.getUserCode());
					shelf.setModifierName(loginUser.getUserName());
					shelf.setUpdateTime(new Date());
					
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
					
					list.add(shelf);
				}
			}
			
		}
		
		return list;
	}

	/**
	 * @Description: 入库细表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月15日 下午1:33:33 
	 * @param vo
	 * @return 
	 * @return List<PurchaseInStorageDetail>
	 * @throws Exception 
	 */
	private List<PurchaseInStorageDetail> setInStorageDetailList(StayInstorageSaveVO vo, 
			PurchaseInStorage storage, UserVO loginUser, Map<Long, PurchaseCheckDetail> checkDtlMap,
			Map<Long, GoodsTaxRate> taxRateMap) throws Exception {
		
		List<StayInstorageDetailSaveVO> detailList = vo.getStayInstorageDetailSaveVO();
		
		List<PurchaseInStorageDetail> list = new ArrayList<PurchaseInStorageDetail>();
		
		if(detailList != null){
			
			PurchaseInStorageDetail dtl = null;
			
			GoodsTaxRate rate = null;
			
			BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
			
			BigDecimal lineDisAll = new BigDecimal(0);//总行优惠
			
			for (int i = 0; i < detailList.size(); i++) {
				
				dtl = new PurchaseInStorageDetail();
				
				dtl.setEnterpriseId(loginUser.getEnterpriseId());
				dtl.setParentId(loginUser.getParentId());
				dtl.setInStorageId(storage.getId());
				dtl.setOrderType(OrderRule.PURCHASE_IN.getType());
				dtl.setInStorageCode(storage.getCode());
				dtl.setInStorageDate(vo.getInStorageDate());
				
				dtl.setBaseOrderDtlId(detailList.get(i).getCheckDtlId());
				dtl.setBaseOrderId(storage.getBaseOrderId());
				dtl.setBaseOrderType(storage.getBaseOrderType());
				dtl.setBaseOrderCode(storage.getBaseOrderCode());
				dtl.setBaseOrderDate(storage.getBaseOrderDate());
				
				PurchaseCheckDetail checkDetail = checkDtlMap.get(detailList.get(i).getCheckDtlId()) 
						!= null ? checkDtlMap.get(detailList.get(i).getCheckDtlId()) : purchaseCheckDetailMapper.selectByPrimaryKey(detailList.get(i).getCheckDtlId());
				if(checkDetail == null){
					throw new Exception("验收明细ID："+detailList.get(i).getCheckDtlId()+"查不到数据！");
				}
				dtl.setPurchaseOrderId(checkDetail.getPurchaseOrderId());
				dtl.setPurchaseOrderDtlId(checkDetail.getPurchaseOrderDtlId());
				
				dtl.setGoodsId(checkDetail.getGoodsId());
				dtl.setGoodsCode(checkDetail.getGoodsCode());
				dtl.setBarcode(checkDetail.getBarcode());
				dtl.setGoodsName(checkDetail.getGoodsName());
				dtl.setGoodsGenericName(checkDetail.getGoodsGenericName());
				dtl.setDosageId(checkDetail.getDosageId());
				dtl.setDosageName(checkDetail.getDosageName());
				
				dtl.setUnitId(checkDetail.getUnitId());
				dtl.setUnitName(checkDetail.getUnitName());
				dtl.setGoodsSpecification(checkDetail.getGoodsSpecification());
				dtl.setManufacturerId(checkDetail.getManufacturerId());
				dtl.setManufacturer(checkDetail.getManufacturer());
				dtl.setGoodsPlace(checkDetail.getGoodsPlace());
				dtl.setApprovalNumber(checkDetail.getApprovalNumber());
				
				dtl.setQuantity(checkDetail.getQuantity());
				dtl.setUnitPrice(detailList.get(i).getUnitPrice());
				dtl.setLineDiscount(detailList.get(i).getLineDiscount());
				dtl.setAmount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
						checkDetail.getQuantity(), detailList.get(i).getUnitPrice(), detailList.get(i).getLineDiscount()));
				dtl.setWholeDiscount(vo.getWholeDiscount());
				//行优惠 = 行金额除以总金额乘以整单优惠金额
				if(i != detailList.size() - 1){
					BigDecimal lineDis = dtl.getAmount().divide(storage.getAmountTotal(), 4, BigDecimal.ROUND_HALF_UP)
							.multiply(vo.getWholeDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);//行优惠（整单优惠分摊到行的金额）
					lineDisAll = lineDisAll.add(lineDis);
					dtl.setLineDiscountAmount(lineDis);
				}else{
					//最后一条减，保证小数除不尽问题
					dtl.setLineDiscountAmount(vo.getWholeDiscountAmount().subtract(lineDisAll));
				}
				//实际金额 = 单行金额乘以整单折扣减去行优惠
//				dtl.setRealAmount(dtl.getAmount().multiply(vo.getWholeDiscount().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP))
//						.subtract(dtl.getLineDiscountAmount()));
				dtl.setRealAmount(CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(dtl.getQuantity(), 
						dtl.getUnitPrice(), dtl.getLineDiscount(), dtl.getWholeDiscount(), dtl.getLineDiscountAmount()));
				
				//实际单价（实际金额/数量）
//				dtl.setRealPrice(dtl.getRealAmount().divide(dtl.getQuantity(), 6, BigDecimal.ROUND_HALF_UP));
				dtl.setRealPrice(CalculateComponent.
						getRealPriceByRealAmountAndQuantity(dtl.getRealAmount(), dtl.getQuantity()));
				
				if(taxRateMap.containsKey(detailList.get(i).getTaxRateId())){
					
					rate = taxRateMap.get(detailList.get(i).getTaxRateId());
				}else{
					
					rate = goodsTaxRateMapper.selectByPrimaryKey(detailList.get(i).getTaxRateId());
				}
				if(rate == null){
					
					throw new Exception("税率ID："+detailList.get(i).getTaxRateId()+"查不到数据！");
				}
				dtl.setTaxRateId(rate.getId());
				dtl.setTaxRate(rate.getTaxRate());
				dtl.setNotaxRealAmount(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(
						dtl.getRealAmount(), rate.getTaxRate()));//不含税实际金额
//				dtl.setNotaxRealPrice(detailList.get(i).getNotaxRealPrice());
				dtl.setNotaxRealPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(dtl.getNotaxRealAmount(), dtl.getQuantity()));//不含税实际单价
				dtl.setTaxAmount(CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(dtl.getRealAmount(), dtl.getNotaxRealAmount()));
				
				dtl.setUnclearQuantity(dtl.getQuantity());
				dtl.setClearQuantity(new BigDecimal(0));
				dtl.setLineNum(detailList.get(i).getLineNum());
				dtl.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
				dtl.setRemark(detailList.get(i).getRemark());
				
				dtl.setCreaterId(loginUser.getUserId());
				dtl.setCreaterCode(loginUser.getUserCode());
				dtl.setCreaterName(loginUser.getUserName());
				dtl.setCreateTime(new Date());
				dtl.setModifierId(loginUser.getUserId());
				dtl.setModifierCode(loginUser.getUserCode());
				dtl.setModifierName(loginUser.getUserName());
				dtl.setUpdateTime(new Date());
				
				commonComponent.updateSupplierVarieties(dtl, storage.getSupplierId());
				
				dtl.setCheckLotDetailVO(detailList.get(i).getCheckLotDetailList());
				
				list.add(dtl);
				
				notaxRealAmountTotal = notaxRealAmountTotal.add(dtl.getNotaxRealAmount());
			}
			
			//设置不含税金额合计
			storage.setNotaxRealAmountTotal(notaxRealAmountTotal);
			
			storage.setTaxAmountTotal(storage.getRealAmountTotal().subtract(storage.getNotaxRealAmountTotal()));//产品设计规则
			
			purchaseInStorageMapper.updateByPrimaryKeySelective(storage);
		}
		
		return list;
	}



	/**
	 * @Description: 入库主表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月15日 下午1:33:17 
	 * @param vo
	 * @param check
	 * @param user
	 * @param loginUser
	 * @return
	 * @throws Exception 
	 * @return PurchaseInStorage
	 */
	private PurchaseInStorage setInstorage(StayInstorageSaveVO vo, PurchaseCheck check, User user,
			UserVO loginUser, Map<String, BigDecimal> map) throws Exception {
		PurchaseInStorage storage = new PurchaseInStorage();

		storage.setIsUse(0);

		storage.setEnterpriseId(loginUser.getEnterpriseId());
		
		storage.setParentId(loginUser.getParentId());
		
		storage.setOrderType(OrderRule.PURCHASE_IN.getType());
		
		storage.setCode(orderCodeComponent.generate(OrderRule.PURCHASE_IN.getCodePrefix(), 
				loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
		
		storage.setInStorageDate(vo.getInStorageDate());
		
		storage.setBaseOrderId(check.getId());
		
		storage.setBaseOrderType(check.getOrderType());
		
		storage.setBaseOrderCode(check.getCode());
		
		storage.setBaseOrderDate(check.getCheckDate());
		
		storage.setPurchaseOrderId(check.getPurchaseOrderId());
		
		storage.setSupplierId(check.getSupplierId());
		
		storage.setSupplierCode(check.getSupplierCode());
		
		storage.setSupplierName(check.getSupplierName());
		
		storage.setSupplierSalerId(check.getSupplierSalerId());
		
		storage.setSupplierSalerCode(check.getSupplierSalerCode());
		
		storage.setSupplierSalerName(check.getSupplierSalerName());
		
		storage.setSupplierSalerPhone(check.getSupplierSalerPhone());
		
		storage.setStorageManId(vo.getStorageManId());
		
		storage.setStorageManCode(user.getCode());
		
		storage.setStorageManName(user.getName());
		
		storage.setFlowCode(vo.getFlowCode());
		
		storage.setQuantityTotal(map.get("quantityTotal")==null?BigDecimal.ZERO:map.get("quantityTotal"));
		
		//品种
		Long varietiesQuantity = 0L;
		if(vo.getStayInstorageDetailSaveVO() != null){
			varietiesQuantity = vo.getStayInstorageDetailSaveVO().stream().map(dtl ->{
				return dtl.getGoodsId();
			}).distinct().count();
		}
		storage.setVarietiesQuantity(varietiesQuantity.intValue());
		
		storage.setAmountTotal(map.get("amountTotal")==null?BigDecimal.ZERO:map.get("amountTotal"));
		
		storage.setWholeDiscount(vo.getWholeDiscount());
		
		storage.setWholeDiscountAmount(vo.getWholeDiscountAmount());
		
		storage.setRealAmountTotal(map.get("realAmountTotal")==null?BigDecimal.ZERO:map.get("realAmountTotal"));
		
		storage.setNotaxRealAmountTotal(BigDecimal.ZERO);
		
		storage.setTaxAmountTotal(BigDecimal.ZERO);//后面赋值
		
		storage.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
		
		storage.setCreaterId(loginUser.getUserId());
		
		storage.setCreaterCode(loginUser.getUserCode());
		
		storage.setCreaterName(loginUser.getUserName());
		
		storage.setCreateTime(new Date());
		
		storage.setModifierId(loginUser.getUserId());
		
		storage.setModifierCode(loginUser.getUserCode());
		
		storage.setModifierName(loginUser.getUserName());
		
		storage.setUpdateTime(new Date());

		storage.setIsUse(0);//是否被引用(配货单) 0-没有，1被调用
		return storage;
	}
	
	/**
	 * @Description: 获取总单合计相关数据
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月18日 上午11:26:28 
	 * @param vo
	 * @return 
	 * @return Map<String,BigDecimal>
	 * @throws Exception 
	 */
	private Map<String, BigDecimal> getTotalDataByVO(StayInstorageSaveVO vo, Map<Long, 
			PurchaseCheckDetail> checkDtlMap, Map<Long, GoodsTaxRate> taxRateMap) throws Exception {
		
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		
		BigDecimal quantityTotal = BigDecimal.ZERO; //数量合计
		
		BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
		
		BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
		
//		BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
		
		List<StayInstorageDetailSaveVO> detailList = vo.getStayInstorageDetailSaveVO();
		
		if(detailList != null){
			
			GoodsTaxRate rate = null;
			
			for (int i = 0; i < detailList.size(); i++) {
				
				PurchaseCheckDetail checkDetail = purchaseCheckDetailMapper.selectByPrimaryKey(detailList.get(i).getCheckDtlId());
				
				if(checkDetail == null){
					throw new Exception("验收明细ID："+detailList.get(i).getCheckDtlId()+"查不到数据！");
				}
				
				checkDtlMap.put(detailList.get(i).getCheckDtlId(), checkDetail);
				
				quantityTotal = quantityTotal.add(checkDetail.getQuantity());
				
				//行金额
				BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
						checkDetail.getQuantity(), detailList.get(i).getUnitPrice(), detailList.get(i).getLineDiscount());
				
				amountTotal = amountTotal.add(amount);
				
				if(taxRateMap.containsKey(detailList.get(i).getTaxRateId())){
					
					rate = taxRateMap.get(detailList.get(i).getTaxRateId());
				}else{
					
					rate = goodsTaxRateMapper.selectByPrimaryKey(detailList.get(i).getTaxRateId());
				}
				if(rate == null){
					
					throw new Exception("税率ID："+detailList.get(i).getTaxRateId()+"查不到数据！");
				}
				taxRateMap.put(detailList.get(i).getTaxRateId(), rate);
				
				//行不含税金额
//				notaxRealAmountTotal = notaxRealAmountTotal.add(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate
//						(amount, rate.getTaxRate()));
				
			}
			
			realAmountTotal = amountTotal.multiply(vo.getWholeDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)).subtract(vo.getWholeDiscountAmount());
		
		}
		map.put("quantityTotal", quantityTotal);
		
		map.put("amountTotal", amountTotal);
		
		map.put("realAmountTotal", realAmountTotal);
		
//		map.put("notaxRealAmountTotal", notaxRealAmountTotal);
		
		return map;
	}


}
