package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnInMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao.PaymentVoucherDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao.PaymentVoucherMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucher;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao.ReceivableVoucherDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao.ReceivableVoucherMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucher;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucherDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao.ReceivableInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao.ReceivableInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.dao.FinancePaymentMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePayment;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common.CompanyType;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.dao.FinanceReceivableMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivable;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao.RetailDailySettleMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettle;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.dao.RetailPaymentMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.RetailPayment;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.FinanceBalanceMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.FinanceVoucherMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.FinanceBalance;
import com.rograndec.feijiayun.chain.business.keytable.entity.FinanceVoucher;
import com.rograndec.feijiayun.chain.business.keytable.entity.InOutRecord;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsClearMapper;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsLoadMapper;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClear;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoad;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherOutMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.ShelfMoveMapper;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherIn;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitMapper;
import com.rograndec.feijiayun.chain.business.storage.split.entity.Split;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.opening.dao.*;
import com.rograndec.feijiayun.chain.business.system.opening.entity.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.model.BalanceInitModel;
import com.rograndec.feijiayun.chain.common.model.VoucherParam;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * @ClassName: FinanceComponet
 * @Description: 财务管理模块通用组件
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2018年02月04日 下午13:38:54
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class FinanceComponent {
	private static final Logger logger = LoggerFactory.getLogger(FinanceComponent.class);

	@Autowired
	private FinanceBalanceMapper financeBalanceMapper;

	@Autowired
	private FinanceVoucherMapper financeVoucherMapper;

	@Autowired
	private GoodsTaxRateMapper goodsTaxRateMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private OrderCodeComponent orderCodeComponent;

	@Autowired
	private InOutRecordMapper inOutRecordMapper;

	@Autowired
	private SaleMapper saleMapper;

	// ---------------------------测试代码------------------------s-----
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private OpeningInventoryMapper openingInventoryMapper;
	@Autowired
	private OpeningPaymentMapper openingPaymentMapper;
	@Autowired
	private OpeningPaymentDetailMapper openingPaymentDetailMapper;
	@Autowired
	private OpeningReceivableMapper openingReceivableMapper;
	@Autowired
	private OpeningReceivableDetailMapper openingReceivableDetailMapper;
	@Autowired
	private OpeningTaxMapper openingTaxMapper;
	@Autowired
	private OpeningTaxDetailMapper openingTaxDetailMapper;
	@Autowired
	private PurchaseInStorageMapper purchaseInStorageMapper;
	@Autowired
	private PurchaseReturnOutMapper purchaseReturnOutMapper;
	@Autowired
	private OtherInMapper otherInMapper;
	@Autowired
	private OtherOutMapper otherOutMapper;
	@Autowired
	private ShelfMoveMapper shelfMoveMapper;
	@Autowired
	private GoodsLoadMapper goodsLoadMapper;
	@Autowired
	private GoodsClearMapper goodsClearMapper;
	@Autowired
	private SplitMapper splitMapper;
	@Autowired
	private GoodsDestroyMapper goodsDestroyMapper;
	@Autowired
	private InventoryMapper inventoryMapper;
	@Autowired
	private DistrOutMapper distrOutMapper;
	@Autowired
	private DistrReturnInMapper distrReturnInMapper;
	@Autowired
	private DistrInMapper distrInMapper;
	@Autowired
	private DistrInReturnOutMapper distrInReturnOutMapper;
	@Autowired
	private PrepayInvoiceMapper prepayInvoiceMapper;
	@Autowired
	private PrepayInvoiceDetailMapper prepayInvoiceDetailMapper;
	@Autowired
	private PaymentInvoiceMapper paymentInvoiceMapper;
	@Autowired
	private PaymentInvoiceDetailMapper paymentInvoiceDetailMapper;
	@Autowired
	private PaymentVoucherMapper paymentVoucherMapper;
	@Autowired
	private PaymentVoucherDetailMapper paymentVoucherDetailMapper;
	@Autowired
	private AdvanceReceivableInvoiceMapper advanceReceivableInvoiceMapper;
	@Autowired
	private AdvanceReceivableInvoiceDetailMapper advanceReceivableInvoiceDetailMapper;
	@Autowired
	private ReceivableInvoiceMapper receivableInvoiceMapper;
	@Autowired
	private ReceivableInvoiceDetailMapper receivableInvoiceDetailMapper;
	@Autowired
	private ReceivableVoucherMapper receivableVoucherMapper;
	@Autowired
	private ReceivableVoucherDetailMapper receivableVoucherDetailMapper;
	@Autowired
	private RetailDailySettleMapper retailDailySettleMapper;
	@Autowired
	private SaleDetailMapper saleDetailMapper;
	@Autowired
	private RetailPaymentMapper retailPaymentMapper;
	@Autowired
	private FinancePaymentMapper financePaymentMapper;
	@Autowired
	private FinanceReceivableMapper financeReceivableMapper;

	public Enterprise getEnterpriseById(Long id){
		return enterpriseMapper.selectByPrimaryKey(id);
	}

	public Supplier getSupplierById(Long supplierId) {
		return supplierMapper.selectByPrimaryKey(supplierId);
	}

	public GoodsTaxRate getTaxRateById(Long taxRateId) {
		return goodsTaxRateMapper.selectByPrimaryKey(taxRateId);
	}

	public OpeningInventory getOpeningInventoryById(Long openingInventoryId){
		return openingInventoryMapper.selectByPrimaryKey(openingInventoryId);
	}

	public OpeningPayment getOpeningPaymentById(Long openingPaymentId){
		return openingPaymentMapper.selectByPrimaryKey(openingPaymentId);
	}

	public List<OpeningPaymentDetail> getOpeningPaymentDetailList(Long openingPaymentId){
		return openingPaymentDetailMapper.queryByPaymentId(openingPaymentId);
	}

	public OpeningReceivable getOpeningReceivableById(Long id){
		return openingReceivableMapper.selectByPrimaryKey(id);
	}

	public List<OpeningReceivableDetail> getOpeningReceivableDetailList(Long openingReceivableId){
		return openingReceivableDetailMapper.queryByReceivableId(openingReceivableId);
	}

	public OpeningTax getOpeningTaxById(Long id){
		return openingTaxMapper.selectByPrimaryKey(id);
	}

	public List<OpeningTaxDetail> getOpeningTaxDetailList(Long openingTaxId){
		return openingTaxDetailMapper.queryByTaxId(openingTaxId);
	}

	public PurchaseInStorage getPurchaseInStorageById(Long id){
		return purchaseInStorageMapper.selectByPrimaryKey(id);
	}

	public PurchaseReturnOut getPurchaseReturnOutById(Long id){
		return purchaseReturnOutMapper.selectByPrimaryKey(id);
	}

	public OtherIn getOtherInById(Long id){
		return otherInMapper.selectByPrimaryKey(id);
	}

	public OtherOut getOtherOutById(Long id){
		return otherOutMapper.selectByPrimaryKey(id);
	}

	public ShelfMove getShelfMoveById(Long id){
		return shelfMoveMapper.selectByPrimaryKey(id);
	}

	public GoodsLoad getGoodsLoadById(Long id){
		return goodsLoadMapper.selectByPrimaryKey(id);
	}

	public GoodsClear getGoodsClearById(Long id){
		return goodsClearMapper.selectByPrimaryKey(id);
	}

	public Split getSplitById(Long id){
		return splitMapper.selectByPrimaryKey(id);
	}

	public GoodsDestroy getGoodsDestroyById(Long id){
		return goodsDestroyMapper.selectByPrimaryKey(id);
	}

	public Inventory getInventoryById(Long id){
		return inventoryMapper.selectByPrimaryKey(id);
	}

	public DistrOut getDistrOutById(Long id){
		return distrOutMapper.selectByPrimaryKey(id);
	}

	public DistrReturnIn getDistrReturnInById(Long id){
		return distrReturnInMapper.selectByPrimaryKey(id);
	}

	public DistrIn getDistrInById(Long id){
		return distrInMapper.selectByPrimaryKey(id);
	}

	public DistrInReturnOut getDistrInReturnOutById(Long id){
		return distrInReturnOutMapper.selectByPrimaryKey(id);
	}

	public PrepayInvoice getPrepayInvoiceById(Long id){
		return prepayInvoiceMapper.selectByPrimaryKey(id);
	}

	public List<PrepayInvoiceDetail> getPrepayInvoiceDetailList(Long invoiceId){
		return prepayInvoiceDetailMapper.selectByInvoiceId(invoiceId);
	}

	public PaymentInvoice getPaymentInvoiceById(Long invoiceId){
		return paymentInvoiceMapper.selectByPrimaryKey(invoiceId);
	}

	public List<PaymentInvoiceDetail> getPaymentInvoiceDetailList(Long invoiceId){
		return paymentInvoiceDetailMapper.queryByInvoiceId(invoiceId);
	}

	public PaymentVoucher getPaymentVoucherById(Long id, Long enterpriseId){
		return paymentVoucherMapper.selectByPrimaryKey(id);
	}

	public List<PaymentVoucherDetail> getPaymentVoucherDetailList(Long voucherId, Long enterpriseId){
		return paymentVoucherDetailMapper.queryByVoucherId(voucherId, enterpriseId);
	}

	public AdvanceReceivableInvoice getAdvanceReceivableInvoiceById(Long id){
		return advanceReceivableInvoiceMapper.selectByPrimaryKey(id);
	}

	public List<AdvanceReceivableInvoiceDetail> getAdvanceReceivableInvoiceDetailList(Long invoiceId){
		return advanceReceivableInvoiceDetailMapper.queryByInvoiceId(invoiceId);
	}

	public ReceivableInvoice getReceivableInvoiceId(Long invoiceId){
		return receivableInvoiceMapper.selectByPrimaryKey(invoiceId);
	}

	public List<ReceivableInvoiceDetail> getReceivableInvoiceDetailList(Long invoiceId){
		return receivableInvoiceDetailMapper.getReceivableInvoiceDetailList(invoiceId);
	}

	public ReceivableVoucher getReceivableVoucherById(Long id){
		return receivableVoucherMapper.selectByPrimaryKey(id);
	}

	public List<ReceivableVoucherDetail> getReceivableVoucherDetailList(Long invoiceId){
		return receivableVoucherDetailMapper.getReceivableInvoiceDetailList(invoiceId);
	}

	public RetailDailySettle getRetailDailySettleById(Long id){
		return retailDailySettleMapper.selectByPrimaryKey(id);
	}

	public List<SaleDetail> getSaleDetailList(Long id){
		return saleDetailMapper.selectByShiftId(id);
	}

	public RetailPayment getRetailPaymentById(Long id){
		return retailPaymentMapper.selectByPrimaryKey(id);
	}

	public FinancePayment getFinancePaymentById(Long id){
		return financePaymentMapper.selectByPrimaryKey(id);
	}

	public FinanceReceivable getFinanceReceivableById(Long id){
		return financeReceivableMapper.selectByPrimaryKey(id);
	}

	// ---------------------------测试代码------------------------e-----

	/**
	 * 总部和分店注册或新增时，初始化财务余额表数据
	 * @param user
	 * @param enterprise
	 */
	public void initEnterpriseBalance(UserVO user, Enterprise enterprise){
		List<BalanceInitModel> balanceInitModelList = new ArrayList<BalanceInitModel>();
		Integer chainType = enterprise.getChainType();
		String remark = "";
		if(chainType.equals(ChainType.Headquarters.getCode())){
			List<GoodsTaxRate> goodsTaxRateList = goodsTaxRateMapper.selectAll(enterprise.getId());
			balanceInitModelList = BalanceInitModel.buildForParent(enterprise.getId(), enterprise.getParentId(), goodsTaxRateList);
			remark = "由注册总部生成";
		}else if(chainType.equals(ChainType.Division.getCode())){
			List<GoodsTaxRate> goodsTaxRateList = goodsTaxRateMapper.selectAll(enterprise.getId());
			Enterprise parent = enterpriseMapper.selectByPrimaryKey(enterprise.getParentId());
			balanceInitModelList = BalanceInitModel.buildForLeague(enterprise, parent, goodsTaxRateList);
			remark = "由新增加盟店生成";
		}else {
			Enterprise parent = enterpriseMapper.selectByPrimaryKey(enterprise.getParentId());
			balanceInitModelList = BalanceInitModel.buildForBranch(enterprise, parent);
			remark = "由新增自营店生成";
		}
		for(BalanceInitModel model:balanceInitModelList){
			insertFinanceBalance(user, model, remark);
		}
	}

	/**
	 * 供货单位新增时，初始化财务余额表数据
	 * @param user
	 * @param supplier
	 */
	public void initSupplierBalance(UserVO user, Supplier supplier){
		List<BalanceInitModel> balanceInitModelList = BalanceInitModel.buildForSupplier(user, supplier);
		for(BalanceInitModel model:balanceInitModelList){
			insertFinanceBalance(user, model, "由新增供货单位生成");
		}
	}

	/**
	 * 税率新增时，初始化财务余额表数据
	 * @param user
	 * @param goodsTaxRate
	 */
	public void initTaxBalance(UserVO user, GoodsTaxRate goodsTaxRate){
		List<BalanceInitModel> balanceInitModelList = BalanceInitModel.buildForTax(user, goodsTaxRate);
		for(BalanceInitModel model:balanceInitModelList){
			insertFinanceBalance(user, model, "由自定义税率生成");
		}
	}

	private void insertFinanceBalance(UserVO user, BalanceInitModel model,  String remark) {
		FinanceBalance financeBalance = new FinanceBalance();
		// 企业ID
		financeBalance.setEnterpriseId(model.getEnterpriseId());
		// 上级企业ID
		financeBalance.setParentId(model.getParentId());
		// 企业类型（0-总部；1-自营店；2-加盟店）
		financeBalance.setChainType(model.getChainType());
		// 科目编码
		financeBalance.setAccountCode(model.getAccountCode());
		// 科目名称
		financeBalance.setAccountName(model.getAccountName());
		// 下级科目类型
		financeBalance.setSubAccountType(model.getSubAccountType());
		// 下级科目ID
		financeBalance.setSubAccountId(model.getSubAccountId());
		// 下级科目编码
		financeBalance.setSubAccountCode(model.getSubAccountCode());
		// 下级科目名称
		financeBalance.setSubAccountName(model.getSubAccountName());
		// 借方金额
		financeBalance.setDebitAmount(BigDecimal.ZERO);
		// 贷方金额
		financeBalance.setCreditAmount(BigDecimal.ZERO);
		// 余额
		financeBalance.setBalance(BigDecimal.ZERO);
		// 出入库金额
		financeBalance.setInOutAmount(BigDecimal.ZERO);
		// 开票金额
		financeBalance.setBillAmount(BigDecimal.ZERO);
		// 欠票金额
		financeBalance.setOweBillAmount(BigDecimal.ZERO);
		// 收款金额
		financeBalance.setReceivePayAmount(BigDecimal.ZERO);
		// 欠款金额
		financeBalance.setOweAmount(BigDecimal.ZERO);
		// 备注
		financeBalance.setRemark(remark);
		// 创建人ID
		financeBalance.setCreaterId(user.getUserId());
		// 创建人编码
		financeBalance.setCreaterCode(user.getUserCode());
		// 创建人名称
		financeBalance.setCreaterName(user.getUserName());
		// 创建时间
		financeBalance.setCreateTime(new Date());
		// 最后修改人ID
		financeBalance.setModifierId(user.getUserId());
		// 最后修改人编码
		financeBalance.setModifierCode(user.getUserCode());
		// 最后修改人名称
		financeBalance.setModifierName(user.getUserName());
		// 更新时间
		financeBalance.setUpdateTime(new Date());
		financeBalanceMapper.insertSelective(financeBalance);
	}

	/**
	 * 期初库存保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param openingInventory
	 * @throws Exception
	 */
	public void openingInventoryToBalanceAndVoucher(UserVO user, OpeningInventory openingInventory) throws Exception {
		BigDecimal amountTotal = openingInventory.getNotaxAmountTotal();
		Integer subAccountType = null;
		Long subAccountId = null;
		String subAccountCode = null;
		String subAccountName = null;
		Date baseOrderDate = openingInventory.getStorageDate();
		Integer baseOrderType = openingInventory.getOrderType();
		Long baseOrderId = openingInventory.getId();
		String baseOrderCode = openingInventory.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = openingInventory.getStorageManId();
		String baseOperatorCode = openingInventory.getStorageManCode();
		String baseOperatorName = openingInventory.getStorageManName();
		String remark = "由期初库存生成";

		Enterprise enterprise = buildEnterprise(user);

		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 690101-以前年度损溢调整
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode()),
				subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}

	private Enterprise buildEnterprise(UserVO user) {
		Integer chainType = user.getChainType();
		Enterprise enterprise = new Enterprise();
		if(ChainType.Selfoperatedshop.getCode() == chainType){
			// 自营店，更新总部对应科目余额，插入总部对应科目凭证
			enterprise = enterpriseMapper.selectByPrimaryKey(user.getParentId());
		}else{
			enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());
		}
		return enterprise;
	}

	private void buildAndInsertFinanceVoucher(int financeDirection,  UserVO user, Enterprise enterprise, BigDecimal amountTotal, String code,
											  String accountCode, String accountName,
											  Integer subAccountType, Long subAccountId, String subAccountCode, String subAccountName,
											  Date baseOrderDate, Integer baseOrderType, Long baseOrderId, String baseOrderCode, Long baseDtlId,
											  Long baseOperatorId, String baseOperatorCode, String baseOperatorName, String remark) throws Exception {
		Long enterpriseId = enterprise.getId();
		Long parentId = enterprise.getParentId();
		FinanceBalance financeBalance = getFinanceBalance(enterpriseId, parentId, accountCode, subAccountType, subAccountId, subAccountName);

		// TODO: 兼容线上老数据
		if(financeBalance==null){
			return;
		}

		// 业务发生前余额表余额
		BigDecimal balanceBalance = financeBalance.getBalance()==null?BigDecimal.ZERO:financeBalance.getBalance();

		// 借方金额
		BigDecimal debitAmount = BigDecimal.ZERO;
		// 贷方金额
		BigDecimal creditAmount = BigDecimal.ZERO;
		// 出库入库金额
		BigDecimal inOutAmount = BigDecimal.ZERO;

		if(financeDirection == FinanceDirection.DEBIT.getDirection()){
			// 借方
			debitAmount = amountTotal;
			creditAmount = BigDecimal.ZERO;
			inOutAmount = financeBalance.getInOutAmount().add(amountTotal);
		}else{
			// 贷方
			debitAmount = BigDecimal.ZERO;
			creditAmount = amountTotal;
			inOutAmount = financeBalance.getInOutAmount().subtract(amountTotal);
		}

		// 更新余额
		updateFinanceBalance(financeBalance, debitAmount, creditAmount, inOutAmount);

		// 余额=余额（余额表同科目）余额+借方金额（凭证表）-贷方金额（凭证表）
		BigDecimal balance = balanceBalance.add(debitAmount).subtract(creditAmount);

		// 插入凭证
		VoucherParam voucherParam = new VoucherParam();
		voucherParam.setOrderCode(code);
		voucherParam.setAccountCode(accountCode);
		voucherParam.setAccountName(accountName);
		voucherParam.setSubAccountType(subAccountType);
		voucherParam.setSubAccountId(subAccountId);

		try{
			// 设置税率编码
			if(subAccountType.equals(FinanceAccountType.TAX.getType())){
				GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(subAccountId);
				subAccountCode = goodsTaxRate.getCode();
			}
		}catch (Exception e){
			logger.error("财务管理-凭证表设置税相关明细科目代码异常！");
		}

		voucherParam.setSubAccountCode(subAccountCode);
		voucherParam.setSubAccountName(subAccountName);
		voucherParam.setBaseOrderDate(baseOrderDate);
		voucherParam.setBaseOrderType(baseOrderType);
		voucherParam.setBaseOrderId(baseOrderId);
		voucherParam.setBaseOrderCode(baseOrderCode);
		voucherParam.setBaseDtlId(baseDtlId);
		voucherParam.setBaseOperatorId(baseOperatorId);
		voucherParam.setBaseOperatorCode(baseOperatorCode);
		voucherParam.setBaseOperatorName(baseOperatorName);
		voucherParam.setDebitAmount(debitAmount);
		voucherParam.setCreditAmount(creditAmount);
		voucherParam.setBalance(balance);
		voucherParam.setRemark(remark);

		insertFinanceVoucher(user, enterprise, voucherParam);
	}

	private void updateFinanceBalance(FinanceBalance financeBalance, BigDecimal debitAmount, BigDecimal creditAmount, BigDecimal inOutAmount) {
		// 借方金额=原借方金额+本次借方金额
		financeBalance.setDebitAmount(financeBalance.getDebitAmount().add(debitAmount));
		financeBalance.setCreditAmount(financeBalance.getCreditAmount().add(creditAmount));
		// 贷方金额=原贷方金额+本次贷方金额
		// 余额=借方金额-贷方金额
		financeBalance.setBalance(financeBalance.getDebitAmount().subtract(financeBalance.getCreditAmount()));
		// 出入库金额=原出入库金额+或-单据不含税金额或含税金额
		financeBalance.setInOutAmount(inOutAmount);
		financeBalanceMapper.updateByPrimaryKeySelective(financeBalance);
	}

	private void insertFinanceVoucher(UserVO user, Enterprise enterprise, VoucherParam voucherParam) throws Exception {

		Long enterpriseId = enterprise.getId();
		Long parentId = enterprise.getParentId();
		Integer chainType = enterprise.getChainType();

		FinanceVoucher financeVoucher = new FinanceVoucher();
		financeVoucher.setEnterpriseId(enterpriseId);
		financeVoucher.setParentId(parentId);
		financeVoucher.setChainType(chainType);
		// 科目编码
		financeVoucher.setAccountCode(voucherParam.getAccountCode());
		// 科目名称
		financeVoucher.setAccountName(voucherParam.getAccountName());
		// 下级科目类型
		financeVoucher.setSubAccountType(voucherParam.getSubAccountType());
		// 下级科目ID
		financeVoucher.setSubAccountId(voucherParam.getSubAccountId());
		// 下级科目编码
		financeVoucher.setSubAccountCode(voucherParam.getSubAccountCode());
		// 下级科目名称
		financeVoucher.setSubAccountName(voucherParam.getSubAccountName());
		// 单据日期
		financeVoucher.setOrderDate(new Date());
		// 单据类型
		financeVoucher.setOrderType(OrderRule.FINANCE_VOUCHER.getType());
		// 单据编码
		financeVoucher.setOrderCode(voucherParam.getOrderCode());
		// 基础单据日期
		financeVoucher.setBaseOrderDate(voucherParam.getBaseOrderDate());
		// 基础单据类型
		financeVoucher.setBaseOrderType(voucherParam.getBaseOrderType());
		// 基础单据ID
		financeVoucher.setBaseOrderId(voucherParam.getBaseOrderId());
		// 基础单据编码
		financeVoucher.setBaseOrderCode(voucherParam.getBaseOrderCode());
		// 基础单据明细ID
		financeVoucher.setBaseDtlId(voucherParam.getBaseDtlId());
		// 基础单据操作人员ID
		financeVoucher.setBaseOperatorId(voucherParam.getBaseOperatorId());
		// 基础单据操作人员编码
		financeVoucher.setBaseOperatorCode(voucherParam.getBaseOperatorCode());
		// 基础单据操作人员名称
		financeVoucher.setBaseOperatorName(voucherParam.getBaseOperatorName());
		// 借方金额=汇总本次明细不含税（或含税）金额
		financeVoucher.setDebitAmount(voucherParam.getDebitAmount());
		// 贷方金额=0
		financeVoucher.setCreditAmount(voucherParam.getCreditAmount());
		// 余额=余额表（同科目）余额+借方金额-贷方金额
		financeVoucher.setBalance(voucherParam.getBalance());
		// 备注
		financeVoucher.setRemark(voucherParam.getRemark());
		// 创建人ID
		financeVoucher.setCreaterId(user.getUserId());
		// 创建人编码
		financeVoucher.setCreaterCode(user.getUserCode());
		// 创建人名称
		financeVoucher.setCreaterName(user.getUserName());
		// 创建时间
		financeVoucher.setCreateTime(new Date());
		// 最后修改人ID
		financeVoucher.setModifierId(user.getUserId());
		// 最后修改人编码
		financeVoucher.setModifierCode(user.getUserCode());
		// 最后修改人名称
		financeVoucher.setModifierName(user.getUserName());
		// 更新时间
		financeVoucher.setUpdateTime(new Date());
		financeVoucherMapper.insertSelective(financeVoucher);
	}

	private FinanceBalance getFinanceBalance(Long enterpriseId, Long parentId, String accountCode,
											 Integer subAccountType, Long subAccountId, String subAccountName) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("enterpriseId", enterpriseId);
		paramMap.put("parentId", parentId);
		paramMap.put("accountCode", accountCode);
		paramMap.put("subAccountType", subAccountType);
		paramMap.put("subAccountId", subAccountId);
		if(subAccountType != null && !subAccountType.equals(FinanceAccountType.TAX.getType())
				&& StringUtils.isNotEmpty(subAccountName)){
			paramMap.put("subAccountName", subAccountName);
		}

		FinanceBalance financeBalance = null;
		try{
			financeBalance = financeBalanceMapper.selectByParamMap(paramMap);

			if(financeBalance == null){
				throw new BusinessException("没有获取到财务余额表数据！【参数enterpriseId="+enterpriseId+";parentId="+parentId+";" +
						"accountCode="+accountCode+";subAccountType="+subAccountType+";subAccountId="+subAccountId+";+subAccountName="+subAccountName+"】" );
			}

		}catch (Exception e){
			// TODO 为了兼容老账号，暂时先不抛
			logger.error("获取财务余额表数据异常："+e.getMessage());
//			throw new BusinessException("获取财务余额表数据异常："+e.getMessage());
		}
		return financeBalance;
	}

	/**
	 * 期初应付保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param openingPayment
	 * @throws Exception
	 */
	public void openingPaymentToBalanceAndVoucher(UserVO user, OpeningPayment openingPayment, List<OpeningPaymentDetail> openingPaymentDetailList) throws Exception {

		Date baseOrderDate = openingPayment.getPaymentDate();
		Integer baseOrderType = openingPayment.getOrderType();
		Long baseOrderId = openingPayment.getId();
		String baseOrderCode = openingPayment.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = openingPayment.getPaymentManId();
		String baseOperatorCode = openingPayment.getPaymentManCode();
		String baseOperatorName = openingPayment.getPaymentManName();
		String remark = "由期初应付生成";

		Enterprise enterprise = buildEnterprise(user);

		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		for(OpeningPaymentDetail openingPaymentDetail:openingPaymentDetailList){
			BigDecimal amountTotal = openingPaymentDetail.getAmount();
			Integer subAccountType = openingPaymentDetail.getFinanceAccountType();
			Long subAccountId = openingPaymentDetail.getSupplierId();
			String subAccountCode = openingPaymentDetail.getSupplierCode();
			String subAccountName = openingPaymentDetail.getSupplierName();
			baseDtlId = openingPaymentDetail.getId();

			Integer direction1 = null;
			Integer direction2 = null;
			if(amountTotal.compareTo(BigDecimal.ZERO) > 0){
				direction1 = FinanceDirection.DEBIT.getDirection();
				direction2 = FinanceDirection.CREDIT.getDirection();
			}else if(amountTotal.compareTo(BigDecimal.ZERO) < 0){
				amountTotal = amountTotal.negate();
				direction1 = FinanceDirection.CREDIT.getDirection();
				direction2 = FinanceDirection.DEBIT.getDirection();
			}else{
				continue;
			}

			// 220201-应付账款-供货单位
			buildAndInsertFinanceVoucher(direction1, user, enterprise, amountTotal, code,
					FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PAYMENT_ACCOUNT.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 690101-以前年度损溢调整
			buildAndInsertFinanceVoucher(direction2, user, enterprise, amountTotal, code,
					FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		}

	}

	/**
	 * 期初应收保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param openingReceivable
	 * @param openingReceivableDetailList
	 * @throws Exception
	 */
	public void openingReceivableToBalanceAndVoucher(UserVO user, OpeningReceivable openingReceivable,
													 List<OpeningReceivableDetail> openingReceivableDetailList) throws Exception {
		Date baseOrderDate = openingReceivable.getReceivableDate();
		Integer baseOrderType = openingReceivable.getOrderType();
		Long baseOrderId = openingReceivable.getId();
		String baseOrderCode = openingReceivable.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = openingReceivable.getReceivableManId();
		String baseOperatorCode = openingReceivable.getReceivableManCode();
		String baseOperatorName = openingReceivable.getReceivableManName();
		String remark = "由期初应收生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		for(OpeningReceivableDetail openingReceivableDetail:openingReceivableDetailList){
			BigDecimal amountTotal = openingReceivableDetail.getAmount();
			Integer subAccountType = openingReceivableDetail.getFinanceAccountType();
			Long subAccountId = openingReceivableDetail.getPurchaseUnitId();
			String subAccountCode = openingReceivableDetail.getPurchaseUnitCode();
			String subAccountName = openingReceivableDetail.getPurchaseUnitName();
			baseDtlId = openingReceivableDetail.getId();

			Integer direction1 = null;
			Integer direction2 = null;
			if(amountTotal.compareTo(BigDecimal.ZERO) > 0){
				direction1 = FinanceDirection.DEBIT.getDirection();
				direction2 = FinanceDirection.CREDIT.getDirection();
			}else if(amountTotal.compareTo(BigDecimal.ZERO) < 0){
				amountTotal = amountTotal.negate();
				direction1 = FinanceDirection.CREDIT.getDirection();
				direction2 = FinanceDirection.DEBIT.getDirection();
			}else{
				continue;
			}

			// 112201-应收账款-购货单位
			buildAndInsertFinanceVoucher(direction1, user, enterprise, amountTotal, code,
					FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 690101-以前年度损溢调整
			buildAndInsertFinanceVoucher(direction2, user, enterprise, amountTotal, code,
					FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}


	}

	/**
	 * 期初应收保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param openingTax
	 * @param openingTaxDetailList
	 * @throws Exception
	 */
	public void openingTaxToBalanceAndVoucher(UserVO user, OpeningTax openingTax,
													 List<OpeningTaxDetail> openingTaxDetailList) throws Exception {
		Date baseOrderDate = openingTax.getTaxDate();
		Integer baseOrderType = openingTax.getOrderType();
		Long baseOrderId = openingTax.getId();
		String baseOrderCode = openingTax.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = openingTax.getOperatorId();
		String baseOperatorCode = openingTax.getOperatorCode();
		String baseOperatorName = openingTax.getOperatorName();
		String remark = "由期初税额生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(OpeningTaxDetail openingTaxDetail:openingTaxDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(openingTaxDetail.getTaxRateId());
			goodsTaxRate.setTaxRate(openingTaxDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}

		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlPurTaxAmountTotal = BigDecimal.ZERO;
			for(OpeningTaxDetail openingTaxDetail:openingTaxDetailList){
				Integer taxType = openingTaxDetail.getTaxType();
				BigDecimal taxAmount = openingTaxDetail.getAmount();
				if(goodsTaxRate.getId().equals(openingTaxDetail.getTaxRateId())){
					if(FinanceDirection.DEBIT.getDirection()==taxType){
						dtlPurTaxAmountTotal = dtlPurTaxAmountTotal.add(taxAmount);
					}
				}
			}

			Integer direction1 = null;
			Integer direction2 = null;
			if(dtlPurTaxAmountTotal.compareTo(BigDecimal.ZERO) > 0){
				direction1 = FinanceDirection.DEBIT.getDirection();
				direction2 = FinanceDirection.CREDIT.getDirection();
			}else if(dtlPurTaxAmountTotal.compareTo(BigDecimal.ZERO) < 0){
				dtlPurTaxAmountTotal = dtlPurTaxAmountTotal.negate();
				direction1 = FinanceDirection.CREDIT.getDirection();
				direction2 = FinanceDirection.DEBIT.getDirection();
			}else{
				continue;
			}

			// 22210101-进项税-税率
			buildAndInsertFinanceVoucher(direction1, user, enterprise, dtlPurTaxAmountTotal, code,
					FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 690101-以前年度损溢调整
			buildAndInsertFinanceVoucher(direction2, user, enterprise, dtlPurTaxAmountTotal, code,
					FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlSaleTaxAmountTotal = BigDecimal.ZERO;
			for(OpeningTaxDetail openingTaxDetail:openingTaxDetailList){
				Integer taxType = openingTaxDetail.getTaxType();
				BigDecimal taxAmount = openingTaxDetail.getAmount().negate();
				if(goodsTaxRate.getId().equals(openingTaxDetail.getTaxRateId())){
					if(FinanceDirection.CREDIT.getDirection()==taxType){
						dtlSaleTaxAmountTotal = dtlSaleTaxAmountTotal.add(taxAmount);
					}
				}
			}

			Integer direction1 = null;
			Integer direction2 = null;
			if(dtlSaleTaxAmountTotal.compareTo(BigDecimal.ZERO) > 0){
				direction1 = FinanceDirection.DEBIT.getDirection();
				direction2 = FinanceDirection.CREDIT.getDirection();
			}else if(dtlSaleTaxAmountTotal.compareTo(BigDecimal.ZERO) < 0){
				dtlSaleTaxAmountTotal = dtlSaleTaxAmountTotal.negate();
				direction1 = FinanceDirection.CREDIT.getDirection();
				direction2 = FinanceDirection.DEBIT.getDirection();
			}else{
				continue;
			}

			// 690101-以前年度损溢调整
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, dtlSaleTaxAmountTotal, code,
					FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 22210105-销项税-税率
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, dtlSaleTaxAmountTotal, code,
					FinanceAccount.SALE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.SALE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		}

	}

	/**
	 * 采购入库保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param purchaseInStorage
	 * @throws Exception
	 */
	public void purchaseToBalanceAndVoucher(UserVO user, PurchaseInStorage purchaseInStorage) throws Exception {
		BigDecimal amountTotal = purchaseInStorage.getNotaxRealAmountTotal();
		Date baseOrderDate = purchaseInStorage.getInStorageDate();
		Integer baseOrderType = purchaseInStorage.getOrderType();
		Long baseOrderId = purchaseInStorage.getId();
		String baseOrderCode = purchaseInStorage.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = purchaseInStorage.getStorageManId();
		String baseOperatorCode = purchaseInStorage.getStorageManCode();
		String baseOperatorName = purchaseInStorage.getStorageManName();
		String remark = "由采购入库生成";

		Long enterpriseId = purchaseInStorage.getEnterpriseId();
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);

		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140101-材料采购
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}

	/**
	 * 购进退出出库复核后，对应财务余额表和凭证表影响
	 * @param user
	 * @param purchaseReturnOut
	 * @throws Exception
	 */
	public void purchaseReturnOutToBalanceAndVoucher(UserVO user, PurchaseReturnOut purchaseReturnOut) throws Exception {
		BigDecimal amountTotal = purchaseReturnOut.getNotaxRealAmountTotal().negate();
		Date baseOrderDate = purchaseReturnOut.getOutDate();
		Integer baseOrderType = purchaseReturnOut.getOrderType();
		Long baseOrderId = purchaseReturnOut.getId();
		String baseOrderCode = purchaseReturnOut.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = purchaseReturnOut.getOutManId();
		String baseOperatorCode = purchaseReturnOut.getOutManCode();
		String baseOperatorName = purchaseReturnOut.getOutManName();
		String remark = "由购进退出出库生成";

		Long enterpriseId = purchaseReturnOut.getEnterpriseId();
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140101-材料采购
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}

	/**
	 * 其它入库保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param otherIn
	 * @throws Exception
	 */
	public void otherInToBalanceAndVoucher(UserVO user, OtherIn otherIn) throws Exception {
		BigDecimal amountTotal = otherIn.getNotaxAmountTotal();
		Date baseOrderDate = otherIn.getInDate();
		Integer baseOrderType = otherIn.getOrderType();
		Long baseOrderId = otherIn.getId();
		String baseOrderCode = otherIn.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = otherIn.getInManId();
		String baseOperatorCode = otherIn.getInManCode();
		String baseOperatorName = otherIn.getInManName();
		String remark = "由其它入库生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 640104-库存差异-收益
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

	}


	/**
	 * 其它出库保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param otherOut
	 * @throws Exception
	 */
	public void otherOutToBalanceAndVoucher(UserVO user, OtherOut otherOut) throws Exception {
		BigDecimal amountTotal = otherOut.getNotaxAmountTotal();
		Date baseOrderDate = otherOut.getOutDate();
		Integer baseOrderType = otherOut.getOrderType();
		Long baseOrderId = otherOut.getId();
		String baseOrderCode = otherOut.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = otherOut.getOutManId();
		String baseOperatorCode = otherOut.getOutManCode();
		String baseOperatorName = otherOut.getOutManName();
		String remark = "由其它出库生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 640105-库存差异-损失
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}

	/**
	 *
	 * 货位移动保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param shelfMove
	 * @throws Exception
	 */	public void shelfMoveToBalanceAndVoucher(UserVO user, ShelfMove shelfMove) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Long enterpriseId = shelfMove.getEnterpriseId();
		Long parentId = shelfMove.getParentId();
		Long orderId = shelfMove.getId();
		Integer orderType = shelfMove.getOrderType();
		paramMap.put("enterpriseId", enterpriseId);
		paramMap.put("parentId", parentId);
		paramMap.put("orderId", orderId);
		paramMap.put("orderType", orderType);
		InOutRecord inOutRecord = inOutRecordMapper.selectByParamMap(paramMap);

		BigDecimal amountTotal = inOutRecord.getNotaxCostAmountTotal();
		Date baseOrderDate = shelfMove.getMoveDate();
		Integer baseOrderType = shelfMove.getOrderType();
		Long baseOrderId = shelfMove.getId();
		String baseOrderCode = shelfMove.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = shelfMove.getMoveManId();
		String baseOperatorCode = shelfMove.getMoveManCode();
		String baseOperatorName = shelfMove.getMoveManName();
		String remark = "由货位移动生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}

	/**
	 * 中药装斗保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param goodsLoad
	 * @throws Exception
	 */
	public void goodsLoadToBalanceAndVoucher(UserVO user, GoodsLoad goodsLoad) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Long enterpriseId = goodsLoad.getEnterpriseId();
		Long parentId = goodsLoad.getParentId();
		Long orderId = goodsLoad.getId();
		Integer orderType = goodsLoad.getOrderType();
		paramMap.put("enterpriseId", enterpriseId);
		paramMap.put("parentId", parentId);
		paramMap.put("orderId", orderId);
		paramMap.put("orderType", orderType);
		InOutRecord inOutRecord = inOutRecordMapper.selectByParamMap(paramMap);

		BigDecimal amountTotal = inOutRecord.getNotaxCostAmountTotal();
		Date baseOrderDate = goodsLoad.getLoadDate();
		Integer baseOrderType = goodsLoad.getOrderType();
		Long baseOrderId = goodsLoad.getId();
		String baseOrderCode = goodsLoad.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = goodsLoad.getLoadManId();
		String baseOperatorCode = goodsLoad.getLoadManCode();
		String baseOperatorName = goodsLoad.getLoadManName();
		String remark = "由中药装斗生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}

	/**
	 * 中药清斗保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param goodsClear
	 * @throws Exception
	 */
	public void goodsClearToBalanceAndVoucher(UserVO user, GoodsClear goodsClear) throws Exception {
		BigDecimal amountTotal = goodsClear.getNotaxAmountTotal();
		Date baseOrderDate = goodsClear.getClearDate();
		Integer baseOrderType = goodsClear.getOrderType();
		Long baseOrderId = goodsClear.getId();
		String baseOrderCode = goodsClear.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = goodsClear.getClearManId();
		String baseOperatorCode = goodsClear.getClearManCode();
		String baseOperatorName = goodsClear.getClearManName();
		String remark = "由中药清斗生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 640105-库存差异-损失
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

	}

	/**
	 * 商品拆零保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param split
	 * @throws Exception
	 */
	public void splitToBalanceAndVoucher(UserVO user, Split split) throws Exception {
		BigDecimal amountTotal = split.getNotaxAmountTotal();
		Date baseOrderDate = split.getSplitDate();
		Integer baseOrderType = split.getOrderType();
		Long baseOrderId = split.getId();
		String baseOrderCode = split.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = split.getSplitManId();
		String baseOperatorCode = split.getSplitManCode();
		String baseOperatorName = split.getSplitManName();
		String remark = "由商品拆零生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 640105-库存差异-损失
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 640104-库存差异-收益
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}


	/**
	 * 商品销毁保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param goodsDestroy
	 * @throws Exception
	 */
	public void goodsDestroyToBalanceAndVoucher(UserVO user, GoodsDestroy goodsDestroy) throws Exception {
		BigDecimal amountTotal = goodsDestroy.getNotaxAmountTotal();
		Date baseOrderDate = goodsDestroy.getDestroyDate();
		Integer baseOrderType = goodsDestroy.getOrderType();
		Long baseOrderId = goodsDestroy.getId();
		String baseOrderCode = goodsDestroy.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = goodsDestroy.getDestroyManId();
		String baseOperatorCode = goodsDestroy.getDestroyManCode();
		String baseOperatorName = goodsDestroy.getDestroyManName();
		String remark = "由商品销毁生成";

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 640105-库存差异-损失
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);
	}


	/**
	 * 盘点保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param inventory
	 * @throws Exception
	 */
	public void inventoryToBalanceAndVoucher(UserVO user, Inventory inventory) throws Exception {
		Date baseOrderDate = inventory.getInvDate();
		Integer baseOrderType = inventory.getOrderType();
		Long baseOrderId = inventory.getId();
		String baseOrderCode = inventory.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = inventory.getInvManId();
		String baseOperatorCode = inventory.getInvManCode();
		String baseOperatorName = inventory.getInvManName();
		String remark = "由盘点单生成";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Long enterpriseId = inventory.getEnterpriseId();
		Long parentId = inventory.getParentId();
		Integer direction = OrderDirection.IN.getDirection();
		Long orderId = inventory.getId();
		Integer orderType = inventory.getOrderType();
		paramMap.put("enterpriseId", enterpriseId);
		paramMap.put("parentId", parentId);
		paramMap.put("direction", direction);
		paramMap.put("orderId", orderId);
		paramMap.put("orderType", orderType);
		InOutRecord inRecord = inOutRecordMapper.selectByParamMap(paramMap);

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 盘盈
		if(inRecord != null){
			BigDecimal inAmountTotal = inRecord.getNotaxRealAmountTotal();
			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(),  user, enterprise, inAmountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 640104-库存差异-收益
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, inAmountTotal, code,
					FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		// 盘亏
		paramMap.put("direction", OrderDirection.OUT.getDirection());
		InOutRecord outRecord = inOutRecordMapper.selectByParamMap(paramMap);
		if(outRecord != null){
			BigDecimal outAmountTotal = outRecord.getNotaxRealAmountTotal();
			// 640105-库存差异-损失
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, outAmountTotal, code,
					FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, outAmountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
	}


	/**
	 * 配货出库复核后，对应财务余额表和凭证表影响
	 * @param user
	 * @param distrOut
	 * @throws Exception
	 */
	public void distrOutToBalanceAndVoucher(UserVO user, DistrOut distrOut) throws Exception {
		BigDecimal amountTotal = distrOut.getNotaxRealAmountTotal();
		Integer subAccountType = null;
		Long subAccountId = null;
		String subAccountCode = null;
		String subAccountName = null;
		Date baseOrderDate = distrOut.getOutDate();
		Integer baseOrderType = distrOut.getOrderType();
		Long baseOrderId = distrOut.getId();
		String baseOrderCode = distrOut.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = distrOut.getOutManId();
		String baseOperatorCode = distrOut.getOutManCode();
		String baseOperatorName = distrOut.getOutManName();
		String remark = "由配货出库单生成";

		// 判断要货单位是自营店还是加盟店
		Long reqUnitId = distrOut.getRequestUnitId();
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(reqUnitId);
		Integer chainType = enterprise.getChainType();

		Enterprise parent = enterpriseMapper.selectByPrimaryKey(enterprise.getParentId());

		generateFinanceVoucherForDistrOutAndDistrReturnIn(user, parent, amountTotal, subAccountType, subAccountId, subAccountCode, subAccountName,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark, chainType);
	}

	/**
	 * 配后退回入库保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param distrReturnIn
	 * @throws Exception
	 */
	public void distrReturnInToBalanceAndVoucher(UserVO user, DistrReturnIn distrReturnIn) throws Exception {
		BigDecimal amountTotal = distrReturnIn.getNotaxRealAmountTotal().negate();
		Integer subAccountType = null;
		Long subAccountId = null;
		String subAccountCode = null;
		String subAccountName = null;
		Date baseOrderDate = distrReturnIn.getReturnInDate();
		Integer baseOrderType = distrReturnIn.getOrderType();
		Long baseOrderId = distrReturnIn.getId();
		String baseOrderCode = distrReturnIn.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = distrReturnIn.getStorageManId();
		String baseOperatorCode = distrReturnIn.getStorageManCode();
		String baseOperatorName = distrReturnIn.getStorageManName();
		String remark = "由配后退回入库单生成";

		// 判断要货单位是自营店还是加盟店
		Long reqUnitId = distrReturnIn.getRequestUnitId();
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(reqUnitId);
		Integer chainType = enterprise.getChainType();

		Enterprise parent = enterpriseMapper.selectByPrimaryKey(enterprise.getParentId());

		generateFinanceVoucherForDistrOutAndDistrReturnIn(user, parent, amountTotal, subAccountType, subAccountId, subAccountCode, subAccountName,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark, chainType);
	}

	private void generateFinanceVoucherForDistrOutAndDistrReturnIn(UserVO user,Enterprise enterprise , BigDecimal amountTotal,
																   Integer subAccountType, Long subAccountId, String subAccountCode, String subAccountName,
																   Date baseOrderDate, Integer baseOrderType, Long baseOrderId, String baseOrderCode, Long baseDtlId,
																   Long baseOperatorId, String baseOperatorCode, String baseOperatorName,
																   String remark, Integer chainType) throws Exception {

		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		if(chainType.equals(ChainType.Selfoperatedshop.getCode())){
			// 自营店
			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}else if(chainType.equals(ChainType.Division.getCode())){
			// 加盟店
			// 640101-销售成本
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.SALE_COST.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.SALE_COST.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
	}


	/**
	 * 配进入库保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param distrIn
	 * @throws Exception
	 */
	public void distrInToBalanceAndVoucher(UserVO user, DistrIn distrIn) throws Exception {
		BigDecimal amountTotal = distrIn.getNotaxRealAmountTotal();
		Integer subAccountType = null;
		Long subAccountId = null;
		String subAccountCode = null;
		String subAccountName = null;
		Date baseOrderDate = distrIn.getInDate();
		Integer baseOrderType = distrIn.getOrderType();
		Long baseOrderId = distrIn.getId();
		String baseOrderCode = distrIn.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = distrIn.getStorageManId();
		String baseOperatorCode = distrIn.getStorageManCode();
		String baseOperatorName = distrIn.getStorageManName();
		String remark = "由配进入库单生成";

		// 判断是自营店还是加盟店做的配进订单
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrIn.getEnterpriseId());
		Integer chainType = enterprise.getChainType();
		generateFinanceVoucherForDistrInOrDistrInReturnOut(user, amountTotal,
				subAccountType, subAccountId, subAccountCode, subAccountName,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark, enterprise, chainType);
	}

	private void generateFinanceVoucherForDistrInOrDistrInReturnOut(UserVO user, BigDecimal amountTotal,
																	Integer subAccountType, Long subAccountId, String subAccountCode, String subAccountName,
																	Date baseOrderDate, Integer baseOrderType, Long baseOrderId, String baseOrderCode, Long baseDtlId,
																	Long baseOperatorId, String baseOperatorCode, String baseOperatorName,
																	String remark, Enterprise enterprise, Integer chainType) throws Exception {

		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());
		if(chainType.equals(ChainType.Selfoperatedshop.getCode())){

			Enterprise parent = enterpriseMapper.selectByPrimaryKey(enterprise.getParentId());

			// 自营店
			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, parent, amountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, parent, amountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}else if(chainType.equals(ChainType.Division.getCode())){
			// 140601-库存商品
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 140101-材料采购
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
					subAccountType, subAccountId, subAccountCode, subAccountName,baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
	}

	/**
	 * 配进退出出库复核后，对应财务余额表和凭证表影响
	 * @param user
	 * @param distrInReturnOut
	 * @throws Exception
	 */
	public void distrInReturnOutToBalanceAndVoucher(UserVO user, DistrInReturnOut distrInReturnOut) throws Exception {
		BigDecimal amountTotal = distrInReturnOut.getNotaxRealAmountTotal().negate();
		Integer subAccountType = null;
		Long subAccountId = null;
		String subAccountCode = null;
		String subAccountName = null;
		Date baseOrderDate = distrInReturnOut.getOutDate();
		Integer baseOrderType = distrInReturnOut.getOrderType();
		Long baseOrderId = distrInReturnOut.getId();
		String baseOrderCode = distrInReturnOut.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = distrInReturnOut.getOutManId();
		String baseOperatorCode = distrInReturnOut.getOutManCode();
		String baseOperatorName = distrInReturnOut.getOutManName();
		String remark = "由配进退出出库单生成";

		// 判断是自营店还是加盟店做的配进退出出库
		Long enterpriseId = distrInReturnOut.getEnterpriseId();
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		Integer chainType = enterprise.getChainType();

		generateFinanceVoucherForDistrInOrDistrInReturnOut(user, amountTotal,
				subAccountType, subAccountId, subAccountCode, subAccountName,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark, enterprise, chainType);
	}


	/**
	 * 预付发票保存或冲销后，对应财务余额表和凭证表影响
	 * @param user
	 * @param prepayInvoice
	 * @param prepayInvoiceDetailList
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void prepayInvoiceToBalanceAndVoucherWhenSaveOrWriteOff(UserVO user, PrepayInvoice prepayInvoice,
														 List<PrepayInvoiceDetail> prepayInvoiceDetailList, String type) throws Exception {
		BigDecimal amountTotal = prepayInvoice.getAmountTotal();
		BigDecimal notaxAmounttotal = prepayInvoice.getNotaxAmountTotal();
		String remark = "由预付发票保存后生成";
		if("writeOff".equals(type)){
			amountTotal = amountTotal.negate();
			notaxAmounttotal = notaxAmounttotal.negate();
			remark = "由预付发票冲销后生成";
		}
		Date baseOrderDate = prepayInvoice.getBillDate();
		Integer baseOrderType = prepayInvoice.getOrderType();
		Long baseOrderId = prepayInvoice.getId();
		String baseOrderCode = prepayInvoice.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = prepayInvoice.getBillManId();
		String baseOperatorCode = prepayInvoice.getBillManCode();
		String baseOperatorName = prepayInvoice.getBillManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140101-材料采购
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, notaxAmounttotal, code,
				FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 22210101-进项税额
		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(PrepayInvoiceDetail prepayInvoiceDetail:prepayInvoiceDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(prepayInvoiceDetail.getTaxRateId());
			goodsTaxRate.setTaxRate(prepayInvoiceDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}
		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlTaxAmountTotal = BigDecimal.ZERO;
			for(PrepayInvoiceDetail prepayInvoiceDetail:prepayInvoiceDetailList){
				BigDecimal taxAmount = prepayInvoiceDetail.getTaxAmount();
				if(goodsTaxRate.getId().equals(prepayInvoiceDetail.getTaxRateId())){
					dtlTaxAmountTotal = dtlTaxAmountTotal.add(taxAmount);
				}
			}
			dtlTaxAmountTotal = ("writeOff".equals(type)) ? dtlTaxAmountTotal.negate() : dtlTaxAmountTotal;
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, dtlTaxAmountTotal, code,
					FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 220201-应付账款-供货单位
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PAYMENT_ACCOUNT.getAccountCode()),
				prepayInvoice.getFinanceAccountType(), prepayInvoice.getSupplierId(), prepayInvoice.getSupplierCode(), prepayInvoice.getSupplierName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 更新余额表的开票金额、欠票金额、欠款金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(),prepayInvoice.getFinanceAccountType(), prepayInvoice.getSupplierId(), prepayInvoice.getSupplierName());
		updateAmountInfoForReceivableOrPaymentAccount(amountTotal, financeBalance);

	}

	private void updateAmountInfoForReceivableOrPaymentAccount(BigDecimal amountTotal, FinanceBalance financeBalance) {
		if(financeBalance != null){
			// 开票金额
			financeBalance.setBillAmount(financeBalance.getBillAmount().add(amountTotal));
			// 欠票金额=出入库金额-开票金额
			financeBalance.setOweBillAmount(financeBalance.getInOutAmount().subtract(financeBalance.getBillAmount()));
			// 欠款金额
			financeBalance.setOweAmount(financeBalance.getBillAmount().subtract(financeBalance.getReceivePayAmount()));
			financeBalanceMapper.updateByPrimaryKeySelective(financeBalance);
		}
	}

	/**
	 * 预付发票对账后，对应财务余额表和凭证表影响
	 * @param user
	 * @param prepayInvoice
	 * @param prepayInvoiceDetailList
	 * @throws Exception
	 */
	public void prepayInvoiceToBalanceAndVoucherWhenCheckAccount(UserVO user, PrepayInvoice prepayInvoice,
																 List<PrepayInvoiceDetail> prepayInvoiceDetailList) throws Exception {
		BigDecimal amountTotal = BigDecimal.ZERO;
		Date baseOrderDate = prepayInvoice.getBillDate();
		Integer baseOrderType = prepayInvoice.getOrderType();
		Long baseOrderId = prepayInvoice.getId();
		String baseOrderCode = prepayInvoice.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = prepayInvoice.getBillManId();
		String baseOperatorCode = prepayInvoice.getBillManCode();
		String baseOperatorName = prepayInvoice.getBillManName();
		String remark = "由预付发票对账后生成";

		Enterprise enterprise = buildEnterprise(user);

		for(PrepayInvoiceDetail prepayInvoiceDetail:prepayInvoiceDetailList){
			BigDecimal diffNotaxAmount=prepayInvoiceDetail.getDiffNotaxAmount()==null?BigDecimal.ZERO:prepayInvoiceDetail.getDiffNotaxAmount();
			amountTotal = amountTotal.add(diffNotaxAmount);
		}

		generateFinanceVoucherForPaymentInvoice(user, amountTotal,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark, enterprise);
	}

	private void generateFinanceVoucherForPaymentInvoice(UserVO user, BigDecimal amountTotal,
														 Date baseOrderDate, Integer baseOrderType, Long baseOrderId, String baseOrderCode, Long baseDtlId,
														 Long baseOperatorId, String baseOperatorCode, String baseOperatorName, String remark,
														 Enterprise enterprise) throws Exception {
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());
		if(amountTotal.compareTo(BigDecimal.ZERO) < 0){
			amountTotal = amountTotal.negate();
			// 差异金额=发票金额-单据金额。差异小于0，代表价格收益
			// 640104-库存差异-收益
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 140101-材料采购
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}else if(amountTotal.compareTo(BigDecimal.ZERO) > 0){
			// 140101-材料采购
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 差异金额=发票金额-单据金额。差异大于0，代表价格损失
			// 640105-库存差异-损失
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
					FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode()),
					null, null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
	}

	/**
	 * 应付发票保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param paymentInvoice
	 * @param paymentInvoiceDetailList
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void paymentInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(UserVO user, PaymentInvoice paymentInvoice,
																	List<PaymentInvoiceDetail> paymentInvoiceDetailList, String type) throws Exception {
		BigDecimal diffNotaxAmountTotal = paymentInvoice.getDiffNotaxAmountTotal();
		BigDecimal amountTotal = paymentInvoice.getAmountTotal();
		BigDecimal notaxAmounttotal = paymentInvoice.getNotaxAmountTotal();
		String remark = "由应付发票保存后生成";
		if("writeOff".equals(type)){
			amountTotal = amountTotal.negate();
			notaxAmounttotal = notaxAmounttotal.negate();
			remark = "由应付发票冲销后生成";
		}
		Date baseOrderDate = paymentInvoice.getBillDate();
		Integer baseOrderType = paymentInvoice.getOrderType();
		Long baseOrderId = paymentInvoice.getId();
		String baseOrderCode = paymentInvoice.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = paymentInvoice.getBillManId();
		String baseOperatorCode = paymentInvoice.getBillManCode();
		String baseOperatorName = paymentInvoice.getBillManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140101-材料采购
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, notaxAmounttotal, code,
				FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
				null,null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 22210101-进项税额
		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(PaymentInvoiceDetail paymentInvoiceDetail:paymentInvoiceDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(paymentInvoiceDetail.getTaxRateId());
			goodsTaxRate.setTaxRate(paymentInvoiceDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}
		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlTaxAmountTotal = BigDecimal.ZERO;
			for(PaymentInvoiceDetail paymentInvoiceDetail:paymentInvoiceDetailList){
				BigDecimal taxAmount = paymentInvoiceDetail.getTaxAmount();
				if(goodsTaxRate.getId().equals(paymentInvoiceDetail.getTaxRateId())){
					dtlTaxAmountTotal = dtlTaxAmountTotal.add(taxAmount);
				}
			}
			dtlTaxAmountTotal = ("writeOff".equals(type)) ? dtlTaxAmountTotal.negate() : dtlTaxAmountTotal;
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, dtlTaxAmountTotal, code,
					FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 220201-应付账款-供货单位
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PAYMENT_ACCOUNT.getAccountCode()),
				paymentInvoice.getFinanceAccountType(), paymentInvoice.getSupplierId(), paymentInvoice.getSupplierCode(), paymentInvoice.getSupplierName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		generateFinanceVoucherForPaymentInvoice(user, diffNotaxAmountTotal,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark, enterprise);

		// 更新余额表的开票金额、欠票金额、欠款金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), paymentInvoice.getFinanceAccountType(), paymentInvoice.getSupplierId(), paymentInvoice.getSupplierName());
		updateAmountInfoForReceivableOrPaymentAccount(amountTotal, financeBalance);

	}

	/**
	 * 应付贷项凭证保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param paymentVoucher
	 * @param paymentInvoiceDetailList
	 * @param type
	 * @throws Exception
	 */
	public void paymentVoucherToBalanceAndVoucherWhenSaveOrWirteOff(UserVO user, PaymentVoucher paymentVoucher,
																	List<PaymentVoucherDetail> paymentInvoiceDetailList, String type) throws Exception {
		BigDecimal diffNotaxAmountTotal = paymentVoucher.getDiffNotaxAmountTotal();

		BigDecimal amountTotal = paymentVoucher.getAmountTotal();
		BigDecimal notaxAmounttotal = paymentVoucher.getNotaxAmountTotal();
		String remark = "由应付贷项凭证冲销后生成";
		if("save".equals(type)){
			amountTotal = amountTotal.negate();
			notaxAmounttotal = notaxAmounttotal.negate();
			remark = "由应付贷项凭证保存后生成";
		}
		Date baseOrderDate = paymentVoucher.getPostDate();
		Integer baseOrderType = paymentVoucher.getOrderType();
		Long baseOrderId = paymentVoucher.getId();
		String baseOrderCode = paymentVoucher.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = paymentVoucher.getPostManId();
		String baseOperatorCode = paymentVoucher.getPostManCode();
		String baseOperatorName = paymentVoucher.getPostManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 140101-材料采购
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, notaxAmounttotal, code,
				FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
				null,null, null, null, baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 22210101-进项税额
		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(PaymentVoucherDetail paymentVoucherDetail:paymentInvoiceDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(paymentVoucherDetail.getTaxRateId());
			goodsTaxRate.setTaxRate(paymentVoucherDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}
		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlTaxAmountTotal = BigDecimal.ZERO;
			for(PaymentVoucherDetail paymentVoucherDetail:paymentInvoiceDetailList){
				BigDecimal taxAmount = paymentVoucherDetail.getTaxAmount();
				if(goodsTaxRate.getId().equals(paymentVoucherDetail.getTaxRateId())){
					dtlTaxAmountTotal = dtlTaxAmountTotal.add(taxAmount);
				}
			}
			dtlTaxAmountTotal = ("save".equals(type))?dtlTaxAmountTotal.negate():dtlTaxAmountTotal;
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, dtlTaxAmountTotal, code,
					FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 220201-应付账款-供货单位
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PAYMENT_ACCOUNT.getAccountCode()),
				paymentVoucher.getFinanceAccountType(), paymentVoucher.getSupplierId(), paymentVoucher.getSupplierCode(), paymentVoucher.getSupplierName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		if(diffNotaxAmountTotal.compareTo(BigDecimal.ZERO)==0){
			// do nothing
		}else if(diffNotaxAmountTotal.compareTo(BigDecimal.ZERO)<0){
			diffNotaxAmountTotal = diffNotaxAmountTotal.negate();

			// 差异金额=发票金额-单据金额。差异小于0，代表价格收益
			// 640104-库存差异-收益
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, diffNotaxAmountTotal.negate(), code,
					FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 140101-材料采购
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, diffNotaxAmountTotal.negate(), code,
					FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}else{
			// 140101-材料采购
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, diffNotaxAmountTotal.negate(), code,
					FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MATERIAL_PURCHASE.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			// 差异金额=发票金额-单据金额。差异大于0，代表价格损失
			// 640105-库存差异-损失
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, diffNotaxAmountTotal.negate(), code,
					FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		}

		// 更新余额表的开票金额、欠票金额、欠款金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), paymentVoucher.getFinanceAccountType(), paymentVoucher.getSupplierId(), paymentVoucher.getSupplierName());
		updateAmountInfoForReceivableOrPaymentAccount(amountTotal, financeBalance);

	}


	/**
	 * 预收发票保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param advanceReceivableInvoice
	 * @param advanceReceivableInvoiceDetailList
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void advanceReceivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(UserVO user, AdvanceReceivableInvoice advanceReceivableInvoice,
																	List<AdvanceReceivableInvoiceDetail> advanceReceivableInvoiceDetailList, String type) throws Exception {
		BigDecimal amountTotal = advanceReceivableInvoice.getAmountTotal();
		BigDecimal notaxAmounttotal = advanceReceivableInvoice.getNotaxAmountTotal();
		String remark = "由预收发票保存后生成";
		if("writeOff".equals(type)){
			amountTotal = amountTotal.negate();
			notaxAmounttotal = notaxAmounttotal.negate();
			remark = "由预收发票冲销后生成";
		}
		Date baseOrderDate = advanceReceivableInvoice.getBillDate();
		Integer baseOrderType = advanceReceivableInvoice.getOrderType();
		Long baseOrderId = advanceReceivableInvoice.getId();
		String baseOrderCode = advanceReceivableInvoice.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = advanceReceivableInvoice.getBillManId();
		String baseOperatorCode = advanceReceivableInvoice.getBillManCode();
		String baseOperatorName = advanceReceivableInvoice.getBillManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 112201-应收账款-购货单位
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode()),
				advanceReceivableInvoice.getFinanceAccountType(), advanceReceivableInvoice.getPurchaseUnitId(), advanceReceivableInvoice.getPurchaseUnitCode(), advanceReceivableInvoice.getPurchaseUnitName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 666101-主营业务收入
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, notaxAmounttotal, code,
				FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode()),
				null,null, null,null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 22210105-销项税额
		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(AdvanceReceivableInvoiceDetail advanceReceivableInvoiceDetail:advanceReceivableInvoiceDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(advanceReceivableInvoiceDetail.getTaxRateId());
			goodsTaxRate.setTaxRate(advanceReceivableInvoiceDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}
		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlTaxAmountTotal = BigDecimal.ZERO;
			for(AdvanceReceivableInvoiceDetail advanceReceivableInvoiceDetail:advanceReceivableInvoiceDetailList){
				BigDecimal taxAmount = advanceReceivableInvoiceDetail.getTaxAmount();
				if(goodsTaxRate.getId().equals(advanceReceivableInvoiceDetail.getTaxRateId())){
					dtlTaxAmountTotal = dtlTaxAmountTotal.add(taxAmount);
				}
			}
			dtlTaxAmountTotal = ("writeOff".equals(type)) ? dtlTaxAmountTotal.negate() : dtlTaxAmountTotal;
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, dtlTaxAmountTotal, code,
					FinanceAccount.SALE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.SALE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 更新余额表的开票金额、欠票金额、欠款金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), advanceReceivableInvoice.getFinanceAccountType(), advanceReceivableInvoice.getPurchaseUnitId(), advanceReceivableInvoice.getPurchaseUnitName());
		updateAmountInfoForReceivableOrPaymentAccount(amountTotal, financeBalance);

	}


	/**
	 * 应收发票保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param receivableInvoice
	 * @param receivableInvoiceDetailList
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void receivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(UserVO user, ReceivableInvoice receivableInvoice,
																			  List<ReceivableInvoiceDetail> receivableInvoiceDetailList, String type) throws Exception {
		BigDecimal amountTotal = receivableInvoice.getAmountTotal();
		BigDecimal notaxAmounttotal = receivableInvoice.getNotaxAmountTotal();
		String remark = "由应收发票保存后生成";
		if("writeOff".equals(type)){
			amountTotal = amountTotal.negate();
			notaxAmounttotal = notaxAmounttotal.negate();
			remark = "由应收发票冲销后生成";
		}
		Date baseOrderDate = receivableInvoice.getBillDate();
		Integer baseOrderType = receivableInvoice.getOrderType();
		Long baseOrderId = receivableInvoice.getId();
		String baseOrderCode = receivableInvoice.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = receivableInvoice.getBillManId();
		String baseOperatorCode = receivableInvoice.getBillManCode();
		String baseOperatorName = receivableInvoice.getBillManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 112201-应收账款-购货单位
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode()),
				receivableInvoice.getFinanceAccountType(), receivableInvoice.getPurchaseUnitId(), receivableInvoice.getPurchaseUnitCode(), receivableInvoice.getPurchaseUnitName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 666101-主营业务收入
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, notaxAmounttotal, code,
				FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 22210105-销项税额
		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(ReceivableInvoiceDetail receivableInvoiceDetail:receivableInvoiceDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(receivableInvoiceDetail.getTaxRateId());
			goodsTaxRate.setTaxRate(receivableInvoiceDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}
		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlTaxAmountTotal = BigDecimal.ZERO;
			for(ReceivableInvoiceDetail receivableInvoiceDetail:receivableInvoiceDetailList){
				BigDecimal taxAmount = receivableInvoiceDetail.getTaxAmount();
				if(goodsTaxRate.getId().equals(receivableInvoiceDetail.getTaxRateId())){
					dtlTaxAmountTotal = dtlTaxAmountTotal.add(taxAmount);
				}
			}
			dtlTaxAmountTotal = ("writeOff".equals(type)) ? dtlTaxAmountTotal.negate() : dtlTaxAmountTotal;
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, dtlTaxAmountTotal, code,
					FinanceAccount.SALE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.SALE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 更新余额表的开票金额、欠票金额、欠款金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), receivableInvoice.getFinanceAccountType(), receivableInvoice.getPurchaseUnitId(), receivableInvoice.getPurchaseUnitName());
		updateAmountInfoForReceivableOrPaymentAccount(amountTotal, financeBalance);
	}


	/**
	 * 应收贷项凭证保存后，对应财务余额表和凭证表影响
	 * @param user
	 * @param receivableVoucher
	 * @param receivableVoucherDetailList
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void receivableVoucherToBalanceAndVoucherWhenSaveOrWirteOff(UserVO user, ReceivableVoucher receivableVoucher,
																	   List<ReceivableVoucherDetail> receivableVoucherDetailList, String type) throws Exception {
		BigDecimal amountTotal = receivableVoucher.getAmountTotal();
		BigDecimal notaxAmountTotal = receivableVoucher.getNotaxAmountTotal();
		String remark = "";
		if("save".equals(type)){
			amountTotal = amountTotal.negate();
			notaxAmountTotal = notaxAmountTotal.negate();
			remark = "由应收贷项凭证保存后生成";
		}else{
			remark = "由应收贷项凭证冲销后生成";
		}
		Date baseOrderDate = receivableVoucher.getPostDate();
		Integer baseOrderType = receivableVoucher.getOrderType();
		Long baseOrderId = receivableVoucher.getId();
		String baseOrderCode = receivableVoucher.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = receivableVoucher.getPostManId();
		String baseOperatorCode = receivableVoucher.getPostManCode();
		String baseOperatorName = receivableVoucher.getPostManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 112201-应收账款-购货单位
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode()),
				receivableVoucher.getFinanceAccountType(), receivableVoucher.getPurchaseUnitId(), receivableVoucher.getPurchaseUnitCode(), receivableVoucher.getPurchaseUnitName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 666101-主营业务收入
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, notaxAmountTotal, code,
				FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode()),
				null, null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 22210105-销项税额
		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(ReceivableVoucherDetail receivableVoucherDetail:receivableVoucherDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(receivableVoucherDetail.getTaxRateId());
			goodsTaxRate.setTaxRate(receivableVoucherDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}
		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlTaxAmountTotal = BigDecimal.ZERO;
			for(ReceivableVoucherDetail receivableVoucherDetail:receivableVoucherDetailList){
				BigDecimal taxAmount = receivableVoucherDetail.getTaxAmount();
				if(goodsTaxRate.getId().equals(receivableVoucherDetail.getTaxRateId())){
					dtlTaxAmountTotal = dtlTaxAmountTotal.add(taxAmount);
				}
			}
			dtlTaxAmountTotal = ("save".equals(type)) ? dtlTaxAmountTotal.negate() : dtlTaxAmountTotal;
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, dtlTaxAmountTotal, code,
					FinanceAccount.SALE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.SALE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 更新余额表的开票金额、欠票金额、欠款金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), receivableVoucher.getFinanceAccountType(), receivableVoucher.getPurchaseUnitId(), receivableVoucher.getPurchaseUnitName());
		updateAmountInfoForReceivableOrPaymentAccount(amountTotal, financeBalance);

	}

	/**
	 * 销售日结保存或冲销后，对应财务余额表和凭证表影响
	 * @param user
	 * @param retailDailySettle
	 * @param saleDetailList
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void retailDailySettleToBalanceAndVoucherWhenSaveOrWriteOff(UserVO user, RetailDailySettle retailDailySettle,
																	   List<SaleDetail> saleDetailList, String type) throws Exception {
		Date baseOrderDate = retailDailySettle.getSettleDate();
		Integer baseOrderType = retailDailySettle.getOrderType();
		Long baseOrderId = retailDailySettle.getId();
		String baseOrderCode = retailDailySettle.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = retailDailySettle.getSettleManId();
		String baseOperatorCode = retailDailySettle.getSettleManCode();
		String baseOperatorName = retailDailySettle.getSettleManName();
		String remark = "由销售日结保存后生成";

		Long enterpriseId = retailDailySettle.getEnterpriseId();
		Enterprise branch = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		Enterprise enterprise = null;

		if(branch.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
			enterprise = enterpriseMapper.selectByPrimaryKey(branch.getParentId());
		}else{
			enterprise = branch;
		}

		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		// 汇总销售、销售退货明细列表的含税金额合计、不含税金额合计、税额合计
		BigDecimal amountTotal = BigDecimal.ZERO;
		BigDecimal notaxAmountTotal = BigDecimal.ZERO;

		for(SaleDetail saleDetail:saleDetailList){
			Long saleId = saleDetail.getSaleId();
			Sale sale = saleMapper.selectByPrimaryKey(saleId);
			Integer saleType = sale.getSaleType();
			BigDecimal dtlAmount = saleDetail.getAmount()==null?BigDecimal.ZERO:saleDetail.getAmount();
			BigDecimal dtlNotaxAmount = saleDetail.getNotaxRealAmount()==null?BigDecimal.ZERO:saleDetail.getNotaxRealAmount();

			if(0==saleType){
				amountTotal = amountTotal.add(dtlAmount);
				notaxAmountTotal = notaxAmountTotal.add(dtlNotaxAmount);
			}else if(1==saleType){
				amountTotal = amountTotal.subtract(dtlAmount);
				notaxAmountTotal = notaxAmountTotal.subtract(dtlNotaxAmount);
			}
		}
		if("writeOff".equals(type)){
			amountTotal = amountTotal.negate();
			notaxAmountTotal = notaxAmountTotal.negate();
			remark = "由销售日结冲销后生成";
		}

		// 640101-销售成本
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, notaxAmountTotal, code,
				FinanceAccount.SALE_COST.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.SALE_COST.getAccountCode()),
				null,null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 140601-库存商品
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, notaxAmountTotal, code,
				FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.INVENTORY_GOODS.getAccountCode()),
				null,null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 112211-应收缴款-购货单位
		buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode()),
				enterprise.getChainType(), branch.getId(), branch.getCode(), branch.getName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 666101-主营业务收入
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, notaxAmountTotal, code,
				FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode()),
				null,null, null, null,
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 22210105-销项税额
		Set<GoodsTaxRate> taxRateSet = new HashSet<GoodsTaxRate>();
		for(SaleDetail saleDetail:saleDetailList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setId(saleDetail.getTaxId());
			goodsTaxRate.setTaxRate(saleDetail.getTaxRate());
			taxRateSet.add(goodsTaxRate);
		}
		for(GoodsTaxRate goodsTaxRate:taxRateSet){
			BigDecimal dtlTaxAmountTotal = BigDecimal.ZERO;
			for(SaleDetail saleDetail:saleDetailList){
				Long saleId = saleDetail.getSaleId();
				Sale sale = saleMapper.selectByPrimaryKey(saleId);
				Integer saleType = sale.getSaleType();
				BigDecimal taxAmount = saleDetail.getTaxAmount()==null?BigDecimal.ZERO:saleDetail.getTaxAmount();
				if(goodsTaxRate.getId().equals(saleDetail.getTaxId())){
					if(0==saleType){
						dtlTaxAmountTotal = dtlTaxAmountTotal.add(taxAmount);
					}else if(1==saleType){
						dtlTaxAmountTotal = dtlTaxAmountTotal.subtract(taxAmount);
					}
				}
			}
			dtlTaxAmountTotal = ("writeOff".equals(type)) ? dtlTaxAmountTotal.negate() : dtlTaxAmountTotal;
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, dtlTaxAmountTotal, code,
					FinanceAccount.SALE_TAX_AMOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.SALE_TAX_AMOUNT.getAccountCode()),
					FinanceAccountType.TAX.getType(), goodsTaxRate.getId(), null, String.valueOf(goodsTaxRate.getTaxRate()),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 更新余额表的开票金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), enterprise.getChainType(), branch.getId(), branch.getName());
		if(financeBalance != null){
			// 开票金额
			financeBalance.setBillAmount(financeBalance.getBillAmount().add(amountTotal));
			financeBalanceMapper.updateByPrimaryKeySelective(financeBalance);
		}

	}

	/**
	 * 零售缴款保存或冲销后，对应财务余额表和凭证表影响
	 * @param user
	 * @param retailPayment
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void retailPaymentToBalanceAndVoucherWhenSaveOrWriteOff(UserVO user, RetailPayment retailPayment, String type) throws Exception {
		// 实缴现金合计
		BigDecimal realCashTotal = retailPayment.getRealCashTotal()==null?BigDecimal.ZERO:retailPayment.getRealCashTotal();
		BigDecimal bankAmountTotal = retailPayment.getBankAmountTotal()==null?BigDecimal.ZERO:retailPayment.getBankAmountTotal();
		BigDecimal otherAmountTotal = retailPayment.getOtherAmountTotal()==null?BigDecimal.ZERO:retailPayment.getOtherAmountTotal();
		BigDecimal amountTotal = realCashTotal.add(bankAmountTotal).add(otherAmountTotal);

		String remark = "";
		if("writeOff".equals(type)){
			realCashTotal = realCashTotal.negate();
			bankAmountTotal = bankAmountTotal.negate();
			otherAmountTotal = otherAmountTotal.negate();
			remark = "由零售缴款冲销后生成";
		}else{
			remark = "由零售缴款保存后生成";
		}
		Date baseOrderDate = retailPayment.getPaymentDate();
		Integer baseOrderType = retailPayment.getOrderType();
		Long baseOrderId = retailPayment.getId();
		String baseOrderCode = retailPayment.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = retailPayment.getPaymentManId();
		String baseOperatorCode = retailPayment.getPaymentManCode();
		String baseOperatorName = retailPayment.getPaymentManName();

		Long enterpriseId = retailPayment.getEnterpriseId();
		Enterprise branch = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		Enterprise enterprise = null;
		if(branch.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
			enterprise = enterpriseMapper.selectByPrimaryKey(branch.getParentId());
		}else{
			enterprise = branch;
		}
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		if(realCashTotal.compareTo(BigDecimal.ZERO) != 0){
			// 100101-现金-人民币
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, realCashTotal, code,
					FinanceAccount.CASH_RMB.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.CASH_RMB.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		if(bankAmountTotal.compareTo(BigDecimal.ZERO) != 0){
			// 100201-银行存款-人民币
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, bankAmountTotal, code,
					FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		if(otherAmountTotal.compareTo(BigDecimal.ZERO) != 0){
			// 101211-其它货币资金
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, otherAmountTotal, code,
					FinanceAccount.OTHER_MONETARY_FUNDS.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.OTHER_MONETARY_FUNDS.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		// 112211-应收缴款-购货单位
		buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, amountTotal, code,
				FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode()),
				enterprise.getChainType(), branch.getId(), branch.getCode(), branch.getName(),
				baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
				baseOperatorId, baseOperatorCode, baseOperatorName, remark);

		// 更新余额表的收付款金额
		FinanceBalance financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
				FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), enterprise.getChainType(), branch.getId(), branch.getName());
		if(financeBalance != null){
			// 收付款金额
			financeBalance.setReceivePayAmount(financeBalance.getReceivePayAmount().add(amountTotal));
			financeBalanceMapper.updateByPrimaryKeySelective(financeBalance);
		}
	}

	/**
	 * 财务付款保存或冲销后，对应财务余额表和凭证表影响
	 * @param user
	 * @param financePayment
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void financePaymentToBalanceAndVoucherWhenSaveOrWriteOff(UserVO user, FinancePayment financePayment, String type) throws Exception {
		// 实付金额合计
		BigDecimal realAmountTotal = financePayment.getRealAmountTotal()==null?BigDecimal.ZERO:financePayment.getRealAmountTotal();
		// 优惠金额合计
		BigDecimal discountAmountTotal = financePayment.getDiscountAmountTotal()==null?BigDecimal.ZERO:financePayment.getDiscountAmountTotal();
		realAmountTotal = realAmountTotal.add(discountAmountTotal);
		// 银行支付金额合计
		BigDecimal bankAmountTotal = financePayment.getBankAmountTotal()==null?BigDecimal.ZERO:financePayment.getBankAmountTotal();
		// 现金支付金额合计
		BigDecimal cashAmountTotal = financePayment.getCashAmountTotal()==null?BigDecimal.ZERO:financePayment.getCashAmountTotal();
		Integer companyType = financePayment.getCompanyType();

		String remark = "";
		if("writeOff".equals(type)){
			realAmountTotal = realAmountTotal.negate();
			bankAmountTotal = bankAmountTotal.negate();
			cashAmountTotal = cashAmountTotal.negate();
			remark = "由财务付款单冲销后生成";
		}else{
			remark = "由财务付款单保存后生成";
		}
		Date baseOrderDate = financePayment.getPaymentDate();
		Integer baseOrderType = financePayment.getOrderType();
		Long baseOrderId = financePayment.getId();
		String baseOrderCode = financePayment.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = financePayment.getPaymentManId();
		String baseOperatorCode = financePayment.getPaymentManCode();
		String baseOperatorName = financePayment.getPaymentManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		FinanceBalance financeBalance = null;
		if(CompanyType.SUPPLIER.getCode() == companyType){
			// 220201-应付账款-供货单位
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, realAmountTotal, code,
					FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PAYMENT_ACCOUNT.getAccountCode()),
					financePayment.getFinanceAccountType(), financePayment.getCompanyId(), financePayment.getCompanyCode(), financePayment.getCompanyName(),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
					FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), financePayment.getFinanceAccountType(), financePayment.getCompanyId(), financePayment.getCompanyName());
		}else{
			// 112201-应收账款-购货单位
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, realAmountTotal, code,
					FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode()),
					financePayment.getFinanceAccountType(), financePayment.getCompanyId(), financePayment.getCompanyCode(), financePayment.getCompanyName(),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
					FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), financePayment.getFinanceAccountType(), financePayment.getCompanyId(), financePayment.getCompanyName());
		}
		if(bankAmountTotal.compareTo(BigDecimal.ZERO) != 0){
			// 100201-银行存款-人民币
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, bankAmountTotal, code,
					FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		if(cashAmountTotal.compareTo(BigDecimal.ZERO) != 0){
			// 100101-现金-人民币
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, cashAmountTotal, code,
					FinanceAccount.CASH_RMB.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.CASH_RMB.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		if(discountAmountTotal.compareTo(BigDecimal.ZERO) !=0){
			// 660305-财务费用-现金折扣
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, discountAmountTotal, code,
					FinanceAccount.CASH_DISCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.CASH_DISCOUNT.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		// 更新余额表的收付款金额
		if(financeBalance != null){
			// 收付款金额
			financeBalance.setReceivePayAmount(financeBalance.getReceivePayAmount().add(realAmountTotal));
			// 欠款金额=开票金额-收付款金额
			financeBalance.setOweAmount(financeBalance.getBillAmount().subtract(financeBalance.getReceivePayAmount()));
			financeBalanceMapper.updateByPrimaryKeySelective(financeBalance);
		}

	}


	/**
	 * 财务收款保存或冲销后，对应财务余额表和凭证表影响
	 * @param user
	 * @param financeReceivable
	 * @param type save：保存；writeOff：冲销
	 * @throws Exception
	 */
	public void financeReceivableToBalanceAndVoucherWhenSaveOrWriteOff(UserVO user, FinanceReceivable financeReceivable, String type) throws Exception {
		// 实付金额合计
		BigDecimal realAmountTotal = financeReceivable.getRealAmountTotal()==null?BigDecimal.ZERO:financeReceivable.getRealAmountTotal();
		// 优惠金额合计
		BigDecimal discountAmountTotal = financeReceivable.getDiscountAmountTotal()==null?BigDecimal.ZERO:financeReceivable.getDiscountAmountTotal();
		realAmountTotal = realAmountTotal.add(discountAmountTotal);
		// 银行支付金额合计
		BigDecimal bankAmountTotal = financeReceivable.getBankAmountTotal()==null?BigDecimal.ZERO:financeReceivable.getBankAmountTotal();
		// 现金支付金额合计
		BigDecimal cashAmountTotal = financeReceivable.getCashAmountTotal()==null?BigDecimal.ZERO:financeReceivable.getCashAmountTotal();
		Integer companyType = financeReceivable.getCompanyType();

		String remark = "";
		if("writeOff".equals(type)){
			realAmountTotal = realAmountTotal.negate();
			bankAmountTotal = bankAmountTotal.negate();
			cashAmountTotal = cashAmountTotal.negate();
			remark = "由财务收款单冲销后生成";
		}else{
			remark = "由财务收款单保存后生成";
		}
		Date baseOrderDate = financeReceivable.getReceivableDate();
		Integer baseOrderType = financeReceivable.getOrderType();
		Long baseOrderId = financeReceivable.getId();
		String baseOrderCode = financeReceivable.getCode();
		Long baseDtlId = null;
		Long baseOperatorId = financeReceivable.getReceivableManId();
		String baseOperatorCode = financeReceivable.getReceivableManCode();
		String baseOperatorName = financeReceivable.getReceivableManName();

		Enterprise enterprise = buildEnterprise(user);
		String code = orderCodeComponent.generate(OrderRule.FINANCE_VOUCHER.getCodePrefix(),enterprise.getId(),enterprise.getCode());

		if(bankAmountTotal.compareTo(BigDecimal.ZERO) !=0){
			// 100201-银行存款-人民币
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, bankAmountTotal, code,
					FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		if(cashAmountTotal.compareTo(BigDecimal.ZERO) !=0){
			// 100101-现金-人民币
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, cashAmountTotal, code,
					FinanceAccount.CASH_RMB.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.CASH_RMB.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}
		if(discountAmountTotal.compareTo(BigDecimal.ZERO) != 0){
			// 660305-财务费用-现金折扣
			buildAndInsertFinanceVoucher(FinanceDirection.DEBIT.getDirection(), user, enterprise, discountAmountTotal, code,
					FinanceAccount.CASH_DISCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.CASH_DISCOUNT.getAccountCode()),
					null,null, null, null,
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);
		}

		FinanceBalance financeBalance = null;

		if(CompanyType.SUPPLIER.getCode() == companyType){
			// 220201-应付账款-供货单位
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, realAmountTotal, code,
					FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.PAYMENT_ACCOUNT.getAccountCode()),
					financeReceivable.getFinanceAccountType(), financeReceivable.getCompanyId(), financeReceivable.getCompanyCode(), financeReceivable.getCompanyName(),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
					FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), financeReceivable.getFinanceAccountType(), financeReceivable.getCompanyId(), financeReceivable.getCompanyName());
		}else{
			// 112201-应收账款-购货单位
			buildAndInsertFinanceVoucher(FinanceDirection.CREDIT.getDirection(), user, enterprise, realAmountTotal, code,
					FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), FinanceAccount.getAccountName(FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode()),
					financeReceivable.getFinanceAccountType(),financeReceivable.getCompanyId(), financeReceivable.getCompanyCode(), financeReceivable.getCompanyName(),
					baseOrderDate, baseOrderType, baseOrderId, baseOrderCode, baseDtlId,
					baseOperatorId, baseOperatorCode, baseOperatorName, remark);

			financeBalance = getFinanceBalance(enterprise.getId(),enterprise.getParentId(),
					FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), financeReceivable.getFinanceAccountType(), financeReceivable.getCompanyId(), financeReceivable.getCompanyName());
		}

		// 更新余额表的收付款金额
		if(financeBalance != null){
			// 收付款金额
			financeBalance.setReceivePayAmount(financeBalance.getReceivePayAmount().add(realAmountTotal));
			// 欠款金额=开票金额-收付款金额
			financeBalance.setOweAmount(financeBalance.getBillAmount().subtract(financeBalance.getReceivePayAmount()));
			financeBalanceMapper.updateByPrimaryKeySelective(financeBalance);
		}

	}


	public static void main(String[] args){
		BigDecimal val = new BigDecimal(-3);
		val = val.abs();
		System.out.println(val);
	}

}
