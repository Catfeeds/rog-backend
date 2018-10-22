package com.rograndec.feijiayun.chain.business.basic.user.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserCoderRule;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserTypeEum;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPersonalMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import com.rograndec.feijiayun.chain.business.basic.user.vo.AddUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserAdministrationVO;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.rogrand.service.RGTService;
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
import javax.validation.ValidationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class QualityControlUserAdminAndCodeCheckValidator implements ConstraintValidator<QualityControl4UserAdminAndCodeCheck, AddUserVO> {
    private Logger logger = LoggerFactory.getLogger(QualityControlUserAdminAndCodeCheckValidator.class);

    @Autowired
    private  ManageConfigComponent manageConfigComponent;

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPersonalMapper userPersonalMapper;

    @Autowired
    private RGTService rgtService;

    Pattern namePattern = Pattern.compile("^([a-zA-Z0-9\\u4e00-\\u9fa5\\·\\.\\(\\)]{1,20})$");
    Pattern loginAacountPattern = Pattern.compile("^[a-zA-Z0-9_\\-]{5,20}$");

    @Override
    public void initialize(QualityControl4UserAdminAndCodeCheck addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(AddUserVO value, ConstraintValidatorContext context) {


        if(null != value.getId() && 0 != value.getId()){
            User user = userMapper.selectByPrimaryKey(value.getId());
            if(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.equals(user.getApproveStatus())
                    || ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.equals(user.getApproveStatus())){
                throw new ValidationException("该用户审批状态处于"+ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(user.getApproveStatus()).getName()+";不允许修改");
            }
        }




        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());


        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO);

        List<Long> userType = value.getUserAdministrationDTO().getUserType();

        if(CollectionUtils.isEmpty(userType)){
            throw new ValidationException("用户类型不能为空");
        }

        if( value.getUserAdministrationDTO().getUserType().contains(Long.parseLong(String.valueOf(UserTypeEum.SYSTEM_USER.getCode())))){
            throw new ValidationException("不允许新增和修改系统管理员");
        }

        /**
         * 总部不能选择pos用户
         */
      /*  if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){

            if(userType.contains(Long.parseLong(String.valueOf(UserTypeEum.POS_USER.getCode())))
                    || userType.contains(Long.parseLong(String.valueOf(UserTypeEum.CLOUD_POS_USER.getCode())))){
                throw new ValidationException("总部不能选择pos用户");
            }
        }*/


        if(CollectionUtils.isEmpty(userType)){
            throw new ValidationException("用户类型不能为空");
        }

        /**
         * 总部不能选择pos用户
         */
       /* if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){

            if(userType.contains(Long.parseLong(String.valueOf(UserTypeEum.POS_USER.getCode())))
                    || userType.contains(Long.parseLong(String.valueOf(UserTypeEum.CLOUD_POS_USER.getCode())))){
                throw new ValidationException("总部不能选择pos用户");
            }
        }*/


        String loginAacount = value.getUserAdministrationDTO().getLoginAacount();


        Matcher m = namePattern.matcher(value.getName());

        if(!m.find())
            throw new ValidationException("用户名格式不正确,只可输入数字,字母,汉字,中文符号·和英文符号. ,1-20位");

        Matcher matcher = loginAacountPattern.matcher(loginAacount);

        if(!matcher.find())
            throw new ValidationException("登录账号格式不正确,只可输入数字,字母,下划线,长度为5-20位");

        if(!StringUtils.isEmpty(loginAacount)){
            if(loginAacount.length() > 20){
                throw new ValidationException("登录账号过长");
            }
        }

        if(null != value.getUserAdministrationDTO().getId() && 0 != value.getUserAdministrationDTO().getId() ){
            UserAdministration dbUserAdmin = userAdministrationMapper.selectByPrimaryKey(value.getUserAdministrationDTO().getId());
            if(!dbUserAdmin.getLoginAccount().trim().equals(value.getUserAdministrationDTO().getLoginAacount().trim())){
                //校验账户在saas系统中是否唯一
                UserAdministration userAdministration = userAdministrationMapper.selectUserAdministrationByAccount(value.getUserAdministrationDTO().getLoginAacount());
                if(null != userAdministration){
                    throw new ValidationException("登录账号存在");
                }
                checkUserLoginAcount(value.getUserAdministrationDTO().getLoginAacount());

            }
        }else {
            //校验账户在saas系统中是否唯一
            UserAdministration userAdministration = userAdministrationMapper.selectUserAdministrationByAccount(value.getUserAdministrationDTO().getLoginAacount());
            if(null != userAdministration){
                throw new ValidationException("登录账号存在");
            }
            checkUserLoginAcount(value.getUserAdministrationDTO().getLoginAacount());
        }



        if(null != value.getUserPersonalDTOS().getId() && value.getUserPersonalDTOS().getId() != 0) {

            UserPersonal dbUserPersonal = userPersonalMapper.selectByPrimaryKey(value.getUserPersonalDTOS().getId());

            if(!dbUserPersonal.getEmail().trim().equals(value.getUserPersonalDTOS().getEmail().trim())){
                //校验邮箱是否存在
                UserPersonal userPersonal = userPersonalMapper.selectByEmail(value.getUserPersonalDTOS().getEmail());
                if (null != userPersonal) {
                    throw new ValidationException("邮箱存在");
                }
                checkUserEmail(value.getUserPersonalDTOS().getEmail());

            }


            if(!dbUserPersonal.getMobilePhone().trim().equals(value.getUserPersonalDTOS().getMobilePhone().trim())) {
                //校验手机号是否存在
                UserPersonal userPersonal = userPersonalMapper.selectByMobilePhone(value.getUserPersonalDTOS().getMobilePhone());
                if (null != userPersonal) {
                    throw new ValidationException("手机号存在");
                }
                checkUserPhone(value.getUserPersonalDTOS().getMobilePhone());
            }

        }else {
            //校验邮箱是否存在
            UserPersonal userPersonal = userPersonalMapper.selectByEmail(value.getUserPersonalDTOS().getEmail());
            if (null != userPersonal) {
                throw new ValidationException("邮箱存在");
            }
            checkUserEmail(value.getUserPersonalDTOS().getEmail());

            //校验手机号是否存在
            userPersonal = userPersonalMapper.selectByMobilePhone(value.getUserPersonalDTOS().getMobilePhone());
            if (null != userPersonal) {
                throw new ValidationException("手机号存在");
            }
            checkUserPhone(value.getUserPersonalDTOS().getMobilePhone());

        }

        UserAdministrationVO administrationVO = value.getUserAdministrationDTO();

        /**
         * 毕业时间
         */
        Date graduationTime = administrationVO.getGraduationTime();

        /**
         * 参加工作时间
         */
        Date workingHours = administrationVO.getWorkingHours();

        if(null != graduationTime && null != workingHours && workingHours.getTime() < graduationTime.getTime()){
            /**
             * 毕业时间不为空,并且参加工作时间小于毕业时间
             */

            throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"工作时间不能小于毕业时间");
        }


        /**
         * 如果基础数据质量控制关闭则不需要校验
         */
        if(manageConfig.getQualityControl().equals(EnableStatus.ENABLE.getStatus())){


            /**
             * 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
             */
            List<Long> limitVarietys = administrationVO.getLimitVariety();

            /**
             * 受限品种范围ID集合
             */
            List<Long> limitVarietyRange = administrationVO.getLimitVarietyRange();

            /**
             * 毕业院校
             */
            String graduationUniversity = administrationVO.getGraduationUniversity();

            /**
             * 学历ID
             */
            Long educationId = administrationVO.getEducationId();

            /**
             * 专业
             */
            Long majorId = administrationVO.getMajorId();

            if(CollectionUtils.isEmpty(limitVarietys)){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"受限品种集合不能为空");
            }

            if(CollectionUtils.isEmpty(limitVarietyRange)){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"受限品种范围ID集合不能为空");
            }

            if(StringUtils.isEmpty(graduationUniversity)){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"毕业院校不能为空不能为空");
            }
            if(null == educationId || educationId == 0){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"学历ID不能为空");
            }
            if(null == majorId || majorId == 0){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"专业不能为空");
            }
            if(null == workingHours ){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"参加工作时间不能为空");
            }

        }

        Integer userCodeRule = manageConfig.getUserCodeRule();
        if(userCodeRule.equals(UserCoderRule.CUSTOM_NUM.getCode())){
            if(StringUtils.isEmpty(value.getCode())){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"自定义用户编码不能为空");
            }
        }

        return true;
    }

    private void checkUserLoginAcount(String loginAccount){
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("username",loginAccount);
        rgtService.validateRGTUserName(userMap);
    }

    private void checkUserEmail(String email){
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("email",email);
        rgtService.validateRGTEmail(userMap);
    }

    private void checkUserPhone(String phone){
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("mobile",phone);
        rgtService.validateRGTMobile(userMap);
    }
}
