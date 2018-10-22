package com.rograndec.feijiayun.chain.business.system.approval.service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.approval.entity.*;
import com.rograndec.feijiayun.chain.business.system.approval.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @ClassName: ApprovalFlowService  
 * @Description: TODO(审批流程管理service接口)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月22日 下午2:18:30  
 *
 */
public interface ApprovalFlowService {

    List<Long>  getUserRole(Long userId);

    /**
	 * 
	 * @Title: getById  
	 * @Description: TODO(通过id查询审批流程)  
	 * @param @param id
	 * @param @return    设定文件  
	 * @return ApprovalFlow    返回类型  
	 * @throws
	 */
	ApprovalFlowUpdateVO getById(long id);
	
	/**
	 * 
	 * @Title: getListByEId  
	 * @Description: TODO(通过企业ID获取审批流基类信息)  
	 * @param @param eId
	 * @param @return    设定文件  
	 * @return List<ApprovalFlow>    返回类型  
	 * @throws
	 */
	List<ApprovalFlow> getListByEId(long eId);
	
	/**
	 * 
	 * @Title: getDetailListByFlowId  
	 * @Description: TODO(通过审批流ID查询审批流明细)  
	 * @param @param flowId
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowDetail>    返回类型  
	 * @throws
	 */
	List<ApprovalFlowDetail> getDetailListByFlowId(long flowId);

	List<ApprovalFlowDetailUdateVO> getDetailListByReturnFlowId(long flowId);

	/**
	 * 
	 * @Title: getContentList  
	 * @Description: TODO(获取审批流全部审批内容)  
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowContent>    返回类型  
	 * @throws
	 */
	List<ApprovalFlowContent> getContentList(UserVO userVO,Long eId,Integer type);
	
	/**
	 * 
	 * @Title: checkDelete  
	 * @Description: TODO(删除审批流程前进行关联检查)  
	 * @param @param id
	 * @param @return    设定文件  
	 * @return Map<String,Object>    {"status": true, "msg": "允许删除"} {"status": false, "msg": "存在关联数据不允许删除"}  
	 * @throws
	 */
	Map<String, Object> checkDelete(long id);
	
	/**
	 * 
	 * @Title: deleteById  
	 * @Description: TODO(根据ID删除审批流程)  
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws
	 */
	void deleteById(long id);
	
	/**
	 * 
	 * @Title: getList  
	 * @Description: TODO(获取审批操作相关列表信息)  
	 * @param @param approvalFlowActionVO
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowAction>    返回类型  
	 * @throws
	 */
	List<ApprovalFlowAction> getActionList(List<Long> userRole,UserVO loginUser,ApprovalFlowActionSearchVO vo,Page page);

    List<ApprovalFlowActionReturnVO> getPendingtActionList(List<Long> userRole, UserVO loginUser, ApprovalFlowActionSearchVO vo);

    Map<Long,TreeMap<Integer,ApprovalFlowDetail>> getApprovalFlow4Ids(List<Long> ids);

    List<ApprovalFlowActionReturnVO> getApprovalFlowActions(List<Long> userRole,UserVO loginUser,List<ApprovalFlowAction> aFAList);

    /**
	 * 
	 * @Title: getActionDetailListByIdForWeb  
	 * @Description: TODO(用于提供审批操作级联查询审批明细)  
	 * @param @param actionId
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowActionDetail>    返回类型  
	 * @throws
	 */
	List<ApprovalFlowActionDetail> getActionDetailListByIdForWeb(long actionId);

    List<ApprovalFlowActionDetail> getActionDetailListByIdForWebs(List<ApprovalFlowAction> aFAList);

    /**
	 * 
	 * @Title: getActionDetailListById  
	 * @Description: TODO(通过actionId查询审批操作明细数据)  
	 * @param @param actionId
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowActionDetail>    返回类型  
	 * @throws
	 */
	List<ApprovalFlowActionDetail> getActionDetailListById(long actionId);


    List<ApprovalFlowActionDetail> getActionDetailListByIds(List<Long> list);

    void saveApproalFlow(SaveOrUpdateApprovalFlowVO approvalFlowVO, UserVO userVO, boolean isSave) throws Exception;

	int updateAuditResult(ApprovalFlowActionDetail afad);
	
	/**
	 * 
	 * @Title: audit  
	 * @Description: TODO(审核操作处理)  
	 * @param @param afad
	 * @param @return    设定文件  
	 * @return boolean    返回类型  
	 * @throws
	 */
	boolean audit(ApprovalFlowActionDetail afad,UserVO userVO) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, Exception;
	
	/**
	 * 
	 * @Title: getPositionRoleGroup  
	 * @Description: TODO(获取岗位和角色关联数据)  
	 * @param @param enterpriseId 企业ID
	 * @param @return    设定文件  
	 * @return List<SysPositionRoleDTO>    返回类型  
	 * @throws
	 */
	List<SysPositionRoleDTO> getPositionRole(long eId);
	
	List<User> getUserList(long eId, long roleId);
	
	/**
	 * 
	 * @Title: checkDisable  
	 * @Description: TODO(禁用审批流程检查)  
	 * @param @param flowId
	 * @param @return    设定文件  
	 * @return Map<String,Object>    {"status": true, "msg": "允许禁用"} {"status": false, "msg": "存在关联数据不允许禁用"} 
	 * @throws
	 */
	Map<String, Object> checkDisable(long flowId,UserVO userVO);
	
	/**
	 * 
	 * @Title: checkUpdate  
	 * @Description: TODO(审批流程修改前校验)  
	 * @param @param flowId
	 * @param @return    设定文件  
	 * @return Map<String,Object>    {"status": true, "msg": "允许修改"} {"status": false, "msg": "有未完成的关联流程，不允许修改"}
	 * @throws
	 */
	Map<String, Object> checkBeforeUpdate(long flowId,UserVO userVO);

}
