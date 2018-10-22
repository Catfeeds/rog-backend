package com.rograndec.feijiayun.chain.business.basic.warehouse.valid;

import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.WarehouseException;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.RequestWarehouseVO;
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
public class AddWarehouseVOValidator implements ConstraintValidator<WarehouseValid, RequestWarehouseVO> {
    private Logger logger = LoggerFactory.getLogger(AddWarehouseVOValidator.class);

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public void initialize(WarehouseValid addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(RequestWarehouseVO value, ConstraintValidatorContext context) throws BaseException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        if(StringUtils.isBlank(value.getName())){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"仓库名称必填");
        }
        if(value.getAreaCodeLength() == null || value.getAreaCodeLength() <= 0){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"库区编码长度必填并且大于0");
        }
        if(value.getCargoAreaCodeLength() == null || value.getCargoAreaCodeLength() <= 0){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"货区编码长度必填并且大于0");
        }
        if(value.getShelfCodeLength() == null || value.getShelfCodeLength() <= 0){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"货位编码长度必填并且大于0");
        }
        if(value.getSpacer() == null){
           value.setSpacer("");
        }
        if(value.getAcreage() == null){
            value.setAcreage(BigDecimal.ZERO);
        }

        Warehouse warehouseByCode = warehouseMapper.getWarehouseByCode(userVO.getEnterpriseId(), value.getCode());
        if(warehouseByCode != null){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"仓库编码重复");
        }
        Warehouse warehouseByName = warehouseMapper.getWarehouseByName(userVO.getEnterpriseId(), value.getName());
        if(warehouseByName != null){
            throw new WarehouseException(WarehouseException.VALUE_CHECK,"仓库名称重复");
        }

        return true;
    }
}
