package com.rograndec.feijiayun.chain.business.system.approval.valid;

import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.business.system.approval.service.ApprovalFlowService;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SaveOrUpdateApprovalFlowVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class ApprovalDetailIsDeleteValidator implements ConstraintValidator<ApprovalDetailIsDelete, SaveOrUpdateApprovalFlowVO> {
    private Logger logger = LoggerFactory.getLogger(ApprovalDetailIsDeleteValidator.class);

    @Autowired
    private ApprovalFlowService approvalFlowService;

    @Override
    public void initialize(ApprovalDetailIsDelete constraintAnnotation) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(SaveOrUpdateApprovalFlowVO value, ConstraintValidatorContext context) {
        try {

            /**
             * 校验是否可以删除
             */
            Map<String, Object> map = approvalFlowService.checkDelete(value.getId());
            if(map.get("status").equals("false")){
                throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_DELETE,map.get("msg").toString());
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
