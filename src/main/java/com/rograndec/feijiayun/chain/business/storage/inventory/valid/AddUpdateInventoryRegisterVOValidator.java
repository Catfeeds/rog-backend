package com.rograndec.feijiayun.chain.business.storage.inventory.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO;
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
 * 盘点单字段校验
 * Created by sunteng on 2017/8/26.
 */
public class AddUpdateInventoryRegisterVOValidator implements ConstraintValidator<AddUpdateInventoryRegisterValid, InventoryForRegisterVO>  {

    private Logger logger = LoggerFactory.getLogger(AddUpdateInventoryRegisterVOValidator.class);

    @Autowired
    private ManageConfigComponent manageConfigComponent;


    @Autowired
    private UserMapper userMapper;


    @Override
    public void initialize(AddUpdateInventoryRegisterValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(InventoryForRegisterVO value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        Long registerManId = value.getRegisterManId();
        if(registerManId == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"登记用户ID为必填字段");
        }
        if(userMapper.getUserByIdAndEnterpriseId(registerManId,userVO.getEnterpriseId()) == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"登记人员不存在");
        }

        Long userId = value.getInvManId();

        if(userId == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"盘点用户ID为必填字段");
        }

        if(userMapper.getUserByIdAndEnterpriseId(userId,userVO.getEnterpriseId()) == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"盘点用户不存在");
        }

        Long secondInvManId = value.getSecondInvManId();

        if(secondInvManId == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"复盘用户ID为必填字段");
        }
        if(userMapper.getUserByIdAndEnterpriseId(secondInvManId,userVO.getEnterpriseId()) == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"复盘人员不存在");
        }

        return true;
    }
}
