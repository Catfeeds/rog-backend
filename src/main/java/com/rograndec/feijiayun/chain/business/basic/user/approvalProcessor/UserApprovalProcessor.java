package com.rograndec.feijiayun.chain.business.basic.user.approvalProcessor;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPersonalMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.component.RGTUserComponent;
import com.rograndec.feijiayun.chain.common.constant.status.UserApprovalStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.List;

/**
 * 
 * 员工审批流的实现类
 * Created by dongdong.zhang on 2017/10/16.
 */
@Service
public class UserApprovalProcessor implements ApprovalFlowPostProcessor{
    Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserAdministrationMapper userAdministrationMapper;

	@Autowired
	private UserPersonalMapper userPersonalMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

    @Autowired
    private RGTUserComponent rgtUserComponent;

    @Autowired
    private UserManagerService userManagerService;

    @Override
    public void afterCancel(String content,Long dataId,Long eId) {
    }

    @Override
    public void afterReapply(String content,Long dataId) {

    }

    @Override
    public void afterApply(String content,Long dataId) {

    }

    @Override
    public void afterReapply() {

    }

    @Override
    public void afterApply() {

    }

    @Override
    public void afterCancel() {

    }

    //更新状态
    public void save(Long id,Integer approvalStatus){

        User user = userMapper.selectByPrimaryKey(id);

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setApproveStatus(approvalStatus);

        userMapper.updateByPrimaryKeySelective(updateUser);



    }

    @Override
    public void afterAudit(Long id, Integer status,Integer approvalStatus){

        if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {

            save(id, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());

            User user = userMapper.selectByPrimaryKey(id);

            List<UserAdministration> userAdministrations = userAdministrationMapper.selectUserAdministrationByUserId(user.getId());

            if (!CollectionUtils.isEmpty(userAdministrations)) {

                List<UserPersonal> userPersonals = userPersonalMapper.selectUserPersonalByUserId(user.getId());

                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());

                Integer rgtUserId = user.getRgtUserId();
                if(null == rgtUserId || 0 == rgtUserId){
                    /**
                     * 融贯通注册user为空表示未注册过,不为空表示已经注册过不需要再去修改
                     */
                    long startTime = Instant.now().toEpochMilli();
                    logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user send mph start"+startTime+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
                    rgtUserComponent.updateUser2Rgt(user, userAdministrations.get(0), userPersonals.get(0), enterprise);
                    long endTime = Instant.now().toEpochMilli();
                    logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++user send mph end"+endTime+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++endTime");
                    logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ user send mph end startTime-endTime"+(startTime-endTime)+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++endTime");
                }
            }
            //添加或是修改pos营业人员及收款人员的信息
            userManagerService.addOrUpdateWithApprovalControl(id);

        }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){

            save(id, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue());
        }
        else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue())){
            save(id, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue());
        }

    }
}
