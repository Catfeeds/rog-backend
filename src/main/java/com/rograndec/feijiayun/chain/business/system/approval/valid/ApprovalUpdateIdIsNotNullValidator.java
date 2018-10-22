package com.rograndec.feijiayun.chain.business.system.approval.valid;

import com.rograndec.feijiayun.chain.business.system.approval.vo.SaveOrUpdateApprovalFlowDetailVO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SaveOrUpdateApprovalFlowVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class ApprovalUpdateIdIsNotNullValidator implements ConstraintValidator<ApprovalUpdateIdIsNotNull, SaveOrUpdateApprovalFlowVO> {
    private Logger logger = LoggerFactory.getLogger(ApprovalUpdateIdIsNotNullValidator.class);
    @Override
    public void initialize(ApprovalUpdateIdIsNotNull constraintAnnotation) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(SaveOrUpdateApprovalFlowVO value, ConstraintValidatorContext context) {
        try {

            if(null == value.getId() || 0 == value.getId())
                return false;


            List<SaveOrUpdateApprovalFlowDetailVO> approvalFlowDetailPojoList = value.getApprovalFlowDetailDTOS();
            if(CollectionUtils.isEmpty(approvalFlowDetailPojoList))
                return false;

//            for(SaveOrUpdateApprovalFlowDetailVO apo : approvalFlowDetailPojoList){
//                if(null == apo.getId() || 0 == apo.getId()){
//                    return false;
//                }
//            }


        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return true;
    }
}
