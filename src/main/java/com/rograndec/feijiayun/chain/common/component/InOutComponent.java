package com.rograndec.feijiayun.chain.common.component;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.CargoQualityStateVO;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInCheckMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnInDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnInMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnInShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.GoodsManageMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.keytable.dao.BatchNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.ProfitMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.BatchNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.InOutRecord;
import com.rograndec.feijiayun.chain.business.keytable.entity.InOutRecordDetail;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Profit;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.vo.AvgCostVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherInDetail;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.constant.DistrPriceType;
import com.rograndec.feijiayun.chain.common.constant.GoodsManageStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.TaxRateType;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.OrderDtlVO;
import com.rograndec.feijiayun.chain.common.vo.OrderVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * 
 * @ClassName: InOutComponent  
 * @Description: 出入库通用服务组件
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月26日 下午3:06:15  
 *
 */

@Component
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class InOutComponent {

	private static final Logger logger = LoggerFactory.getLogger(InOutComponent.class);

	@Autowired
	private LotNumberMapper lotNumberMapper;
	
	@Autowired
	private BatchNumberMapper batchNumberMapper;
	
	@Autowired
	private CostMapper costMapper;
	
	@Autowired
	private ProfitMapper profitMapper;
	
	@Autowired
	private WarehouseCargoAreaMapper warehouseCargoAreaMapper;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private InOutRecordMapper inOutRecordMapper; 
	
	@Autowired
	private InOutRecordDetailMapper inOutRecordDetailMapper;
	
	@Autowired
	private CodeComponent codeComponent;

	@Autowired
	private CommonComponent commonComponent;

	@Autowired
	private GoodsManageMapper goodsManageMapper;

	@Autowired
	private SaleMapper saleMapper;

	@Autowired
	private SaleShelfMapper saleShelfMapper;
	@Autowired
	private DistrReturnInMapper distrReturnInMapper;
	@Autowired
	private DistrReturnInDetailMapper distrReturnInDetailMapper;
	@Autowired
	private DistrReturnInShelfMapper distrReturnInShelfMapper;
	@Autowired
	private DistrReturnCheckDetailMapper distrReturnCheckDetailMapper;
	@Autowired
	private DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;

	@Autowired
	private OtherInDetailMapper otherInDetailMapper;
	@Autowired
	private DistrInReturnOutDetailMapper distrInReturnOutDetailMapper;
	@Autowired
	private DistrInReturnOutShelfMapper distrInReturnOutShelfMapper;

	@Autowired
	private DistrInShelfMapper distrInShelfMapper;

	@Autowired
	private DistrInDetailMapper distrInDetailMapper;

	@Autowired
	private DistrInCheckMapper distrInCheckMapper;

	@Autowired
	private DistrInCheckDetailMapper distrInCheckDetailMapper;

	@Autowired
	private DistrInNoticeDetailMapper distrInNoticeDetailMapper;

	@Autowired
	private DistrOutDetailMapper distrOutDetailMapper;

	@Autowired
	private DistrOutMapper distrOutMapper;

	@Autowired
	private DistrOutShelfMapper distrOutShelfMapper;



	/**
	 * @Title: generateKeyTableDatas  
	 * @Description: 出入库业务生成关键表数据（批次、成本、毛利、库存、出入库明细）
	 * @param @param orderDirection
	 * @param @param orderModel
	 * @param @throws Exception 异常信息
	 * @return void 返回类型
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void generateKeyTableDatas(OrderDirection orderDirection, OrderModel orderModel) throws Exception{
		OrderRule orderRule = orderModel.getOrderRule();
		OrderVO order = orderModel.getOrder();
		List<OrderDtlVO> orderDtlList = orderModel.getOrderDtlList();
		// 设置业务发生前库存数量
		setStorageQuantityToOrderDtl(orderDtlList);
		switch(orderDirection){
			case IN:
				generateIn(orderRule, order, orderDtlList);
				break;
			case OUT:
				generateOut(orderRule, order, orderDtlList);
				break;
			// 货位移动、中药装斗
			case MOVE:
			case LOAD:
				Map<String, List<OrderDtlVO>> orderDtlListMap = getInAndOutOrderDtlList(orderDtlList);
				List<OrderDtlVO> inOrderDtlList = orderDtlListMap.get("inOrderDtlList");
				List<OrderDtlVO> outOrderDtlList = orderDtlListMap.get("outOrderDtlList");
				// 更新库存信息
				updateStockWhenOut(order, outOrderDtlList);
				generateOrUpdateStockWhenIn(inOrderDtlList);

				FillPriceAndAmountInfo fillPriceAndAmountInfo = new FillPriceAndAmountInfo(order, orderDtlList).invoke();
				BigDecimal costAmountTotal = fillPriceAndAmountInfo.getCostAmountTotal();
				BigDecimal notaxCostAmountTotal = fillPriceAndAmountInfo.getNotaxCostAmountTotal();

				// 生成出入库明细
				generateInOutRecord(order, orderDtlList, costAmountTotal, notaxCostAmountTotal);
				break;
			// 盘点
			case INVENTORY:
				Map<String, List<OrderDtlVO>> orderDtlListInventoryMap = getInAndOutOrderDtlList(orderDtlList);
				List<OrderDtlVO> inOrderDtlInventoryList = orderDtlListInventoryMap.get("inOrderDtlList");
				List<OrderDtlVO> outOrderDtlInventoryList = orderDtlListInventoryMap.get("outOrderDtlList");
				// 先出后入
				if(CollectionUtils.isNotEmpty(outOrderDtlInventoryList)){
					// 重置方向，重算总单的金额合计、实际金额合计、不含税实际金额合计、税额合计
					order.setDirection(OrderDirection.OUT.getDirection());
					reCalculateAmountTotalInfo(order, outOrderDtlInventoryList);
					generateOut(orderRule, order, outOrderDtlInventoryList);
				}
				if(CollectionUtils.isNotEmpty(inOrderDtlInventoryList)){
					// 重置方向，重算总单的金额合计、实际金额合计、不含税实际金额合计、税额合计
					order.setDirection(OrderDirection.IN.getDirection());
					reCalculateAmountTotalInfo(order, inOrderDtlInventoryList);
					generateIn(orderRule, order, inOrderDtlInventoryList);
				}
				break;
			default:
				throw new BusinessException("未知业务类型");
		}

		// 更新商品管理表状态
		updateGoodsManageStatus(order, orderDtlList);

	}

	/**
	 * 更新商品管理表状态
	 * @param order
	 * @param orderDtlList
	 */
	private void updateGoodsManageStatus(OrderVO order, List<OrderDtlVO> orderDtlList) {
		Set<Long> goodsIdSet = new HashSet<>();
		for(OrderDtlVO orderDtlVO:orderDtlList){
			goodsIdSet.add(orderDtlVO.getGoodsId());
		}
		for(Long goodsId:goodsIdSet){
			Long enterpriseId = order.getEnterpriseId();
			Long parentId = order.getParentId();
			BigDecimal stockQuantityTotal = stockMapper.getQuantityTotalByGoodsId(goodsId, enterpriseId);
			if(stockQuantityTotal != null){
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("enterpriseId", enterpriseId);
				paramMap.put("parentId", parentId);
				paramMap.put("goodsId", goodsId);
				List<GoodsManage> goodsManageList = goodsManageMapper.selectByParamMap(paramMap);
				if(stockQuantityTotal.compareTo(BigDecimal.ZERO)>0){
					for(GoodsManage goodsManage:goodsManageList){
						goodsManage.setStatus(GoodsManageStatus.ON_SALE.getCode());
						goodsManageMapper.updateByPrimaryKeySelective(goodsManage);
					}
				}else if(stockQuantityTotal.compareTo(BigDecimal.ZERO)==0){
					for(GoodsManage goodsManage:goodsManageList){
						goodsManage.setStatus(GoodsManageStatus.SOLD_OUT.getCode());
						goodsManageMapper.updateByPrimaryKeySelective(goodsManage);
					}
				}
			}
		}
	}

	/**
	 * 重算总单的金额合计、实际金额合计、不含税实际金额合计、税额合计
	 * @param order
	 * @param orderDtlList
	 */
	private void reCalculateAmountTotalInfo(OrderVO order, List<OrderDtlVO> orderDtlList) {
		BigDecimal amountTotal = BigDecimal.ZERO;
		BigDecimal realAmountTotal = BigDecimal.ZERO;
		BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
		BigDecimal taxAmountTotal = BigDecimal.ZERO;
		for(OrderDtlVO orderDtl:orderDtlList){
            amountTotal = amountTotal.add(orderDtl.getAmount());
            realAmountTotal = realAmountTotal.add(orderDtl.getRealAmount());
            notaxRealAmountTotal = notaxRealAmountTotal.add(orderDtl.getNotaxRealAmount());
            taxAmountTotal = taxAmountTotal.add(orderDtl.getTaxAmount());
        }
		order.setAmountTotal(amountTotal.abs());
		order.setRealAmountTotal(realAmountTotal.abs());
		order.setNotaxRealAmountTotal(notaxRealAmountTotal.abs());
		order.setTaxAmountTotal(taxAmountTotal.abs());
	}

	/**
	 * @Title: getInAndOutOrderDtlList  
	 * @Description: 双向业务单据明细拆分成入库单据明细和出库单据明细  
	 * @param @param orderDtlList
	 * @param @return 
	 * @return Map<String,List<OrderDtlVO>> 返回类型  
	 * @throws
	 */
	private Map<String, List<OrderDtlVO>> getInAndOutOrderDtlList(List<OrderDtlVO> orderDtlList){
		List<OrderDtlVO> inOrderDtlList = new ArrayList<OrderDtlVO>();
		List<OrderDtlVO> outOrderDtlList = new ArrayList<OrderDtlVO>();
		for(OrderDtlVO orderDtl:orderDtlList){
			Integer direction = orderDtl.getDirection();
			if(direction.equals(OrderDirection.IN.getDirection())){
				inOrderDtlList.add(orderDtl);
			}else{
				outOrderDtlList.add(orderDtl);
			}
		}
		Map<String, List<OrderDtlVO>> map = new HashMap<String, List<OrderDtlVO>>();
		map.put("inOrderDtlList", inOrderDtlList);
		map.put("outOrderDtlList", outOrderDtlList);
		return map;
	}

	/**
	 * 
	 * @Title: generateIn  
	 * @Description: 普通入库生成关键表数据
	 * @param @param orderRule 单据规则 * @param @param order 入库单据
	 * @param @param orderDtlList 入库单据明细
	 * @param @throws Exception 异常信息  
	 * @return void 返回类型  
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void generateIn(OrderRule orderRule, OrderVO order, List<OrderDtlVO> orderDtlList) throws Exception {
		// 1、生成批号
		generateLotNumWhenIn(order, orderDtlList);
		// 2、生成内部批次
		generateBatchNumberWhenIn(orderRule, order, orderDtlList);
		// 3、生成成本
		generateCostWhenIn(order, orderDtlList);
		// 4、生成库存
		generateOrUpdateStockWhenIn(orderDtlList);
		// 5、生成入库明细：这里成本金额=单据实际金额合计，不含税成本金额=单据不含税税实际金额合计
		generateInOutRecord(order, orderDtlList, order.getRealAmountTotal(), order.getNotaxRealAmountTotal());
	}

	/**
	 * 根据明细计算总单成本金额合计和不含税成本金额合计
	 * @param orderDtlList
	 * @return
	 */
	public Map<String, BigDecimal>  getInOutTotalInfo(List<OrderDtlVO> orderDtlList){
		Map<String, BigDecimal> retMap = new HashMap<String, BigDecimal>();
		BigDecimal costAmountTotal = BigDecimal.ZERO;
		BigDecimal notaxCostAmountTotal = BigDecimal.ZERO;
		for(OrderDtlVO orderDtl:orderDtlList){
			// 成本金额
			BigDecimal costAmount = orderDtl.getCostAmount()==null?BigDecimal.ZERO:orderDtl.getCostAmount();
			// 不含税成本金额
			BigDecimal notaxCostAmount = orderDtl.getNotaxCostAmount()==null?BigDecimal.ZERO:orderDtl.getNotaxCostAmount();

			costAmountTotal = costAmountTotal.add(costAmount);
			notaxCostAmountTotal = notaxCostAmountTotal.add(notaxCostAmount);
		}
		retMap.put("costAmountTotal", costAmountTotal);
		retMap.put("notaxCostAmountTotal", notaxCostAmountTotal);
		return retMap;
	}

	/**
	 * 
	 * @Title: generateLotNumWhenIn  
	 * @Description: 生成批号
	 * @param @param order 入库单据
	 * @param @param orderDtlList 入库单据明细
	 * @param @throws Exception 异常信息  
	 * @return void 返回类型  
	 * @throws
	 */
	private void generateLotNumWhenIn(OrderVO order, List<OrderDtlVO> orderDtlList) {
		for(OrderDtlVO orderDtl : orderDtlList){
			// 根据企业、商品、批号检验批号信息是否存在
			LotNumber lotNumInfo = lotNumberMapper.getLotNumInfo(orderDtl.getEnterpriseId(), orderDtl.getGoodsId(), orderDtl.getLotNum());
			if(lotNumInfo == null || lotNumInfo.getId() == null){
				LotNumber lotNumber = new LotNumber();
				lotNumber.setEnterpriseId(orderDtl.getEnterpriseId());
				lotNumber.setParentId(orderDtl.getParentId());
				lotNumber.setGoodsId(orderDtl.getGoodsId());
				lotNumber.setGoodsCode(orderDtl.getGoodsCode());
				lotNumber.setGoodsName(orderDtl.getGoodsName());
				lotNumber.setLotNum(orderDtl.getLotNum());
				lotNumber.setProductDate(orderDtl.getProductDate());
				lotNumber.setValidUntil(orderDtl.getValidUntil());
				lotNumber.setRemark(orderDtl.getRemark());
				lotNumber.setCreaterId(orderDtl.getCreaterId());
				lotNumber.setCreaterCode(orderDtl.getCreaterCode());
				lotNumber.setCreaterName(orderDtl.getCreaterName());
				lotNumber.setCreateTime(new Date());
				lotNumber.setModifierId(order.getModifierId());
				lotNumber.setModifierCode(order.getModifierCode());
				lotNumber.setModifierName(order.getModifierName());
				lotNumber.setUpdateTime(new Date());
				lotNumberMapper.insertSelective(lotNumber);
				orderDtl.setLotId(lotNumber.getId());
			}else{
				Date productDate = orderDtl.getProductDate()==null?lotNumInfo.getProductDate():orderDtl.getProductDate();
				Date validUntil = orderDtl.getValidUntil()==null?lotNumInfo.getValidUntil():orderDtl.getValidUntil();
				lotNumInfo.setProductDate(productDate);
				lotNumInfo.setValidUntil(validUntil);
				lotNumberMapper.updateByPrimaryKeySelective(lotNumInfo);
				orderDtl.setLotId(lotNumInfo.getId());
			}
		}
	}
	
	/**
	 * @Title: generateInOutRecord  
	 * @Description: 生成出入库明细  
	 * @param @param order 单据
	 * @param @param orderDtlList 单据明细  
	 * @return void 返回类型  
	 * @throws
	 */
	private void generateInOutRecord(OrderVO order, List<OrderDtlVO> orderDtlList, BigDecimal costAmountTotal, BigDecimal notaxCostAmountTotal) {
		InOutRecord inOutRecord = new InOutRecord();
		inOutRecord.setEnterpriseId(order.getEnterpriseId());
		inOutRecord.setParentId(order.getParentId());
		inOutRecord.setDirection(order.getDirection());
		inOutRecord.setOrderId(order.getOrderId());
		inOutRecord.setOrderCode(order.getOrderCode());
		inOutRecord.setOrderType(order.getOrderType());
		inOutRecord.setOrderDate(order.getOrderDate());
		inOutRecord.setCompanyId(order.getCompanyId());
		inOutRecord.setCompanyCode(order.getCompanyCode());
		inOutRecord.setCompanyName(order.getCompanyName());
		// 数量合计
		inOutRecord.setQuantityTotal(order.getQuantityTotal());
		// 品种合计
		inOutRecord.setVarietiesQuantity(order.getVarietiesQuantity());
		// 金额合计
		inOutRecord.setAmountTotal(order.getAmountTotal());
		// 整单折扣(%)
		inOutRecord.setWholeDiscount(order.getWholeDiscount());
		// 整单优惠金额
		inOutRecord.setWholeDiscountAmount(order.getWholeDiscountAmount());
		BigDecimal realAmountTotal = order.getRealAmountTotal()==null?BigDecimal.ZERO:order.getRealAmountTotal();
		// 实际金额合计
		inOutRecord.setRealAmountTotal(realAmountTotal);
		BigDecimal notaxRealAmountTotal = order.getNotaxRealAmountTotal();
		// 不含税实际金额合计
		inOutRecord.setNotaxRealAmountTotal(notaxRealAmountTotal);
		// 税额合计
		inOutRecord.setTaxAmountTotal(order.getTaxAmountTotal());
		// 成本金额合计
		inOutRecord.setCostAmountTotal(costAmountTotal);
		// 不含税成本金额合计
		inOutRecord.setNotaxCostAmountTotal(notaxCostAmountTotal);

		// 计算利润金额和利润率
		// 利润合计
		BigDecimal profitTotal = realAmountTotal.subtract(costAmountTotal).setScale(2, RoundingMode.HALF_UP);
		// 不含税利润合计
		BigDecimal notaxProfitTotal = notaxRealAmountTotal.subtract(notaxCostAmountTotal).setScale(2, RoundingMode.HALF_UP);
		inOutRecord.setProfitTotal(profitTotal);
		inOutRecord.setNotaxProfitTotal(notaxProfitTotal);
		if(realAmountTotal.compareTo(BigDecimal.ZERO)==0){
			inOutRecord.setProfitRate(BigDecimal.ZERO);
		}else{
			// 利润率
			inOutRecord.setProfitRate(profitTotal.divide(realAmountTotal, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
		}
		if(notaxRealAmountTotal.compareTo(BigDecimal.ZERO)==0){
			inOutRecord.setNotaxProfitRate(BigDecimal.ZERO);
		}else{
			// 不含税利润率
			inOutRecord.setNotaxProfitRate(notaxProfitTotal.divide(notaxRealAmountTotal, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
		}

		inOutRecord.setRemark(order.getRemark());
		inOutRecord.setCreaterId(order.getCreaterId());
		inOutRecord.setCreaterCode(order.getCreaterCode());
		inOutRecord.setCreaterName(order.getCreaterName());
		inOutRecord.setCreateTime(new Date());
		inOutRecord.setModifierId(order.getModifierId());
		inOutRecord.setModifierCode(order.getModifierCode());
		inOutRecord.setModifierName(order.getModifierName());
		inOutRecord.setUpdateTime(new Date());
		inOutRecordMapper.insertSelective(inOutRecord);

		for(OrderDtlVO orderDtl:orderDtlList){
			InOutRecordDetail inOutRecordDtl = new InOutRecordDetail();
			inOutRecordDtl.setInOutId(inOutRecord.getId());
			inOutRecordDtl.setEnterpriseId(inOutRecord.getEnterpriseId());
			inOutRecordDtl.setParentId(inOutRecord.getParentId());
			inOutRecordDtl.setDirection(orderDtl.getDirection());
			inOutRecordDtl.setOrderId(inOutRecord.getOrderId());
			inOutRecordDtl.setOrderCode(inOutRecord.getOrderCode());
			inOutRecordDtl.setOrderType(inOutRecord.getOrderType());
			inOutRecordDtl.setOrderDate(inOutRecord.getOrderDate());
			inOutRecordDtl.setOrderDtlId(orderDtl.getOrderDtlId());
			inOutRecordDtl.setLineNum(orderDtl.getLineNum());
			inOutRecordDtl.setCompanyId(orderDtl.getCompanyId());
			inOutRecordDtl.setCompanyCode(orderDtl.getCompanyCode());
			inOutRecordDtl.setCompanyName(orderDtl.getCompanyName());
			inOutRecordDtl.setGoodsId(orderDtl.getGoodsId());
			inOutRecordDtl.setGoodsCode(orderDtl.getGoodsCode());
			inOutRecordDtl.setGoodsName(orderDtl.getGoodsName());
			inOutRecordDtl.setLotId(orderDtl.getLotId());
			inOutRecordDtl.setLotNum(orderDtl.getLotNum());
			inOutRecordDtl.setShelfId(orderDtl.getShelfId());
			inOutRecordDtl.setShelfName(orderDtl.getShelfName());

			// 业务发生前的库存数量
			inOutRecordDtl.setStorageQuantity(orderDtl.getStorageQuantity()==null? BigDecimal.ZERO:orderDtl.getStorageQuantity());
			inOutRecordDtl.setQuantity(orderDtl.getQuantity());
			// 单价
			inOutRecordDtl.setUnitPrice(orderDtl.getUnitPrice());
			// 行折扣（％）
			inOutRecordDtl.setLineDiscount(orderDtl.getLineDiscount());
			// 金额（整单优惠前金额=数量*单价*行折扣）
			inOutRecordDtl.setAmount(orderDtl.getRealAmount());
			// 整单折扣（％）
			inOutRecordDtl.setWholeDiscount(orderDtl.getWholeDiscount());
			// 行优惠（整单优惠分摊到行的金额）
			inOutRecordDtl.setLineDiscountAmount(orderDtl.getLineDiscountAmount());
			// 实际单价（实际金额/数量）
			inOutRecordDtl.setRealPrice(orderDtl.getRealPrice());
			// 实际金额（实际单价*数量）
			inOutRecordDtl.setRealAmount(orderDtl.getRealAmount());
			// 税率
			inOutRecordDtl.setTaxRate(orderDtl.getTaxRate()==null?BigDecimal.ZERO:orderDtl.getTaxRate());
			// 不含税单价
			inOutRecordDtl.setNotaxRealPrice(orderDtl.getNotaxRealPrice());
			// 不含税金额
			inOutRecordDtl.setNotaxRealAmount(orderDtl.getNotaxRealAmount());
			// 税额
			inOutRecordDtl.setTaxAmount(orderDtl.getTaxAmount());
			// 成本金额
			inOutRecordDtl.setCostAmount(orderDtl.getCostAmount()==null?BigDecimal.ZERO:orderDtl.getCostAmount());
			// 不含税成本金额
			inOutRecordDtl.setNotaxCostAmount(orderDtl.getNotaxCostAmount()==null?BigDecimal.ZERO:orderDtl.getNotaxCostAmount());
			// 利润金额
			inOutRecordDtl.setProfit(orderDtl.getProfit());
			// 不含税利润金额
			inOutRecordDtl.setNotaxProfit(orderDtl.getNotaxProfit());
			// 利润率
			inOutRecordDtl.setProfitRate(orderDtl.getProfit());
			// 不含税利润率
			inOutRecordDtl.setNotaxProfitRate(orderDtl.getNotaxProfitRate());
			inOutRecordDtl.setRemark(orderDtl.getRemark());
			inOutRecordDtl.setCreaterId(orderDtl.getCreaterId());
			inOutRecordDtl.setCreaterCode(orderDtl.getCreaterCode());
			inOutRecordDtl.setCreaterName(orderDtl.getCreaterName());
			inOutRecordDtl.setCreateTime(new Date());
			inOutRecordDtl.setModifierId(orderDtl.getModifierId());
			inOutRecordDtl.setModifierCode(orderDtl.getModifierCode());
			inOutRecordDtl.setModifierName(orderDtl.getModifierName());
			inOutRecordDtl.setUpdateTime(new Date());
			inOutRecordDetailMapper.insertSelective(inOutRecordDtl);
		}
	}

	/**
	 * 设置业务发生前库存数量
	 * @param orderDtlList
	 */
	private void setStorageQuantityToOrderDtl(List<OrderDtlVO> orderDtlList) {
		Map<String,BigDecimal> storageQuantityMap = new HashMap<>();
		if(CollectionUtils.isNotEmpty(orderDtlList)){
			for(OrderDtlVO orderDtl:orderDtlList){
				Long enterpriseId = orderDtl.getEnterpriseId();
				Long parentId = orderDtl.getParentId();
				Long goodsId = orderDtl.getGoodsId();
				String key = enterpriseId + "" + parentId + "" + goodsId;
				BigDecimal storageQuantity = storageQuantityMap.get(key);
				if(storageQuantity == null){
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("enterpriseId", enterpriseId);
					paramMap.put("parentId", parentId);
					paramMap.put("goodsId", goodsId);
					BigDecimal storageQuantityTmp = stockMapper.getStorageQuantityByParamMap(paramMap);
					storageQuantity = storageQuantityTmp == null ? BigDecimal.ZERO : storageQuantityTmp;//商品库存数量
				}
				BigDecimal quantity = orderDtl.getQuantity();//业务数量
				Integer direction = orderDtl.getDirection();
				if(direction.equals(OrderDirection.IN.getDirection())){
					//入库
					orderDtl.setStorageQuantity(storageQuantity);
					storageQuantity = storageQuantity.add(quantity);
				} else {
					//出库
					orderDtl.setStorageQuantity(storageQuantity);
					storageQuantity = storageQuantity.subtract(quantity);
				}
				storageQuantityMap.put(key,storageQuantity);

			}
		}
	}

	/**
	 * 
	 * @Title: generateOrUpdateStockWhenIn  
	 * @Description: 入库生成或更新库存
	 * @param @param orderDtlList 入库单据明细
	 * @return void 返回类型  
	 * @throws
	 */
	private void generateOrUpdateStockWhenIn(List<OrderDtlVO> orderDtlList) {
		Stock stock = null;
		for(OrderDtlVO orderDtl:orderDtlList){
			Long enterpriseId = orderDtl.getEnterpriseId();
			Long parentId = orderDtl.getParentId();
			Long goodsId = orderDtl.getGoodsId();
			Long lotId = orderDtl.getLotId();
			Long shelfId = orderDtl.getShelfId();
			BigDecimal quantity = orderDtl.getQuantity();
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("enterpriseId", enterpriseId);
			paramMap.put("parentId", parentId);
			paramMap.put("goodsId", goodsId);
			paramMap.put("lotId", lotId);
			paramMap.put("shelfId",shelfId);
			List<Stock> stockList = stockMapper.selectByParamMap(paramMap);
			if(CollectionUtils.isNotEmpty(stockList)){
				stock = stockList.get(0);
				stock.setUsableQuantity(stock.getUsableQuantity().add(quantity).setScale(6, RoundingMode.HALF_UP));
				stock.setQuantity(stock.getQuantity().add(quantity).setScale(6, RoundingMode.HALF_UP));
				stockMapper.updateByPrimaryKey(stock);
			}else{
				stock = new Stock();
				stock.setEnterpriseId(orderDtl.getEnterpriseId());
				stock.setParentId(orderDtl.getParentId());
				stock.setGoodsId(orderDtl.getGoodsId());
				stock.setGoodsCode(orderDtl.getGoodsCode());
				stock.setGoodsName(orderDtl.getGoodsName());
				stock.setLotId(orderDtl.getLotId());
				stock.setLotNum(orderDtl.getLotNum());
				// 库存数量
				stock.setQuantity(orderDtl.getQuantity());
				// 锁定数量
				stock.setLockQuantity(BigDecimal.ZERO);
				// 可用数量
				stock.setUsableQuantity(orderDtl.getQuantity());
				stock.setShelfId(shelfId);
				stock.setShelfName(orderDtl.getShelfName());
				// 获取货位质量状态
				String shelfStatusDesc = getShelfStatusDesc(shelfId);
				stock.setShelfStatusDesc(shelfStatusDesc);
				// 盘点状态
				stock.setInventoryStatus(0);
				// 最近养护时间
				stock.setLastMaintainTime(new Date());
				stock.setCreaterId(orderDtl.getCreaterId());
				stock.setCreaterCode(orderDtl.getCreaterCode());
				stock.setCreaterName(orderDtl.getCreaterName());
				stock.setCreateTime(new Date());
				stock.setModifierId(orderDtl.getModifierId());
				stock.setModifierCode(orderDtl.getModifierCode());
				stock.setModifierName(orderDtl.getModifierName());
				stock.setUpdateTime(new Date());
				stockMapper.insertSelective(stock);
			}
		}
	}
	
	/**
	 * 根据货位ID获取货区的质量状态
	 * @param shelfId
	 * @return
	 */
	public String getShelfStatusDesc(Long shelfId){
		String shelfStatusDesc = "";
		CargoQualityStateVO cargoQulityState = warehouseCargoAreaMapper.getCargoByShelfId(shelfId);
		Integer jobType = cargoQulityState.getJobType();
		Integer jobArea = cargoQulityState.getJobArea();
		if(0==jobType && 0==jobArea){
			shelfStatusDesc = "合格";
		}else if(0==jobType && 2==jobArea){
			shelfStatusDesc = "不合格";
		}else{
			shelfStatusDesc = "其它";
		}
		return shelfStatusDesc;
	}
	

	/**
	 * @Title: generateCostWhenIn  
	 * @Description: 入库生成成本
	 * @param @param batchNumList 内部批次列表
	 * @param @return 
	 * @return List<Cost> 返回类型  
	 * @throws
	 */
	private void generateCostWhenIn(OrderVO order, List<OrderDtlVO> orderDtlList) {
		for(OrderDtlVO orderDtl : orderDtlList){
			List<BatchNumber> batchNumberList = orderDtl.getBatchNumberList();
			for(BatchNumber batchNum:batchNumberList){
				Cost cost = new Cost();
				cost.setEnterpriseId(batchNum.getEnterpriseId());
				cost.setParentId(batchNum.getParentId());
				cost.setOrderId(batchNum.getOrderId());
				cost.setOrderCode(batchNum.getOrderCode());
				cost.setOrderType(batchNum.getOrderType());
				cost.setOrderDate(batchNum.getOrderDate());
				cost.setOrderDtlId(batchNum.getOrderDtlId());
				cost.setLineNum(batchNum.getLineNum());
				cost.setGoodsId(batchNum.getGoodsId());
				cost.setGoodsCode(batchNum.getGoodsCode());
				cost.setGoodsName(batchNum.getGoodsName());
				cost.setLotId(batchNum.getLotId());
				cost.setLotNum(batchNum.getLotNum());
				cost.setBatchId(batchNum.getId());
				cost.setBatchNum(batchNum.getBatchNum());
				cost.setSrcUnitId(orderDtl.getCompanyId());
				cost.setSrcUnitCode(orderDtl.getCompanyCode());
				cost.setSrcUnitName(orderDtl.getCompanyName());
				// 库存数量取批次入库数量
				BigDecimal quantity = batchNum.getQuantity();
				cost.setQuantity(quantity);
				// 可用数量
				cost.setUsableQuantity(quantity);
				// 实际单价
				BigDecimal realPrice = batchNum.getRealPrice();
				// 税率
				cost.setTaxRate(batchNum.getTaxRate());
				// 不含税实际单价
				BigDecimal notaxRealPrice = batchNum.getNotaxRealPrice();
				// 实际金额
				BigDecimal realAmount = BigDecimal.ZERO;
				BigDecimal notaxRealAmount = BigDecimal.ZERO;
				BigDecimal taxAmount = BigDecimal.ZERO;

				if(order.getOrderType().equals(OrderRule.DISTR_RETURN_IN.getType()) || order.getOrderType().equals(OrderRule.DISTR_IN.getType())){
					realAmount = realPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
					notaxRealAmount = notaxRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
					taxAmount = realAmount.subtract(notaxRealAmount);
				}else {
					realAmount = orderDtl.getRealAmount();
					notaxRealAmount = orderDtl.getNotaxRealAmount();
					taxAmount = orderDtl.getTaxAmount();
				}

				cost.setRealPrice(realPrice);
				cost.setNotaxRealPrice(notaxRealPrice);
				cost.setRealAmount(realAmount);
				// 可用实际金额
				cost.setUsableRealAmout(realAmount);
				// 不含税实际金额
				cost.setNotaxRealAmount(notaxRealAmount);
				// 不含税可用实际金额
				cost.setNotaxUsableRealAmout(notaxRealAmount);
				// 税额
				cost.setTaxAmount(taxAmount);
				// 可用税额
				cost.setUsableTaxAmount(taxAmount);
				cost.setRemark(batchNum.getRemark());
				cost.setCreaterId(batchNum.getCreaterId());
				cost.setCreaterCode(batchNum.getCreaterCode());
				cost.setCreaterName(batchNum.getCreaterName());
				cost.setCreateTime(new Date());
				cost.setModifierId(batchNum.getModifierId());
				cost.setModifierCode(batchNum.getModifierCode());
				cost.setModifierName(batchNum.getModifierName());
				cost.setUpdateTime(new Date());
				costMapper.insertSelective(cost);

				// 配后退回入库或者销售退回入库业务，生成追溯表数据
				generateProfitWhenDistrReturnInOrSaleReturnIn(order, orderDtl, batchNum, quantity);

			}

		}
	}

	/**
	 *
	 * 总部配后退回入库和销售退回入库生成追溯表数据
	 *
	 * @param order
	 * @param orderDtl
	 * @param batchNum
	 * @param quantity
	 */
	private void generateProfitWhenDistrReturnInOrSaleReturnIn(OrderVO order, OrderDtlVO orderDtl, BatchNumber batchNum, BigDecimal quantity) {
		if(order.getOrderType().equals(OrderRule.DISTR_RETURN_IN.getType()) ||
                order.getOrderType().equals(OrderRule.SALES_RETURN_IN.getType())){
            Profit profit = new Profit();
            profit.setEnterpriseId(order.getEnterpriseId());
            profit.setParentId(order.getParentId());
            profit.setOutOrderDtlId(orderDtl.getOrderDtlId());
            profit.setOutOrderId(order.getOrderId());
            profit.setOutOrderCode(order.getOrderCode());
            profit.setOutOrderType(order.getOrderType());
            profit.setOutOrderDate(order.getOrderDate());
            profit.setOutOrderDtlId(orderDtl.getOrderDtlId());
            profit.setOutLineNum(orderDtl.getLineNum());
            profit.setInOrderDtlId(batchNum.getOrderDtlId());
            profit.setInOrderId(batchNum.getOrderId());
            profit.setInOrderCode(batchNum.getOrderCode());
            profit.setInOrderType(batchNum.getOrderType());
            profit.setInOrderDate(batchNum.getOrderDate());
            profit.setInLineNum(batchNum.getLineNum());
            profit.setGoodsId(orderDtl.getGoodsId());
            profit.setGoodsName(orderDtl.getGoodsName());
            profit.setGoodsCode(orderDtl.getGoodsCode());
            profit.setLotId(orderDtl.getLotId());
            profit.setLotNum(orderDtl.getLotNum());
            profit.setBatchId(batchNum.getId());
            profit.setBatchNum(batchNum.getBatchNum());
            profit.setSrcUnitId(orderDtl.getCompanyId());
            profit.setSrcUnitCode(orderDtl.getCompanyCode());
            profit.setSrcUnitName(orderDtl.getCompanyCode());
            profit.setFlowUnitId(orderDtl.getCompanyId());
            profit.setFlowUnitCode(orderDtl.getCompanyCode());
            profit.setFlowUnitName(orderDtl.getCompanyName());

            // 成本出库数量
            profit.setQuantity(quantity);

            // 成本价
            BigDecimal inRealPrice = BigDecimal.ZERO;
            // 成本金额
            BigDecimal inRealAmount = BigDecimal.ZERO;
            // 进项税
            BigDecimal inTaxRate = BigDecimal.ZERO;
            // 不含税成本单价
            BigDecimal inNotaxRealPrice = BigDecimal.ZERO;
            // 不含税成本金额
            BigDecimal inNotaxRealAmount = BigDecimal.ZERO;
            // 实际出库单价
            BigDecimal outRealPrice = BigDecimal.ZERO;
            // 实际出库金额
            BigDecimal outRealAmount = BigDecimal.ZERO;
            // 实际出库税率
            BigDecimal outRealTaxRate = BigDecimal.ZERO;
            // 实际不含税出库单价
            BigDecimal outRealNotaxPrice = BigDecimal.ZERO;
            // 实际不含税出库金额
            BigDecimal outNotaxRealAmount = BigDecimal.ZERO;

            if(order.getOrderType().equals(OrderRule.DISTR_RETURN_IN.getType())){
                inRealPrice = batchNum.getRealPrice()==null?BigDecimal.ZERO:batchNum.getRealPrice();
                inTaxRate = batchNum.getTaxRate();
                inNotaxRealPrice = batchNum.getNotaxRealPrice()==null?BigDecimal.ZERO:batchNum.getNotaxRealPrice();
                Long parentId = 0L;
                Long branchId = 0L;
                if(order.getOrderType().equals(OrderRule.DISTR_RETURN_IN.getType())){
                    parentId = order.getEnterpriseId();
                    branchId = order.getCompanyId();
                }else{
                    parentId = order.getEnterpriseId();
                    branchId = order.getEnterpriseId();
                }
                EnterpriseBusiness enterpriseBusiness = commonComponent.getEnterpriseBusiness(branchId);
                if(enterpriseBusiness==null){
                    logger.error("获取分店业务子表数据错误！总单信息："+order.toString());
                    throw new BusinessException("获取分店业务子表数据错误！");
                }
                Integer distrPriceType = enterpriseBusiness.getDistrPriceType();
                if(distrPriceType.equals(DistrPriceType.COST_PRICE.getCode())){
                    outRealPrice = inRealPrice;
                    outRealTaxRate = inTaxRate;
                    outRealNotaxPrice = inNotaxRealPrice;
                }else if(distrPriceType.equals(DistrPriceType.COST_PLUS.getCode())){
                    BigDecimal addRate = enterpriseBusiness.getAddRate()==null?BigDecimal.ZERO:enterpriseBusiness.getAddRate();
                    outRealPrice = inRealPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
                    outRealTaxRate = inTaxRate;
                    outRealNotaxPrice = inNotaxRealPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
                }else if(distrPriceType.equals(DistrPriceType.PRICE_LIST.getCode())){
                    Long distrPriceOrderId = enterpriseBusiness.getDistrPriceOrderId();
                    PriceOrderDetail priceOrderDetail = commonComponent.getPriceOrderDetail(parentId, distrPriceOrderId, orderDtl.getGoodsId());
                    outRealPrice = priceOrderDetail.getDistrPrice()==null?BigDecimal.ZERO:priceOrderDetail.getDistrPrice();
                    outRealTaxRate = priceOrderDetail.getDistrTaxRate()==null?BigDecimal.ZERO:priceOrderDetail.getDistrTaxRate();
                    outRealNotaxPrice = priceOrderDetail.getNotaxDistrPrice()==null?BigDecimal.ZERO:priceOrderDetail.getNotaxDistrPrice();
                }
            }else{
                // 销售退回入库
                inRealPrice = orderDtl.getRealPrice()==null?BigDecimal.ZERO:orderDtl.getRealPrice();
                inTaxRate = orderDtl.getTaxRate();
                inNotaxRealPrice = orderDtl.getNotaxRealPrice()==null?BigDecimal.ZERO:orderDtl.getNotaxRealPrice();

                outRealPrice = batchNum.getRealPrice()==null?BigDecimal.ZERO:batchNum.getRealPrice();
                outRealTaxRate = batchNum.getTaxRate();
                outRealNotaxPrice = batchNum.getNotaxRealPrice()==null?BigDecimal.ZERO:batchNum.getNotaxRealPrice();
            }
            inRealAmount = inRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
            inNotaxRealAmount = inNotaxRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
            outRealAmount = outRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
            outNotaxRealAmount = outRealNotaxPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);

            // 成本价
            profit.setInRealPrice(inRealPrice);
            // 成本金额
            profit.setInRealAmount(inRealAmount);
            // 进项税
            profit.setPurchaseTaxRate(inTaxRate);
            // 不含税成本单价
            profit.setInNotaxRealPrice(inNotaxRealPrice);
            // 不含税成本金额
            profit.setInNotaxRealAmount(inNotaxRealAmount);
            // 成本税额
            profit.setInTaxAmount(inRealAmount.subtract(inNotaxRealAmount).setScale(2, RoundingMode.HALF_UP));
            // 实际出库单价
            profit.setOutRealPrice(outRealPrice);
            // 实际出库税率
            profit.setSaleTaxRate(outRealTaxRate);
            // 实际出库不含税单价
            profit.setOutNotaxRealPrice(outRealNotaxPrice);
            // 实际出库金额
            profit.setOutRealAmount(outRealAmount);
            // 实际不含税出库金额
            profit.setOutNotaxRealAmount(outNotaxRealAmount);
            // 实际出库税额
            profit.setOutTaxAmount(outRealAmount.subtract(outNotaxRealAmount).setScale(2, RoundingMode.HALF_UP));
            // 利润金额
            BigDecimal profitVal = outRealAmount.subtract(inRealAmount).setScale(2, RoundingMode.HALF_UP);
            // 不含税利润金额
            BigDecimal notaxProfitVal = outNotaxRealAmount.subtract(inNotaxRealAmount).setScale(2, RoundingMode.HALF_UP);
            BigDecimal profitRate = BigDecimal.ZERO;
            BigDecimal notaxProfitRate = BigDecimal.ZERO;
            if(outRealAmount.compareTo(BigDecimal.ZERO)==0){
                profitRate = BigDecimal.ZERO;
            }else{
                // 利润率
                profitRate = profitVal.divide(outRealAmount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            }
            if(outNotaxRealAmount.compareTo(BigDecimal.ZERO)==0){
                notaxProfitRate = BigDecimal.ZERO;
            }else{
                // 不含税利润率
                notaxProfitRate = notaxProfitVal.divide(outNotaxRealAmount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            }
            profit.setProfit(profitVal);
            profit.setNotaxProfit(notaxProfitVal);
            profit.setProfitRate(profitRate);
            profit.setNotaxProfitRate(notaxProfitRate);
            profit.setRemark(orderDtl.getRemark());
            profit.setCreaterId(orderDtl.getCreaterId());
            profit.setCreaterCode(orderDtl.getCreaterCode());
            profit.setCreaterName(orderDtl.getCreaterName());
            profit.setCreateTime(new Date());
            profit.setModifierId(orderDtl.getModifierId());
            profit.setModifierCode(orderDtl.getModifierCode());
            profit.setModifierName(orderDtl.getModifierName());
            profit.setUpdateTime(new Date());

            profitMapper.insertSelective(profit);
        }
	}

	/**
	 * @Title: generateBatchNumberWhenIn  
	 * @Description: 入库生成内部批次
	 * @param @param orderRule 单据规则
	 * @param @param order 入库单据
	 * @param @param orderDtlList 入库单据明细
	 * @param @return
	 * @param @throws Exception 异常信息
	 * @return @return 返回类型
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void generateBatchNumberWhenIn(OrderRule orderRule, OrderVO order, List<OrderDtlVO> orderDtlList) throws Exception {
		for(OrderDtlVO orderDtl : orderDtlList){
			List<BatchNumber> batchNumberList = new ArrayList<BatchNumber>();
			// 生成内部批次
			if(order.getOrderType().equals(OrderRule.DISTR_RETURN_IN.getType())){

				// 总部配后退回入库，获取分店配进退出库的毛利记录
//				List<Profit> profitList = profitMapper.queryProfitWhenDistrReturnIn(orderDtl.getEnterpriseId(), orderDtl.getOrderDtlId(), orderDtl.getOrderId());
				List<Profit> profitList = getProfitWhenDistrReturnIn(orderDtl);

				if(CollectionUtils.isEmpty(profitList)){

					DistrReturnInShelf dsf = distrReturnInShelfMapper.selectByPrimaryKey(orderDtl.getOrderDtlId());

					DistrReturnInDetail distrReturnInDetail = distrReturnInDetailMapper.selectByPrimaryKey(dsf.getDtlId());

					DistrReturnCheckDetail distrReturnCheckDetail = distrReturnCheckDetailMapper.selectByPrimaryKey(distrReturnInDetail.getBaseOrderDtlId());

					Long noticeDtlId = distrReturnCheckDetail.getNoticeDtlId();

					DistrReturnNoticeDetail distrReturnNoticeDetail = distrReturnNoticeDetailMapper.selectByPrimaryKey(noticeDtlId);

					DistrInReturnOutDetail distrInReturnOutDetail = distrInReturnOutDetailMapper.selectByPrimaryKey(distrReturnNoticeDetail.getBaseOrderDtlId());

					OtherInDetail otherInDetail = otherInDetailMapper.selectByEnterpriseIdAndGoodsId(
							distrInReturnOutDetail.getEnterpriseId()
							,dsf.getGoodsId()
							,dsf.getLotNumber()
					);

					if(otherInDetail.getGoodsId().equals(orderDtl.getGoodsId()) && orderDtl.getLotNum().equals(otherInDetail.getLotNumber())){
						Map<String,Object> paramMap = new HashMap<>();
						paramMap.put("enterpriseId",otherInDetail.getEnterpriseId());
						paramMap.put("orderDtlId",otherInDetail.getId());
						List<BatchNumber> branchBatchNumbers = batchNumberMapper.selectByParamMap(paramMap);

						if(CollectionUtils.isNotEmpty(branchBatchNumbers)){
							BatchNumber batchNumber = branchBatchNumbers.get(0);
							String batchNum = batchNumber.getBatchNum();
							BigDecimal realPrice = batchNumber.getRealPrice();
							BigDecimal taxRate = batchNumber.getTaxRate();
							BigDecimal notaxRealPrice = batchNumber.getNotaxRealPrice();
							BigDecimal quantity = orderDtl.getQuantity();
							List<BatchNumber> batchNumbers = getBatchNums(order, orderDtl, batchNum, realPrice, taxRate, notaxRealPrice, quantity);

							if(CollectionUtils.isNotEmpty(batchNumbers)){
								batchNumberList.addAll(batchNumbers);
							}

						}else {
							logger.error("总部配后退回入库，没有追溯到分店配进退出库的毛利记录！总单信息："+order.toString());
							throw new BusinessException("总部配后退回入库，没有追溯到分店配进退出库的毛利记录！");
						}

					}else {
						logger.error("总部配后退回入库，没有追溯到分店配进退出库的毛利记录！总单信息："+order.toString());
						throw new BusinessException("总部配后退回入库，没有追溯到分店配进退出库的毛利记录！");
					}

				}else{
					for(Profit profit:profitList){
						String batchNum = profit.getBatchNum();
						BigDecimal realPrice = profit.getOutRealPrice();
						BigDecimal taxRate = profit.getSaleTaxRate();
						BigDecimal notaxRealPrice = profit.getOutNotaxRealPrice();
						BigDecimal quantity = profit.getQuantity();

						List<BatchNumber> batchNumbers = getBatchNums(order, orderDtl, batchNum, realPrice, taxRate, notaxRealPrice, quantity);

						if(CollectionUtils.isNotEmpty(batchNumbers)){
							batchNumberList.addAll(batchNumbers);
						}
					}
				}
			}else if(order.getOrderType().equals(OrderRule.DISTR_IN.getType())){
				// 分店配进入库，获取总部配货出库的毛利记录
//				List<Profit> profitList = profitMapper.queryProfitWhenDistrIn(orderDtl.getEnterpriseId(), orderDtl.getOrderDtlId(), orderDtl.getOrderId());
				List<Profit> profitList = getProfitWhenDistrIn(orderDtl);
				if(CollectionUtils.isEmpty(profitList)){
					logger.error("分店配进入库，追溯总部配货出库的毛利表记录异常！总单信息："+order.toString());
					throw new BusinessException("分店配进入库，追溯总部配货出库的毛利表记录异常！");
				}else{
					// 分店生成与总部相同内部批次号的内部批次记录
					for(Profit profit:profitList){
						Long enterpriseId = orderDtl.getEnterpriseId();
						Long parentId = orderDtl.getParentId();
						String batchNum = profit.getBatchNum();
						BigDecimal quantity = profit.getQuantity();
						Map<String,Object> paramMap = new HashMap<String,Object>();
						paramMap.put("enterpriseId", enterpriseId);
						paramMap.put("parentId", parentId);
						paramMap.put("batchNum", batchNum);
						List<BatchNumber> branchBatchNumberList = batchNumberMapper.selectByParamMap(paramMap);

						BatchNumber batchNumber = null;
						if(CollectionUtils.isEmpty(branchBatchNumberList)){
							BigDecimal realPrice = profit.getOutRealPrice();
							BigDecimal taxRate = profit.getSaleTaxRate();
							BigDecimal notaxRealPrice = profit.getOutNotaxRealPrice();

							batchNumber = insertBatchNumber(order, orderDtl, batchNum, realPrice, taxRate, notaxRealPrice, quantity);
						}else{
							batchNumber = branchBatchNumberList.get(0);
							batchNumber.setOrderId(order.getOrderId());
							batchNumber.setOrderCode(order.getOrderCode());
							batchNumber.setOrderType(order.getOrderType());
							batchNumber.setOrderDate(order.getOrderDate());
							batchNumber.setOrderDtlId(orderDtl.getOrderDtlId());
							batchNumberMapper.updateByPrimaryKey(batchNumber);

							batchNumber.setQuantity(quantity);
						}
						batchNumberList.add(batchNumber);

					}
				}
			}else if(order.getOrderType().equals(OrderRule.SALES_RETURN_IN.getType())){
				// 分店销售退回入库，获取分店销售出的毛利记录
				List<Profit> profitList = profitMapper.queryProfitWhenSaleReturnIn(orderDtl.getEnterpriseId(), orderDtl.getOrderDtlId(), orderDtl.getOrderId());
				if(CollectionUtils.isEmpty(profitList)){
					//无票销退
//					logger.error("分店销售退回入库，追溯对应销售出库毛利表记录异常！总单信息："+order.toString());
//					throw new BusinessException("分店销售退回入库，追溯对应销售出库毛利表记录异常！");
					String batchNum = codeComponent.generateBatchNum(order.getEnterpriseId(),orderRule,orderDtl.getLineNum(),6);
					BatchNumber batchNumber = insertBatchNumber(order, orderDtl, batchNum, orderDtl.getRealPrice(), orderDtl.getTaxRate(), orderDtl.getNotaxRealPrice(), orderDtl.getQuantity());
					batchNumberList.add(batchNumber);				
				}else{
					// 分店销售退回入库等于销售出库批次价
					for(Profit profit:profitList){
						BatchNumber batchNumber = batchNumberMapper.selectByPrimaryKey(profit.getBatchId());
						batchNumber.setQuantity(profit.getQuantity());
						batchNumberList.add(batchNumber);
					}
				}
			}else {
				// 普通入库业务生成新的内部批次号
				String batchNum = codeComponent.generateBatchNum(order.getEnterpriseId(),orderRule,orderDtl.getLineNum(),6);
				BatchNumber batchNumber = insertBatchNumber(order, orderDtl, batchNum, orderDtl.getRealPrice(), orderDtl.getTaxRate(), orderDtl.getNotaxRealPrice(), orderDtl.getQuantity());
				batchNumberList.add(batchNumber);
			}
			// 入库业务，成本金额、不含税成本金额与业务单据金额、不含税金额一致，后续在出库入库明细中记录
			if(!order.getOrderType().equals(OrderRule.SALES_RETURN_IN.getType())){
				orderDtl.setCostAmount(orderDtl.getRealAmount());
				orderDtl.setNotaxCostAmount(orderDtl.getNotaxRealAmount());
			}
			orderDtl.setBatchNumberList(batchNumberList);
		}
	}

	/**
	 * 分店配进入库，追溯总部配货出库生成的毛利列表
	 * @param orderDtl
	 * @return
	 */
	private List<Profit> getProfitWhenDistrIn(OrderDtlVO orderDtl){
		logger.info("OrderDtlVO 详情 = " + orderDtl);
		Long goodsId = orderDtl.getGoodsId();
		Long enterpriseId = orderDtl.getEnterpriseId();
		Long parentId = orderDtl.getParentId();
		// 业务单据明细ID
		Long orderDtlId = orderDtl.getOrderDtlId();
		DistrInShelf distrInShelf = distrInShelfMapper.selectByPrimaryKeyAndEID(orderDtlId,enterpriseId);
		logger.info("配进入库shelf明细DistrInShelf = " + distrInShelf);
		// 配进入库品种明细ID
		Long dtlId = distrInShelf.getDtlId();
		DistrInDetail distrInDetail = distrInDetailMapper.selectByPrimaryKey(dtlId);
		logger.info("配进入库品种明细DistrInDetail = " + distrInDetail);
		Long inCheckDetailId = distrInDetail.getBaseOrderDtlId();
		Long inCheckId = distrInDetail.getBaseOrderId();
		logger.info("配进验收品种明细DistrInCheckDetail的Id = " + inCheckDetailId);
		logger.info("配进验收单DistrInCheck的Id = " + inCheckId);

		DistrInCheckDetail distrInCheckDetail = distrInCheckDetailMapper.selectByPrimaryKey(inCheckDetailId);
		logger.info("配进验收品种明细DistrInCheckDetail的详情 = " + distrInCheckDetail);
		Long noticeDtlId = distrInCheckDetail.getNoticeDtlId();
		Long noticeId = distrInCheckDetail.getNoticeId();
		logger.info("配进订单品种明细DistrInNoticeDetail的Id = " + noticeDtlId);
		logger.info("配进验收单DistrInNotcie的Id = " + noticeId);
		DistrInNoticeDetail distrInNoticeDetail = distrInNoticeDetailMapper.selectByPrimaryKey(noticeDtlId);
		logger.info("配进订单品种明细DistrInNoticeDetail的详情 = " + distrInNoticeDetail);
		Long distrOutId = distrInNoticeDetail.getBaseOrderId();
		logger.info("配货出库单DistrOut的Id = " + distrOutId);
		DistrOut distrOut = distrOutMapper.selectByPrimaryKey(distrOutId);
		logger.info("配货出库DistrOut的详情 = " + distrOut);
		List<DistrOutDetail> distrOutDetailList = distrOutDetailMapper.getDistrOutDetailList(distrOutId);
		logger.info("配货出库品种明细DistrOutDetail的详情 = " + distrOutDetailList);
		List<DistrOutShelf> distrOutShelfList = distrOutShelfMapper.getDistrOutShelfListByOutId(distrOutId, parentId);
		logger.info("配货出库货位明细DistrOutShelf的详情 = " + distrOutShelfList);
		List<Profit> profitList = new ArrayList<>();
		for(DistrOutShelf distrOutShelf : distrOutShelfList){
			if(distrOutShelf.getGoodsId().equals(goodsId) && distrOutShelf.getLotNumber().equals(orderDtl.getLotNum())){
				Map<String,Object> map = new HashMap<>();
				map.put("enterpriseId",parentId);
				map.put("outOrderDtlId",distrOutShelf.getId());
				map.put("outOrderId",distrOutShelf.getOutId());
				map.put("goodsId",goodsId);
				map.put("orderType",OrderRule.DISTR_OUT.getType());
				List<Profit> profits = profitMapper.selectByOutParamMap(map);
				logger.info("根据条件总部配货出库货位明细ID=" + distrOutShelf.getId() + "，配货出库ID=" + distrOutShelf.getOutId() +"追溯到的总部配货出库毛利列表 = " + profits);
				profitList.addAll(profits);
			}

		}
		logger.info("分店配进入库，追溯总部配货出库生成的毛利列表为"+profitList);
		return profitList;
	}

	/**
	 * 总部配后退回入库，追溯分店配进退出出库生成的毛利列表
	 * @param orderDtl
	 * @return
	 */
	private List<Profit> getProfitWhenDistrReturnIn(OrderDtlVO orderDtl) {
		List<Profit> profitList = new ArrayList<>();
		Long distrReturnInShelfId = orderDtl.getOrderDtlId();
		DistrReturnInShelf distrReturnInShelf = distrReturnInShelfMapper.selectByPrimaryKey(distrReturnInShelfId);
		logger.info("总部配后退回入库shelf明细DistrReturnInShelf = " + distrReturnInShelf);
		Long distrReturnInDtlId = distrReturnInShelf.getDtlId();
		DistrReturnInDetail distrReturnInDetail = distrReturnInDetailMapper.selectByPrimaryKey(distrReturnInDtlId);
		logger.info("总部配后退回入库detail明细DistrReturnInDetail = " + distrReturnInDetail);
		Long distrReturnNoticeDtlId = distrReturnInDetail.getDistrOrderDtlId();
		DistrReturnNoticeDetail distrReturnNoticeDetail = distrReturnNoticeDetailMapper.selectByPrimaryKey(distrReturnNoticeDtlId);
		logger.info("总部配后退回入库通知单detail明细DistrReturnNoticeDetail = " + distrReturnNoticeDetail);
		Long distrInReturnOutDtlId = distrReturnNoticeDetail.getBaseOrderDtlId();
		DistrInReturnOutDetail distrInReturnOutDetail = distrInReturnOutDetailMapper.selectByPrimaryKey(distrInReturnOutDtlId);
		logger.info("分店配进退出出库detail明细DistrInReturnOutDetail = " + distrInReturnOutDetail);
		List<DistrInReturnOutShelf> distrInReturnOutShelfList = distrInReturnOutShelfMapper.selectByDtlIdAndEnterpriseId(distrInReturnOutDtlId,distrInReturnOutDetail.getEnterpriseId(),distrInReturnOutDetail.getParentId());
		for(DistrInReturnOutShelf distrInReturnOutShelf:distrInReturnOutShelfList){
			if(distrInReturnOutShelf.getGoodsId().equals(orderDtl.getGoodsId()) && distrInReturnOutShelf.getLotNumber().equals(orderDtl.getLotNum())){
				Map<String,Object> map = new HashMap<>();
				map.put("enterpriseId",distrInReturnOutShelf.getEnterpriseId());
				map.put("outOrderDtlId",distrInReturnOutShelf.getId());
				map.put("outOrderId",distrInReturnOutShelf.getOutId());
				map.put("goodsId",distrInReturnOutShelf.getGoodsId());
				map.put("orderType",OrderRule.DISTR_IN_RETURN_OUT.getType());
				List<Profit> profits = profitMapper.selectByOutParamMap(map);
				logger.info("根据条件分店配进退出出库货位明细ID=" + distrInReturnOutShelf.getId() + "分店配进退出出库ID=" + distrInReturnOutShelf.getOutId() +"追溯到的分店配进退出出库毛利列表 = " + profits);
				profitList.addAll(profits);
			}

		}
		logger.info("总部配后退回入库，追溯分店配进退出出库生成的毛利列表为："+profitList);
		return profitList;
	}

	private List<BatchNumber> getBatchNums(OrderVO order,OrderDtlVO orderDtl,String batchNum, BigDecimal realPrice,
										   BigDecimal taxRate, BigDecimal notaxRealPrice, BigDecimal quantity){
		List<BatchNumber> batchNumberList = new ArrayList<BatchNumber>();
		Long enterpriseId = orderDtl.getEnterpriseId();
		Long parentId = orderDtl.getParentId();

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("enterpriseId", enterpriseId);
		paramMap.put("parentId", parentId);
		paramMap.put("batchNum", batchNum);
		List<BatchNumber> parentBatchNumberList = batchNumberMapper.selectByParamMap(paramMap);

		// 总部未找到分店配进退出的批次，要新生成一条
		if(CollectionUtils.isEmpty(parentBatchNumberList)){
			Long branchId = orderDtl.getCompanyId();
			EnterpriseBusiness enterpriseBusiness = commonComponent.getEnterpriseBusiness(branchId);
			if(enterpriseBusiness==null){
				logger.error("获取分店业务子表数据错误！总单信息："+order.toString());
				throw new BusinessException("总部配后退回入库获取分店业务子表数据错误！");
			}
			Integer distrPriceType = enterpriseBusiness.getDistrPriceType();
			if(distrPriceType.equals(DistrPriceType.COST_PRICE.getCode())){
				// 1.按成本的同门店 do nothing
			}else if(distrPriceType.equals(DistrPriceType.COST_PLUS.getCode())){
				// 2.按成本加成的，按门店成本/（1+加成）
				BigDecimal addRate = enterpriseBusiness.getAddRate()==null?BigDecimal.ZERO:enterpriseBusiness.getAddRate();
				realPrice = realPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
				notaxRealPrice = notaxRealPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
			}else if(distrPriceType.equals(DistrPriceType.PRICE_LIST.getCode())){
				// 3.按价格清单的，
				// 3.1.判断总部是否存在相同商品、批号的，如存在，取最近的，如不存在按下面的判断
				// 3.2.判断总部是否存在相同商品的，如存在，取最近的，如不存在，按价格清单
				Long distrPriceOrderId = enterpriseBusiness.getDistrPriceOrderId();
				PriceOrderDetail priceOrderDetail = commonComponent.getPriceOrderDetail(orderDtl.getEnterpriseId(), distrPriceOrderId, orderDtl.getGoodsId());
				realPrice = priceOrderDetail.getDistrPrice()==null?BigDecimal.ZERO:priceOrderDetail.getDistrPrice();
				taxRate = priceOrderDetail.getDistrTaxRate()==null?BigDecimal.ZERO:priceOrderDetail.getDistrTaxRate();
				notaxRealPrice = priceOrderDetail.getNotaxDistrPrice()==null?BigDecimal.ZERO:priceOrderDetail.getNotaxDistrPrice();
			}

			BatchNumber newBatchNumber = insertBatchNumber(order, orderDtl, batchNum, realPrice, taxRate, notaxRealPrice, quantity);
			batchNumberList.add(newBatchNumber);
		}else{
			BatchNumber batchNumber = parentBatchNumberList.get(0);
			batchNumber.setQuantity(quantity);
			batchNumberList.add(batchNumber);
		}

		return batchNumberList;
	}

	private BatchNumber insertBatchNumber(OrderVO order, OrderDtlVO orderDtl, String batchNum,
										  BigDecimal realPrice, BigDecimal taxRate, BigDecimal notaxRealPrice, BigDecimal quantity) {
		BatchNumber batchNumber;
		batchNumber = new BatchNumber();
		batchNumber.setBatchNum(batchNum);
		batchNumber.setEnterpriseId(order.getEnterpriseId());
		batchNumber.setParentId(order.getParentId());
		batchNumber.setOrderId(order.getOrderId());
		batchNumber.setOrderCode(order.getOrderCode());
		batchNumber.setOrderType(order.getOrderType());
		batchNumber.setOrderDate(order.getOrderDate());
		batchNumber.setOrderDtlId(orderDtl.getOrderDtlId());
		batchNumber.setLineNum(orderDtl.getLineNum());
		batchNumber.setGoodsId(orderDtl.getGoodsId());
		batchNumber.setGoodsCode(orderDtl.getGoodsCode());
		batchNumber.setGoodsName(orderDtl.getGoodsName());
		batchNumber.setLotId(orderDtl.getLotId());
		batchNumber.setLotNum(orderDtl.getLotNum());
		batchNumber.setRealPrice(realPrice);
		batchNumber.setTaxRate(taxRate);
		batchNumber.setNotaxRealPrice(notaxRealPrice);
		batchNumber.setRemark(orderDtl.getRemark());
		batchNumber.setCreaterId(orderDtl.getCreaterId());
		batchNumber.setCreaterCode(orderDtl.getCreaterCode());
		batchNumber.setCreaterName(orderDtl.getCreaterName());
		batchNumber.setCreateTime(new Date());
		batchNumber.setModifierId(orderDtl.getModifierId());
		batchNumber.setModifierCode(orderDtl.getModifierCode());
		batchNumber.setModifierName(orderDtl.getModifierName());
		batchNumber.setUpdateTime(new Date());
		batchNumberMapper.insertSelective(batchNumber);

		batchNumber.setQuantity(quantity);
		return batchNumber;
	}

	/**
	 * @Title: generateOut  
	 * @Description: 普通出库业务生成关键表数据
	 * @param @param orderRule 单据规则
	 * @param @param order 出库单据
	 * @param @param orderDtlList 出库单据明细
	 * @param @throws Exception 异常信息
	 * @return void 返回类型  
	 * @throws
	 */
	private void generateOut(OrderRule orderRule, OrderVO order, List<OrderDtlVO> orderDtlList) throws Exception {
		if(CollectionUtils.isEmpty(orderDtlList)){
			throw new BusinessException("明细单信息不能为空！");
		}

		// 1、更新cost
		updateCostWhenOut(order, orderDtlList);
		// 2、插入毛利
		generateProfitWhenOut(order, orderDtlList);
		// 3、更新库存
		updateStockWhenOut(order, orderDtlList);
		
		Map<String, BigDecimal> map = getInOutTotalInfo(orderDtlList);
		BigDecimal costAmountTotal = map.get("costAmountTotal");
		BigDecimal notaxCostAmountTotal = map.get("notaxCostAmountTotal");
		
		// 4、生成出库明细
		generateInOutRecord(order, orderDtlList, costAmountTotal, notaxCostAmountTotal);
	}

	/**
	 * @Title: updateStockWhenOut  
	 * @Description: 出库更新库存 
	 * @param @param orderDtlList 出库单明细
	 * @return void 返回类型  
	 * @throws
	 */
	private void updateStockWhenOut(OrderVO order,List<OrderDtlVO> orderDtlList) {
		for(OrderDtlVO orderDtl:orderDtlList){
			Long enterpriseId = orderDtl.getEnterpriseId();
			Long parentId = orderDtl.getParentId();
			Long goodsId = orderDtl.getGoodsId();
			Long lotId = orderDtl.getLotId();
			Long shelfId = orderDtl.getShelfId();
			BigDecimal quantity = orderDtl.getQuantity();
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("enterpriseId", enterpriseId);
			paramMap.put("parentId", parentId);
			paramMap.put("goodsId", goodsId);
			paramMap.put("lotId", lotId);
			paramMap.put("shelfId", shelfId);
			List<Stock> stockList = stockMapper.selectByParamMap(paramMap);
			if(CollectionUtils.isEmpty(stockList)){
				logger.error("没有获取到可用库存！【商品ID为\"+goodsId+\",批号ID为\"+lotId+\",货位ID为\"+shelfId+\"】总单信息："+order.toString());
				throw new BusinessException("没有获取到可用库存！【商品ID为"+goodsId+",批号ID为"+lotId+",货位ID为"+shelfId+"】");
			}
			Stock stock = stockList.get(0);
			BigDecimal usableQuantity = stock.getUsableQuantity()==null?BigDecimal.ZERO:stock.getUsableQuantity();
			BigDecimal currentUsableQuantity = usableQuantity.subtract(quantity).setScale(6, RoundingMode.HALF_UP);
			if(currentUsableQuantity.compareTo(BigDecimal.ZERO) < 0){
				logger.error("库存可用量不足！【更新后，库存可用量将变为\"+currentUsableQuantity+\",商品ID为\"+goodsId+\",批号ID为\"+lotId+\",货位ID为\"+shelfId+\"】总单信息："+order.toString());
				throw new BusinessException("库存可用量不足！\n【更新后，库存可用量将变为"+currentUsableQuantity+",商品ID为"+goodsId+",批号ID为"+lotId+",货位ID为"+shelfId+"】");
			}
			stock.setUsableQuantity(usableQuantity.subtract(quantity).setScale(6, RoundingMode.HALF_UP));
			stock.setQuantity(stock.getQuantity().subtract(quantity).setScale(6, RoundingMode.HALF_UP));
			
			stockMapper.updateByPrimaryKey(stock);
		}
	}

	/**
	 * @Title: generateProfitWhenOut  
	 * @Description: 出库生成毛利
	 * @param @param order 出库单据
	 * @param @param orderDtlList 出库单据明细 
	 * @return void 返回类型  
	 * @throws
	 */
	private void generateProfitWhenOut(OrderVO order, List<OrderDtlVO> orderDtlList) {
		for(OrderDtlVO orderDtl:orderDtlList){
			List<Cost> outCostList = orderDtl.getOutCostList();
			// 实际出库金额合计
			BigDecimal outRealAmountTotal = BigDecimal.ZERO;
			// 实际出库不含税金额合计
			BigDecimal outNotaxRealAmoountTotal = BigDecimal.ZERO;
			// 成本金额合计
			BigDecimal costAmountTotal = BigDecimal.ZERO;
			// 不含税成本金额合计
			BigDecimal notaxCostAmountTotal = BigDecimal.ZERO;
			if(CollectionUtils.isNotEmpty(outCostList)){
				for(Cost cost:outCostList){
					Profit profit = new Profit();
					profit.setEnterpriseId(order.getEnterpriseId());
					profit.setParentId(order.getParentId());
					profit.setOutOrderDtlId(orderDtl.getOrderDtlId());
					profit.setOutOrderId(order.getOrderId());
					profit.setOutOrderCode(order.getOrderCode());
					profit.setOutOrderType(order.getOrderType());
					profit.setOutOrderDate(order.getOrderDate());
					profit.setOutOrderDtlId(orderDtl.getOrderDtlId());
					profit.setOutLineNum(orderDtl.getLineNum());
					
					Long batchId = cost.getBatchId();
					BatchNumber batchNumber = batchNumberMapper.selectByPrimaryKey(batchId);
					profit.setInOrderDtlId(batchNumber.getOrderDtlId());
					profit.setInOrderId(batchNumber.getOrderId());
					profit.setInOrderCode(batchNumber.getOrderCode());
					profit.setInOrderType(batchNumber.getOrderType());
					profit.setInOrderDate(batchNumber.getOrderDate());
					profit.setInLineNum(batchNumber.getLineNum());
					profit.setGoodsId(orderDtl.getGoodsId());
					profit.setGoodsName(orderDtl.getGoodsName());
					profit.setGoodsCode(orderDtl.getGoodsCode());
					profit.setLotId(orderDtl.getLotId());
					profit.setLotNum(orderDtl.getLotNum());
					profit.setBatchId(batchId);
					profit.setBatchNum(batchNumber.getBatchNum());
					profit.setSrcUnitId(orderDtl.getCompanyId());
					profit.setSrcUnitCode(orderDtl.getCompanyCode());
					profit.setSrcUnitName(orderDtl.getCompanyCode());
					profit.setFlowUnitId(orderDtl.getCompanyId());
					profit.setFlowUnitCode(orderDtl.getCompanyCode());
					profit.setFlowUnitName(orderDtl.getCompanyName());

					// 成本出库数量
					BigDecimal quantity = cost.getTmpOutQuantity();
					profit.setQuantity(quantity);
					// 成本价
					BigDecimal inRealPrice = batchNumber.getRealPrice()==null?BigDecimal.ZERO:batchNumber.getRealPrice();
					profit.setInRealPrice(inRealPrice);
					// 成本金额
					BigDecimal inRealAmount = inRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
					profit.setInRealAmount(inRealAmount);
					// 明细单含税成本金额合计
					costAmountTotal = costAmountTotal.add(inRealAmount);
					// 进项税
					BigDecimal inTaxRate = batchNumber.getTaxRate();
					profit.setPurchaseTaxRate(batchNumber.getTaxRate());
					BigDecimal inNotaxRealPrice = batchNumber.getNotaxRealPrice()==null?BigDecimal.ZERO:batchNumber.getNotaxRealPrice();
					// 不含税成本单价
					profit.setInNotaxRealPrice(inNotaxRealPrice);
					// 不含税成本金额
					BigDecimal inNotaxRealAmount = inNotaxRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
					profit.setInNotaxRealAmount(inNotaxRealAmount);
					// 明细单不含税成本金额合计
					notaxCostAmountTotal = notaxCostAmountTotal.add(inNotaxRealAmount);
					// 成本税额
					profit.setInTaxAmount(inRealAmount.subtract(inNotaxRealAmount).setScale(2, RoundingMode.HALF_UP));

					// 实际出库单价
					BigDecimal outRealPrice = BigDecimal.ZERO;
					// 实际出库金额
					BigDecimal outRealAmount = BigDecimal.ZERO;
					// 实际出库税率
					BigDecimal outRealTaxRate = BigDecimal.ZERO;
					// 实际不含税出库单价
					BigDecimal outRealNotaxPrice = BigDecimal.ZERO;
					// 实际不含税出库金额
					BigDecimal outNotaxRealAmount = BigDecimal.ZERO;
					// 实际出库税额
					BigDecimal outRealTaxAmount = BigDecimal.ZERO;

					// 如果是配货出库，要根据分店的配货价格类型来计算实际出库单价
					if(order.getOrderType().equals(OrderRule.DISTR_OUT.getType()) || order.getOrderType().equals(OrderRule.DISTR_IN_RETURN_OUT.getType())){
						Long parentId = 0L;
						Long branchId = 0L;
						if(order.getOrderType().equals(OrderRule.DISTR_OUT.getType())){
							parentId = order.getEnterpriseId();
							branchId = order.getCompanyId();
						}else{
							parentId = order.getParentId();
							branchId = order.getEnterpriseId();
						}
						EnterpriseBusiness enterpriseBusiness = commonComponent.getEnterpriseBusiness(branchId);
						if(enterpriseBusiness==null){
							logger.error("获取分店业务子表数据错误！总单信息："+order.toString());
							throw new BusinessException("获取分店业务子表数据错误！");
						}
						Integer distrPriceType = enterpriseBusiness.getDistrPriceType();
						if(distrPriceType.equals(DistrPriceType.PRICE_LIST.getCode())){
							Long distrPriceOrderId = enterpriseBusiness.getDistrPriceOrderId();
							PriceOrderDetail priceOrderDetail = commonComponent.getPriceOrderDetail(parentId, distrPriceOrderId, orderDtl.getGoodsId());
							outRealPrice = priceOrderDetail.getDistrPrice()==null?BigDecimal.ZERO:priceOrderDetail.getDistrPrice();
							outRealTaxRate = priceOrderDetail.getDistrTaxRate()==null?BigDecimal.ZERO:priceOrderDetail.getDistrTaxRate();
							outRealNotaxPrice = priceOrderDetail.getNotaxDistrPrice()==null?BigDecimal.ZERO:priceOrderDetail.getNotaxDistrPrice();
						}else if(distrPriceType.equals(DistrPriceType.COST_PRICE.getCode())){
							outRealPrice = inRealPrice;
							outRealTaxRate = inTaxRate;
							outRealNotaxPrice = inNotaxRealPrice;
						}else if(distrPriceType.equals(DistrPriceType.COST_PLUS.getCode())){
							BigDecimal addRate = enterpriseBusiness.getAddRate()==null?BigDecimal.ZERO:enterpriseBusiness.getAddRate();
							outRealPrice = inRealPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
							outRealTaxRate = inTaxRate;
							outRealNotaxPrice = inNotaxRealPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
						}
						outRealAmount = outRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
						outNotaxRealAmount = outRealNotaxPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
						outRealTaxAmount = outRealAmount.subtract(outNotaxRealAmount).setScale(2, RoundingMode.HALF_UP);
					}else{
						outRealPrice = orderDtl.getRealPrice()==null?BigDecimal.ZERO:orderDtl.getRealPrice();
						outRealTaxRate = orderDtl.getTaxRate()==null?BigDecimal.ZERO:orderDtl.getTaxRate();
						outRealNotaxPrice = orderDtl.getNotaxRealPrice()==null?BigDecimal.ZERO:orderDtl.getNotaxRealPrice();
						outRealAmount = outRealPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
						outNotaxRealAmount = outRealNotaxPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
						outRealTaxAmount = outRealAmount.subtract(outNotaxRealAmount).setScale(2, RoundingMode.HALF_UP);
					}
					// 实际出库单价
					profit.setOutRealPrice(outRealPrice);
					// 实际出库税率
					profit.setSaleTaxRate(outRealTaxRate);
					// 实际出库不含税单价
					profit.setOutNotaxRealPrice(outRealNotaxPrice);
					// 实际出库金额
					profit.setOutRealAmount(outRealAmount);
					// 实际不含税出库金额
					profit.setOutNotaxRealAmount(outNotaxRealAmount);
					// 实际出库税额
					profit.setOutTaxAmount(outRealTaxAmount);
					// 单据实际出库金额合计
					outRealAmountTotal = outRealAmountTotal.add(outRealAmount);
					// 单据不含税出库金额合计
					outNotaxRealAmoountTotal = outNotaxRealAmoountTotal.add(outNotaxRealAmount);

					// 利润金额
					BigDecimal profitVal = outRealAmount.subtract(inRealAmount).setScale(2, RoundingMode.HALF_UP);
					// 不含税利润金额
					BigDecimal notaxProfitVal = outNotaxRealAmount.subtract(inNotaxRealAmount).setScale(2, RoundingMode.HALF_UP);
					BigDecimal profitRate = BigDecimal.ZERO;
					BigDecimal notaxProfitRate = BigDecimal.ZERO;
					if(outRealAmount.compareTo(BigDecimal.ZERO)==0){
						profitRate = BigDecimal.ZERO;
					}else{
						// 利润率
						profitRate = profitVal.divide(outRealAmount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
					}
					if(outNotaxRealAmount.compareTo(BigDecimal.ZERO)==0){
						notaxProfitRate = BigDecimal.ZERO;
					}else{
						// 不含税利润率
						notaxProfitRate = notaxProfitVal.divide(outNotaxRealAmount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
					}
					profit.setProfit(profitVal);
					profit.setNotaxProfit(notaxProfitVal);
					profit.setProfitRate(profitRate);
					profit.setNotaxProfitRate(notaxProfitRate);
					profit.setRemark(orderDtl.getRemark());
					profit.setCreaterId(orderDtl.getCreaterId());
					profit.setCreaterCode(orderDtl.getCreaterCode());
					profit.setCreaterName(orderDtl.getCreaterName());
					profit.setCreateTime(new Date());
					profit.setModifierId(orderDtl.getModifierId());
					profit.setModifierCode(orderDtl.getModifierCode());
					profit.setModifierName(orderDtl.getModifierName());
					profit.setUpdateTime(new Date());
					
					profitMapper.insertSelective(profit);
				}

				outRealAmountTotal=outRealAmountTotal==null?BigDecimal.ZERO:outRealAmountTotal;
				outNotaxRealAmoountTotal=outRealAmountTotal==null?BigDecimal.ZERO:outNotaxRealAmoountTotal;

				BigDecimal orderProfitVal = outRealAmountTotal.subtract(costAmountTotal).setScale(2, RoundingMode.HALF_UP);
				BigDecimal orderNotaxProfitVal = outNotaxRealAmoountTotal.subtract(notaxCostAmountTotal).setScale(2, RoundingMode.HALF_UP);
				BigDecimal orderProfitRate = BigDecimal.ZERO;
				BigDecimal orderNotaxProfitRate = BigDecimal.ZERO;
				if(outRealAmountTotal.compareTo(BigDecimal.ZERO)==0){
					orderProfitRate = BigDecimal.ZERO;
				}else{
					// 明细单利润率
					orderProfitRate = orderProfitVal.divide(outRealAmountTotal, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
				}
				if(outNotaxRealAmoountTotal.compareTo(BigDecimal.ZERO)==0){
					orderNotaxProfitRate = BigDecimal.ZERO;
				}else{
					// 明细单利润率
					orderNotaxProfitRate = orderNotaxProfitVal.divide(outNotaxRealAmoountTotal, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
				}

				orderDtl.setCostAmount(costAmountTotal);
				orderDtl.setNotaxCostAmount(notaxCostAmountTotal);
				orderDtl.setProfit(orderProfitVal);
				orderDtl.setNotaxProfit(orderNotaxProfitVal);
				orderDtl.setProfitRate(orderProfitRate);
				orderDtl.setNotaxProfitRate(orderNotaxProfitRate);
			}
		}
	}

	/**
	 * @Title: updateCostWhenOut  
	 * @Description: 出库更新成本
	 * @param @param order 出库总单
	 * @param @param orderDtlList 出库单据明细 
	 * @return void 返回类型  
	 * @throws
	 */
	private void updateCostWhenOut(OrderVO order, List<OrderDtlVO> orderDtlList) {
		for(OrderDtlVO orderDtl:orderDtlList){
			Long enterpriseId = orderDtl.getEnterpriseId();
			Long parentId = orderDtl.getParentId();
			Long goodsId = orderDtl.getGoodsId();
			Long lotId = orderDtl.getLotId();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("enterpriseId", enterpriseId);
			paramMap.put("parentId", parentId);
			paramMap.put("goodsId", goodsId);
			paramMap.put("lotId", lotId);
			List<Cost> costList = costMapper.selectByParamMap(paramMap);
			if(CollectionUtils.isNotEmpty(costList)){
				List<Cost> updatingCostList=new ArrayList<Cost>();
				// 临时未配数量赋值为本次出库数量
				BigDecimal tmpQuantity = orderDtl.getQuantity();
				for(Cost cost:costList){
					// 实际单价
					BigDecimal realPrice = cost.getRealPrice();
					// 不含税实际单价
					BigDecimal notaxRealPrice = cost.getNotaxRealPrice();
					// 可用量够用
					if(cost.getUsableQuantity().subtract(tmpQuantity).compareTo(BigDecimal.ZERO) >= 0){
						BigDecimal currentUsableQuantity = cost.getUsableQuantity().subtract(tmpQuantity);
						// 可用实际金额
						BigDecimal usableRealAmount = realPrice.multiply(currentUsableQuantity).setScale(2, RoundingMode.HALF_UP);
						// 不含税实际可用金额
						BigDecimal notaxUsableRealAmount = notaxRealPrice.multiply(currentUsableQuantity).setScale(2, RoundingMode.HALF_UP);
						// 可用税额
						BigDecimal usableTaxAmount = usableRealAmount.subtract(notaxUsableRealAmount).setScale(2, RoundingMode.HALF_UP);

						// 暂存出库数量
						cost.setTmpOutQuantity(tmpQuantity);
						cost.setUsableQuantity(currentUsableQuantity);
						cost.setUsableRealAmout(usableRealAmount);
						cost.setNotaxUsableRealAmout(notaxUsableRealAmount);
						cost.setUsableTaxAmount(usableTaxAmount);

						updatingCostList.add(cost);
						
						// 可用量够用，临时未配数量tmpQuantity=0
						tmpQuantity=BigDecimal.ZERO;
						break;
					}else{
						// 排除可用量为0的成本记录
						if(cost.getUsableQuantity().compareTo(BigDecimal.ZERO)==0){
							continue;
						}

						// 可用量不够用，有多少出多少
						tmpQuantity = tmpQuantity.subtract(cost.getUsableQuantity());

						// 暂存出库数量
						cost.setTmpOutQuantity(cost.getUsableQuantity());
						cost.setUsableQuantity(BigDecimal.ZERO);
						cost.setUsableRealAmout(BigDecimal.ZERO);
						cost.setNotaxUsableRealAmout(BigDecimal.ZERO);
						cost.setUsableTaxAmount(BigDecimal.ZERO);

						updatingCostList.add(cost);
					}
					if(tmpQuantity.compareTo(BigDecimal.ZERO)<=0){
						break;
					}
				}
				if(tmpQuantity.compareTo(BigDecimal.ZERO) > 0){
					logger.error("成本可用量不足！【相差数量为\"+tmpQuantity+\",商品ID为\"+goodsId+\",批号ID为\"+lotId+\"】总单信息："+order.toString());
					throw new BusinessException("成本可用量不足！\n【相差数量为"+tmpQuantity+",商品ID为"+goodsId+",批号ID为"+lotId+"】");
				}
				for(Cost cost:updatingCostList){
					costMapper.updateByPrimaryKey(cost);
				}
				// 暂存出库成本列表至出库明细单
				orderDtl.setOutCostList(updatingCostList);
			}
		}
	}

	/**
	 * 填充单价、金额相关信息
	 */
	private class FillPriceAndAmountInfo {
		private OrderVO order;
		private List<OrderDtlVO> orderDtlList;
		private BigDecimal costAmountTotal;
		private BigDecimal notaxCostAmountTotal;

		public FillPriceAndAmountInfo(OrderVO order, List<OrderDtlVO> orderDtlList) {
			this.order = order;
			this.orderDtlList = orderDtlList;
		}

		public BigDecimal getCostAmountTotal() {
			return costAmountTotal;
		}

		public BigDecimal getNotaxCostAmountTotal() {
			return notaxCostAmountTotal;
		}

		public FillPriceAndAmountInfo invoke() {
			// 金额合计
			BigDecimal amountTotal = BigDecimal.ZERO;
			// 整单折扣
			BigDecimal wholeDiscount = new BigDecimal(100);
			// 整单优惠金额
			BigDecimal wholeDiscountAmount = BigDecimal.ZERO;
			// 实际金额合计
			BigDecimal realAmountTotal = BigDecimal.ZERO;
			// 不含税金额合计
			BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
			// 税额合计
			BigDecimal taxAmountTotal = BigDecimal.ZERO;
			costAmountTotal = BigDecimal.ZERO;
			notaxCostAmountTotal = BigDecimal.ZERO;
			// 利润合计
			BigDecimal profitTotal = BigDecimal.ZERO;
			// 不含税利润合计
			BigDecimal notaxProfitTotal = BigDecimal.ZERO;
			// 利润率
			BigDecimal profitRate = BigDecimal.ZERO;
			// 不含税利润率
			BigDecimal notaxProfitRate = BigDecimal.ZERO;

			// 按照近效期、先进先出原则取最近成本价并将orderDtl表中的单价、金额等字段赋值
			for(OrderDtlVO orderDtl:orderDtlList){
                // 取平均成本价、平均不含税成本价
                Long enterpriseId = orderDtl.getEnterpriseId();
                Long goodsId = orderDtl.getGoodsId();
				Long lotId = orderDtl.getLotId();
				List<Long> lotIds = new ArrayList<>();
				lotIds.add(lotId);
				AvgCostVO avgCostVO = commonComponent.getAvgCostVO(enterpriseId,goodsId,lotIds,orderDtl.getQuantity());
				BigDecimal costPrice = avgCostVO.getAvgCostPrice();
				BigDecimal notatCostPrice = avgCostVO.getAvgNotaxCostPrice();

                costPrice = costPrice == null?BigDecimal.ZERO:costPrice;
                // 取商品的默认的进项税
                GoodsDefTaxRateVO goodsDefTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(enterpriseId, goodsId, TaxRateType.PURCHASE.getType());
                BigDecimal taxRate = goodsDefTaxRateVO.getTaxRate()==null?BigDecimal.ZERO:goodsDefTaxRateVO.getTaxRate();
                orderDtl.setUnitPrice(costPrice);
                orderDtl.setLineDiscount(new BigDecimal(100));
                BigDecimal amount = costPrice.multiply(orderDtl.getQuantity()).setScale(2, RoundingMode.HALF_UP);
                orderDtl.setAmount(amount);
                orderDtl.setWholeDiscount(new BigDecimal(100));
                orderDtl.setLineDiscountAmount(BigDecimal.ZERO);
                orderDtl.setRealPrice(costPrice);
                orderDtl.setRealAmount(amount);
                orderDtl.setTaxRate(taxRate);
                orderDtl.setNotaxRealPrice(notatCostPrice);
                BigDecimal notaxAmount = notatCostPrice.multiply(orderDtl.getQuantity()).setScale(2, BigDecimal.ROUND_UP);
                orderDtl.setNotaxRealAmount(notaxAmount);
                BigDecimal taxAmount = amount.subtract(notaxAmount).setScale(2, BigDecimal.ROUND_UP);
                orderDtl.setTaxAmount(taxAmount);
                orderDtl.setCostAmount(amount);
                orderDtl.setNotaxCostAmount(notaxAmount);
                orderDtl.setProfit(BigDecimal.ZERO);
				orderDtl.setNotaxProfit(BigDecimal.ZERO);
				orderDtl.setProfitRate(BigDecimal.ZERO);
				orderDtl.setNotaxProfitRate(BigDecimal.ZERO);

                // 计算总单单价、金额相关字段合计
				// 仅汇总出库方向的明细单的成本金额和不含税成本金额，否则总单成本金额合计会翻倍
				Integer direction = orderDtl.getDirection();
				if(direction.equals(OrderDirection.OUT.getDirection())){
					amountTotal = amountTotal.add(amount);
					realAmountTotal = realAmountTotal.add(amount);
					notaxRealAmountTotal = notaxRealAmountTotal.add(notaxAmount);
					taxAmountTotal = taxAmountTotal.add(taxAmount);

					costAmountTotal = costAmountTotal.add(amount);
					notaxCostAmountTotal = notaxCostAmountTotal.add(notaxAmount);
				}
            }

			order.setAmountTotal(amountTotal);
			order.setWholeDiscount(wholeDiscount);
			order.setWholeDiscountAmount(wholeDiscountAmount);
			order.setRealAmountTotal(realAmountTotal);
			order.setNotaxRealAmountTotal(notaxRealAmountTotal);
			order.setTaxAmountTotal(taxAmountTotal);
			order.setCostAmountTotal(costAmountTotal);
			order.setNotaxCostAmountTotal(notaxCostAmountTotal);
			order.setProfitTotal(profitTotal);
			order.setProfitRate(profitRate);
			order.setNotaxProfitTotal(notaxProfitTotal);
			order.setNotaxProfitRate(notaxProfitRate);
			return this;
		}
	}

	/**
	 * 超量销售处理
	 * @param enterpriseId 企业ID
	 * @param excessiveSaleCodes 超量销售单号
	 * @param user 操作人员信息对象
	 * @throws Exception
	 */
	public void handleExcessiveSaleData(Long enterpriseId, String excessiveSaleCodes, UserVO user) throws Exception {
		if(!StringUtils.isEmpty(excessiveSaleCodes)){
			String[] saleCodeArr = excessiveSaleCodes.trim().split(",");
			// 去重复销售单号
			Set<String> saleCodeSet = new HashSet<>();
			for(int i=0;i<saleCodeArr.length;i++){
				saleCodeSet.add(saleCodeArr[i]);
			}
			OrderModelBuilder builder = new OrderModelBuilder(user);
			for(String saleCode:saleCodeSet){
				List<Sale> sales = saleMapper.queryByCodeAndEnterpriseId(saleCode, enterpriseId);
				if(CollectionUtils.isNotEmpty(sales)){
					for(Sale sale:sales){
						Long saleId = sale.getId();
						List<SaleShelf> saleShelfList = saleShelfMapper.queryBySaleIdAndEnterpriseId(saleId, enterpriseId);

						// 超量销售出库生成关键表数据
						OrderModel orderModel = builder.buildOrderModel(OrderRule.SALES_OUT, sale, saleShelfList);
						generateKeyTableDatas(OrderDirection.OUT, orderModel);

						// 更新销售单的超量销售标识为2-已处理
						sale.setExcessiveSale(2);
						saleMapper.updateByPrimaryKey(sale);
					}
				}
			}
		}
	}

	/**
	 * 处理以超量销售单作为原单进行退货的销退单
	 *
	 * @param enterpriseId
	 * @param excessiveSaleCodes
	 * @param user
	 * @throws Exception
	 */
	public void handleExcessiveSaleReturnData(Long enterpriseId, String excessiveSaleCodes, UserVO user) throws Exception {
		if(!StringUtils.isEmpty(excessiveSaleCodes)){
			String[] saleCodeArr = excessiveSaleCodes.trim().split(",");
			// 去重复销售单号
			Set<String> saleCodeSet = new HashSet<>();
			for(int i=0;i<saleCodeArr.length;i++){
				saleCodeSet.add(saleCodeArr[i]);
			}
			OrderModelBuilder builder = new OrderModelBuilder(user);
			for(String saleCode:saleCodeSet){
				List<Sale> sales = saleMapper.querySaleReturnByCodeAndEnterpriseId(saleCode, enterpriseId);
				if(CollectionUtils.isNotEmpty(sales)){
					for(Sale sale:sales){
						Long saleId = sale.getId();
						List<SaleShelf> saleShelfList = saleShelfMapper.queryBySaleIdAndEnterpriseId(saleId, enterpriseId);

						// 以超量销售单作为原单进行退货的销退单生成关键表数据
						OrderModel orderModel = builder.buildOrderModel(OrderRule.SALES_RETURN_IN, sale, saleShelfList);
						generateKeyTableDatas(OrderDirection.IN, orderModel);
					}
				}
			}
		}
	}


}