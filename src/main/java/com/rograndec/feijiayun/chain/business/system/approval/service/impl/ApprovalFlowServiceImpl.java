package com.rograndec.feijiayun.chain.business.system.approval.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditResult;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalStartOrg;
import com.rograndec.feijiayun.chain.business.system.approval.dao.*;
import com.rograndec.feijiayun.chain.business.system.approval.entity.*;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.business.system.approval.service.ApprovalFlowService;
import com.rograndec.feijiayun.chain.business.system.approval.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.PositionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.config.ApprovalContentConfiguration;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @ClassName: ApprovalFlowServiceImpl  
 * @Description: TODO(审批流程管理service实现)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月22日 下午1:53:22  
 *
 */
@Service
public class ApprovalFlowServiceImpl implements ApprovalFlowService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApprovalFlowMapper approvalFlowMapper;
	@Autowired
	private ApprovalFlowDetailMapper approvalFlowDetailMapper;
	@Autowired
	private ApprovalFlowActionMapper approvalFlowActionMapper;
	@Autowired
	private ApprovalFlowActionDetailMapper approvalFlowActionDetailMapper;
	@Autowired
	private ApprovalFlowContentMapper approvalFlowContentMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private PositionMapper positionMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private UserComponent commonComponent;

	@Autowired
	private ApprovalFlowComponent approvalFlowComponent;
	@Autowired
	private UserRoleMapper userRoleMapper;


	@Override
	public List<Long>  getUserRole(Long userId){
		List<UserRole> userRoles = userRoleMapper.selectByUserId(userId);

		List<Long> roleIds = userRoles.stream().map(r -> r.getRoleId()).collect(Collectors.toList());

		return roleIds;
	}

	@Override
	public ApprovalFlowUpdateVO getById(long id) {
		return approvalFlowMapper.selectByReturn(id);
	}

	@Override
	public List<ApprovalFlow> getListByEId(long eId) {
		return approvalFlowMapper.getListByEId(eId);
	}

	@Override
	public List<ApprovalFlowDetail> getDetailListByFlowId(long flowId) {
		return approvalFlowDetailMapper.getListByFlowId(flowId);
	}
	@Override
	public List<ApprovalFlowDetailUdateVO> getDetailListByReturnFlowId(long flowId) {
		return approvalFlowDetailMapper.getListByReturnFlowId(flowId);
	}

	@Override
	public List<ApprovalFlowContent> getContentList(UserVO userVO,Long eId,Integer type) {

		List<String> contentCodes = ApprovalContentConfiguration.getContentCodes(userVO);
		List<ApprovalFlowContent> listByContents = approvalFlowContentMapper.getListByContents(contentCodes);

		if((null == type || type == 0) && null != eId) {
			List<ApprovalFlowContent> approvalFlowContents = approvalFlowContentMapper.selectByEnterpriseId(eId);
			Iterator<ApprovalFlowContent> iterator = listByContents.iterator();

			while (iterator.hasNext()) {
				String contentId = iterator.next().getContentId();
				for (ApprovalFlowContent ac : approvalFlowContents) {
					String hasContetnsId = ac.getContentId();
					if (hasContetnsId.equals(contentId)) {
						iterator.remove();
					}
				}


			}
		}

		return listByContents;

	}


	/**
	 * 
	 * @Title: checkDelete  
	 * @Description: TODO(删除审批流程前进行关联检查)  
	 * @param @param id
	 * @param @return    设定文件  
	 * @return Map<String,Object>    {"status": true, "msg": "允许删除"} {"status": false, "msg": "存在关联数据不允许删除"}
	 * @throws
	 */
	public Map<String, Object> checkDelete(long id) {
		Map<String, Object> retVal = Maps.newHashMap();
		
		/*
		 * 审批流程删除逻辑：
		 * 1.如果是系统默认审批流程，不允许删除
		 * 2.如果该审批流程产生过审批流（历史数据），不允许删除
		 * 
		 */
	/*	ApprovalFlow af = approvalFlowMapper.selectByPrimaryKey(id);
		if(af!=null) {
			if(ApprovalFlowDefaultFlag.FLAG_DEFAULT_YES.getValue()==af.getDefaultFlag()) {
				retVal.put("status", false);
				retVal.put("msg", "系统默认审批流程,不允许删除");
				
				return retVal;
			}
		}*/

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());

		List<ApprovalFlowAction> actionList = approvalFlowActionMapper.getListByFlowIdStatusRecom(id,
				ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue(),userVO.getEnterpriseId());
		if(actionList.size()>0) {
			retVal.put("status", false);
			retVal.put("msg", "存在关联数据，不允许修改");

			return retVal;
		}

		retVal.put("status", true);
		retVal.put("msg", "允许删除");
		
		return retVal;
	}

	/**
	 * 
	 * @Title: deleteById  
	 * @Description: TODO(根据ID删除审批流程)  
	 * @param @param id    审批流id 
	 * @return void    返回类型  
	 * @throws
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(long id) {
		if(approvalFlowMapper.deleteByPrimaryKey(id)<=0) {
			throw new RuntimeException("删除审批流程失败");
		}
		if(approvalFlowDetailMapper.deleteByFlowId(id)<=0) {
			throw new RuntimeException("删除审批流程失败");
		}
		
	}

	@Override
	public List<ApprovalFlowAction> getActionList(List<Long> userRole,UserVO loginUser,ApprovalFlowActionSearchVO vo, Page page) {
		com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
		List<ApprovalFlowAction> actionList = approvalFlowActionMapper.getActionList(vo);

		List<ApprovalFlowContent> list = approvalFlowContentMapper.getList();
		for(ApprovalFlowAction approvalFlowAction : actionList){
			for(ApprovalFlowContent approvalFlowContent : list){
				if(approvalFlowAction.getContent().equals(approvalFlowContent.getContentId())){
					approvalFlowAction.setContentDesc(approvalFlowContent.getName());
				}
			}
		}

		List<ApprovalFlowActionReturnVO> approvalFlowActions = getApprovalFlowActions(userRole,loginUser,actionList);

		page.setResult(approvalFlowActions);
		page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
		page.setTotalPage(hPage.getPages());
		return actionList;
	}

	@Override
	public List<ApprovalFlowActionReturnVO> getPendingtActionList(List<Long> userRole, UserVO loginUser, ApprovalFlowActionSearchVO vo) {


		List<ApprovalFlowActionDetailListVO> mustApprovalActionList = approvalFlowActionDetailMapper.getMustApprovalActionList(vo);

		if(CollectionUtils.isEmpty(mustApprovalActionList)){
			return new ArrayList<>();
		}

		List<Long> flowDetailIds = ApprovalFlowActionDetailListVO.getFlowDetailIds(mustApprovalActionList);

		if(CollectionUtils.isEmpty(flowDetailIds)){
			return new ArrayList<>();
		}

		List<Long> flowIds = ApprovalFlowActionDetailListVO.getFlowIds(mustApprovalActionList);

		if(CollectionUtils.isEmpty(flowIds)){
			return new ArrayList<>();
		}

		List<ApprovalFlow> approvalFlows = approvalFlowMapper.selectByIds(flowIds);
		/**
		 * 登录人企业类型：0-总部；1-自营店；2-加盟店
		 */
		Integer chainType = loginUser.getChainType();

		List<ApprovalFlowActionDetailListVO> approvalFlowActionDetailListVOS = new ArrayList<>();

		List<ApprovalFlowDetail> approvalFlowDetails = approvalFlowDetailMapper.selectByIds(flowDetailIds);

		for(ApprovalFlowDetail afa : approvalFlowDetails){
			for(ApprovalFlowActionDetailListVO afalvo : mustApprovalActionList){
				if(afa.getId().equals(afalvo.getDetailId())){
					/**
					 * 审批流程明细 审批机构（0-总部；1-发起机构；2-指定审核机构）
					 */
					Integer approvalOrg = afa.getApprovalOrg();
					/**
					 * 判断可以写成一条判断语句,因为写在一起太长,且不好调试,故分开多个写
					 */
					if(0 == approvalOrg && chainType == ChainType.Headquarters.getCode()){
						/**
						 * 审批机构为总部 ,并且当前登录人企业类型为总部
						 */
						approvalFlowActionDetailListVOS.add(afalvo);

					}else if(1 == approvalOrg){
						/**
						 * 审批机构为发起机构
						 */
						List<ApprovalFlowActionDetailListVO> afvs = setApprovalActionDetail4Org(loginUser, approvalFlows, afalvo, chainType);
						approvalFlowActionDetailListVOS.addAll(afvs);

					}else if(2 == approvalOrg && loginUser.getEnterpriseId().equals(afa.getOrgId())){
						/**
						 * 指定审核机构
						 */
						approvalFlowActionDetailListVOS.add(afalvo);
					}
				}

			}

		}

		List<ApprovalFlowActionReturnVO> approvalFlowActions = new ArrayList<>();
		List<Long> actionIds = ApprovalFlowActionDetailListVO.getActionIds(approvalFlowActionDetailListVOS);

		if(!CollectionUtils.isEmpty(actionIds)){
			List<ApprovalFlowAction> actionList = approvalFlowActionMapper.selectByIds(actionIds);


			/**
			 * 审批机构为:指定审核机构
			 */

			List<ApprovalFlowContent> list = approvalFlowContentMapper.getList();
			for(ApprovalFlowAction approvalFlowAction : actionList){
				for(ApprovalFlowContent approvalFlowContent : list){
					if(approvalFlowAction.getContent().equals(approvalFlowContent.getContentId())){
						approvalFlowAction.setContentDesc(approvalFlowContent.getName());
					}
				}
			}

			approvalFlowActions = getApprovalFlowActions(userRole,loginUser,actionList);
		}

		return approvalFlowActions;
	}


	private List<ApprovalFlowActionDetailListVO> setApprovalActionDetail4Org(UserVO loginUser, List<ApprovalFlow> approvalFlows,ApprovalFlowActionDetailListVO afalvo,Integer chainType){

		List<ApprovalFlowActionDetailListVO> approvalFlowActionDetailListVOS = new ArrayList<>();
		/**
		 * 审批机构为发起机构
		 */
		for(ApprovalFlow approvalFlow : approvalFlows){
			if(approvalFlow.getId().equals(afalvo.getFlowId())){
				/**
				 * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
				 */
				Integer startOrg = approvalFlow.getStartOrg();
				/**
				 * 判断可以写成一条判断语句,因为写在一起太长,且不好调试,故分开多个写
				 */
				if(startOrg == 0 && chainType == ChainType.Headquarters.getCode()){
					/**
					 * 发起机构为总部
					 */
					approvalFlowActionDetailListVOS.add(afalvo);
				}else if(startOrg == 1 && (chainType == ChainType.Selfoperatedshop.getCode() || chainType == ChainType.Division.getCode())){
					/**
					 * 发起机构为全部分店
					 */
					approvalFlowActionDetailListVOS.add(afalvo);
				}else if(startOrg == 2 && chainType == ChainType.Selfoperatedshop.getCode()){
					/**
					 * 发起机构为仅自营店
					 */
					approvalFlowActionDetailListVOS.add(afalvo);
				}else if(startOrg == 3 && chainType == ChainType.Division.getCode()){
					/**
					 * 发起机构为仅自营店
					 */
					approvalFlowActionDetailListVOS.add(afalvo);
				}else if(startOrg == 4 && loginUser.getEnterpriseId().equals(approvalFlow.getStartOrgId())){
					/**
					 * 发起机构为指定发起机构
					 */
					approvalFlowActionDetailListVOS.add(afalvo);
				}

			}
		}

		return approvalFlowActionDetailListVOS;
	}

	@Override
	public Map<Long,TreeMap<Integer,ApprovalFlowDetail>> getApprovalFlow4Ids(List<Long> ids){

		Map<Long,TreeMap<Integer,ApprovalFlowDetail>> approvalTreeMap = new HashMap<>();

		List<ApprovalFlowDetail> aFDList = approvalFlowDetailMapper.getListByFlowIds(ids);

		for(ApprovalFlowDetail approvalFlowDetail : aFDList){
			TreeMap<Integer,ApprovalFlowDetail> treeMap = approvalTreeMap.get(approvalFlowDetail.getId());
			if(CollectionUtils.isEmpty(treeMap)){
				treeMap = new TreeMap<>();
				treeMap.put(approvalFlowDetail.getLevel(),approvalFlowDetail);
				approvalTreeMap.put(approvalFlowDetail.getId(),treeMap);
			}else {
				treeMap.put(approvalFlowDetail.getLevel(),approvalFlowDetail);
			}
		}

		return approvalTreeMap;
	}

	@Override
	public List<ApprovalFlowActionReturnVO> getApprovalFlowActions(List<Long> userRole,UserVO loginUser,List<ApprovalFlowAction> aFAList){

		List<ApprovalFlowActionReturnVO> approvalFlowActionReturnVOS = new ArrayList<>();


		for(ApprovalFlowAction af : aFAList){

			List<ApprovalFlowDetail> approvalFlowDetails = approvalFlowDetailMapper.getListByFlowId(af.getFlowId());

/*
			List<ApprovalFlowActionDetail> approvalFlowActionDetails = approvalFlowActionDetailMapper.selectByActionFlowIdDesc("desc",af.getId());
*/
			List<ApprovalFlowActionDetail> approvalFlowActionDetails = approvalFlowActionDetailMapper.selectByActionFlowIdDesc("asc",af.getId());
			if(!CollectionUtils.isEmpty(approvalFlowActionDetails) && !CollectionUtils.isEmpty(approvalFlowDetails)){

				/**
				 * 按照level desc获取的审批操作,最上面一条肯定是当前还未审批的最新一条记录
				 */


				ApprovalFlowActionDetail lastApprovalFlowActionDetail = approvalFlowActionDetails.get(approvalFlowActionDetails.size()-1);
				String approvalMsg = lastApprovalFlowActionDetail.getApprovalOpinion();
				if(approvalFlowActionDetails.size() >= 2){
					/**
					 * 需要优化,审批意见和审批状态应该实时同步回写到审批头信息里
					 */
					ApprovalFlowActionDetail perApprovalFlowActionDetail = approvalFlowActionDetails.get(approvalFlowActionDetails.size()-2);
					approvalMsg = perApprovalFlowActionDetail.getApprovalOpinion();
				}

/*
				ApprovalFlowActionDetail ada = afc.get(approvalFlowActionDetails.size()-1);
*/


				/**
				 * 审批记录
				 */
				List<ApprovalFlowActionDetail> aFADList = new ArrayList<>();

				for(ApprovalFlowActionDetail ad : approvalFlowActionDetails){

					/**
					 * 按照level desc获取的审批操作,最新的几条都在最前面
					 */
					if(lastApprovalFlowActionDetail.getLevel().equals(ad.getLevel())){
						aFADList.add(ad);
						break;
					}else {
						aFADList.add(ad);
					}
				}

				for(ApprovalFlowDetail alfd : approvalFlowDetails){
					/**
					 * 取最新需要审批的的一条审批流明细
					 */
					if(lastApprovalFlowActionDetail.getLevel() < alfd.getLevel()
							&& af.getStatusRecom() != ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue()
							&& af.getStatusRecom() != ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue()
							&& af.getStatusRecom() != ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_CANCEL
							.getValue()){

						ApprovalFlowActionDetail approvalFlowActionDetail = new ApprovalFlowActionDetail();

						/**
						 *
						 Integer approvalResult = appDetail.getApprovalResult();
						 String approvalOpinion = appDetail.getApprovalOpinion();
						 */
						approvalFlowActionDetail.setApprovalStage(alfd.getApprovalStage());
						approvalFlowActionDetail.setApprovalTime(null);
						approvalFlowActionDetail.setApprovalOrgId(alfd.getOrgId());
						approvalFlowActionDetail.setApprovalOrgName(alfd.getOrgName());
						approvalFlowActionDetail.setApproverId(alfd.getApproverId());
						approvalFlowActionDetail.setApproverName(alfd.getApproverName());
						approvalFlowActionDetail.setApprovalRoleId(alfd.getRoleId());
						approvalFlowActionDetail.setApprovalRoleName(alfd.getRoleName());

						aFADList.add(approvalFlowActionDetail);
					}
				}


				ApprovalFlowActionReturnVO approvalFlowActionReturnVO4ApprovalFlowAction = ApprovalFlowActionReturnVO.getApprovalFlowActionReturnVO4ApprovalFlowAction(af);

				/**
				 * 审批阶段
				 */
				approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalStage(lastApprovalFlowActionDetail.getApprovalStage());

				if(null == lastApprovalFlowActionDetail.getApprovalOrgId()){

					/**
					 * 审批机构ID
					 */
					ApprovalFlow approvalFlow = approvalFlowMapper.selectByPrimaryKey(lastApprovalFlowActionDetail.getFlowId());
					approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalOrgId(Long.parseLong(String.valueOf(approvalFlow.getStartOrg())));

					/**
					 * 审批机构名称
					 */
					approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalOrgName(ApprovalStartOrg.getApprovalStartOrgEnum(approvalFlowActionReturnVO4ApprovalFlowAction.getApprovalOrgId().intValue()).getOrgName());

				}else {
					/**
					 * 审批机构ID
					 */
					approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalOrgId(lastApprovalFlowActionDetail.getApprovalOrgId());

					/**
					 * 审批机构名称
					 */
					approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalOrgName(lastApprovalFlowActionDetail.getApprovalOrgName());
				}


				Long approverId = lastApprovalFlowActionDetail.getApproverId();
				Long approvalRoleId = lastApprovalFlowActionDetail.getApprovalRoleId();

				if(approverId != null && 0 != approverId ){
					/**
					 * 审批人ID
					 */

					approvalFlowActionReturnVO4ApprovalFlowAction.setApproverId(lastApprovalFlowActionDetail.getApproverId());

					/**
					 * 审批人名称
					 */

					approvalFlowActionReturnVO4ApprovalFlowAction.setApproverName(lastApprovalFlowActionDetail.getApproverName());

				}else {
					/**
					 * 审批人ID
					 */

					approvalFlowActionReturnVO4ApprovalFlowAction.setApproverId(null);

					/**
					 * 审批人名称
 					 */

					approvalFlowActionReturnVO4ApprovalFlowAction.setApproverName(lastApprovalFlowActionDetail.getApprovalRoleName());


				}

				/**
				 * 审批意见
				 */
				approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalOpinion(approvalMsg);

				/**
				 * 审批结果（0-拒绝；1-同意；2-待审核）
				 */
				approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalResult(approvalFlowActionReturnVO4ApprovalFlowAction.getStatusRecom().intValue());

				if(approvalFlowActionReturnVO4ApprovalFlowAction.getStatusRecom().intValue() == (ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){
					approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalResultDesc("拒绝");
				}else {
					approvalFlowActionReturnVO4ApprovalFlowAction.setApprovalResultDesc(approvalFlowActionReturnVO4ApprovalFlowAction.getStatusRecomDesc());
				}

				approvalFlowActionReturnVO4ApprovalFlowAction.setLastActionId(lastApprovalFlowActionDetail.getId());
				approvalFlowActionReturnVOS.add(approvalFlowActionReturnVO4ApprovalFlowAction);
				List<ApprovalFlowActionDetailReturnVO> approvalFlowActionDetailReturnVOS = ApprovalFlowActionDetailReturnVO.getApprovalFlowActionDetailReturnVO4sApprovalFlowActionDetails(aFADList);
				approvalFlowActionReturnVO4ApprovalFlowAction.getaFADList().addAll(approvalFlowActionDetailReturnVOS);

			}

		}



		for(ApprovalFlowActionReturnVO av :  approvalFlowActionReturnVOS){

			List<ApprovalFlowActionDetailReturnVO> approvalFlowActionDetailReturnVOS = av.getaFADList();

			for(ApprovalFlowActionDetailReturnVO advo : approvalFlowActionDetailReturnVOS){

				if(advo.getStatus() != null && advo.getStatus().equals(ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue())
						&&
						(
								(userRole.contains(advo.getApprovalRoleId()) && (advo.getApproverId() == null || 0 == advo.getApproverId())) || loginUser.getUserId().equals(advo.getApproverId())
						)

						){

					av.setApproval(true);
					break;
				}else {
					av.setApproval(false);
				}
			}

		}


		return approvalFlowActionReturnVOS;

	}


	@Override
	public List<ApprovalFlowActionDetail> getActionDetailListByIdForWeb(long actionId) {
		
		List<ApprovalFlowActionDetail> aFADList = approvalFlowActionDetailMapper.getActionDetailListById(actionId);
		if(aFADList!=null && aFADList.size()>0) {
			ApprovalFlowAction ac = approvalFlowActionMapper.selectByPrimaryKey(actionId);
			List<ApprovalFlowDetail> aFDList = approvalFlowDetailMapper.getListByFlowId(ac.getFlowId());
			
			int aFADListSize = aFADList.size();
			int diff = aFDList.size() - aFADListSize;
			for(int i=0;i<diff;i++) {
				ApprovalFlowDetail afd = aFDList.get(aFADListSize+i);
				ApprovalFlowActionDetail afad = new ApprovalFlowActionDetail();
				afad.setApprovalStage(afd.getApprovalStage());
				afad.setLevel(afd.getLevel());
				
				aFADList.add(afad);
			}
		}
		
		return aFADList;
	}

	@Override
	public List<ApprovalFlowActionDetail> getActionDetailListByIdForWebs(List<ApprovalFlowAction> aFAList) {

		List<Long> ids = ApprovalFlowAction.getIds(aFAList);
		List<ApprovalFlowActionDetail> aFADList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(ids)){
			aFADList = approvalFlowActionDetailMapper.getActionDetailListByIds(ids);

		}
		return aFADList;

	}


	@Override
	public List<ApprovalFlowActionDetail> getActionDetailListById(long actionId) {
		return approvalFlowActionDetailMapper.getActionDetailListById(actionId);
	}

	@Override
	public List<ApprovalFlowActionDetail> getActionDetailListByIds(List<Long> list) {
		return approvalFlowActionDetailMapper.getActionDetailListByIds(list);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveApproalFlow(SaveOrUpdateApprovalFlowVO approvalFlowVO, UserVO userVO, boolean isSave) throws Exception {

		List<Long> ids = new ArrayList<>();
		List<Long> positionsIds = new ArrayList<>();
		List<Long> roleIds = new ArrayList<>();
		Set<Long> approvalFlowUsersIds = new HashSet<>();
		ids.add(userVO.getEnterpriseId());
		if(null != approvalFlowVO.getStartOrgId()){
			ids.add(approvalFlowVO.getStartOrgId());

		}

		for(SaveOrUpdateApprovalFlowDetailVO approvalFlowDetailPojo : approvalFlowVO.getApprovalFlowDetailDTOS()){
//			positionsIds.add(approvalFlowDetailPojo.getOrgId());
			roleIds.add(approvalFlowDetailPojo.getRoleId());
			approvalFlowUsersIds.add(approvalFlowDetailPojo.getApproverId());
		}

		List<SysRole> roles = sysRoleMapper.selectByIds(roleIds);
		for(SysRole sysRole : roles){
			positionsIds.add(sysRole.getPositionId());
		}
		List<Enterprise> enterprises = enterpriseMapper.selectByIds(ids);
		List<Position> positions = positionMapper.selectByIds(positionsIds);

		List<User> approvalFlowUsers = commonComponent.findUserByUserIds(approvalFlowUsersIds);

		Enterprise userEnterrprises = new Enterprise();
		List<Enterprise> detailstartEnterrprises = new ArrayList<>();
		Enterprise startEnterrprises = new Enterprise();

		for(Enterprise enterprise : enterprises){
			if(enterprise.getId().equals(userVO.getEnterpriseId())){
				userEnterrprises = enterprise;
			}
			if(enterprise.getId().equals(approvalFlowVO.getStartOrgId())){
				startEnterrprises = enterprise;
			}
			for(SaveOrUpdateApprovalFlowDetailVO approvalFlowDetailPojo : approvalFlowVO.getApprovalFlowDetailDTOS()){
				if(enterprise.getId().equals(approvalFlowDetailPojo.getOrgId())){
					detailstartEnterrprises.add(enterprise);
				}
			}
		}

		if(isSave){
			saveApprovalFlowAndDetails(userEnterrprises,startEnterrprises
					,positions,roles,approvalFlowUsers,approvalFlowVO
					, userVO,detailstartEnterrprises,isSave);
		}else {
//			ApprovalFlow.getApprovalFlowPojo4approvalFlow(approvalFlowPojo);
			updateApproalFlow(approvalFlowVO
					, userEnterrprises
					, startEnterrprises
					, userVO
					, detailstartEnterrprises
					, positions
					, roles
					, approvalFlowUsers,isSave);
		}


	}

	@Transactional(rollbackFor = Exception.class)
	public void saveApprovalFlowAndDetails(Enterprise userEnterrprises
			,Enterprise startEnterrprises
			,List<Position> positions
			,List<SysRole> roles
			,List<User> approvalFlowUsers
			,SaveOrUpdateApprovalFlowVO approvalFlowVO
			, UserVO userVO
			, List<Enterprise> detailstartEnterrprises , boolean isSave) throws Exception {
		ApprovalFlow approvalFlow = ApprovalFlow.getApprovalFlow4approvalFlowVO(approvalFlowVO
				,userEnterrprises
				,startEnterrprises
				,userVO,isSave);
		int insertCount = approvalFlowMapper.insertSelective(approvalFlow);
		if(insertCount > 0){
			List<ApprovalFlowDetail> approvalFlowDetails = ApprovalFlowDetail.getApprovalFlow4approvalFlowDetailVO(
					approvalFlowVO.getApprovalFlowDetailDTOS()
					,userEnterrprises,detailstartEnterrprises
					,userVO,approvalFlow
					,positions,roles
					,approvalFlowUsers,isSave
			);
			for(ApprovalFlowDetail approvalFlowDetail : approvalFlowDetails) {
				int detailCount = approvalFlowDetailMapper.insertSelective(approvalFlowDetail);
				if(detailCount <= 0 ){
					throw new RuntimeException("插入失败");
				}
			}
		}
	}

	public void updateApproalFlow(SaveOrUpdateApprovalFlowVO approvalFlowVO
			, Enterprise userEnterprise
			, Enterprise startEnterprise
			, UserVO user
			,List<Enterprise> detailstartEnterrprises
			,List<Position> positions
			,List<SysRole> roles
			,List<User> approvalFlowUsers,boolean isSave) throws Exception {


		Long approvalFlowId = approvalFlowVO.getId();
		if(null == approvalFlowId){
			throw new RuntimeException("修改审批流程失败,无效id");
		}
		List<Long> deleteIds = approvalFlowVO.getDeleteApprovalFlowIds();

//		Map<String, Object> map = checkDelete(approvalFlowPojo.getId());
//		if(map.get("status").equals("false")){
//			throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_DELETE,map.get("msg").toString());
//		}
		if(!CollectionUtils.isEmpty(deleteIds)){
			for(Long delId : deleteIds){
				approvalFlowDetailMapper.deleteByPrimaryKey(delId);
			}
		}


//		List<ApprovalFlowActionDetail> approvalFlowActionDetails = approvalFlowActionDetailMapper.selectByFlowId(approvalFlow.getId());

		ApprovalFlow  newApprovalFlow = ApprovalFlow.getApprovalFlowVO4approvalFlow(approvalFlowVO
				, userEnterprise
				, startEnterprise
				, user,isSave);

		newApprovalFlow.setContent(null);// 内容不可修改直接 赋值null
		approvalFlowMapper.updateByPrimaryKeySelective(newApprovalFlow);

		ApprovalFlow approvalFlow = approvalFlowMapper.selectByPrimaryKey(approvalFlowId);
		if(null == approvalFlow){
			throw new RuntimeException("修改审批流程失败,无效id");
		}

		List<ApprovalFlowDetail> details = ApprovalFlowDetail.getApprovalFlow4approvalFlowDetail(approvalFlowVO.getApprovalFlowDetailDTOS()
				, userEnterprise
				, detailstartEnterrprises
				, user
				, approvalFlow
				, positions
				, roles
				, approvalFlowUsers,false);

		for(ApprovalFlowDetail detail : details){
			if(null == detail.getId()){
				UserEnterpriseUtils.setUserCreateOrModify(detail,user,true);
				approvalFlowDetailMapper.insertSelective(detail);
			}else {

				approvalFlowDetailMapper.updateByPrimaryKeySelective(detail);
				List<SaveOrUpdateApprovalFlowDetailVO> approvalFlowDetailDTOS = approvalFlowVO.getApprovalFlowDetailDTOS();

				for(SaveOrUpdateApprovalFlowDetailVO dv : approvalFlowDetailDTOS){
					if(detail.getId().equals(dv.getId())){
						ApprovalFlowDetail newApprovalFlowDetail = new ApprovalFlowDetail();

						if(null == dv.getApproverId()){
							newApprovalFlowDetail.setId(detail.getId());
							newApprovalFlowDetail.setApproverId(null);
							newApprovalFlowDetail.setApproverName(null);
							newApprovalFlowDetail.setApproverCode(null);
							approvalFlowDetailMapper.updateByApprover(newApprovalFlowDetail);
						}

					}

				}


			}

		}
	}

	@Override
	public int updateAuditResult(ApprovalFlowActionDetail afad) {
		return approvalFlowActionDetailMapper.updateByPrimaryKeySelective(afad);
		/*return approvalFlowActionDetailMapper.updateAuditResult(afad);*/
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean audit(ApprovalFlowActionDetail afad,UserVO userVO) throws Exception {
		boolean retVal = true;
		/**
		 * 1.通过审批结果值判断 0->同意 -> 
		 */
		int approvalResult = afad.getApprovalResult();
		ApprovalFlowActionDetail currentActionDetail = approvalFlowActionDetailMapper.selectByPrimaryKey(afad.getId());

		ApprovalFlowAction action = approvalFlowActionMapper.selectByPrimaryKey(currentActionDetail.getApprovalFlowActionId());

		if(action.getStatusRecom().equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())
				&& action.getStatusRecom().equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())
				&& action.getStatusRecom().equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_CANCEL.getValue())){
			throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE,"该审批阶段已经审批过了,不允许再次审批");
		}

		Long userId = userVO.getUserId();

		List<UserRole> userRoles = userRoleMapper.selectByUserId(userId);

		ApprovalFlowDetail cDetailList = approvalFlowDetailMapper.getByFlowIdLeval(currentActionDetail.getFlowId(), currentActionDetail.getLevel());

		List<Long> userRoleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());

		if(!userRoleIds.contains(cDetailList.getRoleId())){
			throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE,"审批角色与当前登录人不匹配");
		}

		if(null != cDetailList.getApproverId() && !cDetailList.getApproverId().equals(userId)){
			throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE,"审批人员与当前登录人不匹配");
		}

		afad.setApprovalTime(new Date());
		afad.setApprovalOrgId(userVO.getEnterpriseId());
		afad.setApprovalOrgName(userVO.getEnterpriseName());
		afad.setApproverId(userVO.getUserId());
		afad.setApproverName(userVO.getUserName());

		if(approvalResult==1) {

			//同意
			afad.setStatus(ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());
			currentActionDetail.setStatus(ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());

			//更新审批操作明细记录
			if(updateAuditResult(afad) <= 0) {
				throw new RuntimeException("审核失败");
			}
			//查询是否有下个流程，如果有下个审批流程，则新增
			int level = currentActionDetail.getLevel();
			ApprovalFlowDetail nextDetailList = approvalFlowDetailMapper.getByFlowIdLeval(currentActionDetail.getFlowId(), level+1);

			ApprovalFlowActionDetail nextActionDetail = null;
			if(null != nextDetailList) {
				ApprovalFlowDetail nextDetail = nextDetailList;

				nextActionDetail = new ApprovalFlowActionDetail();

				nextActionDetail.setEnterpriseId(nextDetail.getEnterpriseId());
				nextActionDetail.setApprovalFlowActionId(currentActionDetail.getApprovalFlowActionId());
				nextActionDetail.setFlowId(currentActionDetail.getFlowId());
				nextActionDetail.setLevel(level+1);
				nextActionDetail.setApprovalStage(nextDetail.getApprovalStage());
				nextActionDetail.setApprovalOrgId(nextDetail.getOrgId());
				nextActionDetail.setApprovalOrgName(nextDetail.getOrgName());
				nextActionDetail.setApprovalRoleId(nextDetail.getRoleId());
				nextActionDetail.setApprovalRoleName(nextDetail.getRoleName());
				nextActionDetail.setApproverId(nextDetail.getApproverId() == null ? 0L : nextDetail.getApproverId() );
				nextActionDetail.setApproverName(nextDetail.getApproverName());
				nextActionDetail.setApprovalResult(ApprovalFlowAuditResult.DETAIL_RESULT_WAIT.getValue());
				nextActionDetail.setStatus(ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue());
				UserEnterpriseUtils.setUserCreateOrModify(nextActionDetail,userVO,true);
				
				if(approvalFlowActionDetailMapper.insert(nextActionDetail)<=0) {
					throw new RuntimeException("审核失败");
				}
			}
			int lastStatus = 0;
			int statusRecom = 0;
			if(null != nextActionDetail){

				lastStatus = nextActionDetail.getStatus();
			}else {
				lastStatus = afad.getStatus();
			}

			/**
			 * 每次审核阶段设置当前 下一个 当前 前一个审批人 审批阶段信息
			 */
			ApprovalFlowAction approvalFlowAction = new ApprovalFlowAction();
			approvalFlowComponent.setApprover2Audit(approvalFlowAction,currentActionDetail,nextActionDetail);

			/**
			 * 查询业务单据的
			 */

			Integer approvalStatus = 0;
			/**
			 * 审核已完成
			 */
			if(lastStatus==ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue()) {
				/**
				 * 判断是否有下个流程节点
				 */
				if(null == nextActionDetail){
					/**
					 * 如果没有表示已经完结
					 */
					statusRecom = ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue();
					approvalStatus = PurchaseStatus.FINISHED.getStatus();
				}else {
					/**
					 * 如果还有表示整体的审批状态还是处于审批中
					 */
					statusRecom = ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue();

				}

			}else if(lastStatus==ApprovalFlowAuditStatus.DETAIL_STATUS_UNPASS.getValue()) {
				/**
				 * 审批被驳回
				 */

				statusRecom = ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue();
				approvalStatus = PurchaseStatus.AUDIT_WITHDRAWAL.getStatus();

			}else if(lastStatus==ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue()) {

				/**
				 * 待审核
				 */
				statusRecom = ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue();

			}

			if(0 != approvalStatus){
				ApprovalFlowAction afa = approvalFlowActionMapper.selectByPrimaryKey(currentActionDetail.getApprovalFlowActionId());
				approvalFlowComponent.audit(afa.getContent(),afa.getDataId(),statusRecom);
			}
			//更新审批操作基表复合状态 approvalFlowAction
			long l = Long.parseLong(statusRecom + "");
			approvalFlowAction.setStatusRecom(l);
			approvalFlowAction.setId(currentActionDetail.getApprovalFlowActionId());
			if(approvalFlowActionMapper.updateByPrimaryKeySelective(approvalFlowAction) <= 0) {
				throw new RuntimeException("审核失败");
			}

		}else {
			afad.setStatus(ApprovalFlowAuditStatus.DETAIL_STATUS_UNPASS.getValue());
			ApprovalFlowAction afa = approvalFlowActionMapper.selectByPrimaryKey(currentActionDetail.getApprovalFlowActionId());
			approvalFlowComponent.audit(afa.getContent(),afa.getDataId(),ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue());
			afa.setStatusRecom(Long.parseLong(String.valueOf(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())));
			approvalFlowActionMapper.updateByPrimaryKeySelective(afa);
			//更新审批操作明细记录
			if(updateAuditResult(afad) <= 0) {
				throw new RuntimeException("审核失败");
			}
		}
		


		
		return retVal;
	}
	
	
	@Override
	public List<SysPositionRoleDTO> getPositionRole(long eId) {
		return approvalFlowMapper.getPositionRole(eId);
	}

	@Override
	public List<User> getUserList(long eId, long roleId) {
		return approvalFlowActionMapper.getUserListByRoleId(eId, roleId);
	}

	@Override
	public Map<String, Object> checkDisable(long flowId,UserVO userVO) {
		Map<String, Object> retVal = Maps.newHashMap();
		/**
		 * 如果是系统默认审批流程，不允许禁用
		 * 如果该审批流程关联的审批有未完成的，不允许禁用
		 */
	/*	ApprovalFlow af = approvalFlowMapper.selectByPrimaryKey(flowId);*/
	/*	if(af!=null) {
			if(ApprovalFlowDefaultFlag.FLAG_DEFAULT_YES.getValue()==af.getDefaultFlag()) {
				retVal.put("status", false);
				retVal.put("msg", "系统默认审批流程,不允许修改");
				
				return retVal;
			}
		}*/

		List<ApprovalFlowAction> actionList = approvalFlowActionMapper.getListByFlowIdStatusRecom(flowId,
				ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue(),userVO.getEnterpriseId());
		if(actionList.size()>0) {
			retVal.put("status", false);
			retVal.put("msg", "存在关联数据，不允许修改");

			return retVal;
		}
		
		retVal.put("status", true);
		retVal.put("msg", "允许删除");
		
		return retVal;
	}

	@Override
	public Map<String, Object> checkBeforeUpdate(long flowId,UserVO userVO) {
		Map<String, Object> retVal = Maps.newHashMap();
		/**
		 * 如果该审批流程关联的审批有未完成的，不允许修改
		 */
		List<ApprovalFlowAction> actionList = approvalFlowActionMapper.getListByFlowIdStatusRecom(flowId, 
				ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue(),userVO.getEnterpriseId());
		if(actionList!=null && actionList.size()>0) {
			retVal.put("status", false);
			retVal.put("msg", "有未完成的关联流程，不允许修改");
			
			return retVal;
		}
		
		retVal.put("status", true);
		retVal.put("msg", "允许修改");
		return retVal;
	}

}
