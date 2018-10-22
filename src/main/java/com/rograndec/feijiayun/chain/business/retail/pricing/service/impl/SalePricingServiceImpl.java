package com.rograndec.feijiayun.chain.business.retail.pricing.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.retail.pricing.dao.SalePricingDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.pricing.dao.SalePricingMapper;
import com.rograndec.feijiayun.chain.business.retail.pricing.dao.SalePricingShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricing;
import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricingDetail;
import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricingShelf;
import com.rograndec.feijiayun.chain.business.retail.pricing.service.SalePricingService;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.*;
import com.rograndec.feijiayun.chain.business.storage.chgoods.service.ChGoodsLoadService;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.GoodsShelfStatusDescVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

 /**
 * 
 * @ClassName: SalePricingServiceImpl   
 * @Description:  零售管理-划价单-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
@Service
public class SalePricingServiceImpl implements SalePricingService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SalePricingServiceImpl.class);
	
	@Autowired
	private SalePricingMapper salePricingMapper;
	
	@Autowired
	private SalePricingDetailMapper salePricingDetailMapper;
	
	@Autowired
	private SalePricingShelfMapper salePricingShelfMapper;
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;
	
	
	@Autowired
    private OrderCodeComponent orderCodeComponent;
	
	@Autowired  
	private GoodsTaxRateMapper goodsTaxRateMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
	private ManageConfigMapper manageConfigMapper;
	
	@Autowired
	private UserMapper userMapper;
	 @Autowired
	private ChGoodsLoadService chGoodsLoadService;
	@Autowired
	private GoodsBusinessMapper goodsBusinessMapper;

	 @Autowired
	 private SpecialPriceGoodsMapper specialPriceGoodsMapper;

	 @Autowired
	 private SpecialPriceStrategyMapper specialPriceStrategyMapper;
	 @Autowired
	 private EnterpriseBusinessService enterpriseBusinessService;
	 @Autowired
	 private CommonComponent commonComponent;
	@Override
	public Page<SalePricingTotalVO> getSalePricingPage(SalePricingParamVO param) throws Exception {
		Page<SalePricingTotalVO> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		List<SalePricingVO> list = salePricingMapper.findByEnterpriseIdSalePricing(param);
		for(SalePricingVO vo : list) {
			if(null != vo.getStatus()) {
				int st = vo.getStatus();
				vo.setShowStatus(PurchaseStatus.getName(st));
			}
		}
		SalePricingTotalVO totalVO = salePricingMapper.countSalePricingData(param);
		if(totalVO != null) {
			totalVO.setPricingList(list);
		}
		page.setResult(totalVO);
		page.setTotalRecord(salePricingMapper.countEnterpriseIdSalePricing(param));
		return page;
	}

	@Override
	public SalePricingViewVO findByIdSalePricing(Long id) throws Exception {
		SalePricingViewVO list = salePricingMapper.findByIdSalePricing(id);
		if(null != list) {
			List<SalePricingDetailVO> dtlList = salePricingDetailMapper.getSalePricingDetail(list.getId());
			list.setSalePricingDetailList(dtlList);
			List<SalePricingShelfVO> dtlGoods = new ArrayList<>();
			for(SalePricingDetailVO dtl : dtlList) {
				// 设置商品批号、货位
				dtlGoods = salePricingShelfMapper.getPricingShelfGoods(dtl.getId(), dtl.getGoodsId());
				dtlGoods.forEach(item->{
					List<SelectPricingGoodsLotShelfVO> glsList = salePricingMapper.selectGoodsLotShelfId(item.getEnterpriseId(), item.getGoodsId(),item.getShelfId());
					if(glsList == null) {
						throw new BusinessException("该商品无库存可用数量");
					}
					glsList.forEach(g->{
						item.setUsableQuantity(g.getUsableQuantity());
						item.setShelfStatusDesc(g.getShelfStatusDesc());
					});
				});
				dtl.setSalePricingShelfList(dtlGoods);
			}
			
			dtlList.forEach(item->{
				 SelectGoodsMemberPrice mp = salePricingMapper.selectGoodsMemberPrice(item.getEnterpriseId(), item.getGoodsId());
				 if(null != mp) {
					 item.setMemberPrice(mp.getMemberPrice());
					 item.setRetailPrice(mp.getRetailPrice());
				 }
			});
		}
		return list;
	}
	
	/**
	 * 
	 * @Description: 业务质量控制
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月29日 上午10:29:21 
	 * @param salePricingVO
	 * @param userVO 
	 * @return void
	 */
	private void setZl(SalePricingSaveOrupdateVO salePricingVO,UserVO userVO) {
		ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl() == 0 ? false : true;
        if(!zl){//质量流程关闭的时候必提供划价人员ID
        	User user = userMapper.selectByPrimaryKey(salePricingVO.getCreaterId());
        	if(user!=null && user.getEnterpriseId().longValue() == userVO.getEnterpriseId().longValue()){
        		salePricingVO.setCreaterId(user.getId());
        		salePricingVO.setCreaterName(user.getName());
        		salePricingVO.setCode(user.getCode());
        	}
        } else {
			salePricingVO.setCreaterId(userVO.getUserId());
			salePricingVO.setCreaterName(userVO.getUserName());
			salePricingVO.setCode(userVO.getUserCode());
		}
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int saveORupdate(SalePricingSaveOrupdateVO salePricingVO,UserVO userVO) throws Exception {

		if(null == salePricingVO.getId() || 0 == salePricingVO.getId()) {
			
			SalePricing salePricing = (SalePricing)EntityUtils.reflectAddSetDefaultValue(new SalePricing().getClass(), userVO);
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(salePricingVO,salePricing);
			List<SalePricingDetailVO> dtlVOList = salePricingVO.getSalePricingDetailList();
			// 划价订单编号
			String code = orderCodeComponent.generate(OrderRule.SALES_PRICE.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
			salePricing.setOrderType(OrderRule.SALES_PRICE.getType());
			salePricing.setCode(code);
			salePricing.setStatus(PurchaseStatus.WAIT_PAY.getStatus());// 待支付
			salePricing.setPricingDate(DateUtils.StringToDate(salePricingVO.getPricingDate()));
			//数量合计
			BigDecimal allQuantity = BigDecimal.ZERO;
			salePricing.setQuantityTotal(allQuantity);
			 //金额计算
			salePricing.setRealAmountTotal(BigDecimal.ZERO);
			salePricing.setNotaxRealAmountTotal(BigDecimal.ZERO);
			salePricing.setTaxAmountTotal(BigDecimal.ZERO);
			salePricing.setProfitTotal(BigDecimal.ZERO);
			salePricing.setNotaxProfitTotal(BigDecimal.ZERO);
			salePricing.setProfitRate(BigDecimal.ZERO);
			salePricing.setNotaxProfitRate(BigDecimal.ZERO);
			salePricing.setSaleCode("");//设置销售单号为空
			salePricing.setVarietiesQuantity(dtlVOList == null ? 0 : dtlVOList.size()); // 品种数量
			setZl(salePricingVO, userVO);
			salePricingMapper.insertSelective(salePricing); // 划价单主表数据
			
			BigDecimal wholeDiscountAmount = salePricing.getWholeDiscountAmount();//整单优惠金额
	        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
	        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
	        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
	        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
	        BigDecimal profitTotal = BigDecimal.ZERO;//利润合计
	        BigDecimal notaxProfitTotal = BigDecimal.ZERO;//不含税利润合计
	        BigDecimal profitRate = BigDecimal.ZERO;//利润率
	        BigDecimal notaxProfitRate = BigDecimal.ZERO;//不含税利润率
			
			
			List<SalePricingShelfVO> spShelfVOList = null; // 商品批号、货位细表数据
			
			SalePricingDetail copyDtl = new SalePricingDetail();
			SalePricingShelf copySh = new SalePricingShelf();
			
			
			for(SalePricingDetailVO dtlVO : dtlVOList) {
				copyDtl = (SalePricingDetail)EntityUtils.reflectAddSetDefaultValue(copyDtl.getClass(), userVO);
				CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(dtlVO,copyDtl);
				CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(salePricingVO,copyDtl);
				GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(dtlVO.getTaxRateId());
				copyDtl.setPricingId(salePricing.getId());
				//设置税率
				copyDtl.setTaxRate(goodsTaxRate == null ? new BigDecimal(0) : goodsTaxRate.getTaxRate());
				copyDtl.setOrderType(OrderRule.SALES_PRICE.getType());
				copyDtl.setPricingCode(salePricing.getCode());
				copyDtl.setStatus(PurchaseStatus.WAIT_PAY.getStatus());
				copyDtl.setPricingDate(new Date());
				copyDtl.setUnclearQuantity(dtlVO.getQuantity());
				copyDtl.setClearQuantity(new BigDecimal(0));

				BigDecimal totalQuantity = BigDecimal.ZERO;//细表总数量 单剂*剂量
				BigDecimal dtlQuantity = BigDecimal.ZERO;//细表Quantity
				// 批号、货位
				spShelfVOList = dtlVO.getSalePricingShelfList();
				for(SalePricingShelfVO shVO : spShelfVOList) {
					dtlQuantity = dtlQuantity.add(shVO.getQuantity());
					shVO.setSingleDose(dtlVO.getSingleDose());
					totalQuantity = setSHTotalQuantity(dtlVO.getSingleDose(), salePricingVO.getDose());
					// 计算金额
					setCalcu(copyDtl,salePricingVO.getDose(), wholeDiscountAmount, amountTotal, realAmountTotal, notaxRealAmountTotal, taxAmountTotal);
				}
				if(dtlQuantity.intValue() < totalQuantity.intValue()) {
					throw new BusinessException("总数量大于库存可用数量");
				}
				copyDtl.setQuantity(dtlQuantity);
				// 计算主表的品种合计
				allQuantity = allQuantity.add(totalQuantity);
				// 主表的金额合计
				amountTotal = amountTotal.add(copyDtl.getAmount());
				copyDtl.setTotalQuantity(totalQuantity);
				notaxRealAmountTotal = notaxRealAmountTotal.add(copyDtl.getNotaxRealAmount());
				taxAmountTotal = taxAmountTotal.add(copyDtl.getTaxAmount());
				// 划价单细表保存
				salePricingDetailMapper.insertSelective(copyDtl);

				for(SalePricingShelfVO shVO : spShelfVOList) {
					copySh = (SalePricingShelf)EntityUtils.reflectAddSetDefaultValue(copySh.getClass(), userVO);
					CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(copyDtl,copySh); // 将商品中的数据给货位细表复制
					CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(shVO,copySh);
					copySh.setId(null);
					//copySh.setPricingId(salePricing.getId());
					copySh.setPricingDtlId(copyDtl.getId());
					copySh.setUnclearQuantity(dtlVO.getQuantity());
					copySh.setClearQuantity(new BigDecimal(0));
					
					// 计算金额
					//setSHCalcu(copySh, wholeDiscountAmount, amountTotal, realAmountTotal, notaxRealAmountTotal, taxAmountTotal);
					copySh.setOrderType(OrderRule.SALES_PRICE.getType());
					copySh.setPricingCode(salePricing.getCode());
					copySh.setStatus(PurchaseStatus.WAIT_PAY.getStatus());
					copySh.setPricingDate(new Date());
					// 商品批号、货位保存
					salePricingShelfMapper.insertSelective(copySh);
				}
			}	
			
			//更新总总单金额
			SalePricing upsp = salePricingMapper.selectByPrimaryKey(salePricing.getId());
			upsp.setQuantityTotal(allQuantity);
			upsp.setAmountTotal(amountTotal);
	        upsp.setRealAmountTotal(realAmountTotal);
	        upsp.setNotaxRealAmountTotal(notaxRealAmountTotal);
	        upsp.setTaxAmountTotal(taxAmountTotal);
	        upsp.setProfitTotal(profitTotal);
	        upsp.setNotaxProfitTotal(notaxProfitTotal);
	        upsp.setProfitRate(profitRate);
	        upsp.setNotaxProfitRate(notaxProfitRate);
	        salePricingMapper.updateByPrimaryKeySelective(upsp);
			
			return 1;
			
		} else {
			// 更新
			SalePricing salePricing = (SalePricing)EntityUtils.reflectUpdateSetDefaultValue(new SalePricing().getClass(), userVO);
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(salePricingVO,salePricing);
			salePricing.setPricingDate(DateUtils.StringToDate(salePricingVO.getPricingDate()));
			//金额计算
			salePricing.setRealAmountTotal(BigDecimal.ZERO);
			salePricing.setNotaxRealAmountTotal(BigDecimal.ZERO);
			salePricing.setTaxAmountTotal(BigDecimal.ZERO);
			salePricing.setProfitTotal(BigDecimal.ZERO);
			salePricing.setNotaxProfitTotal(BigDecimal.ZERO);
			salePricing.setProfitRate(BigDecimal.ZERO);
			salePricing.setNotaxProfitRate(BigDecimal.ZERO);
			setZl(salePricingVO, userVO);
			salePricingMapper.updateByPrimaryKeySelective(salePricing); // 划价单主表数据
			
			BigDecimal wholeDiscountAmount = salePricing.getWholeDiscountAmount();//整单优惠金额
	        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
	        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
	        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
	        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
	        BigDecimal profitTotal = BigDecimal.ZERO;//利润合计
	        BigDecimal notaxProfitTotal = BigDecimal.ZERO;//不含税利润合计
	        BigDecimal profitRate = BigDecimal.ZERO;//利润率
	        BigDecimal notaxProfitRate = BigDecimal.ZERO;//不含税利润率
	        BigDecimal allQuantity = BigDecimal.ZERO;
			
			List<SalePricingDetailVO> dtlVOList = salePricingVO.getSalePricingDetailList();
			List<SalePricingShelfVO> spShelfVOList = null; // 商品批号、货位细表数据
			
			SalePricingDetail copyDtl = new SalePricingDetail();
			SalePricingShelf copySh = new SalePricingShelf();
			//先删除原来数据
			salePricingDetailMapper.deleteBySalePricingId(salePricingVO.getId());
			for(SalePricingDetailVO dtlVO : dtlVOList) {
				copyDtl = (SalePricingDetail)EntityUtils.reflectAddSetDefaultValue(copyDtl.getClass(), userVO);
				CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(dtlVO,copyDtl);
				CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(salePricingVO,copyDtl);
				GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(dtlVO.getTaxRateId());
				copyDtl.setPricingId(salePricing.getId());
				//设置税率
				copyDtl.setTaxRate(goodsTaxRate == null ? new BigDecimal(0) : goodsTaxRate.getTaxRate());
				copyDtl.setOrderType(OrderRule.SALES_PRICE.getType());
				copyDtl.setPricingCode(salePricing.getCode());
				copyDtl.setStatus(PurchaseStatus.WAIT_PAY.getStatus());
				copyDtl.setPricingDate(new Date());
				copyDtl.setUnclearQuantity(dtlVO.getQuantity());
				copyDtl.setClearQuantity(new BigDecimal(0));
				copyDtl.setTotalQuantity(salePricing.getQuantityTotal());

				BigDecimal totalQuantity = BigDecimal.ZERO;//细表总数量 单剂*剂量
				BigDecimal dtlQuantity = BigDecimal.ZERO;//细表Quantity
				// 批号、货位
				spShelfVOList = dtlVO.getSalePricingShelfList();
				for(SalePricingShelfVO shVO : spShelfVOList) {
					dtlQuantity = dtlQuantity.add(shVO.getQuantity());
					shVO.setSingleDose(dtlVO.getSingleDose());
					totalQuantity = setSHTotalQuantity(dtlVO.getSingleDose(), salePricingVO.getDose());
					// 计算金额
					setCalcu(copyDtl,salePricingVO.getDose(), wholeDiscountAmount, amountTotal, realAmountTotal, notaxRealAmountTotal, taxAmountTotal);
				}
				if(dtlQuantity.intValue() < totalQuantity.intValue()) {
					throw new BusinessException("总数量大于库存可用数量");
				}
				copyDtl.setQuantity(dtlQuantity);
				// 计算主表的品种合计
				allQuantity = allQuantity.add(totalQuantity);
				// 主表的金额合计
				amountTotal = amountTotal.add(copyDtl.getAmount());
				copyDtl.setTotalQuantity(totalQuantity);
				notaxRealAmountTotal = notaxRealAmountTotal.add(copyDtl.getNotaxRealAmount());
				taxAmountTotal = taxAmountTotal.add(copyDtl.getTaxAmount());
				// 划价单细表保存
				copyDtl.setId(null);
				salePricingDetailMapper.insertSelective(copyDtl);
				
				for(SalePricingShelfVO shVO : spShelfVOList) {
					//删除信息
					salePricingShelfMapper.deleteByPrimaryKey(shVO.getId());
					copySh = (SalePricingShelf)EntityUtils.reflectAddSetDefaultValue(copySh.getClass(), userVO);
					CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(copyDtl,copySh); // 将商品中的数据给货位细表复制
					CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(shVO,copySh);
					copySh.setId(null);
					//copySh.setPricingId(salePricing.getId());
					copySh.setPricingDtlId(copyDtl.getId());
					copySh.setUnclearQuantity(dtlVO.getQuantity());
					copySh.setClearQuantity(new BigDecimal(0));
					// 计算金额
					//setSHCalcu(copySh, wholeDiscountAmount, amountTotal, realAmountTotal, notaxRealAmountTotal, taxAmountTotal);
					copySh.setOrderType(OrderRule.SALES_PRICE.getType());
					copySh.setPricingCode(salePricing.getCode());
					copySh.setStatus(PurchaseStatus.WAIT_PAY.getStatus());
					copySh.setPricingDate(new Date());
	
					// 商品批号、货位保存
					salePricingShelfMapper.insertSelective(copySh);
				}
			}
			
			//更新总总单金额
			SalePricing upsp = salePricingMapper.selectByPrimaryKey(salePricing.getId());
			upsp.setQuantityTotal(allQuantity);
			upsp.setAmountTotal(amountTotal);
	        upsp.setRealAmountTotal(realAmountTotal);
	        upsp.setNotaxRealAmountTotal(notaxRealAmountTotal);
	        upsp.setTaxAmountTotal(taxAmountTotal);
	        upsp.setProfitTotal(profitTotal);
	        upsp.setNotaxProfitTotal(notaxProfitTotal);
	        upsp.setProfitRate(profitRate);
	        upsp.setNotaxProfitRate(notaxProfitRate);
	        salePricingMapper.updateByPrimaryKeySelective(upsp);
			
			return 2;
		}

	}

	@Override
	public int cancelSalePricing(Long id) throws Exception {
		// 改为已取消
		int status = PurchaseStatus.CANCELED.getStatus();
		SalePricing salePricing = new SalePricing();
		salePricing.setStatus(status);
		salePricing.setId(id);
		salePricingMapper.updateByPrimaryKeySelective(salePricing); // 划价单主表数据
		// 细表更新
		SalePricingDetail copyDtl = new SalePricingDetail();
		copyDtl.setStatus(status);
		copyDtl.setPricingId(id);
		salePricingDetailMapper.updateByPrimaryKeySelective(copyDtl);
		SalePricingShelf copySh = new SalePricingShelf();
		copySh.setStatus(status);
		copySh.setPricingId(id);
		salePricingShelfMapper.updateByPrimaryKeySelective(copySh);
		return 0;
	}
	
	/**
	 * 
	 * @Description: 计算金额
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月25日 下午1:49:01 
	 * @param copyDtl
	 * @param wholeDiscountAmount 整单优惠金额
	 * @param amountTotal 金额合计（整单优惠前的金额合计）
	 * @param realAmountTotal 实际金额合计
	 * @param notaxRealAmountTotal 不含税金额合计
	 * @param taxAmountTotal 税额合计
	 * @return void
	 */
	private void setCalcu(SalePricingDetail copyDtl,BigDecimal dose,BigDecimal wholeDiscountAmount,BigDecimal amountTotal,BigDecimal realAmountTotal, BigDecimal notaxRealAmountTotal,BigDecimal taxAmountTotal) {
		//计算金额
        BigDecimal quantity = copyDtl.getSingleDose().multiply(dose).setScale(6,BigDecimal.ROUND_HALF_UP);//总数量=单剂量*剂量
        BigDecimal unitPrice = copyDtl.getUnitPrice();//单价
        BigDecimal lineDiscount = copyDtl.getLineDiscount();//行折扣
        BigDecimal taxRate = copyDtl.getTaxRate();//税率
        BigDecimal wholeDiscount = copyDtl.getWholeDiscount();//整单折扣
        //金额（整单优惠前金额）
        BigDecimal am = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(quantity, unitPrice, lineDiscount);
        amountTotal = amountTotal.add(am);
        wholeDiscountAmount = amountTotal.subtract(amountTotal.multiply(wholeDiscount.divide(new BigDecimal(100)))).setScale(2,BigDecimal.ROUND_HALF_UP);//整单优惠金额

		// 金额计算
		CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice, lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);
		
		//金额
        BigDecimal amount = calculateResultModel.getAmount();
        //行优惠
        BigDecimal lineRoundOff = calculateResultModel.getLineRoundOff();
        //实际金额
        BigDecimal realAmount = calculateResultModel.getRealAmount();
        realAmountTotal = realAmountTotal.add(realAmount);
        //实际单价
        BigDecimal realPrice = calculateResultModel.getRealPrice();

        //不含税金额
        BigDecimal notaxRealAmount = calculateResultModel.getNotaxAmount();
        notaxRealAmountTotal = notaxRealAmountTotal.add(notaxRealAmount);
        //不含税实际单价
        BigDecimal notaxRealPrice = calculateResultModel.getNotaxPrice();
        //税额
        BigDecimal taxAmount = calculateResultModel.getTaxAmount();
        taxAmountTotal = taxAmountTotal.add(taxAmount);

        copyDtl.setAmount(amount);
        copyDtl.setLineDiscountAmount(lineRoundOff);
        copyDtl.setRealAmount(realAmount);
        copyDtl.setRealPrice(realPrice);
        copyDtl.setNotaxRealAmount(notaxRealAmount);
        copyDtl.setNotaxRealPrice(notaxRealPrice);
        copyDtl.setTaxAmount(taxAmount);
        
	}
	
	/**
	 * 
	 * @Description: 货位明细计算金额
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月25日 下午1:58:33 
	 * @param copyDtl
	 * @param wholeDiscountAmount
	 * @param amountTotal
	 * @param realAmountTotal
	 * @param notaxRealAmountTotal
	 * @param taxAmountTotal 
	 * @return void
	 */
	@SuppressWarnings("unused")
	private void setSHCalcu(SalePricingShelf copyDtl,BigDecimal wholeDiscountAmount,BigDecimal amountTotal,BigDecimal realAmountTotal, BigDecimal notaxRealAmountTotal,BigDecimal taxAmountTotal) {
		//计算金额
        BigDecimal quantity = copyDtl.getQuantity();//数量
        BigDecimal unitPrice = copyDtl.getUnitPrice();//单价
        BigDecimal lineDiscount = copyDtl.getLineDiscount();//行折扣
        BigDecimal taxRate = copyDtl.getTaxRate();//税率
        BigDecimal wholeDiscount = copyDtl.getWholeDiscount();//整单折扣
		// 金额计算
		CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice, lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);
		
		//金额
        BigDecimal amount = calculateResultModel.getAmount();
        //行优惠
        BigDecimal lineRoundOff = calculateResultModel.getLineRoundOff();
        //实际金额
        BigDecimal realAmount = calculateResultModel.getRealAmount();
        realAmountTotal = realAmountTotal.add(realAmount);
        //实际单价
        BigDecimal realPrice = calculateResultModel.getRealPrice();

        //不含税金额
        BigDecimal notaxRealAmount = calculateResultModel.getNotaxAmount();
        notaxRealAmountTotal = notaxRealAmountTotal.add(notaxRealAmount);
        //不含税实际单价
        BigDecimal notaxRealPrice = calculateResultModel.getNotaxPrice();
        //税额
        BigDecimal taxAmount = calculateResultModel.getTaxAmount();
        taxAmountTotal = taxAmountTotal.add(taxAmount);

        copyDtl.setAmount(amount);
        copyDtl.setLineDiscountAmount(lineRoundOff);
        copyDtl.setRealAmount(realAmount);
        copyDtl.setRealPrice(realPrice);
        copyDtl.setNotaxRealAmount(notaxRealAmount);
        copyDtl.setNotaxRealPrice(notaxRealPrice);
        copyDtl.setTaxAmount(taxAmount);
        
	}
	
	/**
	 * 
	 * @Description: 计算剂量的总数量
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月27日 下午5:47:08  
	 * @return void
	 */
	private BigDecimal setSHTotalQuantity(BigDecimal singleDose,BigDecimal dose) {
		//总数量
		BigDecimal totalQuantity = singleDose.multiply(dose).setScale(6,BigDecimal.ROUND_HALF_UP);
		return totalQuantity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output, Long id) throws Exception {
		SalePricing pric = salePricingMapper.selectByPrimaryKey(id);
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(pric.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("划价单");
		StringBuilder titleSecondRow = new StringBuilder();
		String strDate = pric.getPricingDate() == null ? "" : DateUtils.DateToString(pric.getPricingDate(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN);
		titleSecondRow.append("划价单号：").append(pric.getCode())
		.append("  划价日期：").append(Optional.ofNullable(strDate).orElse(""))
		.append("  划价人员：").append(Optional.ofNullable(pric.getCreaterName()).orElse(""))
		.append("  会员卡号：").append(Optional.ofNullable(pric.getMemberCardCode()).orElse(""))
		.append("  购药者姓名：").append(Optional.ofNullable(pric.getConsumerName()).orElse(""))
		.append("  剂量：").append(Optional.ofNullable(pric.getDose()).orElse(new BigDecimal(0)))
		.append("  备注：").append(Optional.ofNullable(pric.getRemark()).orElse(""));
		
		List<String> titleSecondRowList = new ArrayList<>();
        titleSecondRowList.add(titleSecondRow.toString());
        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
        rowHeaderMap.put("goodsCode","商品编码");
        rowHeaderMap.put("genericName","通用名称");
        rowHeaderMap.put("dosageName","剂型");
        rowHeaderMap.put("specification","规格");
        rowHeaderMap.put("unitName","单位");
        rowHeaderMap.put("manufacturer","生产厂商");
        rowHeaderMap.put("lotNumber","批号");
        rowHeaderMap.put("productDateStr","生产日期");
        rowHeaderMap.put("validDateStr","有效期至");
        rowHeaderMap.put("shelfName","货位");
        rowHeaderMap.put("quantity","数量");
        rowHeaderMap.put("unitPrice","单价");
        rowHeaderMap.put("lineDiscount","折扣");
        rowHeaderMap.put("amount","金额");
        rowHeaderMap.put("taxRate","税率(%)");
        rowHeaderMap.put("notaxRealPrice","不含税单价");
        rowHeaderMap.put("notaxRealAmount","不含税金额");
        rowHeaderMap.put("taxAmount","税额");
        rowHeaderMap.put("remark", "备注");
        
        StringBuilder endString = new StringBuilder();
        endString.append("总商品金额：").append(pric.getAmountTotal()).append(";")
        .append("折扣： ").append(pric.getWholeDiscount()).append("%   ").append(pric.getAmountTotal().multiply(new BigDecimal(100).subtract(pric.getWholeDiscount()))).append(";")
        .append("优惠：").append(pric.getWholeDiscountAmount()).append(";")
        .append("总额：").append(pric.getAmountTotal()).append(";")
        .append("不含税总额：").append(pric.getNotaxRealAmountTotal()).append(";")
        .append("税额：").append(pric.getTaxAmountTotal());
        
        List<SalePricingExcel> listExcel = salePricingShelfMapper.getPricingShelfGoodsExcel(id);
        listExcel.forEach(item->{
        	item.setProductDateStr(DateUtils.DateToString(item.getProductDate(), "yyyy-MM-dd"));
        	item.setValidDateStr(DateUtils.DateToString(item.getValidDate(), "yyyy-MM-dd"));
        });
        
        purchaseGeneralComponent.commExcelExport(output,rowHeaderMap,listExcel,headerList,titleSecondRowList,endString.toString(),true,new ArrayList<>());
        
	}

	@Override
	public List<SelectPricingGoodsVO> selectPricingGoods(UserVO userVO,String param, int pageNo, int pageSize)
			throws Exception {

		GoodsParamStockVO goodsParamStockVO = new GoodsParamStockVO();
		goodsParamStockVO.setPrescriptionType(0);//划价单只搜索中药
		goodsParamStockVO.setParam(param);
		return commonComponent.selectGoodsByParam(userVO,goodsParamStockVO);
	}

	@Override
	public List<SelectPricingGoodsVO> selectGoodsByParam(UserVO userVO,GoodsParamStockVO goodsParamStockVO) {
		return commonComponent.selectGoodsByParam(userVO,goodsParamStockVO);
	}

	 @Override
	 public List<SelectPricingGoodsVO> selectLockGoodsByParam(GoodsParamStockVO goodsParamStockVO) {
		 List<SelectPricingGoodsVO> list = salePricingMapper.selectLockGoodsByParam(goodsParamStockVO);

		 list.forEach(item->{
			 GoodsShelfStatusDescVO goodsShelfStatusDescVO = chGoodsLoadService.getGoodsShelfStatusDesc(goodsParamStockVO.getEnterpriseId(),item.getShelfId());
			 item.setShelfStatusDesc(goodsShelfStatusDescVO.getShelfStatusDesc());
			 item.setProductDateStr(DateUtils.DateToString(item.getProductDate(), DateStyle.YYYY_MM_DD));
			 item.setValidUntilStr(DateUtils.DateToString(item.getValidUntil(), DateStyle.YYYY_MM_DD));
		 });
		 return list;
	 }

	@Override
	public List<SelectPricingGoodsLotShelfVO> selectGoodsLotShelf(Long enterpriseId, Long goodsId) {
		return salePricingMapper.selectGoodsLotShelf(enterpriseId, goodsId);
	}

	@Override
	public List<SelectMemberTypeVO> selectMemberType(Long enterpriseId, String param) throws Exception {
		List<SelectMemberTypeVO> list = salePricingMapper.selectMemberType(enterpriseId, param);
		list.forEach(item -> {
			if(0 == item.getPriceStrategy()) {
				item.setPriceStrategyStr("零售价");
			}
			if(1 == item.getPriceStrategy()) {
				item.setPriceStrategyStr("会员价");
			}
		});
		return list;
	}

	
	
}

