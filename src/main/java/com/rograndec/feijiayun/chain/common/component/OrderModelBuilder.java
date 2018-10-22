package com.rograndec.feijiayun.chain.common.component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchange;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeShelf;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutShelf;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClear;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClearShelf;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoad;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoadDetail;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroyShelf;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryShelf;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjust;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjustShelf;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherIn;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherInDetail;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutShelf;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMoveDetail;
import com.rograndec.feijiayun.chain.business.storage.split.entity.Split;
import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitDetail;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventory;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventoryDetail;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.OrderDtlVO;
import com.rograndec.feijiayun.chain.common.vo.OrderVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * 
 * @ClassName: OrderModelBuilder  
 * @Description: 单据模型构造器
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月26日 下午12:10:49  
 *
 */
public class OrderModelBuilder {
	
	private UserVO user;
	
	public OrderModelBuilder(){}
	
	public OrderModelBuilder(UserVO user) {
		this.user = user;
	}
	
	public <HEAD, DTL> OrderModel buildOrderModel(OrderRule orderRule, HEAD head, List<DTL> dtlList){
		OrderModel orderModel = null;
		switch(orderRule){
			// 期初入库
			case OPENING_INVENTORY :
				orderModel = buildOpeningInverntoryModel(orderRule, (OpeningInventory)head, (List<OpeningInventoryDetail>)dtlList);
				break;
			// 采购入库
			case PURCHASE_IN :
				orderModel = buildPurchaseInStorageModel(orderRule, (PurchaseInStorage)head, (List<PurchaseInStorageShelf>)dtlList);
				break;
			// 购进退出出库
			case PURCHASE_RETURN_OUT :
				orderModel = buildPurchaseReturnOutModel(orderRule, (PurchaseReturnOut)head, (List<PurchaseReturnOutShelf>)dtlList);
				break;
			// 货位移动（双项）
			case SHELF_MOVE:
				orderModel = buildShelfMoveModel(orderRule, (ShelfMove)head, (List<ShelfMoveDetail>)dtlList);
				break;
			// 其它入库
			case RECEIVE :
				orderModel = buildOtherInModel(orderRule, (OtherIn)head, (List<OtherInDetail>)dtlList);
				break;
			// 其它出库
			case SEND:
				orderModel = buildOtherOutModel(orderRule, (OtherOut)head, (List<OtherOutShelf>)dtlList);
				break;
			// 中药饮片装斗（双向）
			case LOAD_BUCKET:
				orderModel = buildLoadBucketModel(orderRule, (GoodsLoad)head, (List<GoodsLoadDetail>)dtlList);
				break;
			// 中药饮片清斗出库
			case CLEAR_BUCKET:
				orderModel = buildClearBucketModel(orderRule, (GoodsClear)head, (List<GoodsClearShelf>)dtlList);
				break;
			// 药品拆零(相当于原品种出库，拆零后的新品种入库，特殊处理：外部直接调用)
//			case SPLIT:
//				break;
			// 批号调整(相当于原批号出库，调整后的新批号入库，特殊处理：外部直接调用)
//			case LOT_ADJUST:
//				break;
			// 药品销毁出库
			case DESTROY:
				orderModel = buildGoodsDestroyModel(orderRule, (GoodsDestroy)head, (List<GoodsDestroyShelf>)dtlList);
				break;
			// 盘点
			case INVENTORY:
				orderModel = buildInventoryModel(orderRule, (Inventory) head, (List<InventoryShelf>)dtlList);
				break;
			// 积分兑换出库
			case INTEGRAL_EXCHANGE:
				orderModel = buildIntegralExchangeModel(orderRule, (IntegralExchange) head, (List<IntegralExchangeShelf>) dtlList);
				break;
			// 销售出库
			case SALES_OUT:
				orderModel = buildSalesOutModel(orderRule, (Sale)head, (List<SaleShelf>) dtlList);
				break;
			// 销后退回入库
			case SALES_RETURN_IN:
				orderModel = buildSalesReturnInModel(orderRule, (Sale)head, (List<SaleShelf>) dtlList);
				break;
			// 总部配货出库
			case DISTR_OUT:
				orderModel = buildDistrOutModel(orderRule, (DistrOut)head, (List<DistrOutShelf>) dtlList);
				break;
			// 分店配进入库
			case DISTR_IN:
				orderModel = buildDistrInModel(orderRule, (DistrIn)head, (List<DistrInShelf>) dtlList);
				break;
			// 分店配进退出出库
			case DISTR_IN_RETURN_OUT:
				orderModel = buildDistrInReturnOutModel(orderRule, (DistrInReturnOut)head, (List<DistrInReturnOutShelf>) dtlList);
				break;
			// 总部配后退回入库
			case DISTR_RETURN_IN:
				orderModel = buildDistrReturnInModel(orderRule, (DistrReturnIn)head, (List<DistrReturnInShelf>) dtlList);
				break;
			default:
				throw new BusinessException("未知业务类型");
		}
		return orderModel;
	}


	/**
	 *
	 * @Title: buildInventoryModel
	 * @Description: 构造盘点单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 盘点单
	 * @param @param srcDtlList 盘点单货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildInventoryModel(OrderRule orderRule, Inventory srcOrder, List<InventoryShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.INVENTORY.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getInvDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getDiffAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getDiffAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getDiffNotaxAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getDiffTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由盘点单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if (CollectionUtils.isNotEmpty(srcDtlList)) {
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for (InventoryShelf srcDtl : srcDtlList) {
				BigDecimal diffQuantity = srcDtl.getDiffQuantity()==null?BigDecimal.ZERO:srcDtl.getDiffQuantity();
				// 盘亏
				if(diffQuantity.compareTo(BigDecimal.ZERO) < 0){
					// 构建出库明细
					orderDtl = new OrderDtlVO();
					orderDtl.setOrderDtlId(srcDtl.getId());
					orderDtl.setOrderId(srcDtl.getInvId());
					orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
					orderDtl.setParentId(srcDtl.getParentId());
					orderDtl.setDirection(OrderDirection.OUT.getDirection());
					orderDtl.setOrderCode(srcOrder.getCode());
					orderDtl.setOrderType(srcOrder.getOrderType());
					orderDtl.setOrderDate(srcOrder.getInvDate());
					orderDtl.setLineNum(srcDtl.getLineNum());
					orderDtl.setCompanyId(user.getEnterpriseId());
					orderDtl.setCompanyCode(user.getEnterpriseCode());
					orderDtl.setCompanyName(user.getEnterpriseName());
					orderDtl.setGoodsId(srcDtl.getGoodsId());
					orderDtl.setGoodsCode(srcDtl.getGoodsCode());
					orderDtl.setGoodsName(srcDtl.getGoodsName());
					orderDtl.setLotId(srcDtl.getLotId());
					orderDtl.setLotNum(srcDtl.getLotNumber());
					orderDtl.setProductDate(srcDtl.getProductDate());
					orderDtl.setValidUntil(srcDtl.getValidDate());
					orderDtl.setShelfId(srcDtl.getShelfId());
					orderDtl.setShelfName(srcDtl.getShelfName());
					orderDtl.setQuantity(srcDtl.getDiffQuantity().abs());
					orderDtl.setUnitPrice(srcDtl.getUnitPrice());
					// 行折扣（%）
					orderDtl.setLineDiscount(new BigDecimal(100));
					// 金额
					orderDtl.setAmount(srcDtl.getAmount());
					// 整单折扣（%）
					orderDtl.setWholeDiscount(new BigDecimal(100));
					// 行优惠（整单优惠分摊到行的金额）
					orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
					// 实际单价
					orderDtl.setRealPrice(srcDtl.getUnitPrice());
					// 实际金额（实际单价*数量）
					orderDtl.setRealAmount(srcDtl.getDiffAmount().abs());
					// 税率
					orderDtl.setTaxRate(srcDtl.getTaxRate());
					// 不含税单价
					orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
					// 不含税金额
					orderDtl.setNotaxRealAmount(srcDtl.getDiffNotaxAmount().abs());
					// 税额
					orderDtl.setTaxAmount(srcDtl.getDiffTaxAmount().abs());
					// 利润金额
					orderDtl.setProfit(BigDecimal.ZERO);
					// 不含税利润金额
					orderDtl.setNotaxProfit(BigDecimal.ZERO);
					// 利润率
					orderDtl.setProfitRate(BigDecimal.ZERO);
					// 不含税利润率
					orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
					orderDtl.setRemark("由盘亏出库单生成");
					orderDtl.setCreaterId(srcDtl.getCreaterId());
					orderDtl.setCreaterCode(srcDtl.getCreaterCode());
					orderDtl.setCreaterName(srcDtl.getCreaterName());
					orderDtl.setCreateTime(srcDtl.getCreateTime());
					orderDtl.setModifierId(srcDtl.getModifierId());
					orderDtl.setModifierCode(srcDtl.getModifierCode());
					orderDtl.setModifierName(srcDtl.getModifierName());
					orderDtl.setUpdateTime(srcDtl.getUpdateTime());

					orderDtlList.add(orderDtl);
				}else if(diffQuantity.compareTo(BigDecimal.ZERO) > 0){
					// 盘盈
					// 构建入库明细单
					orderDtl = new OrderDtlVO();
					orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
					orderDtl.setParentId(srcDtl.getParentId());
					orderDtl.setOrderDtlId(srcDtl.getId());
					orderDtl.setOrderId(srcDtl.getInvId());
					orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
					orderDtl.setParentId(srcDtl.getParentId());
					orderDtl.setDirection(OrderDirection.IN.getDirection());
					orderDtl.setOrderCode(srcOrder.getCode());
					orderDtl.setOrderType(srcOrder.getOrderType());
					orderDtl.setOrderDate(srcOrder.getInvDate());
					orderDtl.setLineNum(srcDtl.getLineNum());
					orderDtl.setCompanyId(user.getEnterpriseId());
					orderDtl.setCompanyCode(user.getEnterpriseCode());
					orderDtl.setCompanyName(user.getEnterpriseName());
					orderDtl.setGoodsId(srcDtl.getGoodsId());
					orderDtl.setGoodsCode(srcDtl.getGoodsCode());
					orderDtl.setGoodsName(srcDtl.getGoodsName());
					orderDtl.setLotId(srcDtl.getLotId());
					orderDtl.setLotNum(srcDtl.getLotNumber());
					orderDtl.setProductDate(srcDtl.getProductDate());
					orderDtl.setValidUntil(srcDtl.getValidDate());
					orderDtl.setShelfId(srcDtl.getShelfId());
					orderDtl.setShelfName(srcDtl.getShelfName());
					orderDtl.setQuantity(srcDtl.getDiffQuantity());
					orderDtl.setUnitPrice(srcDtl.getUnitPrice());
					// 行折扣（%）
					orderDtl.setLineDiscount(new BigDecimal(100));
					// 金额
					orderDtl.setAmount(srcDtl.getAmount());
					// 整单折扣（%）
					orderDtl.setWholeDiscount(new BigDecimal(100));
					// 行优惠（整单优惠分摊到行的金额）
					orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
					// 实际单价
					orderDtl.setRealPrice(srcDtl.getUnitPrice());
					// 实际金额（实际单价*数量）
					orderDtl.setRealAmount(srcDtl.getDiffAmount());
					// 税率
					orderDtl.setTaxRate(srcDtl.getTaxRate());
					// 不含税单价
					orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
					// 不含税金额
					orderDtl.setNotaxRealAmount(srcDtl.getDiffNotaxAmount());
					// 税额
					orderDtl.setTaxAmount(srcDtl.getDiffTaxAmount());
					// 利润金额
					orderDtl.setProfit(BigDecimal.ZERO);
					// 不含税利润金额
					orderDtl.setNotaxProfit(BigDecimal.ZERO);
					// 利润率
					orderDtl.setProfitRate(BigDecimal.ZERO);
					// 不含税利润率
					orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
					orderDtl.setRemark("由盘盈入库单生成");
					orderDtl.setCreaterId(srcDtl.getCreaterId());
					orderDtl.setCreaterCode(srcDtl.getCreaterCode());
					orderDtl.setCreaterName(srcDtl.getCreaterName());
					orderDtl.setCreateTime(srcDtl.getCreateTime());
					orderDtl.setModifierId(srcDtl.getModifierId());
					orderDtl.setModifierCode(srcDtl.getModifierCode());
					orderDtl.setModifierName(srcDtl.getModifierName());
					orderDtl.setUpdateTime(srcDtl.getUpdateTime());

					orderDtlList.add(orderDtl);
				}else{
					// 无损溢 Do Nothing
				}
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

    /**
     *
     * @Title: buildLotAdjustModel
     * @Description: 构造批号调整单数据模型
     * @param @param orderRule 单据枚举类
     * @param @param srcOrder 批号调整单
     * @param @param srcDtlList 批号调整单货位明细列表
     * @param @return
     * @return OrderModel 返回类型
     * @throws
     */
    public OrderModel buildLotAdjustModel(OrderRule orderRule, LotAdjust srcOrder, List<LotAdjustShelf> srcDtlList, int lotAdjustDirection){
        OrderModel orderModel = new OrderModel();
        String remark = "";
        // 构建总单
        OrderVO order = new OrderVO();
        orderModel.setOrderRule(orderRule);
        order.setEnterpriseId(srcOrder.getEnterpriseId());
        order.setParentId(srcOrder.getParentId());
        order.setDirection(lotAdjustDirection);
        order.setOrderId(srcOrder.getId());
        order.setOrderCode(srcOrder.getCode());
        order.setOrderType(srcOrder.getOrderType());
        order.setOrderDate(srcOrder.getAdjustDate());
        order.setCompanyId(user.getEnterpriseId());
        order.setCompanyCode(user.getEnterpriseCode());
        order.setCompanyName(user.getEnterpriseName());
		// 数量合计
        order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
        order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
        if(lotAdjustDirection == OrderDirection.LOT_ADJUST_OUT.getDirection()){
            remark = "由批号调整出库单生成";
        }else if(lotAdjustDirection == OrderDirection.LOT_ADJUST_IN.getDirection()){
            remark = "由批号调整入库单生成";
        }
		// 金额合计
        order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
        order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
        order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
        order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
        order.setNotaxRealAmountTotal(srcOrder.getNotaxAmountTotal());
		// 税额合计
        order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
        order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
        order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
        order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
        order.setNotaxProfitRate(BigDecimal.ZERO);
        order.setRemark(remark);
        order.setCreaterId(srcOrder.getCreaterId());
        order.setCreaterCode(srcOrder.getCreaterCode());
        order.setCreaterName(srcOrder.getCreaterName());
        order.setCreateTime(srcOrder.getCreateTime());
        order.setModifierId(srcOrder.getCreaterId());
        order.setModifierCode(srcOrder.getCreaterCode());
        order.setModifierName(srcOrder.getCreaterName());
        order.setUpdateTime(srcOrder.getCreateTime());
        orderModel.setOrder(order);

        // 构建明细
        if(CollectionUtils.isNotEmpty(srcDtlList)){
            List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
            OrderDtlVO orderDtl = null;
            int index = 0;
            for(LotAdjustShelf srcDtl:srcDtlList){
                orderDtl = new OrderDtlVO();
                orderDtl.setOrderDtlId(srcDtl.getId());
                orderDtl.setOrderId(srcDtl.getAdjustId());
                orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
                orderDtl.setParentId(srcDtl.getParentId());
                if(lotAdjustDirection == OrderDirection.LOT_ADJUST_OUT.getDirection()){
                    orderDtl.setDirection(OrderDirection.OUT.getDirection());
                }else{
                    orderDtl.setDirection(OrderDirection.IN.getDirection());
                }
                orderDtl.setOrderCode(srcOrder.getCode());
                orderDtl.setOrderType(srcOrder.getOrderType());
                orderDtl.setOrderDate(srcOrder.getAdjustDate());
                orderDtl.setOrderDtlId(srcDtl.getId());
                orderDtl.setLineNum(index);
                orderDtl.setCompanyId(user.getEnterpriseId());
                orderDtl.setCompanyCode(user.getEnterpriseCode());
                orderDtl.setCompanyName(user.getEnterpriseName());
                orderDtl.setGoodsId(srcDtl.getGoodsId());
                orderDtl.setGoodsCode(srcDtl.getGoodsCode());
                orderDtl.setGoodsName(srcDtl.getGoodsName());
                orderDtl.setLotId(srcDtl.getLotId());
                orderDtl.setLotNum(srcDtl.getLotNum());
                orderDtl.setProductDate(srcDtl.getProductDate());
                orderDtl.setValidUntil(srcDtl.getValidUntil());
                orderDtl.setShelfId(srcDtl.getShelfId());
                orderDtl.setShelfName(srcDtl.getShelfName());
                orderDtl.setQuantity(srcDtl.getQuantity());
                orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
                orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
                orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
                orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
                orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
                orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 不含税单价
                orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
				// 实际金额（实际单价*数量）
                orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
                orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税金额
                orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
                orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
                orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
                orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
                orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
                orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
                orderDtl.setRemark(remark);
                orderDtl.setCreaterId(srcDtl.getCreaterId());
                orderDtl.setCreaterCode(srcDtl.getCreaterCode());
                orderDtl.setCreaterName(srcDtl.getCreaterName());
                orderDtl.setCreateTime(srcDtl.getCreateTime());
                orderDtl.setModifierId(srcDtl.getCreaterId());
                orderDtl.setModifierCode(srcDtl.getCreaterCode());
                orderDtl.setModifierName(srcDtl.getCreaterName());
                orderDtl.setUpdateTime(srcDtl.getCreateTime());

                orderDtlList.add(orderDtl);
                index ++;
            }
            orderModel.setOrderDtlList(orderDtlList);
        }
        return orderModel;
    }

	/**
	 *
	 * @Title: buildSplitModel
	 * @Description: 构造药品拆零单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 药品拆零单
	 * @param @param srcDtlList 药品拆零单明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	public OrderModel buildSplitModel(OrderRule orderRule, Split srcOrder, List<SplitDetail> srcDtlList, int splitDirection){
		OrderModel orderModel = new OrderModel();
		String remark = "";
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(splitDirection);
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getSplitDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		if(splitDirection == OrderDirection.SPLIT_OUT.getDirection()){
			// 数量合计
			order.setQuantityTotal(srcOrder.getQuantityTotal());
			// 品种合计
			order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
			remark = "由药品拆零出库单生成";
		}else{
			// 拆零品种数量合计
			order.setQuantityTotal(srcOrder.getSplitQuantityTotal());
			// 拆零品种合计
			order.setVarietiesQuantity(srcOrder.getSplitVarietiesQuantity());
			remark = "由药品拆零入库单生成";
		}
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark(remark);
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(SplitDetail srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getSplitId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getSplitDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
                if(splitDirection == OrderDirection.SPLIT_OUT.getDirection()){
                    orderDtl.setDirection(OrderDirection.OUT.getDirection());
					orderDtl.setGoodsId(srcDtl.getGoodsId());
					orderDtl.setGoodsCode(srcDtl.getGoodsCode());
					orderDtl.setGoodsName(srcDtl.getGoodsName());
					orderDtl.setUnitPrice(srcDtl.getUnitPrice());
					orderDtl.setQuantity(srcDtl.getQuantity());
					// 实际单价
					orderDtl.setRealPrice(srcDtl.getUnitPrice());
					// 不含税单价
					orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
                }else{
                    orderDtl.setDirection(OrderDirection.IN.getDirection());
					orderDtl.setGoodsId(srcDtl.getSplitGoodsId());
					orderDtl.setGoodsCode(srcDtl.getSplitGoodsCode());
					orderDtl.setGoodsName(srcDtl.getSplitGoodsName());
					orderDtl.setUnitPrice(srcDtl.getSplitUnitPrice());
					orderDtl.setQuantity(srcDtl.getTargetQuantity());
					// 拆零品种实际单价
					orderDtl.setRealPrice(srcDtl.getSplitUnitPrice());
					// 拆零品种不含税单价
					orderDtl.setNotaxRealPrice(srcDtl.getSplitNotaxPrice());
                }
				// 行折扣（%）
				orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark(remark);
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildDistrReturnInModel
	 * @Description: 构造总部配后退回入库单数据模型
	 * @param @param orderRule 单据规则枚举
	 * @param @param srcOrder 总部配后退回入库单
	 * @param @param srcDtlList 总部配后退回入库单批号／货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildDistrReturnInModel(OrderRule orderRule, DistrReturnIn srcOrder,
										 List<DistrReturnInShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.IN.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getReturnInDate());
		order.setCompanyId(srcOrder.getRequestUnitId());
		order.setCompanyCode(srcOrder.getRequestUnitCode());
		order.setCompanyName(srcOrder.getRequestUnitName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		BigDecimal wholeDiscount = srcOrder.getWholeDiscount() == null ? new BigDecimal(100) : srcOrder.getWholeDiscount();
		// 整单折扣(%)
		order.setWholeDiscount(wholeDiscount);
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由总部配后退回入库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if (CollectionUtils.isNotEmpty(srcDtlList)) {
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for (DistrReturnInShelf srcDtl : srcDtlList) {
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getReturnInId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getReturnInDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getRequestUnitId());
				orderDtl.setCompanyCode(srcOrder.getRequestUnitCode());
				orderDtl.setCompanyName(srcOrder.getRequestUnitName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount() == null ? new BigDecimal(100) : srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(wholeDiscount);
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getRealPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由总部配后退回入库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildDistrInReturnOutModel
	 * @Description: 构造分店配进退出出库单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 分店配进退出出库单
	 * @param @param srcDtlList 分店配进退出出库单货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildDistrInReturnOutModel(OrderRule orderRule, DistrInReturnOut srcOrder,
										  List<DistrInReturnOutShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getOutDate());
		order.setCompanyId(srcOrder.getDistrUnitId());
		order.setCompanyCode(srcOrder.getDistrUnitCode());
		order.setCompanyName(srcOrder.getDistrUnitName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		BigDecimal wholeDiscount = srcOrder.getWholeDiscount() == null ? new BigDecimal(100) : srcOrder.getWholeDiscount();
		// 整单折扣(%)
		order.setWholeDiscount(wholeDiscount);
		// 整单优惠金额
		order.setWholeDiscountAmount(srcOrder.getWholeDiscountAmount());
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由分店配进退出出库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(DistrInReturnOutShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getOutId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getOutDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getDistrUnitId());
				orderDtl.setCompanyCode(srcOrder.getDistrUnitCode());
				orderDtl.setCompanyName(srcOrder.getDistrUnitName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount()==null?new BigDecimal(100):srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(wholeDiscount);
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(srcDtl.getLineDiscountAmount());
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由分店配进退出出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildDistrInModel
	 * @Description: 构造分店配进入库单数据模型
	 * @param @param orderRule 单据规则枚举
	 * @param @param srcOrder 分店配进入库单
	 * @param @param srcDtlList 分店配进入库单批号／货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildDistrInModel(OrderRule orderRule, DistrIn srcOrder,
												   List<DistrInShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.IN.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getInDate());
		order.setCompanyId(srcOrder.getDistrUnitId());
		order.setCompanyCode(srcOrder.getDistrUnitCode());
		order.setCompanyName(srcOrder.getDistrUnitName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		BigDecimal wholeDiscount = srcOrder.getWholeDiscount() == null ? new BigDecimal(100) : srcOrder.getWholeDiscount();
		// 整单折扣(%)
		order.setWholeDiscount(wholeDiscount);
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由分店配进入库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(DistrInShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());//配进入库货位明细id
				orderDtl.setOrderId(srcDtl.getInId());//配进入库的主单id
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getInDate());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getDistrUnitId());
				orderDtl.setCompanyCode(srcOrder.getDistrUnitCode());
				orderDtl.setCompanyName(srcOrder.getDistrUnitName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount()==null?new BigDecimal(100):srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(wholeDiscount);
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getRealPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由分店配进入库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildDistrOutModel
	 * @Description: 构造总部配货出库单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 总部配货出库单
	 * @param @param srcDtlList 总部配货出库单货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildDistrOutModel(OrderRule orderRule, DistrOut srcOrder,
										  List<DistrOutShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getOutDate());
		order.setCompanyId(srcOrder.getRequestUnitId());
		order.setCompanyCode(srcOrder.getRequestUnitCode());
		order.setCompanyName(srcOrder.getRequestUnitName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		BigDecimal wholeDiscount = srcOrder.getWholeDiscount() == null ? new BigDecimal(100) : srcOrder.getWholeDiscount();
		// 整单折扣(%)
		order.setWholeDiscount(wholeDiscount);
		// 整单优惠金额
		order.setWholeDiscountAmount(srcOrder.getWholeDiscountAmount());
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由总部配货出库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(DistrOutShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getOutId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getOutDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getRequestUnitId());
				orderDtl.setCompanyCode(srcOrder.getRequestUnitCode());
				orderDtl.setCompanyName(srcOrder.getRequestUnitName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount()==null?new BigDecimal(100):srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(wholeDiscount);
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(srcDtl.getLineDiscountAmount());
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由总部配货出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildSalesOutModel
	 * @Description: 构造销后退回入库单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 销后退回入库单
	 * @param @param srcDtlList 销后退回入库单货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildSalesReturnInModel(OrderRule orderRule, Sale srcOrder,
											   List<SaleShelf> dtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.IN.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getSaleDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(srcOrder.getWholeDiscount());
		// 整单优惠金额
		order.setWholeDiscountAmount(srcOrder.getWholeDiscountAmount());
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(srcOrder.getProfitTotal());
		// 不含税利润合计
		order.setNotaxProfitTotal(srcOrder.getNotaxProfitTotal());
		// 利润率
		order.setProfitRate(srcOrder.getProfitRate());
		// 不含会利润率
		order.setNotaxProfitRate(srcOrder.getNotaxProfitRate());
		order.setRemark("由销后退回入库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(dtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(SaleShelf srcDtl:dtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getSaleId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getSaleDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(srcDtl.getWholeDiscount());
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(srcDtl.getLineDiscountAmount());
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(srcDtl.getProfit());
				// 不含税利润金额
				orderDtl.setNotaxProfit(srcDtl.getNotaxProfit());
				// 利润率
				orderDtl.setProfitRate(srcDtl.getProfitRate());
				// 不含税利润率
				orderDtl.setNotaxProfitRate(srcDtl.getNotaxProfitRate());
				
				// 按照销退单计算得到的成本金额和不含税成本金额赋值
				orderDtl.setCostAmount(srcDtl.getRealAmount().subtract(srcDtl.getProfit()));
				orderDtl.setNotaxCostAmount(srcDtl.getNotaxRealAmount().subtract(srcDtl.getNotaxProfit()));
				
				orderDtl.setRemark("由销后退回入库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildSalesOutModel
	 * @Description: 构造销售出库单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 销售出库单
	 * @param @param srcDtlList 销售出库单货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildSalesOutModel(OrderRule orderRule, Sale srcOrder,
										  List<SaleShelf> dtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getSaleDate());
		order.setCompanyId(srcOrder.getMemberId());
		order.setCompanyCode(srcOrder.getMemberCardCode());
		order.setCompanyName(srcOrder.getMemberName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(srcOrder.getWholeDiscount());
		// 整单优惠金额
		order.setWholeDiscountAmount(srcOrder.getWholeDiscountAmount());
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(srcOrder.getProfitTotal());
		// 不含税利润合计
		order.setNotaxProfitTotal(srcOrder.getNotaxProfitTotal());
		// 利润率
		order.setProfitRate(srcOrder.getProfitRate());
		// 不含会利润率
		order.setNotaxProfitRate(srcOrder.getNotaxProfitRate());
		order.setRemark("由销售出库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(dtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(SaleShelf srcDtl:dtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getSaleId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getSaleDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getMemberId());
				orderDtl.setCompanyCode(srcOrder.getMemberCardCode());
				orderDtl.setCompanyName(srcOrder.getMemberName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(srcDtl.getWholeDiscount());
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(srcDtl.getLineDiscountAmount());
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(srcDtl.getProfit());
				// 不含税利润金额
				orderDtl.setNotaxProfit(srcDtl.getNotaxProfit());
				// 利润率
				orderDtl.setProfitRate(srcDtl.getProfitRate());
				// 不含税利润率
				orderDtl.setNotaxProfitRate(srcDtl.getNotaxProfitRate());
				orderDtl.setRemark("由销售出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildIntegralExchangeModel
	 * @Description: 构造积分兑换单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 积分兑换单
	 * @param @param srcDtlList 积分兑换单货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildIntegralExchangeModel(OrderRule orderRule, IntegralExchange srcOrder,
										  List<IntegralExchangeShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getExchangeDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由积分兑换出库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			int index = 0;
			for(IntegralExchangeShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getExchangeId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getExchangeDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(index);
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由积分兑换出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
				index ++;
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildGoodsDestroyModel
	 * @Description: 构造药品销毁单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 药品销毁单
	 * @param @param srcDtlList 药品销毁货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildGoodsDestroyModel(OrderRule orderRule, GoodsDestroy srcOrder,
										  List<GoodsDestroyShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getDestroyDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由药品销毁出库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(GoodsDestroyShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getDestroyId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getDestroyDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由药品销毁出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildClearBucketModel
	 * @Description: 构造中药饮片清斗单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 中药饮片清斗单
	 * @param @param srcDtlList 中药饮片清斗货位列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildClearBucketModel(OrderRule orderRule, GoodsClear srcOrder,
											 List<GoodsClearShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getClearDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由中药饮片清斗单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(GoodsClearShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getClearId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getClearDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由中药饮片清斗单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildLoadBucketModel
	 * @Description: 构造中药饮片装斗数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 中药饮片装斗单
	 * @param @param srcDtlList 中药饮片装斗明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildLoadBucketModel(OrderRule orderRule, GoodsLoad srcOrder, List<GoodsLoadDetail> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.LOAD.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getLoadDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		order.setRemark("由中药饮片装斗单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(GoodsLoadDetail srcDtl:srcDtlList){
				// 构建出库明细
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getLoadId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getLoadDate());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getSrcShelfId());
				orderDtl.setShelfName(srcDtl.getSrcShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setRemark("由中药饮片装斗出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());
				orderDtlList.add(orderDtl);

				// 构建入库明细单
				orderDtl = new OrderDtlVO();
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getLoadId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getLoadDate());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getTargetShelfId());
				orderDtl.setShelfName(srcDtl.getTargetShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setRemark("由中药饮片装斗入库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildOtherOutModel
	 * @Description: 构造其它出库单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 其它出库单
	 * @param @param srcDtlList 其它出库货位列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildOtherOutModel(OrderRule orderRule, OtherOut srcOrder,
										  List<OtherOutShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getOutDate());
		order.setCompanyId(srcOrder.getFlowUnitId());
		order.setCompanyCode(srcOrder.getFlowUnitCode());
		order.setCompanyName(srcOrder.getFlowUnitName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由其它出库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(OtherOutShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getOutId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getOutDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getFlowUnitId());
				orderDtl.setCompanyCode(srcOrder.getFlowUnitCode());
				orderDtl.setCompanyName(srcOrder.getFlowUnitName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由其它出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildOtherInModel
	 * @Description: 构造其它入库单数据模型
	 * @param @param orderRule 单据规则枚举
	 * @param @param srcOrder 其它入库单
	 * @param @param srcDtlList 其它入库品种／批号／货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildOtherInModel(OrderRule orderRule, OtherIn srcOrder,
												   List<OtherInDetail> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.IN.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getInDate());
		order.setCompanyId(srcOrder.getSrcUnitId());
		order.setCompanyCode(srcOrder.getSrcUnitCode());
		order.setCompanyName(srcOrder.getSrcUnitName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由其它入库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(OtherInDetail srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getInId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getInDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getSrcUnitId());
				orderDtl.setCompanyCode(srcOrder.getSrcUnitCode());
				orderDtl.setCompanyName(srcOrder.getSrcUnitName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由其它入库生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildShelfMoveModel
	 * @Description: 构造货位移动单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 货位移动单
	 * @param @param srcDtlList 货位移动明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildShelfMoveModel(OrderRule orderRule, ShelfMove srcOrder, List<ShelfMoveDetail> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.MOVE.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getMoveDate());
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		order.setRemark("由货位移动单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(ShelfMoveDetail srcDtl:srcDtlList){
				// 构建出库明细
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getMoveId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getMoveDate());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getSrcShelfId());
				orderDtl.setShelfName(srcDtl.getSrcShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setRemark("由货位移动出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());
				orderDtlList.add(orderDtl);

				// 构建入库明细单
				orderDtl = new OrderDtlVO();
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getMoveId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getMoveDate());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(user.getEnterpriseId());
				orderDtl.setCompanyCode(user.getEnterpriseCode());
				orderDtl.setCompanyName(user.getEnterpriseName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getTargetShelfId());
				orderDtl.setShelfName(srcDtl.getTargetShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setRemark("由货位移动入库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 *
	 * @Title: buildPurchaseReturnOutModel
	 * @Description: 构造购进退出出库单数据模型
	 * @param @param orderRule 单据枚举类
	 * @param @param srcOrder 购进退出出库单
	 * @param @param srcDtlList 购进退出出库货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型
	 * @throws
	 */
	private OrderModel buildPurchaseReturnOutModel(OrderRule orderRule, PurchaseReturnOut srcOrder,
												   List<PurchaseReturnOutShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.OUT.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getOutDate());
		order.setCompanyId(srcOrder.getSupplierId());
		order.setCompanyCode(srcOrder.getSupplierCode());
		order.setCompanyName(srcOrder.getSupplierName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(srcOrder.getWholeDiscount()==null?BigDecimal.ZERO:srcOrder.getWholeDiscount());
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由购进退出出库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(new Date());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);

		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(PurchaseReturnOutShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getOutId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.OUT.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getOutDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getSupplierId());
				orderDtl.setCompanyCode(srcOrder.getSupplierCode());
				orderDtl.setCompanyName(srcOrder.getSupplierName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotId(srcDtl.getLotId());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount()==null?BigDecimal.ZERO:srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(srcDtl.getWholeDiscount()==null?BigDecimal.ZERO:srcDtl.getWholeDiscount());
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(srcDtl.getLineDiscountAmount());
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由购进退出出库单生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());

				orderDtlList.add(orderDtl);
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 * 
	 * @Title: buildPurchaseInStorageModel 
	 * @Description: 构造采购入库单数据模型
	 * @param @param orderRule 单据规则枚举
	 * @param @param srcOrder 采购入库单
	 * @param @param srcDtlList 采购入库批号／货位明细列表
	 * @param @return
	 * @return OrderModel 返回类型 
	 * @throws
	 */
	private OrderModel buildPurchaseInStorageModel(OrderRule orderRule, PurchaseInStorage srcOrder,
			List<PurchaseInStorageShelf> srcDtlList) {
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());	
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.IN.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getInStorageDate());
		order.setCompanyId(srcOrder.getSupplierId());
		order.setCompanyCode(srcOrder.getSupplierCode());
		order.setCompanyName(srcOrder.getSupplierName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		BigDecimal wholeDiscount = srcOrder.getWholeDiscount() == null ? new BigDecimal(100) : srcOrder.getWholeDiscount();
		// 整单折扣(%)
		order.setWholeDiscount(wholeDiscount);
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxRealAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由采购入库单生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(srcOrder.getCreateTime());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);
		
		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			for(PurchaseInStorageShelf srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getInStorageId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getInStorageDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(srcDtl.getLineNum());
				orderDtl.setCompanyId(srcOrder.getSupplierId());
				orderDtl.setCompanyCode(srcOrder.getSupplierCode());
				orderDtl.setCompanyName(srcOrder.getSupplierName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(srcDtl.getLineDiscount()==null?new BigDecimal(100):srcDtl.getLineDiscount());
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(wholeDiscount);
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getRealPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxRealPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxRealAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由采购入库生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());
				
				orderDtlList.add(orderDtl); 
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}

	/**
	 * @Title: buildOpeningInverntoryModel
	 * @Description: 构造期初库存单数据模型 
	 * @param @param srcOrder 期初入库单
	 * @param @param srcDtlList 期初入库单明细列表
	 * @param @return   
	 * @return OrderModel 返回类型  
	 * @throws
	 */
	private OrderModel buildOpeningInverntoryModel(OrderRule orderRule, OpeningInventory srcOrder, List<OpeningInventoryDetail> srcDtlList){
		OrderModel orderModel = new OrderModel();
		// 构建总单
		OrderVO order = new OrderVO();
		orderModel.setOrderRule(orderRule);
		order.setEnterpriseId(srcOrder.getEnterpriseId());	
		order.setParentId(srcOrder.getParentId());
		order.setDirection(OrderDirection.IN.getDirection());
		order.setOrderId(srcOrder.getId());
		order.setOrderCode(srcOrder.getCode());
		order.setOrderType(srcOrder.getOrderType());
		order.setOrderDate(srcOrder.getStorageDate());
		// 期初供货单位存储于明细单，因此总单的单位信息用当前登录人对应企业信息填充
		order.setCompanyId(user.getEnterpriseId());
		order.setCompanyCode(user.getEnterpriseCode());
		order.setCompanyName(user.getEnterpriseName());
		// 数量合计
		order.setQuantityTotal(srcOrder.getQuantityTotal());
		// 品种合计
		order.setVarietiesQuantity(srcOrder.getVarietiesQuantity());
		// 金额合计
		order.setAmountTotal(srcOrder.getAmountTotal());
		// 整单折扣(%)
		order.setWholeDiscount(new BigDecimal(100));
		// 整单优惠金额
		order.setWholeDiscountAmount(BigDecimal.ZERO);
		// 实际金额合计
		order.setRealAmountTotal(srcOrder.getAmountTotal());
		// 不含税实际金额合计
		order.setNotaxRealAmountTotal(srcOrder.getNotaxAmountTotal());
		// 税额合计
		order.setTaxAmountTotal(srcOrder.getTaxAmountTotal());
		// 利润合计
		order.setProfitTotal(BigDecimal.ZERO);
		// 不含税利润合计
		order.setNotaxProfitTotal(BigDecimal.ZERO);
		// 利润率
		order.setProfitRate(BigDecimal.ZERO);
		// 不含会利润率
		order.setNotaxProfitRate(BigDecimal.ZERO);
		order.setRemark("由期初导入生成");
		order.setCreaterId(srcOrder.getCreaterId());
		order.setCreaterCode(srcOrder.getCreaterCode());
		order.setCreaterName(srcOrder.getCreaterName());
		order.setCreateTime(new Date());
		order.setModifierId(srcOrder.getModifierId());
		order.setModifierCode(srcOrder.getModifierCode());
		order.setModifierName(srcOrder.getModifierName());
		order.setUpdateTime(srcOrder.getUpdateTime());
		orderModel.setOrder(order);
		// 构建明细
		if(CollectionUtils.isNotEmpty(srcDtlList)){
			List<OrderDtlVO> orderDtlList = new ArrayList<OrderDtlVO>();
			OrderDtlVO orderDtl = null;
			int index = 0;
			for(OpeningInventoryDetail srcDtl:srcDtlList){
				orderDtl = new OrderDtlVO();
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setOrderId(srcDtl.getOrderId());
				orderDtl.setEnterpriseId(srcDtl.getEnterpriseId());
				orderDtl.setParentId(srcDtl.getParentId());
				orderDtl.setDirection(OrderDirection.IN.getDirection());
				orderDtl.setOrderCode(srcOrder.getCode());
				orderDtl.setOrderType(srcOrder.getOrderType());
				orderDtl.setOrderDate(srcOrder.getStorageDate());
				orderDtl.setOrderDtlId(srcDtl.getId());
				orderDtl.setLineNum(index);
				orderDtl.setCompanyId(srcDtl.getSupplierId());
				orderDtl.setCompanyCode(srcDtl.getSupplierCode());
				orderDtl.setCompanyName(srcDtl.getSupplierName());
				orderDtl.setGoodsId(srcDtl.getGoodsId());
				orderDtl.setGoodsCode(srcDtl.getGoodsCode());
				orderDtl.setGoodsName(srcDtl.getGoodsName());
				orderDtl.setLotNum(srcDtl.getLotNumber());
				orderDtl.setProductDate(srcDtl.getProductDate());
				orderDtl.setValidUntil(srcDtl.getValidDate());
				orderDtl.setShelfId(srcDtl.getShelfId());
				orderDtl.setShelfName(srcDtl.getShelfName());
				orderDtl.setQuantity(srcDtl.getQuantity());
				orderDtl.setUnitPrice(srcDtl.getUnitPrice());
				// 行折扣（%）
				orderDtl.setLineDiscount(new BigDecimal(100));
				// 金额
				orderDtl.setAmount(srcDtl.getAmount());
				// 整单折扣（%）
				orderDtl.setWholeDiscount(new BigDecimal(100));
				// 行优惠（整单优惠分摊到行的金额）
				orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
				// 实际单价
				orderDtl.setRealPrice(srcDtl.getUnitPrice());
				// 实际金额（实际单价*数量）
				orderDtl.setRealAmount(srcDtl.getAmount());
				// 税率
				orderDtl.setTaxRate(srcDtl.getTaxRate());
				// 不含税单价
				orderDtl.setNotaxRealPrice(srcDtl.getNotaxPrice());
				// 不含税金额
				orderDtl.setNotaxRealAmount(srcDtl.getNotaxAmount());
				// 税额
				orderDtl.setTaxAmount(srcDtl.getTaxAmount());
				// 利润金额
				orderDtl.setProfit(BigDecimal.ZERO);
				// 不含税利润金额
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				// 利润率
				orderDtl.setProfitRate(BigDecimal.ZERO);
				// 不含税利润率
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);
				orderDtl.setRemark("由期初导入生成");
				orderDtl.setCreaterId(srcDtl.getCreaterId());
				orderDtl.setCreaterCode(srcDtl.getCreaterCode());
				orderDtl.setCreaterName(srcDtl.getCreaterName());
				orderDtl.setCreateTime(srcDtl.getCreateTime());
				orderDtl.setModifierId(srcDtl.getModifierId());
				orderDtl.setModifierCode(srcDtl.getModifierCode());
				orderDtl.setModifierName(srcDtl.getModifierName());
				orderDtl.setUpdateTime(srcDtl.getUpdateTime());
				
				orderDtlList.add(orderDtl); 
				index ++;
			}
			orderModel.setOrderDtlList(orderDtlList);
		}
		return orderModel;
	}
	

	
}
