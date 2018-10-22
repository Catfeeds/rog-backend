package com.rograndec.feijiayun.chain.business.goods.price.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceOrderVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.UpdatePriceOrderDetailVO;
import com.rograndec.feijiayun.chain.common.component.SupplierQualificationComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 校验修改时候
 * Created by zhaiwei on 2017/8/26.
 */
public class PriceOrderAddParamCheckValidator implements ConstraintValidator<PriceOrderAddParamCheck, AddOrUpdatePriceOrderVO> {
    private Logger logger = LoggerFactory.getLogger(PriceOrderAddParamCheckValidator.class);


    @Autowired
    private SupplierQualificationComponent supplierQualificationComponent;

    @Autowired
    private UserQualificationConfigMapper userQualificationConfigMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Override
    public void initialize(PriceOrderAddParamCheck addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(AddOrUpdatePriceOrderVO value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value.getCode()) ){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"清单编码不能为空!");
        }

        if(value.getPriceType() == null){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"价格类型不能为空!");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());

        List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS = value.getUpdatePriceOrderDetailVOS();

        if(updatePriceOrderDetailVOS != null){// 新增的时候没有明细

            List<Long> goodsIds = UpdatePriceOrderDetailVO.getGoodsIds(updatePriceOrderDetailVOS);

            List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByIndex(goodsIds
                    ,userVO.getEnterpriseId()
                    ,userVO.getParentId()
                    ,value.getId());

            for(PriceOrderDetail priceOrderDetail : priceOrderDetails){

                throw new PriceOrderBizException(HttpStatus.INTERNAL_SERVER_ERROR.value(),priceOrderDetail.getGoodsId()+"该商品在已经存在价格清单!!");
            }
        }else {


            List<PriceOrder> priceOrders = priceOrderMapper.selectByEnterpriseAndCode(userVO.getEnterpriseId(), userVO.getParentId(), value.getCode());

            if(!CollectionUtils.isEmpty(priceOrders)){
                PriceOrder priceOrder = priceOrders.get(0);
                throw new PriceOrderBizException(HttpStatus.INTERNAL_SERVER_ERROR.value(),priceOrder.getCode()+"该清单编码在已经存在价格清单!!");
            }

            priceOrders = priceOrderMapper.selectByEnterpriseAndName(userVO.getEnterpriseId(), userVO.getParentId(), value.getCode());
            if(!CollectionUtils.isEmpty(priceOrders)){
                PriceOrder priceOrder = priceOrders.get(0);
                throw new PriceOrderBizException(HttpStatus.INTERNAL_SERVER_ERROR.value(),priceOrder.getName()+"该清单名称在已经存在价格清单!!");
            }

        }



        return true;
    }


}
