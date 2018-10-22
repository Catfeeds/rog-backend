package com.rograndec.feijiayun.chain.business.system.approval.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlow;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowContent;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowDetail;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.business.system.approval.service.ApprovalFlowService;
import com.rograndec.feijiayun.chain.business.system.approval.valid.ApprovalIsEnable;
import com.rograndec.feijiayun.chain.business.system.approval.valid.ApprovalUpdateIdIsNotNull;
import com.rograndec.feijiayun.chain.business.system.approval.valid.CheckApprovalAdd;
import com.rograndec.feijiayun.chain.business.system.approval.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseAndChildrenVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.OrikaMapperFactory;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: ApprovalFlowContorller  
 * @Description: TODO(审批流程管理)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月22日 上午11:16:18  
 *
 */
@Api(description="审批流程管理接口")
@RestController
@RequestMapping("/approvalFlow")
@Slf4j
@Validated
public class ApprovalFlowController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApprovalFlowService approvalFlowService;

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private EnterpriseBusinessMapper enterpriseBusinessMapper;

	@Autowired
	private OrikaMapperFactory orikaMapperFactory;

	@ApiOperation(value="获取审批流程列表", notes="根据当前登录用户获取全部的审批流列表信息")
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public Result<List<ApprovalFlowReturnVO>> getApprovalFlowList(@ApiIgnore UserVO loginUser){
		Result<List<ApprovalFlowReturnVO>> result = new Result<>();

		EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());

		UserVO targetUserVo = new UserVO();
		targetUserVo = orikaMapperFactory.copyBean(targetUserVo,loginUser);
		/**
		 *审批管理（0-总部控制；1-自主控制）
		 */
		Long enterpriseId = null;
		if(0 == bus.getApprovalControl() && ChainType.Headquarters.getCode() != loginUser.getChainType()){
			enterpriseId = loginUser.getParentId();

			/**
			 * 查询审批内容的时候只需要根据chaintype判断,所以只修改chaintype
			 */
			targetUserVo.setChainType(ChainType.Headquarters.getCode());
		}else {
			enterpriseId = loginUser.getEnterpriseId();
		}



		List<ApprovalFlow> aFlist = approvalFlowService.getListByEId(enterpriseId);

		List<ApprovalFlowContent> contentList = approvalFlowService.getContentList(targetUserVo,null,null);

		List<ApprovalFlowReturnVO> approvalFlowReturnVOS = ApprovalFlowReturnVO.getApprovalFlowReturnVOs4ApprovalFlows(aFlist,contentList);

		if(!CollectionUtils.isEmpty(approvalFlowReturnVOS)) {
			for(ApprovalFlowReturnVO af : approvalFlowReturnVOS) {
				long flowId = af.getId();
				List<ApprovalFlowDetail> aFDList = approvalFlowService.getDetailListByFlowId(flowId);
				List<ApprovalFlowDetailReturnVO> approvalFlowDetailReturnVOS = ApprovalFlowDetailReturnVO.getApprovalFlowDetailsReturnVO4ApprovalFlowDetails(aFDList);
				af.setaFDList(approvalFlowDetailReturnVOS);
			}
		}
		
		//List<ApprovalFlow> aFlist = approvalFlowService.getListByEId(user.getEnterpriseId());
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, approvalFlowReturnVOS);
		return result;
	}
	
	@ApiOperation(value="修改校验", notes="修改审批流程前根据审批流ID进行校验")
	@ApiImplicitParam(name = "id", value = "审批流ID", required = true, paramType="path")
	@RequestMapping(value="/checkBeforeUpdate/{id}",method=RequestMethod.GET)
	public Result<ApprovalFlowUpdateCheckVO> checkBeforeUpdate(
			@NotNull(message = "审批流ID,不能为空") @Min(value=1,message="审批流ID,不能小于1")
			@PathVariable(required=true) Long id,@ApiIgnore UserVO loginUser){
		Result<ApprovalFlowUpdateCheckVO> result = new Result<ApprovalFlowUpdateCheckVO>();
		
		Map<String, Object> checkResult = approvalFlowService.checkBeforeUpdate(id,loginUser);
		ApprovalFlowUpdateCheckVO vo = new ApprovalFlowUpdateCheckVO();
		vo.setStatus((Boolean)checkResult.get("status"));
		vo.setMsg((String)checkResult.get("msg"));
		
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
		return result;
	}
	

	@ApiOperation(value="获取审批流程审批内容", notes="获取审批流审批内容，用于新建审批流程中审批内容下拉框")
	@RequestMapping(value="/{type}/getContent",method=RequestMethod.GET)
	public Result<List<TreeNodeVO>> getContent(
			@NotNull(message = "审批流程新增或者修改类型")
			@PathVariable(required=true) Integer type,
			@ApiIgnore UserVO userVO){
		Result<List<TreeNodeVO>> result = new Result<List<TreeNodeVO>>();
		List<TreeNodeVO> treeList = Lists.newArrayList();
		
		//sql可以按照level和sort排序
		List<ApprovalFlowContent> contentList = approvalFlowService.getContentList(userVO,userVO.getEnterpriseId(),type);
		if(contentList!=null && contentList.size()>0) {
			Map<String, Map<String, String>> groupNameMap = Maps.newLinkedHashMap();
			Map<String, List<TreeNodeVO>> dataMap = Maps.newLinkedHashMap();
			//分组
			for(ApprovalFlowContent bean : contentList) {
				if(bean.getIsLeaf()==1) { //如果是叶子节点
					String groupId = bean.getContentPid();
					
					if(!dataMap.containsKey(groupId)) { //如果map中不包含根节点，进行初始化
						List<TreeNodeVO> afcVoList = new ArrayList<TreeNodeVO>();
						dataMap.put(groupId, afcVoList);
					}
					List<TreeNodeVO> afcVoList = dataMap.get(groupId);
					TreeNodeVO afcVO = new TreeNodeVO();
					afcVO.setName(bean.getName());
					afcVO.setValue(bean.getContentId());
					afcVoList.add(afcVO);
				}else {
					Map<String, String> groupNameData = Maps.newHashMap();
					groupNameData.put("name", bean.getName());
					groupNameData.put("value", bean.getContentId());
					
					groupNameMap.put(bean.getContentId(), groupNameData);
				}
			}
			
			//组装成TreeNodeVO
			for(Map.Entry<String, List<TreeNodeVO>> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				TreeNodeVO treeNodeParent = new TreeNodeVO();
				Map<String, String> groupNameData = groupNameMap.get(key);
				if(CollectionUtils.isEmpty(groupNameData)){
					continue;
				}
				treeNodeParent.setName(groupNameData.get("name"));
				treeNodeParent.setValue(groupNameData.get("value"));
				treeNodeParent.setChildrens(entry.getValue());
				
				treeList.add(treeNodeParent);
			}
		}
		
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, treeList);
		return result;
	}
	

	@ApiOperation(value="获取角色树", notes="获取角色树，主要用于新建审批流程中岗位、角色选择框")
	@RequestMapping(value="/getRoleTree",method=RequestMethod.GET)
	public Result<List<TreeNodeVO>> getRoleTree(@RequestParam(required = false) Long enterpriseId,@ApiIgnore UserVO userVO){
		Result<List<TreeNodeVO>> result = new Result<List<TreeNodeVO>>();
		List<TreeNodeVO> treeList = Lists.newArrayList();

		if (null == enterpriseId){
			enterpriseId = userVO.getEnterpriseId();
		}

		List<SysPositionRoleDTO> contentList = approvalFlowService.getPositionRole(enterpriseId);
		if(contentList!=null && contentList.size()>0) {
			Map<String, Map<String, String>> groupNameMap = Maps.newLinkedHashMap();
			Map<String, List<TreeNodeVO>> dataMap = Maps.newLinkedHashMap();
			
			for(SysPositionRoleDTO bean : contentList) {
				long positionId = bean.getPositionId();
				String groupId = String.valueOf(positionId);
				
				Map<String, String> groupNameData = Maps.newHashMap();
				groupNameData.put("name", bean.getPositionName());
				groupNameData.put("value", String.valueOf(bean.getPositionId()));
				
				groupNameMap.put(groupId, groupNameData);
				
				if(!dataMap.containsKey(groupId)) {
					List<TreeNodeVO> dataList = new ArrayList<TreeNodeVO>();
					dataMap.put(groupId, dataList);
				}
				
				if(StringUtils.isNotEmpty(bean.getRoleName())) {
					List<TreeNodeVO> dataList = dataMap.get(groupId);
					
					TreeNodeVO data = new TreeNodeVO();
					data.setName(bean.getRoleName());
					data.setValue(String.valueOf(bean.getRoleId()));
					
					dataList.add(data);
				}
			}
			
			//组装成TreeNodeVO
			for(Map.Entry<String, List<TreeNodeVO>> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				TreeNodeVO treeNodeParent = new TreeNodeVO();
				Map<String, String> groupNameData = groupNameMap.get(key);
				treeNodeParent.setName(groupNameData.get("name"));
				treeNodeParent.setValue(groupNameData.get("value"));
				treeNodeParent.setChildrens(entry.getValue());
				
				treeList.add(treeNodeParent);
			}
			
		}
		
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, treeList);
		return result;
	}


	@ApiOperation(value="获取人员", notes="获取人员，主要用于新建审批流程中人员选择框")
	@RequestMapping(value="/getUserList",method=RequestMethod.GET)
	public Result<List<SysUserVO>> getUserList(
			@NotNull(message = "角色ID,不能为空") @Min(value=1,message="角色ID,不能小于1")
			@RequestParam Long roleId,
			@RequestParam(required=false) Long enterpriseId,
			@ApiIgnore UserVO userVO){
		Result<List<SysUserVO>> result = new Result<List<SysUserVO>>();

		if(null == enterpriseId || 0 == enterpriseId){
			enterpriseId = userVO.getEnterpriseId();
		}
		List<User> userList = approvalFlowService.getUserList(enterpriseId, roleId);

		List<SysUserVO> data = Lists.newArrayList();
		for(User bean : userList) {
			SysUserVO sup = new SysUserVO();
			sup.setLabel(bean.getName());
			sup.setValue(bean.getId());

			data.add(sup);
		}

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, data);
		return result;
	}
	
	@ApiOperation(value="创建审批流程", notes="根据approvalFlow对象创建审批流程")
    @RequestMapping(value="/postApprovalFlow", method=RequestMethod.POST)
    public Result<Object> postApprovalFlow(
			@RequestBody @Valid @CheckApprovalAdd SaveOrUpdateApprovalFlowVO approvalFlowVO, @ApiIgnore UserVO userVO) {
		Result<Object> result = new Result<Object>();
		try {
			approvalFlowService.saveApproalFlow(approvalFlowVO,userVO,true);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (Exception e){
			logger.error("创建审批流程:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL,"创建审批流程失败");
		}

		return result;
    }
	
	@ApiOperation(value="查询审批流程明细", notes="根据审批流程ID查询审批流程明细信息")
	@ApiImplicitParam(name = "flowId", value = "审批流ID", required = true, dataType = "Long", paramType="path")
	@RequestMapping(value="/getInfoById/{flowId}",method=RequestMethod.GET)
	public Result<ApprovalFlowUpdateVO> getInfoById(
			@NotNull(message = "审批流ID,不能为空") @Min(value=1,message="审批流ID,不能小于1")
			@PathVariable Long flowId,@ApiIgnore UserVO userVO){
		Result<ApprovalFlowUpdateVO> result = new Result<>();

		ApprovalFlowUpdateVO af = approvalFlowService.getById(flowId);
		if(af!=null) {
			List<ApprovalFlowDetailUdateVO> aFDList = approvalFlowService.getDetailListByReturnFlowId(flowId);
			af.setaFDList(aFDList);
		}
		
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, af);
		return result;
	}
	
	
	@ApiOperation(value="更新审批流程", notes="根据approvalFlow对象更新审批流程")
    @RequestMapping(value="/putApprovalFlow", method=RequestMethod.PUT)
    public Result<Object> putApprovalFlow(
    		@RequestBody
			@Valid
			@ApprovalUpdateIdIsNotNull
			@ApprovalIsEnable @CheckApprovalAdd SaveOrUpdateApprovalFlowVO approvalFlowVO,@ApiIgnore UserVO userVO) {
		Result<Object> result = new Result<Object>();

		try {
			approvalFlowService.saveApproalFlow(approvalFlowVO,userVO,false);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (ApprovalFlowBizException appe){
			logger.error("更新审批流程:"+appe.getMessage(),appe);
			result.setBizCodeFallInfo(SysCode.FAIL,appe.getMessage());
		}catch (Exception e){
			logger.error("更新审批流程:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL,"更新审批流程失败");
		}
		return result;
	}
	
	@ApiOperation(value="删除审批流程", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "flowId", value = "审批流程ID", required = true, dataType = "Long", paramType="path")
    @RequestMapping(value="/deleteApprovalFlow/{flowId}", method=RequestMethod.DELETE)
    public Result<Object> deleteApprovalFlow(
    		@NotNull(message = "审批流程ID,不能为空") @Min(value=1,message="审批流程ID,不能小于1")
    		@PathVariable Long flowId) {
		Result<Object> result = new Result<Object>();
		
		Map<String, Object> resultMap = approvalFlowService.checkDelete(flowId);
		if(!(Boolean)resultMap.get("status")) {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS_WITH_WARN.getCode(), (String)resultMap.get("msg"), Maps.newHashMap());
			
			return result;
		}else {
			approvalFlowService.deleteById(flowId);
		}
		
		result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


	@ApiOperation(value="根据企业id查询该企业底下所有子公司以及本身数据", notes = "根据企业id查询该企业底下所有子公司以及本身数据 | 开发者 翟伟", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/children", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<EnterpriseAndChildrenVO>> getEnterpriseChildren(@ApiIgnore UserVO userVO){
		Result<List<EnterpriseAndChildrenVO>> result = new Result<List<EnterpriseAndChildrenVO>>();
		try{

			List<Enterprise> enterprises = enterpriseService.getChildrens(userVO.getEnterpriseId());

			List<EnterpriseAndChildrenVO> enterpriseVOS = new ArrayList<>();
			for(Enterprise enterprise : enterprises){

				EnterpriseAndChildrenVO enterpriseVO = EnterpriseAndChildrenVO.getEnterprise4VO(enterprise);
				enterpriseVOS.add(enterpriseVO);
			}

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseVOS);
		}catch(Exception e){
			logger.error("根据企业id查询该企业底下所有子公司以及本身数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}


}
