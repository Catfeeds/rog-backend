package com.rograndec.feijiayun.chain.business.basic.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UseNatureEum;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserStatusEum;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserTypeEum;
import com.rograndec.feijiayun.chain.business.basic.user.dao.*;
import com.rograndec.feijiayun.chain.business.basic.user.entity.*;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.vo.param.UsersParam;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.PosWarehouseCargoAreaVO;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosClerkMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeAuthMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosTeamMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosClerk;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayee;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayeeAuth;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosTeam;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayeeService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.BaseTreeVO;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.rogrand.service.RGTService;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import com.rograndec.feijiayun.chain.utils.user.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by zhaiwei on 2017/8/28.
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private CodeComponent codeComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private UserRoleComponent userRoleComponent;
    @Autowired
    private UserQualificationMapper userQualificationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private UserPersonalMapper userPersonalMapper;

    @Autowired
    private UserQualificationConfigMapper userQualificationConfigMapper;

    @Autowired
    private UserModifyRecordMapper userModifyRecordMapper;

    @Autowired
    private UserDeptMapper userDeptMapper;

    @Autowired
    private UserPositionMapper userPositionMapper;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private EducationMajorMapper educationMajorMapper;

    @Autowired
    private BusinessScopeMapper businessScopeMapper;

    @Autowired
    private NationMapper nationMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private ExcelComponent<UserExcelVO> excelComponent;

    @Autowired
    private PosTeamMapper posTeamMapper;

    @Autowired
    private PosPayeeMapper posPayeeMapper;

    @Autowired
    private PosPayeeAuthMapper posPayeeAuthMapper;

    @Autowired
    private RGTUserComponent rgtUserComponent;

    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;

    @Autowired
    private PosClerkMapper posClerkMapper;

    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

    @Autowired
    private ManageConfigService manageConfigService;

    @Autowired
    private SaleDetailMapper saleDetailMapper;

    @Autowired
    private PosPayeeService posPayeeService;

    @Autowired
    private RGTService rgtService;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RedisComponent redisComponent;

    private static final String USER_CODE_NAME = "com.rograndec.feijiayun.chain.business.basic.user.entity.User";

    private static final Integer USER_CODE_LENGTH = 4;

    /**
     * 总部
     */
    private static final Integer HQ = 0;

    /**
     * 非总部
     */
    private static final Integer NOT_HQ = 1;
//    @Override
//    public UserInitVO initUser(UserVO userVO){
//        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO.getEnterpriseId());
//
//        UserInitVO userInitVO = new UserInitVO();
//
//        List<UserQualification> userQualifications = userQualificationMapper.selectTypeMustByEnterpriseId(map);
//        return userInitVO;
//
//    }


    /**
     * 查询员工
     *
     * @param userVO
     * @return
     */
    @Override
    public Page users(UserVO userVO, UsersParam usersParam, Page page) {


        Map<String, Object> map = new HashMap<>();

        /**
         * 审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）
         */
        if (null != usersParam.getApproveStatus() &&
                (usersParam.getApproveStatus() == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue()
                        || usersParam.getApproveStatus() == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue()
                        || usersParam.getApproveStatus() == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())) {

            map.put("approveStatus", usersParam.getApproveStatus());

        }

        if (usersParam.getDept() != null) {
            map.put("dept", usersParam.getDept());
        }

        if (usersParam.getPosition() != null) {
            map.put("position", usersParam.getPosition());
        }

        if (usersParam.getRole() != null) {
            map.put("role", usersParam.getRole());
        }

        if (usersParam.getEducation() != null) {
            map.put("education", usersParam.getEducation());
        }

        if (usersParam.getMajor() != null) {
            map.put("major", usersParam.getMajor());
        }

        if (usersParam.getUseNature() != null) {
            map.put("useNature", usersParam.getUseNature());
        }

        if (usersParam.getStatus() != null && (EnableStatus.ENABLE.getStatus() == usersParam.getStatus() || EnableStatus.DISABLE.getStatus() == usersParam.getStatus())) {

            map.put("status", usersParam.getStatus());
        }

        if (!StringUtils.isEmpty(usersParam.getQueryStr())) {
            map.put("queryStr", usersParam.getQueryStr());
        }


        if (null == usersParam.getEnterprise()) {
            map.put("enterprise", userVO.getEnterpriseId());
            map.put("parentId", userVO.getEnterpriseId());
        } else {
            map.put("queryEnterprise", "queryEnterprise");
            map.put("enterprise", usersParam.getEnterprise());
            map.put("parentId", usersParam.getEnterprise());
        }
        /*  com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());*/

        map.put("pageNo", page.getStart());
        map.put("pageSize", page.getPageSize());

        Long total = userMapper.selectByQueryParamCount(map);


        List<QueryUserVO> userVOS = userMapper.selectByQueryParam(map);

        List<Long> enterpriseIds = QueryUserVO.getEnterpriseIds(userVOS);

        List<Enterprise> enterprises = new ArrayList<>();
        if (!CollectionUtils.isEmpty(enterpriseIds)) {
            enterprises = enterpriseMapper.selectByIds(enterpriseIds);
        }


        userVOS = QueryUserVO.setDesc4QueryUserVOs(userVOS, enterprises);

        List<Long> deptsAll = new ArrayList<>();
        List<Long> positionsAll = new ArrayList<>();
        List<Long> rolesAll = new ArrayList<>();

        for (QueryUserVO queryUserVO : userVOS) {
            String deptIdsStr = queryUserVO.getDeptIds();
            List<Long> depts = StringSplit.strSplit(deptIdsStr);
            queryUserVO.setDepts(depts);
            deptsAll.addAll(depts);

            List<Long> positionIds = StringSplit.strSplit(queryUserVO.getPositionIds());
            positionsAll.addAll(positionIds);
            queryUserVO.setPositions(positionIds);

            List<Long> roleIds = StringSplit.strSplit(queryUserVO.getRoleIds());
            rolesAll.addAll(roleIds);
            queryUserVO.setRoles(roleIds);
        }
        List<Department> departments = new ArrayList<>();
        if (!CollectionUtils.isEmpty(deptsAll)) {
            List<Long> collect = deptsAll.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
            departments = departmentMapper.selectByIds(collect);
        }

        List<Position> positions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(positionsAll)) {
            List<Long> postitionIds = positionsAll.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
            positions = positionMapper.selectByIds(postitionIds);
        }

        List<SysRole> sysRoles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rolesAll)) {
            List<Long> roleIds = rolesAll.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
            sysRoles = sysRoleMapper.selectByIds(roleIds);
        }

        for (QueryUserVO queryUserVO : userVOS) {
            queryUserVO.setDeptDesc(departments, queryUserVO);
            queryUserVO.setPositionsDesc(positions, queryUserVO);
            queryUserVO.setRolesDesc(sysRoles, queryUserVO);

            if (null != queryUserVO.getApproveStatus()) {
                ApprovalFlowAuditStatusRecom approvalFlowAuditStatusRecomEnum = ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(queryUserVO.getApproveStatus());
                queryUserVO.setApproveStatusDesc(null == approvalFlowAuditStatusRecomEnum ? "" : approvalFlowAuditStatusRecomEnum.getName());
            }

        }

        page.setResult(userVOS);
        page.setTotalRecord(total.intValue());
        return page;
    }


    /**
     * 根据userId查询修改记录
     *
     * @param userId
     * @return
     */
    @Override
    public Page findUserModifyRecordVOs(Long userId, Page page) {

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<UserModifyRecordWithBLOBs> userModifyRecords = userModifyRecordMapper.selectByUserId(userId);

        List<UserModifyRecordWithReturnVO> userModifyRecordWithReturnVOS = UserModifyRecordWithReturnVO.getRecordVO4Record(userModifyRecords);
        page.setResult(userModifyRecordWithReturnVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }


    /**
     * 查询员工基本信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserReturnVO findUser(Long userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user) {
            throw new UserManagerBizException(UserManagerBizException.NOT_DATA, "用户不存在!!!");
        }
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());

        UserReturnVO userReturnVO = UserReturnVO.getUserReturnVO4User(user, enterprise, null, null, null);
        return userReturnVO;
    }

    @Override
    public List<User> findUserByEId(Long id) {
        List<User> users = userMapper.selectByEnterpriseId(id);
        return users;
    }

    @Override
    public List<User> findUserById(Map<String, Object> map) {
        List<User> users = userMapper.selectById(map);
        return users;
    }

    @Override
    public List<User> findUserByIdAndRole(UserVO userVO, String roleCode) {
        List<User> users = userMapper.selectByIdAndRoleId(userVO.getEnterpriseId(), roleCode);
        return users;
    }

    @Override
    public Map<String, List<User>> findUserByIdAndRoles(UserVO userVO) {
        List<String> list = new ArrayList<>();
        /* 010101:法定代表人;010201:企业负责人;030101:质量负责人*/
        Map<String, List<User>> map = new HashMap<>();
        List<User> bUsers = findUserByIdAndRole(userVO, DefRoleCode.BUSINESS_MAN.getCode());
        List<User> lUsers = findUserByIdAndRole(userVO, DefRoleCode.lEAL_MAN.getCode());
        List<User> qUsers = findUserByIdAndRole(userVO, DefRoleCode.QUALITY_OFFICER.getCode());
        map.put(DefRoleCode.BUSINESS_MAN.getCode(), bUsers);
        map.put(DefRoleCode.lEAL_MAN.getCode(), lUsers);
        map.put(DefRoleCode.QUALITY_OFFICER.getCode(), qUsers);
        return map;
    }


    @Override
    public List<UserReturnVO> findUser(Set<Long> userIds) {
        List<UserReturnVO> resultList = new ArrayList<>();
        List<Long> us = new ArrayList<>(userIds);
        List<User> users = userMapper.selectByIds(us);

        List<Long> ids = User.getentErpriseIds(users);

        List<Enterprise> enterprises = enterpriseMapper.selectByIds(ids);

        for (User user : users) {
            for (Enterprise enterprise : enterprises) {
                if (user.getEnterpriseId().equals(enterprise.getId())) {
                    UserReturnVO userReturnVO = UserReturnVO.getUserReturnVO4User(user, enterprise, null, null, null);
                    resultList.add(userReturnVO);
                }
            }
        }


        return resultList;
    }

    /**
     * 查询员工行政信息
     *
     * @return
     */
    @Override
    public UserAdministrationReturnVO findUserAdministration(Long userId, UserVO userVO) {

        List<UserAdministration> userAdministrations = userAdministrationMapper.selectUserAdministrationByUserId(userId);
        User user = userMapper.selectByPrimaryKey(userId);
        UserAdministrationReturnVO userAdministrationReturnVO = new UserAdministrationReturnVO();
        if (!CollectionUtils.isEmpty(userAdministrations)) {

            UserAdministration userAdministration = userAdministrations.get(0);
            userAdministrationReturnVO = generateUserAdministrationReturnVO(userAdministration);
            userAdministrationReturnVO.setCheckUpdateLoginAcount(EnableStatus.DISABLE.getStatus());
            List<UserRole> userRoles = userRoleMapper.selectByUserId(userId);

            List<Long> roleIds = UserRole.getRoleIds(userRoles);

            List<SysRole> sysRoles = sysRoleMapper.selectByIds(roleIds);

            if (String.valueOf(UserTypeEum.SYSTEM_USER.getCode()) == userAdministration.getUserType()) {
                userAdministrationReturnVO.setCheckUpdateLoginAcount(EnableStatus.DISABLE.getStatus());
            } else {
                for (SysRole role : sysRoles) {
                    if (role.getCode().equals(DefRoleCode.BUSINESS_MAN.getCode())
                            || role.getCode().equals(DefRoleCode.lEAL_MAN.getCode())
                            || role.getCode().equals(DefRoleCode.QUALITY_OFFICER.getCode())
                            ) {
                        /**
                         * 如果是企业负责人,法人,质量负责人 需要判断rgtUserId 是否为空如果为空则需要修改,便于保存后注册融贯通
                         */
                        Integer rgtUserId = user.getRgtUserId();
                        if (null == rgtUserId || 0L == rgtUserId) {
                            userAdministrationReturnVO.setCheckUpdateLoginAcount(EnableStatus.ENABLE.getStatus());
                        }
                    }
                }
            }

        }

        //判断是否存在营业/收款人员，有则进行相关设置
        addCargoAreaInfo(userAdministrationReturnVO, userId);
        return userAdministrationReturnVO;
    }

    private void addCargoAreaInfo(UserAdministrationReturnVO userAdministrationReturnVO, Long userId) {
        List<Long> cargoAreaIds = userAdministrationReturnVO.getCargoAreaIds();
        if (cargoAreaIds != null && !cargoAreaIds.isEmpty()) {
            List<CargoAreaVO> cargoAreaDesc = posClerkMapper.queryByCargoIds(cargoAreaIds);
            cargoAreaIds = new ArrayList<>();
            StringBuffer sb = new StringBuffer();
            for (CargoAreaVO vo : cargoAreaDesc) {
                cargoAreaIds.add(vo.getId());
                sb.append(vo.getName()).append(",");
            }
            String desc = sb.toString().substring(0, sb.length() - 1);
            userAdministrationReturnVO.setCargoAreaDesc(desc);
        }
        Long teamId = userAdministrationReturnVO.getTeamId();
        if (teamId != null) {
            TeamVO teamDesc = posClerkMapper.getByTeamId(teamId);
            userAdministrationReturnVO.setTeamDesc(teamDesc.getName());
        }

    }

    @Override
    public List<UserAdministrationReturnVO> findUserAdministration(Set<Long> userId) {

        List<UserAdministrationReturnVO> resultList = new ArrayList<>();

        List<Long> collect = userId.stream().collect(Collectors.toList());

        List<UserAdministration> userAdministrations = userAdministrationMapper.selectUserAdministrationByUserIds(collect);
        if (!CollectionUtils.isEmpty(userAdministrations)) {

            for (UserAdministration userAdministration : userAdministrations) {

                UserAdministrationReturnVO userAdministrationReturnVO = generateUserAdministrationReturnVO(userAdministration);
                resultList.add(userAdministrationReturnVO);
            }


        }
        return resultList;
    }

    private UserAdministrationReturnVO generateUserAdministrationReturnVO(UserAdministration userAdministration) {
        List<Long> deptIds = UserAdministration.getDeptIds2List(userAdministration);

        List<Department> departments = new ArrayList<>();

        if (!CollectionUtils.isEmpty(deptIds)) {
            departments = departmentMapper.selectByIds(deptIds);
        }


        List<Position> positions = new ArrayList<>();
        List<Long> positionIds = UserAdministration.getPositionIds2List(userAdministration);
        if (!CollectionUtils.isEmpty(positionIds)) {
            positions = positionMapper.selectByIds(positionIds);
        }

        List<SysRole> roles = new ArrayList<>();
        List<Long> roleIds = UserAdministration.getRoleIds2List(userAdministration);
        if (!CollectionUtils.isEmpty(roleIds)) {
            roles = sysRoleMapper.selectByIds(roleIds);
        }


        EducationMajor education = educationMajorMapper.selectByPrimaryKey(userAdministration.getEducationId());
        EducationMajor major = educationMajorMapper.selectByPrimaryKey(userAdministration.getMajorId());

        String limitVarietyRaneStrs = userAdministration.getLimitVarietyRange();
        List<Long> list = StringSplit.strSplit(limitVarietyRaneStrs);
        List<BusinessScope> businessScopes = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            businessScopes = businessScopeMapper.getBusinessScopeByBusinessScopeId(userAdministration.getEnterpriseId(), list);
        }

//        UserAdministrationReturnVO userAdministrationReturnVO = new UserAdministrationReturnVO();
//        if(null != userAdministration && !CollectionUtils.isEmpty(departments)
//                && !CollectionUtils.isEmpty(positions)
//                && !CollectionUtils.isEmpty(businessScopes)
//                && null != education){
        UserAdministrationReturnVO userAdministrationReturnVO = UserAdministrationReturnVO.getUserAdministrationReturnVO4UserAdmin(userAdministration
                , departments, positions, roles
                , education, major, businessScopes);
//        }


        return userAdministrationReturnVO;
    }

    /**
     * 查询员工个人信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserPersonalReturnVO findUserPersonalReturnVO(Long userId) {
        UserPersonalReturnVO userPersonalReturnVO = new UserPersonalReturnVO();
        List<UserPersonal> personals = userPersonalMapper.selectUserPersonalByUserId(userId);
        if (!CollectionUtils.isEmpty(personals)) {

            UserPersonal userPersonal = personals.get(0);
            userPersonalReturnVO = generateUserPersonalReturnVO(userPersonal);
        }
        return userPersonalReturnVO;
    }

    /**
     * 查询员工个人信息集合
     *
     * @param userIds
     * @return
     */
    @Override
    public List<UserPersonalReturnVO> findUserPersonalReturnVO(Set<Long> userIds) {
        List<UserPersonalReturnVO> resultList = new ArrayList<>();

        List<Long> collect = userIds.stream().collect(Collectors.toList());
        List<UserPersonal> personals = userPersonalMapper.selectUserPersonalByUserIds(collect);
        if (!CollectionUtils.isEmpty(personals)) {

            for (UserPersonal personal : personals) {
                UserPersonalReturnVO userPersonalReturnVO = generateUserPersonalReturnVO(personal);
                resultList.add(userPersonalReturnVO);
            }

        }
        return resultList;
    }

    private UserPersonalReturnVO generateUserPersonalReturnVO(UserPersonal userPersonal) {

        Long nationId = userPersonal.getNationId();
        Nation nation = new Nation();
        if (null != nationId)
            nation = nationMapper.selectByPrimaryKey(nationId);

        File file = new File();
        if (null != userPersonal.getPhotoId())
            file = fileMapper.selectByPrimaryKey(userPersonal.getPhotoId());
        UserPersonalReturnVO userPersonalReturnVO = new UserPersonalReturnVO();
        if (null != userPersonal) {
            userPersonalReturnVO = UserPersonalReturnVO.getUserPersonalReturnVO4Personal(userPersonal, nation, file);
        }
        return userPersonalReturnVO;
    }


    @Override
    public List<UserQualificationConfigReturnVO> findUserQualificationConfigReturnVOs(UserVO userVO, Long userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        List<UserQualificationConfig> userQualificationConfigs = userQualificationConfigMapper.selectByUserId(userId, user.getEnterpriseId());
        List<UserQualificationConfigReturnVO> list = new ArrayList<>();
        List<Long> fileIds = UserQualificationConfig.getFileIds(userQualificationConfigs);
        List<File> files = new ArrayList<>();
        if (!CollectionUtils.isEmpty(fileIds))
            files = fileMapper.selectByIds(fileIds);

        if (CollectionUtils.isEmpty(userQualificationConfigs)) {
            return list;
        }
        List<Long> uqIds = UserQualificationConfig.getUserQualificationIds(userQualificationConfigs);
        List<UserQualification> userQualifications = new ArrayList<>();
        if (!CollectionUtils.isEmpty(uqIds))
            userQualifications = userQualificationMapper.selectByIds(uqIds);

        for (UserQualificationConfig config : userQualificationConfigs) {

            UserQualificationConfigReturnVO userQualificationConfigReturnVO
                    = UserQualificationConfigReturnVO.getUserQualificationConfigReturnVO4Config(config, files, userQualifications);

            list.add(userQualificationConfigReturnVO);
        }

        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePwd(UserPwdVO userPwdVO, UserVO userVO) {

        Map<String, Object> map = new HashMap<>();

        User user = userMapper.selectByPrimaryKey(userVO.getUserId());
        if (StringUtils.isEmpty(user.getRgtUserId())) {
            throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK, "融贯通用户id缺失!!!");
        }

        rgtUserComponent.updateRgtUserPwd(user.getRgtUserId(), userVO.getLoginAccount(), userPwdVO.getNewPassword(), userPwdVO.getPassword());
       /* map.put("uid",user.getRgtUserId());
        map.put("flag",3);
        map.put("username",userVO.getLoginAccount());
        map.put("password",userPwdVO.getNewPassword());
        rgtService.updateUserPwd(map);*/
        return userAdministrationMapper.updatePwd(userPwdVO);
    }

    @Override
    public Integer getUserCountByUIDPwd(UserPwdVO userPwdVO) {
        return userAdministrationMapper.getUserCountByUIDPwd(userPwdVO);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(AddUserVO addUserVO, UserVO userVO) throws Exception {

        UserAdministrationVO administrationVO = addUserVO.getUserAdministrationDTO();

        Long enterpriseId = addUserVO.getEnterpriseId();
        //如果添加的是总部的人员是不能为Pos类型的
        if (userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            if (userVO.getEnterpriseId().equals(enterpriseId)) {
                if (administrationVO.getUserType().contains(Long.parseLong(String.valueOf(UserTypeEum.POS_USER.getCode())))
                        || administrationVO.getUserType().contains(Long.parseLong(String.valueOf(UserTypeEum.CLOUD_POS_USER.getCode())))) {
                    throw new BusinessException("总部不能选择pos用户");
                }
            }
        }

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);

//        if(StringUtils.isEmpty(addUserVO)){
        long startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user save get code start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        String code = userComponent.getUserCode(userVO, addUserVO, enterprise);
        addUserVO.setCode(code);
        long endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user save get code end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user save get code start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");


        User user = User.getUser4AddUserVO(addUserVO, userVO, enterprise, true);
        user.setStatus(administrationVO.getStatus());
        user.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue());
        /*user.setApproveStatus(UserApprovalStatus.PENDING_AUDIT);*/

        startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user insertSelective(user) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        userMapper.insertSelective(user);

        endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user insertSelective(user) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user insertSelective(user) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");


        /**
         * 保存权限关系表
         */
        startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userRoleComponent.save(user,administrationVO.getRoleIds()) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        userRoleComponent.save(user, administrationVO.getRoleIds());

        endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userRoleComponent.save(user,administrationVO.getRoleIds()) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userRoleComponent.save(user,administrationVO.getRoleIds()) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");


        startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userAdministrationMapper.insertSelective(userAdministration) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        UserAdministration userAdministration = UserAdministration.getUserAdministration4AddUserVO(addUserVO, userVO, user, enterprise, true);
        userAdministrationMapper.insertSelective(userAdministration);

        endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userAdministrationMapper.insertSelective(userAdministration) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userAdministrationMapper.insertSelective(userAdministration) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");


        startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userPersonalMapper.insertSelective(userPersonal) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        UserPersonal userPersonal = UserPersonal.getUserPersonal4UserPersonalVO(addUserVO, userVO, user, enterprise, true);
        userPersonalMapper.insertSelective(userPersonal);

        endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userPersonalMapper.insertSelective(userPersonal) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user userPersonalMapper.insertSelective(userPersonal) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        List<Long> deptIds = addUserVO.getUserAdministrationDTO().getDeptIds();

        if (!CollectionUtils.isEmpty(deptIds)) {
            List<UserDept> depts = UserDept.getUserUserDepts4UserDeptVOs(deptIds, userVO, user, true);

            startTime = Instant.now().toEpochMilli();
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userDeptMapper.insertSelective(dept) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

            for (UserDept dept : depts) {
                userDeptMapper.insertSelective(dept);
            }

            endTime = Instant.now().toEpochMilli();
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userDeptMapper.insertSelective(dept) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userDeptMapper.insertSelective(dept) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        }

        List<Long> positionIds = addUserVO.getUserAdministrationDTO().getPositionIds();

        if (!CollectionUtils.isEmpty(positionIds)) {
            List<UserPosition> positions = UserPosition.getUserUserPositions4VOs(positionIds, userVO, user, true);

            startTime = Instant.now().toEpochMilli();
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userPositionMapper.insertSelective(dept) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

            for (UserPosition dept : positions) {
                userPositionMapper.insertSelective(dept);
            }

            endTime = Instant.now().toEpochMilli();
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userPositionMapper.insertSelective(dept) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userPositionMapper.insertSelective(dept) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        }

        List<UserQualificationConfig> userQualificationConfigs = UserQualificationConfig.getUserQualificationConfigs4VOs(addUserVO.getUserQualificationConfigDTOS(), userVO, user);

        startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userQualificationConfigMapper.insertSelective(config) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        for (UserQualificationConfig config : userQualificationConfigs) {
            userQualificationConfigMapper.insertSelective(config);
        }

        endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userQualificationConfigMapper.insertSelective(config) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user for userQualificationConfigMapper.insertSelective(config) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        startTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user intPosUser(user,userAdministration) start" + startTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");

        //intPosUser(user,userAdministration);
        //业务需求更改，添加人员信息的同时添加收款人员及营业人员
        initPosUserNew(user, userVO, administrationVO, true);

        endTime = Instant.now().toEpochMilli();
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user intPosUser(user,userAdministration) end" + endTime + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user intPosUser(user,userAdministration) start-end" + (endTime - startTime) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");


        enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());

        if (enterprise.getRgtEnterpriseId() == null || enterprise.getRgtEnterpriseId() == 0) {
            throw new UserManagerBizException(UserManagerBizException.NOT_DATA, "融贯通企业id为空");
        }

        //审批流对象
        // 查询一条默认初始化的审批流/
        Integer chainType = userVO.getChainType();
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), userVO.getEnterpriseName(),
                userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
                chainType.equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                "0102", user.getId(), user.getCode(), user.getName());
        approvalFlowComponent.apply(submitApprovalFlowVO, userVO);


    }

    private void intPosUser(User user, UserAdministration userAdministration) {

        /**
         * 判断员工类型是否是pos 如果是需要初始化pos员工信息
         */
        String userType = userAdministration.getUserType();
        List<Long> longs = StringSplit.strSplit(userType);
        if (longs.contains(Long.parseLong(UserTypeEum.POS_USER.getCode() + ""))
                || longs.contains(Long.parseLong(UserTypeEum.CLOUD_POS_USER.getCode() + ""))) {

            PosTeamVO posTeamVO = posTeamMapper.findByEnterpriseIdAndDef(user.getEnterpriseId());
            PosPayee posPayee = PosPayee.getPosPayee(user, posTeamVO);
            posPayeeMapper.insertSelective(posPayee);

            PosPayeeAuth posPayeeAuth = PosPayeeAuth.getPosPayeeAuth(posPayee);

            posPayeeAuthMapper.insertSelective(posPayeeAuth);

        }


    }

    /**
     * 判断是否是门店添加员工，若是则进行相关判断进行pos员工信息的初始化,dongdong.zhang 2017.12.13
     *
     * @param user             新数据
     * @param userVO           操作人
     * @param administrationVO
     * @param isAdd            true为新增，false为修改
     */
    private void initPosUserNew(User user, UserVO userVO, UserAdministrationVO administrationVO, boolean isAdd) {
        //验证参数
        validParams(administrationVO);
    	/*  Integer chainType = userVO.getChainType();
    	//如果是门店（自营店/加盟店），根据具体条件添加pos员工信息
    	 if(!chainType.equals(ChainType.Headquarters.getCode())) {
    		 ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
    		 //验证参数
    		 validParams(administrationVO);
    		 //审批流程关闭的时候员工信息添加（修改）的同时添加pos员工的信息
    		 if(manageConfig.getApprovalControl() == 0) {
    			 if(isAdd) {
    				 //添加
    				 addPosUser(user, administrationVO);
    			 }else {
    				 //修改
    				 user = userMapper.selectByPrimaryKey(user.getId());
    				 updatePosUserInfo(user, administrationVO);
    			 }
    		 }
    	}*/
    }

    private void validParams(UserAdministrationVO administrationVO) {
        List<Long> userType = administrationVO.getUserType();
        //判断是否有pos用户类型
        if (userType.contains(Long.parseLong(UserTypeEum.POS_USER.getCode() + ""))
                || userType.contains(Long.parseLong(UserTypeEum.CLOUD_POS_USER.getCode() + ""))) {
            List<Long> deptIds = administrationVO.getDeptIds();
            //判断是否有门店部门选项，初始化系统信息的时候门店部门的id为8
            if (deptIds.contains(8L)) {
                List<Long> positionIds = administrationVO.getPositionIds();
                //判断是否包含店员,岗位表中店员id为23
                if (positionIds.contains(23L)) {
                    List<Long> roleIds = administrationVO.getRoleIds();
                    //判断包含营业人员与收款人员的情况，38 为营业人员，39为收款人员
                    if (roleIds.contains(38L)) {
                        List<Long> cargoAreaIds = administrationVO.getCargoAreaIds();
                        if (cargoAreaIds == null || cargoAreaIds.isEmpty()) throw new BusinessException("至少添加一个柜组");
                    } else {
                        administrationVO.setCargoAreaIds(null);
                    }
                    if (roleIds.contains(39L)) {
                        Long teamId = administrationVO.getTeamId();
                        if (teamId == null) throw new BusinessException("缺少班组参数");
                    } else {
                        throw new BusinessException("若为pos用户收款人员角色必填");
                    }
                } else {
                    throw new BusinessException("若为pos用户店员岗位必填");
                }
            } else {
                throw new BusinessException("若为pos用户门店部门必填");
            }
        } else {
            administrationVO.setTeamId(null);
            administrationVO.setCargoAreaIds(null);
        }
    }

    private void addPosUser(User user, UserAdministrationVO administrationVO) {
        List<Long> roleIds = administrationVO.getRoleIds();
        if (roleIds != null && !roleIds.isEmpty()) {
            //判断包含营业人员与收款人员的情况，38 为营业人员，39为收款人员
            if (roleIds.contains(38L)) {
                List<Long> cargoAreaIds = administrationVO.getCargoAreaIds();
                //添加营业人员
                insertPosClerk(cargoAreaIds, user);
            }
            if (roleIds.contains(39L)) {
                Long teamId = administrationVO.getTeamId();
                //删除旧的收款人员及权限信息
                posPayeeMapper.deleteByUserId(user.getId());
                posPayeeAuthMapper.deleteByUserId(user.getId());
                //添加收款人员及收款人员的权限信息
                insertPosPayeeAndPosPayeeAuth(user, teamId);
            }
        }
    }

    /**
     * 添加收款人员及收款人员的权限信息
     *
     * @param user
     * @param teamId
     */
    private void insertPosPayeeAndPosPayeeAuth(User user, Long teamId) {
        if (teamId != null) {
            PosTeamVO posTeamVO = new PosTeamVO();
            PosTeam posTeam = posTeamMapper.selectByPrimaryKey(teamId);
            if (posTeam == null) new BusinessException("班组不存在");
            posTeamVO.setId(posTeam.getId());
            posTeamVO.setCode(posTeam.getCode());
            posTeamVO.setName(posTeam.getName());

            PosPayee posPayee = PosPayee.getPosPayee(user, posTeamVO);
            //添加收款人员
            posPayeeMapper.insertSelective(posPayee);
            PosPayeeAuth posPayeeAuth = PosPayeeAuth.getPosPayeeAuth(posPayee);
            //添加收款人员权限
            posPayeeAuthMapper.insertSelective(posPayeeAuth);
        }
    }

    /**
     * 添加营业人员
     *
     * @param cargoAreaIds
     * @param user
     */
    private void insertPosClerk(List<Long> cargoAreaIds, User user) {
        if (cargoAreaIds != null && !cargoAreaIds.isEmpty()) {
            List<PosWarehouseCargoAreaVO> cargoList = warehouseCargoAreaMapper.selectCargoAreaByAargoAreaIds(cargoAreaIds);
            for (PosWarehouseCargoAreaVO vo : cargoList) {
                PosClerk posClerk = PosClerk.getPosClerk(user, vo);
                posClerkMapper.insertSelective(posClerk);
            }
        }
    }

    /**
     * 修改营业人员及收款人员
     *
     * @param user
     * @param administrationVO
     */
    private void updatePosUserInfo(User user, UserAdministrationVO administrationVO) {
        List<Long> roleIds = administrationVO.getRoleIds();
        if (roleIds != null && !roleIds.isEmpty()) {
            //判断包含营业人员与收款人员的情况，38 为营业人员，39为收款人员
            if (roleIds.contains(38L)) {
                List<Long> cargoAreaIds = administrationVO.getCargoAreaIds();
                //删除旧的营业人员的信息
                posClerkMapper.deleteByUserId(user.getId());
                //添加营业人员
                insertPosClerk(cargoAreaIds, user);
            } else {
                //删除旧的营业人员的信息
                posClerkMapper.deleteByUserId(user.getId());
            }
            if (roleIds.contains(39L)) {
                Long teamId = administrationVO.getTeamId();
                //删除旧的收款人员及权限信息
                posPayeeMapper.deleteByUserId(user.getId());
                posPayeeAuthMapper.deleteByUserId(user.getId());
                //添加收款人员及收款人员的权限信息
                insertPosPayeeAndPosPayeeAuth(user, teamId);
            } else {
                List<Long> userType = administrationVO.getUserType();
                //判断是否有pos用户类型
                if (!userType.contains(Long.parseLong(UserTypeEum.POS_USER.getCode() + ""))
                        && !userType.contains(Long.parseLong(UserTypeEum.CLOUD_POS_USER.getCode() + ""))) {
                    //删除旧的收款人员及权限信息
                    posPayeeMapper.deleteByUserId(user.getId());
                    posPayeeAuthMapper.deleteByUserId(user.getId());
                }
            }
        } else {
            //删除旧的营业人员的信息
            posClerkMapper.deleteByUserId(user.getId());
            List<Long> userType = administrationVO.getUserType();
            //判断是否有pos用户类型
            if (!userType.contains(Long.parseLong(UserTypeEum.POS_USER.getCode() + ""))
                    && !userType.contains(Long.parseLong(UserTypeEum.CLOUD_POS_USER.getCode() + ""))) {
                //删除旧的收款人员及权限信息
                posPayeeMapper.deleteByUserId(user.getId());
                posPayeeAuthMapper.deleteByUserId(user.getId());
            }
        }
    }

    /**
     * 当开启审批流程的时候添加或是修改对应的pos的营业人员及收款人员
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdateWithApprovalControl(Long id) {
        //获取员工信息
        UserAdministration administration = userAdministrationMapper.selectByUserId(id);
        User user = userMapper.selectByPrimaryKey(id);
        if (administration != null) {
            String strIds = administration.getCargoAreaIds();
            List<Long> cargoAreaIds = StringSplit.strSplit(strIds);
            if (cargoAreaIds != null && !cargoAreaIds.isEmpty()) {
                //删除旧的营业人员的信息
                posClerkMapper.deleteByUserId(id);
                //添加营业人员
                insertPosClerk(cargoAreaIds, user);
            } else {
                //删除旧的营业人员的信息
                posClerkMapper.deleteByUserId(id);
            }
            Long teamId = administration.getTeamId();
            if (teamId != null && teamId > 0) {
                //删除旧的收款人员及权限信息
                posPayeeMapper.deleteByUserId(id);
                posPayeeAuthMapper.deleteByUserId(id);
                //添加收款人员及收款人员的权限信息
                insertPosPayeeAndPosPayeeAuth(user, teamId);
            } else {
                //判断是否有pos用户类型
                List<Long> userType = StringSplit.strSplit(administration.getUserType());
                if (!userType.contains(Long.parseLong(UserTypeEum.POS_USER.getCode() + ""))
                        && !userType.contains(Long.parseLong(UserTypeEum.CLOUD_POS_USER.getCode() + ""))) {
                    //删除旧的收款人员及权限信息
                    posPayeeMapper.deleteByUserId(user.getId());
                    posPayeeAuthMapper.deleteByUserId(user.getId());
                }
            }
        }
    }

    /**
     * 验证添加的用户类型
     *
     * @param addUserVO
     * @param userVO
     */
    private void validChainType(AddUserVO addUserVO, UserVO userVO) {
        UserAdministrationVO administrationVO = addUserVO.getUserAdministrationDTO();
        Long enterpriseId = addUserVO.getEnterpriseId();
        //如果添加的是总部的人员是不能为Pos类型的
        if (userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            if (userVO.getEnterpriseId().equals(enterpriseId)) {
                if (administrationVO.getUserType().contains(Long.parseLong(String.valueOf(UserTypeEum.POS_USER.getCode())))
                        || administrationVO.getUserType().contains(Long.parseLong(String.valueOf(UserTypeEum.CLOUD_POS_USER.getCode())))) {
                    throw new BusinessException("总部不能选择pos用户");
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(AddUserVO addUserVO, UserVO userVO) throws Exception {

        validChainType(addUserVO, userVO);
        UserAdministrationVO administrationVO = addUserVO.getUserAdministrationDTO();
        UserPersonalVO userPersonalVO = addUserVO.getUserPersonalDTOS();
        UserAdministration oldAdministration = userAdministrationMapper.selectByPrimaryKey(administrationVO.getId());

        User oldUser = userMapper.selectByPrimaryKey(addUserVO.getId());

        UserPersonal oldUserPersonal = userPersonalMapper.selectByPrimaryKey(userPersonalVO.getId());

        Long enterpriseId = addUserVO.getEnterpriseId();

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);

        User user = User.getUser4AddUserVO(addUserVO, userVO, enterprise, false);
        user.setStatus(administrationVO.getStatus());

        //业务需求更改，添加人员信息的同时添加收款人员及营业人员
        initPosUserNew(user, userVO, administrationVO, false);
        /**
         * 如果修改,状态变为待审核
         */
        user.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue());
        userMapper.updateByPrimaryKeySelective(user);


        if (!oldAdministration.getRoleIds().equals(administrationVO.getRoleIds())) {
            /**
             * 保存权限关系表
             */
            userRoleComponent.save(user, administrationVO.getRoleIds());
        }

        UserAdministration userAdministration = UserAdministration.getUserAdministration4AddUserVO(addUserVO, userVO, user, enterprise, false);

        /**
         *  毕业时间 graduation_time ，参加工作时间 working_hours ，入职时间 working_hours ，转正时间 full_time ，柜组 cargo_area_ids ，班组 team_id
         *  可修改为null
         */
        userAdministrationMapper.updateByPrimaryKeyPartSelective(userAdministration);
        List<Long> deptIds = addUserVO.getUserAdministrationDTO().getDeptIds();
        if (!CollectionUtils.isEmpty(deptIds)) {
            String oldDeptIds = oldAdministration.getDeptIds();
            String newDeptIds = userAdministration.getDeptIds();
            if (!oldDeptIds.equals(newDeptIds)) {
                userDeptMapper.deleteByUserId(user.getId());
                List<UserDept> depts = UserDept.getUserUserDepts4UserDeptVOs(deptIds, userVO, user, true);
                for (UserDept dept : depts) {
                    userDeptMapper.insertSelective(dept);
                }

            }
        }
        List<Long> positionIds = addUserVO.getUserAdministrationDTO().getPositionIds();
        if (!CollectionUtils.isEmpty(positionIds)) {
            String oldPIds = oldAdministration.getPositionIds();
            String newPIds = userAdministration.getPositionIds();
            if (!oldPIds.equals(newPIds)) {
                userPositionMapper.deleteByUserId(user.getId());
                List<UserPosition> positions = UserPosition.getUserUserPositions4VOs(positionIds, userVO, user, true);
                for (UserPosition dept : positions) {
                    userPositionMapper.insertSelective(dept);
                }

            }
        }


        UserPersonal userPersonal = UserPersonal.getUserPersonal4UserPersonalVO(addUserVO, userVO, user, enterprise, false);
        /**
         *  出生日期 birth_dat，照片附件ID photo_id 可修改为null
         */
        userPersonalMapper.updateByPrimaryKeyPartSelective(userPersonal);

        List<Long> deleteIds = addUserVO.getDeleteUserQualificationConfigIds();

        for (Long id : deleteIds) {
            userQualificationConfigMapper.deleteByPrimaryKey(id);
        }


        List<UserQualificationConfig> userQualificationConfigs = UserQualificationConfig.getUserQualificationConfigs4VOs(addUserVO.getUserQualificationConfigDTOS(), userVO, user);

        for (UserQualificationConfig config : userQualificationConfigs) {
            if (null != config.getId()) {
                userQualificationConfigMapper.updateByPrimaryKeySelective(config);
            } else {
                userQualificationConfigMapper.insertSelective(config);
            }

        }

        List<UserModifyRecordWithBLOBs> userModifyRecordWithBLOBs = getUserModifyRecord(userVO, user, userAdministration, userPersonal
                , oldUser, oldAdministration, oldUserPersonal, user.getUpdateTime());

        for (UserModifyRecordWithBLOBs ur : userModifyRecordWithBLOBs) {
            ur.setRemark(addUserVO.getRemark());
            userModifyRecordMapper.insertSelective(ur);
        }

        /**
         * 如果用户行政信息之前选择的是 pos,就不需要再做初始化pos用户
         */
       /* String userType = oldUserAdmin.getUserType();

        List<Long> longs = StringSplit.strSplit(userType);
        if(!longs.contains(Long.parseLong(UserTypeEum.POS_USER.getCode()+""))
                && !longs.contains(Long.parseLong(UserTypeEum.CLOUD_POS_USER.getCode()+""))){

            intPosUser(user,userAdministration);
        }*/

        //审批流对象
        // 查询一条默认初始化的审批流/
        Integer chainType = userVO.getChainType();
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), userVO.getEnterpriseName(),
                userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
                chainType.equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                "0102", user.getId(), user.getCode(), user.getName());
        approvalFlowComponent.apply(submitApprovalFlowVO, userVO);
    }

    public List<UserModifyRecordWithBLOBs> getUserModifyRecord(UserVO userVO,
                                                               User newUser, UserAdministration newUserAdministration, UserPersonal newUserPersonal
            , User user, UserAdministration userAdministration, UserPersonal userPersonal, Date updateDate) throws Exception {

        Map<String, Object> newUserMap = modifyRecordCompoent.getFieldsMap(newUser);
        Map<String, Object> newUserAdministrationMap = modifyRecordCompoent.getFieldsMap(newUserAdministration);
        Map<String, Object> newUserPersonalMap = modifyRecordCompoent.getFieldsMap(newUserPersonal);


        Map<String, Object> userMap = modifyRecordCompoent.getFieldsMap(user);
        Map<String, Object> userAdministrationMap = modifyRecordCompoent.getFieldsMap(userAdministration);
        Map<String, Object> userPersonalMap = modifyRecordCompoent.getFieldsMap(userPersonal);

        Map<String, String> fieldMustMap = fieldMustMap();

        List<UserModifyRecordWithBLOBs> userModifyRecord = getModifyRecordList(userVO, user, "saas_user", user.getId()
                , updateDate, userMap, newUserMap, fieldMustMap);

        List<UserModifyRecordWithBLOBs> userAdministrationRecord = getModifyRecordList(userVO, user, "saas_user_administration", user.getId()
                , updateDate, userAdministrationMap, newUserAdministrationMap, fieldMustMap);

        List<UserModifyRecordWithBLOBs> userPersonalRecord = getModifyRecordList(userVO, user, "saas_user_personal", user.getId()
                , updateDate, userPersonalMap, newUserPersonalMap, fieldMustMap);

        userModifyRecord.addAll(userAdministrationRecord);
        userModifyRecord.addAll(userPersonalRecord);
        return userModifyRecord;
    }

    private List<UserModifyRecordWithBLOBs> getModifyRecordList(
            UserVO userVO, User user, String tableName, Long keyId, Date updateTime
            , Map<String, Object> valMap
            , Map<String, Object> newMap, Map<String, String> fieldMustMap) {

        List<UserModifyRecordWithBLOBs> modifyRecordWithBLOBs = new ArrayList<>();

        for (Map.Entry<String, String> entry : fieldMustMap.entrySet()) {
            String columnName = entry.getKey();
            Object obj = valMap.get(columnName);
            Object newObj = newMap.get(columnName);
            // saas_user,saas_user_personal两个表的字段：状态 status，不做修改记录
            if (("saas_user".equals(tableName) || "saas_user_personal".equals(tableName)) && "status".equals(columnName)) {
                continue;
            }
            if (obj != newObj) {
                String oldContent = obj == null ? "" : obj.toString();
                String newContent = newObj == null ? "" : newObj.toString();
                if (oldContent.equals(newContent)) {
                    continue;
                }

                UserModifyRecordWithBLOBs userModify = new UserModifyRecordWithBLOBs();
                userModify.setUserId(user.getId());
                userModify.setEnterpriseId(user.getEnterpriseId());
                userModify.setParentId(user.getParentId());
                userModify.setTableName(tableName);
                userModify.setKeyId(keyId);
                userModify.setColumnEnName(entry.getKey());
                userModify.setColumnChName(entry.getValue());
                userModify.setUpdateTime(updateTime);
                userModify.setCreaterId(userVO.getUserId());
                userModify.setCreaterCode(userVO.getUserCode());
                userModify.setCreaterName(userVO.getUserName());
                userModify.setCreateTime(new Date());
                userModify.setModifierId(userVO.getUserId());
                userModify.setModifierCode(userVO.getUserCode());
                userModify.setModifierName(userVO.getUserName());
                userModify.setUpdateTime(new Date());
                // set原内容，新内容
                userModify = setOldOrNewContent(userModify, tableName, columnName, oldContent, newContent);
                modifyRecordWithBLOBs.add(userModify);
            }
        }

        return modifyRecordWithBLOBs;
    }

    /**
     * set员工修改记录的原内容和新内容
     * 将部分字段内容转换成中文描述
     *
     * @param userModify
     * @param tableName  表名
     * @param columnName 字段名（英文）
     * @param oldContent 原内容
     * @param newContent 新内容
     * @return
     */
    private UserModifyRecordWithBLOBs setOldOrNewContent(UserModifyRecordWithBLOBs userModify, String tableName, String columnName, String oldContent, String newContent) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(oldContent)) {
            oldContent = setContent(tableName, columnName, oldContent);
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(newContent)) {
            newContent = setContent(tableName, columnName, newContent);
        }
        userModify.setOldContent(oldContent);
        userModify.setNewContent(newContent);
        return userModify;
    }

    /**
     * 特殊字段内容转换成中文描述
     *
     * @param tableName  表名
     * @param columnName 字段名（英文）
     * @param content    字段内容
     * @return
     */
    private String setContent(String tableName, String columnName, String content) {
        // 员工-行政信息 saas_user_administration
        if ("saas_user_administration".equals(tableName)) {
            switch (columnName) {
                /*
                 * 学历 educationId ,专业 majorId 共用一张表
                 */
                case "educationId": {
                }
                case "majorId": {
                    return educationMajorMapper.getNameByPrimaryKey(Long.parseLong(content));
                }

                /*
                 * 毕业时间 graduationTime，参加工作时间 workingHours，入职时间 inductionTime，转正时间fullTime 统一转换为yyyy-MM-dd
                 */
                case "graduationTime": {
                }
                case "workingHours": {
                }
                case "inductionTime": {
                }
                case "fullTime": {
                    return DateUtils.originalStringToString(content, DateUtils.FMT_DATE);
                }

                // 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）userType
                case "userType": {
                    return UserTypeEum.getValue4CodeStr(content);
                }
                // 用工性质（0-兼职；1-全职）useNature
                case "useNature": {
                    return org.apache.commons.lang3.StringUtils.isNumericSpace(content) ? UseNatureEum.getValue(Integer.parseInt(content)) : "";
                }
                // 状态（0-离职；1-在职）status
                case "status": {
                    return org.apache.commons.lang3.StringUtils.isNumericSpace(content) ? UserStatusEum.getValue(Integer.parseInt(content)) : "";
                }
                // 班组 teamId
                case "teamId": {
                    return posTeamMapper.getNameByPrimaryKey(Long.parseLong(content));
                }
                // 受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它） limitVariety
                case "limitVariety": {
                    return LimitVarietyType.getName4CodeStr(content);
                }
                // 受限品种范围 limitVarietyRange
                case "limitVarietyRange": {
                    return getLimitVarietyRangeDescStr(content);
                }
                // 部门 deptIds
                case "deptIds": {
                    return getDeptsDescStr(content);
                }
                // 岗位 positionIds
                case "positionIds": {
                    return getPositionDescStr(content);
                }
                // 角色 roleIds
                case "roleIds": {
                    return getRoleDescStr(content);
                }
                // 柜组 cargoAreaIds
                case "cargoAreaIds": {
                    return getCargoAreaDescStr(content);
                }
                default:
                    break;
            }
        }
        // 员工-个人信息 saas_user_personal
        if ("saas_user_personal".equals(tableName)) {
            switch (columnName) {
                // 性别（0-男；1-女；2-其它）sex
                case "sex": {
                    return org.apache.commons.lang3.StringUtils.isNumericSpace(content) ? SexType.getName(Integer.parseInt(content)) : "";
                }
                // 婚姻状况（0-未婚；1-已婚）maritalStatus
                case "maritalStatus": {
                    return org.apache.commons.lang3.StringUtils.isNumericSpace(content) ? MaritalStatus.getName(Integer.parseInt(content)) : "";
                }
                // 民族 nationId
                case "nationId": {
                    return nationMapper.getNameByPrimaryKey(Long.parseLong(content));
                }
                // 出生日期 birthDate
                case "birthDate": {
                    return DateUtils.originalStringToString(content, DateUtils.FMT_DATE);
                }
                // 照片 photoId
                case "photoId": {
                    return fileMapper.getNameByPrimaryKey(Long.parseLong(content));
                }
                default:
                    break;
            }
        }
        return content;
    }

    /**
     * 柜组id串转换成经验范围柜组串
     *
     * @param cargoAreaIds
     * @return
     */
    private String getCargoAreaDescStr(String cargoAreaIds) {
        List<Long> cargoAreaIdList = StringSplit.strSplit(cargoAreaIds);
        if (CollectionUtils.isEmpty(cargoAreaIdList)) {
            return "";
        } else {
            List<WarehouseCargoArea> cargoAreas = warehouseCargoAreaMapper.selectByIds(cargoAreaIdList);
            StringBuilder name = new StringBuilder();
            for (Long id : cargoAreaIdList) {
                for (WarehouseCargoArea cargoArea : cargoAreas) {
                    if (cargoArea.getId().equals(id)) {
                        name.append(cargoArea.getName()).append(",");
                        break;
                    }
                }
            }
            return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
        }
    }

    /**
     * 经验范围id串转换成经验范围名称串
     *
     * @param scopeIds
     * @return
     */
    private String getLimitVarietyRangeDescStr(String scopeIds) {
        List<Long> scopeIdList = StringSplit.strSplit(scopeIds);
        if (CollectionUtils.isEmpty(scopeIdList)) {
            return "";
        } else {
            List<BusinessScope> businessScopes = businessScopeMapper.selectByIds(scopeIdList);
            StringBuilder name = new StringBuilder();
            for (Long id : scopeIdList) {
                for (BusinessScope businessScope : businessScopes) {
                    if (businessScope.getId().equals(id)) {
                        name.append(businessScope.getName()).append(",");
                        break;
                    }
                }
            }
            return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
        }
    }

    /**
     * 角色id串转换成角色名称串
     *
     * @param roleIds
     * @return
     */
    private String getRoleDescStr(String roleIds) {
        List<Long> roleIdList = StringSplit.strSplit(roleIds);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return "";
        } else {
            List<SysRole> sysRoles = sysRoleMapper.selectByIds(roleIdList);
            StringBuilder name = new StringBuilder();
            for (Long id : roleIdList) {
                for (SysRole sysRole : sysRoles) {
                    if (sysRole.getId().equals(id)) {
                        name.append(sysRole.getName()).append(",");
                        break;
                    }
                }
            }
            return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
        }
    }

    /**
     * 岗位id串转换成部门名称串
     *
     * @param positionIds
     * @return
     */
    private String getPositionDescStr(String positionIds) {
        List<Long> positionIdList = StringSplit.strSplit(positionIds);
        if (CollectionUtils.isEmpty(positionIdList)) {
            return "";
        } else {
            List<Position> positions = positionMapper.selectByIds(positionIdList);
            StringBuilder name = new StringBuilder();
            for (Long id : positionIdList) {
                for (Position position : positions) {
                    if (position.getId().equals(id)) {
                        name.append(position.getName()).append(",");
                        break;
                    }
                }
            }
            return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
        }
    }


    /**
     * 部门id串转换成部门名称串
     *
     * @param deptIds 部门id串
     * @return
     */
    private String getDeptsDescStr(String deptIds) {
        List<Long> deptIdList = StringSplit.strSplit(deptIds);
        if (CollectionUtils.isEmpty(deptIdList)) {
            return "";
        } else {
            List<Department> departments = departmentMapper.selectByIds(deptIdList);
            StringBuilder name = new StringBuilder();
            for (Long id : deptIdList) {
                for (Department department : departments) {
                    if (department.getId().equals(id)) {
                        name.append(department.getName()).append(",");
                        break;
                    }
                }
            }
            return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
        }
    }

    private Map<String, String> fieldMustMap() {
        Map<String, String> fieldNames = new HashMap();
        fieldNames.put("enterpriseId", "企业ID");
        fieldNames.put("code", "编码");
        fieldNames.put("pinyin", "检索码");
        fieldNames.put("name", "员工名称");
        fieldNames.put("remark", "备注");
        fieldNames.put("status", "状态");

        fieldNames.put("limitVarietyRange", "受限品种范围");
        fieldNames.put("userType", "用户类型");
        fieldNames.put("loginAccount", "登录账号");
        fieldNames.put("password", "登录密码");
        fieldNames.put("passwordConfirm", "密码确认");
        fieldNames.put("deptIds", "部门");
        fieldNames.put("positionIds", "岗位");
        fieldNames.put("roleIds", "角色");
        fieldNames.put("cargoAreaIds", "柜组");
        fieldNames.put("teamId", "班组");
        fieldNames.put("limitVariety", "受限品种");
        fieldNames.put("graduationTime", "毕业时间");
        fieldNames.put("graduationUniversity", "毕业院校");
        fieldNames.put("educationId", "学历");
        fieldNames.put("majorId", "专业");
        fieldNames.put("workingHours", "参加工作时间");
        fieldNames.put("inductionTime", "入职时间");
        fieldNames.put("fullTime", "转正时间");
        fieldNames.put("certificateNum", "上岗证号");
        fieldNames.put("useNature", "用工性质");
        fieldNames.put("userAbout", "员工简介");


        fieldNames.put("sex", "性别");
        fieldNames.put("idNum", "身份证号");
        fieldNames.put("birthDate", "出生日期");
        fieldNames.put("maritalStatus", "婚姻状况");
        fieldNames.put("nationId", "民族");
        fieldNames.put("politicalOutlook", "政治面貌");
        fieldNames.put("originPlace", "籍贯");
        fieldNames.put("adderss", "住址");
        fieldNames.put("mobilePhone", "手机");
        fieldNames.put("telephone", "电话");
        fieldNames.put("wechatNum", "微信");
        fieldNames.put("qqNum", "QQ");
        fieldNames.put("email", "邮箱");
        fieldNames.put("photoId", "照片");

        return fieldNames;
    }


    /**
     * 重置密码
     *
     * @param userVO
     * @param userId
     */
    @Override
    public void resetPassword(UserVO userVO, Long userId) {

        List<UserAdministration> userAdministrations = userAdministrationMapper.selectUserAdministrationByUserId(userId);
        for (UserAdministration userAdministration : userAdministrations) {

            String newPassword = PasswordUtils.getDefultPassword();
            UserAdministration newUserAdmin = new UserAdministration();
            newUserAdmin.setPassword(newPassword);
            newUserAdmin.setPasswordConfirm(newPassword);
            newUserAdmin.setId(userAdministration.getId());
            newUserAdmin.setModifierId(userVO.getUserId());
            newUserAdmin.setModifierCode(userVO.getUserCode());
            newUserAdmin.setModifierName(userVO.getUserName());
            newUserAdmin.setUpdateTime(new Date());
            userAdministrationMapper.updateByPrimaryKeySelective(newUserAdmin);

            User user = userMapper.selectByPrimaryKey(userAdministration.getUserId());
            if (StringUtils.isEmpty(user.getRgtUserId())) {
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK, "融贯通用户不存在");
            }
            /**
             * 修改密码为00000000
             */
            Map<String, Object> map = new HashMap<>();
            map.put("username", userAdministration.getLoginAccount());
            map.put("password", newPassword);
//            map.put("uid",user.getRgtUserId());
            rgtService.updateUserPwdByUserName(map);

        }
    }


    /**
     * 初始化必填的资质信息
     *
     * @return
     */
    @Override
    public List<UserQualificationConfigReturnVO> initUserQualificationConfig(UserVO user, List<Long> positionIds) {
        Long enterpriseId = 0L;
        if (user.getChainType() != ChainType.Headquarters.getCode()) {
            enterpriseId = user.getParentId();
        } else {
            enterpriseId = user.getEnterpriseId();
        }
        Map<String, Object> map = new HashMap<>();

        String stringAppendSymbol = StringSplit.StringAppendSymbol(positionIds);
        map.put("enterpriseId", enterpriseId);
        map.put("pIds", stringAppendSymbol);
        List<UserQualification> userQualifications = userQualificationMapper.selectTypeMustByEnterpriseId(map);

        List<UserQualificationConfigReturnVO> list = UserQualificationConfigReturnVO.getAddUserInitVOs(userQualifications);

        return list;
    }

    @Override
    public List<UserQualificationConfigReturnVO> initUserQualificationConfig(UserVO user) {
        Long enterpriseId = 0L;
        if (user.getChainType() != ChainType.Headquarters.getCode()) {
            enterpriseId = user.getParentId();
        } else {
            enterpriseId = user.getEnterpriseId();
        }
        Map<String, Object> map = new HashMap<>();

        map.put("enterpriseId", enterpriseId);
        List<UserQualification> userQualifications = userQualificationMapper.selectTypeMustByEnterpriseIds(map);

        List<UserQualificationConfigReturnVO> list = UserQualificationConfigReturnVO.getAddUserInitVOs(userQualifications);

        return list;
    }


    /**
     * 导出
     *
     * @param output
     * @param userVO
     * @param enterprise
     * @param dept
     * @param position
     * @param role
     * @param education
     * @param major
     * @param useNature
     * @param status
     * @param queryStr
     */
    @Override
    public void excelExport4User(OutputStream output, UserVO userVO, Long enterprise,
                                 Long dept,
                                 Long position,
                                 Long role,
                                 Long education,
                                 Long major,
                                 Long useNature,
                                 Long status,
                                 String queryStr,
                                 Integer approveStatus) {

        Map<String, Object> map = new HashMap<>();

        /**
         * 审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）
         */
        if (null != approveStatus &&
                (approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue()
                        || approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue()
                        || approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())) {

            map.put("approveStatus", approveStatus);

        }

        if (dept != null) {
            map.put("dept", dept);
        }

        if (position != null) {
            map.put("position", position);
        }

        if (role != null) {
            map.put("role", role);
        }

        if (education != null) {
            map.put("education", education);
        }

        if (major != null) {
            map.put("major", major);
        }

        if (useNature != null) {
            map.put("useNature", useNature);
        }

        if (status != null) {
            map.put("status", status);
        }

        if (!StringUtils.isEmpty(queryStr)) {
            map.put("queryStr", queryStr);
        }


        if (null == enterprise) {
            map.put("enterprise", userVO.getEnterpriseId());
            map.put("parentId", userVO.getEnterpriseId());
        } else {
            map.put("queryEnterprise", "queryEnterprise");
            map.put("enterprise", enterprise);
            map.put("parentId", enterprise);
        }
        List<QueryUserVO> queryUserVOS = userMapper.selectByQueryParam(map);

        Set<Long> ids = QueryUserVO.getIds(queryUserVOS);

        List<UserReturnVO> userReturnVOS = findUser(ids);

        List<UserExcelVO> userExcelVOS = UserExcelVO.getUserExcelVO(userReturnVOS);
        List<UserAdministrationReturnVO> userAdministrationReturnVOS = findUserAdministration(ids);


        List<UserPersonalReturnVO> userPersonalReturnVOS = findUserPersonalReturnVO(ids);

        for (UserExcelVO userExcelVO : userExcelVOS) {
            for (UserAdministrationReturnVO userAdministrationReturnVO : userAdministrationReturnVOS) {
                if (userExcelVO.getId().equals(userAdministrationReturnVO.getUserId())) {
                    UserExcelVO.getUserAdminExcelVO(userAdministrationReturnVO, userExcelVO);
                }
            }
        }

        for (UserExcelVO userExcelVO : userExcelVOS) {
            for (UserPersonalReturnVO userPersonalReturnVO : userPersonalReturnVOS) {
                if (userExcelVO.getId().equals(userPersonalReturnVO.getUserId())) {
                    UserExcelVO.getUserPersonalExcelVO(userPersonalReturnVO, userExcelVO);
                }
            }
        }

        excelComponent.commExcelExport(output, generateRowHeaders(), userExcelVOS);
    }

    private LinkedHashMap<String, String> generateRowHeaders() {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        list.put("enterpriseName", "组织机构");
        list.put("code", "编码");
        list.put("name", "用户名称");
        list.put("userTypeDesc", "用户类型");
        list.put("loginAacount", "账号");
        list.put("deptsDesc", "部门");
        list.put("positionsDesc", "岗位");
        list.put("rolesDesc", "角色");
        list.put("statusDesc", "状态");
        list.put("limitVarietysDesc", "授权品种");
        list.put("limitVarietyRangeDesc", "授权品种范围");
        list.put("sexDesc", "性别");
        list.put("idNum", "身份证号");
        list.put("birthDateDesc", "出生日期");
        list.put("maritalStatusDesc", "婚姻状况");
        list.put("nationDesc", "民族");
        list.put("politicalOutlook", "政治面貌");
        list.put("originPlace", "籍贯");
        list.put("adderss", "住址");
        list.put("graduationTimeDesc", "毕业时间");
        list.put("graduationUniversity", "毕业院校");
        list.put("educationName", "学历");
        list.put("majorName", "专业");
        list.put("workingHoursDesc", "参加工作时间");
        list.put("email", "邮箱");
        list.put("mobilePhone", "手机");
        list.put("telephone", "电话");
        list.put("qqNum", "QQ");
        list.put("inductionTimeDesc", "入职日期");
        list.put("fullTimeDesc", "转正日期");
        list.put("certificateNum", "上岗证号");
        list.put("useNatureDesc", "用工性质");

        return list;
    }


    @Override
    public List<Tree> getRole4User(Long userId) {

        List<Tree> trees = new ArrayList<>();

        User user = userMapper.selectByPrimaryKey(userId);

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());

        List<UserAdministration> userAdministrations = userAdministrationMapper.selectUserAdministrationByUserId(userId);

        if (!CollectionUtils.isEmpty(userAdministrations)) {
            UserVO userVO = UserVO.getUserVO(enterprise, user, userAdministrations.get(0));

            List<BaseTreeVO> baseTreeVOS = commonComponent.roles(userVO);

            trees = commonComponent.structureCommTree(baseTreeVOS);

        }

        return trees;
    }


    @Override
    public User queryUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


    @Override
    public void saveDraftCache(UserVO userVO, DraftCacheVO<UserReturnVO> draftCache) {

        String redisKeyValue = draftCache.getId();

        DraftCacheVO<UserReturnVO> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.PURCHASE_PLAN.getCodePrefix());

        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
        redisComponent.saveDraftCacheVO(draftCacheVO);

    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) {

        redisComponent.removeDraftCacheVO(enterpriseId, type, redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) {
        Class<UserReturnVO> clazz = UserReturnVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(), OrderRule.PURCHASE_PLAN.getCodePrefix(), clazz);
    }
}
