package com.rograndec.feijiayun.chain.business.goods.price.valid;

import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceAdjustVO;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
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
import java.util.Map;

/**
 * 校验修改时候 审批开始 ,审批失败时才可以修改价格调整
 * Created by zhaiwei on 2017/8/26.
 */
public class PriceAdjustUpdateCheckValidator implements ConstraintValidator<PriceAdjustUpdateCheck, AddOrUpdatePriceAdjustVO> {
    private Logger logger = LoggerFactory.getLogger(PriceAdjustUpdateCheckValidator.class);


    @Autowired
    private ApprovalFlowActionMapper approvalFlowActionMapper;

    @Autowired
    private ApprovalFlowActionDetailMapper approvalFlowActionDetailMapper;

    @Override
    public void initialize(PriceAdjustUpdateCheck priceAdjustUpdateCheck) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(AddOrUpdatePriceAdjustVO value, ConstraintValidatorContext context) {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");

        Long eId = userVO.getParentId();
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            eId = userVO.getEnterpriseId();
        }

        Map<String,Object> map = new HashMap<>();
        map.put("dataId",value.getId());
        map.put("enterpriseId",eId);
        map.put("content","0202");

        ApprovalFlowAction approvalFlowAction = approvalFlowActionMapper.selectByDataId(map);

        if(!approvalFlowAction.getStatusRecom().equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"该调整单的状态,不允许修改");
        }
        return true;
    }


}
