package com.rograndec.feijiayun.chain.common.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.retail.prescription.exception.PrescriptionBizException;
import com.rograndec.feijiayun.chain.business.retail.prescription.service.PrescriptionRegisterService;
import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
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
 *  一般请求参数字段校验
 * Created by sunteng on 2017/8/26.
 */
public class RequestParamValidator implements ConstraintValidator<RequestParamValid, RequestBaseParamVO>  {

    private Logger logger = LoggerFactory.getLogger(RequestParamValidator.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrescriptionRegisterService prescriptionRegisterService;


    @Override
    public void initialize(RequestParamValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(RequestBaseParamVO value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Integer pageNo = value.getPageNo();
        Integer pageSize = value.getPageSize();
        if(pageNo == null || pageSize == null){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"分页数据为必填字段");
        }

        if(pageNo <= 0 || pageSize <= 0){
            throw new PrescriptionBizException(PrescriptionBizException.VALUE_CHECK,"页码或者每页显示数量必须大于0");
        }

        return true;
    }
}
