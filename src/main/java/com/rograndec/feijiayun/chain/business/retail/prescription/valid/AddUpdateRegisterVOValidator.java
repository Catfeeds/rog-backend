package com.rograndec.feijiayun.chain.business.retail.prescription.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.retail.prescription.exception.PrescriptionBizException;
import com.rograndec.feijiayun.chain.business.retail.prescription.service.PrescriptionRegisterService;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.RequestPrescriptionRegisterDetailForAddUpdateVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.RequestPrescriptionRegisterForAddUpdateVO;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.constant.PrescriptionType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 增加修改处方登记添加校验
 * Created by sunteng on 2017/8/26.
 */
public class AddUpdateRegisterVOValidator implements ConstraintValidator<AddUpdateRegisterValid, RequestPrescriptionRegisterForAddUpdateVO>  {

    private Logger logger = LoggerFactory.getLogger(AddUpdateRegisterVOValidator.class);

    @Autowired
    private ManageConfigComponent manageConfigComponent;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrescriptionRegisterService prescriptionRegisterService;


    @Override
    public void initialize(AddUpdateRegisterValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(RequestPrescriptionRegisterForAddUpdateVO value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        List<RequestPrescriptionRegisterDetailForAddUpdateVO> registerDetailForAddUpdateVOList = value.getRegisterDetailForAddUpdateVOList();

        if(registerDetailForAddUpdateVOList == null || registerDetailForAddUpdateVOList.size() == 0){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"商品明细不能为空");
        }
        if(value.getPrescriptionType() != PrescriptionType.TCM.getCode() && registerDetailForAddUpdateVOList.size() > 5){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"处方登记商品明细不能超过5条");
        }
        return true;
    }
}
