package com.rograndec.feijiayun.chain.business.goods.price.valid;

import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceAdjustMapper;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdateAdjustDetailVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceAdjustVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustReturnVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
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
import java.util.List;
import java.util.Map;

/**
 * 校验修改时候 审批开始 ,审批失败时才可以修改价格调整
 * Created by zhaiwei on 2017/8/26.
 */
public class PriceAdjustApprovalApplyCheckValidator implements ConstraintValidator<PriceAdjustApprovalApplyCheck, AddOrUpdatePriceAdjustVO> {
    private Logger logger = LoggerFactory.getLogger(PriceAdjustApprovalApplyCheckValidator.class);


    @Autowired
    private PriceAdjustMapper priceAdjustMapper;

    @Override
    public void initialize(PriceAdjustApprovalApplyCheck priceAdjustUpdateCheck) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(AddOrUpdatePriceAdjustVO value, ConstraintValidatorContext context) {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");

        Map<String,Object> map = new HashMap<>();
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            map.put("enterpriseId",userVO.getEnterpriseId());
        }else {
            map.put("enterpriseId",userVO.getParentId());
        }
        List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS = value.getAddOrUpdateAdjustDetailVOS();
        List<Long> goodsIds = AddOrUpdateAdjustDetailVO.getGoodsIds(addOrUpdateAdjustDetailVOS);
        map.put("goosIds",goodsIds);
        List<PriceAdjustReturnVO> priceAdjusts = priceAdjustMapper.selectByApproval(map);
        for(PriceAdjustReturnVO pr : priceAdjusts){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,pr.getPriceOrderName()+"正在审核中,无法提交");
        }
        return true;
    }


}
