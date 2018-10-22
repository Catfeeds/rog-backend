package com.rograndec.feijiayun.chain.business.online.purchase.component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusinessVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartGoodVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingSupplierVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

@Component
public class CalculateCartAmountComponent {

	/**
     * 根据数量重算智能采购购物车金额
     */
    public SmartSourcingCartVO reCalculateSmartCart(SmartSourcingCartVO smartSourcingCartVO) throws Exception{
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
                    throw new BusinessException(businessVO.getMphSupplierName()+"供应商下的"+"商品:"+goodVO.getGoodsName()+"的数量必须大于0");
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
    
    
    /**
     * 根据数量重算集采购物车金额
     */
    public CentralizedCartVO reCalculateCentralizedCart(CentralizedCartVO centralizedCart) throws Exception{
        Integer totalAmount = new Integer(0);//商品总数量
        BigDecimal totalPrice = BigDecimal.ZERO;// 当前应付总价格
        for(CentralizedCartBusinessVO businessVO : centralizedCart.getSupplierList()){
            BigDecimal billingAmount = BigDecimal.ZERO;//开票金额
            for(CentralizedCartGoodVO goodVO : businessVO.getGoodsList()){
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
            businessVO.setBillingAmount(billingAmount);
//            totalPrice = totalPrice.add(businessVO.getBillingAmount());
        }
        centralizedCart.setSelectQuantity(totalAmount);
        centralizedCart.setTotalAmount(totalPrice);
        return centralizedCart;
    }
	
}
