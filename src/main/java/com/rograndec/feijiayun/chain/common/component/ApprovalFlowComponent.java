package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditResult;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalStartOrg;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlow;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowDetail;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.business.system.approval.vo.ApprovalAduitBO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.context.SpringUtils;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class ApprovalFlowComponent {


	@Autowired
	private ApprovalFlowMapper approvalFlowMapper;

	@Autowired
	private ApprovalFlowActionMapper approvalFlowActionMapper;

	@Autowired
	private ApprovalFlowActionDetailMapper approvalFlowActionDetailMapper;

	@Autowired
	private ApprovalFlowDetailMapper approvalFlowDetailMapper;

	@Autowired
	private ManageConfigMapper manageConfigMapper;

	@Autowired
	private SpringUtils springUtils;

	@Autowired
	private EnterpriseBusinessMapper enterpriseBusinessMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	private static final String GOODS_APPROVAL_CONTENT = "0201";

	public boolean isCheckApply( UserVO userVO,ApprovalFlow approvalFlow,SubmitApprovalFlowVO submitApprovalFlowVO){

		/**
		 * 如果没有审批流程创建直接返回false 不去申请审批
		 */
		if(approvalFlow == null){
			return false;
		}

		ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);
		boolean isAppy;
		if(manageConfig.getApprovalControl().equals(EnableStatus.ENABLE.getStatus())){
			isAppy=true;
		}else {
			return false;
		}

		/**
		 * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
		 */
		Integer startOrg = approvalFlow.getStartOrg();
		if (ApprovalStartOrg.ZDFQJG.getOrgFlag() == startOrg) {
			if (!approvalFlow.getStartOrgId().equals(submitApprovalFlowVO.getStartOrgId())) {
				return false;
			}
		}else if (ApprovalStartOrg.ZB.getOrgFlag() == startOrg) {
			if (!submitApprovalFlowVO.getChainType().equals(ChainType.Headquarters.getCode())) {
				return false;
			}

		}else if (ApprovalStartOrg.QBFD.getOrgFlag() == startOrg) {
			if (!submitApprovalFlowVO.getChainType().equals(ChainType.Selfoperatedshop.getCode())
					&& !submitApprovalFlowVO.getChainType().equals(ChainType.Division.getCode())) {
				return false;
			}
		}else if (ApprovalStartOrg.JJMD.getOrgFlag() == startOrg) {
			if (!submitApprovalFlowVO.getChainType().equals(ChainType.Division.getCode())) {
				return false;
			}

		}

		return isAppy;
	}


	/**
	 *
	 * @param chainType
	 * @param enterpriseId
	 * @return
	 */
	public boolean checkApprovalControl(Integer chainType,Long enterpriseId){

		if(ChainType.Headquarters.getCode() != chainType){
			EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
			Integer approvalControl = bus.getApprovalControl();
			if(approvalControl == 0){
				/**
				 * 等于0,为总部控制
				 */
				return true;
			}else {
				/**
				 * 不等于0,为门店控制
				 */
				return false;
			}
		}

		/**
		 * 如果是总部则都是总部控制
		 */
		return true;
	}

	/**
	 * 校验是否是分店并且是价格调整审批流
	 * @param submitApprovalFlowVO
	 * @param userVO
	 * @return 1 为是分店并且是价格调整审批流流程,审批为总部控制,2  为是分店并且是价格调整审批流流程,审批为自主控制,3不符合分店并且是价格调整审批流,审批控制随上层判断
	 */
	public Integer checkPriceMangerApprovalControl(SubmitApprovalFlowVO submitApprovalFlowVO, UserVO userVO){

		Integer flag = 3;
		String content = submitApprovalFlowVO.getContent();
		if("0202".equals(content) && ChainType.Headquarters.getCode() != userVO.getChainType()){
			/**
			 * 分店和加盟店 价格调整审批流,不受审批流程开关控制,只受价格管理开关控制
			 */
			Long enterpriseId = userVO.getEnterpriseId();
			EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
			/**
			 *  价格管理（0-总部控制；1-自主控制）
			 */
			Integer priceManage = bus.getPriceManage();
			if(0 == priceManage){
				flag = 1;
			}else {
				/**
				 * 自主控制时不需要走审批流程
				 */
				flag = 2;
			}
		}

		return flag;
	}



	/**
	 * 提交审核
	 * @param userVO 用户实体类
	 * @param submitApprovalFlowVO 审批信息类
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	@Transactional(rollbackFor = Exception.class)
	public Object apply(SubmitApprovalFlowVO submitApprovalFlowVO, UserVO userVO) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {


		boolean checkApprovalControl = checkApprovalControl(submitApprovalFlowVO.getChainType(), userVO.getEnterpriseId());

		/**
		 * 数据库中存在为0的企业,为0的该企业为默认数据支撑用, 不做任何业务使用,所以默认为-1
		 */
		Long enterpriseId = -1L;

		/**
		 * 校验价格管理,1 为是分店并且是价格调整审批流流程,审批为总部控制,2  为是分店并且是价格调整审批流流程,审批为自主控制,3不符合分店并且是价格调整审批流,审批控制随上层判断
		 */
		Integer b = checkPriceMangerApprovalControl(submitApprovalFlowVO, userVO);

		if(1 == b) {
			checkApprovalControl = true;
		}else if(2 == b) {
			Object audit = audit(submitApprovalFlowVO.getContent(), submitApprovalFlowVO.getDataId(), ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
			return audit;
		}

		if(checkApprovalControl){
			/**
			 * 总部控制
			 */
			enterpriseId = submitApprovalFlowVO.getHeadquartersEnterpriseId();
		}else {
			/**
			 * 分部控制
			 */
			enterpriseId = submitApprovalFlowVO.getStartOrgId();
		}

		ApprovalFlow approvalFlow = approvalFlowMapper.selectByContent(submitApprovalFlowVO.getContent(),enterpriseId);

		boolean isMustApply = isCheckApply(userVO,approvalFlow,submitApprovalFlowVO);
		if((isMustApply)) {
			List<ApprovalFlow> approvalFlows = checkRepeart(enterpriseId, submitApprovalFlowVO.getContent());
			applyProcess(enterpriseId,approvalFlows, submitApprovalFlowVO,userVO);
		}else {
			audit(submitApprovalFlowVO.getContent(),submitApprovalFlowVO.getDataId(),ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
		}

		return null;

	}

	@Transactional(rollbackFor = Exception.class)
	public void applyProcess(Long enterpriseId,List<ApprovalFlow> approvalFlows, SubmitApprovalFlowVO submitApprovalFlowVO,UserVO userVO){
		if (!CollectionUtils.isEmpty(approvalFlows)) {

			if (approvalFlows.size() > 1) {
				throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_ADD, "审批流程存在多条");
			}

			ApprovalFlow approvalFlow = approvalFlows.get(0);

			List<Integer> list = new ArrayList<>();
			list.add(0);
			list.add(-1);
			List<ApprovalFlowAction> actions = approvalFlowActionMapper.selectBystatusRecoms(submitApprovalFlowVO.getDataId()
					, submitApprovalFlowVO.getStartOrgId(), submitApprovalFlowVO.getContent()
					, list);
			if(!CollectionUtils.isEmpty(actions)){
				throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_ADD, "审批流程正处于审批阶段,无法提交");
			}

			ApprovalFlowAction approvalFlowAction = ApprovalFlowAction.getApprovalFlowAction4SubmitApprovalFlow(enterpriseId,submitApprovalFlowVO, approvalFlow.getId());

			ApprovalFlowActionDetail approvalFlowActionDetail = getApprovalFlowActionDetail(enterpriseId,submitApprovalFlowVO, approvalFlowAction);

			setApprover4Apply(approvalFlowAction, approvalFlowActionDetail, 1);

			approvalFlowAction.setStartOrgId(userVO.getEnterpriseId());
			approvalFlowAction.setStartOrgName(userVO.getEnterpriseName());
			approvalFlowAction.setStarterId(userVO.getUserId());
			approvalFlowAction.setStarterName(userVO.getUserName());
			approvalFlowActionMapper.insertSelective(approvalFlowAction);
			approvalFlowActionDetail.setApprovalFlowActionId(approvalFlowAction.getId());
			approvalFlowActionDetailMapper.insertSelective(approvalFlowActionDetail);

		}else {
			throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE, "审批流程找不到");
		}
	}

	/**
	 * 重新提交審核
	 * @param submitApprovalFlowVO
	 * @param approvalFlowPostProcessor
	 */
	@Transactional(rollbackFor = Exception.class)
	public void reapply(SubmitApprovalFlowVO submitApprovalFlowVO
			,ApprovalFlowPostProcessor approvalFlowPostProcessor
			,UserVO userVO){
//		apply(submitApprovalFlowVO,userVO);
		approvalFlowPostProcessor.afterReapply();
	}

	/**
	 * 取消流程
	 * @param content 审批内容
	 * @param dataId 数据id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void cancel(String content,Long dataId,Long eId){

		Map<String,Object> map = new HashMap<>();
		map.put("content",content);
		map.put("dataId",dataId);
		map.put("enterpriseId",eId);
		ApprovalFlowAction approvalFlowAction = approvalFlowActionMapper.selectByDataId(map);

		if(null != approvalFlowAction){
			checkCancel(approvalFlowAction);
			ApprovalFlowAction cancelApprovalFlowAction = new ApprovalFlowAction();

			cancelApprovalFlowAction.setId(approvalFlowAction.getId());
			cancelApprovalFlowAction.setStatusRecom(Long.parseLong(ApprovalFlowAuditStatus.DETAIL_STATUS_CANCEL.getValue()+""));

			approvalFlowActionMapper.updateByPrimaryKeySelective(cancelApprovalFlowAction);
		}

	}

	/**
	 * 取消流程
	 * @param content 审批内容
	 * @param dataId 数据id
	 * @param approvalFlowPostProcessor 取消业务处理类 实现ApprovalFlowPostProcessor接口
	 */
	@Transactional(rollbackFor = Exception.class)
	public void cancel(String content,Long dataId,Long eId, ApprovalFlowPostProcessor approvalFlowPostProcessor){

		cancel(content,dataId,eId);

		approvalFlowPostProcessor.afterCancel(content,dataId,eId);
	}

	private void checkCancel(ApprovalFlowAction approvalFlowAction){
		List<ApprovalFlowActionDetail> approvalFlowActionDetails = approvalFlowActionDetailMapper.selectByActionFlowId(approvalFlowAction.getId());
		if(!CollectionUtils.isEmpty(approvalFlowActionDetails)) {
			//顺序查询,取最后一条即为审批最新的状态
			ApprovalFlowActionDetail approvalFlowActionDetail = approvalFlowActionDetails.get(approvalFlowActionDetails.size() - 1);

			if(!approvalFlowActionDetail.getStatus().equals(ApprovalFlowAuditStatus.DETAIL_STATUS_UNPASS.getValue())){
				//审批被驳回才可以操作价格调整单取消
				throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"审批流程不是驳回状态,不允许取消");
			}

			if(null == approvalFlowAction){
				throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"审批流程不存在,不允许取消");
			}

		}
	}

	private ApprovalFlowActionDetail getApprovalFlowActionDetail(Long enterpriseId ,SubmitApprovalFlowVO submitApprovalFlowVO
			,ApprovalFlowAction approvalFlowAction){
		ApprovalFlowActionDetail nextActionDetail = new ApprovalFlowActionDetail();
		/*nextActionDetail.setEnterpriseId(submitApprovalFlowVO.getStartOrgId());*/
		/*nextActionDetail.setApprovalFlowActionId(approvalFlowAction.getId());*/
		nextActionDetail.setFlowId(approvalFlowAction.getFlowId());
		nextActionDetail.setLevel(1);

		List<ApprovalFlowDetail> approvalFlowDetails = approvalFlowDetailMapper.getListByFlowId(approvalFlowAction.getFlowId());

		if(CollectionUtils.isEmpty(approvalFlowDetails)){
			throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_ADD,"审批流程明细不存在");
		}

		ApprovalFlowDetail approvalFlowDetail = approvalFlowDetails.get(0);

		/**
		 * 审批如果是指定机构,企业id为审批机构的企业id,如果不是 为总部id
		 */
		if(null != approvalFlowDetail.getOrgId()){
			nextActionDetail.setEnterpriseId(approvalFlowDetail.getOrgId());

		}else {
			/*nextActionDetail.setEnterpriseId(submitApprovalFlowVO.getHeadquartersEnterpriseId());*/
			nextActionDetail.setEnterpriseId(enterpriseId);
		}


		nextActionDetail.setApprovalStage(approvalFlowDetail.getApprovalStage());
		nextActionDetail.setApprovalOrgId(approvalFlowDetail.getOrgId());
		nextActionDetail.setApprovalOrgName(approvalFlowDetail.getOrgName());
		nextActionDetail.setApprovalRoleId(approvalFlowDetail.getRoleId());
		nextActionDetail.setApprovalRoleName(approvalFlowDetail.getRoleName());
		nextActionDetail.setApproverId(approvalFlowDetail.getApproverId() == null ? 0L : approvalFlowDetail.getApproverId() );
		nextActionDetail.setApproverName(approvalFlowDetail.getApproverName());
		nextActionDetail.setApprovalResult(ApprovalFlowAuditResult.DETAIL_RESULT_WAIT.getValue());
		nextActionDetail.setStatus(ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue());
		nextActionDetail.setStartTime(new Date());

		return nextActionDetail;
	}


	/**
	 * 审核校验
	 * @param submitApprovalFlowVO
	 */
	/*private void checkApplyApprovalFlow(ApprovalFlow approvalFlow,SubmitApprovalFlowVO submitApprovalFlowVO){


		*//**
		 * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
		 *//*
		Integer startOrg = approvalFlow.getStartOrg();
		if (ApprovalStartOrg.ZDFQJG.getOrgFlag() == startOrg) {
			if (!approvalFlow.getStartOrgId().equals(submitApprovalFlowVO.getStartOrgId())) {
				throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE, "审批类型不匹配");
			}
		}

		if (ApprovalStartOrg.ZB.getOrgFlag() == startOrg) {
			if (!submitApprovalFlowVO.getChainType().equals(ChainType.Headquarters.getCode())) {
				throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE, "审批类型不匹配");
			}

		}

		if (ApprovalStartOrg.QBFD.getOrgFlag() == startOrg) {
			if (!submitApprovalFlowVO.getChainType().equals(ChainType.Selfoperatedshop.getCode())
					&& !submitApprovalFlowVO.getChainType().equals(ChainType.Division.getCode())) {
				throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE, "审批类型不匹配");
			}
		}

		if (ApprovalStartOrg.JJMD.getOrgFlag() == startOrg) {
			if (!submitApprovalFlowVO.getChainType().equals(ChainType.Division.getCode())) {
				throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE, "审批类型不匹配");
			}

		}
	}*/




	public List<ApprovalFlow> checkRepeart(Long enterpriseId,String content) {

		Map<String, Object> map = new HashMap<>();
		map.put("enterpriseId", enterpriseId);
		map.put("content", content);

		List<ApprovalFlow> approvalFlows = approvalFlowMapper.selectByCheckRepeart(map);

		return approvalFlows;
	}


	public ApprovalAduitBO setApprover2Audit(ApprovalFlowAction approvalFlowAction, ApprovalFlowActionDetail currentActionDetail, ApprovalFlowActionDetail nextActionDetail){

		approvalFlowAction.setId(currentActionDetail.getApprovalFlowActionId());

		if(null != currentActionDetail){
			/**
			 * 设置当前审批人id和当前审批阶段
			 */
			approvalFlowAction.setCurrentApprover(currentActionDetail.getApproverId());
			approvalFlowAction.setCurrentApprovalStage(currentActionDetail.getApprovalStage());
			approvalFlowAction.setCurrentApproverName(currentActionDetail.getApproverName());
			approvalFlowAction.setCurrentApprovalDate(new Date());
			approvalFlowAction.setCurrentApprovalRole(currentActionDetail.getApprovalRoleId());

		}else {
			approvalFlowAction.setCurrentApprover(0L);
			approvalFlowAction.setCurrentApprovalStage("0");
			approvalFlowAction.setCurrentApproverName("0");
			approvalFlowAction.setCurrentApprovalDate(new Date());
			approvalFlowAction.setCurrentApprovalRole(0L);
		}


		/**
		 * 设置下个审批,审批人id和下个审批阶段
		 */
		if(null != nextActionDetail){
			approvalFlowAction.setNextApprover(nextActionDetail.getApproverId());
			approvalFlowAction.setNextApprovalStage(nextActionDetail.getApprovalStage());
			approvalFlowAction.setNextApproverName(nextActionDetail.getApproverName());
			approvalFlowAction.setNextApprovalDate(new Date());
			approvalFlowAction.setNextApprovalRole(nextActionDetail.getApprovalRoleId());
		}else {
			approvalFlowAction.setNextApprover(0L);
			approvalFlowAction.setNextApprovalStage("0");
			approvalFlowAction.setNextApproverName("0");
			approvalFlowAction.setNextApprovalDate(new Date());
			approvalFlowAction.setNextApprovalRole(0L);
		}


		/**
		 * 查询是否有前一个审批
		 */
		ApprovalFlowDetail prevDetailList = approvalFlowDetailMapper.getByFlowIdLeval(currentActionDetail.getFlowId(), currentActionDetail.getLevel()-1);
		/**
		 * 设置上个审批,审批人id和上个审批阶段
		 */
		if(null != prevDetailList){
			approvalFlowAction.setPreviousApprover(prevDetailList.getApproverId());
			approvalFlowAction.setPreviousApprovalStage(prevDetailList.getApprovalStage());
			approvalFlowAction.setPreviousApproverName(prevDetailList.getApproverName());
			approvalFlowAction.setPreviousApprovalDate(new Date());
			approvalFlowAction.setPreviousApprovalRole(prevDetailList.getRoleId());
		}else {
			approvalFlowAction.setPreviousApprover(0L);
			approvalFlowAction.setPreviousApprovalStage("0");
			approvalFlowAction.setPreviousApproverName("0");
			approvalFlowAction.setPreviousApprovalDate(new Date());
		}

		ApprovalAduitBO approvalAduitBO = new ApprovalAduitBO();
		approvalAduitBO.setApprovalFlowAction(approvalFlowAction);

		ArrayList<ApprovalFlowDetail> pList = new ArrayList<>();
		pList.add(prevDetailList);
		approvalAduitBO.setPrevDetailList(pList);
		return approvalAduitBO;

	}



	/**
	 * 如果是待审核状态当前审批人就是当前应该审批的人, 如果是非待审核,审核中的时候,当前审核人是当前操作审核的人,下个审核人才是需要审核的人员
	 * @param approvalFlowAction
	 * @param currentActionDetail
	 * @param level
	 * @return
	 */
	public ApprovalAduitBO setApprover4Apply(ApprovalFlowAction approvalFlowAction, ApprovalFlowActionDetail currentActionDetail, Integer level){
		/**
		 * 查询是否有下一个审批
		 */
		ApprovalFlowDetail currentDetailList = approvalFlowDetailMapper.getByFlowIdLeval(currentActionDetail.getFlowId(), level);
		ApprovalFlowDetail nextDetailList = approvalFlowDetailMapper.getByFlowIdLeval(currentActionDetail.getFlowId(), level+1);

		approvalFlowAction.setId(currentActionDetail.getApprovalFlowActionId());

		if(null != currentDetailList){
			/**
			 * 设置当前审批人id和当前审批阶段
			 */
			approvalFlowAction.setCurrentApprover(currentDetailList.getApproverId());
			approvalFlowAction.setCurrentApprovalStage(currentDetailList.getApprovalStage());
			approvalFlowAction.setCurrentApproverName(currentDetailList.getApproverName());
			approvalFlowAction.setCurrentApprovalDate(new Date());
			approvalFlowAction.setCurrentApprovalRole(currentDetailList.getRoleId());

		}
		/**
		 * 设置下个审批,审批人id和下个审批阶段
		 */
		if(null != nextDetailList){
			approvalFlowAction.setNextApprover(nextDetailList.getApproverId());
			approvalFlowAction.setNextApprovalStage(nextDetailList.getApprovalStage());
			approvalFlowAction.setNextApproverName(nextDetailList.getApproverName());
			approvalFlowAction.setNextApprovalDate(new Date());
			approvalFlowAction.setNextApprovalRole(nextDetailList.getRoleId());
		}else {
			approvalFlowAction.setNextApprover(0L);
			approvalFlowAction.setNextApprovalStage("0");
			approvalFlowAction.setNextApproverName("0");
			approvalFlowAction.setNextApprovalDate(new Date());
			approvalFlowAction.setNextApprovalRole(0L);
		}
		approvalFlowAction.setPreviousApprover(0L);
		approvalFlowAction.setPreviousApprovalStage("0");
		approvalFlowAction.setPreviousApproverName("0");
		approvalFlowAction.setPreviousApprovalDate(new Date());




		ApprovalAduitBO approvalAduitBO = new ApprovalAduitBO();
		approvalAduitBO.setApprovalFlowAction(approvalFlowAction);
		return approvalAduitBO;
	}

/*	public void audit(String content,Long id,Integer approvalStatus) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
		audit(content,id,null,approvalStatus);
	}*/
	public Object audit(String content,Long id ,Integer approvalStatus) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

		Object afterAudit = generateReflect(content, id, null, approvalStatus, "afterAudit");
		return afterAudit;
	}

	public Object generateReflect(String content,Long id , Integer status,Integer approvalStatus,String methodName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		Class<T> processor = ApprovalFlowContentModel.getProcessor(content);
		Object bean = springUtils.getBean(processor);

		Class aClass = bean.getClass();

		Class partypes[] = new Class[3];
		partypes[0] = Long.class;
		partypes[1] = Integer.class;
		partypes[2] = Integer.class;

		/**
		 * 该状态是属于终结状态
		 * 存在中间状态审批流,审批通过后不一定是已完成状态,所以需要配置一下分别对应的状态
		 */
		if(null == status)
			status = ApprovalFlowContentModel.getOverOrderStatus(content);

		Method method = aClass.getDeclaredMethod(methodName, partypes);
		Object invoke = method.invoke(bean, id, status, approvalStatus);
		return invoke;
	}

	public void initRequirePlanApprovalFlow(UserVO user, Long enterpriseId, Long parentId, String enterpriseName,
											 Integer chainType,boolean headControl) {

		ApprovalFlow af = approvalFlowMapper.selectByContent("0501", enterpriseId);
		if(null != af){
			return;
		}
		ApprovalFlow approvalFlow = new ApprovalFlow();
		approvalFlow.setEnterpriseId(enterpriseId);
		approvalFlow.setParentId(parentId);
		approvalFlow.setChainType(chainType);
		approvalFlow.setName("要货计划审批");
		// 要货计划
		approvalFlow.setContent("0501");
		// 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
		if(!headControl) {
			/**
			 * 分部控制
			 */
			approvalFlow.setStartOrg(4);
			approvalFlow.setStartOrgId(enterpriseId);
			approvalFlow.setStartOrgName(enterpriseName);
		}else {
			approvalFlow.setStartOrg(1);
		}


		approvalFlow.setDefaultFlag(SysType.SYSTEM.getCode());
		approvalFlow.setStatus(EnableStatus.ENABLE.getStatus());
		approvalFlow.setRemark("系统默认审批流");
		approvalFlow.setCreaterId(user.getUserId());
		approvalFlow.setCreaterCode(user.getUserCode());
		approvalFlow.setCreaterName(user.getUserName());
		approvalFlow.setCreateTime(new Date());
		approvalFlow.setModifierId(user.getUserId());
		approvalFlow.setModifierCode(user.getUserCode());
		approvalFlow.setModifierName(user.getUserName());
		approvalFlow.setUpdateTime(new Date());
		approvalFlowMapper.insertSelective(approvalFlow);
		Long flowId = approvalFlow.getId();

		ApprovalFlowDetail approvalFlowDetail = new ApprovalFlowDetail();
		approvalFlowDetail.setEnterpriseId(enterpriseId);
		approvalFlowDetail.setParentId(parentId);
		approvalFlowDetail.setFlowId(flowId);
		approvalFlowDetail.setApprovalStage("配送中心经理审批");
		// 审批机构（0-总部；1-发起机构；2-指定审核机构）
		if(!headControl) {
			/**
			 * 分部控制
			 */
			approvalFlowDetail.setApprovalOrg(2);
			approvalFlowDetail.setOrgId(enterpriseId);
			approvalFlowDetail.setOrgName(enterpriseName);
		}else {
			approvalFlowDetail.setApprovalOrg(0);
		}


		// 审批角色名称
		String roleName = "配送中心经理";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("enterpriseId", 0L);
		paramMap.put("roleName", roleName);
		SysRole sysRole = sysRoleMapper.selectDefaultRoleByParamMap(paramMap);
		// 审批角色ID
		Long roleId = sysRole.getId();
		approvalFlowDetail.setRoleId(roleId);
		approvalFlowDetail.setRoleName(roleName);
		approvalFlowDetail.setApproverId(null);
		approvalFlowDetail.setApproverCode(null);
		approvalFlowDetail.setApproverName(null);
		// 所在级别
		approvalFlowDetail.setLevel(1);
		// 最高级别
		approvalFlowDetail.setHighestLevel(3);
		approvalFlowDetail.setStatus(EnableStatus.ENABLE.getStatus());
		approvalFlowDetail.setRemark("系统默认审批流");
		approvalFlowDetail.setCreaterId(user.getUserId());
		approvalFlowDetail.setCreaterCode(user.getUserCode());
		approvalFlowDetail.setCreaterName(user.getUserName());
		approvalFlowDetail.setCreateTime(new Date());
		approvalFlowDetail.setModifierId(user.getUserId());
		approvalFlowDetail.setModifierCode(user.getUserCode());
		approvalFlowDetail.setModifierName(user.getUserName());
		approvalFlowDetail.setUpdateTime(new Date());
		approvalFlowDetailMapper.insertSelective(approvalFlowDetail);
	}


}
