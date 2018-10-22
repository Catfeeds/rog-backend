package com.rograndec.feijiayun.chain.business.goods.price.valid;

import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceOrderVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.UpdatePriceOrderDetailVO;
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
public class PriceOrderUpdateParamCheckValidator implements ConstraintValidator<PriceOrderUpdateParamCheck, AddOrUpdatePriceOrderVO> {
    private Logger logger = LoggerFactory.getLogger(PriceOrderUpdateParamCheckValidator.class);

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Override
    public void initialize(PriceOrderUpdateParamCheck addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(AddOrUpdatePriceOrderVO value, ConstraintValidatorContext context) {
        if(value.getId() == null){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"修改必须传递清单头id");
        }

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(value.getId());
//        if(priceOrder.getSysType().equals(SysType.SYSTEM.getCode())){
//            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,priceOrder.getName()+"为系统价格清单,无法修改!!!");
//        }

        List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS = value.getUpdatePriceOrderDetailVOS();
        for(UpdatePriceOrderDetailVO uv : updatePriceOrderDetailVOS){
            if(uv.getId() == null){
                throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"修改必须传递清单明细id");
            }
        }

     /*   HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());*/


       /* List<Long> goodsIds = UpdatePriceOrderDetailVO.getGoodsIds(updatePriceOrderDetailVOS);

        List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, userVO.getEnterpriseId());

        for(PriceOrderDetail priceOrderDetail : priceOrderDetails){

            throw new PriceOrderBizException(HttpStatus.INTERNAL_SERVER_ERROR.value(),priceOrderDetail.getGoodsId()+"该商品在已经存在价格清单!!");
        }*/
        return true;
    }


}
