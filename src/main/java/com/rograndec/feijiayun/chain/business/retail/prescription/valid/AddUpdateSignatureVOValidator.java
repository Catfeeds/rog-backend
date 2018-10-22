package com.rograndec.feijiayun.chain.business.retail.prescription.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.retail.prescription.exception.PrescriptionBizException;
import com.rograndec.feijiayun.chain.business.retail.prescription.service.PrescriptionRegisterService;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.PrescriptionSignatureDetailVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionSignatureForAddVO;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 商品信息增加的字段校验
 * Created by sunteng on 2017/8/26.
 */
public class AddUpdateSignatureVOValidator implements ConstraintValidator<AddUpdateSignatureValid, ResponsePrescriptionSignatureForAddVO>  {

    private Logger logger = LoggerFactory.getLogger(AddUpdateSignatureVOValidator.class);

    @Autowired
    private ManageConfigComponent manageConfigComponent;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrescriptionRegisterService prescriptionRegisterService;


    @Override
    public void initialize(AddUpdateSignatureValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(ResponsePrescriptionSignatureForAddVO value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        if(value.getUserId() == null){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"员工ID为必填字段");
        }

        Long userId = value.getUserId();

        if(userMapper.getUserById(userVO.getEnterpriseId(),userId) == null){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"用户不存在");
        }
        if(value.getPrescriptionType() == null){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"处方操作为必填字段");
        }

        if(value.getSignatureType() == null){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"签章类型为必填字段");
        }

//        if(value.getPictureIds() == null){
//            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"签章必填字段");
//        }
        if(value.getPrescriptionSignatureDetailVOS() == null || value.getPrescriptionSignatureDetailVOS().isEmpty()){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"签章明细不能为空");
        }else {
            for(PrescriptionSignatureDetailVO detailVO : value.getPrescriptionSignatureDetailVOS()){
                if(detailVO.getPictureId() == null){
                    throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"签章图片必须上传");
                }
            }
        }
        int val2 = prescriptionRegisterService.getCountSignatureByUserId(userId, userVO.getEnterpriseId(),value.getPrescriptionType());
        if(val2 > 0){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"该用户已经绑定在该处方操作类型上，不能再绑定！");

        }
        return true;
    }
}
