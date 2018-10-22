package com.rograndec.feijiayun.chain.business.system.approval.dao;

import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlow;
import com.rograndec.feijiayun.chain.business.system.approval.vo.ApprovalFlowUpdateVO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SysPositionRoleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: ApprovalFlowMapper  
 * @Description: TODO(审批流基表dao)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月22日 下午1:56:39  
 *
 */
public interface ApprovalFlowMapper {
	
	/**
	 * 
	 * @Title: getListByEId  
	 * @Description: TODO(通过企业id查询审批流程)  
	 * @param @param eId
	 * @param @return    设定文件  
	 * @return List<ApprovalFlow>    返回类型  
	 * @throws
	 */
	List<ApprovalFlow> getListByEId(long eId);
	List<ApprovalFlow> getListByEId2(long eId);
	
	List<SysPositionRoleDTO> getPositionRole(long eId);
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ApprovalFlow record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ApprovalFlow record);

    /**
     *
     * @mbg.generated
     */
    ApprovalFlow selectByPrimaryKey(Long id);

    List<ApprovalFlow> selectByIds(List<Long> list);

    ApprovalFlowUpdateVO selectByReturn(Long id);
    ApprovalFlow selectByContent(@Param("content") String content,@Param("enterpriseId") Long enterpriseId);

    List<ApprovalFlow> selectByCheckRepeart(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ApprovalFlow record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ApprovalFlow record);

    ApprovalFlow getApprovalFlowByParam(@Param("enterpriseId") Long enterpriseId, @Param("defaultFlag")Integer defaultFlag, @Param("name")String name);

    List<ApprovalFlow> selectByEnterPriseId(Long enterpriseId);
}