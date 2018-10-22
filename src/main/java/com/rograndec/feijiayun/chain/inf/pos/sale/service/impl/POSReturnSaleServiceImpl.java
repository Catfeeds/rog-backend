package com.rograndec.feijiayun.chain.inf.pos.sale.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.integral.dao.IntegralRecordMapper;
import com.rograndec.feijiayun.chain.business.member.integral.entity.IntegralRecord;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.storedamount.dao.StoredAmountMapper;
import com.rograndec.feijiayun.chain.business.member.storedamount.entity.StoredAmount;
import com.rograndec.feijiayun.chain.business.retail.prescription.dao.PrescriptionRegisterDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.prescription.dao.PrescriptionRegisterMapper;
import com.rograndec.feijiayun.chain.business.retail.prescription.dao.PrescriptionRegisterShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.business.retail.pricing.dao.SalePricingMapper;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptMapper;
import com.rograndec.feijiayun.chain.business.retail.receipt.entity.Receipt;
import com.rograndec.feijiayun.chain.business.retail.receipt.entity.ReceiptDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterMapper;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegister;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.InOutComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.OrderModelBuilder;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.PriceType;
import com.rograndec.feijiayun.chain.common.constant.SaleGenre;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.SimpleProfitVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.DailySettlementFlag;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.ExcessiveSale;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.PaymentFlag;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.ShiftFlag;
import com.rograndec.feijiayun.chain.inf.pos.sale.service.POSReturnSaleService;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReceiptDetailVo;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReceiptVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReturnSaleDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReturnSaleVO;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

@Service
public class POSReturnSaleServiceImpl implements POSReturnSaleService{

	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private SaleDetailMapper saleDetailMapper;
	
	@Autowired
	private SaleShelfMapper saleShelfMapper;
	
	@Autowired
	private PrescriptionRegisterMapper prescriptionRegisterMapper;
	
	@Autowired
	private PrescriptionRegisterDetailMapper prescriptionRegisterDetailMapper;
	
	@Autowired
	private PrescriptionRegisterShelfMapper prescriptionRegisterShelfMapper;
	
	@Autowired
	private SpecialRegisterMapper specialRegisterMapper;
	
	@Autowired
	private SpecialRegisterDetailMapper specialRegisterDetailMapper;
	
	@Autowired
	private SpecialRegisterShelfMapper specialRegisterShelfMapper;
	
	@Autowired
	private SalePricingMapper salePricingMapper;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private IntegralRecordMapper integralRecordMapper;
	
	@Autowired
	private StoredAmountMapper storedAmountMapper;
	
	@Autowired
	private MemberCardTypeMapper memberCardTypeMapper;
	
	@Autowired
	private PriceOrderMapper priceOrderMapper;
	
	@Autowired
	private PriceOrderDetailMapper priceOrderDetailMapper;
	
	@Autowired
	private LotNumberMapper lotNumberMapper;
	
	@Autowired
	private WarehouseShelfMapper warehouseShelfMapper;
	
	@Autowired
	private ReceiptMapper receiptMapper;
	
	@Autowired
	private ReceiptDetailMapper receiptDetailMapper;
	
	@Autowired
	private OrderCodeComponent orderCodeComponent;
	
	@Autowired
	private InOutComponent inOutComponent;
	
	@Autowired
	private CommonComponent commonComponent;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveReturnSaleData(List<POSReturnSaleVO> returnSaleVOList,
			UserVO userVO) throws Exception {
		
		String msg = validateParam(returnSaleVOList);
		
		if(StringUtils.isNotBlank(msg)){
			return msg;
		}
		
		for (POSReturnSaleVO pOSReturnSaleVO : returnSaleVOList) {
			
			saveSaleAssociatedDocuments(pOSReturnSaleVO, userVO);
			
		}
		
		return null;
	}
	
	
	private Sale saveSaleAssociatedDocuments(POSReturnSaleVO pOSReturnSaleVO,
			UserVO userVO) throws Exception {

		Map<String, BigDecimal> map = getTotalDataByVO(pOSReturnSaleVO);
		
		Sale sale = setSaleData(pOSReturnSaleVO, userVO, map);
		
		saleMapper.insertSelective(sale);
		
		PrescriptionRegister re = new PrescriptionRegister(); //新增处方单
		SpecialRegister sr = new SpecialRegister();//专管登记单
		
		List<SaleShelf> shelfList = new ArrayList<SaleShelf>();
		
		BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
		BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税实际金额合计
		BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
		BigDecimal profitTotal = BigDecimal.ZERO;//利润合计
		BigDecimal notaxProfitTotal = BigDecimal.ZERO;//不含税利润合计
		
		Map<String, BigDecimal> lineDisAllMap = new HashMap<String, BigDecimal>();
		
		for (int i = 0; i < pOSReturnSaleVO.getReturnSaleDetailVOList().size(); i++) {
			
			POSReturnSaleDetailVO detailVO = pOSReturnSaleVO.getReturnSaleDetailVOList().get(i);
			
			Goods goods = goodsMapper.selectByPrimaryKey(detailVO.getGoodsId());
			if(goods == null){
				throw new Exception("商品ID"+detailVO.getGoodsId()+"错误;");
			}
			
//			PriceOrderDetail priceDetail = priceOrderDetailMapper.selectByGoodsIdAndEnterpriseId(detailVO.getGoodsId(), sale.getEnterpriseId());
			
			PriceOrder priceOrder = priceOrderMapper.selectByEnterpriseIdAndSysTypeAndPriceType(sale.getEnterpriseId(), SysType.SYSTEM.getCode(), PriceType.RETAIL_PRICE.getCode());
			
			if(priceOrder == null){
				throw new Exception("该企业"+sale.getEnterpriseId()+"无价格清单数据;");
			}
			
			PriceOrderDetail priceDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(sale.getEnterpriseId(), priceOrder.getId(), detailVO.getGoodsId());
			
			if(priceDetail == null){
				throw new Exception("该企业"+sale.getEnterpriseId()+"当前商品"+detailVO.getGoodsId()+"无价格清单数据;");
			}
			
			SaleDetail saleDetail = setSaleDetail(detailVO, sale, goods, i, 
					pOSReturnSaleVO.getReturnSaleDetailVOList().size(), lineDisAllMap, priceDetail, pOSReturnSaleVO.getIsTicket());
			
			saleDetailMapper.insertSelective(saleDetail);
			
			SaleShelf saleShelf = setSaleShelf(detailVO, saleDetail);
			
			saleShelfMapper.insertSelective(saleShelf);
			
			shelfList.add(saleShelf);
			
			realAmountTotal = realAmountTotal.add(saleDetail.getRealAmount());//实际金额合计
			notaxRealAmountTotal = notaxRealAmountTotal.add(saleDetail.getNotaxRealAmount());//不含税实际金额合计
			taxAmountTotal = taxAmountTotal.add(saleDetail.getTaxAmount());//税额合计
			profitTotal = profitTotal.add(saleDetail.getProfit());//利润合计
			notaxProfitTotal = notaxProfitTotal.add(saleDetail.getNotaxProfit());//不含税利润合计
			
			if(pOSReturnSaleVO.getIsTicket() == 1){
				updateOldSaleData(detailVO, saleDetail, saleShelf);//修改原销售单明细数据
			}	
		}
		//修改主数据总金额
		updateSaleMainData(sale, re, sr, pOSReturnSaleVO, realAmountTotal, notaxRealAmountTotal, taxAmountTotal, profitTotal, notaxProfitTotal);
		
		MemberInfo member = memberInfoMapper.selectByPrimaryKey(pOSReturnSaleVO.getMemberId());
		if(member != null){
			//会员相关操作
			this.handingMemberInfo(member, pOSReturnSaleVO, userVO, sale);
		}
			
		insertReceipt(sale, pOSReturnSaleVO.getReceipt());//保存收款单

		BigDecimal excessiveSale = map.get("excessiveSale")==null?BigDecimal.ZERO:map.get("excessiveSale");
		
		if(excessiveSale.compareTo(BigDecimal.ZERO) == 0){
			
			OrderModelBuilder builder = new OrderModelBuilder(userVO);
			
			OrderModel orderModel = builder.buildOrderModel(OrderRule.SALES_RETURN_IN, sale, shelfList);
			
			inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
		
		}
		return null;
	}


	private void updateOldSaleData(POSReturnSaleDetailVO detailVO,
			SaleDetail saleDetail, SaleShelf saleShelf) throws Exception {
		
		SaleShelf oldSaleShelf = null;
		
		if(detailVO.getBaseOrderShelfId()!=null&&detailVO.getBaseOrderShelfId()!=0){
			
			oldSaleShelf = saleShelfMapper.selectByPrimaryKey(detailVO.getBaseOrderShelfId());
			if(oldSaleShelf == null){
				throw new Exception("销售单明细货位ID:"+detailVO.getBaseOrderShelfId()+"错误;");
			}
		}else {
			
			List<SaleShelf> oldSaleShelfs = saleShelfMapper.queryByGidAndLotIdAndShelfIdAndLineNum(
					saleDetail.getBaseOrderCode(), detailVO.getGoodsId(),
					detailVO.getLotId(), detailVO.getShelfId(), detailVO.getOldLineNum(), saleDetail.getEnterpriseId());
			if(oldSaleShelfs == null || oldSaleShelfs.size() == 0){
				throw new Exception("查不到原销售明细单据Gid:"+detailVO.getGoodsId()+"LotId:"+
						detailVO.getLotId()+"ShelfId:"+detailVO.getShelfId()+"OldLineNum:"+detailVO.getOldLineNum()+
						"EnterId"+saleDetail.getEnterpriseId()+"错误;");
			}
			oldSaleShelf = oldSaleShelfs.get(0);
		}
		
		if(oldSaleShelf == null || oldSaleShelf.getCanReturnQuantity().compareTo(saleShelf.getQuantity())<0){
			throw new Exception("当前销售单明细货位为空，或当前销售单明细货位:"+oldSaleShelf.getId()+"可退数量:"+
					oldSaleShelf.getCanReturnQuantity()+"小于要退数量:"+saleShelf.getQuantity());
		}
		
		SaleDetail oldSaleDetail = saleDetailMapper.selectByPrimaryKey(oldSaleShelf.getSaleDtlId());
		if(oldSaleDetail != null){
			oldSaleDetail.setCanReturnQuantity(oldSaleDetail.getCanReturnQuantity().subtract(saleDetail.getQuantity()));
			saleDetail.setBaseOrderDtlId(oldSaleDetail.getId());
			/*saleDetail.setBaseOrderId(oldSaleDetail.getBaseOrderId());
			saleDetail.setBaseOrderCode(oldSaleDetail.getBaseOrderCode());
			saleDetail.setBaseOrderDate(oldSaleDetail.getBaseOrderDate());
			saleDetail.setBaseOrderType(oldSaleDetail.getBaseOrderType());*/
			saleDetailMapper.updateByPrimaryKeySelective(oldSaleDetail);
		}
		
		Sale sale = saleMapper.selectByPrimaryKey(oldSaleDetail.getSaleId());
		if(sale != null){
			sale.setCanReturnQuantityTotal(sale.getCanReturnQuantityTotal().subtract(saleDetail.getQuantity()));
			saleMapper.updateByPrimaryKeySelective(sale);
		}
		
		oldSaleShelf.setCanReturnQuantity(oldSaleShelf.getCanReturnQuantity().subtract(saleShelf.getQuantity()));
		saleDetailMapper.updateByPrimaryKeySelective(saleDetail);
		saleShelfMapper.updateByPrimaryKeySelective(oldSaleShelf);
	}


	private void updateSaleMainData(Sale sale, PrescriptionRegister re, SpecialRegister sr,
			POSReturnSaleVO pOSReturnSaleVO, BigDecimal realAmountTotal, BigDecimal notaxRealAmountTotal,
			BigDecimal taxAmountTotal, BigDecimal profitTotal,
			BigDecimal notaxProfitTotal) {
		
		sale.setRealAmountTotal(realAmountTotal);//实际金额合计
		sale.setNotaxRealAmountTotal(notaxRealAmountTotal);//不含税金额合计
		sale.setTaxAmountTotal(taxAmountTotal);//税额合计
		sale.setProfitTotal(profitTotal);//利润合计
		sale.setNotaxProfitTotal(notaxProfitTotal);//不含税利润合计
		
		BigDecimal profitRate = BigDecimal.ZERO;
		if(realAmountTotal.compareTo(BigDecimal.ZERO) != 0){
			profitRate = profitTotal.divide(realAmountTotal, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));// 利润率
		}
		sale.setProfitRate(profitRate);//利润率,需赋值
		
		BigDecimal notaxProfitRate = BigDecimal.ZERO;
		if(notaxRealAmountTotal.compareTo(BigDecimal.ZERO) != 0){
			notaxProfitRate = notaxProfitTotal.divide(notaxRealAmountTotal, 4, RoundingMode.HALF_UP);// 不含税利润率
		}
		sale.setNotaxProfitRate(notaxProfitRate);//不含税利润率,需赋值
		saleMapper.updateByPrimaryKeySelective(sale);
		
	}

	
	private SaleShelf setSaleShelf(POSReturnSaleDetailVO detailVO,
			SaleDetail saleDetail) throws Exception {
		SaleShelf shelf = new SaleShelf();
		shelf.setEnterpriseId(saleDetail.getEnterpriseId());
		shelf.setParentId(saleDetail.getParentId());
		shelf.setSaleDtlId(saleDetail.getId());
		shelf.setSaleId(saleDetail.getSaleId());
		shelf.setGoodsId(saleDetail.getGoodsId());
		shelf.setGoodsCode(saleDetail.getGoodsCode());
		shelf.setGoodsName(saleDetail.getGoodsName());
		shelf.setLotId(detailVO.getLotId());
		LotNumber lot = lotNumberMapper.selectByPrimaryKey(detailVO.getLotId());
		if(lot == null){
			throw new Exception("批号ID错误");
		}
		shelf.setLotNumber(lot.getLotNum());
		shelf.setProductDate(lot.getProductDate());
		shelf.setValidDate(lot.getValidUntil());
		WarehouseShelf sh = warehouseShelfMapper.selectByPrimaryKey(detailVO.getShelfId());
		if(sh == null){
			throw new Exception("货位ID错误");
		}
		shelf.setShelfId(detailVO.getShelfId());
		shelf.setShelfName(sh.getName());
		shelf.setQuantity(saleDetail.getQuantity());
		shelf.setCanReturnQuantity(BigDecimal.ZERO);
		shelf.setUnitPrice(saleDetail.getUnitPrice());
		shelf.setLineDiscount(saleDetail.getLineDiscount());
		shelf.setAmount(saleDetail.getAmount());
		shelf.setWholeDiscount(saleDetail.getWholeDiscount());
		shelf.setLineDiscountAmount(saleDetail.getLineDiscountAmount());
		shelf.setRealPrice(saleDetail.getRealPrice());
		shelf.setRealAmount(saleDetail.getRealAmount());
		shelf.setTaxRateId(saleDetail.getTaxId());
		shelf.setTaxRate(saleDetail.getTaxRate());
		shelf.setNotaxRealPrice(saleDetail.getNotaxRealPrice());
		shelf.setNotaxRealAmount(saleDetail.getNotaxRealAmount());
		shelf.setTaxAmount(saleDetail.getTaxAmount());
		shelf.setProfit(saleDetail.getProfit());
		shelf.setNotaxProfit(saleDetail.getNotaxProfit());
		shelf.setProfitRate(saleDetail.getProfitRate());
		shelf.setNotaxProfitRate(saleDetail.getNotaxProfitRate());
		shelf.setThisIntegral(saleDetail.getThisIntegral());
		shelf.setUnclearQuantity(saleDetail.getUnclearQuantity());
		shelf.setClearQuantity(saleDetail.getClearQuantity());
		/**
		 * 插入核销数量和未核销数量
		 */
		shelf.setVerificationQuantity(saleDetail.getClearQuantity());
		shelf.setUnverificationQuantity(saleDetail.getUnclearQuantity());
		shelf.setLineNum(detailVO.getLineNum());
		shelf.setStatus(saleDetail.getStatus());
		shelf.setRemark(saleDetail.getRemark());
		shelf.setCreaterId(saleDetail.getCreaterId());
		shelf.setCreaterCode(saleDetail.getCreaterCode());
		shelf.setCreaterName(saleDetail.getCreaterName());
		shelf.setCreateTime(new Date());
		shelf.setModifierId(saleDetail.getCreaterId());
		shelf.setModifierCode(saleDetail.getCreaterCode());
		shelf.setModifierName(saleDetail.getCreaterName());
		shelf.setUpdateTime(new Date());
		return shelf;
	}
	

	private SaleDetail setSaleDetail(POSReturnSaleDetailVO detailVO, Sale sale,
			Goods goods, int i, int size, Map<String, BigDecimal> lineDisAllMap, PriceOrderDetail priceDetail, int isTicket) throws Exception {
		
		SaleDetail detail = new SaleDetail();
		
		detail.setEnterpriseId(sale.getEnterpriseId());
		detail.setParentId(sale.getParentId());
		detail.setSaleId(sale.getId());
		detail.setOrderType(sale.getOrderType());
		detail.setSaleCode(sale.getCode());
		detail.setSaleDate(sale.getSaleDate());
		detail.setBaseOrderId(sale.getBaseOrderId());
		detail.setBaseOrderType(sale.getBaseOrderType());
		detail.setBaseOrderCode(sale.getBaseOrderCode());
		detail.setBaseOrderDate(sale.getBaseOrderDate());
		
		if(null != detailVO.getClerkId() && detailVO.getClerkId() != 0) {
			User clerker = userMapper.getUserByIdAndEnterpriseId(detailVO.getClerkId(), sale.getEnterpriseId());
			if(clerker == null) {
				throw new BusinessException("营业人员ID不存在！");
			}
			detail.setClerkId(detailVO.getClerkId());
			detail.setClerkCode(clerker.getCode());
			detail.setClerkName(clerker.getName());
		}
		
		detail.setGoodsId(goods.getId());
		detail.setGoodsCode(goods.getCode());
		detail.setBarcode(goods.getBarcode());
		detail.setGoodsName(goods.getName());
		detail.setGoodsGenericName(goods.getGenericName());
		detail.setDosageId(goods.getDosageId());
		detail.setDosageName(goods.getDosageName());
		detail.setUnitId(goods.getUnitId());
		detail.setUnitName(goods.getUnitName());
		detail.setGoodsSpecification(goods.getSpecification());
		detail.setManufacturerId(goods.getManufacturerId());
		detail.setManufacturer(goods.getManufacturer());
		detail.setGoodsPlace(goods.getPlace());
		detail.setApprovalNumber(goods.getApprovalNumber());
		detail.setSingleDose(detailVO.getSingleDose());
		detail.setQuantity(detailVO.getQuantity());
		detail.setCanReturnQuantity(BigDecimal.ZERO);
		detail.setUnitPrice(detailVO.getUnitPrice());
		detail.setLineDiscount(detailVO.getLineDiscount());
		detail.setAmount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(detail.getQuantity(), detail.getUnitPrice(), detail.getLineDiscount()));
		detail.setWholeDiscount(sale.getWholeDiscount());
		
		BigDecimal lineDisAll = lineDisAllMap.get("lineDisAll")==null?BigDecimal.ZERO:new BigDecimal(lineDisAllMap.get("lineDisAll").toString());
		
		if(i != size - 1){
			BigDecimal lineDis = BigDecimal.ZERO;
			
			if(sale.getWholeDiscountAmount().compareTo(BigDecimal.ZERO) != 0){
				lineDis = CalculateComponent.getLineRoundOffByLineAmountAndWholeAmountTotal(sale.getWholeDiscountAmount(),
						detail.getAmount(), sale.getAmountTotal());//行优惠（整单优惠分摊到行的金额）
			}
			lineDisAll = lineDisAll.add(lineDis);
			
			detail.setLineDiscountAmount(lineDis);
		}else{
			//最后一条减，保证小数除不尽问题
			detail.setLineDiscountAmount(sale.getWholeDiscountAmount().subtract(lineDisAll));
		}
		
		lineDisAllMap.put("lineDisAll", lineDisAll);
		
		detail.setRealAmount(CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(detail.getQuantity(), 
				detail.getUnitPrice(), detail.getLineDiscount(), detail.getWholeDiscount(), detail.getLineDiscountAmount()));
		detail.setRealPrice(CalculateComponent.
						getRealPriceByRealAmountAndQuantity(detail.getRealAmount(), detail.getQuantity()));
		
		detail.setTaxId(priceDetail == null ? 0 : priceDetail.getSaleTaxRateId());
		detail.setTaxRate(priceDetail == null ? BigDecimal.ZERO : priceDetail.getSaleTaxRate());
		detail.setNotaxRealAmount(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(
						detail.getRealAmount(), detail.getTaxRate()));
		detail.setNotaxRealPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(detail.getNotaxRealAmount(), detail.getQuantity()));//不含税实际单价
		detail.setTaxAmount(CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(detail.getRealAmount(), detail.getNotaxRealAmount()));
		
		if(isTicket == 0){//无票退
			detail.setProfit(BigDecimal.ZERO);//利润金额,需赋值
			detail.setNotaxProfit(BigDecimal.ZERO);//不含税利润金额,需赋值
			detail.setProfitRate(BigDecimal.ZERO);//利润率,需赋值
			detail.setNotaxProfitRate(BigDecimal.ZERO);//不含税利润率,需赋值
		}else{
			
			List<SaleShelf> oldSaleShelfs = saleShelfMapper.queryByGidAndLotIdAndShelfIdAndLineNum(
					detail.getBaseOrderCode(), detailVO.getGoodsId(),
					detailVO.getLotId(), detailVO.getShelfId(), detailVO.getOldLineNum(), detail.getEnterpriseId());
			if(oldSaleShelfs == null || oldSaleShelfs.size() == 0){
				throw new Exception("查不到原销售明细单据Gid:"+detailVO.getGoodsId()+"LotId:"+
						detailVO.getLotId()+"ShelfId:"+detailVO.getShelfId()+"OldLineNum:"+detailVO.getOldLineNum()+
						"EnterId"+detail.getEnterpriseId()+"错误;");
			}
			
			SimpleProfitVO vo = commonComponent.calculateSaleReturnProfitInfo(oldSaleShelfs.get(0), detail.getEnterpriseId(), detail.getGoodsId(), 
					detailVO.getLotId(), detail.getQuantity(), detail.getRealAmount(), detail.getNotaxRealAmount());
			
			detail.setProfit(vo.getProfit());//利润金额,需赋值
			detail.setNotaxProfit(vo.getNotaxProfit());//不含税利润金额,需赋值
			detail.setProfitRate(vo.getProfitRate());//利润率,需赋值
			detail.setNotaxProfitRate(vo.getNotaxProfitRate());//不含税利润率,需赋值
		}
		
		
		detail.setUnclearQuantity(detail.getQuantity());
		detail.setClearQuantity(BigDecimal.ZERO);
		detail.setRoyaltyFlag(0);
		detail.setThisIntegral(detailVO.getThisIntegral());
		detail.setStatus(sale.getStatus());
		detail.setRemark(detailVO.getRemark());
		detail.setCreaterId(sale.getCreaterId());
		detail.setCreaterCode(sale.getCreaterCode());
		detail.setCreaterName(sale.getCreaterName());
		detail.setCreateTime(new Date());
		detail.setModifierId(sale.getCreaterId());
		detail.setModifierCode(sale.getCreaterCode());
		detail.setModifierName(sale.getCreaterName());
		detail.setUpdateTime(new Date());
		return detail;
	}

	private Sale setSaleData(POSReturnSaleVO pOSReturnSaleVO, UserVO userVO,
			Map<String, BigDecimal> map) throws Exception {
		
		Sale oldSale = new Sale();
		Sale sale = new Sale();
		
		sale.setEnterpriseId(pOSReturnSaleVO.getEnterpriseId());
		sale.setParentId(pOSReturnSaleVO.getParentId());
		sale.setOrderType(OrderRule.SALES_RETURN_IN.getType());
		sale.setSaleDate(pOSReturnSaleVO.getSaleDate());
		sale.setCode(pOSReturnSaleVO.getCode());
		sale.setBaseOrderId(pOSReturnSaleVO.getBaseOrderId());
		sale.setBaseOrderType(OrderRule.SALES_OUT.getType());
		
		if(pOSReturnSaleVO.getIsTicket() == 1){
			if(pOSReturnSaleVO.getBaseOrderId()!=null&&pOSReturnSaleVO.getBaseOrderId()!=0){
				
				oldSale = saleMapper.selectByPrimaryKey(pOSReturnSaleVO.getBaseOrderId());
				if(oldSale == null){
					throw new Exception("销售单ID"+pOSReturnSaleVO.getBaseOrderId()+"错误;");
				}
			}else {
				
				List<Sale> oldSales = saleMapper.queryByCodeAndEnterpriseId(pOSReturnSaleVO.getBaseOrderCode(), userVO.getEnterpriseId());
				if(oldSales == null || oldSales.size() != 1){
					throw new Exception("销售单编号"+pOSReturnSaleVO.getBaseOrderCode()+"错误;");
				}
				oldSale = oldSales.get(0);
			}
			
		}
		sale.setBaseOrderId(oldSale.getId());
		sale.setBaseOrderCode(oldSale.getCode());
		sale.setBaseOrderDate(oldSale.getSaleDate());
		
		sale.setStandCode(pOSReturnSaleVO.getStandCode());
		
		sale.setPayeeId(pOSReturnSaleVO.getPayeeId());
		User payee = userMapper.getUserByIdAndEnterpriseId(pOSReturnSaleVO.getPayeeId(), sale.getEnterpriseId());
		if(payee == null){
			throw new Exception("收款人员ID错误");
		}
		sale.setPayeeCode(payee.getCode());
		sale.setPayeeName(payee.getName());
		sale.setMemberId(pOSReturnSaleVO.getMemberId());
		if(pOSReturnSaleVO.getMemberId()!=null&&pOSReturnSaleVO.getMemberId()!=0){
			MemberInfo member = memberInfoMapper.selectByPrimaryKey(pOSReturnSaleVO.getMemberId());
			if(member != null){
				sale.setMemberName(member.getMemberName());
				sale.setMemberCardCode(member.getCardCode());
				
				sale.setMemberIntegral(member.getCurrentIntegral().subtract(pOSReturnSaleVO.getThisIntegralTotal()));//当前积分
				sale.setThisIntegralTotal(pOSReturnSaleVO.getThisIntegralTotal());//此单积分
			}
		}
		sale.setSaleMode(pOSReturnSaleVO.getSaleMode());
		sale.setSaleType(SaleGenre.SALERETURN.getCode());
		sale.setUpdateTime(pOSReturnSaleVO.getUploadTime());
		sale.setAcceptTime(new Date());
		sale.setExcessiveSale(ExcessiveSale.NO.getCode());
//		sale.setExcessiveSale((oldSale.getExcessiveSale()!=null&&oldSale.getExcessiveSale()==ExcessiveSale.YES.getCode())?
//				ExcessiveSale.YES.getCode():ExcessiveSale.NO.getCode());
		
		map.put("excessiveSale",BigDecimal.ZERO);
		if(oldSale.getExcessiveSale()!=null && oldSale.getExcessiveSale()==ExcessiveSale.YES.getCode()){
			map.put("excessiveSale",BigDecimal.ONE);
		}
		
		sale.setShiftFlag(ShiftFlag.NO.getCode());
		sale.setPaymentFlag(PaymentFlag.NO.getCode());
		sale.setDailySettlementFlag(DailySettlementFlag.NO.getCode());
		sale.setDose(pOSReturnSaleVO.getDose());
		sale.setQuantityTotal(map.get("quantityTotal")==null?BigDecimal.ZERO:map.get("quantityTotal"));
		sale.setCanReturnQuantityTotal(BigDecimal.ZERO);
		sale.setVarietiesQuantity(pOSReturnSaleVO.getVarietiesQuantity());
		sale.setAmountTotal(map.get("amountTotal")==null?BigDecimal.ZERO:map.get("amountTotal"));
		sale.setWholeDiscount(pOSReturnSaleVO.getWholeDiscount());
		sale.setWholeDiscountAmount(pOSReturnSaleVO.getWholeDiscountAmount());
		
		//后面计算重新赋值
		sale.setRealAmountTotal(BigDecimal.ZERO);//实际金额合计
		sale.setNotaxRealAmountTotal(BigDecimal.ZERO);//不含税金额合计
		sale.setTaxAmountTotal(BigDecimal.ZERO);//税额合计
		sale.setProfitTotal(BigDecimal.ZERO);//利润合计
		sale.setNotaxProfitTotal(BigDecimal.ZERO);//不含税利润合计
		sale.setProfitRate(BigDecimal.ZERO);//利润率
		sale.setNotaxProfitRate(BigDecimal.ZERO);//不含税利润率
		
		sale.setStatus(PurchaseStatus.FINISHED.getStatus());
		sale.setRemark(pOSReturnSaleVO.getRemark());
		sale.setCreaterId(userVO.getUserId());
		sale.setCreaterCode(userVO.getUserCode());
		sale.setCreaterName(userVO.getUserName());
		sale.setCreateTime(new Date());
		sale.setModifierId(userVO.getUserId());
		sale.setModifierCode(userVO.getUserCode());
		sale.setModifierName(userVO.getUserName());
		sale.setUpdateTime(new Date());
		
		return sale;
	}
	
	private void handingMemberInfo(MemberInfo member,
			POSReturnSaleVO pOSReturnSaleVO, UserVO userVO, Sale sale) throws Exception {
		
		if(pOSReturnSaleVO.getThisIntegralTotal() != null && pOSReturnSaleVO.getThisIntegralTotal().compareTo(BigDecimal.ZERO)!=0){
//			member.setTotalIntegral(member.getTotalIntegral().add(pOSReturnSaleVO.getThisIntegralTotal()));不减累计积分
			member.setCurrentIntegral(member.getCurrentIntegral().subtract(pOSReturnSaleVO.getThisIntegralTotal()));
			member.setUpdateTime(new Date());
			memberInfoMapper.updateByPrimaryKeySelective(member);
		
			this.addIntegralRecord(member, userVO, pOSReturnSaleVO.getThisIntegralTotal(), sale);//新增积分记录
		}
		
		List<POSReceiptDetailVo> detailList = pOSReturnSaleVO.getReceipt().getDetailList();
		if(detailList != null){
			
			BigDecimal amountTotal = BigDecimal.ZERO;//需增加储值
			
			for (POSReceiptDetailVo posReceiptDetailVo : detailList) {
				if("03".equals(posReceiptDetailVo.getPayTypeCode())){//储值卡
					
					amountTotal = amountTotal.add(posReceiptDetailVo.getAmount());
					this.addStoredAmount(member, userVO, posReceiptDetailVo.getAmount(), sale);//新增储值记录
				}
			}
			
			if(amountTotal.compareTo(BigDecimal.ZERO) != 0){
//				member.setTotalStoredAmount(member.getTotalStoredAmount().subtract(posSaleVO.getThisIntegralTotal()));销退不加累计
				member.setCurrentStoredAmount(member.getCurrentStoredAmount().add(amountTotal));
				memberInfoMapper.updateByPrimaryKeySelective(member);
			}
		}
		
	}
	
	
	private void addIntegralRecord(MemberInfo memberInfo, UserVO userVO, BigDecimal thisIntegralTotal,
			Sale sale) throws Exception {
		IntegralRecord irecord = new IntegralRecord();
		irecord = (IntegralRecord)EntityUtils.reflectAddSetDefaultValue(irecord.getClass(), userVO);
		irecord.setEnterpriseId(memberInfo.getEnterpriseId());
		irecord.setParentId(memberInfo.getParentId());
		irecord.setChangeType(6);
		irecord.setBaseOrderId(sale.getId());
		irecord.setBaseOrderCode(sale.getCode());
		irecord.setBaseOrderType(sale.getOrderType());
		irecord.setBaseOrderDate(sale.getSaleDate());
		irecord.setMemberId(memberInfo.getId());
		irecord.setCardCode(memberInfo.getCardCode());
		irecord.setNewMemberId(memberInfo.getId());
		irecord.setNewCardCode(memberInfo.getCardCode());
		irecord.setTotalIntegral(memberInfo.getTotalIntegral());
		irecord.setCurrentIntegral(memberInfo.getCurrentIntegral());
		irecord.setChangeIntegral(thisIntegralTotal);
		irecord.setNewTotalIntegral(memberInfo.getTotalIntegral());
		irecord.setNewCurrentIntegral(memberInfo.getCurrentIntegral().subtract(thisIntegralTotal));
		irecord.setStatus(EnableStatus.ENABLE.getStatus());
		
		integralRecordMapper.insertSelective(irecord);
	}
	
	
	private void addStoredAmount(MemberInfo memberInfo,UserVO loginUser, BigDecimal amount,
			Sale sale) throws Exception {
		StoredAmount srecord = new StoredAmount();
		srecord = (StoredAmount)EntityUtils.reflectAddSetDefaultValue(srecord.getClass(), loginUser);
		srecord.setEnterpriseId(memberInfo.getEnterpriseId());
		srecord.setParentId(memberInfo.getParentId());
		srecord.setChangeType(6);
		srecord.setBaseOrderId(sale.getId());
		srecord.setBaseOrderCode(sale.getCode());
		srecord.setBaseOrderType(sale.getOrderType());
		srecord.setBaseOrderDate(sale.getSaleDate());
		srecord.setMemberId(memberInfo.getId());
		srecord.setCardCode(memberInfo.getCardCode());
		srecord.setTotalStoredAmount(memberInfo.getTotalStoredAmount());
		srecord.setCurrentStoredAmount(memberInfo.getCurrentStoredAmount());
		srecord.setChangeStoredAmount(amount);
//		srecord.setNewTotalStoredAmount(memberInfo.getTotalStoredAmount().add(amount));
		srecord.setNewCurrentStoredAmount(memberInfo.getCurrentStoredAmount().add(amount));
		srecord.setStatus(EnableStatus.ENABLE.getStatus());
		
		storedAmountMapper.insertSelective(srecord);
	}
	


	private void insertReceipt(Sale sale, POSReceiptVO receipt) throws Exception {
		Receipt re = new Receipt();
		re.setEnterpriseId(sale.getEnterpriseId());
		re.setParentId(sale.getParentId());
		re.setBaseOrderId(sale.getId());
		re.setBaseOrderType(sale.getOrderType());
		re.setBaseOrderCode(sale.getCode());
		re.setBaseOrderDate(sale.getSaleDate());
		re.setAmount(receipt.getAmount());
		re.setRealAmount(receipt.getRealAmount());
		re.setDiffAmount(receipt.getDiffAmount());
		re.setStatus(1);
		re.setCreaterId(sale.getCreaterId());
		re.setCreaterCode(sale.getCreaterCode());
		re.setCreaterName(sale.getCreaterName());
		re.setCreateTime(new Date());
		re.setModifierId(sale.getCreaterId());
		re.setModifierCode(sale.getCreaterCode());
		re.setModifierName(sale.getCreaterName());
		re.setUpdateTime(new Date());
		receiptMapper.insertSelective(re);
		
		if(sale.getRealAmountTotal().compareTo(BigDecimal.ZERO) != 0
				&& (receipt.getDetailList() == null || receipt.getDetailList().size() == 0)){
			throw new Exception("此订单实际金额不为0，收款明细不能为空！");
			
		}else if(receipt.getDetailList() != null || receipt.getDetailList().size() > 0){
			ReceiptDetail detail = null;
			for (POSReceiptDetailVo vo : receipt.getDetailList()) {
				detail = new ReceiptDetail();
				detail.setEnterpriseId(re.getEnterpriseId());
				detail.setParentId(re.getParentId());
				detail.setReceiptId(re.getId());
				detail.setPayTypeId(vo.getPayTypeId());
				detail.setPayTypeName(vo.getPayTypeName());
				detail.setBankId(vo.getBankId());
				detail.setBankName(vo.getBankName());
				detail.setCardAccount(vo.getCardAccount());
				detail.setAmount(vo.getAmount());
				detail.setBalance(vo.getBalance());
				detail.setStatus(1);
				detail.setCreaterId(re.getCreaterId());
				detail.setCreaterCode(re.getCreaterCode());
				detail.setCreaterName(re.getCreaterName());
				detail.setCreateTime(new Date());
				detail.setModifierId(re.getCreaterId());
				detail.setModifierCode(re.getCreaterCode());
				detail.setModifierName(re.getCreaterName());
				detail.setUpdateTime(new Date());
				receiptDetailMapper.insertSelective(detail);
			}
		}	
	}
	
	private Map<String, BigDecimal> getTotalDataByVO(POSReturnSaleVO pOSReturnSaleVO) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		
		BigDecimal quantityTotal = BigDecimal.ZERO; //数量合计
		
		BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
		
		BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
		
		for (POSReturnSaleDetailVO detailVO : pOSReturnSaleVO.getReturnSaleDetailVOList()) {
			
			quantityTotal = quantityTotal.add(detailVO.getQuantity());
			
			//行金额
			BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
					detailVO.getQuantity(), detailVO.getUnitPrice(), detailVO.getLineDiscount());
			
			amountTotal = amountTotal.add(amount);
			
		}
		
		realAmountTotal = amountTotal.multiply(pOSReturnSaleVO.getWholeDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)).subtract(pOSReturnSaleVO.getWholeDiscountAmount());
		
		map.put("quantityTotal", quantityTotal);
		
		map.put("amountTotal", amountTotal);
		
		map.put("realAmountTotal", realAmountTotal);
		
		return map;
	}


	private String validateParam(List<POSReturnSaleVO> returnSaleVOList) {
		
		if(returnSaleVOList == null || returnSaleVOList.size() == 0){
			return "参数不能为空;";
		}
		
		for (POSReturnSaleVO pOSReturnSaleVO : returnSaleVOList) {
			
			Enterprise en = enterpriseMapper.selectByPrimaryKey(pOSReturnSaleVO.getEnterpriseId());
			
			if(en == null || en.getStatus() == 0 || en.getValidFlag() == 0){
				return "当前企业ID"+pOSReturnSaleVO.getEnterpriseId()+"不可用;";
			}
			
			if(!en.getParentId().equals(pOSReturnSaleVO.getParentId())){
				return "当前企业ID"+pOSReturnSaleVO.getEnterpriseId()+",与父级ID"+pOSReturnSaleVO.getParentId()+"不匹配;";
			}
			
			if(StringUtils.isBlank(pOSReturnSaleVO.getCode())){
				return "销退单号不能为空;";
			}
			
			List<Sale> saleList = saleMapper.queryByCodeAndEnterpriseId(pOSReturnSaleVO.getCode(), pOSReturnSaleVO.getEnterpriseId());
			if(saleList != null && saleList.size() > 0){
				return "当前销退单号"+pOSReturnSaleVO.getCode()+"在企业ID"+pOSReturnSaleVO.getEnterpriseId()+"下重复;";
			}
			
			if(pOSReturnSaleVO.getReturnSaleDetailVOList() == null || pOSReturnSaleVO.getReturnSaleDetailVOList().isEmpty()){
				
				return "销退明细对象不能为空;";
			}
			
			if(pOSReturnSaleVO.getReceipt() == null){
				return "收款单对象不能为空;";
			}
			
			
			if(pOSReturnSaleVO.getReceipt().getAmount().compareTo(BigDecimal.ZERO) != 0 &&
					(pOSReturnSaleVO.getReceipt().getDetailList() == null || pOSReturnSaleVO.getReceipt().getDetailList().size() == 0)){
				return "收款应收金额不为0，收款单明细对象不能为空;";
			}
			
			boolean storeAmountPay = false;//是否储值卡支付
			for (POSReceiptDetailVo reDetail : pOSReturnSaleVO.getReceipt().getDetailList()) {
				if("03".equals(reDetail.getPayTypeCode()) && reDetail.getAmount().compareTo(BigDecimal.ZERO) > 0){//储值卡
					storeAmountPay = true;
					break;
				}
			}
			if(storeAmountPay){
				MemberInfo member = memberInfoMapper.selectByPrimaryKey(pOSReturnSaleVO.getMemberId());
				if(member == null){
					return "当前销退单包含储值卡支付，必须传有效会员ID;"+pOSReturnSaleVO.getMemberId();
				}
				
				MemberCardType cardType = memberCardTypeMapper.selectByPrimaryKey(member.getCardTypeId());
				if(cardType == null || cardType.getType().equals(1)){
					return "当前销退单包含储值卡支付，会员卡类型必须具有储值功能;"+pOSReturnSaleVO.getMemberId();
				}
			}
			
			for (POSReturnSaleDetailVO pOSSaleDetailVO : pOSReturnSaleVO.getReturnSaleDetailVOList()) {
				
				if(pOSSaleDetailVO.getLotId() == null || pOSSaleDetailVO.getLotId() == 0){
					
					return "批号ID不能为空;";
				}
				
				if(pOSSaleDetailVO.getShelfId() == null || pOSSaleDetailVO.getShelfId() == 0){
					
					return "货位ID不能为空;";
				}
				
				/*if(pOSSaleDetailVO.getShelfVOList() == null || pOSSaleDetailVO.getShelfVOList().isEmpty()){
					
					return "货位明细对象不能为空;";
				}*/
			}
		}
		
		return null;
	}

}
