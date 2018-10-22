package com.rograndec.feijiayun.chain.business.basic.user.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.TrainPlanDetailMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.TrainPlanMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPositionMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.TrainPlan;
import com.rograndec.feijiayun.chain.business.basic.user.entity.TrainPlanDetail;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.service.TrainPlanService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.ExportDistrReturnCheckDtlVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.PositionMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.TrainStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TrainPlanServiceImpl implements TrainPlanService {

    @Autowired
    TrainPlanMapper trainPlanMapper;
    @Autowired
    TrainPlanDetailMapper trainPlanDetailMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    UserMapper userMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent<ExportClickTrainPlanDetailVO> purchaseGeneralComponent;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    UserPositionMapper userPositionMapper;

    @Override
    public List<WaitTrainPlanPageVO> getWaitTrainPlanPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String code, String plannerName, Long planYear, String planTitle, Long trainType, Long trainContent, Long type, String orderName, String orderType) {

        if (orderName != null) {
            if (orderName.equals("planDate")) {
                orderName = "plan_date";
            }
        }
        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd").format(startTime) + " 00:00:00";
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd").format(endTime) + " 23:59:59";
        }
        Long totalRecord = trainPlanMapper.queryCountByWaitTrainPlanPageParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes,  code, plannerName, planYear,
                planTitle, trainType, trainContent, type, loginUser.getChainType());
        List<WaitTrainPlanPageVO> list = trainPlanMapper.selectByWaitTrainPlanPageParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes,  code, plannerName, planYear,
                planTitle, trainType, trainContent, type, orderName, orderType,loginUser.getChainType());
        if(!list.isEmpty()){
            for(WaitTrainPlanPageVO waitTrainPlanPageVO : list){
                waitTrainPlanPageVO.setTrainTypeName(TrainType.getValue(waitTrainPlanPageVO.getTrainType()));
                waitTrainPlanPageVO.setTrainContentName(TrainContent.getValue(waitTrainPlanPageVO.getTrainContent()));
            }
        }

        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list;
    }

    @Override
    public CheckWaitTrainPlanVO getCheckWaitTrainPlan(Long enterpriseId, Long id, UserVO userVO) {

        CheckWaitTrainPlanVO checkWaitTrainPlanVo = trainPlanMapper.selectByCheckWaitTrainPlanPageParams(enterpriseId, id);
        checkWaitTrainPlanVo.setStatusName(TrainStatus.getValue(checkWaitTrainPlanVo.getStatus()));
        checkWaitTrainPlanVo.setTrainContentName(TrainContent.getValue(checkWaitTrainPlanVo.getTrainContent()));
        checkWaitTrainPlanVo.setTrainTypeName(TrainType.getValue(checkWaitTrainPlanVo.getTrainType()));
        checkWaitTrainPlanVo.setTrainMethodName(TrainMethod.getValue(checkWaitTrainPlanVo.getTrainMethod()));
        if(checkWaitTrainPlanVo.getPositionIds() != null){
            String[] positionIds = checkWaitTrainPlanVo.getPositionIds().split(",");
            Long[] positionIdsArray = new Long[positionIds.length];
            for(int i = 0; i < positionIds.length; i++){
                positionIdsArray[i] = Long.valueOf(positionIds[i]);
            }
            checkWaitTrainPlanVo.setPositionIdsArray(positionIdsArray);
        }

        if(checkWaitTrainPlanVo.getDeptIds() != null){
            String[] deptIds = checkWaitTrainPlanVo.getDeptIds().split(",");
            Long[] deptIdsArray = new Long[deptIds.length];
            for(int i = 0; i < deptIds.length; i++){
                deptIdsArray[i] = Long.valueOf(deptIds[i]);
            }
            checkWaitTrainPlanVo.setDeptIdsArray(deptIdsArray);
        }

        if(checkWaitTrainPlanVo.getUserIds() != null){
            String[] userIds = checkWaitTrainPlanVo.getUserIds().split(",");
            Long[] userIdsArray = new Long[userIds.length];
            for(int i = 0; i < userIds.length; i++){
                userIdsArray[i] = Long.valueOf(userIds[i]);
            }
            checkWaitTrainPlanVo.setUserIdsArray(userIdsArray);
        }

        checkWaitTrainPlanVo = selectNames(checkWaitTrainPlanVo, userVO);
        return checkWaitTrainPlanVo == null ? new CheckWaitTrainPlanVO() : checkWaitTrainPlanVo;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateWaitTrainPlan(UserVO loginUser, CheckWaitTrainPlanVO checkWaitTrainPlanVO) throws Exception{

        TrainPlan trainPlan = new TrainPlan();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(checkWaitTrainPlanVO, trainPlan);
        User user = userMapper.selectByPrimaryKey(trainPlan.getPlannerId());
        trainPlan.setPlannerName(user.getName());
        trainPlan.setPlannerCode(user.getCode());
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(trainPlan.getEnterpriseId());
        trainPlan.setParentId(enterprise.getParentId());
        trainPlan.setEnterpriseCode(enterprise.getCode());
        trainPlan.setEnterpriseName(enterprise.getName());
        //固定数据
        UserEnterpriseUtils.setUserCreateOrModify(trainPlan, loginUser, false);
        Long[] deptIdsArray = checkWaitTrainPlanVO.getDeptIdsArray();
        if(deptIdsArray != null){
            String deptIds = "";
            for(int i = 0; i < deptIdsArray.length; i++){
                deptIds += deptIdsArray[i] + ",";
            }
            trainPlan.setDeptIds(deptIds.substring(0,deptIds.length()-1));
        }
        Long[] positionIdsArray = checkWaitTrainPlanVO.getPositionIdsArray();
        if(positionIdsArray != null){
            String positionIds = "";
            for(int i = 0; i < positionIdsArray.length; i++){
                positionIds += positionIdsArray[i] + ",";
            }
            trainPlan.setPositionIds(positionIds.substring(0,positionIds.length()-1));
        }
        Long[] userIdsArray = checkWaitTrainPlanVO.getUserIdsArray();
        if(userIdsArray != null){
            String userIds = "";
            for(int i = 0; i < userIdsArray.length; i++){
                userIds += userIdsArray[i] + ",";
            }
            trainPlan.setUserIds(userIds.substring(0,userIds.length()-1));
        }
        trainPlanMapper.updateByPrimaryKeySelective(trainPlan);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Integer getDeleteWaitTrainPlan(Long enterpriseId, Long id, UserVO userVO) {

        trainPlanMapper.deleteByPrimaryKey(id);
        return 1;
    }

    @Override
    public ClickWaitTrainPlanHeadVO getClickWaitTrainPlanHead(Long enterpriseId, Long id, UserVO userVO) {

        ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO = trainPlanMapper.selectByClickWaitTrainPlanHeadParams(enterpriseId, id);
        clickWaitTrainPlanHeadVO.setTrainContentName(TrainContent.getValue(clickWaitTrainPlanHeadVO.getTrainContent()));
        clickWaitTrainPlanHeadVO.setTrainTypeName(TrainType.getValue(clickWaitTrainPlanHeadVO.getTrainType()));
        clickWaitTrainPlanHeadVO.setTrainMethodName(TrainMethod.getValue(clickWaitTrainPlanHeadVO.getTrainMethod()));
        return clickWaitTrainPlanHeadVO == null ? new ClickWaitTrainPlanHeadVO() : clickWaitTrainPlanHeadVO;
    }

    @Override
    public List<ClickTrainPlanDetailVO> getClickTrainPlanDetail(Long enterpriseId, Long id, UserVO userVO) {

        List<ClickTrainPlanDetailVO> clickTrainPlanDetailVos = trainPlanDetailMapper.selectByTrainPlanId(enterpriseId, id);
        //set名字
        for(int i = 0; i < clickTrainPlanDetailVos.size(); i++){
            setNames(clickTrainPlanDetailVos.get(i), userVO);
            clickTrainPlanDetailVos.get(i).setExamineMethodName(ExamineMethod.getValue(clickTrainPlanDetailVos.get(i).getExamineMethod()));
            clickTrainPlanDetailVos.get(i).setExamineResultName(ExamineResult.getValue(clickTrainPlanDetailVos.get(i).getExamineResult()));
        }
        return clickTrainPlanDetailVos == null ? new ArrayList<>() : clickTrainPlanDetailVos;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateTrainPlan(UserVO loginUser, CheckTrainPlanVO checkTrainPlanVO) throws Exception{

        ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO = checkTrainPlanVO.getClickWaitTrainPlanHeadVO();
        TrainPlan trainPlan = new TrainPlan();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(clickWaitTrainPlanHeadVO, trainPlan);
        User user = userMapper.selectByPrimaryKey(trainPlan.getPlannerId());
        trainPlan.setPlannerName(user.getName());
        trainPlan.setPlannerCode(user.getCode());
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(trainPlan.getEnterpriseId());
        trainPlan.setParentId(enterprise.getParentId());
        trainPlan.setEnterpriseCode(enterprise.getCode());
        trainPlan.setEnterpriseName(enterprise.getName());
        //固定数据
        UserEnterpriseUtils.setUserCreateOrModify(trainPlan, loginUser, false);
        trainPlanMapper.updateByPrimaryKeySelective(trainPlan);
        List<ClickTrainPlanDetailVO > ClickTrainPlanDetailVos = checkTrainPlanVO.getClickTrainPlanDetailVO();
        for(int i = 0; i < ClickTrainPlanDetailVos.size(); i++){
            ClickTrainPlanDetailVO clickTrainPlanDetailVO = ClickTrainPlanDetailVos.get(i);
            TrainPlanDetail trainPlanDetail = new TrainPlanDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(clickTrainPlanDetailVO, trainPlanDetail);
            trainPlanDetail.setParentId(enterprise.getParentId());
            trainPlanDetail.setEnterpriseId(enterprise.getId());
            //固定数据
            UserEnterpriseUtils.setUserCreateOrModify(trainPlanDetail, loginUser, false);
            trainPlanDetailMapper.updateByPrimaryKeySelective(trainPlanDetail);
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveTrainPlan(UserVO loginUser, CheckTrainPlanVO checkTrainPlanVO) throws Exception, BusinessException {
        ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO = checkTrainPlanVO.getClickWaitTrainPlanHeadVO();
        TrainPlan trainPlan = new TrainPlan();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(clickWaitTrainPlanHeadVO, trainPlan);

        User user = userMapper.selectByPrimaryKey(trainPlan.getPlannerId());
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(trainPlan.getEnterpriseId());
        trainPlan.setParentId(enterprise.getParentId());
        trainPlan.setEnterpriseCode(enterprise.getCode());
        trainPlan.setEnterpriseName(enterprise.getName());

        trainPlan.setPlannerName(user.getName());
        trainPlan.setPlannerCode(user.getCode());
        trainPlan.setStatus(1);
        trainPlanMapper.updateByPrimaryKeySelective(trainPlan);

        saveTrainPlanDetail(loginUser, trainPlan, checkTrainPlanVO);

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveTrainPlanHead(UserVO loginUser, CheckWaitTrainPlanVO checkWaitTrainPlanVO) throws Exception, BusinessException{
        TrainPlan trainPlan = new TrainPlan();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(checkWaitTrainPlanVO, trainPlan);
        //固定数据
        UserEnterpriseUtils.setUserCreateOrModify(trainPlan, loginUser, true);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(trainPlan.getEnterpriseId());
        trainPlan.setParentId(enterprise.getParentId());
        trainPlan.setEnterpriseCode(enterprise.getCode());
        trainPlan.setEnterpriseName(enterprise.getName());
        User user = userMapper.selectByPrimaryKey(trainPlan.getPlannerId());
        trainPlan.setPlannerCode(user.getCode());
        trainPlan.setPlannerName(user.getName());
        //验收单号,单据类型
        trainPlan.setOrderType(OrderRule.TRAIN_PLAN.getType());
        trainPlan.setStatus(0);
        trainPlan.setCode(getCode(OrderRule.TRAIN_PLAN.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        Long[] deptIdsArray = checkWaitTrainPlanVO.getDeptIdsArray();
        if(deptIdsArray != null){
            String deptIds = "";
            for(int i = 0; i < deptIdsArray.length; i++){
                deptIds += deptIdsArray[i] + ",";
            }
            trainPlan.setDeptIds(deptIds.substring(0,deptIds.length()-1));
        }
        Long[] positionIdsArray = checkWaitTrainPlanVO.getPositionIdsArray();
        if(positionIdsArray != null){
            String positionIds = "";
            for(int i = 0; i < positionIdsArray.length; i++){
                positionIds += positionIdsArray[i] + ",";
            }
            trainPlan.setPositionIds(positionIds.substring(0,positionIds.length()-1));
        }
        Long[] userIdsArray = checkWaitTrainPlanVO.getUserIdsArray();
        if(userIdsArray != null){
            String userIds = "";
            for(int i = 0; i < userIdsArray.length; i++){
                userIds += userIdsArray[i] + ",";
            }
            trainPlan.setUserIds(userIds.substring(0,userIds.length()-1));
        }
        trainPlan.setId(null);

        trainPlanMapper.insertSelective(trainPlan);

    }

    @Override
    public List<ClickWaitTrainPlanDetailVO> getClickWaitTrainPlanDetail(Long enterpriseId, Long id, UserVO userVO) {

        CheckWaitTrainPlanVO checkWaitTrainPlanVO = trainPlanMapper.selectByCheckWaitTrainPlanPageParams(enterpriseId, id);
        checkWaitTrainPlanVO.setStatusName(TrainStatus.getValue(checkWaitTrainPlanVO.getStatus()));
        checkWaitTrainPlanVO.setTrainContentName(TrainContent.getValue(checkWaitTrainPlanVO.getTrainContent()));
        checkWaitTrainPlanVO.setTrainTypeName(TrainType.getValue(checkWaitTrainPlanVO.getTrainType()));
        checkWaitTrainPlanVO.setTrainMethodName(TrainMethod.getValue(checkWaitTrainPlanVO.getTrainMethod()));
        String userIds = checkWaitTrainPlanVO.getUserIds();
        String positionIds = checkWaitTrainPlanVO.getPositionIds();
        String deptIds = checkWaitTrainPlanVO.getDeptIds();
        List<ClickWaitTrainPlanDetailVO> clickWaitTrainPlanDetailVos = new ArrayList<>();
        //先判断人员是否存在     ->   岗位    ->部门
        if( userIds.replace(" ", "").length() > 0){
            clickWaitTrainPlanDetailVos = setUserData(userIds, enterpriseId, checkWaitTrainPlanVO);
        }
//        if(positionIds.replace(" ", "").length() > 0){
//            String[] positionId = positionIds.split(",");
//
//            String userId = trainPlanDetailMapper.selectByPositionIds(positionId, enterpriseId);
//            clickWaitTrainPlanDetailVos = setUserData(userId, enterpriseId);
//
//        }
//        if(deptIds.replace(" ", "").length() > 0){
//            String[] deptId = deptIds.split(",");
//
//            String userId = trainPlanDetailMapper.selectByDeptIds(deptId, enterpriseId);
//            clickWaitTrainPlanDetailVos = setUserData(userId, enterpriseId);
//
//        }
        return clickWaitTrainPlanDetailVos;
    }

    @Override
    public ExportCheckTrainPlanVO getCheckTrainPlanVoList(Long enterpriseId, Long id, UserVO loginUser) {

        ExportCheckTrainPlanVO  exportCheckTrainPlanVO = new ExportCheckTrainPlanVO();
        List<ExportClickTrainPlanDetailVO > exportClickTrainPlanDetailVOs = new ArrayList<>();
        ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO = getClickWaitTrainPlanHead(enterpriseId, id, loginUser);
        String startDate =  new SimpleDateFormat("yyyy-MM-dd").format(clickWaitTrainPlanHeadVO.getStartDate());
        String endDate =  new SimpleDateFormat("yyyy-MM-dd").format(clickWaitTrainPlanHeadVO.getEndDate());
        clickWaitTrainPlanHeadVO.setStartDateString(startDate);
        clickWaitTrainPlanHeadVO.setEndDateString(endDate);
        exportCheckTrainPlanVO.setClickWaitTrainPlanHeadVO(clickWaitTrainPlanHeadVO);
        List<ClickTrainPlanDetailVO > clickTrainPlanDetailVOs = getClickTrainPlanDetail(enterpriseId, id, loginUser);
        for(ClickTrainPlanDetailVO clickTrainPlanDetailVO : clickTrainPlanDetailVOs){
            ExportClickTrainPlanDetailVO exportClickTrainPlanDetailVO = new ExportClickTrainPlanDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(clickTrainPlanDetailVO, exportClickTrainPlanDetailVO);
            Date examineStartTime = exportClickTrainPlanDetailVO.getExamineStartTime();
            Date examineEndTime = exportClickTrainPlanDetailVO.getExamineEndTime();
            String examineStartTimes = new SimpleDateFormat("yyyy-MM-dd").format(examineStartTime);
            String examineEndTimes = new SimpleDateFormat("yyyy-MM-dd").format(examineEndTime);
            String examineStartEndTime = examineStartTimes + "至" + examineEndTimes;
            exportClickTrainPlanDetailVO.setExamineStartEndTime(examineStartEndTime);
            exportClickTrainPlanDetailVOs.add(exportClickTrainPlanDetailVO);
        }
        exportCheckTrainPlanVO.setExportClickTrainPlanDetailVO(exportClickTrainPlanDetailVOs);
        return exportCheckTrainPlanVO;
    }

    @Override
    public void exportExcel(OutputStream output, ExportCheckTrainPlanVO  exportCheckTrainPlanVO, UserVO loginUser) {

        //转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("deptNames","部门");
        map.put("positionNames","岗位");
        map.put("userName","员工");
        map.put("trainLessonHour","培训课时");
        map.put("trainPerformance","培训表现");
        map.put("examineItem","考核项目");
        map.put("examineMethodName","考核方式");
        map.put("examineStartEndTime","考核时间");
        map.put("examineResultName","考核结果");
        map.put("measures","采取措施");
        map.put("remark","备注");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("计划编码:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getCode() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getCode());
        titleSecondRow.append("   计划人员:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getPlannerName() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getPlannerName());
        titleSecondRow.append("  组织机构编码:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getEnterpriseCode() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getEnterpriseCode());
        titleSecondRow.append("  组织机构名称:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getEnterpriseName() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getEnterpriseName());
        titleSecondRow.append("  计划标题:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getPlanTitle() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getPlanTitle());
        titleSecondRow.append("  计划年度:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getPlanYear() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getPlanYear());
        titleSecondRow.append("  开始日期:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getStartDateString() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getStartDateString());
        titleSecondRow.append("  结束日期:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getEndDateString() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getEndDateString());
        titleSecond.add(titleSecondRow.toString());

        //标题栏下第二行
        titleSecondRow = new StringBuilder();
        titleSecondRow.append("  培训类型:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainTypeName() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainTypeName());
        titleSecondRow.append("  培训内容:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainContentName() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainContentName());
        titleSecondRow.append("  培训目的:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainGoal() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainGoal());
        titleSecondRow.append("  培训方式:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainMethodName() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainMethodName());
        titleSecondRow.append("  培训机构:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainOrg() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainOrg());
        titleSecondRow.append("  培训地点:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainPlace() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getTrainPlace());
        titleSecondRow.append("  讲师:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getLecturer() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getLecturer());
        titleSecondRow.append("  课时:");
        titleSecondRow.append(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getLessonHour() == null ? "":exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getLessonHour());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();

        List<String> needTotalName = new ArrayList<>();

        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(trainPlanMapper.selectByPrimaryKey(exportCheckTrainPlanVO.getClickWaitTrainPlanHeadVO().getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("培训计划");
        purchaseGeneralComponent.commExcelExport(output,map,exportCheckTrainPlanVO.getExportClickTrainPlanDetailVO(),name,titleSecond,end.toString(),true,needTotalName);
    }

    @Override
    public List<EnterprisePageVO> getEnterprisePage(int pageNo, int pageSize, UserVO loginUser, Page page, String values) {

        Long totalRecord = trainPlanMapper.queryCountEnterprise(loginUser.getEnterpriseId(), Long.valueOf(loginUser.getChainType()), values);
        List<EnterprisePageVO> EnterprisePageVO = trainPlanMapper.selectEnterprise(loginUser.getEnterpriseId(), Long.valueOf(loginUser.getChainType()), values, page.getStart(), pageSize);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return EnterprisePageVO == null ? new ArrayList<>() : EnterprisePageVO;
    }

    @Override
    public List<Position> getPositionTree(String deptIds, UserVO user) {

        String[] deptId = null;
        if(deptIds != null ){
            deptId = deptIds.split(",");
        }

        List<Position> position = positionMapper.selectByDeptIds(deptId, user.getChainType(), user.getEnterpriseId(), user.getParentId());
        return position == null ? new ArrayList<>() : position;
    }

    @Override
    public List<Department> getDeptTree(String deptIds, UserVO user) {

        String[] deptId = null;
        if(deptIds != null ){
            deptId = deptIds.split(",");
        }
        List<Department> department = departmentMapper.selectByDeptIds(deptId, user.getChainType(), user.getEnterpriseId(), user.getParentId());
        return department == null ? new ArrayList<>() : department;
    }

    @Override
    public List<UserDataVO> getUserData(UserVO userVO, List<Long> ids, Long enterpriseId) {

        List<UserDataVO> userDataVO = userMapper.selectByPositionIds(enterpriseId, ids, userVO.getChainType());
        return userDataVO == null ? new ArrayList<>() : userDataVO;
    }


    private void saveTrainPlanDetail(UserVO loginUser, TrainPlan trainPlan, CheckTrainPlanVO checkTrainPlanVO) throws Exception, BusinessException{

        List<ClickTrainPlanDetailVO > clickTrainPlanDetailVos = checkTrainPlanVO.getClickTrainPlanDetailVO();
        if(clickTrainPlanDetailVos != null){
            for(ClickTrainPlanDetailVO clickTrainPlanDetailVo : clickTrainPlanDetailVos){
                TrainPlanDetail trainPlanDetail = new TrainPlanDetail();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(clickTrainPlanDetailVo, trainPlanDetail);
                //总单ID
                trainPlanDetail.setPlanId(trainPlan.getId());
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(trainPlan.getEnterpriseId());
                trainPlanDetail.setParentId(enterprise.getParentId());
                trainPlanDetail.setEnterpriseId(enterprise.getId());
                //固定数据
                UserEnterpriseUtils.setUserCreateOrModify(trainPlanDetail, loginUser, true);
                trainPlanDetail.setEnterpriseId(trainPlan.getEnterpriseId());
                trainPlanDetail.setParentId(trainPlan.getParentId());
                trainPlanDetailMapper.insertSelective(trainPlanDetail);
            }
        }else {
            throw new BusinessException("没有得到明细单据的相关数据,无法保存");
        }

    }

    private CheckWaitTrainPlanVO selectNames(CheckWaitTrainPlanVO checkWaitTrainPlanVo, UserVO userVO) {
        //组装 部门  岗位  人员
        String deptIds = checkWaitTrainPlanVo.getDeptIds();
        String positionIds = checkWaitTrainPlanVo.getPositionIds();
        String userIds = checkWaitTrainPlanVo.getUserIds();
        if (deptIds != null){
            String[] deptId = deptIds.split(",");
            Department department = trainPlanMapper.selectByDeptIds(deptId, userVO.getEnterpriseId());
            if(department != null){
                checkWaitTrainPlanVo.setDeptIdsNames(department.getName() == null ? "" : department.getName());
            }
        }
        if(positionIds != null){
            String[] positionId = positionIds.split(",");
            Position position = trainPlanMapper.selectByPositionIds(positionId, userVO.getEnterpriseId());
            if(position != null){
                checkWaitTrainPlanVo.setPositionIdsNames(position.getName() == null ? "" : position.getName());
            }
        }
        if(userIds != null){
            String[] userId = userIds.split(",");
            User user = trainPlanMapper.selectByUserIds(userId, userVO.getEnterpriseId());
            if(user != null){
                checkWaitTrainPlanVo.setUserIdsName(user.getName() == null ? "" : user.getName());
            }
        }
        return checkWaitTrainPlanVo == null ? new CheckWaitTrainPlanVO() : checkWaitTrainPlanVo;
    }

    private ClickTrainPlanDetailVO setNames(ClickTrainPlanDetailVO clickTrainPlanDetailVO, UserVO userVO) {
        //组装 部门  岗位  人员
        String deptIds = clickTrainPlanDetailVO.getDeptIds();
        String positionIds = clickTrainPlanDetailVO.getPositionIds();
        String userIds = String.valueOf(clickTrainPlanDetailVO.getUserId());
        if (deptIds != null){
            String[] deptId = deptIds.split(",");
            Department department = trainPlanMapper.selectByDeptIds(deptId, userVO.getEnterpriseId());
            if(department != null){
                clickTrainPlanDetailVO.setDeptNames(department.getName());
            }
        }
        if(positionIds != null){
            String[] positionId = positionIds.split(",");
            Position position = trainPlanMapper.selectByPositionIds(positionId, userVO.getEnterpriseId());
            if(position != null){
                clickTrainPlanDetailVO.setPositionNames(position.getName());
            }
        }
        if(userIds != null){
            String[] userId = userIds.split(",");
            User user = trainPlanMapper.selectByUserIds(userId, userVO.getEnterpriseId());
            if(user != null){
                clickTrainPlanDetailVO.setUserName(user.getName());
            }
        }
        return clickTrainPlanDetailVO == null ? new ClickTrainPlanDetailVO() : clickTrainPlanDetailVO;
    }

    //获取单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }


    public List<ClickWaitTrainPlanDetailVO> setUserData(String userData, Long enterpriseId, CheckWaitTrainPlanVO checkWaitTrainPlanVO) {
        String[] userId = userData.split(",");
        //去重
        Set<String> set = new HashSet<>();
        for(int i=0;i<userId.length;i++){
            set.add(userId[i]);
        }
        String[] arrayResult = (String[]) set.toArray(new String[set.size()]);

        List<ClickWaitTrainPlanDetailVO> clickWaitTrainPlanDetailVos = new ArrayList<>();
        for(int i= 0; i < arrayResult.length; i++){
            ClickWaitTrainPlanDetailVO clickWaitTrainPlanDetailVO = new ClickWaitTrainPlanDetailVO();
            clickWaitTrainPlanDetailVO.setUserId(Long.valueOf(arrayResult[i]));
            User user = userMapper.selectByPrimaryKey(Long.valueOf(arrayResult[i]));
            if(user != null){
                clickWaitTrainPlanDetailVO.setUserName(user.getName());
            }
            ClickTrainPlanDetailVO PositionVO = trainPlanDetailMapper.selectPositionByUserId(Long.valueOf(arrayResult[i]), enterpriseId);
            if(PositionVO != null){
                clickWaitTrainPlanDetailVO.setPositionIds(PositionVO.getPositionIds());
                clickWaitTrainPlanDetailVO.setPositionNames(PositionVO.getPositionNames());
            }
            ClickTrainPlanDetailVO DeptVO = trainPlanDetailMapper.selectDeptByUserId(Long.valueOf(arrayResult[i]), enterpriseId);
            if(DeptVO != null){
                clickWaitTrainPlanDetailVO.setDeptIds(DeptVO.getDeptIds());
                clickWaitTrainPlanDetailVO.setDeptNames(DeptVO.getDeptNames());
            }
            clickWaitTrainPlanDetailVO.setTrainLessonHour(checkWaitTrainPlanVO.getLessonHour());

            clickWaitTrainPlanDetailVos.add(i, clickWaitTrainPlanDetailVO);
        }
        return clickWaitTrainPlanDetailVos;
    }
}
