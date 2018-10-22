package com.rograndec.feijiayun.chain.business.storage.inventory.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryForAddVO;
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
public class AddUpdateInventoryOrderVOValidator implements ConstraintValidator<AddUpdateInventoryOrderValid, InventoryForAddVO>  {

    private Logger logger = LoggerFactory.getLogger(AddUpdateInventoryOrderVOValidator.class);

    @Autowired
    private ManageConfigComponent manageConfigComponent;


    @Autowired
    private UserMapper userMapper;


    @Override
    public void initialize(AddUpdateInventoryOrderValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(InventoryForAddVO value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        if(value.getCreaterId() == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"创建盘点单用户ID为必填字段");
        }

        Long userId = value.getCreaterId();

        if(userMapper.getUserByIdAndEnterpriseId(userId,userVO.getEnterpriseId()) == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"用户不存在");
        }


        return true;
    }
}
