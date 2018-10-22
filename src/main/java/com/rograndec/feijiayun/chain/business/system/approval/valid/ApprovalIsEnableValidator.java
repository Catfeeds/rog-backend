package com.rograndec.feijiayun.chain.business.system.approval.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.business.system.approval.service.ApprovalFlowService;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SaveOrUpdateApprovalFlowVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class ApprovalIsEnableValidator implements ConstraintValidator<ApprovalIsEnable, SaveOrUpdateApprovalFlowVO> {
    private Logger logger = LoggerFactory.getLogger(ApprovalIsEnableValidator.class);

    @Autowired
    private ApprovalFlowService approvalFlowService;

    @Override
    public void initialize(ApprovalIsEnable constraintAnnotation) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(SaveOrUpdateApprovalFlowVO value, ConstraintValidatorContext context) {
        try {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());
            /**
             * 校验是否可以禁用
             */
            // TODO: 2017/8/25 需要校验是否可以禁用
            Map<String, Object> map =  approvalFlowService.checkDisable(value.getId(),userVO);
            if(map.get("status").toString().equals("false")){
                String erroMsg = map.get("msg").toString();
                throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_DELETE,erroMsg);
            }


        } catch (ApprovalFlowBizException e) {
            logger.error(e.getMessage(),e);
            throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_DELETE,e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return true;
    }
}
