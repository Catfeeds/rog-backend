package com.rograndec.feijiayun.chain.business.online.purchase.centralized.service.Impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.dao.ActivityGoodsMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity.ActivityGoods;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.service.CentralizedCartService;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusiness2GoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusinessVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartGoodVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.component.CalculateCartAmountComponent;
import com.rograndec.feijiayun.chain.business.online.purchase.component.MphOrderModelBuild;
import com.rograndec.feijiayun.chain.business.online.purchase.constant.SmartCartConstant;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CentralizedCartServiceImpl implements CentralizedCartService{

    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActivityGoodsMapper activityGoodsMapper;

    @Value("${mph.impl.url}")
	private String mphImplUrl;
    
    @Autowired
    private CalculateCartAmountComponent calculateCartAmountComponent;

    @Override
    public CentralizedCartVO getCentralizedCart(UserVO loginUser) throws Exception {
        CentralizedCartVO centralizedCart = (CentralizedCartVO)redisComponent.get(getRedisKey(loginUser));
        //如果有未匹配的供应商(mphSupplierId==0l),需放到list的最下方
        if(centralizedCart == null){
            return new CentralizedCartVO();
        }
        Iterator<CentralizedCartBusinessVO> it = centralizedCart.getSupplierList().iterator();
        CentralizedCartBusinessVO cartBusinessVO = null;
        while (it.hasNext()){
            cartBusinessVO = it.next();
            if(cartBusinessVO.getMphSupplierId() == 0L && centralizedCart.getSupplierList().size()>1){
                it.remove();
            }else {
                cartBusinessVO = null;
            }
        }
        if(cartBusinessVO != null) {
            List<CentralizedCartBusinessVO> businessVOS = centralizedCart.getSupplierList();
            businessVOS.add(centralizedCart.getSupplierList().size(),cartBusinessVO);
            centralizedCart.setSupplierList(businessVOS);
        }
        return centralizedCart;
    }

    @Override
    public String getRedisKey(UserVO loginUser) throws Exception{
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(SmartCartConstant.CENTRALIZED_PURCHASE_CART);
        redisKey.append(loginUser.getEnterpriseId());
        return redisKey.toString();
    }

//  @Override
//  public void updateCentralizedCart(UserVO loginUser, Long mphSupplierId, String goodsId, Integer amount) throws Exception {
//      CentralizedCartVO centralizedCartVO = reCalculate(getCentralizedCart(loginUser));
//      redisComponent.set(getRedisKey(loginUser),centralizedCartVO);
//  }

    @Override
    public CentralizedCartVO deleteCentralizedCart(UserVO loginUser, List<CentralizedCartBusiness2GoodsVO> business2GoodsVOS) throws Exception {
        CentralizedCartVO centralizedCartVO = getCentralizedCart(loginUser);
        for(CentralizedCartBusiness2GoodsVO business2GoodsVO : business2GoodsVOS){
            Iterator<CentralizedCartBusinessVO> it = centralizedCartVO.getSupplierList().iterator();
            while(it.hasNext()){
                CentralizedCartBusinessVO businessVO = it.next();
                if(businessVO.getMphSupplierId().equals(business2GoodsVO.getMphSupplierId())){
                    for(String goodsId : business2GoodsVO.getGoodsIdList()){
                        Iterator<CentralizedCartGoodVO> itGoods = businessVO.getGoodsList().iterator();
                        while (itGoods.hasNext()){
                            CentralizedCartGoodVO goodVO = itGoods.next();
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
        centralizedCartVO = calculateCartAmountComponent.reCalculateCentralizedCart(centralizedCartVO);
        redisComponent.set(getRedisKey(loginUser),centralizedCartVO);
        return centralizedCartVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlaceOrderDataVO dealOrder(UserVO loginUser) throws Exception {
        MphOrderModelBuild orderModelBuild = new MphOrderModelBuild(Long.valueOf(loginUser.getRgtUserId().longValue()),mphImplUrl);
        CentralizedCartVO centralizedCartVO = reCalculate(getCheckedCentralizedCart(loginUser));
//        CentralizedCartVO centralizedCartVO = getCentralizedCart(loginUser);
        for(CentralizedCartBusinessVO businessVO : centralizedCartVO.getSupplierList()){
            if(businessVO.getMatchingAmount().compareTo(businessVO.getBillingAmount()) == 1){
                throw new BusinessException("供应商:"+businessVO.getMphSupplierName()+"的订单金额:"+businessVO.getBillingAmount()+"+,小于起配金额:"+businessVO.getMatchingAmount()+",不能下单!");
            }
            //根据activityId和goodsId减活动商品的库存,不够给出提示,不让下单
            for (CentralizedCartGoodVO goodVO : businessVO.getGoodsList()){
                ActivityGoods activityGoods = activityGoodsMapper.selectByActivityIdAndGoodsId(goodVO.getActivityId(),goodVO.getgId());
                if(activityGoods.getInventoryQuantity() < goodVO.getQuantity()){
                    throw new BusinessException("供应商:"+businessVO.getMphSupplierName()+"的"+goodVO.getgName()+"的订单数量超出库存数量,不能下单!");
                }else {
                    ActivityGoods updateGoods = new ActivityGoods();
                    updateGoods.setId(activityGoods.getId());
                    updateGoods.setInventoryQuantity(activityGoods.getInventoryQuantity()-goodVO.getQuantity());
                    activityGoodsMapper.updateByPrimaryKeySelective(updateGoods);
                }
            }
        }
        return orderModelBuild.centralizedOrderModelBuild(centralizedCartVO.getSupplierList());
    }

    private CentralizedCartVO getCheckedCentralizedCart(UserVO loginUser) throws Exception{
        CentralizedCartVO centralizedCartVO = getCentralizedCart(loginUser);
        if(centralizedCartVO == null){
            throw new BusinessException("请选择商品后下单!");
        }
        List<CentralizedCartBusinessVO> businessVOS = new ArrayList<>();
        for(CentralizedCartBusinessVO businessVO : centralizedCartVO.getSupplierList()){
            List<CentralizedCartGoodVO> goodVOS = new ArrayList<>();
            for(CentralizedCartGoodVO goodVO : businessVO.getGoodsList()){
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
        centralizedCartVO.setSupplierList(businessVOS);
        return centralizedCartVO;
    }

    @Override
    public void saveCentralizedCart(UserVO loginUser, CentralizedCartVO centralizedCartVO) throws Exception {

        redisComponent.set(getRedisKey(loginUser),calculateCartAmountComponent.reCalculateCentralizedCart(centralizedCartVO));
    }

    /**
     * 根据数量重算购物车金额
     */
    private CentralizedCartVO reCalculate(CentralizedCartVO centralizedCart) throws Exception{
        Integer totalAmount = new Integer(0);//商品总数量
        BigDecimal totalPrice = BigDecimal.ZERO;// 当前应付总价格
        for(CentralizedCartBusinessVO businessVO : centralizedCart.getSupplierList()){
            BigDecimal billingAmount = BigDecimal.ZERO;//开票金额
            int checkdGoodsQty = 0;//勾选的商品数量
            for(CentralizedCartGoodVO goodVO : businessVO.getGoodsList()){
                if(goodVO.getChecked()){
                    checkdGoodsQty++;
                }
                BigDecimal subtotal = BigDecimal.ZERO;//小计 = 数量*秒杀价
                if(goodVO.getQuantity()<=0){
                    throw new BusinessException(businessVO.getMphSupplierName()+"供应商下的"+"商品:"+goodVO.getgName()+"的数量必须大于0");
                }
                if(goodVO.getInventoryQuantity()<goodVO.getQuantity()){
                    throw new BusinessException(businessVO.getMphSupplierName()+"供应商下的"+"商品:"+goodVO.getgName()+"的数量>库存数量,不能下单!");
                }
                if(goodVO.getgCanSplit()==0){
                    if(goodVO.getQuantity()%goodVO.getgMiddlePackage() != 0){
                        throw new BusinessException(businessVO.getMphSupplierName()+"供应商下的"+"商品:"+goodVO.getgName()+"的数量不是中包装的整数倍,不能下单!");
                    }
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
//            totalPrice = totalPrice.add(businessVO.getBillingAmount());
        }
        centralizedCart.setSelectQuantity(totalAmount);
        centralizedCart.setTotalAmount(totalPrice);
        return centralizedCart;

    }

}
