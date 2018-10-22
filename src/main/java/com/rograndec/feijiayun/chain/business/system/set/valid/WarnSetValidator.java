package com.rograndec.feijiayun.chain.business.system.set.valid;

import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.WarehouseException;
import com.rograndec.feijiayun.chain.business.system.set.dao.WarnSetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.business.system.set.exception.WarnSetBizException;
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
 * 盘点单字段校验
 * Created by sunteng on 2017/8/26.
 */
public class WarnSetValidator implements ConstraintValidator<WarnSetValid, List<WarnSet>>  {

    private Logger logger = LoggerFactory.getLogger(WarnSetValidator.class);

    @Autowired
    private WarnSetMapper warnSetMapper;


    @Override
    public void initialize(WarnSetValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(List<WarnSet> value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        /**
         @Param("setType") Integer setType,
         @Param("enterpriseId") Long enterpriseId,
         @Param("parentId") Long parentId,
         @Param("qualificationId") Long qualificationId,
         @Param("content") String content
         */

        value.forEach(
                v ->{
                    Integer integer = warnSetMapper.checkValid(
                            v.getSetType(),
                            v.getEnterpriseId(),
                            v.getParentId(),
                            v.getQualificationId(),
                            v.getContent()
                    );

                    if(null != integer && integer > 0){

                        throw new WarnSetBizException(WarnSetBizException.VALUE_CHECK,"设置类型，企业id，资质ID，内容重复");
                    }
                }
        );


        return true;
    }
}
