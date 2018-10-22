package com.rograndec.feijiayun.chain.business.purchase.ret.valid;

import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * 校验修改时候 单据未被引用 和审批被驳回时才可以修改价格调整
 * Created by zhaiwei on 2017/8/26.
 */
public class UpdateReturnCheckValidator implements ConstraintValidator<UpdateReturnCheck, PurchaseReturnSaveOrUpdateVO> {
    private Logger logger = LoggerFactory.getLogger(UpdateReturnCheckValidator.class);


    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private PurchaseReturnOutMapper purchaseReturnOutMapper;

    @Override
    public void initialize(UpdateReturnCheck priceAdjustUpdateCheck) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(PurchaseReturnSaveOrUpdateVO value, ConstraintValidatorContext context) {




        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            map.put("enterpriseId",userVO.getEnterpriseId());
        }else {
            map.put("enterpriseId",userVO.getParentId());
        }
        Long returnId = value.getId();
        if(null == returnId || 0 == returnId){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"修改时,购进退出id不存在");
        }

        PurchaseReturnOut purchaseReturnOut = purchaseReturnOutMapper.selectBaseOrderId(returnId);

        PurchaseReturn purchaseReturn = purchaseReturnMapper.selectByPrimaryKey(returnId);

        if(!purchaseReturn.getStatus().equals(PurchaseStatus.AUDIT_WITHDRAWAL.getStatus())
                && null != purchaseReturnOut){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,value.getId()+"正在审核中或者已被关联,无法提交修改");
        }
        return true;
    }


}
