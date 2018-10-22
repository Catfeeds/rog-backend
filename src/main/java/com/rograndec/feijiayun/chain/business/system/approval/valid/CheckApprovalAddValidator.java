package com.rograndec.feijiayun.chain.business.system.approval.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SaveOrUpdateApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
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

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class CheckApprovalAddValidator implements ConstraintValidator<CheckApprovalAdd, SaveOrUpdateApprovalFlowVO> {
    private Logger logger = LoggerFactory.getLogger(ApprovalDetailIsDeleteValidator.class);

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Override
    public void initialize(CheckApprovalAdd constraintAnnotation) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(SaveOrUpdateApprovalFlowVO value, ConstraintValidatorContext context) {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());
            Integer chainType = userVO.getChainType();

            if(ChainType.Headquarters.getCode() != chainType){
                EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
                Integer approvalControl = bus.getApprovalControl();
                if(approvalControl != 1){
                    /**
                     * 没有开启审批流程自主控制
                     */
                    throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_DELETE,"没有开启审批流程自主控制,门店或加盟店不能增加审批流程");
                }
            }

        return true;
    }
}
