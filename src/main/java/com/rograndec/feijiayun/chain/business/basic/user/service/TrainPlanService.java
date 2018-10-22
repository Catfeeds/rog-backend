package com.rograndec.feijiayun.chain.business.basic.user.service;

import com.rograndec.feijiayun.chain.business.basic.user.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface TrainPlanService {

    List<WaitTrainPlanPageVO> getWaitTrainPlanPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String code,
                                                   String plannerName, Long planYear, String planTitle, Long trainType, Long trainContent, Long type, String orderName, String orderType);

    CheckWaitTrainPlanVO getCheckWaitTrainPlan(Long enterpriseId, Long id, UserVO userVO);

    void updateWaitTrainPlan(UserVO loginUser, CheckWaitTrainPlanVO checkWaitTrainPlanVO) throws Exception;

    Integer getDeleteWaitTrainPlan(Long enterpriseId, Long id, UserVO userVO);

    ClickWaitTrainPlanHeadVO getClickWaitTrainPlanHead(Long enterpriseId, Long id, UserVO userVO);

    List<ClickTrainPlanDetailVO> getClickTrainPlanDetail(Long enterpriseId, Long id, UserVO userVO);

    void updateTrainPlan(UserVO loginUser, CheckTrainPlanVO checkTrainPlanVO) throws Exception;

    void saveTrainPlan(UserVO loginUser, CheckTrainPlanVO checkTrainPlanVO) throws Exception;

    void saveTrainPlanHead(UserVO loginUser, CheckWaitTrainPlanVO checkWaitTrainPlanVO) throws Exception;

    List<ClickWaitTrainPlanDetailVO> getClickWaitTrainPlanDetail(Long enterpriseId, Long id, UserVO userVO);

    ExportCheckTrainPlanVO getCheckTrainPlanVoList(Long enterpriseId, Long id, UserVO loginUser);

    void exportExcel(OutputStream output, ExportCheckTrainPlanVO  exportCheckTrainPlanVO, UserVO loginUser);

    List<EnterprisePageVO> getEnterprisePage(int pageNo, int pageSize, UserVO loginUser, Page page, String values);

    List<Position> getPositionTree(String deptIds, UserVO user);

    List<Department> getDeptTree(String deptIds, UserVO user);

    List<UserDataVO> getUserData(UserVO loginUser, List<Long> ids, Long enterpriseId);
}
