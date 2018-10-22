package com.rograndec.feijiayun.chain.business.system.approval.dao;

import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowContent;

import java.util.List;

public interface ApprovalFlowContentMapper {
	
	/**
	 * 
	 * @Title: getList  
	 * @Description: TODO(获取审批流全部审批内容)  
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowContent>    返回类型  
	 * @throws
	 */
	List<ApprovalFlowContent> getList();

	List<ApprovalFlowContent> getListByContents(List<String> list);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ApprovalFlowContent record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ApprovalFlowContent record);

    /**
     *
     * @mbg.generated
     */
    ApprovalFlowContent selectByPrimaryKey(Long id);

    List<ApprovalFlowContent> selectByEnterpriseId(Long enterpriseId);

    List<ApprovalFlowContent> getListExclude(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ApprovalFlowContent record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ApprovalFlowContent record);
}