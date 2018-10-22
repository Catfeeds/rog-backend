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
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.PharmacySetMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
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
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegisterDetail;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegisterShelf;
import com.rograndec.feijiayun.chain.business.retail.pricing.dao.SalePricingMapper;
import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricing;
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
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterDetail;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterShelf;
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
import com.rograndec.feijiayun.chain.common.constant.SaleMode;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.constant.status.PrescriptionStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.SimpleProfitVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.DailySettlementFlag;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.ExcessiveSale;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.PaymentFlag;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.ShiftFlag;
import com.rograndec.feijiayun.chain.inf.pos.sale.service.POSSaleService;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSNewSpecialRegisterVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSPrescriptionRegisterVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReceiptDetailVo;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReceiptVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSSaleDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSSaleVO;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

@Service
public class POSSaleServiceImpl implements POSSaleService{

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
	private StockMapper stockMapper;
	
	@Autowired
	private WarehouseShelfMapper warehouseShelfMapper;
	
	@Autowired
	private PharmacySetMapper pharmacySetMapper;
	
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
	public String saveSaleData(List<POSSaleVO> saleVOList, UserVO userVO) throws Exception {
		
		String msg = validateParam(saleVOList);
		
		if(StringUtils.isNotBlank(msg)){
			return msg;
		}
		
		for (POSSaleVO posSaleVO : saleVOList) {
			
			saveSaleAssociatedDocuments(posSaleVO, userVO);
			
		}
		
		return null;
	}

	private Sale saveSaleAssociatedDocuments(POSSaleVO posSaleVO,
			UserVO userVO) throws Exception {

		Map<String, BigDecimal> map = getTotalDataByVO(posSaleVO);
		
		Sale sale = setSaleData(posSaleVO, userVO, map);
		
		saleMapper.insertSelective(sale);
		
		PrescriptionRegister re = new PrescriptionRegister(); //新增处方单
		SpecialRegister sr = new SpecialRegister();//专管登记单
		
		if(posSaleVO.getBaseOrderId() != null && posSaleVO.getBaseOrderId() == -1){//新增处方单或者专管登记单
			
			insertAssociatedDocuments(posSaleVO, userVO, map, sale, re, sr);
			
		}else if(posSaleVO.getBaseOrderId() != null && posSaleVO.getBaseOrderId() != 0){//关联处方单、专管登记或者划价单
			
			updateAssociatedDocuments(posSaleVO, sale);
			
		}
		
		List<SaleShelf> shelfList = new ArrayList<SaleShelf>();
		
		PrescriptionRegisterDetail detail = null;
		PrescriptionRegisterShelf shelf = null;
		SpecialRegisterDetail srDetail = null;
		SpecialRegisterShelf srShelf = null;
		
		BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
		BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税实际金额合计
		BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
		BigDecimal profitTotal = BigDecimal.ZERO;//利润合计
		BigDecimal notaxProfitTotal = BigDecimal.ZERO;//不含税利润合计
		
		Map<String, BigDecimal> judgeIsExcessiveSaleMap = new HashMap<String, BigDecimal>();
		
		Map<String, BigDecimal> lineDisAllMap = new HashMap<String, BigDecimal>();
		
		for (int i = 0; i < posSaleVO.getSaleDetailVOList().size(); i++) {
			
			POSSaleDetailVO detailVO = posSaleVO.getSaleDetailVOList().get(i);
			
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
					posSaleVO.getSaleDetailVOList().size(), lineDisAllMap, priceDetail, posSaleVO, re, sr);
			
			saleDetailMapper.insertSelective(saleDetail);
			
			SaleShelf saleShelf = setSaleShelf(detailVO, saleDetail, i);
			
			saleShelfMapper.insertSelective(saleShelf);
			
			shelfList.add(saleShelf);
			
			realAmountTotal = realAmountTotal.add(saleDetail.getRealAmount());//实际金额合计
			notaxRealAmountTotal = notaxRealAmountTotal.add(saleDetail.getNotaxRealAmount());//不含税实际金额合计
			taxAmountTotal = taxAmountTotal.add(saleDetail.getTaxAmount());//税额合计
			profitTotal = profitTotal.add(saleDetail.getProfit());//利润合计
			notaxProfitTotal = notaxProfitTotal.add(saleDetail.getNotaxProfit());//不含税利润合计
			
			if(sale.getExcessiveSale() == ExcessiveSale.NO.getCode()){
				judgeIsExcessiveSale(detailVO, sale, judgeIsExcessiveSaleMap);
			}
			
			if(posSaleVO.getBaseOrderId() != null && posSaleVO.getBaseOrderId() == -1){//生成处方单明细
			
				switch (posSaleVO.getBaseOrderType()) {
				case 6202://处方登记

					detail = new PrescriptionRegisterDetail();
					detail = setPrescriptionRegisterDetail(re, saleDetail, i, detailVO, sale);
					
					prescriptionRegisterDetailMapper.insertSelective(detail);
					
					saleDetail.setBaseOrderDtlId(detail.getId());
					
					shelf = new PrescriptionRegisterShelf();
					shelf = setPrescriptionRegisterShelf(detail, saleShelf);
					
					prescriptionRegisterShelfMapper.insertSelective(shelf);
					break;
					
				default://专管登记
					
					srDetail = new SpecialRegisterDetail();
					srDetail = setSpecialRegisterDetail(sr, saleDetail, i);
					
					specialRegisterDetailMapper.insertSelective(srDetail);
					
					saleDetail.setBaseOrderDtlId(srDetail.getId());
					
					srShelf = new SpecialRegisterShelf();
					srShelf = setSpecialRegisterShelf(srDetail, saleShelf);
					
					specialRegisterShelfMapper.insertSelective(srShelf);
					break;
					
				}
				
				saleDetailMapper.updateByPrimaryKeySelective(saleDetail);
				
			}
		}
		//修改主数据总金额
		updateSaleMainData(sale, re, sr, posSaleVO, realAmountTotal, notaxRealAmountTotal, taxAmountTotal, profitTotal, notaxProfitTotal);
		
		MemberInfo member = memberInfoMapper.selectByPrimaryKey(posSaleVO.getMemberId());
		if(member != null){
			//会员相关操作
			this.handingMemberInfo(member, posSaleVO, userVO, sale);
		}
			
		insertReceipt(sale, posSaleVO.getReceipt());//保存收款单
		
		if(sale.getExcessiveSale() == ExcessiveSale.NO.getCode()){

			OrderModelBuilder builder = new OrderModelBuilder(userVO);
			
			OrderModel orderModel = builder.buildOrderModel(OrderRule.SALES_OUT, sale, shelfList);
			
			inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
		}	
			
		return null;
	}


	/**
	 * @Description: TODO判断是否超售
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年10月21日 下午6:22:35 
	 * @param detailVO
	 * @param sale
	 * @param judgeIsExcessiveSaleMap 
	 * @return void
	 */
	private void judgeIsExcessiveSale(POSSaleDetailVO detailVO, Sale sale, Map<String, BigDecimal> judgeIsExcessiveSaleMap) {

		String key = sale.getEnterpriseId() + "-" + detailVO.getGoodsId() +"-" + detailVO.getShelfId() + "-" + detailVO.getLotId();
		
		BigDecimal usableQuantity = BigDecimal.ZERO;
		
		if(judgeIsExcessiveSaleMap.containsKey(key)){

			usableQuantity = judgeIsExcessiveSaleMap.get(key);
			
			usableQuantity = usableQuantity.subtract(detailVO.getQuantity());
		}else{
			
			GoodsInfoStockShelfVO vo = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(sale.getEnterpriseId(), 
					detailVO.getGoodsId(), detailVO.getShelfId(), detailVO.getLotId());
			
			usableQuantity = vo.getUsableQuantity().subtract(detailVO.getQuantity());
			
		}
		judgeIsExcessiveSaleMap.put(key, usableQuantity);
		
		if(usableQuantity.compareTo(BigDecimal.ZERO) < 0){
			
			sale.setExcessiveSale(ExcessiveSale.YES.getCode());
		}
		
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

	private void updateSaleMainData(Sale sale, PrescriptionRegister re, SpecialRegister sr,
			POSSaleVO posSaleVO, BigDecimal realAmountTotal, BigDecimal notaxRealAmountTotal,
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
		
		
		if(posSaleVO.getBaseOrderId() != null && posSaleVO.getBaseOrderId() == -1){
			switch (posSaleVO.getBaseOrderType()) {
			case 6202://处方登记
				sale.setBaseOrderId(re.getId());
				sale.setBaseOrderCode(re.getCode());
				sale.setBaseOrderType(re.getOrderType());
				sale.setBaseOrderDate(re.getRegisterDate());
				
				re.setRealAmountTotal(sale.getRealAmountTotal());//实际金额合计
				re.setNotaxRealAmountTotal(sale.getNotaxRealAmountTotal());//不含税金额合计
				re.setTaxAmountTotal(sale.getTaxAmountTotal());//税额合计
				re.setProfitTotal(sale.getProfitTotal());//利润合计
				re.setNotaxProfitTotal(sale.getNotaxProfitTotal());//不含税利润合计
				re.setProfitRate(sale.getProfitRate());//利润率
				re.setNotaxProfitRate(sale.getNotaxProfitRate());//不含税利润率
				prescriptionRegisterMapper.updateByPrimaryKeySelective(re);
				
				break;
				
			default://专管登记
				sale.setBaseOrderId(sr.getId());
				sale.setBaseOrderCode(sr.getCode());
				sale.setBaseOrderType(sr.getOrderType());
				sale.setBaseOrderDate(sr.getRegisterDate());
				
				sr.setRealAmountTotal(sale.getRealAmountTotal());//实际金额合计
				sr.setNotaxRealAmountTotal(sale.getNotaxRealAmountTotal());//不含税金额合计
				sr.setTaxAmountTotal(sale.getTaxAmountTotal());//税额合计
				sr.setProfitTotal(sale.getProfitTotal());//利润合计
				sr.setNotaxProfitTotal(sale.getNotaxProfitTotal());//不含税利润合计
				sr.setProfitRate(sale.getProfitRate());//利润率
				sr.setNotaxProfitRate(sale.getNotaxProfitRate());//不含税利润率
				specialRegisterMapper.updateByPrimaryKeySelective(sr);
				
				break;
			}	
		}
		
		saleMapper.updateByPrimaryKeySelective(sale);
	}

	
	private SaleShelf setSaleShelf(POSSaleDetailVO detailVO,
			SaleDetail saleDetail, int i) throws Exception {
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
		shelf.setCanReturnQuantity(shelf.getQuantity());
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
		shelf.setUnclearQuantity(saleDetail.getUnclearQuantity());
		shelf.setThisIntegral(saleDetail.getThisIntegral());
		shelf.setClearQuantity(saleDetail.getClearQuantity());
		/**
		 * 插入已核销数量 + 未核销数量
		 */
		shelf.setVerificationQuantity(saleDetail.getClearQuantity());
		shelf.setUnverificationQuantity(saleDetail.getUnclearQuantity());
		shelf.setLineNum(i+1);
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
	

	private SaleDetail setSaleDetail(POSSaleDetailVO detailVO, Sale sale,
			Goods goods, int i, int size, Map<String, BigDecimal> lineDisAllMap, PriceOrderDetail priceDetail,
			POSSaleVO posSaleVO, PrescriptionRegister re, SpecialRegister sr) throws Exception {
		
		SaleDetail detail = new SaleDetail();
		
		detail.setEnterpriseId(sale.getEnterpriseId());
		detail.setParentId(sale.getParentId());
		detail.setSaleId(sale.getId());
		detail.setOrderType(sale.getOrderType());
		detail.setSaleCode(sale.getCode());
		detail.setSaleDate(sale.getSaleDate());
		/*detail.setBaseOrderId(sale.getBaseOrderId());
		detail.setBaseOrderType(sale.getBaseOrderType());
		detail.setBaseOrderCode(sale.getBaseOrderCode());
		detail.setBaseOrderDate(sale.getBaseOrderDate());*/
		
		if(posSaleVO.getBaseOrderId() != null && posSaleVO.getBaseOrderId() == -1){
			switch (posSaleVO.getBaseOrderType()) {
			case 6202://处方登记
				detail.setBaseOrderId(re.getId());
				detail.setBaseOrderCode(re.getCode());
				detail.setBaseOrderType(re.getOrderType());
				detail.setBaseOrderDate(re.getRegisterDate());
				
				break;
				
			default://专管登记
				detail.setBaseOrderId(sr.getId());
				detail.setBaseOrderCode(sr.getCode());
				detail.setBaseOrderType(sr.getOrderType());
				detail.setBaseOrderDate(sr.getRegisterDate());
				
				break;
			}	
		}

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
		detail.setCanReturnQuantity(detail.getQuantity());
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
		
		SimpleProfitVO vo = commonComponent.calculateProfitInfo(detail.getEnterpriseId(), detail.getGoodsId(), 
				detailVO.getLotId(), detail.getQuantity(), detail.getRealAmount(), detail.getNotaxRealAmount());
		
		detail.setProfit(vo.getProfit());//利润金额,需赋值
		detail.setNotaxProfit(vo.getNotaxProfit());//不含税利润金额,需赋值
		detail.setProfitRate(vo.getProfitRate());//利润率,需赋值
		detail.setNotaxProfitRate(vo.getNotaxProfitRate());//不含税利润率,需赋值
		detail.setUnclearQuantity(detail.getQuantity());
		detail.setClearQuantity(BigDecimal.ZERO);
		detail.setRoyaltyFlag(0);//未提成
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

	private Sale setSaleData(POSSaleVO posSaleVO, UserVO userVO,
			Map<String, BigDecimal> map) throws Exception {
		
		Sale sale = new Sale();
		
		sale.setEnterpriseId(posSaleVO.getEnterpriseId());
		sale.setParentId(posSaleVO.getParentId());
		sale.setOrderType(OrderRule.SALES_OUT.getType());
		sale.setSaleDate(posSaleVO.getSaleDate());
		sale.setCode(posSaleVO.getCode());
		sale.setBaseOrderId(posSaleVO.getBaseOrderId());
		sale.setBaseOrderType(posSaleVO.getBaseOrderType());
		
		if(posSaleVO.getBaseOrderId()!=null&&posSaleVO.getBaseOrderId()!=0&&posSaleVO.getBaseOrderId()!=-1){
			
			switch (posSaleVO.getBaseOrderType()) {
			case 6201:
				SalePricing pricing = salePricingMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
				if(pricing == null){
					throw new Exception("划价单ID"+posSaleVO.getBaseOrderId()+"错误;");
				}
				sale.setBaseOrderCode(pricing.getCode());
				sale.setBaseOrderDate(pricing.getPricingDate());
				break;
			case 6202:
				PrescriptionRegister re = prescriptionRegisterMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
				if(re == null){
					throw new Exception("处方单ID"+posSaleVO.getBaseOrderId()+"错误;");
				}
				sale.setBaseOrderCode(re.getCode());
				sale.setBaseOrderDate(re.getRegisterDate());
				break;
			case 6203:
				SpecialRegister sr = specialRegisterMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
				if(sr == null){
					throw new Exception("划价单ID"+posSaleVO.getBaseOrderId()+"错误;");
				}
				sale.setBaseOrderCode(sr.getCode());
				sale.setBaseOrderDate(sr.getRegisterDate());
				break;
			default:

				break;
			}
			
			/*if(posSaleVO.getBaseOrderType() == 6201){//划价单
				SalePricing pricing = salePricingMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
				if(pricing == null){
					throw new Exception("划价单ID"+posSaleVO.getBaseOrderId()+"错误;");
				}
				sale.setBaseOrderCode(pricing.getCode());
				sale.setBaseOrderDate(pricing.getPricingDate());
			}else if(posSaleVO.getBaseOrderType() == 6202){//处方单
				PrescriptionRegister re = prescriptionRegisterMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
				if(re == null){
					throw new Exception("处方单ID"+posSaleVO.getBaseOrderId()+"错误;");
				}
				sale.setBaseOrderCode(re.getCode());
				sale.setBaseOrderDate(re.getRegisterDate());
			}else if(posSaleVO.getBaseOrderType() == 6204){//销售单
				List<Sale> oldSale = saleMapper.queryByCodeAndEnterpriseId(posSaleVO.getBaseOrderCode(), userVO.getEnterpriseId());
				if(oldSale == null || oldSale.size() == 0){
					throw new Exception("销售单编号"+posSaleVO.getBaseOrderCode()+"错误;");
				}
				sale.setBaseOrderCode(oldSale.get(0).getCode());
				sale.setBaseOrderDate(oldSale.get(0).getSaleDate());
			}*/
		}
		
		sale.setStandCode(posSaleVO.getStandCode());
		
		sale.setPayeeId(posSaleVO.getPayeeId());
		User payee = userMapper.getUserByIdAndEnterpriseId(posSaleVO.getPayeeId(), sale.getEnterpriseId());
		if(payee == null){
			throw new Exception("收款人员ID错误");
		}
		sale.setPayeeCode(payee.getCode());
		sale.setPayeeName(payee.getName());
		sale.setMemberId(posSaleVO.getMemberId());
		if(posSaleVO.getMemberId()!=null&&posSaleVO.getMemberId()!=0){
			MemberInfo member = memberInfoMapper.selectByPrimaryKey(posSaleVO.getMemberId());
			if(member != null){
				
				if(!member.getStatus().equals(0)){
					throw new Exception("会员卡ID:"+posSaleVO.getMemberId()+"，卡非正常状态！");
				}
				
				sale.setMemberName(member.getMemberName());
				sale.setMemberCardCode(member.getCardCode());
				
				sale.setMemberIntegral(member.getCurrentIntegral().add(posSaleVO.getThisIntegralTotal()));//当前积分
				sale.setThisIntegralTotal(posSaleVO.getThisIntegralTotal());//此单积分
			}else{
				throw new Exception("会员卡ID:"+posSaleVO.getMemberId()+"不存在！");
			}
		}
		sale.setSaleMode(posSaleVO.getSaleMode());
		sale.setSaleType(SaleGenre.SALE.getCode());
		sale.setUploadTime(posSaleVO.getUploadTime());
		sale.setAcceptTime(new Date());
		sale.setExcessiveSale(ExcessiveSale.NO.getCode());
		sale.setShiftFlag(ShiftFlag.NO.getCode());
		sale.setPaymentFlag(PaymentFlag.NO.getCode());
		sale.setDailySettlementFlag(DailySettlementFlag.NO.getCode());
		sale.setDose(posSaleVO.getDose());
		sale.setQuantityTotal(map.get("quantityTotal")==null?BigDecimal.ZERO:map.get("quantityTotal"));
		sale.setCanReturnQuantityTotal(sale.getQuantityTotal());
		sale.setVarietiesQuantity(posSaleVO.getVarietiesQuantity());
		sale.setAmountTotal(map.get("amountTotal")==null?BigDecimal.ZERO:map.get("amountTotal"));
		sale.setWholeDiscount(posSaleVO.getWholeDiscount());
		sale.setWholeDiscountAmount(posSaleVO.getWholeDiscountAmount());
		
		//后面计算重新赋值
		sale.setRealAmountTotal(BigDecimal.ZERO);//实际金额合计
		sale.setNotaxRealAmountTotal(BigDecimal.ZERO);//不含税金额合计
		sale.setTaxAmountTotal(BigDecimal.ZERO);//税额合计
		sale.setProfitTotal(BigDecimal.ZERO);//利润合计
		sale.setNotaxProfitTotal(BigDecimal.ZERO);//不含税利润合计
		sale.setProfitRate(BigDecimal.ZERO);//利润率
		sale.setNotaxProfitRate(BigDecimal.ZERO);//不含税利润率
		
		sale.setStatus(PurchaseStatus.FINISHED.getStatus());
		sale.setRemark(posSaleVO.getRemark());
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
	
	//对会员相关信息进行处理
	private void handingMemberInfo(MemberInfo member, POSSaleVO posSaleVO,
			UserVO userVO, Sale sale) throws Exception {
		if(posSaleVO.getThisIntegralTotal() != null && posSaleVO.getThisIntegralTotal().compareTo(BigDecimal.ZERO)!=0){
			
			member.setTotalIntegral(member.getTotalIntegral().add(posSaleVO.getThisIntegralTotal()));
			member.setCurrentIntegral(member.getCurrentIntegral().add(posSaleVO.getThisIntegralTotal()));
			member.setUpdateTime(new Date());
			memberInfoMapper.updateByPrimaryKeySelective(member);
			
			this.addIntegralRecord(member, userVO, posSaleVO.getThisIntegralTotal(), sale);//新增积分记录
		}
		
		List<POSReceiptDetailVo> detailList = posSaleVO.getReceipt().getDetailList();
		if(detailList != null){
			
			BigDecimal currentStoredAmount = member.getCurrentStoredAmount();//当前储值
			BigDecimal amountTotal = BigDecimal.ZERO;//需扣减储值
			
			for (POSReceiptDetailVo posReceiptDetailVo : detailList) {
				if("03".equals(posReceiptDetailVo.getPayTypeCode())){//储值卡
					
					amountTotal = amountTotal.add(posReceiptDetailVo.getAmount());
					this.addStoredAmount(member, userVO, posReceiptDetailVo.getAmount(), sale);//新增储值记录
				}
			}
			
			if(amountTotal.compareTo(currentStoredAmount) == 1){
				throw new Exception("会员卡ID:"+member.getId()+"当前储值小于需扣减储值，交易失败！");
			}
			
			if(amountTotal.compareTo(BigDecimal.ZERO) != 0){
//				member.setTotalStoredAmount(member.getTotalStoredAmount().subtract(posSaleVO.getThisIntegralTotal()));销售不减累计
				member.setCurrentStoredAmount(member.getCurrentStoredAmount().subtract(amountTotal));
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
		irecord.setChangeType(5);
		irecord.setBaseOrderId(sale.getId());
		irecord.setBaseOrderCode(sale.getCode());
		irecord.setBaseOrderType(sale.getOrderType());
		irecord.setBaseOrderDate(sale.getSaleDate());
		irecord.setMemberId(memberInfo.getId());
		irecord.setCardCode(memberInfo.getCardCode());
		irecord.setNewMemberId(memberInfo.getId());
		irecord.setNewCardCode(memberInfo.getCardCode());
		irecord.setTotalIntegral(memberInfo.getTotalIntegral().subtract(thisIntegralTotal));
		irecord.setCurrentIntegral(memberInfo.getCurrentIntegral().subtract(thisIntegralTotal));
		irecord.setChangeIntegral(thisIntegralTotal);
		irecord.setNewTotalIntegral(memberInfo.getTotalIntegral());
		irecord.setNewCurrentIntegral(memberInfo.getCurrentIntegral());
		irecord.setStatus(EnableStatus.ENABLE.getStatus());
		
		integralRecordMapper.insertSelective(irecord);
	}
	
	
	private void addStoredAmount(MemberInfo memberInfo,UserVO loginUser, BigDecimal amount,
			Sale sale) throws Exception {
		StoredAmount srecord = new StoredAmount();
		srecord = (StoredAmount)EntityUtils.reflectAddSetDefaultValue(srecord.getClass(), loginUser);
		srecord.setEnterpriseId(memberInfo.getEnterpriseId());
		srecord.setParentId(memberInfo.getParentId());
		srecord.setChangeType(5);
		srecord.setBaseOrderId(sale.getId());
		srecord.setBaseOrderCode(sale.getCode());
		srecord.setBaseOrderType(sale.getOrderType());
		srecord.setBaseOrderDate(sale.getSaleDate());
		srecord.setMemberId(memberInfo.getId());
		srecord.setCardCode(memberInfo.getCardCode());
		srecord.setTotalStoredAmount(memberInfo.getTotalStoredAmount());
		srecord.setCurrentStoredAmount(memberInfo.getCurrentStoredAmount());
		srecord.setChangeStoredAmount(amount);
		srecord.setNewTotalStoredAmount(memberInfo.getTotalStoredAmount());
		srecord.setNewCurrentStoredAmount(memberInfo.getCurrentStoredAmount().subtract(amount));
		srecord.setStatus(EnableStatus.ENABLE.getStatus());
		
		storedAmountMapper.insertSelective(srecord);
	}

	private void insertAssociatedDocuments(POSSaleVO posSaleVO, UserVO userVO,
			Map<String, BigDecimal> map, Sale sale, PrescriptionRegister re, SpecialRegister sr) throws Exception {
		
		switch (posSaleVO.getBaseOrderType()) {
		case 6202://处方登记

			re = setPrescriptionRegister(posSaleVO, userVO, map, sale, re);
			
			prescriptionRegisterMapper.insertSelective(re);
			
			break;
			
		default://专管登记
			
			sr = setSpecialRegister(posSaleVO, userVO, map, sale, sr);
			
			specialRegisterMapper.insertSelective(sr);
			
			break;
			
		}	
	}


	private void updateAssociatedDocuments(POSSaleVO posSaleVO, Sale sale) {
		
		switch (posSaleVO.getBaseOrderType()) {
		case 6202://处方登记
			PrescriptionRegister re = prescriptionRegisterMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
			re.setBaseOrderId(sale.getId());
			re.setBaseOrderCode(sale.getCode());
			re.setBaseOrderDate(sale.getSaleDate());
			re.setBaseOrderType(sale.getOrderType());
			re.setStatus(PrescriptionStatus.FINISHED);
			prescriptionRegisterMapper.updateByPrimaryKeySelective(re);
			break;
			
		case 6203://专管登记
			SpecialRegister sr = specialRegisterMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
			sr.setBaseOrderId(sale.getId());
			sr.setBaseOrderCode(sale.getCode());
			sr.setBaseOrderDate(sale.getSaleDate());
			sr.setBaseOrderType(sale.getOrderType());
			sr.setStatus(PurchaseStatus.FINISHED.getStatus());
			specialRegisterMapper.updateByPrimaryKeySelective(sr);
			break;	

		default://划价
			SalePricing pricing = salePricingMapper.selectByPrimaryKey(posSaleVO.getBaseOrderId());
			pricing.setSaleCode(sale.getCode());;
			pricing.setStatus(PurchaseStatus.FINISHED.getStatus());
			salePricingMapper.updateByPrimaryKeySelective(pricing);
			break;
		}
		
	}

	
	private Map<String, BigDecimal> getTotalDataByVO(POSSaleVO posSaleVO) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		
		BigDecimal quantityTotal = BigDecimal.ZERO; //数量合计
		
		BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
		
		BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
		
		for (POSSaleDetailVO detailVO : posSaleVO.getSaleDetailVOList()) {
			
			quantityTotal = quantityTotal.add(detailVO.getQuantity());
			
			//行金额
			BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
					detailVO.getQuantity(), detailVO.getUnitPrice(), detailVO.getLineDiscount());
			
			amountTotal = amountTotal.add(amount);
			
		}
		
		realAmountTotal = amountTotal.multiply(posSaleVO.getWholeDiscount().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)).subtract(posSaleVO.getWholeDiscountAmount());
		
		map.put("quantityTotal", quantityTotal);
		
		map.put("amountTotal", amountTotal);
		
		map.put("realAmountTotal", realAmountTotal);
		
		return map;
	}
	
	
	private SpecialRegisterShelf setSpecialRegisterShelf(SpecialRegisterDetail srDetail,
			SaleShelf saleShelf) {
		SpecialRegisterShelf shelf = new SpecialRegisterShelf();
		
		shelf.setEnterpriseId(srDetail.getEnterpriseId());
		shelf.setParentId(srDetail.getParentId());
		shelf.setRegisterDtlId(srDetail.getId());
		shelf.setRegisterId(srDetail.getRegisterId());
		shelf.setGoodsId(srDetail.getGoodsId());
		shelf.setLotId(saleShelf.getLotId());
		shelf.setLotNumber(saleShelf.getLotNumber());
		shelf.setProductDate(saleShelf.getProductDate());
		shelf.setValidDate(saleShelf.getValidDate());
		shelf.setShelfId(saleShelf.getShelfId());
		shelf.setShelfName(saleShelf.getShelfName());
		shelf.setQuantity(saleShelf.getQuantity());
		shelf.setSingleDose(srDetail.getSingleDose());
		shelf.setTotalQuantity(srDetail.getTotalQuantity());
		shelf.setUnitPrice(srDetail.getUnitPrice());
		shelf.setLineDiscount(srDetail.getLineDiscount());
		shelf.setAmount(srDetail.getAmount());
		shelf.setWholeDiscount(srDetail.getWholeDiscount());
		shelf.setLineDiscountAmount(srDetail.getLineDiscountAmount());
		shelf.setRealPrice(srDetail.getRealPrice());
		shelf.setRealAmount(srDetail.getRealAmount());
		shelf.setTaxRate(srDetail.getTaxRate());
		shelf.setNotaxRealPrice(srDetail.getNotaxRealPrice());
		shelf.setNotaxRealAmount(srDetail.getNotaxRealAmount());
		shelf.setTaxAmount(srDetail.getTaxAmount());
		shelf.setProfit(srDetail.getProfit());
		shelf.setNotaxProfit(srDetail.getNotaxProfit());
		shelf.setProfitRate(srDetail.getProfitRate());
		shelf.setNotaxProfitRate(srDetail.getNotaxProfitRate());
		shelf.setUnclearQuantity(srDetail.getUnclearQuantity());
		shelf.setClearQuantity(srDetail.getClearQuantity());
		shelf.setLineNum(srDetail.getLineNum());
		shelf.setStatus(srDetail.getStatus());
		shelf.setRemark(srDetail.getRemark());
		shelf.setCreaterId(srDetail.getCreaterId());
		shelf.setCreaterCode(srDetail.getCreaterCode());
		shelf.setCreaterName(srDetail.getCreaterName());
		shelf.setCreateTime(new Date());
		shelf.setModifierId(shelf.getCreaterId());
		shelf.setModifierCode(shelf.getCreaterCode());
		shelf.setModifierName(shelf.getCreaterName());
		shelf.setUpdateTime(new Date());
		return shelf;
	}

	private SpecialRegisterDetail setSpecialRegisterDetail(
			SpecialRegister sr, SaleDetail saleDetail, int i) {
		SpecialRegisterDetail detail = new SpecialRegisterDetail();
		
		if(sr != null){
			detail.setEnterpriseId(sr.getEnterpriseId());
			detail.setParentId(sr.getParentId());
			detail.setRegisterId(sr.getId());
			detail.setOrderType(sr.getOrderType());
			detail.setRegisterCode(sr.getCode());
			detail.setRegisterDate(sr.getRegisterDate());
			detail.setGoodsId(saleDetail.getGoodsId());
			detail.setGoodsCode(saleDetail.getGoodsCode());
			detail.setBarcode(saleDetail.getBarcode());
			detail.setGoodsName(saleDetail.getGoodsName());
			detail.setGoodsGenericName(saleDetail.getGoodsGenericName());
			detail.setDosageId(saleDetail.getDosageId());
			detail.setDosageName(saleDetail.getDosageName());
			detail.setUnitId(saleDetail.getUnitId());
			detail.setUnitName(saleDetail.getUnitName());
			detail.setGoodsSpecification(saleDetail.getGoodsSpecification());
			detail.setManufacturerId(saleDetail.getManufacturerId());
			detail.setManufacturer(saleDetail.getManufacturer());
			detail.setGoodsPlace(saleDetail.getGoodsPlace());
			detail.setApprovalNumber(saleDetail.getApprovalNumber());
			
			detail.setQuantity(saleDetail.getQuantity());
			detail.setSingleDose(saleDetail.getSingleDose());
			detail.setTotalQuantity(sr.getQuantityTotal());
			detail.setUnitPrice(saleDetail.getUnitPrice());
			detail.setLineDiscount(saleDetail.getLineDiscount());
			detail.setAmount(saleDetail.getAmount());
			detail.setWholeDiscount(saleDetail.getWholeDiscount());
			detail.setLineDiscountAmount(saleDetail.getLineDiscountAmount());
			detail.setRealPrice(saleDetail.getRealPrice());
			detail.setRealAmount(saleDetail.getRealAmount());
			detail.setTaxRateId(saleDetail.getTaxId());
			detail.setTaxRate(saleDetail.getTaxRate());
			detail.setNotaxRealPrice(saleDetail.getNotaxRealPrice());
			detail.setNotaxRealAmount(saleDetail.getNotaxRealAmount());
			detail.setTaxAmount(saleDetail.getTaxAmount());
			detail.setProfit(saleDetail.getProfit());
			detail.setNotaxProfit(saleDetail.getNotaxProfit());
			detail.setProfitRate(saleDetail.getProfitRate());
			detail.setNotaxProfitRate(saleDetail.getNotaxProfitRate());
			detail.setUnclearQuantity(saleDetail.getUnclearQuantity());
			detail.setClearQuantity(saleDetail.getClearQuantity());
			detail.setLineNum(i+1);
			detail.setStatus(saleDetail.getStatus());
			detail.setRemark(saleDetail.getRemark());
			detail.setCreaterId(saleDetail.getCreaterId());
			detail.setCreaterCode(saleDetail.getCreaterCode());
			detail.setCreaterName(saleDetail.getCreaterName());
			detail.setCreateTime(new Date());
			detail.setModifierId(detail.getCreaterId());
			detail.setModifierCode(detail.getCreaterCode());
			detail.setModifierName(detail.getCreaterName());
			detail.setUpdateTime(new Date());
		}
		return detail;
	}
	
	
	private PrescriptionRegisterShelf setPrescriptionRegisterShelf(PrescriptionRegisterDetail detail, 
			SaleShelf saleShelf) throws Exception {
		PrescriptionRegisterShelf shelf = new PrescriptionRegisterShelf();
		shelf.setEnterpriseId(detail.getEnterpriseId());
		shelf.setParentId(detail.getParentId());
		shelf.setRegisterDtlId(detail.getId());
		shelf.setRegisterId(detail.getRegisterId());
		shelf.setGoodsId(detail.getGoodsId());
		shelf.setLotNumberId(saleShelf.getLotId());
		shelf.setLotNumber(saleShelf.getLotNumber());
		shelf.setProductDate(saleShelf.getProductDate());
		shelf.setValidDate(saleShelf.getValidDate());
		shelf.setShelfId(saleShelf.getShelfId());
		shelf.setShelfName(saleShelf.getShelfName());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsId",shelf.getGoodsId());
		map.put("lotId",shelf.getLotNumberId());
		map.put("enterpriseId",shelf.getEnterpriseId());
		map.put("shelfId",shelf.getShelfId());
		List<Stock> list = stockMapper.selectByParamMap(map);
		if(list != null && !list.isEmpty()){
			shelf.setShelfStatusDesc(list.get(0).getShelfStatusDesc());
		}else{
			shelf.setShelfStatusDesc("");
		}
		
		shelf.setQuantity(detail.getQuantity());
		shelf.setUnitPrice(detail.getUnitPrice());
		shelf.setLineDiscount(detail.getLineDiscount());
		shelf.setAmount(detail.getAmount());
		shelf.setWholeDiscount(detail.getWholeDiscount());
		shelf.setLineDiscountAmount(detail.getLineDiscountAmount());
		shelf.setRealPrice(detail.getRealPrice());
		shelf.setRealAmount(detail.getRealAmount());
		shelf.setTaxRateId(detail.getTaxRateId());
		shelf.setTaxRate(detail.getTaxRate());
		shelf.setNotaxRealPrice(detail.getNotaxRealPrice());
		shelf.setNotaxRealAmount(detail.getNotaxRealAmount());
		shelf.setTaxAmount(detail.getTaxAmount());
		shelf.setProfit(detail.getProfit());
		shelf.setNotaxProfit(detail.getNotaxProfit());
		shelf.setProfitRate(detail.getProfitRate());
		shelf.setNotaxProfitRate(detail.getNotaxProfitRate());
		shelf.setUnclearQuantity(detail.getUnclearQuantity());
		shelf.setClearQuantity(detail.getClearQuantity());
		shelf.setLineNum(detail.getLineNum());
		shelf.setStatus(detail.getStatus());
		shelf.setCreaterId(detail.getCreaterId());
		shelf.setCreaterCode(detail.getCreaterCode());
		shelf.setCreaterName(detail.getCreaterName());
		shelf.setCreateTime(new Date());
		shelf.setModifierId(detail.getCreaterId());
		shelf.setModifierCode(detail.getCreaterCode());
		shelf.setModifierName(detail.getCreaterName());
		shelf.setUpdateTime(new Date());
		return shelf;
	}

	private PrescriptionRegisterDetail setPrescriptionRegisterDetail(PrescriptionRegister re, 
			SaleDetail saleDetail, int i, POSSaleDetailVO detailVO, Sale sale) throws Exception {
		
		PrescriptionRegisterDetail detail = new PrescriptionRegisterDetail();
		
		if(re != null){
			
			detail.setEnterpriseId(re.getEnterpriseId());
			detail.setParentId(re.getParentId());
			detail.setRegisterId(re.getId());
			detail.setOrderType(re.getOrderType());
			detail.setRegisterCode(re.getCode());
			detail.setRegisterDate(re.getRegisterDate());
			
			detail.setGoodsId(saleDetail.getGoodsId());
			detail.setGoodsCode(saleDetail.getGoodsCode());
			detail.setBarcode(saleDetail.getBarcode());
			detail.setGoodsName(saleDetail.getGoodsName());
			detail.setGoodsGenericName(saleDetail.getGoodsGenericName());
			detail.setDosageId(saleDetail.getDosageId());
			detail.setDosageName(saleDetail.getDosageName());
			detail.setUnitId(saleDetail.getUnitId());
			detail.setUnitName(saleDetail.getUnitName());
			detail.setGoodsSpecification(saleDetail.getGoodsSpecification());
			detail.setManufacturerId(saleDetail.getManufacturerId());
			detail.setManufacturer(saleDetail.getManufacturer());
			detail.setGoodsPlace(saleDetail.getGoodsPlace());
			detail.setApprovalNumber(saleDetail.getApprovalNumber());
			detail.setSingleDose(saleDetail.getSingleDose());//单剂数量
			detail.setQuantity(saleDetail.getQuantity());
			detail.setUnitPrice(saleDetail.getUnitPrice());
			detail.setLineDiscount(saleDetail.getLineDiscount());
			detail.setAmount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(detail.getQuantity(), detail.getUnitPrice(), detail.getLineDiscount()));
			detail.setWholeDiscount(re.getWholeDiscount());
			detail.setLineDiscountAmount(saleDetail.getLineDiscountAmount());
			detail.setRealPrice(saleDetail.getRealPrice());
			detail.setRealAmount(saleDetail.getRealAmount());
			detail.setTaxRateId(saleDetail.getTaxId());
			detail.setTaxRate(saleDetail.getTaxRate());
			detail.setNotaxRealPrice(saleDetail.getNotaxRealPrice());
			detail.setNotaxRealAmount(saleDetail.getNotaxRealAmount());
			detail.setTaxAmount(saleDetail.getTaxAmount());
			detail.setProfit(saleDetail.getProfit());
			detail.setNotaxProfit(saleDetail.getNotaxProfit());
			detail.setProfitRate(saleDetail.getProfitRate());
			detail.setNotaxProfitRate(saleDetail.getNotaxProfitRate());
			detail.setUnclearQuantity(saleDetail.getUnclearQuantity());
			detail.setClearQuantity(saleDetail.getClearQuantity());
			detail.setLineNum(i+1);
			detail.setStatus(re.getStatus());
			detail.setCreaterId(saleDetail.getCreaterId());
			detail.setCreaterCode(saleDetail.getCreaterCode());
			detail.setCreaterName(saleDetail.getCreaterName());
			detail.setCreateTime(new Date());
			detail.setModifierId(saleDetail.getCreaterId());
			detail.setModifierCode(saleDetail.getCreaterCode());
			detail.setModifierName(saleDetail.getCreaterName());
			detail.setUpdateTime(new Date());
			/* 暂时先注释，等POS开发完成 解注
			 * if(sale.getSaleMode() == SaleMode.CONVENTIONAL.getCode()){
				detail.setTimeDoseId(detailVO.getTimeDoseId());
				PharmacySet set = pharmacySetMapper.selectByPrimaryKey(detailVO.getTimeDoseId());
				if(set == null || set.getSetType() != 2){
					throw new Exception ("单次剂量ID:"+detailVO.getTimeDoseId()+"错误;");
				}
				detail.setTimeDoseName(set.getName());
				detail.setUsageId(detailVO.getUsageId());
				PharmacySet useSet = pharmacySetMapper.selectByPrimaryKey(detailVO.getUsageId());
				if(useSet == null || useSet.getSetType() != 0){
					throw new Exception ("用法ID:"+detailVO.getUsageId()+"错误;");
				}
				detail.setUsageName(useSet.getName());
				detail.setUseQtyId(detailVO.getUseQtyId());
				PharmacySet useQtySet = pharmacySetMapper.selectByPrimaryKey(detailVO.getUseQtyId());
				if(useQtySet == null || useQtySet.getSetType() != 1){
					throw new Exception ("用量ID:"+detailVO.getUseQtyId()+"错误;");
				}
				detail.setUseQtyName(useQtySet.getName());
			}*/
		}
		return detail;
	}
	
	
	private SpecialRegister setSpecialRegister(POSSaleVO posSaleVO,
			UserVO vo, Map<String, BigDecimal> map, Sale sale, SpecialRegister sr) throws Exception {
		
		POSNewSpecialRegisterVO posVO = posSaleVO.getpOSNewSpecialRegisterVO();
		
		sr.setEnterpriseId(sale.getEnterpriseId());
		sr.setParentId(sale.getParentId());
		sr.setOrderType(OrderRule.SPECIAL_REGISTER.getType());
		sr.setRegisterDate(posVO.getRegisterDate());
		sr.setCode(orderCodeComponent.generate(OrderRule.SPECIAL_REGISTER.getCodePrefix(), 
				vo.getEnterpriseId(), vo.getEnterpriseCode()));
		sr.setBaseOrderId(sale.getId());
		sr.setBaseOrderCode(sale.getCode());
		sr.setBaseOrderType(sale.getOrderType());
		sr.setBaseOrderDate(sale.getSaleDate());
		sr.setRegisterManId(posVO.getRegisterManId());
		
		User register = userMapper.getUserByIdAndEnterpriseId(posVO.getRegisterManId(), vo.getEnterpriseId());
		if(register == null){
			throw new Exception("登记人员ID错误");
		}
		sr.setRegisterManCode(register.getCode());
		sr.setRegisterManName(register.getName());
		sr.setMemberId(posVO.getMemberId());
		if(posVO.getMemberId()!=null&&posVO.getMemberId()!=0){
			MemberInfo member = memberInfoMapper.selectByPrimaryKey(posVO.getMemberId());
			if(member != null){
				sr.setMemberCardCode(member.getCardCode());
				sr.setMemberName(member.getMemberName());
			}
		}
		
		sr.setConsumerName(posVO.getConsumerName());
		sr.setSex(posVO.getSex());
		sr.setAge(posVO.getAge());
		sr.setIdNum(posVO.getIdNum());
		
		sr.setQuantityTotal(map.get("quantityTotal")==null?BigDecimal.ZERO:map.get("quantityTotal"));
		sr.setVarietiesQuantity(posSaleVO.getVarietiesQuantity());
		sr.setAmountTotal(map.get("amountTotal")==null?BigDecimal.ZERO:map.get("amountTotal"));
		sr.setWholeDiscount(posSaleVO.getWholeDiscount());
		
		sr.setDiscountAmount(sr.getAmountTotal().multiply(
				(BigDecimal.valueOf(100).subtract(sr.getWholeDiscount()))
				.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
		
		sr.setWholeDiscountAmount(posSaleVO.getWholeDiscountAmount());
		
		//后面计算重新赋值
		sr.setRealAmountTotal(BigDecimal.ZERO);//实际金额合计
		sr.setNotaxRealAmountTotal(BigDecimal.ZERO);//不含税金额合计
		sr.setTaxAmountTotal(BigDecimal.ZERO);//税额合计
		sr.setProfitTotal(BigDecimal.ZERO);//利润合计
		sr.setNotaxProfitTotal(BigDecimal.ZERO);//不含税利润合计
		sr.setProfitRate(BigDecimal.ZERO);//利润率
		sr.setNotaxProfitRate(BigDecimal.ZERO);//不含税利润率
		
		sr.setStatus(PurchaseStatus.FINISHED.getStatus());
		sr.setCreaterId(vo.getUserId());
		sr.setCreaterCode(vo.getUserCode());
		sr.setCreaterName(vo.getUserName());
		sr.setCreateTime(new Date());
		sr.setModifierId(vo.getUserId());
		sr.setModifierCode(vo.getUserCode());
		sr.setModifierName(vo.getUserName());
		sr.setUpdateTime(new Date());
		return sr;
	}
	

	private PrescriptionRegister setPrescriptionRegister(POSSaleVO posSaleVO,
			UserVO vo, Map<String, BigDecimal> map, Sale sale, PrescriptionRegister re) throws Exception {
		
		POSPrescriptionRegisterVO posVO = posSaleVO.getpOSPrescriptionRegisterVO();
		
		re.setEnterpriseId(posSaleVO.getEnterpriseId());
		re.setParentId(posSaleVO.getParentId());
		re.setOrderType(OrderRule.PRESCRIPTION_REGISTER.getType());
		re.setRegisterDate(posVO.getRegisterDate());
		re.setPrescriptionCode(posVO.getPrescriptionCode());
		re.setCode(orderCodeComponent.generate(OrderRule.PRESCRIPTION_REGISTER.getCodePrefix(), 
				vo.getEnterpriseId(), vo.getEnterpriseCode()));
		re.setBaseOrderId(sale.getId());
		re.setBaseOrderCode(sale.getCode());
		re.setBaseOrderType(sale.getOrderType());
		re.setBaseOrderDate(sale.getSaleDate());
		User register = userMapper.getUserByIdAndEnterpriseId(posVO.getRegisterManId(), vo.getEnterpriseId());
		if(register == null){
			throw new Exception("登记人员ID错误");
		}
		re.setRegisterManId(posVO.getRegisterManId());
		re.setRegisterManCode(register.getCode());
		re.setRegisterManName(register.getName());
		re.setBillingDate(posVO.getBillingDate());
		re.setMedicalOrgCode(posVO.getMedicalOrgCode());
		re.setMedicalOrgName(posVO.getMedicalOrgName());
		re.setPrescriptionType(posVO.getPrescriptionType());
		re.setFeeType(posVO.getFeeType());
		re.setOutpatientCaseNum(posVO.getOutpatientCaseNum());
		re.setSectionRoom(posVO.getSectionRoom());
		re.setPatientId(posVO.getPatientId());
		re.setName(posVO.getName());
		re.setSex(posVO.getSex());
		re.setAge(posVO.getAge());
		re.setIdNum(posVO.getIdNum());
		re.setHeight(posVO.getHeight());
		re.setWeight(posVO.getWeight());
		re.setDiagnosis(posVO.getDiagnosis());
		re.setAllergyTest(posVO.getAllergyTest());
		re.setPhysician(posVO.getPhysician());
		re.setDoctorAdvice(posVO.getDoctorAdvice());
		re.setDayDose(posVO.getDayDose());
		
		if(sale.getSaleMode() == SaleMode.CHINESE_MEDICINE.getCode()){
			if(posVO.getUsageId() != null && !posVO.getUsageId().equals(0L)){
				PharmacySet set = pharmacySetMapper.selectByPrimaryKey(posVO.getUsageId());
				if(set == null || set.getSetType() != 0){
					throw new Exception ("用法ID:"+posVO.getUsageId()+"错误;");
				}
				re.setUsageId(posVO.getUsageId());
				re.setUsageName(set.getName());
				re.setUsageValue(posVO.getUsageValue());
			}

			if(posVO.getUseQtyId() != null && !posVO.getUseQtyId().equals(0L)){
				PharmacySet useSet = pharmacySetMapper.selectByPrimaryKey(posVO.getUseQtyId());
				if(useSet == null || useSet.getSetType() != 1){
					throw new Exception ("用量ID:"+posVO.getUseQtyId()+"错误;");
				}
				re.setUseQtyId(posVO.getUseQtyId());
				re.setUseQtyName(useSet.getName());
			}

			if(posVO.getAttentionMatterId() != null && !posVO.getAttentionMatterId().equals(0L)){
				PharmacySet attSet = pharmacySetMapper.selectByPrimaryKey(posVO.getAttentionMatterId());
				if(attSet == null || attSet.getSetType() != 3){
					throw new Exception ("注意事项ID:"+posVO.getAttentionMatterId()+"错误;");
				}
				re.setAttentionMatterId(posVO.getAttentionMatterId());
				re.setAttentionMatterName(attSet.getName());
			}
		}
			
		re.setDose(posVO.getDose());
		
		re.setFileId(posVO.getFileId());
		
		re.setQuantityTotal(map.get("quantityTotal")==null?BigDecimal.ZERO:map.get("quantityTotal"));
		re.setVarietiesQuantity(posSaleVO.getVarietiesQuantity());
		re.setAmountTotal(map.get("amountTotal")==null?BigDecimal.ZERO:map.get("amountTotal"));
		re.setWholeDiscount(posSaleVO.getWholeDiscount());
		re.setWholeDiscountAmount(posSaleVO.getWholeDiscountAmount());
		
		//后面计算重新赋值
		re.setRealAmountTotal(BigDecimal.ZERO);//实际金额合计
		re.setNotaxRealAmountTotal(BigDecimal.ZERO);//不含税金额合计
		re.setTaxAmountTotal(BigDecimal.ZERO);//税额合计
		re.setProfitTotal(BigDecimal.ZERO);//利润合计
		re.setNotaxProfitTotal(BigDecimal.ZERO);//不含税利润合计
		re.setProfitRate(BigDecimal.ZERO);//利润率
		re.setNotaxProfitRate(BigDecimal.ZERO);//不含税利润率

		User audito = userMapper.getUserByIdAndEnterpriseId(posVO.getAuditorId(), vo.getEnterpriseId());
		if(audito == null){
			throw new Exception("审核人员ID错误;"+posVO.getAuditorId());
		}
		re.setAuditTime(posVO.getRegisterDate());
		re.setAuditorId(posVO.getAuditorId());
		re.setAuditorCode(audito.getCode());
		re.setAuditorName(audito.getName());
		re.setAuditOpinion("同意");
		re.setAuditResult(1);
		
		User swapMan = userMapper.getUserByIdAndEnterpriseId(posVO.getSwapManId(), vo.getEnterpriseId());
		if(swapMan == null){
			throw new Exception("调剂人员ID错误;"+posVO.getSwapManId());
		}
		re.setSwapTime(posVO.getRegisterDate());
		re.setSwapManId(posVO.getSwapManId());
		re.setSwapManCode(swapMan.getCode());
		re.setSwapManName(swapMan.getName());
		
		User checker = userMapper.getUserByIdAndEnterpriseId(posVO.getCheckerId(), vo.getEnterpriseId());
		if(checker == null){
			throw new Exception("核对人ID错误;"+posVO.getCheckerId());
		}
		re.setCheckerId(posVO.getCheckerId());
		re.setCheckerCode(checker.getCode());
		re.setCheckerName(checker.getName());
		
		User sender = userMapper.getUserByIdAndEnterpriseId(posVO.getSendId(), vo.getEnterpriseId());
		if(sender == null){
			throw new Exception("发药人ID错误;"+posVO.getSendId());
		}
		re.setSendId(posVO.getSendId());
		re.setSendCode(sender.getCode());
		re.setSendName(sender.getName());
		
		re.setStatus(PrescriptionStatus.FINISHED);
		re.setRemark(posVO.getRemark());
		re.setCreaterId(vo.getUserId());
		re.setCreaterCode(vo.getUserCode());
		re.setCreaterName(vo.getUserName());
		re.setCreateTime(new Date());
		re.setModifierId(vo.getUserId());
		re.setModifierCode(vo.getUserCode());
		re.setModifierName(vo.getUserName());
		re.setUpdateTime(new Date());
		return re;
	}

	private String validateParam(List<POSSaleVO> saleVOList) {
		
		if(saleVOList == null || saleVOList.size() == 0){
			return "参数不能为空;";
		}
		
		for (POSSaleVO posSaleVO : saleVOList) {
			
			Enterprise en = enterpriseMapper.selectByPrimaryKey(posSaleVO.getEnterpriseId());
			
			if(en == null || en.getStatus() == 0 || en.getValidFlag() == 0){
				return "当前企业ID"+posSaleVO.getEnterpriseId()+"不可用;";
			}
			
			if(!en.getParentId().equals(posSaleVO.getParentId())){
				return "当前企业ID"+posSaleVO.getEnterpriseId()+",与父级ID"+posSaleVO.getParentId()+"不匹配;";
			}
			
			if(StringUtils.isBlank(posSaleVO.getCode())){
				return "销售单号不能为空;";
			}
			
			List<Sale> saleList = saleMapper.queryByCodeAndEnterpriseId(posSaleVO.getCode(), posSaleVO.getEnterpriseId());
			if(saleList != null && saleList.size() > 0){
				return "当前销售单号"+posSaleVO.getCode()+"在企业ID"+posSaleVO.getEnterpriseId()+"下重复;";
			}
			
			if(posSaleVO.getBaseOrderId() != null && posSaleVO.getBaseOrderId() != 0){
				
				if(!OrderRule.PRESCRIPTION_REGISTER.getType().equals(posSaleVO.getBaseOrderType()) 
						&& !OrderRule.SALES_PRICE.getType().equals(posSaleVO.getBaseOrderType())
						&& !OrderRule.SPECIAL_REGISTER.getType().equals(posSaleVO.getBaseOrderType())){
					
					return "基础单据类型"+posSaleVO.getBaseOrderType()+"错误;";
				}
				
				if(OrderRule.PRESCRIPTION_REGISTER.getType().equals(posSaleVO.getBaseOrderType())
						&& posSaleVO.getBaseOrderId() == -1
						&& posSaleVO.getpOSPrescriptionRegisterVO() == null){
					
					return "POS新增处方单，处方单对象不能为空;";
				}
				
				if(OrderRule.SPECIAL_REGISTER.getType().equals(posSaleVO.getBaseOrderType())
						&& posSaleVO.getBaseOrderId() == -1
						&& posSaleVO.getpOSNewSpecialRegisterVO() == null){
					
					return "POS新增专管药品登记单，专管药品登记单对象不能为空;";
				}
			}
			
			if(posSaleVO.getSaleMode() == null || (posSaleVO.getSaleMode() != 0 && posSaleVO.getSaleMode() != 1)){
				return "销售模式:"+posSaleVO.getSaleMode()+"错误;";
			}
			
			if(posSaleVO.getBaseOrderId() != null && posSaleVO.getBaseOrderId() == -1 
					&& posSaleVO.getpOSPrescriptionRegisterVO() == null 
						&& posSaleVO.getpOSNewSpecialRegisterVO() == null){
			
				return "POS新增处方单对象或新增专管登记单对象不能为空;";
			}
			
			if(posSaleVO.getSaleDetailVOList() == null || posSaleVO.getSaleDetailVOList().isEmpty()){
				
				return "销售明细对象不能为空;";
			}
			
			if(posSaleVO.getReceipt() == null){
				return "收款单对象不能为空;";
			}
			
			if(posSaleVO.getReceipt().getAmount().compareTo(BigDecimal.ZERO) != 0 &&
					(posSaleVO.getReceipt().getDetailList() == null || posSaleVO.getReceipt().getDetailList().size() == 0)){
				return "收款应收金额不为0，收款单明细对象不能为空;";
			}
			
			boolean storeAmountPay = false;//是否储值卡支付
			for (POSReceiptDetailVo reDetail : posSaleVO.getReceipt().getDetailList()) {
				if("03".equals(reDetail.getPayTypeCode()) && reDetail.getAmount().compareTo(BigDecimal.ZERO) > 0){//储值卡
					storeAmountPay = true;
					break;
				}
			}
			if(storeAmountPay){
				MemberInfo member = memberInfoMapper.selectByPrimaryKey(posSaleVO.getMemberId());
				if(member == null){
					return "当前销售单包含储值卡支付，必须传有效会员ID;"+posSaleVO.getMemberId();
				}
				
				MemberCardType cardType = memberCardTypeMapper.selectByPrimaryKey(member.getCardTypeId());
				if(cardType == null || cardType.getType().equals(1)){
					return "当前销售单包含储值卡支付，会员卡类型必须具有储值功能;"+posSaleVO.getMemberId();
				}
			}
			
			for (POSSaleDetailVO pOSSaleDetailVO : posSaleVO.getSaleDetailVOList()) {
				
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
