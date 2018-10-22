package com.rograndec.feijiayun.chain.business.report.quality.user.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.EducationMajorMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.EducationMajor;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserQualificationConfigReturnVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.service.UserReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sulei
 */
@Service
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private UserMapper userMapper;

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
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private UserQualificationMapper userQualificationMapper;

    @Override
    public Page getUserReportPage(Page page, String param, Integer chainType, String enterpriseCode, String enterpriseName,
                                  String deptIds, String positionIds, String roleIds, Long educationId,
                                  Long majorId, Long useNature, Integer status, String order,
                                  String sort, UserVO loginUser) {
        /**
         * 排序两列的名称为code&enterpriseCode
         */
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("param", param);
        map.put("chainType", chainType);
        map.put("enterpriseCode", enterpriseCode);
        map.put("enterpriseName", enterpriseName);
        map.put("deptIds", deptIds);
        map.put("positionIds", positionIds);
        map.put("roleIds", roleIds);
        map.put("educationId", educationId);
        map.put("majorId", majorId);
        map.put("useNature", useNature);
        map.put("status", status);
        map.put("order", order);
        map.put("sort", sort);
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());

        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<UserReportPageVO> list = new ArrayList<UserReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = userMapper.selectFatherUserReportByMap(map);
            totalRecord = userMapper.selectFatherUserReportTotalRecord(map);
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = userMapper.selectSonUserReportByMap(map);
            totalRecord = userMapper.selectSonUserReportTotalRecord(map);
        }
        if (list != null && list.size() > 0) {
            for (UserReportPageVO vo : list) {
                vo = converTOPage(vo, vo.getDeptIds(), vo.getPositionIds(), vo.getRoleIds(), vo.getLimitVariety(), vo.getLimitVarietyRange(), vo.getEducationId());
            }
        }
        page.setResult(list);
        page.setTotalRecord(totalRecord);
        return page;
    }

    private UserReportPageVO converTOPage(UserReportPageVO vo, String deptIds, String positionIds, String roleIds, String limitVariety, String limitVarietyRange, Long educationId) {
        StringBuilder deptName = new StringBuilder();
        if (deptIds != null && !deptIds.equals("")) {
            String[] deptIdsList = deptIds.split(",");
            for (String s : deptIdsList) {
                Long id = Long.parseLong(s);
                Department department = departmentMapper.selectByPrimaryKey(id);
                if (department != null) {
                    deptName.append(department.getName() + ",");
                }
            }
        }
        if (!deptName.toString().equals("")){
            vo.setDeptIds(deptName.toString().substring(0,deptName.toString().length() - 1));
        }
        StringBuilder positionName = new StringBuilder();
        if (positionIds != null && !"".equals(positionIds)) {
            String[] positionIdsList = positionIds.split(",");
            for (String s : positionIdsList) {
                Long id = Long.parseLong(s);
                Position position = positionMapper.selectByPrimaryKey(id);
                if (position != null) {
                    positionName.append(position.getName() + ",");
                }
            }
        }
        if (!positionName.toString().equals("")){
            vo.setPositionIds(positionName.toString().substring(0,positionName.toString().length() - 1));
        }
        StringBuilder roleName = new StringBuilder();
        if (roleIds != null && !"".equals(roleIds)) {
            String[] roleIdsList = roleIds.split(",");
            for (String s : roleIdsList) {
                Long id = Long.parseLong(s);
                SysRole role = sysRoleMapper.selectByPrimaryKey(id);
                if (role != null) {
                    roleName.append(role.getName() + ",");
                }
            }
        }
        if (!roleName.toString().equals("")){
            vo.setRoleIds(roleName.toString().substring(0,roleName.toString().length() - 1));
        }

        StringBuilder limitVarietyName = new StringBuilder();
        if (limitVariety != null && !"".equals(limitVariety)) {
            String[] limitVarietyList = limitVariety.split(",");
            for (String s : limitVarietyList) {
                int id = Integer.parseInt(s);
                limitVarietyName.append(BusinessVariety.getName(id) + ",");
            }
        }
        if (!limitVarietyName.toString().equals("")){
            vo.setLimitVariety(limitVarietyName.toString().substring(0,limitVarietyName.toString().length() - 1));
        }

        StringBuilder rangeName = new StringBuilder();
        if (limitVarietyRange != null && !"".equals(limitVarietyRange)) {
            String[] limitVarietyRangeList = limitVarietyRange.split(",");
            for (String s : limitVarietyRangeList) {
                Long id = Long.parseLong(s);
                BusinessScope businessScope = businessScopeMapper.selectByPrimaryKey(id);
                if (businessScope != null) {
                    rangeName.append(businessScope.getName() + ",");
                }
            }
        }
        if (!rangeName.toString().equals("")){
            vo.setLimitVarietyRange(rangeName.toString().substring(0,rangeName.toString().length() - 1));
        }
        if (educationId != null && !"".equals(educationId)) {
            EducationMajor educationMajor = educationMajorMapper.selectByPrimaryKey(educationId);
            vo.setEducationName(educationMajor.getName());
        } else {
            vo.setEducationName("");
        }
        if(vo.getMajorId() != null) {
        	EducationMajor major = educationMajorMapper.selectByPrimaryKey(vo.getMajorId());
        	if(major != null) vo.setMajorName(major.getName());
        }
        return vo;
    }

    @Override
    public List<UserReportExcelVO> getExcelForm(String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, Long educationId, Long majorId, Long useNature, Integer status, String order, String sort, UserVO loginUser) {
        Map<String, Object> map = new HashMap<String, Object>(14);
        map.put("param", param);
        map.put("chainType", chainType);
        map.put("enterpriseCode", enterpriseCode);
        map.put("enterpriseName", enterpriseName);
        map.put("deptIds", deptIds);
        map.put("positionIds", positionIds);
        map.put("roleIds", roleIds);
        map.put("educationId", educationId);
        map.put("majorId", majorId);
        map.put("useNature", useNature);
        map.put("status", status);
        map.put("order", order);
        map.put("sort", sort);
        map.put("enterpriseId", loginUser.getEnterpriseId());

        List<UserReportExcelVO> excelList = new ArrayList<UserReportExcelVO>();
        Integer userChainType = loginUser.getChainType();
        List<UserReportPageVO> list = new ArrayList<UserReportPageVO>();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = userMapper.selectFatherUserReportByMap(map);
            if (list != null && list.size() > 0) {
                for (UserReportPageVO vo : list) {
                    UserReportExcelVO excelVO = converTOFatherExcel(vo);
                    excelList.add(excelVO);
                }
            }
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = userMapper.selectSonUserReportByMap(map);
            if (list != null && list.size() > 0) {
                for (UserReportPageVO vo : list) {
                    UserReportExcelVO excelVO = converTOFatherExcel(vo);
                    //分店的内容的组织机构和
                    excelVO.setEnterpriseCode(loginUser.getEnterpriseCode());
                    excelVO.setEnterpriseName(loginUser.getEnterpriseName());
                    excelList.add(excelVO);
                }
            }
        }
        return excelList;
    }

    private UserReportExcelVO converTOFatherExcel(UserReportPageVO vo) {
        UserReportExcelVO excelVO = new UserReportExcelVO();
        excelVO.setId(vo.getId());
        excelVO.setEnterpriseCode(vo.getEnterpriseCode() == null ? "" : vo.getEnterpriseCode());
        excelVO.setEnterpriseName(vo.getEnterpriseName() == null ? "" : vo.getEnterpriseName());
        excelVO.setCode(vo.getCode());
        excelVO.setName(vo.getName());
        excelVO.setLoginAccount(vo.getLoginAccount());

        excelVO = converTODetail(excelVO, vo.getDeptIds(), vo.getPositionIds(), vo.getRoleIds(), vo.getLimitVariety(), vo.getLimitVarietyRange(), vo.getEducationId());

        excelVO.setGraduationTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getGraduationTime()));
        excelVO.setGraduationUniversity(vo.getGraduationUniversity());
        excelVO.setMajorId(vo.getMajorId());
        excelVO.setMajorName(vo.getMajorName());
        excelVO.setWorkingHours(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getWorkingHours()));
        excelVO.setInductionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getInductionTime()));
        excelVO.setFullTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getFullTime()));
        excelVO.setCertificateNum(vo.getCertificateNum());
        excelVO.setUseNature(vo.getUseNature() == 0 ? "兼职" : "全职");
        excelVO.setStatus(vo.getStatus() == 0 ? "离职" : "在职");
        return excelVO;
    }

    private UserReportExcelVO converTODetail(UserReportExcelVO excelVO, String deptIds, String positionIds, String roleIds, String limitVariety, String limitVarietyRange, Long educationId) {
        StringBuilder deptName = new StringBuilder();
        if (deptIds != null && !deptIds.equals("")) {
            String[] deptIdsList = deptIds.split(",");
            for (String s : deptIdsList) {
                Long id = Long.parseLong(s);
                Department department = departmentMapper.selectByPrimaryKey(id);
                if (department != null) {
                    deptName.append(department.getName() + ",");
                }
            }
        }
        if (!deptName.toString().equals("")){
            excelVO.setDeptIds(deptName.toString().substring(0,deptName.toString().length() - 1));
        }
        StringBuilder positionName = new StringBuilder();
        if (positionIds != null && !"".equals(positionIds)) {
            String[] positionIdsList = positionIds.split(",");
            for (String s : positionIdsList) {
                Long id = Long.parseLong(s);
                Position position = positionMapper.selectByPrimaryKey(id);
                if (position != null) {
                    positionName.append(position.getName() + ",");
                }
            }
        }
        if (!positionName.toString().equals("")){
            excelVO.setPositionIds(positionName.toString().substring(0,positionName.toString().length() - 1));
        }
        StringBuilder roleName = new StringBuilder();
        if (roleIds != null && !"".equals(roleIds)) {
            String[] roleIdsList = roleIds.split(",");
            for (String s : roleIdsList) {
                Long id = Long.parseLong(s);
                SysRole role = sysRoleMapper.selectByPrimaryKey(id);
                if (role != null) {
                    roleName.append(role.getName() + ",");
                }
            }
        }
        if (!roleName.toString().equals("")){
            excelVO.setRoleIds(roleName.toString().substring(0,roleName.toString().length() - 1));
        }
        StringBuilder limitVarietyName = new StringBuilder();
        if (limitVariety != null && !"".equals(limitVariety)) {
            String[] limitVarietyList = limitVariety.split(",");
            for (String s : limitVarietyList) {
                int id = Integer.parseInt(s);
                limitVarietyName.append(BusinessVariety.getName(id) + ",");
            }
        }
        if (!limitVarietyName.toString().equals("")){
            excelVO.setLimitVariety(limitVarietyName.toString().substring(0,limitVarietyName.toString().length() - 1));
        }
        StringBuilder rangeName = new StringBuilder();
        if (limitVarietyRange != null && !"".equals(limitVarietyRange)) {
            String[] limitVarietyRangeList = limitVarietyRange.split(",");
            for (String s : limitVarietyRangeList) {
                Long id = Long.parseLong(s);
                BusinessScope businessScope = businessScopeMapper.selectByPrimaryKey(id);
                if (businessScope != null) {
                    rangeName.append(businessScope.getName() + ",");
                }
            }
        }
        if (!rangeName.toString().equals("")){
            excelVO.setLimitVarietyRange(rangeName.toString().substring(0,rangeName.toString().length() - 1));
        }
        if (educationId != null && !"".equals(educationId)) {
            EducationMajor educationMajor = educationMajorMapper.selectByPrimaryKey(educationId);
            excelVO.setEducationName(educationMajor.getName());
        } else {
            excelVO.setEducationName("");
        }
        if(excelVO.getMajorId() != null) {
        	EducationMajor major = educationMajorMapper.selectByPrimaryKey(excelVO.getMajorId());
        	if(major != null) excelVO.setMajorName(major.getName() == null?"":major.getName());
        }else {
        	excelVO.setMajorName("");
        }
        return excelVO;
    }

    @Override
    public void export(OutputStream output, List<UserReportExcelVO> userReportExcelVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("code", "员工编码");
        map.put("name", "员工名称");
        map.put("loginAccount", "登录账号");
        map.put("deptIds", "部门");
        map.put("positionIds", "岗位");
        map.put("roleIds", "角色");
        map.put("limitVariety", "受限品种");
        map.put("limitVarietyRange", "受限品种范围");

        map.put("graduationTime", "毕业时间");
        map.put("graduationUniversity", "毕业院校");
        map.put("educationName", "学历");

        map.put("majorName", "专业");
        map.put("workingHours", "参加工作时间");
        map.put("inductionTime", "入职时间");
        map.put("fullTime", "转正时间");
        map.put("certificateNum", "上岗证号");
        map.put("useNature", "用工性质");
        map.put("status", "状态");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("员工信息");
        purchaseGeneralComponent.commExcelExport(output, map, userReportExcelVO, name, new ArrayList<>(), "", true, new ArrayList<>());

    }

    @Override
    public Page getUserLicensePage(Page page, String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, String order, String sort, UserVO loginUser) {
        Map<String, Object> map = new HashMap<String, Object>(12);
        map.put("param", param);
        map.put("chainType", chainType);
        map.put("enterpriseCode", enterpriseCode);
        map.put("enterpriseName", enterpriseName);
        map.put("deptIds", deptIds);
        map.put("positionIds", positionIds);
        map.put("roleIds", roleIds);
        map.put("order", order);
        map.put("sort", sort);
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<UserLicenseReportPageVO> list = new ArrayList<UserLicenseReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = userMapper.selectFatherUserLicenseReportByMap(map);
            totalRecord = userMapper.selectFatherUserLicenseTotalRecord(map);
            if (list != null && list.size() > 0) {
                for (UserLicenseReportPageVO vo : list) {
                    Long id = vo.getId();
                    List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOs = userManagerService.findUserQualificationConfigReturnVOs(loginUser,id);
                    vo.setList(userQualificationConfigReturnVOs);
                    vo = addDeptAndPositionDetail(vo);
                }
            }
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = userMapper.selectSonUserLicenseReportByMap(map);
            totalRecord = userMapper.selectSonUserLicenseTotalRecord(map);
            if (list != null && list.size() > 0) {
                for (UserLicenseReportPageVO vo : list) {
                    Long id = vo.getId();
                    List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOs = userManagerService.findUserQualificationConfigReturnVOs(loginUser,id);
                    vo.setList(userQualificationConfigReturnVOs);
                    vo = addDeptAndPositionDetail(vo);
                }
            }
        }
        page.setResult(list);
        page.setTotalRecord(totalRecord);
        return page;
    }

    private UserLicenseReportPageVO addDeptAndPositionDetail(UserLicenseReportPageVO vo) {
        String deptIds = vo.getDeptIds();
        String positionIds = vo.getPositionIds();
        StringBuilder deptName = new StringBuilder();
        if (deptIds != null && !deptIds.equals(""))  {
            String[] deptIdsList = deptIds.split(",");
            for (String s : deptIdsList) {
                Long id = Long.parseLong(s);
                Department department = departmentMapper.selectByPrimaryKey(id);
                if (department != null) {
                    deptName.append(department.getName() + ",");
                }
            }
        }
        if (!deptName.toString().equals("")){
            vo.setDeptIds(deptName.toString().substring(0,deptName.toString().length() - 1));
        }
        StringBuilder positionName = new StringBuilder();
        if (positionIds != null && !"".equals(positionIds)) {
            String[] positionIdsList = positionIds.split(",");
            for (String s : positionIdsList) {
                Long id = Long.parseLong(s);
                Position position = positionMapper.selectByPrimaryKey(id);
                if (position != null) {
                    positionName.append(position.getName() + ",");
                }
            }
        }
        if (!positionName.toString().equals("")){
            vo.setPositionIds(positionName.toString().substring(0,positionName.toString().length() - 1));
        }
        return vo;
    }

    @Override
    public List<UserLicenseExcelVO> getUserLicenseExcelForm(String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, String order, String sort, UserVO loginUser) {
        Map<String, Object> map = new HashMap<String, Object>(14);
        map.put("param", param);
        map.put("chainType", chainType);
        map.put("enterpriseCode", enterpriseCode);
        map.put("enterpriseName", enterpriseName);
        map.put("deptIds", deptIds);
        map.put("positionIds", positionIds);
        map.put("roleIds", roleIds);
        map.put("order", order);
        map.put("sort", sort);
        map.put("enterpriseId", loginUser.getEnterpriseId());

        List<UserLicenseExcelVO> excelList = new ArrayList<UserLicenseExcelVO>();
        List<UserLicenseReportPageVO> list = new ArrayList<UserLicenseReportPageVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = userMapper.selectFatherUserLicenseReportByMap(map);
            if (list != null && list.size() > 0) {
                for (UserLicenseReportPageVO vo : list) {
                    Long id = vo.getId();
                    List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOs = userManagerService.findUserQualificationConfigReturnVOs(loginUser,id);
                    vo.setList(userQualificationConfigReturnVOs);
                }
            }
            if (list != null && list.size() > 0) {
                for (UserLicenseReportPageVO vo : list) {
                    List<UserQualificationConfigReturnVO> configList = vo.getList();
                    if (configList != null && configList.size() > 0) {
                        for (UserQualificationConfigReturnVO config : configList) {
                            UserLicenseExcelVO excelVO = converTOLicenseExcel(config, vo);
                            excelList.add(excelVO);
                        }
                    } else {
                        UserLicenseExcelVO excelVO = converTOLicenseExcel(new UserQualificationConfigReturnVO(), vo);
                        excelList.add(excelVO);
                    }

                }
            }
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = userMapper.selectSonUserLicenseReportByMap(map);
            if (list != null && list.size() > 0) {
                for (UserLicenseReportPageVO vo : list) {
                    Long id = vo.getId();
                    List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOs = userManagerService.findUserQualificationConfigReturnVOs(loginUser,id);
                    vo.setList(userQualificationConfigReturnVOs);
                }
            }
            if (list != null && list.size() > 0) {
                for (UserLicenseReportPageVO vo : list) {
                    List<UserQualificationConfigReturnVO> configList = vo.getList();
                    if (configList != null && configList.size() > 0) {
                        for (UserQualificationConfigReturnVO config : configList) {
                            UserLicenseExcelVO excelVO = converTOLicenseExcel(config, vo);
                            //分店的内容的组织机构和
                            excelVO.setEnterpriseCode(loginUser.getEnterpriseCode());
                            excelVO.setEnterpriseName(loginUser.getEnterpriseName());
                            excelList.add(excelVO);
                        }
                    } else {
                        UserLicenseExcelVO excelVO = converTOLicenseExcel(new UserQualificationConfigReturnVO(), vo);
                        //分店的内容的组织机构和
                        excelVO.setEnterpriseCode(loginUser.getEnterpriseCode());
                        excelVO.setEnterpriseName(loginUser.getEnterpriseName());
                        excelList.add(excelVO);
                    }
                }
            }
        }
        return excelList;
    }

    /**
     * 转换成资质的ExcelVO
     *
     * @param config
     * @param vo
     * @return
     */
    private UserLicenseExcelVO converTOLicenseExcel(UserQualificationConfigReturnVO config, UserLicenseReportPageVO vo) {
        UserLicenseExcelVO excelVO = new UserLicenseExcelVO();
        excelVO.setEnterpriseCode(vo.getEnterpriseCode());
        excelVO.setEnterpriseName(vo.getEnterpriseName());
        excelVO.setCode(vo.getCode());
        excelVO.setName(vo.getName());
        excelVO.setLoginAccount(vo.getLoginAccount());
        StringBuilder deptName = new StringBuilder();
        if (vo.getDeptIds() != null && !vo.getDeptIds().equals("")) {
            String[] deptIds = vo.getDeptIds().split(",");
            for (String s : deptIds) {
                Long id = Long.parseLong(s);
                Department department = departmentMapper.selectByPrimaryKey(id);
                if (department != null) {
                    deptName.append(department.getName() + ",");
                }
            }
        }
        if (!deptName.toString().equals("")){
            excelVO.setDeptIds(deptName.toString().substring(0,deptName.toString().length() - 1));
        }
        StringBuilder positionName = new StringBuilder();
        if (vo.getPositionIds() != null && !"".equals(vo.getPositionIds())) {
            String[] positionIds = vo.getPositionIds().split(",");
            for (String s : positionIds) {
                Long id = Long.parseLong(s);
                Position position = positionMapper.selectByPrimaryKey(id);
                if (position != null) {
                    positionName.append(position.getName() + ",");
                }
            }
        }
        if (!positionName.toString().equals("")){
            excelVO.setPositionIds(positionName.toString().substring(0,positionName.toString().length() - 1));
        }
        excelVO.setQualificationName(config.getQualificationName());
        excelVO.setGrade(config.getGrade());
        excelVO.setQualificationCode(config.getCode());
        excelVO.setRegisterCode(config.getRegisterCode());
        //地区 + 类别 + 适用范围
        excelVO.setRegion(config.getRegion());
        excelVO.setCategory(config.getCategory());
        excelVO.setRange(config.getRange());
        return excelVO;
    }

    @Override
    public void exportLicense(OutputStream output, List<UserLicenseExcelVO> userLicenseExcelVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("code", "员工编码");
        map.put("name", "员工名称");
        map.put("loginAccount", "登录账号");
        map.put("deptIds", "部门");
        map.put("positionIds", "岗位");
        map.put("qualificationName", "资质名称");
        map.put("grade", "等级");
        map.put("qualificationCode", "资格证书号");

        map.put("registerCode", "注册证书号");
        map.put("region", "适用地区");
        map.put("category", "适用类别");

        map.put("range", "适用范围");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("员工资质");
        purchaseGeneralComponent.commExcelExport(output, map, userLicenseExcelVO, name, new ArrayList<>(), "", true, new ArrayList<>());
    }

    @Override
    public Page getUserWarningPage(Page page, String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds, String positionIds, String roleIds, String order, String sort, UserVO loginUser) {
        Map<String, Object> map = new HashMap<String, Object>(12);
        map.put("param", param);
        map.put("chainType", chainType);
        map.put("enterpriseCode", enterpriseCode);
        map.put("enterpriseName", enterpriseName);
        map.put("deptIds", deptIds);
        map.put("positionIds", positionIds);
        map.put("roleIds", roleIds);
        map.put("order", order);
        map.put("sort", sort);
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",page.getStart());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<UserWarningPageVO> list = new ArrayList<UserWarningPageVO>();
        Integer userChainType = loginUser.getChainType();
        Integer totalRecord = 0;
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = userMapper.selectFatherUserWarningByMap(map);
            totalRecord = userMapper.selectFatherUserWarningTotalRecord(map);
            list = getUserWarningContext(loginUser,list);
            if (list != null && list.size() > 0) {
                for (UserWarningPageVO vo : list) {
                    vo = addDeptAndPositionWarningDetail(vo);
                }
            }
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            list = userMapper.selectSonUserWarningByMap(map);
            totalRecord = userMapper.selectSonUserWarningTotalRecord(map);
            list = getUserWarningContext(loginUser,list);
            if (list != null && list.size() > 0) {
                for (UserWarningPageVO vo : list) {
                    vo = addDeptAndPositionWarningDetail(vo);
                }
            }
        }
        page.setResult(list);
        page.setTotalRecord(totalRecord);
        return page;
    }

   /* @Override
    public List<UserWarningPageVO> getUserWarningPage(String order, String sort, UserVO loginUser) {
        Map<String, Object> map = new HashMap<String, Object>(5);
        if(!StringUtils.isEmpty(order)){
            map.put("order", order);
        }
        if(!StringUtils.isEmpty(sort)) {
            map.put("sort", sort);
        }
        map.put("enterpriseId", loginUser.getEnterpriseId());
        *//**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         *//*
        List<UserWarningPageVO> list = new ArrayList<UserWarningPageVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            *//**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             *//*
            list = userMapper.selectFatherUserWarningByMap(map);
            list = getUserWarningContext(list);
            if (list != null && list.size() > 0) {
                for (UserWarningPageVO vo : list) {
                    vo = addDeptAndPositionWarningDetail(vo);
                }
            }
        } else {
            *//**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             *//*
            list = userMapper.selectSonUserWarningByMap(map);
            list = getUserWarningContext(list);
            if (list != null && list.size() > 0) {
                for (UserWarningPageVO vo : list) {
                    vo = addDeptAndPositionWarningDetail(vo);
                }
            }
        }
        return list;
    }*/
    @Override
    public List<UserWarningExcelVO> getUserWarningExcelForm(String order, String sort, UserVO loginUser){

       return getUserWarningExcelForm(null, null, null, null, null, null, null, order, sort, loginUser);
    }

    @Override
    public List<UserWarningExcelVO> getUserWarning2WarinSet(UserVO loginUser, List<WarnSet> userWarnSets) {
        if(CollectionUtils.isEmpty(userWarnSets)){
            return new ArrayList<>();
        }
        Map<String, Object> map = new HashMap<String, Object>(10);
        map.put("enterpriseId", loginUser.getEnterpriseId());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<UserWarningExcelVO> warningExcel = new ArrayList<UserWarningExcelVO>();
        List<UserWarningPageVO> list = new ArrayList<UserWarningPageVO>();
        Integer userChainType = loginUser.getChainType();
        map.put("order", "code");
        list = userMapper.selectSonUserWarningByMap(map);
        list = getUserWarningContext2WarnSet(loginUser,list,userWarnSets);
        if (list != null && list.size() > 0) {
            for (UserWarningPageVO vo : list) {
                UserWarningExcelVO excelVO = new UserWarningExcelVO();
                vo = addDeptAndPositionWarningDetail(vo);
                excelVO = convertToWarningExcel(vo);
                excelVO.setEnterpriseCode(loginUser.getEnterpriseCode());
                excelVO.setEnterpriseName(loginUser.getEnterpriseName());
                warningExcel.add(excelVO);
            }
        }
        return warningExcel;
    }


    @Override
    public List<UserWarningExcelVO> getUserWarningExcelForm(String param, Integer chainType, String enterpriseCode, String enterpriseName, String deptIds
            , String positionIds, String roleIds, String order, String sort, UserVO loginUser) {
        Map<String, Object> map = new HashMap<String, Object>(10);
        map.put("param", param);
        map.put("chainType", chainType);
        map.put("enterpriseCode", enterpriseCode);
        map.put("enterpriseName", enterpriseName);
        map.put("deptIds", deptIds);
        map.put("positionIds", positionIds);
        map.put("roleIds", roleIds);
        map.put("order", order);
        map.put("sort", sort);
        map.put("enterpriseId", loginUser.getEnterpriseId());
        /**
         * 当前人员如果是总部的，那么可以查自己还有自己所属的分部，如果是分部，那么只能查自己的
         */
        List<UserWarningExcelVO> warningExcel = new ArrayList<UserWarningExcelVO>();
        List<UserWarningPageVO> list = new ArrayList<UserWarningPageVO>();
        Integer userChainType = loginUser.getChainType();
        if (userChainType == ChainType.Headquarters.getCode()) {
            /**
             * 总部情况(企业信息什么都不填的时候是搜的总部以及所有分部)
             */
            list = userMapper.selectFatherUserWarningByMap(map);
            list = getUserWarningContext(loginUser,list);
            if (list != null && list.size() > 0) {
                for (UserWarningPageVO vo : list) {
                    UserWarningExcelVO excelVO = new UserWarningExcelVO();
                    vo = addDeptAndPositionWarningDetail(vo);
                    excelVO = convertToWarningExcel(vo);
                    warningExcel.add(excelVO);
                }
            }
        } else {
            /**
             * 分店情况(企业信息为空的时候[因为没有可填的地方]只搜当前项)
             */
            map.put("order", "code");
            list = userMapper.selectSonUserWarningByMap(map);
            list = getUserWarningContext(loginUser,list);
            if (list != null && list.size() > 0) {
                for (UserWarningPageVO vo : list) {
                    UserWarningExcelVO excelVO = new UserWarningExcelVO();
                    vo = addDeptAndPositionWarningDetail(vo);
                    excelVO = convertToWarningExcel(vo);
                    excelVO.setEnterpriseCode(loginUser.getEnterpriseCode());
                    excelVO.setEnterpriseName(loginUser.getEnterpriseName());
                    warningExcel.add(excelVO);
                }
            }
        }
        return warningExcel;
    }

    private UserWarningExcelVO convertToWarningExcel(UserWarningPageVO pageVO) {
        UserWarningExcelVO vo = new UserWarningExcelVO();
        vo.setId(pageVO.getId());
        vo.setEnterpriseCode(pageVO.getEnterpriseCode());
        vo.setEnterpriseName(pageVO.getEnterpriseName());
        vo.setCode(pageVO.getCode());
        vo.setName(pageVO.getName());
        vo.setDeptIds(pageVO.getDeptIds());
        vo.setPositionIds(pageVO.getPositionIds());
        List<String> warningContext = pageVO.getWarningContext();
        StringBuilder warnContextName = new StringBuilder();
        if (warningContext != null && warningContext.size() > 0){
            for (String s : warningContext) {
                warnContextName.append(s + ",");
            }
        }
        vo.setWarningContext(warnContextName.toString());
        List<String> warningMessage = pageVO.getWarningMessage();
        StringBuilder warnMessageName = new StringBuilder();
        if (warningMessage != null && warningMessage.size() > 0){
            for (String s : warningMessage) {
                warnMessageName.append(s + ",");
            }
        }
        vo.setWarningMessage(warnMessageName.toString());
        return vo;
    }

    @Override
    public void exportWarning(OutputStream output, List<UserWarningExcelVO> userWarningExcelVO, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("code", "员工编码");
        map.put("name", "员工名称");
        map.put("deptIds", "部门");
        map.put("positionIds", "岗位");

        map.put("warningContext", "预警内容");

        map.put("warningMessage", "预警信息");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("员工资质预警");
        purchaseGeneralComponent.commExcelExport(output, map, userWarningExcelVO, name, new ArrayList<>(), "", true, new ArrayList<>());
    }

    private UserWarningPageVO addDeptAndPositionWarningDetail(UserWarningPageVO vo) {
        String deptIds = vo.getDeptIds();
        String positionIds = vo.getPositionIds();
        StringBuilder deptName = new StringBuilder();
        if (deptIds != null && !deptIds.equals(""))  {
            String[] deptIdsList = deptIds.split(",");
            for (String s : deptIdsList) {
                Long id = Long.parseLong(s);
                Department department = departmentMapper.selectByPrimaryKey(id);
                if (department != null) {
                    deptName.append(department.getName() + ",");
                }
            }
        }
        if (!deptName.toString().equals("")){
            vo.setDeptIds(deptName.toString().substring(0,deptName.toString().length() - 1));
        }
        StringBuilder positionName = new StringBuilder();
        if (positionIds != null && !"".equals(positionIds)) {
            String[] positionIdsList = positionIds.split(",");
            for (String s : positionIdsList) {
                Long id = Long.parseLong(s);
                Position position = positionMapper.selectByPrimaryKey(id);
                if (position != null) {
                    positionName.append(position.getName() + ",");
                }
            }
        }
        if (!positionName.toString().equals("")){
            vo.setPositionIds(positionName.toString().substring(0,positionName.toString().length() - 1));
        }
        return vo;
    }


    private List<UserWarningPageVO> getUserWarningContext2WarnSet(UserVO loginUser,List<UserWarningPageVO> list,List<WarnSet> userWarnSets) {
        List<UserWarningPageVO> realList = new ArrayList<UserWarningPageVO>();
        if (list != null && list.size() > 0) {
            for (UserWarningPageVO pageVO : list) {
                Long id = pageVO.getId();
                /**
                 * 预警内容
                 */
                List<String> warningContext = new ArrayList<String>();
                /**
                 * 预警信息
                 */
                List<String> warningMessage = new ArrayList<String>();

                List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOs = userManagerService.findUserQualificationConfigReturnVOs(loginUser,id);
                if (userQualificationConfigReturnVOs != null && userQualificationConfigReturnVOs.size() > 0) {
                    for (UserQualificationConfigReturnVO qualificationVO : userQualificationConfigReturnVOs) {
                        Long qualificationId = qualificationVO.getQualificationId();
                        /**
                         * 资质文件
                         */
                        Long fileId = qualificationVO.getFileId();
                        String grade = qualificationVO.getGrade();
                        String code = qualificationVO.getCode();
                        String registerCode = qualificationVO.getRegisterCode();
                        String region = qualificationVO.getRegion();
                        String category = qualificationVO.getCategory();
                        String range = qualificationVO.getRange();

                        WarnSet warnSet = userWarnSets.stream().filter(userWarnSet -> userWarnSet.getQualificationId().equals(qualificationVO.getQualificationId())).findFirst().orElse(null);
                        if(null != warnSet){
                            UserQualification userQualification = userQualificationMapper.selectByPrimaryKey(qualificationId);
                            if (null != userQualification) {
                                Integer fileMust = userQualification.getFileMust();//附件是否必填
                                Integer gradeMust = userQualification.getGradeMust();//等级是否必填
                                Integer codeMust = userQualification.getCodeMust();//资格证书号
                                Integer registerCodeMust = userQualification.getRegisterCodeMust();//注册证书号
                                Integer regionMust = userQualification.getRegionMust();
                                Integer categoryMust = userQualification.getCategoryMust();
                                Integer rangeMust = userQualification.getRangeMust();
                                if (fileId == null && fileMust == 1) {
                                    warningContext.add(userQualification.getName());
                                    warningMessage.add("资质缺失");
                                }
                                if ("".equals(grade) && gradeMust == 1){
                                    warningContext.add(userQualification.getName());
                                    warningMessage.add("等级未填");
                                }
                                if ("".equals(code) && codeMust == 1){
                                    warningContext.add(userQualification.getName());
                                    warningMessage.add("资格证书号未填");
                                }
                                if ("".equals(registerCode) && registerCodeMust == 1){
                                    warningContext.add(userQualification.getName());
                                    warningMessage.add("注册证书号未填");
                                }
                                if ("".equals(region) && regionMust == 1){
                                    warningContext.add(userQualification.getName());
                                    warningMessage.add("适用地区未填");
                                }
                                if ("".equals(category) && categoryMust == 1){
                                    warningContext.add(userQualification.getName());
                                    warningMessage.add("适用类别未填");
                                }
                                if ("".equals(range) && rangeMust == 1){
                                    warningContext.add(userQualification.getName());
                                    warningMessage.add("适用范围未填");
                                }

                            }
                        }

                    }
                }
                if (warningContext.size() > 0 && warningMessage.size() > 0) {
                    pageVO.setWarningContext(warningContext);
                    pageVO.setWarningMessage(warningMessage);
                    realList.add(pageVO);
                }

            }
        }
        return realList;
    }

    private List<UserWarningPageVO> getUserWarningContext(UserVO loginUser,List<UserWarningPageVO> list) {
        List<UserWarningPageVO> realList = new ArrayList<UserWarningPageVO>();
        if (list != null && list.size() > 0) {
            for (UserWarningPageVO pageVO : list) {
                Long id = pageVO.getId();
                /**
                 * 预警内容
                 */
                List<String> warningContext = new ArrayList<String>();
                /**
                 * 预警信息
                 */
                List<String> warningMessage = new ArrayList<String>();

                List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOs = userManagerService.findUserQualificationConfigReturnVOs(loginUser,id);
                if (userQualificationConfigReturnVOs != null && userQualificationConfigReturnVOs.size() > 0) {
                    for (UserQualificationConfigReturnVO qualificationVO : userQualificationConfigReturnVOs) {
                        Long qualificationId = qualificationVO.getQualificationId();
                        /**
                         * 资质文件
                         */
                        Long fileId = qualificationVO.getFileId();
                        String grade = qualificationVO.getGrade();
                        String code = qualificationVO.getCode();
                        String registerCode = qualificationVO.getRegisterCode();
                        String region = qualificationVO.getRegion();
                        String category = qualificationVO.getCategory();
                        String range = qualificationVO.getRange();
                        UserQualification userQualification = userQualificationMapper.selectByPrimaryKey(qualificationId);
                        if (userQualification != null) {
                            Integer fileMust = userQualification.getFileMust();//附件是否必填
                            Integer gradeMust = userQualification.getGradeMust();//等级是否必填
                            Integer codeMust = userQualification.getCodeMust();//资格证书号
                            Integer registerCodeMust = userQualification.getRegisterCodeMust();//注册证书号
                            Integer regionMust = userQualification.getRegionMust();
                            Integer categoryMust = userQualification.getCategoryMust();
                            Integer rangeMust = userQualification.getRangeMust();
                            if (fileId == null && fileMust == 1) {
                                warningContext.add(userQualification.getName());
                                warningMessage.add("资质缺失");
                            }
                            if ("".equals(grade) && gradeMust == 1){
                                warningContext.add(userQualification.getName());
                                warningMessage.add("等级未填");
                            }
                            if ("".equals(code) && codeMust == 1){
                                warningContext.add(userQualification.getName());
                                warningMessage.add("资格证书号未填");
                            }
                            if ("".equals(registerCode) && registerCodeMust == 1){
                                warningContext.add(userQualification.getName());
                                warningMessage.add("注册证书号未填");
                            }
                            if ("".equals(region) && regionMust == 1){
                                warningContext.add(userQualification.getName());
                                warningMessage.add("适用地区未填");
                            }
                            if ("".equals(category) && categoryMust == 1){
                                warningContext.add(userQualification.getName());
                                warningMessage.add("适用类别未填");
                            }
                            if ("".equals(range) && rangeMust == 1){
                                warningContext.add(userQualification.getName());
                                warningMessage.add("适用范围未填");
                            }

                        }
                    }
                }
                if (warningContext.size() > 0 && warningMessage.size() > 0) {
                    pageVO.setWarningContext(warningContext);
                    pageVO.setWarningMessage(warningMessage);
                    realList.add(pageVO);
                }

            }
        }
        return realList;
    }
}
