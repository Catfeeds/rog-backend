package com.rograndec.feijiayun.chain.business.auth.register.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.auth.register.dao.RegisterAuditMapper;
import com.rograndec.feijiayun.chain.business.auth.register.dao.RegisterMapper;
import com.rograndec.feijiayun.chain.business.auth.register.entity.Register;
import com.rograndec.feijiayun.chain.business.auth.register.entity.RegisterAudit;
import com.rograndec.feijiayun.chain.business.auth.register.exception.RegisterBizException;
import com.rograndec.feijiayun.chain.business.auth.register.service.RegisterService;
import com.rograndec.feijiayun.chain.business.auth.register.vo.CheckRegisterVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseNotBindVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterUserVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.transfer.RegisterParamVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.*;
import com.rograndec.feijiayun.chain.business.basic.user.entity.*;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.GoodsManageMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.init.service.SysDataInitService;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.LocationMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseQualificationConfig;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Location;
import com.rograndec.feijiayun.chain.business.system.enterprise.exception.EnterpriseBizException;
import com.rograndec.feijiayun.chain.business.system.set.dao.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.component.EnterpriseComponent;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.component.SupplierComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.GoodsManageStatus;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.rogrand.service.RGTService;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private EnterpriseQualificationConfigMapper enterpriseQualificationConfigMapper;

    @Autowired
    private UserQualificationConfigMapper userQualificationConfigMapper;

    @Autowired
    private UserQualificationMapper userQualificationMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private CodeComponent codeComponent;

    @Autowired
    private NationMapper nationMapper;

    @Autowired
    private UserPersonalMapper userPersonalMapper;

    @Autowired
    private EnterpriseQualificationMapper enterpriseQualificationMapper;


    @Autowired
    private SysDataInitService sysDataInitService;

    @Autowired
    private RGTService rgtService;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private EnterpriseComponent enterpriseComponent;

    @Autowired
    private GoodsManageMapper goodsManageMapper;

    @Autowired
    private RegisterAuditMapper registerAuditMapper;

    @Autowired
    private SysRoleActionMapper sysRoleActionMapper;

    @Autowired
    private SysActionMapper sysActionMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private BusinessScopeMapper businessScopeMapper;

    @Autowired
    private UserDeptMapper userDeptMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private UserPositionMapper userPositionMapper;

    @Autowired
    private SupplierComponent supplierComponent;

    @Autowired
    private FinanceComponent financeComponent;

    /**
     * 注册并初始化 员工信息
     * @param registerVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User registerUser(RegisterUserVO registerVO,Boolean isRegist) throws Exception {

        UUID uuid = UUID.randomUUID();
        /**
         * 初始化注册企业用户信息
         */
        String registerUserName = registerVO.getUserName();
        User registerUser = User.initEnterpriseUser(
                registerUserName
                , uuid.toString()
                , null
        );

        Register rg = checkRegisterUserSaveOrUpdate(registerVO);
        if(null == rg) {


            userMapper.insertSelective(registerUser);


            Map<String,Object> map = new HashMap<>();
            map.put("username",registerVO.getLoginAccount());
            map.put("mobile",registerVO.getMobilePhone());
            map.put("email",registerVO.getEmail());
            map.put("password",registerVO.getPassword());
            map.put("eId",0);
            map.put("userType",1);
            map.put("staffType",0);

            /**
             * 获得融贯通
             */

            Integer registerRGTUserId = rgtService.registerRGTUser(map, registerUser.getId());
            registerUser.setRgtUserId(registerRGTUserId);

            RegisterParamVO registerParamVO = new RegisterParamVO();
            registerParamVO.setUser(registerUser);
            registerParamVO.setRegisterUserVO(registerVO);

            Register register = Register.getRegister2User(registerParamVO);

            registerMapper.insertSelective(register);
        }else {

            Long userId = rg.getUserId();

            User user = userMapper.selectByPrimaryKey(userId);

            if(user == null){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"用户不存在");
            }

            registerUser.setId(userId);
            RegisterParamVO registerParamVO = new RegisterParamVO();
            registerParamVO.setUser(registerUser);
            registerParamVO.setRegisterUserVO(registerVO);

            Register register = Register.getRegister2User(registerParamVO);

            registerUser.setCreateTime(null);
            registerUser.setCreaterName(null);
            registerUser.setCreaterCode(null);
            registerUser.setCreaterId(null);
            userMapper.updateByPrimaryKeySelective(registerUser);

            register.setId(rg.getId());
            registerMapper.updateByPrimaryKeySelective(register);

            registerUser.setRgtUserId(user.getRgtUserId());
        }



        return registerUser;
    }

    /**
     * 返回true是新增,false是修改
     * @param registerVO
     * @return
     */
    private Register checkRegisterUserSaveOrUpdate(RegisterUserVO registerVO){

        Long id = registerVO.getId();

        if(null == id){
            return null;
        }

        Register register = registerMapper.selectByPrimaryKey(id);

        if(null == register){

            return null;
        }

        User user = userMapper.selectByPrimaryKey(register.getUserId());

        if(null == user){
            throw new RegisterBizException(RegisterBizException.VALUE_CHECK,"用户找不到");
        }

        return register;
    }

    /**
     "账号是否可用("
         "存在并且没有和融贯通关联则表示可用,"
         "不存在表示可用,"
         "存在但是和融贯通关联则表示不可用,"
         "存在并且企业id为0时可用
     )"
     * @param loginAccount
     * @return
     */
    @Override
    public CheckRegisterVO checkUser(String loginAccount) {


        CheckRegisterVO checkRegisterVO = new CheckRegisterVO();

        Register register = registerMapper.selectByLoginAccount(loginAccount);

        UserAdministration userAdministration = userAdministrationMapper.selectUserAdministrationByAccount(loginAccount);

        if(null != register){
            register.setPasswordConfirm(null);
            register.setPassword(null);
        }

        /**
         * 表示没有注册过
         */
        if(register == null && userAdministration == null){

            checkRegisterVO.setEnable(true);

            return checkRegisterVO;
        }


        User user = null;

        if(null != register){

            user = userMapper.selectByPrimaryKey(register.getUserId());

        }else if(null != userAdministration){
            user = userMapper.selectByPrimaryKey(userAdministration.getUserId());
        }


        /**
         * 表示注册过,但是账户没有和融贯通同步成功
         */
      /*  if(null == user.getRgtUserId()){
            checkRegisterVO.setEnable(true);
            checkRegisterVO.setRegister(register);
            return checkRegisterVO;
        }*/

        /**
         * 表示注册过,账户和融贯通同步成功,但是企业注册失败 (企业必须是总部)
         */

        if(user.getEnterpriseId() == 0L){
            checkRegisterVO.setEnable(true);
            checkRegisterVO.setRegister(register);
            return checkRegisterVO;
        }


        checkRegisterVO.setEnable(false);
        return checkRegisterVO;

    }

    /**
     * 注册并初始化 企业信息
     * saas_enterprise  saas_enterprise_business saas_user saas_user_administration
     saas_enterprise_qualification_config
     * @param registerVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Enterprise registerEnterprise(RegisterEnterpriseVO registerVO,Boolean isSynchronize) throws Exception {

        if(!registerVO.getChainType().equals(ChainType.Headquarters.getCode()) && ( registerVO.getParentId() == null || 0 == registerVO.getParentId())){
            throw new EnterpriseBizException("非总部,父级企业不能为空",EnterpriseBizException.NOT_ENTERPRISE);
        }

        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("eLicenseNo",registerVO.getBusinessFileCode());
        /**
         * 校验营业执照编号是否重复,true为已存在,false为不存在
         */
//        Boolean aBoolean = rgtService.checkELicenseNo(objectMap);
//        if(aBoolean){
//            throw new EnterpriseBizException("营业执照已存在",EnterpriseBizException.NOT_ENTERPRISE);
//        }


        List<Integer> locationIds = RegisterEnterpriseVO.getLocationIds(registerVO);

        List<Location> locations = locationMapper.selectByIds(locationIds);


        List<BusinessScope> businessScopes = businessScopeMapper.selectByDefult();


        String s = null;
        if(!CollectionUtils.isEmpty(businessScopes)){
            List<Long> ids = BusinessScope.getIds(businessScopes);
            s = StringSplit.StringAppendSymbol(ids);
        }

        /**
         * 初始化企业信息
         */
        Enterprise enterprise = Enterprise.initEnterprise(registerVO, locations,s);

        /**
         * 审批状态统已审批
         */
        enterprise.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());

        enterpriseMapper.insertSelective(enterprise);

        if(ChainType.Headquarters.getCode() == registerVO.getChainType()){

            enterprise.setCode("0000");

        }else {
            String enterpriseCode = codeComponent.generate(USER_CODE_ENTERPRISE_NAME,USER_CODE_LENGTH,enterprise.getParentId());

            enterprise.setCode(enterpriseCode);
        }


        Enterprise newEnterprise = new Enterprise();
        newEnterprise.setId(enterprise.getId());
        newEnterprise.setCode(enterprise.getCode());
        enterpriseMapper.updateByPrimaryKeySelective(enterprise);

        /**
         * 回填用户基本信息和个人信息
         */
        User rUser = userMapper.selectByPrimaryKey(registerVO.getUserId());
        if(null == rUser){
            throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"用户不存在");
        }
        User newRuser = new User();
        newRuser.setId(rUser.getId());
        newRuser.setEnterpriseId(enterprise.getId());
        newRuser.setParentId(enterprise.getParentId());
        String registerUserCode = codeComponent.generate(USER_CODE_USER_NAME,USER_CODE_LENGTH,enterprise.getEnterpriseId());
        newRuser.setCode(registerUserCode);
        /**
         * 注册完企业,员工信息设置为已审批
         */
        newRuser.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
        userMapper.updateByPrimaryKeySelective(newRuser);

        rUser.setEnterpriseId(enterprise.getId());
        rUser.setParentId(enterprise.getParentId());
        rUser.setCode(registerUserCode);



        /**
         * 初始化企业负责人 , 法定代表人,质量负责人
         */
        List<User> users = initEnterpriseUser(registerVO, enterprise);
        for(User user : users){

            userMapper.insertSelective(user);
        }

        /**
         * 设置企业基本信息中企业负责人 , 法定代表人,质量负责人的id和code
         * 初始化企业负责人 , 法定代表人,质量负责人时返回时是顺序插入企业负责人 , 法定代表人,质量负责人,所以顺序出来就行
         */
        /**
         * 企业负责人
         */
        User chargeMan = users.get(0);
        /**
         * 法定负责人
         */
        User legalMan = users.get(1);
        /**
         * 质量负责人
         */
        User qualityOfficer = users.get(2);

        Enterprise ent = Enterprise.initAfterSetUser(chargeMan, legalMan, qualityOfficer);
        ent.setId(enterprise.getId());
        enterpriseMapper.updateByPrimaryKeySelective(ent);




        RegisterParamVO registerParamVO = new RegisterParamVO();
        registerParamVO.setRegisterEnterpriseVO(registerVO);
        registerParamVO.setLocations(locations);

        Register register = registerMapper.selectByUserId(rUser.getId());

        Register newRegister = Register.getRegister2Enterprise(registerParamVO);
        newRegister.setId(register.getId());
        registerMapper.updateByPrimaryKeySelective(newRegister);

        /**
         * 查询最新数据的register 修改newRegister 时 只set了相对应的值 有的值并未set 所以需要重新查询一次register
         */
        register = registerMapper.selectByUserId(rUser.getId());


        /**
         * 初始化用户个人信息
         * saas_user_administration
         */
        UserAdministration userAdministration = UserAdministration.initUserAdministration(
                register
                , rUser
        );

        userAdministration.setLimitVariety(enterprise.getBusinessVariety());
        userAdministration.setLimitVarietyRange(enterprise.getBusinessScopeId());



        /**
         * 初始化员工-个人信息子表
         * saas_user_personal
         *
         * 汉族code 为01
         */
        Nation nation = nationMapper.selectByCode("01");
        UserPersonal userPersonal = UserPersonal.initUserPersonal(
                register
                , rUser
                , nation
        );
        userPersonalMapper.insertSelective(userPersonal);


        /**
         * 初始化默认资质和保存填入的资质信息
         * saas_enterprise_qualification_config
         */
        List<String> list = new ArrayList<>();
        /**
         * 01 : 营业制造
         * 06 : 药品经营许可证
         * 07 : 药品经营质量管理规范认证证书
         */
        list.add("01");
        list.add("06");
        list.add("07");
        List<EnterpriseQualification> defaultEnterpriseQulifications = enterpriseQualificationMapper.getEnterpriseQulificationByCodes(list);

        List<EnterpriseQualificationConfig> enterpriseQualificationConfigs = EnterpriseQualificationConfig.initEnterpriseQualificationConfigs(
                register
                ,enterprise
                ,defaultEnterpriseQulifications
        );

        enterpriseQualificationConfigs.stream().forEach( sqf ->
                enterpriseQualificationConfigMapper.insertSelective(sqf)
        );

        /**
         * 初始化角色管理信息
         * saas_user_role
         *
         * 090101 信息管理经理
         */
        SysRole sysRole = sysRoleMapper.selectDefRoleByCode("090101");
        UserRole userRole = UserRole.getUserRole(enterprise, rUser, sysRole);

        userRoleMapper.insertSelective(userRole);

        userAdministration.setRoleIds(sysRole.getId().toString());
        userAdministration.setDeptIds(sysRole.getDeptId().toString());
        userAdministration.setPositionIds(sysRole.getPositionId().toString());
        userAdministrationMapper.insertSelective(userAdministration);

        UserVO userVO = UserVO.getUserVO(enterprise, rUser, userAdministration);

        /**
         * 设置注册用户的部门和岗位
         */
        UserDept dept = UserDept.getUserUserDept4UserDeptVO(sysRole.getDeptId(),userVO,rUser,true);
        userDeptMapper.insertSelective(dept);

        UserPosition userPosition = UserPosition.getUserPosition4VO(sysRole.getPositionId(), userVO, rUser, true);
        userPositionMapper.insertSelective(userPosition);


        List<SysRole> roleList = new ArrayList<>();
        roleList.add(sysRole);

        // 角色菜单、权限设置
        List<SysAction> sysActions = sysActionMapper.selectByEnterpriseId(0L);
        initSysRoleAction(rUser,enterprise, roleList, sysActions);


        /**
         * 企业负责人
         */
        SysRole chargeManSysRole = sysRoleMapper.selectDefRoleByCode("010201");
        UserRole cur = UserRole.getUserRole(enterprise, chargeMan, chargeManSysRole);
        userRoleMapper.insertSelective(cur);
        UserDept chargeManDept = UserDept.getUserUserDept4UserDeptVO(chargeManSysRole.getDeptId(),userVO,chargeMan,true);
        userDeptMapper.insertSelective(chargeManDept);
        UserPosition chargeManDeptPosition = UserPosition.getUserPosition4VO(chargeManSysRole.getPositionId(), userVO, chargeMan, true);
        userPositionMapper.insertSelective(chargeManDeptPosition);
        /**
         * 法定负责人
         */
        SysRole legalManSysRole = sysRoleMapper.selectDefRoleByCode("010101");
        UserRole lur = UserRole.getUserRole(enterprise, legalMan, legalManSysRole);
        userRoleMapper.insertSelective(lur);
        UserDept legalManSysDept = UserDept.getUserUserDept4UserDeptVO(legalManSysRole.getDeptId(),userVO,legalMan,true);
        userDeptMapper.insertSelective(legalManSysDept);
        UserPosition legalManSysPosition = UserPosition.getUserPosition4VO(legalManSysRole.getPositionId(), userVO, legalMan, true);
        userPositionMapper.insertSelective(legalManSysPosition);
        /**
         * 质量负责人
         */
        SysRole qualityOfficerSysRole = sysRoleMapper.selectDefRoleByCode("030101");
        UserRole qur = UserRole.getUserRole(enterprise, qualityOfficer, qualityOfficerSysRole);
        userRoleMapper.insertSelective(qur);
        UserDept qualityOfficerSysDept = UserDept.getUserUserDept4UserDeptVO(qualityOfficerSysRole.getDeptId(),userVO,qualityOfficer,true);
        userDeptMapper.insertSelective(qualityOfficerSysDept);
        UserPosition qualityOfficerPosition = UserPosition.getUserPosition4VO(qualityOfficerSysRole.getPositionId(), userVO, qualityOfficer, true);
        userPositionMapper.insertSelective(qualityOfficerPosition);

        /**
         * 初始化用户个人信息
         * saas_user_administration
         */

        UserAdministration chargeManAdministration = UserAdministration.initOtherUserAdministration(
                chargeMan
        );

        chargeManAdministration.setLimitVariety(enterprise.getBusinessVariety());
        chargeManAdministration.setLimitVarietyRange(enterprise.getBusinessScopeId());
        chargeManAdministration.setRoleIds(chargeManSysRole.getId().toString());
        chargeManAdministration.setDeptIds(chargeManSysRole.getDeptId().toString());
        chargeManAdministration.setPositionIds(chargeManSysRole.getPositionId().toString());
        userAdministrationMapper.insertSelective(chargeManAdministration);

        UserAdministration legalManAdministration = UserAdministration.initOtherUserAdministration(
                legalMan
        );

        legalManAdministration.setLimitVariety(enterprise.getBusinessVariety());
        legalManAdministration.setLimitVarietyRange(enterprise.getBusinessScopeId());
        legalManAdministration.setRoleIds(legalManSysRole.getId().toString());
        legalManAdministration.setDeptIds(legalManSysRole.getDeptId().toString());
        legalManAdministration.setPositionIds(legalManSysRole.getPositionId().toString());
        userAdministrationMapper.insertSelective(legalManAdministration);

        UserAdministration qualityOfficerAdministration = UserAdministration.initOtherUserAdministration(
                qualityOfficer
        );

        qualityOfficerAdministration.setLimitVariety(enterprise.getBusinessVariety());
        qualityOfficerAdministration.setLimitVarietyRange(enterprise.getBusinessScopeId());
        qualityOfficerAdministration.setRoleIds(qualityOfficerSysRole.getId().toString());
        qualityOfficerAdministration.setDeptIds(qualityOfficerSysRole.getDeptId().toString());
        qualityOfficerAdministration.setPositionIds(qualityOfficerSysRole.getPositionId().toString());
        userAdministrationMapper.insertSelective(qualityOfficerAdministration);


        /**
         * 初始化员工-个人信息子表
         * saas_user_personal
         *
         * 汉族code 为01
         */
        UserPersonal chargeManrPersonal = UserPersonal.initUserPersonal(
                chargeMan
                , nation
                ,chargeManAdministration.getLoginAccount()
        );
        userPersonalMapper.insertSelective(chargeManrPersonal);

        UserPersonal legalManManrPersonal = UserPersonal.initUserPersonal(
                legalMan
                , nation
                ,legalManAdministration.getLoginAccount()
        );
        userPersonalMapper.insertSelective(legalManManrPersonal);

        UserPersonal qualityOfficerPersonal = UserPersonal.initUserPersonal(
                qualityOfficer
                , nation
                ,qualityOfficerAdministration.getLoginAccount()
        );
        userPersonalMapper.insertSelective(qualityOfficerPersonal);

        /**
         * 生成质量控制人的员工默认资质
         */
        UserQualification userQualification = userQualificationMapper.selectByDefCode("01");

        UserQualificationConfig userQualificationConfig = UserQualificationConfig.getUserQualificationConfig4Qualification(userQualification, userVO, qualityOfficer);

        userQualificationConfigMapper.insertSelective(userQualificationConfig);


        /**
         * 初始化企业业务表
         */

        EnterpriseBusiness enterpriseBusiness = EnterpriseBusiness.initEnterpriseBusiness(enterprise);

        //如果不是总部需要取总部的相关信息赋值
        if(registerVO.getChainType() != ChainType.Headquarters.getCode()){
            EnterpriseBusiness busParent = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(registerVO.getParentId());
            if(null != busParent){
                enterpriseBusiness.setSettlementMode(busParent.getSettlementMode());
                enterpriseBusiness.setDistrPriceOrderId(busParent.getDistrPriceOrderId());
                enterpriseBusiness.setDistrPriceOrderName(busParent.getDistrPriceOrderName());
                enterpriseBusiness.setPaymentProvision(busParent.getPaymentProvision());
                enterpriseBusiness.setPaymentPeriod(busParent.getPaymentPeriod());
                enterpriseBusiness.setPaymentPeriodUnit(busParent.getPaymentPeriodUnit());
                enterpriseBusiness.setPaymentTime(busParent.getPaymentTime());
                enterpriseBusiness.setPaymentTimeUnit(busParent.getPaymentTimeUnit());
            }

        }
        enterpriseBusinessMapper.insertSelective(enterpriseBusiness);


        Map<String,Object> map = new HashMap<>();
        if(null != registerVO.getParentId()){
            map.put("eParentId",registerVO.getParentId().toString());
        }else {
            map.put("eParentId","0");
        }

        map.put("eMobile",register.getMobilePhone());
        map.put("eName",registerVO.getEnterpriseName());
        Integer eType = ChainType.getRgtCode(registerVO.getChainType());
        map.put("eType",eType);
        map.put("uid",rUser.getRgtUserId());
        map.put("eProvince",registerVO.getProvinceId());
        map.put("eCity",registerVO.getCityId());
        map.put("eRegion",registerVO.getAreaId());
        map.put("eAddress",registerVO.getAddress());
        map.put("isCheckEnterpriseELicenseNo",1);//校验重复
        map.put("eLicenseNo",register.getBusinessFileCode());


        List<Map<String,Object>> elist = new ArrayList<>();
        Map<String,Object> qualificationMap = new HashMap<>();
        qualificationMap.put("epType","1");
        qualificationMap.put("epNo",register.getBusinessFileCode());
        qualificationMap.put("epPic",registerVO.getBusinessFileUrl());
        qualificationMap.put("epEndTime",register.getBusinessValidUntil());
        elist.add(qualificationMap);


        Map<String,Object> qualificationMap1 = new HashMap<>();
        qualificationMap1.put("epType","2");
        qualificationMap1.put("epNo",register.getPermitFileCode());
        qualificationMap1.put("epPic",registerVO.getPermitFileUrl());
        qualificationMap1.put("epEndTime",register.getPermitValidUntil());
        elist.add(qualificationMap1);

        Map<String,Object> qualificationMap2 = new HashMap<>();
        qualificationMap2.put("epType","3");
        qualificationMap2.put("epNo",register.getQualityFileCode());
        qualificationMap2.put("epPic",registerVO.getQualityFileUrl());
        qualificationMap2.put("epEndTime",register.getQualityValidUntil());
        elist.add(qualificationMap2);

        map.put("epList",elist);

        /**
         * 初始化后续操作
         */
        if(registerVO.getChainType() != ChainType.Headquarters.getCode()){
            savePriceOrder(enterprise,userVO);
        }else {
            sysDataInitService.initCommonData(enterprise,userVO);
        }

        /**
         *  企业注册保存后，或者分店新增保存后调用 生成余额科目表信息
         */
        financeComponent.initEnterpriseBalance(userVO, enterprise);
        /**
         * 最后调用mph 防止saas系统报错导致 mph和saas系统数据不一致
         */

        rgtService.addEnterprise(map,enterprise.getId());

        registerEnterpriseAudit(register);


        return  enterprise;
    }


    /**
     * 注册审核信息
     * @param register
     */
    @Transactional(rollbackFor = Exception.class)
    public void registerEnterpriseAudit(Register register){

        User user = userMapper.selectByPrimaryKey(register.getUserId());

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());

        /**
         * 只有总部需要审核,门店不需要
         */
        RegisterAudit registerAudit = RegisterAudit.initRegisterAudit(enterprise);
        registerAuditMapper.insert(registerAudit);
      /*  if(enterprise.getChainType().equals(ChainType.Headquarters.getCode())){
            RegisterAudit registerAudit = RegisterAudit.initRegisterAudit(enterprise);

            registerAuditMapper.insert(registerAudit);
        }else {

            Enterprise newEnterprise = new Enterprise();
            newEnterprise.setId(enterprise.getId());
            newEnterprise.setStatus(EnableStatus.ENABLE.getStatus());

            enterpriseMapper.updateByPrimaryKeySelective(newEnterprise);
        }*/

    }

    private PriceOrder savePriceOrder(Enterprise newEnterprise,UserVO userVO) throws Exception {

        sysDataInitService.initCommonData(newEnterprise, userVO);
        //默认价格清单主表
        PriceOrder order = new PriceOrder();
        order.setEnterpriseId(newEnterprise.getId());
        order.setParentId(newEnterprise.getParentId());
        order.setParentOrderId(0L);
        // 类型：0-自定义价格清单；1-系统价格清单
        order.setSysType(SysType.SYSTEM.getCode());
        // 价格类型（0-基础价格；1-配货价格；2-零售价格）
        order.setPriceType(2);
        order.setCode(newEnterprise.getCode());
        order.setName(newEnterprise.getName()+"零售价格清单");
        order.setStatus(EnableStatus.ENABLE.getStatus());
        order.setRemark("系统默认分店价格清单");
        order.setCreaterId(0L);
        order.setCreaterCode("0000");
        order.setCreaterName("0000");
        order.setCreateTime(new Date());
        order.setModifierId(0L);
        order.setModifierCode("0000");
        order.setModifierName("0000");
        order.setUpdateTime(new Date());
        priceOrderMapper.insertSelective(order);



        //默认总部当前商品相关数据
        Map<String,Object> map = new HashMap<>();
        supplierComponent.setParamMap(userVO,map);
        List<GoodsBusiness> goodsBusinesses = goodsBusinessMapper.selectByEnterpriseId(map);

        if(!CollectionUtils.isEmpty(goodsBusinesses)){
            saveStoreDefailtGoods(newEnterprise, goodsBusinesses, order);
        }
        return order;
    }


    private void saveStoreDefailtGoods(Enterprise newEnterprise, List<GoodsBusiness>  goodsIdList, PriceOrder order) {
        //默认安全库存表数据
        List<SafetyStock> stockList = new ArrayList<>();
        SafetyStock stock = null;
        for (GoodsBusiness map : goodsIdList) {
            stock = new SafetyStock();
            stock.setEnterpriseId(newEnterprise.getId());
            stock.setParentId(newEnterprise.getParentId());
            stock.setChainType(newEnterprise.getChainType());
            stock.setGoodsId(map.getGoodsId());
            stock.setStatus(EnableStatus.ENABLE.getStatus());
            stock.setCreaterId(0L);
            stock.setCreaterCode("0000");
            stock.setCreaterName("0000");
            stock.setCreateTime(new Date());
            stock.setModifierId(0L);
            stock.setModifierCode("0000");
            stock.setModifierName("0000");
            stock.setUpdateTime(new Date());
            stockList.add(stock);
        }
        safetyStockMapper.batchInsert(stockList);

        //默认价格清单细表
        List<PriceOrderDetail> orderDetailList = new ArrayList<>();
        PriceOrderDetail orderDetail = null;
        for (GoodsBusiness map : goodsIdList) {
            orderDetail = new PriceOrderDetail();
            orderDetail.setPriceOrderId(order.getId());
            orderDetail.setEnterpriseId(newEnterprise.getId());
            orderDetail.setParentId(newEnterprise.getParentId());
            orderDetail.setGoodsId(map.getGoodsId());
            orderDetail.setStatus(1);
            orderDetail.setDistrTaxRate(map.getDistrTaxRate());
            orderDetail.setDistrTaxRateId(map.getDistrTaxRateId());
            orderDetail.setRetailPrice(map.getRetailPrice());
            orderDetail.setMemberPrice(map.getMemberPrice());
            orderDetail.setSaleTaxRate(map.getSaleTaxRate());
            orderDetail.setSaleTaxRateId(map.getSaleTaxRateId());

            orderDetail.setDistrPrice(map.getDistrPrice());
            /**
             * 单价/(1+税率)
             */
            BigDecimal distTraxRate = map.getDistrTaxRate();
            BigDecimal distrPrice = map.getDistrPrice();
            orderDetail.setNotaxDistrPrice(enterpriseComponent.getExclusiveTax(distrPrice,distTraxRate));

            orderDetail.setNotaxRetailPrice(enterpriseComponent.getExclusiveTax(orderDetail.getRetailPrice(),orderDetail.getSaleTaxRate()));
            orderDetail.setNotaxMemberPrice(enterpriseComponent.getExclusiveTax(orderDetail.getMemberPrice(),orderDetail.getSaleTaxRate()));

            orderDetail.setCreaterId(0L);
            orderDetail.setCreaterCode("0000");
            orderDetail.setCreaterName("0000");
            orderDetail.setCreateTime(new Date());
            orderDetail.setModifierId(0L);
            orderDetail.setModifierCode("0000");
            orderDetail.setModifierName("0000");
            orderDetail.setUpdateTime(new Date());
            orderDetailList.add(orderDetail);
        }
        priceOrderDetailMapper.batchInsert(orderDetailList);
        //默认商品管理表
        List<GoodsManage> manageList = new ArrayList<>();
        GoodsManage goodsManage = null;
        for(int i=0; i<goodsIdList.size(); i++){
            goodsManage = new GoodsManage();
            goodsManage.setEnterpriseId(newEnterprise.getId());
            goodsManage.setParentId(newEnterprise.getParentId());
            goodsManage.setChainType(newEnterprise.getChainType());
            goodsManage.setGoodsId(Long.parseLong(goodsIdList.get(i).getId().toString()));
            goodsManage.setSafetyStockId(stockList.get(i).getId());
            goodsManage.setPriceOrderDtlId(orderDetailList.get(i).getId());
            goodsManage.setStatus(GoodsManageStatus.WAIT_ON_SHELVES.getCode());
            goodsManage.setCreaterId(0L);
            goodsManage.setCreaterCode("0000");
            goodsManage.setCreaterName("0000");
            goodsManage.setCreateTime(new Date());
            goodsManage.setModifierId(0L);
            goodsManage.setModifierCode("0000");
            goodsManage.setModifierName("0000");
            goodsManage.setUpdateTime(new Date());
            manageList.add(goodsManage);
        }
        goodsManageMapper.batchInsert(manageList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Enterprise synchronizeRGTEnterprise(Long userId) throws Exception {

        Register register = registerMapper.selectByUserId(userId);

        User user = userMapper.selectByPrimaryKey(userId);

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());


        Map<String,Object> map = new HashMap<>();
        map.put("username",register.getLoginAccount());
        JSONObject rgtUser = rgtService.getRGTUser(map);

        Integer eId = rgtUser.getInteger("eId");
        if(eId == null || eId.equals(0)){
            throw new RegisterBizException(SysCode.FAIL.getCode(),"获取不到融贯通企业信息");
        }

        Enterprise newEnterprise = new Enterprise();
        newEnterprise.setId(enterprise.getId());
        newEnterprise.setRgtEnterpriseId(eId);

        enterpriseMapper.updateByPrimaryKeySelective(newEnterprise);

        /**
         * 注册审核企业
         */

        registerEnterpriseAudit(register);

        return enterprise;

    }


    /**
     * 初始化企业负责人 , 法定代表人,质量负责人
     */
    private static final String USER_CODE_ENTERPRISE_NAME = "com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise";
    private static final String USER_CODE_USER_NAME = "com.rograndec.feijiayun.chain.business.basic.user.entity.User";

    private static final Integer USER_CODE_LENGTH = 4;

    private List<User> initEnterpriseUser(RegisterEnterpriseVO registerVO, Enterprise enterprise) throws Exception {

        List<User> users = new ArrayList<>();

        Integer chainType = enterprise.getChainType();

        Long enterpriseId = enterprise.getEnterpriseId();


        if(!StringUtils.isEmpty(registerVO.getChargeMan())){
            /**
             * 企业负责人
             */
            String chargeMan = registerVO.getChargeMan();
            String code = codeComponent.generate(USER_CODE_USER_NAME,USER_CODE_LENGTH,enterpriseId);
            User chargeManUser = User.initEnterpriseUser(
                    chargeMan
                    , code
                    , enterprise
            );

            chargeManUser.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
            users.add(chargeManUser);

            /**
             * 法定负责人
             */
            String legalMan = registerVO.getLegalMan();

            code = codeComponent.generate(USER_CODE_USER_NAME,USER_CODE_LENGTH,enterpriseId);
            User legalManUser = User.initEnterpriseUser(
                    legalMan
                    , code
                    , enterprise
            );
            legalManUser.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
            users.add(legalManUser);

            /**
             * 质量负责人
             */
            String qualityOfficer = registerVO.getQualityOfficer();

            code = codeComponent.generate(USER_CODE_USER_NAME,USER_CODE_LENGTH,enterpriseId);
            User lqualityOfficerUser = User.initEnterpriseUser(
                    qualityOfficer
                    , code
                    , enterprise
            );
            lqualityOfficerUser.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
            users.add(lqualityOfficerUser);

        }



        return users;
    }



    @Override
    public List<RegisterEnterpriseNotBindVO> getNotBindEnterprise(Page page){

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());

        List<RegisterEnterpriseNotBindVO> registerEnterpriseNotBindVOS = registerMapper.selectNotBindEnterprise();

        page.setResult(registerEnterpriseNotBindVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
        page.setTotalPage(hPage.getPages());

        return registerEnterpriseNotBindVOS;
    }

    private void initSysRoleAction(User user, Enterprise enterprise,List<SysRole> roleList, List<SysAction> actionList) {

        // 新获取一个模式为BATCH，自动提交为false的session
        // 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        try {
            SysRoleActionMapper mapper = session.getMapper(SysRoleActionMapper.class);
            int i = 0 ;
            for(SysRole sysRole:roleList){
                for(SysAction sysAction:actionList){
                    SysRoleAction sysRoleAction = new SysRoleAction();
                    sysRoleAction.setEnterpriseId(user.getEnterpriseId());
                    sysRoleAction.setParentId(user.getParentId());
                    sysRoleAction.setChainType(enterprise.getChainType());
                    sysRoleAction.setRoleId(sysRole.getId());
                    sysRoleAction.setActionId(sysAction.getId());
                    sysRoleAction.setRemark("系统默认权限");
                    sysRoleAction.setCreaterId(user.getId());
                    sysRoleAction.setCreaterCode(user.getCode());
                    sysRoleAction.setCreaterName(user.getName());
                    sysRoleAction.setCreateTime(new Date());
                    sysRoleAction.setModifierId(user.getId());
                    sysRoleAction.setModifierCode(user.getCode());
                    sysRoleAction.setModifierName(user.getName());
                    sysRoleAction.setUpdateTime(new Date());
                    mapper.insertSelective(sysRoleAction);
                    i++;
                    if(i % 1000 == 0) {
                        session.commit();
                        session.clearCache();
                    }
/*
                sysRoleActionMapper.insertSelective(sysRoleAction);
*/
            }
        }
        session.commit();
        }catch (Exception e){
            session.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }


   /* private List<SysAction> initSysAction(User user) {
        List<SysAction> sysActionList = new ArrayList<SysAction>();

        Map<Long,Long> newActionIdMaping = new HashMap<>();
        List<SysAction> sysActions = sysActionMapper.selectByEnterpriseId(0L);

        Map<Long, Long> oldParentChildActionIdMaping = sysActions.stream().collect(Collectors.toMap(SysAction::getId, SysAction::getParentActionId));

        for (SysAction sysAction : sysActions){

            Long aLong = oldParentChildActionIdMaping.get(sysAction.getId());
            if(aLong.equals(0L)){
                Long oldId = sysAction.getId();
                sysAction.setEnterpriseId(user.getEnterpriseId());
                sysAction.setParentId(user.getParentId());
                sysAction.setId(null);
                sysAction.setParentActionId(0L);
                sysActionMapper.insertSelective(sysAction);
                newActionIdMaping.put(oldId,sysAction.getId());
            }else {
                Long oldId = sysAction.getId();
                Long oldParentActionId = sysAction.getParentActionId();
                sysAction.setEnterpriseId(user.getEnterpriseId());
                sysAction.setParentId(user.getParentId());
                sysAction.setId(null);
                Long newParentActionId = newActionIdMaping.get(oldParentActionId);
                sysAction.setParentActionId(newParentActionId);
                sysActionMapper.insertSelective(sysAction);
                newActionIdMaping.put(oldId,sysAction.getId());
            }

            sysActionList.add(sysAction);

        }

        return sysActionList;
    }*/


}
