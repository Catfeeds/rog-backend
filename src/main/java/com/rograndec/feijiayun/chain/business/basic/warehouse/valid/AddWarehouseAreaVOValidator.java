package com.rograndec.feijiayun.chain.business.basic.warehouse.valid;

import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.WarehouseException;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.RequestWarehouseAreaVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Created by sunteng on 2017/8/26.
 */
public class AddWarehouseAreaVOValidator implements ConstraintValidator<WarehouseAreaValid, RequestWarehouseAreaVO> {
    private Logger logger = LoggerFactory.getLogger(AddWarehouseAreaVOValidator.class);

    @Autowired
    private WarehouseAreaMapper warehouseAreaMapper;

    @Override
    public void initialize(WarehouseAreaValid addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(RequestWarehouseAreaVO value, ConstraintValidatorContext context) throws BaseException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        if(StringUtils.isBlank(value.getName())){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"库区名称必填");
        }

        if(value.getAcreage() == null){
            value.setAcreage(BigDecimal.ZERO);
        }

        WarehouseArea warehouseArea = warehouseAreaMapper.getWarehouseAreaByCodeName(userVO.getEnterpriseId(), value.getCode(),null);
        if(warehouseArea != null){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"库区编码重复");
        }
        WarehouseArea warehouseArea1 = warehouseAreaMapper.getWarehouseAreaByCodeName(userVO.getEnterpriseId(), null,value.getName());
        if(warehouseArea1 != null){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"库区名称重复");
        }

        return true;
    }
}
