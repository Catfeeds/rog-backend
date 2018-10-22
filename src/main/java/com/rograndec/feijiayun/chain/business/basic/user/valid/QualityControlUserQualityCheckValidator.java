package com.rograndec.feijiayun.chain.business.basic.user.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserQualificationConfig;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import com.rograndec.feijiayun.chain.business.basic.user.vo.AddUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserAdministrationVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.UserQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.common.component.SupplierQualificationComponent;
import com.rograndec.feijiayun.chain.common.constant.MustInput;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class QualityControlUserQualityCheckValidator implements ConstraintValidator<QualityControl4UserQualityCheck, AddUserVO> {
    private Logger logger = LoggerFactory.getLogger(QualityControlUserQualityCheckValidator.class);


    @Autowired
    private SupplierQualificationComponent supplierQualificationComponent;

    @Autowired
    private UserQualificationConfigMapper userQualificationConfigMapper;

    @Autowired
    private UserQualificationMapper userQualificationMapper;

    @Override
    public void initialize(QualityControl4UserQualityCheck addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(AddUserVO value, ConstraintValidatorContext context) {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        Map<String,Object> map = new HashMap<>();

     List<UserQualificationConfigVO> userQualificationConfigVOS = value.getUserQualificationConfigDTOS();

 /*
        if(CollectionUtils.isEmpty(userQualificationConfigVOS)){
            throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填项资质信息,不能为空");
        }*/

        UserAdministrationVO userAdministrationDTO = value.getUserAdministrationDTO();

        if(CollectionUtils.isEmpty(userAdministrationDTO.getPositionIds())){
            throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"岗位未选择!!!");
        }

        String stringAppendSymbol = StringSplit.StringAppendSymbol(userAdministrationDTO.getPositionIds());
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("pIds",stringAppendSymbol);

        List<UserQualification> userQualifications = userQualificationMapper.selectTypeMustByEnterpriseId(map);

        Map<Long,Integer> mustUserQualificationCheckMap = new HashMap<>();
        for(UserQualification userQualification : userQualifications){
            mustUserQualificationCheckMap.put(userQualification.getId(),0);
        }



        List<Long> deleteIds = value.getDeleteUserQualificationConfigIds();


        if(!CollectionUtils.isEmpty(deleteIds)) {
            List<UserQualificationConfig> userQualificationConfigs4TypeMust = userQualificationConfigMapper.selectByTypeMust(deleteIds);
            if(!CollectionUtils.isEmpty(userQualificationConfigs4TypeMust)){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"必填资质信息,不能删除!");
            }
        }

        if(!CollectionUtils.isEmpty(userQualificationConfigVOS)){

            for(UserQualificationConfigVO uv : userQualificationConfigVOS){
                mustUserQualificationCheckMap.put(uv.getQualificationId(),1);
            }

            Integer isMust = 1;

            for(Map.Entry<Long,Integer> entry : mustUserQualificationCheckMap.entrySet()){
                isMust = isMust | entry.getValue();
            }

            if(isMust == 0){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填项资质信息,未添加");
            }
            if(CollectionUtils.isEmpty(userQualifications)){
                return true;
            }


            for(UserQualification userQualification : userQualifications){
                for(UserQualificationConfigVO userQualificationConfigVO : userQualificationConfigVOS){
                    if(userQualification.getId().equals(userQualificationConfigVO.getQualificationId())){
                        /**
                         * 判断等级是否必填
                         */
                        Integer gradeMust = userQualification.getGradeMust();
                        //是必填
                        if(gradeMust.equals(MustInput.MUST.getCode())){
                            if(StringUtils.isEmpty(userQualificationConfigVO.getGrade())){
                                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填资质信息,等级信息必须填入!");
                            }
                        }
                        /**
                         * 资格证书是否必填
                         */
                        Integer codeMust = userQualification.getCodeMust();

                        if(codeMust.equals(MustInput.MUST.getCode())){
                            if(StringUtils.isEmpty(userQualificationConfigVO.getCode())){
                                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填资质信息,资格证书信息必须填入!");
                            }
                        }
                        /**
                         * 注册证书号是否必填
                         */
                        Integer registerCodeMust = userQualification.getRegisterCodeMust();

                        if(registerCodeMust.equals(MustInput.MUST.getCode())){
                            if(StringUtils.isEmpty(userQualificationConfigVO.getRegisterCode())){
                                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填资质信息,注册证书号信息必须填入!");
                            }
                        }

                        /**
                         * 适用地区是否必填
                         */
                        Integer regionMust = userQualification.getRegionMust();

                        if(regionMust.equals(MustInput.MUST.getCode())){
                            if(StringUtils.isEmpty(userQualificationConfigVO.getRegion())){
                                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填资质信息,适用地区信息必须填入!");
                            }
                        }

                        /**
                         * 适用类别是否必填
                         */
                        Integer categoryMust = userQualification.getCategoryMust();

                        if(categoryMust.equals(MustInput.MUST.getCode())){
                            if(StringUtils.isEmpty(userQualificationConfigVO.getCategory())){
                                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填资质信息,适用类别信息必须填入!");
                            }
                        }

                        /**
                         * 适用范围是否必填
                         */
                        Integer rangeMust = userQualification.getRangeMust();

                        if(rangeMust.equals(MustInput.MUST.getCode())){
                            if(StringUtils.isEmpty(userQualificationConfigVO.getRange())){
                                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填资质信息,适用范围信息必须填入!");
                            }
                        }

                        /**
                         * 附件是否必填
                         */
                        Integer fileMust = userQualification.getFileMust();

                        if(fileMust.equals(MustInput.MUST.getCode())){
                            if(null == userQualificationConfigVO.getFileId()){
                                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"有必填资质信息,附件信息必须填入!");
                            }
                        }
                    }
                }
            }


        }

        return true;
    }


}
