package com.rograndec.feijiayun.chain.business.purchase.order.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierBusinessMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierBusiness;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.purchase.order.approvalProcessor.PurchaseOrderApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.order.approvalProcessor.PurchaseOrderCancelApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderModifyRecord;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.service.PurchaseOrderService;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.EnterpriseOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.GoodsOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderCountVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailRequestVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailResponseVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherRequestVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherResponseVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderReqVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderResponseVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseUserOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.RecordVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.SaleManOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.SupplierOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.UserOrderVO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.ModifyRecordCompoent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

/**
 * 采购订单接口
 * @author 孙帮祥
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

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
    private SupplierBusinessMapper supplierBusinessMapper;
    @Autowired
    private SupplierSalerMapper supplierSalerMapper;
    @Autowired 
    private GoodsMapper goodsMapper;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	@Autowired
	private ManageConfigService manageConfigService;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private ApprovalFlowComponent approvalFlowComponent;
	@Autowired
	private PurchaseOrderApprovalProcessor purchaseOrderApprovalProcessor;
	@Autowired
	private PurchaseOrderCancelApprovalProcessor purchaseOrderCancelApprovalProcessor;
	@Autowired
	private ManageConfigMapper manageConfigMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PurchaseOrderModifyRecordMapper purchaseOrderModifyRecordMapper;
	@Autowired
	private ModifyRecordCompoent modifyRecordCompoent;
	@Autowired  
	private GoodsTaxRateMapper goodsTaxRateMapper;
	@Autowired
	private GoodsBusinessMapper goodsBusinessMapper;
	@Autowired
	private RedisComponent redisTemplate;
	@Autowired
    private RedisComponent redisComponent;
	
	@Autowired
	private SafetyStockMapper safetyStockMapper;
	
    /**
     * 保存采购订单,采购详情，采购订单配送和结算
     * @param order
     * @return
     * @throws Exception 
     */
    @Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void addOrder(UserVO userVO,PurchaseOrderReqVO purchaseOrderRequestVO) throws Exception,BusinessException{
		Integer status = PurchaseStatus.WAIT_RECEIVE.getStatus();// 订单状态 待收货
    	ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
		if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
			status = PurchaseStatus.PENDING_AUDIT.getStatus();// 待审核状态
		}
    	PurchaseOrder purchaseOrder=new PurchaseOrder();
    	PurchaseOrderOtherRequestVO purchaseOrderOtherRequestVO=purchaseOrderRequestVO.getPurchaseOrderOtherRequestVO();
    	List<PurchaseOrderDetailRequestVO> purchaseOrderRequestVOList=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO();
    	Set cearRepeat=new HashSet();//获取品种数量
    	BeanUtils.copyProperties(purchaseOrderRequestVO,purchaseOrder);
    	EntityUtils.reflectAddSetDefaultValue(purchaseOrder.getClass(),userVO);//复制用户基本信息到基础表
    	purchaseOrder.setCreateTime(new Date());
    	purchaseOrder.setEnterpriseId(userVO.getEnterpriseId());//插入企业id
    	purchaseOrder.setParentId(userVO.getParentId());//插入父级id
    	purchaseOrder.setCreaterId(userVO.getUserId());
    	purchaseOrder.setCreaterCode(userVO.getUserCode());
    	purchaseOrder.setCreaterName(userVO.getUserName());
    	purchaseOrder.setModifierId(userVO.getUserId());
    	purchaseOrder.setModifierCode(userVO.getUserCode());
    	purchaseOrder.setModifierName(userVO.getUserName());
    	long supplier_id=purchaseOrder.getSupplierId();//根据供货单位id查询供货单位信息
    	Supplier supplier=supplierMapper.selectByPrimaryKey(supplier_id);
    	if(supplier!=null){
    		purchaseOrder.setSupplierId(supplier.getId());
    		purchaseOrder.setSupplierName(supplier.getName());
        	purchaseOrder.setSupplierCode(supplier.getCode());
    	}
    	SupplierSaler supplierSaler=supplierSalerMapper.selectByPrimaryKey(purchaseOrder.getSupplierSalerId());
		if(supplierSaler!=null){
			purchaseOrder.setSupplierSalerId(supplierSaler.getId());
    		purchaseOrder.setSupplierSalerCode(supplierSaler.getCode());
    		purchaseOrder.setSupplierSalerName(supplierSaler.getName());
			purchaseOrder.setSupplierSalerPhone(supplierSaler.getTelephone());
		}
    	purchaseOrder.setStatus(status);//采购订单状态
    	purchaseOrder.setOrderMode(1);//采购方式，现在默认为标准采购
        String code=orderCodeComponent.generate(OrderRule.PURCHASE_ORDER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        purchaseOrder.setCode(code);
        purchaseOrder.setOrderType(OrderRule.PURCHASE_ORDER.getType());//订单类型
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;
        if(zl){//质量流程开启的时候不必提供采购人员ID
        	purchaseOrder.setPurchaserId(userVO.getUserId());
        	purchaseOrder.setPurchaserCode(userVO.getUserCode());
        	purchaseOrder.setPurchaserName(userVO.getUserName());
        }else{//质量流程关闭的时候前台必须提供采购人员id
        	User user=userMapper.selectByPrimaryKey(purchaseOrder.getPurchaserId());
        	if(user!=null){
        	 	purchaseOrder.setPurchaserId(purchaseOrder.getPurchaserId());
            	purchaseOrder.setPurchaserCode(user.getCode());
            	purchaseOrder.setPurchaserName(user.getName());
        	}
        }
        BigDecimal amountTotal=new BigDecimal(0);//金额合计
        BigDecimal quantityTotal=new BigDecimal(0);//数量合计
    	for(int i=0;i<purchaseOrderRequestVOList.size();i++){//计算合计
    		//在此处判断 单价unitPrice，数量quantity，行折扣line_discount,税率tax_rate,|整单折扣whole_discount，整单优惠金额whole_discount_amount，
    		PurchaseOrderDetailRequestVO purchaseOrderDetailRequestVO=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO().get(i);
				if(purchaseOrderDetailRequestVO.getUnitPrice().compareTo(BigDecimal.ZERO)==-1){//如果单价小于0
			    	throw new BusinessException("单价不能小于0");
				}
				if(purchaseOrderDetailRequestVO.getQuantity().compareTo(BigDecimal.ZERO)==-1){//如果数量小于0
					throw new BusinessException("数量不能小于0");
				}
				if(purchaseOrderDetailRequestVO.getLineDiscount().compareTo(BigDecimal.ZERO)==-1){//如果折扣小于0
					throw new BusinessException("折扣不能小于0");
				}else if(purchaseOrderDetailRequestVO.getLineDiscount().compareTo(BigDecimal.ZERO)==0){//如果折扣等于0
					throw new BusinessException("折扣不能等于0");
				}
				/*if(purchaseOrderDetailRequestVO.getTaxRate().compareTo(BigDecimal.ZERO)==-1){//如果税率小于0
					throw new BusinessException("税率不能小于0");
				}*/
				if(purchaseOrderDetailRequestVO.getTaxRateId()==null){
						throw new BusinessException("税率ID不能为空");
				}else{
					GoodsTaxRate goodsTaxRate=goodsTaxRateMapper.selectByPrimaryKey(purchaseOrderDetailRequestVO.getTaxRateId());
					if(goodsTaxRate==null){
						throw new BusinessException("没有该税率值，请核实税率ID");
					}else{
						purchaseOrderDetailRequestVO.setTaxRate(goodsTaxRate.getTaxRate());
					}
					
				}
				/*else if(purchaseOrderDetailRequestVO.getTaxRate().compareTo(BigDecimal.ZERO)==0){//如果税率等于0
					throw new BusinessException("税率不能等于0");
				}*/
    		//计算金额合计
    		BigDecimal amount=CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetailRequestVO.getQuantity(), purchaseOrderDetailRequestVO.getUnitPrice(), purchaseOrderDetailRequestVO.getLineDiscount());
    		amountTotal=amountTotal.add(amount);
    		quantityTotal=quantityTotal.add(purchaseOrderDetailRequestVO.getQuantity());
    		cearRepeat.add(purchaseOrderDetailRequestVO.getGoodsId());
    	}
    	if(purchaseOrder.getWholeDiscount().compareTo(BigDecimal.ZERO)==-1){//整单折扣不能小于0
    		throw new BusinessException("整单折扣不能小于0");
    	}
    	if(purchaseOrder.getWholeDiscountAmount().compareTo(BigDecimal.ZERO)==-1){//整单优惠不能小于0
    		throw new BusinessException("整单优惠不能小于0");
    	}
		//以下是计算不含税金额与税额的（明细表）
        //add subtract multiply divide(加减乘除)
    	//定义不含税金额，与税额
    	BigDecimal notaxRealAmountTotal=new BigDecimal(0);//不含税总额 
        BigDecimal taxAmountTotal=new BigDecimal(0);//税额
    	for(int i=0;i<purchaseOrderRequestVOList.size();i++){//计算合计
			PurchaseOrderDetailRequestVO purchaseOrderDetailRequestVO=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO().get(i);
   			//根据数量、单价、行折扣获取金额（整单折扣金额）：数量*单价*行折扣 amount 有可能是0
 			BigDecimal amount=CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetailRequestVO.getQuantity(), purchaseOrderDetailRequestVO.getUnitPrice(), purchaseOrderDetailRequestVO.getLineDiscount());
 			BigDecimal lineDiscountAmount=new BigDecimal(0);
 			
 			if(amount.compareTo(BigDecimal.ZERO)==1){//大于0
 				lineDiscountAmount=CalculateComponent.getLineRoundOffByLineAmountAndWholeAmountTotal(purchaseOrder.getWholeDiscountAmount(),amount,amountTotal);
 				//lineDiscountAmount=purchaseOrder.getWholeDiscountAmount().multiply(amount.divide(amountTotal,2, BigDecimal.ROUND_HALF_UP));
    		}
    		//计算实际金额，根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
    		BigDecimal realAmount=CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetailRequestVO.getQuantity(),purchaseOrderDetailRequestVO.getUnitPrice(),purchaseOrderDetailRequestVO.getLineDiscount(),purchaseOrder.getWholeDiscount(),lineDiscountAmount);
    		//计算实际单价   ：实际金额/数量
    		BigDecimal realPrice=new BigDecimal(0);
    		if(purchaseOrderDetailRequestVO.getQuantity().compareTo(BigDecimal.ZERO)==1){//大于0
  		   	  realPrice =CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, purchaseOrderDetailRequestVO.getQuantity());
  		    }
     		//获取不含税金额 
    		//BigDecimal val1=new BigDecimal(1);
    		//BigDecimal notaxRealAmount= realAmount.divide(val1.add(purchaseOrderDetailRequestVO.getTaxRate()),2,BigDecimal.ROUND_HALF_UP);
    		BigDecimal notaxRealAmount=CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(realAmount,purchaseOrderDetailRequestVO.getTaxRate());
    		//计算不含税单价
    		BigDecimal notaxRealPrice=new BigDecimal(0);
    		if(purchaseOrderDetailRequestVO.getQuantity().compareTo(BigDecimal.ZERO)==1){//如果不含税金额大于0的时候
    	   	    notaxRealPrice=CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxRealAmount,purchaseOrderDetailRequestVO.getQuantity());
    		}
    		//获取税额   实际金额-不含税金额
    		//BigDecimal taxAmount=realAmount.subtract(notaxRealAmount);
    		BigDecimal taxAmount=CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(realAmount,notaxRealAmount);
    		purchaseOrderDetailRequestVO.setNotaxRealAmount(notaxRealAmount);//不含税金额
    		purchaseOrderDetailRequestVO.setNotaxRealPrice(notaxRealPrice);//不含税实际单价
    		purchaseOrderDetailRequestVO.setRealAmount(realAmount);//实际金额
    		purchaseOrderDetailRequestVO.setRealPrice(realPrice);//实际单价
    		purchaseOrderDetailRequestVO.setTaxAmount(taxAmount);//税额
			//计算订单详情里面的金额
    		purchaseOrderDetailRequestVO.setAmount(amount);
			//行优惠（行舍入）优惠分摊    计算优惠分摊    
    		purchaseOrderDetailRequestVO.setWholeDiscount(purchaseOrder.getWholeDiscount());//整单折扣
			purchaseOrderDetailRequestVO.setLineDiscountAmount(lineDiscountAmount);

			notaxRealAmountTotal=notaxRealAmountTotal.add(notaxRealAmount);//不含税金额总额
			taxAmountTotal=taxAmountTotal.add(taxAmount);
    	}
    	//实际金额合计
    	BigDecimal realAmountTotal =amountTotal.multiply(purchaseOrder.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).subtract(purchaseOrder.getWholeDiscountAmount());
    	//不含税总额
		//税额
    	purchaseOrder.setVarietiesQuantity(cearRepeat.size());
    	purchaseOrder.setQuantityTotal(quantityTotal);
    	purchaseOrder.setAmountTotal(amountTotal);//总额（优惠前金额合计）
    	purchaseOrder.setRealAmountTotal(realAmountTotal);//实际金额合计
    	purchaseOrder.setNotaxRealAmountTotal(notaxRealAmountTotal);
    	purchaseOrder.setTaxAmountTotal(taxAmountTotal);
    	purchaseOrder.setUpdateTime(new Date());
        purchaseOrderMapper.insert(purchaseOrder);//插入订单基表信息
    		for(int i=0;i<purchaseOrderRequestVOList.size();i++){
    			PurchaseOrderDetail purchaseOrderDetail=new PurchaseOrderDetail();
    			PurchaseOrderDetailRequestVO purchaseOrderDetailRequestVO=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO().get(i);
    			BeanUtils.copyProperties(purchaseOrderDetailRequestVO,purchaseOrderDetail);//订单详情从vo复制到entity
    			EntityUtils.reflectAddSetDefaultValue(purchaseOrderDetail.getClass(),userVO);
    			//以下为商品信息
    			Goods goods=goodsMapper.selectByPrimaryKey(purchaseOrderDetail.getGoodsId());//根据商品id获取商品信息
    			purchaseOrderDetail.setId(null);
    			if(goods!=null){
    				purchaseOrderDetail.setGoodsId(goods.getId());
    				purchaseOrderDetail.setGoodsCode(goods.getCode());
        			purchaseOrderDetail.setGoodsName(goods.getName());
        			purchaseOrderDetail.setBarcode(goods.getBarcode());
        			purchaseOrderDetail.setGoodsGenericName(goods.getGenericName());
        			purchaseOrderDetail.setDosageId(goods.getDosageId());//剂型ID
        			purchaseOrderDetail.setDosageName(goods.getDosageName());
        			purchaseOrderDetail.setUnitId(goods.getUnitId());
        			purchaseOrderDetail.setUnitName(goods.getUnitName());
        			purchaseOrderDetail.setGoodsPlace(goods.getPlace());
        			purchaseOrderDetail.setGoodsSpecification(goods.getSpecification());
        			purchaseOrderDetail.setManufacturerId(goods.getManufacturerId());//生产厂商ID
        			purchaseOrderDetail.setManufacturer(goods.getManufacturer());
        			purchaseOrderDetail.setApprovalNumber(goods.getApprovalNumber());
    			}
    			purchaseOrderDetail.setOrderCode(code);
    			purchaseOrderDetail.setOrderType(OrderRule.PURCHASE_ORDER.getType());
    			purchaseOrderDetail.setOrderId(purchaseOrder.getId());
    			purchaseOrderDetail.setOrderDate(purchaseOrder.getOrderDate());
    			purchaseOrderDetail.setWholeDiscount(purchaseOrder.getWholeDiscount());
    			//以下为企业信息
    			purchaseOrderDetail.setStatus(status);
    			purchaseOrderDetail.setEnterpriseId(userVO.getEnterpriseId());
    			purchaseOrderDetail.setParentId(userVO.getParentId());
    			purchaseOrderDetail.setCreaterId(userVO.getUserId());
    			purchaseOrderDetail.setCreaterCode(userVO.getUserCode());
    			purchaseOrderDetail.setCreaterName(userVO.getUserName());
    			purchaseOrderDetail.setModifierId(userVO.getUserId());
    			purchaseOrderDetail.setModifierCode(userVO.getUserCode());
    			purchaseOrderDetail.setModifierName(userVO.getUserName());
    			purchaseOrderDetail.setCreateTime(new Date());
    			purchaseOrderDetail.setUpdateTime(new Date());

    			purchaseOrderDetail.setUnclearQuantity(purchaseOrderDetail.getQuantity());
    			purchaseOrderDetail.setClearQuantity(BigDecimal.ZERO);

    			//获取 零售基价 会员基价 配货基价
    		    Map map=new HashMap();
    		    map.put("enterpriseId", userVO.getEnterpriseId());
    		    map.put("goodsId", goods.getId());
    		    GoodsBusiness goodsBusiness=goodsBusinessMapper.selectByEnterpriseIdAndGoodsId(map);
    		    if(goodsBusiness!=null){
    		    	    purchaseOrderDetail.setRetailPrice(goodsBusiness.getRetailPrice());
    		    	    purchaseOrderDetail.setMemberPrice(goodsBusiness.getMemberPrice());
    		    	    purchaseOrderDetail.setDistrPrice(goodsBusiness.getDistrPrice());
    		    }
    			purchaseOrderDetailMapper.insert(purchaseOrderDetail);//插入订单详情信息  //此处有个坑
    	}
        PurchaseOrderOther purchaseOrderOther =new PurchaseOrderOther();
        BeanUtils.copyProperties(purchaseOrderRequestVO.getPurchaseOrderOtherRequestVO(),purchaseOrderOther);//采购订单配送和结算从vo复制到entity
        EntityUtils.reflectAddSetDefaultValue(purchaseOrderOther.getClass(),userVO);
        //获取结算单位信息
        if(purchaseOrderOther.getSettlementUnitId()==null){
        	throw new BusinessException("结算单位ID不能为空");
        }
        Enterprise settlementUnit=enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getSettlementUnitId());//结算单位
        if(settlementUnit!=null){
        	purchaseOrderOther.setSettlementUnitName(settlementUnit.getName());
        }
        Enterprise receiveUnit=enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getReceiveUnitId());//收货单位
        User receiver=userMapper.selectByPrimaryKey(purchaseOrderOther.getReveiverId());//收货人员
        if(receiveUnit!=null){
        	purchaseOrderOther.setReceiveUnitName(receiveUnit.getName());
        	purchaseOrderOther.setReceiveUnitCode(receiveUnit.getCode());
        }
        if(receiver!=null){
        	purchaseOrderOther.setReceiverCode(receiver.getCode());
        	purchaseOrderOther.setReceiverName(receiver.getName());
        }
        purchaseOrderOther.setOrderId(purchaseOrder.getId());
        purchaseOrderOther.setEnterpriseId(userVO.getEnterpriseId());
        purchaseOrderOther.setParentId(userVO.getParentId());
        purchaseOrderOther.setStatus(status);
        purchaseOrderOther.setCreaterId(userVO.getUserId());
        purchaseOrderOther.setCreaterCode(userVO.getUserCode());
        purchaseOrderOther.setCreaterName(userVO.getUserName());
        purchaseOrderOther.setModifierId(userVO.getUserId());
        purchaseOrderOther.setModifierCode(userVO.getUserCode());
        purchaseOrderOther.setModifierName(userVO.getUserName());
        purchaseOrderOther.setUpdateTime(new Date());
        purchaseOrderOther.setCreateTime(new Date());
    	purchaseOrderOtherMapper.insert(purchaseOrderOther);
    	// 提交流程审批
		if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
			 Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), enterprise.getName(),
        		userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
        		userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                ApprovalFlowContentModel.getPurchaseOrderCode(), purchaseOrder.getId(), "", "");
        approvalFlowComponent.apply(submitApprovalFlowVO, userVO);
	        }
		removeDraftCach(userVO.getEnterpriseId(), OrderRule.PURCHASE_ORDER.getCodePrefix(), purchaseOrderRequestVO.getRedisKeyValue());
    }
    /**
     * 通过采购计划保存采购订单,采购详情，采购订单配送和结算（因为很多参数没有提供因此计算内容只有一个金额合计）
     * @param order
     * @return
     * @throws Exception 
     */
    @Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String addOrderByPlan(UserVO userVO,PurchaseOrderVO purchaseOrderVO) throws Exception{
    	PurchaseOrder purchaseOrder=new PurchaseOrder();
    	PurchaseOrderOtherVO purchaseOrderOtherRequestVO=purchaseOrderVO.getPurchaseOrderOtherVO();
    	List<PurchaseOrderDetailVO> purchaseOrderRequestVOList=purchaseOrderVO.getPurchaseOrderDetailVO();
    	BeanUtils.copyProperties(purchaseOrderVO,purchaseOrder);
    	EntityUtils.reflectAddSetDefaultValue(purchaseOrder.getClass(),userVO);//复制用户基本信息到基础表
    	purchaseOrder.setCreateTime(new Date());
    	purchaseOrder.setEnterpriseId(userVO.getEnterpriseId());//插入企业id
    	purchaseOrder.setParentId(userVO.getParentId());//插入父级id
    	purchaseOrder.setCreaterId(userVO.getUserId());
    	purchaseOrder.setCreaterCode(userVO.getUserCode());
    	purchaseOrder.setCreaterName(userVO.getUserName());
    	purchaseOrder.setModifierId(userVO.getUserId());
    	purchaseOrder.setModifierCode(userVO.getUserCode());
    	purchaseOrder.setModifierName(userVO.getUserName());
    	purchaseOrder.setUpdateTime(new Date());
    	purchaseOrder.setArrivalTime(new Date());//预计到货日期
    	long supplier_id=purchaseOrder.getSupplierId();//根据供货单位id查询供货单位信息
    	Supplier supplier=supplierMapper.selectByPrimaryKey(supplier_id);
    	if(supplier!=null){
    		purchaseOrder.setSupplierId(supplier.getId());
    		purchaseOrder.setSupplierName(supplier.getName());
        	purchaseOrder.setSupplierCode(supplier.getCode());
        	purchaseOrder.setSupplierSalerId(supplier.getSaleManId());
    		purchaseOrder.setSupplierSalerCode(supplier.getSaleManCode());
    		purchaseOrder.setSupplierSalerName(supplier.getSaleManName());
    		purchaseOrder.setSupplierSalerPhone(supplier.getTel());
    	}
    	/*if(purchaseOrder.getSupplierSalerId()!=null){
    		long supplier_saler_id=purchaseOrder.getSupplierSalerId();
    		SupplierSaler supplierSaler=supplierSalerMapper.selectByPrimaryKey(supplier_saler_id);//根据供货单位销售人员获取相关信息
        	if(supplierSaler!=null){
        		purchaseOrder.setSupplierSalerId(supplierSaler.getId());
        		purchaseOrder.setSupplierSalerCode(supplierSaler.getCode());
        		purchaseOrder.setSupplierSalerName(supplierSaler.getName());
        		purchaseOrder.setSupplierSalerPhone(supplierSaler.getMobilePhone());
        	}
    	}*/
    	purchaseOrder.setStatus(PurchaseStatus.getCode("待收货"));//采购订单状态
    	purchaseOrder.setOrderMode(1);//采购方式，现在默认为标准采购
        String code=orderCodeComponent.generate(OrderRule.PURCHASE_ORDER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        purchaseOrder.setCode(code);
        purchaseOrder.setOrderType(OrderRule.PURCHASE_ORDER.getType());//订单类型
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;
        if(zl){
        	purchaseOrder.setPurchaserId(userVO.getUserId());
        	purchaseOrder.setPurchaserCode(userVO.getUserCode());
        	purchaseOrder.setPurchaserName(userVO.getUserName());
        }else{
        	User user=userMapper.selectByPrimaryKey(purchaseOrder.getPurchaserId());
        	if(user!=null){
        		purchaseOrder.setPurchaserId(purchaseOrder.getPurchaserId());
            	purchaseOrder.setPurchaserCode(user.getCode());
            	purchaseOrder.setPurchaserName(user.getName());
        	}
        }
        BigDecimal amountTotal=new BigDecimal(0);//金额合计
    	for(int i=0;i<purchaseOrderRequestVOList.size();i++){//计算合计
			PurchaseOrderDetailVO purchaseOrderDetailVO=purchaseOrderVO.getPurchaseOrderDetailVO().get(i);
			//计算金额合计
    		BigDecimal amount=CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetailVO.getQuantity(), purchaseOrderDetailVO.getUnitPrice(), purchaseOrderDetailVO.getLineDiscount());
    		amountTotal=amountTotal.add(amount);
    	}
    	purchaseOrder.setAmountTotal(amountTotal);//计算金额合计
        purchaseOrderMapper.insert(purchaseOrder);//插入订单基表信息
    		for(int i=0;i<purchaseOrderRequestVOList.size();i++){
    			PurchaseOrderDetail purchaseOrderDetail=new PurchaseOrderDetail();
    			PurchaseOrderDetailVO purchaseOrderDetailRequestVO=purchaseOrderVO.getPurchaseOrderDetailVO().get(i);
    			BeanUtils.copyProperties(purchaseOrderDetailRequestVO,purchaseOrderDetail);//订单详情从vo复制到entity
    			EntityUtils.reflectAddSetDefaultValue(purchaseOrderDetail.getClass(),userVO);
    			//以下为商品信息
    			Goods goods=goodsMapper.selectByPrimaryKey(purchaseOrderDetail.getGoodsId());//根据商品id获取商品信息
    			if(goods!=null){
    				purchaseOrderDetail.setGoodsId(goods.getId());
    				purchaseOrderDetail.setGoodsCode(goods.getCode());
        			purchaseOrderDetail.setGoodsName(goods.getName());
        			purchaseOrderDetail.setBarcode(goods.getBarcode());
        			purchaseOrderDetail.setGoodsGenericName(goods.getGenericName());
        			purchaseOrderDetail.setDosageId(goods.getDosageId());//剂型ID
        			purchaseOrderDetail.setDosageName(goods.getDosageName());
        			purchaseOrderDetail.setUnitId(goods.getUnitId());
        			purchaseOrderDetail.setUnitName(goods.getUnitName());
        			purchaseOrderDetail.setGoodsPlace(goods.getPlace());
        			purchaseOrderDetail.setGoodsSpecification(goods.getSpecification());
        			purchaseOrderDetail.setManufacturerId(goods.getManufacturerId());//生产厂商ID
        			purchaseOrderDetail.setManufacturer(goods.getManufacturer());
        			purchaseOrderDetail.setApprovalNumber(goods.getApprovalNumber());
    			}
    			purchaseOrderDetail.setOrderCode(code);
    			purchaseOrderDetail.setOrderType(OrderRule.PURCHASE_ORDER.getType());
    			purchaseOrderDetail.setOrderId(purchaseOrder.getId());
    			purchaseOrderDetail.setOrderDate(purchaseOrder.getOrderDate());
    			purchaseOrderDetail.setStatus(purchaseOrder.getStatus());
    			BigDecimal cq=new BigDecimal(0);
    			purchaseOrderDetail.setClearQuantity(cq);//已清数量为0
    			purchaseOrderDetail.setUnclearQuantity(purchaseOrderDetail.getQuantity());//未清数量
    			//以下为企业信息
    			purchaseOrderDetail.setEnterpriseId(userVO.getEnterpriseId());
    			purchaseOrderDetail.setParentId(userVO.getParentId());
    			purchaseOrderDetail.setCreaterId(userVO.getUserId());
    			purchaseOrderDetail.setCreaterCode(userVO.getUserCode());
    			purchaseOrderDetail.setCreaterName(userVO.getUserName());
    			purchaseOrderDetail.setModifierId(userVO.getUserId());
    			purchaseOrderDetail.setModifierCode(userVO.getUserCode());
    			purchaseOrderDetail.setModifierName(userVO.getUserName());
    			purchaseOrderDetail.setCreateTime(new Date());
    			purchaseOrderDetail.setUpdateTime(new Date());
    			//获取 零售基价 会员基价 配货基价
    		    Map map=new HashMap();
    		    map.put("enterpriseId", userVO.getEnterpriseId());
    		    map.put("goodsId", goods.getId());
    		    GoodsBusiness goodsBusiness=goodsBusinessMapper.selectByEnterpriseIdAndGoodsId(map);
    		    if(goodsBusiness!=null){
    		    	    purchaseOrderDetail.setRetailPrice(goodsBusiness.getRetailPrice());
    		    	    purchaseOrderDetail.setMemberPrice(goodsBusiness.getMemberPrice());
    		    	    purchaseOrderDetail.setDistrPrice(goodsBusiness.getDistrPrice());
    		    }
    			purchaseOrderDetailMapper.insert(purchaseOrderDetail);//插入订单详情信息  //此处有个坑
    	}
        PurchaseOrderOther purchaseOrderOther =new PurchaseOrderOther();
        BeanUtils.copyProperties(purchaseOrderVO.getPurchaseOrderOtherVO(),purchaseOrderOther);//采购订单配送和结算从vo复制到entity
        EntityUtils.reflectAddSetDefaultValue(purchaseOrderOther.getClass(),userVO);

		purchaseOrderOther.setReveiverId(manage.getReceiverId());
		purchaseOrderOther.setReceiverName(manage.getReceiverName());
		purchaseOrderOther.setReceiverCode(manage.getReceiverCode());

		purchaseOrderOther.setSettlementType(0);// 现结
		purchaseOrderOther.setInvoiceType(0);// 普通发票
		purchaseOrderOther.setSettlementUnitId(userVO.getEnterpriseId());// 结算单位
		purchaseOrderOther.setSettlementUnitName(userVO.getEnterpriseName());
		purchaseOrderOther.setReceiveUnitId(userVO.getEnterpriseId());//收货单位
		purchaseOrderOther.setReceiveUnitName(userVO.getEnterpriseName());
		purchaseOrderOther.setReceiveUnitCode(userVO.getEnterpriseCode());

		purchaseOrderOther.setCarriageUnit(userVO.getEnterpriseName());//承运单位
        purchaseOrderOther.setOrderId(purchaseOrder.getId());
        purchaseOrderOther.setEnterpriseId(userVO.getEnterpriseId());
        purchaseOrderOther.setParentId(userVO.getParentId());
        purchaseOrderOther.setStatus(purchaseOrder.getStatus());
        purchaseOrderOther.setCreaterId(userVO.getUserId());
        purchaseOrderOther.setCreaterCode(userVO.getUserCode());
        purchaseOrderOther.setCreaterName(userVO.getUserName());
        purchaseOrderOther.setModifierId(userVO.getUserId());
        purchaseOrderOther.setModifierCode(userVO.getUserCode());
        purchaseOrderOther.setModifierName(userVO.getUserName());
        purchaseOrderOther.setCreateTime(new Date());
        purchaseOrderOther.setUpdateTime(new Date());
    	purchaseOrderOtherMapper.insert(purchaseOrderOther);
        return purchaseOrder.getCode();
    }
    /**
     * 修改采购订单,采购详情，采购订单配送和结算
     * @param order
     * @return
     * @throws Exception 
     */
    @Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateOrder(UserVO userVO,PurchaseOrderReqVO purchaseOrderRequestVO) throws Exception,BusinessException{
    	ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
    	//先查询一下数据库获取采购订单的数据
    	if(purchaseOrderRequestVO.getId()==null){
    		throw new BusinessException("订单id不能为空");
    	}
    	PurchaseOrder purchaseOrderOld=purchaseOrderMapper.selectByPrimaryKey(purchaseOrderRequestVO.getId());
    	if(purchaseOrderOld==null){
    		throw new BusinessException("该订单不存在");
    	}
    	if(purchaseOrderOld.getStatus().equals(PurchaseStatus.PENDING_AUDIT.getStatus())){//如果该状态是待审核状态
    		throw new BusinessException("待审核状态不允许修改");
    	}
    	if(purchaseOrderOld.getStatus().equals(PurchaseStatus.WAIT_CHECK.getStatus())){//如果该状态是待验收状态
    		throw new BusinessException("待验收状态不允许修改");
    	}
    	if(purchaseOrderOld.getStatus().equals(PurchaseStatus.WAIT_STORAGE.getStatus())){//如果该状态是待入库状态
    		throw new BusinessException("待入库状态不允许修改");
    	}
    	if(purchaseOrderOld.getStatus().equals(PurchaseStatus.FINISHED.getStatus())){//如果该状态是已完成状态
    		throw new BusinessException("已完成不允许修改");
    	}
    	if(purchaseOrderOld.getStatus().equals(PurchaseStatus.CANCELED.getStatus())){//如果该状态是已取消状态
    		throw new BusinessException("已取消状态不允许修改");
    	}
    	PurchaseOrder purchaseOrder=new PurchaseOrder();
    	PurchaseOrderOtherRequestVO purchaseOrderOtherRequestVO=purchaseOrderRequestVO.getPurchaseOrderOtherRequestVO();
    	List<PurchaseOrderDetailRequestVO> purchaseOrderRequestVOList=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO();

    	/**
    	 * 处理修改记录逻辑start
    	 * */
    	for(int i=0;i<purchaseOrderRequestVOList.size();i++){
			Goods goods=goodsMapper.selectByPrimaryKey(purchaseOrderRequestVOList.get(i).getGoodsId());//根据商品id获取商品信息
			if(goods!=null){
				purchaseOrderRequestVOList.get(i).setGoodsCode(goods.getCode());
			    }
	    }
        PurchaseOrderModifyRecord record=this.getGoodsModifyRecord(userVO, purchaseOrderRequestVO.getId(), purchaseOrderRequestVO);//获取修改的字段
        /**
    	 * 处理修改记录逻辑end
    	 * */
        //获取订单的状态 用来判断 审批流应该走哪一个
    	Integer statusOld=purchaseOrderOld.getStatus();
   	
		Integer status = PurchaseStatus.WAIT_RECEIVE.getStatus();// 订单状态 待收货
		if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
			status = PurchaseStatus.PENDING_AUDIT.getStatus();// 待审核状态
		}
		//审批流逻辑处理开始
		/*ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
		if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
			status = PurchaseStatus.PENDING_AUDIT.getStatus();// 待审核状态
		}*/
		//审批流逻辑处理结束
    	BeanUtils.copyProperties(purchaseOrderRequestVO,purchaseOrder);
    	EntityUtils.reflectUpdateSetDefaultValue(purchaseOrder.getClass(),userVO);//复制用户基本信息到基础表
    	purchaseOrder.setUpdateTime(new Date());
    	purchaseOrder.setEnterpriseId(userVO.getEnterpriseId());//插入企业id
    	purchaseOrder.setParentId(userVO.getParentId());//插入父级id
    	purchaseOrder.setModifierId(userVO.getUserId());
    	purchaseOrder.setModifierCode(userVO.getUserCode());
    	purchaseOrder.setModifierName(userVO.getUserName());
    	long supplier_id=purchaseOrder.getSupplierId();//根据供货单位id查询供货单位信息
    	Supplier supplier=supplierMapper.selectByPrimaryKey(supplier_id);
    	if(supplier!=null){
    		purchaseOrder.setSupplierId(supplier.getId());
    		purchaseOrder.setSupplierName(supplier.getName());
        	purchaseOrder.setSupplierCode(supplier.getCode());
    	}
    	SupplierSaler supplierSaler=supplierSalerMapper.selectByPrimaryKey(purchaseOrder.getSupplierSalerId());
		if(supplierSaler!=null){
			purchaseOrder.setSupplierSalerId(supplierSaler.getId());
    		purchaseOrder.setSupplierSalerCode(supplierSaler.getCode());
    		purchaseOrder.setSupplierSalerName(supplierSaler.getName());
			purchaseOrder.setSupplierSalerPhone(supplierSaler.getTelephone());
		}
    /*	long supplier_saler_id=purchaseOrder.getSupplierSalerId();
    	SupplierSaler supplierSaler=supplierSalerMapper.selectByPrimaryKey(supplier_saler_id);//根据供货单位销售人员获取相关信息
    	if(supplierSaler!=null){
    		purchaseOrder.setSupplierSalerId(supplierSaler.getId());
    		purchaseOrder.setSupplierSalerCode(supplierSaler.getCode());
    		purchaseOrder.setSupplierSalerName(supplierSaler.getName());
    		purchaseOrder.setSupplierSalerPhone(supplierSaler.getMobilePhone());
    	}*/
    	purchaseOrder.setPurchaserId(userVO.getUserId());//采购人员ID
    	purchaseOrder.setPurchaserCode(userVO.getUserCode());//采购人员编码
    	purchaseOrder.setPurchaserName(userVO.getUserName());//采购人员姓名
    	purchaseOrder.setStatus(status);
        BigDecimal amountTotal=new BigDecimal(0);//金额合计
        BigDecimal quantityTotal=new BigDecimal(0);//数量合计
        Set cearRepeat=new HashSet();//获取品种数量
    	for(int i=0;i<purchaseOrderRequestVOList.size();i++){//计算合计
			PurchaseOrderDetailRequestVO purchaseOrderDetailRequestVO=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO().get(i);
			if(purchaseOrderDetailRequestVO.getUnitPrice().compareTo(BigDecimal.ZERO)==-1){//如果单价小于0
		    	throw new BusinessException("单价不能小于0");
			}
			if(purchaseOrderDetailRequestVO.getQuantity().compareTo(BigDecimal.ZERO)==-1){//如果数量小于0
				throw new BusinessException("数量不能小于0");
			}
			if(purchaseOrderDetailRequestVO.getLineDiscount().compareTo(BigDecimal.ZERO)==-1){//如果折扣小于0
				throw new BusinessException("折扣不能小于0");
			}else if(purchaseOrderDetailRequestVO.getLineDiscount().compareTo(BigDecimal.ZERO)==0){//如果折扣等于0
				throw new BusinessException("折扣不能等于0");
			}
			if(purchaseOrderDetailRequestVO.getTaxRateId()==null){
				throw new BusinessException("税率ID不能为空");
			}else{
				GoodsTaxRate goodsTaxRate=goodsTaxRateMapper.selectByPrimaryKey(purchaseOrderDetailRequestVO.getTaxRateId());
				if(goodsTaxRate==null){
					throw new BusinessException("没有该税率值，请核实税率ID");
				}else{
					purchaseOrderDetailRequestVO.setTaxRate(goodsTaxRate.getTaxRate());
				}
			}
			//计算金额合计
    		BigDecimal amount=CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetailRequestVO.getQuantity(), purchaseOrderDetailRequestVO.getUnitPrice(), purchaseOrderDetailRequestVO.getLineDiscount());
    		amountTotal=amountTotal.add(amount);
    	}
    	if(purchaseOrder.getWholeDiscount().compareTo(BigDecimal.ZERO)==-1){//整单折扣不能小于0
    		throw new BusinessException("整单折扣不能小于0");
    	}
    	if(purchaseOrder.getWholeDiscount().compareTo(BigDecimal.ZERO)==-1){//整单优惠不能小于0
    		throw new BusinessException("整单优惠不能小于0");
    	}
    	//定义不含税金额，与税额
    	BigDecimal notaxRealAmountTotal=new BigDecimal(0);//不含税总额 
        BigDecimal taxAmountTotal=new BigDecimal(0);//税额
    	for(int i=0;i<purchaseOrderRequestVOList.size();i++){//计算合计
			PurchaseOrderDetailRequestVO purchaseOrderDetailRequestVO=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO().get(i);
   			//根据数量、单价、行折扣获取金额（整单折扣金额）：数量*单价*行折扣
 			BigDecimal amount=CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetailRequestVO.getQuantity(), purchaseOrderDetailRequestVO.getUnitPrice(), purchaseOrderDetailRequestVO.getLineDiscount());
 			BigDecimal lineDiscountAmount=new BigDecimal(0);
 			if(amount.compareTo(BigDecimal.ZERO)==1){//大于0的时候
 				//lineDiscountAmount=purchaseOrder.getWholeDiscountAmount().multiply(amount.divide(amountTotal,2,BigDecimal.ROUND_HALF_UP));
 				lineDiscountAmount=CalculateComponent.getLineRoundOffByLineAmountAndWholeAmountTotal(purchaseOrder.getWholeDiscountAmount(),amount,amountTotal);
 			}    		//计算实际金额，根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
    		BigDecimal realAmount=CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetailRequestVO.getQuantity(),purchaseOrderDetailRequestVO.getUnitPrice(),purchaseOrderDetailRequestVO.getLineDiscount(),purchaseOrder.getWholeDiscount(),lineDiscountAmount);
    		//计算实际单价   ：实际金额/数量
    		BigDecimal realPrice=new BigDecimal(0);
    		if(purchaseOrderDetailRequestVO.getQuantity().compareTo(BigDecimal.ZERO)==1){//大于0
  		   	  realPrice =CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, purchaseOrderDetailRequestVO.getQuantity());
  		    } 
    		//获取不含税金额 
    		//BigDecimal val1=new BigDecimal(1);
    		//BigDecimal notaxRealAmount= realAmount.divide(val1.add(purchaseOrderDetailRequestVO.getTaxRate()),2,BigDecimal.ROUND_HALF_UP);
    		BigDecimal notaxRealAmount=CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(realAmount,purchaseOrderDetailRequestVO.getTaxRate());
    		//获取税额   实际金额-不含税金额
    		//BigDecimal taxAmount=realAmount.subtract(notaxRealAmount);
    		BigDecimal taxAmount=CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(realAmount,notaxRealAmount);
    		//计算不含税单价
    		//计算不含税单价
    		BigDecimal notaxRealPrice=new BigDecimal(0);
    		if(purchaseOrderDetailRequestVO.getQuantity().compareTo(BigDecimal.ZERO)==1){//如果数量大于0的时候
    	   	    notaxRealPrice=CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxRealAmount,purchaseOrderDetailRequestVO.getQuantity());
    		}
    		purchaseOrderDetailRequestVO.setTaxAmount(taxAmount);
    		purchaseOrderDetailRequestVO.setNotaxRealPrice(notaxRealPrice);//不含税单价
    		purchaseOrderDetailRequestVO.setNotaxRealAmount(notaxRealAmount);
    		purchaseOrderDetailRequestVO.setRealAmount(realAmount);
    		purchaseOrderDetailRequestVO.setRealPrice(realPrice);
			//计算订单详情里面的金额
    		purchaseOrderDetailRequestVO.setAmount(amount);
			//行优惠（行舍入）优惠分摊    计算优惠分摊    
    		purchaseOrderDetailRequestVO.setWholeDiscount(purchaseOrder.getWholeDiscount());//整单折扣
			purchaseOrderDetailRequestVO.setLineDiscountAmount(lineDiscountAmount);

			notaxRealAmountTotal=notaxRealAmountTotal.add(notaxRealAmount);//不含税金额总额
			taxAmountTotal=taxAmountTotal.add(taxAmount);
			quantityTotal=quantityTotal.add(purchaseOrderDetailRequestVO.getQuantity());
    		cearRepeat.add(purchaseOrderDetailRequestVO.getGoodsId());
    	}
    	//实际金额合计
    	//BigDecimal realAmountTotal =amountTotal.multiply(purchaseOrder.getWholeDiscount()).subtract(purchaseOrder.getWholeDiscountAmount());
       	BigDecimal realAmountTotal =amountTotal.multiply(purchaseOrder.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).subtract(purchaseOrder.getWholeDiscountAmount());
    	//不含税总额
		//税额
    	purchaseOrder.setVarietiesQuantity(cearRepeat.size());
    	purchaseOrder.setQuantityTotal(quantityTotal);
    	purchaseOrder.setAmountTotal(amountTotal);//总额（优惠前金额合计）
    	purchaseOrder.setRealAmountTotal(realAmountTotal);//实际金额合计
    	purchaseOrder.setNotaxRealAmountTotal(notaxRealAmountTotal);//不含税金额合计
    	purchaseOrder.setTaxAmountTotal(taxAmountTotal);

        purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);//修改订单基表信息
        purchaseOrder=purchaseOrderMapper.selectByPrimaryKey(purchaseOrder.getId());//根据采购订单id重新获取采购订单实体
        if(purchaseOrder==null){
        	throw new BusinessException("没有此订单");
        }
        purchaseOrderDetailMapper.deleteByOrderId(purchaseOrder.getId());//删除之前先把列表全部删除
    		for(int i=0;i<purchaseOrderRequestVOList.size();i++){
    			PurchaseOrderDetail purchaseOrderDetail=new PurchaseOrderDetail();
    			PurchaseOrderDetailRequestVO purchaseOrderDetailRequestVO=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO().get(i);
    			BeanUtils.copyProperties(purchaseOrderDetailRequestVO,purchaseOrderDetail);//订单详情从vo复制到entity
    			EntityUtils.reflectAddSetDefaultValue(purchaseOrderDetail.getClass(),userVO);
    			purchaseOrderDetail.setId(null);
    			purchaseOrderDetail.setEnterpriseId(userVO.getEnterpriseId());
    			purchaseOrderDetail.setParentId(userVO.getParentId());
    			purchaseOrderDetail.setOrderId(purchaseOrder.getId());
    			//以下为商品信息
    			Goods goods=goodsMapper.selectByPrimaryKey(purchaseOrderDetail.getGoodsId());//根据商品id获取商品信息
    			if(goods!=null){
    			purchaseOrderDetail.setGoodsId(goods.getId());
    			purchaseOrderDetail.setGoodsCode(goods.getCode());
    			purchaseOrderDetail.setGoodsName(goods.getName());
    			purchaseOrderDetail.setBarcode(goods.getBarcode());
    			purchaseOrderDetail.setGoodsGenericName(goods.getGenericName());
    			purchaseOrderDetail.setDosageId(goods.getDosageId());//剂型ID
    			purchaseOrderDetail.setDosageName(goods.getDosageName());
    			purchaseOrderDetail.setUnitId(goods.getUnitId());
    			purchaseOrderDetail.setUnitName(goods.getUnitName());
    			purchaseOrderDetail.setGoodsSpecification(goods.getSpecification());
    			purchaseOrderDetail.setManufacturerId(goods.getManufacturerId());//生产厂商ID
    			purchaseOrderDetail.setOrderType(purchaseOrder.getOrderType());
    			purchaseOrderDetail.setOrderCode(purchaseOrder.getCode());//这里面应该有采购订单编码
    			purchaseOrderDetail.setManufacturer(goods.getManufacturer());
    			purchaseOrderDetail.setGoodsPlace(goods.getPlace());
    			purchaseOrderDetail.setApprovalNumber(goods.getApprovalNumber());
    			}
    			purchaseOrderDetail.setOrderId(purchaseOrder.getId());
    			purchaseOrderDetail.setOrderDate(purchaseOrder.getOrderDate());
    			purchaseOrderDetail.setOrderType(purchaseOrder.getOrderType());
    			purchaseOrderDetail.setOrderCode(purchaseOrder.getCode());//这里面应该有采购订单编码
    			purchaseOrderDetail.setStatus(status);
    			BigDecimal cq=new BigDecimal(0);
    			purchaseOrderDetail.setClearQuantity(cq);//已清数量为0
    			purchaseOrderDetail.setUnclearQuantity(purchaseOrderDetail.getQuantity());//未清数量
    			purchaseOrderDetail.setModifierId(userVO.getUserId());
    			purchaseOrderDetail.setModifierCode(userVO.getUserCode());
    			purchaseOrderDetail.setModifierName(userVO.getUserName());
    			purchaseOrderDetail.setCreaterId(userVO.getUserId());
    			purchaseOrderDetail.setCreaterCode(userVO.getUserCode());
    			purchaseOrderDetail.setCreaterName(userVO.getUserName());
    			purchaseOrderDetail.setCreateTime(new Date());
    			purchaseOrderDetail.setUpdateTime(new Date());
    			//获取 零售基价 会员基价 配货基价
    		    Map map=new HashMap();
    		    map.put("enterpriseId", userVO.getEnterpriseId());
    		    map.put("goodsId", goods.getId());
    		    GoodsBusiness goodsBusiness=goodsBusinessMapper.selectByEnterpriseIdAndGoodsId(map);
    		    if(goodsBusiness!=null){
    		    	    purchaseOrderDetail.setRetailPrice(goodsBusiness.getRetailPrice());
    		    	    purchaseOrderDetail.setMemberPrice(goodsBusiness.getMemberPrice());
    		    	    purchaseOrderDetail.setDistrPrice(goodsBusiness.getDistrPrice());
    		    }
    			purchaseOrderDetailMapper.insert(purchaseOrderDetail);//修改订单详情信息
    	}
        PurchaseOrderOther purchaseOrderOther =new PurchaseOrderOther();
        BeanUtils.copyProperties(purchaseOrderRequestVO.getPurchaseOrderOtherRequestVO(),purchaseOrderOther);//采购订单配送和结算从vo复制到entity
        EntityUtils.reflectAddSetDefaultValue(purchaseOrderOther.getClass(),userVO);
    //    purchaseOrderOther.setOrderId(purchaseOrder.getId());
        purchaseOrderOther.setStatus(status);
        //获取结算单位信息
        if(purchaseOrderOther.getSettlementUnitId()==null){
        	throw new BusinessException("结算单位ID不能为空");
        }
        Enterprise settlementUnit=enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getSettlementUnitId());
        if(settlementUnit!=null){
        	purchaseOrderOther.setSettlementUnitName(settlementUnit.getName());
        }
        Enterprise receiveUnit=enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getReceiveUnitId());//收货单位
        User receiver=userMapper.selectByPrimaryKey(purchaseOrderOther.getReveiverId());//收货人员
        if(receiveUnit!=null){
        	purchaseOrderOther.setReceiveUnitName(receiveUnit.getName());
        	purchaseOrderOther.setReceiveUnitCode(receiveUnit.getCode());
        }
        if(receiver!=null){
        	purchaseOrderOther.setReceiverCode(receiver.getCode());
        	purchaseOrderOther.setReceiverName(receiver.getName());
        }
        purchaseOrderOther.setModifierId(userVO.getUserId());
        purchaseOrderOther.setModifierCode(userVO.getUserCode());
        purchaseOrderOther.setModifierName(userVO.getUserName());
        purchaseOrderOther.setUpdateTime(new Date());
    	purchaseOrderOtherMapper.updateByPrimaryKeySelective(purchaseOrderOther);
    	//修改原因
     	record.setReason(purchaseOrderRequestVO.getReason());//把修改原因放入修改表字段
    	record.setUpdateTime(new Date());//修改日期
    	record.setCreateTime(new Date());//创建日期
    	record.setTableName("saas_purchase_order");
    	record.setEnterpriseId(userVO.getEnterpriseId());
    	record.setParentId(userVO.getParentId());
    	record.setKeyId(purchaseOrder.getId());
    	record.setColumnEnName("purchaseOrder");
    	record.setCreaterId(userVO.getUserId());
    	record.setCreaterCode(userVO.getUserCode());
    	record.setCreaterName(userVO.getUserName());
    	record.setModifierId(userVO.getUserId());
    	record.setModifierCode(userVO.getUserCode());
    	record.setModifierName(userVO.getUserName());
    	record.setRemark("备注");
    	purchaseOrderModifyRecordMapper.insertSelective(record);
		// 提交流程审批
		if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
			 Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), enterprise.getName(),
        		userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
        		userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                ApprovalFlowContentModel.getPurchaseOrderCode(), purchaseOrder.getId(), "", "");
        approvalFlowComponent.apply(submitApprovalFlowVO, userVO);
	        }
        // 重新提交审批，并更新状态为 待审批
    }
    public PurchaseOrderModifyRecord getGoodsModifyRecord(UserVO userVO,Long orderId,PurchaseOrderReqVO  purchaseOrderRequestVO) throws Exception {
    PurchaseOrderResponseVO orderOld=purchaseOrderMapper.selectById(orderId);//获取旧数据
    PurchaseOrderOtherResponseVO otherOld=orderOld.getPurchaseOrderOtherResponseVO();//旧结算数据对象
    List<PurchaseOrderDetailResponseVO> detailOld=orderOld.getPurchaseOrderDetailResponseVO();//旧详情对象
    
    PurchaseOrderOtherRequestVO otherNew=purchaseOrderRequestVO.getPurchaseOrderOtherRequestVO();//新数据对象
    List<PurchaseOrderDetailRequestVO> detailNew=purchaseOrderRequestVO.getPurchaseOrderDetailRequestVO();//旧数据对象
    StringBuffer oldField=new StringBuffer();
    StringBuffer newField=new StringBuffer();
    StringBuffer projectName=new StringBuffer();
if(orderOld!=null && purchaseOrderRequestVO!=null){
	if(purchaseOrderRequestVO.getSupplierId()!=null){//供货单位比较
	if(!purchaseOrderRequestVO.getSupplierId().equals(orderOld.getSupplierId())){
		oldField.append(+orderOld.getSupplierId()+"<br>");
		newField.append(+purchaseOrderRequestVO.getSupplierId()+"<br>");
		projectName.append("供货单位ID<br>");
	 }
	}else if(orderOld.getSupplierId()!=null){
		if(!orderOld.getSupplierId().equals(purchaseOrderRequestVO.getSupplierId())){
			oldField.append(+orderOld.getSupplierId()+"<br>");
			newField.append(+purchaseOrderRequestVO.getSupplierId()+"<br>");
			projectName.append("供货单位ID<br>");
		 }
	}
	if(purchaseOrderRequestVO.getSupplierSalerId()!=null){//供货单位销售人员对比
	if(!purchaseOrderRequestVO.getSupplierSalerId().equals(orderOld.getSupplierSalerId())){
		oldField.append(orderOld.getSupplierSalerId()+"<br>");
		newField.append(purchaseOrderRequestVO.getSupplierSalerId()+"<br>");
		projectName.append("供货单位销售人员ID<br>");
	  }
	}else if(orderOld.getSupplierSalerId()!=null){
		if(!orderOld.getSupplierSalerId().equals(purchaseOrderRequestVO.getSupplierSalerId())){
			oldField.append(orderOld.getSupplierSalerId()+"<br>");
			newField.append(purchaseOrderRequestVO.getSupplierSalerId()+"<br>");
			projectName.append("供货单位销售人员ID<br>");
		}
	}
	if(purchaseOrderRequestVO.getOrderDate()!=null){//订单日期对比
	if(!purchaseOrderRequestVO.getOrderDate().equals(orderOld.getOrderDate())){
		oldField.append(orderOld.getOrderDate()+"<br>");
		newField.append(purchaseOrderRequestVO.getOrderDate()+"<br>");
		projectName.append("订单日期<br>");
	}
	}else if(orderOld.getOrderDate()!=null){
		if(!orderOld.getOrderDate().equals(purchaseOrderRequestVO.getOrderDate())){
			oldField.append(orderOld.getOrderDate()+"<br>");
			newField.append(purchaseOrderRequestVO.getOrderDate()+"<br>");
			projectName.append("订单日期<br>");
		}
		}
	if(purchaseOrderRequestVO.getArrivalTime()!=null){//预计到货日期
	if(!purchaseOrderRequestVO.getArrivalTime().equals(orderOld.getArrivalTime())){
		oldField.append(orderOld.getArrivalTime()+"<br>");
		newField.append(purchaseOrderRequestVO.getArrivalTime()+"<br>");
		projectName.append("预计到货日期<br>");
	}
	}else if(orderOld.getArrivalTime()!=null){
		if(!orderOld.getArrivalTime().equals(purchaseOrderRequestVO.getArrivalTime())){
			oldField.append(orderOld.getArrivalTime()+"<br>");
			newField.append(purchaseOrderRequestVO.getArrivalTime()+"<br>");
			projectName.append("预计到货日期<br>");
		}
		}
	if(purchaseOrderRequestVO.getPurchaserId()!=null){//采购人员对比
	if(!purchaseOrderRequestVO.getPurchaserId().equals(orderOld.getPurchaserId())){
		oldField.append(orderOld.getPurchaserId()+"<br>");
		newField.append(purchaseOrderRequestVO.getPurchaserId()+"<br>");
		projectName.append("采购人员ID<br>");
	}
	}else if(orderOld.getPurchaserId()!=null){
		if(!orderOld.getPurchaserId().equals(purchaseOrderRequestVO.getPurchaserId())){
			oldField.append(orderOld.getPurchaserId()+"<br>");
			newField.append(purchaseOrderRequestVO.getPurchaserId()+"<br>");
			projectName.append("采购人员ID<br>");
		}
		}
	if(purchaseOrderRequestVO.getRemark()!=null){//备注对比
	if(!purchaseOrderRequestVO.getRemark().equals(orderOld.getRemark())){
		oldField.append(orderOld.getRemark()+"<br>");
		newField.append(purchaseOrderRequestVO.getRemark()+"<br>");
		projectName.append("备注<br>");
	}
	}else if(orderOld.getRemark()!=null){
		if(!orderOld.getRemark().equals(purchaseOrderRequestVO.getRemark())){
			oldField.append(orderOld.getRemark()+"<br>");
			newField.append(purchaseOrderRequestVO.getRemark()+"<br>");
			projectName.append("备注<br>");
		}
		}
	if(otherOld!=null && otherNew!=null){//配送和结算 //	otherOld.getPurchaseOrderOtherResponseVO
		if(otherOld.getContractCode()!=null){//合同单号比较
			if(!otherOld.getContractCode().equals(otherNew.getContractCode())){
				oldField.append(otherOld.getContractCode()+"<br>");
				newField.append(otherNew.getContractCode()+"<br>");
				projectName.append("合同单号<br>");
			}
		}else if(otherNew.getContractCode()!=null){
			if(!otherNew.getContractCode().equals(otherOld.getContractCode())){
				oldField.append(otherOld.getContractCode()+"<br>");
				newField.append(otherNew.getContractCode()+"<br>");
				projectName.append("合同单号ID<br>");
			}
		}
	  if(otherOld.getPlanCode()!=null){//计划单号比较
		if(!otherOld.getPlanCode().equals(otherNew.getPlanCode())){
			oldField.append(otherOld.getPlanCode()+"<br>");
			newField.append(otherNew.getPlanCode()+"<br>");
			projectName.append("计划单号<br>");
		}
	  }else if(otherNew.getPlanCode()!=null){
			if(!otherNew.getPlanCode().equals(otherOld.getPlanCode())){
				oldField.append(otherOld.getPlanCode()+"<br>");
				newField.append(otherNew.getPlanCode()+"<br>");
				projectName.append("计划单号<br>");
			}
		}
	  if(otherOld.getSettlementUnitId()!=null){//结算单位比较
		if(!otherOld.getSettlementUnitId().equals(otherNew.getSettlementUnitId())){
			oldField.append(otherOld.getSettlementUnitId()+"<br>");
			newField.append(otherNew.getSettlementUnitId()+"<br>");
			projectName.append("结算单位ID<br>");
		}
	  }else if(otherNew.getSettlementUnitId()!=null){
			if(!otherNew.getSettlementUnitId().equals(otherOld.getSettlementUnitId())){
				oldField.append(otherOld.getSettlementUnitId()+"<br>");
				newField.append(otherNew.getSettlementUnitId()+"<br>");
				projectName.append("结算单位ID<br>");
			}
		}
	  if(otherOld.getSettlementType()!=null){//结算方式
		if(!otherOld.getSettlementType().equals(otherNew.getSettlementType())){
			oldField.append(otherOld.getSettlementType()+"<br>");
			newField.append(otherNew.getSettlementType()+"<br>");
			projectName.append("结算方式<br>");
		}
	  }else if(otherNew.getSettlementType()!=null){
			if(!otherNew.getSettlementType().equals(otherOld.getSettlementType())){
				oldField.append(otherOld.getSettlementType()+"<br>");
				newField.append(otherNew.getSettlementType()+"<br>");
				projectName.append("结算方式<br>");
			}
	    }
	  if(otherOld.getSettlementUnitPhone()!=null){//结算单位电话
		if(!otherOld.getSettlementUnitPhone().equals(otherNew.getSettlementUnitPhone())){
			oldField.append(otherOld.getSettlementUnitPhone()+"<br>");
			newField.append(otherNew.getSettlementUnitPhone()+"<br>");
			projectName.append("结算单位电话<br>");
		}
	  }else if(otherNew.getSettlementUnitPhone()!=null){
			if(!otherNew.getSettlementUnitPhone().equals(otherOld.getSettlementUnitPhone())){
				oldField.append(otherOld.getSettlementUnitPhone()+"<br>");
				newField.append(otherNew.getSettlementUnitPhone()+"<br>");
				projectName.append("结算单位电话<br>");
			}
		  }
	  if(otherOld.getSettlementUnitAddress()!=null){//结算单位地址比较
		if(!otherOld.getSettlementUnitAddress().equals(otherNew.getSettlementUnitAddress())){
			oldField.append(otherOld.getSettlementUnitAddress()+"<br>");
			newField.append(otherNew.getSettlementUnitAddress()+"<br>");
			projectName.append("结算单位地址<br>");
		}
	  }else if(otherNew.getSettlementUnitAddress()!=null){
			if(!otherNew.getSettlementUnitAddress().equals(otherOld.getSettlementUnitAddress())){
				oldField.append(otherOld.getSettlementUnitAddress()+"<br>");
				newField.append(otherNew.getSettlementUnitAddress()+"<br>");
				projectName.append("结算单位地址<br>");
			}
		 }
	  if(otherOld.getBank()!=null){//开户行对比
		if(!otherOld.getBank().equals(otherNew.getBank())){
			oldField.append(otherOld.getBank()+"<br>");
			newField.append(otherNew.getBank()+"<br>");
			projectName.append("开户户行<br>");
		}
	  }else if(otherNew.getBank()!=null){
			if(!otherNew.getBank().equals(otherOld.getBank())){
				oldField.append(otherOld.getBank()+"<br>");
				newField.append(otherNew.getBank()+"<br>");
				projectName.append("开户户行<br>");
			}
		}
	  if(otherOld.getAccount()!=null){//开户账号
		if(!otherOld.getAccount().equals(otherNew.getAccount())){
			oldField.append(otherOld.getAccount()+"<br>");
			newField.append(otherNew.getAccount()+"<br>");
			projectName.append("开户账号<br>");
		}
	  }else if(otherNew.getAccount()!=null){
			if(!otherNew.getAccount().equals(otherOld.getAccount())){
				oldField.append(otherOld.getAccount()+"<br>");
				newField.append(otherNew.getAccount()+"<br>");
				projectName.append("开户账号<br>");
			}
		}
	  if(otherOld.getInvoiceType()!=null){//发票类型比对
		if(!otherOld.getInvoiceType().equals(otherNew.getInvoiceType())){
			oldField.append(otherOld.getInvoiceType()+"<br>");
			newField.append(otherNew.getInvoiceType()+"<br>");
			projectName.append("发票类型<br>");
		}
	  }else if(otherNew.getInvoiceType()!=null){
			if(!otherNew.getInvoiceType().equals(otherOld.getInvoiceType())){
				oldField.append(otherOld.getInvoiceType()+"<br>");
				newField.append(otherNew.getInvoiceType()+"<br>");
				projectName.append("发票类型<br>");
			}
		}
	  if(otherOld.getTaxpayerCode()!=null){//纳税人识别号
		if(!otherOld.getTaxpayerCode().equals(otherNew.getTaxpayerCode())){
			oldField.append(otherOld.getTaxpayerCode()+"<br>");
			newField.append(otherNew.getTaxpayerCode()+"<br>");
			projectName.append("纳税人识别号<br>");
		}
	   }else if(otherNew.getTaxpayerCode()!=null){
			if(!otherNew.getTaxpayerCode().equals(otherOld.getTaxpayerCode())){
				oldField.append(otherOld.getTaxpayerCode()+"<br>");
				newField.append(otherNew.getTaxpayerCode()+"<br>");
				projectName.append("纳税人识别号<br>");
			}
		 }
	  if(otherOld.getCarriageMode()!=null){
		if(!otherOld.getCarriageMode().equals(otherNew.getCarriageMode())){
			oldField.append(otherOld.getCarriageMode()+"<br>");
	 		newField.append(otherNew.getCarriageMode()+"<br>");
	 		projectName.append("承运方式<br>");
	   } }else if(otherNew.getCarriageMode()!=null){
		  if(!otherNew.getCarriageMode().equals(otherOld.getCarriageMode())){
				oldField.append(otherOld.getCarriageMode()+"<br>");
				newField.append(otherNew.getCarriageMode()+"<br>");
				projectName.append("承运方式<br>");
		}
	   }
	  if(otherOld.getCarriageUnit()!=null){
		if(!otherOld.getCarriageUnit().equals(otherNew.getCarriageUnit())){
			oldField.append(otherOld.getCarriageUnit()+"<br>");
			newField.append(otherNew.getCarriageUnit()+"<br>");
			projectName.append("承运单位<br>");
		}
	  }else if(otherNew.getCarriageUnit()!=null){
			if(!otherNew.getCarriageUnit().equals(otherOld.getCarriageUnit())){
				oldField.append(otherOld.getCarriageUnit()+"<br>");
				newField.append(otherNew.getCarriageUnit()+"<br>");
				projectName.append("承运单位<br>");
			}
		  }
	  if(otherOld.getTransportMode()!=null){
		if(!otherOld.getTransportMode().equals(otherNew.getTransportMode())){
			oldField.append(otherOld.getTransportMode()+"<br>");
			newField.append(otherNew.getTransportMode()+"<br>");
			projectName.append("运输方式<br>");
		}
	  }else if(otherNew.getTransportMode()!=null){
			if(!otherNew.getTransportMode().equals(otherOld.getTransportMode())){
				oldField.append(otherOld.getTransportMode()+"<br>");
				newField.append(otherNew.getTransportMode()+"<br>");
				projectName.append("运输方式<br>");
			}
		  }
	  if(otherOld.getTempControlMode()!=null){
		if(!otherOld.getTempControlMode().equals(otherNew.getTempControlMode())){
			oldField.append(otherOld.getTempControlMode()+"<br>");
			newField.append(otherNew.getTempControlMode()+"<br>");
			projectName.append("温控方式<br>");
		}
	  }else if(otherNew.getTempControlMode()!=null){
			if(!otherNew.getTempControlMode().equals(otherOld.getTempControlMode())){
				oldField.append(otherOld.getTempControlMode()+"<br>");
				newField.append(otherNew.getTempControlMode()+"<br>");
				projectName.append("温控方式<br>");
			}
		  }
	  if(otherOld.getReceiveUnitName()!=null){
		if(!otherOld.getReceiveUnitName().equals(otherNew.getReceiveUnitName())){
			oldField.append(otherOld.getReceiveUnitName()+"<br>");
			newField.append(otherNew.getReceiveUnitName()+"<br>");
			projectName.append("收货单位<br>");
		}
	  }else if(otherNew.getReceiveUnitName()!=null){
			if(!otherNew.getReceiveUnitName().equals(otherOld.getReceiveUnitName())){
				oldField.append(otherOld.getReceiveUnitName()+"<br>");
				newField.append(otherNew.getReceiveUnitName()+"<br>");
				projectName.append("收货单位<br>");
			}
		 }
	  if(otherOld.getReceiverName()!=null){
		if(!otherOld.getReceiverName().equals(otherNew.getReceiverName())){
			oldField.append(otherOld.getReceiverName()+"<br>");
			newField.append(otherNew.getReceiverName()+"<br>");
			projectName.append("收货人员<br>");
		}
	  }else if(otherNew.getReceiverName()!=null){
			if(!otherNew.getReceiverName().equals(otherOld.getReceiverName())){
				oldField.append(otherOld.getReceiverName()+"<br>");
				newField.append(otherNew.getReceiverName()+"<br>");
				projectName.append("收货人员<br>");
			}
		  }
	  if(otherOld.getReveiverPhone()!=null){
		if(!otherOld.getReveiverPhone().equals(otherNew.getReveiverPhone())){
			oldField.append(otherOld.getReveiverPhone()+"<br>");
			newField.append(otherNew.getReveiverPhone()+"<br>");
			projectName.append("收货人电话<br>");
		}
	  }else if(otherNew.getReveiverPhone()!=null){
			if(!otherNew.getReveiverPhone().equals(otherOld.getReveiverPhone())){
				oldField.append(otherOld.getReveiverPhone()+"<br>");
				newField.append(otherNew.getReveiverPhone()+"<br>");
				projectName.append("收货人电话<br>");
			}
		  }
	  if(otherOld.getReceiveAddress()!=null){
		if(!otherOld.getReceiveAddress().equals(otherNew.getReceiveAddress())){
			oldField.append(otherOld.getReceiveAddress()+"<br>");
			newField.append(otherNew.getReceiveAddress()+"<br>");
			projectName.append("收货地址<br>");
		}
	  }else if(otherNew.getReceiveAddress()!=null){
			if(!otherNew.getReceiveAddress().equals(otherOld.getReceiveAddress())){
				oldField.append(otherOld.getReceiveAddress()+"<br>");
				newField.append(otherNew.getReceiveAddress()+"<br>");
				projectName.append("收货地址<br>");
			}
	   }
	  //如果id不为空的话说明是修改的
	  for(int i=0;i<detailNew.size();i++){
		  if(detailNew.get(i).getId()!=null){
			    oldField.append(detailNew.get(i).getGoodsCode()+"<br>");
				newField.append(detailNew.get(i).getGoodsCode()+"<br>");
				projectName.append("商品修改<br>");
		  }else{
			    oldField.append("null<br>");
				newField.append(detailNew.get(i).getGoodsCode()+"<br>");
				projectName.append("商品新增<br>");
		  }
	  }
		for(int i=0;i<detailOld.size();i++){
			for(int j=0;j<detailNew.size();j++){
				if(detailNew.get(j).getId()!=null){
					if(!detailNew.get(j).equals(detailOld.get(i).getId())){
						    oldField.append(detailOld.get(i).getGoodsCode()+"<br>");
							newField.append("null<br>");
							projectName.append("商品删除<br>");
					}
				}
			}
		}
	}
}

PurchaseOrderModifyRecord record=new PurchaseOrderModifyRecord();
record.setNewContent(newField.toString());
record.setOldContent(oldField.toString());
record.setColumnChName(projectName.toString());//修改项目
return record;
}
    /**
     * 根据id查询采购订单
     * */
    @Override
    public PurchaseOrder selectByPrimaryKey(Long id){
    	PurchaseOrder purchaseOrder=purchaseOrderMapper.selectByPrimaryKey(id);
    	return purchaseOrder;
    }
    /**
     * 根据企业id查询采购订单
     * @throws ParseException 
     * */
    @Override
    public void selectByEnterpriseId(Page page,Long enterpriseId, 
			String supplier_code,String supplier_name,
			String code,String purchaser_name,String sort,String status,String sortField,Date startDate,Date endDate) throws ParseException{
    	 int status_=0;
    	 if(status!=null && status!=""){
         status_=Integer.valueOf(status);
    	 }
    	 if(sortField!=null){
    	   if(sortField.equals("orderDate")){
    		 sortField="order_date";
    	    }
    	 }
    	 Date startD=null;
    	 Date endD=null;
    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	 if(startDate!=null){
    		 String startDate_ = formatter.format(startDate); 
    		   startD = formatter.parse(startDate_);
    	 }
    	  if(endDate!=null){
    		  String endDate_ = formatter.format(endDate);
    		  endD=formatter.parse(endDate_);
    	  }

		List<Integer> statusList = new ArrayList<>();
		statusList.add(status_);
		if(PurchaseStatus.FINISHED.getStatus() == status_){
			statusList.add(PurchaseStatus.WAIT_BILL.getStatus());
			statusList.add(PurchaseStatus.PART_BILL.getStatus());
		}
    	Long totalRecord=purchaseOrderMapper.queryCountByEnterpriseId(enterpriseId, 
    			supplier_code,supplier_name,
    			code,purchaser_name,status ==null?null:statusList,sortField,startDate,endDate);
    	PurchaseOrderCountVO purchaseOrderCountVO=purchaseOrderMapper.selectCount(page.getPageNo(),page.getPageSize(),enterpriseId,
    			supplier_code,supplier_name,code,purchaser_name,sort,status == null?null: statusList,sortField,startD,endD);
    	List<PurchaseOrderResponseVO>  purchaseOrderList=purchaseOrderMapper.selectByEnterpriseId(page.getPageNo(),page.getPageSize(),enterpriseId,
    			supplier_code,supplier_name,code,purchaser_name,sort,status == null?null:statusList,sortField,startD,endD);
    	//设置配送信息
		if (CollectionUtils.isNotEmpty(purchaseOrderList)){
			List<Long> orderIds = new ArrayList<>();
			orderIds.addAll(purchaseOrderList.stream().map(l ->{
				return l.getId();
			}).distinct().collect(Collectors.toList()));

			if (CollectionUtils.isNotEmpty(orderIds)){
				List<PurchaseOrderOther> otherList = purchaseOrderOtherMapper.selectByEnterpriseIdAndOrderIds(enterpriseId, orderIds);
				purchaseOrderList.forEach(l->{
					otherList.forEach(o->{
						if(l.getId().longValue() == o.getOrderId().longValue()) {
							PurchaseOrderOtherResponseVO otherResponseVO = new PurchaseOrderOtherResponseVO();
							CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(o,otherResponseVO);
							otherResponseVO.setCarriageModeName(DistributionType.getName(otherResponseVO.getCarriageMode()));
							l.setCarriageModeName(otherResponseVO.getCarriageModeName());
							l.setPurchaseOrderOtherResponseVO(otherResponseVO);
						}
					});
				});

			}
		}

    	if(purchaseOrderCountVO!=null){
    	 	purchaseOrderCountVO.setPurchaseOrderResponseListVO(purchaseOrderList);
    	}
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(purchaseOrderCountVO);
    }
    /**
     * 根据采购订单id查询采购订单信息以及详情，配送和结算
     * */
	@Override
	public PurchaseOrderResponseVO selectById(Long id, UserVO userVO) {
		PurchaseOrderResponseVO purchaseOrder=purchaseOrderMapper.selectById(id);
		purchaseOrder.setEnterpriseName(userVO.getEnterpriseName());
		purchaseOrder.setWholeDisAmount((BigDecimal.ONE.subtract(purchaseOrder.getWholeDiscount().
				divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP))).multiply(purchaseOrder.getAmountTotal()));
		return purchaseOrder;
	}
	/**
	 * 采购订单导出
	 * */
	@Override
	public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
	        //转换一下显示日期
		PurchaseOrderResponseVO purchaseOrder=purchaseOrderMapper.selectById(id);
		List<PurchaseOrderDetailResponseVO> purchaseOrderDetailResponseVO=purchaseOrder.getPurchaseOrderDetailResponseVO();
	        LinkedHashMap<String,String> map = new LinkedHashMap<>();
	        map.put("goodsCode","商品编码");
	        map.put("goodsName","通用名称");
	        map.put("dosageName","剂型");
	        map.put("goodsSpecification","规格");
	        map.put("manufacturer","生产厂商");
	        map.put("unitName","单位");
	        map.put("quantity","数量");
	        map.put("unitPrice","单价");
	        map.put("lineDiscount","折扣");
	        map.put("amount","金额");
	        map.put("taxRate","税率");
	        map.put("notaxRealPrice","不含税单价");
	        map.put("notaxRealAmount","不含税金额");
	        map.put("taxAmount","税额");
	        map.put("remark","备注");
	        List<String> secondTitle=new ArrayList<String>();
	        StringBuilder titleSecondRow = new StringBuilder();
	        titleSecondRow.append("供货单位编码:");
	        titleSecondRow.append(purchaseOrder.getSupplierCode() ==null? "":purchaseOrder.getSupplierCode());
	        titleSecondRow.append("  供货单位名称:");
	        titleSecondRow.append(purchaseOrder.getPurchaserName() == null ? "":purchaseOrder.getPurchaserName());
	        titleSecondRow.append("  供货单位销售人员:");
	        titleSecondRow.append(purchaseOrder.getSupplierSalerName() == null ? "":purchaseOrder.getSupplierSalerName());
	        titleSecondRow.append("  联系电话:");
	        titleSecondRow.append(purchaseOrder.getSupplierSalerPhone() == null ? "":purchaseOrder.getSupplierSalerPhone());
	        titleSecondRow.append("  订单日期:");
	        titleSecondRow.append(purchaseOrder.getOrderDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(purchaseOrder.getOrderDate()));
	        titleSecondRow.append("  预计到货日期:");
	        titleSecondRow.append(purchaseOrder.getArrivalTime() == null ? "":purchaseOrder.getArrivalTime());
	        titleSecondRow.append("  采购人员");
	        titleSecondRow.append(purchaseOrder.getPurchaserName() == null ? "":purchaseOrder.getPurchaserName());
	        secondTitle.add(titleSecondRow.toString());
	        List<StringBuilder> list = new ArrayList<>();
	        StringBuilder end = new StringBuilder();
	        end.append("商品  ");
	        end.append("总金额为：");
	        end.append(purchaseOrder.getAmountTotal());
	        //每行以分号隔开
	        end.append(";");
	        end.append("折扣：");
	        end.append(purchaseOrder.getWholeDiscount());
	        end.append("%");
	        end.append(";");
	        end.append("优惠：");
	        end.append(purchaseOrder.getWholeDiscountAmount());
	        end.append(";");
	        end.append("总额：");
	        end.append(purchaseOrder.getRealAmountTotal());
	        end.append(";");
	        end.append("不含税总额：");
	        end.append(purchaseOrder.getNotaxRealAmountTotal());
	        end.append(";");
	        end.append("税额：");
	        end.append(purchaseOrder.getTaxAmountTotal());
	        end.append(";");
	        List titleList=new ArrayList();
	        titleList.add(loginUser.getEnterpriseName());
	        titleList.add("采购订单");
	        purchaseGeneralComponent.commExcelExport(output, map, purchaseOrderDetailResponseVO,titleList,secondTitle , end.toString(), true,new ArrayList());
	    }
	/**
	 * 修改订单的状态
	 * @throws Exception 
	 * */
	@Override
	public void changeStatus(UserVO userVO,PurchaseOrder order) throws Exception{
		EntityUtils.reflectUpdateSetDefaultValue(order.getClass(),userVO);//复制用户基本信息到基础表
		purchaseOrderMapper.updateByPrimaryKeySelective(order);
		// 提交流程审批
		/*ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
				if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
						Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
						SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), enterprise.getName(),
								userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
								userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
										OrderRule.PURCHASE_ORDER.getTypeName(), purchaseOrder.getId(), "", "");
						approvalFlowComponent.apply(submitApprovalFlowVO, purchaseOrderCancelApprovalProcessor);
			        }
				}*/
	}
	@Override
	public List<SupplierOrderVO> getSupplier(UserVO userVO) {

		Map<String, Object> param = new HashMap<>();
		param.put("status", 1);
		param.put("validFlag", 1);
		Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
		param.put("enterpriseId", headEnterpriseId);
		param.put("ownerId", headEnterpriseId);
		return purchaseOrderMapper.selectSupplier(param);
	}
	@Override
	public List<EnterpriseOrderVO> getEnterprise(Long parentId) {
		return purchaseOrderMapper.selectEnterprise(parentId);
	}
	@Override
	public List<UserOrderVO> getUser(Long enterpriseId) {
		return purchaseOrderMapper.selectUser(enterpriseId);
	}
	@Override
	public List<PurchaseUserOrderVO> getPurchaseUser(Long enterpriseId) {
		return purchaseOrderMapper.selectPurchaseUser(enterpriseId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsOrderVO> getGoods(Map map) {
		
		SupplierBusiness bus = supplierBusinessMapper.getSupplierBuseinessBySupplierId(map.get("supplierId")!=null?Long.parseLong(map.get("supplierId").toString()):0L);
		if(bus == null){
			throw new BusinessException("该供应商业务子表查不到数据！");
		}
		map.put("managementMode", bus.getManagementMode());
		
		List<GoodsOrderVO> goodsOrderVOS = purchaseOrderMapper.selectGoods(map);
		
		goodsOrderVOS.parallelStream().forEach(item -> {
			//获取商品默认货位
			SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(item.getId(), Long.valueOf(map.get("enterpriseId")+""));
			if(null != safetyStock) {
				item.setDefaultShelfId(safetyStock.getDefaultShelfId());
				item.setDefaultShelfName(safetyStock.getDefaultShelfName());
			}
			if (item.getUnitPrice() == null) {
				item.setUnitPrice(BigDecimal.ZERO);
			}
		});

		return goodsOrderVOS;
	}
	@Override
	public List<SaleManOrderVO> getSaleMan(Long supplierId) {
	 	return purchaseOrderMapper.selectSaleMan(supplierId);
	}
	@Override
	public List<RecordVO> getRecord(Map map) {
	 	return purchaseOrderMapper.selectRecord(map);
	}
	@Override
	public UserOrderVO getManageConfigByEnterpriseId(Long enterpriseId) {
		return purchaseOrderMapper.selectDefaultUser(enterpriseId);
	}
	@Override
	public void temporarySave(UserVO userVO,PurchaseOrderReqVO purchaseOrderRequestVO) {
		List<PurchaseOrderReqVO> reqList=new ArrayList<PurchaseOrderReqVO>();
		if(redisTemplate.get(userVO.getEnterpriseId()+"purchaseOrder")!=null){
	    reqList=(List<PurchaseOrderReqVO>) redisTemplate.get(userVO.getEnterpriseId()+"purchaseOrder");
		reqList.add(purchaseOrderRequestVO);
		}else{
			reqList.add(purchaseOrderRequestVO);
		}
		redisTemplate.set(userVO.getEnterpriseId()+"purchaseOrder", reqList);
	}
	@Override
	public List<PurchaseOrderReqVO> temporaryGet(UserVO userVO) {
		List<PurchaseOrderReqVO> reqList=new ArrayList<PurchaseOrderReqVO>();
		reqList=(List<PurchaseOrderReqVO>) redisTemplate.get(userVO.getEnterpriseId()+"purchaseOrder");
		return reqList;
	}
	 /**
     * 分页获取商品列表
     * @throws ParseException 
     * */
    @Override
    public void getGoodsByPage(Page page,Map map) throws ParseException{
    	
    	SupplierBusiness bus = supplierBusinessMapper.getSupplierBuseinessBySupplierId(map.get("supplierId")!=null?Long.parseLong(map.get("supplierId").toString()):0L);
		if(bus == null){
			throw new BusinessException("该供应商业务子表查不到数据！");
		}
		map.put("managementMode", bus.getManagementMode());
    	
    	Long totalRecord=purchaseOrderMapper.queryGoodsCount(map);
    	List<GoodsOrderVO>  goodsList=purchaseOrderMapper.queryGoodsList(map);

		goodsList.forEach(item ->{

					if(item.getUnitPrice() == null){
						item.setUnitPrice(BigDecimal.ZERO);
					}
				}
		);
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(goodsList);
    }
    @Override
    public DraftCacheVO<PurchaseOrderReqVO> saveDraftCache(UserVO userVO, DraftCacheVO<PurchaseOrderReqVO> draftCache)throws BusinessException{
    	if(draftCache.getOrderData()==null){
    		throw new BusinessException("实体不能为空");
    	}
        String redisKeyValue = draftCache.getId();

        DraftCacheVO<PurchaseOrderReqVO> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.PURCHASE_ORDER.getCodePrefix());

        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
		draftCacheVO = redisComponent.saveDraftCacheVO(draftCacheVO);

		return draftCacheVO;
	}

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue){

        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO){
		Class<PurchaseOrderReqVO> clazz = PurchaseOrderReqVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.PURCHASE_ORDER.getCodePrefix(),clazz);
    }
}