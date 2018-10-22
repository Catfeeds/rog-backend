package com.rograndec.feijiayun.chain.common.component;

import java.util.ArrayList;
import java.util.List;

import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeShelfMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchange;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeShelf;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutShelf;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.*;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.*;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroyShelf;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryShelf;
import com.rograndec.feijiayun.chain.business.storage.lot.dao.LotAdjustMapper;
import com.rograndec.feijiayun.chain.business.storage.lot.dao.LotAdjustShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjust;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjustShelf;
import com.rograndec.feijiayun.chain.business.storage.move.dao.*;
import com.rograndec.feijiayun.chain.business.storage.move.entity.*;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitMapper;
import com.rograndec.feijiayun.chain.business.storage.split.entity.Split;
import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitDetail;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningInventoryDetailMapper;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningInventoryMapper;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventory;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventoryDetail;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 * @ClassName: InOutComponentTest  
 * @Description: 出入库通用组件测试 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月30日 下午5:36:25  
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@MapperScan("com.rograndec.feijiayun.chain.**.dao")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InOutComponentTest {

	@Autowired
	private OpeningInventoryMapper openingInventoryMapper;

	@Autowired
	private OpeningInventoryDetailMapper openingInventoryDetailMapper;

	@Autowired
	private InOutComponent inOutComponent;

	@Autowired
	private PurchaseInStorageMapper purchaseInStorageMapper;

	@Autowired
	private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;

	@Autowired
	private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;

	@Autowired
	private PurchaseReturnOutMapper purchaseReturnOutMapper;

	@Autowired
	private PurchaseReturnOutDetailMapper purchaseReturnOutDetailMapper;

	@Autowired
	private PurchaseReturnOutShelfMapper purchaseReturnOutShelfMapper;

	@Autowired
	private OtherInMapper otherInMapper;

	@Autowired
	private OtherInDetailMapper otherInDetailMapper;

	@Autowired
	private OtherOutMapper otherOutMapper;

	@Autowired
	private OtherOutShelfMapper otherOutShelfMapper;

	@Autowired
	private ShelfMoveMapper shelfMoveMapper;

	@Autowired
	private ShelfMoveDetailMapper shelfMoveDetailMapper;

	@Autowired
	private GoodsLoadMapper goodsLoadMapper;

	@Autowired
	private GoodsLoadDetailMapper goodsLoadDetailMapper;

	@Autowired
	private GoodsClearMapper goodsClearMapper;

	@Autowired
	private GoodsClearShelfMapper goodsClearShelfMapper;

	@Autowired
	private SplitMapper splitMapper;

	@Autowired
	private SplitDetailMapper splitDetailMapper;

	@Autowired
	private IntegralExchangeMapper integralExchangeMapper;

	@Autowired
	private IntegralExchangeShelfMapper integralExchangeShelfMapper;

	@Autowired
	private LotAdjustMapper lotAdjustMapper;

	@Autowired
	private LotAdjustShelfMapper lotAdjustShelfMapper;

	@Autowired
	private GoodsDestroyMapper goodsDestroyMapper;

	@Autowired
	private GoodsDestroyShelfMapper goodsDestroyShelfMapper;

	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private InventoryShelfMapper inventoryShelfMapper;

	@Autowired
	private SaleMapper saleMapper;

	@Autowired
	private SaleShelfMapper saleShelfMapper;

	@Autowired
	private DistrOutMapper distrOutMapper;

	@Autowired
	private DistrOutShelfMapper distrOutShelfMapper;

	@Autowired
	private DistrInMapper distrInMapper;

	@Autowired
	private DistrInShelfMapper distrInShelfMapper;

	@Autowired
	private DistrInReturnOutMapper distrInReturnOutMapper;

	@Autowired
	private DistrInReturnOutShelfMapper distrInReturnOutShelfMapper;

	@Autowired
	private DistrReturnInMapper distrReturnInMapper;

	@Autowired
	private DistrReturnInShelfMapper distrReturnInShelfMapper;

	// 测试期初库存生成关键表数据
//	@Test
	public void testGenerateInForOpeningInventory() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();
		// 构造期初库存信息
		Long opengingInventoryId = 83L;
		OpeningInventory opengingInventory = openingInventoryMapper.selectByPrimaryKey(opengingInventoryId);
		List<OpeningInventoryDetail> dtlList = openingInventoryDetailMapper.getInventoryRecord(opengingInventoryId);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.OPENING_INVENTORY, opengingInventory, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
	}

	private UserVO getUserVO() {
		UserVO user = new UserVO();
		user = new UserVO();
		user.setUserId(1L);
		user.setEnterpriseId(1L);
		user.setEnterpriseCode("RGRANDEC");
		user.setEnterpriseName("融贯医药");
		user.setParentId(0L);
		user.setUserCode("0001");
		user.setUserName("接口测试人员（总部）");
		user.setLoginAccount("admin");
		user.setPassword("123456");
		user.setChainType(0);
		return user;
	}

	// 测试采购入库生成关键表数据
//	@Test
	public void testGenerateInForPurchaseInStorage() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();
		// 获取采购测试数据

		Long inStorageId = 49L;
		PurchaseInStorage purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(inStorageId);

		List<PurchaseInStorageShelf> dtlList = new ArrayList<>();
		List<PurchaseInStorageShelf> dtlList1 = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(1L, 77L);
		List<PurchaseInStorageShelf> dtlList2 = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(1L, 78L);
		dtlList.addAll(dtlList1);
		dtlList.addAll(dtlList2);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.PURCHASE_IN, purchaseInStorage, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);

	}

	// 测试购进退出出库生成关键表数据
//	@Test
	public void testGenerateOutForPurchaseReturnOut() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造购进退出出库单数据
		Long outId = 1L;
		PurchaseReturnOut purchaseReturnOut = purchaseReturnOutMapper.selectByPrimaryKey(outId);

		List<PurchaseReturnOutShelf> dtlList = new ArrayList<>();
		PurchaseReturnOutShelf outShelf1 = purchaseReturnOutShelfMapper.selectByPrimaryKey(1L);
		PurchaseReturnOutShelf outShelf2 = purchaseReturnOutShelfMapper.selectByPrimaryKey(2L);
		dtlList.add(outShelf1);
		dtlList.add(outShelf2);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.PURCHASE_RETURN_OUT, purchaseReturnOut, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}

	// 测试其它入库生成关键表数据
//	@Test
	public void testGenerateInForOtherIn() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造其它入库单单据
		Long otherInId = 12L;
		OtherIn otherIn = otherInMapper.selectByPrimaryKey(otherInId);
		List<OtherInDetail> dtlList = otherInDetailMapper.selectByEnterpriseIdAndInId(1L, 12L);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.RECEIVE, otherIn, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
	}

	// 测试其它出库生成关键表数据
//	@Test
	public void testGenerateOutForOtherOut() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造其它入库单单据
		Long otherOutId = 12L;
		OtherOut otherOut = otherOutMapper.selectByPrimaryKey(otherOutId);
		List<Long> ids = new ArrayList<>();
		ids.add(1L);

		List<OtherOutShelf> dtlList = otherOutShelfMapper.selectByDtlIds(ids);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.SEND, otherOut, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}

	// 测试货位移动生成关键表数据
//	@Test
	public void testGenerateMoveForShelfMove() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 货位移动单数据
		Long shelfMoveId = 31L;
		ShelfMove shelfMove = shelfMoveMapper.selectByPrimaryKey(shelfMoveId);

		List<ShelfMoveDetail> dtlList = new ArrayList<>();
		ShelfMoveDetail dtl1 = shelfMoveDetailMapper.selectByPrimaryKey(21L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.SHELF_MOVE, shelfMove, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.MOVE, orderModel);
	}

	// 测试中药饮片装斗生成关键表数据
//	@Test
	public void testGenerateForGoodsLoad() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 中药装斗数据
		Long loadId = 71L;
		GoodsLoad goodsLoad = goodsLoadMapper.selectByPrimaryKey(loadId);

		List<GoodsLoadDetail> dtlList = new ArrayList<>();
		GoodsLoadDetail dtl1 = goodsLoadDetailMapper.selectByPrimaryKey(68L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.LOAD_BUCKET, goodsLoad, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.LOAD, orderModel);

	}

	// 测试中药饮片清斗生成关键表数据
//	@Test
	public void testGenerateForGoodsClear() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 中药清斗数据
		Long clearId = 52L;
		GoodsClear goodsClear = goodsClearMapper.selectByPrimaryKey(clearId);

		List<GoodsClearShelf> dtlList = new ArrayList<>();
		GoodsClearShelf dtl1 = goodsClearShelfMapper.selectByPrimaryKey(43L);
		dtlList.add(dtl1);

		GoodsClearShelf dtl2 = goodsClearShelfMapper.selectByPrimaryKey(44L);
		dtlList.add(dtl2);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.CLEAR_BUCKET, goodsClear, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}

	// 测试药品拆零生成关键表数据
//	@Test
	public void testGenerateForSplit() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造商品拆零数据
		Long splitId = 23L;
		Split split = splitMapper.selectByPrimaryKey(splitId);

		List<SplitDetail> dtlList = new ArrayList<>();
		SplitDetail dtl1 = splitDetailMapper.selectByPrimaryKey(15L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel outOrderModel = builder.buildSplitModel(OrderRule.SPLIT, split, dtlList, OrderDirection.SPLIT_OUT.getDirection());
		OrderModel inOrderModel = builder.buildSplitModel(OrderRule.SPLIT, split, dtlList, OrderDirection.SPLIT_IN.getDirection());
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, outOrderModel);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, inOrderModel);

	}

	// 测试积分兑换生成关键表数据
//	@Test
	public void testGenerateForIntegralExchange() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造积分兑换数据
		Long exchangeId = 14L;
		IntegralExchange integralExchange = integralExchangeMapper.selectByPrimaryKey(exchangeId);

		List<IntegralExchangeShelf> dtlList = new ArrayList<>();
		IntegralExchangeShelf dtl1 = integralExchangeShelfMapper.selectByPrimaryKey(7L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.INTEGRAL_EXCHANGE, integralExchange, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}

	// 测试批号调整生成关键表数据
//	@Test
	public void testGenerateForLotAdjust() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造批号调整数据
		Long adjustId = 77L;
		LotAdjust lotAdjust = lotAdjustMapper.selectByPrimaryKey(adjustId);

		List<LotAdjustShelf> dtlList = new ArrayList<>();
		LotAdjustShelf dtl1 = lotAdjustShelfMapper.selectByPrimaryKey(18L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel outOrderModel = builder.buildLotAdjustModel(OrderRule.LOT_ADJUST, lotAdjust, dtlList, OrderDirection.LOT_ADJUST_OUT.getDirection());
		OrderModel inOrderModel = builder.buildLotAdjustModel(OrderRule.LOT_ADJUST, lotAdjust, dtlList, OrderDirection.LOT_ADJUST_IN.getDirection());
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, outOrderModel);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, inOrderModel);

	}

	// 测试药品销毁生成关键表数据
//	@Test
	public void testGenerateForGoodsDestroy() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造批号调整数据
		Long destroyId = 77L;
		GoodsDestroy goodsDestroy = goodsDestroyMapper.selectByPrimaryKey(destroyId);

		List<GoodsDestroyShelf> dtlList = new ArrayList<>();
		GoodsDestroyShelf dtl1 = goodsDestroyShelfMapper.selectByPrimaryKey(18L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.DESTROY, goodsDestroy, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}

	// 测试盘点生成关键表数据
//	@Test
	public void testGenerateForInventory() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造盘点数据
		Long invId = 32L;
		Inventory inventory = inventoryMapper.selectByPrimaryKey(invId);

		List<InventoryShelf> dtlList = new ArrayList<>();
		InventoryShelf dtl1 = inventoryShelfMapper.selectByPrimaryKey(93L);
		dtlList.add(dtl1);

		InventoryShelf dtl2 = inventoryShelfMapper.selectByPrimaryKey(94L);
		dtlList.add(dtl2);

		InventoryShelf dtl3 = inventoryShelfMapper.selectByPrimaryKey(95L);
		dtlList.add(dtl3);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.INVENTORY, inventory, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.INVENTORY, orderModel);
	}


	// 测试销售出库生成关键表数据
//	@Test
	public void testGenerateForSale() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造销售出库数据
		Long saleId = 63L;
		Sale sale = saleMapper.selectByPrimaryKey(saleId);

		List<SaleShelf> dtlList = new ArrayList<>();
		SaleShelf dtl1 = saleShelfMapper.selectByPrimaryKey(96L);
		dtlList.add(dtl1);

		SaleShelf dtl2 = saleShelfMapper.selectByPrimaryKey(97L);
		dtlList.add(dtl2);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.SALES_OUT, sale, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}

	// 测试销后退回入库生成关键表数据
//	@Test
	public void testGenerateForSaleReturn() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造销后退回入库数据
		Long saleId = 73L;
		Sale sale = saleMapper.selectByPrimaryKey(saleId);

		List<SaleShelf> dtlList = new ArrayList<>();
		SaleShelf dtl1 = saleShelfMapper.selectByPrimaryKey(109L);
		dtlList.add(dtl1);

		SaleShelf dtl2 = saleShelfMapper.selectByPrimaryKey(110L);
		dtlList.add(dtl2);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.SALES_RETURN_IN, sale, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
	}


	// 测试总部配货出库生成关键表数据
//	@Test
	public void testGenerateForDistrOut() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造配货出库数据
		Long outId = 6L;
		DistrOut distrOut = distrOutMapper.selectByPrimaryKey(outId);

		List<DistrOutShelf> dtlList = new ArrayList<>();
		DistrOutShelf dtl1 = distrOutShelfMapper.selectByPrimaryKey(4L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_OUT, distrOut, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}

	// 测试分店配进入库生成关键表数据
//	@Test
	public void testGenerateForDistrIn() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造分店配进入库数据
		Long inId = 3L;
		DistrIn distrIn = distrInMapper.selectByPrimaryKey(inId);

		List<DistrInShelf> dtlList = new ArrayList<>();
		DistrInShelf dtl1 = distrInShelfMapper.selectByPrimaryKey(1L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_IN, distrIn, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
	}


	// 测试分店配进退出出库生成关键表数据
//	@Test
	public void testGenerateForDistrInReturnOut() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造分店配进退出单数据
		Long outId = 12L;
		DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(outId);

		List<DistrInReturnOutShelf> dtlList = new ArrayList<>();
		DistrInReturnOutShelf dtl1 = distrInReturnOutShelfMapper.selectByPrimaryKey(10L);
		dtlList.add(dtl1);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_IN_RETURN_OUT, distrInReturnOut, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
	}


	// 测试总部配后退回入库生成关键表数据
//	@Test
	public void testGenerateDistrReturnIn() throws Exception {
		// 构造当前登录人信息
		UserVO user = getUserVO();

		// 构造总部配后退回入库
		Long inId = 22L;
		DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(inId);

		List<DistrReturnInShelf> dtlList = new ArrayList<>();
		DistrReturnInShelf dtl1 = distrReturnInShelfMapper.selectByPrimaryKey(14L);
		dtlList.add(dtl1);

		DistrReturnInShelf dtl2 = distrReturnInShelfMapper.selectByPrimaryKey(15L);
		dtlList.add(dtl2);

		OrderModelBuilder builder = new OrderModelBuilder(user);
		OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_RETURN_IN, distrReturnIn, dtlList);
		inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
	}



}