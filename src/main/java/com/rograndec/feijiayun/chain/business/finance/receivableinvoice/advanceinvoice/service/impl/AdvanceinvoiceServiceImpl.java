package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceAccountDetailParamVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceAccountParamVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceAccountMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceInfoMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceAccount;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceInfo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.service.IAdvanceinvoiceService;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReceivableInvoiceAccountDetailVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReceivableInvoiceDetailListVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReceivableInvoiceDetailVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReceivableInvoiceInfoVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableInvoiceGoodsVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableInvoiceListVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableinvoiceSelectVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableinvoiceVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceDistrOutShelfVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceListrequestVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceUpdateVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.ReveivableInvoiceListParamVo;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.vo.JoinGoodsRequestVo;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.AdvanceInvoiceStatus;
import com.rograndec.feijiayun.chain.common.constant.AdvanceInvoiceType;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrOutStatus;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.constant.status.FinanceReconciliationStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
@Service
public class AdvanceinvoiceServiceImpl implements IAdvanceinvoiceService{
	@Autowired
	private AdvanceReceivableInvoiceMapper advanceReceivableInvoiceMapper;
	@Autowired
	private AdvanceReceivableInvoiceDetailMapper advanceReceivableInvoiceDetailMapper;
	@Autowired
	private AdvanceReceivableInvoiceInfoMapper advanceReceivableInvoiceInfoMapper;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private DistrOutDetailMapper distrOutDetailMapper;
    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;
    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;
    @Autowired
    private DistrOutMapper distrOutMapper;
    @Autowired
    private AdvanceReceivableInvoiceAccountMapper advanceReceivableInvoiceAccountMapper;
    @Autowired
    private AdvanceReceivableInvoiceModifyRecordMapper advanceReceivableInvoiceModifyRecordMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    FinanceComponent financeComponent;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsTaxRateMapper goodsTaxRateMapper;
	@Override
	@Transactional
	public String save(UserVO loginUser, AdvanceReveivableinvoiceVo advanceReveivableinvoiceVo) throws Exception {
		//对主表插入数据
		AdvanceReceivableInvoice advanceReceivableInvoice=new AdvanceReceivableInvoice();
		BeanUtils.copyProperties(advanceReveivableinvoiceVo, advanceReceivableInvoice);
		UserEnterpriseUtils.setUserCreateOrModify(advanceReceivableInvoice, loginUser, true);
		advanceReceivableInvoice.setEnterpriseId(loginUser.getEnterpriseId());
		advanceReceivableInvoice.setParentId(loginUser.getParentId());
		//初始化字段
		advanceReceivableInvoice.setOrderType(OrderRule.ADVANCE_RECEIVABLE_INVOICE.getType());
		if(StringUtils.isEmpty(advanceReceivableInvoice.getCode())) {
			advanceReceivableInvoice.setCode(this.getCode(loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));			
		}
		advanceReceivableInvoice.setClearAmountTotal(BigDecimal.ZERO);
		advanceReceivableInvoice.setUnclearAmountTotal(advanceReceivableInvoice.getAmountTotal());
		advanceReceivableInvoice.setFinanceAccountType(FinanceAccountType.LEAGUE.getType());
		advanceReceivableInvoice.setStatus(AdvanceInvoiceStatus.WAIT_RECEIVE.getCode());
		advanceReceivableInvoice.setAccountStatus(FinanceReconciliationStatus.PRE_RECONCILIATION);
		//查询开票人员信息
		User user = userMapper.selectByPrimaryKey(advanceReveivableinvoiceVo.getBillManId());
		advanceReceivableInvoice.setBillManCode(user.getCode());
		advanceReceivableInvoice.setBillManName(user.getName());
		advanceReceivableInvoice.setVarietiesQuantity(advanceReveivableinvoiceVo.getAridvList().size());
		advanceReceivableInvoiceMapper.insert(advanceReceivableInvoice);
		String code=advanceReceivableInvoice.getCode();
		//插入预收发票明细表
		List<AdvanceReceivableInvoiceDetailVo> aridvList = advanceReveivableinvoiceVo.getAridvList();
		if(aridvList!=null) {
			for(AdvanceReceivableInvoiceDetailVo av:aridvList) {
				//判断明细商品和加盟商是否对应
				if((!av.getOwnerId().equals(advanceReveivableinvoiceVo.getPurchaseUnitId()))
						&&(!av.getOwnerId().equals(loginUser.getEnterpriseId()))) {
					throw new BusinessException("商品:"+av.getGoodsName()+"不属于加盟店和总店");
				}
				AdvanceReceivableInvoiceDetail ard=new AdvanceReceivableInvoiceDetail();
				GoodsTaxRate tax = goodsTaxRateMapper.selectByPrimaryKey(av.getTaxRateId());
				//根据税率算				
				av.setTaxRate(tax.getTaxRate());
				if(av.getUnitPrice().compareTo(BigDecimal.ZERO)<=0) {
					throw new BusinessException("商品单价不能小于0");
				}
				BigDecimal notaxPrice=(av.getUnitPrice()).divide(BigDecimal.ONE.add(tax.getTaxRate().divide(BigDecimal.valueOf(100L),2, RoundingMode.HALF_UP)),2,RoundingMode.HALF_UP);
				av.setNotaxPrice(notaxPrice);
				BigDecimal amount=av.getQuantity().multiply(av.getUnitPrice());
				BigDecimal notaxAmount=av.getQuantity().multiply(notaxPrice);
				BigDecimal taxAmount=amount.subtract(notaxAmount);
				av.setAmount(amount);
				av.setNotaxAmount(notaxAmount);
				av.setTaxAmount(taxAmount);
				
				BeanUtils.copyProperties(av, ard);
				UserEnterpriseUtils.setUserCreateOrModify(ard, loginUser, true);
				ard.setInvoiceId(advanceReceivableInvoice.getId());
				ard.setEnterpriseId(loginUser.getEnterpriseId());
				ard.setParentId(loginUser.getParentId());
/*				ard.setClearQuantity(BigDecimal.ZERO);
				ard.setUnclearQuantity(ard.getQuantity());*/
				ard.setUnaccountQuantity(ard.getQuantity());
				ard.setAccountQuantity(BigDecimal.ZERO);
				ard.setStatus(FinancePaymentStatus.PRE_PAYMENT);
				ard.setAccountStatus(FinanceReconciliationStatus.PRE_RECONCILIATION);
				ard.setDiffAmount(BigDecimal.ZERO);
				ard.setDiffNotaxAmount(BigDecimal.ZERO);
				ard.setDiffTaxAmount(BigDecimal.ZERO);
				advanceReceivableInvoiceDetailMapper.insert(ard);
			}
		}
		//插入开票信息
		AdvanceReceivableInvoiceInfoVo ariv = advanceReveivableinvoiceVo.getAdvanceReceivableInvoiceInfoVo();
		AdvanceReceivableInvoiceInfo advanceReceivableInvoiceInfo=new AdvanceReceivableInvoiceInfo();
		BeanUtils.copyProperties(ariv, advanceReceivableInvoiceInfo);
		advanceReceivableInvoiceInfo.setInvoiceId(advanceReceivableInvoice.getId());
		advanceReceivableInvoiceInfo.setEnterpriseId(loginUser.getEnterpriseId());
		advanceReceivableInvoiceInfo.setParentId(loginUser.getParentId());
		advanceReceivableInvoiceInfoMapper.insert(advanceReceivableInvoiceInfo);		
		//调用中义接口
		financeComponent.advanceReceivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(
				loginUser, advanceReceivableInvoice, 
				advanceReceivableInvoiceDetailMapper.selectByInvoiceId(advanceReceivableInvoice.getId()),
				"save");
		//删除草稿
		if(!StringUtils.isEmpty(advanceReveivableinvoiceVo.getRedisKeyValue())) {
			this.removeDraftCach(advanceReveivableinvoiceVo.getPurchaseUnitId(),loginUser.getEnterpriseId(), OrderRule.ADVANCE_RECEIVABLE_INVOICE.getCodePrefix(),advanceReveivableinvoiceVo.getRedisKeyValue());
		}
		return code;
	}
	//获取采购验收单号
    private String getCode(Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(OrderRule.ADVANCE_RECEIVABLE_INVOICE.getCodePrefix(), enterpriseId, enterpriseCode);
    }
	@Override
	public DraftCacheVO saveDraft(UserVO userVO, DraftCacheVO<AdvanceReveivableinvoiceVo> draftCache) throws Exception {
		/*if(StringUtils.isEmpty(draftCache.getOrderData().getCode())) {
			draftCache.getOrderData().setCode(this.getCode(userVO.getEnterpriseId(), userVO.getEnterpriseCode()));			
		}
		   String redisKeyValue = draftCache.getId();
	       DraftCacheVO<AdvanceReveivableinvoiceVo> draftCacheVO = new DraftCacheVO();
	       draftCacheVO.setOrderCode(OrderRule.ADVANCE_RECEIVABLE_INVOICE.getCodePrefix());
	       draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
	       draftCacheVO.setOrderData(draftCache.getOrderData());
	       draftCacheVO.setId(redisKeyValue);
	       return redisComponent.saveDraftCacheVO(draftCacheVO);*/
		draftCache.setOrderCode(OrderRule.ADVANCE_RECEIVABLE_INVOICE.getCodePrefix());

        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);

        return draftCache;
	}
	@Override
    public void removeDraftCach(Long supplierId,Long enterpriseId, String type, String redisKeyValue) throws Exception {
        redisComponent.removeDraftCacheVO(supplierId,enterpriseId,type,redisKeyValue);
    }
	@Override
    public List<DraftCacheVO> getDraftCacheVO(Long supplierId,UserVO userVO) throws Exception {
/*        Class<AdvanceReveivableinvoiceVo> clazz = AdvanceReveivableinvoiceVo.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.ADVANCE_RECEIVABLE_INVOICE.getCodePrefix(),clazz);*/
		DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.ADVANCE_RECEIVABLE_INVOICE.getCodePrefix());
        draftCacheVO.setSupplierId(supplierId);
        return redisComponent.getDraftCacheVO(draftCacheVO);
	}
	@Override
	public AdvanceReceivableInvoice selectReveivableinvoiceAdd(Long invoiceId) {
		if(invoiceId==null) {
			throw new BusinessException("发票id不能为空");
		}
		AdvanceReceivableInvoice advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(invoiceId);
		if(advanceReceivableInvoice==null) {
			throw new BusinessException("发票id数据不存在");
		}
		AdvanceReceivableInvoiceInfo advanceReceivableInvoiceInfo = advanceReceivableInvoiceInfoMapper.selectByInvoiceId(invoiceId);
		if(advanceReceivableInvoiceInfo==null) {
			throw new BusinessException("开票信息数据不存在");
		}
		List<AdvanceReceivableInvoiceDetail> list = advanceReceivableInvoiceDetailMapper.selectByInvoiceId(invoiceId);
		if(list==null||list.size()==0) {
			throw new BusinessException("发票详情数据不存在");
		}
		advanceReceivableInvoice.setAridvList(list);
		advanceReceivableInvoice.setAdvanceReceivableInvoiceInfoVo(advanceReceivableInvoiceInfo);
		return advanceReceivableInvoice;
	}
	@Override
	public AdvanceReveivableinvoiceSelectVo selectReveivableinvoice(Long invoiceId) {
		AdvanceReveivableinvoiceSelectVo advanceReveivableinvoiceSelectVo=new AdvanceReveivableinvoiceSelectVo();
		if(invoiceId==null) {
			throw new BusinessException("发票id不能为空");
		}
		AdvanceReceivableInvoice advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(invoiceId);
		advanceReveivableinvoiceSelectVo.setAdvanceReceivableInvoice(advanceReceivableInvoice);
		if(advanceReceivableInvoice==null) {
			throw new BusinessException("发票id数据不存在");
		}
		AdvanceReceivableInvoiceInfo advanceReceivableInvoiceInfo = advanceReceivableInvoiceInfoMapper.selectByInvoiceId(invoiceId);
		if(advanceReceivableInvoiceInfo==null) {
			throw new BusinessException("开票信息数据不存在");
		}
		advanceReveivableinvoiceSelectVo.setAdvanceReceivableInvoiceInfo(advanceReceivableInvoiceInfo);
		List<AdvanceReceivableInvoiceDetail> list = advanceReceivableInvoiceDetailMapper.selectByInvoiceId(invoiceId);
		if(list==null||list.size()==0) {
			throw new BusinessException("发票详情数据不存在");
		}
		AdvanceReceivableInvoiceDetailListVo aridlv=new AdvanceReceivableInvoiceDetailListVo();
		aridlv.setArbidList(list);
		aridlv.setTotalCount(list.size());
		//初始化数据
		BigDecimal totalNum=BigDecimal.ZERO;
		BigDecimal totalMoney=BigDecimal.ZERO;
		BigDecimal totalMoneyOutTax=BigDecimal.ZERO;
		BigDecimal totalTax=BigDecimal.ZERO;
//	    BigDecimal totalClearQuantity=BigDecimal.ZERO;
//	    BigDecimal totalUnclearQuantity=BigDecimal.ZERO;
		BigDecimal totalDiffAmount=BigDecimal.ZERO;
		BigDecimal totalDiffNotaxAmount=BigDecimal.ZERO;
		BigDecimal totalDiffTaxAmount=BigDecimal.ZERO;
		
		BigDecimal accountQuantity=BigDecimal.ZERO;
		BigDecimal unaccountQuantity=BigDecimal.ZERO;
		//统计数据
		for(AdvanceReceivableInvoiceDetail a:list) {
			totalMoney=totalMoney.add(a.getAmount());
			totalMoneyOutTax=totalMoneyOutTax.add(a.getNotaxAmount());
			totalTax=totalTax.add(a.getTaxAmount());				
			totalNum=totalNum.add(a.getQuantity());
//			totalClearQuantity=totalClearQuantity.add(a.getClearQuantity());
//			totalUnclearQuantity=totalUnclearQuantity.add(a.getUnclearQuantity());
			totalDiffAmount=totalDiffAmount.add(a.getDiffAmount());
			totalDiffNotaxAmount=totalDiffNotaxAmount.add(a.getDiffNotaxAmount());
			totalDiffTaxAmount=totalDiffTaxAmount.add(a.getDiffTaxAmount());
			accountQuantity=accountQuantity.add(a.getAccountQuantity());
			unaccountQuantity=unaccountQuantity.add(a.getUnaccountQuantity());
		}
		aridlv.setTotalMoney(totalMoney);
		aridlv.setTotalMoneyOutTax(totalMoneyOutTax);
		aridlv.setTotalTax(totalTax);
		aridlv.setTotalNum(totalNum);
//		aridlv.setTotalClearQuantity(totalClearQuantity);
//		aridlv.setTotalUnclearQuantity(totalUnclearQuantity);
		aridlv.setTotalDiffAmount(totalDiffAmount);
		aridlv.setTotalDiffNotaxAmount(totalDiffNotaxAmount);
		aridlv.setTotalDiffTaxAmount(totalDiffTaxAmount);
		aridlv.setAccountQuantity(unaccountQuantity);
		aridlv.setUnaccountQuantity(unaccountQuantity);
		
		advanceReveivableinvoiceSelectVo.setAdvanceReceivableInvoiceDetailListVo(aridlv);
		return advanceReveivableinvoiceSelectVo;
	}
	@Override
	public ReveivableInvoiceListParamVo selectReveivableinvoiceList(Page<ReveivableInvoiceListParamVo> page,
			InvoiceListrequestVo invoiceListrequestVo) {
//		Map<String,Object> map=new HashMap<String,Object>();
		ReveivableInvoiceListParamVo reveivableInvoiceListParamVo=new ReveivableInvoiceListParamVo();
		List<AdvanceReveivableInvoiceListVo> returnList=new ArrayList<AdvanceReveivableInvoiceListVo>();
		List<AdvanceReceivableInvoice> selectAdvanceReceivableInvoiceList = advanceReceivableInvoiceMapper.
				selectAdvanceReceivableInvoiceList(invoiceListrequestVo,page.getStart(), page.getPageSize());
		Long count = advanceReceivableInvoiceMapper.selectAdvanceReceivableInvoiceListCount(invoiceListrequestVo);
		if(selectAdvanceReceivableInvoiceList!=null&&selectAdvanceReceivableInvoiceList.size()!=0) {
			//初始化数据			
			BigDecimal totalMoney=BigDecimal.ZERO;
		    BigDecimal totalMoneyOutTax=BigDecimal.ZERO;
		    BigDecimal totalTax=BigDecimal.ZERO;
		    //附加额外值
			for(AdvanceReceivableInvoice a:selectAdvanceReceivableInvoiceList) {
				AdvanceReveivableInvoiceListVo arilv=new AdvanceReveivableInvoiceListVo();
				BeanUtils.copyProperties(a, arilv);
				arilv.setStatusName(AdvanceInvoiceStatus.getName(arilv.getStatus()));
				arilv.setAccountStatusName(FinanceReconciliationStatus.getStatusDesc(arilv.getAccountStatus()));
				totalMoney=totalMoney.add(arilv.getAmountTotal());
				totalMoneyOutTax=totalMoneyOutTax.add(arilv.getNotaxAmountTotal());
				totalTax=totalTax.add(arilv.getTaxAmountTotal());	
				arilv.setAccount(this.isAccount(a));
				arilv.setUpdate(this.isUpdate(a));
				arilv.setCancel(this.isCancel(a));
				returnList.add(arilv);
			}
		/*	map.put("totalMoney", totalMoney);
			map.put("totalMoneyOutTax", totalMoneyOutTax);
			map.put("totalTax", totalTax);*/
			reveivableInvoiceListParamVo.setTotalMoney(totalMoney);
			reveivableInvoiceListParamVo.setTotalMoneyOutTax(totalMoneyOutTax);
			reveivableInvoiceListParamVo.setTotalTax(totalTax);
		}
//		map.put("list", returnList);
		reveivableInvoiceListParamVo.setList(returnList);
		page.setTotalRecord(count.intValue());
		return reveivableInvoiceListParamVo;
	}
	/**
	 * 是否可以对账
	 * @return
	 */
	private boolean isAccount(AdvanceReceivableInvoice advanceReceivableInvoice) {
		boolean flag=false;
		if(FinanceReconciliationStatus.PRE_RECONCILIATION==advanceReceivableInvoice.getAccountStatus()
				||FinanceReconciliationStatus.PARTIAL_RECONCILIATION==advanceReceivableInvoice.getAccountStatus()) {
			flag=true;
		}
		return flag;
		
	}
	/**
	 * 是否可以更新
	 * @return
	 */
	private boolean isUpdate(AdvanceReceivableInvoice advanceReceivableInvoice) {
		boolean flag=true;
		if(FinanceReconciliationStatus.WARITE_OF==advanceReceivableInvoice.getAccountStatus())
		 {
			flag=false;
		}
		return flag;
		
	}
	/**
	 * 是否可以冲销
	 * @return
	 */
	private boolean isCancel(AdvanceReceivableInvoice advanceReceivableInvoice) {
		boolean flag=false;
		if(FinancePaymentStatus.PRE_PAYMENT==advanceReceivableInvoice.getStatus()) {
			flag=true;
		}
		return flag;	
	}
	@Override
	public List<AdvanceReveivableInvoiceGoodsVo> selectJoinGoodsList(Page<Object> page,JoinGoodsRequestVo joinGoodsRequestVo) {
		if(StringUtils.isEmpty(joinGoodsRequestVo.getCommonParam())) {
			joinGoodsRequestVo.setCommonParam(null);
		}
		List<Goods> joinGoodsList = goodsMapper.selectJoinGoodsList(joinGoodsRequestVo,page.getStart() , page.getPageSize());
		List<AdvanceReveivableInvoiceGoodsVo> list=new ArrayList<AdvanceReveivableInvoiceGoodsVo>();
		if(joinGoodsList!=null&&joinGoodsList.size()>0) {
			for(Goods g:joinGoodsList) {
				AdvanceReveivableInvoiceGoodsVo arigv=new AdvanceReveivableInvoiceGoodsVo();
				BeanUtils.copyProperties(g, arigv);
				//调用最近出库点获取默认值
				arigv.setQuantity(BigDecimal.ONE);//需求初始化数量为1
				DistrOutDetail distrOutDetail = distrOutDetailMapper.selectByEnterpriseIdGoodsID(joinGoodsRequestVo.getEnterpriseId(), g.getId());
				if(distrOutDetail!=null) {
					arigv.setUnitPrice(distrOutDetail.getUnitPrice());
					arigv.setNotaxPrice(distrOutDetail.getNotaxRealPrice());
					arigv.setTaxRate(distrOutDetail.getTaxRate());
					arigv.setTaxRateId(distrOutDetail.getTaxRateId());
				}else {
					arigv.setUnitPrice(BigDecimal.ZERO);
					arigv.setNotaxPrice(BigDecimal.ZERO);
					//无最近入库默认价格清单中的配货税率
					PriceOrderDetail orderDetail = priceOrderDetailMapper.getEnterpriseDefault(g.getId(), g.getOwnerId());
					if(orderDetail!=null) {
						arigv.setTaxRate(orderDetail.getDistrTaxRate());
						arigv.setTaxRateId(orderDetail.getDistrTaxRateId());
					}else {
						throw new BusinessException("未查找到默认价格清单数据");
					}
				}
				//计算默认金额
				BigDecimal amount=arigv.getUnitPrice().multiply(arigv.getQuantity());
				//不含锐金额
				BigDecimal notaxAmount=arigv.getNotaxPrice().multiply(arigv.getQuantity());
				//税额
				BigDecimal taxAmount=amount.subtract(notaxAmount);
				arigv.setAmount(amount);
				arigv.setTaxAmount(taxAmount);
				arigv.setNotaxAmount(notaxAmount);
				//配合前端添加冗余字段
				arigv.setNotaxRealPrice(arigv.getNotaxPrice());
				arigv.setNotaxRealAmount(arigv.getNotaxAmount());
				arigv.setGoodsCode(arigv.getCode());
				arigv.setGoodsGenericName(arigv.getGenericName());
				arigv.setGoodsId(arigv.getId());
				arigv.setGoodsPlace(arigv.getPlace());
				arigv.setGoodsSpecification(arigv.getSpecification());
				arigv.setGoodsName(arigv.getName());
				list.add(arigv);
			}
		}
		//pageSize,pageNo==0为不分页
		if(!page.getPageSize().equals(0)) {
			Long count = goodsMapper.selectJoinGoodsListCount(joinGoodsRequestVo);
			page.setTotalRecord(count.intValue());					
		}
		return list;
	}
	@Override
	public List<AdvanceReceivableInvoiceAccountDetailVo> selectReveivableInvoiceAccountDetail(Long invoiceId) {
		List<AdvanceReceivableInvoiceAccountDetailVo> returnList=new ArrayList<AdvanceReceivableInvoiceAccountDetailVo>();
		if(invoiceId==null) {
			throw new BusinessException("发票id不能为空");
		}
		AdvanceReceivableInvoice advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(invoiceId);
		if(advanceReceivableInvoice==null) {
			throw new BusinessException("发票id数据不存在");
		}
		List<AdvanceReceivableInvoiceDetail> list = advanceReceivableInvoiceDetailMapper.selectByInvoiceId(invoiceId);
		if(list==null||list.size()==0) {
			throw new BusinessException("发票详情数据不存在");
		}
		//获取配货出库数据
		for(AdvanceReceivableInvoiceDetail a:list) {
			AdvanceReceivableInvoiceAccountDetailVo arbiadv=new AdvanceReceivableInvoiceAccountDetailVo();
			BeanUtils.copyProperties(a, arbiadv);
			//配合前端添加冗余字段
			arbiadv.setUnclearQuantity(arbiadv.getUnaccountQuantity());
			arbiadv.setClearQuantity(arbiadv.getAccountQuantity());
			arbiadv.setTimeCheckAccountQty(BigDecimal.ZERO);
			List<InvoiceDistrOutShelfVo> invoiceDistrOutShelfList = distrOutShelfMapper.getInvoiceDistrOutShelfList(advanceReceivableInvoice.getPurchaseUnitId(), a.getGoodsId());
			if(invoiceDistrOutShelfList!=null) {
				for(InvoiceDistrOutShelfVo i:invoiceDistrOutShelfList) {
					//配合前端添加冗余字段
					i.setCode(i.getBaseOrderCode());
					i.setValidUntil(i.getValidDate());
					i.setAccountQuantity(BigDecimal.ZERO);	
					i.setOrderShelfId(i.getBaseShelfDtlId());
				}
			}
			arbiadv.setShelfList(invoiceDistrOutShelfList);
			returnList.add(arbiadv);
		}
		return returnList;
	}
	@Override
	public List<AdvanceReceivableInvoiceAccountDetailVo> autoAccount(Long invoiceId) {
		List<AdvanceReceivableInvoiceAccountDetailVo> returnList=new ArrayList<AdvanceReceivableInvoiceAccountDetailVo>();
		if(invoiceId==null) {
			throw new BusinessException("发票id不能为空");
		}
		AdvanceReceivableInvoice advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(invoiceId);
		if(advanceReceivableInvoice==null) {
			throw new BusinessException("发票id数据不存在");
		}
		List<AdvanceReceivableInvoiceDetail> list = advanceReceivableInvoiceDetailMapper.selectByInvoiceId(invoiceId);
		if(list==null||list.size()==0) {
			throw new BusinessException("发票详情数据不存在");
		}
		//获取配货出库数据
		for(AdvanceReceivableInvoiceDetail a:list) {
			AdvanceReceivableInvoiceAccountDetailVo arbiadv=new AdvanceReceivableInvoiceAccountDetailVo();
			BeanUtils.copyProperties(a, arbiadv);
			//配合前端添加冗余字段
			arbiadv.setUnclearQuantity(arbiadv.getUnaccountQuantity());
			arbiadv.setClearQuantity(arbiadv.getAccountQuantity());
			//未对账 数量
			BigDecimal unaccountQuantity=arbiadv.getUnaccountQuantity();
			//本次自动对账数量
			BigDecimal finalAccountQuantity=BigDecimal.ZERO;
			
		  //------------------------------------------ 
		  //出库总金额
		    BigDecimal totalAccountAmount=BigDecimal.ZERO;
		  //出库不含税总金额
		    BigDecimal totalAccountNotaxAmount=BigDecimal.ZERO;
		  //出库总税额
		    BigDecimal totalAccountTaxAmount=BigDecimal.ZERO;
		    
			List<InvoiceDistrOutShelfVo> invoiceDistrOutShelfList = distrOutShelfMapper.getInvoiceDistrOutShelfList(advanceReceivableInvoice.getPurchaseUnitId(), a.getGoodsId());
			if(invoiceDistrOutShelfList!=null) {
				for(InvoiceDistrOutShelfVo i:invoiceDistrOutShelfList) {
					//配合前端添加冗余字段
					i.setCode(i.getBaseOrderCode());
					i.setValidUntil(i.getValidDate());
					i.setOrderShelfId(i.getBaseShelfDtlId());
					//数量校验
					BigDecimal unclearQuantity=i.getUnclearQuantity();					
					//如果还有未对账数量
					if(unaccountQuantity.compareTo(BigDecimal.ZERO)>0) {
						if(unclearQuantity.compareTo(unaccountQuantity)>=0) {
							finalAccountQuantity=finalAccountQuantity.add(unaccountQuantity);
							i.setAccountQuantity(unaccountQuantity);	
							unaccountQuantity=unaccountQuantity.subtract(unaccountQuantity);
						}else {
							finalAccountQuantity=finalAccountQuantity.add(unclearQuantity);
							unaccountQuantity=unaccountQuantity.subtract(unclearQuantity);
							i.setAccountQuantity(unclearQuantity);	
						}						
					}else {
						i.setAccountQuantity(BigDecimal.ZERO);							
					}
					//本次对账金额
					BigDecimal accountAmount=i.getAccountQuantity().multiply(i.getUnitPrice());
					totalAccountAmount=totalAccountAmount.add(accountAmount);
					//本次对账不含税金额
				    BigDecimal accountNotaxAmount=i.getAccountQuantity().multiply(i.getNotaxRealPrice());
				    totalAccountNotaxAmount=totalAccountNotaxAmount.add(accountNotaxAmount);
				    //本次对账税额
				    BigDecimal accountTaxAmount=accountAmount.subtract(accountNotaxAmount);
				    totalAccountTaxAmount=totalAccountTaxAmount.add(accountTaxAmount);
				    i.setAccountAmount(accountAmount);
				    i.setAccountNotaxAmount(accountNotaxAmount);
				    i.setAccountTaxAmount(accountTaxAmount);
				}
			}
			arbiadv.setShelfList(invoiceDistrOutShelfList);
			
			//设置对账数量
			arbiadv.setTimeCheckAccountQty(finalAccountQuantity);
		    //计算发票明细的总数据
			BigDecimal totalMoney=finalAccountQuantity.multiply(a.getUnitPrice());
			BigDecimal totalMoneyOutTax=finalAccountQuantity.multiply(a.getNotaxPrice());
			BigDecimal totalTax=totalMoney.subtract(totalMoneyOutTax);
			//差额含义修改
		/*	BigDecimal totalDiffAmount=totalAccountAmount.subtract(totalMoney);
			BigDecimal totalDiffNotaxAmount=totalAccountNotaxAmount.subtract(totalMoneyOutTax);
			BigDecimal totalDiffTaxAmount=totalAccountTaxAmount.subtract(totalTax);*/			
			BigDecimal totalDiffAmount=totalMoney.subtract(totalAccountAmount);
			BigDecimal totalDiffNotaxAmount=totalMoneyOutTax.subtract(totalAccountNotaxAmount);
			BigDecimal totalDiffTaxAmount=totalTax.subtract(totalAccountTaxAmount);
			
			arbiadv.setTimeCheckAccountAmount(totalMoney);
			arbiadv.setTimeCheckAccountNotaxAmount(totalMoneyOutTax);
			arbiadv.setTimeCheckAccountTaxAmount(totalTax);
			arbiadv.setDiffAmount(totalDiffAmount);
			arbiadv.setDiffNotaxAmount(totalDiffNotaxAmount);
			arbiadv.setDiffTaxAmount(totalDiffTaxAmount);
			returnList.add(arbiadv);
		}
		return returnList;
	}
	@Override
	@Transactional
	public void updateAccount(UserVO loginUser,List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParamVO) throws Exception {
		if(perpayInvoiceAccountParamVO==null||perpayInvoiceAccountParamVO.size()==0) {
			throw new BusinessException("参数不能为空");
		}
		AdvanceReceivableInvoice advanceReceivableInvoice =null;
		for(PerpayInvoiceAccountParamVO p:perpayInvoiceAccountParamVO) {
			//初始化参数校验
			List<PerpayInvoiceAccountDetailParamVO> paramList=p.getPerpayInvoiceAccountDetailParamVOS();
			if(paramList==null||paramList.size()==0) {
				throw new BusinessException("参数货位明细不能为空");
			}
			AdvanceReceivableInvoiceDetail advanceReceivableInvoiceDetail = advanceReceivableInvoiceDetailMapper.selectByPrimaryKey(p.getId());
			if(advanceReceivableInvoiceDetail==null) {
				throw new BusinessException("预收发票明细id数据不存在");
			}
			if(advanceReceivableInvoice==null) {
				advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(advanceReceivableInvoiceDetail.getInvoiceId());
				if(advanceReceivableInvoice==null) {
					throw new BusinessException("预收发票id数据不存在");
				}
			}
			if(!this.isAccount(advanceReceivableInvoice)) {
				throw new BusinessException("发票状态不能对账");
			}
			//获取出货单
			List<InvoiceDistrOutShelfVo> invoiceDistrOutShelfList = distrOutShelfMapper.getInvoiceDistrOutShelfList(advanceReceivableInvoice.getPurchaseUnitId(), advanceReceivableInvoiceDetail.getGoodsId());
			if(invoiceDistrOutShelfList==null||invoiceDistrOutShelfList.size()==0) {
				throw new BusinessException("未查到相应出货单");
			}
			//初始化总变量数据
			//总数量
			BigDecimal totalNum=BigDecimal.ZERO;
			//总额
			BigDecimal totalMoney=BigDecimal.ZERO;
			//不含税总额
		    BigDecimal totalMoneyOutTax=BigDecimal.ZERO;
		    //总税额
		    BigDecimal totalTax=BigDecimal.ZERO;
		  //金额差额
		    BigDecimal totalDiffAmount=BigDecimal.ZERO;
		  //不含税金额差额
		    BigDecimal totalDiffNotaxAmount=BigDecimal.ZERO;
		  //税额差额
		    BigDecimal totalDiffTaxAmount=BigDecimal.ZERO;
		    
		   //------------------------------------------ 
		  //出库总金额
		    BigDecimal totalAccountAmount=BigDecimal.ZERO;
		  //出库不含税总金额
		    BigDecimal totalAccountNotaxAmount=BigDecimal.ZERO;
		  //出库总税额
		    BigDecimal totalAccountTaxAmount=BigDecimal.ZERO;
		    

		    //统计数据
		    for(InvoiceDistrOutShelfVo i:invoiceDistrOutShelfList) {
				for(PerpayInvoiceAccountDetailParamVO pp:paramList) {
					if(i.getBaseShelfDtlId().equals(pp.getOrderShelfId())) {
						//本次对账数量
						BigDecimal accountQuantity=pp.getAccountQuantity();
						totalNum=totalNum.add(accountQuantity);
						//本次对账金额
						BigDecimal accountAmount=accountQuantity.multiply(i.getUnitPrice());
						totalAccountAmount=totalAccountAmount.add(accountAmount);
						//本次对账不含税金额
					    BigDecimal accountNotaxAmount=accountQuantity.multiply(i.getNotaxRealPrice());
					    totalAccountNotaxAmount=totalAccountNotaxAmount.add(accountNotaxAmount);
					    //本次对账税额
					    BigDecimal accountTaxAmount=accountAmount.subtract(accountNotaxAmount);
					    totalAccountTaxAmount=totalAccountTaxAmount.add(accountTaxAmount);
						//匹配货位后，执行相关操作
						this.account(loginUser,advanceReceivableInvoiceDetail, pp,i);
						//跟新配货出库货位信息和状态信息
						this.updateDistrOutShelfInfo(i.getBaseShelfDtlId(),i.getUnclearQuantity(),i.getClearQuantity(),accountQuantity);
					}
				}
			}
		    //计算发票明细的总数据
		    totalMoney=totalNum.multiply(advanceReceivableInvoiceDetail.getUnitPrice());
		    totalMoneyOutTax=totalNum.multiply(advanceReceivableInvoiceDetail.getNotaxPrice());
		    totalTax=totalMoney.subtract(totalMoneyOutTax);
		/*    totalDiffAmount=totalAccountAmount.subtract(totalMoney);
		    totalDiffNotaxAmount=totalAccountNotaxAmount.subtract(totalMoneyOutTax);
		    totalDiffTaxAmount=totalAccountTaxAmount.subtract(totalTax);*/
		    //修改差额含义
		    totalDiffAmount=totalMoney.subtract(totalAccountAmount);
		    totalDiffNotaxAmount=totalMoneyOutTax.subtract(totalAccountNotaxAmount);
		    totalDiffTaxAmount=totalTax.subtract(totalAccountTaxAmount);
		    //校验数量
			if(totalNum.compareTo(advanceReceivableInvoiceDetail.getUnaccountQuantity())>0) {
				throw new BusinessException("对账数量大于发票明细未未对账数量");
			}
		    //跟新发票明细及状态
		    this.updateAdvanceReceivableInvoiceDetail(advanceReceivableInvoiceDetail,
					totalNum,totalDiffAmount,totalDiffNotaxAmount,totalDiffTaxAmount);
		 
		}		
	}
	//对账跟新操作
	private  void account(UserVO loginUser,AdvanceReceivableInvoiceDetail advanceReceivableInvoiceDetail,
			PerpayInvoiceAccountDetailParamVO pp,InvoiceDistrOutShelfVo i
			) throws Exception {
		//校验数量是否超出
		if(pp.getAccountQuantity().compareTo(i.getUnclearQuantity())>0) {
			throw new BusinessException("货位对账数量大于未清数量");
		}
		//初始化数据
		//本次对账数量
		BigDecimal accountQuantity=pp.getAccountQuantity();
		//本次对账金额
		BigDecimal accountAmount=accountQuantity.multiply(advanceReceivableInvoiceDetail.getUnitPrice());
		//本次对账不含税金额
	    BigDecimal accountNotaxAmount=accountQuantity.multiply(advanceReceivableInvoiceDetail.getNotaxPrice());
	    //本次对账税额
	    BigDecimal accountTaxAmount=accountAmount.subtract(accountNotaxAmount);
	    //金额差额
/*	    BigDecimal diffAmount=(i.getUnitPrice().subtract(advanceReceivableInvoiceDetail.getUnitPrice())).multiply(accountQuantity);*/
	    BigDecimal diffAmount=(advanceReceivableInvoiceDetail.getUnitPrice().subtract(i.getUnitPrice())).multiply(accountQuantity);
	    //不含税差额
/*	    BigDecimal diffNotaxAmount=(i.getNotaxRealPrice().subtract(advanceReceivableInvoiceDetail.getNotaxPrice())).multiply(accountQuantity);	*/    
	    BigDecimal diffNotaxAmount=(advanceReceivableInvoiceDetail.getNotaxPrice().subtract(i.getNotaxRealPrice())).multiply(accountQuantity);	    
	  //  发票明细税额
	    BigDecimal taxAmount=(advanceReceivableInvoiceDetail.getUnitPrice().subtract(advanceReceivableInvoiceDetail.getNotaxPrice())).multiply(accountQuantity);
	    //税额差额
	    /*BigDecimal diffTaxAmount=accountTaxAmount.subtract(taxAmount);*/
	    BigDecimal diffTaxAmount=taxAmount.subtract(accountTaxAmount);
	    
		//像saas_advance_receivable_invoice_account（应付发票-预付发票对账表）插入记录
		AdvanceReceivableInvoiceAccount advanceReceivableInvoiceAccount=new AdvanceReceivableInvoiceAccount();
		UserEnterpriseUtils.setUserCreateOrModify(advanceReceivableInvoiceAccount, loginUser, true);
		advanceReceivableInvoiceAccount.setEnterpriseId(loginUser.getEnterpriseId());
		advanceReceivableInvoiceAccount.setParentId(loginUser.getParentId());
		advanceReceivableInvoiceAccount.setLineNum(pp.getLineNum());
		advanceReceivableInvoiceAccount.setStatus(1);//无说明默认1
		advanceReceivableInvoiceAccount.setInvoiceId(advanceReceivableInvoiceDetail.getInvoiceId());
		advanceReceivableInvoiceAccount.setInvoiceDtlId(advanceReceivableInvoiceDetail.getId());
		BeanUtils.copyProperties(i, advanceReceivableInvoiceAccount);
		advanceReceivableInvoiceAccount.setAccountAmount(accountAmount);
		advanceReceivableInvoiceAccount.setAccountQuantity(accountQuantity);
		advanceReceivableInvoiceAccount.setAccountNotaxAmount(accountNotaxAmount);
		advanceReceivableInvoiceAccount.setAccountTaxAmount(accountTaxAmount);
		advanceReceivableInvoiceAccount.setDiffAmount(diffAmount);
		advanceReceivableInvoiceAccount.setDiffNotaxAmount(diffNotaxAmount);
		advanceReceivableInvoiceAccount.setDiffTaxAmount(diffTaxAmount);
		advanceReceivableInvoiceAccountMapper.insert(advanceReceivableInvoiceAccount);
		//更新预收发票和预收发票明细相关状态		
	}
	/**
	 * 跟新货位信息数量和状态
	 * @param shelfId
	 * @param accountQuantity
	 */
	public void updateDistrOutShelfInfo(Long shelfId,BigDecimal unclearQuantity,BigDecimal clearQuantity,BigDecimal accountQuantity) {
		DistrOutShelf record=new DistrOutShelf();
		record.setId(shelfId);
		BigDecimal finalUnclearQuantity=BigDecimal.ZERO;
		BigDecimal finalClearQuantity=BigDecimal.ZERO;
		if(accountQuantity.compareTo(unclearQuantity)>0) {
			throw new BusinessException("对账数量+已清数量大于出库数量");
		}else {
			finalUnclearQuantity=unclearQuantity.subtract(accountQuantity);
			finalClearQuantity=clearQuantity.add(accountQuantity);
		}
		//查看更新状态
		if(accountQuantity.compareTo(unclearQuantity)==0) {
			//跟新为已完成
			//检查更新配货明细状态和数量
			record.setStatus(DistrOutStatus.FINISHED);
		}else {
			if(clearQuantity.compareTo(BigDecimal.ZERO)==0) {
				//部分开票
				record.setStatus(DistrOutStatus.PART_BILL);
			}
		}		
		record.setClearQuantity(finalClearQuantity);
		record.setUnclearQuantity(finalUnclearQuantity);
		record.setUpdateTime(new Date());
		distrOutShelfMapper.updateByPrimaryKeySelective(record);	
		this.updateDistrOutDetailInfo(shelfId,accountQuantity);
	}
	//更新配货出库明细
	public void updateDistrOutDetailInfo(Long shelfId,BigDecimal accountQuantity) {
		Integer status=null;
		DistrOutShelf distrOutShelf = distrOutShelfMapper.selectByPrimaryKey(shelfId);
		if(distrOutShelf==null) {
			throw new BusinessException("配货出库货位获取不到");
		}
		DistrOutDetail distrOutDetail = distrOutDetailMapper.selectByPrimaryKey(distrOutShelf.getDtlId());
		if(distrOutDetail==null) {
			throw new BusinessException("配货出库明细获取不到");
		}
		DistrOutDetail dod=new DistrOutDetail();
		dod.setId(distrOutDetail.getId());
		BigDecimal finalUnclearQuantity=distrOutDetail.getUnclearQuantity().subtract(accountQuantity);
		BigDecimal finalClearQuantity=distrOutDetail.getClearQuantity().add(accountQuantity);
		//查看更新状态
		if(accountQuantity.compareTo(distrOutDetail.getUnclearQuantity())==0) {
			//跟新为已完成
			//检查更新配货明细状态和数量
			dod.setStatus(DistrOutStatus.FINISHED);
			status=DistrOutStatus.FINISHED;
		}else {
			if(distrOutDetail.getClearQuantity().compareTo(BigDecimal.ZERO)==0) {
				//部分开票
				dod.setStatus(DistrOutStatus.PART_BILL);
				status=DistrOutStatus.PART_BILL;
			}
		}
		dod.setClearQuantity(finalClearQuantity);
		dod.setUnclearQuantity(finalUnclearQuantity);
		dod.setUpdateTime(new Date());
		distrOutDetailMapper.updateByPrimaryKeySelective(dod);
		if(status!=null) {
			//跟新配货出库主表状态
			this.updateDistrOutDetailInfo(distrOutShelf.getOutId());
		}
	}
	private void updateDistrOutDetailInfo(Long id) {
		DistrOut distrOut=new DistrOut();
		distrOut.setId(id);
		boolean flag=true;
		List<DistrOutDetail> distrOutDetailList = distrOutDetailMapper.getDistrOutDetailList(id);
		if(distrOutDetailList==null||distrOutDetailList.size()==0) {
			throw new BusinessException("配货出库明主表id错误");
		}
		for(DistrOutDetail d:distrOutDetailList) {
			if(!d.getStatus().equals(DistrOutStatus.FINISHED)) {
				flag=false;
			}
		}
		if(flag) {
			distrOut.setStatus(DistrOutStatus.FINISHED);
		}else {
			distrOut.setStatus(DistrOutStatus.PART_BILL);
		}
		distrOut.setUpdateTime(new Date());
		distrOutMapper.updateByPrimaryKeySelective(distrOut);
	}
	//更新明细
	private void updateAdvanceReceivableInvoiceDetail(AdvanceReceivableInvoiceDetail arid,
			BigDecimal totalNum,BigDecimal totalDiffAmount,BigDecimal totalDiffNotaxAmount,
			BigDecimal totalDiffTaxAmount) {
		BigDecimal finalUnclearQuantity=arid.getUnaccountQuantity().subtract(totalNum);
		BigDecimal finalClearQuantity=arid.getAccountQuantity().add(totalNum);
		BigDecimal clearAmountTotal=arid.getUnitPrice().multiply(totalNum);
		AdvanceReceivableInvoiceDetail advanceReceivableInvoiceDetail=new AdvanceReceivableInvoiceDetail();
		advanceReceivableInvoiceDetail.setId(arid.getId());
		//查看更新状态
		if(totalNum.compareTo(arid.getUnaccountQuantity())==0) {
			//跟新为已完成
			advanceReceivableInvoiceDetail.setAccountStatus(FinanceReconciliationStatus.RECONCILED);
		}else {
			if(arid.getAccountQuantity().compareTo(BigDecimal.ZERO)==0) {
				//部分对账
				advanceReceivableInvoiceDetail.setAccountStatus(FinanceReconciliationStatus.PARTIAL_RECONCILIATION);
			}
		}
		//0118修改为对账数据字段--已清未清不处理
		advanceReceivableInvoiceDetail.setAccountQuantity(finalClearQuantity);
		advanceReceivableInvoiceDetail.setUnaccountQuantity(finalUnclearQuantity);
		advanceReceivableInvoiceDetail.setUpdateTime(new Date());
		advanceReceivableInvoiceDetail.setDiffAmount(totalDiffAmount);
		advanceReceivableInvoiceDetail.setDiffNotaxAmount(totalDiffNotaxAmount);
		advanceReceivableInvoiceDetail.setDiffTaxAmount(totalDiffTaxAmount);
		advanceReceivableInvoiceDetailMapper.updateByPrimaryKeySelective(advanceReceivableInvoiceDetail);
		this.updateAdvanceReceivableInvoice(arid.getInvoiceId(), clearAmountTotal);
	}
	//更新主表
	private void updateAdvanceReceivableInvoice(Long invoiceId,BigDecimal clearAmountTotal) {
		AdvanceReceivableInvoice advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(invoiceId);
		List<AdvanceReceivableInvoiceDetail> list = advanceReceivableInvoiceDetailMapper.selectByInvoiceId(invoiceId);
		//已清金额未清金额不由对账更改20180129
//		BigDecimal finalClearAmountTotal=advanceReceivableInvoice.getClearAmountTotal().add(clearAmountTotal);
//		BigDecimal finalUnClearAmountTotal=advanceReceivableInvoice.getUnclearAmountTotal().subtract(clearAmountTotal);
		boolean flag=true;
		AdvanceReceivableInvoice ari=new AdvanceReceivableInvoice();
		ari.setId(invoiceId);
		ari.setUpdateTime(new Date());
//		ari.setUnclearAmountTotal(finalUnClearAmountTotal);
//		ari.setClearAmountTotal(finalClearAmountTotal);
		for(AdvanceReceivableInvoiceDetail a:list) {
			if(!a.getAccountStatus().equals(FinanceReconciliationStatus.RECONCILED)) {
				flag=false;
			}
		}
		if(flag) {
			ari.setAccountStatus(FinanceReconciliationStatus.RECONCILED);
		}else {
			ari.setAccountStatus(FinanceReconciliationStatus.PARTIAL_RECONCILIATION);
		}
		advanceReceivableInvoiceMapper.updateByPrimaryKeySelective(ari);
	}
	@Override
	@Transactional
	public String updateInvoice(UserVO loginUser, InvoiceUpdateVo invoiceUpdateVo) throws Exception {
		AdvanceReceivableInvoiceInfo receivableInvoiceInfo = advanceReceivableInvoiceInfoMapper.selectByInvoiceId(invoiceUpdateVo.getId());
		if(receivableInvoiceInfo==null) {
			throw new BusinessException("请传入有效的id");
		}
		AdvanceReceivableInvoiceInfoVo old=new AdvanceReceivableInvoiceInfoVo();
		BeanUtils.copyProperties(receivableInvoiceInfo, old);
		
		User user = userMapper.selectByPrimaryKey(invoiceUpdateVo.getBillManId());
		String billManCode=user.getCode();
		String billManName=user.getName();

		StringBuilder column_en=new StringBuilder();
		StringBuilder column_ch=new StringBuilder();
		StringBuilder column_old=new StringBuilder();
		StringBuilder column_new=new StringBuilder();
		AdvanceReceivableInvoice invoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(invoiceUpdateVo.getId());
		if(invoice==null) {
			throw new BusinessException("发票id无效");
		}
		String code=invoice.getCode();
		if(!this.isUpdate(invoice)) {
			throw new BusinessException("发票状态不能更新");
		}
		AdvanceReceivableInvoice advanceReceivableInvoice=new AdvanceReceivableInvoice();
		advanceReceivableInvoice.setId(invoiceUpdateVo.getId());
		advanceReceivableInvoice.setBillManId(invoiceUpdateVo.getBillManId());
		advanceReceivableInvoice.setBillManCode(billManCode);
		advanceReceivableInvoice.setBillManName(billManName);
		advanceReceivableInvoice.setRemark(invoiceUpdateVo.getRemark());
		advanceReceivableInvoice.setUpdateTime(new Date());
		advanceReceivableInvoice.setBillDate(invoiceUpdateVo.getBillDate());
		//比较开票时间
		String date1=DateUtils.DateToString(invoice.getBillDate(), DateUtils.FMT_DATE);
		String date2=DateUtils.DateToString(invoiceUpdateVo.getBillDate(), DateUtils.FMT_DATE);
		if(!date1.equals(date2)) {
			column_en.append("|billDate");
			column_ch.append("|开票日期");
			column_old.append("|开票日期:"+DateUtils.DateToString(invoice.getBillDate(), DateUtils.FMT_DATE_TIME));
			column_new.append("|开票日期:"+DateUtils.DateToString(invoiceUpdateVo.getBillDate(), DateUtils.FMT_DATE_TIME));						
		}
		if(!invoiceUpdateVo.getBillManId().equals(invoice.getBillManId())) {
			column_en.append("|billManName");
			column_ch.append("|开票人员");
			column_old.append("|开票人员:"+invoice.getBillManName());
			column_new.append("|开票人员:"+billManName);		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getRemark())) {
			if(!invoiceUpdateVo.getRemark().equals(invoice.getRemark())) {
				column_en.append("|remark");
				column_ch.append("|备注");
				column_old.append("|备注:"+invoice.getRemark());
				column_new.append("|备注:"+invoiceUpdateVo.getRemark());					
			}
		
		}
		if(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceType()!=null) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceType().equals(receivableInvoiceInfo.getInvoiceType())) {
				column_en.append("|invoiceType");
				column_ch.append("|发票类型");
				column_old.append("|发票类型:"+AdvanceInvoiceType.getName(receivableInvoiceInfo.getInvoiceType()));
				column_new.append("|发票类型:"+AdvanceInvoiceType.getName(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceType()));	
			}			
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceCode())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceCode().equals(receivableInvoiceInfo.getInvoiceCode())) {
				column_en.append("|invoiceCode");
				column_ch.append("|发票代码");
				column_old.append("|发票代码:"+receivableInvoiceInfo.getInvoiceCode());
				column_new.append("|发票代码:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceCode());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceNumber())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceNumber().equals(receivableInvoiceInfo.getInvoiceNumber())) {
				column_en.append("|invoiceNumber");
				column_ch.append("|发票号码");
				column_old.append("|发票号码:"+receivableInvoiceInfo.getInvoiceNumber());
				column_new.append("|发票号码:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getInvoiceNumber());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getTaxpayerIdCode())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getTaxpayerIdCode().equals(receivableInvoiceInfo.getTaxpayerIdCode())) {
				column_en.append("|taxpayerIdCode");
				column_ch.append("|购货单位纳税人识别号");
				column_old.append("|购货单位纳税人识别号:"+receivableInvoiceInfo.getTaxpayerIdCode());
				column_new.append("|购货单位纳税人识别号:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getTaxpayerIdCode());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAccountName())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAccountName().equals(receivableInvoiceInfo.getAccountName())) {
				column_en.append("|accountName");
				column_ch.append("|购货单位开户户名");
				column_old.append("|购货单位开户户名:"+receivableInvoiceInfo.getAccountName());
				column_new.append("|购货单位开户户名:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAccountName());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAccountBank())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAccountBank().equals(receivableInvoiceInfo.getAccountBank())) {
				column_en.append("|accountBank");
				column_ch.append("|购货单位开户银行");
				column_old.append("|购货单位开户银行:"+receivableInvoiceInfo.getAccountBank());
				column_new.append("|购货单位开户银行:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAccountBank());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAddress())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAddress().equals(receivableInvoiceInfo.getAddress())) {
				column_en.append("|account");
				column_ch.append("|购货单位地址");
				column_old.append("|购货单位地址:"+receivableInvoiceInfo.getAddress());
				column_new.append("|购货单位地址:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getAddress());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getTelephone())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getTelephone().equals(receivableInvoiceInfo.getTelephone())) {
				column_en.append("|telephone");
				column_ch.append("|购货单位电话");
				column_old.append("|购货单位电话:"+receivableInvoiceInfo.getTelephone());
				column_new.append("|购货单位电话:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getTelephone());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyName())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyName().equals(receivableInvoiceInfo.getCompanyName())) {
				column_en.append("|companyName");
				column_ch.append("|企业名称");
				column_old.append("|企业名称:"+receivableInvoiceInfo.getCompanyName());
				column_new.append("|企业名称:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyName());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAddress())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAddress().equals(receivableInvoiceInfo.getCompanyAddress())) {
				column_en.append("|companyAddress");
				column_ch.append("|企业地址");
				column_old.append("|企业地址:"+receivableInvoiceInfo.getCompanyAddress());
				column_new.append("|企业地址:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAddress());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyTelephone())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyTelephone().equals(receivableInvoiceInfo.getCompanyTelephone())) {
				column_en.append("|companyTelephone");
				column_ch.append("|企业电话");
				column_old.append("|企业电话:"+receivableInvoiceInfo.getCompanyTelephone());
				column_new.append("|企业电话:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyTelephone());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyTaxpayerIdCode())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyTaxpayerIdCode().equals(receivableInvoiceInfo.getCompanyTaxpayerIdCode())) {
				column_en.append("|companyTaxpayerIdCode");
				column_ch.append("|企业纳税人识别号");
				column_old.append("|企业纳税人识别号:"+receivableInvoiceInfo.getCompanyTaxpayerIdCode());
				column_new.append("|企业纳税人识别号:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyTaxpayerIdCode());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAccountName())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAccountName().equals(receivableInvoiceInfo.getCompanyAccountName())) {
				column_en.append("|companyAccountName");
				column_ch.append("|企业开户户名");
				column_old.append("|企业开户户名:"+receivableInvoiceInfo.getCompanyAccountName());
				column_new.append("|企业开户户名:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAccountName());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAccount())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAccount().equals(receivableInvoiceInfo.getCompanyAccount())) {
				column_en.append("|companyAccount");
				column_ch.append("|企业开户银行");
				column_old.append("|企业开户银行:"+receivableInvoiceInfo.getCompanyAccount());
				column_new.append("|企业开户银行:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getCompanyAccount());					
			}		
		}
		if(!StringUtils.isEmpty(invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getRemark())) {
			if(!invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getRemark().equals(receivableInvoiceInfo.getRemark())) {
				column_en.append("|remark");
				column_ch.append("|开票详情备注");
				column_old.append("|开票详情备注:"+receivableInvoiceInfo.getRemark());
				column_new.append("|开票详情备注:"+invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo().getRemark());					
			}		
		}		

		advanceReceivableInvoiceMapper.updateByPrimaryKeySelective(advanceReceivableInvoice);
		//插入开票信息
		AdvanceReceivableInvoiceInfoVo ariv = invoiceUpdateVo.getAdvanceReceivableInvoiceInfoVo();
		AdvanceReceivableInvoiceInfo advanceReceivableInvoiceInfo=new AdvanceReceivableInvoiceInfo();
		BeanUtils.copyProperties(ariv, advanceReceivableInvoiceInfo);
		advanceReceivableInvoiceInfo.setInvoiceId(invoiceUpdateVo.getId());
		advanceReceivableInvoiceInfo.setId(receivableInvoiceInfo.getId());
		advanceReceivableInvoiceInfoMapper.updateByPrimaryKeySelective(advanceReceivableInvoiceInfo);
		//插入更新记录
		AdvanceReceivableInvoiceModifyRecord arimr=new AdvanceReceivableInvoiceModifyRecord();
		UserEnterpriseUtils.setUserCreateOrModify(arimr, loginUser, true);
		arimr.setEnterpriseId(loginUser.getEnterpriseId());
		arimr.setParentId(loginUser.getParentId());
		arimr.setKeyId(invoiceUpdateVo.getId());
		arimr.setTableName("saas_advance_receivable_invoice");
		String column_en_new=column_en.toString();
		String column_ch_new=column_ch.toString();
		String column_old_new=column_old.toString();
		String column_new_new=column_new.toString();
		if(column_en_new.length()>0) {
			column_en_new=column_en_new.substring(1, column_en_new.length());
		}
		if(column_ch_new.length()>0) {
			column_ch_new=column_ch_new.substring(1, column_ch_new.length());
		}
		if(column_old_new.length()>0) {
			column_old_new=column_old_new.substring(1, column_old_new.length());
		}
		if(column_new_new.length()>0) {
			column_new_new=column_new_new.substring(1, column_new_new.length());
		}
		arimr.setColumnEnName(column_en_new);
		arimr.setColumnChName(column_ch_new);
		arimr.setOldContent(column_old_new);
		arimr.setNewContent(column_new_new);
		arimr.setReason(invoiceUpdateVo.getReason());
		advanceReceivableInvoiceModifyRecordMapper.insert(arimr);	
		return code;
	}
	@Override
	public List<AdvanceReceivableInvoiceModifyRecord> selectAdvanceReceivableInvoiceModifyRecordList(Long keyId,Page<List<AdvanceReceivableInvoiceModifyRecord>> page,UserVO userVO){
		List<AdvanceReceivableInvoiceModifyRecord> list = advanceReceivableInvoiceModifyRecordMapper.selectAdvanceReceivableInvoiceModifyRecordList(keyId,userVO.getEnterpriseId(), page.getStart(), page.getPageSize());
		Long count = advanceReceivableInvoiceModifyRecordMapper.selectAdvanceReceivableInvoiceModifyRecordListCount(keyId,userVO.getEnterpriseId());
		page.setTotalRecord(count.intValue());
		return list;
	}
	@Override
	public List<AdvanceReceivableInvoiceModifyRecord> selectAdvanceReceivableInvoiceModifyRecordList(Long keyId,UserVO userVO){
		List<AdvanceReceivableInvoiceModifyRecord> list = advanceReceivableInvoiceModifyRecordMapper.selectAdvanceReceivableInvoiceModifyRecordListNoPage(keyId,userVO.getEnterpriseId());
		return list;
	}
	 @Override
	public void exportExcel(OutputStream output, AdvanceReveivableinvoiceSelectVo selectReveivableinvoice, UserVO loginUser) {

	        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//	        map.put("baseOrderDate", "日期");
//	        map.put("baseOrderCode", "单号");
	        map.put("goodsCode", "商品编码");
//	        map.put("barcode", "条形码");
	        map.put("goodsGenericName", "通用名称");
//	        map.put("goodsName", "商品名称");
	        map.put("dosageName", "剂型");
	        map.put("goodsSpecification", "规格");
	        map.put("unitName", "单位");
	        map.put("manufacturer", "生产厂商");
	        map.put("goodsPlace", "产地");
	        map.put("approvalNumber", "批准文号");
//	        map.put("lotNumber", "批号");
//	        map.put("productDate", "生产日期");
//	        map.put("validDate", "有效期至");
	        map.put("quantity", "数量");
	        map.put("unitPrice", "单价");
	        map.put("amount", "金额");
	        map.put("taxRate", "税率");
	        map.put("notaxAmount", "不含税金额");
	        map.put("taxAmount", "税额");
//	        map.put("diffAmount", "金额差额");
//	        map.put("diffNotaxAmount", "不含税金额差额");
//	        map.put("diffTaxAmount", "税额差额");
//	        map.put("remark", "备注");
	        List<String> titleSecond = new ArrayList<>();
	        //标题栏下第一行
	        StringBuilder titleSecondRow = new StringBuilder();
	        titleSecondRow.append("购货单位编码:");
	        titleSecondRow.append(selectReveivableinvoice.getAdvanceReceivableInvoice().getPurchaseUnitCode() == null ? "" : selectReveivableinvoice.getAdvanceReceivableInvoice().getPurchaseUnitCode());
	        titleSecondRow.append("  购货单位名称:");
	        titleSecondRow.append(selectReveivableinvoice.getAdvanceReceivableInvoice().getPurchaseUnitName() == null ? "" : selectReveivableinvoice.getAdvanceReceivableInvoice().getPurchaseUnitName());
	        titleSecondRow.append("  开票日期:");
	        //处理日期显示
	        Date date=selectReveivableinvoice.getAdvanceReceivableInvoice().getBillDate();
	        if(date!=null) {
		        titleSecondRow.append(DateUtils.DateToString(date, DateUtils.FMT_DATE));

	        }else {
	        	titleSecondRow.append("");
	        }
	        titleSecondRow.append("   开票人员:");
	        titleSecondRow.append(selectReveivableinvoice.getAdvanceReceivableInvoice().getBillManName() == null ? "" : selectReveivableinvoice.getAdvanceReceivableInvoice().getBillManName() );
	        titleSecondRow.append("  单号:");
	        titleSecondRow.append(selectReveivableinvoice.getAdvanceReceivableInvoice().getCode() == null ? "" : selectReveivableinvoice.getAdvanceReceivableInvoice().getCode());
	        titleSecondRow.append("  备注:");
	        titleSecondRow.append(selectReveivableinvoice.getAdvanceReceivableInvoice().getRemark() == null ? "" : selectReveivableinvoice.getAdvanceReceivableInvoice().getRemark());
	        titleSecond.add(titleSecondRow.toString());
	        StringBuilder end = new StringBuilder();
	        
	        //统计

	        BigDecimal quantity = selectReveivableinvoice.getAdvanceReceivableInvoiceDetailListVo().getTotalNum();//数量
	        BigDecimal amount = selectReveivableinvoice.getAdvanceReceivableInvoiceDetailListVo().getTotalMoney();//金额
	        BigDecimal notaxAmount = selectReveivableinvoice.getAdvanceReceivableInvoiceDetailListVo().getTotalMoneyOutTax();//不含税金额
	        BigDecimal taxAmount = selectReveivableinvoice.getAdvanceReceivableInvoiceDetailListVo().getTotalTax();//税额

	        end.append(quantity);
	        end.append(";");
	        end.append(amount);
	        end.append(";");
	        end.append(notaxAmount);
	        end.append(";");
	        end.append(taxAmount);
	        end.append(";");

	        List<String> needTotalName = new ArrayList<>();
	        needTotalName.add("quantity");
	        needTotalName.add("amount");
	        needTotalName.add("notaxAmount");
	        needTotalName.add("taxAmount");

	        List<String> name = new ArrayList<>();
	        name.add(loginUser.getEnterpriseName());
	        name.add("预收发票");
	        purchaseGeneralComponent.commExcelExport(output, map, selectReveivableinvoice.getAdvanceReceivableInvoiceDetailListVo().getArbidList(), name, titleSecond, end.toString(), false, needTotalName);
	    }
	@Override
	@Transactional
	public void cancel(UserVO loginUser,Long invoiceId) throws Exception {
		AdvanceReceivableInvoice invoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(invoiceId);
		if(invoice==null) {
			throw new BusinessException("发票id无效");
		}
		if(!this.isCancel(invoice)) {
			throw new BusinessException("发票状态不能冲销");
		}
		//调取中义接口
		//跟新相关记录状态
		AdvanceReceivableInvoice updateInvoice=new AdvanceReceivableInvoice();
		updateInvoice.setId(invoiceId);
		updateInvoice.setUpdateTime(new Date());
		updateInvoice.setStatus(AdvanceInvoiceStatus.CANCEL_RECEIVE.getCode());
		updateInvoice.setAccountStatus(FinanceReconciliationStatus.WARITE_OF);
		advanceReceivableInvoiceMapper.updateByPrimaryKeySelective(updateInvoice);
		List<AdvanceReceivableInvoiceDetail> list = advanceReceivableInvoiceDetailMapper.selectByInvoiceId(invoiceId);
		AdvanceReceivableInvoiceDetail newAdvanceReceivableInvoiceDetail=new AdvanceReceivableInvoiceDetail();
		if(list!=null) {
			for(AdvanceReceivableInvoiceDetail a:list) {
				newAdvanceReceivableInvoiceDetail.setId(a.getId());
				newAdvanceReceivableInvoiceDetail.setUpdateTime(new Date());
				newAdvanceReceivableInvoiceDetail.setStatus(AdvanceInvoiceStatus.CANCEL_RECEIVE.getCode());
				newAdvanceReceivableInvoiceDetail.setAccountStatus(FinanceReconciliationStatus.WARITE_OF);
				advanceReceivableInvoiceDetailMapper.updateByPrimaryKeySelective(newAdvanceReceivableInvoiceDetail);
			}
		}
		//调用中义接口
		financeComponent.advanceReceivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(loginUser,
				invoice,
				list, "writeOff");
		//还原上游单据
		List<AdvanceReceivableInvoiceAccount> accountList = advanceReceivableInvoiceAccountMapper.selectByInvoiceId(invoiceId);
		if(accountList!=null) {
			for(AdvanceReceivableInvoiceAccount a:accountList) {
				//调用还原方法
				this.recoveryAccount(a);
			}
		}
	}
	@Override
	public void exportUpdateExcel(OutputStream output, List<AdvanceReceivableInvoiceModifyRecord> list,
			UserVO loginUser) {
	    LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("updateTime","修改时间");
        map.put("modifierName","修改人员");
        map.put("reason","修改原因");
        map.put("columnChName","修改项目");
        map.put("oldContent","原内容");
        map.put("newContent","新内容");
        List<String> secondTitle = new ArrayList<String>();
        String end = "";
        List<String> name = new ArrayList<String>();
        //第一行的企业名
        name.add(loginUser.getEnterpriseName());
        //第二行的
        name.add("预收发票修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,secondTitle,end,true,new ArrayList<>());		
	}
	//还原出库单据
	private void recoveryAccount(AdvanceReceivableInvoiceAccount advanceReceivableInvoiceAccount) {
		if(advanceReceivableInvoiceAccount==null) {
			return;
		}
		DistrOutShelf record=new DistrOutShelf();
		record.setId(advanceReceivableInvoiceAccount.getBaseShelfDtlId());
		DistrOutShelf distrOutShelf = distrOutShelfMapper.selectByPrimaryKey(advanceReceivableInvoiceAccount.getBaseShelfDtlId());
		BigDecimal finalUnclearQuantity=distrOutShelf.getUnclearQuantity().add(advanceReceivableInvoiceAccount.getAccountQuantity());
		BigDecimal finalClearQuantity=distrOutShelf.getClearQuantity().subtract(advanceReceivableInvoiceAccount.getAccountQuantity());
		if(finalClearQuantity.compareTo(BigDecimal.ZERO)==0) {
			record.setStatus(DistrOutStatus.WAIT_BILL);
		}else {
			record.setStatus(DistrOutStatus.PART_BILL);
		}
		record.setUnclearQuantity(finalUnclearQuantity);
		record.setClearQuantity(finalClearQuantity);
		record.setUpdateTime(new Date());
		distrOutShelfMapper.updateByPrimaryKeySelective(record);
		this.updateDistrOutDetailInfoBack(advanceReceivableInvoiceAccount.getBaseDtlId(),advanceReceivableInvoiceAccount.getAccountQuantity());
	}
	//更新配货出库明细
	private void updateDistrOutDetailInfoBack(Long detailId,BigDecimal accountQuantity) {
		DistrOutDetail distrOutDetail = distrOutDetailMapper.selectByPrimaryKey(detailId);
		if(distrOutDetail==null) {
			throw new BusinessException("配货出库明细获取不到");
		}
		DistrOutDetail dod=new DistrOutDetail();
		dod.setId(detailId);
		BigDecimal finalUnclearQuantity=distrOutDetail.getUnclearQuantity().add(accountQuantity);
		BigDecimal finalClearQuantity=distrOutDetail.getClearQuantity().subtract(accountQuantity);
		//查看更新状态
		if(finalClearQuantity.compareTo(BigDecimal.ZERO)==0) {
			//跟新为已完成
			//检查更新配货明细状态和数量
			dod.setStatus(DistrOutStatus.WAIT_BILL);
		}else {
			dod.setStatus(DistrOutStatus.PART_BILL);
		}
		dod.setClearQuantity(finalClearQuantity);
		dod.setUnclearQuantity(finalUnclearQuantity);
		dod.setUpdateTime(new Date());
		distrOutDetailMapper.updateByPrimaryKeySelective(dod);
		//跟新配货出库主表状态
		this.updateDistrOutBack(distrOutDetail.getOutId());
	}
	private void updateDistrOutBack(Long id) {
		DistrOut distrOut=new DistrOut();
		distrOut.setId(id);
		boolean flag=true;
		List<DistrOutDetail> distrOutDetailList = distrOutDetailMapper.getDistrOutDetailList(id);
		if(distrOutDetailList==null||distrOutDetailList.size()==0) {
			throw new BusinessException("配货出库明主表id错误");
		}
		for(DistrOutDetail d:distrOutDetailList) {
			if(!d.getStatus().equals(DistrOutStatus.WAIT_BILL)) {
				flag=false;
			}
		}
		if(flag) {
			distrOut.setStatus(DistrOutStatus.WAIT_BILL);
		}else {
			distrOut.setStatus(DistrOutStatus.PART_BILL);
		}
		distrOut.setUpdateTime(new Date());
		distrOutMapper.updateByPrimaryKeySelective(distrOut);
	}
}

