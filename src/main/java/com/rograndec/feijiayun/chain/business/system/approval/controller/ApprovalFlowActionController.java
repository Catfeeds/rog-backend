package com.rograndec.feijiayun.chain.business.system.approval.controller;

import com.google.common.collect.Maps;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import com.rograndec.feijiayun.chain.business.system.approval.service.ApprovalFlowService;
import com.rograndec.feijiayun.chain.business.system.approval.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Api(description="审批操作接口")
@RestController
@RequestMapping("/approvalFlowAction")
@Validated
public class ApprovalFlowActionController {
	
	@Autowired
	private ApprovalFlowService approvalFlowService;
	@Autowired
	private ApprovalFlowComponent approvalFlowComponent;

	@ApiOperation(value="获取审批操作列表(已完成,被驳回)", notes="获取审批操作列表(已完成,被驳回)")
	@ApiImplicitParam(name = "approvalFlowActionVO", value = "用户审批流程查询参数对象", required = false, dataType = "ApprovalFlowActionVO")
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	public Result<Page<List<ApprovalFlowActionReturnVO>>> getList(@RequestBody ApprovalFlowActionVO approvalFlowActionVO, @ApiIgnore UserVO loginUser){
		Result<Page<List<ApprovalFlowActionReturnVO>>> result = new Result<>();

		Page<List<ApprovalFlowActionReturnVO>> page = new Page(approvalFlowActionVO.getPageNo(), approvalFlowActionVO.getPageSize());


		boolean checkApprovalControl = approvalFlowComponent.checkApprovalControl(loginUser.getChainType(), loginUser.getEnterpriseId());

		/**
		 * 数据库中存在为0的企业,为0的该企业为默认数据支撑用, 不做任何业务使用,所以默认为-1
		 */
		Long enterpriseId = loginUser.getEnterpriseId();

		if(checkApprovalControl){
			/**
			 * 总部控制
			 */
			Long headquartersEnterpriseId = getHeadquartersEnterpriseId(loginUser);
			enterpriseId = headquartersEnterpriseId;
		}


		/**
		 * 前端参数校验
		 * 1.审批内容content
		 * 2.审批操作复合状态statusRecom
		 * 
		 */
		
		//设置相关查询参数
		ApprovalFlowActionSearchVO vo = new ApprovalFlowActionSearchVO();
		vo.setEnterpriseId(enterpriseId);
		/*if(!loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
			vo.setEnterpriseId(loginUser.getParentId());
		}else {
			vo.setEnterpriseId(loginUser.getEnterpriseId());
		}*/

		vo.setCurrentUserId(loginUser.getUserId());
		
		String roleIdsStr = ""; 
	/*	Set<SysRoleVO> roles = loginUser.getRoles();*/

		List<Long> userRole = approvalFlowService.getUserRole(loginUser.getUserId());

		if(userRole==null) {
			userRole = new ArrayList<>();
		}
		
		if(userRole!=null && userRole.size()>0) {
			for(Long role : userRole) {
				roleIdsStr += role + ",";
			}
			roleIdsStr = roleIdsStr.substring(0, roleIdsStr.lastIndexOf(","));
		}
		vo.setRoleIdsStr(roleIdsStr);
		if(approvalFlowActionVO.getStatusRecom()!=3) {
			vo.setStatusRecom(approvalFlowActionVO.getStatusRecom());
		}
		vo.setContent(StringUtils.isEmpty(approvalFlowActionVO.getContent()) ? null : approvalFlowActionVO.getContent());
		vo.setName(approvalFlowActionVO.getName());
		vo.setStartOrgId(approvalFlowActionVO.getStartOrgId());
		vo.setStarterName(approvalFlowActionVO.getStarterName());
		vo.setApprovalOrgId(approvalFlowActionVO.getApprovalOrgId());
		vo.setApproverName(approvalFlowActionVO.getApproverName());
		
		List<ApprovalFlowAction> aFAList = approvalFlowService.getActionList(userRole,loginUser,vo,page);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		return result;
	}

	private Long getHeadquartersEnterpriseId(UserVO userVO){

		if(ChainType.Headquarters.getCode() == userVO.getChainType()){
			return userVO.getEnterpriseId();
		}else{
			return userVO.getParentId();
		}

	}

	@ApiOperation(value="获取审批操作列表(待审核)", notes="获取审批操作列表(待审核)")
	@ApiImplicitParam(name = "approvalFlowActionVO", value = "用户审批流程查询参数对象", required = false, dataType = "ApprovalFlowActionVO")
	@RequestMapping(value="/pendingApprovalList",method=RequestMethod.POST)
	public Result<List<ApprovalFlowActionReturnVO>> getPendingList(@RequestBody ApprovalFlowActionVO approvalFlowActionVO, @ApiIgnore UserVO loginUser){
		Result<List<ApprovalFlowActionReturnVO>> result = new Result<>();

		boolean checkApprovalControl = approvalFlowComponent.checkApprovalControl(loginUser.getChainType(), loginUser.getEnterpriseId());

		/**
		 * 数据库中存在为0的企业,为0的该企业为默认数据支撑用, 不做任何业务使用,所以默认为-1
		 */
		Long enterpriseId = loginUser.getEnterpriseId();

		if(checkApprovalControl){
			/**
			 * 总部控制
			 */
			Long headquartersEnterpriseId = getHeadquartersEnterpriseId(loginUser);
			enterpriseId = headquartersEnterpriseId;
		}

		/**
		 * 前端参数校验
		 * 1.审批内容content
		 * 2.审批操作复合状态statusRecom
		 *
		 */

		//设置相关查询参数
		ApprovalFlowActionSearchVO vo = new ApprovalFlowActionSearchVO();
		vo.setEnterpriseId(enterpriseId);
	/*	if(!loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
			vo.setEnterpriseId(loginUser.getParentId());
		}else {
			vo.setEnterpriseId(loginUser.getEnterpriseId());
		}*/

		vo.setCurrentUserId(loginUser.getUserId());

		String roleIdsStr = "";
	/*	Set<SysRoleVO> roles = loginUser.getRoles();*/

		List<Long> userRole = approvalFlowService.getUserRole(loginUser.getUserId());

		if(userRole==null) {
			userRole = new ArrayList<>();
		}

		if(userRole!=null && userRole.size()>0) {
			for(Long role : userRole) {
				roleIdsStr += role + ",";
			}
			roleIdsStr = roleIdsStr.substring(0, roleIdsStr.lastIndexOf(","));
		}
		vo.setRoleIdsStr(roleIdsStr);
		if(approvalFlowActionVO.getStatusRecom()!=3) {
			vo.setStatusRecom(approvalFlowActionVO.getStatusRecom());
		}
		vo.setContent(StringUtils.isEmpty(approvalFlowActionVO.getContent()) ? null : approvalFlowActionVO.getContent());
		vo.setName(approvalFlowActionVO.getName());
		vo.setStartOrgId(approvalFlowActionVO.getStartOrgId());
		vo.setStarterName(approvalFlowActionVO.getStarterName());
		vo.setApprovalOrgId(approvalFlowActionVO.getApprovalOrgId());
		vo.setApproverName(approvalFlowActionVO.getApproverName());

		List<ApprovalFlowActionReturnVO> pendingtActionList = approvalFlowService.getPendingtActionList(userRole, loginUser, vo);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, pendingtActionList);
		return result;
	}
	
	@ApiOperation(value="获取审批明细列表", notes="根据审批操作ID获取审批流程明细列表，用于审批操作列表单个级联查询审批流程明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "审批操作ID", required = true, dataType = "Long", paramType="path")
	})
	@RequestMapping(value="/getDetailList/{id}",method=RequestMethod.GET)
	public Result<ApprovalFlowActionDetailVO> getDetailList(
			@NotNull(message = "审批操作ID,不能为空") @Min(value=1,message="审批操作ID,不能小于1") 
			@PathVariable Long id){
		Result<ApprovalFlowActionDetailVO> result = new Result<ApprovalFlowActionDetailVO>();
		
		ApprovalFlowActionDetailVO aFADP = new ApprovalFlowActionDetailVO();
		
		List<ApprovalFlowActionDetail> aFADListForWeb = approvalFlowService.getActionDetailListByIdForWeb(id);
		List<ApprovalFlowActionDetail> aFADList = approvalFlowService.getActionDetailListById(id);
		
		aFADP.setStage(aFADList.size()-1);
		aFADP.setaFADList(aFADListForWeb);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, aFADP);
		return result;
	}
	
	@ApiOperation(value="审核操作", notes="用户对当前需要审批的流程进行审核操作")
	@ApiImplicitParam(name = "approvalResultVO", value = "用户审批流程审核结果对象", required = true, dataType = "ApprovalResultVO")
	@RequestMapping(value="/audit",method=RequestMethod.POST)
	public Result<Object> audit(@RequestBody @Valid ApprovalResultVO approvalResultVO,@ApiIgnore UserVO userVO) throws Exception {
		Result<Object> result = new Result<>();
		
		ApprovalFlowActionDetail afad = new ApprovalFlowActionDetail();
		afad.setId(approvalResultVO.getId());
		afad.setApprovalOpinion(approvalResultVO.getApprovalOpinion());
		afad.setApprovalResult(approvalResultVO.getApprovalResult());
		
		boolean updateFlag = approvalFlowService.audit(afad,userVO);
		
		if(!updateFlag) {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS_WITH_WARN.getCode(), "审核失败", Maps.newHashMap());
		}
		
		result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		return result;
	}



}
