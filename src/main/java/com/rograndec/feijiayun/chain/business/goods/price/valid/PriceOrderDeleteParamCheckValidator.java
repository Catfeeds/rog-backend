package com.rograndec.feijiayun.chain.business.goods.price.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.common.component.SupplierQualificationComponent;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 校验修改时候
 * Created by zhaiwei on 2017/8/26.
 */
public class PriceOrderDeleteParamCheckValidator implements ConstraintValidator<PriceOrderDeleteCheck, Long> {
    private Logger logger = LoggerFactory.getLogger(PriceOrderDeleteParamCheckValidator.class);


    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private SupplierQualificationComponent supplierQualificationComponent;

    @Autowired
    private UserQualificationConfigMapper userQualificationConfigMapper;


    @Override
    public void initialize(PriceOrderDeleteCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        List<PriceOrder> parentOrder = priceOrderMapper.selectByParentOrder(value);

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(value);
        if(priceOrder.getSysType().equals(SysType.SYSTEM.getCode())){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,priceOrder.getName()+"为系统价格清单,无法删除!!!");
        }

        for(PriceOrder pc : parentOrder){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,pc.getName()+"已经关联了该清单,无法删除!!!");
        }
        return true;
    }


}
