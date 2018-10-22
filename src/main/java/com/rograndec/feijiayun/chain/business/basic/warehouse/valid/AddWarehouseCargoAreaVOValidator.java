package com.rograndec.feijiayun.chain.business.basic.warehouse.valid;

import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.WarehouseException;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.RequestWarehouseCargoAreaVO;
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
 * Created by sunteng on 2017/8/26.
 */
public class AddWarehouseCargoAreaVOValidator implements ConstraintValidator<WarehouseCargoAreaValid, RequestWarehouseCargoAreaVO> {
    private Logger logger = LoggerFactory.getLogger(AddWarehouseCargoAreaVOValidator.class);

    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

    @Override
    public void initialize(WarehouseCargoAreaValid addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(RequestWarehouseCargoAreaVO value, ConstraintValidatorContext context) throws BaseException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");



        WarehouseCargoArea warehouseCargoArea = warehouseCargoAreaMapper.getWarehouseCargoAreaByCodeName(userVO.getEnterpriseId(), value.getCode(),null);
        if(warehouseCargoArea != null){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"货区编码重复");
        }
        WarehouseCargoArea warehouseCargoArea1 = warehouseCargoAreaMapper.getWarehouseCargoAreaByCodeName(userVO.getEnterpriseId(), null,value.getName());
        if(warehouseCargoArea1 != null){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"货区名称重复");
        }

        return true;
    }
}
