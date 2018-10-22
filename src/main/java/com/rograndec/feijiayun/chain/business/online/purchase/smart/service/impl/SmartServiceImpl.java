package com.rograndec.feijiayun.chain.business.online.purchase.smart.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.online.purchase.component.CalculateCartAmountComponent;
import com.rograndec.feijiayun.chain.business.online.purchase.component.MphOrderModelBuild;
import com.rograndec.feijiayun.chain.business.online.purchase.constant.SmartCartConstant;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.PriceComparisonDisplayMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SmartPurchasingPlanMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.PriceComparisonDisplay;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SmartPurchasingPlan;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.SmartService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.RequestDeleteScartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartPurchasingPlanVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingSupplierVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanGoodsVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.search.MphGoodsSearchServiceClient;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

@Service
public class SmartServiceImpl implements SmartService{

    @Autowired
    private SmartPurchasingPlanMapper smartPurchasingPlanMapper;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private MphGoodsSearchServiceClient mphGoodsSearchServiceClient;
    
    @Autowired
    private CalculateCartAmountComponent calculateCartAmountComponent;

    @Autowired
    private PriceComparisonDisplayMapper priceComparisonDisplayMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    
    @Value("${mph.impl.url}")
	private String mphImplUrl;

    @Override
    public List<SmartPurchasingPlanVO> selectSmartPlan(UserVO userVO) {
        List<SmartPurchasingPlan> list = smartPurchasingPlanMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        List<SmartPurchasingPlanVO> planList = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (SmartPurchasingPlan plan : list) {
                SmartPurchasingPlanVO vo = new SmartPurchasingPlanVO();
                vo = SmartPurchasingPlan.convertToVO(plan);
                Long goodsId = plan.getGoodsId();
                Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                vo.setSpecification(goods.getSpecification());
                planList.add(vo);
            }
        }
        return planList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SmartPurchasingPlanVO> convertToListWithDistinct(List<PurchasePlanGoodsVO> purchasePlanGoodsVOS, UserVO userVO) throws Exception{
        /**
         * 当前企业存在的订单数据
         */
        List<SmartPurchasingPlan> list = smartPurchasingPlanMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        List<Long> preList = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (SmartPurchasingPlan pre : list) {
                preList.add(pre.getGoodsId());
            }
        }
        /**
         * 智能采购数据
         */
        List<SmartPurchasingPlan> intellegentList = new ArrayList<>();
        for (PurchasePlanGoodsVO vo : purchasePlanGoodsVOS) {
            SmartPurchasingPlan plan = new SmartPurchasingPlan();
            plan = PurchasePlanGoodsVO.convertTOEntity(vo,userVO);
            intellegentList.add(plan);
        }
        //需要返回的数据（去重后）
        List<SmartPurchasingPlan> returnList = new ArrayList<>();
        if (intellegentList.size() > 0){
            for (SmartPurchasingPlan now : intellegentList) {
                if (!preList.contains(now.getGoodsId())){
                    returnList.add(now);
                }
            }
        }
        //插入数据库智彩的内容
        if (returnList.size() > 0){
            for (SmartPurchasingPlan b : returnList) {
                smartPurchasingPlanMapper.insertSelective(b);
            }
        }
        List<SmartPurchasingPlanVO> smartPurchasingPlanVOS = selectSmartPlan(userVO);
        return smartPurchasingPlanVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SmartPurchasingPlanVO> insertSerachGoodsList(List<SmartPurchasingPlanVO> list, UserVO userVO) throws Exception{
        List<SmartPurchasingPlan> realList = smartPurchasingPlanMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        //原来的商品信息
        List<Long> goodsList = new ArrayList<>();
        if (realList != null && realList.size() > 0){
            for (SmartPurchasingPlan plan : realList) {
                goodsList.add(plan.getGoodsId());
            }
        }
        //验证新增商品信息
        if (list.size() > 0){
            for (SmartPurchasingPlanVO vo : list) {
                if (goodsList.contains(vo.getId())){
                    throw new BusinessException(SysCode.FAIL.getCode(), "商品已重复不可添加!");
                }
            }
        }
        //插入商品信息
        if (list.size() > 0){
            for (SmartPurchasingPlanVO vo : list) {
                SmartPurchasingPlan plan = new SmartPurchasingPlan();
                plan = SmartPurchasingPlanVO.convertTOEntity(vo,userVO);
                Long goodsId = plan.getGoodsId();
                Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                plan.setGoodsCode(goods.getCode());
                plan.setGoodsName(goods.getName());
                plan.setGoodsGenericName(goods.getGenericName());
                plan.setManufacturerId(goods.getManufacturerId());
                plan.setManufacturer(goods.getManufacturer());
                plan.setInventoryQuantity(BigDecimal.ZERO);
                plan.setLackQuantity(BigDecimal.ZERO);
                smartPurchasingPlanMapper.insertSelective(plan);
            }
        }
        List<SmartPurchasingPlanVO> smartPurchasingPlanVOS = selectSmartPlan(userVO);
        return smartPurchasingPlanVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SmartPurchasingPlanVO> updateGoodsCount(Long tid, BigDecimal quantity, UserVO userVO) throws Exception{
        SmartPurchasingPlan plan = new SmartPurchasingPlan();
        plan.setId(tid);
        plan.setPurchaseQuantity(quantity);
        UserEnterpriseUtils.setUserCreateOrModify(plan,userVO,false);
        smartPurchasingPlanMapper.updateByPrimaryKeySelective(plan);
        List<SmartPurchasingPlanVO> smartPurchasingPlanVOS = selectSmartPlan(userVO);
        return smartPurchasingPlanVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SmartPurchasingPlanVO> deleteGoodsList(List<Long> list, UserVO userVO) {
        if (list.size() > 0){
            for (Long s : list) {
                smartPurchasingPlanMapper.deleteByPrimaryKey(s);
            }
        }
        return selectSmartPlan(userVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitCart(List<Long> list, UserVO userVO) {
        //需要存到Redis中的数据
        SmartSourcingCartVO redisSmart = new SmartSourcingCartVO();
        //需要转移到购物车的数据
        List<SmartPurchasingPlan> removeList = new ArrayList<>();
        for (Long s : list) {
            SmartPurchasingPlan smartPurchasingPlan = smartPurchasingPlanMapper.selectByPrimaryKey(s);
            removeList.add(smartPurchasingPlan);
        }
        //将需要转移到购物车的数据转换成redis需要暂存的VO类型[第一次插入的时候并没有对应的供货商]
        List<SmartSourcingSupplierVO> supplierList = new ArrayList<>();
        SmartSourcingSupplierVO supplierVO = new SmartSourcingSupplierVO();
        supplierVO.setEntrepriseId(userVO.getEnterpriseId());
        supplierVO.setMphSupplierId(0L);
        List<SmartSourcingGoodsVO> goodsList = new ArrayList<>();
        //将商品信息加入供货商对象中
        for (SmartPurchasingPlan plan : removeList) {
            SmartSourcingGoodsVO goodsVO = new SmartSourcingGoodsVO();
            Long goodsId = plan.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            goodsVO = SmartPurchasingPlan.convertToGoodsVO(plan,goods,userVO);
            goodsList.add(goodsVO);
        }
        supplierVO.setGoodsList(goodsList);
        supplierList.add(supplierVO);
        redisSmart.setSupplierList(supplierList);
        //将购物车对象存储到redis中(智采标识 + 企业ID)
        //第一次添加
        if (redisComponent.get(getRedisKey(userVO)) == null){
            redisComponent.set(getRedisKey(userVO), redisSmart);
        }else {
            /**
             * 不是第一次添加数据，插入redis中的时候需要判断当前商品如果存在，那么数量相加，商品不存在那么添加商品信息
             * 因为将订单中的信息添加进去都是属于供货商为0的,如果没有则加一个供货商，并将单位加进去
             */
            SmartSourcingCartVO smartCart = (SmartSourcingCartVO)redisComponent.get(getRedisKey(userVO));
            List<SmartSourcingSupplierVO> preSupplierList = smartCart.getSupplierList();
            List<Long> supplierIdList = new ArrayList<>();
            for (SmartSourcingSupplierVO supplier : preSupplierList) {
                supplierIdList.add(supplier.getMphSupplierId());
            }
            if (!supplierIdList.contains(0L)){
                /**
                 * 购物车不包含0L的供货单位
                 */
                preSupplierList.add(supplierVO);
                smartCart.setSupplierList(preSupplierList);
            }else{
                /**
                 * 当包含0L的供货单位时 1。商品相同数量添加 2.商品不同添加商品
                 */
                List<SmartSourcingSupplierVO> nowSupplierList = new ArrayList<>();
                for (SmartSourcingSupplierVO supplier : preSupplierList){
                    if (supplier.getMphSupplierId().equals(0L)){
                        List<SmartSourcingGoodsVO> newGoodList = supplier.getGoodsList();
                        for (SmartSourcingGoodsVO goodsVO : newGoodList){
                            Iterator<SmartSourcingGoodsVO> it = goodsList.iterator();
                            while (it.hasNext()){
                                SmartSourcingGoodsVO next = it.next();
                                if (goodsVO.getGoodsId().equals(next.getGoodsId())){
                                    goodsVO.setQuantity(goodsVO.getQuantity() + next.getQuantity());
                                    it.remove();
                                }
                            }
                        }
                        /**
                         * 添加不同的商品信息
                         */
                        List<SmartSourcingGoodsVO> uniqueGoodList = supplier.getGoodsList();
                        for (SmartSourcingGoodsVO g : goodsList) {
                            uniqueGoodList.add(g);
                        }
                        supplier.setGoodsList(uniqueGoodList);
                        nowSupplierList.add(supplier);
                    }
                }
                smartCart.setSupplierList(nowSupplierList);
            }
            redisComponent.remove(getRedisKey(userVO));
            redisComponent.set(getRedisKey(userVO), smartCart);
        }

        //删除采购计划表中被转移到数据库中的商品数据
        for (Long s : list) {
            smartPurchasingPlanMapper.deleteByPrimaryKey(s);
        }
    }

    @Override
    public SmartSourcingCartVO getSmartCart(UserVO loginUser) throws Exception{
        SmartSourcingCartVO smartCart = (SmartSourcingCartVO)redisComponent.get(getRedisKey(loginUser));
        //将购物车中的不包含供货单位的数据信息放在list下面
        if (smartCart != null){
            Iterator<SmartSourcingSupplierVO> it = smartCart.getSupplierList().iterator();
            SmartSourcingSupplierVO supplier = null;
            while (it.hasNext()){
                supplier = it.next();
                if (supplier.getMphSupplierId() == 0L && smartCart.getSupplierList().size() > 1){
                    it.remove();
                    break;
                }else {
                    supplier = null;
                }
            }
            if (supplier != null){
                List<SmartSourcingSupplierVO> supplierList = smartCart.getSupplierList();
                supplierList.add(smartCart.getSupplierList().size(),supplier);
                smartCart.setSupplierList(supplierList);
            }
            SmartSourcingCartVO smartSourcingCartVO = calculateCartAmountComponent.reCalculateSmartCart(smartCart);
            /**
             * 判断当前登陆企业对应的全网比价按钮是否显示
             */
            Long enterpriseId = loginUser.getEnterpriseId();
            Enterprise en = enterpriseMapper.selectByPrimaryKey(enterpriseId);
            /**
             * 省市区ID
             */
            List<PriceComparisonDisplay> dis = null;
            if(en.getAreaId() != null && en.getAreaId() != 0){
                dis = priceComparisonDisplayMapper.selectByAreaId(en.getAreaId());
            }
            if(dis == null && en.getCityId() != null && en.getCityId() != 0){
                dis = priceComparisonDisplayMapper.selectByCityId(en.getCityId());
            }
            if(dis == null && en.getProvinceId() != null && en.getProvinceId() != 0){
                dis = priceComparisonDisplayMapper.selectByProvinceId(en.getProvinceId());
            }
            if(dis != null && dis.size() > 0){
                PriceComparisonDisplay priceComparisonDisplay = dis.get(0);
                smartSourcingCartVO.setWholeNetworkParity(priceComparisonDisplay.getStatus());
            }else{
                smartSourcingCartVO.setWholeNetworkParity(1);
            }
            return smartSourcingCartVO;
        }
        return new SmartSourcingCartVO();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SmartSourcingCartVO deleteSmartCart(UserVO loginUser, List<RequestDeleteScartVO> requestDeleteScartVO) throws Exception{
        SmartSourcingCartVO smartSourcingCartVO = getSmartCart(loginUser);
        for(RequestDeleteScartVO business2GoodsVO : requestDeleteScartVO){
            Iterator<SmartSourcingSupplierVO> it = smartSourcingCartVO.getSupplierList().iterator();
            while(it.hasNext()){
                SmartSourcingSupplierVO businessVO = it.next();
                /**
                 * 未匹配供应商 && 用的是saas商品ID比较
                 */
                if(businessVO.getMphSupplierId().equals(business2GoodsVO.getMphSupplierId()) && businessVO.getMphSupplierId().equals(0L)){
                    for(Long goodsId : business2GoodsVO.getGoodsIdList()){
                        Iterator<SmartSourcingGoodsVO> itGoods = businessVO.getGoodsList().iterator();
                        while (itGoods.hasNext()){
                            SmartSourcingGoodsVO goodVO = itGoods.next();
                            if(goodVO.getGoodsId().equals(goodsId)){
                                itGoods.remove();
                            }
                        }
                    }
                    if(businessVO.getGoodsList().isEmpty()){
                        it.remove();
                    }
                /**
                  * 匹配供应商 && 用的是MPH商品ID比较
                */
                }else if (businessVO.getMphSupplierId().equals(business2GoodsVO.getMphSupplierId()) && !businessVO.getMphSupplierId().equals(0L)){
                    for(Long goodsId : business2GoodsVO.getGoodsIdList()){
                        Iterator<SmartSourcingGoodsVO> itGoods = businessVO.getGoodsList().iterator();
                        while (itGoods.hasNext()){
                            SmartSourcingGoodsVO goodVO = itGoods.next();
                            if(goodVO.getgId().equals(goodsId)){
                                itGoods.remove();
                            }
                        }
                    }
                    if(businessVO.getGoodsList().isEmpty()){
                        it.remove();
                    }
                }
            }
        }
        smartSourcingCartVO = calculateCartAmountComponent.reCalculateSmartCart(smartSourcingCartVO);
        redisComponent.set(getRedisKey(loginUser),smartSourcingCartVO);
        return smartSourcingCartVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSmartCart(UserVO loginUser, SmartSourcingCartVO smartSourcingCartVO) throws Exception{
        redisComponent.set(getRedisKey(loginUser),calculateCartAmountComponent.reCalculateSmartCart(smartSourcingCartVO));
    }

//    @Override
//    public PlaceOrderDataVO dealOrder(UserVO loginUser, SmartSourcingCartVO smartSourcingCartVO) throws Exception{
//        SmartSourcingCartVO cartVO = calculateCartAmountComponent.reCalculateSmartCart(smartSourcingCartVO);
//        MphOrderModelBuild orderModelBuild = new MphOrderModelBuild(Long.valueOf(loginUser.getRgtUserId()), mphImplUrl);
//        return orderModelBuild.smartSourcingOrderModelBuild(cartVO.getSupplierList());
//    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlaceOrderDataVO dealOrder(UserVO loginUser) throws Exception{
        MphOrderModelBuild orderModelBuild = new MphOrderModelBuild(Long.valueOf(loginUser.getRgtUserId().longValue()), mphImplUrl);
        SmartSourcingCartVO smartSourcingCartVO = reCalculate(getCheckedSmartCart(loginUser));
        return orderModelBuild.smartSourcingOrderModelBuild(smartSourcingCartVO.getSupplierList());
    }

    private SmartSourcingCartVO getCheckedSmartCart(UserVO loginUser) throws Exception{
        SmartSourcingCartVO smartCart = getSmartCart(loginUser);
        if(smartCart == null){
            throw new BusinessException("请选择商品后下单!");
        }
        List<SmartSourcingSupplierVO> businessVOS = new ArrayList<>();
        for(SmartSourcingSupplierVO businessVO : smartCart.getSupplierList()){
            List<SmartSourcingGoodsVO> goodVOS = new ArrayList<>();
            for(SmartSourcingGoodsVO goodVO : businessVO.getGoodsList()){
                if(goodVO.getChecked()){
                    goodVOS.add(goodVO);
                }
            }
            if(goodVOS.size()>0){
                businessVO.setGoodsList(goodVOS);
                businessVOS.add(businessVO);
            }
        }
        if(businessVOS.isEmpty()){
            throw new BusinessException("请选择商品后下单!");
        }
        smartCart.setSupplierList(businessVOS);
        return smartCart;
    }

    /**
     * 根据数量重算购物车金额
     */
    private SmartSourcingCartVO reCalculate(SmartSourcingCartVO smartSourcingCartVO) throws Exception{
        Integer totalAmount = new Integer(0);//商品总数量
        BigDecimal totalPrice = BigDecimal.ZERO;// 当前应付总价格
        for(SmartSourcingSupplierVO businessVO : smartSourcingCartVO.getSupplierList()){
            BigDecimal billingAmount = BigDecimal.ZERO;//开票金额
            int checkdGoodsQty = 0;//勾选的商品数量
            for(SmartSourcingGoodsVO goodVO : businessVO.getGoodsList()){
                if(goodVO.getChecked()){
                    checkdGoodsQty++;
                }
                BigDecimal subtotal = BigDecimal.ZERO;//小计 = 数量*秒杀价
                if(goodVO.getQuantity()<=0){
                    throw new BusinessException(businessVO.getMphSupplierName()+"供应商下的"+"商品:"+goodVO.getgName()+"的数量必须大于0");
                }
                if(goodVO.getQuantity()>goodVO.getInventoryQuantity()){
                    throw new BusinessException(businessVO.getMphSupplierName()+"供应商下的"+"商品:"+goodVO.getgName()+"的库存数量不足");
                }
                if(goodVO.getgCanSplit() == 0 && goodVO.getQuantity()%goodVO.getgMiddlePackage() != 0){
                    throw new BusinessException(businessVO.getMphSupplierName()+"供应商下的"+"商品:"+goodVO.getgName()+"的数量"+goodVO.getQuantity()+"必须是中包装数量"+goodVO.getgMiddlePackage()+"的整数倍!");
                }
                subtotal = goodVO.getRetailPrice().multiply(new BigDecimal(goodVO.getQuantity()));
                goodVO.setSubtotal(subtotal.setScale(2, RoundingMode.HALF_UP));
                billingAmount = billingAmount.add(goodVO.getSubtotal());
                if(goodVO.getChecked()) {
                    totalAmount += goodVO.getQuantity();
                    totalPrice = totalPrice.add(goodVO.getSubtotal());
                }
            }
            if(businessVO.getGoodsList().size() == checkdGoodsQty){
                businessVO.setChecked(true);
            }
            businessVO.setBillingAmount(billingAmount);
        }
        smartSourcingCartVO.setSelectQuantity(totalAmount);
        smartSourcingCartVO.setTotalAmount(totalPrice);
        return smartSourcingCartVO;
    }

    private String getRedisKey(UserVO loginUser) {
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(SmartCartConstant.SMART_PURCHASE_CART);
        redisKey.append(loginUser.getEnterpriseId());
        return redisKey.toString();
    }


}
